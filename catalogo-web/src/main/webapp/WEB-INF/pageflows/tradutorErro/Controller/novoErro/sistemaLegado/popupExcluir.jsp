<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<catalogo:popupWidthFull>		
		<div id="div_erro_popup" style="position: relative;"></div>
		<div id="popup_excluir_sistema_legado" style="width: 100%;">
			<input id="larguraPopup" value="825px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<c:set var="nomeBox" scope="application"  value="Excluir Sistema Legado"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div id="exclui_sistema_legado" style="width: 100%; float: left; height: 100px" align="center">
				<br clear="all" /><br clear="all" />
				Deseja excluir este Sistema Legado?
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/sistemaLegado/excluirSL.do">
					<br clear="all" /><br clear="all" /><br clear="all" />
	 				<html:hidden property="cdSistemaLegado" styleId="hidden_cd_sistema_legado"/>
	 				<html:button property="btn_excluir" onMouseDown="$('hidden_cd_sistema_legado').value=${cdSistemaLegado};" onclick="if(send(this, null, null, 'div_erro_popup')){ }; return false" styleClass="btNavegacao74" value="Sim"/>
	 				<html:link styleId="bot_hidden" action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/sistemaLegado/abrirPopup.do?tipoPopup=exclui" onClick="abrirPopup1(this.href, null , 'popup_excluir_sistema_legado');return false;">
	 				</html:link>
	 			</html:form>
				&nbsp;
				<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Não" />
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
		<div id="script" class="secao_conteudo" style="position: relative;"></div>
</catalogo:popupWidthFull>