package LineEditor.tools.mouse;

import generic.timers.DebounceTimer;
import shapes.Point;
import shapes.Shape;
import LineEditor.data.WorldGeometryData;

public class ShapeDeleteTool extends WorldEditorMouseTool {
	
	private DebounceTimer debounceTimer;
	
	public ShapeDeleteTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		debounceTimer = new DebounceTimer();
		debounceTimer.setDebounceTime_sec(0.5);
	}
	
	private void findAndRemoveFirstFoundWorldShapeAtPoint(Point pointToRemoveAt){
		for(Shape s : collisionBounds()){
			if(s.contains(pointToRemoveAt)){
				worldData.remove(s);
				//return;
			}
		}
	}

	protected boolean shouldAcceptRequest() {
		return debounceTimer.isDebounceComplete();
	}

	protected void performAction() {
		debounceTimer.reset();
		findAndRemoveFirstFoundWorldShapeAtPoint(position);
	}
	
}