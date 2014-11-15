package UI.AWT;

import java.awt.Color;

import UI.MouseUserDevice;
import UI.UIElement;
import data.graphics.AWTGraphicData;
import data.shapes.Rectangle;

public abstract class AWTBarSlider extends UIElement {
	
	enum ORIENTATION { VERTICAL, HORIZONTAL };
	private ORIENTATION orientation;
	
	private Color 	  pressedColor;
	private Color 	  releasedColor;
	private Color 	  highlightColor;
	private Color     baseColor;
	private Rectangle base;
	private Rectangle fill;
	private float 	  fillPercent;
	
	public AWTBarSlider() {
		super();	
		orientation   = ORIENTATION.VERTICAL;
		fillPercent   = 1.0f;
		pressedColor  = AWTGraphicData.buttonPressedColor; 
		releasedColor = AWTGraphicData.buttonColor;         
		highlightColor= AWTGraphicData.buttonHighlightColor;
		baseColor     = AWTGraphicData.baseColor;
		base 		  = new Rectangle();
		fill          = new Rectangle();
	}
	
	protected float getFillPercent() { return fillPercent; }
	
	public void setBase(Rectangle bounding) { base = bounding; }
	
	public void setHorizontal() { orientation = ORIENTATION.HORIZONTAL; }
	public void setVertical()   { orientation = ORIENTATION.VERTICAL; }

	public void setBaseColor(Color c) { baseColor = c; }
	
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
	
	public Rectangle getBase()      { return base; }
	public Rectangle getFill()      { return fill; }
	public Color     getBaseColor() { return baseColor; }
	public Color	 getFillColor() { return isPressed() ? pressedColor : isHighlighted() ? highlightColor : releasedColor; }
	
	public void update(MouseUserDevice mouse) {
		
		updateSlider(mouse.getCursorX(), mouse.getCursorY());
		
		if( base.contains(mouse.getCursorX(), mouse.getCursorY())) {
			highlight();
			if ( mouse.isPressed() || mouse.isDragged() ) { press(); }
		} else {
			removeHighlight();
		}
		
		if ( mouse.isReleased() || mouse.isClicked() ) { release(); }
	}
	
	private void updateSlider(float mouseX, float mouseY) {
		if( isPressed() ) {
			switch(orientation) {
			case HORIZONTAL: setFillPercent(  ((mouseX - base.x)/base.width)  );
				break;
			case VERTICAL:   setFillPercent(1-((mouseY - base.y)/base.height) );
				break;
			}
		}
	}
	
	protected void setFillPercent(float delta) {
		fillPercent = delta;
		if     (fillPercent > 1.0f) { fillPercent = 1.0f; }
		else if(fillPercent < 0.0f) { fillPercent = 0.0f; }
		updateFillBounds();
	}
	
	private void updateFillBounds() {
		switch(orientation) {
		case HORIZONTAL: fill.setBounds(base.x, base.y, (int)(base.width * fillPercent), base.height);
			break;
		case VERTICAL:   fill.setBounds(base.x, base.y+base.height, base.width, -(int)(base.height * fillPercent));
			break;
		}
	}

}
