package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import shapes.Circle;
import AWT.UI.AWTUILayer;
import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTCursorDrawer;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.UI.uiTools.WorldEditorMouseTool;
import LineEditor.data.WorldGeometryData;

public class AWTCircleMoverTool extends WorldEditorMouseTool implements AWTUILayer {

	private AWTShapeDrawer  shapeDrawer;
	private AWTCursorDrawer cursorDrawer;
	private Circle circleToMove;
	
	public AWTCircleMoverTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		circleToMove = new Circle(0,0,0);
		shapeDrawer  = new AWTShapeDrawer();
		cursorDrawer = new AWTCursorDrawer();
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
