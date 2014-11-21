package UI;

import java.util.ArrayList;

import data.shapes.Grid;
import data.shapes.Point;

public abstract class DynamicGridMenu implements UIMenu {
	
	protected Grid displaygrid;
	protected ArrayList<MenuButton> buttons;
	protected int width, height, buttonOffset, buttonWidth, buttonHeight;
	
	public DynamicGridMenu(Grid DISPLAYGRID) {
		displaygrid = DISPLAYGRID;
		buttonOffset = 20;
		buttonWidth = buttonHeight = 40;
		buttons = new ArrayList<MenuButton>();
		resetMenuDimensions();
	}
	
	public int numberOfButtons() { return buttons.size(); }
	public MenuButton getButton(int index) { return buttons.get(index); }
	
	public int getX() { return displaygrid.x; }
	public int getY() { return displaygrid.y; }
	public int getWidth()  { return width; }
	public int getHeight() { return height;}
	
	public void setPostition(Point POSITION) { 
		displaygrid.x = (int)POSITION.x;
		displaygrid.y = (int)POSITION.y;
	}	
	
	public void setButtonOffset(int BUTTON_OFFSET) { 
		buttonOffset = BUTTON_OFFSET; 
		resetMenuDimensions();
	}
	public void setButtonSize(int BUTTON_SIZE)     {
		buttonWidth = buttonHeight = BUTTON_SIZE;
		resetMenuDimensions();
	}
	public void setButtonDimensions(int WIDTH, int HEIGHT) {
		buttonWidth  = WIDTH;
		buttonHeight = HEIGHT;
		resetMenuDimensions();
	}
	
	protected void resetMenuDimensions() {
		width  = getSuggestedDimension(buttonOffset, buttonWidth,  displaygrid.cols);
		height = getSuggestedDimension(buttonOffset, buttonHeight, displaygrid.rows);
	}
	
	protected int getSuggestedDimension(int buttonOffset, int buttonSize, int numButtonsAcross){
		return (((numButtonsAcross+1) * buttonOffset) + ((numButtonsAcross) * buttonSize));
	}
	
	public void clearButtons() {
		buttons.clear();
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
		return (int)(getUnadjustedXOffset(buttonIndex) / (float)(width-buttonWidth));
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
	
	@Override
	public boolean contains(MouseUserDevice mouse) {
		return getX() <= mouse.getCursorX() && mouse.getCursorX() <= getX() + getWidth()
			&& getY() <= mouse.getCursorY() && mouse.getCursorY() <= getY() + getHeight();
	}
	
	protected abstract MenuButton newButton(int index);
	protected abstract MenuButton newEmptyButton();
}
