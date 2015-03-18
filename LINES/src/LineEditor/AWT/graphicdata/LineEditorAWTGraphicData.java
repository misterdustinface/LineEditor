package LineEditor.AWT.graphicdata;

import generic.tags.NamedData;
import generic.tags.Singleton;

import java.awt.Color;

import AWT.graphicdata.EditorAWTGraphicData;
import AWT.graphicdata.AWTGraphicData;

public class LineEditorAWTGraphicData extends AWTGraphicData implements Singleton, NamedData {
	
	private EditorAWTGraphicData graphicData;
	private static LineEditorAWTGraphicData lineEditorGraphicData = new LineEditorAWTGraphicData();
	
	private LineEditorAWTGraphicData() {
		graphicData = EditorAWTGraphicData.getGraphicData();
		loadColors();
		loadThicknesses();
	}
	
	public static LineEditorAWTGraphicData getGraphicData() {
		return lineEditorGraphicData;
	}
	
	private void loadColors() {
		colors.put("movePointHighlight", new Color(233,250,51));
		colors.put("dragLineTracingLine", Color.LIGHT_GRAY);
		colors.put("dragSelectionBoxBorder", new Color(48,190,56));
		colors.put("dragSelectionBoxTransparentArea", 
				new Color(colors.get("dragSelectionBoxBorder").brighter().getRed(),
						  colors.get("dragSelectionBoxBorder").brighter().getGreen(),
						  colors.get("dragSelectionBoxBorder").brighter().getBlue(),
				64));
		colors.put("copyToolSelectionBoxBorder", new Color(255,190,64));
		colors.put("copyToolSelectionBoxInner", 
				new Color(colors.get("copyToolSelectionBoxBorder").brighter().getRed(),
						colors.get("copyToolSelectionBoxBorder").brighter().getGreen(),
						colors.get("copyToolSelectionBoxBorder").brighter().getBlue(),
				64));
		colors.put("cursor", new Color(25,25,25));
		colors.put("point", Color.BLACK);
		colors.put("pointCircleHighlight", Color.LIGHT_GRAY);
		colors.put("selectedPoint", new Color(48,51,255));
		colors.put("selectedPointCircleHighlight", new Color(151,255,56));
		colors.put("line", Color.DARK_GRAY);
		colors.put("lineBoxHighlight", new Color(238,255,56));
		colors.put("selectedLine", new Color(221,42,63));
		colors.put("selectedLineBoxHighlight", new Color(255,190,64));
	}
	
	private void loadThicknesses() {
		thicknesses.put("INITIAL_pointSize", graphicData.getThicknessOf("pointSize"));
		thicknesses.put("INITIAL_pointHighlightCircle", graphicData.getThicknessOf("pointSize") * 3);
		thicknesses.put("INITIAL_lineHighlightBox", graphicData.getThicknessOf("pointSize") * 2);
		thicknesses.put("pointHighlightCircle", thicknesses.get("INITIAL_pointHighlightCircle"));
		thicknesses.put("lineHighlightBox", thicknesses.get("INITIAL_lineHighlightBox"));
	}
	
	public void scaleHighlightedWorldGeometry(float scale){
		thicknesses.put("pointHighlightCircle", (int) (thicknesses.get("pointHighlightCircle") * scale) );
		thicknesses.put("lineHighlightBox", (int) (thicknesses.get("lineHighlightBox") * scale) );
	}
	
	public void scaleWorldGeometry(float scale) {
		graphicData.scaleThicknessOf("pointSize", scale);
	}
	
	public void resetHighlightedWorldGeometryScales(){
		thicknesses.put("pointHighlightCircle", thicknesses.get("INITIAL_pointHighlightCircle"));
		thicknesses.put("lineHighlightBox", thicknesses.get("INITIAL_lineHighlightBox"));
	}
	
	public void resetWorldGeometryScales(){
		graphicData.setThicknessOf("pointSize", thicknesses.get("INITIAL_pointSize"));
	}
	
}
