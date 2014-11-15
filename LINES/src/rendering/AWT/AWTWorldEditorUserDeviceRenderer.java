package rendering.AWT;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import UI.MouseUserDevice;
import UI.UILayer;
import UI.AWT.AWTLineEditorUserDevice;
import data.graphics.AWTGraphicData;
import data.shapes.Circle;
import data.shapes.Pipe;
import data.shapes.Shape;

public class AWTWorldEditorUserDeviceRenderer implements UILayer {

	private AWTLineEditorUserDevice device;
	private AWTShapeDrawer shapeDrawer;
	
	private Circle[] 	getWorldCircles() 		{ return device.getData().getCopyOfWorldPointCollisionCircles(); }
	private Pipe[] 		getWorldRectangles() 	{ return device.getData().getCopyOfWorldLineCollisionBoxes(); }
	private boolean 	isEditorCursorHoveredOver(Shape s) { return s.contains(device.getCursorPosition()); }

	public AWTWorldEditorUserDeviceRenderer(AWTLineEditorUserDevice WORLD_EDITOR_DEVICE){
		device 		= WORLD_EDITOR_DEVICE;
		shapeDrawer = new AWTShapeDrawer();
	}
	
	@Override
	public void update(MouseUserDevice mouse) {
		// device = mouse;
	}
	
	@Override
	public void render(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		shapeDrawer.setGraphics(g);
		drawLines();
		drawPoints();
				
		g.drawString("LINES: " + device.getData().totalNumberOfLines(), device.getCursorX(), 14);
		
		device.getCurrentMouseTool().render(g);
	}
	
	private void drawPoints() {
		for(Circle worldCircle : getWorldCircles()){
			drawPoint(worldCircle);
		}
	}
	
	private void drawPoint(Circle worldCircle) {
		boolean isPointSelected = device.getData().isSelected(worldCircle);
		if(isEditorCursorHoveredOver(worldCircle)) {
			shapeDrawer.setColor(isPointSelected ? AWTGraphicData.selectedPointCircleHighlight : AWTGraphicData.pointCircleHighlight);
			shapeDrawer.drawCircle(worldCircle);
		}
		shapeDrawer.setColor(isPointSelected ? AWTGraphicData.selectedPoint : AWTGraphicData.point);
		shapeDrawer.drawPoint(worldCircle.centerCopy());
	}
	
	private void drawLines() {
		for(Pipe worldRectangle : getWorldRectangles()){
			drawLine(worldRectangle);
		}
	}
	
	private void drawLine(Pipe worldRectangle) {
		boolean isLineSelected = device.getData().isSelected(worldRectangle);
		if(isEditorCursorHoveredOver(worldRectangle)) {
			shapeDrawer.setColor(isLineSelected ? AWTGraphicData.selectedLineBoxHighlight : AWTGraphicData.lineBoxHighlight);
			shapeDrawer.drawPolygonBorder(worldRectangle.getArea());
		}
		shapeDrawer.setColor(isLineSelected ? AWTGraphicData.selectedLine : AWTGraphicData.line);
		shapeDrawer.drawLineSegment(worldRectangle.centerLine);
	}

}
