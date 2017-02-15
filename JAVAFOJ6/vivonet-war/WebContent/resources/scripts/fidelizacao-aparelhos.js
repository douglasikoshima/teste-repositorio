function cancela(acao, obj){    
    document.forms[0].elements["tipoEncerramento"].value="5";
    document.forms[0].action="filtroFimRetencao.do?acao="+acao;
    parent.parent.mostrar_div();
    document.forms[0].target = "";
    document.forms[0].submit();
}

function mostraMeioPagamento(){
    if (document.meioPagamento.meioPagamento.options[document.meioPagamento.meioPagamento.selectedIndex].value == 1) {            
        document.all["divCartaoCredito"].style.visibility = "visible";
        document.all["divDebitoConta"].style.visibility = "hidden";
    }else{
        if (document.meioPagamento.meioPagamento.options[document.meioPagamento.meioPagamento.selectedIndex].value == 2) {
            document.all["divCartaoCredito"].style.visibility = "hidden";
            document.all["divDebitoConta"].style.visibility = "visible";
	    }else{
            document.all["divCartaoCredito"].style.visibility = "hidden";
            document.all["divDebitoConta"].style.visibility = "hidden";
        }
    }
}

function verificaMeio(obj){
    if(obj.value==3 || obj.value==4) {
        // Caso o Meio de Pagamento seja Doacao
        document.forms[0].elements['vlDesconto'].value="0";
        document.forms[0].elements['vlParcela'].value="0";
        document.forms[0].elements['nroParcela'].selectedIndex=0;
        document.forms[0].elements['nroParcela'].options[0].text=0;
        document.forms[0].elements['percDesconto'].selectedIndex=0;
        document.forms[0].elements['percDesconto'].options[0].text=100;
        document.forms[0].elements['percDesconto'].disabled=true;
        document.forms[0].elements['nroParcela'].disabled=true;
        document.forms[0].elements['vlDesconto'].disabled=true;
        document.forms[0].elements['vlParcela'].disabled=true;

    }else{
        // Caso retorne do meio de Pagamento Doacao
        if( document.forms[0].elements['percDesconto'].options[0].text == 100 ){
            document.forms[0].elements['percDesconto'].selectedIndex=0;
            document.forms[0].elements['percDesconto'].options[0].text="";

            document.forms[0].elements['nroParcela'].selectedIndex=0;
            document.forms[0].elements['nroParcela'].options[0].text="";

            document.forms[0].elements['vlParcela'].value="";
            document.forms[0].elements['vlDesconto'].value = document.forms[0].elements['vlCalcularDesconto'].value;
        }
        document.forms[0].elements['vlDesconto'].disabled=false;
        document.forms[0].elements['vlParcela'].disabled=false;
        document.forms[0].elements['nroParcela'].disabled=false;
        document.forms[0].elements['percDesconto'].disabled=false;
    }
}

function abreManualAparelho(){
    document.forms["frmManual"].target = "ifrmManualAparelho";
    document.forms["frmManual"].action = "http://www.vivo.com.br";
    document.forms["frmManual"].submit();   
}
    
function mostraTodosAparelhos(obj){
    if(obj.checked){
        document.forms[0].action = "getTodosAparelhos.do?acao=0";                
    }else{
        document.forms[0].action = "getTodosAparelhos.do";                
    }
    parent.parent.mostrar_div();
    document.forms[0].target = "";
    document.forms[0].submit();   
}
    
function carregarPercDesconto(obj){
/*
    for (i=0; i < document.all.length; i++){
        if (obj == document.all[i]){
            objId = document.all[i].value;
            i= i + 4;
            objIndex = document.all[i].value;
        }
    }
    if(document.forms[0].elements["wlw-checkbox_key:inExcecao}"].checked){
        objAcao = "0"
    }else{
        objAcao = null
    }
    if(objAcao != null){
    document.forms[0].action = "getPercDescontoParcelas.do?index="+objId+"&acao="+objAcao+"&id="+objIndex;
    }else{
    document.forms[0].action = "getPercDescontoParcelas.do?index="+objId+"&id="+objIndex;
    }*/
    //alert(obj.checked);
    //alert(obj.name);
    //obj.checked=true;
    document.forms[0].elements["index"].value=obj.value;
    document.forms[0].action = "getPercDescontoParcelas.do";
    parent.parent.mostrar_div();
    document.forms[0].target = "";
    document.forms[0].submit();   
}
    
