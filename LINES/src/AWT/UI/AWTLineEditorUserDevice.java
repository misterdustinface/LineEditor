package AWT.UI;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import LineEditor.AWT.UI.uiTools.AWTWorldEditorMouseTool;
import LineEditor.AWT.UI.uiTools.AWTWorldEditorMouseToolSetter;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;
import data.shapes.Shape;

// TODO - use a SwingVMouseDriver instead of the WorldEditorMouseToolSetter
public class AWTLineEditorUserDevice extends AWTDefaultMouseUserDevice implements AWTUILayer {
	
	private WorldGeometryData 				data;
	private AWTWorldEditorMouseTool 		currentTool;
	private AWTWorldEditorMouseToolSetter 	toolSetter;
	
	public AWTLineEditorUserDevice(WorldGeometryData DATA){
		super();
		data 		= DATA;
		toolSetter 	= new AWTWorldEditorMouseToolSetter(DATA);
		currentTool = AWTWorldEditorMouseTool.defaultMouseTool;
	}
	
	public boolean isSelected(Shape s) { return data.isSelected(s); }

	@Override
	public void update(MouseUserDevice mouse) {
		if(mouse.isClicked() || mouse.isPressed())
			currentTool = toolSetter.getTool(mouse);

		currentTool.update(mouse);

		if(mouse.isClicked() || mouse.isReleased())
			currentTool = AWTWorldEditorMouseTool.defaultMouseTool;
	}

	@Override
	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);		
		g.drawString("LINES: " + data.totalNumberOfLines(), getCursorX(), 14);
		currentTool.render(g);
	}
}