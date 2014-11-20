package UI;

import generic.VoidFunctionPointer;

public class DropdownMenu implements UILayer {
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
	public void setMenu(ListMenu MENU) { menu = MENU; }
	
	protected void 	  toggleListMenu() { isListMenuOpen = !isListMenuOpen; }
	protected boolean isListMenuOpen() { return isListMenuOpen; }

	@Override
	public void update(MouseUserDevice mouse) {
		root.update(mouse);
		if(isListMenuOpen) { menu.update(mouse); }
	}
}
