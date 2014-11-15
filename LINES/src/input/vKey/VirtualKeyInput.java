package input.vKey;

import java.util.HashMap;

public class VirtualKeyInput {
	private RawKeyInput rawKeyInput;
	private HashMap<String, VirtualKey> vKeys;
	
	public VirtualKeyInput() {
		rawKeyInput = new RawKeyInput();
		vKeys = new HashMap<String, VirtualKey>();
	}
	
	public void mapVKey(final String vKeyName, int keyCode) {
		addIfNotThere(vKeyName);
		final VirtualKey vKey = vKeys.get(vKeyName);
		
		rawKeyInput.setKeyDownAction(keyCode, new RawKeyPressedListener() {
			@Override
			public boolean performPressedKeyAction(int keyCode) {
				return vKey.performPressedKeyAction(keyCode);
			}
		});
		
		rawKeyInput.setKeyUpAction(keyCode, new RawKeyReleasedListener() {
			@Override
			public boolean performReleasedKeyAction(int keyCode) {
				return vKey.performReleasedKeyAction(keyCode);
			}
		});
	}
	
	public boolean isPressed(String vKeyName) {
		if(vKeys.containsKey(vKeyName)) {
			return vKeys.get(vKeyName).isPressed();
		} else {
			return false;
		}
	}
	
	public boolean isRelased(String vKeyName) {
		if(vKeys.containsKey(vKeyName)) {
			return vKeys.get(vKeyName).isRelased();
		} else {
			return false;
		}
	}
	
	public void setFirstPressedListener(String vKeyName, VirtualKeyPressedListener listener) {
		addIfNotThere(vKeyName);
		vKeys.get(vKeyName).setFirstPressedListener(listener);
	}
	
	public void setFinalReleasedListener(String vKeyName, VirtualKeyReleasedListener listener) {
		addIfNotThere(vKeyName);
		vKeys.get(vKeyName).setFinalReleasedListener(listener);
	}
	
	public void addKeyPressedListener(String vKeyName, VirtualKeyPressedListener listener) {
		addIfNotThere(vKeyName);
		vKeys.get(vKeyName).addKeyPressedListener(listener);
	}
	
	public void addKeyReleasedListener(String vKeyName, VirtualKeyReleasedListener listener) {
		addIfNotThere(vKeyName);
		vKeys.get(vKeyName).addKeyRelasedListener(listener);
	}
	
	public boolean rawKeyDown(int keyCode) {
		return rawKeyInput.keyDown(keyCode);
	}
	
	public boolean rawKeyUp(int keyCode) {
		return rawKeyInput.keyUp(keyCode);
	}
	
	private void addIfNotThere(String vKeyName) {
		if(!vKeys.containsKey(vKeyName)) {
			vKeys.put(vKeyName, new VirtualKey());
		}
	}
}
