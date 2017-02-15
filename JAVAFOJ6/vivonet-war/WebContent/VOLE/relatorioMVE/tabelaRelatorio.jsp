<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>

<%
 int i=0;
%>

    <div id="divTableTitle2">
        <table cellspacing="0" cellpadding="0" style="width:730px;">
          <tr>          
                <td align="center" width="100">Status</td>
                <td align="center" width="200">Tipo Relatorio</td>
                <td align="center" width="100">Data Inicio</td>
                <td align="center" width="100">Data Termino</td>
                <td align="center" width="150">Data Solicitação</td>
                <td align="center" width="80">Download</td>
        </tr>
    </table>
    </div> 
    <logic:iterate id="itLinha" name="Form" property="resultset.linhas.linhaArray">
    <table cellspacing="0" cellpadding="0" style="width:730px;" >
    <%i++;
    
    String classLinha=null;
    if (i % 2 == 0)
        classLinha = "rowTabelaZebradoOff";
    else
        classLinha = "rowTabelaZebradoOn";    
    
    %>
    
    <tr class="<%=classLinha%>">
        <td width="100" align="left" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[1]"/></td>
        <td width="200" align="left" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[2]"/></td>
        <td width="100" align="center" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[3]"/></td>
        <td width="100" align="center" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[4]"/></td>
        <td width="150" align="center" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[5]"/></td>
        <td width="80" align="center" onclick="javascript:exibirMensagem('divMensagem<%=i%>');">
             <logic:equal name="itLinha" property="valorArray[1]" value="Processado">
                <img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" title="Download" border="0" style="cursor:pointer;" onclick="download('<bean:write name="itLinha" property="valorArray[0]"/>');">
            </logic:equal>            
             <logic:notEqual name="itLinha" property="valorArray[1]" value="Processado">
                <img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" title="Download nao disponivel" border="0" >
            </logic:notEqual>     
        </td>  
    </tr>            
    </logic:iterate>
   </table>