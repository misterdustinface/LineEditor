package UI.AWT;

import java.awt.event.MouseEvent;

import UI.AWTuiTools.AWTWorldEditorMouseTool;
import UI.AWTuiTools.AWTWorldEditorMouseToolSetter;
import data.shapes.WorldGeometryData;

// TODO - use a SwingVMouseDriver instead of the WorldEditorMouseToolSetter
public class AWTLineEditorUserDevice extends AWTMouseUserDevice{
	
	private WorldGeometryData 				data;
	private AWTWorldEditorMouseToolSetter 	toolSetter;
	private AWTWorldEditorMouseTool 		currentTool;
	
	public AWTLineEditorUserDevice(WorldGeometryData DATA){
		super();
		data 			= DATA;
		toolSetter 		= new AWTWorldEditorMouseToolSetter(DATA);
		currentTool 	= AWTWorldEditorMouseTool.defaultMouseTool;
	}
	
	public WorldGeometryData getData(){
		return data;
	}
	public AWTWorldEditorMouseTool getCurrentMouseTool(){
		return currentTool;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	@Override
	public void mouseDragged(MouseEvent arg0) {
		super.mouseDragged(arg0);
		cursorPosition.set(arg0.getX(), arg0.getY());
		currentTool.mouseDragged(arg0);
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		super.mouseMoved(arg0);
		cursorPosition.set(arg0.getX(), arg0.getY());
		currentTool.mouseMoved(arg0);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		super.mouseClicked(arg0);
		currentTool = toolSetter.mouseClicked(arg0);
		currentTool.mouseClicked(arg0);
		currentTool = AWTWorldEditorMouseTool.defaultMouseTool;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		currentTool = toolSetter.mousePressed(arg0);
		currentTool.mousePressed(arg0);
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		super.mouseReleased(arg0);
		currentTool.mouseReleased(arg0);
		currentTool = AWTWorldEditorMouseTool.defaultMouseTool;
		currentTool.setCurrentPosition(arg0.getX(), arg0.getY());
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
}