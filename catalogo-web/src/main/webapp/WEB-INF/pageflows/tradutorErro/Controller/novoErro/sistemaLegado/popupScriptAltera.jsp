<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="popup_script" style="width: 100%;">
	<input id="larguraPopup" value="825px" class="hide"/>
	<input id="alturaPopup" value="auto" class="hide"/>
	<c:set var="nomeBox" scope="application"  value="SCRIPT"/>
	<jsp:include page="/templates/box_pre.jsp"/>
	<div id="script" style="width: 100%; float: left; height: 100px">
		<br clear="all" /><br clear="all" />
		<div class="fleft">
			${SQLALTERASL}
		</div>
		<br clear="all" /><br clear="all" /><br clear="all" />
		<div class="botao">
			<html:button property="btn_ok" tabindex="1" styleId="bot_ok" onClick="fecharPopup('popup1');$('bot_pesquisa').onclick();" styleClass="btNavegacao74" value="Ok"/>
		</div>
	</div>
	<jsp:include page="/templates/box_pos.jsp"/>
</div>