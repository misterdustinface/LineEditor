package LineEditor.AWT.UI.uiTools;

import java.util.HashSet;

import LineEditor.data.WorldGeometryData;
import data.shapes.LineSegment;
import data.shapes.Pipe;

public class AWTCopyTool extends AWTSelectionBoxTool{


	private HashSet<LineSegment>		copiedLines;
	//private HashSet<Polyline> 	copiedPolylines;
	
	public AWTCopyTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		copiedLines		= new HashSet<LineSegment>();
		//copiedPolylines = new HashSet<Polyline>();
	}

	@Override
	public void setInitialPosition(int x, int y) {
		super.setInitialPosition(x, y);
	}

	@Override
	public void setCurrentPosition(int x, int y) {
		super.setCurrentPosition(x, y);
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return copiedLines.size() > 0;
	}

	@Override
	protected void performAction() {
		copyWorldShapes();
	}

	private void copyWorldShapes(){
		setWorldShapesToCopy();

		copyShapesIntoWorldData();
	}

	private void setWorldShapesToCopy(){
		for(Pipe worldRectangle : worldRectangles()){
			if(selectionRectangleContainsRectangle(selectionBox, worldRectangle)){
				copiedLines.add(worldRectangle.centerLine);
			}
		}
	}
	private void copyShapesIntoWorldData(){
		// make copies
		// move copies
	}
}
