<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="FormManterDaemon" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon"  type="VOLTAV.manterDaemon.ManterDaemonController.FormManterDaemon"/>

<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body>
        <table width="100%" height="100%" border="1">
            <tr>
                <td valign="top">
                    <bean:write name="FormManterDaemon" property="xml"/>
                </td>
            </tr>
        </table>
        
        
        <script>
            window.top.frameApp.oculta_div();
        </script>
        
    </body>
</html>