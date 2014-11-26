package AWT.UI;

import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import UI.MenuButton;
import UI.MouseUserDevice;
import UI.StaticListMenu;
import data.shapes.Point;
import file.LuaScriptFiler;
import generic.DataModificationListener;
import generic.VoidFunctionPointer;

public class AWTFileMenu extends AWTDropdownMenu {

	private LuaScriptFiler filer;
	
	public static String SAVE_STRING = "SAVE";
	public static String LOAD_STRING = "LOAD";
	
	private AWTFileChooser fileChooser;
	private boolean	isSaving;
	private boolean isLoading;
	
	public final VoidFunctionPointer SAVE = new VoidFunctionPointer() {
		@Override
		public void call() {
			fileChooser.chooseFile();
			isSaving = true;
		}
	};
	
	public final VoidFunctionPointer LOAD = new VoidFunctionPointer() {
		@Override
		public void call() {
			fileChooser.chooseFile();
			isLoading = true;
		}
	};
	
	public AWTFileMenu(LuaScriptFiler FILER) {
		super();
		filer = FILER;
		fileChooser = new AWTFileChooser();
		
		DataModificationListener fileSelected = new DataModificationListener(){
			@Override
			protected void whenMyDataIsModifiedExternally() {
				if(isSaving){
					try {
						filer.save(new FileOutputStream(fileChooser.getChosenFile()));
					} catch (FileNotFoundException fnf) {
						fnf.printStackTrace();
					}
					isSaving = false;
				}
				if(isLoading){
					try {
						filer.load(new FileInputStream(fileChooser.getChosenFile()));
					} catch (FileNotFoundException fnf) {
						fnf.printStackTrace();
					}
					isLoading = false;
				}
			}
		};
		
		fileChooser.addDataModificationListener(fileSelected);
		
		isSaving = false;
		isLoading = false;
		
		setup();
	}
	
	private void setup() {

		AWTMenuButton fileButton = new AWTMenuButton();
		fileButton.textLabel.setText("FILE");
		fileButton.textLabel.center();
		fileButton.makeSuggestedBoxRelativeToPoint(0, 0, 2, 2);
		
		AWTMenuButton saveButton = new AWTMenuButton();
		saveButton.textLabel.setText(SAVE_STRING);
		saveButton.textLabel.center();
		saveButton.debounceTimer.setDebounceTime_sec(1);
		saveButton.setButtonPressedFunction(SAVE);
		
		AWTMenuButton openButton = new AWTMenuButton();
		openButton.textLabel.setText(LOAD_STRING);
		openButton.textLabel.center();
		openButton.debounceTimer.setDebounceTime_sec(1);
		openButton.setButtonPressedFunction(LOAD);
		
		MenuButton[] fileMenuOptions = new MenuButton[] { saveButton, openButton };
		StaticListMenu list = new StaticListMenu();
		list.setButtons(fileMenuOptions);
		list.setPostition(new Point(2,2 + fileButton.getHeight()));
		list.setButtonOffset(2);
		list.setButtonDimensions(fileButton.getWidth() - 4, fileButton.getHeight() - 4);
		
		setRoot(fileButton);
		setMenu(list);
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		super.update(mouse);
		fileChooser.update(mouse);
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		menuDrawer.drawFileChooser(fileChooser);
	}
}
