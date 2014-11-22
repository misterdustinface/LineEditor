package UI;

import data.shapes.Point;

public abstract class MouseUserDevice {
	protected Point cursorPosition;
	
	public MouseUserDevice(){
		cursorPosition = new Point(0,0);
	}
	
	public Point getCursorPosition(){
		return cursorPosition.copy();
	}
	public float getCursorX(){
		return cursorPosition.x;
	}
	public float getCursorY(){
		return cursorPosition.y;
	}
	
	private enum MOUSE_STATE{PRESSED, RELEASED, CLICKED, DRAGGED, MOVED, NONE};
	private MOUSE_STATE state = MOUSE_STATE.NONE;
	
	final public boolean wasIntercepted() { return state == MOUSE_STATE.NONE; }
	
	final public boolean isClicked()  { return state == MOUSE_STATE.CLICKED;  }
	final public boolean isPressed()  { return state == MOUSE_STATE.PRESSED;  }
	final public boolean isReleased() { return state == MOUSE_STATE.RELEASED; }
	final public boolean isDragged()  { return state == MOUSE_STATE.DRAGGED;  }
	final public boolean isMoved()    { return state == MOUSE_STATE.MOVED;    }
	
	final public void intercept() { state = MOUSE_STATE.NONE; }
	
	final protected void click()   { state = MOUSE_STATE.CLICKED;  }
	final protected void press()   { state = MOUSE_STATE.PRESSED;  }
	final protected void release() { state = MOUSE_STATE.RELEASED; }
	final protected void drag()    { state = MOUSE_STATE.DRAGGED;  }
	final protected void move()    { state = MOUSE_STATE.MOVED;    }
	
	private enum BUTTON{ONE, TWO, THREE, NONE};
	private BUTTON button = BUTTON.NONE;
	
	final public boolean isPrimaryButton()   { return button == BUTTON.ONE; }
	final public boolean isSecondaryButton() { return button == BUTTON.TWO; }
	final public boolean isTerciaryButton()  { return button == BUTTON.THREE; }
	
	final protected void buttonOne()   { button = BUTTON.ONE;  }
	final protected void buttonTwo()   { button = BUTTON.TWO;  }
	final protected void buttonThree() { button = BUTTON.THREE; }
	final protected void noButton()    { button = BUTTON.NONE; }
}
