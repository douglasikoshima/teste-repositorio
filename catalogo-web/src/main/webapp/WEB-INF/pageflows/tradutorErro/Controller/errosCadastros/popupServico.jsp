<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao>
	<div id="popup_mensagem">
		<input id="larguraPopup" value="520px" class="hide"/>
		<input id="alturaPopup" value="50px" class="hide"/>
		<c:set var="nomeBox" scope="application"  value="Mensagem"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div align="center">
			<br clear="all" />
			${cdServico} - ${dsServico}
			<br clear="all" /><br clear="all" /><br clear="all" />
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
	</div>
</catalogo:popupPadrao>