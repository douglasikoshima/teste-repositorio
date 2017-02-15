

function submitPesquisar(idTab) {
    f = document.forms["rWFInBoxPesquisaForm"];
    statusSel = f.elements["idEstado"].value;
    dtFecIni = f.elements["dtFechamentoInicio"].value;
    dtFecFim = f.elements["dtFechamentoFim"].value;
    if (idTab == null) {
        idTab = 2;
    }
    if (idTab == 2) {
        if (inFechadoCancelado==1) {
            if (optDataSel==1) {
                alert("Não utilize Data Fechamento para pesquisar\nprocessos no estado Aberto/Em Tratamento/Em Retorno!");
                return false;
            }
        } else if (inFechadoCancelado==0) {
            if (optDataSel!=1) {
                alert("Para pesquisa de processos Cancelados ou Fechados,\npreencha os campos de Data de Fechamento!");
                return false;
            }
        }
        if (optDataSel==1) {
            if (dtFecIni == "" || dtFecFim == "") {
                alert("Para pesquisa de processos Cancelados ou Fechados\né necessário preencher Data Início e Fim!");
                return false;
            }
        }
    }
    if(verificaDatas()) {
		if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        pesquisaAba(idTab);
        dvEncaminhar.style.display='none';
        if (idTab == 0) {
            abaEnTratamento();
        } else if (idTab == 1) {
            abaEnPausa();
        } else if (idTab == 2) {
            abaEnPesquisa();
        }
        ep=true;f.method="POST";f.target="ifrPesquisa";f.submit();
	}
}
/*
*   Mesma lógica de validaçao para function submitPesquisar()
*   Se abaPesquisaSelecionada == 1 'pesquisa'
*/
function pesquisaAba(valor){

    top.frameCTI.filtro.inbox.ultimaTab = valor;

    if( top.frameApp.dvAnimarAguarde != null )
         top.frameApp.startAnimation();

    f = document.forms["rWFInBoxPesquisaForm"];
    
    if(abaPesquisaSelecionada==1){
        statusSel = f.elements["idEstado"].value;
        dtFecIni = f.elements["dtFechamentoInicio"].value;
        dtFecFim = f.elements["dtFechamentoFim"].value;
        
        if (inFechadoCancelado==1) {
            if (optDataSel==1) {
                alert("Não utilize Data Fechamento para pesquisar\nprocessos no estado Aberto/Em Tratamento/Em Retorno!");
                return false;
            }
        } else if (inFechadoCancelado==0) {
            if (optDataSel!=1) {
                alert("Para pesquisa de processos Cancelados ou Fechados,\npreencha os campos de Data de Fechamento!");
                return false;
            }
        }
        if (optDataSel==1) {
            if (dtFecIni == "" || dtFecFim == "") {
                alert("Para pesquisa de processos Cancelados ou Fechados\né necessário preencher Data Início e Fim!");
                return false;
            }
        }
    }
    else{
        f.reset();
        f.elements["idSubEstado"].value='-1';
    }
    
    f.action="RWFAtendimento.do?tabPausa="+valor;
    if(valor == 1){
        f.target="ifrEPau";
        ept=true;
        abaEnPausa();
    } else if (valor == 0) {
        f.target="ifrETrat";
        abaEnTratamento();
    } else if (valor == 2) {
        f.target="ifrPesquisa";
        abaEnPesquisa();
    }
    f.submit();
}

