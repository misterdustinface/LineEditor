package LineEditor.tools.mouse;

import generic.structures.Pair;
import LineEditor.data.WorldGeometryData;
import UI.input.MouseUserDevice;

public class WorldEditorMouseToolSetter {

	protected Pair<WorldEditorMouseToolSelectCondition, WorldEditorMouseTool>[] clickTools;
	protected Pair<WorldEditorMouseToolSelectCondition, WorldEditorMouseTool>[] pressTools;
	private WorldEditorMouseTool defaultTool;
	
	public WorldEditorMouseToolSetter(WorldGeometryData DATA) {
		WorldEditorMouseToolSelectorConditions.setWorldData(DATA);
		clickTools = null;
		pressTools = null;
	}
	
	final public void setDefaultTool(WorldEditorMouseTool DEFAULT_TOOL) {
		defaultTool = DEFAULT_TOOL;
	}
	
	final public WorldEditorMouseTool getTool(MouseUserDevice mouse) {
		if (mouse.isClicked()) {
			return selectTool(mouse, clickTools);
		} else if (mouse.isPressed()) {
			return selectTool(mouse, pressTools);
		} else {
			return defaultTool;
		}
	}
	
	private WorldEditorMouseTool selectTool(MouseUserDevice mouse, Pair<WorldEditorMouseToolSelectCondition, WorldEditorMouseTool>[] tools) {
		for (Pair<WorldEditorMouseToolSelectCondition, WorldEditorMouseTool> pair : tools) {
			if (pair.first.shouldBeSelected(mouse)) {
				return pair.second;
			}
		}
		return defaultTool;
	}
}
