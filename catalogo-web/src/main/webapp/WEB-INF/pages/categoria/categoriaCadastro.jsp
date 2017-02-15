<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
	<jsp:attribute name="headScripts">
		<script type="text/javascript" src="/catalogo/static_server/js/categoriaCadastro.js"></script>
	</jsp:attribute>
	
	<jsp:body>
		<html:form styleId="categoriaForm" action="/br/com/vivo/catalogoPRS/pageflows/param/categoria/search.do">
		<div class="breadcrumb"> Parametriza&ccedil;&atilde;o > An&aacute;lise de Cr&eacute;dito > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/CategoriaScoreAction.do';" style="cursor: pointer;">Categoria</a><span><span></div>
			<script>carregaMenu('mn_parametrizacao_analise_cadastro_categoria');</script>
			<html:hidden styleId="idCategoriaScore" property="idCategoriaScore" />
			<html:hidden styleId="possuiAssociacao" property="possuiAssociacao" value="${pageInput.possuiAssociacao}"/>
			<html:hidden styleId="cadastrar" property="cadastrar" />

				<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
				<c:if test="${labelError != null}">
					<script>
						generateContentErrorForm("${labelError}")
					</script>
				</c:if>

				<catalogo:contentBox title="Categoria" doubt="true">

						<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;" >
							<label for="nmClassificacaoCategoriaScoreSearch" style="text-align: right; width: 82px;">Classificação:</label>
							
                            <html:select property="idClassificacaoCategoriaScoreSearch" styleId="idClassificacaoCategoriaScoreSearch" style="width:200px;" styleClass="required">
                                <html:option value="">--Selecione--</html:option>
                                <c:forEach var="classificacaoCategoriaScoreTO" items="${classificacaoCategoriaScoreList}">
                                    <html:option value="${classificacaoCategoriaScoreTO.idClassificacaoCategoriaScore}">${classificacaoCategoriaScoreTO.nmClassificacaoCategoriaScore}</html:option>
                                </c:forEach>
                            </html:select>							
						</div>
						<div class="fleft" style="width:45%; margin-bottom: 5px;">
							<label for="nmCategoriaScoreSearch" style="text-align: right; width: 82px;">Categoria:</label>
							<html:text property="nmCategoriaScoreSearch" styleId="nmCategoriaScoreSearch" style="width: 200px;"/>
						</div>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<cata:temPermissao acao="novoCategoria">
								<html:button property="botao_novo_form" styleId="botao_novo_form" onClick="create()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/>
								<span>&nbsp;</span>
							</cata:temPermissao>
							<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
							<span>&nbsp;</span>
							<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="clearPage()" styleClass="btNavegacao74" value="Limpar" alt="Limpar" title=""/>
						</div>

				</catalogo:contentBox>

				<catalogo:contentBox title="Resultado da Pesquisa" >
					<display:table name="categoriaScoreTOList" id="categoriaScoreTable" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
						<display:column title="Categoria"  style="text-align: center;" sortable="true" headerClass="sortable" >
							<cata:temPermissao acao="alterarCategoria">
								<a href="javascript:editar('${categoriaScoreTable.idCategoriaScore}')" >${categoriaScoreTable.nmCategoriaScore}</a>
							</cata:temPermissao>
						</display:column>
						<display:column property="classificacaoCategoriaScoreTO.nmClassificacaoCategoriaScore" title="Classificação" sortable="true" headerClass="sortable"/>
						<display:column property="cdCategoriaScore" title="Código" sortable="true" headerClass="sortable" />
						<display:column title="Excluir" style="text-align:center; width:40px;">
							<cata:temPermissao acao="excluirCategoria">
								<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="remove(${categoriaScoreTable.idCategoriaScore},${categoriaScoreTable.possuiAssociacaoPlano},${categoriaScoreTable.possuiAssociacaoOfertaServico},${categoriaScoreTable.possuiAssociacaoServico}, ${categoriaScoreTable.possuiAssociacaoServicoFixa})" style="cursor: pointer;"/>
							</cata:temPermissao>
						</display:column>
						<display:footer />
					</display:table>
				</catalogo:contentBox>
				
				<!-- Create and Edit Categoria Cadastro -->
				<div id="createEditCadastro"></div>
				
		</html:form>
	</jsp:body>
</catalogo:defaultTemplate>