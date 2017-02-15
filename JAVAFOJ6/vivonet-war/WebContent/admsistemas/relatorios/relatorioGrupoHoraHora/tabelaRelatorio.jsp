<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>

<%
 int i=0;
%>

    <div id="divTableTitle2">
        <table cellspacing="0" cellpadding="0" style="width:900px;">
          <tr>          
            <td align="center" width="100">Data</td>
            <td align="center" width="100">Intervalo</td>
            <td align="center" width="100">Canal Atual</td>
            <td align="center" width="100">Grupo Anterior</td>
            <td align="center" width="100">Fornecedor Anterior</td>                                
            <td align="center" width="100">Site Anterior</td>                                
            <td align="center" width="100">Motivo do Recontato</td>                                
            <td align="center" width="100">Sub Motivo do Recontato</td>                                
            <td align="center" width="100">Quantidade</td>
        </tr>
    </table>
    </div> 
    <logic:iterate id="itLinha" name="Form" property="resultset.linhas.linhaArray">
    <table cellspacing="0" cellpadding="0" style="width:900px;" >
    <%i++;
    
    String classLinha=null;
    if (i % 2 == 0)
        classLinha = "rowTabelaZebradoOff";
    else
        classLinha = "rowTabelaZebradoOn";    
    
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
    </tr>            
    </logic:iterate>
   </table>