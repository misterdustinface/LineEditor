package LineEditor.AWT.UI.uiTools;

import generic.timers.DebounceTimer;

import java.awt.Graphics2D;

import shapes.Point;
import shapes.Shape;
import AWT.UI.AWTUILayer;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.WorldEditorMouseTool;

public class AWTShapeDeleteTool extends WorldEditorMouseTool implements AWTUILayer {

	private DebounceTimer 	debounceTimer;
	
	public AWTShapeDeleteTool(WorldGeometryData WORLD_DATA) {
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

	@Override
	protected boolean shouldAcceptRequest() {
		return debounceTimer.isDebounceComplete();
	}

	@Override
	protected void performAction() {
		debounceTimer.reset();
		findAndRemoveFirstFoundWorldShapeAtPoint(position);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(Color.RED);
//		cursorDrawer.drawLargeXCursor((int)position.x, (int)position.y);
	}
}
