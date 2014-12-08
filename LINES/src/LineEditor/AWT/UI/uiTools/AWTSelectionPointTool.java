package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import LineEditor.data.WorldGeometryData;
import data.shapes.Point;
import data.shapes.Shape;

public class AWTSelectionPointTool extends AWTWorldEditorMouseTool{

	private Point	positionOfLastUse;
	
	public AWTSelectionPointTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		positionOfLastUse = new Point(0,0);
	}

	private boolean hasBeenMovedSinceLastUse() { return ! positionOfLastUse.equals(position); }
	
	private void selectWorldShapes(Point selectionPoint){
		for(Shape s : collisionBounds())
			if(s.contains(selectionPoint))
				toggleSelected(s);
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return hasBeenMovedSinceLastUse();
	}

	@Override
	protected void performAction() {
		positionOfLastUse.setPosition(position);
		selectWorldShapes(position);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(AWTGraphicData.selectedPointCircleHighlight);
//		cursorDrawer.drawTriangularCrosshairCursor((int)position.x, (int)position.y);
	}

}
