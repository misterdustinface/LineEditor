package AWT.UI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import data.shapes.Point;

public class AWTScreenShifter implements MouseListener, MouseMotionListener {

	private boolean shiftingScreen = false;
	private Point 	lastOffset     = new Point(0,0);
	
	private AWTViewport viewport;
	
	public AWTScreenShifter(AWTViewport VIEWPORT) {
		viewport = VIEWPORT;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON2
		||(e.getButton() == MouseEvent.BUTTON1 && e.isControlDown())){
			shiftingScreen = true;
			lastOffset.set(e.getX(), e.getY());
		}
		viewport.repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		shiftingScreen = false;
		viewport.repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}


	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(shiftingScreen){
			int newX = e.getX() - (int)lastOffset.x;
			int newY = e.getY() - (int)lastOffset.y;
			lastOffset.shift(newX, newY);
			viewport.translatePosition(newX, newY);
		}
		
		viewport.repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		lastOffset.shift(e.getX(), e.getY());
		viewport.repaint();
	}
	
}
