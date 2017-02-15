<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupPadrao>
	<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupUFs.do">	
		<c:set var="nomeBox" scope="application"  value="UF:"/>
		<input type="hidden" id="larguraPopup" value="400"/>
		<input type="hidden" id="alturaPopup" value="200"/>
		<jsp:include page="/templates/box_pre.jsp"/>
			<div id="popup_alterar_descricao_plano" align="center">
			<br clear="all"/><br clear="all"/>
			
				<logic:iterate id="ufList" property="ufList" name="consultaPlanosForm">
					<div class="fleft" style="width:20px; margin-left:15px;">
						<b>${ufList.nmUF}</b>
						<c:if test="${ufList.listaAreaRegistro != null}">
							<c:forEach items="${ufList.listaAreaRegistro}" var="cdArea">
								<div style="padding-top:4px;">
									<c:out value="${cdArea.codigoArea}"/>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</logic:iterate>
				
			</div>
		<jsp:include page="/templates/box_pos.jsp"/>
		<br/>
	</html:form>
</catalogo:popupPadrao>		
