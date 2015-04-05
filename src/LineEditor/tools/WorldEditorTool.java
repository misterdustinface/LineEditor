package LineEditor.tools;

import base.Requestible;
import LineEditor.data.WorldGeometryData;

public abstract class WorldEditorTool extends Requestible {

	protected WorldGeometryData worldData;
	
	public WorldEditorTool(WorldGeometryData WORLD_DATA) {
		worldData = WORLD_DATA;
	}
	
}