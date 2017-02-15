<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
<div id="conteudo_divErros"></div>
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Servi&ccedil;o Enablement</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco"  />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/pesquisarEN.do">
					<!-- <netui:hidden tagId="pagina_solicitada" dataSource="actionForm.pageNumber"/> -->
					<html:hidden property="pagina_solicitada" styleId="pagina_solicitada"/>
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold" style="width:140px;">Sistema Legado:</div>
						<html:select tabindex="1" styleId="select_SL" style="width:190px;"  property="servicoVO.sistemaLegado">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="sistemaLegadoTO" items="${sistemaLegado}">
								<html:option value="${sistemaLegadoTO.cdSistemaLegado}">${sistemaLegadoTO.cdSistemaLegado} - ${sistemaLegadoTO.dsSistemaLegado}</html:option>
							</c:forEach>
						</html:select>
						
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold" style="width:140px;">Codigo do SE:</div>
						<html:text tabindex="2" size="35" styleId="cd_Servico" property="servicoVO.cdServico"/>						
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width:140px;">Descrição do SE:</div>
						<html:text tabindex="3" size="50" styleId="ds_Servico" property="servicoVO.dsServico"/>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<div class="botao">
						<html:button tabindex="5" bundle="messages" property="btn_limpar" onClick="clearAndShow('resultado_pesquisa');limparForm(this);habilitar('bot_novo');return false;" styleClass="btNavegacao74" value="Limpar" titleKey="catalogo.global.Limpar"/>
						<span>&nbsp;</span>
						<html:button tabindex="4" bundle="messages" property="btn_pesquisar" styleId="bot_pesquisa" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null , null);" styleClass="btNavegacao74" value="Pesquisar" altKey="catalogo.tradutorErro.Controller.novoErro.sevico.Pesquisar" titleKey="catalogo.tradutorErro.Controller.novoErro.sevico.Pesquisar" />
						<span>&nbsp;</span>
						<html:link action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/abrirNovoEN.do"  onClick="abrirPopup1(this.href, null , 'resultado_pesquisa');return false;">
							<html:button tabindex="3" bundle="messages" styleId="bot_novo" property="btn_novo" styleClass="btNavegacao74" value="Novo" altKey="catalogo.tradutorErro.Controller.novoErro.sevico.Novo" titleKey="catalogo.tradutorErro.Controller.novoErro.sevico.Novo"/>
						</html:link>
						
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
<div id="resultado_pesquisa" class="secao_conteudo" style="position: relative;"></div>