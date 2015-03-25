package LineEditor.AWT.rendering;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import shapes.Circle;
import shapes.Pipe;
import shapes.Shape;
import AWT.UI.AWTUILayer;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.AWT.UI.uiTools.AWTLineEditorUserDevice;
import LineEditor.AWT.graphicdata.LineEditorAWTGraphicData;
import LineEditor.data.WorldGeometryData;
import UI.input.MouseUserDevice;

public class AWTLinesRenderer implements AWTUILayer {

	private AWTLineEditorUserDevice device;
	private WorldGeometryData 		worldGeometry;
	private AWTShapeDrawer 			shapeDrawer;
	private LineEditorAWTGraphicData graphicData;

	public AWTLinesRenderer(WorldGeometryData WORLD_GEOMETRY) {
		worldGeometry = WORLD_GEOMETRY;
		shapeDrawer   = AWTShapeDrawer.getShapeDrawer();
		graphicData = LineEditorAWTGraphicData.getGraphicData();
	}
	
	private Circle[] getWorldCircles() { 
		return worldGeometry.getWorldPointCollisionCircles(); 
	}
	
	private Pipe[] getWorldRectangles() { 
		return worldGeometry.getWorldLineCollisionBoxes(); 
	}
	
	private boolean isEditorCursorHoveredOver(Shape s) { 
		return s.contains(device.getCursorPosition()); 
	}
	
	public void update(MouseUserDevice mouse) {
		device = (AWTLineEditorUserDevice)mouse;
	}

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
		for (Circle worldCircle : getWorldCircles())
			drawPoint(worldCircle);
	}
	
	private void drawPoint(Circle worldCircle) {
		boolean isPointSelected = device.isSelected(worldCircle);
		if (isEditorCursorHoveredOver(worldCircle)) {
			shapeDrawer.setColor(isPointSelected ? graphicData.getColorOf("selectedPointCircleHighlight") : graphicData.getColorOf("pointCircleHighlight"));
			shapeDrawer.drawCircle(worldCircle);
		}
		shapeDrawer.setColor(isPointSelected ? graphicData.getColorOf("selectedPoint") : graphicData.getColorOf("point"));
		shapeDrawer.drawPoint(worldCircle.center());
	}
	
	private void drawLines() {
		for (Pipe worldRectangle : getWorldRectangles())
			drawLine(worldRectangle);
	}
	
	private void drawLine(Pipe worldRectangle) {
		boolean isLineSelected = device.isSelected(worldRectangle);
		if (isEditorCursorHoveredOver(worldRectangle)) {
			shapeDrawer.setColor(isLineSelected ? graphicData.getColorOf("selectedLineBoxHighlight") : graphicData.getColorOf("lineBoxHighlight"));
			shapeDrawer.drawPolygon(worldRectangle.getArea());
		}
		shapeDrawer.setColor(isLineSelected ? graphicData.getColorOf("selectedLine") : graphicData.getColorOf("line"));
		shapeDrawer.drawLineSegment(worldRectangle.centerLine);
	}

}