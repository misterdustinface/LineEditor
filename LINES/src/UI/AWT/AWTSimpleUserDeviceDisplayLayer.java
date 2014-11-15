package UI.AWT;

import java.awt.Color;
import java.awt.Graphics2D;

import rendering.AWT.AWTCursorDrawer;
import UI.MouseUserDevice;
import UI.UILayer;

public class AWTSimpleUserDeviceDisplayLayer implements UILayer {
	private AWTCursorDrawer 	cursorDrawer;
	private AWTMouseUserDevice 	userDevice;
	
	public AWTSimpleUserDeviceDisplayLayer(AWTMouseUserDevice USER_DEVICE) {
		userDevice = USER_DEVICE;
		cursorDrawer = new AWTCursorDrawer();
	}
	
	@Override
	public void render(Graphics2D g) {
		cursorDrawer.setGraphics(g);
		cursorDrawer.setColor(Color.BLACK);
		cursorDrawer.drawPointerCursor((int)userDevice.getCursorX(), (int)userDevice.getCursorY());
	}

	@Override
	public void update(MouseUserDevice mouse) {
		
	}
}
