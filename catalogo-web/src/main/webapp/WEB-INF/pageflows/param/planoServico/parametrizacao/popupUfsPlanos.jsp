<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<catalogo:popupPadrao>
	<c:set var="nomeBox" scope="application"  value="UF:"/>
	<input type="hidden" id="larguraPopup" value="390"/>
	<input type="hidden" id="alturaPopup" value="200"/>
	<jsp:include page="/templates/box_pre.jsp"/>
		<div id="popup_alterar_descricao_plano" align="center">
		<br clear="all"/><br clear="all"/>
			<c:forEach var="ufsTO" items="${ufs}">
				<div class="fleft" style="width:20px; margin-left:15px;">
					<b>${ufsTO.nmUF}</b>
				</div>
			</c:forEach>
		</div>
	<jsp:include page="/templates/box_pos.jsp"/>
	<br/>
</catalogo:popupPadrao>
