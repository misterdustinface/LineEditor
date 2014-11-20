package rendering.AWT;

import UI.DropdownMenu;
import UI.GridMenu;
import UI.ListMenu;
import UI.MenuButton;
import UI.TextLabel;
import UI.AWT.AWTBarSlider;
import UI.AWT.AWTMenuButton;
import UI.AWT.AWTRenderer;
import data.graphics.AWTGraphicData;
import data.shapes.Point;

public class AWTMenuDrawer extends AWTRenderer {

	private AWTShapeDrawer shapeDrawer;
	
	public AWTMenuDrawer() {
		shapeDrawer = new AWTShapeDrawer();
	}
	
	public void drawTextLabel( TextLabel tl) {
		if (tl.hasText()) {
			graphics.drawString(tl.getText(), tl.getPosition().x, tl.getPosition().y);
		}
	}
	
	public void drawButton( AWTMenuButton b ) {
		shapeDrawer.setGraphics(graphics);
		shapeDrawer.setColor(b.getColor());
		shapeDrawer.drawPolygonBorder(b.polygon);
		drawTextLabel(b.textLabel);
	}
	
	public void drawFilledButton( AWTMenuButton b ) {
		shapeDrawer.setGraphics(graphics);
		shapeDrawer.setColor(b.getNormalColor());
		shapeDrawer.drawPolygon(b.polygon);
		shapeDrawer.setColor(b.getColor());
		shapeDrawer.drawPolygon(b.polygon);
		drawTextLabel(b.textLabel);
	}
	
	public void drawPlusOnButton( MenuButton b ) {
		
		int centerX    = b.getCenterX();
		int centerY    = b.getCenterY();
		int plusWidth  = b.getWidth() >>1;
		int plusHeight = b.getHeight()>>1;
		plusWidth  = plusWidth < plusHeight ? plusWidth : plusHeight;
		plusHeight = AWTGraphicData.plusSignThickness;
		
		shapeDrawer.setGraphics(graphics);
		shapeDrawer.setColor(AWTGraphicData.PLUS_SIGN_COLOR);
		graphics.fillRect(centerX - (plusWidth >>1), centerY - (plusHeight>>1), plusWidth,  plusHeight);
		graphics.fillRect(centerX - (plusHeight>>1), centerY - (plusWidth >>1), plusHeight, plusWidth);
	}
	
	public void drawSlider( AWTBarSlider s ) {
		shapeDrawer.setGraphics(graphics);
		shapeDrawer.setColor(s.getBaseColor());
		shapeDrawer.drawRectangle(s.getBase());
		shapeDrawer.setColor(s.getFillColor());
		shapeDrawer.drawRectangle(s.getFill());
	}
	
	public void drawSelectorArrow( AWTMenuButton b, int x, int size ) {
		int y = (int) b.polygon.getBounds().getCenterY();
		graphics.setColor(b.getColor());
		graphics.drawLine(x,        y,        x - size, y + size);
		graphics.drawLine(x,        y,        x - size, y - size);
		graphics.drawLine(x - size, y - size, x - size, y + size);
	}
	
	public void drawMenu( Point topLeft, int width, int height) {
		drawMenu((int)topLeft.x, (int)topLeft.y, width, height);
	}
	
	public void drawMenu( int X, int Y, int width, int height) {
		graphics.setColor(AWTGraphicData.MENU_BACKGROUND_COLOR);
		graphics.fillRect(X, Y, width, height);
		graphics.setColor(AWTGraphicData.buttonColor);
		graphics.drawRect(X, Y, width, height);
	}
	
	public void drawListMenu(ListMenu menu) {
		drawMenu(menu.getX(), menu.getY(), menu.getWidth(), menu.getHeight());
		for(int i = 0; i < menu.numberOfButtons(); ++i) {
			drawFilledButton((AWTMenuButton)menu.getButton(i));
		}
	}
	
	public void drawGridMenu(GridMenu menu) {
		drawMenu(menu.getX(), menu.getY(), menu.getWidth(), menu.getHeight());
		for(int i = 0; i < menu.numberOfButtons(); ++i) {
			drawFilledButton((AWTMenuButton)menu.getButton(i));
		}
		if(menu.canFitNewEmptyEntry()) {
			drawPlusOnButton(menu.getEmptyEntry());
		}
	}
}
