package input.keys;

import input.vKey.VirtualKeyPressedListener;
import input.vKey.VirtualKeyReleasedListener;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class test {
	public static void main(String [] args) {
		JFrame f = new JFrame();

		SwingVKeyDriver vKeys = new SwingVKeyDriver();
		vKeys.mapVKey("test1", KeyEvent.VK_A);
		vKeys.mapVKey("test2", KeyEvent.VK_S);
		vKeys.mapVKey("test2", KeyEvent.VK_D);

		vKeys.setVKeyFirstPressedListener("test1", new VirtualKeyPressedListener() {
			@Override
			public void keyPressed() {
				System.out.println("test 1 pressed");
			}
		});
		
		vKeys.setVKeyFirstPressedListener("test2", new VirtualKeyPressedListener() {
			@Override
			public void keyPressed() {
				System.out.println("test 2 pressed");
			}
		});
		
		vKeys.addVKeyReleasedListener("test1", new VirtualKeyReleasedListener() {
			@Override
			public void keyReleased() {
				System.out.println("test 1 relased");
			}
		});
		
		vKeys.addVKeyReleasedListener("test2", new VirtualKeyReleasedListener() {
			@Override
			public void keyReleased() {
				System.out.println("test 2 relased");
			}
		});
		
		f.addKeyListener(vKeys);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(100, 100);
		f.setVisible(true);
	}
}
