var ligacaoCTI             = false;
var senhaValidadaCTI       = false;
var ligacaoCTIPendente     = false;
var flUp                   = true;
var pesquisaEfetuada       = false;
var identificado           = false;
var idChamadaTelefonica    = "";
var oXml                   = null;
var idTipoLinha            = "";
var idPessoaCliente        = "";
var inCorporativo          = false;
var inProspect             = false;
var idGrupoAtendimento     = 0;
var idTipoRelacionamento   = "";            /* ID SIGLA DESCRIÇÃO                   ID SIGLA DESCRIÇÃO
                                             * 1  U     USUÁRIO                     2  C     CLIENTE
                                             * 3  R     CONSULTOR RELACIONAMENTO    4  V     EXECUTIVO DE VENDAS
                                             * 5  GC    GESTOR DE CONTA             6  P     PROSPECT
                                             * 7  NC    NÃO CLIENTE                 8  GRP   CONTA GRUPO */
var idPos                  = "";
var idProspect             = "";
var nrConta                = "";
var inLegado               = "";
var ajusteFila             = 0;
var inIdPosAutomatico      = true;
var inVerAbaRelacionamento = false;
var numeroPesquisado       = "";
var idGrauSatisfacao       = 0;
var inCorrespondencia      = false;
var inTipoPessoa           = "";
var inPortada              = false;
var isPortabilidade        = false;	//Linha em processo de Portabilidade
var nrLinha                = "";
var inOrigemWorkflow       = false;
var idAtendimento          = "";
var telaCarregadaFila      = false;
var idRelacionamentoIndex  = 0;
var pesquisa               = "";
var tipoAtendimento        = "";
var fila                   = "";
var limparObjetos          = "";
var exceptionMessage       = "";
var friendlyMessage        = "";
var severity               = 0;
var flUpdateCombos         = false;         /* Seleciona automaticamente listas de Grupo e Tipo de relacionamento.
                                            Utilizado após gravação de dados em pré-pago. */
var inClienteUsuario	   = "";
var idContato              = 0;             /* Mantém ID do Contato selecionado na Árvore de Atendimento */

var isClientePRE           = false; //idTipoLinha==2 || idTipoLinha==6
var isClientePOS           = false; //idTipoLinha==1 || idTipoLinha==4 || idTipoLinha==5 || idTipoLinha==7
var isClienteCDMA          = false; //idTipoLinha==1 || idTipoLinha==2 || idTipoLinha==4
var isClienteGSM           = false; //idTipoLinha==5 || idTipoLinha==6 || idTipoLinha==7
var isClienteCTR           = false; //idTipoLinha==4 || idTipoLinha==7
var nrProtocolo			   = '';
var nrProtocoloScreenPop   = '';
var isScreenPop            = false;
var inAcessoAltNumeroSMS   = false; // Controle de acesso para exibição de ícone de alteração de número de SMS
var inAcessoGerarProtocolo = false; // Controle de acesso para exibição de ícone de solicitação de geração de protocolo
var protocoloInativo       = false;
var inGestorGerente;

var messages = new Array();
messages[0] = "É necessária identificação da ligação para acesso a esta aba.";
messages[1] = "É necessária identificação da ligação para registro de atendimento.";
messages[2] = "Um Não Cliente/Prospect não possui privilégios suficientes para acesso a esta aba.";
messages[3] = "Para acesso a esta aba o relacionamento deve ser Cliente ou Usuário.";
messages[4] = "Protocolo não gerado";
messages[5] = "Para acessar esta funcionalidade é preciso ter um número de protocolo.";

/* ------------------ VERIFICA SE O NÚMERO DO CELULAR É VIVO ------------------ */

function validaPesquisa(protocoloAtivo) {

    tipo = $('ti_comboPesquisa').selectedIndex;
    obj  = $('ti_inputValorPesquisa');

    switch (tipo) {
        case 0:                                                 /* Celular */
            //checaTelefoneNew(obj);
            if ($F(obj)=="") {
                alert("Favor preencher uma linha!");
            } else if(obj.value.length < 8) { //alterado de 12 para 10
                alert("Linha inválida. Favor corrigir!");
            } else {
                if(!flUp) resizeFrameDetalhe();
                abrePesquisa();
            }
            break;
        case 1:                                                 /* Nome do Cliente */
            if (obj.value == "") {
                alert("Favor preencher um nome!");
            } else if(!validaNome(obj.value)) {
                alert("Favor preencher nome e sobrenome!");
            } else {
                abrePesquisa();
            }
            break;
        case 2:                                                 /* Conta */
            checaInteiro(obj);
            if (obj.value == "") {
                alert("Favor preencher uma conta!");
            } else {
                abrePesquisa();
            }
            break;
        case 3:                                                 /* CPF */
            checaCPF(obj);
            if (obj.value == "") {
                alert("Favor preencher um CPF!");
            } else if (!validaCPF(obj.value)) {
                alert("CPF inválido, favor corrigir!");
            } else {
                abrePesquisa();
            }
            break;
        case 4:                                                 /* CNPJ */
            checaCNPJ(obj);
            if (obj.value == "") {
                alert("Favor preencher um CNPJ!");
            } else if(!validaCNPJ(obj.value)) {
                alert("CNPJ inválido. Favor corrigir!");
            } else {
                abrePesquisa();
            }
            break;
        case 5:                                                 /* RNE */
            if (obj.value == ""){
                alert("Favor preencher um RNE!");
            } else {
                abrePesquisa();
            }
            break;
        case 6:                                                 /* Passaporte */
            if (obj.value == "") {
                alert("Favor preencher um Passaporte!");
            } else {
                abrePesquisa();
            }
            break;
        case 7:                                                 /* IE */
            if (obj.value == "") {
                alert("Favor preencher uma IE!");
            } else {
                abrePesquisa();
            }
            break;
        case 9:                                                 /* Certidão de nascimento */
            if (obj.value == "") {
                alert("Favor preencher uma certidão de nascimento!");
            } else {
                abrePesquisa();
            }
            break;
        case 10:                                                /* RG */
            if (obj.value == "") {
                alert("Favor preencher um RG!");
            } else {
                abrePesquisa();
            }
            break;
        case 11:                                                /* Inscrição municipal */
            if (obj.value == "") {
                alert("Favor preencher uma Inscrição municipal!");
            } else {
                abrePesquisa();
            }
            break;
        case 12:                                                /* Prospect workflow */
            checaInteiro(obj);
            abrePesquisa();
            break;
        case 13:                                                /* Protocolo */
            checaInteiro(obj);
            if(obj.value == ""){
                alert("Favor preencher o número do Protocolo!");
            }else{
                if(!flUp) resizeFrameDetalhe();
                abrePesquisa(protocoloAtivo);
            }
            break;
    }
}

/*
 * Função para remoção de caracteres especiais de números de telefone
 */
function  removeMascara(telefone){
    var strCheck = '0123456789';
    var aux = '';
    var len = telefone.length;
    
    //considerando apenas numeros
    for(i = 0; i < len; i++){
        if (strCheck.indexOf(telefone.charAt(i))!=-1){
            aux += telefone.charAt(i);
        }
    }
     
    //limitando o tamanho de 11 caracteres
    var aux2 = '';
    len = aux.length;
    if (len > 11) len = 11;
    for(i = 0; i < len; i++)
    	 aux2 += aux.charAt(i);
     
    return aux2;
}

function pesquisaLinhaAjax(idLinhaTelefonica, nrLinha) {
	var params = $H();
    params.set('idLinhaTelefonica', idLinhaTelefonica);
    params.set('valor', nrLinha);
    params.set('pesquisaScreenPop', false);
    new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/carregaTelaInicialXml.do', {
      method: 'get',
      parameters: params,
      contentType: 'text/xml',
      onComplete: loadUserData
    });
}

/**
 *   funcao responsável por setar valores nos objetos quando executada uma pesquisa
 */
function setValoresPesquisa(valor){
    try {
        idTipoRelacionamento = "";
        idChamadaTelefonica  = "";

        if ($F('ti_inputValorPesquisa') == '') {
            $('ti_inputValorPesquisa').value = valor;
        }
        if ($('bt07')) {
            $('bt07').innerHTML = "";
        }

        $('idPos').selectedIndex = 0;
        $('tipoRelacionamento').selectedIndex = 0;

    } catch(e) {}
}

/*
 * Função acessada quando selecionada a opção "Não Cliente" no combo "Pesquisa por:"
 */
function loadNaoClienteData(originalRequest) {

    //Apresenta aba Relacionamento
    if ($('bt01')) {
        CarregaAba('bt01');
    }

    //Esconde aba Correspondência Devolvida
    if ($('bt07')){
        abaOcultar(btAba, bt07, true);
    }

    loadUserData(originalRequest);
    pesquisaEfetuada = true;

    showDataScreen();
    $('ti_divPrePagoFaturamento').hide();

    controlLupasLinhaUsuarioDisplay();
    $('ti_divLupaCliente').style.cursor = "pointer";
    $('ti_divLupaCliente').onclick = showCadastroProspect;
}

