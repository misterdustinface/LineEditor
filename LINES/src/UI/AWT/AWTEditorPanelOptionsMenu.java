package UI.AWT;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AWTEditorPanelOptionsMenu extends Menu {

	private static final long serialVersionUID = -3207544261732311004L;	
	private AWTEditorPanel editorPanel;
	
	public AWTEditorPanelOptionsMenu(AWTEditorPanel EDITOR_PANEL) {
		super("Options");
		editorPanel = EDITOR_PANEL;
	}
	
	public void addMenuItemToggleUI(String title, final AWTUILayer ui) {
		MenuItem item = new MenuItem(title);
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				editorPanel.toggleLayer(ui);
			}
		});
		add(item);
	}	
}
