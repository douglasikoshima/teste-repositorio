<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupPadrao>
		<div id="div_erro_copiar_restricao" style="position: relative"></div>
		<div id="popup_copiar_restricoes" style="width: 100%">
			<c:set var="nomeBox" scope="application"  value="VALOR SIMCARD"/>
			<jsp:include page="/templates/box_pre.jsp"/>
				<br clear="all"/><br clear="all"/><br clear="all"/>
				<div style="width:90%; height: 150px; text-align: center; font-weight: bold; color: #666; position: absolute;">
					Confirma o valor do SIMCARD R$ ${valorSimCard} (à vista)
					<br clear="all"/>
				</div>
				<br clear="all"/><br clear="all"/>
				<div class="barra"></div>
			<div class="botao">
			    <input type="button" onclick="fecharPopup('popup1');$('resultado_pesquisa').hide();" class="btNavegacao74" value="Cancelar" />
			    <span>&nbsp;</span>
			    <netui:button tagId="botao_confirmar_valor_simcard" value="Sim" styleClass="btNavegacao74" />
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupConfirmacao>