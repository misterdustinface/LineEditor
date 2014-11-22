package AWT.rendering;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import AWT.UI.AWTViewport;
import UI.MouseUserDevice;
import data.shapes.Rectangle;

public class AWTGridDrawer implements AWTUILayer {
	
	public Color BACKGROUND_COLOR  	= new Color(250,250,250);
	public Color MINOR_LINES_COLOR 	= new Color(225,225,225);
	public Color MAJOR_LINES_COLOR 	= new Color(180,180,180);
	public Color ORIGIN_COLOR 		= Color.RED;
	public int 	MINOR_LINE_SPACING 	= 16;
	public int 	MAJOR_LINE_SPACING 	= MINOR_LINE_SPACING * 8;
	
	private int 	xPos, yPos, width, height;
	private float 	zoom;
	
	private Rectangle drawingBounds;
	private AWTViewport  viewport;
	
	public AWTGridDrawer(AWTViewport VIEWPORT){
		xPos = yPos = width = height = 0;
		zoom = 1.0f;
		drawingBounds = new Rectangle();
		viewport = VIEWPORT;
	}
	
	@Override
	public void render(Graphics2D g) {
		drawBackground(g);
		drawMinorLines(g);
		drawMajorLines(g);
		drawOriginLines(g);
	}
	@Override
	public void update(MouseUserDevice mouse) {
		setCenter((int)viewport.getXPosition(), (int)viewport.getYPosition());
		setDimensions(viewport.getWidth(), viewport.getHeight());
		setZoom(viewport.getZoom());
		calculateDrawingBounds();
	}
	
	private void setCenter(int X, int Y) { xPos = X; yPos = Y; }
	private void setDimensions(int WIDTH, int HEIGHT) { width = WIDTH; height = HEIGHT; }
	private void setZoom(float ZOOM) { zoom = ZOOM; }
	
	private void calculateDrawingBounds(){
		drawingBounds.x 	 = -(int)(width/zoom)-xPos;
		drawingBounds.y 	 = -(int)(height/zoom)-yPos;
		drawingBounds.width  = 2*(int)(width/zoom);
		drawingBounds.height = 2*(int)(height/zoom);
	}
	private void drawBackground(Graphics g){
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(drawingBounds.x, drawingBounds.y, drawingBounds.width, drawingBounds.height);
	}
	private void drawMinorLines(Graphics g){
		g.setColor(MINOR_LINES_COLOR);
		makeCrissCrossLines(g, MINOR_LINE_SPACING);
	}
	private void drawMajorLines(Graphics g){
		g.setColor(MAJOR_LINES_COLOR);
		makeCrissCrossLines(g, MAJOR_LINE_SPACING);
	}
	
	private void makeCrissCrossLines(Graphics g, int SPACING) {
		for(int i = 0; i < width; i+=  SPACING)
			g.drawLine(i, drawingBounds.y, i , drawingBounds.height);
		for(int j = 0; j < height; j+= SPACING)
			g.drawLine(drawingBounds.x, j, drawingBounds.width , j);
		for(int i = 0; i > -width; i-= SPACING)
			g.drawLine(i, drawingBounds.y, i , drawingBounds.height);
		for(int j = 0; j > -height;j-= SPACING)
			g.drawLine(drawingBounds.x, j, drawingBounds.width , j);
	}
	
	private void drawOriginLines(Graphics g){
		g.setColor(ORIGIN_COLOR);
		g.drawLine(0,drawingBounds.y,0,drawingBounds.height);
		g.drawLine(drawingBounds.x, 0, drawingBounds.width, 0);
	}
	
}
