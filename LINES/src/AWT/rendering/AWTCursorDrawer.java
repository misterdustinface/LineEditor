package AWT.rendering;

import rendering.CursorDrawer;
import AWT.graphicdata.AWTGraphicData;

public class AWTCursorDrawer extends AWTRenderer implements CursorDrawer {
	
	public AWTCursorDrawer() {}
	
	public void drawCrosshairCursor(int X, int Y){
		graphics.drawLine(	X - AWTGraphicData.cursorStretchOutAmount, Y, 
							X - AWTGraphicData.cursorCenterGapAmount,  Y);
		graphics.drawLine(	X + AWTGraphicData.cursorStretchOutAmount, Y, 
							X + AWTGraphicData.cursorCenterGapAmount,  Y);
		graphics.drawLine(	X, Y - AWTGraphicData.cursorStretchOutAmount, 
							X, Y - AWTGraphicData.cursorCenterGapAmount);
		graphics.drawLine(	X, Y + AWTGraphicData.cursorStretchOutAmount, 
							X, Y + AWTGraphicData.cursorCenterGapAmount);
	}

	private final static double radianThetaOfCursor = Math.PI/6;
	public void drawTriangularCrosshairCursor(int X, int Y){
		graphics.drawLine(	(int)(X - AWTGraphicData.cursorStretchOutAmount   * Math.cos(radianThetaOfCursor)), (int)(Y + AWTGraphicData.cursorStretchOutAmount * Math.sin(radianThetaOfCursor)),
							(int)(X - AWTGraphicData.cursorCenterGapAmount    * Math.cos(radianThetaOfCursor)), (int)(Y + AWTGraphicData.cursorCenterGapAmount  * Math.sin(radianThetaOfCursor)));
		graphics.drawLine(	1+(int)(X + AWTGraphicData.cursorStretchOutAmount * Math.cos(radianThetaOfCursor)), (int)(Y + AWTGraphicData.cursorStretchOutAmount * Math.sin(radianThetaOfCursor)), 
							1+(int)(X + AWTGraphicData.cursorCenterGapAmount  * Math.cos(radianThetaOfCursor)), (int)(Y + AWTGraphicData.cursorCenterGapAmount  * Math.sin(radianThetaOfCursor)));
		graphics.drawLine(	X, Y - AWTGraphicData.cursorStretchOutAmount, 
							X, Y - AWTGraphicData.cursorCenterGapAmount);
	}
	
	public void drawLargeCircleCursor(int X, int Y){
		drawO(X,Y, AWTGraphicData.cursorStretchOutAmount);
	}
	public void drawSmallCircleCursor(int X, int Y){
		drawO(X,Y, AWTGraphicData.cursorCenterGapAmount);
	}
	
	public void drawLargeXCursor(int X, int Y){
		drawX(X,Y, AWTGraphicData.cursorStretchOutAmount);
	}
	public void drawSmallXCursor(int X, int Y){
		drawX(X,Y, AWTGraphicData.cursorCenterGapAmount);
	}
	
	private void drawX(int X, int Y, int radius){
		graphics.drawLine(	X - radius, Y - radius, 
							X + radius, Y + radius);
		graphics.drawLine(	X + radius, Y - radius, 
							X - radius, Y + radius);
	}
	private void drawO(int X, int Y, int radius){
		graphics.drawOval(	X - radius, 
							Y - radius, 
							radius+radius, 
							radius+radius);
	}
	
	@Override
	public void drawPointerCursor(int X, int Y) {

		int last = AWTGraphicData.POINTER_CURSOR_X.length-1;
		
		for(int i = 0; i < last; ++i) {
			graphics.drawLine(X + AWTGraphicData.POINTER_CURSOR_X[i]  , Y + AWTGraphicData.POINTER_CURSOR_Y[i]  , 
					  		  X + AWTGraphicData.POINTER_CURSOR_X[i+1], Y + AWTGraphicData.POINTER_CURSOR_Y[i+1]);
		}
		
		graphics.drawLine(X + AWTGraphicData.POINTER_CURSOR_X[0]   , Y + AWTGraphicData.POINTER_CURSOR_Y[0]   , 
		  		  		  X + AWTGraphicData.POINTER_CURSOR_X[last], Y + AWTGraphicData.POINTER_CURSOR_Y[last]);
	}
}
