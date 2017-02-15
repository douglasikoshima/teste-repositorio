zsa="if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();"
zso="if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();"
optDataSel=0;
var tipoPesquisaContador = 0;
tipoPesquisa=-1;
inFechadoCancelado=-1;
function desativar_combos() {
    if (frmsState==null) {
        forms=document.forms;
        frmsState=new Array(forms.length);
        for (i=0;i<forms.length;i++) {
            el=forms[i].elements;elState=new Array(el.length);frmsState[i]=elState;
            for(j=0;j<el.length;j++){
                elState[j]=el[j].disabled;el[j].disabled=true;
            }
        }
    }
    return;
}
function ativar_combos() {
    if (frmsState!=null) {
        forms=document.forms;
        for (i=0;i<forms.length;i++) {
            el=forms[i].elements;elState=frmsState[i];frmsState[i]=elState;
            for(j=0;j<el.length;j++){ el[j].disabled=elState[j]; }
        }
    }
    frmsState = null;return;
}
function reziseMaxMin() {
    tbConfiguracoes.style.display=(up?"none":"");reziseObject(up);
    f.elements["btMaxMin"].src="../../resources/images/"+(up?"bt_lupa_aba_down.gif":"bt_lupa_aba.gif");up=!up;
}
function reziseObject(maximized) {
    tbResultadoPesquisa_body.parentElement.style.height=(maximized?"324px":(abaPesquisaBasica?"183px":"107px"));
}
function exibicaoAbaPesquisas(index) {
    tipoPesquisaContador=index;
    if(index==0) {
        abaPesquisaBasica=false;abaSelected(btAbaPesquisas, btPesquisaAvancada);
        trPesquisaBasica.style.display='none';trPesquisaAvancada.style.display='';
        if (pava) { eval(zsa);document.getElementById("ifComboCampos").src="obterComboCampos.do";pava=false;}
    } else {
        abaPesquisaBasica=true;abaSelected(btAbaPesquisas, btPesquisaBasica);
        trPesquisaBasica.style.display='';trPesquisaAvancada.style.display='none';
    }
    f.elements["abaSelecionada"].value=index;
    if(!up) reziseMaxMin();
    reziseObject(false);
}
function habilitaData(valor) {
   if(valor==0) {
        f.elements["atdFilaPesqVO.dtFechamentoInicio"].value="";
        f.elements["atdFilaPesqVO.dtFechamentoFim"].value="";
        f.elements["atdFilaPesqVO.dtFechamentoInicio"].disabled=true;
        f.elements["atdFilaPesqVO.dtFechamentoFim"].disabled=true;
        f.elements["atdFilaPesqVO.dtAberturaInicio"].disabled=false;
        f.elements["atdFilaPesqVO.dtAberturaFim"].disabled=false;
        f.elements["imgCalendDtFecIni"].style.display='none';
        f.elements["imgCalendDtFecFim"].style.display='none';
        f.elements["imgCalendDtAbIni"].style.display='';
        f.elements["imgCalendDtAbFim"].style.display='';
    }else{
        f.elements["atdFilaPesqVO.dtAberturaInicio"].value="";
        f.elements["atdFilaPesqVO.dtAberturaFim"].value="";
        f.elements["atdFilaPesqVO.dtFechamentoInicio"].disabled=false;
        f.elements["atdFilaPesqVO.dtFechamentoFim"].disabled=false;
        f.elements["atdFilaPesqVO.dtAberturaInicio"].disabled=true;
        f.elements["atdFilaPesqVO.dtAberturaFim"].disabled=true;
        f.elements["imgCalendDtAbIni"].style.display='none';
        f.elements["imgCalendDtAbFim"].style.display='none';
        f.elements["imgCalendDtFecIni"].style.display='';
        f.elements["imgCalendDtFecFim"].style.display='';
    }

    optDataSel=valor;
}

