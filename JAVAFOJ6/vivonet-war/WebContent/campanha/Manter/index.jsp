<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script> </netui-template:section>
    <netui-template:section name="bodySection">
        
    <vivo:body width="780" height="600" idTable="tbMain" idDiv="dvMain">        
        <jsp:include page="/resources/menus/MenuManterCampanha.jsp"/>
        <IFRAME name="fraAbas" ID=fraAbas WIDTH="780" HEIGHT="500" FRAMEBORDER=0 SCROLLING=NO SRC="/FrontOfficeWeb/campanha/Manter/nada.html"></IFRAME>
    </vivo:body> </netui-template:section>
</netui-template:template>
