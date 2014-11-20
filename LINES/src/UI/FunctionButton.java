package UI;

import generic.VoidFunctionPointer;

public abstract class FunctionButton extends UIElement {
	
	private VoidFunctionPointer buttonPressedFunction;
	private VoidFunctionPointer buttonReleasedFunction;
	
	public FunctionButton() {
		buttonPressedFunction  = VoidFunctionPointer.EMPTY_FUNCTION;
		buttonReleasedFunction = VoidFunctionPointer.EMPTY_FUNCTION;
	}

	public void setButtonPressedFunction(VoidFunctionPointer BUTTON_PRESSED_FUNCTION) {
		buttonPressedFunction = BUTTON_PRESSED_FUNCTION;
	}
	public void setButtonReleasedFunction(VoidFunctionPointer BUTTON_RELEASED_FUNCTION) {
		buttonReleasedFunction = BUTTON_RELEASED_FUNCTION;
	}
	
	@Override
	protected void pressAction() {
		buttonPressedFunction.call();
	}

	@Override
	protected void releaseAction() {
		buttonReleasedFunction.call();
	}

}
