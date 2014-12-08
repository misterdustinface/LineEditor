package LineEditor.AWT.UI.uiTools;

import generic.Requestible;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTCursorDrawer;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;
import data.shapes.Circle;
import data.shapes.Pipe;
import data.shapes.Point;
import data.shapes.Shape;


public abstract class AWTWorldEditorMouseTool implements Requestible, AWTUILayer {

	final public static AWTWorldEditorMouseTool defaultMouseTool = new AWTWorldEditorMouseTool(null) {
		private Point position = new Point(0,0);
		@Override
		public void setInitialPosition(int x, int y) {position.set(x, y);}
		@Override
		public void setCurrentPosition(int x, int y) {position.set(x, y);}
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
	protected WorldGeometryData worldData;
	
	AWTWorldEditorMouseTool(WorldGeometryData WORLD_DATA){
		worldData    = WORLD_DATA;
		cursorDrawer = new AWTCursorDrawer();
	}
	
	@Override
	final public void request(){
		if(shouldAcceptRequest())
			performAction();
	}
	
	abstract public    void 	setInitialPosition(int x, int y);
	abstract public    void 	setCurrentPosition(int x, int y);
	abstract protected boolean 	shouldAcceptRequest();
	abstract protected void		performAction();

	final protected void 		select(Shape s) 		{ worldData.select(s); }
	final protected boolean 	isSelected(Shape s) 	{ return worldData.isSelected(s); }
	final protected void 		toggleSelected(Shape s) { worldData.toggleSelected(s); }

	final protected Circle[] 	worldCircles() 		{ return worldData.getWorldPointCollisionCircles(); }
	final protected Pipe[] 		worldRectangles() 	{ return worldData.getWorldLineCollisionBoxes(); }
	final protected Shape[] 	collisionBounds() 	{ return worldData.getAllCollisionBounds(); }
	
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