function loadUserData(originalRequest) {

    //inIdPosAutomatico = true;
    var xmlString = originalRequest.responseText;
    oXml          = new ActiveXObject("microsoft.xmldom");
    oXml.async    = false;
    var regExp    = new RegExp ('&', 'gi') ;
    xmlString     = xmlString.replace(regExp,"&amp;");
    oXml.loadXML(xmlString);

    if (oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {
        exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
        friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
        severity         = oXml.selectSingleNode("/xml-fragment/severity").text;
        if(severity=="0"){
            alert(friendlyMessage);
        }else{
            top.frameApp.$('errTitle').innerHTML = "Erro";
            top.frameApp.$('errCode').innerHTML = "";
            top.frameApp.$('errMsg').innerHTML = friendlyMessage;
            top.frameApp.$('errDetails').value = exceptionMessage;
            top.frameApp.$('dvAnimarAguardeErro').style.display = "block";
        }
    } else {
        if (oXml.selectSingleNode("forward") != null) {
            window.location = "/FrontOfficeWeb" + oXml.selectSingleNode("/forward").text;

        } else {
            var tipoCliente = oXml.selectSingleNode("/xmlDadosTI/@tipo").text;
            idPessoaCliente = oXml.selectSingleNode("/xmlDadosTI/parametros/idPessoaCliente").text;
            if (oXml.selectSingleNode("/xmlDadosTI/parametros/tipoRelacionamento") != null) {
                idTipoRelacionamento = oXml.selectSingleNode("/xmlDadosTI/parametros/tipoRelacionamento").text;
            }
            idTipoLinha     = oXml.selectSingleNode("/xmlDadosTI/parametros/idTipoLinha").text;
            inPortada       = (oXml.selectSingleNode("/xmlDadosTI/parametros/inPortada").text == '1') ? true : false;
			nrProtocolo     = (oXml.selectSingleNode("/xmlDadosTI/cliente/nrProtocolo")) ?
				oXml.selectSingleNode("/xmlDadosTI/cliente/nrProtocolo").text : '';
            isScreenPop     = (oXml.selectSingleNode("/xmlDadosTI/parametros/isScreenPop").text == '1') ? true : false;

            if (nrProtocoloScreenPop == '') {
                isScreenPop = false;
            }

			if (nrProtocolo != 'null' && nrProtocolo != '' && !protocoloInativo) {
				$('nrProtocolo').update(nrProtocolo);
				setAlteracaoSMSSolicitacaoProtocolo('alteracaoSMS');
			}

            isClientePRE  = (idTipoLinha==2 || idTipoLinha==6);
            isClientePOS  = (idTipoLinha==1 || idTipoLinha==4 || idTipoLinha==5 || idTipoLinha==7);
            isClienteCDMA = (idTipoLinha==1 || idTipoLinha==2 || idTipoLinha==4);
            isClienteGSM  = (idTipoLinha==5 || idTipoLinha==6 || idTipoLinha==7);
            isClienteCTR  = (idTipoLinha==4 || idTipoLinha==7);

            //Se houver idPessoaCliente e não for "Cliente não identificado"
            if(idPessoaCliente != "" && tipoCliente != "naoCliente" && !inPortada){
                var inBloqueado = oXml.selectSingleNode("/xmlDadosTI/linha/inBloqueado").text;
                try {
                    //DDD bloqueado
                    if (inBloqueado == 1) {
                        $('ti_inputValorPesquisa').focus();
                        alert('DDD '+valor.substring(0,2)+' bloqueado temporariamente. Não é possível seguir com o atendimento.');
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        limparDados();
                        hideDataScreen();

                    } else { //Procedimento normal
                        if(isClienteGSM){
                            showAbasClienteGSM();
                        }else{
                            showAbasClienteCDMA();
                        }

                        showDataScreen();

                        //Oculta Aba Correspondência Devolvida
                        if ($('bt07')) {
                            abaOcultar(btAba, bt07, true);
                        }

                        pesquisaEfetuada = true;

                        //IDPos automático
                        if (inIdPosAutomatico && !flUpdateCombos) {
                            // Variável pode vir como "U" (Usuário) quando pesquisa
                            // na tela de atendimento for feita por número de protocolo.
                            if (inClienteUsuario != "") {
                            	if (inClienteUsuario == "U") {
                            		$('tipoRelacionamento').selectedIndex = 1; // Usuário
                            	} else {
                            		$('tipoRelacionamento').selectedIndex = 2; // Cliente
                            	}
                            } else {
                                if (idTipoRelacionamento == 6) {
                                    $('tipoRelacionamento').selectedIndex = 3;     // Prospect
                                    identificado = true;
                                    iniciarChamado();
                                    $('tipoRelacionamento').disabled = true;
                                    $('idPos').disabled = true;
                                } else {
                                    $('tipoRelacionamento').selectedIndex = 2;     // Cliente
                                }
                        	}
                            $('idPos').value = 1;
                        }
                        limparCamposTela();
                        carregarDados(oXml);
                    }
                }catch(e){
                    alert("[ERRO] - "+e.description);
                }
            }

            //Cliente não identificado
            else if (tipoCliente == "naoCliente") {

                $('ddClienteNome').innerHTML = $('ddClienteNome').title = oXml.selectSingleNode("/xmlDadosTI/nome").text;
                $('ti_divPrePagoFaturamento').hide();
                idTipoRelacionamento = 6;
                idChamadaTelefonica  = "";
                $('tipoRelacionamento').selectedIndex = 3; // Não cliente
                $('idPos').value = 1;
            }

            //Cliente Portabilidade
            else if (inPortada) {

                showDataScreen();
                $('ti_divPrePagoFaturamento').hide();
                $('ti_divLupaCliente').onclick = null;
                $('ti_divLupaUsuario').onclick = null;
                $('ti_divLupaLinha').onclick = null;
                $('ti_lupaCarteirizacao').onclick = null;
                $('ti_lupaSegmentacao').onclick = null;

                showAbasClientePORT();

                pesquisaEfetuada = true;
                limparCamposTela();
                carregarDados(oXml);

                if(window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();
            }
            //Não existe idPessoaCliente
            else {
                verificaLinhaPreAtiva();
                hideDataScreen();
                setTimeout(function(){$('ti_inputValorPesquisa').focus();},300);
                if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
            }

            if (oXml.selectSingleNode("/xmlDadosTI/linha/ItaucardVO")) {
                var itaucardVO = oXml.selectSingleNode("/xmlDadosTI/linha/ItaucardVO");
                if (itaucardVO.selectSingleNode("sgTipoLinha").text == 'PRÉ') {
                    $('ti_divLupaLinha').setStyle({
                        'background' : 'url(/FrontOfficeWeb/resources/images/itauPrePago.gif) no-repeat'
                    })
                } else if (itaucardVO.selectSingleNode("sgTipoLinha").text == 'PÓS') {
                    $('ti_divLupaLinha').setStyle({
                        'background' : 'url(/FrontOfficeWeb/resources/images/itauPosPago.gif) no-repeat'
                    })
                } else if (itaucardVO.selectSingleNode("sgTipoLinha").text == 'CONTROLE') {
                    $('ti_divLupaLinha').setStyle({
                        'background' : 'url(/FrontOfficeWeb/resources/images/itauControle.gif) no-repeat'
                    })
                } else {
                    $('ti_divLupaLinha').setStyle({
                        'background' : 'url(/FrontOfficeWeb/resources/images/ti_lupa_linha.gif) no-repeat'
                    })
                }
            } else {
                $('ti_divLupaLinha').setStyle({
                    'background' : 'url(/FrontOfficeWeb/resources/images/ti_lupa_linha.gif) no-repeat'
                })
            }
        }
    }
    top.frameApp.document.getElementById('divPopupTI').style.display = 'none';
    cleanContent();
}

var prossegirAtendimento = function() {
    var f = document.forms['formBlind'];
    if(f.inibeMsg.checked){
        f.inibeMsg.value = '1';
    }
    f.submit();
    $('winBlindagem').remove();
}

function verificaLinhaPreAtiva() {
    nrLinhaPre = $F('ti_inputValorPesquisa');
    new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/verificaLinhaPreAtiva.do', {
        method: 'post',
        parameters: {
            nrLinha: nrLinhaPre, limit:13
        },
        contentType: 'application/x-www-form-urlencoded',
        onComplete: function(r){
            var xmlString = r.responseText;
            var oXml      = new ActiveXObject("microsoft.xmldom");
            oXml.async    = false;
            var regExp    = new RegExp ('&', 'gi') ;
            xmlString     = xmlString.replace(regExp,"&amp;");
            oXml.loadXML(xmlString);
            if(oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {
                exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                alert(friendlyMessage);
            }else{
                var result   = oXml.selectSingleNode("/xml-fragment/result").text;
                var idLinha  = oXml.selectSingleNode("/xml-fragment/idlinhaTelefonica").text;
                var idPessoa = oXml.selectSingleNode("/xml-fragment/idPessoa").text;

                if(result=="OK" && idLinha!="" && (idPessoa=="" || idPessoa==null)){
                    if (messageBox("Esta linha não está cadastrada. Deseja cadastrá-la agora?") == "6") {
                        if (window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.startAnimation();
                        top.frameApp.location = "/FrontOfficeWeb/cliente/PrePago/begin.do?incluir=true&source=TI&nrLinha="+nrLinhaPre;
                    }
                } else {
                    if (isScreenPop || protocoloInativo) { // Linha não Vivo vinda pelo Screen Pop
                        if (protocoloInativo) {
                            isScreenPop = true;
                        }
                        // Redirecionamento para cadastro de prospect
                        /*if (idPessoa == '' || idPessoa == null) {
                            alert('cadastra prospect')
                            showPopupTI('Inclusão de Prospect', 790, 530, null, 'irCadastroProspect.do?screenPop=true&nrLinhaProspect=' + $F('ti_inputValorPesquisa'));
                        } else { */
                            $('ti_comboPesquisa').value = 'naoCliente';
                            pesquisaNome();
                            $('ti_divLupaCliente').style.cursor = "pointer";
                            $('ti_divLupaCliente').onclick = showCadastroProspect;
                        //}
                    } else {
                        if (confirm('Linha não cadastrada!\nDeseja gerar um protocolo para linha de não cliente?')) {
                            top.location.href = 'http://' + location.host + '/FrontOfficeWeb/consultaLinha.do?nrLinha=' + $F('ti_inputValorPesquisa');
                        }
                        else{
                        	$('divPopupTI').style.display = 'none';
                        	cleanContent();
                        }
                    }
                }
            }
        },
        onFailure: function(e){alert("[Failure] "+e+"\n");},
        onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport);}
    });
}

function resetValidacao() {
    $('tipoRelacionamento').selectedIndex=0;
    $('idPos').selectedIndex=0;
}

function abrePesquisa(protAtivo) {
    pesquisa = $F('ti_comboPesquisa');
    valor    = trim($F('ti_inputValorPesquisa'));
    if(pesquisa != "nome" && pesquisa != "Pas" && pesquisa != "RNE"){
        for(i = 0; i < valor.length; i++) {
            if (!inteiro.test(valor.charAt(i))) {
                valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
                i = 0;
            }
        }
    }

    if (top.frameApp.dvAnimarAguarde) top.frameApp.startAnimation();

	if (nrProtocoloScreenPop == '') {
		$('nrProtocolo').update(messages[4]);
		setAlteracaoSMSSolicitacaoProtocolo('solicitacaoProtocolo');
	}
    	
	//Ocultação da aba Dados do Chip
    if($('bt09')){
    	abaOcultar(btAba, bt09, true);
    }
    if (pesquisa == 'CPF' || pesquisa == 'CNPJ') {
        $('ti_inputValorPesquisa').value = valor;
    }
    // Caso tenha vindo do workflow - lupa de detalhe
    var action;
	var sProtAtivo = protAtivo ? "true" : "false";
    if ($('ti_comboPesquisa').selectedIndex == 12) { //Não Cliente
        action = '/FrontOfficeWeb/cliente/TelaInicial/pesquisaCliente.do?tipoLista=linhas&clienteSelecionado='+valor+'&idPessoa='+valor+"&protocoloAtivo="+sProtAtivo;
    } else if ($('ti_comboPesquisa').selectedIndex == 0) { // Linha
    	action = '/FrontOfficeWeb/cliente/TelaInicial/pesquisaCliente.do?tipoLista=linhas&valor='+valor+'&pesquisa='+pesquisa+"&protocoloAtivo="+sProtAtivo;
    } else {
    	action = '/FrontOfficeWeb/cliente/TelaInicial/pesquisaCliente.do?tipoLista=clientes&valor='+valor+'&pesquisa='+pesquisa+"&protocoloAtivo="+sProtAtivo;
    }
    //controlCombos();
    showPopupTI('Pesquisa de clientes', 790, 530, null, action);
}

/*
 * Função utilizada para gerenciamento do grau de satisfação do cliente/usuário.
 */
manageSmiley = function(obj) {

    if (obj != undefined) {
        switch (obj.id) {
            case "smileySatisfeito":
                $('idImgSmiley').src = "/FrontOfficeWeb/resources/images/smiley_satisfeito.gif";
                idGrauSatisfacao = 1;
                break;
            case "smileyImparcial":
                $('idImgSmiley').src = "/FrontOfficeWeb/resources/images/smiley_imparcial.gif";
                idGrauSatisfacao = 2;
                break;
            case "smileyInsatisfeito":
                $('idImgSmiley').src = "/FrontOfficeWeb/resources/images/smiley_insatisfeito.gif";
                idGrauSatisfacao = 3;
                break;
        }
        $('divSmiley').hide();
    } else {
        $('divSmiley').show();
    }
}

