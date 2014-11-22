package UI.AWTuiTools;

import generic.Pair;

import java.awt.event.MouseEvent;

import UI.AWTuiTools.FunctionPointers.*;
import data.shapes.WorldGeometryData;

//TODO - use a SwingVMouseDriver instead of the WorldEditorMouseToolSetter
final public class AWTWorldEditorMouseToolSetter {

	private Pair<AWTWorldEditorMouseToolSelectCondition, AWTWorldEditorMouseTool>[] clickTools;
	private Pair<AWTWorldEditorMouseToolSelectCondition, AWTWorldEditorMouseTool>[] pressTools;
	
	public AWTWorldEditorMouseToolSetter(WorldGeometryData DATA){		
		AWTWorldEditorMouseToolSelectorConditions.setWorldData(DATA);
		
		clickTools = new Pair[] {
				new Pair(AWTWorldEditorMouseToolSelectorConditions.deleteToolSelectCondition,		new ShapeDeleteTool(DATA))		,
				new Pair(AWTWorldEditorMouseToolSelectorConditions.pointCreateToolSelectCondition,	new CircleCreatorTool(DATA))	,
				new Pair(AWTWorldEditorMouseToolSelectorConditions.valueEditorToolSelectCondition,	new ShapeValueEditorTool(DATA))	,
				new Pair(AWTWorldEditorMouseToolSelectorConditions.selectPointToolSelectCondition, 	new SelectionPointTool(DATA))
		};
		
		pressTools = new Pair[] {
				new Pair(AWTWorldEditorMouseToolSelectorConditions.moveCircleToolSelectCondition,	new CircleMoverTool(DATA))		,
				new Pair(AWTWorldEditorMouseToolSelectorConditions.tracerLineToolSelectCondition,	new TracerLineTool(DATA))		,
				new Pair(AWTWorldEditorMouseToolSelectorConditions.selectionBoxToolSelectCondition,	new SelectionBoxTool(DATA))
		};
	}

	public AWTWorldEditorMouseTool mouseClicked(MouseEvent arg0) {
		return selectTool(arg0, clickTools);
	}

	public AWTWorldEditorMouseTool mousePressed(MouseEvent arg0) {
		return selectTool(arg0, pressTools);
	}
	
	private AWTWorldEditorMouseTool selectTool(MouseEvent arg0, Pair<AWTWorldEditorMouseToolSelectCondition, AWTWorldEditorMouseTool>[] tools) {
		for(Pair<AWTWorldEditorMouseToolSelectCondition, AWTWorldEditorMouseTool> pair : tools) {
			if(pair.first.shouldBeSelected(arg0)) {
				return pair.second;
			}
		}
		return AWTWorldEditorMouseTool.defaultMouseTool;
	}

}