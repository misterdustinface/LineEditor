package UI;

import generic.DataModificationNotifier;
import generic.VoidFunctionPointer;

import java.io.File;

import data.shapes.Grid;
import data.shapes.Point;

public abstract class FileChooser extends DataModificationNotifier implements UILayer {
	public static String START_DIRECTORY = System.getProperty("user.home");
	public int gridRows = 4;
	public int gridCols = 4;
	
	private StaticGridMenu 	fileListing;
	private MenuButton     	upButton;
	private MenuButton	  	exitButton;	
	private boolean shouldDisplayAndUpdate;
	
	private File filepath;
	
	private static String UP_BUTTON_TEXT   = "UP";
	private static String EXIT_BUTTON_TEXT = "EXIT";
	
	public FileChooser() {		
		
		upButton = makeButton();
		upButton.textLabel.setText(UP_BUTTON_TEXT);
		upButton.textLabel.center();
		upButton.makeBoxRelativeToPoint(0, 0, 2, 2, 120, 40);
		upButton.setButtonPressedFunction(new VoidFunctionPointer() {
			@Override
			public void call() {
				chooseFile(filepath.getParent());
			}
		});
		
		exitButton = makeButton();
		exitButton.textLabel.setText(EXIT_BUTTON_TEXT);
		exitButton.textLabel.center();
		exitButton.makeBoxRelativeToPoint(122, 0, 2, 2, 120, 40);
		exitButton.setButtonPressedFunction(new VoidFunctionPointer() {
			@Override
			public void call() {
				exit();
			}
		});
		
		fileListing = new StaticGridMenu(new Grid(gridRows,gridCols));
		fileListing.setPostition(new Point(2,48));
		fileListing.setButtonDimensions(140, 40);
		fileListing.setButtonOffset(4);
		
		shouldDisplayAndUpdate = false;
	}
	
	public boolean shouldDisplayAndUpdate() { return shouldDisplayAndUpdate; }
	public StaticGridMenu getFileListing() { return fileListing; }
	public MenuButton getUpButton() { return upButton; }
	public MenuButton getExitButton() { return exitButton; }
	
	protected abstract MenuButton makeButton();
	
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
				MenuButton button = makeFileChooserButton(file);
				fileListing.addButton(button);
				final String PATH = file.getPath();
				button.setButtonPressedFunction(new VoidFunctionPointer() {
					@Override
					public void call() {
						chooseFile(PATH);
					}
				});
			}
		}
	}
	
	private MenuButton makeFileChooserButton(File file) {
		MenuButton button = makeButton();
		button.textLabel.setMaxTextWidth(20);
		button.textLabel.setText(file.getName());
		button.textLabel.center();
		button.makeSuggestedBoxRelativeToPoint(getFileListing().getX(), getFileListing().getY());
		return button;
	}
	
	private void resizeGridToFitFiles(int numberOfFiles) {
		gridCols = gridCols < 1 ? 1 : gridCols;
		gridRows = numberOfFiles / gridCols;
		gridRows = gridRows < 1 ? 1 : gridRows;
		fileListing.setGridDimensions(gridRows, gridCols);
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		if(shouldDisplayAndUpdate) {
			upButton.update(mouse);
			exitButton.update(mouse);
			try {
				fileListing.update(mouse);
			} catch (Exception e) {}
			mouse.intercept();
		}
	}
}
