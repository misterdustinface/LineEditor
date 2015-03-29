package LineEditor.AWT.graphicdata;

import generic.datastructures.Property;
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
		@SuppressWarnings("unchecked")
		Property<Color> colorProperty = getProperty("color");
		colorProperty.add("movePointHighlight", new Color(233,250,51));
		colorProperty.add("dragLineTracingLine", Color.LIGHT_GRAY);
		colorProperty.add("dragSelectionBoxBorder", new Color(48,190,56));
		colorProperty.add("dragSelectionBoxTransparentArea", 
				new Color(getColorOf("dragSelectionBoxBorder").brighter().getRed(),
						  getColorOf("dragSelectionBoxBorder").brighter().getGreen(),
						  getColorOf("dragSelectionBoxBorder").brighter().getBlue(),
				64));
		colorProperty.add("copyToolSelectionBoxBorder", new Color(255,190,64));
		colorProperty.add("copyToolSelectionBoxInner", 
				new Color(getColorOf("copyToolSelectionBoxBorder").brighter().getRed(),
						  getColorOf("copyToolSelectionBoxBorder").brighter().getGreen(),
						  getColorOf("copyToolSelectionBoxBorder").brighter().getBlue(),
				64));
		colorProperty.add("cursor", new Color(25,25,25));
		colorProperty.add("point", Color.BLACK);
		colorProperty.add("pointCircleHighlight", Color.LIGHT_GRAY);
		colorProperty.add("selectedPoint", new Color(48,51,255));
		colorProperty.add("selectedPointCircleHighlight", new Color(151,255,56));
		colorProperty.add("line", Color.DARK_GRAY);
		colorProperty.add("lineBoxHighlight", new Color(238,255,56));
		colorProperty.add("selectedLine", new Color(221,42,63));
		colorProperty.add("selectedLineBoxHighlight", new Color(255,190,64));
	}
	
	private void loadThicknesses() {
		@SuppressWarnings("unchecked")
		Property<Integer> thicknessProp = getProperty("thickness");
		thicknessProp.add("INITIAL_pointSize", graphicData.getThicknessOf("pointSize"));
		thicknessProp.add("INITIAL_pointHighlightCircle", graphicData.getThicknessOf("pointSize") * 3);
		thicknessProp.add("INITIAL_lineHighlightBox", graphicData.getThicknessOf("pointSize") * 2);
		thicknessProp.add("pointHighlightCircle", getThicknessOf("INITIAL_pointHighlightCircle"));
		thicknessProp.add("lineHighlightBox", getThicknessOf("INITIAL_lineHighlightBox"));
	}
	
	public void scaleHighlightedWorldGeometry(float scale){
		@SuppressWarnings("unchecked")
		Property<Integer> thicknessProp = getProperty("thickness");
		thicknessProp.add("pointHighlightCircle", (int) (getThicknessOf("pointHighlightCircle") * scale) );
		thicknessProp.add("lineHighlightBox", (int) (getThicknessOf("lineHighlightBox") * scale) );
	}
	
	public void scaleWorldGeometry(float scale) {
		graphicData.scaleThicknessOf("pointSize", scale);
	}
	
	public void resetHighlightedWorldGeometryScales(){
		@SuppressWarnings("unchecked")
		Property<Integer> thicknessProp = getProperty("thickness");
		thicknessProp.add("pointHighlightCircle", getThicknessOf("INITIAL_pointHighlightCircle"));
		thicknessProp.add("lineHighlightBox", getThicknessOf("INITIAL_lineHighlightBox"));
	}
	
	public void resetWorldGeometryScales(){
		graphicData.setThicknessOf("pointSize", getThicknessOf("INITIAL_pointSize"));
	}
	
}