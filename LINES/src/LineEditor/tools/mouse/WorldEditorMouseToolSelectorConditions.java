package LineEditor.tools.mouse;

import shapes.Circle;
import shapes.Shape;
import LineEditor.data.ShapeQuery;
import LineEditor.data.WorldGeometryData;
import UI.input.MouseUserDevice;

public abstract class WorldEditorMouseToolSelectorConditions {
	
	private static WorldGeometryData WORLD_DATA;
	private static MouseUserDevice MOUSE;
	
	public static void setWorldData(WorldGeometryData worldData) {
		WORLD_DATA = worldData;
	}
	
	public static WorldEditorMouseToolSelectCondition deleteToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			MOUSE = mouse;
			return mouse.isButton("MIDDLE");
		}
	};
	
	public static WorldEditorMouseToolSelectCondition pointCreateToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			MOUSE = mouse;
			return mouse.isButton("LEFT");
		}
	};
	
	public static WorldEditorMouseToolSelectCondition valueEditorToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			MOUSE = mouse;
			return false;
		}
	};
	
	public static WorldEditorMouseToolSelectCondition selectPointToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			MOUSE = mouse;
			return mouse.isButton("RIGHT");
		}
	};
	
	private static ShapeQuery isCircleAtPositionSelected = new ShapeQuery() {
		public boolean isSatisfiableGivenShape(Shape s) {
			return (s instanceof Circle) && WORLD_DATA.isSelected(s) && s.contains(MOUSE.getCursorPosition());
		}
	};
	
	public static WorldEditorMouseToolSelectCondition moveCircleToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			MOUSE = mouse;
			return mouse.isButton("LEFT") && WORLD_DATA.isShapeQuerySatisfied(isCircleAtPositionSelected);
		}
	};
	
	public static WorldEditorMouseToolSelectCondition tracerLineToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			MOUSE = mouse;
			return mouse.isButton("LEFT");
		}
	};
	
	public static WorldEditorMouseToolSelectCondition selectionBoxToolSelectCondition = new WorldEditorMouseToolSelectCondition(){
		public boolean shouldBeSelected(MouseUserDevice mouse) {
			MOUSE = mouse;
			return mouse.isButton("RIGHT");
		}
	};
	
}