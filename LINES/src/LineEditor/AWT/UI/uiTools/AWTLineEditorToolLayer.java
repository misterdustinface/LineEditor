package LineEditor.AWT.UI.uiTools;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import AWT.UI.AWTUILayer;
import LineEditor.UI.LineEditorToolLayer;
import LineEditor.data.WorldGeometryData;
import LineEditor.tools.mouse.WorldEditorMouseTool;
import LineEditor.tools.mouse.WorldEditorMouseToolSetter;

public class AWTLineEditorToolLayer extends LineEditorToolLayer implements AWTUILayer {
	
	public AWTLineEditorToolLayer(WorldGeometryData DATA){
		super(DATA);
	}

	public void render(Graphics2D g) {
		renderCursor(g);
	}

	protected WorldEditorMouseTool newDefaultToolSubclass(WorldGeometryData DATA) {
		return new AWTDefaultMouseTool(DATA);
	}

	protected WorldEditorMouseToolSetter newMouseToolSetterSubclass(WorldGeometryData DATA) {
		return new AWTWorldEditorMouseToolSetter(DATA);
	}
	
	private void renderCursor(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
		g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_SPEED);

		((AWTUILayer)currentTool).render(g);
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, 	RenderingHints.VALUE_RENDER_DEFAULT);
	}

}