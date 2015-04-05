package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.CircleCreatorTool;

public class AWTCircleCreatorTool extends CircleCreatorTool implements AWTUILayer {

	public AWTCircleCreatorTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(AWTGraphicData.cursorColor);
//		cursorDrawer.drawSmallCircleCursor(currentX, currentY);
	}

}