<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<% ParametrosVO parametros = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)); %>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="faturaOnlineForm"
             type="extworkflw.FaturaOnlineOptin32.FaturaOnlineOptin32Controller.FaturaOnlineForm" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript" language="javascript">
        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
        confirmar = function() {
            var action = '<%=Form.isManutencao()?"manutencaoFaturaOnline.do":"ativacaoFaturaOnline.do"%>';
            var indexes = $('divTermoAtivacao').select('[class="indexes"]');
            var dsEmail;
            var emailSelecionado = false;
            for (var i = 0; i < indexes.length; i++) {
                if (indexes[i].checked) {
                    emailSelecionado = true;
                    dsEmail = $('divTermoAtivacao').select('[class="emails"]')[i].value;
                    break;
                }
            }
            if (!emailSelecionado) {
                <% if ("PF".equals(parametros.getInTipoPessoa())) { %>
                alert('Favor selecionar um e-mail existente ou cadastrar um novo e-mail.');
                <% } else if ("PJ".equals(parametros.getInTipoPessoa())) { %>
                alert('É necessária a seleção de um e-mail para prosseguimento.');
                <% } %>
            } else {
                new Ajax.Request(action + '?dsEmail=' + dsEmail, {
                    method: 'post',
                    onSuccess: function(e) {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        var dom = parseXml(e.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();
                        if (jsonObj.xmlObject.nrProtocolo
                            && (top.frameApp.nrProtocolo != jsonObj.xmlObject.nrProtocolo)) {
                            alert('Protocolo ' + jsonObj.xmlObject.nrProtocolo + ' gerado.');
                            top.frameApp.updateProtocolo(jsonObj.xmlObject.nrProtocolo);
                            top.frameApp.nrProtocoloScreenPop = '';
                        }
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                        alert(jsonObj.xmlObject.msgRetorno);

						top.frameApp.CarregaAba('bt02');
						top.frameApp.flUp = true;
						top.frameApp.resizeFrameDetalhe();

                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }, on406: function(e) {
                        window.top.frameApp.createErrorPopUp('erroContaOnline', e.responseText, e.statusText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }
        cancelar = function() {
            $('aceitar').value = "true";
            $('operacao').value = "";
            $('formContaOnline').action = 'manageContaOnline.do';
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
            $('formContaOnline').submit();
        }
        incluirEmail = function() {
            top.frameApp.createNewPopUp('incluirEmailPF', 'Inclusão de E-mail', 500, 100, null, '/FrontOfficeWeb/extworkflw/FaturaOnlineOptin32/manageCadastroEmail.do', true, null);
        }
    </script>
    <style type="text/css">
        body {
            background:#ededed;
            margin:20px;
        }
        #divContaOnlineWrapper {
            padding:10px;
        }
        #body_divFaturamento {
            padding:10px;
            background:#ededed;
        }
        #spanStatus {
            color:#1969cc;
            margin:0;
            padding:0;
        }
        #divBotoes, #divTermoAtivacao {
            width:650px;
        }
        #divBotoes {
            text-align:right;
            margin-top:2px;
        }
        #divTermoAtivacao {
            border:1px solid #ccc;
            margin-top:10px;
            background:#fff;
            display:block;
            height:128px;
            overflow:hidden;
            padding:5px;
            text-indent:0;
        }
        #divTermoAtivacao h2 {
            font-weight:bold;
            font-size:11px;
            margin-bottom:3px;
            text-transform:uppercase;
            text-align:center;
        }
        form input {
            clear:both;
            background:none;
            border:none;
            margin:0;
            padding:0;
        }
    </style>
</head>

<% String tituloSessao = Form.isManutencao() ? "Manutenção de avisos" : "Ativação e desativação"; %>

<body id="bodyContaOnline">
<form id="formContaOnline" action="begin.do<%=Form.isManutencao()?"?operacao=manutencao":""%>" method="post" target="ifrmAbas">
    <html:hidden name="Form" property="aceitar" styleId="aceitar" value="true" />
    <html:hidden name="Form" property="operacao" styleId="operacao" />
    <vivo:sessao id="divContaOnline" width="680" height="240" label="Fatura Online" operacoes="<%=tituloSessao%>">
        <logic:equal name="Form" property="msgErro" value="">
            <div id="divContaOnlineWrapper">

                <logic:equal name="Form" property="manutencao" value="false">

                <vivo:quadro id="divFaturamento" width="650" height="40" label="Faturamento">
                    Conta Online:
                    <span id="spanStatus"><logic:match name="Form" property="faturaOnlineAtiva" value="false">DES</logic:match>ATIVADO</span>
                </vivo:quadro>

                </logic:equal>

                <div id="divTermoAtivacao"><br/>

                    <% if ("PF".equals(parametros.getInTipoPessoa())) { %>

                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="620" layoutHeight="83" tableWidth="620" styleId="tableTitle" sortable="false" resize="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="10%" type="string">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="45%" type="string">E-mail</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="45%" type="string">Tipo</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="iterEmails" name="Form" property="listaEmails.comunicacaoVOArray" indexId="c" type="br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO">
                                <vivo:tbRow key="rowsPermissoes">
                                    <vivo:tbRowColumn>
                                        <input type="radio" name="indexEmail" id="indexEmail" value="index_<%=c%>" class="indexes" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <input type="hidden" name="email_<%=c%>" id="email_<%=c%>" value="<bean:write name="iterEmails" property="dsContato" />" class="emails" />
                                        <bean:write name="iterEmails" property="dsContato" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="iterEmails" property="tipoComunicacaoVO.dsTipoComunicacao" />
                                    </vivo:tbRowColumn>
                                </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>

                    <% } else if ("PJ".equals(parametros.getInTipoPessoa())) { %>

                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="620" layoutHeight="83" tableWidth="620" styleId="tableTitle" sortable="false" resize="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="10%" type="string">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="90%" type="string">E-mail</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="iterEmails" name="Form" property="listaEmails.comunicacaoVOArray" indexId="c" type="br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO">
                                <vivo:tbRow key="rowsPermissoes">
                                    <vivo:tbRowColumn>
                                        <input type="radio" name="indexEmail" id="indexEmail" value="index_<%=c%>" class="indexes" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <input type="hidden" name="email_<%=c%>" id="email_<%=c%>" value="<bean:write name="iterEmails" property="dsContato" />" class="emails" />
                                        <bean:write name="iterEmails" property="dsContato" />
                                    </vivo:tbRowColumn>
                                </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>

                    <% } %>
                </div>
                <div id="divBotoes">
                    <img height="13" src="<%=request.getContextPath()%>/resources/images/bt_cancelar_nrml.gif" style="cursor:pointer" onmouseup="cancelar()" />
                    <% if ("PF".equals(parametros.getInTipoPessoa())) { %>
                    <img height="13" src="<%=request.getContextPath()%>/resources/images/bt_incluir_email_nrml.gif" style="cursor:pointer" onmouseup="incluirEmail()" />
                    <% } %>
                    <img height="13" src="<%=request.getContextPath()%>/resources/images/bt_confirmar_nrml.gif" style="cursor:pointer" onmouseup="confirmar()" />
                </div>
            </div>
        </logic:equal>
        <logic:notEqual name="Form" property="msgErro" value="">
            <div id="divMsgErro" style="height:100%;padding-top:120px;text-align:center;width:100%">
                <bean:write name="Form" property="msgErro" />
            </div>
        </logic:notEqual>
    </vivo:sessao>
</form>
</body>
</html>