package UI;

import data.shapes.Grid;
import data.shapes.Point;

public abstract class DynamicGridMenu extends UIMenu {
	
	protected Grid displaygrid;
	
	public DynamicGridMenu(Grid DISPLAYGRID) {
		displaygrid = DISPLAYGRID;
		buttonOffset = 20;
		buttonWidth = buttonHeight = 40;
		resetMenuDimensions();
	}
	
	public int getX() { return displaygrid.x; }
	public int getY() { return displaygrid.y; }
	
	public void setPostition(Point POSITION) { 
		displaygrid.x = (int)POSITION.x;
		displaygrid.y = (int)POSITION.y;
	}	
	
	protected void resetMenuDimensions() {
		width  = getSuggestedDimension(buttonOffset, buttonWidth,  displaygrid.cols);
		height = getSuggestedDimension(buttonOffset, buttonHeight, displaygrid.rows);
	}
	
	public void makeButtons(int number) {
		for(int i = 0; i < number; ++i)
			makeButton(i);
		makeNewEmptyEntry();
		resetMenuDimensions();
	}
	
	protected void makeButton(int index) {
		MenuButton button = newButton(index);
		button.makeBoxRelativeToPoint(displaygrid.x, displaygrid.y, getXOffset(index), getYOffset(index), buttonWidth, buttonHeight);
		buttons.add(button);
	}
	
	private void makeNewEmptyEntry() {
		MenuButton button = newEmptyButton();
		button.makeBoxRelativeToPoint(displaygrid.x, displaygrid.y, getXOffset(numberOfButtons()), getYOffset(numberOfButtons()), buttonWidth, buttonHeight);
		buttons.add(button);	
	}
	
	private int getXOffset(int buttonIndex) {
		return getUnadjustedXOffset(buttonIndex) % (width-buttonOffset);
	}
	private int getYOffset(int buttonIndex) {	
		return getUnadjustedYOffset(getButtonRow(buttonIndex)) % (height-buttonOffset);
	}
	private int getUnadjustedXOffset(int buttonIndex){
		return getUnadjustedOffset(buttonIndex, buttonWidth);
	}
	private int getUnadjustedYOffset(int buttonIndex){
		return getUnadjustedOffset(buttonIndex, buttonHeight);
	}
	private int getUnadjustedOffset(int buttonIndex, int buttonSize) {
		return (((buttonIndex+1) * buttonOffset) + ((buttonIndex) * buttonSize));
	}
	private int getButtonRow(int buttonIndex) {
		return (int)(getUnadjustedXOffset(buttonIndex) / (float)(width-buttonOffset));
	}
	
	public boolean canFitNewEmptyEntry() {
		return getUnadjustedYOffset(getButtonRow(numberOfButtons())) < (height-buttonHeight);
	}
	
	public MenuButton getEmptyEntry() {
		return buttons.get(numberOfButtons()-1);
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		if(contains(mouse)) {
			for(MenuButton button : buttons) {
				button.update(mouse);
			}
			if(getEmptyEntry().isPressed() && canFitNewEmptyEntry()) {
				makeNewEmptyEntry();
			}
		}
	}
	
	protected abstract MenuButton newButton(int index);
	protected abstract MenuButton newEmptyButton();
}
