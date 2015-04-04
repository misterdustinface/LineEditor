package LineEditor.tools;

import generic.Requestible;
import LineEditor.data.WorldGeometryData;

public abstract class WorldEditorTool implements Requestible {

	protected WorldGeometryData worldData;
	
	public WorldEditorTool(WorldGeometryData WORLD_DATA) {
		worldData = WORLD_DATA;
	}
	
	final public void request() {
		if (shouldAcceptRequest())
			performAction();
	}
	
	abstract protected boolean 	shouldAcceptRequest();
	abstract protected void		performAction();
	
}