package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class BooleanModel {

	private boolean response;
	
	public BooleanModel() {}

	public boolean getResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}
}
