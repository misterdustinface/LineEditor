package AWT.rendering;

import rendering.MenuDrawer;
import AWT.UI.AWTBarSlider;
import AWT.UI.AWTMenuButton;
import AWT.graphicdata.AWTGraphicData;
import UI.BarSlider;
import UI.FileChooser;
import UI.MenuButton;
import UI.TextLabel;
import UI.UIMenu;
import data.shapes.Point;

public class AWTMenuDrawer extends AWTRenderer implements MenuDrawer {

	private AWTShapeDrawer shapeDrawer;
	
	public AWTMenuDrawer() {
		shapeDrawer = new AWTShapeDrawer();
	}
	
	public void drawTextLabel( TextLabel tl) {
		if (tl.hasText()) {
			graphics.drawString(tl.getText(), tl.getPosition().x, tl.getPosition().y);
		}
	}
	
	public void drawButton( MenuButton b) {
		if(((AWTMenuButton)b).isFilled()) {
			drawFilledButton((AWTMenuButton)b);
		} else {
			drawButton((AWTMenuButton)b);
		}
	}
	
	private void drawButton( AWTMenuButton b ) {
		shapeDrawer.setGraphics(graphics);
		shapeDrawer.setColor(((AWTMenuButton)b).getColor());
		shapeDrawer.drawPolygonBorder(b.polygon);
		drawTextLabel(b.textLabel);
	}
	
	private void drawFilledButton( AWTMenuButton b ) {
		shapeDrawer.setGraphics(graphics);
		shapeDrawer.setColor(((AWTMenuButton)b).getNormalColor());
		shapeDrawer.drawPolygon(b.polygon);
		shapeDrawer.setColor(((AWTMenuButton)b).getColor());
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
	
	public void drawSlider( BarSlider s ) {
		shapeDrawer.setGraphics(graphics);
		shapeDrawer.setColor(((AWTBarSlider)s).getBaseColor());
		shapeDrawer.drawRectangle(s.getBase());
		shapeDrawer.setColor(((AWTBarSlider)s).getFillColor());
		shapeDrawer.drawRectangle(s.getFill());
	}
	
	public void drawSelectorArrow( MenuButton b, int x, int size ) {
		int y = (int) b.polygon.getBoundingRectangle().getCenterY();
		graphics.setColor(((AWTMenuButton)b).getColor());
		graphics.drawLine(x,        y,        x - size, y + size);
		graphics.drawLine(x,        y,        x - size, y - size);
		graphics.drawLine(x - size, y - size, x - size, y + size);
	}
	
	public void drawMenuBox( Point topLeft, int width, int height) {
		drawMenuBox((int)topLeft.x, (int)topLeft.y, width, height);
	}
	
	public void drawMenuBox( int X, int Y, int width, int height) {
		graphics.setColor(AWTGraphicData.MENU_BACKGROUND_COLOR);
		graphics.fillRect(X, Y, width, height);
		graphics.setColor(AWTGraphicData.buttonColor);
		graphics.drawRect(X, Y, width, height);
	}
	
	public void drawUIMenu( UIMenu menu ) {
		drawMenuBox(menu.getX(), menu.getY(), menu.getWidth(), menu.getHeight());
		for(int i = 0; i < menu.numberOfButtons(); ++i) {
			drawButton(menu.getButton(i));
		}
	}

	@Override
	public void drawFileChooser(FileChooser fileChooser) {
		if(fileChooser.shouldDisplayAndUpdate()){
			drawUIMenu(fileChooser.getFileListing());
			drawButton(fileChooser.getUpButton());
			drawButton(fileChooser.getExitButton());
		}
	}
	
}
