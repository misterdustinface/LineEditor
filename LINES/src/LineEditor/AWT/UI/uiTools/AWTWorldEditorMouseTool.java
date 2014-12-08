package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTCursorDrawer;
import LineEditor.UI.uiTools.WorldEditorMouseTool;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;
import data.shapes.Circle;
import data.shapes.Point;


public abstract class AWTWorldEditorMouseTool extends WorldEditorMouseTool implements AWTUILayer {

	final public static AWTWorldEditorMouseTool defaultMouseTool = new AWTWorldEditorMouseTool(null) {
		@Override
		protected boolean shouldAcceptRequest() { return false; }
		@Override
		protected void performAction() {}
		@Override
		public void render(Graphics2D g) {
			cursorDrawer.setGraphics(g);
			cursorDrawer.setColor(AWTGraphicData.cursorColor);
			cursorDrawer.drawCrosshairCursor((int)position.x, (int)position.y);
		}
	};
	
	protected AWTCursorDrawer 	cursorDrawer;

	AWTWorldEditorMouseTool(WorldGeometryData WORLD_DATA){
		super(WORLD_DATA);
		cursorDrawer = new AWTCursorDrawer();
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
		if(mouse.isClicked() || mouse.isPressed())
			setInitialPosition((int)mouse.getCursorX(), (int)mouse.getCursorY());
		
		setCurrentPosition((int)mouse.getCursorX(), (int)mouse.getCursorY());	
		
		if(mouse.isClicked() || mouse.isReleased())
			request();
	}
}
