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
}
