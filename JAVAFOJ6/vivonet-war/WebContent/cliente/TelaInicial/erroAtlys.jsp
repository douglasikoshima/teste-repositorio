<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<html>
    <head>
        <title>Error</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.oculta_div();
            -->
        </SCRIPT>
    </head>
    <body bgcolor="#E3ECF4" background="#E3ECF4">
    <acesso:controlHiddenItem nomeIdentificador="cli_erroat_verpagina">
        <table width="100%" align="center" height="100%" border="0" bgcolor="#E3ECF4" >
            <tr valign="middle">
                <td align="center"><b>O sistema de HIST�RICO DE SERVI�OS est� temporariamente fora de servi�o</b></td>
            </tr>
        </table>
    </acesso:controlHiddenItem> 
    </body>
</html>