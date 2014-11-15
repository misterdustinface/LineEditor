package request;

public abstract class RequestibleAction implements Requestible{
	
	public RequestibleAction(){}
	
	@Override
	final public void request(){
		if(shouldAcceptRequest()){
			performAction();
		}
	}
	
	abstract protected boolean 	shouldAcceptRequest();
	abstract protected void		performAction();
}
