package LineEditor.tools.mouse;

import shapes.Circle;
import shapes.LineSegment;
import shapes.Point;
import LineEditor.data.WorldGeometryData;

public class TracerLineTool extends WorldEditorMouseTool {
	
	protected Point		start;
	protected LineSegment tracerLine;	
	
	public TracerLineTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		start = new Point(0,0);
		tracerLine 	= new LineSegment(start,position);
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
	
}