package LineEditor.AWT.UI.uiTools;

import generic.structures.Pair;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.WorldEditorMouseToolSelectorConditions;
import LineEditor.tools.mouse.WorldEditorMouseToolSetter;

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