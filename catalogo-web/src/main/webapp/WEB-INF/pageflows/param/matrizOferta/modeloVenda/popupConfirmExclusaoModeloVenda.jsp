<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupConfirmacao.jsp">
	<netui-temp:section name="conteudo">
	<input id="larguraPopup" value="500px" class="hide"/>
	<input id="alturaPopup" value="150px" class="hide"/>
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			<c:choose>
				<c:when test="${prodsAssociado != 0}">
					Não é possível excluir o Modelo de Venda <b>"${cdModeloVenda}"</b>, <br>pois há produtos associados a Esse Modelo de Venda.			
					<br clear="all" /><br clear="all" /><br clear="all" />
					<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="OK" title="${bundle.default['catalogo.popupAlterarModelo.Não']}"/>
				</c:when>
				<c:otherwise>
					Confirme a Exclusão do modelo de venda: <b>${cdModeloVenda}</b>
					<br clear="all" /><br clear="all" /><br clear="all" />
					<netui:form action="deleteModeloVendaMatrizOferta">
						<netui:hidden tagId="hidden_id_modelo_venda" dataSource="actionForm.idModeloVenda" />
						<netui:button type="button" action="deleteModeloVendaMatrizOferta" onMouseDown="$('hidden_id_modelo_venda').value=${idModeloVenda};" onclick="send(this, null, null, 'right_section');fecharPopup('popup1');return false" styleClass="btNavegacao74" value="Sim" title="${bundle.default['catalogo.popupAlterarModelo.Sim']}" />
					</netui:form>
					&nbsp;
					<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Não" title="${bundle.default['catalogo.popupAlterarModelo.Não']}"/>
				</c:otherwise>
			</c:choose>
	</netui-temp:section>
</netui-temp:template>