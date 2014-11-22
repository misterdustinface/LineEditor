package AWT.graphicdata;

import java.awt.Color;

public class AWTGraphicData {
	
	public static Color clear                           = new Color(0,0,0,0);
	public static Color lightclear                      = new Color(50,50,50,50);
	
	public static Color buttonColor                     = new Color(150,150,150);
	public static Color buttonHighlightColor            = new Color(125,125,162);
	public static Color buttonPressedColor              = new Color(175,100,175);
	public static Color baseColor                       = new Color(200,200,200);
	
	public static Color selectedPointCircleHighlight	= new Color(151,255,56);
	public static Color selectedPoint					= new Color(48,51,255);
	public static Color pointCircleHighlight 			= Color.LIGHT_GRAY;
	public static Color point							= Color.BLACK;
	public static Color selectedLineBoxHighlight		= new Color(255,190,64);
	public static Color selectedLine					= new Color(221,42,63);
	public static Color lineBoxHighlight				= new Color(238,255,56);
	public static Color line							= Color.DARK_GRAY;
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
	
	public static int pointHighlightCircleThickness		= 12;//8
	public static int pointSize 						= 4; //4
	public static int lineHighlightBoxThickness 		= 8; //6	
	public static int cursorStretchOutAmount 			= 10;
	public static int cursorCenterGapAmount  			= 2;
	
	final private static int INITIAL_pointHighlightCircleThickness	= 12; //8
	final private static int INITIAL_pointSize 						= 4; //4
	final private static int INITIAL_lineHighlightBoxThickness 		= 8; //6

	public static void scaleHighlightedWorldGeometry(float scale){
		pointHighlightCircleThickness *= scale;
		lineHighlightBoxThickness     *= scale;
	}
	public static void scaleWorldGeometry(float scale){
		pointSize *= scale;
	}
	
	public static void resetHighlightedWorldGeometryScales(){
		pointHighlightCircleThickness = INITIAL_pointHighlightCircleThickness;
		lineHighlightBoxThickness	  = INITIAL_lineHighlightBoxThickness;
	}
	public static void resetWorldGeometryScales(){
		pointSize = INITIAL_pointSize;
	}
	
	public static int[] POINTER_CURSOR_X = new int[] {0, 15, 8, 5};
	public static int[] POINTER_CURSOR_Y = new int[] {0,  5, 8,15};

	public static Color MENU_BACKGROUND_COLOR = new Color(240,240,240);
	public static Color BACKGROUND_COLOR      = new Color(240,240,240);
	public static Color PLUS_SIGN_COLOR       = new Color(165,165,165);
	public static int   plusSignThickness     = 4;
}
