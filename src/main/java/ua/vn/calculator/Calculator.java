package ua.vn.calculator;

import java.util.List;

public interface Calculator {
	
	public void reset();
	public void equal(Double number);
	
	public void add(Double number);
	public void sub(Double number);
	public void mul(Double number);
	public void div(Double number);
	
	public Double       getResult();
	public List<String> getHistory();

}
