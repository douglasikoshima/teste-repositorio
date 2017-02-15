<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupConfirmacao>
		<c:if test="${qtdeServico != 0}">
			<input id="larguraPopup" value="400" class="hide"/>
			<input id="alturaPopup" value="150" class="hide"/>
			<br clear="all"/><br clear="all"/><br clear="all"/>
			Não é possível excluir o Grupo de Serviços,
			<br clear="all"/>
			pois existe um ou mais Serviços vinculados.
			<br clear="all"/><br clear="all"/>
			<input type="button" onclick="fecharPopup('popup2');setEditando();" class="btNavegacao74" value="OK" title="Ok"/>
		</c:if>
			<br clear="all"/>
		<c:if test="${qtdeServico == 0}">
			<input id="larguraPopup" value="400" class="hide"/>
			<input id="alturaPopup" value="150" class="hide"/>
			<br clear="all"/><br clear="all"/>
			Você tem certeza de que deseja excluir o Grupo de Serviços? <label class="lblForm">(${nmCategoria})</label>
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/deletarCategoria.do?id_categoria=${idCategoria}">
				<div style="width:70%;">
				<br clear="all"/><br clear="all"/>
				<html:button property="bt_nao" onclick="send(this, null , 'popup2', 'popup_valores_categoria', null, 'div_erros_popup_confirmacao');" styleClass="btNavegacao74" value="Sim" bundle="messages" titleKey="catalogo.confirmarExclusao.categoria"/>
			</html:form>
			&nbsp;
			<html:button property="bt_nao" onclick="fecharPopup('popup2');setEditando();" styleClass="btNavegacao74" value="Não" bundle="messages" titleKey="catalogo.cancelarExclusao.categoria"/>
		</c:if>
			<br/><br/>
</catalogo:popupConfirmacao>
