package LineEditor.AWT.UI.uiTools;

import generic.Requestible;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import rendering.Renderable;
import AWT.UI.AWTMouseUserDevice;
import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTCursorDrawer;
import LineEditor.data.WorldGeometryData;
import data.shapes.Circle;
import data.shapes.Pipe;
import data.shapes.Point;
import data.shapes.Shape;


public abstract class AWTWorldEditorMouseTool extends AWTMouseUserDevice implements Renderable, Requestible {

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
	
	
	protected AWTCursorDrawer cursorDrawer;
	protected WorldGeometryData worldData;
	
	AWTWorldEditorMouseTool(WorldGeometryData WORLD_DATA){
		worldData    = WORLD_DATA;
		cursorDrawer = new AWTCursorDrawer();
	}
	
	@Override
	final public void request(){
		if(shouldAcceptRequest()){
			performAction();
		}
	}
	
	abstract public    void 	setInitialPosition(int x, int y);
	abstract public    void 	setCurrentPosition(int x, int y);
	abstract protected boolean 	shouldAcceptRequest();
	abstract protected void		performAction();

	final protected void 		select(Shape s) 		{ worldData.select(s); }
	final protected boolean 	isSelected(Shape s) 	{ return worldData.isSelected(s); }
	final protected void 		toggleSelected(Shape s) { worldData.toggleSelected(s); }

	final protected Circle[] 	worldCircles() 		{ return worldData.getCopyOfWorldPointCollisionCircles(); }
	final protected Pipe[] 		worldRectangles() 	{ return worldData.getCopyOfWorldLineCollisionBoxes(); }
	final protected Shape[] 	collisionBounds() 	{ return worldData.getCopyOfAllCollisionBounds(); }
	
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
	final public void mouseEntered(MouseEvent e) {}
	@Override
	final public void mouseExited(MouseEvent e) {}
	@Override
	final public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		setCurrentPosition(e.getX(), e.getY());
		request();
	}
	@Override
	final public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		setInitialPosition(e.getX(), e.getY());
		setCurrentPosition(e.getX(), e.getY());
	}
	@Override
	final public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		setCurrentPosition(e.getX(), e.getY());
		request();
	}
	@Override
	final public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		setCurrentPosition(e.getX(), e.getY());
	}
	@Override
	final public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		setCurrentPosition(e.getX(), e.getY());
	}
}
