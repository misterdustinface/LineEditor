package UI;

import data.shapes.Point;

public class StaticListMenu extends UIMenu {
	
	protected Point position;
	
	public StaticListMenu() {
		buttonOffset = 20;
		buttonWidth = buttonHeight = 40;
		width = height = 140;
	}
	
	public int getX() { return (int)position.x; }
	public int getY() { return (int)position.y; }
	
	public void setPostition(Point POSITION) { 
		position = POSITION;
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
		}
	}
}
