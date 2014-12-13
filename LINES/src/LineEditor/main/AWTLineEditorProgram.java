package LineEditor.main;

import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTLayerManager;
import AWT.UI.AWTViewportOptionsMenu;
import AWT.UI.AWTFileMenu;
import AWT.UI.AWTProgramWindow;
import AWT.UI.AWTScreenShifter;
import AWT.UI.AWTToggleLayersMenu;
import AWT.UI.AWTZoomWheelListener;
import AWT.rendering.AWTGridDrawer;
import AWT.update.AWTProgramMain;
import LineEditor.AWT.UI.uiTools.AWTLineEditorUserDevice;
import LineEditor.AWT.rendering.AWTLinesRenderer;
import LineEditor.data.WorldGeometryData;
import LineEditor.file.WorldGeometryFiler;

public class AWTLineEditorProgram {
	
	public static void main(String[] args) {
		AWTProgramWindow window = new AWTProgramWindow("Lines");
		
		WorldGeometryData 			worldData 				= new WorldGeometryData();
		AWTLinesRenderer			worldRenderer  			= new AWTLinesRenderer(worldData);
		WorldGeometryFiler 			worldFiler 				= new WorldGeometryFiler();
		worldFiler.setData(worldData);
		AWTFileMenu 				fileMenu 				= new AWTFileMenu(worldFiler);
		AWTLineEditorUserDevice 	lineEditorUserDevice 	= new AWTLineEditorUserDevice(worldData);
		AWTEditorPanel 				worldEditorPanel 		= new AWTEditorPanel(lineEditorUserDevice);
		AWTLayerManager 			layerManager 			= new AWTLayerManager();
		AWTToggleLayersMenu			toggleLayersMenu		= new AWTToggleLayersMenu(layerManager);
		AWTViewportOptionsMenu 		optionsMenu 			= new AWTViewportOptionsMenu(worldEditorPanel);
		AWTGridDrawer 				gridDrawer 				= new AWTGridDrawer(worldEditorPanel);
		AWTScreenShifter			screenShifter 			= new AWTScreenShifter(worldEditorPanel);
		worldEditorPanel.setLayerManager(layerManager);
		worldEditorPanel.addMouseMotionListener(screenShifter);
		worldEditorPanel.addMouseListener(screenShifter);
		worldEditorPanel.addMouseWheelListener(new AWTZoomWheelListener(worldEditorPanel));
		window.add(worldEditorPanel);

		toggleLayersMenu.addMenuItemToggleUI("GRID",   gridDrawer);
		toggleLayersMenu.addMenuItemToggleUI("EDITOR", worldRenderer);
		
		layerManager.addLayer(gridDrawer);
		layerManager.addLayer(worldRenderer);
		// Make a menubar which is a set of dropdown menus or roots buttons. Somehow stop the viewport from changing it.
		layerManager.addLayer(optionsMenu);
		layerManager.addLayer(toggleLayersMenu);
		layerManager.addLayer(fileMenu);
		// end menubar
		layerManager.addLayer(lineEditorUserDevice);
		
		window.revalidate();
		
		AWTProgramMain main = new AWTProgramMain();
		main.setMouse(lineEditorUserDevice);
		main.setLayerManager(layerManager);
		Thread mainThread = new Thread(main);
		mainThread.start();
	}
}