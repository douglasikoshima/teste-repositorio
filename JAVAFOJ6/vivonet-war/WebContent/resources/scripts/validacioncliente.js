/* Monta combos de identificação
 */
function bindCombos() {
    if  ($('idPos')) {
        inProspect = false;
        $('idPos').disabled = false;
        $('tipoRelacionamento').disabled = false;
        switch (tipoRelacionamento) {
            case '6':
                $('tipoRelacionamento').selectedIndex = 3;
                inProspect = true;
                break;
            case '7':
                $('idPos').selectedIndex = 1;
                $('idPos').disabled = true;
                $('tipoRelacionamento').selectedIndex = 3;
                $('tipoRelacionamento').disabled = true;
                idChamadaTelefonica = '';
                identificado = true;
                break;
        }
    }
}
/*
 * Faz identificação positiva
 */
function submitIdPos() {
    if ($('tipoRelacionamento').selectedIndex == 0) {
        alert('Escolha um tipo de relacionamento!');
        $('idPos').selectedIndex = 0;
        return;
    }
    if (pesquisaEfetuada == false && inProspect == false) {
        $('idPos').selectedIndex = 0;
        alert("Não existe uma pesquisa efetuada!");
        return false;
    } else if (inProspect == true
               && (($('tipoRelacionamento').selectedIndex == 1) || ($('tipoRelacionamento').selectedIndex == 2))) {
			   alert('Relacionamento não pode ser Cliente ou Usuário');
        $('idPos').selectedIndex = 0;
        return false;
    } else if (inProspect == false && ($('tipoRelacionamento').selectedIndex == 3) && idTipoRelacionamento != 6) {
        $('idPos').selectedIndex = 0;
        alert('Relacionamento deve ser Cliente ou Usuário');
        return false;
    }
    //Veriffica se a pesquisa é feita através de celular
    if ($F('ti_comboPesquisa') == "celular"){
        valor = $F('ti_inputValorPesquisa');
    } else {
        valor = numeroPesquisado;
    }
    valor = valor+"";
    valor = valor.split("(").join("");
    valor = valor.split(")").join("");
    valor = valor.split("-").join("");
    idPos                = $F('idPos');
    idTipoRelacionamento = $F('tipoRelacionamento');
    if(idPos=="1") inIdPosAutomatico=true;
    //Se não for prospect/Não Cliente
    if (idTipoRelacionamento != '6') {
        //caso identificação positiva
        if (idPos != "0" && inIdPosAutomatico) {
            inIdPosAutomatico = false;
            identificado = true;
            new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/idPosAction.do', {
                method: 'post',
                parameters: {
                    valor: valor, limit: 100,
                    tipoRelacionamento: idTipoRelacionamento, limit: 10,
                    idPos: idPos, limit: 1,
                    idChamadaTelefonica: idChamadaTelefonica, limit: 20
                },
                onComplete: function(originalRequest) {
                    var xmlString = originalRequest.responseText;
                    //prompt('',xmlString);
                    oXml = new ActiveXObject("microsoft.xmldom");
                    oXml.async = false;
                    oXml.loadXML(xmlString);
                    if (oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {
                        exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                        friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                        //severity         = oXml.selectSingleNode("/xml-fragment/severity").text;
                        top.frameApp.$('errTitle').innerHTML = "Erro";
                        top.frameApp.$('errCode').innerHTML = "";
                        top.frameApp.$('errMsg').innerHTML = friendlyMessage;
                        top.frameApp.$('errDetails').value = exceptionMessage;
                        top.frameApp.$('dvAnimarAguardeErro').style.display = "block";
                    } else {
                        idTipoRelacionamento = oXml.selectSingleNode("/xmlDadosTI/parametros/tipoRelacionamento").text;
                        idPessoaCliente      = oXml.selectSingleNode("/xmlDadosTI/parametros/idPessoaCliente").text;
                        idProspect           = oXml.selectSingleNode("/xmlDadosTI/parametros/idProspect").text;
                        inTipoPessoa         = oXml.selectSingleNode("/xmlDadosTI/parametros/inTipoPessoa").text;
                        nrLinha              = oXml.selectSingleNode("/xmlDadosTI/parametros/nrLinha").text;
                        if (oXml.selectSingleNode("/xmlDadosTI/parametros/inCorporativo").text == "1")
                            inCorporativo = true;
                        else inCorporativo = false;
                        if (oXml.selectSingleNode("/xmlDadosTI/parametros/inCorrespondencia").text == "1")
                            inCorrespondencia = true;
                        else inCorrespondencia = false;
                        //retorna a chamada telefonica
                        //caso já tenha uma em curso não carrega, usa a mesma
                        idGrauSatisfacao = oXml.selectSingleNode("/xmlDadosTI/parametros/idSatisfacao").text;
                        if (idGrauSatisfacao == 0) idGrauSatisfacao = 1;
                        if (idGrauSatisfacao == 1)
                            $('idImgSmiley').src = '/FrontOfficeWeb/resources/images/smiley_satisfeito.gif';
                        else if (idGrauSatisfacao == 2)
                            $('idImgSmiley').src = '/FrontOfficeWeb/resources/images/smiley_imparcial.gif';
                        else if (idGrauSatisfacao == 3)
                            $('idImgSmiley').src = '/FrontOfficeWeb/resources/images/smiley_insatisfeito.gif';
                        if (idChamadaTelefonica == '') {
                            idChamadaTelefonica = oXml.selectSingleNode("/xmlDadosTI/parametros/idChamadaTelefonica").text;
                        }
                        try {
                            controlLupaClienteDisplay();
                            controlLupaCarterizacaoSegmentacaoDisplay();
                            controlLupasLinhaUsuarioDisplay();
                        } catch(e) {}
                        colorCorrespondencia();
                        //Ajuste para evitar quebra de linha nas abas
                        if (inCorrespondencia) {
                            if ($('bt04')) $('bt04').innerHTML = 'Hist.Campanha';
                        }
                        //Desabilita select list de Relacionamento
                        $('tipoRelacionamento').disabled = true;
                        //if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
                    }
                }
            });
        } else {
            inIdPosAutomatico = false;
            if($F('idPos')==0)$('tipoRelacionamento').selectedIndex = 0;
            abrePesquisa();
        }
        if (idPos == 0) identificado = false;
    }
    //Prospect
    else {
		iniciarChamado();
        $('tipoRelacionamento').disabled = true;
        $('idPos').disabled = true;
    }
    //Identificação Positiva
    if (idPos != 0) {
        identificado = true;
    } else {
        //Reseta o smiley
        $('idImgSmiley').src = '../../resources/images/smiley_satisfeito.gif';
    }
}
/*
 * Inicia uma chamada telefônica
 */
