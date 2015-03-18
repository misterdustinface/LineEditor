package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.SelectionPointTool;

public class AWTSelectionPointTool extends SelectionPointTool implements AWTUILayer {
	
	public AWTSelectionPointTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(AWTGraphicData.selectedPointCircleHighlight);
//		cursorDrawer.drawTriangularCrosshairCursor((int)position.x, (int)position.y);
	}

}
