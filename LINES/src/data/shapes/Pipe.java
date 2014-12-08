package data.shapes;

public class Pipe implements Shape{
	
	private static final String NAME = "PIPE";
	private static long instanceCounter = 0;
	private long instance;
	private String id;
	
	public  LineSegment  centerLine;
	private float 		 thickness;
	
	private Point 		 previousA;
	private Point 		 previousB;
	private Polygon 	 area;
	
	public Pipe(LineSegment CENTERLINE, float THICKNESS){
		instance   = instanceCounter++;
		id 		   = NAME + instance;
		centerLine = CENTERLINE; thickness = THICKNESS;
		previousA  = new Point(centerLine.a.x, centerLine.a.y);
		previousB  = new Point(centerLine.b.x, centerLine.b.y);
		area       = new Polygon(4);
		calculateArea();
	}
	
	public float thickness(){return thickness;}
	public void scaleThickness(double percent){
		thickness *= percent;
		calculateArea();
	}
	
	public Polygon getArea(){
		
		if(!previousA.equals(centerLine.a) || !previousB.equals(centerLine.b)){
			resetPreviousPoints();
			calculateArea();
		}

		return area;
	}
	
	public Rectangle getBoundingRectangle(){
		return getArea().getBoundingRectangle();
	}
	
	private void resetPreviousPoints(){
		previousA.set(centerLine.a.x, centerLine.a.y);
		previousB.set(centerLine.b.x, centerLine.b.y);
	}
	
	private void calculateArea(){
		double perpTheta 	= Math.perpendicular(Math.theta(centerLine.a, centerLine.b));
		//rectangle trig loves opposite day
		float yOff 			= (float)java.lang.Math.cos(perpTheta)*thickness;
		float xOff 			= (float)java.lang.Math.sin(perpTheta)*thickness;
		
		area.clearPoints();
		
		area.addPoint(	(int)(centerLine.a.x - xOff), 
						(int)(centerLine.a.y - yOff));
		area.addPoint(	(int)(centerLine.a.x + xOff), 
						(int)(centerLine.a.y + yOff));
		area.addPoint(	(int)(centerLine.b.x + xOff), 
						(int)(centerLine.b.y + yOff));
		area.addPoint(	(int)(centerLine.b.x - xOff), 
						(int)(centerLine.b.y - yOff));	
	}

	@Override
	public boolean contains(Point point) {
		return getArea().contains(point.x, point.y);
	}
	@Override
	public boolean contains(float x, float y) {
		return getArea().contains(x, y);
	}
	
	public boolean intersects(Rectangle rect){
		return getArea().intersects(rect);
	}
	
	@Override
	public String ID() { return id; }

	@Override
	public void scale(float percent) {
		centerLine.scale(percent);
		scaleThickness(percent);
		calculateArea();
	}
	@Override
	public void shift(float xOffset, float yOffset){
		centerLine.shift(xOffset, yOffset);
	}
	@Override
	public void rotate(int degrees){
		centerLine.rotate(degrees);
	}

	@Override
	public void setPosition(float x, float y) {
		Point midp = centerLine.midpoint();
		float xoff = Math.abs(Math.abs(x) - Math.abs(midp.x));
		float yoff = Math.abs(Math.abs(y) - Math.abs(midp.y));
		if (x < midp.x) { xoff = -xoff; }
		if (y < midp.y) { yoff = -yoff;	}
		shift(xoff, yoff);
	}

//	public Polygon getArea(float scale){
//		
//		double perpTheta 	= Math.perpendicular(Math.theta(center.a, center.b));
//		//rectangle trig loves opposite day
//		float yOff 			= (float)java.lang.Math.cos(perpTheta)*thickness;
//		float xOff 			= (float)java.lang.Math.sin(perpTheta)*thickness;
//		
//		Polygon p = new Polygon();
//		p.addPoint(	(int)((center.a.x - xOff)*scale), 
//					(int)((center.a.y - yOff)*scale));
//		p.addPoint(	(int)((center.a.x + xOff)*scale), 
//					(int)((center.a.y + yOff)*scale));
//		p.addPoint(	(int)((center.b.x + xOff)*scale), 
//					(int)((center.b.y + yOff)*scale));
//		p.addPoint(	(int)((center.b.x - xOff)*scale), 
//					(int)((center.b.y - yOff)*scale));
//		return p;
//	}
	
	
}
