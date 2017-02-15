<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao>
	<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupAlterarDescricaoPlano.do">
	
	<c:set var="nomeBox" scope="application"  value="Nome do Plano"/>
	<input type="hidden" id="larguraPopup" value="400"/>
	<input type="hidden" id="alturaPopup" value="50"/>
	<jsp:include page="/templates/box_pre.jsp"/>
		<div id="popup_alterar_descricao_plano">
		<br>
		${desc_plano}
		<br clear="all"/>
		</div>
	<jsp:include page="/templates/box_pos.jsp"/>
	<br/>

	</html:form>
</catalogo:popupPadrao>	
		
