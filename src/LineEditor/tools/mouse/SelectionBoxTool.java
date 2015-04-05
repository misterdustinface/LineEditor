package LineEditor.tools.mouse;

import shapes.Circle;
import shapes.LineSegment;
import shapes.Pipe;
import shapes.Point;
import shapes.Shape;
import LineEditor.data.ShapeFunction;
import LineEditor.data.WorldGeometryData;

public class SelectionBoxTool extends WorldEditorMouseTool {

	protected Point startPoint;
	protected Pipe selectionBox;
	
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
	
	private ShapeFunction toggleShapeSelection = new ShapeFunction() {
		public void manipulateShape(Shape s) {
			if ((s instanceof Circle) && (selectionRectangleContainsCircleCenter((Circle)s))) {
				worldData.toggleSelected(s);
			}
			if ((s instanceof Pipe) && (selectionRectangleContainsPipe((Pipe) s))) {
				worldData.toggleSelected(s);
			}
		}
	};
	
	private void selectWorldShapes(){
		worldData.performShapeFunctionOnAllShapes(toggleShapeSelection);
	}
	
	protected boolean selectionRectangleContainsCircleCenter(Circle circle){
		return selectionBox.getBoundingRectangle().contains(circle.center());
	}
	
	protected boolean selectionRectangleContainsPipe(Pipe rectangularPipe){
		return selectionBox.intersects(rectangularPipe.getBoundingRectangle());
	}

	protected boolean shouldAcceptRequest() {
		return true;
	}

	protected void performAction() {
		selectWorldShapes();
	}
	
}