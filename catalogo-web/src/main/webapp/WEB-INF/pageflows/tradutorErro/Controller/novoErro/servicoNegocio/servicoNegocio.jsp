<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<div class="secao_conteudo">
<div id="conteudo_divErros"></div>
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Servi&ccedil;o de Negocio</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/pesquisarSN.do">
					<html:hidden styleId="pagina_solicitada" property="pageNumber"/>
					<br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold" style="width:140px;">Codigo do SN:</div>
						<html:text tabindex="1" size="35" styleId="cd_ServicoNegocio" property="cdServicoNegocio"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold" style="width:140px;">Descri&ccedil;&atilde;o do SN:</div>
						<html:text tabindex="2" size="50" styleId="ds_ServicoNegocio" property="dsServicoNegocio"/>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<div class="botao">
						<html:button property="bt_limpar" tabindex="5" onClick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" styleClass="btNavegacao74" value="Limpar" bundle="messages" titleKey="catalogo.global.Limpar"/>
						<span>&nbsp;</span>
						<html:button tabindex="4" property="bot_pesquisa" styleId="bot_pesquisa" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null , null);" styleClass="btNavegacao74" value="Pesquisar" bundle="messages" altKey="catalogo.tradutorErro.Controller.novoErro.sevicoNegocio.Pesquisar" titleKey="catalogo.tradutorErro.Controller.novoErro.sevicoNegocio.Pesquisar"/>
						<span>&nbsp;</span>
						<html:link action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/abrirNovoSN.do" onClick="abrirPopup1(this.href, null , 'resultado_pesquisa');return false;">
							<html:button tabindex="3" property="bot_novo" styleId="bot_novo" styleClass="btNavegacao74" value="Novo" bundle="messages" altKey="catalogo.tradutorErro.Controller.novoErro.sevicoNegocio.Novo" titleKey="catalogo.tradutorErro.Controller.novoErro.sevicoNegocio.Novo"/>
						</html:link>
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
<div id="resultado_pesquisa" class="secao_conteudo" style="position: relative;"></div>