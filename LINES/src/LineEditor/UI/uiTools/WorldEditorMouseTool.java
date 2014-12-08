package LineEditor.UI.uiTools;

import data.shapes.Point;
import LineEditor.data.WorldGeometryData;

public abstract class WorldEditorMouseTool extends WorldEditorTool {

	protected Point position;
	
	public WorldEditorMouseTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
		position = new Point(0,0);
	}
	
	public void setInitialPosition(int x, int y) { position.set(x, y); }
	public void setCurrentPosition(int x, int y) { position.set(x, y); }

}
