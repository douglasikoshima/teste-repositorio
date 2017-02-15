<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.TrackingRelatorioConsolidadoVODocument.TrackingRelatorioConsolidadoVO"%>
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
<bean:define id="Relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatoriosTrackingForm.relatorioConsolidado" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="modulo" value="Tracking" />
<netui-template:section name="headerSection">
    <script type="text/javascript" language="javascript">
        onload = function() {
            var totalGeral = '<bean:write name="Relatorio" property="vlVolumeTotalGeral" format="#" />';
            if ($('tdTotalGeral')) $('tdTotalGeral').update(totalGeral);
            if(window.top.frameApp.dvAnimarAguarde) window.top.frameApp.stopAnimation();
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
            background:#fff;
        }
        #divTableContainer {
            width:780px;
            height:430px;
            overflow:auto;
        }
        .trResultados td {
            background-color:#ccc;
        }
    </style>
</netui-template:section>
<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
<div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
<vivo:sessao id="bodyRelatoriosTracking" height="470" width="790" label="Tracking" operacoes="Relatório de Pedidos em Aberto" scroll="N">

<div id="divTableContainer">
    <%
    TrackingRelatorioConsolidadoVO relatorio = (TrackingRelatorioConsolidadoVO)Relatorio;
    if (relatorio.getVlVolumeTotalGeral().intValue() == 0) { %>
    <div style="width:100%;text-align:center;padding-top:200px">Nenhum relatório foi encontrado.</div>
    <% } else { %>
    <table id="tableRelatorio" width="100%" cellspacing="1" cellpadding="2" bgcolor="#000000">
        <thead>
            <tr>
                <td align="left" width="220">Status</td>
                <td align="left" width="70">Volume Total</td>
                <td align="left" width="70">Porcentagem<br/>sobre Total</td>
                <td align="left" width="70">Volume<br/>até 12 horas</td>
                <td align="left" width="70">Volume<br/>até 1 dia</td>
                <td align="left" width="70">Volume<br/>até 2 dias</td>
                <td align="left" width="70">Volume<br/>até 3 dias</td>
                <td align="left" width="70">Volume<br/>até 4 dias</td>
                <td align="left" width="70">Volume<br/>até 5 dias</td>
                <td align="left" width="70">Volume<br/>+ 5 dias</td>
                <td align="left" width="70">Volume<br/>+ 10 dias</td>
                <td align="left" width="40">Aging Status</td>
                <td align="left" width="50">Aging Pedido</td>
            </tr>
        </thead>
        <logic:iterate id="itemRelatorio" name="Relatorio" property="itemRelatorioArray">
            <tr>
                <td><bean:write name="itemRelatorio" property="dsStatusPedido"/></td>
                <td><bean:write name="itemRelatorio" property="vlVolumeTotal" format="#" /></td>
                <td><bean:write name="itemRelatorio" property="vlPorcentagem" format="#" />%</td>
                <logic:notEqual name="itemRelatorio" property="nrStatusPedido" value="8">
                <logic:iterate id="itemRelatorioVolume" name="itemRelatorio" property="trackingVolumeVOArray">
                    <td>
                        <logic:notEqual name="itemRelatorioVolume" property="vlVolume" value="0" >
                            <bean:write name="itemRelatorioVolume" property="vlVolume" format="#" />
                        </logic:notEqual>
                    </td>
                </logic:iterate>
                </logic:notEqual>
                <logic:equal name="itemRelatorio" property="nrStatusPedido" value="8">
                    <td colspan="8"></td>
                </logic:equal>
                <td>
                    <logic:notEqual name="itemRelatorio" property="dsAgingStatus" value="0">
                        <bean:write name="itemRelatorio" property="dsAgingStatus" />
                    </logic:notEqual>
                </td>
                <td>
                    <logic:notEqual name="itemRelatorio" property="dsAgingPedido" value="0 dias">
                        <bean:write name="itemRelatorio" property="dsAgingPedido" />
                    </logic:notEqual>
                </td>
            </tr>
        </logic:iterate>
        <logic:iterate id="itemResultado" name="Relatorio" property="resultadosArray" indexId="c">
        <% int indice = c.intValue(); %>
        <tr <% if (indice==0) {%>bgcolor="#cccccc"<% } %>>
            <td style="background-color:#f0f0f0;font-weight:bold;"><% if (indice==0) {%>Total:<% } %></td>
            <td style="background-color:#f0f0f0;" id="tdTotalGeral"></td>
            <td style="background-color:#f0f0f0;"><% if (indice==0) {%>100%<% } %></td>
            <logic:iterate id="itemResultadoVolume" name="itemResultado" property="trackingVolumeVOArray">
            <td style="background-color:#f0f0f0;"><bean:write name="itemResultadoVolume" property="vlVolume" format="#" /><% if (indice==1) {%>%<% } %></td>
            </logic:iterate>
            <td style="background-color:#f0f0f0;"></td>
            <td style="background-color:#f0f0f0;"></td>
        </tr>
        </logic:iterate>
    </table>
    <% } %>
</div>

<a href="javascript:if(window.top.frameApp.dvAnimarAguarde) window.top.frameApp.startAnimation();window.top.frameApp.location.href='begin.do';"><img style="margin:10px 0 0 10px;border:none" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" /></a>

</vivo:sessao>

</netui-template:section>
</netui-template:template>