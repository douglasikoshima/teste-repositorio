<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="FormFrameLupaLinha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm" type="cliente.TelaInicial.TelaInicialController.LupaLinhaForm"/>

<%!
private String convertCalendarToString(String format, Calendar calendar) {
    SimpleDateFormat sdf = new SimpleDateFormat( format );
    return sdf.format(calendar.getTime());
}
%>

<html>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet" />
    <style type="text/css">
        div#div_data_atualizacao,
        div#div_atualizar_todos {
            padding:5px;
        }
        div#div_data_atualizacao {
            font-size:11px;
            width:300px;
            font-weight:bold;
            float:left;
        }
        div#div_atualizar_todos {
            width:300px;
            float:right;
            text-align:right;
        }
    </style>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript">
        onload = function() {
            parent.parent.oculta_div();
            document.body.style.backgroundColor = '#ededed';
            <% if (request.getAttribute("erroAPI") != null) { %>
            alert('<%=request.getAttribute("erroAPI")%>');
            <% } %>
        }
        atualizarTodos = function() {
            new Ajax.Updater('containerPesquisa', 'modeloAparelho.do', {
                method: 'get',
                evalScripts: true,
                parameters: {
                    nrLinha: '<%=request.getAttribute("nrLinha")%>',
                    'operacao': 'atualizar'
                },
                onCreate: function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                },
                onComplete: function(e) {

                    if(window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                    var dom = parseXml(e.responseText);
                    var jsonString = xml2json(dom, '');
                    var jsonObj = jsonString.evalJSON();

                    if (jsonObj.retorno.erro == 'false') {
                        alert('Todos os parâmetros do aparelho foram atualizados com sucesso!');
                    } else {
                        alert(jsonObj.retorno.msgErro);
                        $('erroInfoLog').value = jsonObj.retorno.erroInfoLog;
                    }

                }, on503: function(e) {
                    createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }
            });
        }
    </script>
</head>
<body>
<input type="hidden" id="erroInfoLog" value="" />
<!-- <%=request.getAttribute("erroInfoLog")!=null?request.getAttribute("erroInfoLog"):""%>" -->
<% if (request.getAttribute("erroAPI") == null
        || ConstantesCRM.SVAZIO.equals(request.getAttribute("erroAPI").toString())) { %>
    <vivo:tbTable selectable="true" layoutWidth="737" layoutHeight="115" tableWidth="737" styleId="historicoAparelhos">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="left" width="100%" type="string">Nome do Parâmetro</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="item" name="FormFrameLupaLinha" property="modeloAparelho.linha.servicos" type="com.indracompany.ws.aparelhoservice.to.Servico">
                <vivo:tbRow key="linha1">
                    <vivo:tbRowColumn><bean:write name="item" property="codigoServSistemaOrigem" /></vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
    <div id="div_data_atualizacao">
        <%
        if (request.getAttribute("ultimaAtualizacao") != null) {
            Date dataUltimaAtualizacao = (Date) request.getAttribute("ultimaAtualizacao");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataUltimaAtualizacao);
            out.print("Data da Última Atualização: " + convertCalendarToString("dd/MM/yyyy HH:mm:ss", cal));
        }
        %>
    </div>
    <div id="div_atualizar_todos">
        <a href="javascript:atualizarTodos()"><img src="<%=request.getContextPath()%>/resources/images/bt_atualizar_todos_nrml.gif" /></a>
    </div>
<% } %>
</body>
</html>