package LineEditor.data;

import generic.ListenerPattern.Descriptive.DataModificationNotifier;

import java.util.ArrayList;
import java.util.Stack;

import shapes.Circle;
import shapes.LineSegment;
import shapes.Pipe;
import shapes.Point;
import shapes.Shape;
import shapes.ShapeSelectorMap;
import LineEditor.AWT.graphicdata.LineEditorAWTGraphicData;

public class WorldGeometryData extends DataModificationNotifier {
	
	private ArrayList<Shape> 	worldCollisionBounds;
	private ArrayList<Circle> 	worldPointCollisionCircles;
	private ArrayList<Pipe> 	worldLineCollisionBoxes;
	private ShapeSelectorMap 	selectionMap;
	
	private LineEditorAWTGraphicData lineEditorGraphicData;
	
	private Stack<Pipe> associatedLineRemovalList;
	
	public WorldGeometryData() {
		worldPointCollisionCircles 	= new ArrayList<Circle>();
		worldLineCollisionBoxes 	= new ArrayList<Pipe>();		
		worldCollisionBounds		= new ArrayList<Shape>();
		selectionMap 				= new ShapeSelectorMap();
		
		lineEditorGraphicData = LineEditorAWTGraphicData.getGraphicData();
		
		associatedLineRemovalList = new Stack<Pipe>();
	}
	
	public void load(WorldGeometryData other) {
		this.worldPointCollisionCircles = other.worldPointCollisionCircles;
		this.worldLineCollisionBoxes 	= other.worldLineCollisionBoxes;
		this.worldCollisionBounds		= other.worldCollisionBounds;
		this.selectionMap 				= other.selectionMap;
		notifyDataModified();
	}
	
	private void append(WorldGeometryData other) {
		for (Shape s : other.worldCollisionBounds ) {
			add(s);
			if (other.isSelected(s))
				select(s); 
		}
		notifyDataModified();
	}
	
	/**
	 * FOR GRAPHICS ONLY
	 */
	public ArrayList<Circle> getCircles() {
		return worldPointCollisionCircles;
	}
	
	/**
	 * FOR GRAPHICS ONLY
	 */
	public ArrayList<Pipe> getPipes() {
		return worldLineCollisionBoxes;
	}
	
	public boolean isShapeQuerySatisfied(ShapeQuery sq) {
		for (Shape s : worldCollisionBounds) {
			if (sq.isSatisfiableGivenShape(s)) {
				return true;
			}
		}
		return false;
	}
	
	public Shape getShapeThatSatisfiesQuery(ShapeQuery sq) {
		for (Shape s : worldCollisionBounds) {
			if (sq.isSatisfiableGivenShape(s)) {
				return s;
			}
		}
		return null;
	}
	
	public void performShapeFunctionOnAllShapes(ShapeFunction sf) {
		for (Shape s : worldCollisionBounds) {
			sf.manipulateShape(s);
		}
	}
	
	public boolean isSelected(Shape s) { 
		return selectionMap.isSelected(s); 
	}
	
	public void toggleSelected(Shape s) { 
		selectionMap.toggleSelected(s);
		notifyDataModified();
	}
	
	public void select(Shape s) { 
		selectionMap.select(s);
		notifyDataModified();
	}
	
	public int totalNumberOfShapes() { 
		return worldCollisionBounds.size();
	}
	
	public int totalNumberOfPoints() { 
		return worldPointCollisionCircles.size(); 
	}
	
	public int totalNumberOfLines() { 
		return worldLineCollisionBoxes.size(); 
	}
	
	public void scaleSelectionAreaSizeForWorldGeometry(float percent) {
		lineEditorGraphicData.scaleHighlightedWorldGeometry(percent);
		for (Shape s : worldCollisionBounds)
			s.scale(percent);
		notifyDataModified();
	}
	
	public void createPoint(float x, float y){
		Circle temp = new Circle(x, y, lineEditorGraphicData.getThicknessOf("pointHighlightCircle"));
		if (!pointHasDuplicate(temp.center()))
			add(temp);
		notifyDataModified();
	}
	
	public void createLine(Point A, Point B){
		A = getPointDirectlyToCenterOfEquivalentCollisionCircle(A);
		B = getPointDirectlyToCenterOfEquivalentCollisionCircle(B);
		Pipe temp = new Pipe(new LineSegment(A,B), lineEditorGraphicData.getThicknessOf("lineHighlightBox"));
		add(temp);
		notifyDataModified();
	}
	
	public LineSegment[] getLines() {
		LineSegment[] lines = new LineSegment[worldLineCollisionBoxes.size()];
		for (int i = 0; i < lines.length; ++i) {
			lines[i] = worldLineCollisionBoxes.get(i).centerLine;
		}
		return lines;
	}
	
	public void add(Shape s) {
		selectionMap.put(s);
		worldCollisionBounds.add(s);
		if (s instanceof Pipe)   worldLineCollisionBoxes.add((Pipe)s);
		if (s instanceof Circle) worldPointCollisionCircles.add((Circle)s);
		notifyDataModified();
	}
	
	public void remove(Shape s) {
		selectionMap.remove(s);
		worldCollisionBounds.remove(s);
		if (s instanceof Pipe) {
			worldLineCollisionBoxes.remove(s);
		}
		if (s instanceof Circle){
	    	worldPointCollisionCircles.remove(s);
	    	removeAssociatedLines(((Circle)s).center());
		}
		notifyDataModified();
	}

	public void splitCollisionBox(Pipe collisionBox, int percent) {

		LineSegment newLine = collisionBox.centerLine.split(percent / 100f);
		Circle newCircle = new Circle(newLine.a.x, newLine.a.y, lineEditorGraphicData.getThicknessOf("pointHighlightCircle"));
		newLine.a = collisionBox.centerLine.b = newCircle.center(); // Circle makes copy of point. Get the new point and set the ends of the line to the point.
		Pipe newRect = new Pipe(newLine, collisionBox.thickness());
		
		add(newCircle);
		add(newRect);
		select(newCircle);
		//select(newRect);
		notifyDataModified();
	}
	
	private boolean pointHasDuplicate(Point p) {
		return getPointDirectlyToCenterOfEquivalentCollisionCircle(p) != null;
	}
	
	private Point getPointDirectlyToCenterOfEquivalentCollisionCircle(Point point){
		Circle c = getCollisionCircleWithGivenCenter(point);
		return c == null ? null : c.center();
	}
	
	private Circle getCollisionCircleWithGivenCenter(Point centerOfCircle){
		for (Circle collisionCircle : worldPointCollisionCircles)
			if ((collisionCircle.center().equals(centerOfCircle)))
				return collisionCircle;
		return null;
	}
	
	private void removeAssociatedLines(Point point) {
		for (Pipe collisionBox : worldLineCollisionBoxes) {
			if (collisionBox.centerLine.isEdge(point))
				associatedLineRemovalList.push(collisionBox);
		}
		while (!associatedLineRemovalList.isEmpty()) {
			remove(associatedLineRemovalList.pop());
		}
		notifyDataModified();
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