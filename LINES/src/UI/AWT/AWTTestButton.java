package UI.AWT;


public class AWTTestButton extends AWTMenuButton {

	@Override
	protected void pressAction() {
		text = "PRESSED";
	}

	@Override
	protected void releaseAction() {
		text = "RELEASED";
	}

}
