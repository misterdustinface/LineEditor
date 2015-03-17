package LineEditor.tools.mouse;

import shapes.Pipe;
import shapes.Point;
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
		worldData.splitCollisionBox(getIntersectedRectangle(position.x, position.y), 50);
	}
	
	private boolean pointIntersectsSomeWorldRectangle(float x, float y) {
		for (Pipe worldRectangle : worldRectangles()) {
			if (worldRectangle.getArea().contains(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	private Pipe getIntersectedRectangle(float x, float y) {
		for (Pipe worldRectangle : worldRectangles()) {
			if (worldRectangle.getArea().contains(x, y)) {
				return worldRectangle;
			}
		}
		return null; // TODO throw exception
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return hasBeenMovedSinceLastUse();
	}

	@Override
	protected void performAction() {
		positionOfLastUse.set(position.x, position.y);
		if (pointIntersectsSomeWorldRectangle(position.x, position.y)) {
			splitIntersectedRectangleAtMidpoint();
		} else {
			createWorldCircleAtPoint((int)position.x, (int)position.y);
		}
	}
}
