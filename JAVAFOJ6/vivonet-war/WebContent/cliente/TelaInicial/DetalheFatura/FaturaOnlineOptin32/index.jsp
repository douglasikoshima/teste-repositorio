<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<% ParametrosVO parametros = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)); %>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="faturaOnlineForm"
             type="cliente.TelaInicial.DetalheFatura.FaturaOnlineOptin32.FaturaOnlineOptin32Controller.FaturaOnlineForm" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript" language="javascript">

        inContaOnlineAtiva = <bean:write name="Form" property="faturaOnlineAtiva" />;
        var msgErro = 'No momento n�o foi poss�vel realizar a sua solicita��o. Por favor, tente mais tarde.';

        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

            <logic:equal name="Form" property="redirect" value="true">
            if (confirm('<bean:write name="Form" property="msgErro" />')) {
                top.frameApp.showPopupTI('Detalhes do cliente',790,530,null,"/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/begin.do?aba=Gestor&origem=ContaOnline");
            
            } else {
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                top.frameApp.CarregaAba('bt02');
                top.frameApp.flUp = true;
                top.frameApp.resizeFrameDetalhe();
            }
            </logic:equal>
        };

        manageContaOnline = function(inAceitar) {

            var ajaxAction = '';

            if (inContaOnlineAtiva) {
                ajaxAction = 'desativacaoFaturaOnline.do'; // Desativa��o de fatura
            
            } else if (!inContaOnlineAtiva && !inAceitar) {
                ajaxAction = 'ativacaoFaturaOnline.do'; // N�o aceitou ativa��o de fatura
            }

            if (ajaxAction != '') {
                new Ajax.Request(ajaxAction, {
                    method: 'post',
                    parameters: {
                        aceitar: inAceitar
                    
                    }, onSuccess: function(e) {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        var dom = parseXml(e.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();

                        var msgProtocolo = '';
						if (jsonObj.xmlObject.nrProtocolo && (top.frameApp.nrProtocolo != jsonObj.xmlObject.nrProtocolo)) {
							msgProtocolo = 'Protocolo ' + jsonObj.xmlObject.nrProtocolo + ' gerado.';
                            top.frameApp.updateProtocolo(jsonObj.xmlObject.nrProtocolo);
                            top.frameApp.nrProtocoloScreenPop = '';
                        }

                        if (inAceitar) {
                            if (msgProtocolo != "") {
								alert(msgProtocolo);
							}
							
							alert(jsonObj.xmlObject.msgRetorno);
                            
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                            top.frameApp.CarregaAba('bt02');
                            top.frameApp.flUp = true;
                            top.frameApp.resizeFrameDetalhe();

                        } else {
							if (msgProtocolo != '') {
								alert(msgProtocolo);
							}
                            
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                            top.frameApp.CarregaAba('bt02');
                            top.frameApp.flUp = true;
                            top.frameApp.resizeFrameDetalhe();
                        }
                    
                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    
                    }, on406: function(e) {
                        window.top.frameApp.createErrorPopUp('erroContaOnline', msgErro, e.statusText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });

            } else {
                $('aceitar').value = "true";
                $('formContaOnline').action = 'manageContaOnline.do';
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                $('formContaOnline').submit();
        	}
        };
    </script>
    <style type="text/css">
        body {
            background:#ededed;
            margin:5px;
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
            width:690px;
        }
        #divBotoes {
            text-align:right;
        }
        #divTermoAtivacao {
            border:1px solid #ccc;
            margin-top:10px;
            background:#fff;
            display:block;
            height:128px;
            overflow:hidden;
            padding:5px;
        }
        #divTermoAtivacao h2 {
            font-weight:bold;
            font-size:11px;
            margin-bottom:3px;
            text-transform:uppercase;
            text-align:center;
        }
    </style>
