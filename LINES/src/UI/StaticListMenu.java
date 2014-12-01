package UI;


public class StaticListMenu extends UIMenu {
	
	public StaticListMenu() {
		super();
		buttonOffset = 20;
		buttonWidth  = 80;
		buttonHeight = 40;
		width = height = 140;
	}
	
	protected void resetMenuDimensions() {
		width  = getSuggestedDimension(buttonOffset, buttonWidth,  1);
		height = getSuggestedDimension(buttonOffset, buttonHeight, buttons.size());
	}
	
	public void refreshButton(int index) {
		getButton(index).makeBoxRelativeToPoint((int)position.x, (int)position.y, buttonOffset, getYOffset(index), buttonWidth, buttonHeight);
	}
	
	protected int getYOffset(int buttonIndex) {	
		return (((buttonIndex+1) * buttonOffset) + ((buttonIndex) * buttonHeight));
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		if(contains(mouse)) {
			for(MenuButton button : buttons) {
				button.update(mouse);
			}
			mouse.intercept();
		}
	}
}