function iniciarChamado() {
    if (idTipoRelacionamento == '') {
        alert("Tem que identificar a ligação!");
        return;
    }
    if (idChamadaTelefonica != undefined && idChamadaTelefonica != '') {
        alert("Já existe chamada!");
        return;
    }
    var xmlhtttp = new ActiveXObject("microsoft.xmlhttp");
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    xmlhtttp.open("POST","iniciarChamada.do",false);
    xmlhtttp.send();
    var oXml = new ActiveXObject("microsoft.xmldom");
    oXml.async=false;
    if(!oXml.loadXML(xmlhtttp.responseText)){
        alert("Error on load of document");
        alert(oXml.parseError.reason);
        alert(oXml.parseError.line);
    }
    var idChamadaTelefonica = oXml.selectSingleNode("/xmlDadosTI/idChamadaTelefonica").text;
    top.frameApp.idChamadaTelefonica = idChamadaTelefonica;
    var idGrauSatisfacao = oXml.selectSingleNode("/xmlDadosTI/idGrauSatisfacao").text;
    if (idGrauSatisfacao == 0)
        idGrauSatisfacao = 1;
    if (idGrauSatisfacao == 1)
        $('idImgSmiley').src = '/FrontOfficeWeb/resources/images/smiley_satisfeito.gif';
    else if (idGrauSatisfacao == 2)
         $('idImgSmiley').src = '/FrontOfficeWeb/resources/images/smiley_imparcial.gif';
    else if (idGrauSatisfacao == 3)
         $('idImgSmiley').src = '/FrontOfficeWeb/resources/images/smiley_insatisfeito.gif';
	if (idTipoRelacionamento == 6) {
		if (window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();
	}
}
/*
 * Função necessária para execução de eventos pertinentes
 * à ação de clique no botão Terminar.
 */
function submitTerminar() {
    if (idChamadaTelefonica == '') {
        alert("Não existe chamada!");
        return;
    }
    //Verifica se o objeto OCX está carregado
    if (top.frameCTI.isCarregadoOCX()) {
        //Verifica se existe alguma ligação em curso
        if (obtemEstadoLigacaoCTI() == 1) {
            //Verifica se existe alguma ligação pendente
            if (divNovaLigacao.innerHTML == ''){
                alert('Estado com Ligação');
                return;
            }
        }
    }
    new Ajax.Request("terminarAction.do", {
        method: 'get',
        contentType: 'text/xml',
        parameters: {
            idGrauSatisfacao: idGrauSatisfacao, limit: 12
        },
        onComplete: function(originalRequest) {
            var xmlString = originalRequest.responseText;
            oXml = new ActiveXObject("microsoft.xmldom");
            oXml.async = false;
            oXml.loadXML(xmlString);
            if (oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {
                exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                //severity         = oXml.selectSingleNode("/xml-fragment/severity").text;
                top.frameApp.$('errTitle').innerHTML = "Erro";
                top.frameApp.$('errCode').innerHTML = "";
                top.frameApp.$('errMsg').innerHTML = friendlyMessage;
                top.frameApp.$('errDetails').value = exceptionMessage;
                top.frameApp.$('dvAnimarAguardeErro').style.display = "block";
            }
        }
    });
    //if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    try {
        nrProtocolo = '';
        limparDados();
        hideDataScreen();
        retornaBtPesquisar();
        $('ti_inputValorPesquisa').value = '';
        pesquisaEfetuada                 = false;
        idChamadaTelefonica              = '';
        idTipoRelacionamento             = '';
        identificado                     = false;
		if ($('divNovaLigacao') && $('divNovaLigacao').innerHTML != '') {
            carregaLigacaoPendente();
        }
    }catch(e){}
    //Retorna o smiley default
    $('idImgSmiley').src = '/FrontOfficeWeb/resources/images/smiley_satisfeito.gif';
	inIdPosAutomatico = true;
    resetCTIBar();
    $('ti_inputValorPesquisa').focus();
}
/*
*   Transfere a ligaçao.
*/
function transferir() {
    //Liga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    parent.dvTransferir.style.display = '';
    document.forms["0"].method = "POST";
    document.forms["0"].action = "transferirCarregar.do";
    document.forms["0"].target = "ifrmTransferir";
    document.forms["0"].submit();
}
/*
*   Completa a ligaçao em curso
*/
function completarTerminar() {
    parent.dvTransferir.style.display = 'none';
    submitTerminar();
}
/*
 * Limpa os dados da barra CTI
 */
function resetCTIBar(){
    $('spanStatusSenha').innerHTML = "Senha n&atilde;o validada";
    $('spanValidacaoClienteUsuario').show();
	$('ti_comboPesquisa').selectedIndex = 0;
	$('nrProtocolo').update(messages[4]);
	setAlteracaoSMSSolicitacaoProtocolo('solicitacaoProtocolo');
    if ($('ddNrOrigem')) $('ddNrOrigem').update('');
    if ($('ura_nomeContato')) $('ura_nomeContato').value = '';
    if ($('imgUra')) $('imgUra').hide();
    if ($('dtNrOrigem')) $('dtNrOrigem').update('');
    if ($('imgTransferir')) $('imgTransferir').hide();
    ligacaoCTI = false;
    ligacaoCTIPendente = false;
    if ($('idPos') != null) {
        $('idPos').selectedIndex = 0;
        $('tipoRelacionamento').selectedIndex = 0;
    }
}
/**
     *   mostra antendimento anterior
    */
    function voltarAtendimentoAnterior() {
        document.getElementById('divNovaLigacao').style.visibility='hidden';
        document.getElementById('tabelaUra').style.display = '';
    }
    /**
     *   mostra ligacao pendente
    */
    function mostraNovaLigacao() {
        document.getElementById('divNovaLigacao').style.visibility='visible';
        document.getElementById('tabelaUra').style.display = 'none';
    }
  /**
     *   Faz chamada a telaInicial
    */
    function telaInicial(inicioTela) {
        top.frameApp.document.forms[0].method = "POST";
        top.frameApp.document.forms[0].target = "";
        if(inicioTela!='' && inicioTela=='inicioTela'){
            top.frameApp.document.forms[0].action = "/FrontOfficeWeb/cliente/TelaInicial/TelaInicial.do?inicioTela=TRUE";
        }else if(inicioTela=='idPositiva'){
            top.frameApp.document.forms[0].action = "/FrontOfficeWeb/cliente/TelaInicial/TelaInicial.do?idPositiva=TRUE";
        }else{
            top.frameApp.document.forms[0].action = "/FrontOfficeWeb/cliente/TelaInicial/TelaInicial.do";
        }
        top.frameApp.document.forms[0].submit();
    }
    /**
     *   Troca campanha atual selecionada
    */
    function trocarCampanha(itemValue) {
        // se tem campanha aberta deve fechar a campanha
        var campanhas = top.frameCTI.obtemCampanhas();
        var posHifen = campanhas.search('-');
        //se há campanha anterior, deve ser fechada
        if(posHifen >= 0) {
            var campanhaAnterior = campanhas.substr(0, posHifen);
            campanhaAnterior = Trim(campanhaAnterior);
            //marcar inDisponibilidade
            top.frameCTI.alterarEstadoEspecifico('N');
            top.frameCTI.fecharCampanha(campanhaAnterior);
        }
        if(itemValue != -1) {
            oOption = document.getElementById('numCampanhaSel');
            for(i = 0; i < oOption.options.length; i++ ) {
                if(oOption.options(i).value == itemValue) {
                    // abrir a nova campanha
                    top.frameCTI.abrirCampanha(oOption.options(i).text);
                    //marcar disponibilidade
                    top.frameCTI.alterarEstadoEspecifico('S');
                    break;
                }
            }
        }
        obtemDisponibilidadeCTI();
    }
    /**
     *   Obtem disponibilidade do agente
     *   caso esteja logado e disponivel seta como disponivel
    */
    function obtemDisponibilidadeCTI() {
        var estadoAgente = top.frameCTI.obtemEstadoAgente();
        var loginOk = estadoAgente.search('logado');
        var disponivelOk = estadoAgente.search('disponivel');
        if(loginOk >= 0) {
            if(disponivelOk >= 0) {
                document.getElementById('imgDisponibilidade').src = '../../resources/images/icon_atend_disponivel.gif';
            }
            else {
                document.getElementById('imgDisponibilidade').src = '../../resources/images/icon_atend_indisponivel.gif';
            }
        }
        else {
            document.getElementById('imgDisponibilidade').src = '../../resources/images/icon_atend_indisponivel.gif';
        }
    }
    /**
     *   Muda disponibilidade do agente
     *   caso disponivel marca como indisponivel e vice-versa
    */
    function mudaDisponibilidade() {
        //verifica se o objeto OCX está carregado
        if(top.frameCTI.isCarregadoOCX())
        {
            if(obtemEstadoLigacaoCTI()!=1)
            {
                // Executa chamada ao cti para marcar como disponível
                top.frameCTI.alterarEstado();
                //obtém a disponibilidade do usuário quanto ao CTI
                obtemDisponibilidadeCTI();
            }
            else
            {
                alert('Estado com Ligação');
            }
        }
    }
    /**
     *   Obtem o estado da ligaçao
     *   1 - com ligaçao
     *   2 - sem ligaçao
    */
    function obtemEstadoLigacaoCTI() {
        var estadoLigacao = top.frameCTI.obtemEstadoLigacao();
        var comLigacao = estadoLigacao.search('Com');
        var semLigacao = estadoLigacao.search('Sem');
        if(comLigacao >= 0) {
            return 1;
        }
        else{
            if(semLigacao >= 0){
                return 2;
            }
        }
    }
    // Função de Trim, pois o javascript não tem uma função/método correspondente
    function Trim(s) {
        // Remove leading spaces and carriage returns
        while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r')) {
            s = s.substring(1,s.length);
        }
        // Remove trailing spaces and carriage returns
        while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r')) {
            s = s.substring(0,s.length-1);
        }
        return s;
    }
