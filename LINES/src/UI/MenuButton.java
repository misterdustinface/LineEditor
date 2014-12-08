package UI;

import generic.DebounceTimer;
import data.shapes.Point;
import data.shapes.Polygon;

public class MenuButton extends FunctionButton {

	public TextLabel 	 textLabel;
	public Polygon   	 polygon;
	public DebounceTimer debounceTimer;
	
	public MenuButton() {
		super();
		polygon   = new Polygon(4);
		textLabel = new TextLabel();
		debounceTimer = new DebounceTimer();
	}
	
	public void makeSuggestedBoxRelativeToPoint(int xPos, int yPos) {
		makeBoxRelativeToPoint(xPos, yPos, 0, 0, textLabel.suggestedWidth(), textLabel.suggestedHeight());
	}
	
	public void makeSuggestedBoxRelativeToPoint(Point position, int xOff, int yOff) {
		makeBoxRelativeToPoint(position, xOff, yOff, textLabel.suggestedWidth(), textLabel.suggestedHeight());
	}
	
	public void makeSuggestedBoxRelativeToPoint(int xPos, int yPos, int xOff, int yOff) {
		makeBoxRelativeToPoint(xPos, yPos, xOff, yOff, textLabel.suggestedWidth(), textLabel.suggestedHeight());
	}
	
	public void makeBoxRelativeToPoint(Point position, int xOff, int yOff, int width, int height) {
		makeBoxRelativeToPoint((int)position.x, (int)position.y, xOff, yOff, width, height);
	}
	
	public void makeBoxRelativeToPoint(int xPos, int yPos, int xOff, int yOff, int width, int height) {
		polygon = new Polygon(4);
		addPointRelativeToMenuPosition(xPos, yPos, xOff, yOff);
		addPointRelativeToMenuPosition(xPos, yPos, xOff + width, yOff);
		addPointRelativeToMenuPosition(xPos, yPos, xOff + width, yOff + height);
		addPointRelativeToMenuPosition(xPos, yPos, xOff, yOff + height);
		textLabel.alignText(polygon);
	}
	
	public void addPointRelativeToMenuPosition(Point position, int x, int y) {
		addPointRelativeToMenuPosition((int) position.x, (int) position.y, x, y);
	}
	
	public void addPointRelativeToMenuPosition(int menuPointX, int menuPointY, int x, int y) {
		polygon.addPoint(menuPointX + x, menuPointY + y);
	}
	
	public int getWidth()   { return polygon.getBoundingRectangle().width;  }
	public int getHeight()  { return polygon.getBoundingRectangle().height; }
	public int getCenterX() { return polygon.getBoundingRectangle().getCenterX(); }
	public int getCenterY() { return polygon.getBoundingRectangle().getCenterY(); }
	
	@Override
	public void update(MouseUserDevice mouse) {
		if( contains(mouse) ) {
			highlight();
			if ( mouse.isPressed()  || mouse.isClicked() ) { debouncedPress();  }
			if ( mouse.isReleased() || mouse.isClicked() ) { release();         }
			if ( mouse.isPressed()  || mouse.isClicked() ) { mouse.intercept(); }
		} else {
			removeHighlight();
			if ( isPressed() ) { release(); }
		}
		
		if(textLabel.isCentered()) { textLabel.alignText(polygon); }
	}
	
	private boolean contains(MouseUserDevice mouse) {
		return polygon.contains(mouse.getCursorX(), mouse.getCursorY());
	}
	
	final public void debouncedPress() {
		if(debounceTimer.isDebounceComplete()) {
			debounceTimer.reset(); // TEMPORAL COUPLING. UPDATE CAN BE CALLED MULTIPLE TIMES AT ONCE IN DIFFERENT THREADS; THEREFORE, THIS FUNCTION CAN TOO.
			press();
		}   
	}

}
