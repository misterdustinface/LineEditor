package AWT.UI;

import generic.VoidFunctionPointer;
import UI.MenuButton;
import UI.StaticListMenu;
import data.shapes.Point;

public class AWTToggleLayersMenu extends AWTDropdownMenu {

	private AWTEditorPanel editorPanel;
	
	public AWTToggleLayersMenu(AWTEditorPanel EDITOR_PANEL) {
		super();
		editorPanel = EDITOR_PANEL;
		setup();
	}
	
	public void addMenuItemToggleUI(String title, final AWTUILayer ui) {
		AWTMenuButton toggleButton = new AWTMenuButton();
		toggleButton.textLabel.setText(title);
		toggleButton.textLabel.center();
		toggleButton.debounceTimer.setDebounceTime_sec(1);
		toggleButton.setButtonPressedFunction(new VoidFunctionPointer(){
			@Override
			public void call() {
				editorPanel.toggleLayer(ui);
			}
		});
		menu.addButton(toggleButton);
	}
	
	private void setup() {

		AWTMenuButton optionsButton = new AWTMenuButton();
		optionsButton.textLabel.setText("TOGGLE");
		optionsButton.textLabel.center();
		optionsButton.makeSuggestedBoxRelativeToPoint(140, 0, 2, 2);
		
		MenuButton[] menuButtons = new MenuButton[] { };
		StaticListMenu list = new StaticListMenu();
		list.setButtons(menuButtons);
		list.setPostition(new Point(142,2 + optionsButton.getHeight()));
		list.setButtonOffset(2);
		list.setButtonDimensions(optionsButton.getWidth() - 4, optionsButton.getHeight() - 4);
		
		setRoot(optionsButton);
		setMenu(list);
	}

}
