<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupConfirmacao>
<div name="conteudo">

<c:if test="${qtdeServico == 0}">
			<img src="/catalogo/static_server/img/transparent.gif" onload="$('botao_sim').onclick();"/><br/>
		</c:if>
		<c:if test="${qtdeServico != 0}">
			<input id="larguraPopup" value="400" class="hide"/>
			<input id="alturaPopup" value="150" class="hide"/>
			<br clear="all"/><br clear="all"/><br clear="all"/>
			O Grupo de Serviços está vinculada a <label class="lblForm">(${servicosViculados})</label> Serviços.
			<br clear="all"/>
			Todos esses Serviços serão afetados com a alteração.		
			<br clear="all"/>
			<br clear="all"/>
			
			Você tem certeza de que deseja realizar essa alteração?
		</c:if>
			<br clear="all"/>
			<br clear="all"/>
			<form>
			<div style="width:70%;">
			<html:button property="bt_sim" styleId="botao_sim" onclick="if(checkRequired($(this).form, 'div_erros_popup_confirmacao')){return false;};fecharPopup('popup2');$('botao_alterar_valor_categoria').onclick();" styleClass="btNavegacao74" value="Sim" bundle="messages" titleKey="catalogo.confirmarAlteracao.categoria"/>
			&nbsp;
			<html:button property="bt_nao" onclick="fecharPopup('popup2');setEditando();" styleClass="btNavegacao74" value="Não" bundle="messages" titleKey="catalogo.cancelarAlteracao.categoria"/>
			</form>
			<br/><br/>

</div>
</catalogo:popupConfirmacao>
