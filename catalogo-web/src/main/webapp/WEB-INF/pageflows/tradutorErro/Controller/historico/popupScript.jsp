<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupConfirmacao>
<netui-temp:template templatePage="/templates/popupConfirmacao.jsp">
	<input id="larguraPopup" value="500px" class="hide"/>
	<input id="alturaPopup" value="200px" class="hide"/>
		<div>
			<br clear="all" /><br clear="all" />
			${SQL}
			<br clear="all" /><br clear="all" /><br clear="all" />
			<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Ok" />
		</div>
</catalogo:popupConfirmacao>