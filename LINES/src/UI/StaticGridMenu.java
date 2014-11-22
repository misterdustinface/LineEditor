package UI;

import data.shapes.Grid;

public class StaticGridMenu extends UIMenu {

	protected Grid displaygrid;
	
	public StaticGridMenu(Grid DISPLAYGRID) {
		displaygrid = DISPLAYGRID;
		buttonOffset = 20;
		buttonWidth = buttonHeight = 40;
		resetMenuDimensions();
	}
	
	public void setGridDimensions(int ROWS, int COLS) {
		displaygrid.rows = ROWS; displaygrid.cols = COLS;
		resetMenuDimensions();
	}
	
	protected void resetMenuDimensions() {
		width  = getSuggestedDimension(buttonOffset, buttonWidth,  displaygrid.cols);
		height = getSuggestedDimension(buttonOffset, buttonHeight, displaygrid.rows);
	}
	
	@Override
	public void refreshButton(int index) {
		getButton(index).makeBoxRelativeToPoint(position, getXOffset(index), getYOffset(index), buttonWidth, buttonHeight);
	}
	
	protected int getXOffset(int buttonIndex) {
		return getUnadjustedXOffset(buttonIndex) % (width-buttonOffset);
	}
	protected int getYOffset(int buttonIndex) {	
		return getUnadjustedYOffset(getButtonRow(buttonIndex)) % (height-buttonOffset);
	}
	protected int getUnadjustedXOffset(int buttonIndex){
		return getUnadjustedOffset(buttonIndex, buttonWidth);
	}
	protected int getUnadjustedYOffset(int buttonIndex){
		return getUnadjustedOffset(buttonIndex, buttonHeight);
	}
	protected int getUnadjustedOffset(int buttonIndex, int buttonSize) {
		return (((buttonIndex+1) * buttonOffset) + ((buttonIndex) * buttonSize));
	}
	protected int getButtonRow(int buttonIndex) {
		return (int)(getUnadjustedXOffset(buttonIndex) / (float)(width-buttonOffset));
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
