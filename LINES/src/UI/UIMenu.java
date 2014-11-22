package UI;

import java.util.ArrayList;

import data.shapes.Point;

public abstract class UIMenu implements UILayer {
	
	protected ArrayList<MenuButton> buttons;
	protected int width, height, buttonOffset, buttonWidth, buttonHeight;
	
	public UIMenu() {
		buttons = new ArrayList<MenuButton>();
		buttonOffset = 20;
		buttonWidth = buttonHeight = 40;
		width = height = 140;
	}
	
	abstract protected void 	resetMenuDimensions();
	abstract public    int 		getX();
	abstract public    int 		getY();
	abstract public    void 	setPostition(Point POSITION);

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
	
	public void setButtons(MenuButton ... BUTTONS) {
		for(int i = 0; i < BUTTONS.length; ++i)
			addButton(BUTTONS[i]);
	}
	
	public void setButtons(ArrayList<MenuButton> BUTTONS) {
		buttons = BUTTONS;
		resetMenuDimensions();
	}
	
	public void addButton(MenuButton BUTTON) {
		buttons.add(BUTTON);
		resetMenuDimensions();
	}
	
	public void removeButton(MenuButton BUTTON) {
		buttons.remove(BUTTON);
		resetMenuDimensions();
	}
	
	public void clearButtons() {
		buttons.clear();
		resetMenuDimensions();
	}
	
	public void refreshButtons() {
		for(int i = 0; i < numberOfButtons(); ++i)
			refreshButton(i);
	}
	
	abstract public void refreshButton(int index);
	
	public int numberOfButtons() { return buttons.size(); }
	public MenuButton getButton(int index) { return buttons.get(index); }
	
	public int getWidth()  	{ return width; }
	public int getHeight() 	{ return height;}
	
	public boolean contains(MouseUserDevice mouse) {
		return getX() <= mouse.getCursorX() && mouse.getCursorX() <= getX() + getWidth()
			&& getY() <= mouse.getCursorY() && mouse.getCursorY() <= getY() + getHeight();
	}
	
	protected int getSuggestedDimension(int buttonOffset, int buttonSize, int numButtonsAcross){
		return (((numButtonsAcross+1) * buttonOffset) + ((numButtonsAcross) * buttonSize));
	}
	
}
