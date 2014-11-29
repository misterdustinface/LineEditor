package LineEditor.AWT.UI.uiTools;

import generic.DebounceTimer;

import java.awt.Graphics2D;

import LineEditor.data.WorldGeometryData;
import data.shapes.Point;
import data.shapes.Shape;

public class ShapeDeleteTool extends AWTWorldEditorMouseTool {

	private Point 			position;
	private DebounceTimer 	debounceTimer;
	
	public ShapeDeleteTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		position = new Point(0,0);
		debounceTimer = new DebounceTimer();
		debounceTimer.setDebounceTime_sec(0.5);
	}

	@Override
	public void setInitialPosition(int x, int y) {
		position.set(x, y);
	}

	@Override
	public void setCurrentPosition(int x, int y) {
		position.set(x, y);
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
