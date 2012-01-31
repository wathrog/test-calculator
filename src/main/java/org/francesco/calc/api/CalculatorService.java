package org.francesco.calc.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public interface CalculatorService {
	
	@GET
	@Path("/calculate")
    @Produces("text/plain")
	public Response calculate(@QueryParam("formula") String formula);
	

}
