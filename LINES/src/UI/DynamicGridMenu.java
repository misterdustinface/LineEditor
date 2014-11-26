package UI;

import data.shapes.Grid;

public abstract class DynamicGridMenu extends StaticGridMenu {

	public DynamicGridMenu(Grid DISPLAYGRID) {
		super(DISPLAYGRID);
	}
	
	public void refreshButtons() {
		refreshButtons(numberOfButtons() - 1);
	}
	
	protected void refreshButtons(int amount) {
		clearButtons();
		for(int i = 0; i < limitNumberOfButtons(amount); ++i)
			refreshButton(i);
		if(canFitNewEmptyEntry())
			makeNewEmptyEntry();
		resetMenuDimensions();
	}
	
	private int limitNumberOfButtons(int desiredAmount) { 
		int max = getMaxNumberOfButtons(); 
		return desiredAmount > max ? max : desiredAmount;
	}
	
	private int getMaxNumberOfButtons() { return displaygrid.getNumberOfCells(); }
	
	@Override
	public void refreshButton(int index) { 
		MenuButton button = newButton(index);
		button.makeBoxRelativeToPoint(position, getXOffset(index), getYOffset(index), buttonWidth, buttonHeight);
		buttons.add(button);
	}
	
	private void makeNewEmptyEntry() {
		MenuButton button = newEmptyButton();
		button.makeBoxRelativeToPoint(position, getXOffset(numberOfButtons()), getYOffset(numberOfButtons()), buttonWidth, buttonHeight);
		buttons.add(button);	
	}
	
	public boolean canFitNewEmptyEntry() {
		return getUnadjustedYOffset(getButtonRow(numberOfButtons())) < (height-buttonHeight);
	}
	
	public MenuButton getEmptyEntry() {
		return buttons.get(numberOfButtons()-1);
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		super.update(mouse);
		
		if(contains(mouse)) {
			if(getEmptyEntry().isPressed() && canFitNewEmptyEntry()) {
				makeNewEmptyEntry();
			}
		}
	}
	
	protected abstract MenuButton newButton(int index);
	protected abstract MenuButton newEmptyButton();
}
