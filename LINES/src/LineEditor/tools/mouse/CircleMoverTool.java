package LineEditor.tools.mouse;

import shapes.Circle;
import LineEditor.data.WorldGeometryData;

public class CircleMoverTool extends WorldEditorMouseTool {
	
	protected Circle circleToMove;
	
	public CircleMoverTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		circleToMove = new Circle(0,0,0);
	}
	
	@Override
	public void setInitialPosition(int x, int y) {
		super.setInitialPosition(x, y);
		circleToMove = retrieveSelectedCircleToMove();
	}

	@Override
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
	
	@Override
	protected boolean shouldAcceptRequest() {
		return worldData.isCircleAtPositionSelected(position.x, position.y);
	}

	@Override
	protected void performAction() {
		circleToMove.setPosition(position.x, position.y);
	}

}
