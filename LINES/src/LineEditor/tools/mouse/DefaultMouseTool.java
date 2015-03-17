package LineEditor.tools.mouse;

import LineEditor.data.WorldGeometryData;

public class DefaultMouseTool extends WorldEditorMouseTool {

	public DefaultMouseTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return false;
	}

	@Override
	protected void performAction() {

	}

}
