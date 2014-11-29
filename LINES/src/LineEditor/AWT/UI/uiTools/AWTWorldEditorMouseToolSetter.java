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