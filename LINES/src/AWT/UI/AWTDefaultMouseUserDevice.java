package AWT.UI;

import java.awt.event.MouseEvent;

public class AWTDefaultMouseUserDevice extends AWTMouseUserDevice {
	@Override
	public void mouseDragged(MouseEvent arg0) {
		super.mouseDragged(arg0);
		cursorPosition.set(arg0.getX(), arg0.getY());
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		super.mouseMoved(arg0);
		cursorPosition.set(arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		super.mouseClicked(arg0);
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		super.mouseReleased(arg0);
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}

}
