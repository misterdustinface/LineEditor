package LineEditor.AWT.UI.uiTools;

import generic.DebounceTimer;

import java.awt.Graphics2D;

import LineEditor.data.WorldGeometryData;
import data.shapes.Pipe;

public class CircleCreatorTool extends AWTWorldEditorMouseTool{

	private int currentX;
	private int currentY;
	private DebounceTimer debounceTimer;
	
	public CircleCreatorTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		currentX = currentY = 0;
		debounceTimer = new DebounceTimer();
		debounceTimer.setDebounceTime_sec(1);
	}

	@Override
	public void setInitialPosition(int x, int y) {
		currentX = x;
		currentY = y;
	}

	@Override
	public void setCurrentPosition(int x, int y) {
		currentX = x;
		currentY = y;
	}
	
	private void createWorldCircleAtPoint(int x, int y){worldData.createPoint(x,y);}
	
	private void splitIntersectedRectangleAtMidpoint(){
		worldData.splitCollisionBox(getIntersectedRectangle(currentX, currentY), 50);
	}
	private boolean pointIntersectsSomeWorldRectangle(float x, float y){
		for(Pipe worldRectangle : worldRectangles()){
			if(worldRectangle.getArea().contains(x, y)){
				return true;
			}
		}
		return false;
	}
	private Pipe getIntersectedRectangle(float x, float y){
		for(Pipe worldRectangle : worldRectangles()){
			if(worldRectangle.getArea().contains(x, y)){
				return worldRectangle;
			}
		}
		return null; // throw exception
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return debounceTimer.isDebounceComplete();
	}

	@Override
	protected void performAction() {
		debounceTimer.reset();
		if(pointIntersectsSomeWorldRectangle(currentX, currentY)){
			splitIntersectedRectangleAtMidpoint();
		}else{
			createWorldCircleAtPoint(currentX, currentY);
		}
	}

	@Override
	public void render(Graphics2D g) {
//		cursorDrawer.setGraphics(g);
//		cursorDrawer.setColor(AWTGraphicData.cursorColor);
//		cursorDrawer.drawSmallCircleCursor(currentX, currentY);
	}

}
