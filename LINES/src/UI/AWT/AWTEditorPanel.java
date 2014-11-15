package UI.AWT;


import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import data.graphics.AWTGraphicData;
import UI.UILayer;

public class AWTEditorPanel extends Viewport{
	
	private static final long serialVersionUID = -4178489165743436644L;

	final public static Cursor INVISIBLE_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor((Image)(new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB)),new java.awt.Point(0, 0), "INVISIBLE");
	
	private HashMap<UILayer, Boolean> shouldShow;
	private ArrayList<UILayer>		 uis;
	private AWTMouseUserDevice		 mouse;
	
	//private SwingVKeyDriver   vKey;
	
	public AWTEditorPanel(AWTMouseUserDevice userDevice){
		setCursor(INVISIBLE_CURSOR);
		shouldShow 	= new HashMap<UILayer, Boolean>();
		uis   		= new ArrayList<UILayer>();
		mouse 		= userDevice;
		
		addViewportMouseListener(mouse);
		addViewportMotionListener(mouse);
		setBackground(AWTGraphicData.BACKGROUND_COLOR);
	}
	
	public void addLayer 	(UILayer ui) { uis.add(ui);    shouldShow.put(ui, true); }
	public void removeLayer (UILayer ui) { uis.remove(ui); shouldShow.put(ui, true); }
	public void toggleLayer (UILayer ui) { shouldShow.put(ui, ! shouldShow.get(ui)); }
	
	private boolean shouldShow(UILayer ui) { return shouldShow.get(ui); }
	
	@Override
    protected void paintComponent(Graphics g){
		super.paintComponent(g);
		repaint();
		for(UILayer ui : uis) {
			if (shouldShow(ui)) {
				ui.update(mouse);
				ui.render((Graphics2D)g);
			}
		}
    }
}
