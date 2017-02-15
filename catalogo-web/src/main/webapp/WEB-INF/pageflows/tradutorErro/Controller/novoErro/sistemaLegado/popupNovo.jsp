<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html" %>

<catalogo:popupWidthFull>
		<div id="div_erro_popup" style="position: relative;"></div>
		<div id="popup_novo_sistema_legado" style="width: 100%;">
			<input id="larguraPopup" value="825px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<c:set var="nomeBox" scope="application"  value="Novo Sistema Legado"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div id="novo_sistema_legado" style="width: 100%; float: left; height: 100px">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/sistemaLegado/novoSL.do">
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
					<div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
					<br clear="all" />
					<div class="fleft">
						<div class="label-form-bold" style="width:150px">Código do SL</div>
						<html:text property="cdSistemaLegado" tabindex="1" size="30" styleId="cd_sistemaLegadoAlterado"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">Descri&ccedil;&atilde;o do SL<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:text property="dsSistemaLegado" styleClass="required" tabindex="2" size="50" styleId="ds_sistemaLegadoNovo" value="${ds_sistema_legado}"/>
					</div>
					<br clear="all" /><br clear="all" />
					<br clear="all" />
					<div class="fleft">
						<div class="label-form-bold" style="width:150px;">Nome da Aplica&ccedil;&atilde;o do SL</div>
						<html:text property="nmAplicacaoHeader" tabindex="3" size="30" styleId="nm_AplicacaoHeader" value="${nm_aplicacao_header}"/>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="botao">
						<html:button property="btn_gravar" tabindex="4" styleId="bot_novo" onClick="if(send(this, null, null, 'div_erro_popup'));return false" styleClass="btNavegacao74" value="Gravar" bundle="messages" altKey="catalogo.tradutorErro.Controller.novoErro.sistemaLegado.Gravar" titleKey="catalogo.tradutorErro.Controller.novoErro.sistemaLegado.Gravar"/>
						<html:link styleId="bot_hidden" action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/sistemaLegado/abrirPopup.do?tipoPopup=novo" onClick="abrirPopup1(this.href, null , 'popup_novo_sistema_legado');return false;">
						</html:link>
					</div>
				</html:form>	
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupWidthFull>
