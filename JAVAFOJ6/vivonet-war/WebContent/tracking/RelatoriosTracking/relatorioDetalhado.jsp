<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.TrackingRelatorioDetalhadoVODocument.TrackingRelatorioDetalhadoVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatoriosTrackingForm" />
<bean:define id="Relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatoriosTrackingForm.relatorioDetalhado" type="br.com.vivo.fo.cliente.vo.TrackingRelatorioDetalhadoVODocument.TrackingRelatorioDetalhadoVO" />

<% String tableWidth = ConstantesCRM.SVAZIO; %>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="modulo" value="Tracking" />
<netui-template:section name="headerSection">
    <script type="text/javascript" language="javascript">
        onload = function() {
            <logic:equal name="Form" property="tipoRelatorio" value="RDPA">
            <% tableWidth = "1970"; %>
            var tituloPagina = 'Relatório detalhado de pedidos em aberto';
            </logic:equal>
            <logic:equal name="Form" property="tipoRelatorio" value="RDOL">
            <% tableWidth = "2180"; %>
            var tituloPagina = 'Relatório detalhado para monitorar o operador logístico';
            </logic:equal>
            $('bodyRelatoriosTracking').innerHTML = "<span class='sessaoTitulo'>Tracking&nbsp;>&nbsp;</span><span class='sessaoOperacoes'>" + tituloPagina + "</span>";
	        if ($('tableBody')) {
                var TRs = $('tableBody').select('tr');
                for (var i = 0; i < TRs.length; i++) {
                    TRs[i].observe('mouseover', mOver);
                    TRs[i].observe('mouseout', mOut);
                }
            }
            if(window.top.frameApp.dvAnimarAguarde) window.top.frameApp.stopAnimation();
		}
		function mOver(event) {
			var elements = event.element().ancestors()[0];
			if (elements.tagName == "TD") elements = event.element().ancestors()[1];
			var TDs = elements.childElements();
			for (var i = 0; i < TDs.length; i++) {
				TDs[i].setStyle({
					backgroundColor: '#d9e8f0'
				});
			}
		}
		function mOut(event) {
			var elements = event.element().ancestors()[0];
			if (elements.tagName == "TD") elements = event.element().ancestors()[1];
			var TDs = elements.childElements();
			for (var i = 0; i < TDs.length; i++) {
				TDs[i].setStyle({
					backgroundColor: '#fff'
				});
			}
		}
    </script>
    <style type="text/css">
        #tableRelatorio {

        }
        #tableRelatorio thead td {
            background:#545454;
            font-size:9px;
            font-weight:bold;
            color:#fff;
            text-align:center;
        }
        #tableRelatorio td {
            background-color:#fff;
            padding:3px;
            vertical-align:top;
        }
        #divTableContainer {
            width:780px;
            height:430px;
            overflow:auto;
        }
        .trResultados td {
            background-color:#ccc;
        }
        a, a:visited {
            text-decoration:none;
            color:#545454;
        }
    </style>
    <script type="text/javascript" language="javaScript" src="javascript/RelatoriosTracking.js" ></script>
</netui-template:section>
<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
<div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
<vivo:sessao id="bodyRelatoriosTracking" height="470" width="790" label="Tracking" operacoes="" scroll="S">

