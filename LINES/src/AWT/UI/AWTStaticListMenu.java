package AWT.UI;

import java.awt.Graphics2D;

import AWT.rendering.AWTMenuDrawer;
import UI.StaticListMenu;

public class AWTStaticListMenu extends StaticListMenu implements AWTUILayer {

	protected AWTMenuDrawer menuDrawer;
	
	public AWTStaticListMenu() {
		menuDrawer = new AWTMenuDrawer();
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		menuDrawer.drawUIMenu(this);
	}

}
