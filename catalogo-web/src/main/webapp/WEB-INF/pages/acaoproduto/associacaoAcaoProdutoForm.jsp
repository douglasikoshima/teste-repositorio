<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
	<jsp:attribute name="headScripts">
		<script>

			function editar(id){	
				document.getElementById("idAcao").value = id
				document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/edit.do#contentForm";
				document.getElementById("acaoForm").submit();
			}

			function clearPage() {
				document.location.href = "/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/AssociacaoAcaoProdutoAction.do";
			}	

			function searchProduto() {
				if (validarFormSearch()) {
					document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/searchProduto.do#contentForm";
					document.getElementById("acaoForm").submit();
				}
			}
			
			function adicionar() {
				if (validarFormAdicionar()) {
					document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/adicionar.do#contentForm";
					document.getElementById("acaoForm").submit();
				}
			}
			
			function carregar() {
				
				document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/carregar.do#contentForm";
				document.getElementById("acaoForm").submit();
			}
			
			function validarFormSearch() {
				var camposErro = "";
				var retorno = true;
				var contador = 0;
				
				if (document.getElementById("idTipoProdutoSearch").value != "" ) {
					contador++;
				}
				
				if (document.getElementById("idFabricanteSearch").value != "" ) {
					contador++;
				}
				
				if (document.getElementById("idModeloSearch").value != "" ) {
					contador++;
				}
				
				if (document.getElementById("idTipoLinhaSearch").value != "" ) {
					contador++;
				}
				
				if (document.getElementById("cdProdutoSearch").value != "" ) {
					contador++;
				}
				
				if (contador < 2) {
					camposErro += "Favor preencher pelo menos dois parâmetros da Pesquisa.<br />";
				}
				
				if (camposErro != "") {
					generateContentErrorForm(camposErro);
					retorno = false;
				}
				
				return retorno;
			}
			
			function init(){
			
			}
			
			function validarFormRemover() {
				var camposErro = "";
				var retorno = true;

				if (!verificarCheckBoxes("acaoForm", "idProdutosAdicionadosCheck", "", "checkAllAdicionados")) {
					camposErro += "Favor selecionar ao menos um registro.<br />";
				}

				if (camposErro != "") {
					generateContentErrorForm(camposErro);
					retorno = false;
				}
				
				return retorno;
			}
			
			function validarFormAdicionar() {
				var camposErro = "";
				var retorno = true;

				if (!verificarCheckBoxes("acaoForm", "idProdutosCheck", "", "checkAll")) {
					camposErro += "Favor selecionar ao menos um registro.<br />";
				}

				if (camposErro != "") {
					generateContentErrorForm(camposErro);
					retorno = false;
				}
				
				return retorno;
			}
			
			function cancel() {
				document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/search.do";
				document.getElementById("acaoForm").submit();
			}
			
			function remove(id) {
				if (validarFormRemover()) {
					document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/remove.do";
					document.getElementById("acaoForm").submit();
				}
			}
			
			function checkAdicionados() {
				setAllCheckBoxes("acaoForm", "idProdutosAdicionadosCheck", "", "checkAllAdicionados");
			}
			
			function checkProdutos() {
				setAllCheckBoxes("acaoForm", "idProdutosCheck", "", "checkAll");
			}
		</script>
	</jsp:attribute>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/search.do" styleId="acaoForm">
			<script>carregaMenu('mn_parametrizacao_matriz_oferta_associacaoproduto');</script>
			<div class="secao_conteudo">
				<catalogo:contentBox title="Associar Produtos à Ação de Vendas">	
					<html:hidden property="idAcao" styleId="idAcao"/>
					<display:table name="acaoList" id="acaoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
						<display:column title="Sigla" sortable="true" headerClass="sortable" style="width: 150px;" >
							<html:link href="javascript:editar(${acaoTO.idAcao})">${acaoTO.sgAcao}</html:link>
						</display:column>
						<display:column title="Nome" sortable="true" headerClass="sortable">
							<html:link href="javascript:editar(${acaoTO.idAcao})" >${acaoTO.nmAcao}</html:link>
						</display:column>
					</display:table>
				</catalogo:contentBox>
				<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
				<c:if test="${labelError != null}">
					<script>
						generateContentErrorForm("${labelError}")
					</script>
				</c:if>
				<c:if test="${cadastrar}">
					<catalogo:contentBox title="Associar Produtos à Ação de Vendas" requiredFields="true">	
						<div class="fleft" style="width:95%; margin-bottom: 5px; margin-left: 5px;"  id="contentForm"> 
							<div class="label-form-bold label_required" style="width: 100px;"><label>A&ccedil;&atilde;o de Venda:<font size="1px" color="#EEB422"></font></label></div>
							${nmAcaoVenda}
						</div>
						
						<div class="fleft" style="width:30%; margin-bottom: 5px; margin-left: 5px;"  id="contentForm"> 
							<div class="label-form-bold label_required" style="width: 100px;"><label for="idTipoProdutoSearch">Tipo Produto:</label></div>
							<html:select property="idTipoProdutoSearch" styleId="idTipoProdutoSearch" styleClass="required" style="width: 140px;" onChange="carregar()">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach items="${tipoProdutoList}" var="tipoProdutoTO">
									<html:option value="${tipoProdutoTO.idTipoProduto}">${tipoProdutoTO.nmTipoProduto}</html:option>
								</c:forEach>
							</html:select>
						</div>
						<div class="fleft" style="width:36%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="width: 110px;"><label for=idFabricanteSearch>Fabricante:</label></div>
							<html:select property="idFabricanteSearch" styleId="idFabricanteSearch" styleClass="required" style="width: 180px;" onChange="carregar()">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach items="${fabricanteList}" var="fabricanteTO" >
									<html:option value="${fabricanteTO.idFabricante}">${fabricanteTO.nmFabricante}</html:option>
								</c:forEach>
							</html:select>
						</div>
						<div class="fleft" style="width: 31%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="width: 60px;"><label for="idModeloSearch">Modelo:</label></div>
							<html:select property="idModeloSearch" styleId="idModeloSearch" styleClass="required" style="width: 180px;">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach items="${grupoProdutoList}" var="grupoProdutoTO">
									<html:option value="${grupoProdutoTO.idGrupoProduto}">${grupoProdutoTO.nmGrupoProduto}</html:option>
								</c:forEach>
							</html:select>
						</div>
						<div class="fleft" style="width:30%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="width: 100px;"><label for="idTipoLinhaSearch">Tipo Linha:</label></div>
							<html:select property="idTipoLinhaSearch" styleId="idTipoLinhaSearch" styleClass="required" style="width: 140px;">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach items="${tipoLinhaList}" var="tipoLinhaTO">
									<html:option value="${tipoLinhaTO.idTipoLinha}">${tipoLinhaTO.dscTipoLinha}</html:option>
								</c:forEach>
							
							</html:select>
						</div>
						<div class="fleft" style="width:36%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 110px;"><label for="cdProdutoSearch">C&oacute;digo Produto:</label></div>
							<html:text property="cdProdutoSearch" styleId="cdProdutoSearch" style="width:180px;" maxlength="254"/>
						</div>
						<div class="fleft" style="width: 31%; margin-bottom: 5px; margin-left: 10px;"> 
							<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="searchProduto()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
						</div>
						<c:if test="${produtoList != null}">
							<br clear="all"/><br clear="all"/>
							<div class="label-form-bold label_required" style="width: 80px;">SELECION&Aacute;VEIS:</div>
							<br clear="all"/>
							<div class="barra"></div>
								<display:table name="produtoList" id="produtoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
									<display:column title="<input type='checkbox' name='checkAll' id='checkAll' onclick='checkProdutos()' />" sortable="true" headerClass="sortable" style="width:10px;">
										<html:checkbox property="idProdutosCheck" styleId="idProdutosCheck" value="${produtoTO.idProduto}|${produtoTO.idPlataforma}|${produtoTO.idOrganizacaoVenda}" />
									</display:column>
									<display:column title="Código Produto" sortable="true" headerClass="sortable" property="cdProduto" />
									<display:column title="Nome Produto" sortable="true" headerClass="sortable" property="nmProduto" />
									<display:column title="Nome Modelo" sortable="true" headerClass="sortable" property="nmModelo" />
									<display:column title="Fabricante" sortable="true" headerClass="sortable" property="nmFabricante" />
									<display:column title="Tipo Produto" sortable="true" headerClass="sortable" property="nmTipoProduto" />
									<display:column title="Tipo Linha" sortable="true" headerClass="sortable" property="nmTipoLinha" />
									<display:column title="Plataforma" sortable="true" headerClass="sortable" property="nmPlataforma" />
									<display:column title="Org. Vendas" sortable="true" headerClass="sortable" property="nmOrganizacaoVenda" style="text-align:center;" />
								</display:table> 
							<cata:temPermissao acao="adicionarAssociaProdutoAcao">
								<div class="botao">
									<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="adicionar()" styleClass="btNavegacao74" value="Adicionar" alt="Adicionar" title=""/><span>&nbsp;</span>
								</div>
							</cata:temPermissao>
						</c:if>
						
						<c:if test="${produtosAdicionadosList != null}">
							<br clear="all"/><br clear="all"/>
							<div class="label-form-bold label_required" style="width: 80px;">SELECIONADOS:</div>
							<br clear="all"/>
							<div class="barra"></div>
								<display:table name="produtosAdicionadosList" id="produtosAdicionadosTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
									<display:column title="<input type='checkbox' name='checkAllAdicionados' id='checkAllAdicionados' onclick='checkAdicionados()' />" sortable="true" headerClass="sortable" style="width:10px;">
										<html:checkbox property="idProdutosAdicionadosCheck" styleId="idProdutosAdicionadosCheck" value="${produtosAdicionadosTO.idProduto}|${produtosAdicionadosTO.idPlataforma}|${produtosAdicionadosTO.idOrganizacaoVenda}"/>
									</display:column>
									<display:column title="Código Produto" sortable="true" headerClass="sortable" property="cdProduto" />
									<display:column title="Nome Produto" sortable="true" headerClass="sortable" property="nmProduto" />
									<display:column title="Nome Modelo" sortable="true" headerClass="sortable" property="nmModelo" />
									<display:column title="Fabricante" sortable="true" headerClass="sortable" property="nmFabricante" />
									<display:column title="Tipo Produto" sortable="true" headerClass="sortable" property="nmTipoProduto" />
									<display:column title="Tipo Linha" sortable="true" headerClass="sortable" property="nmTipoLinha" />
									<display:column title="Plataforma" sortable="true" headerClass="sortable" property="nmPlataforma" />
									<display:column title="Org. Vendas" sortable="true" headerClass="sortable" property="nmOrganizacaoVenda" style="text-align:center;" />
								</display:table>
							<cata:temPermissao acao="removerAssociaProdutoAcao">
								<div class="botao">
									<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="remove()" styleClass="btNavegacao74" value="Remover" alt="Remover" title=""/><span>&nbsp;</span>
								</div>
							</cata:temPermissao>
						</c:if>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form"onClick="cancel()" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title=""/><span>&nbsp;</span>
						</div>
					</catalogo:contentBox>
				</c:if>
			</div>
			</html:form>
</catalogo:defaultTemplate>