package UI.AWTuiTools;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UI.uiTools.WorldEditorTool;
import rendering.Renderable;
import rendering.AWT.AWTCursorDrawer;
import data.graphics.AWTGraphicData;
import data.shapes.Circle;
import data.shapes.Point;
import data.shapes.WorldGeometryData;


public abstract class AWTWorldEditorMouseTool extends WorldEditorTool implements MouseListener, MouseMotionListener, Renderable{

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
	
	AWTWorldEditorMouseTool(WorldGeometryData WORLD_DATA){
		super(WORLD_DATA);
		cursorDrawer = new AWTCursorDrawer();
	}
	
	abstract public void setInitialPosition(int x, int y);
	abstract public void setCurrentPosition(int x, int y);

	final protected boolean pointShouldSnapToCenterOfWorldCircle(Point point, Circle worldCircle){return worldCircle.contains(point);}
	final protected void createWorldLine(Point A, Point B){worldData.createLine(A, B);}
	final protected void snapPointToCircleCenter(Point point, Circle circle){
		point.set(circle.x(), circle.y());
	}
	
	@Override
	final public void mouseEntered(MouseEvent e) {}
	@Override
	final public void mouseExited(MouseEvent e) {}
	@Override
	final public void mouseClicked(MouseEvent e) {
		
		setCurrentPosition(e.getX(), e.getY());
		request();
	}
	@Override
	final public void mousePressed(MouseEvent e) {
		setInitialPosition(e.getX(), e.getY());
		setCurrentPosition(e.getX(), e.getY());
	}
	@Override
	final public void mouseReleased(MouseEvent e) {
		setCurrentPosition(e.getX(), e.getY());
		request();
	}
	@Override
	final public void mouseDragged(MouseEvent e) {
		setCurrentPosition(e.getX(), e.getY());
	}
	@Override
	final public void mouseMoved(MouseEvent e) {
		setCurrentPosition(e.getX(), e.getY());
	}
}
