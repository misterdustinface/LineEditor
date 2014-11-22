package data.shapes;

import AWT.graphicdata.AWTGraphicData;

public class Circle implements Shape{

	private static final String NAME = "CIRCLE";
	private static long instanceCounter = 0;
	private long instance;
	private String id;
	
	private float r;
	private Point center;
	
	public Circle(final float x, final float y, final float r){
		instance = instanceCounter++;
		id = NAME + instance;
		this.r = Math.abs(r);
		this.center = new Point(x,y);
	}
	public Circle(final Circle other){
		instance = instanceCounter++;
		id = "CIRCLE"+instance;
		this.r = other.r;
		this.center = other.center.copy();
	}
	
	public Circle(final Point center, final float r){
		instance = instanceCounter++;
		id = "CIRCLE"+instance;
		this.center = center.copy();
		this.r = Math.abs(r);
	}
	public void setPosition(float x, float y){center.x = x; center.y = y;}
	void setRadius(float r){this.r = Math.abs(r);}
	
	public Point center(){return center;}
	public Point centerCopy(){return center.copy();}
	public float radius(){return r;}
	public float diameter(){return r*2;}
	public float x(){return center.x;}
	public float y(){return center.y;}
	public float area(){return (float)(Math.PI) * Math.squared(r);}
	public float circumference(){return diameter() * (float)Math.PI;}
	
	@Override
	public boolean contains(Point point){
		return Math.distance(center, point) <= r;
	}
	public boolean contains(final float X, final float Y){
		return Math.distance(center.x, center.y, X, Y) <= r;
	}
	
	@Override
	public int hashCode(){
		return center.hashCode();
	}
	
	@Override
	public String ID() { return id; }
	@Override
	public void scale(float percent) {
		setRadius(AWTGraphicData.pointHighlightCircleThickness);
	}
	@Override
	public void shift(float xOffset, float yOffset) {
		setPosition(center.x + xOffset, center.y + yOffset);
	}
	@Override
	public void rotate(int degrees) {}
	
}
