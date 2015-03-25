package LineEditor.tools.mouse;

import LineEditor.data.WorldGeometryData;

public class DefaultMouseTool extends WorldEditorMouseTool {

	public DefaultMouseTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	protected boolean shouldAcceptRequest() {
		return false;
	}

	protected void performAction() {

	}

}