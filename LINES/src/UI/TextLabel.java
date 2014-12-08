package UI;

import data.shapes.Point;
import data.shapes.Polygon;
import data.shapes.Rectangle;

public class TextLabel {
	public enum ALIGNMENT{LEFT, CENTER, RIGHT};
	
	private ALIGNMENT 	alignment;
	private int 		maxChars;
	private String 		text;
	private Point		textLocation;
	
	public TextLabel() {
		text    	 = new String();
		textLocation = new Point(0, 0);
		maxChars     = 10;
		alignment    = ALIGNMENT.LEFT;
	}
	
	public void setText(String TEXT)	{ 
		text = TEXT;
		modifyText();
	}
	private void modifyText() {
		if (text.length() > maxChars) {
			text = text.substring(0, maxChars-3);
			text += "...";
		}
	}
	
	public String 	getText() 				{ return text; }
	public void 	setPosition(Point POS) 	{ textLocation = POS; }
	public Point 	getPosition() 			{ return textLocation; }
	
	public void 	setAlignment(ALIGNMENT a) { alignment = a; }
	public void 	center() 	 			{ alignment = ALIGNMENT.CENTER; }
	public boolean 	hasText() 	 			{ return text.length() > 0;}
	public int 		suggestedWidth()  		{ return maxChars * 10; }
	public int 		suggestedHeight() 		{ return 40;            }
	
	public void 	setMaxTextWidth(int maxChars) { 
		this.maxChars = maxChars; 
		modifyText();
	}

	public boolean isCentered()       		{ return alignment == ALIGNMENT.CENTER; }
	public boolean isRightJustified() 		{ return alignment == ALIGNMENT.RIGHT;  }
	public boolean isLeftJustified()  		{ return alignment == ALIGNMENT.LEFT;   }
	
	public void alignText(Polygon polygon) {
		if(hasText()) {
			Rectangle bounding = polygon.getBoundingRectangle();
			int width  = isCentered() ? text.length() : maxChars; 
			float xoff = width * 3.75f;
			if(isRightJustified()) { xoff = -xoff; }
			textLocation.set((float)bounding.getCenterX() - xoff, (float)bounding.getCenterY() + 4);
		}
	}

}
