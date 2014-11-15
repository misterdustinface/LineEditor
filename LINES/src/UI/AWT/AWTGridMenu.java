package UI.AWT;

import java.awt.Graphics2D;
import java.util.ArrayList;

import rendering.AWT.AWTMenuDrawer;
import UI.MouseUserDevice;
import UI.UILayer;
import data.shapes.Grid;
import data.shapes.Point;

public abstract class AWTGridMenu implements UILayer {
	
	private AWTMenuDrawer 				menuDrawer;
	private ArrayList<AWTMenuButton> 	buttons;
	private int width, height, buttonOffset, buttonSize;
	private Grid displaygrid;
	
	public AWTGridMenu(Grid DISPLAYGRID) {
		displaygrid = DISPLAYGRID;
		buttonOffset = 20;
		buttonSize   = 40;
		resetMenuDimensions();
		menuDrawer 	= new AWTMenuDrawer();
		buttons 	= new ArrayList<AWTMenuButton>();
	}
	
	public int numberOfButtons() { return buttons.size(); }
	
	public void setPostition(Point POSITION) { 
		displaygrid.x = (int)POSITION.x;
		displaygrid.y = (int)POSITION.y;
	}	
	
	public void setButtonOffset(int BUTTON_OFFSET) { 
		buttonOffset = BUTTON_OFFSET; 
		resetMenuDimensions();
	}
	public void setButtonSize(int BUTTON_SIZE)     {
		buttonSize = BUTTON_SIZE;
		resetMenuDimensions();
	}
	
	private void resetMenuDimensions() {
		width    = getSuggestedDimenstion(buttonOffset, buttonSize, displaygrid.rows);
		height   = getSuggestedDimenstion(buttonOffset, buttonSize, displaygrid.cols);
	}
	
	private static int getSuggestedDimenstion(int buttonOffset, int buttonSize, int numButtonsAcross){
		return (((numButtonsAcross+1) * buttonOffset) + ((numButtonsAcross) * buttonSize));
	}
	
	protected void clearButtons() {
		buttons.clear();
	}
	
	protected void makeButtons(int number) {
		for(int i = 0; i < number; ++i)
			makeButton(i);
		makeNewEmptyEntry();
	}
	
	private void makeButton(int index) {
		AWTMenuButton button = newButton(index);
		button.makeBoxRelativeToPoint(displaygrid.x, displaygrid.y, getXOffset(index), getYOffset(index), buttonSize, buttonSize);
		buttons.add(button);
	}
	
	private void makeNewEmptyEntry() {
		AWTMenuButton button = newEmptyButton();
		button.makeBoxRelativeToPoint(displaygrid.x, displaygrid.y, getXOffset(numberOfButtons()), getYOffset(numberOfButtons()), buttonSize, buttonSize);
		buttons.add(button);	
	}
	
	private int getXOffset(int buttonIndex) {
		return getUnadjustedOffset(buttonIndex) % (width-buttonOffset);
	}
	private int getYOffset(int buttonIndex) {	
		return getUnadjustedOffset(getButtonRow(buttonIndex)) % (height-buttonOffset);
	}
	private boolean canFitNewEmptyEntry() {
		return getUnadjustedOffset(getButtonRow(numberOfButtons())) < (height-buttonOffset);
	}
	private int getUnadjustedOffset(int buttonIndex){
		return (((buttonIndex+1) * buttonOffset) + ((buttonIndex) * buttonSize));
	}
	private int getButtonRow(int buttonIndex) {
		return (int)(getUnadjustedOffset(buttonIndex) / (float)(width-buttonOffset));
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		menuDrawer.drawMenu(displaygrid.x, displaygrid.y, width, height);
		for(int i = 0; i < numberOfButtons(); ++i) {
			menuDrawer.drawFilledButton(buttons.get(i));
		}
		if(canFitNewEmptyEntry()) {
			menuDrawer.drawPlusOnButton(getEmptyEntry());
		}
	}

	@Override
	public void update(MouseUserDevice mouse) {
		for(AWTMenuButton button : buttons) {
			button.update(mouse);
		}
		if(getEmptyEntry().isPressed() && canFitNewEmptyEntry()) {
			makeNewEmptyEntry();
		}
	}
	
	private AWTMenuButton getEmptyEntry() {
		return buttons.get(numberOfButtons()-1);
	}
	
	protected abstract AWTMenuButton newButton(int index);
	protected abstract AWTMenuButton newEmptyButton();
}
