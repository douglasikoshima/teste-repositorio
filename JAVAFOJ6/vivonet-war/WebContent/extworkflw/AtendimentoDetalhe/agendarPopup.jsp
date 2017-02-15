<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<form action="processoAction.do" method="post">
    <table width="100%">
        <tr valign="top">
            <td width="20%">Data:</td>
            <td><netui:textBox dataSource="{}"/></td>
        </tr>
        <tr valign="top">
            <td>Hora:</td>
            <td><netui:textBox dataSource="{}"/></td>
        </tr>
        <tr valign="top">
            <td colspan="2">Observações:</td>
        </tr>
        <tr valign="top">
            <td colspan="2"><netui:textArea rows="5" dataSource="{}" style="width=290"/></td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td width="50%" align="right">            
                <vivo:botao id="btAgendar" width="60px" height="10px" value="Agendar" styleClass="btTemplate" onclick="window.close();"/>            
            </td>
            <td width="50%" align="left">            
                <vivo:botao id="btFechar" width="60px" height="10px" value="Fechar" styleClass="btTemplate" onclick="window.close();"/>            
            </td>
        </tr>
    </table>
</form>
