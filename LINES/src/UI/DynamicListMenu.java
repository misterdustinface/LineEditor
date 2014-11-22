package UI;

public abstract class DynamicListMenu extends StaticListMenu {

	public DynamicListMenu() {}
	
	public void makeButtons(int number) {
		for(int i = 0; i < number; ++i)
			makeButton(i);
		resetMenuDimensions();
	}
	
	protected void makeButton(int index) {
		MenuButton button = newButton(index);
		button.makeBoxRelativeToPoint((int)position.x, (int)position.y, buttonOffset, getYOffset(index), buttonWidth, buttonHeight);
		buttons.add(button);
	}
	
	protected abstract MenuButton newButton(int index);
}
