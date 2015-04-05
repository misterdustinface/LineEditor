package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import AWT.rendering.AWTCursorDrawer;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.AWT.graphicdata.LineEditorAWTGraphicData;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.TracerLineTool;

public class AWTTracerLineTool extends TracerLineTool implements AWTUILayer {

	private AWTCursorDrawer cursorDrawer;
	private AWTShapeDrawer  shapeDrawer;
	private LineEditorAWTGraphicData graphicData;
	
	public AWTTracerLineTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		
		cursorDrawer = AWTCursorDrawer.getCursorDrawer();
		shapeDrawer = AWTShapeDrawer.getShapeDrawer();
		graphicData = LineEditorAWTGraphicData.getGraphicData();
	}

	public void render(Graphics2D g) {
		if (shouldAcceptRequest()) {
			shapeDrawer.setGraphics(g);
			shapeDrawer.setColor(graphicData.getColorOf("dragLineTracingLine"));
			shapeDrawer.drawLineSegment(tracerLine);
			
			cursorDrawer.setGraphics(g);
			cursorDrawer.setColor(graphicData.getColorOf("cursor"));
			cursorDrawer.setCursor("Triangle");
			cursorDrawer.drawCursor((int)position.x, (int)position.y);
		} else {
			cursorDrawer.setGraphics(g);
			cursorDrawer.setColor(graphicData.getColorOf("cursor"));
			cursorDrawer.setCursor("SmallX");
			cursorDrawer.drawCursor((int)position.x, (int)position.y);
		}
	}

}