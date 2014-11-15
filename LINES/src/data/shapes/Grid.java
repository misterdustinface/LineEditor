package data.shapes;

public class Grid {
	public int x, y, rows, cols;
	
	public Grid() {
		setBounds(0,0,0,0);
	}
	
	public Grid(Point XY, int ROWS, int COLS) {
		setBounds((int)XY.x, (int)XY.y, ROWS, COLS);
	}
	
	public Grid(int X, int Y, int ROWS, int COLS) {
		setBounds(X,Y,ROWS,COLS);
	}
	
	public void setBounds(int X, int Y, int ROWS, int COLS) {
		x = X; y = Y; rows = ROWS; cols = COLS;
	}
}
