package LineEditor.main;

import generic.Application;
import generic.EditorProgramMain;
import AWT.UI.AWTDropdownMenuBar;
import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTGridLayer;
import AWT.UI.AWTMenuBar;
import AWT.UI.AWTProgramWindow;
import AWT.UI.CommonMenus.AWTFileMenu;
import AWT.UI.CommonMenus.AWTToggleLayersMenu;
import AWT.UI.CommonMenus.AWTViewOptionsMenu;
import AWT.UI.Mouse.AWTScreenShifter;
import AWT.UI.Mouse.AWTZoomWheelListener;
import AWT.input.AWTKeyboardUserDevice;
import LineEditor.AWT.UI.uiTools.AWTLineEditorUserDevice;
import LineEditor.AWT.rendering.AWTLinesRenderer;
import LineEditor.data.WorldGeometryData;
import LineEditor.file.WorldGeometryFiler;
import UI.UILayerManager;

public class AWTLineEditorProgram {
	
	public static void main(String[] args) {
		final AWTProgramWindow window = new AWTProgramWindow("Lines");
		
		final WorldGeometryData worldData = new WorldGeometryData();
		final WorldGeometryFiler worldFiler = new WorldGeometryFiler();
		worldFiler.setData(worldData);
		final AWTLineEditorUserDevice lineEditorUserDevice = new AWTLineEditorUserDevice(worldData);
		final AWTKeyboardUserDevice keyboardDevice = new AWTKeyboardUserDevice(); // NEW
		
		final AWTEditorPanel worldEditorPanel = new AWTEditorPanel();	
		worldEditorPanel.addViewportMotionListener(lineEditorUserDevice);
		worldEditorPanel.addViewportMouseListener(lineEditorUserDevice);
		
		final AWTLinesRenderer worldRenderer = new AWTLinesRenderer(worldData);
		final AWTGridLayer gridDrawer = new AWTGridLayer(worldEditorPanel, worldEditorPanel);
		final AWTScreenShifter screenShifter = new AWTScreenShifter(worldEditorPanel);
		final UILayerManager layerManager = new UILayerManager();
		
		worldEditorPanel.setLayerManager(layerManager);
		worldEditorPanel.addMouseMotionListener(screenShifter);
		worldEditorPanel.addMouseListener(screenShifter);
		worldEditorPanel.addMouseWheelListener(new AWTZoomWheelListener(worldEditorPanel));
		window.add(worldEditorPanel);
		
		window.addKeyListener(keyboardDevice); // NEW

		final AWTToggleLayersMenu toggleLayersMenu = new AWTToggleLayersMenu(layerManager);
		toggleLayersMenu.addMenuItemToggleUI("GRID",   gridDrawer);
		toggleLayersMenu.addMenuItemToggleUI("EDITOR", worldRenderer);

		final AWTMenuBar menuBar = new AWTMenuBar();
		menuBar.setViewport(worldEditorPanel);
		menuBar.setOffset(32, 8);
		menuBar.setSpacing(2);
		
		menuBar.addUIMenus( new AWTViewOptionsMenu(worldEditorPanel, worldEditorPanel),
							toggleLayersMenu,
							new AWTFileMenu(worldFiler));
		
		layerManager.addLayers(gridDrawer,
							   worldRenderer,
							   new AWTDropdownMenuBar(menuBar),
							   lineEditorUserDevice);
		
		window.revalidate();
		
		final Application editorProgram = new Application();
		editorProgram.setMain(EditorProgramMain.create(layerManager, lineEditorUserDevice));
		
		// NEW
		Runnable keyboardInterpreter = new Runnable() {
			String event;
			boolean moveWorld = true;
			
			private void toggleMoveWorld() {
				moveWorld = !moveWorld;
			}
			
			private void move(int dx, int dy) {
				if (moveWorld) {
					worldEditorPanel.setPosition(worldEditorPanel.getXPosition() + dx, worldEditorPanel.getYPosition() + dy);
				} else {
					lineEditorUserDevice.forcePosition(lineEditorUserDevice.getCursorX() - dx, lineEditorUserDevice.getCursorY() - dy);
				}
			}
			
			private void zoom(float dz) {
				worldEditorPanel.setZoom(worldEditorPanel.getZoom() + dz);
			}
			
			public void run() {
				for (;;) {
					event = keyboardDevice.consumeEvent();
					
					if (event != null) {
						
						if (event.contains("keyText=Up") && event.contains("KEY_PRESSED")) {
							move(0, 5);
						}
						if (event.contains("keyText=Down") && event.contains("KEY_PRESSED")) {
							move(0, -5);
						}
						if (event.contains("keyText=Right") && event.contains("KEY_PRESSED")) {
							move(-5, 0);
						}
						if (event.contains("keyText=Left") && event.contains("KEY_PRESSED")) {
							move(5, 0);
						}
						if (event.contains("keyChar=\'+\'") && event.contains("KEY_PRESSED")) {
							zoom(0.1f);
						}
						if (event.contains("keyChar=\'-\'") && event.contains("KEY_PRESSED")) {
							zoom(-0.1f);
						}
						if (event.contains("keyText=M") && event.contains("KEY_PRESSED")) {
							toggleMoveWorld();
						}
						if (event.contains("keyText=G") && event.contains("KEY_PRESSED")) {
							layerManager.toggleLayer(gridDrawer);
						}
						if (event.contains("keyText=Z") && event.contains("KEY_PRESSED")) {
							worldEditorPanel.resetToDefaultZoom();
						}
						if (event.contains("keyText=O") && event.contains("KEY_PRESSED")) {
							worldEditorPanel.resetToOrigin();
						}
						if (event.contains("keyText=S") && event.contains("KEY_PRESSED")) {
							lineEditorUserDevice.forceClick();
							lineEditorUserDevice.forceButton("right");
						}
						if (event.contains("keyText=D") && event.contains("KEY_PRESSED")) {
							lineEditorUserDevice.forceClick();
							lineEditorUserDevice.forceButton("middle");
						}
//						if (event.contains("keyText=B") && event.contains("KEY_PRESSED")) {
//							lineEditorUserDevice.forcePress();
//							lineEditorUserDevice.forceButton("right");
//						}
//						if (event.contains("keyText=B") && event.contains("KEY_RELEASED")) {
//							lineEditorUserDevice.forceRelease();
//						}
						if (event.contains("keyText=Space") && event.contains("KEY_PRESSED")) {
							//lineEditorUserDevice.forcePress();
							lineEditorUserDevice.forceClick();
							lineEditorUserDevice.forceButton("left");
						}
//						if (event.contains("keyText=Space") && event.contains("KEY_RELEASED")) {
//							lineEditorUserDevice.forceRelease();
//						}
						if (event.contains("keyText=Escape") && event.contains("KEY_PRESSED")) {
							editorProgram.quit();
						}
						
						//System.out.println(event);
					}
					
				}
			}
		};
		editorProgram.addComponent("keyboardInterpreter", keyboardInterpreter); // NEW
		
		editorProgram.start();
	}
}