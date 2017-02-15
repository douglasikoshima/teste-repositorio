<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupPadrao>
		<input id="larguraPopup" value="600px" class="hide"/>
		<input id="alturaPopup" value="250px" class="hide"/>
		<c:set var="nomeBox" scope="application" value="Detalhes do erro" />
		<jsp:include page="/templates/box_pre.jsp"/>
			<div class="both-scroll" style="height:200px; margin: 30px 0px 20px 0px; font-weight: bold">
				${descErro}
			</div>			
			<div style="text-align: right;">
				<input type="button" onclick="fecharPopup('popup1');" class="btOk" value="OK" />
			</div>
		<jsp:include page="/templates/box_pos.jsp"/>
</catalogo:popupPadrao>