function submitEstado() {
	if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
	f.method="POST";f.action="obterSubEstado.do";f.target="iframeEstado";f.submit();
}
function verificaDatas() {
    f = document.forms["rWFInBoxPesquisaForm"];  
	dtFecIni=f.elements["dtFechamentoInicio"].value;dtFecFim=f.elements["dtFechamentoFim"].value;dtAbeIni=f.elements["dtAberturaInicio"].value;dtAbeFim=f.elements["dtAberturaFim"].value;
	if(dtAbeIni.length > 0) {
		if(!validaData(dtAbeIni)) { alert('Data de abertura inicial deve estar no formato DD/MM/AAAA.');return false;}
	}
	if(dtAbeFim.length > 0) {
		if(!validaData(dtAbeFim)) { alert('Data de abertura final deve estar no formato DD/MM/AAAA.');return false;}
	}
	if(dtFecIni.length > 0) {
		if(!validaData(dtFecIni)) {alert('Data de fechamento inicial deve estar no formato DD/MM/AAAA.');return false;}
	}
	if(dtFecFim.length > 0) {
		if(!validaData(dtFecFim)) {alert('Data de fechamento final deve estar no formato DD/MM/AAAA.');return false;}
	}
    if (dtFecFim.length>0 && dtFecIni.length>0) {
        if (!validaDataFinal(dtFecIni,dtFecFim)) {alert("Data final de Fechamento deve ser posterior a Data incial!");return false;}
        if (!valida1mes(f.elements["dtFechamentoInicio"],f.elements["dtFechamentoFim"])) {alert("Intervalo de Datas Fechamento maior que 30 dias!\n");return false;}
    }
    if (dtAbeFim.length>0 && dtAbeIni.length>0) {
        if (!validaDataFinal(dtAbeIni, dtAbeFim)) {alert("Data final de Abertura deve ser posterior a Data inicial!");return false;}
        if (!valida1mes(f.elements["dtAberturaInicio"],f.elements["dtAberturaFim"])) {alert("Intervalo de Datas Abertura maior que 30 dias!\n");return false;}
    }
	return true;
}
function valida1mes(objInicial,objFinal){
    objSomaDias = document.all["somaDias"];
    somaDiasData(objInicial,objSomaDias,30);
    resposta = validaDataFinal(objFinal.value,objSomaDias.value);
    return resposta;
}
function validaData(dataValidar) {
	var dataOriginal = dataValidar;
	try {
		if(dataValidar == undefined || typeof(dataValidar) != 'string') {return false;}
		dataValidar = replaceAll(dataValidar, '/', '');
		if(isNaN(dataValidar)) {return false;}
		if(dataValidar.length != 8) {return false;}
		dia = dataOriginal.substring(0, dataOriginal.indexOf('/'));
		mes = dataOriginal.substring(dataOriginal.indexOf('/') + 1, dataOriginal.lastIndexOf('/'));
		ano = dataOriginal.substring(dataOriginal.lastIndexOf('/') + 1, dataOriginal.length);
		if((dia<1 || dia>31) || (mes<1 || mes>12) || (ano<1000)) {return false;}
		if(mes==2 && dia>29) {return false;}
		if((mes==4 || mes==6 || mes==9 || mes==11) && dia>30) {return false;}
		return true;
	} catch(ex) {alert('erro');}
}
function replaceAll(texto, encontrar, substituir) {
	var ret=texto;
	do { ret=ret.replace(encontrar, substituir); }
	while(ret.indexOf(encontrar) != -1);
	return ret;
}
function linhaSel( linha ) {
	if(!controleDetalhe)  {
		controleDetalhe=true;return;
	}
	var idAtd = linha.cells(2).innerText;
    fila = escape("/FrontOfficeWeb/workflow/AtendimentoInBox/begin.do");    
    top.frameApp.location="../AtendimentoDetalhe/begin.do?idAtendimento=" + idAtd + "&fila=" + fila;
}
function habilitaData(valor) {
    f = document.forms["rWFInBoxPesquisaForm"];   
    if(valor == 0) {
        f.elements["dtFechamentoInicio"].value = "";
        f.elements["dtFechamentoFim"].value = "";
        f.elements["dtFechamentoInicio"].disabled = true;
        f.elements["dtFechamentoFim"].disabled = true;
        f.elements["dtAberturaInicio"].disabled = false;
        f.elements["dtAberturaFim"].disabled = false;
        f.elements["imgCalendDtFecIni"].style.display = 'none';
        f.elements["imgCalendDtFecFim"].style.display = 'none';
        f.elements["imgCalendDtAbIni"].style.display = '';
        f.elements["imgCalendDtAbFim"].style.display = '';
    }
    else {
        f.elements["dtAberturaInicio"].value = "";
        f.elements["dtAberturaFim"].value = "";
        f.elements["dtFechamentoInicio"].disabled = false;
        f.elements["dtFechamentoFim"].disabled = false;
        f.elements["dtAberturaInicio"].disabled = true;
        f.elements["dtAberturaFim"].disabled = true;
        f.elements["imgCalendDtAbIni"].style.display = 'none';
        f.elements["imgCalendDtAbFim"].style.display = 'none';
        f.elements["imgCalendDtFecIni"].style.display = '';
        f.elements["imgCalendDtFecFim"].style.display = '';
    }
    optDataSel=valor;
}

function limparPesquisa() { 
f = document.forms["rWFInBoxPesquisaForm"]; 
f.reset();
f.elements["idSubEstado"].value='-1';
optDataSel=0;
inFechadoCancelado=-1;
}
