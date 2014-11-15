package file;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public interface FileBus {
	public void load(FileInputStream reader);
	public void save(FileOutputStream writer);
}
