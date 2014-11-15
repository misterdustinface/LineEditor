package UI;

import java.awt.Graphics2D;

public interface UILayer {
	
	public void render(Graphics2D g);
	public void update(MouseUserDevice mouse);
	
}
