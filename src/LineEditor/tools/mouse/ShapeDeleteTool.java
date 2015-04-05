package LineEditor.tools.mouse;

import shapes.Shape;
import timers.DebounceTimer;
import LineEditor.data.ShapeQuery;
import LineEditor.data.WorldGeometryData;

public class ShapeDeleteTool extends WorldEditorMouseTool {
	
	private DebounceTimer debounceTimer;
	
	public ShapeDeleteTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		debounceTimer = new DebounceTimer();
		debounceTimer.setDebounceTime__sec(0.5);
	}
	
	private ShapeQuery containsPosition = new ShapeQuery() {
		public boolean isSatisfiableGivenShape(Shape s) {
			return s.contains(position);
		}
	};
	
	private void findAndRemoveFirstFoundWorldShapeAtPosition(){
		Shape s = worldData.getShapeThatSatisfiesQuery(containsPosition);
		if (s != null) {
			worldData.remove(s);
		}
	}

	protected boolean shouldAcceptRequest() {
		return debounceTimer.isDebounceComplete();
	}

	protected void performAction() {
		debounceTimer.reset();
		findAndRemoveFirstFoundWorldShapeAtPosition();
	}
	
}