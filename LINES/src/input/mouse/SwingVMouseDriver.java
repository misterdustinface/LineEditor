package input.mouse;

import input.vKey.VirtualKeyInput;
import input.vKey.VirtualKeyPressedListener;
import input.vKey.VirtualKeyReleasedListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingVMouseDriver implements MouseListener{

	private VirtualKeyInput vKeys;
	
	public SwingVMouseDriver() {
		vKeys = new VirtualKeyInput();
	}
	
	public void mapVKey(String vKeyName, int keyCode) {
		vKeys.mapVKey(vKeyName, keyCode);
	}
	
	/**
	 * WARNING: Swing will call pressed multiple times while the key is held
	 */
	public void addVKeyPressedListener(String vKeyName, VirtualKeyPressedListener listener) {
		vKeys.addKeyPressedListener(vKeyName, listener);
	}
	
	public void addVKeyReleasedListener(String vKeyName, VirtualKeyReleasedListener listener) {
		vKeys.addKeyReleasedListener(vKeyName, listener);
	}
	
	public void setVKeyFirstPressedListener(String vKeyName, VirtualKeyPressedListener listener) {
		vKeys.setFirstPressedListener(vKeyName, listener);
	}
	
	public void setVKeyFinalReleasedListener(String vKeyName, VirtualKeyReleasedListener listener) {
		vKeys.setFinalReleasedListener(vKeyName, listener);
	}
	
	public void isPressed(String vKey) {
		vKeys.isPressed(vKey);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		vKeys.rawKeyDown(arg0.getModifiersEx());
		vKeys.rawKeyUp(arg0.getModifiersEx());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		vKeys.rawKeyDown(arg0.getModifiersEx());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		vKeys.rawKeyUp(arg0.getModifiersEx());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
}
