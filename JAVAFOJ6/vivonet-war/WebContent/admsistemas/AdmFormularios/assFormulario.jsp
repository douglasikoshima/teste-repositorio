<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm" type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
        <script>
            <!--
                ativar_combos = function(){
                    ifrmArvore.src = 'about:blank';
                }

                arvoreContato = function() {
                    if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                    dvArvore.style.display = '';
                    $('assForm').method = "POST";
                    $('assForm').action = "obterArvoreContato.do";
                    $('assForm').target = "ifrmArvore";
                    $('assForm').submit();
                }

                function removerItem(destino){
                     $('listaPrazoAtendimentoSelecionado').selectedIndex = 0;
                     if(destino.selectedIndex>=0 && destino.options[destino.selectedIndex].selected){
                        var label = destino.options[destino.selectedIndex].text;
                        var value = destino.options[destino.selectedIndex].value;
                        addValueToSelectList(label, value, $('frmDisponiveis'));
                        destino.remove(destino.selectedIndex);
                    }
                }

                function moveAcima(destino){
                    if(destino.selectedIndex){
                        if(destino.selectedIndex>0){
                            var posicao = destino.selectedIndex -1;
                            if ($('listaContasSelecionadas').options.length > 0 && posicao == 0) {
                                alert('Formulário selecionado é exclusivo, não poderá tornar-se um formulário padrão.');
                            } else {
                                var id = destino.options[destino.selectedIndex-1].value;
                                var ds = destino.options[destino.selectedIndex-1].text;
                                destino.options[destino.selectedIndex-1].value = destino.options[destino.selectedIndex].value;
                                destino.options[destino.selectedIndex-1].text  = destino.options[destino.selectedIndex].text;
                                destino.options[destino.selectedIndex].value = id;
                                destino.options[destino.selectedIndex].text  = ds;
                                destino.selectedIndex--;
                            }
                        }
                    }
                }

                function moveAbaixo(destino){
                    if(destino){
                        if(destino.selectedIndex>-1 && destino.selectedIndex<(destino.options.length-1)){
                            var posicao = destino.selectedIndex;
                            if (posicao == 0) {
                                var idProximo = destino.options[destino.selectedIndex+1].value;
                                moverAbaixoFormulario(idProximo, destino);
                            } else {
                                var id = destino.options[destino.selectedIndex+1].value;
                                var ds = destino.options[destino.selectedIndex+1].text;
                                destino.options[destino.selectedIndex+1].value = destino.options[destino.selectedIndex].value;
                                destino.options[destino.selectedIndex+1].text  = destino.options[destino.selectedIndex].text;
                                destino.options[destino.selectedIndex].value = id;
                                destino.options[destino.selectedIndex].text  = ds;
                                destino.selectedIndex++;
                            }
                        }
                    }
                }

                transfereSelecaoLista = function(listaOrigem, listaDestino, inverse, flAll){
                    var i;
                    if (inverse) {
                        for(i = 0; i<listaDestino.options.length; i++) {
                            if (flAll) listaDestino.options[i].selected = true;
                                if(listaDestino.options[i].selected)
                                   listaOrigem.options[listaOrigem.options.length] = new Option(listaDestino.options[i].text, listaDestino.options[i].value);
                        }
                        for(i = listaDestino.options.length-1; i>=0; i--)
                            if(listaDestino.options[i].selected)
                                listaDestino.options[i] = null;
                    } else {
                        var limpar = false;
                        for(i = 0; i<listaOrigem.options.length; i++) {
                            if (flAll) listaOrigem.options[i].selected = true;
                            if(listaOrigem.options[i].selected)
                                if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                                    limpar = true;
                                } else {
                                     alert("Empresa " + listaOrigem.options[i].text + " já associada ao formulário.");
                                     limpar =false;
                                }


                        }
                        for(i = listaOrigem.options.length-1; i>=0; i--)
                            if(listaOrigem.options[i].selected && limpar)
                                listaOrigem.options[i] = null;

                    }
                }

                transfereAll = function(listaOrigem, listaDestino){
                    for(i = 0; i<listaOrigem.options.length; i++) {
                        listaOrigem.options[i].selected = true;
                        if(listaOrigem.options[i].selected)
                            if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                            }
                    }
                    clearSelectList(listaOrigem);
                }

                function getFuncionalidadeInfo(){
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    if ($('idFuncionalidade').selectedIndex > 0) {
                        limparAll();
                        getLoadDadosFuncionalidadeFrm();
                        getLoadFormsDisponiveis();
                        getLoadFormsAssociados();
                    } else {
                        limparAll();
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }

                }

                function getLoadFormsDisponiveis(){
                    if($F('idFuncionalidade')!=''){
                        clearSelectList($('frmDisponiveis'));
                        var params = $H();
                        params.set('idFuncionalidade', $F('idFuncionalidade'));
                        new Ajax.Request('loadFormsDisponiveis.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                if (e.responseText != '') {
                                    var dom = parseXml(e.responseText);
                                    var jsonString = xml2json(dom, '');
                                    var jsonObj = jsonString.evalJSON();
                                    if(jsonObj.Resultset && jsonObj.Resultset.linhas){
                                        var campos = jsonObj.Resultset.linhas.linha;
                                        if(campos.length){
                                            for(var i=0;i<campos.length;i++){
                                                var campo = campos[i].valor;
                                                addValueToSelectList(campo[1], campo[0], $('frmDisponiveis'));
                                            }
                                        }else{
                                            var campos = jsonObj.Resultset.linhas.linha.valor;
                                            addValueToSelectList(campos[1], campos[0], $('frmDisponiveis'));
                                        }
                                    }
                                }

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            }
                        });
                    }
                }

                function getLoadFormsAssociados(){
                    if($F('idFuncionalidade')!=''){
                        clearSelectList($('frmSelecionados'));
                        var params = $H();
                        params.set('idFuncionalidade', $F('idFuncionalidade'));
                        new Ajax.Request('loadFormsAssociados.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                if (e.responseText != '') {
                                    var dom = parseXml(e.responseText);
                                    var jsonString = xml2json(dom, '');
                                    var jsonObj = jsonString.evalJSON();
                                    if(jsonObj.Resultset && jsonObj.Resultset.linhas){
                                        var campos = jsonObj.Resultset.linhas.linha;
                                        if(campos.length){
                                            for(var i=0;i<campos.length;i++){
                                                var campo = campos[i].valor;
                                                addValueToSelectList(campo[1], campo[0],  $('frmSelecionados'));
                                            }
                                        }else{
                                            var campos = jsonObj.Resultset.linhas.linha.valor;
                                            addValueToSelectList(campos[1], campos[0],   $('frmSelecionados'));
                                        }
                                    }
                                }

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            }
                        });
                    }
                }

                function getLoadDadosFuncionalidadeFrm(){
                    if($F('idFuncionalidade')!=''){
                        $('idContato').value = '';
                        $('contato').value   = '';
                        var params = $H();
                        params.set('idFuncionalidade', $F('idFuncionalidade'));
                        new Ajax.Request('loadDadosFuncionalidadeFrm.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                if (e.responseText != '') {
                                    var dom = parseXml(e.responseText);
                                    var jsonString = xml2json(dom, '');
                                    var jsonObj = jsonString.evalJSON();
                                    if(jsonObj.Resultset && jsonObj.Resultset.linhas){
                                        var campos = jsonObj.Resultset.linhas.linha.valor;
                                        $('idContato').value = campos[0];
                                        $('contato').value   = campos[1];
                                    }
                                }

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            }
                        });
                    }
                }


                function buscaItemCombo(lista, valorItem){
                    for(i=0;i<lista.options.length;i++) {
                        if(lista.options[i].value==valorItem){
                            return true;
                        }
                    }
                    return false;
                }


                getPesquisa = function(tpOperacao) {
                    var bSubmit = true;
                    if (tpOperacao == 'pesquisarEmpresaVO') {
                        if (!validaCNPJ($F('nrCNPJ'))) {
                            alert('Digite um número de CNPJ válido.');
                            window.setTimeout(function(){$('nrCNPJ').focus();$('nrCNPJ').select()},200);
                            bSubmit = false;
                        }
                   } else if (tpOperacao == 'pesquisarClienteVOFuncionalidade') {
                        if ($('idFuncionalidade').selectedIndex < 0) {
                            alert('Necessita selecionar uma funcionalidade.');
                            window.setTimeout(function(){$('idFuncionalidade').focus();$('idFuncionalidade').select()},200);
                            bSubmit = false;
                        } else if ($('frmSelecionados').selectedIndex < 0) {
                            alert('Necessita selecionar um formulário.');
                            window.setTimeout(function(){$('idFuncionalidade').focus();$('frmSelecionados').select()},200);
                            bSubmit = false;
                        }

                        /* Carregar prazo atendimento do formulario selecionado */
                         loadPrazoAtendimento();
                        $('nrCNPJ').value = '';
                        clearSelectList($('listaContasDisponiveis'));
                        clearSelectList($('listaContasDessassociadas'));

                   }
                   if (bSubmit) {
                        var container = 'container_' + tpOperacao;
                        new Ajax.Updater({ success : container}, 'getPesquisa.do', {
                            method: 'post',
                            evalScripts: true,
                            parameters: {
                                'tpOperacao' : tpOperacao,
                                'idContato' :  $F('idContato'),
                                'idFuncionalidade' : $F('idFuncionalidade'),
                                'frmSelecionados' : $F('frmSelecionados'),
                                'nrCNPJ': $F('nrCNPJ').gsub('[^0-9]','')
                            },
                            onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            },
                            onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                            }, on406: function(e) {
                                window.top.frameApp.createErrorPopUp('erroConsultor', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            }
                        });
                  }
                }

                function loadSelecionarFormulario(tpOperacao){

                    /*
                    alert("tpOperacao " + tpOperacao);
                    alert("frmDisponiveis " + $('frmDisponiveis').selectedIndex );
                    alert("frmSelecionados " + $('frmSelecionados').selectedIndex );
                    */

                    var id;
                    var descricao;

                    if (tpOperacao == "adicionar" && $('frmDisponiveis').selectedIndex >= 0) {
                        id = $('frmDisponiveis').options[$('frmDisponiveis').selectedIndex].value;
                        descricao = $('frmDisponiveis').options[$('frmDisponiveis').selectedIndex].text;
                    } else if ($('frmSelecionados').selectedIndex >= 0){
                        id = $('frmSelecionados').options[$('frmSelecionados').selectedIndex].value;
                        descricao = $('frmSelecionados').options[$('frmSelecionados').selectedIndex].text;
                    }

                    if(id != null && descricao != null){
                        var params = $H();
                        params.set('idFormulario', id);
                        params.set('nmFormulario', descricao);
                        params.set('tpOperacao', tpOperacao);
                        new Ajax.Request('loadSelecionarFormulario.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                return true;

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                return false;
                            }
                        });
                    }

                }

                selecionarVariosFormularios = function () {

                     for(i = 0; i < $('frmDisponiveis').options.length; i++) {
                        $('frmDisponiveis').options[i].selected = true;
                        if($('frmDisponiveis').options[i].selected) {

                            id = $('frmDisponiveis').options[i].value;
                            desc = $('frmDisponiveis').options[i].text;

                            loadSelecionarVariosFormulario('adicionar', id, desc);
                        }
                    }
                }

                loadSelecionarVariosFormulario = function(tpOperacao, idFormulario, descricao){
                    /*
                    alert("tpOperacao " + tpOperacao);
                    alert("idFormulario " + idFormulario  + " " +  descricao);
                    */
                    if(id != null && descricao != null){
                        var params = $H();
                        params.set('idFormulario', idFormulario);
                        params.set('nmFormulario', descricao);
                        params.set('tpOperacao', tpOperacao);
                        new Ajax.Request('loadSelecionarFormulario.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                return true;

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                return false;
                            }
                        });
                    }

                }

                function loadSelecionarCliente(tpOperacao){

                    var nmEmpresa;
                    var nrCNPJ;
                    var idFormulario;

                    var idFormulario = $F('frmSelecionados');
                    
               
	                    if (tpOperacao == "adicionar" && $('listaContasDisponiveis').selectedIndex >= 0) {
	                        nrCNPJ = $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].value;
	                        nmEmpresa = $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].text;
	                    } else if ($('frmSelecionados').selectedIndex >= 0){
	                        nrCNPJ = $('listaContasSelecionadas').options[$('listaContasSelecionadas').selectedIndex].value;
	                        nmEmpresa = $('listaContasSelecionadas').options[$('listaContasSelecionadas').selectedIndex].text;
	                    }
	
	                    //alert("idFormulario = " + idFormulario + " nmEmpresa = " + nmEmpresa + " CNPJ= " + nrCNPJ);
	                    if(idFormulario != '' && nmEmpresa != '' && nrCNPJ != ''){
	                        var params = $H();
	                        params.set('tpOperacao', tpOperacao);
	                        params.set('idFormulario', idFormulario);
	                        params.set('nrCNPJ', nrCNPJ);
	                        params.set('nmEmpresa', nmEmpresa);
	                        new Ajax.Request('loadSelecionarCliente.do?' + params.toQueryString(), {
	                            method: 'post',
	                            onSuccess: function(e) {
	                                return true;
	
	                            }, onCreate: function() {
	                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
	
	                            }, onComplete: function() {
	                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
	
	                            }, onException: function(e) {
	                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
	                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
	                                return false;
	                            }
	                        });
	                    }
                }
                
                
                
                function loadSelecionarClienteDessassociado(tpOperacao){

                    var nmEmpresa;
                    var nrCNPJ;
                    var idFormulario;

                    var idFormulario = $F('frmSelecionados');

                    if (tpOperacao == "adicionar" && $('listaContasDisponiveis').selectedIndex >= 0) {
                        nrCNPJ = $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].value;
                        nmEmpresa = $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].text;
                    } else if ($('frmSelecionados').selectedIndex >= 0){
                        nrCNPJ = $('listaContasDessassociadas').options[$('listaContasDessassociadas').selectedIndex].value;
                        nmEmpresa = $('listaContasDessassociadas').options[$('listaContasDessassociadas').selectedIndex].text;
                    }

                    //alert("idFormulario = " + idFormulario + " nmEmpresa = " + nmEmpresa + " CNPJ= " + nrCNPJ);
                    if(idFormulario != '' && nmEmpresa != '' && nrCNPJ != ''){
                        var params = $H();
                        params.set('tpOperacao', tpOperacao);
                        params.set('idFormulario', idFormulario);
                        params.set('nrCNPJ', nrCNPJ);
                        params.set('nmEmpresa', nmEmpresa);
                        new Ajax.Request('loadSelecionarClienteDessassociado.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                return true;

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                return false;
                            }
                        });
                    }
                    
                }                
                
                


                 function loadSelecionarPrazo(){

                    var idFormulario = $F('frmSelecionados');

                    var idPrazo = $('listaPrazoAtendimentoSelecionado').options[$('listaPrazoAtendimentoSelecionado').selectedIndex].value;
                    var nmPrazo = $('listaPrazoAtendimentoSelecionado').options[$('listaPrazoAtendimentoSelecionado').selectedIndex].text;

                    //alert("idFormulario = " + idFormulario + " nmPrazo = " + nmPrazo + " idPrazo= " + idPrazo);

                    if (idFormulario  == null || idFormulario == ''){
                        alert('É necessário selecionar um formulário na lista de associados.');
                        $('listaPrazoAtendimentoSelecionado').selectedIndex= 0;
                    } else {

                        if(nmPrazo != null && idPrazo != null && idPrazo != ''){
                            var params = $H();
                            params.set('idFormulario', idFormulario);
                            params.set('idPrazoSelecionado', idPrazo);
                            params.set('nmPrazoSelecionado', nmPrazo);
                            new Ajax.Request('loadSelecionarPrazo.do?' + params.toQueryString(), {
                                method: 'post',
                                onSuccess: function(e) {
                                    return true;

                                }, onCreate: function() {
                                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                                }, onComplete: function() {
                                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                                }, onException: function(e) {
                                    window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                    return false;
                                }
                            });
                        }
                    }
                }

                function gravarAssociacao(){
                    var idContato = $F('idContato');
                    if(validaForm()){
                            var params = $H();
                            params.set('idContato', idContato);
                            new Ajax.Request('gravarFormularioFuncionalidade.do?' + params.toQueryString(), {
                                method: 'post',
                                onSuccess: function(transport) {
                                        var dom = parseXml(transport.responseText);
                                        var jsonString = xml2json(dom, '');
                                        var jsonObj = jsonString.evalJSON();
                                        if (jsonObj.msg.retorno == 'true') {
                                            var msgAlerta = jsonObj.msg.msErro;
                                            if (msgAlerta != null && msgAlerta != '') {
                                                alert(msgAlerta);
                                            }
                                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                         } else {
                                            alert('Ocorreu um problema durante gravação dos dados.');
                                         }

                                }, onCreate: function() {
                                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                                }, onComplete: function() {
                                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                                }, onException: function(e) {
                                    window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                    return false;
                                }
                            });
                        }
                }

                function validaForm(){
                    if($F('idFuncionalidade')==''){
                        alert('Favor escolher uma funcionalidade para associaçao.');
                        return false;
                    }

                    if($F('idContato')=='' ){
                        alert('Favor escolher um contato para associaçao através da Lupa.');
                        return false;
                    }
                    return true;
                }

                verificarFormularios = function () {

                    if ($('listaContasDisponiveis').selectedIndex >= 0) {

                        if ($('frmSelecionados').selectedIndex < 0) {
                            /* Não permitir que usuário adicione um cliente */
                            alert('É necessário selecionar um formulário na lista de associados.');
                            return false;
                        } else if (valueExistsInSelect($('listaContasDessassociadas'), $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].value)) {
                        	alert("Não é possível Associar o formulário. Existem Clientes Dessassociados");
                        	return false;  
                        } else if ($('frmSelecionados').options.length == 1) {
                                var empresa = $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].text;
                                return (confirm('Tem certeza de que deseja associar o formulário à empresa ' + empresa  + '?\nEle não estará mais disponível como formulário padrão de todos os clientes?'))
                        } else if ($('frmSelecionados').options.length > 1 && $('frmSelecionados').selectedIndex == 0) {
                            alert('O formulário selecionado é formulário padrão, não é permitido associá-lo ao cliente.\nSelecione o segundo da lista em diante, ou altere o formulário padrão.');
                            return false;
                        }
                        return true;
                    } else {
                        alert('Selecionar um cliente.');
                    }
                }
                
                
                verificarFormulariosDessassociado = function () {

                    if ($('listaContasDisponiveis').selectedIndex >= 0) {

                        if ($('frmSelecionados').selectedIndex < 0) {
                            /* Não permitir que usuário adicione um cliente */
                            alert('É necessário selecionar um formulário na lista de associados.');
                            return false;
                        } else if (valueExistsInSelect($('listaContasSelecionadas'), $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].value)) {
                          	alert("Não é possível Associar o formulário. Existem Clientes Associados");
                           	return false;                           
                        } else if ($('frmSelecionados').options.length == 1) {
                                var empresa = $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].text;
                                return (confirm('Tem certeza de que deseja associar o formulário à empresa ' + empresa  + '?\nEle não estará mais disponível como formulário padrão de todos os clientes?'))
                        } else if ($('frmSelecionados').options.length > 1 && $('frmSelecionados').selectedIndex == 0) {
                            alert('O formulário selecionado é formulário padrão, não é permitido associá-lo ao cliente.\nSelecione o segundo da lista em diante, ou altere o formulário padrão.');
                            return false;
                        }
                        return true;
                    } else {
                        alert('Selecionar um cliente.');
                    }
                }                


                function loadPrazoAtendimento(){
                     //alert("loadPrazoAtendimento frmSelecionados = " + $F('frmSelecionados'));
                     if ($('frmSelecionados').selectedIndex >= 0) {
                            var params = $H();
                            params.set('frmSelecionados', $F('frmSelecionados'));
                            new Ajax.Request('loadPrazoAtendimentoFormulario.do?' + params.toQueryString(), {
                                method: 'post',
                                onSuccess: function(transport) {
                                        var dom = parseXml(transport.responseText);
                                        var jsonString = xml2json(dom, '');
                                        var jsonObj = jsonString.evalJSON();
                                        if (jsonObj.msg.retorno == 'true') {
                                            var msgAlerta = jsonObj.msg.msErro;
                                            if (msgAlerta != null && msgAlerta != '') {
                                                selectListItemByValue(msgAlerta, $('listaPrazoAtendimentoSelecionado'));
                                            }
                                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                         } else {
                                            alert('Ocorreu um problema na pesquisa dos dados.');
                                         }

                                }, onCreate: function() {
                                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                                }, onComplete: function() {
                                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                                }, onException: function(e) {
                                    window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                    return false;
                                }
                            });
                        }
                }


                 function moverAbaixoFormulario(idFormulario, destino){

                    if(idFormulario != null){
                        var params = $H();
                        params.set('idFormulario', idFormulario);
                        new Ajax.Request('verificarFormularioExclusivo.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {

                                var id = destino.options[destino.selectedIndex+1].value;
                                var ds = destino.options[destino.selectedIndex+1].text;
                                destino.options[destino.selectedIndex+1].value = destino.options[destino.selectedIndex].value;
                                destino.options[destino.selectedIndex+1].text  = destino.options[destino.selectedIndex].text;
                                destino.options[destino.selectedIndex].value = id;
                                destino.options[destino.selectedIndex].text  = ds;
                                destino.selectedIndex++;

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, on406: function(e) {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                alert('Formulário ' + destino.options[destino.selectedIndex+1].text + ' não poderá tornar-se formulário padrão porque é um formulário exclusivo.')


                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                return false;
                            }
                        });
                    }

                }

                controlarSelecao = function(lista) {
                    for(i = 0; i<lista.options.length; i++) {
                         if (lista.options[i].selected == true) {
                              if (i != lista.selectedIndex) {
                                    lista.options[i].selected = false;
                              }
                         }
                    }
                    return true;

                }

                limparAll = function() {

                    clearSelectList($('frmDisponiveis'));
                    clearSelectList($('frmSelecionados'));
                    $('listaPrazoAtendimentoSelecionado').selectedIndex = 0;;
                    $('idContato').value = '';
                    $('contato').value = '';
                    $('nrCNPJ').value = '';
                    clearSelectList($('listaContasDisponiveis'));
                    clearSelectList($('listaContasSelecionadas'));
                    clearSelectList($('listaContasDessassociadas'));

                }

                onload = function(){
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    <logic:present name="msgRetorno" scope="request">
                        //alert('Operação realizada com sucesso!');
                    </logic:present>
                    clearSelectList($('frmSelecionados'));
                    clearSelectList($('frmDisponiveis'));
                }
            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="configurar" width="790" height="480" operacoes="Associar Formulário" scroll="no">
        <form method="post" action="" id="assForm" name="assForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;" target="_self">
        <input type="hidden" name="idContato" id="idContato"/>
        <table width="780" border="0" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
            <tr>
                <td colspan="4" height="20">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table>
                        <tr>
                            <td width="160"><b>Funcionalidades Disponíveis:</b></td>
                            <td>
                                <html:select name="Form" property="idFuncionalidade" styleId="idFuncionalidade" style="width:250px;" styleClass="SELECT" onchange="getFuncionalidadeInfo();">
                                    <option value="">-- Selecione --</option>
                                    <html:optionsCollection name="Form" property="lstFuncionalidade.itArray" value="id" label="ds"/>
                                </html:select>

                            </td>
                            <td width="200" align="right"><b>Peso/Prioridade Atendimento:</b></td>
                            <td>
                                <html:select name="Form" property="listaPrazoAtendimentoSelecionado" styleId="listaPrazoAtendimentoSelecionado" styleClass="SELECT" style="width:90px;" onchange="loadSelecionarPrazo();">
                                    <option value="0">--Selecione--</option>
                                    <logic:iterate id="it" name="Form" property="listaPrazoAtendimento.linhas.linhaArray">
                                        <option value="<bean:write name="it" property="valorArray[0]" />"><bean:write name="it" property="valorArray[1]" /></option>
                                    </logic:iterate>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table>
                        <tr>
                            <td width="160"><b>Contato:</b></td>
                            <td>
                                <input type="text" name="contato" id="contato" value="" size="40" style="width:250px;" readonly>
                                <img src="/FrontOfficeWeb/resources/images/lupa_bg_transp.gif" onclick="arvoreContato();" style="cursor:pointer;"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                </td>
            </tr>
            <tr>
                <td>Formulários disponíveis</td>
                <td></td>
                <td>Formulários associados</td>
                <td></td>
            </tr>
            <tr>
                <td width="310" valign="top">
                    <html:select name="Form" property="frmDisponiveis" styleId="frmDisponiveis" style="width:300px;" size="5" styleClass="SELECT" multiple="false" onchange="controlarSelecao($('frmDisponiveis'))">
                        <html:optionsCollection name="Form" property="lstFrmDisponiveis.itArray" value="id" label="ds"/>
                    </html:select>
                </td>
                <td width="60" align="center">
                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="loadSelecionarFormulario('adicionar'); transfereSelecaoLista($('frmDisponiveis'),$('frmSelecionados'),false,false);" style="cursor:pointer;"/>
                    <br/><br/>
                    <img src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" onclick="selecionarVariosFormularios();transfereAll($('frmDisponiveis'),$('frmSelecionados'));" style="cursor:pointer;"/>
                    <br/><br/>
                    <img src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" onclick="loadSelecionarFormulario('remover'); removerItem($('frmSelecionados'));  clearSelectList($('listaContasSelecionadas'));" style="cursor:pointer;"/>
                </td>
                <td width="310" valign="top">
                    <html:select name="Form" property="frmSelecionados" styleId="frmSelecionados" style="width:300px;" size="5" styleClass="SELECT" multiple="false" onchange="if (controlarSelecao($('frmSelecionados'))) {getPesquisa('pesquisarClienteVOFuncionalidade');}">
                    </html:select>
                </td>
                <td>
                    <img src="/FrontOfficeWeb/resources/images/bt_up_nrml.gif" onclick="moveAcima($('frmSelecionados'));" style="cursor:pointer;"/>
                    <br/><br/>
                    <img src="/FrontOfficeWeb/resources/images/bt_down_nrml.gif" onclick="moveAbaixo($('frmSelecionados'));" style="cursor:pointer;"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                     <div id="filtrosCliente" class="divFiltros">
                        <table width="5%">
                            <tr>
                                <td nowrap style="padding-right:20px;">CNPJ&nbsp;&nbsp;<html:text name="Form" property="clienteFormularioVO.clienteVO.nrCNPJ" styleId="nrCNPJ" onkeyup="checaCNPJ(this)" onblur="checaCNPJ(this)" maxlength="18" style="width:110px;" /></td>
                                <td align="right" width="100%">
                                    <a href="javascript:getPesquisa('pesquisarEmpresaVO')"><img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" /></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
             </tr>
             <tr>
                <td colspan="4">
                </td>
            </tr>

            <tr>
                <td>Clientes disponíveis</td>
                <td></td>
                <td>Clientes associados</td>
                <td></td>
            </tr>
            <tr>
                <td width="310" valign="top" id="container_pesquisarEmpresaVO" rowspan="2">
                    <html:select name="Form" property="listaContasDisponiveis" styleId="listaContasDisponiveis" multiple="true" styleClass="select2" size="10" onmouseover="ativarToolTip(this,1);" style="width:300px;">
                        <logic:iterate id="iterContas" name="Form" property="contasDisponiveis.IDValorClienteVOArray">
                            <option value="<bean:write name="iterContas" property="id" />"><bean:write name="iterContas" property="valor" />&nbsp;CNPJ:<bean:write name="iterContas" property="nrDocumentoFmt" /></option>
                        </logic:iterate>
                    </html:select>
                </td>
                <td width="60" valign="top">
                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="if (verificarFormularios()){ loadSelecionarCliente('adicionar'); transfereSelecaoLista($('listaContasDisponiveis'),$('listaContasSelecionadas'),false,false);}" style="cursor:pointer;"/>
                    <br/><br/>
                    <img src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" onclick="if ($('listaContasSelecionadas').selectedIndex >= 0){loadSelecionarCliente('remover'); transfereSelecaoLista($('listaContasDisponiveis'),$('listaContasSelecionadas'),true, false);}else{alert('Selecione um cliente.');}" style="cursor:pointer;"/>

		            <br><br><br>

                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="if (verificarFormulariosDessassociado()){ loadSelecionarClienteDessassociado('adicionar'); transfereSelecaoLista($('listaContasDisponiveis'),$('listaContasDessassociadas'),false,false);}" style="cursor:pointer;"/>
                    <br/><br/>
                    <img src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" onclick="if ($('listaContasDessassociadas').selectedIndex >= 0){loadSelecionarClienteDessassociado('remover'); transfereSelecaoLista($('listaContasDisponiveis'),$('listaContasDessassociadas'),true, false);}else{alert('Selecione um cliente.');}" style="cursor:pointer;"/>
                </td>
                <td id="container_pesquisarClienteVOFuncionalidade" width="220" valign="top" style="padding-left:10px;">
                    <html:select name="Form" property="listaContasSelecionadas" styleId="listaContasSelecionadas" multiple="true" styleClass="select2" size="4" style="width:300px;" onmouseover="ativarToolTip(this, 1);">
                        <logic:iterate id="iterContas" name="Form" property="arrClientes">
                            <option value="<bean:write name="iterContas" property="nrCNPJ" />"><bean:write name="iterContas" property="nome" /><logic:notEqual name="iterContas" property="nrDocumentoFormatado" value="">&nbsp;CNPJ:<bean:write name="iterContas" property="nrDocumentoFormatado" /></logic:notEqual></option>
                        </logic:iterate>
                    </html:select>
                    <br>Clientes desassociados
                    <html:select name="Form" property="listaContasDessassociadas" styleId="listaContasDessassociadas" multiple="true" styleClass="select2" size="4" style="width:300px;" onmouseover="ativarToolTip(this, 1);">
                        <logic:iterate id="iterContasDessassociados" name="Form" property="arrClientesDessassociados">
                            <option value="<bean:write name="iterContasDessassociados" property="nrCNPJ" />"><bean:write name="iterContasDessassociados" property="nome" /><logic:notEqual name="iterContasDessassociados" property="nrDocumentoFormatado" value="">&nbsp;CNPJ:<bean:write name="iterContasDessassociados" property="nrDocumentoFormatado" /></logic:notEqual></option>
                        </logic:iterate>                    
                    </html:select>                    
                    
                </td>
            </tr>
            <tr>
                <td colspan="4">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table width="100%" border="0" cellspacing="5" cellpadding="5">
                        <tr>
                            <td align="left">
                                <!--img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"-->
                            </td>
                            <td></td>
                            <td align="right">
                                <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onclick="gravarAssociacao();" style="cursor:pointer;"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
       </form>
        </vivo:sessao>
        <vivo:quadroFlutuante id="dvArvore" idIframe="ifrmArvore" width="720" height="300" spacesTop="90" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto" onclick="ativar_combos();"/>
    </netui-template:section>
</netui-template:template>