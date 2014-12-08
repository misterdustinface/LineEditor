package LineEditor.UI.uiTools;

import generic.Pair;
import LineEditor.AWT.UI.uiTools.WorldEditorMouseToolSelectorConditions;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;

public class WorldEditorMouseToolSetter {

	public WorldEditorMouseToolSetter(WorldGeometryData DATA) {
		WorldEditorMouseToolSelectorConditions.setWorldData(DATA);
	}
	
	protected Pair<WorldEditorMouseToolSelectCondition, WorldEditorMouseTool>[] clickTools;
	protected Pair<WorldEditorMouseToolSelectCondition, WorldEditorMouseTool>[] pressTools;
	
	final public WorldEditorMouseTool getTool(MouseUserDevice mouse) {
		if(mouse.isClicked()) {
			return selectTool(mouse, clickTools);
		} else if(mouse.isPressed()) {
			return selectTool(mouse, pressTools);
		} else {
			return defaultTool();
		}
	}
	
	private WorldEditorMouseTool selectTool(MouseUserDevice mouse, Pair<WorldEditorMouseToolSelectCondition, WorldEditorMouseTool>[] tools) {
		for(Pair<WorldEditorMouseToolSelectCondition, WorldEditorMouseTool> pair : tools) {
			if(pair.first.shouldBeSelected(mouse)) {
				return pair.second;
			}
		}
		return defaultTool();
	}
	
	private WorldEditorMouseTool defaultTool() {
		return WorldEditorMouseTool.defaultMouseTool;
	}
}
