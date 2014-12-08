package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import LineEditor.UI.uiTools.CircleCreatorTool;
import LineEditor.data.WorldGeometryData;

public class AWTCircleCreatorTool extends CircleCreatorTool implements AWTUILayer {

	public AWTCircleCreatorTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(AWTGraphicData.cursorColor);
//		cursorDrawer.drawSmallCircleCursor(currentX, currentY);
	}

}
