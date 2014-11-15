package generic;

import java.util.ArrayList;

public class DataModificationNotifier implements Notifier {

	private ArrayList<DataModificationListener> dataModificationListeners;
	
	public DataModificationNotifier() {
		dataModificationListeners = new ArrayList<DataModificationListener>();
	}
	
	final public void addDataModificationListener(DataModificationListener listener) {
		dataModificationListeners.add(listener);
	}
	
	final public void notifyListeners() {
		for(DataModificationListener listener : dataModificationListeners) {
			listener.notifyListenerAboutDataModification();
		}
	}
}
