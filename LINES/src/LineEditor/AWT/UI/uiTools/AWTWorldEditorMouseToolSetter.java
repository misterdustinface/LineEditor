package LineEditor.AWT.UI.uiTools;

import generic.Pair;
import LineEditor.UI.uiTools.WorldEditorMouseToolSelectCondition;
import LineEditor.UI.uiTools.WorldEditorMouseToolSelectorConditions;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;

//TODO - use a SwingVMouseDriver instead of the WorldEditorMouseToolSetter
final public class AWTWorldEditorMouseToolSetter { //extends WorldEditorMouseToolSetter {

	public AWTWorldEditorMouseToolSetter(WorldGeometryData DATA){		
		//super(DATA);
		WorldEditorMouseToolSelectorConditions.setWorldData(DATA);
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

	protected Pair<WorldEditorMouseToolSelectCondition, AWTWorldEditorMouseTool>[] clickTools;
	protected Pair<WorldEditorMouseToolSelectCondition, AWTWorldEditorMouseTool>[] pressTools;
	
	final public AWTWorldEditorMouseTool getTool(MouseUserDevice mouse) {
		if(mouse.isClicked()) {
			return selectTool(mouse, clickTools);
		} else if(mouse.isPressed()) {
			return selectTool(mouse, pressTools);
		} else {
			return defaultTool();
		}
	}
	
	private AWTWorldEditorMouseTool selectTool(MouseUserDevice mouse, Pair<WorldEditorMouseToolSelectCondition, AWTWorldEditorMouseTool>[] tools) {
		for(Pair<WorldEditorMouseToolSelectCondition, AWTWorldEditorMouseTool> pair : tools) {
			if(pair.first.shouldBeSelected(mouse)) {
				return pair.second;
			}
		}
		return defaultTool();
	}
	
	private AWTWorldEditorMouseTool defaultTool() {
		return AWTWorldEditorMouseTool.defaultMouseTool;
	}

}