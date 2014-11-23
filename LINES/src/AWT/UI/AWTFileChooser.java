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
	
	private boolean shouldDisplayAndUpdate;
	
	public int gridRows = 4;
	public int gridCols = 4;
	
	public AWTFileChooser() {		
		makeUpButton();
		makeExitButton();
		
		fileListing = new AWTStaticGridMenu(new Grid(gridRows,gridCols));
		fileListing.setPostition(new Point(2,48));
		fileListing.setButtonDimensions(140, 40);
		fileListing.setButtonOffset(4);
		
		menuDrawer = new AWTMenuDrawer();
		
		shouldDisplayAndUpdate = false;
	}
	
	private void makeUpButton() {
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
	}
	private void makeExitButton() {
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
	}
	
	public void chooseFile() {
		shouldDisplayAndUpdate = true;
		chooseFile(START_DIRECTORY);
	}

	public void exit() {
		shouldDisplayAndUpdate = false;
		notifyListeners();
		fileListing.clearButtons();
	}
	
	public File getChosenFile() { return filepath; }
	
	private void chooseFile(String path) {
		filepath = new File(path);
		if(filepath.isDirectory()) {
			refreshFileListing();
		} else if (filepath.isFile()) {
			exit();
		}
	}
	
	private void refreshFileListing() {
		fileListing.clearButtons();
		
		final File[] files = filepath.listFiles();
		resizeGridToFitFiles(files.length);
		
		for(File file : files) {
			if(! file.isHidden()) {
				fileListing.addButton(makeFileChooserButton(file));
			}
		}
	}
	
	private void resizeGridToFitFiles(int numberOfFiles) {
		gridCols = gridCols < 1 ? 1 : gridCols;
		gridRows = numberOfFiles / gridCols;
		gridRows = gridRows < 1 ? 1 : gridRows;
		fileListing.setGridDimensions(gridRows, gridCols);
	}
	
	private AWTMenuButton makeFileChooserButton(File file) {
		AWTMenuButton button = new AWTMenuButton();
		button.textLabel.setMaxTextWidth(20);
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
		return button;
	}
	
	@Override
	public void render(Graphics2D g) {		
		if(shouldDisplayAndUpdate) {
			fileListing.render(g);
			menuDrawer.setGraphics(g);		
			menuDrawer.drawButton(upButton);
			menuDrawer.drawButton(exitButton);
		}
	}

	@Override
	public void update(MouseUserDevice mouse) {
		if(shouldDisplayAndUpdate) {
			upButton.update(mouse);
			exitButton.update(mouse);
			try {
				fileListing.update(mouse);
			} catch (Exception e) {}
		}
	}
	
}