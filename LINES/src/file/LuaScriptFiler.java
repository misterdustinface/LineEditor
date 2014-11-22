package file;

import generic.DataModificationNotifier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class LuaScriptFiler extends DataModificationNotifier implements FileBus {
	final private static String INDENT          = "  ";
	final private static String TABLE_START     = Character.toString('{');
	final private static String TABLE_END       = Character.toString('}');
	final private static String TABLE_DELIMITER = Character.toString(',');
	final private static String NEWLINE         = System.lineSeparator();
	final private static String COMMENT         = "--";
	final private static String IGNORE_LINE     = COMMENT;
	
	final protected String scriptHeading(String title) {
		return "return " + TABLE_START + IGNORE_LINE + " " + title +  NEWLINE;
	}
	
	final protected String scriptCloser(String lastComment) {
		return TABLE_END + IGNORE_LINE + " " + lastComment;
	}
	
	final protected String entryStart() {
		return INDENT + TABLE_START;
	}
	
	final protected String entryEnd() {
		return TABLE_END + TABLE_DELIMITER + NEWLINE;
	}
	
	final protected String buildEntryList(String ... strings) {
		String entrylist = new String();
		for(int i = 0; i < strings.length - 1; ++i) {
			entrylist += strings[i];
			entrylist += TABLE_DELIMITER + " ";
		}
		return entrylist + strings[strings.length - 1];
	}
	
	final protected String createEntry(String ... strings) {
		return entryStart() + buildEntryList(strings) + entryEnd();
	}
	
	final protected String[] parseEntries(String line) {
		ArrayList<String> entries = new ArrayList<String>();
		int startIndex 	= line.indexOf(TABLE_START) + 1;
		int endIndex 	= line.indexOf(TABLE_DELIMITER);
		
		while(endIndex > 0) {
			entries.add(line.substring(startIndex, endIndex).trim());
			startIndex 	= endIndex + 1;
			endIndex 	= line.indexOf(TABLE_DELIMITER, startIndex);
		}
		
		String lastEntry = entries.get(entries.size()-1);
		entries.set(entries.size()-1, lastEntry.substring(0, lastEntry.indexOf(TABLE_END)).trim() );
		
		return entries.toArray(new String[] {});
	}
	
	final protected void parseScript(FileInputStream reader) {
		Scanner scanner = new Scanner(reader);
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			if(! line.contains(IGNORE_LINE)) {
				parseLine(line);
			}
		}
		scanner.close();
	}
	
	@Override
	final public void load(FileInputStream reader) {
		preparseOperation();
		parseScript(reader);
		postparseOperation();
		notifyListeners();
	}
	
	@Override
	final public void save(FileOutputStream writer) {
		PrintWriter out = new PrintWriter(writer);
		out.write(dataToLuaScript());
		out.close();
	}
	
	protected abstract void preparseOperation();
	protected abstract void postparseOperation();
	protected abstract void parseLine(String line); 
	protected abstract String dataToLuaScript();
}
