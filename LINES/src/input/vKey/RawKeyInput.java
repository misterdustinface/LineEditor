package input.vKey;

import java.util.HashMap;

public class RawKeyInput {
	private HashMap<Integer, RawKeyPressedListener> keysDown;
	private HashMap<Integer, RawKeyReleasedListener> keysUp;
	
	public static boolean DEBUG_KEY_INPUT = true;
	
	RawKeyInput() {
		keysDown  = new HashMap<Integer, RawKeyPressedListener>();
		keysUp    = new HashMap<Integer, RawKeyReleasedListener>();
	}
	
	boolean keyDown(int keyCode) {
		if(keysDown.containsKey(keyCode)) {
			return keysDown.get(keyCode).performPressedKeyAction(keyCode);
		} else {
			nullKeyAction(keyCode, "Key Down");
			return false;
		}
	}
	
	boolean keyUp(int keyCode) {
		if(keysUp.containsKey(keyCode)) {
			return keysUp.get(keyCode).performReleasedKeyAction(keyCode);
		} else {
			nullKeyAction(keyCode, "Key Up");
			return false;
		}
	}
	
	void setKeyDownAction(int keyCode, RawKeyPressedListener action) {
		if(keysDown.containsKey(keyCode)) {
			keysDown.remove(keyCode);
		}
		
		keysDown.put(keyCode, action);
	}
	
	void setKeyUpAction(int keyCode, RawKeyReleasedListener action) {
		if(keysUp.containsKey(keyCode)) {
			keysUp.remove(keyCode);
		}
		
		keysUp.put(keyCode, action);
	}
	
	private void nullKeyAction(int keyCode, String type) {
		if(DEBUG_KEY_INPUT) {
			System.out.println(type + " " + keyCode + " no action");
		}
	}
}
