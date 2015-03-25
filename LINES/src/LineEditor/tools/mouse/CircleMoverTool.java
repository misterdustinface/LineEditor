package LineEditor.tools.mouse;

import shapes.Circle;
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
	
	private Circle retrieveSelectedCircleToMove(){
		for(Circle worldCircle : worldData.getWorldPointCollisionCircles()){
			if(worldCircle.contains(position)){
				if(isSelected(worldCircle)){
					return worldCircle;
				}
			}
		}
		return circleToMove;
	}
	
	protected boolean shouldAcceptRequest() {
		return worldData.isCircleAtPositionSelected(position.x, position.y);
	}

	protected void performAction() {
		circleToMove.setCenterPosition(position.x, position.y);
	}

}