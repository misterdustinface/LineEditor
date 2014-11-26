package AWT.UI;

import java.awt.Graphics2D;

import AWT.rendering.AWTMenuDrawer;
import UI.DropdownMenu;

public class AWTDropdownMenu extends DropdownMenu implements AWTUILayer {

	protected AWTMenuDrawer menuDrawer;
	
	public AWTDropdownMenu() {
		menuDrawer = new AWTMenuDrawer();
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		if(isListMenuOpen()) { menuDrawer.drawUIMenu(menu); }
		menuDrawer.drawButton(root);
	}
}