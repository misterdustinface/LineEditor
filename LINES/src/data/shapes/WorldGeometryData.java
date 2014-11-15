package data.shapes;

import java.util.ArrayList;

import data.graphics.AWTGraphicData;

public class WorldGeometryData{

	private ArrayList<Circle> 			worldPointCollisionCircles;
	private ArrayList<Pipe> 			worldLineCollisionBoxes;
	private CollisionBoundSelectorMap 	selectionMap;
	
	//private ArrayList<BoundedElement> boundedElements;
	
	public WorldGeometryData() {
		worldPointCollisionCircles 	= new ArrayList<Circle>();
		worldLineCollisionBoxes 	= new ArrayList<Pipe>();		
		//boundedElements             = new ArrayList<BoundedElement>();
		selectionMap 				= new CollisionBoundSelectorMap();
	}
	
	public void load(WorldGeometryData other) {
		this.worldPointCollisionCircles = other.worldPointCollisionCircles;
		this.worldLineCollisionBoxes 	= other.worldLineCollisionBoxes;
		this.selectionMap 				= other.selectionMap;
	}
	
	public void append(WorldGeometryData other) {

	}
	
	public boolean 	isSelected(Shape s) 	{ return selectionMap.isSelected(s); }
	public void 	toggleSelected(Shape s) { selectionMap.toggleSelected(s); }
	public void 	select(Shape s) 		{ selectionMap.select(s); }
	public int 		totalNumberOfShapes() 	{ return totalNumberOfPoints() + totalNumberOfLines(); }
	public int 		totalNumberOfPoints() 	{ return worldPointCollisionCircles.size(); }
	public int 		totalNumberOfLines() 	{ return worldLineCollisionBoxes.size(); }
	
	public boolean isCircleAtPositionSelected(float X, float Y){
		for(Circle circle : worldPointCollisionCircles){
			if(circle.contains(X, Y)){
				return isSelected(circle);
			}
		}
		return false;
	}
	
	public void scaleSelectionAreaSizeForWorldGeometry(float percent){
		AWTGraphicData.scaleHighlightedWorldGeometry(percent);
		for(Shape s : worldPointCollisionCircles)
			s.scale(percent);
		for(Shape s : worldLineCollisionBoxes)
			s.scale(percent);
	}
	
	private boolean pointHasDuplicate(Point p) {
		return getPointDirectlyToCenterOfEquivalentCollisionCircle(p) != null;
	}
	
	public void createPoint(float x, float y){
		Circle temp = new Circle(x, y, AWTGraphicData.pointHighlightCircleThickness);
		if(! pointHasDuplicate(temp.center())) {
			worldPointCollisionCircles.add(temp);
			selectionMap.put(temp);
		}
	}
	public void createLine(Point A, Point B){
		A = getPointDirectlyToCenterOfEquivalentCollisionCircle(A);
		B = getPointDirectlyToCenterOfEquivalentCollisionCircle(B);
		Pipe temp = new Pipe(new LineSegment(A,B), AWTGraphicData.lineHighlightBoxThickness);
		worldLineCollisionBoxes.add(temp);
		selectionMap.put(temp);
	}
	
	private Point getPointDirectlyToCenterOfEquivalentCollisionCircle(Point point){
		Circle c = getCollisionCircleWithGivenCenter(point);
		return c == null ? null : c.center();
	}
	
	private Circle getCollisionCircleWithGivenCenter(Point centerOfCircle){
		for(Circle collisionCircle : worldPointCollisionCircles)
			if((collisionCircle.center().equals(centerOfCircle)))
				return collisionCircle;
		return null;
	}
	
	public Shape[] getCopyOfAllCollisionBounds() {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.addAll(worldPointCollisionCircles);
		shapes.addAll(worldLineCollisionBoxes);
		return shapes.toArray(new Shape[]{});
	}
	
	public Circle[] getCopyOfWorldPointCollisionCircles() { return worldPointCollisionCircles.toArray(new Circle[]{}); }
	public Pipe[] 	getCopyOfWorldLineCollisionBoxes() 	  { return worldLineCollisionBoxes.toArray(new Pipe[]{}); }
	
	public void remove(Shape s){
		selectionMap.remove(s);

		if(s instanceof Circle){
	    	worldPointCollisionCircles.remove(s);
	    	removeAssociatedLines(((Circle)s).center());
		}
		
		if(s instanceof Pipe){
			worldLineCollisionBoxes.remove(s);
		}
	}

	private void removeAssociatedLines(Point point){
		for(Pipe collisionBox : getCopyOfWorldLineCollisionBoxes())
			if(collisionBox.centerLine.isEdge(point))
				remove(collisionBox);
	}

	public void splitCollisionBox(Pipe collisionBox, int percent){

		LineSegment newLine 	= collisionBox.centerLine.split(percent / 100f);
		Circle 		newCircle 	= new Circle(newLine.a, AWTGraphicData.pointHighlightCircleThickness);
		newLine.a = collisionBox.centerLine.b  = newCircle.center(); // Circle makes copy of point. Get the new point and set the ends of the line to the point.
		Pipe newRect = new Pipe(newLine, collisionBox.thickness());
		
		worldPointCollisionCircles.add(newCircle);
		selectionMap.putWithSelected(newCircle);
		worldLineCollisionBoxes.add(newRect);
		selectionMap.putWithSelected(newRect);
	}
	
//	/**
//	 * I am mad that I added this but I had to. Very confusing. Copy tool needed it.
//	 * @param LINE
//	 */
//	public void addCopyOfLine(Line LINE){
//		Circle tempA = new Circle(LINE.a.x, LINE.a.y, GraphicData.pointHighlightCircleThickness);
//		Circle tempB = new Circle(LINE.b.x, LINE.b.y, GraphicData.pointHighlightCircleThickness);
//		points.add(tempA); pointSelectionMap.put(tempA, true);
//		points.add(tempB); pointSelectionMap.put(tempB, true);
//		Rectangle tempLine = new Rectangle(new Line(tempA.center(), tempB.center()), GraphicData.lineHighlightBoxThickness);
//		lines.add(tempLine);
//		lineSelectionMap.put(tempLine, true);
//	}
	
}
