//package LineEditor.UI.uiTools;
//
//import LineEditor.data.WorldGeometryData;
//import data.shapes.Circle;
//import data.shapes.Pipe;
//
//public class SelectAllTool extends WorldEditorTool {
//
//	public SelectAllTool(WorldGeometryData WORLD_DATA) {
//		super(WORLD_DATA);
//	}
//	
//	private void selectAllWorldShapes(){
//		selectAllWorldCircles();
//		selectAllWorldRectangles();
//		//TODO same for polygons
//	}
//	private void selectAllWorldCircles(){
//		for(Circle worldCircle : worldCircles())
//			select(worldCircle);
//	}
//	private void selectAllWorldRectangles(){
//		for(Pipe worldRectangle : worldRectangles())
//			select(worldRectangle);
//	}
//	//TODO same for polygons
//	
//	private boolean allWorldShapesAreAlreadySelected(){
//		for(Circle worldCircle : worldCircles()){
//			if(! isSelected(worldCircle)){
//				return false;
//			}
//		}
//		for(Pipe worldRectangle : worldRectangles()){
//			if(! isSelected(worldRectangle)){
//				return false;
//			}
//		}
//		return true;
//	}
//
//	private void toggleAllWorldShapes(){
//		for(Circle worldCircle : worldCircles())
//			toggleSelected(worldCircle);
//		for(Pipe worldRectangle : worldRectangles())
//			toggleSelected(worldRectangle);
//	}
//	@Override
//	protected boolean shouldAcceptRequest() {
//		return true;
//	}
//	@Override
//	protected void performAction() {
//		if(allWorldShapesAreAlreadySelected()){
//			toggleAllWorldShapes();
//		}else{
//			selectAllWorldShapes();
//		}
//	}
//}
