package ua.vn.calculator;

import static ua.vn.calculator.Operations.OPERATION_ADD;
import static ua.vn.calculator.Operations.OPERATION_DIV;
import static ua.vn.calculator.Operations.OPERATION_MUL;
import static ua.vn.calculator.Operations.OPERATION_NOT_OPER;
import static ua.vn.calculator.Operations.OPERATION_SUB;

import java.util.ArrayList;
import java.util.List;

import ua.vn.calculator.Calculator;
import ua.vn.calculator.Operations;

import org.apache.log4j.Logger;


public class SimpleCalculator implements Calculator {
	
	private static final Logger LOG = Logger.getLogger( SimpleCalculator.class );
	
	private Double       result;
	private Operations   operation;
	private List<String> history;
	
	public SimpleCalculator() {
		LOG.debug("START_CONSTRUCTOR SimpleCalculator().");
		
		result    = 0.0;
		operation = OPERATION_NOT_OPER;
		history   = new ArrayList<String>();
		
		LOG.debug("END_CONSTRUCTOR SimpleCalculator().");
	}

	public void reset() {
		LOG.debug("START_METHOD reset().");

		result    = 0.0;
		operation = OPERATION_NOT_OPER;
		history.clear();
		
		LOG.info("Was pressed button [C] of calculator.");
		LOG.debug("END_METHOD reset().");
	}

	public void equal(Double number) {
		LOG.debug("START_METHOD equal().");

		calculate(number);
		operation = OPERATION_NOT_OPER;
		history.clear();

		LOG.info("Was pressed button [=] of calculator. Result is " + result);
		LOG.debug("END_METHOD equal().");
	}

	public void add(Double number) {
		LOG.debug("START_METHOD add().");
		
		if ( calculate(number) ) {
			operation = OPERATION_ADD;
			history.add("" + number + " + ");			
		}		
		
		LOG.info("Was pressed button [+] of calculator.");
		LOG.debug("END_METHOD add().");
	}

	public void sub(Double number) {
		LOG.debug("START_METHOD sub().");
		
		if ( calculate(number) ) {
			operation = OPERATION_SUB;
			history.add("" + number + " - ");			
		}
		
		LOG.info("Was pressed button [-] of calculator.");	
		LOG.debug("END_METHOD sub().");
	}

	public void mul(Double number) {
		LOG.debug("START_METHOD mul().");
		
		if ( calculate(number) ) {
			operation = OPERATION_MUL;
			history.add("" + number + " * ");
		}	
		
		LOG.info("Was pressed button [*] of calculator.");			
		LOG.debug("END_METHOD mul().");
	}

	public void div(Double number) {
		LOG.debug("START_METHOD div().");
		
		if ( calculate(number) ) {
			operation = OPERATION_DIV;
			history.add("" + number + " / ");
		}	
		
		LOG.info("Was pressed button [/] of calculator.");		
		LOG.debug("END_METHOD div().");
	}

	public Double getResult() {
		return result;
	}

	public List<String> getHistory() {
		return history;
	}
	
	private boolean calculate(Double number) {
		LOG.debug("START_METHOD calculate().");
		
		boolean ret = true;
		
		switch ( operation ) {
		
			case OPERATION_ADD: 
				{ 
					result += number;
					LOG.info("Execute operation ADD for number < " + number + " >. Result operation is " + result);					
					break;
				}
			
			case OPERATION_SUB:      
				{
					result -= number;
					LOG.info("Execute operation SUB for number < " + number + " >. Result operation is " + result);					
					break;
				}

			case OPERATION_MUL:      
				{
					result *= number;
					LOG.info("Execute operation MUL for number < " + number + " >. Result operation is " + result);					
					break; 
				}
		
			case OPERATION_DIV:
				{
					if ( number == 0.0 ) {
						LOG.warn("Opearation DIV by zero is not illegal.");
						ret = false;
						break;
					}
					result /= number;
					LOG.info("Execute operation DIV for number < " + number + " >. Result operation is " + result);					
					break; 
				}
		
			case OPERATION_NOT_OPER:
				{
					result = number;
					LOG.info("Initialization calculation. Result is " + result);					
					break;
				}
		
			default: 
				{
					LOG.warn("Execute operation which not support this calculator!");
					break;
				}
		}		
		LOG.debug("END_METHOD calculate().");
		
		return ret;
	}

}
