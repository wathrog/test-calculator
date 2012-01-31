package org.francesco.calc.api;

import javax.ws.rs.core.Response;

public interface CalculatorService {
	
	
	public Response calculate(String formula);
	

}
