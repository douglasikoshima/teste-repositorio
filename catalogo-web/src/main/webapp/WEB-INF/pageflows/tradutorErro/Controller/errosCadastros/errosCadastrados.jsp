<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
<div id="conteudo_divErros"></div>
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Erros Cadastrados</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/errosCadastros/pesquisarErros.do">
					<!-- <netui:hidden tagId="pagina_solicitada" dataSource="actionForm.pageNumber"/> -->
					<html:hidden styleId="pagina_solicitada" property="pagina_solicitada"/>
					<br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold" style="width:150px;">Servi&ccedil;o Negocio:</div>
						<html:select tabindex="1" styleId="select_sn" style="width:200px;" property="erroComumVO.servicoNegocio">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="listaNegocioTO" items="${listaNegocio}">
								<html:option value="${listaNegocioTO.cdServicoNegocio}">${listaNegocioTO.cdServicoNegocio} - ${listaNegocioTO.dsServicoNegocio}</html:option>
							</c:forEach>
						
						</html:select>
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width:150px;">Sistema Legado:</div>
						<html:select tabindex="2" styleId="select_sl" style="width:200px;" property="erroComumVO.sistemaLegado">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="listaLegadoTO" items="${listaLegado}">
								<html:option value="${listaLegadoTO.cdSistemaLegado}">${listaLegadoTO.cdSistemaLegado} - ${listaLegadoTO.dsSistemaLegado}</html:option>
							</c:forEach>
						</html:select>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="fleft">
						<div class="label-form-bold" style="width:150px;" style="width:110px;">Servi&ccedil;o Enablement:</div>
						<html:select tabindex="3" styleId="select_se" style="width:200px;" property="erroComumVO.servico">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="listaServicoTO" items="${listaServico}">
								<html:option value="${listaServicoTO.cdServico}">${listaServicoTO.cdServico} - ${listaServicoTO.dsServico}</html:option>
							</c:forEach>						
						</html:select>
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width:150px;">Codigo do Erro Padr&atilde;o:</div>
						<html:text tabindex="4" style="width:200px;" styleId="descricao" property="erroComumVO.cdPadrao"/>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<div class="botao">
						<html:button bundle="messages" tabindex="5" property="btn_limpar" onClick="clearAndShow('resultado_pesquisa');limparForm(this);limparForm('select_sn');return false;" styleClass="btNavegacao74" value="Limpar" titleKey="catalogo.global.Limpar"/>
						<span>&nbsp;</span>
						<html:button bundle="messages" tabindex="4" styleId="bot_pesquisa" property="bot_pesquisa" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null , 'right_section');" styleClass="btNavegacao74" value="Pesquisar" altKey="catalogo.tradutorErro.Controller.errosCadastros.Pesquisar" titleKey="catalogo.tradutorErro.Controller.errosCadastros.Pesquisar"/>
						<span>&nbsp;</span>
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
<div id="resultado_pesquisa" class="secao_conteudo" style="position: relative;"></div>