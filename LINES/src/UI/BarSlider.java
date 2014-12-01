package UI;

import data.shapes.Rectangle;

public abstract class BarSlider extends UIElement {

	enum ORIENTATION { VERTICAL, HORIZONTAL };
	private ORIENTATION orientation;
	
	private Rectangle base;
	private Rectangle fill;
	private float 	  fillPercent;
	
	public BarSlider() {
		super();	
		orientation   = ORIENTATION.VERTICAL;
		fillPercent   = 1.0f;
		base 		  = new Rectangle();
		fill          = new Rectangle();
	}
	
	protected float getFillPercent() { return fillPercent; }
	
	public void setBase(Rectangle bounding) { base = bounding; }
	
	public void setHorizontal() { orientation = ORIENTATION.HORIZONTAL; }
	public void setVertical()   { orientation = ORIENTATION.VERTICAL; }
	
	public Rectangle getBase() { return base; }
	public Rectangle getFill() { return fill; }
	
	public void update(MouseUserDevice mouse) {
		
		updateSlider(mouse.getCursorX(), mouse.getCursorY());
		
		if( base.contains(mouse.getCursorX(), mouse.getCursorY())) {
			highlight();
			if ( mouse.isPressed()  || mouse.isDragged() ) { press();   }
			if ( mouse.isReleased() || mouse.isClicked() ) { release(); }
		} else {
			removeHighlight();
			release();
		}
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
