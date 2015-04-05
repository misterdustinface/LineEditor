package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import AWT.rendering.AWTCursorDrawer;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.AWT.graphicdata.LineEditorAWTGraphicData;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.CircleMoverTool;

public class AWTCircleMoverTool extends CircleMoverTool implements AWTUILayer {

	private AWTShapeDrawer  shapeDrawer;
	private AWTCursorDrawer cursorDrawer;
	private LineEditorAWTGraphicData graphicData;
	
	public AWTCircleMoverTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		shapeDrawer  = AWTShapeDrawer.getShapeDrawer();
		cursorDrawer = AWTCursorDrawer.getCursorDrawer();
		graphicData = LineEditorAWTGraphicData.getGraphicData();
	}
	
	public void render(Graphics2D g) {
		shapeDrawer.setGraphics(g);
		shapeDrawer.setColor(graphicData.getColorOf("movePointHighlight"));
		shapeDrawer.drawCircle(circleToMove);

		cursorDrawer.setGraphics(g);
		cursorDrawer.setColor(graphicData.getColorOf("cursor"));
		cursorDrawer.setCursor("Crosshair");
		cursorDrawer.drawCursor((int)position.x, (int)position.y);
	}

}