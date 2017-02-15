

  
 
function retornoChamadaLinkMenu(t){
}

function tratarErros(message){
	$('divErros').show();
}

function carregaMenu(id){
	var parent = document.getElementById(id).parentElement;		
	var count = 2;
	while(parent.tagName != 'DIV' && count > 0) {
		if (parent.tagName == 'UL') {		    
			TwMenu(parent.id,count--);
		}
		parent = parent.parentElement;
	}	
}
