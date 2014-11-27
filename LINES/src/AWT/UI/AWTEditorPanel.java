package AWT.UI;


import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import AWT.graphicdata.AWTGraphicData;
import UI.StratifiableUI;
import UI.UILayer;

public class AWTEditorPanel extends AWTViewport implements StratifiableUI {
	
	private static final long serialVersionUID = -4178489165743436644L;

	final public static Cursor INVISIBLE_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor((Image)(new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB)),new java.awt.Point(0, 0), "INVISIBLE");
	
	private HashMap<UILayer, Boolean> 	shouldShow;
	private ArrayList<AWTUILayer>	  	uis;
	private AWTMouseUserDevice		  	mouse;
	
	//private SwingVKeyDriver   vKey;
	
	public AWTEditorPanel(AWTMouseUserDevice userDevice){
		setCursor(INVISIBLE_CURSOR);
		shouldShow 	= new HashMap<UILayer, Boolean>();
		uis   		= new ArrayList<AWTUILayer>();
		mouse 		= userDevice;
		
		addViewportMouseListener(mouse);
		addViewportMotionListener(mouse);
		setBackground(AWTGraphicData.BACKGROUND_COLOR);
		
		setDoubleBuffered(true);
	}
	
	public void addLayer 	(UILayer ui) { uis.add((AWTUILayer)ui);  		shouldShow.put((AWTUILayer)ui, true); }
	public void removeLayer (UILayer ui) { uis.remove((AWTUILayer)ui); 		shouldShow.put((AWTUILayer)ui, true); }
	public void toggleLayer (UILayer ui) { shouldShow.put((AWTUILayer)ui, ! shouldShow.get((AWTUILayer)ui)); }
	
	private boolean shouldShow(UILayer ui) { return shouldShow.get((AWTUILayer)ui); }
	
	@Override
    protected void paintComponent(Graphics g){		
		super.paintComponent(g);
		repaint();
		
		for(AWTUILayer ui : uis) {
			if (shouldShow(ui)) {
				ui.update(mouse);
				ui.render((Graphics2D)g);
			}
		}
		
		try {
			Thread.sleep(5); // Fixes really strange swing thread priority bug.
		} catch (Exception e) {}
    }
}
