package LineEditor.AWT.rendering;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import shapes.Circle;
import shapes.Pipe;
import shapes.Point;
import shapes.Shape;
import AWT.UI.AWTUILayer;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.AWT.graphicdata.LineEditorAWTGraphicData;
import LineEditor.UI.LineEditorToolLayer;
import LineEditor.data.WorldGeometryData;
import UI.input.MouseUserDevice;

public class AWTLineEditorWorldLayerRenderer implements AWTUILayer {

	private Point 					 lastMousePosition;
	private LineEditorToolLayer      lineEditor;
	private WorldGeometryData 		 worldGeometry;
	private AWTShapeDrawer 			 shapeDrawer;
	private LineEditorAWTGraphicData graphicData;

	public AWTLineEditorWorldLayerRenderer(LineEditorToolLayer lineEditor, WorldGeometryData WORLD_GEOMETRY) {
		this.lineEditor = lineEditor;
		worldGeometry = WORLD_GEOMETRY;
		shapeDrawer   = AWTShapeDrawer.getShapeDrawer();
		graphicData = LineEditorAWTGraphicData.getGraphicData();
		lastMousePosition = new Point(0,0);
	}
	
	private Circle[] getWorldCircles() { 
		return worldGeometry.getWorldPointCollisionCircles(); 
	}
	
	private Pipe[] getWorldRectangles() { 
		return worldGeometry.getWorldLineCollisionBoxes(); 
	}
	
	private boolean isEditorCursorHoveredOver(Shape s) { 
		return s.contains(lastMousePosition); 
	}
	
	public void update(MouseUserDevice mouse) {
		lastMousePosition = mouse.getCursorPosition();
	}

	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_DEFAULT);
		
		shapeDrawer.setGraphics(g);
		drawLines();
		drawPoints();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		//g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_DEFAULT);

		// g.drawString("LINES: " + worldGeometry.totalNumberOfLines(), lastMousePosition.x, 14);
	}
	
	private void drawPoints() {
		for (Circle worldCircle : getWorldCircles())
			drawPoint(worldCircle);
	}
	
	private void drawPoint(Circle worldCircle) {
		boolean isPointSelected = lineEditor.isSelected(worldCircle);
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
		boolean isLineSelected = lineEditor.isSelected(worldRectangle);
		if (isEditorCursorHoveredOver(worldRectangle)) {
			shapeDrawer.setColor(isLineSelected ? graphicData.getColorOf("selectedLineBoxHighlight") : graphicData.getColorOf("lineBoxHighlight"));
			shapeDrawer.drawPolygon(worldRectangle.getArea());
		}
		shapeDrawer.setColor(isLineSelected ? graphicData.getColorOf("selectedLine") : graphicData.getColorOf("line"));
		shapeDrawer.drawLineSegment(worldRectangle.centerLine);
	}

}