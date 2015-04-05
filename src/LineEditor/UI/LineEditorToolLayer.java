package LineEditor.UI;

import shapes.Shape;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.WorldEditorMouseTool;
import LineEditor.tools.mouse.WorldEditorMouseToolSetter;
import UI.UILayer;
import UI.input.MouseUserDevice;

public abstract class LineEditorToolLayer implements UILayer {

	protected WorldGeometryData  			data;
	protected WorldEditorMouseTool 	    	currentTool;
	protected WorldEditorMouseTool			defaultTool;
	protected WorldEditorMouseToolSetter 	toolSetter;
	
	public LineEditorToolLayer(WorldGeometryData DATA) {
		super();
		data = DATA;
		defaultTool = newDefaultToolSubclass(DATA);
		toolSetter = newMouseToolSetterSubclass(DATA);
		toolSetter.setDefaultTool(defaultTool);
		currentTool = defaultTool;
	}
	
	public boolean isSelected(Shape s) { 
		return data.isSelected(s); 
	}
	
	public void update(MouseUserDevice mouse) {
		if (mouse.isClicked() || mouse.isPressed())
			currentTool = toolSetter.getTool(mouse);
		
		currentTool.update(mouse);

		if (mouse.isClicked() || mouse.isReleased())
			currentTool = defaultTool;
	}
	
	protected abstract WorldEditorMouseTool newDefaultToolSubclass(WorldGeometryData DATA);
	protected abstract WorldEditorMouseToolSetter newMouseToolSetterSubclass(WorldGeometryData DATA);
}
