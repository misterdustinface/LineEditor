package generic;

public abstract class DataModificationListener {
	final public void notifyListenerAboutDataModification() { whenMyDataIsModifiedExternally(); }
	protected abstract void whenMyDataIsModifiedExternally();
}