function linhaSel(linha) {
    if(!controleDetalhe) {
        controleDetalhe=true;
        return;
    }
    if (f.inMirrorRC != null) {
        linha.cells(3).id = "tdLinhaAtendimento";
        if (f.inMirrorRC.value == "1") {
            var idAtd = $('tdLinhaAtendimento').select('[class="classIdAtendimento"]')[0].value;
        } else {
        	var idAtd = $('tdLinhaAtendimento').select('[class="classIdAtendimento"]')[0].value;
        }
    } else {
       	linha.cells(3).id = "tdLinhaAtendimento";
       	var idAtd = $('tdLinhaAtendimento').select('[class="classIdAtendimento"]')[0].value;
    }
    eval(zsa)
    fila = escape("/FrontOfficeWeb/workflow/AtendimentoFila/begin.do");
    inCRI  = f.elements["inCRI"].value;
    inResp = f.elements["inResp"].value;
    if(inResp=='1'){
        fila = escape("/FrontOfficeWeb/workflow/AtendimentoFila/beginResp.do");
    }
    if(inCRI=='1'||inCRI=='2'){
        fila = escape("/FrontOfficeWeb/workflow/AtendimentoFila/beginCRI.do");
    }
    if (f.inMirrorRC!=null){
        if (f.inMirrorRC.value=="1"){
            top.frameApp.location="../AtendimentoDetalhe/begin.do?idAtendimento=" + idAtd + "&fila=" + fila+ "&inCRI=" + f.elements["inCRI"].value+"&inMirrorRC="+f.inMirrorRC.value+"&inRC="+f.elements["inResp"].value;
        }else{
            top.frameApp.location="../AtendimentoDetalhe/begin.do?idAtendimento=" + idAtd + "&fila=" + fila+ "&inCRI=" + f.elements["inCRI"].value+"&inRC="+f.elements["inResp"].value;
        }
    }else{
       top.frameApp.location="../AtendimentoDetalhe/begin.do?idAtendimento=" + idAtd + "&fila=" + fila+ "&inCRI=" + f.elements["inCRI"].value+"&inRC="+f.elements["inResp"].value;
    }
}

