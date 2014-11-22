package UI.AWT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import UI.MenuButton;
import UI.StaticListMenu;
import data.shapes.Point;
import file.LuaScriptFiler;
import generic.VoidFunctionPointer;

public class AWTFileMenu extends AWTDropdownMenu {

	private LuaScriptFiler 	filer;
	private JFileChooser 	exporter;
	private JFileChooser 	loader;

	public static String SAVE_STRING = "SAVE";
	public static String LOAD_STRING = "LOAD";
	
	public final VoidFunctionPointer SAVE = new VoidFunctionPointer(){

		@Override
		public void call() {
			try {
				System.out.println("CALLED");
				if(exporter.showSaveDialog(new JPanel()) == JFileChooser.APPROVE_OPTION){
					filer.save(new FileOutputStream(exporter.getSelectedFile()));
				}
				System.out.println("FINISHED");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	};
	
	public final VoidFunctionPointer LOAD = new VoidFunctionPointer(){

		@Override
		public void call() {
			try {
				if(loader.showOpenDialog(new JPanel()) == JFileChooser.APPROVE_OPTION){
					filer.load(new FileInputStream(loader.getSelectedFile()));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	};
	
	public AWTFileMenu(LuaScriptFiler FILER) {
		super();
		filer = FILER;
		setup();
	}
	
	private void setup() {
		
		exporter = new JFileChooser();
		exporter.setApproveButtonText(SAVE_STRING);
		exporter.setApproveButtonMnemonic(SAVE_STRING.charAt(0));
		exporter.setFileHidingEnabled(true);
		
		loader = new JFileChooser();
		loader.setApproveButtonText(LOAD_STRING);
		loader.setApproveButtonMnemonic(LOAD_STRING.charAt(0));
		loader.setFileHidingEnabled(true);
		
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
		
		final MenuButton[] fileMenuOptions = new MenuButton[] { saveButton, openButton };
		StaticListMenu list = new StaticListMenu();
		list.setButtons(fileMenuOptions);
		list.setPostition(new Point(2,2 + fileButton.getHeight()));
		list.setButtonOffset(2);
		list.setButtonDimensions(fileButton.getWidth() - 4, fileButton.getHeight() - 4);
		
		setRoot(fileButton);
		setMenu(list);
	}
}
