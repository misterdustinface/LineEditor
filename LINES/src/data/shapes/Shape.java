package data.shapes;

public interface Shape {
	String  ID();
	boolean contains(Point point);
	boolean contains(float x, float y);
	void	setPosition(float x, float y);
	void    scale(float percent);
	void	shift(float xOffset, float yOffset);
	void	rotate(int degrees);
}
