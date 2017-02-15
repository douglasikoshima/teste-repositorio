<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="faturaOnlineForm"
             type="extworkflw.FaturaOnlineOptin32.FaturaOnlineOptin32Controller.FaturaOnlineForm" />

<%
ParametrosVO parametros = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
String tituloSessao = Form.isManutencao() ? "Manutenção de avisos" : "Ativação e desativação";
%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript" language="javascript">

        inContaOnlineAtiva = <bean:write name="Form" property="faturaOnlineAtiva" />;
        var inAlteracaoCheckboxes = false;
        var isManutencao = <%=Form.isManutencao()?"true":"false"%>;

        var html  = '<div id="divMsgErro" style="height:100%;padding-top:100px;text-align:center;width:100%">';
            html += '   Será necessário <img align="top" src="<%=request.getContextPath()%>/resources/images/bt_reiniciar_nrml.gif" style="cursor:pointer" onclick="window.top.frameApp.ti_frameAbas.ifrmAbas.location.href=\'/FrontOfficeWeb/extworkflw/FaturaOnlineOptin32/begin.do<%=Form.isManutencao()?"?operacao=manutencao":""%>\'" /> a funcionalidade <strong><%=tituloSessao%> de Conta Online</strong>.';
            html += '</div>';

        onload = function() {
            <logic:equal name="Form" property="clienteDadosZap" value="true">
            $('avisoFaturaDisponivelPorEmail').checked = true;
            $('servicoEnvioCodigoBarrasPorSMS').disabled = true;
            </logic:equal>
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
        setNaoAtivarServicos = function(obj) {
            if (obj.checked) {
                $('avisoFaturaDisponivelPorEmail').checked = false;
                $('servicoEnvioFaturaPorEmail').checked = false;
                $('servicoEnvioCodigoBarrasPorSMS').checked = false;
            }
        }
        setServico = function(obj) {
            <logic:equal name="Form" property="clienteDadosZap" value="true">
            if (obj.id == 'avisoFaturaDisponivelPorEmail') {
                obj.checked = true;
            }
            </logic:equal>
            <logic:equal name="Form" property="clienteDadosZap" value="false">
                <logic:equal name="Form" property="manutencao" value="false">
                    if ($('avisoFaturaDisponivelPorEmail').checked
                            || $('servicoEnvioFaturaPorEmail').checked
                            || $('servicoEnvioCodigoBarrasPorSMS').checked) {
                        $('radioDesativar').checked = false;
                    }
                </logic:equal>
            </logic:equal>
        }

        verificaEmail = function(obj) {
            <% if ("PJ".equals(parametros.getInTipoPessoa())) { %>
            if (obj.checked) {
                if ($F('gestorEmail') == 'false') {
                    if (confirm('Operação não pode ser efetivada. O Gestor de Contas não possui E-mail cadastrado. Deseja cadastrá-lo agora?')) {
                        $('divContaOnlineWrapper').update(html);
                        top.frameApp.showPopupTI('Detalhes do cliente',790,530,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/begin.do?aba=Gestor&origem=ContaOnline");
                    } else {
                        obj.checked = false;
                        if (isManutencao) {
                            $('avisoFaturaDisponivelPorEmail').checked = false;
                            $('servicoEnvioFaturaPorEmail').checked = false;
                            return false;
                        }
                    }
                } else {
                    return true;
                }
            }
            <% } %>
        }

        verificaSMS = function(obj) {
            <% if ("PJ".equals(parametros.getInTipoPessoa())) { %>
            if (obj.checked) {
                if ($F('gestorTelefone') == 'true') {
                    if (confirm('O Torpedo SMS será enviado para o número <bean:write name="Form" property="nrTelefoneGestor" />. Deseja confirmar?\n\nCertifique-se que o número cadastrado é um número VIVO, pois avisos serão enviados apenas para números VIVO.')) {
                        obj.checked = true;
                        return true;
                    } else {
                        if (confirm('Deseja alterar o celular do Gestor de Contas?')) {
                            $('divContaOnlineWrapper').update(html);
                            top.frameApp.showPopupTI('Detalhes do cliente',790,530,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/begin.do?aba=Gestor&origem=ContaOnline");
                        } else {
                            obj.checked = false;
                            return false;
                        }
                    }
                } else {
                    if (confirm('Operação não pode ser efetivada. O Gestor de Contas não possui Telefone cadastrado. Deseja cadastrá-lo agora?')) {
                        $('divContaOnlineWrapper').update(html);
                        top.frameApp.showPopupTI('Detalhes do cliente',790,530,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/begin.do?aba=Gestor&origem=ContaOnline");
                    } else {
                        obj.checked = false;
                        return false;
                    }
                }
            }
            <% } %>
        }

        submitServicos = function(inAceitar) {

            <% if ("PJ".equals(parametros.getInTipoPessoa()) && Form.isManutencao()) { %>

            if (!inAlteracaoCheckboxes) {
                if ($('servicoEnvioCodigoBarrasPorSMS').checked) {
                    if (!verificaSMS($('servicoEnvioCodigoBarrasPorSMS'))) {
                        return false;
                    }
                }
                if ($('avisoFaturaDisponivelPorEmail').checked
                        || $('servicoEnvioFaturaPorEmail').checked) {
                    if (!verificaEmail($('avisoFaturaDisponivelPorEmail'))) {
                        return false;
                    }
                }
            }
            <% } %>

            if (inAceitar) {
                $('aceitar').value = 'true';
            } else {
                $('aceitar').value = 'false';
            }

            if (inAceitar && ((!$('avisoFaturaDisponivelPorEmail').checked
                    && !$('servicoEnvioFaturaPorEmail').checked
                    && !$('servicoEnvioCodigoBarrasPorSMS').checked) && $('radioDesativar') && !$('radioDesativar').checked)) {
                alert("Favor selecionar algum serviço ou selecionar a opção 'Nenhuma das opções acima'.");
            } else {

                if (($('radioDesativar') && $('radioDesativar').checked)
                        || (!$('avisoFaturaDisponivelPorEmail').checked && !$('servicoEnvioFaturaPorEmail').checked)
                        || !inAceitar) {

					if (!inAceitar) {
						$('avisoFaturaDisponivelPorEmail').checked = false;
						$('servicoEnvioFaturaPorEmail').checked = false;
					}

                    new Ajax.Request('submitServicos.do', {
                        method: 'post',
                        parameters: $('formContaOnline').serialize(),
                        onSuccess: function(e) {

							var dom = parseXml(e.responseText);
                            var jsonString = xml2json(dom, '');
                            var jsonObj = jsonString.evalJSON();

                            if (inAceitar) {

                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
								var msgProtocolo = "";
                                if (jsonObj.xmlObject.nrProtocolo
                                    && (top.frameApp.nrProtocolo != jsonObj.xmlObject.nrProtocolo)) {
                                    msgProtocolo = 'Protocolo ' + jsonObj.xmlObject.nrProtocolo + ' gerado.';
                                    top.frameApp.updateProtocolo(jsonObj.xmlObject.nrProtocolo);
                                    top.frameApp.nrProtocoloScreenPop = '';
                                    alert(msgProtocolo);
                                }
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                                alert(jsonObj.xmlObject.msgRetorno);

                                top.frameApp.CarregaAba('bt02');
                                top.frameApp.flUp = true;
                                top.frameApp.resizeFrameDetalhe();

                            } else {

								if (jsonObj.xmlObject.nrProtocolo
                                    && (top.frameApp.nrProtocolo != jsonObj.xmlObject.nrProtocolo)) {
                                    alert('Protocolo ' + jsonObj.xmlObject.nrProtocolo + ' gerado.');
                                    top.frameApp.updateProtocolo(jsonObj.xmlObject.nrProtocolo);
                                    top.frameApp.nrProtocoloScreenPop = '';
                                }
								if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                                top.frameApp.CarregaAba('bt02');
                                top.frameApp.flUp = true;
                                top.frameApp.resizeFrameDetalhe();
                            }

                        }, onCreate: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                        }, on406: function(e) {
                            window.top.frameApp.createErrorPopUp('erroContaOnline', e.responseText, e.statusText, false);
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }
                    });

                } else {

                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                    $('formContaOnline').action = 'submitServicos.do';
                    $('formContaOnline').submit();

                }
            }

        }
        cancelaManutencao = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            top.frameApp.CarregaAba('bt02');
            top.frameApp.flUp = true;
            top.frameApp.resizeFrameDetalhe();
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
<body id="bodyContaOnline">
<form id="formContaOnline" action="begin.do<%=Form.isManutencao()?"?operacao=manutencao":""%>" method="post" target="ifrmAbas">

    <html:hidden name="Form" property="operacao" value="getEmails" />
    <html:hidden name="Form" property="possuiGestor" styleId="possuiGestor" />
    <html:hidden name="Form" property="gestorEmail" styleId="gestorEmail" />
    <html:hidden name="Form" property="gestorTelefone" styleId="gestorTelefone" />
    <html:hidden name="Form" property="msgErroDetalhada" styleId="msgErroDetalhada" />
    <html:hidden name="Form" property="aceitar" styleId="aceitar" />

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

                    <html:checkbox name="Form"
                                   property="avisoFaturaDisponivelPorEmail"
                                   styleId="avisoFaturaDisponivelPorEmail"
                                   onclick="inAlteracaoCheckboxes=true;setServico(this);verificaEmail(this);" />
                    <label for="avisoFaturaDisponivelPorEmail">
                        <%=Form.isManutencao()?"Aviso de disponibilidade da fatura via E-mail":"Ativar aviso de disponibilidade da fatura via E-mail"%>
                    </label><br/>

                    <html:checkbox name="Form"
                                   property="servicoEnvioFaturaPorEmail"
                                   styleId="servicoEnvioFaturaPorEmail"
                                   onclick="setServico(this);verificaEmail(this);" />
                    <label for="servicoEnvioFaturaPorEmail">
                        <%=Form.isManutencao()?"Envio de fatura via E-mail":"Ativar envio de fatura via E-mail"%>
                    </label><br/>

                    <html:checkbox name="Form"
                                   property="servicoEnvioCodigoBarrasPorSMS"
                                   styleId="servicoEnvioCodigoBarrasPorSMS"
                                   onclick="inAlteracaoCheckboxes=true;setServico(this);verificaSMS(this);" />
                    <label for="servicoEnvioCodigoBarrasPorSMS">
                        <%=Form.isManutencao()?"Envio de código de barras via Torpedo SMS":"Ativar envio de código de barras via Torpedo SMS"%>
                    </label><br/><br/>

                    <logic:equal name="Form" property="manutencao" value="false">

                        <input type="radio" id="radioDesativar" name="radioDesativar" onclick="setNaoAtivarServicos(this)" <logic:equal name="Form" property="clienteDadosZap" value="true">disabled="disabled"</logic:equal> />
                        <label for="radioDesativar" onclick="setNaoAtivarServicos($('radioDesativar'))">Nenhuma das opções acima.</label>

                    </logic:equal>

                </div>
                <logic:equal name="Form" property="manutencao" value="true">
                <style type="text/css">
                    #divTermoAtivacao label {
                        float:left;
                    }
                    #divTermoAtivacao input {
                        float:right;
                    }
                </style>
                </logic:equal>
                <div id="divBotoes">
                    <img width="63" height="13" src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" style="cursor:pointer" onmouseup="submitServicos(true)" />
                    <logic:equal name="Form" property="manutencao" value="false">
                    <img width="75" height="13" src="<%=request.getContextPath()%>/resources/images/bt_cancelar_nrml.gif" style="cursor:pointer" onmouseup="submitServicos(false)" />
                    </logic:equal>
                    <logic:equal name="Form" property="manutencao" value="true">
                    <img width="75" height="13" src="<%=request.getContextPath()%>/resources/images/bt_cancelar_nrml.gif" style="cursor:pointer" onmouseup="cancelaManutencao()" />
                    </logic:equal>
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