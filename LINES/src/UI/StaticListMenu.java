package UI;

import java.util.ArrayList;

import data.shapes.Point;

public class StaticListMenu implements UIMenu {
	
	protected Point position;
	protected ArrayList<MenuButton> buttons;
	protected int width, height, buttonOffset, buttonWidth, buttonHeight;
	
	private int rows;
	
	public StaticListMenu() {
		buttons = new ArrayList<MenuButton>();
		buttonOffset = 20;
		buttonWidth = buttonHeight = 40;
		width = height = 140;
		rows = 1;
	}
	
	public void setButtons(MenuButton ... BUTTONS) {
		for(int i = 0; i < BUTTONS.length; ++i)
			addButton(BUTTONS[i]);
		setRows(BUTTONS.length);
	}
	
	public void setButtons(ArrayList<MenuButton> BUTTONS) {
		buttons = BUTTONS;
		setRows(BUTTONS.size());
	}
	
	public void addButton(MenuButton BUTTON) {
		buttons.add(BUTTON);
		setRows(buttons.size());
	}
	
	public void removeButton(MenuButton BUTTON) {
		buttons.remove(BUTTON);
		setRows(buttons.size());
	}
	
	public void clearButtons() {
		buttons.clear();
		rows = 1;
	}

	private void setRows(int ROWS) { 
		rows = ROWS; 
		resetMenuDimensions();
	}
	
	public int getX() { return (int)position.x; }
	public int getY() { return (int)position.y; }
	public int getWidth()  { return width; }
	public int getHeight() { return height;}
	
	public void setPostition(Point POSITION) { 
		position = POSITION;
	}	
	
	public void setButtonOffset(int BUTTON_OFFSET) { 
		buttonOffset = BUTTON_OFFSET; 
		resetMenuDimensions();
		refreshButtons();
	}
	public void setButtonSize(int BUTTON_SIZE)     {
		buttonWidth = buttonHeight = BUTTON_SIZE;
		resetMenuDimensions();
		refreshButtons();
	}
	
	public void setButtonDimensions(int WIDTH, int HEIGHT) {
		buttonWidth  = WIDTH;
		buttonHeight = HEIGHT;
		resetMenuDimensions();
		refreshButtons();
	}
	
	protected void resetMenuDimensions() {
		width  = getSuggestedDimension(buttonOffset, buttonWidth,  1);
		height = getSuggestedDimension(buttonOffset, buttonHeight, rows);
	}
	
	protected int getSuggestedDimension(int buttonOffset, int buttonSize, int numButtonsAcross){
		return (((numButtonsAcross+1) * buttonOffset) + ((numButtonsAcross) * buttonSize));
	}
	
	public int numberOfButtons() { return buttons.size(); }
	public MenuButton getButton(int index) { return buttons.get(index); }
	
	public void refreshButtons() {
		for(int i = 0; i < numberOfButtons(); ++i)
			refreshButton(i);
	}
	public void refreshButton(int index) {
		getButton(index).makeBoxRelativeToPoint((int)position.x, (int)position.y, buttonOffset, getYOffset(index), buttonWidth, buttonHeight);
	}
	
	private int getYOffset(int buttonIndex) {	
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

	@Override
	public boolean contains(MouseUserDevice mouse) {
		return getX() <= mouse.getCursorX() && mouse.getCursorX() <= getX() + getWidth()
			&& getY() <= mouse.getCursorY() && mouse.getCursorY() <= getY() + getHeight();
	}
}
