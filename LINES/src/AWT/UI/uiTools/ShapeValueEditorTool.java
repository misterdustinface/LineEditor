package AWT.UI.uiTools;

import java.awt.Graphics2D;

import data.shapes.Point;
import data.shapes.Shape;
import data.shapes.WorldGeometryData;

public class ShapeValueEditorTool extends AWTWorldEditorMouseTool{

	private Point position;
	
	public ShapeValueEditorTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		position = new Point(0,0);
	}

	@Override
	public void setInitialPosition(int x, int y) {
		position.set(x, y);
	}

	@Override
	public void setCurrentPosition(int x, int y) {
		position.set(x, y);
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
