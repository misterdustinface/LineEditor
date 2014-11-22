package UI.AWT;

import java.awt.Graphics2D;

import rendering.AWT.AWTMenuDrawer;
import UI.DynamicGridMenu;
import data.shapes.Grid;

public abstract class AWTDynamicGridMenu extends DynamicGridMenu implements AWTUILayer {
	
	private AWTMenuDrawer menuDrawer;
	
	public AWTDynamicGridMenu(Grid DISPLAYGRID) {
		super(DISPLAYGRID);
		menuDrawer = new AWTMenuDrawer();
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		menuDrawer.drawGridMenu(this);
		if(canFitNewEmptyEntry()) {
			menuDrawer.drawPlusOnButton(getEmptyEntry());
		}
	}
}
