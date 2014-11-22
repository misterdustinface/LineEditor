package AWT.graphicdata;


import java.awt.Color;
import java.util.Random;

public abstract class AWTColorGenerator {
	public static Color randomColor(){
		return new Color(	(int)(Math.random()*250), 
				  			(int)(Math.random()*250), 
				  			(int)(Math.random()*250));
	}
	
	//http://stackoverflow.com/questions/43044/algorithm-to-randomly-generate-an-aesthetically-pleasing-color-palette
	public static Color randomPastel(){
		return new Color(	(int)(Math.random()*127) + 127, 
				  			(int)(Math.random()*127) + 127, 
				  			(int)(Math.random()*127) + 127);
		//return mixColor(Color.WHITE);
	}
	
	//http://stackoverflow.com/questions/43044/algorithm-to-randomly-generate-an-aesthetically-pleasing-color-palette
	public static Color mixColor(Color mix){
	    Random random = new Random();
	    int red 	= random.nextInt(256);
	    int green 	= random.nextInt(256);
	    int blue 	= random.nextInt(256);

	    // mix the color
	    if (mix != null) {
	        red = (red + mix.getRed()) / 2;
	        green = (green + mix.getGreen()) / 2;
	        blue = (blue + mix.getBlue()) / 2;
	    }

	    Color color = new Color(red, green, blue);
	    return color;
	}
	
	public static Color getCompliment(Color primary){
		return new Color(255 - primary.getRed(), 
						 255 - primary.getGreen(),
						 255 - primary.getBlue());
	}
	
	public static Color getIntermediateColor(Color primary, Color secondary){
		return new Color(	(primary.getRed() + secondary.getRed())/2,
							(primary.getGreen() + secondary.getGreen())/2,
							(primary.getBlue() + secondary.getBlue())/2);
	}
}
