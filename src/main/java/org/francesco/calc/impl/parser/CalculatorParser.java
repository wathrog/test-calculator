package org.francesco.calc.impl.parser;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.annotations.SuppressSubnodes;

@BuildParseTree
public class CalculatorParser extends BaseParser<Integer> {
	
    public Rule InputLine() {
        return Sequence(Expression(), EOI);
    }

    public Rule Expression() {
        return Sequence(
                Term(), // a successful match of a Term pushes one Integer value onto the value stack
                ZeroOrMore(
                        FirstOf(
                                // the action that is run after the '+' and the Term have been matched consumes the
                                // two top value stack elements and replaces them with the calculation result
                                Sequence('+', Term(), push(pop() + pop())),

                                // same for the '-' operator, however, here the order of the "pop"s matters, we need to
                                // retrieve the second to last value first, which is what the pop(1) call does
                                Sequence('-', Term(), push(pop(1) - pop()))
                        )
                )
        );
    }

    public Rule Term() {
        return Sequence(
                Factor(), // a successful match of a Factor pushes one Integer value onto the value stack
                ZeroOrMore(
                        FirstOf(
                                // the action that is run after the '*' and the Factor have been matched consumes the
                                // two top value stack elements and replaces them with the calculation result
                                Sequence('*', Factor(), push(pop() * pop())),

                                // same for the '/' operator, however, here the order of the "pop"s matters, we need to
                                // retrieve the second to last value first, which is what the pop(1) call does
                                Sequence('/', Factor(), push(pop(1) / pop()))
                        )
                )
        );
    }

    public Rule Factor() {
        return FirstOf(Number(), Parens()); // a factor "produces" exactly one Integer value on the value stack
    }

    public Rule Parens() {
        return Sequence('(', Expression(), ')');
    }

    public Rule Number() {
        return Sequence(
                Digits(),

                // parse the input text matched by the preceding "Digits" rule,
                // convert it into an Integer and push it onto the value stack
                // the action uses a default string in case it is run during error recovery (resynchronization)
                push(Integer.parseInt(matchOrDefault("0")))
        );
    }

    @SuppressSubnodes
    public Rule Digits() {
        return OneOrMore(Digit());
    }

    public Rule Digit() {
        return CharRange('0', '9');
    }

}
