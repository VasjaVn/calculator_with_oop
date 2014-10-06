package ua.vn.processor;

import static ua.vn.calculator.Operations.OPERATION_ADD;
import static ua.vn.calculator.Operations.OPERATION_C;
import static ua.vn.calculator.Operations.OPERATION_CE;
import static ua.vn.calculator.Operations.OPERATION_DIV;
import static ua.vn.calculator.Operations.OPERATION_EQUAL;
import static ua.vn.calculator.Operations.OPERATION_MUL;
import static ua.vn.calculator.Operations.OPERATION_NOT_OPER;
import static ua.vn.calculator.Operations.OPERATION_ONE_DIV_X;
import static ua.vn.calculator.Operations.OPERATION_PERCENT;
import static ua.vn.calculator.Operations.OPERATION_SQRT;
import static ua.vn.calculator.Operations.OPERATION_SUB;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_ADD;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_C;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_CE;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_DIV;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_EQUAL;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_MUL;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_ONE_DIV_X;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_PERCENT;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_SQRT;
import static ua.vn.servlet.Parameters.PARAMETER_BTN_SUB;
import static ua.vn.servlet.Parameters.PARAMETER_TXT_NUMBER;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.vn.calculator.Calculator;
import ua.vn.calculator.Operations;
import ua.vn.calculator.SimpleCalculator;


public class Processor {
	
	private static final Logger LOG = Logger.getLogger( Processor.class );
	
	private static final String PROPERTIES_PROCESSOR_CALCULATOR = "processor.calculator";
	
	private static final String DEFAULT_PROCESSOR_CALCULATOR    = "SimpleCalculator";	
	
	private static Processor instance;
	
	public static Processor getInstance() {
		LOG.debug("START_METHOD getInstance().");		
		
		if ( instance == null ) {
			instance = new Processor();
		}
		LOG.debug("END_METHOD getInstance().");		

		return instance;
	}
	
	private Calculator         calculator;
	private HttpServletRequest request;
	private boolean            isSetData;
	private Properties         properties;
	
	public Processor() {	
		LOG.debug("START_CONSTRUCTOR Processor().");
		
		this.properties = new Properties();

		LOG.debug("END_CONSTRUCTOR Processor().");
	}
	
	
	public void init() throws InitProcessorException {		
		LOG.debug("START_METHOD init().");
		
		try {
			LOG.info("Loading properties from file: \"application.properties\".");
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
			
		} catch (IOException e) {
			LOG.error("Cannot load file \"application.properties\" wiht properties.");
			throw new InitProcessorException();
		}
		
		LOG.info("Get property: " + PROPERTIES_PROCESSOR_CALCULATOR);
		String value = properties.getProperty(PROPERTIES_PROCESSOR_CALCULATOR, DEFAULT_PROCESSOR_CALCULATOR);
		LOG.info("Property " + PROPERTIES_PROCESSOR_CALCULATOR + " = " + value);
		
		if (value.equals("SimpleCalculator")) {
			this.calculator = new SimpleCalculator();
			LOG.info("Processor set calculator: " + value + ".");
			
		} else {
			LOG.error("Processor not support this calculator: " + value);
			throw new InitProcessorException();
		}
		LOG.debug("END_METHOD init().");
	}
	
	
	public void setDataForCalculate( HttpServletRequest request ) {		
		LOG.debug("START_METHOD setDataForCalculate().");
		
		this.request   = request;
		this.isSetData = true;
		
		LOG.debug("END_METHOD setDataForCalculate().");
	}
	
	
	public void executeCalculate() {		
		LOG.debug("START_METHOD executeCalculate().");
		
		if ( isSetData ) {
			
			LOG.info("Processor executing calculation.");
			
			Operations operation = getOperation();
			
			if ( operation == OPERATION_C ) {
				calculator.reset();
				
			} else {	
				String strNumber = request.getParameter(PARAMETER_TXT_NUMBER);
				
				LOG.debug("Number < " + strNumber + " > for calculate.");
				
				if ( !strNumber.isEmpty() ) {
					
					Double number = new Double(strNumber);
					
					switch ( operation ) {	
					
						case OPERATION_ADD:
							{
								LOG.info("Processor delegation operation ADD to calculator.");
								calculator.add(number);
								break; 
							}

						case OPERATION_SUB:   
							{
								LOG.info("Processor delegation operation SUB to calculator.");
								calculator.sub(number);
								break;
							}

						case OPERATION_MUL: 
							{
								LOG.info("Processor delegation operation MUL to calculator.");
								calculator.mul(number);
								break;
							}

						case OPERATION_DIV:
							{
								LOG.info("Processor delegation operation DIV to calculator.");
								calculator.div(number);
								break;
							}

						case OPERATION_EQUAL:
							{
								LOG.info("Processor delegation operation EQUAL to calculator.");
								calculator.equal(number);
								break;
							}
						
						case OPERATION_CE:
							{
								LOG.info("Processor work operation CE.");
								break;
							}
						
						default:
							{
								LOG.warn("Illegal operation.");
								break;
							}
					}
				}
			}	
			
			isSetData = false;	
			
		}
		LOG.debug("END_METHOD executeCalculate().");
	}
	
	
	public double getResult() {
		
		return calculator.getResult();
	}
	
	
	public List<String> getHistory() {
		
		return calculator.getHistory();
	}
	
	
	private Operations getOperation() {	
		LOG.debug("START_METHOD getOperation().");
		
		Operations operation = OPERATION_NOT_OPER;
	
		if ( request.getParameter(PARAMETER_BTN_ADD) != null ) {
			operation = OPERATION_ADD;
			LOG.info("Operation ADD.");

		} else if ( request.getParameter(PARAMETER_BTN_SUB) != null ) {
			operation = OPERATION_SUB;
			LOG.info("Operation SUB.");
			
		} else if ( request.getParameter(PARAMETER_BTN_MUL) != null ) {
			operation = OPERATION_MUL;
			LOG.info("Operation MUL.");
	
		} else if ( request.getParameter(PARAMETER_BTN_DIV) != null ) {
			operation = OPERATION_DIV;
			LOG.info("Operation DIV.");
			
		} else if ( request.getParameter(PARAMETER_BTN_SQRT) != null ) {
			operation = OPERATION_SQRT; //not support
			LOG.info("Operation SQRT. //NOT SUPPORT//");
			
		} else if ( request.getParameter(PARAMETER_BTN_PERCENT) != null ) {
			operation = OPERATION_PERCENT; //not support
			LOG.info("Operation PERCENT. //NOT SUPPORT//");
			
		} else if ( request.getParameter(PARAMETER_BTN_ONE_DIV_X) != null ) {
			operation = OPERATION_ONE_DIV_X; //not support
			LOG.info("Operation ONE_DIV_X. //NOT SUPPORT//");
			
		} else if ( request.getParameter(PARAMETER_BTN_EQUAL) != null ) {
			operation = OPERATION_EQUAL;
			LOG.info("Operation EQUAL.");
			
		} if ( request.getParameter(PARAMETER_BTN_CE) != null ) {
			operation = OPERATION_CE;
			LOG.info("Operation CE.");
			
		} else if ( request.getParameter(PARAMETER_BTN_C) != null ) {
			operation = OPERATION_C;
			LOG.info("Operation C.");
			
		}	
		LOG.debug("END_METHOD getOperation().");
		
		return operation;
	}

}
