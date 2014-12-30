package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import AWT.graphicdata.AWTGraphicData;
import AWT.rendering.AWTCursorDrawer;
import LineEditor.UI.uiTools.DefaultMouseTool;
import LineEditor.data.WorldGeometryData;

public class AWTDefaultMouseTool extends DefaultMouseTool implements AWTUILayer {

	private AWTCursorDrawer cursorDrawer;
	
	public AWTDefaultMouseTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		cursorDrawer = AWTCursorDrawer.getCursorDrawer();
	}

	@Override
	public void render(Graphics2D g) {
		cursorDrawer.setGraphics(g);
		cursorDrawer.setColor(AWTGraphicData.cursorColor);
		cursorDrawer.drawCrosshairCursor((int)position.x, (int)position.y);
	}

}
