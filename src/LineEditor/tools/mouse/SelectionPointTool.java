package LineEditor.tools.mouse;

import shapes.Point;
import shapes.Shape;
import LineEditor.data.ShapeFunction;
import LineEditor.data.WorldGeometryData;

public class SelectionPointTool extends WorldEditorMouseTool {
	
	private Point positionOfLastUse;
	
	public SelectionPointTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		positionOfLastUse = new Point(0,0);
	}

	private boolean hasBeenMovedSinceLastUse() { 
		return !positionOfLastUse.equals(position); 
	}
	
	private ShapeFunction toggleShapeSelection = new ShapeFunction() {
		public void manipulateShape(Shape s) {
			if (s.contains(positionOfLastUse)) {
				worldData.toggleSelected(s);
			}
		}
	};
	
	private void selectWorldShapes(Point selectionPoint){
		worldData.performShapeFunctionOnAllShapes(toggleShapeSelection);
	}

	protected boolean shouldAcceptRequest() {
		return hasBeenMovedSinceLastUse();
	}

	protected void performAction() {
		positionOfLastUse.setPosition(position);
		selectWorldShapes(position);
	}
	
}