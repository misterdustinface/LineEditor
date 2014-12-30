package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import shapes.Circle;
import shapes.LineSegment;
import shapes.Pipe;
import shapes.Point;
import AWT.UI.AWTUILayer;
import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTCursorDrawer;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.UI.uiTools.WorldEditorMouseTool;
import LineEditor.data.WorldGeometryData;

public class AWTSelectionBoxTool extends WorldEditorMouseTool implements AWTUILayer {

	protected Point boxStart; // keep point in scope
	protected Pipe selectionBox; // scope of points is hidden.
	private AWTCursorDrawer cursorDrawer;
	private AWTShapeDrawer  shapeDrawer;
	
	public AWTSelectionBoxTool(WorldGeometryData WORLD_DATA){
		super(WORLD_DATA);
		boxStart = new Point(0,0);
		selectionBox = new Pipe(new LineSegment(boxStart, position), 1);
		cursorDrawer = new AWTCursorDrawer();
		shapeDrawer  = new AWTShapeDrawer();
	}
	
	@Override
	public void setInitialPosition(int x, int y) {
		super.setInitialPosition(x, y);
		boxStart.set(x, y);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	private void selectWorldShapes(){
		selectWorldCircles(selectionBox);
		selectWorldRectangles(selectionBox);
	}

	private void selectWorldCircles(Pipe selectionRectangle){
		for(Circle worldCircle : worldCircles())
			if(selectionRectangleContainsCircleCenter(selectionRectangle, worldCircle))
				toggleSelected(worldCircle);
	}
	private void selectWorldRectangles(Pipe selectionRectangle){
		for(Pipe worldRectangle : worldRectangles())
			if(selectionRectangleContainsRectangle(selectionRectangle, worldRectangle))
				toggleSelected(worldRectangle);
	}
	
	protected boolean selectionRectangleContainsCircleCenter(Pipe selectionRectangle, Circle circle){
		return selectionRectangle.contains(circle.center());
	}
	protected boolean selectionRectangleContainsRectangle(Pipe selectionRectangle, Pipe rectangularPipe){
		return selectionRectangle.intersects(rectangularPipe.getBoundingRectangle());
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return true;
	}

	@Override
	protected void performAction() {
		selectWorldShapes();
	}

	@Override
	public void render(Graphics2D g) {
		shapeDrawer.setGraphics(g);
		shapeDrawer.setColor(AWTGraphicData.dragSelectionBoxTransparentArea);
				
		shapeDrawer.drawFilledRectangle(selectionBox.getBoundingRectangle());
		shapeDrawer.setColor(AWTGraphicData.dragSelectionBoxBorder);
		shapeDrawer.drawRectangle(selectionBox.getBoundingRectangle());

		cursorDrawer.setGraphics(g);
		cursorDrawer.setColor(AWTGraphicData.cursorColor);
		cursorDrawer.drawCrosshairCursor((int)position.x, (int)position.y);
	}

}
