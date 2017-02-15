/**
 * Limpa espaços em branco antes e depois da string
 */
		
function maskPhoneNumber(value){
	
		var number = "";

		number = value.strip().gsub('[^0-9]','');
		var l = number.length;
		
		if(l == 1)
			number = '(' + number;
	
		if(l == 2)
			number = '(' + number + ')';
		
		if(l > 2 && l < 6)
			number = number = '(' + number.substring(0,2) + ')' + number.substring(2,l);
	
		if(l == 6)
			number = '(' + number.substring(0,2) + ')' + number.substring(2,6) + '-';
				
		if(l > 6 && l < 11){
			number = "(" + number.substring(0, 2) + ")" + number.substring(2, 6) + "-" + number.substring(6);
		}
		
		if(l >= 11){
			number = "(" + number.substring(0, 2) + ")" + number.substring(2, 7) + "-" + number.substring(7, 11);
		}
		
		return number;
}

function formatPhoneNumber(value){
	
	var number = "";
	
	if(value != ""){
		
		number = value.strip().gsub('[^0-9]','');
		var l = number.length;
		
		if(l > 0){
			if(l == 10)	
				number = "(" + number.substring(0, 2) + ")" + number.substring(2, 6) + "-" + number.substring(6);
			
            else if(l == 12 && number.charAt(0) == "0") 
            	number = "(" + number.substring(1, 3) + ")" + number.substring(3, 8) + "-" + number.substring(8);
            
            else if(l == 11 && number.charAt(0) == "0") 
            	number = "(" + number.substring(1, 3) + ")" + number.substring(3, 7) + "-" + number.substring(7);
            
            else if(l == 11) 
            	number = "(" + number.substring(0, 2) + ")" + number.substring(2, 7) + "-" + number.substring(7);  
            
            else if(l == 8) 
            	number = number.substring(0, 4) + "-" + number.substring(4);
            
            else if(l == 9) 
            	number = number.substring(0, 5) + "-" + number.substring(5);
			
            else
            	return false;
		}	
	}
	
	return number;
}

function maskPhoneNumberObj(obj){
	
	obj.value = maskPhoneNumber(obj.value);

}

function formatPhoneNumberObj(obj){
	
	var valor = formatPhoneNumber(obj.value);
	
	if(valor == false)
		obj.value = "";
	else
		obj.value = valor;
}