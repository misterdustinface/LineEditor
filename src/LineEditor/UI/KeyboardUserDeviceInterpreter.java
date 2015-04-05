package LineEditor.UI;

import java.util.LinkedList;

import UI.input.InputEvent;
import UI.input.KeyboardUserDevice;

public class KeyboardUserDeviceInterpreter implements Runnable {

	private InputEvent event;
	final private LinkedList<InputEventFunction> functions;
	final private KeyboardUserDevice keyboardDevice;
	
	public KeyboardUserDeviceInterpreter(KeyboardUserDevice keyboardDevice) {
		functions = new LinkedList<InputEventFunction>();
		this.keyboardDevice = keyboardDevice;
	}
	
	public void run() {
		for (;;) {
			event = keyboardDevice.consumeEvent();
			executeAllSpecifiedFunctions(event);
		}
	}
	
	public void addFunction(InputEventFunction function) {
		functions.add(function);
	}
	
	private void executeAllSpecifiedFunctions(InputEvent event) {
		for (InputEventFunction function : functions) {
			function.call(event);
		}
	}

}