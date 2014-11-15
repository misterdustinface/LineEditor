package input.keys;

import input.vKey.VirtualKeyInput;
import input.vKey.VirtualKeyPressedListener;
import input.vKey.VirtualKeyReleasedListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingVKeyDriver implements KeyListener {

	private VirtualKeyInput vKeys;
	
	public SwingVKeyDriver() {
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
	public void keyPressed(KeyEvent arg0) {
		vKeys.rawKeyDown(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		vKeys.rawKeyUp(arg0.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
