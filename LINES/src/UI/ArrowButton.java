package UI;

import data.shapes.Point;
import data.shapes.Polygon;

final public class ArrowButton extends MenuButton {

	enum TYPE{INCREMENTOR, DECREMENTOR, SELECTOR}
	private TYPE  type; 
	private int   val;
	private Point position;
	private int offset, size;
	
	public ArrowButton(Point POSITION, int OFFSET, int SIZE) {
		val  = 0;
		type = TYPE.SELECTOR;
		position = POSITION;
		offset   = OFFSET;
		size     = SIZE;
		setRight();
	}
	
	public void setRight(){ right(this, position, offset, size); }
	public void setLeft() {  left(this, position, offset, size); }
	public void setUp()   {    up(this, position, offset, size); }
	public void setDown() {  down(this, position, offset, size); }
	
	private static void right(ArrowButton b, Point p, int xoff, int size) {
		b.polygon = new Polygon(3);
		b.addPointRelativeToMenuPosition(p, xoff,   0);
		b.addPointRelativeToMenuPosition(p, xoff - size,  size);
		b.addPointRelativeToMenuPosition(p, xoff - size, -size);
	}
	
	private static void left(ArrowButton b, Point p, int xoff, int size) {
		b.polygon = new Polygon(3);
		b.addPointRelativeToMenuPosition(p, xoff,   0);
		b.addPointRelativeToMenuPosition(p, xoff + size,  size);
		b.addPointRelativeToMenuPosition(p, xoff + size, -size);
	}
	
	private static void up(ArrowButton b, Point p, int yoff, int size) {
		b.polygon = new Polygon(3);
		b.addPointRelativeToMenuPosition(p, 0, yoff);
		b.addPointRelativeToMenuPosition(p, size,  yoff-size);
		b.addPointRelativeToMenuPosition(p, -size, yoff-size);
	}
	
	private static void down(ArrowButton b, Point p, int yoff, int size) {
		b.polygon = new Polygon(3);
		b.addPointRelativeToMenuPosition(p, 0, yoff);
		b.addPointRelativeToMenuPosition(p, size,  yoff+size);
		b.addPointRelativeToMenuPosition(p, -size, yoff+size);
	}
	
	public int     getValue()         { return val;              }
	public boolean isActivated()      { return Math.abs(val) > 0;}
	public void    setAsIncrementor() { type = TYPE.INCREMENTOR; }
	public void    setAsDecrementor() { type = TYPE.DECREMENTOR; }
	public void    setAsSelector()    { type = TYPE.SELECTOR;    }
	public void    reset()            { val  = 0; }
	
	@Override
	protected void pressAction() {
		switch(type){
		case INCREMENTOR: ++val;
				break;
		case DECREMENTOR: --val;
				break;
		case SELECTOR:    val = 1;
				break;
		}
	}

	@Override
	protected void releaseAction() {
		switch(type){
		case INCREMENTOR:
				break;
		case DECREMENTOR:
				break;
		case SELECTOR:    val = 0;
				break;
		}
	}

}
