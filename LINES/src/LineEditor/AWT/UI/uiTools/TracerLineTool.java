package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.rendering.AWTShapeDrawer;
import data.graphics.AWTGraphicData;
import data.shapes.Circle;
import data.shapes.LineSegment;
import data.shapes.Point;
import data.shapes.WorldGeometryData;

public class TracerLineTool extends AWTWorldEditorMouseTool{

	private Point	start;
	private Point 	end;
	private LineSegment 	tracerLine;	
	
	private AWTShapeDrawer  shapeDrawer;
	
	public TracerLineTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		start = new Point(0,0);
		end = new Point(0,0);
		tracerLine 	= new LineSegment(start,end);
		shapeDrawer = new AWTShapeDrawer();
	}

	@Override
	public void setInitialPosition(int x, int y) {
		start.set(x, y);
		end.set(x, y);
		snapTracerLinePointToSomeWorldCircleIfWithinCircleBounds(start);
	}

	@Override
	public void setCurrentPosition(int x, int y) {
		end.set(x, y);
		snapTracerLinePointToSomeWorldCircleIfWithinCircleBounds(end);
	}
	
	private boolean pointsNotEquivalent(Point A, Point B){
		return !A.equals(B);
	}
	private void snapTracerLinePointToSomeWorldCircleIfWithinCircleBounds(Point point){
		for(Circle worldCircle : worldCircles()){
			if(pointShouldSnapToCenterOfWorldCircle(point, worldCircle)){
				snapPointToCircleCenter(point, worldCircle);
				return;
			}
		}
	}

	private boolean pointIntersectsSomeWorldCircle(float x, float y){
		for(Circle worldCircle : worldCircles()){
			if(worldCircle.contains(x, y)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected boolean shouldAcceptRequest() { 
		return pointIntersectsSomeWorldCircle(start.x, start.y); 
	}

	@Override
	protected void performAction() {
		if(pointsNotEquivalent(start, end)){
			for(Circle worldCircle : worldCircles()){
				if(pointShouldSnapToCenterOfWorldCircle(end, worldCircle)){
					createWorldLine(start, end);
					return;
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		if (shouldAcceptRequest()) {
			shapeDrawer.setGraphics(g);
			shapeDrawer.setColor(AWTGraphicData.dragLineTracingLine);
			shapeDrawer.drawLineSegment(tracerLine);
			
			cursorDrawer.setGraphics(g);
			cursorDrawer.setColor(AWTGraphicData.cursorColor);
			cursorDrawer.drawTriangularCrosshairCursor((int)end.x, (int)end.y);
		} else {
			cursorDrawer.setGraphics(g);
			cursorDrawer.setColor(AWTGraphicData.cursorColor);
			cursorDrawer.drawSmallXCursor((int)end.x, (int)end.y);
		}
	}

}
