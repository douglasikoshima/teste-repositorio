<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<table width="100%" height="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="middle" align="center">
            <% if (request.getAttribute("erro") != null) { %>
            <%=request.getAttribute("erro")%>
            <% } else if ("SWF".equals(request.getAttribute("extensao"))) { %>
            <object type="application/x-shockwave-flash"
                    data="/FrontOfficeWeb/VOLE/banner/<%=request.getAttribute("nmArquivo")%>"
                    wmode="transparent"  width="600" height="350">
                <param name="movie" value="/FrontOfficeWeb/VOLE/banner/<%=request.getAttribute("nmArquivo")%>" />
                <param name="wmode" value="transparent" />
                <param name="width" value="600" />
                <param name="height" value="350" />
            </object>
            <% } else { %>
            <img src="<%=request.getContextPath()%>/VOLE/ParametrizacaoBanner/mostrarImagemBanner.do?geraImagem=true&nmArquivo=<%=request.getAttribute("nmArquivo")%>" />
            <% } %>
        </td>
</table>
</body>
</html>