function limparPesquisa(valor) {
    /**
     * alteração feita para permitir o reuso da função,
     * assim quando estiver fazendo uma pesquisa por processo ou linha,
     * estes campos não serão limpados
     * @author ppaula
     */
    if(valor == "proc"){
        f.elements["atdFilaPesqVO.nrLinha"].value="";
    }else if (valor == "linha"){
        f.elements["atdFilaPesqVO.nrProtocolo"].value="";
    }else{
        f.elements["atdFilaPesqVO.nrProtocolo"].value="";
        f.elements["atdFilaPesqVO.nrLinha"].value="";
    }
    /**** END *****/

    f.elements["grupoSel"].value=-1;

    f.elements["regionalSelecionada"].value=-1;
    f.elements["usuarioSel"].value="";
    f.elements["estadoSel"].value="-1";
    f.elements["subEstadoSel"].value="";

    f.elements["atdFilaPesqVO.dtAberturaInicio"].value="";
    f.elements["atdFilaPesqVO.dtAberturaFim"].value="";

    f.elements["atdFilaPesqVO.dtFechamentoInicio"].value="";
    f.elements["atdFilaPesqVO.dtFechamentoFim"].value="";
    f.elements["textoContato"].value="";
    f.elements["atdFilaPesqVO.idContato"].value="";
    while(f.elements["usuarioSel"].options.length!=0)
        f.elements["usuarioSel"].options.remove(0);
    while(f.elements["subEstadoSel"].options.length!=0)
        f.elements["subEstadoSel"].options.remove(0);
    inFechadoCancelado=-1;
}
function submitLimparPesquisaAvancada() {
    eval(zsa)
    dvEncaminhar.style.display='none';
    document.getElementById("ifrmdvEncaminhar").style.display='none';
    dvSuspeito.style.display='none';
    document.getElementById("ifrmdvSuspeito").style.display='none';
    f.action="limparPesquisaAvancada.do";f.target="iframeValoresDominio";f.submit();
}
function submitPesquisa(i) { submitPesquisaBasica(i);}
function submitPesquisaBasica(i) {
    salvaFiltros();
    tipoPesquisa=1;
    ativar_combos();
    statusSel = f.elements["estadoSel"].value;
    dtFecIni = f.elements["atdFilaPesqVO.dtFechamentoInicio"].value;
    dtFecFim = f.elements["atdFilaPesqVO.dtFechamentoFim"].value;

    if(i==null){
        if((f.elements["regionalSelecionada"].value==-1 &&(f.elements["atdFilaPesqVO.nrLinha"].value=="" && f.elements["atdFilaPesqVO.nrProtocolo"].value=="") )){
            alert("É necessário a escolha de uma regional para realizar a pesquisa!");
            return false;
        }
    }
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
    if (optDataSel==1 && document.forms[0].elements("atdFilaPesqVO.nrLinha").value.length <= 0) {
        if (dtFecIni == "" || dtFecFim == "") {
            alert("Para pesquisa de processos Cancelados ou Fechados\né necessário preencher Data Início e Fim!");
            return false;
        }
    }
    if(verificaDatas()) {
        eval(zsa);
        dvEncaminhar.style.display='none';
        document.getElementById("ifrmdvEncaminhar").style.display='none';
        dvSuspeito.style.display='none';
        document.getElementById("ifrmdvSuspeito").style.display='none';
        f.action="pesquisar.do";f.target="ifrFilas";f.submit();
    }
}
function valida1mes(objInicial,objFinal){
    objSomaDias = f.elements["somaDias"];
    somaDiasData(objInicial,objSomaDias,30);
    resposta = validaDataFinal(objFinal.value,objSomaDias.value);
    return resposta;
}
function submitPesquisaAvancada() {
    tipoPesquisa=2;
    if(tbListaPesquisa_body.rows.length== 0) {
        //f.elements["formularioCampoSel"].focus();
        alert('Para executar a pesquisa é necessário configurar o filtro');
        return;
    }
    eval(zsa)
    dvEncaminhar.style.display='none';
    document.getElementById("ifrmdvEncaminhar").style.display='none';
    dvSuspeito.style.display='none';
    document.getElementById("ifrmdvSuspeito").style.display='none';
    f.action="pesquisarAvancado.do";f.target="ifrFilas";f.submit();
}
function submitGrupo() {
    eval(zsa)
    f.action="obterUsuario.do";f.target="iframeUsuario";f.submit();
}
function submitEstado() {
    eval(zsa)
    f.action="obterSubEstado.do";f.target="iframeEstado";f.submit();
}
function submitEncaminhar() {
    eval(zsa)
    if (ifrFilas.treadRefresh!=null)
        ifrFilas.stopRefresh();

    inCRI =  f.elements["inCRI"].value;
    inResp =  f.elements["inResp"].value;
    var contEnc=0;
    var contNaoEnc=0;
    var aEnc=new Array();
    if(inCRI=='1'||inCRI=='2'){
        aCheckbox=document.getElementsByTagName('input');
        for(i=0;i<aCheckbox.length;i++){
            if(aCheckbox[i].type == 'checkbox' && aCheckbox[i].checked == true){
                if(aCheckbox[i].parentElement.all['atendimentoVO.inCRI'].value!=0){
                    aCheckbox[i].checked=false;

                    //caso haja algum erro na busca devido a busca do primeiro nível
                    //buscar-se-á a partir do terceiro nível
                    try{
                        aEnc[contEnc]=aCheckbox[i].parentElement.parentElement.childNodes(2).innerText;
                    }catch(e){
                        aEnc[contEnc]=aCheckbox[i].parentElement.parentElement.parentElement.childNodes(2).innerText;
                    }

                    contEnc++;
                } else
                    contNaoEnc++;
            }
        }

        if(aEnc.length>0 && contNaoEnc==0){
            if( top.frameApp.dvAnimarAguarde!=null){
                top.frameApp.stopAnimation();
            }
            alert("Nenhum atendimento selecionado pode ser Encaminhado!")
            return false;
        }

        var strEnc='';
        for(i=0;i<aEnc.length;i++){
            if(i==aEnc.length-1){
                strEnc=strEnc+aEnc[i];
            }
            else{
                strEnc=strEnc+aEnc[i]+",";
            }
        }
        if(aEnc.length>0){
            if(!(confirm("Os seguintes pocessos não podem ser Encaminhados: "+strEnc+" . \nDeseja continuar?"))){
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                return false;
            }
        }

    }

    if (ifrFilas.treadRefresh!=null)
        ifrFilas.stopRefresh();
    dvEncaminhar.style.display='';
    document.getElementById("ifrmdvEncaminhar").style.display='';
    f.action="encaminhar.do";
    f.target="ifrmEncaminhar";
    f.submit();
}
function submitTrocar() {
    eval(zsa)
    if (ifrFilas.treadRefresh!=null)
        ifrFilas.stopRefresh();

    var contTrocar=0;
    var contNaoTrocar=0;
    var aTroca=new Array();
    aCheckbox=document.getElementsByTagName('input');
    for(i=0;i<aCheckbox.length;i++){
        if(aCheckbox[i].type == 'checkbox' && aCheckbox[i].checked == true){
            if(aCheckbox[i].parentElement.all['atendimentoVO.inCRI'].value==0){
                aCheckbox[i].checked=false;

                //caso haja algum erro na busca devido a busca do primeiro nível
                //buscar-se-á a partir do terceiro nível
                try{
                    aTroca[contTrocar]=aCheckbox[i].parentElement.parentElement.childNodes(2).innerText;
                }catch(e){
                    aTroca[contTrocar]=aCheckbox[i].parentElement.parentElement.parentElement.childNodes(2).innerText;
                }

                contTrocar++;
            } else
                contNaoTrocar++;
        }
    }

    if(aTroca.length>0 && contNaoTrocar==0){
        if( top.frameApp.dvAnimarAguarde!=null){
            top.frameApp.stopAnimation();
        }
        alert("Nenhum atendimento selecionado pode ser trocado!")
        return false;
    }

    var strTrocar='';
    for(i=0;i<aTroca.length;i++){
        if(i==aTroca.length-1){
            strTrocar=strTrocar+aTroca[i];
        }
        else{
            strTrocar=strTrocar+aTroca[i]+",";
        }
    }
    if(aTroca.length>0){
        if(!(confirm("Os seguintes pocessos não podem ser trocados: "+strTrocar+" . \nDeseja continuar?"))){
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            return false;
        }
    }

    dvTrocar.style.display='';
    f.action="trocar.do";
    f.target="ifrmTrocar";
    f.submit();
}
function submitSuspeito() {
    if (ifrFilas.treadRefresh!=null)
        ifrFilas.stopRefresh();
    eval(zsa)
    var contSusp=0;
    var contNaoSusp=0;
    var aSuspeito=new Array();
    aCheckbox=document.getElementsByTagName('input');
    for(i=0;i<aCheckbox.length;i++){
        if(aCheckbox[i].type == 'checkbox' && aCheckbox[i].checked == true){
            if(aCheckbox[i].parentElement.childNodes(0).value!=""
               || aCheckbox[i].parentElement.all['atendimentoVO.idAtendimentoBaixaHistorico'].value!=0){
                aCheckbox[i].checked=false;

                //caso haja algum erro na busca devido a busca do primeiro nível
                //buscar-se-á a partir do terceiro nível
                try{
                    aSuspeito[contSusp]=aCheckbox[i].parentElement.parentElement.childNodes(2).innerText;
                }catch(e){
                    aSuspeito[contSusp]=aCheckbox[i].parentElement.parentElement.parentElement.childNodes(2).innerText;
                }

                contSusp++;
            } else
                contNaoSusp++;
        }
    }
    if(aSuspeito.length>0 && contNaoSusp==0){
        if( top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
        alert("Nenhum atendimento selecionado pode ser marcado como suspeito!")
        return false;
    }
    var strSusp='';
    for(i=0;i<aSuspeito.length;i++){
        if(i==aSuspeito.length-1) strSusp=strSusp+aSuspeito[i];
        else strSusp=strSusp+aSuspeito[i]+",";
    }
    if(aSuspeito.length>0)
        if(!(confirm("Os seguintes pocessos não podem ser marcados como suspeitos: "+strSusp+" . \nDeseja continuar?"))){
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            return false;
        }
    dvSuspeito.style.display='';
    document.getElementById("ifrmdvSuspeito").style.display='';
    f.action="suspeito.do";f.target="ifrmSuspeito";f.submit();
}
function submitAlerta(linha) {
    controleDetalhe=false;
    eval(zsa);
    dvAlerta.style.display='';
    f.action="obterAlerta.do?idAtendimento="+linha;
    f.target="ifrmAlerta";
    f.submit();
}
function arvoreContato() {
    eval(zsa)
    dvArvore.style.display='';
    f.action="obterArvoreContato.do";
    f.target="ifrmArvore";
    f.submit();
}
function submitCampo() {
    document.all.divFormSemDominio.style.display="none";
    document.all.divFormComDominio.style.display="none";
    document.all.divValorDominio.style.display="none";
    document.forms[0].tipoLinhaSel.value=-1;
    document.forms[0].regionalSel.value=-1;
    document.forms[0].valorSel.value=-1;
    if (document.forms[0].formularioCampoSel.value==-1) {return;}
    eval(zsa)
    f.action="obterCampo.do";f.target="iframeCampo";f.submit();
}
function submitTipoLinhaUF() {
    sel = true;
    if (document.forms[0].tipoLinhaSel.value==-1) {sel=false;}
    if (document.forms[0].regionalSel.value == -1) {sel=false;}
    if (sel) {
        eval(zsa);
        f.action="obterValoresDominio.do";f.target="iframeCampo";f.submit();
    } else {
        document.all.divValorDominio.style.display="none";
        document.forms[0].valorSel.value=-1;
    }
}
function submitPesquisaAvancadaAdicionarItem() {
    if(pesquisaAvancadaVerificaAdicao()) {
        eval(zsa)
        f.action="adicionarItem.do";f.target="iframeValoresDominio";f.submit();
    }
}
function deleteFiltroItem(itemExcluir) {
    if(!(confirm("Deseja excluir esse filtro?")))
        return false;
    eval(zsa)
    dvEncaminhar.style.display='none';
    document.getElementById("ifrmdvEncaminhar").style.display='none';
    dvSuspeito.style.display='none';
    document.getElementById("ifrmdvSuspeito").style.display='none';
    f.action="excluirItem.do?itemExcluir=" + itemExcluir;f.target="iframeValoresDominio";f.submit();
}
function pesquisaAvancadaVerificaAdicao() {
    psqAv = top.frameCTI.filtro.psqAv;
    if( f.elements["formularioCampoSel"].value==-1 ) {
        f.elements["formularioCampoSel"].focus();
        alert('Selecione o Campo para Pesquisa');
        return false;
    }
    if (document.all.divFormComDominio.style.display=="") {
        if( f.valorSel.value==-1 ) {
            alert('Selecione um Tipo Linha/Regional/Valor de Dominio');
            return false;
        }

    }
    if (document.all.divFormSemDominio.style.display=="") {
        if( f.valor.value=="" ) {
            alert('Digite um Valor para Comparação');
            return false;
        }
    }
    // verificar se esse campo ja foi adicionado e nao permitir
    if (psqAv != null) {
        for (var i=0; i<psqAv.length; i++) {
            if (psqAv[i].idCampo == f.formularioCampoSel.value) {
                alert("Campo '"+f.formularioCampoSel.options[f.formularioCampoSel.selectedIndex].text+"' já selecionado para consulta!");
                return false;
            }
        }
    }

    return true;
}
function verificaDatas() {
    dtFecIni=f.elements["atdFilaPesqVO.dtFechamentoInicio"].value;
    dtFecFim=f.elements["atdFilaPesqVO.dtFechamentoFim"].value;
    dtAbeIni=f.elements["atdFilaPesqVO.dtAberturaInicio"].value;
    dtAbeFim=f.elements["atdFilaPesqVO.dtAberturaFim"].value;
    if(dtAbeIni.length>0) {
        if(!validaData(dtAbeIni)) { alert('Data de abertura inicial deve estar no formato DD/MM/AAAA.');return false;}
    }
    if(dtAbeFim.length>0) {
        if(!validaData(dtAbeFim)) {alert('Data de abertura final deve estar no formato DD/MM/AAAA.');return false;}
    }
    if(dtFecIni.length>0) {
        if(!validaData(dtFecIni)) {alert('Data de fechamento inicial deve estar no formato DD/MM/AAAA.');return false;}
    }
    if(dtFecFim.length>0) {
        if(!validaData(dtFecFim)) {alert('Data de fechamento final deve estar no formato DD/MM/AAAA.');return false;}
    }
    if (dtFecFim.length>0 && dtFecIni.length>0) {
        if (!validaDataFinal(dtFecIni,dtFecFim)) {alert("Data final de Fechamento deve ser posterior a Data incial!");return false;}
        if (!valida1mes(f.elements["atdFilaPesqVO.dtFechamentoInicio"],f.elements["atdFilaPesqVO.dtFechamentoFim"])) {alert("Intervalo de Datas Fechamento maior que 30 dias!\n");return false;}
    }
    if (dtAbeFim.length>0 && dtAbeIni.length>0) {
        if (!validaDataFinal(dtAbeIni, dtAbeFim)) {alert("Data final de Abertura deve ser posterior a Data inicial!");return false;}
        if (!valida1mes(f.elements["atdFilaPesqVO.dtAberturaInicio"],f.elements["atdFilaPesqVO.dtAberturaFim"])) {alert("Intervalo de Datas Abertura maior que 30 dias!\n");return false;}
    }
    return true;
}

function validaData(dataValidar) {
    var dataOriginal=dataValidar;
    try {
        if(dataValidar==undefined || typeof(dataValidar)!='string') { return false; }
        dataValidar=replaceAll(dataValidar,'/','');
        if(isNaN(dataValidar)) { return false; }
        if(dataValidar.length != 8) { return false; }
        dia=dataOriginal.substring(0, dataOriginal.indexOf('/'));
        mes=dataOriginal.substring(dataOriginal.indexOf('/') + 1, dataOriginal.lastIndexOf('/'));
        ano=dataOriginal.substring(dataOriginal.lastIndexOf('/') + 1, dataOriginal.length);
        if((dia<1 || dia>31) || (mes<1 || mes>12) || (ano<1000)) { return false; }
        if(mes==2 && dia>29) { return false; }
        if((mes==4 || mes==6 || mes==9 || mes==11) && dia>30) { return false; }
        return true;
    } catch(ex) {
        alert('Erro na execução da funçao javascript validaData(dataValidar)!');
    }
}

function replaceAll(t, e, s) {
    var ret = t;
    do { ret = ret.replace(e, s);
    } while(ret.indexOf(e)!=-1);
    return ret;
}

function validaCampo(campo, tipoCampo){
    switch(tipoCampo){
        case 'texto':
            return;
            break;
        case 'data':
            if(campo.value=='') return false;
            if(!validate_date(campo.value)){
                campo.value='';campo.focus();
                alert("Data inválida");
            }
            break;
        case 'hora':
            if(campo.value=='') return false;
            if(!validaHora(campo.value)){ campo.value='';campo.focus();}
            break;
        case 'cpf':
            if(campo.value=='') return false;
            if(!VerificaCPF(campo.value)){
                campo.value='';campo.focus();
                alert("CPF inválido");
            }
            break;
        case 'cnpj':
            if(campo.value=='') return false;
            if(!VerificaCNPJ(campo.value)){
                campo.value='';campo.focus();
                alert("CNPJ inválido");
            }
            break;
    }
}

function submitPesquisar() {
    eval(zsa)
    f.action="begin.do";f.target="";f.submit();
}

function salvaFiltros() {
    f=document.forms["formFila"];
    filtro=top.frameCTI.filtro;
    filtro.grupoSel=f.grupoSel.value;
    filtro.usuarioSelValue=f.usuarioSel.value;
    var oOption=f.usuarioSel; ;
    var oOptionParent=filtro.usuarioSel;
    while(oOptionParent.options.length!=0) {oOptionParent.options.remove(0);}
    for(i=0;i<oOption.options.length;i++ ) {
        var oOptionNew=parent.document.createElement("OPTION");
        oOptionParent.options.add(oOptionNew);
        oOptionNew.innerText=oOption.options(i).text;
        oOptionNew.value=oOption.options(i).value;
    }


    filtro.estadoSel=f.estadoSel.value;
    filtro.regionalSelecionada=f.regionalSelecionada.value;
    filtro.subEstadoSel=f.subEstadoSel.value;
    filtro.nrProtocolo=f.elements['atdFilaPesqVO.nrProtocolo'].value;
    filtro.optGrpSel1=f.elements['wlw-radio_button_group_key:{pageContext.form.optGrpSel}'][0].checked;
    filtro.optGrpSel2=f.elements['wlw-radio_button_group_key:{pageContext.form.optGrpSel}'][1].checked;
    filtro.dtAberturaInicio=f.elements['atdFilaPesqVO.dtAberturaInicio'].value;
    filtro.dtAberturaFim=f.elements['atdFilaPesqVO.dtAberturaFim'].value;
    filtro.nrLinha=f.elements['atdFilaPesqVO.nrLinha'].value;
    filtro.dtFechamentoInicio=f.elements['atdFilaPesqVO.dtFechamentoInicio'].value;
    filtro.dtFechamentoFim=f.elements['atdFilaPesqVO.dtFechamentoFim'].value;
    filtro.textoContato=f.textoContato.value;
    filtro.idContato=f.elements['atdFilaPesqVO.idContato'].value;
}

function carregaFiltros() {
    f=document.forms["formFila"];
    filtro=top.frameCTI.filtro;
    f.grupoSel.value=filtro.grupoSel;
    f.regionalSelecionada.value=filtro.regionalSelecionada;
    var oOption=filtro.usuarioSel;
    var oOptionParent=f.usuarioSel;
    while(oOptionParent.options.length!=0) {oOptionParent.options.remove(0);}
    for(i=0;i<oOption.options.length;i++ ) {
        var oOptionNew=parent.document.createElement("OPTION");
        oOptionParent.options.add(oOptionNew);
        oOptionNew.innerText=oOption.options(i).text;
        oOptionNew.value=oOption.options(i).value;
    }
    f.usuarioSel.value=filtro.usuarioSelValue;

    f.estadoSel.value=filtro.estadoSel;
    loadSubEstados(filtro.estadoSel);
    f.subEstadoSel.value=filtro.subEstadoSel;
    f.elements['atdFilaPesqVO.nrProtocolo'].value=filtro.nrProtocolo;
    f.elements['atdFilaPesqVO.dtAberturaInicio'].value=filtro.dtAberturaInicio;
    f.elements['atdFilaPesqVO.dtAberturaFim'].value=filtro.dtAberturaFim;
    f.elements['atdFilaPesqVO.nrLinha'].value=filtro.nrLinha;
    f.elements['atdFilaPesqVO.nrLinha'].blur();
    f.elements['atdFilaPesqVO.dtFechamentoInicio'].value=filtro.dtFechamentoInicio;
    f.elements['atdFilaPesqVO.dtFechamentoFim'].value=filtro.dtFechamentoFim;
    f.textoContato.value=filtro.textoContato;
    f.elements['atdFilaPesqVO.idContato'].value=filtro.idContato;
    f.elements['wlw-radio_button_group_key:{pageContext.form.optGrpSel}'][0].checked=filtro.optGrpSel1;
    f.elements['wlw-radio_button_group_key:{pageContext.form.optGrpSel}'][1].checked=filtro.optGrpSel2;
    if (filtro.optGrpSel1) {
        habilitaData(0);
    } else {
        habilitaData(1);
    }
}

/********************************************************
 * @author ppaula
 * @date 2006-02-23
 ********************************************************/


/**
 * Habilita todos os campos de pesquisa, calendario e lupa
 */
function habilitarCampos(){

    // habilita combo gupo
    document.forms[0].grupoSel.disabled = false;

    document.forms[0].regionalSelecionada.disabled = false;

    // habilita combo usuario
    document.forms[0].usuarioSel.disabled = false;

    // habilita combo estado
    document.forms[0].estadoSel.disabled = false;

    // habilita combo sub estado
    document.forms[0].subEstadoSel.disabled = false;

    // habilita campo processo
    document.forms[0].elements("atdFilaPesqVO.nrProtocolo").disabled = false;

    optDataSel=0;
    document.forms[0].elements("wlw-radio_button_group_key:{pageContext.form.optGrpSel}")[0].checked =true;

    // habilita campo abertura
    document.forms[0].elements("wlw-radio_button_group_key:{pageContext.form.optGrpSel}")[0].disabled = false;

    // habilita datas de abertura
    document.forms[0].elements("atdFilaPesqVO.dtAberturaInicio").disabled = false;
    document.forms[0].elements("atdFilaPesqVO.dtAberturaFim").disabled = false;

    // habilita campo linha
    document.forms[0].elements("atdFilaPesqVO.nrLinha").disabled = false;

    // habilita campo fechamento
    document.forms[0].elements("wlw-radio_button_group_key:{pageContext.form.optGrpSel}")[1].disabled = false;

    // habilita datas fechamento
    document.forms[0].elements("atdFilaPesqVO.dtFechamentoInicio").disabled = false;
    document.forms[0].elements("atdFilaPesqVO.dtFechamentoFim").disabled = false;

    // habilita campo contato
    document.forms[0].textoContato.disabled = false;

    // habilita calendarios
    //document.getElementById("imgCalendDtAbIni").onclick="javascript:return showCalendar('atdFilaPesqVO.dtAberturaInicio', '%d/%m/%Y');");
    document.getElementById("imgCalendDtAbIni").style.cursor = "hand";
    //document.getElementById("imgCalendDtAbFim").onclick="return showCalendar('atdFilaPesqVO.dtAberturaFim', '%d/%m/%Y');";
    document.getElementById("imgCalendDtAbFim").style.cursor = "hand";
    //document.getElementById("imgCalendDtFecIni").onclick="return showCalendar('atdFilaPesqVO.dtFechamentoInicio', '%d/%m/%Y');";
    document.getElementById("imgCalendDtFecIni").style.cursor = "hand";
    //document.getElementById("imgCalendDtFecFim").onclick="return showCalendar('atdFilaPesqVO.dtFechamentoFim', '%d/%m/%Y');";
    document.getElementById("imgCalendDtFecFim").style.cursor = "hand";

    // habilita lupa
    //document.getElementById("imgLupa").onclick="arvoreContato(); return false;";
    // document.getElementById("imgLupa").href = "/FrontOfficeWeb/workflow/AtendimentoFila/obterArvoreContato.do";
    document.getElementById("imgLupa").style.cursor = "pointer";
    document.getElementById("imgLupa").style.cursor = "pointer";
}

/**
 * Desabilita campos que devem ser desabilitados quando a pesquisa for por processo ou linha
 */
function desabilitarCampos(){
    document.forms[0].regionalSelecionada.disabled = true;

    // desabilita combo gupo
    document.forms[0].grupoSel.disabled = true;

    // desabilita combo usuario
    document.forms[0].usuarioSel.disabled = true;

    // desabilita combo estado
    document.forms[0].estadoSel.disabled = true;

    // desabilita combo sub estado
    document.forms[0].subEstadoSel.disabled = true;

    // desabilita campo processo
    //document.forms[0].elements("atdFilaPesqVO.nrProtocolo").disabled = true;

    // desabilita campo contato
    document.forms[0].textoContato.disabled = true;

    // desabilita lupa
    //document.getElementById("imgLupa").onclick="void(0)";
    // document.getElementById("imgLupa").href = "javascript:void(0)";
    document.getElementById("imgLupa").style.cursor = "default";
    //document.getElementById("imgLupa").getElementsByTagName("img")[0].style.cursor = "default";
}

/**
 * Desabilita campos para pesquisa por processo
 */
function desabilitarCampos_PesquisarProcesso(obj){
    if(obj.value.length > 0){
        // limpa os campos, exceto o campo processo
        limparPesquisa("proc");

        // desabilita campos
        desabilitarCampos();

        // desabilita campo abertura
        optDataSel=0;
         document.forms[0].elements("wlw-radio_button_group_key:{pageContext.form.optGrpSel}")[0].checked =true;
        document.forms[0].elements("wlw-radio_button_group_key:{pageContext.form.optGrpSel}")[0].disabled = true;

        // desabilita campo linha
        document.forms[0].elements("atdFilaPesqVO.nrLinha").disabled = true;

        // desabilita campo fechamento
        document.forms[0].elements("wlw-radio_button_group_key:{pageContext.form.optGrpSel}")[1].disabled = true;

        // desabilita datas de abertura
        document.forms[0].elements("atdFilaPesqVO.dtAberturaInicio").disabled = true;
        document.forms[0].elements("atdFilaPesqVO.dtAberturaFim").disabled = true;

        // desabilita datas fechamento
        document.forms[0].elements("atdFilaPesqVO.dtFechamentoInicio").disabled = true;
        document.forms[0].elements("atdFilaPesqVO.dtFechamentoFim").disabled = true;

        // desabilita calendarios
        //document.getElementById("imgCalendDtAbIni").onclick="void(0)";
        document.getElementById("imgCalendDtAbIni").style.cursor = "default";
        //document.getElementById("imgCalendDtAbFim").onclick="void(0)";
        document.getElementById("imgCalendDtAbFim").style.cursor = "default";
        //document.getElementById("imgCalendDtFecIni").onclick="void(0)";
        document.getElementById("imgCalendDtFecIni").style.cursor = "default";
        //document.getElementById("imgCalendDtFecFim").onclick="void(0)";
        document.getElementById("imgCalendDtFecFim").style.cursor = "default";
    }else{
        habilitarCampos();
    }
}

/**
 * Desabilita campos para pesquisa por linha
 */
function desabilitarCampos_PesquisarLinha(obj){
    if(obj.value.length > 0){

        // limpa campos, exceto o campo linha
        limparPesquisa("linha");

        // desabilita campos
        desabilitarCampos();

        // desabilita campo processo
        document.forms[0].elements("atdFilaPesqVO.nrProtocolo").disabled = true;

    }else{
        habilitarCampos();
    }
}


/**
 * função que permite desabilitar e habilitar o calendário quando a pesquisa for por processo
 */
function onClickCalendar(name){
    if(document.forms[0].elements("atdFilaPesqVO.nrProtocolo").value.length == 0){
        return showCalendar(name, '%d/%m/%Y');
    }else{
        return false;
    }
}

/**
 * função que permite desabilitar e habilitar a lupa de contato quando a pesquisa for por processo ou linha
 */
function showArvoreContato(){
    if(document.forms[0].elements("atdFilaPesqVO.nrProtocolo").value.length == 0
        && document.forms[0].elements("atdFilaPesqVO.nrLinha").value.length == 0){
        return arvoreContato();
    }else{
        return false;
    }
}

/**
 * Limpa os campos e habilita-os, pois podem estar desabilitados devido a uma pesquisa por processo ou linha
 */
function execLimparPesquisa(){
    habilitarCampos();
    limparPesquisa();
}

function submitPesquisaTotalResultado() {
    if(tipoPesquisaContador==1){
        submitPesquisaBasicaTotalResultado();
    }else{
        submitPesquisaAvancadaTotalResultado();
    }
}

function submitPesquisaBasicaTotalResultado(i) {
    salvaFiltros();
    tipoPesquisa=1;
    ativar_combos();
    statusSel = f.elements["estadoSel"].value;
    dtFecIni = f.elements["atdFilaPesqVO.dtFechamentoInicio"].value;
    dtFecFim = f.elements["atdFilaPesqVO.dtFechamentoFim"].value;

    if(i==null){
        if((f.elements["regionalSelecionada"].value==-1 &&(f.elements["atdFilaPesqVO.nrLinha"].value=="" && f.elements["atdFilaPesqVO.nrProtocolo"].value=="") )){
            alert("É necessário a escolha de uma regional para realizar a pesquisa!");
            return false;
        }
    }
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
    if (optDataSel==1 && document.forms[0].elements("atdFilaPesqVO.nrLinha").value.length <= 0) {
        if (dtFecIni == "" || dtFecFim == "") {
            alert("Para pesquisa de processos Cancelados ou Fechados\né necessário preencher Data Início e Fim!");
            return false;
        }
    }
    if(verificaDatas()) {
        eval(zsa);
        dvEncaminhar.style.display='none';
        document.getElementById("ifrmdvEncaminhar").style.display='none';
        dvSuspeito.style.display='none';
        document.getElementById("ifrmdvSuspeito").style.display='none';
        f.action="pesquisarTotalResultados.do";
        f.target="hiddenIframe";
        f.submit();
    }
}

function submitPesquisaAvancadaTotalResultado() {
    tipoPesquisa=2;
    if(tbListaPesquisa_body.rows.length== 0) {
        //f.elements["formularioCampoSel"].focus();
        alert('Para executar a pesquisa é necessário configurar o filtro');
        return;
    }
    eval(zsa)
    dvEncaminhar.style.display='none';
    document.getElementById("ifrmdvEncaminhar").style.display='none';
    dvSuspeito.style.display='none';
    document.getElementById("ifrmdvSuspeito").style.display='none';
    f.action="pesquisarAvancadoTotalResultados.do";
    f.target="hiddenIframe";
    f.submit();
}

/********************************************************
 * END ppaula
 ********************************************************/