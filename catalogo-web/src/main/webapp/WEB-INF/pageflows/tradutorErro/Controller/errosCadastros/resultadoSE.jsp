<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<netui:form action="pesquisarErros">
		<br clear="all" /><br clear="all" />
		<div class="fleft">
			<div class="label-form-bold" style="width:150px;" style="width:110px;">Servi&ccedil;o Enablement:</div>
			<html:select tabindex="3" styleId="select_se" property="erroComumVO.servico" style="width:200px;">
				<html:option value="">-- Selecione --</html:option>
				<c:forEach var="listaServicoFiltroTO" items="${listaServicoFiltro}">
					<html:option value="${listaServicoFiltroTO.cdServico}">${listaServicoFiltroTO.cdServico} - ${listaServicoFiltroTO.dsServico}</html:option>
				</c:forEach>
			</html:select>
		</div>
		<div class="fleft">
			<div class="label-form-bold" style="width:150px;">Codigo do SN:</div>
			<!-- <netui:textBox tabindex="4" style="width:200px;" tagId="descricao" dataSource="actionForm.erroComumVO.mensagem"/> -->
			<html:text tabindex="4" style="width:200px;" styleId="descricao" property="erroComumVO.mensagem"/>
		</div>
		<br clear="all" /><br clear="all" />
		<div class="barra"></div>
		<div class="botao">
			<html:button bundle="messages" tabindex="6" property="btn_limpar" onClick="clearAndShow('resultado_pesquisa');limparForm(this);limparForm('select_sn');return false;" styleClass="btNavegacao74" value="Limpar" titleKey="catalogo.global.Limpar"/>
			<span>&nbsp;</span>
			<html:button tabindex="5" property="bot_pesquisa" styleId="bot_pesquisa" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null , null);" styleClass="btNavegacao74" value="Pesquisar"/>
			<span>&nbsp;</span>
		</div>
	</netui:form>
</div>