function abreUrl(obj){
    for (i=0; i < document.all.length; i++){
        if (obj == document.all[i]){
            i++;
            url = document.all[i].value;
        }
    }
    if ((url != null)&&(url != "")){
        if (url.indexOf("http://") < 0){
            url = "http://" + url;
        }
        window.open(url,"janela");
    }	
}

valorInicio = null;
function calculaPerc(obj){
    destino = document.forms[0].elements['vlCalcularDesconto'];
    if(valorInicio == null){
        valorInicio = destino.value.replace(".","").replace(",",".")
        valorInicio = parseInt(valorInicio*100)
    }
    if(obj.selectedIndex == 0){
        valorInicio = valorInicio.toString();
        document.forms[0].elements["vlDesconto"].value = valorInicio.substring(0,valorInicio.length - 2) + "," + valorInicio.substring(valorInicio.length - 2,valorInicio.length);		
        calculaParc(document.forms[0].elements['nroParcela']);

    }else if(obj.value != "netui_null" && obj.value != ''){
        perc = parseInt(obj.options[obj.selectedIndex].text);
        calculo = parseInt((valorInicio*perc)/100);
        calculo = valorInicio-calculo;
        calculo = calculo.toString();
        calculo = calculo.substring(0,calculo.length - 2) + "," + calculo.substring(calculo.length - 2,calculo.length);
        
        if(calculo.length > 6){
            calculo = calculo.substring(0,calculo.length - 6) + "." + calculo.substring(calculo.length - 6,calculo.length);
        }
    
        destino = document.forms[0].elements['vlDesconto'];
        destino.value = calculo;
        calculaParc();

    }else{
        valorInicio = valorInicio.toString();
        valorInicio = valorInicio.substring(0,valorInicio.length - 2) + "," + valorInicio.substring(valorInicio.length - 2,valorInicio.length);
        
        if(valorInicio.length > 6){
            valorInicio = valorInicio.substring(0,valorInicio.length - 6) + "." + valorInicio.substring(valorInicio.length - 6,valorInicio.length);
        }
        
        destino = document.forms[0].elements['vlDesconto'];
        destino.value = valorInicio;
        
        for(i=0;i<document.forms[0].elements['nroParcela'].options.length;i++){
            if(document.forms[0].elements['nroParcela'].options[i].value ==""){
                document.forms[0].elements['nroParcela'].options[i].selected = true;
            }
        }
        
        document.forms[0].elements['vlParcela'].value = "";
        valorInicio = null;
    }
}

function calculaParc(obj){
    var f = document.forms[0];
    if(f.percDesconto.value==100){
        f.elements['vlDesconto'].value="0";
        f.elements['vlParcela'].value="0";
        f.elements['nroParcela'].selectedIndex=0;
        f.elements['nroParcela'].value=0;
        f.elements['nroParcela'].options[0].text=0;

    }else{
        origem  = f.elements['vlDesconto'];
        destino = f.elements['vlParcela'];
        if(f.elements['nroParcela'].selectedIndex !=0){
            numParcelas = parseInt(f.elements['nroParcela'].options[f.elements['nroParcela'].selectedIndex].value);
            if(numParcelas==0) numParcelas = 1;
            valor = origem.value.replace(".","").replace(",",".");
            valor = parseInt(valor*100);
            calculo = parseInt(valor/numParcelas);
            calculo = calculo.toString();
            calculo = calculo.substring(0,calculo.length - 2) + "," + calculo.substring(calculo.length - 2,calculo.length);
            
            if(calculo.length > 6){
                calculo = calculo.substring(0,calculo.length - 6) + "." + calculo.substring(calculo.length - 6,calculo.length);
            }
            
            destino.value = calculo;
        
        }else{
            destino.value = "";
        }
    }
}

document.onmouseup=mdown;

