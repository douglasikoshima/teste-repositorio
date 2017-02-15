<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm" type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm"/>
<bean:define id="LstCamposForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaCamposFormulario" type="br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript">
            <!--
                var nCamposInclusos = 0;
                <logic:equal name="Form" property="action" value="A">
                nCamposInclusos = <bean:write name="Form" property="qtCamposFrm"/>;
                </logic:equal>

                function voltar(){
                    self.location.href = "/FrontOfficeWeb/admsistemas/AdmFormularios/loadManFormulario.do";
                }

                function showCamposExistentes(){
                    if($F('tpCampo')!=''){
                        clearSelectList($('idCampoExistente'));
                        getListaCamposExistentes();
                    }else{
                        clearSelectList($('idCampoExistente'));
                    }
                }

                function getListaCamposExistentes(){
                    if($F('tpCampo')!=''){
                        var params = $H();
                        params.set('tpCampo', $F('tpCampo'));
                        new Ajax.Request('loadCamposExistentes.do?' + params.toQueryString(), {
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
                                                addValueToSelectList(campo[1], campo[0], $('idCampoExistente'));
                                            }
                                        }else{
                                            var campos = jsonObj.Resultset.linhas.linha.valor;
                                            addValueToSelectList(campos[1], campos[0], $('idCampoExistente'));
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

                function adicionarItem(){
                    if($('idCampoExistente').selectedIndex>=0 && $F('tpCampo')!=''){
                        var valor = $('idCampoExistente').options[$('idCampoExistente').selectedIndex].text;
                        var params = $H();
                        params.set('idCampo', $F('idCampoExistente'));
                        params.set('nmCampo', valor);
                        params.set('tpCampo',$F('tpCampo'));

                        new Ajax.Updater('dataFormTable', 'loadCamposFormulario2Add.do', {
                            method: 'post',
                            parameters: params,
                            evalScripts: true,
                            onSuccess: function() {
                                $('idCampoExistente').options[$('idCampoExistente').selectedIndex] = null;
                                nCamposInclusos++;
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onFailure: function(e) {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                                top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                            }
                        });
                    }else{
                        alert('É necessário selecionar um campo para enviar para a tabela');
                    }
                }

                function incluirSubFormulario() {
                    var params = $H();
                    var valor = $('idSubForm').options[$('idSubForm').selectedIndex].value;

                    if($('idSubForm').selectedIndex>=0 && valor > 0){
                        params.set('idSubForm', valor);

                        new Ajax.Updater('dataFormTable', 'loadCamposSubFormulario2Add.do', {
                            method: 'post',
                            parameters: params,
                            evalScripts: true,
                            onSuccess: function() {
                                nCamposInclusos++;
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            }, onFailure: function(e) {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                                top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                            }
                        });
                    } else {
                        alert('Selecione um sub-formulário');
                    }

                    atualizarSubFormList();
                }

                function atualizarSubFormList() {
                    var params = $H();
                    var valor = $('idSubForm').options[$('idSubForm').selectedIndex].value;

                    params.set('idSubForm', valor);

                    new Ajax.Updater('subFormSelect', 'atualizaListaSubFormularios.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }, onFailure: function(e) {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                        }
                    });
                }

                function mudarObrigatoriedade(id) {
                    var params = $H();
                    params.set('idCampo', id);

                    new Ajax.Updater('dataFormTable', 'atualizarObrigatoriedade.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if(window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }, onFailure: function(e) {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                        }
                    });
                }

                function excluirCampo(id,idx){
                    if(idx!='' || idx!=undefined){
                        if(confirm('Deseja realmente excluir este campo?')){
                            var params = $H();
                            params.set('nPos', idx);
                            new Ajax.Updater('dataFormTable', 'removeItemFormulario.do', {
                                method: 'post',
                                parameters: params,
                                evalScripts: true,
                                onSuccess: function() {
                                    if(window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                    if(nCamposInclusos>0) nCamposInclusos--;
                                    atualizarSubFormList();
                                    alert('Operação realizada com sucesso!');
                                }, onFailure: function(e) {
                                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                    var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                                    top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                                }
                            });
                        }
                    }
                }

                function validaForm(){

                    <logic:notEqual name="Form" property="duplicarFormulario" value="true">
                        if($F('idClassificador')==''){
                            alert('É necessario selecionar um Classificador.');
                            return false;
                        }
                        if(nCamposInclusos==0){
                            alert('É necessario selecionar um campo para enviar para a tabela.');
                            return false;
                        }
                    </logic:notEqual>
                        if($F('nmFormulario')==''){
                            alert('É necessario selecionar um nome para o Formulário.');
                            return false;
                        }
                    return true;
                }

                function gravar(){

                    if(validaForm()){
                        <logic:equal name="Form" property="action" value="A">
                            $('cfgForm').action = 'alteraFormulario.do';
                        </logic:equal>
                        <logic:notEqual name="Form" property="action" value="A">
                            $('cfgForm').action = 'gravarFormulario.do';
                        </logic:notEqual>
                        <logic:equal name="Form" property="duplicarFormulario" value="true">
                            desabilitar(false);
                            $('cfgForm').action = 'gravarFormulario.do';
                        </logic:equal>
                        $('cfgForm').submit();
                    }
                }

                function desabilitar(status){
                    for(var i = 0; i < $('cfgForm').elements.length;i++) {
                        var objeto = $('cfgForm').elements[i];

                        if((objeto.type == 'text')||
                            (objeto.type == 'password')||
                            (objeto.type == 'select-one')||
                            (objeto.type == 'checkbox')){

                            objeto.disabled = status;
                        }
                    }
                    $('nmFormulario').disabled = false;


                }

                onload = function(){

                    <logic:equal name="Form" property="action" value="A">
                    $('nmFormulario').disabled = true;
                    </logic:equal>
                    <logic:present name="msgRetorno" scope="request">
                        alert('Operação realizada com sucesso!');
                    </logic:present>
                    <logic:present name="msgErro" scope="request">
                        alert('<bean:write name="msgErro" scope="request"/>');
                    </logic:present>

                    <logic:equal name="Form" property="duplicarFormulario" value="true">
                        desabilitar(true);
                    </logic:equal>

                }

                checkRegraAssociada = function() {
                    var regraAssociada = false;
                    var imgAssociarDesassociar = $('tableRegrasEncaminhamento_body').select("[class=botaoAssociarDesassociar]");
                    for (var i = 0; i < imgAssociarDesassociar.length; i++) {
                        if (imgAssociarDesassociar[i].readAttribute('title') == 'Excluir Associação') {
                            return true;
                            break;
                        }
                    }
                    return false;
                }

                associarDesassociarFormula = function(operacao, idFormula, nmFormula, idx) {

                    if (operacao == 'associacao' && checkRegraAssociada()) {

                        alert('É permitida a associação de apenas uma\nregra de encaminhamento por formulário.');

                    } else {

                        var verbo = (operacao == 'desassociacao') ? 'desassociar' : 'associar';
                        if (confirm('Deseja ' + verbo + ' a fórmula ' + nmFormula + ' ao formulário?')) {
                            new Ajax.Request('/FrontOfficeWeb/admsistemas/AdmFormularios/saveFormulaEncaminhamento.do', {
                                parameters: {
                                    'idFormula' : idFormula,
                                    'idFormulario' : '<bean:write name="Form" property="idFormulario" />',
                                    'operacao' : operacao,
                                    'nmFormula' : nmFormula
                                },
                                method: 'get',
                                onSuccess: function(e) {
                                    if (e.responseText != '') {
                                        var dom = parseXml(e.responseText);
                                        var jsonString = xml2json(dom, '');
                                        var jsonObj = jsonString.evalJSON();
                                        var imagem = (operacao == 'associacao') ? 'bt_icon_propried' : 'bt_icon_propried_inverso';

                                        var imgAssociarDesassociar = $('tableRegrasEncaminhamento_body').select("[class=botaoAssociarDesassociar]")[idx];
                                        imgAssociarDesassociar.src = '/FrontOfficeWeb/resources/images/' + imagem + '.gif';
                                        imgAssociarDesassociar.writeAttribute('title', operacao == 'desassociacao' ? 'Associar Fórmula' : 'Excluir Associação');
                                        imgAssociarDesassociar.onmouseup = function() {
                                            associarDesassociarFormula(operacao == 'desassociacao' ? 'associacao' : 'desassociacao', idFormula, nmFormula, idx);
                                        }
                                        alert(jsonObj.out.msgRetorno);
                                    }
                                }, onCreate: function() {
                                }, onComplete: function() {
                                }, onException: function(e) {
                                }, on406: function(e) {
                                }
                            });
                        }
                    }
                }

                alterarFormula = function(idFormula, nmFormula) {
                    if(self.$('dvAnimarAguarde'))self.startAnimation();
                    self.location.href = 'beginFormulaEncaminhamento.do?idFormulaEncaminhamento=' + idFormula + '&operacao=alteracao&idFormulario=<bean:write name="Form" property="idFormulario" />';
                }

                excluirFormula = function(idFormula, nmFormula, idx) {
                    if (confirm('Deseja excluir a fórmula?')) {
                        new Ajax.Request('/FrontOfficeWeb/admsistemas/AdmFormularios/saveFormulaEncaminhamento.do', {
                            parameters: {
                                'idFormula' : idFormula,
                                'operacao' : 'exclusao',
                                'idFormulario' : '<bean:write name="Form" property="idFormulario" />',
                                'nmFormula' : nmFormula
                            },
                            method: 'get',
                            onSuccess: function(e) {
                                if (e.responseText != '') {

                                    var dom = parseXml(e.responseText);
                                    var jsonString = xml2json(dom, '');
                                    var jsonObj = jsonString.evalJSON();

                                    if (jsonObj.out.permiteExclusao && jsonObj.out.permiteExclusao == 'false') {
                                        var qtdeAssociacoes = parseInt(jsonObj.out.qtdeAssociacoes);
                                        var s = (qtdeAssociacoes > 1) ? 's' : '';
                                        var pluralAssociacao = (qtdeAssociacoes > 1) ? 'ões' : 'ão';
                                        var msgAlert  = 'Operação não permitida.\n\nA fórmula solicitada está associada a ' + qtdeAssociacoes + ' formulário' + s + '.\n';
                                            msgAlert += 'Por favor, remova a' + s + ' associaç' + pluralAssociacao + ' e tente novamente.'
                                        alert(msgAlert);
                                    } else {
                                        alert(jsonObj.out.msgRetorno);
                                        removerLinha($('tableRegrasEncaminhamento_body').select("[class=rowTabela]")[idx]);
                                    }
                                    if(self.$('dvAnimarAguarde'))self.stopAnimation();
                                }
                            }, onCreate: function() {
                                if(self.$('dvAnimarAguarde'))self.startAnimation();
                            }, onComplete: function() {
                                if(self.$('dvAnimarAguarde'))self.stopAnimation();
                            }, onException: function(e) {
                            }, on406: function(e) {
                            }
                        });
                    }
                }

            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="configurar" width="790" height="480" operacoes="Configuração de Formulários" scroll="no">
        <form method="post" action="" id="cfgForm" name="cfgForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;">
        <html:hidden name="Form" property="idFormulario" styleId="idFormulario"/>
        <input type="hidden" name="isAlterar" id="isAlterar" value="<bean:write name="Form" property="action" />">
        <html:hidden name="Form" property="duplicarFormulario" styleId="duplicarFormulario"/>
        <table width="780" border="0" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
            <tr>
                <td colspan="3">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <table>
                        <tr>
                            <td width="100"><b>Classificador:</b></td>
                            <td>
                                <html:select name="Form" property="idClassificador" styleId="idClassificador" style="width:250px;" styleClass="SELECT">
                                    <option value="">-- Selecione --</option>
                                    <html:optionsCollection name="Form" property="lstClassificacaoFrm.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                            <td width="120"><b>Nome do Formulário:</b></td>
                            <td>
                                <html:text name="Form" property="nmFormulario" styleId="nmFormulario" size="50" maxlength="50"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <table>
                        <tr>
                            <td width="100"><b>Tipo de campo:</b></td>
                            <td>
                                <select name="tpCampo" id="tpCampo" style="width:250px;" onchange="showCamposExistentes();">
                                    <option value="">-- Selecione --</option>
                                    <option value="T">Texto</option>
                                    <option value="S">Combo</option>
                                    <option value="SM">Lista seleção múltipla</option>
                                    <option value="LP">Lista preenchida</option>
                                    <option value="L">Label</option>
                                    <option value="M">Memo</option>
                                </select>
                            </td>
                            <td width="120"><b>Sub-formulário:</b></td>
                            <td>
                                <div id="subFormSelect">
                                <html:select name="Form" property="idSubForm" style="width:215px;" styleClass="SELECT">
                                <option value="">-- Selecione --</option>
                                <html:optionsCollection name="Form" property="lstSubFrm.itArray" value="id" label="ds"/>
                                </html:select>
                                </div>
                            </td>
                             <logic:notEqual name="Form" property="duplicarFormulario" value="true">
                                <td><img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" align="middle" onclick="incluirSubFormulario()" style="cursor:pointer;"></td>
                             </logic:notEqual>
                             <logic:equal name="Form" property="duplicarFormulario" value="true">
                                <td><img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" align="middle" style="cursor:pointer;"></td>
                             </logic:equal>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                </td>
            </tr>
            <tr>
                <td colspan="3">Campos existentes</td>
            </tr>
            <tr>
                <td width="255" valign="top">
                    <select name="idCampoExistente" id="idCampoExistente" style="width:250px;" size="10">
                    </select>
                </td>
                <td width="52">
                <logic:notEqual name="Form" property="duplicarFormulario" value="true">
                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="adicionarItem()" style="cursor:pointer;"/>
                </logic:notEqual>
                <logic:equal name="Form" property="duplicarFormulario" value="true">
                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="" style="cursor:pointer;"/>
                </logic:equal>
                </td>
                <td valign="top">
                    <div id="dataFormTable" style="with:465;height:100px;margin-top:5px;margin-left:5px;border:1px solid #D4D7DE;">
                        <vivo:tbTable selectable="true" layoutWidth="450" layoutHeight="100" tableWidth="450" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="15%" type="string">Obrigatório</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="35%" type="string">Tipo</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="40%" type="string">Item</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="it" name="LstCamposForm" property="linhas.linhaArray" indexId="idx">
                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn><input type="checkbox" id="<bean:write name="it" property="valorArray[0]"/>" class="radio" onclick="mudarObrigatoriedade('<bean:write name="it" property="valorArray[0]"/>')" <bean:write name="it" property="valorArray[4]"/>/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="it" property="valorArray[6]"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="it" property="valorArray[2]"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><logic:notEqual name="Form" property="duplicarFormulario" value="true"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluirCampo('<bean:write name="it" property="valorArray[0]"/>','<%=idx%>')" style="cursor:pointer;"></logic:notEqual></vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </div>
                </td>
            </tr>

            <tr>
                <td colspan="3">
                    <vivo:sessao id="regraEncaminhamento" width="770" height="155" operacoes="Regra de encaminhamento" scroll="no">
                        <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="80" tableWidth="743" styleId="tableRegrasEncaminhamento" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="85%" type="string">Nome da fórmula</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="it" name="Form" property="regrasEncaminhamento" indexId="idx" type="br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoDisponivelVODocument.RegrasEncaminhamentoDisponivelVO">
                                    <vivo:tbRow key="linha">
                                        <vivo:tbRowColumn><bean:write name="it" property="nmRegraEncaminhamento" /></vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <logic:notEqual name="Form" property="duplicarFormulario" value="true">
                                                <logic:notEqual name="Form" property="idFormulario" value="">
                                                    <%
                                                    String imagem = it.getInAssociada() ? "bt_icon_propried" : "bt_icon_propried_inverso";
                                                    String operacao = it.getInAssociada() ? "desassociacao" : "associacao";
                                                    %>
                                                    <img src="/FrontOfficeWeb/resources/images/<%=imagem%>.gif"
                                                         onmouseup="associarDesassociarFormula('<%=operacao%>','<bean:write name="it" property="idRegraEncaminhamento" />', '<bean:write name="it" property="nmRegraEncaminhamento" />', <%=idx%>)"
                                                         title="<%=it.getInAssociada() ? "Excluir Associação" : "Associar Fórmula"%>"
                                                         class="botaoAssociarDesassociar"
                                                         style="cursor:pointer;">
                                                </logic:notEqual>
                                            </logic:notEqual>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn><logic:notEqual name="Form" property="duplicarFormulario" value="true"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterarFormula('<bean:write name="it" property="idRegraEncaminhamento" />', '<bean:write name="it" property="nmRegraEncaminhamento" />')" style="cursor:pointer;"></logic:notEqual></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><logic:notEqual name="Form" property="duplicarFormulario" value="true"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluirFormula('<bean:write name="it" property="idRegraEncaminhamento" />', '<bean:write name="it" property="nmRegraEncaminhamento" />', '<%=idx%>')" style="cursor:pointer;"></logic:notEqual></vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                        <table width="100%" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                            <tr>
                                <td valign="middle" width="90"><logic:notEqual name="Form" property="duplicarFormulario" value="true"><b>&nbsp;Legendas:</b></logic:notEqual></td>
                                <td><logic:notEqual name="Form" property="duplicarFormulario" value="true"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" align="absmiddle"> &nbsp;Associar/Desassociar Fórmula</logic:notEqual></td>
                                <td><logic:notEqual name="Form" property="duplicarFormulario" value="true"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Fórmula</logic:notEqual></td>
                                <td><logic:notEqual name="Form" property="duplicarFormulario" value="true"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Excluir Fórmula</logic:notEqual></td>
                                <td align="right">
                                    <logic:notEqual name="Form" property="duplicarFormulario" value="true">
                                        <a onclick="if(self.$('dvAnimarAguarde'))self.startAnimation();" href="/FrontOfficeWeb/admsistemas/AdmFormularios/beginFormulaEncaminhamento.do?idFormulario=<bean:write name="Form" property="idFormulario" />&operacao=inclusao"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_criarnovo_nrml.gif" align="absmiddle"></a>
                                    </logic:notEqual>
                                    <logic:equal name="Form" property="duplicarFormulario" value="true">
                                        <a onclick="">&nbsp;</a>
                                    </logic:equal>
                                </td>
                            </tr>
                        </table>

                    </vivo:sessao>
                </td>
            </tr>

            <tr>
                <td colspan="3">
                    <table width="100%" border="0" cellspacing="5" cellpadding="5">
                        <tr>
                            <td align="left">
                                <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" align="middle" onclick="voltar();" style="cursor:pointer;">
                            </td>
                            <td align="right">
                                <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" align="middle" onclick="gravar();" style="cursor:pointer;">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        </form>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>