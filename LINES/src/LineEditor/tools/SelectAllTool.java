package LineEditor.tools;

import shapes.Shape;
import LineEditor.data.WorldGeometryData;

public class SelectAllTool extends WorldEditorTool {

	public SelectAllTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}
	
	private void selectAllWorldShapes() {
		for (Shape s : collisionBounds()) 
			select(s);
	}
	
	private void toggleAllWorldShapes() {
		for (Shape s : collisionBounds()) 
			toggleSelected(s);
	}
	
	private boolean allWorldShapesAreAlreadySelected() {
		for (Shape s : collisionBounds()) 
			if (!isSelected(s))
				return false;
		return true;
	}
	
	protected boolean shouldAcceptRequest() {
		return true;
	}
	
	protected void performAction() {
		if (allWorldShapesAreAlreadySelected()) {
			toggleAllWorldShapes();
		} else {
			selectAllWorldShapes();
		}
	}
	
}