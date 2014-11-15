package input.vKey;

import java.util.HashSet;
import java.util.LinkedList;

class VirtualKey implements RawKeyPressedListener, RawKeyReleasedListener {
	private HashSet<Integer> keysPressed;
	private LinkedList<VirtualKeyReleasedListener> vKeysReleasedListener;
	private LinkedList<VirtualKeyPressedListener> vKeysPressedListener;
	private VirtualKeyPressedListener firstPressed;
	public VirtualKeyReleasedListener finalReleased;
	
	VirtualKey() {
		keysPressed = new HashSet<Integer>();
		vKeysReleasedListener = new LinkedList<VirtualKeyReleasedListener>();
		vKeysPressedListener = new LinkedList<VirtualKeyPressedListener>();
		firstPressed = null;
		finalReleased = null;
	}
		
	public boolean isPressed() {
		return keysPressed.size() > 0;
	}

	public boolean isRelased() {
		return keysPressed.size() <= 0;
	}
	
	@Override
	public boolean performPressedKeyAction(int keyCode) {
		if(keysPressed.size() == 0 && firstPressed != null) {
			firstPressed.keyPressed();
		}
				
		for(VirtualKeyPressedListener vKeyPressedListener : vKeysPressedListener) {
			vKeyPressedListener.keyPressed();
		}
		
		keysPressed.add(keyCode);
		
		return true;
	}

	@Override
	public boolean performReleasedKeyAction(int keyCode) {
		for(VirtualKeyReleasedListener vKeyRelasedListener: vKeysReleasedListener) {
			vKeyRelasedListener.keyReleased();
		}		
		
		if(keysPressed.size() == 1 && finalReleased != null) {
			finalReleased.keyReleased();
		}
		
		keysPressed.remove(keyCode);
		return true;
	}
	
	public void setFirstPressedListener(VirtualKeyPressedListener listener) {
		firstPressed = listener;
	}
	
	public void setFinalReleasedListener(VirtualKeyReleasedListener listener) {
		finalReleased = listener;
	}
	
	public void addKeyPressedListener(VirtualKeyPressedListener listener) {
		vKeysPressedListener.add(listener);
	}
	
	public void addKeyRelasedListener(VirtualKeyReleasedListener listener) {
		vKeysReleasedListener.add(listener);
	}
}
