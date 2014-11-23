package AWT.UI;

import java.awt.Graphics2D;
import java.io.File;

import AWT.rendering.AWTMenuDrawer;
import UI.FileChooser;
import UI.MenuButton;

public class AWTFileChooser extends FileChooser implements AWTUILayer {
	
	private AWTMenuDrawer menuDrawer;
	
	public AWTFileChooser() {
		menuDrawer = new AWTMenuDrawer();
	}
	
	protected MenuButton makeUpButton() {
		AWTMenuButton upButton = new AWTMenuButton();
		upButton.textLabel.setText("UP");
		upButton.textLabel.center();
		upButton.makeBoxRelativeToPoint(0, 0, 2, 2, 120, 40);
		return upButton;
	}
	protected MenuButton makeExitButton() {
		AWTMenuButton exitButton = new AWTMenuButton();
		exitButton.textLabel.setText("EXIT");
		exitButton.textLabel.center();
		exitButton.makeBoxRelativeToPoint(122, 0, 2, 2, 120, 40);
		return exitButton;
	}
	
	protected MenuButton makeFileChooserButton(File file) {
		AWTMenuButton button = new AWTMenuButton();
		button.textLabel.setMaxTextWidth(20);
		button.textLabel.setText(file.getName());
		button.textLabel.center();
		button.makeSuggestedBoxRelativeToPoint(fileListing.getX(), fileListing.getY());
		return button;
	}
	
	@Override
	public void render(Graphics2D g) {		
		if(shouldDisplayAndUpdate) {
			menuDrawer.setGraphics(g);		
			menuDrawer.drawUIMenu(fileListing);
			menuDrawer.drawButton(upButton);
			menuDrawer.drawButton(exitButton);
		}
	}
	
}