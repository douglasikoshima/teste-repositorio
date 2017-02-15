<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<catalogo:popupWidthFull>
	<netui-temp:section name="conteudo">
		<div id="div_erro_popup" style="position: relative;"></div>
		<div id="popup_alterar_erro_padrao" style="width: 100%;">
			<input id="larguraPopup" value="825px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<c:set var="nomeBox" scope="application"  value="Alterar Mensagem"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div id="altera_erro_padrao" style="width: 100%; float: left; height: 100px">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/errosCadastros/alterarErro">
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
					<div class="link_manual" title="Dúvida">
						<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
						<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank">
							<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
						</html:link>
					</div>
					<br clear="all" />
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px">C&oacute;digo do Erro Padr&atilde;o</div>
						<html:text tabindex="1" size="30" styleId="cd_erroPadraoAlterado" value="${cd_erro_padrao}" property="erroComumVO.cdPadrao" readonly="true"/>
						<html:hidden property="erroComumVO.cdPadrao" styleClass="hide" value="${cd_erro_padrao}"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">Mensagem<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:text styleClass="required" tabindex="2" size="50" styleId="mensagem" value="${mensagem}" property="erroComumVO.mensagem"/>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="botao">
						<html:button bundle="messages" tabindex="3" styleId="bot_alterar" property="bot_alterar" onClick="if(send(this, null, null, 'div_erro_popup')){ $(bot_hidden).click()}; return false" styleClass="btNavegacao74" value="Gravar" altKey="catalogo.tradutorErro.Controller.errosCadastros.Alterar" titleKey="catalogo.tradutorErro.Controller.errosCadastros.Alterar"/>
						<html:link styleId="bot_hidden" action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/errosCadastros/abrirPopup.do?tipoPopup=altera" onClick="abrirPopup1(this.href, null , 'popup_alterar_erro_padrao');return false;" />
					</div>
				</html:form>
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
	</netui-temp:section>
</catalogo:popupWidthFull>