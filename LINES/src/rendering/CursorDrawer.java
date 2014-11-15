package rendering;

public interface CursorDrawer {
	void drawCrosshairCursor			(int X, int Y);
	void drawTriangularCrosshairCursor	(int X, int Y);
	void drawLargeCircleCursor			(int X, int Y);
	void drawSmallCircleCursor			(int X, int Y);
	void drawLargeXCursor				(int X, int Y);
	void drawSmallXCursor				(int X, int Y);
	void drawPointerCursor				(int X, int Y);
}
