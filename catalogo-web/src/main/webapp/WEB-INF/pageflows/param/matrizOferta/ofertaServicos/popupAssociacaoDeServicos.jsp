<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupWidthFull>
		<div id="popup_nova_associacao_servicos" style="width: 100%;">
			<input id="larguraPopup" value="810px" class="hide"/>
			<input id="alturaPopup" value="auto" class="hide"/>
			<div id="div_erro_popup_servicos"></div>
			<c:set var="nomeBox" scope="application" value="Associação de Serviços" />
			<jsp:include page="/templates/box_pre.jsp"/>
			<br clear="all"/><br clear="all"/>
			<div id="div_form" style="width: 100%; float: left;">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/pesquisarServicosDisponiveisParaAssociacao.do">
				<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
				<html:hidden property="idOfertaServico" styleId="idOfertaServico" value="${idOfertaServico}"/>
					<div id="conteudo_form" style="float: left">
						<div class="fleft">
							<div class="label-form-bold label_required" style="width:100px;">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
                            <html:select property="idPlataforma" styleId="select_plataforma" tabindex="3" styleClass="required" style="width:130px;">
                                <html:option value="">--Selecione--</html:option>
                                <c:forEach var="plataformaTO" items="${plataformas}">
                                    <html:option value="${plataformaTO.idPlataforma}">${plataformaTO.nmPlataforma}</html:option>
                                </c:forEach>
                            </html:select>							
						</div>
					</div>
					<div id="conteudo_form" style="float: left">
						<div class="label-form-bold label_required" style="width:100px;">Tecnologia:</div>
                            <html:select property="idTecnologia" styleId="select_tecnologia" tabindex="2" style="width:130px;">
                                <html:option value="">--Selecione--</html:option>
                                <c:forEach var="tecnologiaTO" items="${listaTecnologia}">
                                    <html:option value="${tecnologiaTO.idTecnologia}">${tecnologiaTO.nmTecnologia}</html:option>
                                </c:forEach>
                            </html:select>						
					</div>
					<div id="conteudo_form" style="float: left">
						<div class="label-form-bold label_required" style="width: 120px;">Grupo de Serviços:</div>
                            <html:select property="idCategoria" styleId="select_categoria" tabindex="3" style="width:130px;">
                                <html:option value="">--Selecione--</html:option>
                                <c:forEach var="categoriaTO" items="${categoria}">
                                    <html:option value="${categoriaTO.idCategoria}">${categoriaTO.nmCategoria}</html:option>
                                </c:forEach>
                            </html:select>								
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:100px;">Tipo de Servi&ccedil;o:</div>
                            <html:select property="idTipoServico" styleId="select_tipoServico" tabindex="4" style="width:130px;">
                                <html:option value="">--Selecione--</html:option>
                                <c:forEach var="tipoServicoTO" items="${tipoServico}">
                                    <html:option value="${tipoServicoTO.idTipoServico}">${tipoServicoTO.dscTipoServico}</html:option>
                                </c:forEach>
                            </html:select>							
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width:100px;">Nome Comercial:</div>
						<html:text property="nmServico" styleId="nmServico" tabindex="5" size="23"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width: 120px;">Nome T&eacute;cnico:</div>
						<html:text property="cdServico" styleId="cdServico" tabindex="6" size="23"/>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_pesquisar_servicos_sem_associasao" styleId="botao_pesquisar_servicos_sem_associasao" tabindex="7" onMouseDown="$('pagina_solicitada').value='';return true;" onClick="clearAndShow('resultado_pesquisa_popup');send(this, 'resultado_pesquisa_popup', null , 'div_erro_popup_servicos', null);" styleClass="btNavegacao74" value="Pesquisar" title="Listar Serviços" alt="Listar Serviços"/>
					</div>
				</html:form>
			</div>
			<br clear="all">
			<div class="barra"></div>
			<br clear="all">
			<div style="display: block; width: 100%; height: 300px;">
				<div id="resultado_pesquisa_popup" style="display: none;"></div>
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupWidthFull>