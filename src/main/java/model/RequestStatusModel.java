package model;

public class RequestStatusModel {
	private boolean requestStatus;
	
	public RequestStatusModel() {}
	
	public RequestStatusModel(boolean requestStatus) {
		this.set(requestStatus);
	}

	public boolean get() {
		return requestStatus;
	}

	public void set(boolean requestStatus) {
		this.requestStatus = requestStatus;
	}
}
