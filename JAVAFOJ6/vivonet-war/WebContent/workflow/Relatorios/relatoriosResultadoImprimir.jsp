<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ColunasRelatorio"%>
<%@ page import="workflow.Relatorios.RelatoriosController.RelatorioForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", -1); //evita o caching no servidor proxy

String dsTituloRelatorio = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getDsTituloRelatorio();
String detalheScript = ((RelatorioForm)relatorioForm).getDetalheScript();
String zcor="100";
%>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<table cellpadding="5" cellspacing="0" border="0" width="100%">
<tr bgcolor="#777777">
    <td colspan="4">
        <font color="white"><b><%=dsTituloRelatorio%></b></font>
    </td>
</tr>
</table>
<% 
String mostrarFiltros = ((RelatorioForm)relatorioForm).getMostrarFiltros();
if (mostrarFiltros != null && mostrarFiltros.equals("S")) {
    Object[] keys = ((RelatorioForm)relatorioForm).getCabecRelatorios().keySet().toArray();
    HashMap cabec = ((RelatorioForm)relatorioForm).getCabecRelatorios();
    int quebrar=0;
%>
<table cellpadding="1" cellspacing="0" border="0" width="100%" bgcolor="#777777">
    <%
    for(int i=0; i<keys.length; i++) {
        quebrar++;
    %>
        <%if (quebrar==1){%><tr bgcolor="#777777"><%}%>
            <td width="33%"><font color="white"><b><%=keys[i]%>:</b> <%=cabec.get(keys[i])%></font></td>
        <%if (quebrar==3) {%></tr><%quebrar=0;}%>
    <%}%>
    <%if (quebrar<3){%></tr><%}%>
</table><%}%>
<table cellpadding="1" cellspacing="0" border="1" width="100%">
    <tr bgcolor="#777777">
    <logic:iterate id="colunasVO" name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray" indexId="idx">
        <td align="center" width="10%">
            <font color="white" size="1"><b><bean:write name="colunasVO" property="dsColuna"/></b></font>
        </td>
    </logic:iterate>
    </tr>
    <logic:iterate id="valoresRelatorioVO" name="relatorioForm" property="wFRelatorioDinamicoVO.valoresRelatorioArray" indexId="idx">
    <tr>
        <logic:iterate id="valoresColunasVO" name="valoresRelatorioVO" property="valorColunaArray" indexId="idx2">
        <td align="center">
            <font size="1">
            <logic:equal name="valoresRelatorioVO" property="inTotal" value="1"><b></logic:equal>
            &nbsp;<bean:write name="valoresColunasVO" property="valor"/>&nbsp;
            </font>
        </td>
        </logic:iterate>
    </tr>
    <%if (zcor.equals("100")){ zcor = "83"; } else { zcor = "100"; }%>
    </logic:iterate>
</table>
<script>window.print();</script>