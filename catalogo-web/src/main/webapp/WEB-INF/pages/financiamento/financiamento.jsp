<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:defaultTemplate titulo="Home Catalogo">
   <jsp:attribute name="headScripts">
		<script type="text/javascript" src="/catalogo/static_server/js/financiamento.js"></script>
	</jsp:attribute>
	<jsp:body>
		<div class="breadcrumb"> Parametrização > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/financiamento/CadastroFinanciamentoAction.do';" style="cursor: pointer;">Financiamentos</a><span></div>
		<script>carregaMenu('mn_parametrizacao_cadastro_financiamento');</script>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/financiamento/search.do" styleId="acaoForm" onsubmit="false">
			<div class="secao_conteudo">
				<catalogo:contentBox title="Pesquisa" requiredFields="false">
					<!--1 linha com as labels de seus respectivos inputs -->
					<div class="line">
						<div class="linerow bold label_required"><label for="codigoPlanoFinanciamento">Cod. Plano de Financiamento:</label></div>
						<div class="linerow bold label_required"><label for="nome">Nome do Plano:</label></div>
						<div class="linerow bold label_required"><label for="qtdeParcela">Quantidade de Parcelas:</label></div>
						<div class="linerow bold label_required"><label for="taxaJuros">Juros:</label></div>
					</div>
					<div class="line">
						<div class="linerow"><html:text property="codigoPlanoFinanciamento" styleId="codigoPlanoFinanciamento"/></div>
						<div class="linerow"><html:text property="nome" styleId="nome"/></div>
						<div class="linerow"><html:text property="qtdeParcela" styleId="qtdeParcela"/></div>
						<div class="linerow"><html:text property="taxaJuros" styleId="taxaJuros"/></div>
					</div>
					<!-- final 1 linha com as labels de seus respectivos inputs -->
				
					<!-- 2 linha  linha com as labels de seus respectivos inputs -->
					<div class="line">
						<div class="linerow bold"><label for="origem">Origem:</label></div>
						<div class="linerow bold"><label for="disponibilidade">Disponibilidade:</label></div>						
					</div>
					<div class="line">
						<div class="linerow">
							<html:select styleClass="required" styleId="origem" property="origem">
								<html:option value="">Ambos</html:option>
			                    <html:option value="catalogo">Catálogo</html:option>
			                    <html:option value="legado">Legado</html:option>
		                    </html:select>
						</div>
						<div class="linerow">
							<html:select styleClass="required" styleId="disponibilidade" property="disponibilidade" >
								<html:option value="sim">Sim</html:option>
			                    <html:option value="nao">Não</html:option>
			                    <html:option value="">Ambos</html:option>
		                    </html:select>
						</div>
					</div>
					<!--Final 2 linha com as labels de seus respectivos inputs -->
	
					<!-- botões pesquisar e limpar campos -->
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
						<cata:temPermissao acao="novoFinanciamento">
							<html:button property="botao_cancelar_form" styleId="botao_cancelar_form" onClick="novosRegistros()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/><span>&nbsp;</span>						
						</cata:temPermissao>							
					</div>
					<!--Final botões pesquisar e limpar campos -->
				</catalogo:contentBox>
				<!-- Resultado da Pesquisa -->
				<c:if test="${financiamentoList != null}">
					<div id="resultadoPesquisaProduto">	
						<catalogo:contentBox  title="Resultado da Pesquisa">
							<display:table name="financiamentoList" id="condicaoPagamentoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "search.do" class="tabela-padrao-new tablesorter table_body" >						
								<display:column title="Origem" sortable="true" headerClass="sortable" value="${condicaoPagamentoTO.inCriacaoCatalogo ? 'Cat&aacute;logo' : 'Legado'}"/>
								<display:column property="cdCondicaoPagamento" title="Código" sortable="true" headerClass="sortable" />
								<display:column property="nmCondicaoPagamento" title="Nome do Plano de Financiamento" sortable="true" headerClass="sortable" />
								<display:column property="qtParcelas" format="{0,number,0}" title="Quantidade de Parcelas" sortable="true" headerClass="sortable" style="text-align:right;"/>
								<display:column property="txJuroParcela" format="{0,number,0.00}" title="Juros" sortable="true" headerClass="sortable" style="text-align:right;"/>
								<display:column title="Status" sortable="true" headerClass="sortable">
									<div class='clean'><div class='${condicaoPagamentoTO.inDisponivel ? "iconativo" : "iconinativo" }'></div></div>
								</display:column>
								<cata:temPermissao acao="alterarFinanciamento">
									<display:column title="Alterar" style="text-align:center; width:60px;">
										<img src="/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" width="15" onclick="change('${condicaoPagamentoTO.idCondicaoPagamento}', '${condicaoPagamentoTO.nmCondicaoPagamento}', '${condicaoPagamentoTO.qtParcelas}', '${condicaoPagamentoTO.txJuroParcela}',${condicaoPagamentoTO.inCriacaoCatalogo});" alt="Alterar" style="cursor: pointer;"/>
									</display:column>
								</cata:temPermissao>
								<cata:temPermissao acao="excluirFinanciamento">
									<display:column title="Excluir" style="text-align:center; width:60px;">
											<c:if test="${condicaoPagamentoTO.inCriacaoCatalogo}">
												<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="remove(${condicaoPagamentoTO.idCondicaoPagamento})" style="cursor: pointer;"/>
											</c:if>
									</display:column>
								</cata:temPermissao>
								<cata:temPermissao acao="ativarDesativarFinanciamento">
									<display:column title="Ação" style="text-align:center; width:60px;">
											<a href="javascript:changeStatus(${condicaoPagamentoTO.idCondicaoPagamento},'${condicaoPagamentoTO.inDisponivel}')">${condicaoPagamentoTO.inDisponivel ? "Desativar" : "Ativar"}</a>
									</display:column>
								</cata:temPermissao>										
							</display:table>								
							<div class="legendatable">
								<div><span class="bold">Legenda:</span></div>
									<div><img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar"> <span> Alterar</span></div>
									<div><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir"> <span> Excluir</span></div>
									<div><div class="iconativo"></div><span>Ativo</span></div>
									<div><div class="iconinativo"></div><span>Inativo</span></div>
								</div>
							</div>
						</catalogo:contentBox>
					</div>
					<script>mostrarPesquisa()</script>
				</c:if>
				<c:if test="${erro != null}">
					<script>mostrarErro('${erro}')</script>
				</c:if>
				<!--Final Resultado da Pesquisa -->
			
				<!--Edição de Produtos -->
				<div id="editarProdutos" style="display:${displayEditar} !important">	
					<catalogo:contentBox title="Cadastro de Financiamento" doubt="true" requiredFields="true">
						<div id="flash" style="display:none"></div>
						<div class="line">
							<div class="linerow bold">Nome do Plano de Financiamento:<span class="required">*</span></div>
							<div class="linerow bold">Quantidade de Parcelas:<span class="required">*</span></div>
							<div class="linerow bold">Juros:<span class="required">*</span></div>
						</div>
						<div class="line">
							<html:hidden styleId="idEditado" property="idEditado"/>
							<html:hidden styleId="statusAtual" property="statusAtual"/>
							<div class="linerow"><html:text styleId="nomeNew" property="nomeNew" /></div>
							<div class="linerow"><html:text styleId="qtdeParcelaNew" property="qtdeParcelaNew" /></div>
							<div class="linerow"><html:text styleId="taxaJurosNew" property="taxaJurosNew" /></div>
						</div>
						<!-- final 1 linha com as labels de seus respectivos inputs -->
						<!-- botões pesquisar e limpar campos -->
						<div class="barra"></div>
						<div class="botao">
							<cata:temPermissao acao="gravarFinanciamento">
								<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="gravarRegistros()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
							</cata:temPermissao>
						</div>
						<!--Final botões pesquisar e limpar campos -->
					</catalogo:contentBox>
				</div>
				<!--Final Edição de Produtos -->
			</div>			
        </html:form>     
   </jsp:body>        
</catalogo:defaultTemplate>