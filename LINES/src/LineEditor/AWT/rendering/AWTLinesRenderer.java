package LineEditor.AWT.rendering;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import shapes.Circle;
import shapes.Pipe;
import shapes.Shape;
import LineEditor.AWT.UI.uiTools.AWTLineEditorUserDevice;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;
import AWT.UI.AWTUILayer;
import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTShapeDrawer;

public class AWTLinesRenderer implements AWTUILayer {

	private AWTLineEditorUserDevice device;
	private WorldGeometryData 		worldGeometry;
	private AWTShapeDrawer 			shapeDrawer;

	public AWTLinesRenderer(WorldGeometryData WORLD_GEOMETRY) {
		worldGeometry = WORLD_GEOMETRY;
		shapeDrawer   = new AWTShapeDrawer();
	}
	
	private Circle[] getWorldCircles()    { return worldGeometry.getWorldPointCollisionCircles(); }
	private Pipe[]   getWorldRectangles() { return worldGeometry.getWorldLineCollisionBoxes(); }
	private boolean  isEditorCursorHoveredOver(Shape s) { return s.contains(device.getCursorPosition()); }
	
	@Override
	public void update(MouseUserDevice mouse) {
		device = (AWTLineEditorUserDevice)mouse;
	}

	@Override
	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_DEFAULT);
		
		shapeDrawer.setGraphics(g);
		drawLines();
		drawPoints();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		//g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_DEFAULT);
	}
	
	private void drawPoints() {
		for(Circle worldCircle : getWorldCircles())
			drawPoint(worldCircle);
	}
	
	private void drawPoint(Circle worldCircle) {
		boolean isPointSelected = device.isSelected(worldCircle);
		if(isEditorCursorHoveredOver(worldCircle)) {
			shapeDrawer.setColor(isPointSelected ? AWTGraphicData.selectedPointCircleHighlight : AWTGraphicData.pointCircleHighlight);
			shapeDrawer.drawCircle(worldCircle);
		}
		shapeDrawer.setColor(isPointSelected ? AWTGraphicData.selectedPoint : AWTGraphicData.point);
		shapeDrawer.drawPoint(worldCircle.center());
	}
	
	private void drawLines() {
		for(Pipe worldRectangle : getWorldRectangles())
			drawLine(worldRectangle);
	}
	
	private void drawLine(Pipe worldRectangle) {
		boolean isLineSelected = device.isSelected(worldRectangle);
		if(isEditorCursorHoveredOver(worldRectangle)) {
			shapeDrawer.setColor(isLineSelected ? AWTGraphicData.selectedLineBoxHighlight : AWTGraphicData.lineBoxHighlight);
			shapeDrawer.drawPolygon(worldRectangle.getArea());
		}
		shapeDrawer.setColor(isLineSelected ? AWTGraphicData.selectedLine : AWTGraphicData.line);
		shapeDrawer.drawLineSegment(worldRectangle.centerLine);
	}

}
