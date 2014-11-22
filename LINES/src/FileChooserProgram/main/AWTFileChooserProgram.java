package FileChooserProgram.main;


import AWT.UI.AWTDefaultMouseUserDevice;
import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTFileChooser;
import AWT.UI.AWTMouseUserDevice;
import AWT.UI.AWTProgramWindow;
import AWT.UI.AWTSimpleUserDeviceDisplayLayer;

public class AWTFileChooserProgram {
	
	public static void main(String[] args) {
		AWTProgramWindow window = new AWTProgramWindow("File Chooser");
		window.setSize(500, 400);

		AWTFileChooser 		fileBrowser = new AWTFileChooser();
		AWTMouseUserDevice 	userDevice 	= new AWTDefaultMouseUserDevice();
		AWTEditorPanel 		editorPanel = new AWTEditorPanel(userDevice);
		
		editorPanel.addLayer(fileBrowser);
		editorPanel.addLayer(new AWTSimpleUserDeviceDisplayLayer(userDevice));
		window.add(editorPanel);
		window.revalidate();
		
		fileBrowser.chooseFile();
	}

}
