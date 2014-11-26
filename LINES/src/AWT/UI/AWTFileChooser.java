package AWT.UI;

import java.awt.Graphics2D;

import AWT.rendering.AWTMenuDrawer;
import UI.FileChooser;
import UI.MenuButton;

public class AWTFileChooser extends FileChooser implements AWTUILayer {
	
	private AWTMenuDrawer menuDrawer;
	
	public AWTFileChooser() {
		menuDrawer = new AWTMenuDrawer();
	}
	
	protected MenuButton makeButton() { return new AWTMenuButton(); }
	
	@Override
	public void render(Graphics2D g) {		
		menuDrawer.setGraphics(g);
		menuDrawer.drawFileChooser(this);
	}
	
}