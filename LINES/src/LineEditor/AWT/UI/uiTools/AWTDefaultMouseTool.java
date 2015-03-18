package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import AWT.rendering.AWTCursorDrawer;
import LineEditor.AWT.graphicdata.LineEditorAWTGraphicData;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.DefaultMouseTool;

public class AWTDefaultMouseTool extends DefaultMouseTool implements AWTUILayer {

	private AWTCursorDrawer cursorDrawer;
	private LineEditorAWTGraphicData graphicData;
	
	public AWTDefaultMouseTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		cursorDrawer = AWTCursorDrawer.getCursorDrawer();
		graphicData = LineEditorAWTGraphicData.getGraphicData();
	}

	@Override
	public void render(Graphics2D g) {
		cursorDrawer.setGraphics(g);
		cursorDrawer.setColor(graphicData.getColorOf("cursor"));
		cursorDrawer.drawCrosshairCursor((int)position.x, (int)position.y);
	}

}
