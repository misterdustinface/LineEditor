package rendering;

import data.shapes.Point;
import UI.AWT.AWTBarSlider;
import UI.AWT.AWTMenuButton;

public interface MenuDrawer {
	void drawButton       ( AWTMenuButton b );
	void drawSlider       ( AWTBarSlider s );
	void drawSelectorArrow( AWTMenuButton b, int x, int size );
	void drawMenu         ( Point topLeft, int width, int height);
}
