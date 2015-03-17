package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.ShapeValueEditorTool;

public class AWTShapeValueEditorTool extends ShapeValueEditorTool implements AWTUILayer {

	public AWTShapeValueEditorTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(AWTGraphicData.cursorColor);
//		cursorDrawer.drawTriangularCrosshairCursor((int)position.x, (int)position.y);
	}
}
