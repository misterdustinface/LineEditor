package UI.AWT;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import file.LuaScriptFiler;

public class AWTFileMenu extends Menu {

	private static final long serialVersionUID = 8358609608587159275L;
	private LuaScriptFiler 	filer;
	private JPanel 			panel;
	private JFileChooser 	exporter;
	private JFileChooser 	loader;

	public static String SAVE_STRING = "Save";
	public static String LOAD_STRING = "Load";
	
	public AWTFileMenu(LuaScriptFiler FILER, JPanel PANEL) {
		super("File");
		filer = FILER;
		panel = PANEL;
		setup();
	}
	
	private void setup() {
		
		MenuItem save = new MenuItem(SAVE_STRING);
		MenuItem load = new MenuItem(LOAD_STRING);
		
		exporter 				= new JFileChooser();
		exporter.setApproveButtonText(SAVE_STRING);
		exporter.setApproveButtonMnemonic(SAVE_STRING.charAt(0));
		exporter.setFileHidingEnabled(true);
		
		loader					= new JFileChooser();
		loader.setApproveButtonText(LOAD_STRING);
		loader.setApproveButtonMnemonic(LOAD_STRING.charAt(0));
		loader.setFileHidingEnabled(true);
		
		save.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(exporter.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION){
						filer.save(new FileOutputStream(exporter.getSelectedFile()));
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		});
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) { 
				try {
					if(loader.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION){
						filer.load(new FileInputStream(loader.getSelectedFile()));
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}	
			}
			
		});
		
		add(save);
		add(load);
	}
}
