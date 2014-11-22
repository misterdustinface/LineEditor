package AWT.UI;

import java.awt.Graphics2D;

import UI.StaticGridMenu;
import AWT.rendering.AWTMenuDrawer;
import data.shapes.Grid;

public class AWTStaticGridMenu extends StaticGridMenu implements AWTUILayer {

	protected AWTMenuDrawer menuDrawer;
	
	public AWTStaticGridMenu(Grid DISPLAYGRID) {
		super(DISPLAYGRID);
		menuDrawer = new AWTMenuDrawer();
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		menuDrawer.drawUIMenu(this);
	}
	
}
