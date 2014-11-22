package AWT.UI;


public class AWTTestButton extends AWTMenuButton {

	@Override
	protected void pressAction() {
		textLabel.setText("PRESSED");
	}

	@Override
	protected void releaseAction() {
		textLabel.setText("RELEASED");
	}

}
