package LineEditor.AWT.UI;

import generic.Application;
import generic.TickingLoop;
import generic.fp.VoidFunctionPointer;
import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTGridLayer;
import UI.UILayerManager;
import UI.input.InputEvent;
import UI.input.KeyboardUserDevice;
import UI.input.MouseUserDevice;

public class AWTLineEditorKeyboardInterpreter implements Runnable {

	private InputEvent event;
	private boolean moveWorld;
	private int DX, DY;
	private float DZ;
	
	final private AWTEditorPanel worldEditorPanel;
	final private MouseUserDevice mouseUserDevice;
	final private KeyboardUserDevice keyboardDevice;
	final private Application editorProgram;
	final private UILayerManager layerManager;
	final private AWTGridLayer gridDrawer;
	
	public AWTLineEditorKeyboardInterpreter(final AWTEditorPanel worldEditorPanel,
											final MouseUserDevice mouseUserDevice,
											final KeyboardUserDevice keyboardDevice,
											final Application editorProgram,
											final UILayerManager layerManager,
											final AWTGridLayer gridDrawer) {
		
		this.worldEditorPanel = worldEditorPanel;
		this.mouseUserDevice = mouseUserDevice;
		this.keyboardDevice = keyboardDevice;
		this.editorProgram = editorProgram;
		this.layerManager = layerManager;
		this.gridDrawer = gridDrawer;
		
		TickingLoop keyboardMoverZoomer = new TickingLoop();
		VoidFunctionPointer moverZoomerFunction = new VoidFunctionPointer() {
			
			public void call() {
				move();
				zoom();
			}
			
			private void move() {
				if (moveWorld) {
					worldEditorPanel.setPosition(worldEditorPanel.getXPosition() + DX, worldEditorPanel.getYPosition() + DY);
				} else {
					mouseUserDevice.forcePosition(mouseUserDevice.getCursorX() - DX, mouseUserDevice.getCursorY() - DY);
				}
			}
			
			private void zoom() {
				worldEditorPanel.setZoom(worldEditorPanel.getZoom() + DZ);
			}
			
		};
		keyboardMoverZoomer.addFunction(moverZoomerFunction);
		
		editorProgram.addComponent("keyboardMoverZoomer", keyboardMoverZoomer);
	}
	
	private void toggleMoveWorld() {
		moveWorld = !moveWorld;
	}
	
	private void setMovement(int dx, int dy) {
		DX = dx;
		DY = dy;
	}
	
	private void setZoom(float dz) {
		DZ = dz;
	}
	
	@Override
	public void run() {
		for (;;) {
			event = keyboardDevice.consumeEvent();
			
			if (event.is("Up") && event.is("PRESSED")) {
				setMovement(0, 1);
			}
			if (event.is("Down") && event.is("PRESSED")) {
				setMovement(0, -1);
			}
			if (event.is("Right") && event.is("PRESSED")) {
				setMovement(-1, 0);
			}
			if (event.is("Left") && event.is("PRESSED")) {
				setMovement(1, 0);
			}
			if ((event.is("Left") || event.is("Right") || event.is("Down") || event.is("Up")) 
			&& event.is("RELEASED")) {
				setMovement(0, 0);
			}
			if (event.is("+") && event.is("PRESSED")) {
				setZoom(0.01f);
			}
			if (event.is("-") && event.is("PRESSED")) {
				setZoom(-0.01f);
			}
			if ((event.is("+") || event.is("-")) 
			&& event.is("RELEASED")) {
				setZoom(0.0f);
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
				mouseUserDevice.forceClick();
				mouseUserDevice.forceButton("RIGHT");
			}
			if (event.is("D") && event.is("PRESSED")) {
				mouseUserDevice.forceClick();
				mouseUserDevice.forceButton("MIDDLE");
			}
			if (event.is("B") && event.is("PRESSED")) {
				mouseUserDevice.forcePress();
				mouseUserDevice.forceButton("RIGHT");
			}
			if (event.is("B") && event.is("RELEASED")) {
				mouseUserDevice.forceRelease();
			}
			if (event.is("Space") && event.is("PRESSED")) {
				mouseUserDevice.forceClick();
				mouseUserDevice.forceButton("LEFT");
			}
			if (event.is("Escape") && event.is("PRESSED")) {
				editorProgram.quit();
			}
		}
	}

}
