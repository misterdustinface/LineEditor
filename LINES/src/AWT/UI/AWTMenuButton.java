package AWT.UI;

import java.awt.Color;

import AWT.graphicdata.AWTGraphicData;
import UI.MenuButton;

public class AWTMenuButton extends MenuButton {
	
	//SwingUtilities
	
	protected Color pressedColor;
	protected Color releasedColor;
	protected Color highlightColor;
	private boolean isFilled;
	
	public AWTMenuButton() {
		super();
		pressedColor  = AWTGraphicData.buttonPressedColor; 
		releasedColor = AWTGraphicData.buttonColor;         
		highlightColor= AWTGraphicData.buttonHighlightColor;
		isFilled      = false;
	}
	
	public void setColor(Color c) {
		pressedColor = releasedColor = highlightColor = c;
	}
	
	public void setColor(Color pressed, Color released) {
		pressedColor  = pressed;
		highlightColor = releasedColor = released;
	}
	
	public void setColor(Color pressed, Color released, Color highlight) {
		pressedColor  = pressed;
		releasedColor = released;
		highlightColor = highlight;
	}
	
	public Color getColor() {
		return isPressed() ? pressedColor : isHighlighted() ? highlightColor : releasedColor;
	}
	public Color getNormalColor() {
		return releasedColor;
	}
	
	public boolean 	isFilled() 	{ return isFilled; }
	public void 	fill() 		{ isFilled = true; }
	public void 	toggleFill(){ isFilled = !isFilled; }
}
