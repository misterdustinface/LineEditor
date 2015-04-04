package LineEditor.file;

import shapes.LineSegment;
import shapes.Point;
import LineEditor.data.WorldGeometryData;
import file.LuaScriptFiler;

public class WorldGeometryFiler extends LuaScriptFiler {

	private WorldGeometryData programData;
	private WorldGeometryData loadedData;		
	
	public void setData(WorldGeometryData DATA) { 
		programData = DATA; 
	}
	
	protected String dataToLuaScript() {
		String script = new String();
		for (LineSegment line : programData.getLines()) {
			script += createEntry(	String.valueOf(line.a.x),
									String.valueOf(line.a.y),
									String.valueOf(line.b.x),
									String.valueOf(line.b.y));
		}
		return scriptHeading("Lines Data") + script + scriptCloser("End of Data");
	}

	protected void preparseOperation() {
		loadedData = new WorldGeometryData();
	}
	
	protected void parseLine(String line) {
		Point[] points = parsePointDataFromLine(line);
		addPointDataToWorldGeometryData(points[0], points[1]);
	}
	
	private Point[] parsePointDataFromLine(String line) {
		String[] entries = super.parseEntries(line);
		return new Point[] { new Point(Float.parseFloat(entries[0]), Float.parseFloat(entries[1])),
							 new Point(Float.parseFloat(entries[2]), Float.parseFloat(entries[3])) };
	}
	
	private void addPointDataToWorldGeometryData(Point A, Point B) {
		loadedData.createPoint(A.x, A.y);
		loadedData.createPoint(B.x, B.y);
		loadedData.createLine(A, B);
	}

	protected void postparseOperation() {
		programData.load(loadedData);
	}

}