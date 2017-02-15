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
		<script type="text/javascript" src="/catalogo/static_server/js/categorizacaoAnaliseCredito.js"></script>
	</jsp:attribute>

	<jsp:body>
		<html:form styleId="categorizacaoAnaliseCreditoForm" action="/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/categorizacao/search.do">
		<div class="breadcrumb"> Parametriza&ccedil;&atilde;o > An&aacute;lise de Cr&eacute;dito > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/categorizacao/CategorizacaoAnaliseCreditoAction.do';" style="cursor: pointer;">Categoriza&ccedil;&atilde;o Plano/Servi&ccedil;o/Oferta Complementar</a><span><span></div>
			<script>carregaMenu('mn_parametrizacao_analise_categorizacao');</script>
			<div class="secao_conteudo">
				<c:if test="${labelError != null}">
					<script>
						generateContentError("${labelError}")
					</script>
				</c:if>
				<catalogo:contentBox title="Categoriza&ccedil;&atilde;o Plano/Servi&ccedil;o/Oferta Complementar" doubt="true">
					<html:hidden styleId="idAnaliseCredito" property="idAnaliseCredito" />
					<html:hidden styleId="tpPesquisa" property="tpPesquisa" />
					<div class="fleft" style="width:50%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="width: 100px;"><label>Tipo de Pesquisa:</label></div>
																		
							<label> <html:radio property="optPesquisa" styleId="optPesquisa" value="P" onclick="begin();" styleClass="semBorda checkbox" />&nbsp;Plano</label>
							<label> <html:radio property="optPesquisa" styleId="optPesquisa" value="S" onclick="begin();" styleClass="semBorda checkbox" />&nbsp;Serviço</label>
							<label> <html:radio property="optPesquisa" styleId="optPesquisa" value="O" onclick="begin();" styleClass="semBorda checkbox" />&nbsp;Oferta Complementar</label>
							<label> <html:radio property="optPesquisa" styleId="optPesquisa" value="F" onclick="begin();" styleClass="semBorda checkbox" />&nbsp;Servi&ccedil;o Fixa</label>					
					
					</div>
					<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;  clear: both;"> 
						<div class="label-form-bold label_required" style="width: 100px;"><label for="nome">Nome:</label></div>
						<html:text styleId="nome" property="nome" style="width:200px;" styleClass="required" maxlength="254" />
					</div>
					
					<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px; clear: both;"> 
						<div class="label-form-bold label_required" style="width: 100px;"><label for="idCategoriaScoreSearch">Categoria:</label></div>
													                    
	                    <html:select property="idCategoriaScoreSearch" styleId="idCategoriaScoreSearch" style="width:200px;" styleClass="required">
                                <html:option value="">--Selecione--</html:option>
                                <c:forEach var="categoriaScoreTO" items="${categoriaScoreList}">
                                    <html:option value="${categoriaScoreTO.idCategoriaScore}">${categoriaScoreTO.nmCategoriaScore}</html:option>
                                </c:forEach>
                        </html:select>	
	                    
					</div>
					
					<div id="contentPlataforma" >
					<div class="fleft" style="width:37%; margin-bottom: 5px; margin-left: 5px;">
						<c:if test="${!(tipoPesquisa == 'O' || tipoPesquisa == 'F')}"> 
						<div class="label-form-bold label_required" style="width: 100px;"><label for="idPlataformas">Plataforma:</label></div>
												
	                    <html:select property="idPlataformas" styleId="idPlataformas" style="width:200px; height: 55px;" styleClass="required" multiple="true">
                                <c:forEach var="plataformaTO" items="${plataformaList}">
                                    <html:option value="${plataformaTO.idPlataforma}">${plataformaTO.nmPlataforma}</html:option>
                                </c:forEach>
                        </html:select>
	                	</c:if>    
					</div>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
						<span>&nbsp;</span>	
					    <html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="clearPage()" styleClass="btNavegacao74" value="Limpar" alt="Limpar" title=""/>					    				    
					</div>
				</catalogo:contentBox>
				<script>
					esconderPlataforma('${optPesquisa}')
				</script>
				<c:if test="${categorizacaoAnaliseCreditoList != null}">
					<catalogo:contentBox title="Resultado da Pesquisa">
																	
						<display:table name="categorizacaoAnaliseCreditoList" id="categorizacaoAnaliseCreditoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="<input type='checkbox' name='checkAll' id='checkAll' onclick='setAllCheckBoxes()' style='border:none'/>" sortable="true" headerClass="sortable" style="width:10px;">
								<html:checkbox property="checkRecord" styleId="checkRecord" value="${categorizacaoAnaliseCreditoTO.id}" styleClass="semBorda belongsToForm checkbox" />
							</display:column>
							<display:column property="nome"  title="Nome" sortable="true" headerClass="sortable" />
							<c:if test="${!(tipoPesquisa == 'O' || tipoPesquisa == 'F')}">
								<display:column property="nmPlataforma"  title="Plataforma" sortable="true" headerClass="sortable" style="width:150px;" />
							</c:if>
							<display:column property="nmCategoria"  title="Categoria" sortable="true" headerClass="sortable" style="width:150px;"/>
						</display:table>
						
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<cata:temPermissao acao="desassociarCategorizarFinanceiro">
								<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="desassociar()" styleClass="btNavegacao74" value="Desassociar" alt="Desassociar" title=""/>
							</cata:temPermissao>
							<span>&nbsp;</span>
						    <cata:temPermissao acao="salvarCategorizarFinanceiro">
						    	<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="save()" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/>					    				    
							    <div class="fright" style="margin-right: 10px;"> 
									<div class="label-form-bold label_required" style="width: 60px;"><label for="idCategoriaScore">Categoria:</label></div>
																						                    
				                <html:select property="idCategoriaScore" styleId="idCategoriaScore" style="width:200px;" styleClass="required">
	                                <html:option value="">--Selecione--</html:option>
	                                <c:forEach var="categoriaScoreTO" items="${categoriaScoreList}">
	                                    <html:option value="${categoriaScoreTO.idCategoriaScore}">${categoriaScoreTO.nmCategoriaScore}</html:option>
	                                </c:forEach>
                        		</html:select>
				                    
								</div>
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</c:if>
			</div>
		</html:form>
	</jsp:body>
</catalogo:defaultTemplate>