package LineEditor.main;

import generic.Application;
import AWT.UI.AWTDropdownMenuBar;
import AWT.UI.AWTGridLayer;
import AWT.UI.AWTMenuBar;
import AWT.UI.AWTProgramWindow;
import AWT.UI.CommonMenus.AWTFileMenu;
import AWT.UI.CommonMenus.AWTToggleLayersMenu;
import AWT.UI.CommonMenus.AWTViewOptionsMenu;
import AWT.UI.Mouse.AWTScreenShifter;
import AWT.UI.Mouse.AWTZoomWheelListener;
import AWT.UI2.AWTDisplay;
import AWT.UI2.AWTUIDrawer;
import AWT.UI2.AWTViewport;
import AWT.UI2.AWTZoomableViewport;
import AWT.UI2.FixedDrawer;
import AWT.update.AWTProgramMain;
import LineEditor.AWT.UI.uiTools.AWTLineEditorUserDevice;
import LineEditor.AWT.rendering.AWTLinesRenderer;
import LineEditor.data.WorldGeometryData;
import LineEditor.file.WorldGeometryFiler;
import UI.UILayerManager;

public class AWTLineEditorProgramV2 {
	
	public static void main(String[] args) {
		AWTProgramWindow window = new AWTProgramWindow("Lines");
		
		WorldGeometryData 			worldData 				= new WorldGeometryData();
		WorldGeometryFiler 			worldFiler 				= new WorldGeometryFiler();
		worldFiler.setData(worldData);
		AWTLineEditorUserDevice 	lineEditorUserDevice 	= new AWTLineEditorUserDevice(worldData);
		
		final AWTZoomableViewport worldView = new AWTZoomableViewport();
		worldView.setSize(800, 600);
		
		final AWTViewport menuView = new AWTViewport();
		menuView.setSize(400, 175);	
		
		AWTDisplay display = new AWTDisplay(lineEditorUserDevice);
		display.addView(worldView);
		display.addView(menuView);
		
		AWTLinesRenderer			worldRenderer  			= new AWTLinesRenderer(worldData);
		AWTGridLayer 				gridDrawer 				= new AWTGridLayer(worldView, worldView);
		AWTScreenShifter			screenShifter 			= new AWTScreenShifter(worldView);
		
		display.addSurfaceMouseListener(screenShifter);
		display.addSurfaceMotionListener(screenShifter);
		display.addMouseWheelListener(new AWTZoomWheelListener(worldView));
		
		UILayerManager 				menuLayerManager 		= new UILayerManager();
		UILayerManager				worldLayerManager		= new UILayerManager();
		
		final AWTUIDrawer menuDrawer = new AWTUIDrawer();
		menuDrawer.setDrawing(menuView);
		menuDrawer.setLayerManager(menuLayerManager);
		
		final AWTUIDrawer worldDrawer = new AWTUIDrawer();
		worldDrawer.setDrawing(worldView);
		worldDrawer.setLayerManager(worldLayerManager);

		AWTToggleLayersMenu	toggleLayersMenu = new AWTToggleLayersMenu(worldLayerManager);
		toggleLayersMenu.addMenuItemToggleUI("GRID",   gridDrawer);
		toggleLayersMenu.addMenuItemToggleUI("EDITOR", worldRenderer);

		AWTMenuBar menuBar = new AWTMenuBar();
		menuBar.setViewport(menuView);
		menuBar.setOffset(32, 8);
		menuBar.setSpacing(4);
		menuBar.addUIMenus( new AWTViewOptionsMenu(worldView, worldView),
							toggleLayersMenu,
							new AWTFileMenu(worldFiler));
		
		menuLayerManager.addLayers(new AWTDropdownMenuBar(menuBar), lineEditorUserDevice);
		worldLayerManager.addLayers(gridDrawer, worldRenderer, lineEditorUserDevice);
		
		window.add(display);
		window.revalidate();
		
		Application editorProgram = new Application();
		editorProgram.setMain(AWTProgramMain.create(menuLayerManager, lineEditorUserDevice));
		editorProgram.setAudioSystem(AWTProgramMain.create(worldLayerManager, lineEditorUserDevice)); // Because I had to use a thread...
		
		FixedDrawer fixedDrawer = new FixedDrawer(worldDrawer);
		fixedDrawer.setDrawsPerSecond(20);
		editorProgram.setDrawer(fixedDrawer);
		
		FixedDrawer fixedDrawer2 = new FixedDrawer(menuDrawer);
		fixedDrawer2.setDrawsPerSecond(20);
		editorProgram.setRenderer(fixedDrawer2);
		
		editorProgram.start();
	}
}