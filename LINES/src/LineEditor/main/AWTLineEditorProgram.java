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
import UI.input.InputEvent;

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
			InputEvent event;
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
					
					if (event.is("Up") && event.is("PRESSED")) {
						move(0, 5);
					}
					if (event.is("Down") && event.is("PRESSED")) {
						move(0, -5);
					}
					if (event.is("Right") && event.is("PRESSED")) {
						move(-5, 0);
					}
					if (event.is("Left") && event.is("PRESSED")) {
						move(5, 0);
					}
					if (event.is("+") && event.is("PRESSED")) {
						zoom(0.1f);
					}
					if (event.is("-") && event.is("PRESSED")) {
						zoom(-0.1f);
					}
					if (event.is("M") && event.is("PRESSED")) {
						toggleMoveWorld();
					}
					if (event.is("G") && event.is("PRESSED")) {
						layerManager.toggleLayer(gridDrawer);
					}
					if (event.is("Z") && event.is("PRESSED")) {
						worldEditorPanel.resetToDefaultZoom();
					}
					if (event.is("O") && event.is("PRESSED")) {
						worldEditorPanel.resetToOrigin();
					}
					if (event.is("S") && event.is("PRESSED")) {
						lineEditorUserDevice.forceClick();
						lineEditorUserDevice.forceButton("RIGHT");
					}
					if (event.is("D") && event.is("PRESSED")) {
						lineEditorUserDevice.forceClick();
						lineEditorUserDevice.forceButton("MIDDLE");
					}
//					if (event.is("B") && event.is("PRESSED")) {
//						lineEditorUserDevice.forcePress();
//						lineEditorUserDevice.forceButton("RIGHT");
//					}
//					if (event.is("B") && event.is("RELEASED")) {
//						lineEditorUserDevice.forceRelease();
//					}
					if (event.is("Space") && event.is("PRESSED")) {
						//lineEditorUserDevice.forcePress();
						lineEditorUserDevice.forceClick();
						lineEditorUserDevice.forceButton("LEFT");
					}
//					if (event.is("Space") && event.is("RELEASED")) {
//						lineEditorUserDevice.forceRelease();
//					}
					if (event.is("Escape") && event.is("PRESSED")) {
						editorProgram.quit();
					}
					
					if (event.is("PRESSED") || event.is("RELEASED")) {
						System.out.println(event.toString());
					}
					
				}
			}
		};
		editorProgram.addComponent("keyboardInterpreter", keyboardInterpreter); // NEW
		
		editorProgram.start();
	}
}