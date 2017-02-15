<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<!--acesso:controlInitEnv/-->
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm"/>
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            top.frameApp.oculta_div();
        -->
        </SCRIPT>
    </head>
    <body>
    <!--acesso:controlHiddenItem nomeIdentificador="fid_pe_verpagina"-->
        <table border="0" align="center" cellpadding="0" cellspacing="10">
            <tr>
                <td valign="top">Saldo total de Pontos:</td>
                <td valign="top"><bean:write name="Form" property="saldoPontos"/></td>
            </tr>
        </table>
        <script>
            //document.body.style.backgroundColor = '#ededed';
        </script>
          <!--/acesso:controlHiddenItem--> 
    </body>
</html>