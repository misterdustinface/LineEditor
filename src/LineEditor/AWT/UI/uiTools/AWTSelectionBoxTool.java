package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import AWT.UI.AWTUILayer;
import AWT.rendering.AWTCursorDrawer;
import AWT.rendering.AWTShapeDrawer;
import LineEditor.AWT.graphicdata.LineEditorAWTGraphicData;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.SelectionBoxTool;

public class AWTSelectionBoxTool extends SelectionBoxTool implements AWTUILayer {

	private AWTCursorDrawer cursorDrawer;
	private AWTShapeDrawer  shapeDrawer;
	private LineEditorAWTGraphicData graphicData;
	
	public AWTSelectionBoxTool(WorldGeometryData WORLD_DATA){
		super(WORLD_DATA);
		cursorDrawer = AWTCursorDrawer.getCursorDrawer();
		shapeDrawer  = AWTShapeDrawer.getShapeDrawer();
		graphicData = LineEditorAWTGraphicData.getGraphicData();
	}
	
	public void render(Graphics2D g) {
		shapeDrawer.setGraphics(g);
		shapeDrawer.setColor(graphicData.getColorOf("dragSelectionBoxTransparentArea"));
				
		shapeDrawer.drawFilledRectangle(selectionBox.getBoundingRectangle());
		shapeDrawer.setColor(graphicData.getColorOf("dragSelectionBoxBorder"));
		shapeDrawer.drawRectangle(selectionBox.getBoundingRectangle());

		cursorDrawer.setGraphics(g);
		shapeDrawer.setColor(graphicData.getColorOf("cursor"));
		cursorDrawer.setCursor("Crosshair");
		cursorDrawer.drawCursor((int)position.x, (int)position.y);
	}

}