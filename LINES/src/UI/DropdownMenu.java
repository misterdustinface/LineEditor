package UI;

import generic.VoidFunctionPointer;

public abstract class DropdownMenu extends UIElement {
	protected MenuButton root;
	protected ListMenu   menu;
	private   boolean	 isListMenuOpen;
	
	private final VoidFunctionPointer TOGGLE_LIST_MENU = new VoidFunctionPointer() {
		@Override
		public void call() {
			toggleListMenu();
		}
	};
	
	public DropdownMenu() {
		isListMenuOpen = false;
	}
	
	public void setRoot(MenuButton ROOT) { 
		root = ROOT;
		root.setButtonPressedFunction(TOGGLE_LIST_MENU);
	}
	
	public void setMenu(ListMenu MENU)   { 
		menu = MENU; 
	}
	
	protected void toggleListMenu() {
		if(isListMenuOpen) {
			closeListMenu();
		} else {
			openListMenu();
		}
		isListMenuOpen = !isListMenuOpen;
	}
	
	protected abstract void openListMenu();
	protected abstract void closeListMenu();
}
