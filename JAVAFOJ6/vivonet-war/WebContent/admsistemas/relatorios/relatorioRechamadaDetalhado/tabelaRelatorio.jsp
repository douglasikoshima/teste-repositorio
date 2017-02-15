<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm" />

<%
 int i=0;
%>
    <div id="divTableTitle2">
        <table cellspacing="0" cellpadding="0">
          <tr>          
            <td align="center" width="50">Seq&#252;&#234;ncia</td>
            <td align="center" width="100">Canal Atual</td>
            <td align="center" width="100">Data/Hora</td>
            <td align="center" width="100">Grupo Anterior</td>
            <td align="center" width="100">Fornecedor Anterior</td>
            <td align="center" width="100">Site Anterior</td>
            <td align="center" width="100">Login Anterior</td>
            <td align="center" width="100">Motivo da Recontato</td>
            <td align="center" width="100">Submotivo de Recontato</td>
            <td align="center" width="100">N&#250;mero Linha</td>
            <td align="center" width="100">Tipo de Linha</td>
            <td align="center" width="100">Segmenta&#231;&#227;o</td>
            <td align="center" width="50">* Nota da URA</td>
            <td align="center" width="100">N&#186; Protocolo</td>
        </tr>
    </table>
    </div> 
    <table cellspacing="0" cellpadding="0" width="1300px">
    <logic:iterate id="itLinha" name="Form" property="resultset.linhas.linhaArray">
    <%i++;
    String classLinha=ConstantesCRM.SVAZIO;
    if (i % 2 == 0){
        classLinha = "rowTabelaZebradoOff";
    }else{
        classLinha = "rowTabelaZebradoOn";    
    }
    %>
    <tr class="<%=classLinha%>">
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[0]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[1]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[2]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[3]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[4]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[5]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[6]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[7]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[8]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[9]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[10]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[11]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[12]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[13]"/></td>
    </tr>            
    </logic:iterate>
    </table>
	<input type="hidden" id="pagNum" value="1" />
    <%
    	long totalPag = (Long)request.getSession().getAttribute("totalPaginasRecontato");
    	out.write("<input type=\"hidden\" id=\"totalpag\" value=\"" + String.valueOf(totalPag) + "\" />");
    %>