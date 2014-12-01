package AWT.UI;

import generic.VoidFunctionPointer;
import UI.MenuButton;
import UI.StaticListMenu;
import data.shapes.Point;

public class AWTViewportOptionsMenu extends AWTDropdownMenu {

	public AWTViewportOptionsMenu(final AWTViewport VIEWPORT) {
		super();

		AWTMenuButton optionsButton = new AWTMenuButton();
		optionsButton.textLabel.setText("OPTIONS");
		optionsButton.textLabel.center();
		optionsButton.makeSuggestedBoxRelativeToPoint(280, 0, 2, 2);
		
		AWTMenuButton resetZoom = new AWTMenuButton();
		resetZoom.textLabel.setText("RESET ZOOM");
		resetZoom.textLabel.center();
		resetZoom.setButtonPressedFunction(new VoidFunctionPointer() {
			@Override
			public void call() {
				VIEWPORT.resetToDefaultZoom();
			}
		});
		
		AWTMenuButton gotoOrigin = new AWTMenuButton();
		gotoOrigin.textLabel.setText("TO ORIGIN");
		gotoOrigin.textLabel.center();
		gotoOrigin.setButtonPressedFunction(new VoidFunctionPointer() {
			@Override
			public void call() {
				VIEWPORT.resetToOrigin();
			}
		});
		
		MenuButton[] menuButtons = new MenuButton[] {resetZoom, gotoOrigin};
		StaticListMenu list = new StaticListMenu();
		list.setButtons(menuButtons);
		list.setPostition(new Point(282,2 + optionsButton.getHeight()));
		list.setButtonOffset(2);
		list.setButtonDimensions(optionsButton.getWidth() - 4, optionsButton.getHeight() - 4);
		
		setRoot(optionsButton);
		setMenu(list);
	}
	
	
}
