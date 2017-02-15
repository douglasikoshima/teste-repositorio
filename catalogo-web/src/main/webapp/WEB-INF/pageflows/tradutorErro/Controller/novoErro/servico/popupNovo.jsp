<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<catalogo:popupWidthFull>
		<div id="div_erro_popup" style="position: relative;"></div>
		<div id="popup_novo_servico" style="width: 100%;">
			<input id="larguraPopup" value="825px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<c:set var="nomeBox" scope="application"  value="Novo Serviço Enablement"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div id="novo_servico" style="width: 100%; float: left; height: 100px">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/novoEN.do">
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
					<div class="link_manual" title="Dúvida">
						<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
						<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank">
							<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
						</html:link>
					</div>
					<br clear="all" />
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">Sistema Legado:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select tabindex="1" styleClass="required" styleId="select_SL" style="width:190px;" property="servicoVO.sistemaLegado">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="sistemaLegadoTO" items="${sistemaLegado}">
								<html:option value="${sistemaLegadoTO.cdSistemaLegado}">${sistemaLegadoTO.cdSistemaLegado} - ${sistemaLegadoTO.dsSistemaLegado}</html:option>
							</c:forEach>						
						</html:select>						
						<br clear="all" />
					<div class="fleft">
						<div class="label-form-bold" style="width:110px">Codigo do SE</div>
						<html:text tabindex="2" size="35" styleId="cd_servicoAlterado" property="servicoVO.cdServico"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">Descri&ccedil;&atilde;o do SE<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:text styleClass="required" tabindex="3" size="50" styleId="ds_servicoNovo" value="${ds_servico}"  property="servicoVO.dsServico"/>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="botao">
						<html:button bundle="messages" tabindex="4" styleId="bot_novo" property="btn_novo" onClick="if(send(this, null, null, 'div_erro_popup'));return false" styleClass="btNavegacao74" value="Gravar" altKey="catalogo.tradutorErro.Controller.novoErro.sevico.Gravar" titleKey="catalogo.tradutorErro.Controller.novoErro.sevico.Gravar"/>
						<html:link styleId="bot_hidden" action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/abrirPopup.do?tipoPopup=novo" onClick="abrirPopup1(this.href, null , 'popup_novo_servico');return false;"/>
					</div>
				</html:form>
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupWidthFull>