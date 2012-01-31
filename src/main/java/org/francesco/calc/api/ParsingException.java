package org.francesco.calc.api;

public class ParsingException extends Exception {

	private static final long serialVersionUID = 96045665345924254L;
	
	public ParsingException(String reason) {
		super(reason);
	}
	
	public ParsingException(Throwable e) {
		super(e);
	}

}
