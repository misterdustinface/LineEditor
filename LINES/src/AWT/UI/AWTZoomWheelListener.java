package AWT.UI;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class AWTZoomWheelListener implements MouseWheelListener {
	
	private AWTViewport viewport;
	
	public AWTZoomWheelListener(AWTViewport VIEWPORT) {
		viewport = VIEWPORT;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {		
		if(e.getPreciseWheelRotation() > 0) {
			zoomOut();
		} else {
			zoomIn();
		}
		
		//scale();
		viewport.repaint();
	}
	
	final private static float ZOOM_TICK_VIEW_SCALE_MODIFIER = 0.1f;

	public void zoomIn(){
		//position.set(mouse.getCursorX(), mouse.getCursorY());
		viewport.increaseZoom(ZOOM_TICK_VIEW_SCALE_MODIFIER);
	}
	public void zoomOut(){
		viewport.decreaseZoom(ZOOM_TICK_VIEW_SCALE_MODIFIER);
		if(viewport.getZoom() < ZOOM_TICK_VIEW_SCALE_MODIFIER){
			viewport.setZoom(ZOOM_TICK_VIEW_SCALE_MODIFIER);
		} else {
			//position.set(mouse.getCursorX(), mouse.getCursorY());
		}
	}
}
