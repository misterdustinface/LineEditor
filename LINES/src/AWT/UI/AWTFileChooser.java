package AWT.UI;

import generic.DataModificationNotifier;
import generic.VoidFunctionPointer;

import java.awt.Graphics2D;
import java.io.File;

import AWT.rendering.AWTMenuDrawer;
import UI.MouseUserDevice;
import data.shapes.Grid;
import data.shapes.Point;

public class AWTFileChooser extends DataModificationNotifier implements AWTUILayer {

	public static String START_DIRECTORY = System.getProperty("user.home");
	private File  			  filepath;
	private AWTStaticGridMenu fileListing;
	private AWTMenuButton     upButton;
	private AWTMenuButton	  exitButton;
	private AWTMenuDrawer 	  menuDrawer;
	
	public int gridRows = 5;
	public int gridCols = 5;
	
	public AWTFileChooser() {		
		upButton = new AWTMenuButton();
		upButton.textLabel.setText("UP");
		upButton.textLabel.center();
		//upButton.makeSuggestedBoxRelativeToPoint(2, 2);
		upButton.makeBoxRelativeToPoint(0, 0, 2, 2, 120, 40);
		upButton.setButtonPressedFunction(new VoidFunctionPointer() {
			@Override
			public void call() {
				chooseFile(filepath.getParent());
			}
		});
		
		exitButton = new AWTMenuButton();
		exitButton.textLabel.setText("EXIT");
		exitButton.textLabel.center();
		//exitButton.makeSuggestedBoxRelativeToPoint(2, 2);
		exitButton.makeBoxRelativeToPoint(122, 0, 2, 2, 120, 40);
		exitButton.setButtonPressedFunction(new VoidFunctionPointer() {
			@Override
			public void call() {
				exit();
			}
		});
		
		fileListing = new AWTStaticGridMenu(new Grid(gridRows,gridCols));
		fileListing.setPostition(new Point(2,48));
		fileListing.setButtonDimensions(80, 40);
		fileListing.setButtonOffset(4);
		
		menuDrawer = new AWTMenuDrawer();
	}
	
	public void chooseFile() {
		chooseFile(START_DIRECTORY);
	}
	
	public File getChosenFile() {
		return filepath;
	}

	public void exit() {
		notifyListeners();
		fileListing.clearButtons();
		System.out.println("DONE");
	}
	
	private void chooseFile(String path) {
		filepath = new File(path);
		if(filepath.isDirectory()) {
			refreshFileListing();
		} else if (filepath.isFile()) {
			exit();
		}
	}
	
	private void refreshFileListing() {
		final File[] files = filepath.listFiles();
		fileListing.clearButtons();
		
		gridRows = files.length % gridCols;
		fileListing.setGridDimensions(gridRows, gridCols);
	
		for(File file : files) {
			if(! file.isHidden()) {
				AWTMenuButton button = new AWTMenuButton();
				button.textLabel.setText(file.getName());
				button.textLabel.center();
				button.makeSuggestedBoxRelativeToPoint(fileListing.getX(), fileListing.getY());
				
				final String PATH = file.getPath();
				button.setButtonPressedFunction(new VoidFunctionPointer() {
					@Override
					public void call() {
						chooseFile(PATH);
					}
				});
				
				fileListing.addButton(button);
			}
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		//fileListing.render(g);
		menuDrawer.setGraphics(g);		
		menuDrawer.drawButton(upButton);
		menuDrawer.drawButton(exitButton);
	}

	@Override
	public void update(MouseUserDevice mouse) {
		upButton.update(mouse);
		exitButton.update(mouse);
		//try {
		//	fileListing.update(mouse);
		//} catch (java.util.ConcurrentModificationException cme) {}
	}
	
}
