package main;

import file.ColorPaletteLuaScriptFiler;
import generic.ColorData;

import java.awt.MenuBar;
import java.util.ArrayList;

import UI.AWT.AWTColorChooserMenu;
import UI.AWT.AWTColorPaletteMenu;
import UI.AWT.AWTDefaultMouseUserDevice;
import UI.AWT.AWTEditorPanel;
import UI.AWT.AWTFileMenu;
import UI.AWT.AWTMouseUserDevice;
import UI.AWT.AWTProgramWindow;
import UI.AWT.AWTSimpleUserDeviceDisplayLayer;
import data.shapes.Grid;
import data.shapes.Point;
import data.shapes.Rectangle;

public class AWTColorPaletteEditorProgram implements Program {
	
	int X_OFFSET     = 16;
	int MENU_WIDTH   = 180;
	int Y_OFFSET     = 16;
	int MENU_HEIGHT  = 100;
	
	Rectangle COLOR_CHOOSER_DISPLAYBOX = new Rectangle(new Point(X_OFFSET, Y_OFFSET), MENU_WIDTH, MENU_HEIGHT);
	
	int BUTTON_OFFSET = 16;
	int BUTTON_SIZE   = 32;
	int BUTTON_ROWS   = 3;
	int BUTTON_COLS   = 4;

	Grid COLOR_PALETTE_DISPLAYGRID = new Grid(new Point(2*X_OFFSET + MENU_WIDTH, Y_OFFSET), BUTTON_ROWS, BUTTON_COLS);
	
	@Override
	public void launch() {
		AWTProgramWindow window = new AWTProgramWindow("Color Palette");
		window.setSize(400, 300);
		
		ArrayList<ColorData> 		colorPalette 	= new ArrayList<ColorData>();
		ColorPaletteLuaScriptFiler 	colorFiler 		= new ColorPaletteLuaScriptFiler();
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
		editorPanel.addLayer(colorChooser);
		editorPanel.addLayer(paletteMenu);
		editorPanel.addLayer(new AWTSimpleUserDeviceDisplayLayer(userDevice));
		window.add(editorPanel);
		
		MenuBar menuBar = new MenuBar();
		menuBar.add(new AWTFileMenu(colorFiler, editorPanel));
		window.setMenuBar(menuBar);
	}
	
//	class ColorDeleteButton extends AWTMenuButton {
//		private AWTColorChooserMenu colorchooser;
//		private AWTColorPaletteMenu colorpalette;
//		
//		public ColorDeleteButton(AWTColorChooserMenu CHOOSER, AWTColorPaletteMenu PALETTE) {
//			text = "DELETE COLOR";
//			makeSuggestedBoxRelativeToPoint(COLOR_CHOOSER_DISPLAYBOX.x, COLOR_CHOOSER_DISPLAYBOX.y, X_OFFSET, Y_OFFSET);
//			center();
//			colorchooser = CHOOSER;
//			colorpalette = PALETTE;
//		}
//		
//		@Override
//		protected void pressAction() {
//			//colorpalette.requestColorDeletion(colorchooser.getColorData());
//		}
//
//		@Override
//		protected void releaseAction() {
//			colorpalette.requestColorDeletion(colorchooser.getColorData());
//		}
//	}
}
