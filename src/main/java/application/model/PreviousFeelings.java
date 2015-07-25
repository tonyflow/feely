package application.model;

import java.io.Serializable;
import java.util.List;

public class PreviousFeelings implements Serializable {

	private static final long serialVersionUID = -4548065843528750468L;
	private List<String> previousFeelings;

	public PreviousFeelings() {
	}

	public PreviousFeelings(List<String> previousFeelings) {
		super();
		this.previousFeelings = previousFeelings;
	}

	public List<String> getPreviousFeelings() {
		return previousFeelings;
	}

	public void setPreviousFeelings(List<String> previousFeelings) {
		this.previousFeelings = previousFeelings;
	}

}
