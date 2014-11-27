package LineEditor.UI.uiTools;

import generic.Pair;
import LineEditor.UI.uiTools.WorldEditorMouseToolSelectCondition;
import LineEditor.UI.uiTools.WorldEditorMouseToolSelectorConditions;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;

//TODO - use a SwingVMouseDriver instead of the WorldEditorMouseToolSetter
abstract public class WorldEditorMouseToolSetter {

	protected Pair<WorldEditorMouseToolSelectCondition, MouseUserDevice>[] clickTools;
	protected Pair<WorldEditorMouseToolSelectCondition, MouseUserDevice>[] pressTools;
	
	public WorldEditorMouseToolSetter(WorldGeometryData DATA){		
		WorldEditorMouseToolSelectorConditions.setWorldData(DATA);
		clickTools = new Pair[] {};
		pressTools = new Pair[] {};
	}
	
	final public MouseUserDevice getTool(MouseUserDevice mouse) {
		if(mouse.isClicked()) {
			return selectTool(mouse, clickTools);
		} else if(mouse.isPressed()) {
			return selectTool(mouse, pressTools);
		} else {
			return defaultTool();
		}
	}
	
	private MouseUserDevice selectTool(MouseUserDevice mouse, Pair<WorldEditorMouseToolSelectCondition, MouseUserDevice>[] tools) {
		for(Pair<WorldEditorMouseToolSelectCondition, MouseUserDevice> pair : tools) {
			if(pair.first.shouldBeSelected(mouse)) {
				return pair.second;
			}
		}
		return defaultTool();
	}
	
	abstract protected MouseUserDevice defaultTool();

}