package AWT.UI;


import java.awt.Dimension;

import javax.swing.JFrame;

final public class AWTProgramWindow extends JFrame {

	private static final long serialVersionUID = -3484849588202585389L;
	final public static Dimension MIN_SIZE 	   = new Dimension(400, 300);

	public AWTProgramWindow(String title){
		super(title);
		//setupLookAndFeel();
		setupSelf();
	}
	
//	private void setupLookAndFeel(){
//		try{
//			for(LookAndFeelInfo feel : UIManager.getInstalledLookAndFeels()){
//				if(feel.getName() == "Nimbus"){
//					UIManager.setLookAndFeel(feel.getClassName());
//					SwingUtilities.updateComponentTreeUI(this);
//				}
//			}
//		}catch(Exception e){
//			try {
//				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				SwingUtilities.updateComponentTreeUI(this);
//			} catch ( ClassNotFoundException | InstantiationException
//					| IllegalAccessException | UnsupportedLookAndFeelException e1) {}
//		}
//	}
	
	private void setupSelf(){
		super.setResizable(true);
		super.setMinimumSize(MIN_SIZE);
		super.setSize(620, 400);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
	}

}
