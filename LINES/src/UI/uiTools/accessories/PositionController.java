package UI.uiTools.accessories;

import data.shapes.Point;

public class PositionController {
	public Point position;
	public PositionController(){position = new Point(0,0);}
	public void reset(){position.set(0, 0);}
	public Point getPosition(){return position;}
	
}
