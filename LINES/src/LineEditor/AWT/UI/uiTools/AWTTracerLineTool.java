package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import shapes.Circle;
import shapes.LineSegment;
import shapes.Point;
import AWT.UI.AWTUILayer;
import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTCursorDrawer;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.UI.uiTools.WorldEditorMouseTool;
import LineEditor.data.WorldGeometryData;

public class AWTTracerLineTool extends WorldEditorMouseTool implements AWTUILayer {

	private Point		start;
	private LineSegment tracerLine;	
	
	private AWTCursorDrawer cursorDrawer;
	private AWTShapeDrawer  shapeDrawer;
	
	public AWTTracerLineTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		start = new Point(0,0);
		tracerLine 	= new LineSegment(start,position);
		
		cursorDrawer = AWTCursorDrawer.getCursorDrawer();
		shapeDrawer = AWTShapeDrawer.getShapeDrawer();
	}

	@Override
	public void setInitialPosition(int x, int y) {
		super.setInitialPosition(x, y);
		start.set(x, y);
		snapTracerLinePointToSomeWorldCircleIfWithinCircleBounds(start);
	}

	@Override
	public void setCurrentPosition(int x, int y) {
		super.setCurrentPosition(x, y);
		snapTracerLinePointToSomeWorldCircleIfWithinCircleBounds(position);
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
		if(pointsNotEquivalent(start, position)){
			for(Circle worldCircle : worldCircles()){
				if(pointShouldSnapToCenterOfWorldCircle(position, worldCircle)){
					createWorldLine(start, position);
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
			cursorDrawer.drawTriangularCrosshairCursor((int)position.x, (int)position.y);
		} else {
			cursorDrawer.setGraphics(g);
			cursorDrawer.setColor(AWTGraphicData.cursorColor);
			cursorDrawer.drawSmallXCursor((int)position.x, (int)position.y);
		}
	}

}
