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
		<div id="popup_novo_servico_negocio" style="width: 100%;">
			<input id="larguraPopup" value="825px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<c:set var="nomeBox" scope="application"  value="Novo Serviço de Negocio"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div id="novo_servico_negocio" style="width: 100%; float: left; height: 100px">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/novoSN.do">
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
					<div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
					<br clear="all" />
					<div class="fleft">
						<div class="label-form-bold" style="width:110px">Codigo do SN</div>
						<html:text tabindex="1" size="30" styleId="cd_servicoNegocioAlterado" property="cdServicoNegocio" />
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">Descri&ccedil;&atilde;o do SN<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:text styleClass="required" tabindex="2" size="50" styleId="ds_servicoNegocioNovo" value="${ds_servico_negocio}" property="dsServicoNegocio" />
					</div>
					<br clear="all" /><br clear="all" />
					<div class="botao">
						<html:button tabindex="3" styleId="bot_novo" property="bot_novo" onClick="if(send(this, null, null, 'div_erro_popup'));return false" styleClass="btNavegacao74" value="Gravar" bundle="messages" altKey="catalogo.tradutorErro.Controller.novoErro.servicoNegocio.Gravar" titleKey="catalogo.tradutorErro.Controller.novoErro.servicoNegocio.Gravar"/>
						<html:link styleId="bot_hidden" action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/abrirPopup.do?tipoPopup=novo" onClick="abrirPopup1(this.href, null , 'popup_novo_servico_negocio');return false;"/>
					</div>
				</html:form>
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupWidthFull>