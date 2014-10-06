package ua.vn.servlet;

import static ua.vn.servlet.Attributes.ATTRIBUTE_HISTORY;
import static ua.vn.servlet.Attributes.ATTRIBUTE_NUMBER;
import static ua.vn.servlet.Attributes.ATTRIBUTE_RESULT;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.vn.processor.InitProcessorException;
import ua.vn.processor.Processor;

public class ServletCalc extends HttpServlet {

	private static final Logger LOG = Logger.getLogger( ServletCalc.class );

	private static final long serialVersionUID = 1L;
	
	private Processor processor;
	
	
	public ServletCalc() {
		LOG.debug("START_CONSTRUCTOR: ServletCalc().");
		
		this.processor = Processor.getInstance();
		
		LOG.debug("END_CONSTRUCTOR: ServletCalc().");
	}
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		LOG.debug("START_METHOD: init().");
		
		try {
			LOG.info("Initialize processor.");
			processor.init();
			
		} catch (InitProcessorException e) {
			LOG.error("Can not initialize processor.");
			throw new ServletException();
		}
		
		super.init(config);
		
		LOG.debug("END_METHOD: init().");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("START_METHOD: doPost().");
		
		LOG.info("Set data for calcualate.");
		processor.setDataForCalculate(request);
		
		LOG.info("Execute calculate.");
		processor.executeCalculate();
		
		LOG.info("Set attributes.");
		setAttributes(request);
		
		LOG.info("Forward request to \"index.jsp\".");
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
		LOG.debug("END_METHOD: doPost().");
	}
	
	
	private void setAttributes(HttpServletRequest request) {
		LOG.debug("START_METHOD: setAttributes().");
		
		LOG.info("Set attribute HISTORY.");
		setAttributeHistory(request);
		
		LOG.info("Set attribute NUMBER.");
		setAttributeNumber(request);
		
		LOG.info("Set attribute RESULT.");
		setAttributeResult(request);

		LOG.debug("END_METHOD: setAttributes().");
	}
	
	
	private void setAttributeHistory(HttpServletRequest request) {
		LOG.debug("START_METHOD: setAttributeHistory().");
		
		StringBuilder builder = new StringBuilder();
		List<String> list     = processor.getHistory();		
		
		for (String element : list) {
			builder.append(element);
		}
		
		String value = builder.toString();
		request.setAttribute(ATTRIBUTE_HISTORY, value );
		LOG.info("Attribute " + ATTRIBUTE_HISTORY + "=" + value);

		LOG.debug("END_METHOD: setAttributeHistory().");
	}

	
	private void setAttributeNumber(HttpServletRequest request) {
		LOG.debug("START_METHOD: setAttributeNumber().");
		
		String value = "";
		request.setAttribute(ATTRIBUTE_NUMBER, value);
		LOG.info("Attribute " + ATTRIBUTE_NUMBER + "=" + value);

		LOG.debug("END_METHOD: setAttributeNumber().");
	}

	
	private void setAttributeResult(HttpServletRequest request) {
		LOG.debug("START_METHOD: setAttributeResult().");
		
		Double value = processor.getResult();
		request.setAttribute(ATTRIBUTE_RESULT, value);
		LOG.info("Attribute " + ATTRIBUTE_RESULT + "=" + value);
		
		LOG.debug("END_METHOD: setAttributeResult().");
	}

}
