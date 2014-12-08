package data.shapes;


public class Polygon {

	private final int MAX_NUMBER_OF_POINTS;
	private int  points;
	public int[] xpoints;
	public int[] ypoints;
	
	public Polygon(int MAX_NUMBER_OF_POINTS) {
		xpoints = new int[MAX_NUMBER_OF_POINTS];
		ypoints = new int[MAX_NUMBER_OF_POINTS];
		this.MAX_NUMBER_OF_POINTS = MAX_NUMBER_OF_POINTS;
	}
	
	public void clearPoints() {
		points = 0;
	}
	
	public void addPoint(int x, int y) {
		if (points < MAX_NUMBER_OF_POINTS) {
			xpoints[points] = x;
			ypoints[points] = y;
			++points;
		}
	}
	
	public int getNumberOfPoints() { return points; }
	
	public boolean contains(Point point) {
		return contains(point.x, point.y);
	}
	
	// right direction ray odds/evens intersection solution
	public boolean contains(float X, float Y) {
		boolean doesContain = isRightFacingRayFromXYCollidingWithPolygonLinesegmentAB(X, Y, 0, points-1);
		for(int i = 1; i < getNumberOfPoints(); ++i)
			if(isRightFacingRayFromXYCollidingWithPolygonLinesegmentAB(X, Y, i, i-1))
				doesContain = !doesContain;
		return doesContain;
	}
	
	// if Y is within yRange then we can compute: X <= xBase + (xRange * yPercent)
	private boolean isRightFacingRayFromXYCollidingWithPolygonLinesegmentAB(float X, float Y, int A, int B) {
		return isWithinBounds(ypoints[A], Y, ypoints[B])
		&& (X <= xpoints[A] + (xpoints[B] - xpoints[A]) * (Y - ypoints[A]) / (ypoints[B] - ypoints[A]) );
	}
	
	private boolean isWithinBounds(float A, float Y, float B) {
		return (Y < A) ^ (Y < B);
	}
	
//	public boolean intersects(Polygon poly) {
//		for(int i = 0; i < poly.points; ++i)
//			if(contains(poly.xpoints[i], poly.ypoints[i]))
//				return true;
//		for(int i = 0; i < points; ++i)
//			if(poly.contains(xpoints[i], ypoints[i]))
//				return true;
//		return false;
//	}
	
	public boolean intersects(Rectangle rect) {
		return intersectsX(rect) && intersectsY(rect);
	}
	
	private boolean intersectsX(Rectangle rect) {
		return intersectionRange1D(smallest(xpoints), largest(xpoints), rect.x, rect.x + rect.width);
	}
	
	private boolean intersectsY(Rectangle rect) {
		return intersectionRange1D(smallest(ypoints), largest(ypoints), rect.y, rect.y + rect.height);
	}
	
	private boolean intersectionRange1D(int x1, int X1, int x2, int X2) {
		return (x1 <= x2 && x2 <= X1 ) || (x1 <= X2 && X2 <= X1 );
	}
	
	public Rectangle getBoundingRectangle() {
		int x = smallest(xpoints);
		int y = smallest(ypoints);
		return new Rectangle(x, y, largest(xpoints) - x, largest(ypoints) - y);
	}
	
	private int smallest(int[] ar) {
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < points; ++i)
			if(ar[i] < result)
				result = ar[i];
		return result;
	}
	
	private int largest(int[] ar) {
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < points; ++i)
			if(ar[i] > result)
				result = ar[i];
		return result;
	}
}