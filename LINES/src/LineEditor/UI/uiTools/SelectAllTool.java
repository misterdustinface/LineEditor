package LineEditor.UI.uiTools;

import LineEditor.data.WorldGeometryData;
import data.shapes.Shape;

public class SelectAllTool extends WorldEditorTool {

	public SelectAllTool(WorldGeometryData WORLD_DATA) {
		super(WORLD_DATA);
	}
	
	private void selectAllWorldShapes(){
		for(Shape s : collisionBounds()) 
			select(s);
	}
	
	private void toggleAllWorldShapes(){
		for(Shape s : collisionBounds()) 
			toggleSelected(s);
	}
	
	private boolean allWorldShapesAreAlreadySelected(){
		for(Shape s : collisionBounds()) 
			if(! isSelected(s))
				return false;
		return true;
	}
	
	@Override
	protected boolean shouldAcceptRequest() {
		return true;
	}
	
	@Override
	protected void performAction() {
		if(allWorldShapesAreAlreadySelected()){
			toggleAllWorldShapes();
		}else{
			selectAllWorldShapes();
		}
	}
}
