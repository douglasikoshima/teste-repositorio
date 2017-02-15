<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>

<%
 int i=0;
%>

    <div id="divTableTitle">
        <table width="760px" cellspacing="0" cellpadding="0" class="tableProtocolo">
            <tr>          
                <td align="center" width="120"></td>
                <td align="center" width="120"></td>
                <td align="center" width="120"></td>
                <td align="center" width="200" colspan="2">Período</td>
                <td align="center" width="200" colspan="2">Consolidado</td>                                
            </tr>
            <tr>          
                <td align="center" width="120">Intervalo</td>
                <td align="center" width="120">Motivo de Rechamada</td>
                <td align="center" width="120">Sub Motivo de Rechamada</td>
                <td align="center" width="100">Quantidade</td>
                <td align="center" width="100">Percentual</td>
                <td align="center" width="100">Quantidade</td>
                <td align="center" width="100">Percentual</td>                
            </tr>
        </table>
    </div> 
    <logic:iterate id="itLinha" name="Form" property="resultset.linhas.linhaArray">
    <table width="760px" cellspacing="1" cellpadding="0" class="tableProtocolo" >
    <%i++;
    
    String classLinha=null;
    if (i % 2 == 0)
        classLinha = "rowTabelaZebradoOff";
    else
        classLinha = "rowTabelaZebradoOn";    
    
    %>
    
    <tr class="<%=classLinha%>">
        <td width="110" align="center"><bean:write name="itLinha" property="valorArray[0]"/></td>
        <td width="115" align="center"><bean:write name="itLinha" property="valorArray[1]"/></td>
        <td width="115" align="center"><bean:write name="itLinha" property="valorArray[2]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[3]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[4]"/>%</td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[5]"/></td>
        <td width="100" align="center"><bean:write name="itLinha" property="valorArray[6]"/>%</td>
    </tr>            
    </logic:iterate>
   </table>