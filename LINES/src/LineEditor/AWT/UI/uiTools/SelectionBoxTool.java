package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.data.WorldGeometryData;
import data.shapes.Circle;
import data.shapes.LineSegment;
import data.shapes.Pipe;
import data.shapes.Point;

public class SelectionBoxTool extends AWTWorldEditorMouseTool{

	protected Point a; // keep point in scope
	protected Point b; // keep point in scope
	protected Pipe selectionBox; // scope of points is hidden.
	
	private AWTShapeDrawer  shapeDrawer;
	
	public SelectionBoxTool(WorldGeometryData WORLD_DATA){
		super(WORLD_DATA);
		a = new Point(0,0);
		b = new Point(0,0);
		selectionBox = new Pipe(new LineSegment(a, b), 1);
		shapeDrawer  = new AWTShapeDrawer();
	}
	
	@Override
	public void setInitialPosition(int x, int y) {
		a.set(x, y);
		b.set(x, y);
	}
	@Override
	public void setCurrentPosition(int x, int y){
		b.set(x, y);
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
		shapeDrawer.drawPipe(selectionBox);
		shapeDrawer.setColor(AWTGraphicData.dragSelectionBoxBorder);
		shapeDrawer.drawPipeBorder(selectionBox);

		cursorDrawer.setGraphics(g);
		cursorDrawer.setColor(AWTGraphicData.cursorColor);
		cursorDrawer.drawCrosshairCursor((int)b.x, (int)b.y);
	}

}
