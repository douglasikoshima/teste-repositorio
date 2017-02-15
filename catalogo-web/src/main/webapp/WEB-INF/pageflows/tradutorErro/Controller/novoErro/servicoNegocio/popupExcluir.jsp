<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupWidthFull>
		<div id="div_erro_popup" style="position: relative;"></div>
		<div id="popup_excluir_servico_negocio" style="width: 100%;">
			<input id="larguraPopup" value="825px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<c:set var="nomeBox" scope="application"  value="Excluir Serviço de Negocio"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div id="exclui_servico_negocio" style="width: 100%; float: left; height: 100px" align="center">
				<br clear="all" /><br clear="all" />
				Deseja excluir este Servi&ccedil;o de Negocio?
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/excluirSN.do">
					<br clear="all" /><br clear="all" /><br clear="all" />
						<html:hidden styleId="hidden_cd_servico_negocio" property="cdServicoNegocio" />
						<html:button property="bt_sim" onMouseDown="$('hidden_cd_servico_negocio').value=${cdServicoNegocio};" onclick="if(send(this, null, null, 'div_erro_popup')){ $(bot_hidden).click()}; return false" styleClass="btNavegacao74" value="Sim" />
						<html:link styleId="bot_hidden" action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/abrirPopup.do?tipoPopup=exclui" onClick="abrirPopup1(this.href, null , 'popup_excluir_servico_negocio');return false;"/>
				</html:form>
				&nbsp;
				<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Não"/>
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupWidthFull>