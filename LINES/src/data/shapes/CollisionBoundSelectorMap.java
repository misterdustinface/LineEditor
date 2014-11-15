package data.shapes;

import java.util.ArrayList;
import java.util.HashMap;

public class CollisionBoundSelectorMap{
	
	private HashMap<String, Boolean> shapeSelectionMap;
	
	public CollisionBoundSelectorMap(){
		shapeSelectionMap = new HashMap<String, Boolean>();
	}
	
	private static String hash(Shape s) { return s.ID(); }
	
	public boolean isSelected(Shape s) { 
		return shapeSelectionMap.get(hash(s));
	}
	
	public void toggleSelected(Shape s) { 
		shapeSelectionMap.put(hash(s), !isSelected(s)); 
	}
	
	public void select(Shape s) {
		shapeSelectionMap.put(hash(s), true);
	}

	public void put(Shape s) {
		shapeSelectionMap.put(hash(s), false);
	}
	
	public void putWithSelected(Shape s) {
		shapeSelectionMap.put(hash(s), true);
	}
	
	public void remove(Shape s) {
		shapeSelectionMap.remove(hash(s));
	}
	
	public void putWithAllSelected(ArrayList<Shape> shapes) {
		for(Shape s : shapes) putWithSelected(s);
	}
	
	public void remove(ArrayList<Shape> shapes) {
		for(Shape s : shapes) remove(s);
	}

}
