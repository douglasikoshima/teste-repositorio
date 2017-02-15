<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>

<catalogo:popupPadrao>
	<c:set var="nomeBox" scope="application"  value="UF:"/>
	<input type="hidden" id="larguraPopup" value="400"/>
	<input type="hidden" id="alturaPopup" value="200"/>
	<jsp:include page="/templates/box_pre.jsp"/>
		<div id="popup_alterar_descricao_plano" align="center">
		<br clear="all"/><br clear="all"/>
			<logic:iterate id="ufs" property="listUF" name="ofertaSapForm">
				<div class="fleft" style="width:20px; margin-left:15px;">
					<b>${ufs.nmUF}</b>
	 					<c:if test="${ufs.listaAreaRegistro!= null}">
							<c:forEach items="${ufs.listaAreaRegistro}" var="sublista">
								<div style="padding-top:4px;"> 
 									${sublista.codigoArea}
								</div>
 							</c:forEach>
	 					</c:if> 
				</div>
			</logic:iterate>
		</div>
	<jsp:include page="/templates/box_pos.jsp"/>
	<br/>
</catalogo:popupPadrao>
