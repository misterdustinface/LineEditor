package LineEditor.tools.mouse;

import java.util.HashSet;

import shapes.LineSegment;
import shapes.Pipe;
import LineEditor.data.WorldGeometryData;

public class CopyTool extends SelectionBoxTool {
	
	private HashSet<LineSegment>		copiedLines;
	//private HashSet<Polyline> 	copiedPolylines;
	
	public CopyTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		copiedLines		= new HashSet<LineSegment>();
		//copiedPolylines = new HashSet<Polyline>();
	}

	public void setInitialPosition(int x, int y) {
		super.setInitialPosition(x, y);
	}

	public void setCurrentPosition(int x, int y) {
		super.setCurrentPosition(x, y);
	}

	protected boolean shouldAcceptRequest() {
		return copiedLines.size() > 0;
	}

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