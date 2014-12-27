package LineEditor.main;

import generic.EditorProgram;
import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTFileMenu;
import AWT.UI.AWTMenuBar;
import AWT.UI.AWTProgramWindow;
import AWT.UI.AWTScreenShifter;
import AWT.UI.AWTToggleLayersMenu;
import AWT.UI.AWTViewportOptionsMenu;
import AWT.UI.AWTZoomWheelListener;
import AWT.rendering.AWTGridDrawer;
import AWT.update.AWTProgramMain;
import LineEditor.AWT.UI.uiTools.AWTLineEditorUserDevice;
import LineEditor.AWT.rendering.AWTLinesRenderer;
import LineEditor.data.WorldGeometryData;
import LineEditor.file.WorldGeometryFiler;
import UI.UILayerManager;

public class AWTLineEditorProgram {
	
	public static void main(String[] args) {
		AWTProgramWindow window = new AWTProgramWindow("Lines");
		
		WorldGeometryData 			worldData 				= new WorldGeometryData();
		WorldGeometryFiler 			worldFiler 				= new WorldGeometryFiler();
		worldFiler.setData(worldData);
		AWTLineEditorUserDevice 	lineEditorUserDevice 	= new AWTLineEditorUserDevice(worldData);
		AWTEditorPanel 				worldEditorPanel 		= new AWTEditorPanel(lineEditorUserDevice);
		
		AWTLinesRenderer			worldRenderer  			= new AWTLinesRenderer(worldData);
		AWTGridDrawer 				gridDrawer 				= new AWTGridDrawer(worldEditorPanel);
		AWTScreenShifter			screenShifter 			= new AWTScreenShifter(worldEditorPanel);
		UILayerManager 				layerManager 			= new UILayerManager();
		
		worldEditorPanel.setLayerManager(layerManager);
		worldEditorPanel.addMouseMotionListener(screenShifter);
		worldEditorPanel.addMouseListener(screenShifter);
		worldEditorPanel.addMouseWheelListener(new AWTZoomWheelListener(worldEditorPanel));
		window.add(worldEditorPanel);

		AWTToggleLayersMenu	toggleLayersMenu = new AWTToggleLayersMenu(layerManager);
		toggleLayersMenu.addMenuItemToggleUI("GRID",   gridDrawer);
		toggleLayersMenu.addMenuItemToggleUI("EDITOR", worldRenderer);

		AWTMenuBar menuBar = new AWTMenuBar();
		menuBar.setViewport(worldEditorPanel);
		menuBar.setOffset(16, 8);
		menuBar.setSpacing(4);
		menuBar.addUIMenus( new AWTViewportOptionsMenu(worldEditorPanel),
							toggleLayersMenu,
							new AWTFileMenu(worldFiler));
		
		layerManager.addLayers(gridDrawer,
							   worldRenderer,
							   menuBar,
							   lineEditorUserDevice);
		
		window.revalidate();
		
		EditorProgram editorProgram = new EditorProgram();
		editorProgram.setMain(AWTProgramMain.create(layerManager, lineEditorUserDevice));
		editorProgram.start();
	}
}