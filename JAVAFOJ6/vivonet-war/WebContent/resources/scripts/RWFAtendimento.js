function verificaCancelamento(){
	var contCancelado = 0;
	var aCancelado = new Array();
	aCheckbox = document.getElementsByTagName('input');
	for(i=0;i<aCheckbox.length;i++){
		if(aCheckbox[i].type == 'checkbox'){
			if(aCheckbox[i].parentElement.all['atendimentoVO.dtSolicitacaoCancelamento'].value != "" ){
				aCancelado[contCancelado] = aCheckbox[i].parentElement.parentElement.childNodes(2).innerText;
				contCancelado++;
			} 
		}
	}
	var strCancelados = "";
	for(i=0;i<aCancelado.length;i++){
		if(i == aCancelado.length-1)  
			strCancelados = strCancelados + aCancelado[i];
		else   
			strCancelados = strCancelados + aCancelado[i] + ", ";                        
	}
	if(aCancelado.length > 0)
		alert("Os seguintes processos foram cancelados:\n" + strCancelados);
}
verificaCancelamento();

