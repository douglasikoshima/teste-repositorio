<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<acesso:controlInitEnv/>

<html>
    <head>
        <title>Error</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.parent.oculta_div();
    -->
    </SCRIPT>
    
    <body bgcolor="#E3ECF4" background="#E3ECF4">
    <acesso:controlHiddenItem nomeIdentificador="cli_erp_verpagina">
           <table width="100%" align="center" height="100%" border="0" bgcolor="#E3ECF4" >
            <tr valign="middle">
                <td align="left" height="20">Data de Vigência de Contrato:&nbsp;<b><bean:write name="dtVigenciaP"/></b></td>
            </tr>
            <tr valign="middle">
                <td align="center"><b>O sistema de PONTUAÇÃO está temporariamento fora de serviço<b></td>
            </tr>
        </table>
    </acesso:controlHiddenItem>   
    </body>
</html>
