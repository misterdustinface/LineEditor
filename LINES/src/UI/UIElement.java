package UI;

public abstract class UIElement {

	private boolean isPressed;
	private boolean isHighlighted;
	
	public UIElement() { isPressed = isHighlighted = false; }

	final public void press() { 
		isPressed = true; 
		pressAction();
	}
	
	final public void release() { 
		isPressed = false; 
		releaseAction();
	}
	
	final public boolean isPressed()       { return isPressed;     }
	final public boolean isHighlighted()   { return isHighlighted; }
	final protected void highlight()       { isHighlighted = true;  }
	final protected void removeHighlight() { isHighlighted = false; }
	
	protected abstract void pressAction();
	protected abstract void releaseAction();

	public abstract void    update(MouseUserDevice mouse);
}
