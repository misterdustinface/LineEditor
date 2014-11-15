package UI.uiTools;

import request.RequestibleAction;
import data.shapes.Circle;
import data.shapes.Pipe;
import data.shapes.Shape;
import data.shapes.WorldGeometryData;

//public abstract class WorldEditorTool implements ToolAction{
public abstract class WorldEditorTool extends RequestibleAction{

	final public static WorldEditorTool nullTool = new WorldEditorTool(null) {
		@Override
		protected boolean shouldAcceptRequest() {return false;}
		@Override
		protected void performAction() {}
	};
	
	protected WorldGeometryData worldData;
	public WorldEditorTool(WorldGeometryData WORLD_DATA){
		worldData = WORLD_DATA;
	}
	
	final protected void 		select(Shape s) 		{ worldData.select(s); }
	final protected boolean 	isSelected(Shape s) 	{ return worldData.isSelected(s); }
	final protected void 		toggleSelected(Shape s) { worldData.toggleSelected(s); }

	final protected Circle[] 	worldCircles() 		{ return worldData.getCopyOfWorldPointCollisionCircles(); }
	final protected Pipe[] worldRectangles() 	{ return worldData.getCopyOfWorldLineCollisionBoxes(); }
	final protected Shape[] 	collisionBounds() 	{ return worldData.getCopyOfAllCollisionBounds(); }
}
