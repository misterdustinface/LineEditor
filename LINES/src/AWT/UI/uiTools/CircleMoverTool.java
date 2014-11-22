package UI.AWTuiTools;

import java.awt.Graphics2D;

import rendering.AWT.AWTShapeDrawer;
import data.graphics.AWTGraphicData;
import data.shapes.Circle;
import data.shapes.Point;
import data.shapes.WorldGeometryData;

public class CircleMoverTool extends AWTWorldEditorMouseTool{

	private AWTShapeDrawer  shapeDrawer;
	
	private Circle circleToMove;
	private Point  position;
	
	public CircleMoverTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		circleToMove = new Circle(0,0,0);
		position 	 = new Point(0,0);
		shapeDrawer  = new AWTShapeDrawer();
	}
	
	@Override
	public void setInitialPosition(int x, int y) {
		position.set(x, y);
		circleToMove = retrieveSelectedCircleToMove();
	}

	@Override
	public void setCurrentPosition(int x, int y) {
		position.set(x, y);
		performAction();
	}
	
	private Circle retrieveSelectedCircleToMove(){
		for(Circle worldCircle : worldData.getCopyOfWorldPointCollisionCircles()){
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

	@Override
	public void render(Graphics2D g) {
		shapeDrawer.setGraphics(g);
		shapeDrawer.setColor(AWTGraphicData.movePointHighlight);
		shapeDrawer.drawCircle(circleToMove);

		cursorDrawer.setGraphics(g);
		cursorDrawer.setColor(AWTGraphicData.cursorColor);
		cursorDrawer.drawCrosshairCursor((int)position.x, (int)position.y);
	}

}
