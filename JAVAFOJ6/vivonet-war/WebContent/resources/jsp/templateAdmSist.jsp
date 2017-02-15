<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/esacStyle.css" type="text/css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/dtree.css" type="text/css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        
        <!-- link rel="StyleSheet" href="dtree.css" type="text/css" / -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/dtree.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
    </netui-template:section>
</netui-template:template>
