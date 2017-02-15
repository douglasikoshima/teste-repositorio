<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<div id="popup_script" style="width: 100%;">
	<input id="larguraPopup" value="825px" class="hide"/>
	<input id="alturaPopup" value="auto" class="hide"/>
	<c:set var="nomeBox" scope="application"  value="SCRIPT"/>
	<jsp:include page="/templates/box_pre.jsp"/>
	<div id="script" style="width: 100%; float: left; height: 100px">
			<br clear="all" /><br clear="all" />
			<div class="fleft">${SQLNOVOSN}</div>
			<br clear="all" /><br clear="all" /><br clear="all" />
			<div class="botao">
				<html:button tabindex="1" styleId="bot_ok" property="bot_ok" onClick="fecharPopup('popup1');$('bot_pesquisa').onclick();" styleClass="btNavegacao74" value="Ok"/>
			</div>
	</div>
	<jsp:include page="/templates/box_pos.jsp"/>
</div>