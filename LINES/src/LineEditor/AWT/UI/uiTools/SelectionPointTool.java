package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import LineEditor.data.WorldGeometryData;
import data.shapes.Point;
import data.shapes.Shape;

public class SelectionPointTool extends AWTWorldEditorMouseTool{

	private Point position;
	
	public SelectionPointTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		position = new Point(0,0);
	}

	@Override
	public void setInitialPosition(int x, int y) {
		position.set(x, y);
	}

	@Override
	public void setCurrentPosition(int x, int y) {
		position.set(x, y);
	}
	
	private void selectWorldShapes(Point selectionPoint){
		for(Shape s : collisionBounds())
			if(s.contains(selectionPoint))
				toggleSelected(s);
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return true;
	}

	@Override
	protected void performAction() {
		selectWorldShapes(position);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(AWTGraphicData.selectedPointCircleHighlight);
//		cursorDrawer.drawTriangularCrosshairCursor((int)position.x, (int)position.y);
	}

}
