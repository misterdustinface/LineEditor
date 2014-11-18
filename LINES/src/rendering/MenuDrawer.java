package rendering;

import UI.BarSlider;
import UI.MenuButton;
import data.shapes.Point;

public interface MenuDrawer {
	void drawButton       ( MenuButton b );
	void drawSlider       ( BarSlider s );
	void drawSelectorArrow( MenuButton b, int x, int size );
	void drawMenu         ( Point topLeft, int width, int height);
}
