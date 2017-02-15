<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<div class="breadcrumb"> Parametriza&ccedil;&atilde;o > Programa Pontos > <span><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/ParametrizacaoProdutosPPAction.do');" style="cursor: pointer;">Produtos</a><span><span></div>
<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Disponibilidade de Produtos:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>

	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg relative">
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
					<div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html" target="_blank">
							<img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
						</a>
					</div>
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/pesquisarProdutosPP.do" styleId="parametrizacaoProdutosPPForm">
						<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
						<br/>
						<div class="fleft" style="margin-left: 7%;">
							<div class="label-form-bold label_required" style="width:110px; text-align: left;">Tipo de Produto:<font size="1px" color="#EEB422">*</font></div>
							
				            <html:select property="idTipoProduto" tabindex="1" styleClass="required" styleId="select_tipo_produto" style="width:140px; clear:both;"
				            	onchange="setSelectDestino($(this).next('a').href, $F(this), $('select_fabricante'));limpaSelectModeloAndOrgVendas($F(this));return false;">
				            	<html:option value="">-- Selecione --</html:option>
				            	<c:forEach var="tipoProdutoLista" items="${tipoProdutoLista}">
				            		<html:option value="${tipoProdutoLista.idTipoProduto}">${tipoProdutoLista.nmTipoProduto}</html:option>
				            	</c:forEach>
				            </html:select>
				            <html:link action="/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/buscarListaFabricantePorTipoProduto.do" styleClass="display:none;"/>
						</div>
						
						<div class="fleft" style="margin-left: 5%;">
							<div class="label-form-bold label_required" style="width:110px; text-align: left;">Fabricante:<font size="1px" color="#EEB422">*</font></div>
							
				            <html:select property="idFabricante" tabindex="2" styleClass="required" styleId="select_fabricante" style="width:140px; clear:both;"
				            	onchange="setSelectDestino($(this).next('a').href, $F(this), $('select_modelo'));return false;">
				            	<html:option value="">-- Selecione --</html:option>
				            </html:select>
				            <html:link action="/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/buscarListaModeloPorTpProdutoFabricante.do" styleClass="display:none;"/>
						</div>
						
						<div class="fleft" style="margin-left: 5%;">
							<div class="label-form-bold label_required" style="width:110px; text-align: left;">Modelo:<font size="1px" color="#EEB422" >*</font></div>
							
				            <html:select property="idModelo" tabindex="3" styleClass="required" styleId="select_modelo" style="width:140px; clear:both;"
				            	onchange="">
				            	<html:option value="">-- Selecione --</html:option>
				            </html:select>
						</div>
						
						<div class="fleft" style="margin-left: 5%;">
							<div class="label-form-bold label_required" style="width:150px; text-align: left;">Organiza&ccedil;&atilde;o de Vendas:<font size="1px" color="#EEB422" valign="center">*</font></div>
							
				            <html:select property="idOrgVendas" tabindex="4" styleClass="required" styleId="select_orgvendas" style="width:140px; clear:both;">
				            	<html:option value="">-- Selecione --</html:option>
				            	<c:forEach var="orgVendasLista" items="${orgVendasLista}">
				            		<html:option value="${orgVendasLista.idOrganizacaoVendas}">${orgVendasLista.sgOrganizacaoVendas}</html:option>
				            	</c:forEach>
				            </html:select>
						</div>
						
						<br clear="all" />
						<br clear="all" />
						<div class="barra"></div>
						<div class="botao">
							<input tabindex="5" type="button" />
							<html:button property="bt_limpar" tabindex="5" onclick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" styleClass="btNavegacao74" value="Limpar" bundle="messages" altKey="catalogo.global.Limpar" titleKey="catalogo.global.Limpar"/>
							<span>&nbsp;</span>
							<html:button property="botao_pesquisar_produtos_pp" styleId="botao_pesquisar_produtos_pp" tabindex="6" onmousedown="$('pagina_solicitada').value='1';return true;" onclick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section')" styleClass="btNavegacao74" value="Pesquisar" bundle="messages" altKey="catalogo.pp.produtos.Pesquisar" titleKey="catalogo.pp.produtos.Pesquisar"/>
						</div>
					
					</html:form>	
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<div id="resultado_pesquisa" style="position:relative;"></div>