package LineEditor.tools.mouse;

import shapes.Circle;
import shapes.Shape;
import LineEditor.data.ShapeQuery;
import LineEditor.data.WorldGeometryData;

public class CircleMoverTool extends WorldEditorMouseTool {
	
	protected Circle circleToMove;
	
	public CircleMoverTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		circleToMove = new Circle(0,0,0);
	}
	
	public void setInitialPosition(int x, int y) {
		super.setInitialPosition(x, y);
		circleToMove = retrieveSelectedCircleToMove();
	}

	public void setCurrentPosition(int x, int y) {
		super.setCurrentPosition(x, y);
		performAction();
	}
	
	private ShapeQuery isCircleAtPositionSelected = new ShapeQuery() {
		public boolean isSatisfiableGivenShape(Shape s) {
			return (s instanceof Circle) && (s.contains(position) && worldData.isSelected(s));
		}
	};
	
	private Circle retrieveSelectedCircleToMove() {
		Circle c = (Circle) worldData.getShapeThatSatisfiesQuery(isCircleAtPositionSelected);
		if (c == null) c = circleToMove;
		return c;
	}
	
	protected boolean shouldAcceptRequest() {
		return worldData.isShapeQuerySatisfied(isCircleAtPositionSelected);
	}

	protected void performAction() {
		circleToMove.setCenterPosition(position.x, position.y);
	}

}