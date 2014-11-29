package AWT.UI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UI.MouseUserDevice;

public abstract class AWTMouseUserDevice extends MouseUserDevice implements MouseListener, MouseMotionListener {
	
	public AWTMouseUserDevice(){ super(); }
	@Override
	public void mouseClicked(MouseEvent e) { click(); recordButtonData(e); }
	@Override
	public void mousePressed(MouseEvent e) { press(); recordButtonData(e); }
	@Override
	public void mouseReleased(MouseEvent e) { release(); }
	@Override
	public void mouseDragged(MouseEvent e) { drag(); }
	@Override
	public void mouseMoved(MouseEvent e) { move(); }
	
	private void recordButtonData(MouseEvent arg0) {
		switch(arg0.getButton()) {
		case MouseEvent.BUTTON1: buttonOne();   break;
		case MouseEvent.BUTTON2: buttonTwo();   break;
		case MouseEvent.BUTTON3: buttonThree(); break;
		default:				 noButton();    break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}