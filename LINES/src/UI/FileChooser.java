package UI;

import java.io.File;

import data.shapes.Grid;
import data.shapes.Point;
import generic.DataModificationNotifier;
import generic.VoidFunctionPointer;

public abstract class FileChooser extends DataModificationNotifier implements UILayer {
	public static String START_DIRECTORY = System.getProperty("user.home");
	public int gridRows = 4;
	public int gridCols = 4;
	
	protected StaticGridMenu 	fileListing;
	protected MenuButton     	upButton;
	protected MenuButton	  	exitButton;	
	protected boolean shouldDisplayAndUpdate;
	
	private File filepath;
	
	public FileChooser() {		
		upButton = makeUpButton();
		upButton.setButtonPressedFunction(new VoidFunctionPointer() {
			@Override
			public void call() {
				chooseFile(filepath.getParent());
			}
		});
		exitButton = makeExitButton();
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
	
	protected abstract MenuButton makeUpButton();
	protected abstract MenuButton makeExitButton();
	protected abstract MenuButton makeFileChooserButton(File file);
	
	
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
		}
	}
}
