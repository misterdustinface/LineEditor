package LineEditor.UI.uiTools;

import UI.uiTools.accessories.ScaleController;
import data.shapes.WorldGeometryData;

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
