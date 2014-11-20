package UI.AWT;

import generic.ColorData;
import generic.DataModificationListener;

import java.awt.Color;
import java.util.ArrayList;

import UI.MouseUserDevice;
import data.shapes.Grid;

public class AWTColorPaletteMenu extends AWTGridMenu {

	private ArrayList<ColorData> 	paletteColors;
	private AWTColorChooserMenu 	colorChooser;
	private ColorData 				toRemove;
	
	public AWTColorPaletteMenu(AWTColorChooserMenu COLOR_CHOOSER, Grid DISPLAYGRID) {
		super(DISPLAYGRID);
		colorChooser = COLOR_CHOOSER;
		setPalette(new ArrayList<ColorData>());
	}
	
	public void setPalette(ArrayList<ColorData> colorData) {
		paletteColors = colorData;
		remakeButtons();
	}
	
	private void removeColor(ColorData COLOR_DATA) {
		paletteColors.remove(COLOR_DATA);
		remakeButtons();
	}
	
	public void remakeButtons() {
		clearButtons();
		makeButtons(paletteColors.size());
	}
	
	public DataModificationListener getDataModificationListener() {
		return new DataModificationListener() {
			@Override
			protected void whenMyDataIsModifiedExternally() {
				remakeButtons();
			}
		};
	}
	
	@Override
	protected AWTMenuButton newButton(int index) {
		return new ColorPaletteButton(paletteColors.get(index));
	}
	
	@Override
	protected AWTMenuButton newEmptyButton() {
		ColorData newColor = new ColorData();
		newColor.r = 200; newColor.g = 200; newColor.b = 200; newColor.a = 255;
		paletteColors.add(newColor);
		return new ColorPaletteButton(newColor);
	}	
	
	public void requestColorDeletion(ColorData COLOR_DATA) {
		setColorToRemove(COLOR_DATA);
	}
	
	private boolean shouldRemoveColor() 			{ return toRemove != null; }
	private void	setColorToRemove(ColorData cd) 	{ toRemove = cd; }
	private void    removalComplete()   			{ toRemove = null; }
	
	public void update(MouseUserDevice mouse) {
		super.update(mouse);
		if(shouldRemoveColor()) {
			removeColor(toRemove);
			removalComplete();
		}
		
	}
	
	class ColorPaletteButton extends AWTMenuButton {

		private ColorData colordata;
		
		private final Color PRESSED_COLOR   = new Color(	pressedColor.getRed(),
															pressedColor.getGreen(),
															pressedColor.getBlue(),
															64);
		private final Color HIGHLIGHT_COLOR = new Color(	highlightColor.getRed(),
															highlightColor.getGreen(),
															highlightColor.getBlue(),
															64);
		//private boolean isDeleteButtonPressed = false;
		
		public ColorPaletteButton(ColorData COLOR_DATA) {
			colordata = COLOR_DATA;
			pressedColor = PRESSED_COLOR;
			highlightColor = HIGHLIGHT_COLOR;
			updateColor();
			
			colorChooser.addDataModificationListener(new DataModificationListener() {
				@Override
				protected void whenMyDataIsModifiedExternally() {
					updateColor();
				}
			});
		}
		
		private void updateColor() {
			setColor(pressedColor,
					 new Color(colordata.r, colordata.g, colordata.b, colordata.a),
					 highlightColor);
		}
		
		@Override
		protected void pressAction() { 
			colorChooser.setColorData(colordata); 
		}
		@Override
		protected void releaseAction() {
//			if(isDeleteButtonPressed) { 
//				requestColorDeletion(colordata);
//				isDeleteButtonPressed = false;
//			}
		}
		
//		public void update(MouseUserDevice mouse) {
//			isDeleteButtonPressed = mouse.isTerciaryButton() && mouse.isClicked();
//			super.update(mouse);
//		}
		
	}

}