package UI.AWT;

import java.awt.Graphics2D;

import rendering.AWT.AWTMenuDrawer;
import UI.GridMenu;
import data.shapes.Grid;

public abstract class AWTGridMenu extends GridMenu {
	
	private AWTMenuDrawer menuDrawer;
	
	public AWTGridMenu(Grid DISPLAYGRID) {
		super(DISPLAYGRID);
		menuDrawer 	= new AWTMenuDrawer();
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		menuDrawer.drawMenu(displaygrid.x, displaygrid.y, width, height);
		for(int i = 0; i < numberOfButtons(); ++i) {
			menuDrawer.drawFilledButton((AWTMenuButton)buttons.get(i));
		}
		if(canFitNewEmptyEntry()) {
			menuDrawer.drawPlusOnButton(getEmptyEntry());
		}
	}
}
