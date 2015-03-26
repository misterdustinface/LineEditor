package LineEditor.tools.mouse;

import LineEditor.data.WorldGeometryData;
import UI.input.MouseUserDevice;

public abstract class WorldEditorMouseToolSelectorConditions {
	
	private static WorldGeometryData WORLD_DATA;
	
	public static void setWorldData(WorldGeometryData worldData) {
		WORLD_DATA = worldData;
	}
	
	public static WorldEditorMouseToolSelectCondition deleteToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			return mouse.isButton("MIDDLE");
			//		arg0.isShiftDown()
			//	&& arg0.getButton() == MouseEvent.BUTTON1;
		}
	};
	
	public static WorldEditorMouseToolSelectCondition pointCreateToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			return mouse.isButton("LEFT");
			//		!arg0.isControlDown()
			//	&& arg0.getButton() == MouseEvent.BUTTON1;
		}
	};
	
	public static WorldEditorMouseToolSelectCondition valueEditorToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			return false;
			//		arg0.isControlDown()
			//	&& arg0.getButton() == MouseEvent.BUTTON3;
		}
	};
	
	public static WorldEditorMouseToolSelectCondition selectPointToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			return mouse.isButton("RIGHT");
			//		arg0.getButton() == MouseEvent.BUTTON3;
		}
	};
	
	public static WorldEditorMouseToolSelectCondition moveCircleToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			return mouse.isButton("LEFT")
				&& WORLD_DATA.isCircleAtPositionSelected(mouse.getCursorX(), mouse.getCursorY());
			//		!arg0.isControlDown() 
			//	&& arg0.getButton() == MouseEvent.BUTTON1 
			//	&& WORLD_DATA.isCircleAtPositionSelected(arg0.getX(), arg0.getY());
		}
	};
	
	public static WorldEditorMouseToolSelectCondition tracerLineToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			return mouse.isButton("LEFT"); // checks if a circle is at mouse position... why doesn't moveCircleTool?
			//		!arg0.isControlDown()
			//	&& !arg0.isShiftDown()
			//	&& arg0.getButton() == MouseEvent.BUTTON1;
		}
	};
	
	public static WorldEditorMouseToolSelectCondition selectionBoxToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			return mouse.isButton("RIGHT");
			//		!arg0.isControlDown() 
			//	&& arg0.getButton() == MouseEvent.BUTTON3;
		}
	};
	
}