<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupWidthFull>
	<div id="div_erro_popup" style="position: relative;"></div>
	<div id="popup_excluir_servico" style="width: 100%;">
		<input id="larguraPopup" value="825px" class="hide"/>
		<input id="alturaPopup" value="auto" class="hide"/>
		<c:set var="nomeBox" scope="application"  value="Excluir Serviço Enablement"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div id="exclui_servico" style="width: 100%; float: left; height: 100px" align="center">
			<br clear="all" /><br clear="all" />
			Deseja excluir este Servi&ccedil;o Enablemet?
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/excluirEN.do">
				<br clear="all" /><br clear="all" /><br clear="all" />
					<!-- <netui:hidden tagId="hidden_cd_servico" dataSource="actionForm.servicoVO.cdServico" /> -->
					<html:hidden property="servicoVO.cdServico" styleId="hidden_cd_servico"/>
					<html:button property="btn_sim" onMouseDown="$('hidden_cd_servico').value=${cdServico};" onclick="if(send(this, null, null, 'div_erro_popup')); return false" styleClass="btNavegacao74" value="Sim" />
					<html:link styleId="bot_hidden" action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/abrirPopup.do?tipoPopup=exclui" onClick="abrirPopup1(this.href, null , 'popup_excluir_servico');return false;"/>
			</html:form>
			&nbsp;
			<html:button property="btn_fechar" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Não"/>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
	</div>
	<div id="script" class="secao_conteudo" style="position: relative;"></div>
</catalogo:popupWidthFull>