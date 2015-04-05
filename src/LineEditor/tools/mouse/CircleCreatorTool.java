package LineEditor.tools.mouse;

import shapes.Pipe;
import shapes.Point;
import shapes.Shape;
import LineEditor.data.ShapeQuery;
import LineEditor.data.WorldGeometryData;

public class CircleCreatorTool extends WorldEditorMouseTool {
	
	private Point positionOfLastUse;
	
	public CircleCreatorTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		positionOfLastUse = new Point(0,0);
	}
	
	private boolean hasBeenMovedSinceLastUse() { 
		return ! positionOfLastUse.equals(position); 
	}
	
	private void createWorldCircleAtPoint(int x, int y) {
		worldData.createPoint(x,y);
	}
	
	private void splitIntersectedRectangleAtMidpoint() {
		worldData.splitCollisionBox(getIntersectedRectangle(), 50);
	}
	
	private ShapeQuery pointIntersectsPipe = new ShapeQuery() {
		public boolean isSatisfiableGivenShape(Shape s) {
			return (s instanceof Pipe) && ((Pipe) s).getArea().contains(position.x, position.y);
		}
	};
	
	private boolean pointIntersectsSomeWorldRectangle() {
		return worldData.isShapeQuerySatisfied(pointIntersectsPipe);
	}
	
	private Pipe getIntersectedRectangle() {
		return (Pipe) worldData.getShapeThatSatisfiesQuery(pointIntersectsPipe);
	}

	protected boolean shouldAcceptRequest() {
		return hasBeenMovedSinceLastUse();
	}

	protected void performAction() {
		positionOfLastUse.set(position.x, position.y);
		if (pointIntersectsSomeWorldRectangle()) {
			splitIntersectedRectangleAtMidpoint();
		} else {
			createWorldCircleAtPoint((int)position.x, (int)position.y);
		}
	}

}