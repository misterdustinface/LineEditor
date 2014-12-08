package data.shapes;

public class Rectangle {

	public int x, y, width, height;
	
	public Rectangle() {
		setBounds(0,0,0,0);
	}
	
	public Rectangle(Point XY, int WIDTH, int HEIGHT) {
		setBounds((int)XY.x, (int)XY.y, WIDTH, HEIGHT);
	}
	
	public Rectangle(int X, int Y, int WIDTH, int HEIGHT) {
		setBounds(X,Y,WIDTH,HEIGHT);
	}
	
	public boolean contains(float X, float Y) {
		return x <= X && X <= (x+width) 
			&& y <= Y && Y <= (y+height);
	}
	
	public void setBounds(int X, int Y, int WIDTH, int HEIGHT) {
		if(WIDTH  < 0) { WIDTH  = -WIDTH;  X -= WIDTH; }
		if(HEIGHT < 0) { HEIGHT = -HEIGHT; Y -= HEIGHT;}
		
		x = X;
		y = Y;
		width = WIDTH;
		height = HEIGHT;
	}
	
	public int getCenterX() { return x + (width /2); }
	public int getCenterY() { return y + (height/2); }
}