/**
*  funcao responsável por carregar os dados da linha na tela quando
*  recebe ligaçao de CTI
*/
function pesquisaLinhaCTI(){

    var xmlhtttp = new ActiveXObject("microsoft.xmlhttp");
    xmlhtttp.open("GET","/FrontOfficeWeb/cliente/TelaInicial/carregaTelaInicialXml.do?valor="+valor,false);
    xmlhtttp.setRequestHeader("Content-Type","text/xml");
    xmlhtttp.setRequestHeader("chartset","ISO-8859-1");
    xmlhtttp.send();
    /*
    *   Somente mostrar dados se idPessoaCliente != ''
    */
    oXml = new ActiveXObject("microsoft.xmldom");
    var str = xmlhtttp.responseText;
    var re = new RegExp ('&', 'gi');
    str =str.replace(re,"&amp;");
    oXml.async=false;
    if(!oXml.loadXML(str)){
        alert("Error on load of document\n"+oXml.parseError.reason+" on "+oXml.parseError.line);
    }else{
        if(oXml.selectSingleNode("/xmlDadosTI/@tipo").text!='erro'){
            //var idPessoaCliente = oXml.selectSingleNode("/xmlDadosTI/parametros/idPessoaCliente").text;
            //somente carrega se existir idPessoaCliente
            if(idPessoaCliente!=''){
                try{
                    frmDados.limparCamposTela();
                    frmDados.carregarDados(str);
                    frmDados.ifrLinha.carregarDados(str);
                }catch(e){
                    //alert('Privilégios insuficientes para a exibição correta da página!');
                }
                CarregaAba('linha');
                pesquisaEfetuada = true;
                showDataScreen();
            }else{
                alert('Linha não cadastrada!');
                $('ti_inputValorPesquisa').focus();
                if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
            }
        }else{
            alert(oXml.selectSingleNode("/xmlDadosTI/erro").text);
        }
    }
}

/*
 * Função utilizada para preenchimento da Tela de Atendimento
 * quando tratar-se de um prospect.
 */
function getProspect() {

    pesquisaEfetuada     = true;
    inProspect           = true;
    idTipoRelacionamento = 6;
    idTipoLinha = '';

    $('tipoRelacionamento').selectedIndex = 3; //Não cliente
	$('idPos').selectedIndex = 1;

    new Ajax.Request("/FrontOfficeWeb/cliente/TelaInicial/loadDados.do", {
        method: 'get',
        contentType: 'text/xml',
        onComplete: function(originalRequest) {

            top.frameApp.showDataScreen();

            var xmlString = originalRequest.responseText;
            var oXml      = new ActiveXObject("microsoft.xmldom");
            oXml.async    = false;
            var regExp    = new RegExp ('&', 'gi') ;
            xmlString     = xmlString.replace(regExp,"&amp;");

            oXml.loadXML(xmlString);

            var nmCliente       = oXml.selectSingleNode("/xml-fragment/TICliente/nmNome").text;
            var nrTelefone      = oXml.selectSingleNode("/xml-fragment/TICliente/nrTelefone").text;
            var inTipoPessoa    = oXml.selectSingleNode("/xml-fragment/TICliente/inTipoPessoa").text;
            var dsTipoDocumento = oXml.selectSingleNode("/xml-fragment/TIDocumento/dsTipoDocumento").text;
            var nrDocumento     = oXml.selectSingleNode("/xml-fragment/TIDocumento/nrDocumento").text;

            switch(inTipoPessoa) {
                case "PF":
                    inTipoPessoa = "PESSOA F&Iacute;SICA";
                    nrDocumento = getMaskCPF(nrDocumento);
                    break;
                case "PJ":
                    inTipoPessoa = "PESSOA JUR&Iacute;DICA";
                    nrDocumento = getMaskCNPJ(nrDocumento);
                    break;
            }

            $('ddClienteNome').update(nmCliente);
            $('dtClienteTipoDocumento').update(dsTipoDocumento);
            $('ddClienteDocumento').update(nrDocumento);
            $('ddClienteNatureza').update(inTipoPessoa);
            $('ddClienteTelefone').update(nrTelefone);

            $('ti_divLupaCliente').style.cursor = "pointer";
            $('ti_divLupaCliente').onclick = showLupaCliente;

			controlLupasLinhaUsuarioDisplay();

            $('ti_divPrePagoFaturamento').hide();

			submitIdPos();
            CarregaAba("bt01");

		}
    });
}

/*
 * Função utilizada para redimensionamento e reposicionamento
 * do popup da Tela de Atendimento.
 */
function changePopupTIProps(width, height) {
    $('wrapper_divPopupTI').style.marginLeft = "0";
    $('wrapper_divPopupTI').style.marginTop  = "0";
    var left = (800 - width) / 2;
    var top  = (600 - height) / 2;
    $('wrapper_divPopupTI').style.marginLeft = left + "px";
    $('wrapper_divPopupTI').style.marginTop  = top + "px";
    $('iframePopupTI').width  = width;
    $('iframePopupTI').height = height;
    $('header_divPopupTI').style.width  = width + "px";
}

function controlCombos() {
/*
    for(i=0; i < document.frames.length; i++){
        var  array = document.frames[i].document.getElementsByTagName("SELECT");
        internalCombos(array);
    }
    internalCombos(document.getElementsByTagName("SELECT"));
*/
}

function internalCombos(array){
    for(j=0; j < array.length; j++){
        if(array[j].style.visibility == 'hidden')
            array[j].style.visibility = 'visible';
        else
            array[j].style.visibility = 'hidden';
    }
}

