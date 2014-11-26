package LineEditor.main;

import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTEditorPanelOptionsMenu;
import AWT.UI.AWTFileMenu;
import AWT.UI.AWTLineEditorUserDevice;
import AWT.UI.AWTProgramWindow;
import AWT.UI.AWTScreenShifter;
import AWT.UI.AWTZoomWheelListener;
import AWT.rendering.AWTGridDrawer;
import LineEditor.AWT.rendering.AWTWorldEditorUserDeviceRenderer;
import LineEditor.data.WorldGeometryData;
import LineEditor.file.WorldGeometryFiler;

public class AWTLineEditorProgram {
	
	public static void main(String[] args) {
		AWTProgramWindow window = new AWTProgramWindow("Lines");
		
		WorldGeometryData 			worldData 				= new WorldGeometryData();
		AWTLineEditorUserDevice 	lineEditorUserDevice 	= new AWTLineEditorUserDevice(worldData);
		final AWTEditorPanel 		worldEditorPanel 		= new AWTEditorPanel(lineEditorUserDevice);
		AWTScreenShifter			screenShifter 			= new AWTScreenShifter(worldEditorPanel);
		worldEditorPanel.addMouseMotionListener(screenShifter);
		worldEditorPanel.addMouseListener(screenShifter);
		worldEditorPanel.addMouseWheelListener(new AWTZoomWheelListener(worldEditorPanel));
		window.add(worldEditorPanel);
		WorldGeometryFiler worldFiler 				= new WorldGeometryFiler();
		worldFiler.setData(worldData);
		
		AWTGridDrawer 					 gridDrawer 	= new AWTGridDrawer(worldEditorPanel);
		AWTWorldEditorUserDeviceRenderer deviceRenderer = new AWTWorldEditorUserDeviceRenderer(lineEditorUserDevice);
		AWTFileMenu fileMenu = new AWTFileMenu(worldFiler);
		
		worldEditorPanel.addLayer(gridDrawer);
		worldEditorPanel.addLayer(fileMenu);
		worldEditorPanel.addLayer(deviceRenderer);
		
		MenuBar menuBar = new MenuBar();
				
		AWTEditorPanelOptionsMenu optionsMenu = new AWTEditorPanelOptionsMenu(worldEditorPanel);
		MenuItem resetZoom  = new MenuItem("Reset Zoom");
		MenuItem gotoOrigin = new MenuItem("Go To Origin");
		
		resetZoom.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				worldEditorPanel.resetToDefaultZoom();
			}
		});
		gotoOrigin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				worldEditorPanel.resetToOrigin();
			}
		});
		
		optionsMenu.add(resetZoom);
		optionsMenu.add(gotoOrigin);
		optionsMenu.addMenuItemToggleUI("Toggle Grid",   gridDrawer);
		optionsMenu.addMenuItemToggleUI("Toggle Editor", deviceRenderer);
		menuBar.add(optionsMenu);
		
		window.setMenuBar(menuBar);
	}

}
