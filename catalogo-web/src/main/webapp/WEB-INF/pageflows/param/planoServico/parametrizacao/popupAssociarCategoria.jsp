<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<catalogo:popupPadrao>
<div id="conteudo_associacao_categoria">
	<input id="larguraPopup" value="450" class="hide"/>
	<input id="alturaPopup" value="150" class="hide"/>
	
	<div id="popup_associar_categoria" style="position:relative;">
		<c:set var="nomeBox" scope="application" value="Associar Novo Grupo de Serviços"/>
		<jsp:include page="/templates/box_pre.jsp"/>
				<br clear="all" /><br clear="all" />
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/associarNovaCategoria.do">
					<div class="label-form-bold label_required" style="width: 120px;">Grupo de Serviços:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:select property="idCategoria"  tabindex="1" style="width:150px;" styleClass="required" styleId="select_associar_categoria">
						<html:option value="">-- Selecione --</html:option>
						<c:forEach var="listCategoriaTO" items="${listCategoria}">
							<html:option value="${listCategoriaTO.idCategoria}"> ${listCategoriaTO.nmCategoria}</html:option>
						</c:forEach>
					</html:select>
					<html:button property="bt_gravar" tabindex="2" styleId="botao_gravar_categoria" value="Gravar" onClick="if(send(this, 'div_associar_nova_categoria', null, 'conteudo_associacao_categoria', null, null)){fecharPopup('popup1')};return false;" styleClass="btNavegacao74" />
					</html:form>
		<jsp:include page="/templates/box_pos.jsp"/>
	</div>
	
</div>
</catalogo:popupPadrao>
