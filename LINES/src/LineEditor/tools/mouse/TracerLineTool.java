package LineEditor.tools.mouse;

import shapes.Circle;
import shapes.LineSegment;
import shapes.Point;
import shapes.Shape;
import LineEditor.data.ShapeQuery;
import LineEditor.data.WorldGeometryData;

public class TracerLineTool extends WorldEditorMouseTool {
	
	protected Point		start;
	protected LineSegment tracerLine;	
	
	public TracerLineTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		start = new Point(0,0);
		tracerLine 	= new LineSegment(start,position);
	}

	public void setInitialPosition(int x, int y) {
		super.setInitialPosition(x, y);
		start.set(x, y);
		snapTracerLinePointToSomeWorldCircleIfWithinCircleBounds(start);
	}

	public void setCurrentPosition(int x, int y) {
		super.setCurrentPosition(x, y);
		snapTracerLinePointToSomeWorldCircleIfWithinCircleBounds(position);
	}
	
	private boolean pointsNotEquivalent(Point A, Point B){
		return !A.equals(B);
	}
	
	private ShapeQuery shouldPointSnapToCenterOfCircle = new ShapeQuery() {
		public boolean isSatisfiableGivenShape(Shape s) {
			return (s instanceof Circle) && pointShouldSnapToCenterOfWorldCircle(position, (Circle)s);
		}
	};
	
	private void snapTracerLinePointToSomeWorldCircleIfWithinCircleBounds(Point point) {
		Shape s = worldData.getShapeThatSatisfiesQuery(shouldPointSnapToCenterOfCircle);
		if (s != null) snapPointToCircleCenter(point, (Circle) s);
	}

	private ShapeQuery startPositionIntersectsCircle = new ShapeQuery() {
		public boolean isSatisfiableGivenShape(Shape s) {
			return (s instanceof Circle) && s.contains(start);
		}
	};
	
	private boolean startPositionIntersectsSomeWorldCircle(){
		return worldData.isShapeQuerySatisfied(startPositionIntersectsCircle);
	}
	
	protected boolean shouldAcceptRequest() { 
		return startPositionIntersectsSomeWorldCircle(); 
	}
	
	protected void performAction() {
		if (pointsNotEquivalent(start, position)) {
			if (worldData.isShapeQuerySatisfied(shouldPointSnapToCenterOfCircle)) {
				createWorldLine(start, position);
			}
		}
	}
	
}