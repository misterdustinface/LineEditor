package LineEditor.data;

import java.util.ArrayList;

import shapes.Circle;
import shapes.LineSegment;
import shapes.Pipe;
import shapes.Point;
import shapes.Shape;
import shapes.ShapeSelectorMap;
import LineEditor.AWT.graphicdata.LineEditorAWTGraphicData;

public class WorldGeometryData{

	private ArrayList<Shape> 	worldCollisionBounds;
	private ArrayList<Circle> 	worldPointCollisionCircles;
	private ArrayList<Pipe> 	worldLineCollisionBoxes;
	private ShapeSelectorMap 	selectionMap;
	
	private LineEditorAWTGraphicData lineEditorGraphicData;
	
	public WorldGeometryData() {
		worldPointCollisionCircles 	= new ArrayList<Circle>();
		worldLineCollisionBoxes 	= new ArrayList<Pipe>();		
		worldCollisionBounds		= new ArrayList<Shape>();
		selectionMap 				= new ShapeSelectorMap();
		
		lineEditorGraphicData = LineEditorAWTGraphicData.getGraphicData();
	}
	
	public void load(WorldGeometryData other) {
		this.worldPointCollisionCircles = other.worldPointCollisionCircles;
		this.worldLineCollisionBoxes 	= other.worldLineCollisionBoxes;
		this.worldCollisionBounds		= other.worldCollisionBounds;
		this.selectionMap 				= other.selectionMap;
	}
	
	public void append(WorldGeometryData other) {
		for(Shape s : other.worldCollisionBounds ) {
			add(s);
			if(other.isSelected(s)) { select(s); }
		}		
	}
	
	public boolean 	isSelected(Shape s) 	{ return selectionMap.isSelected(s); }
	public void 	toggleSelected(Shape s) { selectionMap.toggleSelected(s); }
	public void 	select(Shape s) 		{ selectionMap.select(s); }
	public int 		totalNumberOfShapes() 	{ return totalNumberOfPoints() + totalNumberOfLines(); }
	public int 		totalNumberOfPoints() 	{ return worldPointCollisionCircles.size(); }
	public int 		totalNumberOfLines() 	{ return worldLineCollisionBoxes.size(); }
	
	public boolean isShapeAtPositionSelected(float X, float Y){
		for(Shape s : worldCollisionBounds)
			if(s.contains(X, Y))
				return isSelected(s);
		return false;
	}
	
	public boolean isCircleAtPositionSelected(float X, float Y){
		for(Circle circle : worldPointCollisionCircles)
			if(circle.contains(X, Y))
				return isSelected(circle);
		return false;
	}
	
	public void scaleSelectionAreaSizeForWorldGeometry(float percent){
		lineEditorGraphicData.scaleHighlightedWorldGeometry(percent);
		for(Shape s : worldCollisionBounds)
			s.scale(percent);
	}
	
	private boolean pointHasDuplicate(Point p) {
		return getPointDirectlyToCenterOfEquivalentCollisionCircle(p) != null;
	}
	
	public void createPoint(float x, float y){
		Circle temp = new Circle(x, y, lineEditorGraphicData.pointHighlightCircleThickness);
		if(! pointHasDuplicate(temp.center()))
			add(temp);
	}
	public void createLine(Point A, Point B){
		A = getPointDirectlyToCenterOfEquivalentCollisionCircle(A);
		B = getPointDirectlyToCenterOfEquivalentCollisionCircle(B);
		Pipe temp = new Pipe(new LineSegment(A,B), lineEditorGraphicData.lineHighlightBoxThickness);
		add(temp);
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
	
	private Shape[] 	shapeArray 	= new Shape[] {};
	private Circle[] 	circleArray = new Circle[] {};
	private Pipe[] 		pipeArray 	= new Pipe[] {};
	public Shape[] 	getAllCollisionBounds() 		{ return worldCollisionBounds.toArray(shapeArray); }
	public Circle[] getWorldPointCollisionCircles() { return worldPointCollisionCircles.toArray(circleArray); }
	public Pipe[] 	getWorldLineCollisionBoxes() 	{ return worldLineCollisionBoxes.toArray(pipeArray); }
	
	public void add(Shape s){
		selectionMap.put(s);
		worldCollisionBounds.add(s);
		if(s instanceof Pipe)
			worldLineCollisionBoxes.add((Pipe)s);
		if(s instanceof Circle)
	    	worldPointCollisionCircles.add((Circle)s);
	}
	
	public void remove(Shape s){
		selectionMap.remove(s);
		worldCollisionBounds.remove(s);
		if(s instanceof Pipe)
			worldLineCollisionBoxes.remove(s);
		if(s instanceof Circle){
	    	worldPointCollisionCircles.remove(s);
	    	removeAssociatedLines(((Circle)s).center());
		}
	}

	private void removeAssociatedLines(Point point){
		for(Pipe collisionBox : getWorldLineCollisionBoxes())
			if(collisionBox.centerLine.isEdge(point))
				remove(collisionBox);
	}

	public void splitCollisionBox(Pipe collisionBox, int percent){

		LineSegment newLine 	= collisionBox.centerLine.split(percent / 100f);
		Circle 		newCircle 	= new Circle(newLine.a, lineEditorGraphicData.pointHighlightCircleThickness);
		newLine.a = collisionBox.centerLine.b  = newCircle.center(); // Circle makes copy of point. Get the new point and set the ends of the line to the point.
		Pipe newRect = new Pipe(newLine, collisionBox.thickness());
		
		add(newCircle);
		add(newRect);
		select(newCircle);
		select(newRect);
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
