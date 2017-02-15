<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<bean:define name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="detalheForm" id="DetalheForm"/>
<html>
<head>
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            var spanArray = document.getElementsByTagName("SPAN");
            spanArray[11].style.width = "200px";
            spanArray[17].style.width = "100px";
            spanArray[18].style.width = "100px";
            parent.parent.oculta_div();
        -->
    </SCRIPT>
</head>
<body>
    <bean:write filter="false" name="DetalheForm" property="detalheHTML"/>
</body>
</html>