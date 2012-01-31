package org.francesco.calc.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.francesco.calc.api.CalculatorService;
import org.francesco.calc.api.ParsingCalculator;
import org.francesco.calc.api.ParsingException;

@Path("/simplecalc")
public class CalculatorServiceSimpleImpl implements CalculatorService {
	
	private ParsingCalculator calc;
	
	public CalculatorServiceSimpleImpl() {
	}
	
	public void setCalc(ParsingCalculator calc) {
		checkNotNull(calc);
		this.calc = calc;
	}

	@Override
	@GET
	@Path("/calculate")
    @Produces("text/plain")
	public Response calculate(@QueryParam("formula") String formula) {
		checkNotNull(calc, "Calculator implementation has not been injected");
		try {
			checkNotNull(formula, "Formula is not passed correctly");
		} catch (NullPointerException e) {
			return Response.status(400).entity("Can not parse an empty formula").build();
		}
		Response r;
		try {
			Integer res = calc.calculate(formula);
			r = Response.ok(res).build();
		} catch (ParsingException e) {
			r = Response.status(400).entity("Error during parsing the formula: "  + e.getLocalizedMessage()).build();
		}
		return r;
	}
}
