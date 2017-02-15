function isCarregadoCTI(){
	if(top.frameCTI.isCarregadoOCX()){
		if(top.frameCTI.isAgenteLogado()){
            return true;
		}
	}
    return false;
}

f = document.forms["0"];
optDataSel=0;
dvMax=316;
dvMin=266;
abaPesquisaSelecionada = 0;
if(isCarregadoCTI()){
    dvMin=216;
}
inFechadoCancelado=-1;

function resizeMaxMin() {
	tbConfiguracoes.style.display=(up ? "none" : "");
    tbTrat = document.all["tbProcessosTratamento_body"];
    tbPausa = document.all["tbProcessosPausa_body"];
    tbPesquisa = document.all["tbProcessosPesquisa_body"];
    tbMensagens = document.all["tbResultado_body"];
    tbMensagensRC = document.all["tbResultadoRC_body"];
    if (tbTrat != null) {
        tbTrat.parentElement.style.height = (up ? dvMax+"px" : dvMin+"px");
    }
    if (tbPausa != null) {
        tbPausa.parentElement.style.height = (up ? dvMax+"px" : dvMin+"px");
    }
    if (tbPesquisa != null) {
        tbPesquisa.parentElement.style.height = (up ? dvMax+"px" : dvMin+"px");
    }
    if (tbMensagens != null) {
        tbMensagens.parentElement.style.height = (up ? dvMax+"px" : dvMin+"px");
    }
    if (tbMensagensRC != null) {
        tbMensagensRC.parentElement.style.height = (up ? dvMax+"px" : dvMin+"px");
    }
	document.getElementById("btMaxMin").src = "../../resources/images/" + (up ? "bt_lupa_aba_down.gif" : "bt_lupa_aba.gif");
	up = !up;
}

function exibicaoAbaPesquisas(index) {
    abaPesquisaSelecionada=index;
    if( index == 0) {
        dvMax=316;
        dvMin=266;
        if(isCarregadoCTI()){
            dvMin=216;
        }
        abaSelected(btAbaPesquisas, btCTIAtendente);
        trPesquisa.style.display = 'none';
        trEstadoAtendente.style.display = '';
    } else if (index == 1) {
        dvMax=316;
        dvMin=175;
        abaSelected(btAbaPesquisas, btPesquisa);
        trPesquisa.style.display='';
        trEstadoAtendente.style.display='none';
    }
    up=!up;
    resizeMaxMin();
}

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
        f.target="ifrPesquisa"
        if (idTab == 0) {
            abaEnTratamento();
        } else if (idTab == 1) {
            abaEnPausa();
        } else if (idTab == 2) {
            abaEnPesquisa();
        } else if (idTab == 3) {
            abaMensagens();
        }
        //ep=true;f.method="POST";f.submit();
	}
}

