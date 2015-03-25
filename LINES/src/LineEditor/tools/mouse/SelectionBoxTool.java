package LineEditor.tools.mouse;

import shapes.Circle;
import shapes.LineSegment;
import shapes.Pipe;
import shapes.Point;
import LineEditor.data.WorldGeometryData;

public class SelectionBoxTool extends WorldEditorMouseTool {

	protected Point 		startPoint;
	protected Pipe 			selectionBox;
	
	public SelectionBoxTool(WorldGeometryData WORLD_DATA){
		super(WORLD_DATA);
		startPoint   = new Point(0,0);
		LineSegment spanLine = new LineSegment(startPoint, position);
		selectionBox = new Pipe(spanLine, 1);
	}
	
	public void setInitialPosition(int x, int y) {
		super.setInitialPosition(x, y);
		startPoint.set(x, y);
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
		return selectionRectangle.getBoundingRectangle().contains(circle.center());
	}
	
	protected boolean selectionRectangleContainsRectangle(Pipe selectionRectangle, Pipe rectangularPipe){
		return selectionRectangle.intersects(rectangularPipe.getBoundingRectangle());
	}

	protected boolean shouldAcceptRequest() {
		return true;
	}

	protected void performAction() {
		selectWorldShapes();
	}
	
}