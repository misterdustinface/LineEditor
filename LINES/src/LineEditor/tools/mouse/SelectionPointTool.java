package LineEditor.tools.mouse;

import shapes.Point;
import shapes.Shape;
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
	
	private void selectWorldShapes(Point selectionPoint){
		for(Shape s : collisionBounds())
			if(s.contains(selectionPoint))
				toggleSelected(s);
	}

	protected boolean shouldAcceptRequest() {
		return hasBeenMovedSinceLastUse();
	}

	protected void performAction() {
		positionOfLastUse.setPosition(position);
		selectWorldShapes(position);
	}
	
}