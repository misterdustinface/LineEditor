package UI.AWTuiTools;

import java.awt.Graphics2D;

import data.shapes.Point;
import data.shapes.Shape;
import data.shapes.WorldGeometryData;

public class ShapeDeleteTool extends AWTWorldEditorMouseTool {

	private Point position;
	
	public ShapeDeleteTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		position = new Point(0,0);
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
		return true;
	}

	@Override
	protected void performAction() {
		findAndRemoveFirstFoundWorldShapeAtPoint(position);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(Color.RED);
//		cursorDrawer.drawLargeXCursor((int)position.x, (int)position.y);
	}
}
