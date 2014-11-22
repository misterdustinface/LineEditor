package file;

import data.shapes.Pipe;
import data.shapes.Point;
import data.shapes.WorldGeometryData;

public class WorldGeometryFiler extends LuaScriptFiler {

	private WorldGeometryData programData;
	private WorldGeometryData loadedData;		
	
	public void setData(WorldGeometryData DATA) { programData = DATA; }
	
	protected String dataToLuaScript() {
		String script = new String();
		for(Pipe collisionBox : programData.getCopyOfWorldLineCollisionBoxes()){
			script += createEntry(	String.valueOf(collisionBox.centerLine.a().x),
									String.valueOf(collisionBox.centerLine.a().y),
									String.valueOf(collisionBox.centerLine.b().x),
									String.valueOf(collisionBox.centerLine.b().y));
		}
		return scriptHeading("Lines Data") + script + scriptCloser("End of Data");
	}

	@Override
	protected void preparseOperation() {
		loadedData = new WorldGeometryData();
	}
	
	@Override
	protected void parseLine(String line) {
		Point[] points = parsePointDataFromLine(line);
		addPointDataToWorldGeometryData(points[0], points[1]);
	}
	
	private Point[] parsePointDataFromLine(String line){
		String[] entries = super.parseEntries(line);
		return new Point[] {new Point(Float.parseFloat(entries[0]), Float.parseFloat(entries[1])),
							new Point(Float.parseFloat(entries[2]), Float.parseFloat(entries[3]))};
	}
	
	private void addPointDataToWorldGeometryData(Point A, Point B){
		loadedData.createPoint(A.x, A.y);
		loadedData.createPoint(B.x, B.y);
		loadedData.createLine(A, B);
	}

	@Override
	protected void postparseOperation() {
		programData.load(loadedData);
	}

}
