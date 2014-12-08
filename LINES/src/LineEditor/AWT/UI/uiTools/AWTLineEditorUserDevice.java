package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import AWT.UI.AWTDefaultMouseUserDevice;
import AWT.UI.AWTUILayer;
import LineEditor.UI.uiTools.WorldEditorMouseTool;
import LineEditor.UI.uiTools.AWTWorldEditorMouseToolSetter;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;
import data.shapes.Shape;

// TODO - use a SwingVMouseDriver instead of the WorldEditorMouseToolSetter
public class AWTLineEditorUserDevice extends AWTDefaultMouseUserDevice implements AWTUILayer {
	
	private WorldGeometryData 				data;
	private WorldEditorMouseTool 			currentTool;
	private AWTWorldEditorMouseToolSetter 	toolSetter;
	
	public AWTLineEditorUserDevice(WorldGeometryData DATA){
		super();
		data 		= DATA;
		toolSetter 	= new AWTWorldEditorMouseToolSetter(DATA);
		currentTool = WorldEditorMouseTool.defaultMouseTool;
	}
	
	public boolean isSelected(Shape s) { return data.isSelected(s); }

	@Override
	public void update(MouseUserDevice mouse) {
		if(mouse.isClicked() || mouse.isPressed())
			currentTool = toolSetter.getTool(mouse);

		currentTool.update(mouse);

		if(mouse.isClicked() || mouse.isReleased())
			currentTool = WorldEditorMouseTool.defaultMouseTool;
	}

	@Override
	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
		g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_SPEED);
		
		g.drawString("LINES: " + data.totalNumberOfLines(), getCursorX(), 14);
		//((AWTUILayer)currentTool).render(g);
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_DEFAULT);
	}
}