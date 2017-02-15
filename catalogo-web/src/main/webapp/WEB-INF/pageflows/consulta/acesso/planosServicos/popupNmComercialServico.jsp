<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupWidthFull>
		<div id="popup_nmComercial_servicos" style="width: 100%;">
			<input id="larguraPopup" value="810px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<div id="div_erro_popup_servicos"></div>
			<c:set var="nomeBox" scope="application"  value="Localização de Serviços"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<br clear="all"/>
			<div id="div_form" style="width: 100%; float: left;">
				<html:form styleId="acaoForm" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/pesquisarLocalizacaoServicos.do">
					<div id="conteudo_form" style="float: left">
						<div class="fleft">
							<div class="label-form-bold label_required" style="width:100px;">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="idPlataforma" styleId="select_plataforma" styleClass="required" style="width:130px;" tabindex="3" >
								<html:option value="">-- Selecione --</html:option>
								<c:forEach var="plataformaTO" items="${plataformas}">
									<html:option value="${plataformaTO.idPlataforma}">${plataformaTO.nmPlataforma}</html:option>
								</c:forEach>
							</html:select>	
						</div>
					</div>
					
					<div id="conteudo_form" style="float: left">
						<div class="label-form-bold label_required" style="width:100px;">Categoria:<font size="1px" color="#EEB422" valign="center">*</div>
							<html:select property="idCategoria" styleId="select_categoria" styleClass="required" style="width:130px;" tabindex="3" >
							    <html:option value="">-- Selecione --</html:option>
								<c:forEach var="categoriaTO" items="${categoria}">
									<html:option value="${categoriaTO.idCategoria}">${categoriaTO.nmCategoria}</html:option>
								</c:forEach>
							</html:select>							
					</div>
					
					<div id="conteudo_form" style="float: left">
						<div class="label-form-bold label_required" style="width:100px;">Tipo de Servi&ccedil;o:<font size="1px" color="#EEB422" valign="center">*</div>
							<html:select property="idTipoServico" styleId="select_tipoServico" styleClass="required" style="width:130px;" tabindex="4" >
							    <html:option value="">-- Selecione --</html:option>
								<c:forEach var="tipoServicoTO" items="${tipoServico}">
									<html:option value="${tipoServicoTO.idTipoServico}">${tipoServicoTO.dscTipoServico}</html:option>
								</c:forEach>
							</html:select>							
					</div>
					<br clear="all"/><br clear="all"/>
					
					<div class="fleft">
						<div class="label-form-bold" style="width:100px;">Nome Comercial:</div>
						<html:text property="nmComercialServico" styleId="nmComercialServico" size="23" tabindex="5"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width:100px;">Nome T&eacute;cnico:</div>
						<html:text property="nmTecnico" styleId="nmTecnico" size="23" tabindex="6"/>
					</div>
					<br clear="all"/><br clear="all"/>
					
					<div class="barra"></div>
					<div class="botao">
					    <html:hidden property="tpNmComercial" value="${tipoNmComercial}"/>
					    <html:button property="botao_pesquisar_localizacao_servicos" styleId="botao_pesquisar_localizacao_servicos" tabindex="7" onMouseDown="$('pagina_solicitada').value='';return true;" onClick="clearAndShow('resultado_pesquisa_popup');send(this, 'resultado_pesquisa_popup', null , 'div_erro_popup_servicos', null);" styleClass="btNavegacao74" value="Pesquisar" title="Listar Planos"/>						
					</div>
				</html:form>
			</div>
			<br clear="all">
			<div class="barra"></div>
			<br clear="all">
			<div style="display: block; width: 100%; height: 400px;">
				<div id="resultado_pesquisa_popup" style="display: none;"></div>
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupWidthFull>