package LineEditor.UI.uiTools;

import generic.Pair;
import LineEditor.AWT.UI.uiTools.AWTCircleCreatorTool;
import LineEditor.AWT.UI.uiTools.AWTCircleMoverTool;
import LineEditor.AWT.UI.uiTools.AWTSelectionBoxTool;
import LineEditor.AWT.UI.uiTools.AWTSelectionPointTool;
import LineEditor.AWT.UI.uiTools.AWTShapeDeleteTool;
import LineEditor.AWT.UI.uiTools.AWTShapeValueEditorTool;
import LineEditor.AWT.UI.uiTools.AWTTracerLineTool;
import LineEditor.AWT.UI.uiTools.WorldEditorMouseToolSelectorConditions;
import LineEditor.data.WorldGeometryData;

final public class AWTWorldEditorMouseToolSetter extends WorldEditorMouseToolSetter {

	public AWTWorldEditorMouseToolSetter(WorldGeometryData DATA){		
		super(DATA);
		clickTools = new Pair[] {
				new Pair(WorldEditorMouseToolSelectorConditions.deleteToolSelectCondition,		new AWTShapeDeleteTool(DATA))		,
				new Pair(WorldEditorMouseToolSelectorConditions.pointCreateToolSelectCondition,	new AWTCircleCreatorTool(DATA))	,
				new Pair(WorldEditorMouseToolSelectorConditions.valueEditorToolSelectCondition,	new AWTShapeValueEditorTool(DATA))	,
				new Pair(WorldEditorMouseToolSelectorConditions.selectPointToolSelectCondition, new AWTSelectionPointTool(DATA))
		};
		
		pressTools = new Pair[] {
				new Pair(WorldEditorMouseToolSelectorConditions.moveCircleToolSelectCondition,	new AWTCircleMoverTool(DATA))		,
				new Pair(WorldEditorMouseToolSelectorConditions.tracerLineToolSelectCondition,	new AWTTracerLineTool(DATA))		,
				new Pair(WorldEditorMouseToolSelectorConditions.selectionBoxToolSelectCondition,new AWTSelectionBoxTool(DATA))
		};
	}
}