function mdown(){
	obj = event.srcElement;
	if(obj.type == "radio"){
		carregarPercDesconto(obj);
	}
}

function checaSubmit(){
    erro = true;
    if(document.forms[0].elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'] && document.forms[0].elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'].length){
        for(i=0;i<document.forms[0].elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'].length;i++){
            if(document.forms[0].elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'][i].checked == true){
                erro = false;
            }
        }
    }else if(document.forms[0].elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}']){
            if(document.forms[0].elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'].checked == true){
                erro = false;
            }
    }    
    if(erro){
        alert('Favor Selecionar um Aparelho')
        
   }else{        
        if(document.forms[0].elements['percDesconto'].selectedIndex <=0 && document.forms[0].elements['percDesconto'].options[0].text != 100){
            alert('Favor Selecionar o Percentual de Desconto')
        }else if(document.forms[0].elements['nroParcela'].selectedIndex <=0 && document.forms[0].elements['percDesconto'].options[document.forms[0].elements['percDesconto'].selectedIndex].value!="100"
              && document.forms[0].elements['percDesconto'].options[0].text != 100 ){
            alert('Favor Selecionar o Número da Parcela')    
        }else if(document.forms[0].elements['meioPagamento'].selectedIndex <=0){
            alert('Favor Selecionar um Meio de Pagamento')    
        }else{

            if( document.forms[0].elements['meioPagamento'].value == "3" ){
                document.forms[0].action = "getDadosAparelho.do?acao=proxima&meioPgto=3";
            
            } else {
                var nrCPF = onlyNumbers(window.top.frameApp.$('ddClienteDocumento').innerHTML);
                var dsMeioPagamento = document.forms[0].meioPagamento.options[document.forms[0].meioPagamento.selectedIndex].text;
                document.forms[0].elements['vlDesconto'].disabled=false;
                document.forms[0].elements['vlParcela'].disabled=false;
                document.forms[0].elements['nroParcela'].disabled=false;
                document.forms[0].elements['percDesconto'].disabled=false;
                document.forms[0].action = "getDadosAparelho.do?acao=proxima&nrCPF="+nrCPF+"&dsMeioPagamento="+dsMeioPagamento;
            }
			parent.parent.mostrar_div();
			document.forms[0].target = "";
            document.forms[0].submit();
        }
    }
}

function agendar(){
    parent.agendar();
}

var objPercDesc = document.createElement("SELECT");

function mostrarPercDesc(obj){
    for(i=0; i<document.forms[0].elements["wlw-radio_button_group_key:{pageContext.listaAparelhosVO}"].length; i++){
        if(document.forms[0].elements["wlw-radio_button_group_key:{pageContext.listaAparelhosVO}"][i].checked){
            document.forms[0].elements["vlDesconto"].value =  document.forms[0].elements["{pageContext.listaAparelhosVO["+ i +"].preco}"].value;
            break;
        }
    }
    document.forms[0].elements["nroParcela"].selectedIndex=0;
    document.forms[0].elements["vlParcela"].value="";
    if(document.forms[0].elements["vlDesconto"].value !=""){
        if(obj.checked){        
            objPercDesc.options.length = 0;
            for(i= 0; i< document.forms[0].percDesconto.options.length; i++){
                var newOpt = document.createElement("OPTION");
                newOpt.text=document.forms[0].percDesconto.options[i].text;
                newOpt.value=document.forms[0].percDesconto.options[i].value;
                objPercDesc.add(newOpt);        
            }
            document.forms[0].percDesconto.options.length = 0;

            for(i=0;i<= 100;i+=5){
                var newOpt = document.createElement("OPTION");
                newOpt.text=i;
                newOpt.value=i;
                document.forms[0].percDesconto.add(newOpt);
            }        

        }else{
            document.forms[0].percDesconto.options.length=0;
            for(i=0; i<objPercDesc.options.length; i++){
                var newOpt = document.createElement("OPTION");
                newOpt.text =objPercDesc.options[i].text;
                newOpt.value=objPercDesc.options[i].value;
                document.forms[0].percDesconto.add(newOpt); 
            }
        }
    }else{
        obj.checked=false;
    }
}