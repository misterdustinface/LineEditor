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
		colors.insert("movePointHighlight", new Color(233,250,51));
		colors.insert("dragLineTracingLine", Color.LIGHT_GRAY);
		colors.insert("dragSelectionBoxBorder", new Color(48,190,56));
		colors.insert("dragSelectionBoxTransparentArea", 
				new Color(colors.get("dragSelectionBoxBorder").brighter().getRed(),
						  colors.get("dragSelectionBoxBorder").brighter().getGreen(),
						  colors.get("dragSelectionBoxBorder").brighter().getBlue(),
				64));
		colors.insert("copyToolSelectionBoxBorder", new Color(255,190,64));
		colors.insert("copyToolSelectionBoxInner", 
				new Color(colors.get("copyToolSelectionBoxBorder").brighter().getRed(),
						colors.get("copyToolSelectionBoxBorder").brighter().getGreen(),
						colors.get("copyToolSelectionBoxBorder").brighter().getBlue(),
				64));
		colors.insert("cursor", new Color(25,25,25));
		colors.insert("point", Color.BLACK);
		colors.insert("pointCircleHighlight", Color.LIGHT_GRAY);
		colors.insert("selectedPoint", new Color(48,51,255));
		colors.insert("selectedPointCircleHighlight", new Color(151,255,56));
		colors.insert("line", Color.DARK_GRAY);
		colors.insert("lineBoxHighlight", new Color(238,255,56));
		colors.insert("selectedLine", new Color(221,42,63));
		colors.insert("selectedLineBoxHighlight", new Color(255,190,64));
	}
	
	private void loadThicknesses() {
		thicknesses.insert("INITIAL_pointSize", graphicData.getThicknessOf("pointSize"));
		thicknesses.insert("INITIAL_pointHighlightCircle", graphicData.getThicknessOf("pointSize") * 3);
		thicknesses.insert("INITIAL_lineHighlightBox", graphicData.getThicknessOf("pointSize") * 2);
		thicknesses.insert("pointHighlightCircle", thicknesses.get("INITIAL_pointHighlightCircle"));
		thicknesses.insert("lineHighlightBox", thicknesses.get("INITIAL_lineHighlightBox"));
	}
	
	public void scaleHighlightedWorldGeometry(float scale){
		thicknesses.insert("pointHighlightCircle", (int) (thicknesses.get("pointHighlightCircle") * scale) );
		thicknesses.insert("lineHighlightBox", (int) (thicknesses.get("lineHighlightBox") * scale) );
	}
	
	public void scaleWorldGeometry(float scale) {
		graphicData.scaleThicknessOf("pointSize", scale);
	}
	
	public void resetHighlightedWorldGeometryScales(){
		thicknesses.insert("pointHighlightCircle", thicknesses.get("INITIAL_pointHighlightCircle"));
		thicknesses.insert("lineHighlightBox", thicknesses.get("INITIAL_lineHighlightBox"));
	}
	
	public void resetWorldGeometryScales(){
		graphicData.setThicknessOf("pointSize", thicknesses.get("INITIAL_pointSize"));
	}
	
}
