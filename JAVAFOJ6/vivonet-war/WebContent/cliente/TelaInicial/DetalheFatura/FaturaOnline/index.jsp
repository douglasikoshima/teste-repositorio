<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
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
             type="cliente.TelaInicial.DetalheFatura.FaturaOnline.FaturaOnlineController.FaturaOnlineForm" />

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
        manageContaOnline = function(inAceitar) {
            new Ajax.Request('manageContaOnline.do', {
                method: 'post',
                parameters: {
                    aceitar: inAceitar
                }, onSuccess: function(e) {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    var dom = parseXml(e.responseText);
                    var jsonString = xml2json(dom, '');
                    var jsonObj = jsonString.evalJSON();
                    if (inAceitar) {
                        alert(jsonObj.xmlObject.msgRetorno);
                        if (jsonObj.xmlObject.nrProtocolo
                            && (top.frameApp.nrProtocolo != jsonObj.xmlObject.nrProtocolo)) {
                            alert('Protocolo ' + jsonObj.xmlObject.nrProtocolo + ' gerado.');
                            top.frameApp.updateProtocolo(jsonObj.xmlObject.nrProtocolo);
							top.frameApp.nrProtocoloScreenPop = '';
                        }
                    }
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    $('formContaOnline').submit();
                }, onCreate: function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                }, on406: function(e) {
                    window.top.frameApp.createErrorPopUp('erroContaOnline', e.responseText, e.statusText, false);
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }
            });
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
        }
        #divTermoAtivacao h2 {
            font-weight:bold;
            font-size:11px;
            margin-bottom:3px;
        }
    </style>
</head>
<body id="bodyContaOnline">
<form id="formContaOnline" action="begin.do" method="post" target="ifrmAbas">
    <vivo:sessao id="divContaOnline" width="680" height="270" label="Fatura Online" operacoes="Ativação e desativação">
        <logic:equal name="Form" property="msgErro" value="">
            <div id="divContaOnlineWrapper">
                <vivo:quadro id="divFaturamento" width="650" height="30" label="Faturamento">
                    Status atual: 
                    <span id="spanStatus"><logic:match name="Form" property="faturaOnlineAtiva" value="false">DES</logic:match>ATIVADO</span>
                </vivo:quadro>
                <div id="divTermoAtivacao">
                    <logic:match name="Form" property="faturaOnlineAtiva" value="false">
                    <h2>Termo para ativação</h2>
                    </logic:match>
                    <p style="margin-top:0;" align="justify">
                        <logic:match name="Form" property="faturaOnlineAtiva" value="false">

                            <% if ("PF".equals(parametros.getInTipoPessoa())) { %>

                            Sr(a)_____, ao solicitar a Fatura Online, o(a) Sr(a) autoriza a VIVO a enviar,
                            mensalmente, por correspondência, apenas o boleto de pagamento referente à sua 
                            fatura do Serviço Móvel Pessoal, comprometendo-se a quitá-la até sua data de 
                            vencimento, sob pena de sujeitar-se às sanções decorrentes da inadimplência 
                            perante a VIVO. Dessa forma, os detalhamentos dos serviços e das ligações, 
                            assim como a fatura, estarão disponíveis para consulta no Vivo Online, através 
                            do endereço www.vivo.com.br/vivoonline. Ainda, o Sr. (a) está ciente de que, 
                            caso não receba a fatura ou o boleto para pagamento, nos canais ora acordados, 
                            em até 5 dias antes da data de vencimento, o Sr(a) deverá entrar em contato 
                            com os canais de atendimento da Vivo, para solicitação de segunda via da 
                            fatura. O Sr.(a) confirma a solicitação da Fatura On Line?

                            <% } else if ("PJ".equals(parametros.getInTipoPessoa())) { %>

                            Sr(a)_____, ao solicitar a Fatura On Line, o(a) Sr(a) autoriza a VIVO a enviar,
                            mensalmente, por correspondência, apenas o boleto de pagamento referente à sua
                            fatura do Serviço Móvel Pessoal, comprometendo-se a quitá-la até sua data de
                            vencimento, sob pena de sujeitar-se às sanções decorrentes da inadimplência
                            perante a VIVO. Dessa forma, os detalhamentos dos serviços e das ligações,
                            assim como a fatura, estarão disponíveis para consulta no Vivo Online Empresas,
                            através do endereço www.vivo.com.br/vivoempresas. Ainda, o Sr. (a) está ciente
                            de que, caso não receba a fatura ou o boleto para pagamento, nos canais ora
                            acordados, em até 5 dias antes da data de vencimento, o Sr(a) deverá entrar em
                            contato com os canais de atendimento da Vivo, para solicitação de segunda via da
                            fatura. O Sr.(a) confirma a solicitação da Fatura On Line?

                            <% } %>

                        </logic:match>
                        <logic:match name="Form" property="faturaOnlineAtiva" value="true">

                            <% if ("PF".equals(parametros.getInTipoPessoa())) { %>

                            Ao desativar esta opção, você voltará a receber a fatura em sua
                            correspondência, conforme o tipo de fatura, e não mais apenas o boleto para
                            pagamento.

                            <% } else if ("PJ".equals(parametros.getInTipoPessoa())) { %>

                            Ao desativar a Fatura online voltará a ser enviada para o endereço de sua
                            empresa a sua fatura. Continuaremos disponibilizando sua fatura no vivo
                            online empresas.

                            <% } %>

                        </logic:match>
                    </p>
                </div>
                <logic:match name="Form" property="faturaOnlineAtiva" value="false">
                <% if ("PJ".equals(parametros.getInTipoPessoa())) { %>
                <div id="infoAtivacao">
                    <strong>Ao ativar a fatura online você acessa sua fatura na internet e recebe no
                    endereço de sua empresa apenas o Boleto para pagamento.</strong>
                </div>
                <% } %>
                </logic:match>
                <div id="divBotoes">
                    <img width="63" height="13" src="<%=request.getContextPath()%>/resources/images/bt_aceitar_nrml.gif" style="cursor:pointer" onmouseup="manageContaOnline(true)" />
                    <img width="75" height="13" src="<%=request.getContextPath()%>/resources/images/bt_cancelar_nrml.gif" style="cursor:pointer" onmouseup="manageContaOnline(false)" />
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