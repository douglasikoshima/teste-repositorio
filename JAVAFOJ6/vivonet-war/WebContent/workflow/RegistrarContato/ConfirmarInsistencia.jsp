<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<html>

<script language="JavaScript">
    function registrar() {
    }
    
    function finalizar() {
    }
</script>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<br>
<center>Registrar Insistência?</center><br>
<table width="100%">
    <tr>
        <td width="50%" align="right">
            <vivo:botao id="btSim" width="60px" height="10px" value="Sim" styleClass="btTemplate" onclick="registrar();"/>
        </td>
        <td width="50%" align="left">
            <vivo:botao id="btNao" width="60px" height="10px" value="Não" styleClass="btTemplate" onclick="finalizar();"/>
        </td>
    </tr>
</table>
</html>