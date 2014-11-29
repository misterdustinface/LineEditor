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
	
	public boolean contains(float X, float Y) {
		// TODO - make more precise algorithm.
		return getBounds().contains(X, Y);
	}
	
	public boolean intersects(Rectangle rect) {
		return intersectsX(rect) && intersectsY(rect);
	}
	
	private boolean intersectsX(Rectangle rect) {
		return intersectionRange1D(smallestX(), largestX(), rect.x, rect.x + rect.width);
	}
	
	private boolean intersectsY(Rectangle rect) {
		return intersectionRange1D(smallestY(), largestY(), rect.y, rect.height);
	}
	
	private boolean intersectionRange1D(int x1, int X1, int x2, int X2) {
		return (x1 <= x2 && x2 <= X1 ) || (x1 <= X2 && X2 <= X1 );
	}
	
	public Rectangle getBounds() {
		int x = smallestX();
		int y = smallestY();
		return new Rectangle(x, y, largestX() - x, largestY() - y);
	}
	
	private int smallestY() {
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < points; ++i)
			if(ypoints[i] < result)
				result = ypoints[i];
		return result;
	}
	private int smallestX() {
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < points; ++i)
			if(xpoints[i] < result)
				result = xpoints[i];
		return result;
	}

	private int largestY() {
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < points; ++i)
			if(ypoints[i] > result)
				result = ypoints[i];
		return result;
	}
	private int largestX() {
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < points; ++i)
			if(xpoints[i] > result)
				result = xpoints[i];
		return result;
	}

}
