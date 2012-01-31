package org.francesco.calc.impl;

import static org.parboiled.errors.ErrorUtils.printParseErrors;

import org.francesco.calc.api.ParsingCalculator;
import org.francesco.calc.api.ParsingException;
import org.francesco.calc.impl.parser.CalculatorParser;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.ErrorLocatingParseRunner;
import org.parboiled.support.ParsingResult;

public class ParsingCalculatorImpl implements ParsingCalculator {
	
	CalculatorParser parser;
	
	public ParsingCalculatorImpl() {
		parser = Parboiled.createParser(CalculatorParser.class);
	}

	@Override
	public Integer calculate(String formula) throws ParsingException {
		ParsingResult<Integer> result = new ErrorLocatingParseRunner<Integer>(parser.InputLine()).run(formula);
		
		if (result.hasErrors()) {
			throw new ParsingException("Parsing of the expression has failed: " + printParseErrors(result));
		}
		
		return result.parseTreeRoot.getValue();
		
		
	}

}
