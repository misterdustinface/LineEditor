package UI.AWT.obsolete;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;

public class AWTWorldEditorPopupMenu extends JPopupMenu implements MouseListener, ActionListener{
	private static final long serialVersionUID = 986665903708595315L;

	private Component currentComponent;

	public AWTWorldEditorPopupMenu(){
		super();
		currentComponent = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//Object source = event.getSource();
		
		// modify currentComponent
		// or modify worldShape at position
	}

	@Override
	public void mouseClicked(MouseEvent event) {
//		if(event.isPopupTrigger()){
//			currentComponent = event.getComponent();
//			show(currentComponent, event.getX(), event.getY());
//		}
	}
	@Override
	public void mouseEntered(MouseEvent event) {}
	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mousePressed(MouseEvent event) {
		if(event.isPopupTrigger()){
			currentComponent = event.getComponent();
			show(currentComponent, event.getX(), event.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if(event.isPopupTrigger()){
			currentComponent = event.getComponent();
			show(currentComponent, event.getX(), event.getY());
		}
	}

}
