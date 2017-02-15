<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	
<catalogo:popupWidthFull>
<fmt:bundle basename="catalogoprs_messages" >
	<div id="popup_nmcomercial_servico_perfil" style="width: 100%;">
		<html:text property="larguraPopup" styleId="larguraPopup" value="810px" styleClass="hide"/>
		<html:text property="alturaPopup" styleId="alturaPopup" value="auto" styleClass="hide"/>			
		<div id="div_erro_popup_nmcomercial_servico_perfil"></div>
		<c:set var="nomeBox" scope="application"  value="Nome Comercial do Serviço" />
		<jsp:include page="/templates/box_pre.jsp"/>
		
			<br clear="all"/>
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/pesquisarNmComercialNoPopupServicoPerfil">
				<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>				
				<div class="legendaObrigatorio help" style="position:absolute; top:2px; right:6px;">(*) Campo Obrigat&oacute;rio</div>
	            <div class="link_manual" title="Dúvida">
	            	<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
	            	<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank" >
						<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
					</html:link>
				</div>
				<div style="height:10px;"></div>
				
				<div class="fleft">
					<div class="label-form-bold label_required" style="width: 100px;">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select tabindex="1" styleClass="required" property="idPlataforma" styleId="select_plataforma"  style="width:150px;">
							<html:option value="">-- Selecione --</html:option>
								<c:forEach var="plataformaTO" items="${plataformas}">
									<html:option value="${plataformaTO.idPlataforma}">${plataformaTO.nmPlataforma}</html:option>
								</c:forEach>
						</html:select>
				</div>
				
				<div class="fleft">
					<div class="label-form-bold" style="width: 100px;">Grupo Serviços:</div>
					<html:select tabindex="2" property="idCategoria" styleId="select_categoria" style="width:144px;" >
						<html:option value="">-- Selecione --</html:option>
						<c:forEach var="categoriaTO" items="${categorias}">
							<html:option value="${categoriaTO.idCategoria}">${categoriaTO.nmCategoria}</html:option>
						</c:forEach>
					</html:select>
				</div>
				
				<div class="fleft">
					<div class="label-form-bold" style="width: 100px;">Tipo de Servi&ccedil;o:</div>
					<html:select tabindex="3" property="idTipoServico" styleId="select_tipo_servico" style="width:154px;" >
						<html:option value="">-- Selecione --</html:option>
						<c:forEach var="tipoServicoTO" items="${tipoServico}">
							<html:option value="${tipoServicoTO.idTipoServico}">${tipoServicoTO.dscTipoServico}</html:option>
						</c:forEach>					
					</html:select>
				</div>
				
				<br clear="all"/><br clear="all"/>
				<div class="fleft">
					<div class="label-form-bold" style="width: 100px;">Nome Comercial:</div>
					<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome Comercial".</div>
					<div class="hide min_size_required_value">3</div>
					<html:text tabindex="4" size="27" property="nmComServicoPerfilPop" styleId="nmComServicoPerfilPop" styleClass="min_size_required"/>
				</div>
				
				<div class="fleft">
					<div class="label-form-bold" style="width: 100px;">Nome Técnico:</div>
					<html:text tabindex="5" size="26" property="nmTecnico" styleId="nmTecnico"/>
				</div>
				
				<br clear="all"/><br clear="all"/>
				<div class="barra"></div>
				<div class="botao">
					<html:button bundle="messages" property="btn_limpar" styleId="btn_limpar" tabindex="7" onClick="$('resultado_pesquisa_popup').hide();limparForm(this);return false;" value="Limpar" styleClass="btNavegacao74" altKey="catalogo.global.Limpar" titleKey="catalogo.global.Limpar" />
					<span>&nbsp;</span>
					<html:button bundle="messages" tabindex="6" property="botao_pesquisar_nome_comercial_plano_popup" styleId="botao_pesquisar_nome_comercial_plano_popup" onMouseDown="$('pagina_solicitada').value='';return true;" onClick="clearAndShow('resultado_pesquisa_popup');send(this, 'resultado_pesquisa_popup', null , 'div_erro_popup_nmcomercial_servico_perfil', null);" styleClass="btNavegacao74" value="Pesquisar" altKey="catalogo.paramProdutos.Pesquisar" titleKey="catalogo.paramProdutos.Pesquisar"/>					
				</div>
			</html:form>
			
			<div class="barra"></div>
			<br clear="all">
			<div style="display: block; width: 100%; height: 300px;">
				<div id="resultado_pesquisa_popup" style="display: none;"></div>
			</div>
			
		<jsp:include page="/templates/box_pos.jsp"/>
	</fmt:bundle>
</catalogo:popupWidthFull>

