package rendering;

import UI.BarSlider;
import UI.FileChooser;
import UI.MenuButton;
import UI.UIMenu;
import data.shapes.Point;

public interface MenuDrawer {
	void drawButton       		( MenuButton b );
	void drawSlider       		( BarSlider s );
	void drawSelectorArrow		( MenuButton b, int x, int size );
	//void drawPlusOnButton 		( MenuButton b );
	void drawMenuBox         	( Point topLeft, int width, int height);
	void drawMenuBox			( int X, int Y, int width, int height);
	void drawUIMenu       		( UIMenu menu );
	void drawFileChooser		( FileChooser fileChooser );
}
