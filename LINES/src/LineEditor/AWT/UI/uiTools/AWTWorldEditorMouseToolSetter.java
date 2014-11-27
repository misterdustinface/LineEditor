package LineEditor.AWT.UI.uiTools;

import generic.Pair;
import LineEditor.UI.uiTools.WorldEditorMouseToolSelectorConditions;
import LineEditor.UI.uiTools.WorldEditorMouseToolSetter;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;

//TODO - use a SwingVMouseDriver instead of the WorldEditorMouseToolSetter
final public class AWTWorldEditorMouseToolSetter extends WorldEditorMouseToolSetter {

	public AWTWorldEditorMouseToolSetter(WorldGeometryData DATA){		
		super(DATA);
		clickTools = new Pair[] {
				new Pair(WorldEditorMouseToolSelectorConditions.deleteToolSelectCondition,		new ShapeDeleteTool(DATA))		,
				new Pair(WorldEditorMouseToolSelectorConditions.pointCreateToolSelectCondition,	new CircleCreatorTool(DATA))	,
				new Pair(WorldEditorMouseToolSelectorConditions.valueEditorToolSelectCondition,	new ShapeValueEditorTool(DATA))	,
				new Pair(WorldEditorMouseToolSelectorConditions.selectPointToolSelectCondition, new SelectionPointTool(DATA))
		};
		
		pressTools = new Pair[] {
				new Pair(WorldEditorMouseToolSelectorConditions.moveCircleToolSelectCondition,	new CircleMoverTool(DATA))		,
				new Pair(WorldEditorMouseToolSelectorConditions.tracerLineToolSelectCondition,	new TracerLineTool(DATA))		,
				new Pair(WorldEditorMouseToolSelectorConditions.selectionBoxToolSelectCondition,new SelectionBoxTool(DATA))
		};
	}

	@Override
	protected MouseUserDevice defaultTool() {
		return AWTWorldEditorMouseTool.defaultMouseTool;
	}

}