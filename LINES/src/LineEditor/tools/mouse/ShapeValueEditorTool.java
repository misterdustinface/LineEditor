package LineEditor.tools.mouse;

import shapes.Point;
import shapes.Shape;
import LineEditor.data.WorldGeometryData;

public class ShapeValueEditorTool extends WorldEditorMouseTool {
	
	public ShapeValueEditorTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	private void selectAndOpenValueEditorWindowForFirstMatchingIntersection(Point point){
		
		for(Shape bound : collisionBounds()) {
			if(bound.contains(point)) {
				toggleSelected(bound);
				if(isSelected(bound)) {
					openEditWindow(bound);
					return;
		}}}
		
	}

	private void openEditWindow(Shape s) {
		//windowManager.addWindow(s.ID(), s.detailedEditorFrame(worldData));
		System.out.println("NO EDITOR WINDOWS EXIST");
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return true;
	}

	@Override
	protected void performAction() {
		selectAndOpenValueEditorWindowForFirstMatchingIntersection(position);
	}
}
