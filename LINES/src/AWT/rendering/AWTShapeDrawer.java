package AWT.rendering;

//import uiTools.WorldEditorUITool;

import AWT.graphicdata.AWTGraphicData;
import rendering.ShapeDrawer;
import data.shapes.Circle;
import data.shapes.LineSegment;
import data.shapes.Point;
import data.shapes.Pipe;
import data.shapes.Polygon;
import data.shapes.Rectangle;

final public class AWTShapeDrawer extends AWTRenderer implements ShapeDrawer {
	
	public AWTShapeDrawer() {}
	
	public void drawPolygonBorder(Polygon p){
		graphics.drawPolygon(p.xpoints, p.ypoints, p.getNumberOfPoints());
	}
	
	public void drawPolygon(Polygon p){
		graphics.fillPolygon(p.xpoints, p.ypoints, p.getNumberOfPoints());
	}
	
	public void drawRectangle(Rectangle r){
		graphics.fillRect(r.x, r.y, r.width, r.height);
	}
	
	public void drawLineSegment(LineSegment l){
		graphics.drawLine(	(int)(l.a().x), 
							(int)(l.a().y), 
							(int)(l.b().x), 
							(int)(l.b().y));
	}
	
	public void drawCircle(Circle c){
		graphics.drawArc(	(int)((c.x()-c.radius())), 
							(int)((c.y()-c.radius())), 
							(int)(c.diameter()), 
							(int)(c.diameter()), 0, 360);
	}
	
	public void drawPoint(Point p){
		graphics.fillOval(	(int)((p.x-((AWTGraphicData.pointSize)>>1))), 
							(int)((p.y-((AWTGraphicData.pointSize)>>1))), 
							AWTGraphicData.pointSize, AWTGraphicData.pointSize);
	}
	

	public void drawPipe(Pipe pipe){
		int x = pipe.centerLine.a().x < pipe.centerLine.b().x ? (int)pipe.centerLine.a().x : (int)pipe.centerLine.b().x;
		int y = pipe.centerLine.a().y < pipe.centerLine.b().y ? (int)pipe.centerLine.a().y : (int)pipe.centerLine.b().y;
		
		graphics.fillRect(	x,
							y, 
							(int)Math.abs(pipe.centerLine.b().x - pipe.centerLine.a().x), 
							(int)Math.abs(pipe.centerLine.b().y - pipe.centerLine.a().y));
	}

	public void drawPipeBorder(Pipe pipe){
		int x = pipe.centerLine.a().x < pipe.centerLine.b().x ? (int)pipe.centerLine.a().x : (int)pipe.centerLine.b().x;
		int y = pipe.centerLine.a().y < pipe.centerLine.b().y ? (int)pipe.centerLine.a().y : (int)pipe.centerLine.b().y;
		
		graphics.drawRect(	x,
							y, 
							(int)Math.abs(pipe.centerLine.b().x - pipe.centerLine.a().x), 
							(int)Math.abs(pipe.centerLine.b().y - pipe.centerLine.a().y));
	}
}
