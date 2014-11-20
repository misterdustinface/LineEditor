package UI.AWT;

import java.awt.Graphics2D;

import rendering.AWT.AWTMenuDrawer;
import UI.DropdownMenu;

public class AWTDropdownMenu extends DropdownMenu implements AWTUILayer {

	private AWTMenuDrawer menuDrawer;
	
	public AWTDropdownMenu() {
		menuDrawer = new AWTMenuDrawer();
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		if(isListMenuOpen()) { menuDrawer.drawListMenu(menu); }
		menuDrawer.drawButton((AWTMenuButton)root);
	}

}