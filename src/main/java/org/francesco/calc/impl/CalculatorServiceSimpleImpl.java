package org.francesco.calc.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.francesco.calc.api.CalculatorService;
import org.francesco.calc.api.ParsingCalculator;
import org.francesco.calc.api.ParsingException;

@Path("/simplecalc")
public class CalculatorServiceSimpleImpl implements CalculatorService {
	
	private ParsingCalculator calc;
	
	public CalculatorServiceSimpleImpl(ParsingCalculator calc) {
		checkNotNull(calc);
		this.calc = calc;
	}

	@Override
	public Response calculate(String formula) {
		checkNotNull(formula);
		Response r;
		try {
			Integer res = calc.calculate(formula);
			r = Response.ok(res).build();
		} catch (ParsingException e) {
			r = Response.status(400).entity("Error during parsing the formula").build();
		}
		return r;
	}

	

}
