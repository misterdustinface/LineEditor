package LineEditor.AWT.UI.uiTools.FunctionPointers;

import java.awt.event.MouseEvent;

import data.shapes.WorldGeometryData;

public abstract class AWTWorldEditorMouseToolSelectorConditions {
	
	private static WorldGeometryData WORLD_DATA;
	
	public static void setWorldData(WorldGeometryData worldData) {
		WORLD_DATA = worldData;
	}
	
	public static AWTWorldEditorMouseToolSelectCondition deleteToolSelectCondition = new AWTWorldEditorMouseToolSelectCondition(){
		@Override
		public boolean shouldBeSelected(MouseEvent arg0) {
			return arg0.isShiftDown()
				&& arg0.getButton() == MouseEvent.BUTTON1;
		}
	};
	public static AWTWorldEditorMouseToolSelectCondition pointCreateToolSelectCondition = new AWTWorldEditorMouseToolSelectCondition(){
		@Override
		public boolean shouldBeSelected(MouseEvent arg0) {
			return !arg0.isControlDown()
				&& arg0.getButton() == MouseEvent.BUTTON1;
		}
	};
	public static AWTWorldEditorMouseToolSelectCondition valueEditorToolSelectCondition = new AWTWorldEditorMouseToolSelectCondition(){
		@Override
		public boolean shouldBeSelected(MouseEvent arg0) {
			return arg0.isControlDown()
				&& arg0.getButton() == MouseEvent.BUTTON3;
		}
	};
	public static AWTWorldEditorMouseToolSelectCondition selectPointToolSelectCondition = new AWTWorldEditorMouseToolSelectCondition(){
		@Override
		public boolean shouldBeSelected(MouseEvent arg0) {
			return arg0.getButton() == MouseEvent.BUTTON3;
		}
	};
	public static AWTWorldEditorMouseToolSelectCondition moveCircleToolSelectCondition = new AWTWorldEditorMouseToolSelectCondition(){
		@Override
		public boolean shouldBeSelected(MouseEvent arg0) {
			return !arg0.isControlDown() 
				&& arg0.getButton() == MouseEvent.BUTTON1 
				&& WORLD_DATA.isCircleAtPositionSelected(arg0.getX(), arg0.getY());
		}
	};
	public static AWTWorldEditorMouseToolSelectCondition tracerLineToolSelectCondition = new AWTWorldEditorMouseToolSelectCondition(){
		@Override
		public boolean shouldBeSelected(MouseEvent arg0) {
			return !arg0.isControlDown() 
				&& !arg0.isShiftDown()
				&& arg0.getButton() == MouseEvent.BUTTON1;
		}
	};
	public static AWTWorldEditorMouseToolSelectCondition selectionBoxToolSelectCondition = new AWTWorldEditorMouseToolSelectCondition(){
		@Override
		public boolean shouldBeSelected(MouseEvent arg0) {
			return !arg0.isControlDown() 
				&& arg0.getButton() == MouseEvent.BUTTON3;
		}
	};
}
