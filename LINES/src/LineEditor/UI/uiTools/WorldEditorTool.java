package LineEditor.UI.uiTools;

import data.shapes.Circle;
import data.shapes.Pipe;
import data.shapes.Shape;
import LineEditor.data.WorldGeometryData;
import generic.Requestible;

public abstract class WorldEditorTool implements Requestible {

	protected WorldGeometryData worldData;
	
	public WorldEditorTool(WorldGeometryData WORLD_DATA) {
		worldData = WORLD_DATA;
	}
	
	final protected void 		select(Shape s) 		{ worldData.select(s); }
	final protected boolean 	isSelected(Shape s) 	{ return worldData.isSelected(s); }
	final protected void 		toggleSelected(Shape s) { worldData.toggleSelected(s); }

	final protected Circle[] 	worldCircles() 		{ return worldData.getWorldPointCollisionCircles(); }
	final protected Pipe[] 		worldRectangles() 	{ return worldData.getWorldLineCollisionBoxes(); }
	final protected Shape[] 	collisionBounds() 	{ return worldData.getAllCollisionBounds(); }
	
	@Override
	final public void request(){
		if(shouldAcceptRequest())
			performAction();
	}
	
	abstract protected boolean 	shouldAcceptRequest();
	abstract protected void		performAction();
	
}
