package main;

import file.ColorPaletteFiler;
import generic.ColorData;

import java.util.ArrayList;

import AWT.UI.AWTColorChooserMenu;
import AWT.UI.AWTColorPaletteMenu;
import AWT.UI.AWTDefaultMouseUserDevice;
import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTFileMenu;
import AWT.UI.AWTMenuButton;
import AWT.UI.AWTMouseUserDevice;
import AWT.UI.AWTProgramWindow;
import AWT.UI.AWTSimpleUserDeviceDisplayLayer;
import data.shapes.Grid;
import data.shapes.Point;
import data.shapes.Rectangle;

public class AWTColorPaletteEditorProgram implements Program {
	
	int X_OFFSET     = 16;
	int MENU_WIDTH   = 180;
	int Y_OFFSET     = 16;
	int MENU_HEIGHT  = 100;
	
	int COLOR_CHOOSER_DISPLAYBOX_YOFFSET = Y_OFFSET + 100;
	
	Rectangle COLOR_CHOOSER_DISPLAYBOX = new Rectangle(new Point(X_OFFSET, COLOR_CHOOSER_DISPLAYBOX_YOFFSET), MENU_WIDTH, MENU_HEIGHT);
	
	int BUTTON_OFFSET = 16;
	int BUTTON_SIZE   = 32;
	int BUTTON_ROWS   = 4;
	int BUTTON_COLS   = 3;

	Grid COLOR_PALETTE_DISPLAYGRID = new Grid(new Point(2*X_OFFSET + MENU_WIDTH, Y_OFFSET), BUTTON_ROWS, BUTTON_COLS);
	
	int DELETE_BUTTON_X_POS = 107;
	int DELETE_BUTTON_Y_POS = 80 - Y_OFFSET;
	
	@Override
	public void launch() {
		AWTProgramWindow window = new AWTProgramWindow("Color Palette");
		window.setSize(400, 300);
		
		ArrayList<ColorData> colorPalette 	= new ArrayList<ColorData>();
		ColorPaletteFiler 	 colorFiler 	= new ColorPaletteFiler();
		colorFiler.setPalette(colorPalette);

		AWTColorChooserMenu colorChooser = new AWTColorChooserMenu(COLOR_CHOOSER_DISPLAYBOX);
		
		AWTColorPaletteMenu paletteMenu = new AWTColorPaletteMenu(colorChooser, COLOR_PALETTE_DISPLAYGRID);
		paletteMenu.setButtonOffset(BUTTON_OFFSET);
		paletteMenu.setButtonSize(BUTTON_SIZE);
		paletteMenu.setPalette(colorPalette);
		colorFiler.addDataModificationListener(paletteMenu.getDataModificationListener());
		
		//colorChooser.addButton(new ColorDeleteButton(colorChooser, paletteMenu));
		
		AWTMouseUserDevice 	userDevice 	= new AWTDefaultMouseUserDevice();
		AWTEditorPanel 		editorPanel = new AWTEditorPanel(userDevice);
		AWTFileMenu 		fileMenu 	= new AWTFileMenu(colorFiler);
		editorPanel.addLayer(colorChooser);
		editorPanel.addLayer(paletteMenu);
		editorPanel.addLayer(fileMenu);
		editorPanel.addLayer(new AWTSimpleUserDeviceDisplayLayer(userDevice));
		window.add(editorPanel);
		window.revalidate();
	}
	
	class ColorDeleteButton extends AWTMenuButton {
		private AWTColorChooserMenu colorchooser;
		private AWTColorPaletteMenu colorpalette;
		
		public ColorDeleteButton(AWTColorChooserMenu CHOOSER, AWTColorPaletteMenu PALETTE) {
			textLabel.setText("DELETE COLOR");
			makeSuggestedBoxRelativeToPoint(DELETE_BUTTON_X_POS, DELETE_BUTTON_Y_POS);
			textLabel.center();
			colorchooser = CHOOSER;
			colorpalette = PALETTE;
		}
		
		@Override
		protected void pressAction() {
			colorpalette.requestColorDeletion(colorchooser.getColorData());
		}

		@Override
		protected void releaseAction() {}
	}
}