function CarregaAba(nome){

    var pagina = "";
    if (nome != "bt11") {
        $("ti_frameAbas").src = 'about:blank';
    }

    top.frameApp.$('ti_frameAbas').setStyle({
        width: '788px',
        overflowX: 'hidden',
        overflowY: 'auto'
    });

	switch (nome) {

        case "linha":
            //Seleciona aba Relacionamento
            if($('bt01')){
                abaSelected(btAba, bt01);
            }
            $("ti_frameAbas").style.display = "block";

            //variável para controle de radio na aba relacionamento
            ti_frameAbas.setaRadio = true;
            ti_frameAbas.limparSemFoco();
            ti_frameAbas.pesquisarRelacionamento();
            return;
            break;

        case "bt01":                                                                /* Aba Relacionamento */
            abaSelected(btAba, bt01);
            $("ti_frameAbas").setaRadio = true;
            pagina = "/FrontOfficeWeb/cliente/TelaInicial/carregaRelacionamento.do";
            break;

        case "bt02":                                                                /* Aba Atendimento */
            //Verifica se existe uma chamada telefônica
            if (!identificado) {
                alert(messages[1]);
                oculta_div();
                return;
            }else{
                abaSelected(btAba, bt02);
                pagina = "/FrontOfficeWeb/workflow/RegistrarContato/begin.do";
            }
            break;

        case "bt03":                                                                /* Aba Serviços */
            if (!identificado) {
                alert(messages[0]);
                oculta_div();
                return;
            }else if(idTipoRelacionamento!='1' && idTipoRelacionamento!='2'){
                alert(messages[3]);
                oculta_div();
                return;
            }else{
                abaSelected(btAba, bt03);
                pagina = "/FrontOfficeWeb/cliente/TelaInicial/getHistoricoServicos.do";
            }
            break;

        case "bt04":                                                                /* Aba Histórico de Campanhas */
            pagina = "/FrontOfficeWeb/campanha/HistoricoCampanha/begin.do";
            break;

        case "bt05":                                                                /* Aba Senha */
            if (idTipoRelacionamento != "") {
                if (idTipoRelacionamento != "1" && idTipoRelacionamento != "2") {
                    alert(messages[2]);
                    oculta_div();
                    return;
                } else {
                    abaSelected(btAba, bt05);
                    pagina = "/FrontOfficeWeb/senha/historicoSenha/begin.do";
                    if (!inOrigemWorkflow){
                        if (flUp)
                            resizeFrameDetalhe();
                    }
                }
            } else {
                alert(messages[0]);
                oculta_div();
                return;
            }
            break;

        case "bt06":                                                                    /* Aba Retenção */
            pagina = "/FrontOfficeWeb/fidelizacao/begin.do";
            break;

        case "bt07":                                                                    /* Aba Correspondência Devolvida */
            pagina = "/FrontOfficeWeb/cliente/CDevolvida/telaInicial/begin.do?start=true";
            break;

        case "bt08":                                                                    /* Aba Campanha */
            pagina = "/FrontOfficeWeb/campanha/ExecutarCampanha/telaInicial.do";
            break;

        case "bt09":                                                                    /* Aba Chip */

            if (!identificado || (idTipoRelacionamento!='1') && (idTipoRelacionamento!='2')) {
                alert(messages[0]);
                oculta_div();
                return;
            } else {
                abaSelected(btAba, bt09);
                pagina = "/FrontOfficeWeb/cliente/TelaInicial/getDadosChip.do";
            }
            break;

        case "bt10":                                                                    /* Aba IMEI */
            if (!identificado) {
                alert(messages[0]);
                oculta_div();
                return;
            } else {
                abaSelected(btAba, bt10);
                pagina = "/FrontOfficeWeb/cliente/TelaInicial/showAbaIMEI.do";
            }
            break;

		case "bt11":
            if (idTipoRelacionamento != '' && nrProtocolo != '') {
                $("ti_frameAbas").src = 'about:blank';
                abaSelected(btAba, bt11);
                resizeFrameDetalhe();
                pagina = "/FrontOfficeWeb/cliente/TelaInicial/trackingLista.do";
            } else {
                if (nrProtocolo == '') {
                    alert(messages[5]);
                    oculta_div();
                    return;
                } else {
                    alert(messages[0]);
                    oculta_div();
                    return;
                }
            }
            break;
    }
    if (window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.startAnimation();
    $("ti_divConteudoAbas").show();
    $("ti_frameAbas").src = pagina;
}

function carregaTela() {
    // Carregamento normal da Tela de Atendimento
    if ($F('ti_inputValorPesquisa').blank()) {
        showBarraPesquisa();
        showBarraHorizontal();
        $('menuPrincipal').style.display = 'block';
        $('menuVoltar').style.display = 'none';
        $('ti_inputValorPesquisa').focus();

    }
    // Necessário carregamento automático
    else {
        showBarraPesquisa();
        showBarraHorizontal();
        if (pesquisa != '') {
            $('ti_comboPesquisa').selectedIndex = 2;
        }

        // Se não houver linha ou conta, utiliza-se o idPessoa
        if (tipoAtendimento == 'prospect' && nrLinha != ''){
            $('ti_comboPesquisa').selectedIndex = 12;
            if (window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.startAnimation();
        }

        if (nrLinha == 26){
            tipoAtendimento = 'naoCliente';
            pesquisaNome();
            $('ti_inputValorPesquisa').disabled = true;
        } else {
            validaPesquisa();
        }

        // Chamada proveniente de Workflow
        if (telaCarregadaFila) {
            $('menuVoltar').style.display = 'block';
            if (pesquisa == 'celular') {
                pesquisaEfetuada = true;
                carregaTelaFila();
            } else {
                hideBarraPesquisa();
                hideBarraHorizontal();
                $('menuPrincipal').style.display = 'none';
            }
            if (tipoAtendimento == 'naoCliente') {
                $('ti_comboPesquisa').selectedIndex = 12;
                carregaTelaFila();
            }
        }

        if(flUpdateCombos)
            updateSelectLists();

    }
}

/* Função utilizada para atualização dos valores das selects lists
 * de Grupo de Atendimento, Tipo de Relacionamento e Identificação
 * Positiva na Tela de Atendimento. Utilizada após gravação de
 * dados em processos de manutenção de cadastro de pré-pago.
 */
updateSelectLists = function(){

    for(i=0;i<$('grupoSel').length;i++){
        if ($('grupoSel').options[i].value == idGrupoAtendimento){
            $('grupoSel').selectedIndex = i;
            break;
        }
    }
    for(i=0;i<$('tipoRelacionamento').length;i++){
        if ($('tipoRelacionamento').options[i].value == idTipoRelacionamento){
            $('tipoRelacionamento').selectedIndex = i;
            break;
        }
    }
    for (i=0;i<$('idPos').length;i++){
        if ($('idPos').options[i].value == idPos){
            $('idPos').selectedIndex = i;
            break;
        }
    }
}

/*
 * Função utilizada para retorno à Tela de Detalhes do Atendimento.
 */
function mostraDetalhe() {
    top.frameApp.location = "/FrontOfficeWeb/workflow/AtendimentoDetalhe/begin.do?idAtendimento=" + idAtendimento + "&fila=" + fila;
}

/*
 * Função utilizada para carregamento de dados da Tela de Atendimento quando origem for
 * Workflow, fazendo tratamento necessário para exibição de informações e abas.
 */
function carregaTelaFila() {

    ajusteFila = 86;

    $('ti_frameAbas').height = 250;
    $('ti_divConteudoAbas').style.height = 250 + "px";
    $('divContainer').style.height = "520px";

    // Não cliente / Prospect
    if ($('ti_comboPesquisa') == 12 ) {

        abaOcultar(btAba, bt02, true);
        abaOcultar(btAba, bt03, false);
        abaOcultar(btAba, bt04, false);
        abaOcultar(btAba, bt05, false);
        abaOcultar(btAba, bt06, true);
        abaOcultar(btAba, bt07, false);
        abaOcultar(btAba, bt08, true);
        //if (window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();

    } else {

        $('ti_divConteudoAbasTI').show();
        abaOcultar(btAba, bt02, true);
        abaOcultar(btAba, bt03, false);
        abaOcultar(btAba, bt04, false);
        abaOcultar(btAba, bt05, false);
        //abaOcultar(btAba, bt06, true);
        abaOcultar(btAba, bt07, false);
        abaOcultar(btAba, bt08, true);
        abaOcultar(btAba, bt06, false);
        hideBarraHorizontal();
        hideBarraPesquisa();
        $('menuPrincipal').style.display = 'none';
        $('idPos').selectedIndex = 1;
        $('tipoRelacionamento').selectedIndex = idRelacionamentoIndex;
        submitIdPos();
    }
}

/* Função utilizada para resize das abas da tela de atendimento.
 * @param flBtMinMax    booleano    Indica se clique foi feito no botão de minimização/maximização
 */
function resizeFrameDetalhe(flBtMinMax) {

    var oldFrameHeight = 0;

    $("ti_divConteudoAbasTI").style.backgroundColor = '#FFFFFF';

    var newTop  = Position.get($('ti_divCliente')).top - 87;
    var conteudoAbasHeightUp, conteudoAbasHeightDown, topUp, topDown;
    conteudoAbasHeightDown = 170;

    if (telaCarregadaFila) {
        conteudoAbasHeightUp = 483;
        conteudoAbasHeightDown = 248;
        topUp = 0;
        topDown = 235;
    } else {
        conteudoAbasHeightUp = 400;
        topUp = 78;
        topDown = 310;
    }

    // Aumentar área de visibilidade
    if (flUp) {

        $("ti_divConteudoAbasTI").style.position = 'absolute';
        Position.set($("ti_divConteudoAbasTI"), 0, topUp);

        $("ti_divAbasResizer").style.backgroundImage = "url(/FrontOfficeWeb/resources/images/bt_lupa_aba_down.gif)";

        $("ti_divConteudoAbas").style.height = conteudoAbasHeightUp + "px";
        $("ti_frameAbas").height = conteudoAbasHeightUp;

        $('menuVoltar').style.position = "absolute";
        $('menuVoltar').style.top = "502px";
        $('menuVoltar').style.left = "0";

        flUp = false;

    }

    // Diminuir área de visibilidade
    else {

        $("ti_divConteudoAbasTI").style.position = 'absolute';
        Position.set($("ti_divConteudoAbasTI"), 0, topDown);

        $("ti_divConteudoAbas").style.height = conteudoAbasHeightDown + "px";
        $("ti_frameAbas").height = conteudoAbasHeightDown;

        $("ti_divAbasResizer").style.backgroundImage = "url(/FrontOfficeWeb/resources/images/bt_lupa_aba.gif)";

        flUp = true;

    }
}

function escolheMascara(obj) {
    tipo = $('ti_comboPesquisa').selectedIndex;
    qtdeCaracteres = 0;
	switch(tipo) {
        case 0:         //Celular
            //checaTelefoneNew(obj);
            obj.value = removeMascara(obj.value);
            qtdeCaracteres = 11;
            break;
        case 1:         //Nome do Cliente
            qtdeCaracteres = 255;
            break;
        case 2:         //Conta
            checaInteiro(obj);
            qtdeCaracteres = 100;
            break;
        case 3:         //CPF
            checaCPF(obj);
            qtdeCaracteres = 14;
            break;
        case 4:         //CNPJ
            checaCNPJ(obj);
            qtdeCaracteres = 18;
            break;
        case 5:         //RNE
            //checaInteiro(obj);
            qtdeCaracteres = 25;
        case 6:         //Passaporte
            qtdeCaracteres = 25;
            break;
        case 7:         //IE
            checaInteiro(obj);
            qtdeCaracteres = 25;
        case 9:         //RG
            qtdeCaracteres = 25;
        case 10:        //IM
            checaInteiro(obj);
            qtdeCaracteres = 25;
            break;
        case 13: //Protocolo
            checaInteiro(obj);
            qtdeCaracteres = 22;
            break;
    }
	if (qtdeCaracteres != 0) {
		obj.maxLength = qtdeCaracteres;
	}
}

/*
 * Troca o grupo do usuário logado
 */
function trocarGrupo(idGrupo) {

    new Ajax.Request("/FrontOfficeWeb/cliente/TelaInicial/carregaGrupo.do", {
        method: 'get',
        contentType: 'text/xml',
        parameters: { idGrupo: idGrupo, limit: 12 },
        onComplete: function(originalRequest) {

        }
    });

}

/*
 * Efetua o tratamento ao receber uma chamada do CTI
 */
function receberChamada(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA) {

    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

    //verifica se irá carregar nova ligação
    if (idChamadaTelefonica == '') {

        var strAction = "carregaParametrosCTI.do?";
        strAction += "idUra=" + GrupOrigCh;
        strAction += "&";
        strAction += "idCallCenter=" + GrupDest;
        strAction += "&";
        strAction += "inSenhaValidada=";
        strAction += (CliAut == 'S');
        strAction += "&";
        strAction += "nrLinha=" + NumLinCliUra;
        strAction += "&";
        strAction += "nrTelefone=" + NumOrigCh;
        strAction += "&";
        strAction += "observacao=" + ObsAtend;
        strAction += "&";
        strAction += "timeoutURA=" + IndRedirTimeoutURA;
        strAction += "&";
        strAction += "navegacaoURA=" + NavCliURA;
        strAction += "&";
        strAction += "tipProc=" + TipProc;
        strAction += "&";
        strAction += "numProc=" + NumProc;
        strAction += "&";
        strAction += "idTipoRelacionamento=";

        switch(IndTitular){
            case 'C':
                strAction += '2';
                break;
            case 'U':
                strAction += '1';
                break;
            case 'N':
                strAction += '6';
                break;
        }

        /* NOVO */
        $('ddNrOrigem').update(NumOrigCh);
        /* CONTINUAR DEPOIS */

        /* document.forms[0].method = "POST";
        document.forms[0].target = "frameEscondido";
        document.forms[0].action = strAction;
        document.forms[0].submit(); */

        new Ajax.Request(strAction, {
            method: 'post',
            contentType: 'text/xml',
            onComplete: function(originalRequest) {

            }
        });

    } else {

        var strAction = "/FrontOfficeWeb/cliente/TelaInicial/carregarLigacao.do?";
        strAction += "idUra=" + GrupOrigCh;
        strAction += "&";
        strAction += "idCallCenter=" + GrupDest;
        strAction += "&";
        strAction += "inSenhaValidada=";
        strAction += (CliAut == 'S');
        strAction += "&";
        strAction += "nrLinha=" + NumLinCliUra;
        strAction += "&";
        strAction += "nrTelefone=" + NumOrigCh;
        strAction += "&";
        strAction += "observacao=" + ObsAtend;
        strAction += "&";
        strAction += "timeoutURA=" + IndRedirTimeoutURA;
        strAction += "&";
        strAction += "navegacaoURA=" + NavCliURA;
        strAction += "&";
        strAction += "tipProc=" + TipProc;
        strAction += "&";
        strAction += "numProc=" + NumProc;
        strAction += "&";
        strAction += "idTipoRelacionamento=";

        switch (IndTitular) {
            case 'C':
                strAction += '2';
                break;
            case 'U':
                strAction += '1';
                break;
            case 'N':
                strAction += '6';
                break;
        }

        document.forms[0].method = "POST";
        document.forms[0].action = strAction;
        document.forms[0].target = "frameEscondido";
        document.forms[0].submit();

    }
}

/*
 * Tratamento quando a ligação for finalizada.
 * TODO: Tratamento completo de CTI
 */
function semLigacao() {

    $('numCampanhaSel').disabled = false;
    $('imgTransferir').style.display = 'none';
    $('dvImgIniciarChamado').style.display = 'none';
    $('grupoSel').disabled = false;
    alert('Ligação Finalizada');
}

function showPopupTI(title, width, height, top, action, msgImpeditiva) {

    html = '    <div id="wrapper_divPopupTI" style="width:600px;height:400px;margin:100px 0 0 100px;" class="popUpDiv">';
    html += '        <div id="header_divPopupTI" style="width:600px;height:21px;background-image:url(/FrontOfficeWeb/resources/images/window_topbg.gif);background-color:#0066cb;">';
    html += '            <span id="title_divPopupTI" style="float:left;padding:3px 0 0 3px;;color:#fff;font-weight:bold;">';
    html += '                Atendimento';
    html += '            </span>';
    html += '            <span id="upperRightButton_divPopupTI" style="float:right;">';
    html += '                <img id="imgClose_divPopupTI" hspace="3" src="/FrontOfficeWeb/resources/images/window_fechar.gif" ';
    if (msgImpeditiva != null && msgImpeditiva != undefined && msgImpeditiva != "") {
        html += 'onmouseup="alert(\''+msgImpeditiva+'\');" ';
    } else {
        html += "onmouseup=\"$('divPopupTI').style.display='none';cleanContent();\" ";
    }
    html += 'style="cursor:pointer;">';
    html += '            </span>';
    html += '        </div>';
    html += '        <div id="wrapper_iframePopupTI" style="width:600px;background-color:#0066cb;">';
    html += '            <iframe width="600" height="395" id="iframePopupTI" name="iframePopupTI" frameborder="0" scrolling="no"></iframe>';
    html += '        </div>';
    html += '    </div>';
    html += '    <!--[if lte IE 6.5]><iframe style="display:none;display/**/:block;position:absolute;top:0;left:0;z-index:-1;filter:mask();width:3000px;height:3000px;"></iframe><![endif]-->';
    window.top.frameApp.$('divPopupTI').innerHTML = html;

    if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();

    if ($('divPopupTI').style.display != "none") {
        $('divPopupTI').style.display = "none;";
    }
    //controlCombos();
    $('divPopupTI').style.display = "block";
    $('title_divPopupTI').innerHTML = title;
    $('iframePopupTI').width = width;
    $('wrapper_iframePopupTI').style.width = width + "px";
    $('header_divPopupTI').style.width = width + "px";
    $('iframePopupTI').height = height-5;
    $('iframePopupTI').src = action;
    $('wrapper_divPopupTI').style.marginLeft = (800-width)/2 + "px";
    if (top != null) {
        $('wrapper_divPopupTI').style.marginTop = (top-21) + "px";
    } else {
        $('wrapper_divPopupTI').style.marginTop = (600-height)/2 + "px";
    }
}

function cleanContent() {$('iframePopupTI').src = "about:blank";}
function showLupaCliente(){showPopupTI('Detalhes do cliente',790,530,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/begin.do");}
function showCadastroProspect(){showPopupTI('Inclusão de Prospect', 790, 530, null, '/FrontOfficeWeb/cliente/TelaInicial/irCadastroProspect.do?screenPop=true&nrLinhaProspect=' + $F('ti_inputValorPesquisa'));}
function showLupaLinha(){showPopupTI('Detalhes da linha',790,515,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheLinha.do");}
function showLupaSegmentacao(){showPopupTI('Detalhes de Segmentação',790,290,null,"/FrontOfficeWeb/cliente/TelaInicial/loadSegmentacao.do");}
function showLupaCarteirizacao(){var metodo = ""; if (inCorporativo) metodo = "/FrontOfficeWeb/cliente/TelaInicial/loadCarteirizacao.do"; else metodo = "loadCarteiraPF.do"; showPopupTI('Detalhes de Carteirização', 790, 290, 35, metodo);}
function showLupaUsuario(){showPopupTI('Detalhes de usuário',790,400,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheUsuario/begin.do");}
function showLupaFaturamento(){showPopupTI('Detalhes de faturamento',790,390,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheFatura/begin.do");}
function showLupaPrePago(){showPopupTI('Detalhes de Pré-pago',790,450,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalhePrePago/lupaPrePago.jsp");}
function showLupaClienteGestor(){showPopupTI('Detalhes do cliente',790,530,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/begin.do?aba=Gestor");}

function fechaLupa() {
    cleanContent();
    $('divPopupTI').style.display = 'none';
    //controlCombos();
    if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
}

function pesquisaNome() {

    limparDados();
    var tipoPesquisa = $F('ti_comboPesquisa');

    if (tipoPesquisa == 'nome' || tipoPesquisa == 'razao') {
        showPopupTI('Pesquisa de Clientes',575,400,100,"/FrontOfficeWeb/cliente/TelaInicial/irPesquisaNome.do?tipo=" + tipoPesquisa);

    } else if (tipoPesquisa == 'naoCliente') {
        new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/carregaNaoClienteXml.do', {
            method: 'get',
            contentType: 'text/xml',
            onComplete: loadNaoClienteData
        });

    } else {
        return false;
    }
}

function limparDados() {

    pesquisaEfetuada     = false;
    identificado         = false;
    idChamadaTelefonica  = "";
    idTipoRelacionamento = "";
    inClienteUsuario     = "";

    if(ligacaoCTI == false){
        idTipoRelacionamento = "";
    }

    if($('idPos') && inIdPosAutomatico){
        $('idPos').selectedIndex = 0;
        $('tipoRelacionamento').selectedIndex = 0;
    }

    limparCamposTela();
}

function limparCamposTela(){
    try {
        /* Limpeza de informações - Dados de cliente */
        $('ddClienteNome').innerHTML = '';
        $('ddClienteDocumento').innerHTML = '';
        $('ddClienteNatureza').innerHTML = '';
        $('ddClienteTelefone').innerHTML = '';
        $('ddClienteChurn').innerHTML = '';
        $('ddSegmentacao').innerHTML = '';
        $('ddCarteirizacao').innerHTML = '';

        /* Limpeza de informações - Dados de Gestor */
        $('ddGestorNome').innerHTML = '';
        $('ddGestorTelefone').innerHTML = '';

        /* Limpeza de informações - Dados de linha */
        $('ddNrLinha').innerHTML = '';
        $('ddTipoLinha').innerHTML = '';
        $('ddPlanoServico').innerHTML = '';
        $('ddStatusLinha').innerHTML = '';

        /* Limpeza de informações - Dados de usuário */
        $('ddUsuarioNome').innerHTML = '';
        $('ddUsuarioDocumento').innerHTML = '';
        $('ddUsuarioCargo').innerHTML = '';
        $('ddUsuarioContato').innerHTML = '';

        /* Limpeza de informações - Quantidade de linhas */
        //$('ddQtdeLinhas').innerHTML = '';
        //$('ddLinhasAtivas').innerHTML = '';
        //$('ddLinhasCanceladas').innerHTML = '';

        /* Limpeza de informações - Dados de endereço */
        $('ddEndereco').innerHTML = '';
        $('ddBairro').innerHTML = '';
        $('ddCidade').innerHTML = '';
        $('ddEstado').innerHTML = '';
        $('ddCEP').innerHTML = '';

        /* Limpeza de informações - Dados de faturamento */
        if ($('ddFaturamentoConta')) $('ddFaturamentoConta').innerHTML = '';
        if ($('ddFaturamentoVencimento')) $('ddFaturamentoVencimento').innerHTML = '';
        if ($('ddFaturamentoCiclo')) $('ddFaturamentoCiclo').innerHTML = '';
        if ($('ddFaturamentoUltFatura')) $('ddFaturamentoUltFatura').innerHTML = '';
        if ($('ddFaturamentoFormaPagto')) $('ddFaturamentoFormaPagto').innerHTML = '';

        /* Limpeza de informações - Dados de pré-pago */
        if ($('ddPrePagoCodigoSeguranca')) $('ddPrePagoCodigoSeguranca').innerHTML = "";
        if ($('ddPrePagoSaldoTotal')) $('ddPrePagoSaldoTotal').innerHTML = "";

		// Limpeza Campos Portabilidade
		$('ti_divPortabilidade').setStyle({
			'background': 'url(/FrontOfficeWeb/resources/images/ti_bg_portabilidade.gif)',
			'cursor': 'default'
		});
		$('ti_divPortabilidade').onmouseup = function(){return};
		$('ti_divPortabilidade').title = '';
		$('ti_divPortabilidadeContentIcon').update('');
		$('ti_divPortabilidadeContentTipo').update('');
		$('ti_divPortabilidadeContentDescricao').update('');

    } catch(e) {}
}

function carregarDados(oXml) {

    var sgTipoCarteira = oXml.selectSingleNode("/xmlDadosTI/cliente/carterizacaoVO/sgTipoCarteira")
        ? oXml.selectSingleNode("/xmlDadosTI/cliente/carterizacaoVO/sgTipoCarteira").text
        : '';

    if (oXml.selectSingleNode("/xmlDadosTI/@tipo").text != 'naoCliente'){

        inVerAbaRelacionamento = true;

        if ($('ddClienteNome')) {
            $('ddClienteNome').innerHTML     = $('ddClienteNome').title     = oXml.selectSingleNode("/xmlDadosTI/cliente/nome").text;

            // Caso o cliente possua uma das três carteiras, indica-se que este é responsável pelo pagamento.
            if (sgTipoCarteira == 'U1' || sgTipoCarteira == 'U2' || sgTipoCarteira == 'U3') {
                $('ddClienteNome').innerHTML = '<img src="/FrontOfficeWeb/resources/images/icon_pagamento.gif" align="absmiddle" title="Usuário Responsável pelo Pagamento" />&nbsp;'
                    + $('ddClienteNome').innerHTML;
            }

            // Caso o cliente possua a carteira abaixo (GCN – RESPONSAVEL CONTRATO), indica-se que este é responsável pelo contrato.
            if (sgTipoCarteira == 'U4') {
                $('ddClienteNome').innerHTML = '<img src="/FrontOfficeWeb/resources/images/icon_contrato.gif" align="absmiddle" title="Usuário Responsável pelo Contrato" />&nbsp;'
                    + $('ddClienteNome').innerHTML;
            }

            var dsTipoPessoa = oXml.selectSingleNode("/xmlDadosTI/cliente/inTipoPessoa").text;
            switch(dsTipoPessoa) {
                case "PESSOA FÍSICA":
                    inTipoPessoa = "PF";
                    break;
                case "PESSOA JURÍDICA":
                    inTipoPessoa = "PJ";
                    break;
            }
            $('ddClienteNatureza').innerHTML = $('ddClienteNatureza').title = oXml.selectSingleNode("/xmlDadosTI/cliente/inTipoPessoa").text;
            $('ddClienteChurn').innerHTML    = $('ddClienteChurn').title    = oXml.selectSingleNode("/xmlDadosTI/cliente/dsChurn").text;
            $('ddCarteirizacao').innerHTML   = $('ddCarteirizacao').title   = oXml.selectSingleNode("/xmlDadosTI/cliente/carterizacaoVO/descricao").text;
            $('ddSegmentacao').innerHTML     = $('ddSegmentacao').title     = oXml.selectSingleNode("/xmlDadosTI/cliente/segmentacaoVO/descricao").text;

            $('ddClienteTelefone').innerHTML      = oXml.selectSingleNode("/xmlDadosTI/cliente/nrTelefone").text;
            $('dtClienteTipoDocumento').innerHTML = oXml.selectSingleNode("/xmlDadosTI/cliente/documento/dsTipoDocumento").text;
            $('ddClienteDocumento').innerHTML     = oXml.selectSingleNode("/xmlDadosTI/cliente/documento/nrDocumento").text;

            if ($('dtClienteTipoDocumento').innerHTML == "CPF") {
                $('ddClienteDocumento').innerHTML = getMaskCPF($('ddClienteDocumento').innerHTML);
            } else if ($('dtClienteTipoDocumento').innerHTML == "CNPJ") {
                $('ddClienteDocumento').innerHTML = getMaskCNPJ($('ddClienteDocumento').innerHTML);
            }

            if($('ti_divEndereco')){
                if (oXml.selectSingleNode("/xmlDadosTI/cliente/endereco/dsEndereco").text != ""){
                    $('ddEndereco').innerHTML  = oXml.selectSingleNode("/xmlDadosTI/cliente/endereco/dsEndereco").text;
                    $('ddEndereco').innerHTML += ", ";
                    $('ddEndereco').innerHTML += oXml.selectSingleNode("/xmlDadosTI/cliente/endereco/nrEndereco").text;
                    $('ddEndereco').title      = $('ddEndereco').innerHTML;
                }

                $('ddBairro').innerHTML    = $('ddBairro').title = oXml.selectSingleNode("/xmlDadosTI/cliente/endereco/dsBairro").text;
                $('ddCidade').innerHTML    = $('ddCidade').title = oXml.selectSingleNode("/xmlDadosTI/cliente/endereco/dsCidade").text;
                $('ddEstado').innerHTML    = oXml.selectSingleNode("/xmlDadosTI/cliente/endereco/UFVO/nmUF").text;
                $('ddCEP').innerHTML       = oXml.selectSingleNode("/xmlDadosTI/cliente/endereco/nrCEP").text;
            }

            if ($('ti_divLinha')) {
                $('ddNrLinha').innerHTML      = oXml.selectSingleNode("/xmlDadosTI/linha/nrLinhaFormat").text;
                $('ddTipoLinha').innerHTML    = $('ddTipoLinha').title    = oXml.selectSingleNode("/xmlDadosTI/linha/dsTipoLinha").text;
                $('ddPlanoServico').innerHTML = $('ddPlanoServico').title = oXml.selectSingleNode("/xmlDadosTI/linha/dsPlanoServico").text;
                $('ddStatusLinha').innerHTML  = $('ddStatusLinha').title  = oXml.selectSingleNode("/xmlDadosTI/linha/dsEstadoLinha").text;
            }

            if ($('ti_divUsuario')) {
                $('ddUsuarioNome').innerHTML          = $('ddUsuarioNome').title    = oXml.selectSingleNode("/xmlDadosTI/linha/usuarioLinhaVO/nmUsuario").text;
                $('ddUsuarioCargo').innerHTML         = $('ddUsuarioCargo').title   = oXml.selectSingleNode("/xmlDadosTI/linha/usuarioLinhaVO/dsCargo").text;
                $('ddUsuarioContato').innerHTML       = $('ddUsuarioContato').title = oXml.selectSingleNode("/xmlDadosTI/linha/usuarioLinhaVO/nrContato").text;
                $('ddUsuarioTipoDocumento').innerHTML = oXml.selectSingleNode("/xmlDadosTI/linha/usuarioLinhaVO/dsTipoDocumento").text;
                $('ddUsuarioDocumento').innerHTML     = oXml.selectSingleNode("/xmlDadosTI/linha/usuarioLinhaVO/nrDocumento").text;

                if ($('ddUsuarioTipoDocumento').innerHTML == "CPF") {
                    $('ddUsuarioDocumento').innerHTML = getMaskCPF($('ddUsuarioDocumento').innerHTML);
                } else if ($('dtClienteTipoDocumento').innerHTML == "CNPJ") {
                    $('ddUsuarioDocumento').innerHTML = getMaskCNPJ($('ddUsuarioDocumento').innerHTML);
                }
            }

            if ($('ti_divGestor')) {
                var nmGestor = oXml.selectSingleNode("/xmlDadosTI/cliente/gestor/nmGestor").text;
                var nrTelGst = oXml.selectSingleNode("/xmlDadosTI/cliente/gestor/nrTelefone").text;
                if (inTipoPessoa == "PJ" && isClientePOS){
                    $('ti_divLupaGestor').style.cursor = "pointer";
                    $('ti_divLupaGestor').onclick = showLupaClienteGestor;
                    $('ti_divLupaGestor').show();
                }else{
                    $('ti_divLupaGestor').style.cursor = "default";
                    $('ti_divLupaGestor').onclick = null;
                    $('ti_divLupaGestor').hide();
                }
                $('ddGestorNome').innerHTML = nmGestor;
                $('ddGestorTelefone').innerHTML = nrTelGst;
            }

            if ($('ti_divQtdeLinhas')) {
                $('ddQtdeLinhas').innerHTML       = oXml.selectSingleNode("/xmlDadosTI/linha/qtdLinhasTotal").text;
                $('ddLinhasAtivas').innerHTML     = oXml.selectSingleNode("/xmlDadosTI/linha/qtdLinhasAtivas").text;
                $('ddLinhasCanceladas').innerHTML = oXml.selectSingleNode("/xmlDadosTI/linha/qtdLinhasCanceladas").text;
            }
        }

		isPortabilidade = (oXml.selectSingleNode("/xmlDadosTI/parametros/inPortabilidade").text == '1') ? true : false;
		if (isPortabilidade) {

			identificado = true;

            var tpPortada = oXml.selectSingleNode("/xmlDadosTI/parametros/tpPortada") ?
				oXml.selectSingleNode("/xmlDadosTI/parametros/tpPortada").text : '';
			var dsPortada = oXml.selectSingleNode("/xmlDadosTI/parametros/dsPortada") ?
				oXml.selectSingleNode("/xmlDadosTI/parametros/dsPortada").text : '';

			if (tpPortada != '') {

				var arrayFiles = new Array();
				arrayFiles[0] = 'ti_portabilidade_portin.gif';
				arrayFiles[1] = 'ti_portabilidade_portout.gif';
				arrayFiles[2] = 'ti_portabilidade_winback.gif';
				arrayFiles[3] = 'ti_portabilidade_fraud.gif';

				var indice, color;
				var tpVerification = tpPortada.gsub(' ','').toLowerCase();

				switch (tpVerification) {
					case 'portin':
						indice = 0;
						color = '#2fbb01';
						break;
					case 'portout':
						indice = 1;
						color = '#cc3333';
						break;
					case 'winback':
						indice = 2;
						color = '#2fbb01';
						break;
					case 'fraude':
						indice = 3;
						color = '#eaa000';
						break;
					default:
						indice = 0;
				}
				$('ti_divPortabilidade').setStyle({
					'background':'url(/FrontOfficeWeb/resources/images/ti_bg_portabilidade_in.gif)',
					'cursor':'pointer'
				});
				$('ti_divPortabilidade').onmouseup = function() {getHistoricoPortabilidade()};
				$('ti_divPortabilidade').title = 'Clique para visualizar o histórico de portabilidade.';
				$('ti_divPortabilidadeContentIcon').update('<img src="/FrontOfficeWeb/resources/images/' + arrayFiles[indice] + '" />');
				$('ti_divPortabilidadeContentTipo').update(tpPortada);
				$('ti_divPortabilidadeContentTipo').setStyle({'color': color});
				$('ti_divPortabilidadeContentDescricao').update(dsPortada);
			}
		} else {
			$('ti_divPortabilidade').setStyle({
				'background':'url(/FrontOfficeWeb/resources/images/ti_bg_portabilidade.gif)',
				'cursor':'default'
			});
			$('ti_divPortabilidade').onmouseup = function() {return};
		}

		// pesquisa Plataforma Unificada de Permissões
        new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/consultarLinhaPUP.do', {
            method: 'get',
            parameters: {inAjax: true},
            onComplete: function(r) {
                var xmlString = r.responseText;
                var dom = parseXml(xmlString);
                var jsonString = xml2json(dom, '');
                var jsonObj = jsonString.evalJSON();
                var msgPadrao = '';
                var msgRodape = 'Acesse os detalhes da linha para fazer o cadastro.';

                if (jsonObj.resultadoLinhaPUPTO.inMensagensPUP == 'true') {

				if (jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO) {

                    msgPadrao        = 'As permissões de contato abaixo não estão definidas:\n';

                    var msgParceiros = '-' + jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.dsSMSParceiros + '\n';
                    var msgProdutos  = '-' + jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.dsSMSProdutos + '\n';
                    var msgPromocoes = '-' + jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.dsSMSPromocoes + '\n';
                    var msgTelMkt    = '-' + jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.dsTelmktMsgVoz + '\n';

                    var parceiros = null;
                    var produtos = null;
                    var promocoes = null;
                    var telemarketing = null;

                    if (jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.inSMSParceiros) {
                        parceiros = jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.inSMSParceiros == 'true' ? true : false;
                    }
                    if (jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.inSMSProdutos) {
                        produtos = jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.inSMSProdutos == 'true' ? true : false;
                    }
                    if (jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.inSMSPromocoes) {
                        promocoes = jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.inSMSPromocoes == 'true' ? true : false;
                    }
                    if (jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.inTelmktMsgVoz) {
                        telemarketing = jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.inTelmktMsgVoz == 'true' ? true : false;
                    }

                    if (jsonObj.resultadoLinhaPUPTO.linhaPUPWSTO.linhaInexistentePUP == 'true') {
                    	msgPadrao = 'Verifique se o(a) cliente aceita receber em seu celular informações sobre promoções e novidades que a Vivo e seus parceiros podem oferecer.\n';
                        alert(msgPadrao + '\n' + msgRodape);
                    } else {
                    	var msg = '';
                        msg += (parceiros == null) ? msgParceiros  : '';
                        msg += (produtos  == null) ? msgProdutos   : '';
                        msg += (promocoes  == null) ? msgPromocoes  : '';
                        // msg += (telemarketing  == null) ? msgTelMkt   : '';

                        if (msg != '') {
                            alert(msgPadrao + msg + '\n' + msgRodape);
                            }
                        }
                    }
                } 
            }
        });

    } else {
        if ($('ddClienteNome')) {
            $('ddClienteNome').innerHTML = oXml.selectSingleNode("/xmlDadosTI/nome").text;
        }
    }

    // Blindagem de Clientes
    var isBlindagem = oXml.selectSingleNode("/xmlDadosTI/cliente/isBlindagem").text;
    if(isBlindagem=='1'){
        createNewPopUp('winBlindagem','Blindagem de Clientes', 500, 200, null, '/FrontOfficeWeb/cliente/TelaInicial/prosseguirBlindagem.do', true);
    }
    // Fim Blindagem de Clientes

    idProspect          = oXml.selectSingleNode("/xmlDadosTI/parametros/idProspect").text;

    if (oXml.selectSingleNode("/xmlDadosTI/parametros/inCorporativo").text == "1")
        inCorporativo = true;
    else
        inCorporativo = false;

    nrConta              = oXml.selectSingleNode("/xmlDadosTI/parametros/nrConta").text;
    inLegado             = oXml.selectSingleNode("/xmlDadosTI/parametros/inLegado").text;
    idTipoRelacionamento = oXml.selectSingleNode("/xmlDadosTI/parametros/tipoRelacionamento").text;
    if (inLegado == 1) {
        controlPrePagoFaturamentoDisplay();
    }
    /* Valores possiveis para a variável "idTipoLinha"
     * id Sigla Descrição
     * 1  POS   PÓS-PAGO
     * 2  PRÉ   PRÉ-PAGO
     * 3  NC    NÃO CLASSIFICADO
     * 4  HIB   Controle CDMA
     * 5  POSC  PÓS-PAGO CHIP
     * 6  PRÉC  PRÉ-PAGO CHIP
     * 7  CTRL  Controle GSM
     */

    //Tratamento para linhas pós-pagas
    if ( isClientePOS && !inPortada) {

        if ($('ddClienteNome')) {
            try {
                $('ddFaturamentoConta').innerHTML = "&nbsp;"+nrConta;
                $('spanAlertCobranca').innerHTML  = "";
                if (inLegado == 1) {
                    $('ti_divPrePagoFaturamento').style.display = "block";
                } else {
                    //desbilita lupa pre-pago
                    $('ti_divPrePagoFaturamento').style.display = "none";
                }
            } catch(e) {}
        }
    }
    //Tratamento para linhas pré-pagas
    else if (isClientePRE && !inPortada) {

        if ($('ddClienteNome')) {
            if (inLegado == 1) {
                $('ti_divPrePagoFaturamento').style.display = "block";
            } else {
                //Inibe dados de Faturamento/Pré-pago
                $('ti_divPrePagoFaturamento').style.display = "none";
            }
        }
    }
    flUpdateCombos = false;
    controlLupaClienteDisplay();
    controlLupasLinhaUsuarioDisplay();
    controlLupaCarterizacaoSegmentacaoDisplay();
	controlCombosDisplay();
    CarregaAba("bt01");
}

/* Função utilizada para controle de exibição dos dados de
 * positivação de usuário na Tela de Atendimento
 */
function controlCombosDisplay() {

    if ($('idPos')) {

        inProspect = false;
        $('idPos').disabled = false;
        $('tipoRelacionamento').disabled = false;

        switch (idTipoRelacionamento) {

            case '6':
                $('tipoRelacionamento').selectedIndex = 3;
                inProspect = true;
                $('idPos').disabled = true;
                $('tipoRelacionamento').disabled = true;
                break;
            case '7':
                $('idPos').selectedIndex = 1;
                $('idPos').disabled = true;
                $('tipoRelacionamento').selectedIndex = 3;
                $('tipoRelacionamento').disabled = true;
                idChamadaTelefonica = "";
                identificado = true;
                break;
        }
    }
}

/* Função utilizada para controle de exibição dos dados de
 * Faturamento de Pré-pago e acesso às respectivas lupas.
 */
function controlPrePagoFaturamentoDisplay() {

    var funcaoLupa, funcaoLoadDados, prePagoContent, faturamentoContent;

    faturamentoContent  = "<span id='spanFaturamentoPrePagoCaller'></span>";
    faturamentoContent += "<span id='spanAlertCobranca'></span>";
    faturamentoContent += "<dl>";
    faturamentoContent += "    <dt>Nº Conta:</dt>";
    faturamentoContent += "    <dd id='ddFaturamentoConta'></dd>";
    faturamentoContent += "    <dt style='clear:right;'>Vcto:</dt>";
    faturamentoContent += "    <dd id='ddFaturamentoVencimento'></dd>";
    faturamentoContent += "    <dt>Ciclo:</dt>";
    faturamentoContent += "    <dd id='ddFaturamentoCiclo'></dd>";
    faturamentoContent += "    <dt style='margin-right:5px;'>Valor Ult. Fatura:</dt>";
    faturamentoContent += "    <dd id='ddFaturamentoUltFatura'></dd>";
    faturamentoContent += "    <dt style='margin-right:28px;'>Forma pgto.:</dt>";
    faturamentoContent += "    <dd id='ddFaturamentoFormaPagto'></dd>";
    faturamentoContent += "</dl>";

    prePagoContent  = "<span id='spanFaturamentoPrePagoCaller'></span>";
    prePagoContent += "<dl>";
    prePagoContent += "    <dt>C&oacute;digo de Seguran&ccedil;a:</dt>";
    prePagoContent += "    <dd id='ddPrePagoCodigoSeguranca'></dd>";
    prePagoContent += "    <dt style='clear:right;'>Saldo Total:</dt>";
    prePagoContent += "    <dd id='ddPrePagoSaldoTotal'></dd>";
    prePagoContent += "</dl>";

    //Linhas pós-pagas
    if ( isClientePOS ) {

        funcaoLupa      = showLupaFaturamento;
        funcaoLoadDados = loadFaturamento;

        $('ti_divInfoPrePagoFaturamento').innerHTML = faturamentoContent;
        $('ti_divInfoPrePagoFaturamento').className = "ti_infoFaturamento";
        $('ti_divLupaPrePagoFaturamento').className = "ti_lupaFaturamento";

    }

    //Linhas pré-pagas
    else if ( isClientePRE ) {
        funcaoLupa      = showLupaPrePago;
        funcaoLoadDados = loadPrePago;
        $('ti_divInfoPrePagoFaturamento').innerHTML = prePagoContent;
        $('ti_divInfoPrePagoFaturamento').className = "ti_infoPrePago";
        $('ti_divLupaPrePagoFaturamento').className = "ti_lupaPrePago";
    }
    
    // Linhas básicas (Telefonia fixa)
    else {
    	$('ti_divInfoPrePagoFaturamento').innerHTML = '';
    }
    
    //Para Propect(6) ou Não Cliente(7)
    if (idTipoRelacionamento == 6 || idTipoRelacionamento == 7 || idProspect != "") {
        $('ti_divLupaPrePagoFaturamento').onclick = null;
        $('ti_divLupaPrePagoFaturamento').style.cursor = "default";
        $('spanFaturamentoPrePagoCaller').onclick = null;
        $('spanFaturamentoPrePagoCaller').style.cursor = "default";

    } else if (isClientePRE || isClientePOS) {
        $('ti_divLupaPrePagoFaturamento').onclick = funcaoLupa;
        $('ti_divLupaPrePagoFaturamento').style.cursor = 'pointer';
        $('spanFaturamentoPrePagoCaller').onclick = funcaoLoadDados;
        $('spanFaturamentoPrePagoCaller').style.cursor = 'pointer';
    }
}

/* Função utilizada para exibição dos dados de
 * pré-pago de uma determinada linha.
 */
function loadPrePago(){
    showTrail();
    new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/loadPrePago.do', {
        method: 'get',
        contentType: 'text/xml',
        onComplete: function(originalRequest) {
            var xmlString         = originalRequest.responseText;
            if (xmlString == "erro") {
                alert('Dados não recuperados pois sistema legado encontra-se temporariamente indisponível!');
                hideTrail();
            } else {
                try {
                    oXml       = new ActiveXObject("microsoft.xmldom");
                    oXml.async = false;
                    var regExp = new RegExp ('&', 'gi') ;
                    xmlString  = xmlString.replace(regExp,"&amp;");
                    if(!oXml.loadXML(xmlString)){
                        hideTrail();
                    }else{
                        $('ddPrePagoCodigoSeguranca').innerHTML = oXml.selectSingleNode("/xml-fragment/dtVencimento").text;
                        $('ddPrePagoSaldoTotal').innerHTML      = oXml.selectSingleNode("/xml-fragment/saldo").text;
                        hideTrail();
                    }
                }catch(e){hideTrail();}
            }
        }, onFailure: function(){hideTrail();
        }, onException: function(){hideTrail();}
    });
}

/* Função utilizada para exibição dos dados de
 * faturamento de uma determinada conta.
 */
function loadFaturamento() {

    showTrail();

    new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/loadFaturamento.do', {
        method: 'get',
        contentType: 'text/xml',
        onComplete: function(originalRequest) {

            var xmlString = originalRequest.responseText;

            if (xmlString == "erro") {
                alert('Sistema de FATURAMENTO temporariamente fora de serviço!');
                hideTrail();
            } else {

                oXml       = new ActiveXObject("microsoft.xmldom");
                oXml.async = false;
                var regExp = new RegExp ('&', 'gi') ;
                xmlString  = xmlString.replace(regExp,"&amp;");

                oXml.loadXML(xmlString);

                //Caso exista alguma cobrança pendente
                var inContaCobranca = oXml.selectSingleNode("/xml-fragment/inContaCobranca").text;
                if (inContaCobranca == "S") {
                    $('spanAlertCobranca').innerHTML = "$";
                }

                $('ddFaturamentoVencimento').innerHTML = oXml.selectSingleNode("/xml-fragment/dtVencimento").text;
                $('ddFaturamentoUltFatura').innerHTML  = oXml.selectSingleNode("/xml-fragment/saldo").text;
                $('ddFaturamentoFormaPagto').innerHTML = $('ddFaturamentoFormaPagto').title = oXml.selectSingleNode("/xml-fragment/dsFormaPagamento").text;
                $('ddFaturamentoCiclo').innerHTML      = $('ddFaturamentoCiclo').title      = oXml.selectSingleNode("/xml-fragment/dsCicloFatura").text;

                hideTrail();
            }
        }
    });

}

/* Função utilizada para controle de exibição dos dados
 * de Cliente e acesso à lupa Cliente.
 */
function controlLupaClienteDisplay() {

    if ($('ti_divCliente')) {
        $('ti_divLupaCliente').onclick = null;
        $('ti_divLupaCliente').style.cursor = "default";
        if (idTipoRelacionamento == 6 && !inPortada) {
            $('ti_divLupaCliente').style.cursor = "pointer";
            $('ti_divLupaCliente').onclick = showLupaUsuario;
        } else {
            if (idPessoaCliente != 26 && !inPortada) {
                $('ti_divLupaCliente').style.cursor = "pointer";
                $('ti_divLupaCliente').onclick = showLupaCliente;
            }
        }
    }
}

/* Função utilizada para controle de acesso às informações
 * presentes nas lupas Linha e Usu
 */
function controlLupasLinhaUsuarioDisplay() {

    if (idTipoRelacionamento==6 || idTipoRelacionamento==7 || idProspect != "") {
        if ($('ti_divLupaLinha')) {
            $('ti_divLupaLinha').onclick = null;
            $('ti_divLupaLinha').style.cursor = 'default';
        }
        if ($('ti_divLupaUsuario')) {
            $('ti_divLupaUsuario').onclick = null;
            $('ti_divLupaUsuario').style.cursor = 'default';
        }
    } else {
        if ($('ti_divLupaLinha')) {
            $('ti_divLupaLinha').onclick = showLupaLinha;
            $('ti_divLupaLinha').style.cursor = 'pointer';
        }
        if ($('ti_divLupaUsuario')) {
            $('ti_divLupaUsuario').onclick = showLupaUsuario;
            $('ti_divLupaUsuario').style.cursor = 'pointer';
        }
    }
}

function controlLupaCarterizacaoSegmentacaoDisplay(){

    if (idTipoRelacionamento == 6 || idTipoRelacionamento == 7 || idProspect != "") {

        if ($('ti_lupaCarteirizacao')) {
            $('ti_lupaCarteirizacao').onclick = null;
            $('ti_lupaCarteirizacao').style.cursor = "default";
        }
        if ($('ti_lupaSegmentacao')) {
            $('ti_lupaSegmentacao').onclick = null;
            $('ti_lupaSegmentacao').style.cursor = "default";
        }

    } else {

        if ($('ti_lupaCarteirizacao')) {
            $('ti_lupaCarteirizacao').onclick = showLupaCarteirizacao;
            $('ti_lupaCarteirizacao').style.cursor = "pointer";
        }
        if($('ti_lupaSegmentacao')){
            $('ti_lupaSegmentacao').onclick = showLupaSegmentacao;
            $('ti_lupaSegmentacao').style.cursor = "pointer";
        }
    }
}

/* Funções utilizadas para controle de exibição da tela de
 * visualização dos dados de Cliente, Usuário, Linha e Abas.
 */
function showDataScreen() {
    $('divContainer').show();
    $('divContentContainer').show();
    $('ti_divConteudoAbas').show();
    $('ti_divPrePagoFaturamento').show();
}
function hideDataScreen() { $('divContentContainer').hide(); }

function hideBarraHorizontal() {
	if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
	$('ti_barraHorizontal').hide();
	$('ti_divProtocolo').hide();
	$('ti_divSMS').hide();
	$('ti_divSenha').hide();
	$('ti_divPortabilidade').hide();
	$('ti_divSaltador').hide();
	$('ti_divSmiley').hide();
	$('ti_divTerminar').hide();
}

showBarraHorizontal = function() {
	if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
	$('ti_barraHorizontal').show();
	$('ti_divProtocolo').show();
	$('ti_divSMS').show();
	$('ti_divSenha').show();
	$('ti_divPortabilidade').show();
	$('ti_divSaltador').show();
	$('ti_divSmiley').show();
	$('ti_divTerminar').show();
}

function hideBarraPesquisa() {
    $('ti_divPesquisa').style.display = "none";
    $('ti_divGrupoLogin').style.display = "none";
}

function showBarraPesquisa() {
    $('ti_divPesquisa').style.display = "block";
    $('ti_divGrupoLogin').style.display = "block";
}

function mostraIdPos() {
    $('spanValidacaoClienteUsuario').style.display = 'block';
    $('spanStatusSenha').style.color = "#aa2c2c";
    $('spanClienteUsuario').style.display = "none";
    $('spanStatusSenha').innerHTML = "Senha n&atilde;o validada";
}

function preValidaKey(){
    if ($('ti_comboPesquisa').selectedIndex == 0) {
    	inClienteUsuario = '';
    }
    if (window.event.keyCode == 13) {
        window.event.keyCode = 0;
        if ($F('ti_comboPesquisa') == 'naoCliente') {
            $('ti_comboPesquisa').selectedIndex = 0;
        }
        isScreenPop = false;
        nrProtocoloScreenPop = '';
        inIdPosAutomatico = true;
        mostraIdPos();
        validaPesquisa();
    }
}

function abaOcultar(abas, abaDestion, estado) {
    var tdElement;
    var imgElementLeft;
    var imgElementRight;
    for( x = 1; x < abas.cells.length; x+=3 ) {
        tdElement       = abas.cells(x);
        imgElementLeft  = $("AbaLeft_"  + tdElement.id);
        imgElementRight = $("AbaRight_" + tdElement.id);
        try{
            if( tdElement.id == abaDestion.id) {
                if(estado){
                    abas.cells(x).style.display='none';
                    imgElementLeft.parentElement.style.display='none';
                    imgElementRight.parentElement.style.display='none';
                }else{
                    abas.cells(x).style.display='block';
                    imgElementLeft.parentElement.style.display='block';
                    imgElementRight.parentElement.style.display='block';
                }
                break;
            }
        }catch(e){
            break;
        }
    }
}

/* Função utilizada para destaque da aba correspondência devolvida
 * em caso de existência de item de correspondência pendente.
 */
function colorCorrespondencia(){

    if ($('bt07')) {
        if(inCorrespondencia)
            abaOcultar(btAba, bt07, false);
        $('bt07').onclick = null;

        //Diferente de Não Cliente
        if (idTipoRelacionamento != 7) {
            if (inCorrespondencia) {
                strInner = "<span id=\'titCDevolvida\' style=\'color=#aa2c2c;font-weight:bold;\'>C.Devolvida</span>"
            } else {
                strInner = "<span id=\'titCDevolvida\'>C.Devolvida</span>"
            }
            $('bt07').onclick = marcarCorrespondenciaDevolvida;
            $('bt07').innerHTML = strInner;
        }
    }
}

function marcarCorrespondenciaDevolvida() {

    if ($('bt07')) {
        abaSelected(btAba, bt07);
        if (inCorrespondencia) {
            $('bt07').innerHTML = 'Corresp. Devolvida';
            $('bt07').style.color = '#ffffff';
        } else {
            $('titCDevolvida').style.color = '#ffffff';
            $('bt07').onclick = marcarCorrespondenciaDevolvida;
        }
        CarregaAba(bt07.id);
    } else return false;
}

function guardaIds() {
    new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/refreshCombos.do', {
        method: 'get',
        parameters: {
            idRelacionamentoTroca: $F('tipoRelacionamento'), limit: 12,
            idPosTroca: $F('idPos'), limit: 12,
            idGrupoTroca: $F('grupoSel'), limit: 12
        }
    });
}

function verificaDoc() {

    var selectedDoc = $('ti_comboPesquisa');
    if ($('ti_botaoPesquisar')) {
		$('ti_botaoPesquisar').onclick = "mostraIdPos(); validaPesquisa();";
	}

    if  (selectedDoc.options[selectedDoc.selectedIndex].value == "naoCliente") {
        $('ti_inputValorPesquisa').disabled = true;
        if ($('ti_botaoPesquisar')) {
			$('ti_botaoPesquisar').src = "/FrontOfficeWeb/resources/images/bt_pesquisar_off.gif";
	        $('ti_botaoPesquisar').onclick = "";
	        $('ti_botaoPesquisar').style.cursor = "default";
		}
    } else {
        limparDados();
        hideDataScreen();
        $('ti_inputValorPesquisa').disabled = false;
        if ($('ti_botaoPesquisar')) {
			$('ti_botaoPesquisar').style.cursor = "pointer";
	        $('ti_botaoPesquisar').onclick = function(){ mostraIdPos(); validaPesquisa();};
	        $('ti_botaoPesquisar').src = "/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif";
		}
    }
	$('nrProtocolo').update(messages[4]);
	nrProtocoloScreenPop = '';
	setAlteracaoSMSSolicitacaoProtocolo('solicitacaoProtocolo');
    pesquisaNome();
}

function retornaBtPesquisar() {
	if ($('ti_botaoPesquisar')) {
	    $('ti_botaoPesquisar').style.cursor = "pointer";
	    $('ti_botaoPesquisar').onclick = function(){ inIdPosAutomatico=true;mostraIdPos();validaPesquisa();};
	    $('ti_botaoPesquisar').src = '/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';
    }
	$('ti_inputValorPesquisa').disabled = false;
    $('ti_inputValorPesquisa').focus();
}

document.onkeyup = function () {
    return null;
}

function showAbasClienteCDMA(){
    if($('bt01')) abaOcultar(btAba, bt01, false); //Aba Relacionamento
    if($('bt02')) abaOcultar(btAba, bt02, false); //Aba Atendimento
    if($('bt03')) abaOcultar(btAba, bt03, false); //Serviços
    if($('bt04')) abaOcultar(btAba, bt04, false); //Hist.Campanha
    if($('bt05')) abaOcultar(btAba, bt05, false); //Senha
    if($('bt06')) abaOcultar(btAba, bt06, false); //Retenção
    if($('bt07')) abaOcultar(btAba, bt07, false); //C.Devolvida
    if($('bt08')) abaOcultar(btAba, bt08, false); //Campanha
    if($('bt09')) abaOcultar(btAba, bt09, true);  //Chip
    if($('bt10')) abaOcultar(btAba, bt10, false);  //IMEI
    if($('bt11')) abaOcultar(btAba, bt11, false); //Aba Tracking
}

function showAbasClienteGSM(){
    if($('bt01')) abaOcultar(btAba, bt01, false); //Aba Relacionamento
    if($('bt02')) abaOcultar(btAba, bt02, false); //Aba Atendimento
    if($('bt03')) abaOcultar(btAba, bt03, false); //Serviços
    if($('bt04')) abaOcultar(btAba, bt04, false); //Hist.Campanha
    if($('bt05')) abaOcultar(btAba, bt05, false); //Senha
    if($('bt06')) abaOcultar(btAba, bt06, false); //Retenção
    if($('bt07')) abaOcultar(btAba, bt07, false); //C.Devolvida
    if($('bt08')) abaOcultar(btAba, bt08, false); //Campanha
    if($('bt09')) abaOcultar(btAba, bt09, false); //Chip
    if($('bt10')) abaOcultar(btAba, bt10, false); //IMEI
    if($('bt11')) abaOcultar(btAba, bt11, false); //Aba Tracking
}

function showAbasClientePORT(){
    if($('bt01')) abaOcultar(btAba, bt01, false); //Aba Relacionamento
    if($('bt02')) abaOcultar(btAba, bt02, false); //Aba Atendimento
    if($('bt03')) abaOcultar(btAba, bt03, true);  //Serviços
    if($('bt04')) abaOcultar(btAba, bt04, true);  //Hist.Campanha
    if($('bt05')) abaOcultar(btAba, bt05, true);  //Senha
    if($('bt06')) abaOcultar(btAba, bt06, true);  //Retenção
    if($('bt07')) abaOcultar(btAba, bt07, true);  //C.Devolvida
    if($('bt08')) abaOcultar(btAba, bt08, true);  //Campanha
    if($('bt09')) abaOcultar(btAba, bt09, true);  //Chip
    if($('bt10')) abaOcultar(btAba, bt10, true);  //IMEI
    if($('bt11')) abaOcultar(btAba, bt11, false); //Aba Tracking
}

alterarNumeroSMS = function() {
    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
    top.frameApp.createNewPopUp('alterarNumeroSMS', 'Alterar número de linha para envio de SMS', 400, 130, null, 'alterarNumeroSMS.do', true);
}

setAlteracaoSMSSolicitacaoProtocolo = function(arg) {
	var html;
	switch (arg) {
		case 'solicitacaoProtocolo':
			if (!inAcessoGerarProtocolo) return;
			html = '<img title="Solicitar Protocolo" width="25" height="27" src="/FrontOfficeWeb/resources/images/ti_bt_novoprotocolo.gif" onmouseup="solicitarProtocolo()" />';
			break;
		case 'alteracaoSMS':
			if (!inAcessoAltNumeroSMS) return;
			html = '<img title="Alterar número para envio de SMS" src="/FrontOfficeWeb/resources/images/ti_icon_sms.gif" width="28" height="26" onmouseup="alterarNumeroSMS()" />';
			break;
	}
	$('ti_divSMS').update(html);
}

solicitarProtocolo = function() {

	if (validacaoSolicitacaoProtocolo()) {

		new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/abrirProtocolo.do', {
			method: 'post',
			onSuccess: function(e) {
				if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

				var dom = parseXml(e.responseText);
				var jsonString = xml2json(dom, '');
				var jsonObj = jsonString.evalJSON();

				if (jsonObj.AbreProtocoloOutTO.cdError
					&& jsonObj.AbreProtocoloOutTO.cdError != 0) {
					alert(jsonObj.AbreProtocoloOutTO.msgError);
				} else {
					nrProtocolo = jsonObj.AbreProtocoloOutTO.nrProtocolo;
					nrProtocoloScreenPop = '';
					alert('Protocolo ' + nrProtocolo + ' gerado.');
					$('nrProtocolo').update(nrProtocolo);
					setAlteracaoSMSSolicitacaoProtocolo('alteracaoSMS');
				}
			}, onCreate: function() {
				if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
			}, onComplete: function() {
				if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
			}, onException: function(e) {
				alert(e.description);
			}, on406: function(e) {
				window.top.frameApp.createErrorPopUp('erroSolicitacaoProtocolo', e.statusText, e.responseText, false);
				if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
			}
		});
	}
}

validacaoSolicitacaoProtocolo = function() {
    if (!pesquisaEfetuada) {
		alert('É necessária a realização de pesquisa antes da solicitação de abertura de protocolo de atendimento.');
		return false;
	} else if ((!identificado && !inProspect) || ($F('ti_comboPesquisa') == 'naoCliente' && !inProspect)) {
		alert('Não é possível abrir protocolo de atendimento. Por favor, cadastre um prospect.');
		return false;
	}
	return true;
}

updateProtocolo = function(nrProtocoloArg) {
	nrProtocolo = nrProtocoloArg;
	$('nrProtocolo').update(nrProtocoloArg);
	setAlteracaoSMSSolicitacaoProtocolo('alteracaoSMS');
}

getHistoricoPortabilidade = function() {
    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
    top.frameApp.createNewPopUp('historicoPortabilidade','Histórico de Portabilidade',650,320,null,'/FrontOfficeWeb/cliente/TelaInicial/getHistoricoPortabilidade.do', true);
}