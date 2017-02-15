<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupWidthFull>
		<div id="popup_nmComercial_planos" style="width: 100%;">
			<input id="larguraPopup" value="810px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<div id="div_erro_popup_planos"></div>
			<c:set var="nomeBox" scope="application"  value="Localização de Planos"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<br clear="all"/>
			<div id="div_form" style="width: 100%; float: left;">
				<html:form styleId="acaoForm" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/pesquisarLocalizacaoPlanos.do">
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
						<div class="label-form-bold label_required" style="width:100px;">Tipo de Cliente:<font size="1px" color="#EEB422" valign="center">*</div>
							<html:select property="idTipoCliente" styleId="select_tipoCliente" styleClass="required" style="width:130px;" tabindex="3" >
							    <html:option value="">-- Selecione --</html:option>
								<c:forEach var="tipoClienteTO" items="${tipoCliente}">
									<html:option value="${tipoClienteTO.idTipoCliente}">${tipoClienteTO.nmTipoCliente}</html:option>
								</c:forEach>
							</html:select>							
					</div>
					<div id="conteudo_form" style="float: left">
						<div class="label-form-bold label_required" style="width:100px;">Tecnologia:<font size="1px" color="#EEB422" valign="center">*</div>
							<html:select property="idTecnologia" styleId="select_tecnologia" styleClass="required" style="width:130px;" tabindex="2" >
								<html:option value="">-- Selecione --</html:option>
								<c:forEach var="tecnologiaTO" items="${listaTecnologia}">
									<html:option value="${tecnologiaTO.idTecnologia}">${tecnologiaTO.nmTecnologia}</html:option>
								</c:forEach>
							</html:select>							
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:100px;">UF:<font size="1px" color="#EEB422" valign="center">*</div>
							<html:select property="idUf" styleId="select_tipoServico" styleClass="required" style="width:130px;" tabindex="2" >
								<html:option value="">-- Selecione --</html:option>
								<c:forEach var="ufTO" items="${listaUF}">
									<html:option value="${ufTO.idUf}">${ufTO.nmUF}</html:option>
								</c:forEach>
							</html:select>							
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width:100px;">Nome Comercial:</div>
						<html:text property="nmComercialPlano" styleId="nmComercialPlano" size="23" tabindex="5"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width:100px;">Nome T&eacute;cnico:</div>
						<html:text property="nmTecnico" styleId="nmTecnico" size="23" tabindex="6"/>
					</div>
					<br clear="all"/><br clear="all"/>
					
					<div class="barra"></div>
					<div class="botao">
					    <html:hidden property="tpNmComercial" value="${tipoNmComercial}"/>
					    <html:button property="botao_pesquisar_planos" styleId="botao_pesquisar_planos" tabindex="7" onmousedown="$('pagina_solicitada').value='';return true;" onClick="clearAndShow('resultado_pesquisa_popup');send(this, 'resultado_pesquisa_popup', null , 'div_erro_popup_planos', null);" styleClass="btNavegacao74" value="Pesquisar" title="Listar Planos"/>
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