package UI.AWT.obsolete;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class AWTWindowManager {

	private JDesktopPane desktop;
	private HashMap<Object, JInternalFrame> windows;
	
	public AWTWindowManager(final JDesktopPane DESKTOP){
		desktop = DESKTOP;
		windows = new HashMap<Object, JInternalFrame>();
	}
	
	private void addWindowToDesktopAndInitializeWindowAttributes(JInternalFrame window){
		
		window.addInternalFrameListener(new InternalFrameListener(){
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameClosing(InternalFrameEvent arg0) {
				removeWindowFromWindowManager(arg0.getInternalFrame());
			}
			@Override
			public void internalFrameDeactivated(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameDeiconified(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameIconified(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {}
		});
		
		window.setClosable(true);
		window.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		desktop.add(window);
		window.toFront();
	}
	
	private void removeWindowFromWindowManager(JInternalFrame window){
		for(Map.Entry<Object, JInternalFrame> pair : windows.entrySet()){
			if(pair.getValue().equals(window)){
				windows.remove(pair.getKey());
			}
		}
	}
	
	public void addWindow(Object key, JInternalFrame window){
		if(windows.containsKey(key)){
			windows.get(key).toFront();
		}else{
			windows.put(key, window);
			addWindowToDesktopAndInitializeWindowAttributes(window);
		}
	}
	public void removeWindow(Object key){
		desktop.remove(windows.remove(key));
	}
	
	public void setWindowPosition(Object key, int x, int y){
		JInternalFrame tempWindow = windows.remove(key);
		desktop.remove(tempWindow);
		tempWindow.setLocation(x, y);
		addWindow(key, tempWindow);
	}
	
	public void setWindowToFullscreenView(Object key){
		JInternalFrame tempWindow = windows.remove(key);
		desktop.remove(tempWindow);
		tempWindow.setLocation(0, 0);
		tempWindow.setSize(desktop.getWidth(), desktop.getHeight());
		addWindow(key, tempWindow);
	}
	
	public void setLookAndFeel(LookAndFeelInfo feel){
		try{
			UIManager.setLookAndFeel(feel.getClassName());
			SwingUtilities.updateComponentTreeUI(desktop);
		}catch(Exception e){
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(desktop);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {}
		}
	}

	public int getNumberOfWindows() { return windows.size(); }
}
 