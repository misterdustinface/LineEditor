package UI;

import data.shapes.Point;
import data.shapes.Polygon;

public class MenuButton extends FunctionButton {

	public TextLabel textLabel;
	public Polygon   polygon;
	
	public MenuButton() {
		super();
		polygon   = new Polygon(4);
		textLabel = new TextLabel();
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
	
	public int getWidth()   { return polygon.getBounds().width;  }
	public int getHeight()  { return polygon.getBounds().height; }
	public int getCenterX() { return polygon.getBounds().getCenterX(); }
	public int getCenterY() { return polygon.getBounds().getCenterY(); }

	@Override
	public void update(MouseUserDevice mouse) {
		if( polygon.contains(mouse.getCursorX(), mouse.getCursorY())) {
			highlight();
			if ( mouse.isPressed()  || mouse.isClicked() ) { press();   }
			if ( mouse.isReleased() || mouse.isClicked() ) { release(); }
		} else {
			removeHighlight();
			if ( isPressed() && mouse.isDragged() ) { release(); }
		}
		
		if(textLabel.isCentered()) { textLabel.alignText(polygon); }
	}
	
}