</head>
<body id="bodyContaOnline">
<form id="formContaOnline" action="begin.do" method="post" target="ifrmAbas">

    <html:hidden name="Form" property="aceitar" styleId="aceitar" />
    <html:hidden name="Form" property="operacao" styleId="operacao" value="getServicos" />
    <html:hidden name="Form" property="msgErroDetalhada" styleId="msgErroDetalhada" />

    <% String tituloSessao = Form.isManutencao() ? "Manuten��o de avisos" : "Ativa��o e desativa��o"; %>

    <vivo:sessao id="divContaOnline" width="720" height="260" label="Fatura Online" operacoes="<%=tituloSessao%>">
        <logic:equal name="Form" property="msgErro" value="">
            <div id="divContaOnlineWrapper">
                <vivo:quadro id="divFaturamento" width="690" height="40" label="Faturamento">
                    Fatura Online:
                    <span id="spanStatus"><logic:match name="Form" property="faturaOnlineAtiva" value="false">DES</logic:match>ATIVADO</span>
                </vivo:quadro>
                <div id="divTermoAtivacao">
                    <logic:match name="Form" property="faturaOnlineAtiva" value="false">
                    <h2>Autoriza��o verbal<br/>Via atendimento humano</h2>
                    </logic:match>
                    <p style="margin-top:0;" align="justify">
                        <logic:match name="Form" property="faturaOnlineAtiva" value="false">

                            <% if ("PF".equals(parametros.getInTipoPessoa())) { %>

                            "Sr(a)_____, ao solicitar a Fatura On Line, o(a) Sr(a) autoriza a VIVO a enviar,
                            mensalmente, por correspond�ncia, apenas o boleto de pagamento referente � sua
                            fatura do Servi�o M�vel Pessoal, comprometendo-se a quit�-la at� sua data de
                            vencimento, sob pena de sujeitar-se �s san��es decorrentes da inadimpl�ncia
                            perante a VIVO. Dessa forma, os detalhamentos dos servi�os e das liga��es, assim
                            como a fatura, estar�o dispon�veis para consulta no Vivo Online, atrav�s do
                            endere�o www.vivo.com.br/vivoonline. Ainda, o Sr. (a) est� ciente de que, caso
                            n�o receba a fatura ou o boleto para pagamento, nos canais ora acordados, em at�
                            5 dias antes da data de vencimento, o Sr(a) dever� entrar em contato com os
                            canais de atendimento da Vivo, para solicita��o de segunda via da fatura.
                            O Sr.(a) confirma a solicita��o da Fatura On Line?"

                            <% } else if ("PJ".equals(parametros.getInTipoPessoa())) { %>

                            "Sr(a)_____, ao solicitar a Fatura On Line, o(a) Sr(a) autoriza a VIVO a enviar,
                            mensalmente, por correspond�ncia, apenas o boleto de pagamento referente � sua
                            fatura do Servi�o M�vel Pessoal, comprometendo-se a quit�-la at� sua data de
                            vencimento, sob pena de sujeitar-se �s san��es decorrentes da inadimpl�ncia
                            perante a VIVO. Dessa forma, os detalhamentos dos servi�os e das liga��es, assim
                            como a fatura, estar�o dispon�veis para consulta no Vivo Online Empresas,
                            atrav�s do endere�o www.vivo.com.br/vivoempresas. Ainda, o Sr. (a) est� ciente
                            de que, caso n�o receba a fatura ou o boleto para pagamento, nos canais ora
                            acordados, em at� 5 dias antes da data de vencimento, o Sr(a) dever� entrar em
                            contato com os canais de atendimento da Vivo, para solicita��o de segunda via da
                            fatura. O Sr.(a) confirma a solicita��o da Fatura On Line?"

                            <% } %>

                        </logic:match>
                        <logic:match name="Form" property="faturaOnlineAtiva" value="true">

                            <% if ("PF".equals(parametros.getInTipoPessoa())) { %>

                            Ao desativar esta op��o, voc� receber� sua fatura resumida em seu endere�o de correspond�ncia.

                            <% } else if ("PJ".equals(parametros.getInTipoPessoa())) { %>

                            Ao desativar esta op��o, a fatura resumida ser� enviada para o endere�o de sua empresa.

                            <% } %>

                        </logic:match>
                    </p>
                </div>
                <logic:match name="Form" property="faturaOnlineAtiva" value="false">
                <div id="infoAtivacao">
                    <strong>
                <% if ("PJ".equals(parametros.getInTipoPessoa())) { %>
					Ao ativar essa op��o Sr.(a) acessa sua Conta na internet e recebe no endere�o de sua
                    empresa apenas o boleto para pagamento.
                <% } else if ("PF".equals(parametros.getInTipoPessoa())) { %>
                    Ao ativar essa op��o Sr.(a) acessa sua Conta na internet e recebe no endere�o de sua
                    resid�ncia apenas o boleto para pagamento.
                <% } %>
                    </strong>
                </div>
                </logic:match>
                <div id="divBotoes">
                    <img width="63" height="13" src="<%=request.getContextPath()%>/resources/images/bt_aceitar_nrml.gif" style="cursor:pointer" onmouseup="manageContaOnline(true)" />
                    <img width="75" height="13" src="<%=request.getContextPath()%>/resources/images/bt_cancelar_nrml.gif" style="cursor:pointer" onmouseup="manageContaOnline(false)" />
                </div>
            </div>
        </logic:equal>
        <logic:notEqual name="Form" property="msgErro" value="">
            <logic:equal name="Form" property="redirect" value="false">
            <div id="divMsgErro" style="height:100%;padding-top:120px;text-align:center;width:100%">
                <bean:write name="Form" property="msgErro" />
            </div>
            </logic:equal>
        </logic:notEqual>
    </vivo:sessao>
</form>
</body>
</html>