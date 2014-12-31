package LineEditor.AWT.graphicdata;

import java.awt.Color;

import AWT.graphicdata.AWTGraphicData;

public class LineEditorAWTGraphicData {
	final private int INITIAL_pointSize;
	final private int INITIAL_pointHighlightCircleThickness;
	final private int INITIAL_lineHighlightBoxThickness;
	public int pointHighlightCircleThickness;
	public int lineHighlightBoxThickness;
	
	private AWTGraphicData graphicData;
	private static LineEditorAWTGraphicData lineEditorGraphicData = new LineEditorAWTGraphicData();
	
	private LineEditorAWTGraphicData() {
		graphicData = AWTGraphicData.getGraphicData();
		INITIAL_pointSize = graphicData.pointSize;
		INITIAL_pointHighlightCircleThickness = INITIAL_pointSize * 3;
		INITIAL_lineHighlightBoxThickness = INITIAL_pointSize * 2;
		
		pointHighlightCircleThickness = INITIAL_pointHighlightCircleThickness;
		lineHighlightBoxThickness = INITIAL_lineHighlightBoxThickness;
	}
	
	public static LineEditorAWTGraphicData getGraphicData() {
		return lineEditorGraphicData;
	}
	
	public void scaleHighlightedWorldGeometry(float scale){
		pointHighlightCircleThickness *= scale;
		lineHighlightBoxThickness     *= scale;
	}
	public void scaleWorldGeometry(float scale){
		graphicData.pointSize *= scale;
	}
	
	public void resetHighlightedWorldGeometryScales(){
		pointHighlightCircleThickness = INITIAL_pointHighlightCircleThickness;
		lineHighlightBoxThickness	  = INITIAL_lineHighlightBoxThickness;
	}
	public void resetWorldGeometryScales(){
		graphicData.pointSize = INITIAL_pointSize;
	}
	
	public static Color movePointHighlight				= new Color(233,250,51);
	public static Color dragLineTracingLine				= Color.LIGHT_GRAY;
	public static Color dragSelectionBoxBorder			= new Color(48,190,56); //new Color(48,190,56);
	public static Color dragSelectionBoxTransparentArea = new Color(dragSelectionBoxBorder.brighter().getRed(),
																	dragSelectionBoxBorder.brighter().getGreen(),
																	dragSelectionBoxBorder.brighter().getBlue(),
																	64);
	public static Color copyToolSelectionBoxBorder		= new Color(255,190,64);
	public static Color copyToolSelectionBoxInner		= new Color(copyToolSelectionBoxBorder.brighter().getRed(),
																	copyToolSelectionBoxBorder.brighter().getGreen(),
																	copyToolSelectionBoxBorder.brighter().getBlue(),
																	64);
	public static Color cursorColor   					= new Color(25,25,25);
	
	public static Color point							= Color.BLACK;
	public static Color pointCircleHighlight 			= Color.LIGHT_GRAY;
	public static Color selectedPoint					= new Color(48,51,255);
	public static Color selectedPointCircleHighlight	= new Color(151,255,56);
	
	public static Color line							= Color.DARK_GRAY;
	public static Color lineBoxHighlight				= new Color(238,255,56);
	public static Color selectedLine					= new Color(221,42,63);
	public static Color selectedLineBoxHighlight		= new Color(255,190,64);
}
