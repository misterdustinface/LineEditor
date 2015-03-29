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
import AWT.UI.Mouse.AWTMouseUserDevice;
import AWT.UI.Mouse.AWTScreenShifter;
import AWT.UI.Mouse.AWTZoomWheelListener;
import AWT.input.AWTKeyboardUserDevice;
import LineEditor.AWT.UI.AWTLineEditorKeyboardInterpreter;
import LineEditor.AWT.UI.uiTools.AWTLineEditorToolLayer;
import LineEditor.AWT.rendering.AWTLineEditorWorldLayerRenderer;
import LineEditor.UI.InputEventFunction;
import LineEditor.data.WorldGeometryData;
import LineEditor.file.WorldGeometryFiler;
import UI.UILayerManager;
import UI.input.InputEvent;
import UI.input.MouseUserDeviceWrapper;

public class AWTLineEditorProgram {
	
	public static void main(String[] args) {
		final AWTProgramWindow window = new AWTProgramWindow("Lines");
		final AWTKeyboardUserDevice keyboardDevice = new AWTKeyboardUserDevice();
		window.addKeyListener(keyboardDevice);
		
		final WorldGeometryData worldData = new WorldGeometryData();
		final WorldGeometryFiler worldFiler = new WorldGeometryFiler();
		worldFiler.setData(worldData);
		final AWTLineEditorToolLayer lineEditorLayer = new AWTLineEditorToolLayer(worldData);
		
		final AWTEditorPanel worldEditorPanel = new AWTEditorPanel();	
		final AWTMouseUserDevice mouseUserDevice = new AWTMouseUserDevice();
		worldEditorPanel.addViewportMotionListener(mouseUserDevice);
		worldEditorPanel.addViewportMouseListener(mouseUserDevice);
		
		final AWTLineEditorWorldLayerRenderer worldRenderer = new AWTLineEditorWorldLayerRenderer(lineEditorLayer, worldData);
		final AWTGridLayer gridDrawer = new AWTGridLayer(worldEditorPanel, worldEditorPanel);
		final AWTScreenShifter screenShifter = new AWTScreenShifter(worldEditorPanel);
		final UILayerManager layerManager = new UILayerManager();
		
		worldEditorPanel.setLayerManager(layerManager);
		worldEditorPanel.addMouseMotionListener(screenShifter);
		worldEditorPanel.addMouseListener(screenShifter);
		worldEditorPanel.addMouseWheelListener(new AWTZoomWheelListener(worldEditorPanel));
		window.add(worldEditorPanel);

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
							   lineEditorLayer);
		
		window.revalidate();
		
		final Application editorProgram = new Application();
		editorProgram.setMain(EditorProgramMain.create(layerManager, mouseUserDevice));
		
		AWTLineEditorKeyboardInterpreter keyboardInterpreter = new AWTLineEditorKeyboardInterpreter(
				keyboardDevice,      
				mouseUserDevice,
				worldEditorPanel,    
				editorProgram
		);
		
		keyboardInterpreter.addFunction(new InputEventFunction() {
			public void call(InputEvent event) {
				if (event.is("Escape") && event.is("PRESSED")) {
					editorProgram.quit();
				}
			}
		});
		
		final MouseUserDeviceWrapper mouseUserDeviceWrapper = new MouseUserDeviceWrapper();
		mouseUserDeviceWrapper.set(mouseUserDevice);
		
		keyboardInterpreter.addFunction(new InputEventFunction() {
			public void call(InputEvent event) {
				if (event.is("S") && event.is("PRESSED")) {
					mouseUserDeviceWrapper.forceClick();
					mouseUserDeviceWrapper.forceButton("RIGHT");
				}
				if (event.is("D") && event.is("PRESSED")) {
					mouseUserDeviceWrapper.forceClick();
					mouseUserDeviceWrapper.forceButton("MIDDLE");
				}
				if (event.is("B") && event.is("PRESSED")) {
					mouseUserDeviceWrapper.forcePress();
					mouseUserDeviceWrapper.forceButton("RIGHT");
				}
				if (event.is("B") && event.is("RELEASED")) {
					mouseUserDeviceWrapper.forceRelease();
				}
				if (event.is("Space") && event.is("PRESSED")) {
					mouseUserDeviceWrapper.forceClick();
					mouseUserDeviceWrapper.forceButton("LEFT");
				}
			}
		});
		
		keyboardInterpreter.addFunction(new InputEventFunction() {
			public void call(InputEvent event) {
				if (event.is("G") && event.is("PRESSED")) {
					layerManager.toggleLayer(gridDrawer);
				}
			}
		});
		
		editorProgram.addComponent("keyboardInterpreter", keyboardInterpreter);
		
		editorProgram.start();
	}
}