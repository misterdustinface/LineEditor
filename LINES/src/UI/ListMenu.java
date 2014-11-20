package UI;

import java.util.ArrayList;

import data.shapes.Point;

public abstract class ListMenu implements UILayer {
	
	protected Point position;
	protected ArrayList<MenuButton> buttons;
	protected int width, height, buttonOffset, buttonSize;

	public ListMenu() {
		buttons = new ArrayList<MenuButton>();
		buttonOffset = 20;
		buttonSize   = 40;
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
	}
	public void setButtonSize(int BUTTON_SIZE)     {
		buttonSize = BUTTON_SIZE;
		resetMenuDimensions();
	}
	
	protected void resetMenuDimensions() {
		width  = getSuggestedDimension(buttonOffset, buttonSize, numberOfButtons());
		height = getSuggestedDimension(buttonOffset, buttonSize, 1);
	}
	
	protected int getSuggestedDimension(int buttonOffset, int buttonSize, int numButtonsAcross){
		return (((numButtonsAcross+1) * buttonOffset) + ((numButtonsAcross) * buttonSize));
	}
	
	public int numberOfButtons() { return buttons.size(); }
	public MenuButton getButton(int index) { return buttons.get(index); }
	
	protected void clearButtons() {
		buttons.clear();
	}
	
	protected void makeButtons(int number) {
		for(int i = 0; i < number; ++i)
			makeButton(i);
		resetMenuDimensions();
	}
	
	protected void makeButton(int index) {
		MenuButton button = newButton(index);
		button.makeBoxRelativeToPoint((int)position.x, (int)position.y, buttonOffset, buttonOffset, buttonSize, buttonSize);
		buttons.add(button);
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		for(MenuButton button : buttons) {
			button.update(mouse);
		}
	}
	
	protected abstract MenuButton newButton(int index);
}
