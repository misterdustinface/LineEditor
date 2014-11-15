package main;

final public class ProgramLauncher {

	private ProgramLauncher(){}
	
	private static Program program;
	
	public static void main(String[] args){
		program = new AWTColorPaletteEditorProgram();
		program.launch();
		//program = new AWTLineEditorProgram();
		//program.launch();
	}
}
