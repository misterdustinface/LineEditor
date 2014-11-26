package AWT.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class AWTRenderer {
	
	protected Graphics2D graphics;

	public AWTRenderer() {}
	
	final public void setGraphics(Graphics2D graphics2D){
		graphics = graphics2D;
	}
	final public void setColor(Color color){
		graphics.setColor(color);
	}
}
