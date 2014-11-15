package UI.AWT;

import java.awt.Color; 

import UI.MouseUserDevice;
import UI.UIElement;
import data.graphics.AWTGraphicData;
import data.shapes.Point;
import data.shapes.Polygon;
import data.shapes.Rectangle;

public abstract class AWTMenuButton extends UIElement {
	
	protected Color pressedColor;
	protected Color releasedColor;
	protected Color highlightColor;
	private int 	maxChars;
	private boolean permaCenterText;
	
	public String 	text;
	public Polygon  polygon;
	public Point	textLocation;
	
	public AWTMenuButton() {
		super();
		text    	 = new String();
		polygon 	 = new Polygon(4);
		textLocation = new Point(0, 0);
		maxChars     = 10;
		permaCenterText  = false; 
		
		pressedColor  = AWTGraphicData.buttonPressedColor; 
		releasedColor = AWTGraphicData.buttonColor;         
		highlightColor= AWTGraphicData.buttonHighlightColor;
	}
	
	public boolean hasText() {
		return text.length() > 0;
	}
	
	public void setMaxTextWidth(int maxChars) {
		this.maxChars = maxChars;
	}
	
	public void makeSuggestedBoxRelativeToPoint(Point position, int xOff, int yOff) {
		makeBoxRelativeToPoint(position, xOff, yOff, suggestedWidth(), suggestedHeight());
	}
	
	public void makeSuggestedBoxRelativeToPoint(int xPos, int yPos, int xOff, int yOff) {
		makeBoxRelativeToPoint(xPos, yPos, xOff, yOff, suggestedWidth(), suggestedHeight());
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
		alignText();
	}
	
	public void addPointRelativeToMenuPosition(Point position, int x, int y) {
		addPointRelativeToMenuPosition((int) position.x, (int) position.y, x, y);
	}
	
	public void addPointRelativeToMenuPosition(int menuPointX, int menuPointY, int x, int y) {
		polygon.addPoint(menuPointX + x, menuPointY + y);
	}
	
	public void center() { permaCenterText = true; }
	
	public void alignText() {
		Rectangle bounding = polygon.getBounds();
		int width  = permaCenterText ? text.length() : maxChars; 
		float xoff = width * 3.75f;
		textLocation.set((float)bounding.getCenterX() - xoff, (float)bounding.getCenterY() + 4);
	}
	
	public int suggestedWidth()  { return maxChars * 10; }
	public int suggestedHeight() { return 40;            }
	
	public void setColor(Color c) {
		pressedColor = releasedColor = highlightColor = c;
	}
	
	public void setColor(Color pressed, Color released) {
		pressedColor  = pressed;
		highlightColor = releasedColor = released;
	}
	
	public void setColor(Color pressed, Color released, Color highlight) {
		pressedColor  = pressed;
		releasedColor = released;
		highlightColor = highlight;
	}
	
	public Color getColor() {
		return isPressed() ? pressedColor : isHighlighted() ? highlightColor : releasedColor;
	}
	public Color getNormalColor() {
		return releasedColor;
	}
	
	public void update(MouseUserDevice mouse) {
		if( polygon.contains(mouse.getCursorX(), mouse.getCursorY())) {
			highlight();
			if ( mouse.isPressed()  || mouse.isClicked() ) { press();   }
			if ( mouse.isReleased() || mouse.isClicked() ) { release(); }
		} else {
			removeHighlight();
			if ( isPressed() && mouse.isDragged() ) { release(); }
		}
		
		if( permaCenterText ) { alignText(); }
	}
	
	public int getWidth()   { return polygon.getBounds().width;  }
	public int getHeight()  { return polygon.getBounds().height; }
	public int getCenterX() { return polygon.getBounds().getCenterX(); }
	public int getCenterY() { return polygon.getBounds().getCenterY(); }
}