<%
TrackingRelatorioDetalhadoVO relatorio = (TrackingRelatorioDetalhadoVO) Relatorio;
if (relatorio.getItemRelatorioArray().length == 0) { %>
<div style="width:100%;text-align:center;padding-top:200px;height:430px">Nenhum relatório foi encontrado.</div>
<% } else { %>

<form action="getRelatorio" name="getRelatorio" id="getRelatorio" method="post">
    <input type="hidden" name="pageNumber" value="<bean:write name="Form" property="filtros.paginacao.pageNumber" format="#" />" />
</form>

<div id="divTableContainer">
	<table id="tableRelatorio" width="2340" cellspacing="1" cellpadding="3" bgcolor="#cccccc">
	    <thead>
	        <tr>
	        	<td align="left" width="130">Data/Hora Pedido</td>
		        <td align="left" width="110">Pedido</td>
		        <td align="left" width="110">Ordem Venda</td>
		        <td align="left" width="130">Data/Hora<br/>Ordem Venda</td>
		        <td align="left" width="200">Status</td>
                <logic:notEqual name="Form" property="tipoRelatorio" value="RDOL">
		        <td align="left" width="150">Responsável</td>
		        <td align="left" width="100">Bloqueio</td>
		        <td align="left" width="100">Data/Hora Bloqueio</td>
                </logic:notEqual>
		        <td align="left" width="20">UF</td>
		        <td align="left" width="100">Canal</td>
		        <td align="left" width="100">Qtde Aparelhos</td>
		        <td align="left" width="100">Fornecimento</td>
		        <td align="left" width="130">Data/Hora Fornecimento</td>
		        <td align="left" width="80">Picking</td>
		        <td align="left" width="130">Data/Hora Picking</td>
		        <td align="left" width="100">Nota Fiscal</td>
		        <td align="left" width="130">Data/Hora<br/>Nota Fiscal</td>
		        <!--  Apenas para Relatório Detalhado para Monitorar o Operador Logístico -->
		        <logic:equal name="Form" property="tipoRelatorio" value="RDOL">
		            <%--td align="left" width="80">Em Transporte</td--%>
		            <%--td align="left" width="130">Data/Hora Transporte</td--%>
		        </logic:equal>
		        <td align="left" width="80">Tempo Status</td>
		        <td align="left" width="80">Tempo decorrido</td>
		        <td align="left" width="150">Login de Criação da<br/>Ordem de Venda</td>
		    </tr>
		</thead>
	    <tbody id="tableBody">
	        <logic:iterate id="itemRelatorio" name="Relatorio" property="itemRelatorioArray">
	            <tr>
	                <td title="Data/Hora Pedido"><bean:write name="itemRelatorio" property="dtPedido" /></td>
	                <td title="Pedido"><bean:write name="itemRelatorio" property="nrPedido" /></td>
	                <td title="Ordem Venda"><bean:write name="itemRelatorio" property="nrOV" /></td>
	                <td title="Data/Hora Ordem Venda"><bean:write name="itemRelatorio" property="dtOV" /></td>
	                <td title="Status">
	                	<nobr>
		                    <script type="text/javascript" language="javascript">
		                    var txtStatus = '<bean:write name="itemRelatorio" property="dsStatus" />';
		                    txtStatus = replaceAll(txtStatus, "/n", "<br/>")
		                    document.write(txtStatus);
		                    </script>
	                	</nobr>
	                </td>
                    <logic:notEqual name="Form" property="tipoRelatorio" value="RDOL">
	                <td title="Responsável">
	                	<nobr>
		                	<script type="text/javascript" language="javascript">
		                    var txtStatus = '<bean:write name="itemRelatorio" property="nmLoginResponsavel" />';
		                    txtStatus = replaceAll(txtStatus, "/n", "<br/>")
		                    document.write(txtStatus);
		                    </script>
	                    </nobr>
	                </td>
	                <td title="Bloqueio"><bean:write name="itemRelatorio" property="dsMotivoBloqueio"/></td>
	                <td title="Data/Hora Bloqueio"><bean:write name="itemRelatorio" property="dtMotivoBloqueio"/></td>
                    </logic:notEqual>
	                <td title="UF"><bean:write name="itemRelatorio" property="sgUF"/></td>
	                <td title="Canal"><bean:write name="itemRelatorio" property="nmCanal"/></td>
	                <td title="Quantidade de Aparelhos"><bean:write name="itemRelatorio" property="qtAparelhos"/></td>
	                <td title="Fornecimento"><bean:write name="itemRelatorio" property="qtFornecimento"/></td>
	                <td title="Data/Hora Fornecimento"><bean:write name="itemRelatorio" property="dtFornecimento"/></td>
	                <td title="Picking"><bean:write name="itemRelatorio" property="nrPicking"/></td>
	                <td title="Data/Hora Picking"><bean:write name="itemRelatorio" property="dtPicking"/></td>
	                <td title="Nota Fiscal"><bean:write name="itemRelatorio" property="nrNF"/></td>
	                <td title="Data/Hora Nota Fiscal"><bean:write name="itemRelatorio" property="dtNF"/></td>
	                <!--  Apenas para Relatório Detalhado para Monitorar o Operador Logístico -->
	                <logic:equal name="Form" property="tipoRelatorio" value="RDOL">
	                    <%--td title="Em Transporte"><bean:write name="itemRelatorio" property="inTransporte"/></td--%>
	                    <%--td title="Data/Hora Transporte"><bean:write name="itemRelatorio" property="dtTransporte"/></td--%>
	                </logic:equal>
	                <td title="Tempo Status"><bean:write name="itemRelatorio" property="qtTempoStatus"/></td>
	                <td title="Tempo decorrido"><bean:write name="itemRelatorio" property="qtTempoDecorrido"/></td>
	                <td title="Login de Criação da Ordem de Venda"><bean:write name="itemRelatorio" property="nmLoginCriacao"/></td>
	            </tr>
	        </logic:iterate>
	    </tbody>
	</table>
</div>
<% } %>

