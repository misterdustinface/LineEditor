package LineEditor.tools.mouse;

import shapes.Circle;
import shapes.Point;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.WorldEditorTool;
import UI.UILayer;
import UI.input.MouseUserDevice;

public abstract class WorldEditorMouseTool extends WorldEditorTool implements UILayer {
	
	protected Point position;
	
	public WorldEditorMouseTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		position = new Point(0,0);
	}
	
	public void setInitialPosition(int x, int y) { 
		position.set(x, y); 
	}
	
	public void setCurrentPosition(int x, int y) { 
		position.set(x, y); 
	}

	final protected boolean pointShouldSnapToCenterOfWorldCircle(Point point, Circle worldCircle) {
		return worldCircle.contains(point);
	}
	
	final protected void createWorldLine(Point A, Point B) {
		worldData.createLine(A, B);
	}
	
	final protected void snapPointToCircleCenter(Point point, Circle circle){
		point.set(circle.x(), circle.y());
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		if (mouse.isClicked() || mouse.isPressed())
			setInitialPosition((int)mouse.getCursorX(), (int)mouse.getCursorY());
		
		setCurrentPosition((int)mouse.getCursorX(), (int)mouse.getCursorY());	
		
		if (mouse.isClicked() || mouse.isReleased())
			request();
	}
}
