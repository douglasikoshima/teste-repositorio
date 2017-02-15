<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<acesso:controlInitEnv/>
<bean:define name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="detalheForm" id="DetalheForm"/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.parent.oculta_div();
            document.body.style.backgroundColor = '#e3ecf4';
        -->
        </SCRIPT>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_eat_verpagina">
            <table width="100%" align="center" height="100%" border="0" cellpadding="0" cellspacing="0">
                <html:form action="/cliente/TelaInicial/DetalheFatura/loadFatura.do">
                    <tr>
                        <td align="center"><b><bean:write filter="false" name="DetalheForm" property="msgErro"/></b></td>
                    </tr>
                </html:form>
            </table>
    </acesso:controlHiddenItem>
    </body>
</html>