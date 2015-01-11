package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import shapes.Shape;
import AWT.UI.AWTUILayer;
import AWT.UI.Mouse.AWTDefaultMouseUserDevice;
import LineEditor.UI.uiTools.WorldEditorMouseTool;
import LineEditor.data.WorldGeometryData;
import UI.MouseUserDevice;

public class AWTLineEditorUserDevice extends AWTDefaultMouseUserDevice implements AWTUILayer {
	
	private WorldGeometryData 				data;
	private WorldEditorMouseTool 			currentTool;
	private AWTWorldEditorMouseToolSetter 	toolSetter;
	private WorldEditorMouseTool			defaultTool;
	
	public AWTLineEditorUserDevice(WorldGeometryData DATA){
		super();
		data 		= DATA;
		defaultTool = new AWTDefaultMouseTool(DATA);
		toolSetter 	= new AWTWorldEditorMouseToolSetter(DATA);
		toolSetter.setDefaultTool(defaultTool);
		currentTool = defaultTool;
	}
	
	public boolean isSelected(Shape s) { return data.isSelected(s); }

	@Override
	public void update(MouseUserDevice mouse) {
		if(mouse.isClicked() || mouse.isPressed())
			currentTool = toolSetter.getTool(mouse);
		
		currentTool.update(mouse);

		if(mouse.isClicked() || mouse.isReleased())
			currentTool = defaultTool;
	}

	@Override
	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
		g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_SPEED);
		
		g.drawString("LINES: " + data.totalNumberOfLines(), getCursorX(), 14);
		((AWTUILayer)currentTool).render(g);
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_DEFAULT);
	}
}