<div style="float:left">
    <a href="javascript:if(window.top.frameApp.dvAnimarAguarde) window.top.frameApp.startAnimation();window.top.frameApp.location.href='begin.do';"><img style="margin:10px 0 0 10px;" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" /></a>
</div>
<%
int[] arrayPaginas = (int[]) request.getAttribute("paginas");
if (relatorio.getItemRelatorioArray().length > 0 && arrayPaginas.length > 1) { %>
<div style="float:right;margin-top:10px">
    <table>
        <tr>
            <td width="83" style="padding-right:20px;">
                <% if (Relatorio.getPaginacao().getPageNumber() > 1) { %>
                <a href="javascript:paginacao(<%=Relatorio.getPaginacao().getPageNumber()-1%>)"><img src="<%=request.getContextPath()%>/resources/images/bt_pag_anterior_nrml.gif" /></a>
                <% } %>
            </td>
            <td valign="top" style="color:#ccc">
                <logic:iterate indexId="c" id="paginasList" name="paginas" scope="request">
                <%
                String atualAbre = ConstantesCRM.SVAZIO;
                String atualFecha = ConstantesCRM.SVAZIO;
                if (Relatorio.getPaginacao().getPageNumber() == arrayPaginas[c.intValue()]) {
                    atualAbre = "<span style=\"font-weight:bold;color:#1969cc;font-size:13px;\">";
                    atualFecha = "</span>";

                }
                if (!String.valueOf(Relatorio.getPaginacao().getPageNumber()).equals(paginasList.toString())) {
                %>
                <a href="javascript:paginacao(<bean:write name="paginasList" format="#" />)">
                <%}%>
                    <%=atualAbre%><bean:write name="paginasList" format="#" /><%=atualFecha%>
                <%if(!String.valueOf(Relatorio.getPaginacao().getPageNumber()).equals(paginasList.toString())) { %>
                </a>
                <%}%>
                <%if(c.intValue() < arrayPaginas.length - 1) { %> | <% } %>
            </logic:iterate>
            </td>
            <td width="83" style="padding-left:20px;">
                <% if (Relatorio.getPaginacao().getHasNext() == 1) { %>
                <a href="javascript:paginacao(<%=Relatorio.getPaginacao().getPageNumber()+1%>)"><img src="<%=request.getContextPath()%>/resources/images/bt_prox_pag_nrml.gif" /></a>
                <% } %>
            </td>
        </tr>
    </table>
</div>
<% } %>

</vivo:sessao>

</netui-template:section>
</netui-template:template>