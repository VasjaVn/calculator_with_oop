<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form name="frmCalc" method="post" action="srvltCalc" onSubmit="">
	<table class="outer">

		<!----------------------------- DISPLAY ---------------------------------//-->

		<tr>
			<td colspan="5">
				<table class="inner">
					<tr>
						<td colspan="4">
							<input type="text"
						    	   name="txtHISTORY"
							       id="txtHistory"
						    	   size="43"
						       	   value="<%if ( request.getAttribute("History") != null ) {
						       		   			out.print( request.getAttribute("History") );
						       		   		}%>"
					  		  	   readonly />
				
						</td>
					</tr>
			
					<tr>
						<td class="label">
							Enter:
						</td>
						<td>
							<input type="text"
							       name="txtNUMBER"
						    	   id="txtNumber"
							       size="8"
						    	   value="<%if ( request.getAttribute("Number") != null ) {
						    		   			out.print( request.getAttribute("Number") );
						    		   		}%>"
							       readonly />
						</td>
						<td class="label">
							Result:
						</td>
						<td>
							<input type="text"
							       name="txtRESULT"
						    	   id="txtResult"
							       size="13"
						    	   value="<%if ( request.getAttribute("Result") != null ) {
						    		   			out.print( request.getAttribute("Result") );
						    		   		}%>"
							       readonly />
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<!--------------------------------- BUTTONS ----------------------------//-->
		<!------------------------ [<-]  [CE]  [C] [SQRT] ----------------------//-->
		
		<tr>
			<td colspan="2">
				<button class="bigWidth" type="button" name="btnLARR" onClick="backspace();">&larr;</button>
			</td>
			<td>
				<button type="submit" name="btnCE" onClick="">CE</button>
			</td>
			<td>
				<button type="submit" name="btnC" onClick="">C</button>
			</td>
			<td>
				<button type="button" name="btnSQRT" onClick="">&radic;</button>
			</td>	
		</tr>

		<!--------------------------------- BUTTONS ----------------------------//-->
		<!------------------------ [7]  [8]  [9] [/]  [%] ----------------------//-->
		
		<tr>
			<td>
				<button type="button" name="btn7" onClick="printDigit(7);">7</button>
			</td>
			<td>
				<button type="button" name="btn8" onClick="printDigit(8);">8</button>
			</td>
			<td>
				<button type="button" name="btn9" onClick="printDigit(9);">9</button>
			</td>
			<td>
				<button type="submit" name="btnDIV" onClick="">/</button>
			</td>
			<td>
				<button type="button" name="btnPERCENT" onClick="">%</button>
			</td>			
		</tr>

		<!---------------------------------- BUTTONS -----------------------------//-->
		<!------------------------ [4]  [5]  [6] [*]  [1/x] ----------------------//-->
		
		<tr>
			<td>
				<button type="button" name="btn4" onClick="printDigit(4);">4</button>
			</td>
			<td>
				<button type="button" name="btn5" onClick="printDigit(5);">5</button>
			</td>
			<td>
				<button type="button" name="btn6" onClick="printDigit(6);">6</button>
			</td>
			<td>
				<button type="submit" name="btnMUL" onClick="">*</button>
			</td>
			<td>
				<button type="button" name="btn1DIVX" onClick="">1/x</button>
			</td>			
		</tr>

		<!--------------------------------- BUTTONS ----------------------------//-->
		<!------------------------ [1]  [2]  [3] [-]  [=] ----------------------//-->
		
		<tr>
			<td>
				<button type="button" name="btn1" onClick="printDigit(1);">1</button>
			</td>
			<td>
				<button type="button" name="btn2" onClick="printDigit(2);">2</button>
			</td>
			<td>
				<button type="button" name="btn3" onClick="printDigit(3);">3</button>
			</td>
			<td>
				<button type="submit" name="btnSUB" onClick="">-</button>
			</td>
			<td rowspan="2">
				<button class="bigHeight" type="submit" name="btnEQUAL" onClick="">=</button>
			</td>			
		</tr>

		<!--------------------------------- BUTTONS ----------------------------//-->
		<!----------------------------- [0]  [,]  [+] --------------------------//-->
		
		<tr>
			<td colspan="2">
				<button class="bigWidth" type="button" name="btn0" onClick="printDigit(0);">0</button>
			</td>
			<td>
				<button type="button" name="btnCOMMA" onClick="printComma();">,</button>
			</td>
			<td>
				<button type="submit" name="btnADD" onClick="">+</button>
			</td>
		</tr>
			
	</table>
</form>
