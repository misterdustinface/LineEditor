package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;

import shapes.Point;
import shapes.Shape;
import AWT.UI.AWTUILayer;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.WorldEditorMouseTool;

public class AWTShapeValueEditorTool extends WorldEditorMouseTool implements AWTUILayer {

	public AWTShapeValueEditorTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	private void selectAndOpenValueEditorWindowForFirstMatchingIntersection(Point point){
		
		for(Shape bound : collisionBounds()) {
			if(bound.contains(point)) {
				toggleSelected(bound);
				if(isSelected(bound)) {
					openEditWindow(bound);
					return;
		}}}
		
	}

	private void openEditWindow(Shape s) {
		//windowManager.addWindow(s.ID(), s.detailedEditorFrame(worldData));
		System.out.println("NO EDITOR WINDOWS EXIST");
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return true;
	}

	@Override
	protected void performAction() {
		selectAndOpenValueEditorWindowForFirstMatchingIntersection(position);
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(AWTGraphicData.cursorColor);
//		cursorDrawer.drawTriangularCrosshairCursor((int)position.x, (int)position.y);
	}
}
