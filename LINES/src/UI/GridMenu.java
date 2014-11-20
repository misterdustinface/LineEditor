package UI;

import data.shapes.Grid;
import data.shapes.Point;

public abstract class GridMenu extends ListMenu {
	
	protected Grid displaygrid;
	
	public GridMenu(Grid DISPLAYGRID) {
		super();
		displaygrid = DISPLAYGRID;
		buttonOffset = 20;
		buttonSize   = 40;
		resetMenuDimensions();
	}
	
	public int getX() { return displaygrid.x; }
	public int getY() { return displaygrid.y; }
	
	@Override
	public void setPostition(Point POSITION) { 
		displaygrid.x = (int)POSITION.x;
		displaygrid.y = (int)POSITION.y;
	}	
	
	@Override
	protected void resetMenuDimensions() {
		width  = getSuggestedDimension(buttonOffset, buttonSize, displaygrid.rows);
		height = getSuggestedDimension(buttonOffset, buttonSize, displaygrid.cols);
	}
	
	@Override
	protected void makeButtons(int number) {
		super.makeButtons(number);
		makeNewEmptyEntry();
		resetMenuDimensions();
	}
	
	@Override
	protected void makeButton(int index) {
		MenuButton button = newButton(index);
		button.makeBoxRelativeToPoint(displaygrid.x, displaygrid.y, getXOffset(index), getYOffset(index), buttonSize, buttonSize);
		buttons.add(button);
	}
	
	private void makeNewEmptyEntry() {
		MenuButton button = newEmptyButton();
		button.makeBoxRelativeToPoint(displaygrid.x, displaygrid.y, getXOffset(numberOfButtons()), getYOffset(numberOfButtons()), buttonSize, buttonSize);
		buttons.add(button);	
	}
	
	private int getXOffset(int buttonIndex) {
		return getUnadjustedOffset(buttonIndex) % (width-buttonOffset);
	}
	private int getYOffset(int buttonIndex) {	
		return getUnadjustedOffset(getButtonRow(buttonIndex)) % (height-buttonOffset);
	}
	private int getUnadjustedOffset(int buttonIndex){
		return (((buttonIndex+1) * buttonOffset) + ((buttonIndex) * buttonSize));
	}
	private int getButtonRow(int buttonIndex) {
		return (int)(getUnadjustedOffset(buttonIndex) / (float)(width-buttonOffset));
	}
	
	public boolean canFitNewEmptyEntry() {
		return getUnadjustedOffset(getButtonRow(numberOfButtons())) < (height-buttonOffset);
	}
	
	public MenuButton getEmptyEntry() {
		return buttons.get(numberOfButtons()-1);
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		super.update(mouse);
		if(getEmptyEntry().isPressed() && canFitNewEmptyEntry()) {
			makeNewEmptyEntry();
		}
	}
	
	protected abstract MenuButton newEmptyButton();
}
