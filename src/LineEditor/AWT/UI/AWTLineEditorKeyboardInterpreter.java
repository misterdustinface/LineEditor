package LineEditor.AWT.UI;

import AWT.UI.AWTZoomableViewport;
import LineEditor.UI.InputEventFunction;
import LineEditor.UI.KeyboardUserDeviceInterpreter;
import UI.input.InputEvent;
import UI.input.KeyboardUserDevice;
import UI.input.MouseUserDevice;
import UI.input.MouseUserDeviceWrapper;
import base.Application;
import base.TickingLoop;
import functionpointers.VoidFunctionPointer;

public class AWTLineEditorKeyboardInterpreter extends KeyboardUserDeviceInterpreter {

	private boolean moveWorld;
	private int DX, DY;
	private float DZ;
	
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
	
	public AWTLineEditorKeyboardInterpreter(final KeyboardUserDevice keyboardDevice,
											final MouseUserDevice mouseUserDevice,
											final AWTZoomableViewport zoomableViewport, 
											final Application editorProgram) {
		
		super(keyboardDevice);
		
		final MouseUserDeviceWrapper mouseUserDeviceWrapper = new MouseUserDeviceWrapper();
		mouseUserDeviceWrapper.set(mouseUserDevice);
		
		TickingLoop keyboardMoverZoomer = new TickingLoop();
		VoidFunctionPointer moverZoomerFunction = new VoidFunctionPointer() {
			
			public void call() {
				move();
				zoom();
			}
			
			private void move() {
				if (moveWorld) {
					zoomableViewport.setPosition(zoomableViewport.getXPosition() + DX, zoomableViewport.getYPosition() + DY);
				} else {
					mouseUserDeviceWrapper.forcePosition(mouseUserDevice.getCursorX() - DX, mouseUserDevice.getCursorY() - DY);
				}
			}
			
			private void zoom() {
				zoomableViewport.setZoom(zoomableViewport.getZoom() + DZ);
			}
			
		};
		keyboardMoverZoomer.addFunction(moverZoomerFunction);
		
		editorProgram.addComponent("keyboardMoverZoomer", keyboardMoverZoomer);
		
		addFunction(new InputEventFunction() {
			public void call(InputEvent event) {
				if (event.is("M") && event.is("PRESSED")) {
					toggleMoveWorld();
				}
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
				if (event.is("O") && event.is("PRESSED")) {
					zoomableViewport.resetToOrigin();
				}
			}
		});
		
		addFunction(new InputEventFunction() {
			public void call(InputEvent event) {
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
				if (event.is("Z") && event.is("PRESSED")) {
					zoomableViewport.resetToDefaultZoom();
				}
			}
		});
		
	}

}