/*
*   Mesma lógica de validaçao para function submitPesquisar()
*   Se abaPesquisaSelecionada == 1 'pesquisa'
*/
function pesquisaAba(valor,flLimpar){
    if (valor==3 || valor==4) {
        document.getElementById('trGrupo').style.display='none';
        document.getElementById('trEstado').style.display='none';
        document.getElementById('tdFechamento').style.display='none';
        document.getElementById('tdFechamentoLabel').style.display='none';
        dvMax=316;
        dvMin=230;
        document.getElementById("wlw-radio_button_group_key:{pageContext.form.radioButton}").value=0;
    } else {
        document.getElementById('trGrupo').style.display='';
        document.getElementById('trEstado').style.display='';
        document.getElementById('tdFechamento').style.display='';
        document.getElementById('tdFechamentoLabel').style.display='';
    }

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
    }else{
        f.reset();
        f.elements["idSubEstado"].value='-1';
    }

    if (flLimpar!=null && flLimpar==1){
        f.action="RWFAtendimento.do?tabPausa="+valor+"&flLimpar="+flLimpar;
    }else{
        f.action="RWFAtendimento.do?tabPausa="+valor;
    }
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
    } else if (valor == 3) {
        f.target="ifrMensagens";
        f.action="pesquisarMensagens.do";
        abaMensagens();
    } else if (valor == 4) {
        f.target="ifrMensagensRC";
        f.action="pesquisarMensagensRC.do";
        abaMensagensRC();
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
	linha.cells(2).id = "tdLinhaAtendimento";
	var idAtd = $('tdLinhaAtendimento').select('[class="classIdAtendimento"]')[0].innerText;
    fila = escape("/FrontOfficeWeb/extworkflw/AtendimentoInBox/begin.do");
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
function abaEnTratamento() {
    abaSelected(btAbaInBox, btTratamento);
    dETrat.style.display='';
    dEPau.style.display='none';
    dMensagensRC.style.display='none';
    dPesquisa.style.display='none';
    dMensagens.style.display='none';
    DoResizeHeaderTableVivo();
}
function abaEnPausa() {
    abaSelected(btAbaInBox, btPausa);
    dETrat.style.display='none';
    dMensagensRC.style.display='none';
    dPesquisa.style.display='none';
    dMensagens.style.display='none';
    if (ep) { ep=true;
    } else { dEPau.style.display='';DoResizeHeaderTableVivo();return false; }
}
function abaEnPesquisa() {
    abaSelected(btAbaInBox, btPesquisaResult);
    dPesquisa.style.display='';
    dMensagensRC.style.display='none';
    dETrat.style.display='none';
    dEPau.style.display='none';
    dMensagens.style.display='none';
    DoResizeHeaderTableVivo();
}
function abaMensagens() {
    abaSelected(btAbaInBox, btMensagensResult);
    dMensagens.style.display='';
    dMensagensRC.style.display='none';
    dPesquisa.style.display='none';
    dETrat.style.display='none';
    dEPau.style.display='none';
    DoResizeHeaderTableVivo();
}

function abaMensagensRC() {
    abaSelected(btAbaInBox, btMensagensResultRC);
    dMensagensRC.style.display='';
    dPesquisa.style.display='none';
    dETrat.style.display='none';
    dEPau.style.display='none';
    dMensagens.style.display='none';
    DoResizeHeaderTableVivo();
}

function arvoreContato(){document.getElementById("ifrmArvore").src="obterArvoreContato.do";}

function limparPesquisa() {
    f = document.forms["rWFInBoxPesquisaForm"];
    f.reset();
    f.elements["nrProtocolo"].value = '';
    f.elements["idSubEstado"].value='-1';
    optDataSel=0;
    inFechadoCancelado=-1;
}

function submitAlerta(idAtd) {
    controleDetalhe = false;
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    dvAlerta.style.display='';
    document.getElementById("ifrmAlerta").src="RWFAlerta.do?idAtendimento="+idAtd;
}

function submitEncaminhar() {
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    f = document.getElementById("formListaAtendimentos");
    dvEncaminhar.style.display='';
    f.method="POST";
    f.action="encaminhar.do";
    f.target="ifrmEncaminhar";
    f.submit();
}

function desativar_combos() {
    if (frmsState == null) {
        forms=document.forms;
        frmsState=new Array(forms.length);
        for (i=0;i<forms.length;i++) {
            el=forms[i].elements;
            elState=new Array(el.length);
            frmsState[i]=elState;
            for(j=0;j<el.length;j++){elState[j]=el[j].disabled;el[j].disabled=true;}
        }
    }
    return;
}
function ativar_combos() {
    if (frmsState != null) {
        forms=document.forms;
        for (i=0;i<forms.length;i++) {
            el=forms[i].elements;
            elState=frmsState[i];
            frmsState[i]=elState;
            for(j=0;j<el.length;j++){el[j].disabled=elState[j];}
        }
    }
    frmsState = null;
    return;
}

function receberChamada(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA) {
    fila = escape("/FrontOfficeWeb/extworkflw/AtendimentoInBox/begin.do");
    //Obtém tela de detalhe
    document.forms["0"].target = "frameApp";
    document.forms["0"].action = "/FrontOfficeWeb/extworkflw/AtendimentoDetalhe/begin.do?idAtendimento=" + NumProc + "&fila=" + fila;
    document.forms["0"].submit();
}

/*
* Efetua algum tratamento quando a ligação for finalizada.
*/
function semLigacao() {
    alert('Ligação Finalizada');
}

function salvaFiltros() {
    voltar2=1;
    f=document.forms["rWFInBoxPesquisaForm"]; filtro=top.frameCTI.filtro.inbox;
    filtro.grupoSel=f.idGrupo.value;
    filtro.estadoSel=f.idEstado.value;
    filtro.subEstadoSel=f.idSubEstado.value;
    filtro.nrProtocolo=f.nrProtocolo.value;
    filtro.optGrpSel1=f.elements['wlw-radio_button_group_key:{pageContext.form.radioButton}'][0].checked;
    filtro.optGrpSel2=f.elements['wlw-radio_button_group_key:{pageContext.form.radioButton}'][1].checked;
    filtro.dtAberturaInicio=f.dtAberturaInicio.value;
    filtro.dtAberturaFim=f.dtAberturaFim.value;
    filtro.nrLinha=f.nrLinha.value;
    filtro.dtFechamentoInicio=f.dtFechamentoInicio.value;
    filtro.dtFechamentoFim=f.dtFechamentoFim.value;
    filtro.textoContato=f.textoContato.value;
    filtro.idContato=f.idContato.value;
}

function carregaFiltros() {
    if (voltar!=1 && voltar2==0) {
        return;
    }
    f=document.forms["rWFInBoxPesquisaForm"]; filtro=top.frameCTI.filtro.inbox;
    f.idGrupo.value=filtro.grupoSel;
    f.idEstado.value=filtro.estadoSel; loadSubEstados(filtro.estadoSel);
    f.idSubEstado.value=filtro.subEstadoSel;
    f.nrProtocolo.value=filtro.nrProtocolo;
    f.dtAberturaInicio.value=filtro.dtAberturaInicio;
    f.dtAberturaFim.value=filtro.dtAberturaFim;
    f.nrLinha.value=filtro.nrLinha; f.nrLinha.blur();
    f.dtFechamentoInicio.value=filtro.dtFechamentoInicio;
    f.dtFechamentoFim.value=filtro.dtFechamentoFim;
    f.textoContato.value=filtro.textoContato;
    f.idContato.value=filtro.idContato;
    f.elements['wlw-radio_button_group_key:{pageContext.form.radioButton}'][0].checked=filtro.optGrpSel1;
    f.elements['wlw-radio_button_group_key:{pageContext.form.radioButton}'][1].checked=filtro.optGrpSel2;
    if (filtro.optGrpSel1) {
        habilitaData(0);
    } else {
        habilitaData(1);
    }
}