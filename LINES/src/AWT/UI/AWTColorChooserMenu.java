package AWT.UI;

import generic.ColorData;
import generic.DataModificationNotifier;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTMenuDrawer;
import UI.MouseUserDevice;
import data.shapes.Rectangle;

public class AWTColorChooserMenu extends DataModificationNotifier implements AWTUILayer {
	
	private AWTMenuDrawer menuDrawer;
	
	private final static int R = 0;
	private final static int G = 1;
	private final static int B = 2;
	private final static int A = 3;
	private AWTHueBarSlider[] hueSliders = {new AWTHueBarSlider(), new AWTHueBarSlider(), new AWTHueBarSlider(), new AWTHueBarSlider()};
	private Rectangle displayBox;
	private static int elementSize         = 64;
	private static int elementShortsize    = 21;
	private static int elementOffset       = 4;
	
	private Color     awtColor;
	private ColorData color;
	
	private ArrayList<AWTMenuButton> buttons;
	
	public AWTColorChooserMenu(Rectangle DISPLAYBOX) {
		displayBox = DISPLAYBOX;
				
		color    = new ColorData(128,128,128,255);
		awtColor = new Color(color.r, color.g, color.b, color.a);
		
		hueSliders[R].setBase(new Rectangle(displayBox.x + elementOffset, displayBox.y +elementOffset , elementShortsize, displayBox.height));
		hueSliders[R].setBaseColor(AWTGraphicData.lightclear);
		hueSliders[R].setColor(Color.RED.darker(), Color.RED, Color.RED.darker());
		
		hueSliders[G].setBase(new Rectangle(displayBox.x + 2*elementOffset + elementShortsize, displayBox.y +elementOffset , elementShortsize, displayBox.height));
		hueSliders[G].setBaseColor(AWTGraphicData.lightclear);
		hueSliders[G].setColor(Color.GREEN.darker(), Color.GREEN, Color.GREEN.darker());
		
		hueSliders[B].setBase(new Rectangle(displayBox.x + 3*elementOffset + 2*elementShortsize, displayBox.y +elementOffset, elementShortsize, displayBox.height));
		hueSliders[B].setBaseColor(AWTGraphicData.lightclear);
		hueSliders[B].setColor(Color.BLUE.darker(), Color.BLUE, Color.BLUE.darker());
		
		hueSliders[A].setBase(new Rectangle(displayBox.x + 4*elementOffset + 3*elementShortsize, displayBox.y +elementOffset, elementShortsize, displayBox.height));
		hueSliders[A].setBaseColor(AWTGraphicData.lightclear);
		hueSliders[A].setColor(AWTGraphicData.buttonPressedColor, AWTGraphicData.buttonColor, AWTGraphicData.buttonHighlightColor);
		
		setSlidersToMatchColorData();
		
		menuDrawer = new AWTMenuDrawer();
		buttons = new ArrayList<AWTMenuButton>();
	}
	
	public ColorData getColorData() { return color; }
	
	public void setColorData(ColorData COLOR_DATA) {
		color    = COLOR_DATA;
		awtColor = new Color(color.r, color.g, color.b, color.a);
		setSlidersToMatchColorData();
	}	
	
	private void setSlidersToMatchColorData() {
		hueSliders[R].setHue(color.r);
		hueSliders[G].setHue(color.g);
		hueSliders[B].setHue(color.b);
		hueSliders[A].setHue(color.a);
	}
	
	private void setColorDataToMatchSliders() {
		color.r = hueSliders[R].getHue();
		color.g = hueSliders[G].getHue();
		color.b = hueSliders[B].getHue();
		color.a = hueSliders[A].getHue();
	}
	
	@Override
	public void render(Graphics2D g) {
		menuDrawer.setGraphics(g);
		//menuDrawer.drawMenu(position, width, height);
		for(AWTMenuButton button : buttons)
			menuDrawer.drawButton(button);
		for(AWTBarSlider slider : hueSliders)
			menuDrawer.drawSlider(slider);

		g.setColor(Color.WHITE);
		g.fillOval(displayBox.x + displayBox.width - elementSize - elementOffset, displayBox.y + elementSize/4 + elementOffset, elementSize, elementSize);
		g.setColor(awtColor);
		g.fillRect(displayBox.x + displayBox.width - elementSize - elementOffset, displayBox.y + elementOffset, elementSize, displayBox.height);
	}

	@Override
	public void update(MouseUserDevice mouse) {
		for(AWTMenuButton button : buttons)
			button.update(mouse);
		for(AWTBarSlider slider : hueSliders)
			slider.update(mouse);

		if(slidersHaveChanged()) {
			formColorWithCombinedHues();
			notifyListeners();
		}
		
	}

	private boolean slidersHaveChanged() {
		return  ( ( hueSliders[R].getHue() ^ color.r ) 
				| ( hueSliders[G].getHue() ^ color.g )
				| ( hueSliders[B].getHue() ^ color.b )
				| ( hueSliders[A].getHue() ^ color.a )
				) > 0;
	}
	
	private void formColorWithCombinedHues() {
		setColorDataToMatchSliders();
		awtColor = new Color(color.r, color.g, color.b, color.a);
	}
	
	public void addButton(AWTMenuButton button) {
		buttons.add(button);
	}
}
