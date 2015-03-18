package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.ShapeDeleteTool;

public class AWTShapeDeleteTool extends ShapeDeleteTool implements AWTUILayer {

	public AWTShapeDeleteTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(Color.RED);
//		cursorDrawer.drawLargeXCursor((int)position.x, (int)position.y);
	}
}
