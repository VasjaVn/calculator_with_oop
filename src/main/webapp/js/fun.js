var isWasPrintComma = false;

/**
 * 
 */
function printDigit(digit) {
	var txtNumber = document.forms["frmCalc"]["txtNumber"].value;
	document.forms["frmCalc"]["txtNumber"].value = txtNumber + digit;
}

/**
 * 
 */
function printComma() {
	if ( !isWasPrintComma ) {
		var str = document.forms["frmCalc"]["txtNumber"].value;
		document.forms["frmCalc"]["txtNumber"].value = str + ".";
		isWasPrintComma = true;
	}
}

/**
 * 
 */
function backspace() {
	var strNumber = new String( document.forms["frmCalc"]["txtNumber"].value );
	
	if ( strNumber.charAt(strNumber.length - 1) == '.' ) {
		isWasPrintComma = false;
	}
	
	if ( strNumber != 1 ) {
		document.forms["frmCalc"]["txtNumber"].value = strNumber.substring(0, strNumber.length - 1);
		
	} else {
		document.forms["frmCalc"]["txtNumber"].value = "";
	}
}