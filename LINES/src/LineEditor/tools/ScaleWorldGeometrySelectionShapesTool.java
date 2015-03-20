package LineEditor.tools;

import LineEditor.data.WorldGeometryData;
import UI.uiTools.accessories.ScaleController;

public class ScaleWorldGeometrySelectionShapesTool extends WorldEditorTool{
	
	private ScaleController scaleController;
	
	ScaleWorldGeometrySelectionShapesTool(WorldGeometryData WORLD_DATA, ScaleController CONTROLLER) {
		super(WORLD_DATA);
		scaleController = CONTROLLER;
	}

	@Override
	protected boolean shouldAcceptRequest() {
		return scaleController.getScale() != 1.0f;
	}

	@Override
	protected void performAction() {
		worldData.scaleSelectionAreaSizeForWorldGeometry(scaleController.getScale());
	}

}