package UI.AWT;

import java.awt.Graphics2D;

import UI.MouseUserDevice;
import UI.UILayer;
import rendering.AWT.AWTMenuDrawer;
import data.shapes.Point;

public class AWTTestMenu implements UILayer {

	private AWTMenuDrawer menuDrawer; 
	
	private AWTMenuButton[] testButtons = {new AWTTestButton(), new AWTTestButton()};//,new RightArrowButton(new Point(10,10), -5, 8)};
	private Point position;
	private int width, height;
	
	public AWTTestMenu(Point POS, int WIDTH, int HEIGHT) {
		position = POS;
		width    = WIDTH;
		height   = HEIGHT;
		testButtons[0].text = "TESTING ABCDEFGH";
		testButtons[1].text = "TESTING IJKLMNOP";
		testButtons[0].setMaxTextWidth(16);
		testButtons[1].setMaxTextWidth(16);
		//testButton.setColor(Color.GREEN, Color.LIGHT_GRAY, Color.YELLOW);
		int xBoff = 6;
		int yBoff = 6;
		testButtons[0].makeSuggestedBoxRelativeToPoint(position, xBoff, yBoff);
		testButtons[0].center();
		yBoff = 60;
		testButtons[1].makeSuggestedBoxRelativeToPoint(position, xBoff, yBoff);
		//testButtons[1].center();
		
		menuDrawer = new AWTMenuDrawer();
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		menuDrawer.drawMenu(position, width, height);
		for(AWTMenuButton button : testButtons) {
			menuDrawer.drawButton(button);
			if(button.isHighlighted()){
				menuDrawer.drawSelectorArrow(button, (int)position.x - 5, 8);			
			}
		}
	}

	@Override
	public void update(MouseUserDevice mouse) {
		for(AWTMenuButton button : testButtons) 
			button.update(mouse);
	}
	
}
