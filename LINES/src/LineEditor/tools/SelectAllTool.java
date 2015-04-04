package LineEditor.tools;

import shapes.Shape;
import LineEditor.data.ShapeFunction;
import LineEditor.data.ShapeQuery;
import LineEditor.data.WorldGeometryData;

public class SelectAllTool extends WorldEditorTool {

	public SelectAllTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}
	
	private ShapeFunction selectShape = new ShapeFunction() {
		public void manipulateShape(Shape s) {
			worldData.select(s);
		}
	};
	
	private void selectAllWorldShapes() {
		worldData.performShapeFunctionOnAllShapes(selectShape);
	}
	
	private ShapeFunction toggleShape = new ShapeFunction() {
		public void manipulateShape(Shape s) {
			worldData.toggleSelected(s);
		}
	};
	
	private void toggleAllWorldShapes() {
		worldData.performShapeFunctionOnAllShapes(toggleShape);
	}
	
	private ShapeQuery isNotSelected = new ShapeQuery() {
		public boolean isSatisfiableGivenShape(Shape s) {
			return !worldData.isSelected(s);
		}
	};
	
	private boolean allWorldShapesAreAlreadySelected() {
		boolean allSelected = !worldData.isShapeQuerySatisfied(isNotSelected);
		return allSelected;
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