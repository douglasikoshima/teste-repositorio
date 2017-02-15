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
		<script type="text/javascript" src="/catalogo/static_server/js/familiacategoria.js"></script>
   </jsp:attribute>
   <jsp:body>
		<!-- div para montar o caminho das páginas !-->
		<div class="breadcrumb">Parametrização > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/FamiliaCategoriaAction.do';" style="cursor: pointer;">Família | Categoria</a><span></div>
			<script>carregaMenu('mn_parametrizacao_familia_categoria');</script>
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/search.do" styleId="acaoForm" onsubmit="false">
				<div class="secao_conteudo">
					<div class="contentGroup">
						<!-- Box para montar as Pesquisas de Familia x Categoria !-->
						<catalogo:contentBox title="Pesquisa" doubt="false" requiredFields="false">
							<!-- Escolha o Tipo de Pesquisa #pesquisaTipo !-->
							<fieldset id="pesquisaTipo">
								<legend>Escolha o tipo de pesquisa</legend>
								<div class="line">
									<div class="bold" style="float:left;padding-right:10px;"><html:radio property="tipoPesquisa" styleId="familia" value="familia" styleClass="semBorda checkbox">Família</html:radio></div>
									<div class="bold" style="float:left"><html:radio property="tipoPesquisa" styleId="categoria" value="categoria" styleClass="semBorda checkbox">Categoria</html:radio></div>					
								</div>
							</fieldset>
							<!-- Box com Filtros de Pesquisa da Familia #pesquisa !-->
							<div id="pesquisa">
								<div id="pesquisa_familia" style="display:${tipoPesquisa == 'familia' ? 'block' : 'none' };">
									<div class="line">
										<div class="linerow bold ">Código:</div>
										<div class="linerow bold label_required"><label for="nome" id="nomeLabel">Nome:</label></div>
										<div class="linerow bold">Origem:</div>
										<div class="linerow bold">Disponibilidade:</div>
									</div>
									<div class="line">
										<div class="linerow"><html:text property="codigoFamilia" styleId="codigoFamilia"></html:text></div>
										<div class="linerow"><html:text property="nomeFamilia" styleId="nomeFamilia"></html:text></div>
										<div class="linerow">
											<html:select styleClass="required" styleId="origemFamilia" property="origemFamilia">
												<html:option value="">Ambos</html:option>
							                    <html:option value="catalogo">Catálogo</html:option>
							                    <html:option value="legado">Legado</html:option>
						                    </html:select>
										</div>
										<div class="linerow">
											<html:select styleClass="required" styleId="disponibilidadeFamilia" property="disponibilidadeFamilia" >
							                    <html:option value="">Ambos</html:option>
												<html:option value="sim">Sim</html:option>
							                    <html:option value="nao">Não</html:option>
						                    </html:select>
										</div>										
									</div>
								</div>
								
								<div id="pesquisa_categoria" style="display:${tipoPesquisa == 'categoria' ? 'block' : 'none' };">
									<div class="line">
										<div class="linerow bold ">Código:</div>
										<div class="linerow bold label_required"><label for="descricaoCategoria" id="descLabel">Descrição Categoria:</label></div>
										<div class="linerow bold">Nome da Família:</div>
										<div class="linerow bold">Origem:</div>
									</div>
									<div class="line">
										<div class="linerow"><html:text property="codigoCategoria" styleId="codigoCategoria"/></div>
										<div class="linerow"><html:text property="descricaoCategoria" styleId="descricaoCategoria"/></div>
										<div class="linerow">
											<html:select styleClass="required" styleId="idFamiliaCategoria" property="idFamiliaCategoria">
												<html:option value="">--Selecione--</html:option>
												<c:forEach var="familiaTO" items="${familiaCategoriaTOList}">
													<html:option value="${familiaTO.idFamilia}">${familiaTO.nmFamilia}</html:option>
												</c:forEach>
						                    </html:select>
										</div>
										<div class="linerow">
											<html:select styleClass="required" styleId="origemCategoria" property="origemCategoria">
												<html:option value="">Ambos</html:option>
							                    <html:option value="catalogo">Catálogo</html:option>
							                    <html:option value="legado">Legado</html:option>
						                    </html:select>
										</div>
									</div>
									<div class="line">
										<div class="linerow bold">Disponibilidade:</div>
									</div>
									<div class="line">
										<div class="linerow">
											<html:select styleClass="required" styleId="disponibilidadeCategoria" property="disponibilidadeCategoria" >
							                    <html:option value="">Ambos</html:option>
												<html:option value="sim">Sim</html:option>
							                    <html:option value="nao">Não</html:option>
						                    </html:select>
										</div>
									</div>
									
								</div>
								<!-- botões pesquisar e limpar campos -->
								<div class="barra"></div>
								<div class="botao" id="divBotoes" style="display:${tipoPesquisa != null ? 'block' : 'none'}">
									<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
									<cata:temPermissao acao="novoCategoriaFamilia">
										<html:button property="botao_novo_form" styleId="botao_novo_form" onClick="editFamilia('', '')" styleClass="btNavegacao74" value="Novo" alt="Novo" title="" style="display:${tipoPesquisa == 'categoria' ? 'none' : 'block' };"/><span>&nbsp;</span>						
									</cata:temPermissao>
								</div>
							</div>
						</catalogo:contentBox>
						<!-- FINAL Box para montar as Pesquisas de Familia x Categoria !-->
					
						<!-- Resultado da Pesquisa da Familia #resultadoFamilia !-->
						<c:if test="${familiaTOList != null}">
							<div id="resultadoFamilia">	
								<catalogo:contentBox  title="Resultado da Pesquisa" doubt="false" requiredFields="false">
									<display:table name="familiaTOList" id="familitaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "search.do" class="tabela-padrao-new tablesorter table_body" >
										<display:column title="Origem" sortable="true" headerClass="sortable" value="${familitaTO.inCriacaoCatalogo ? 'Cat&aacute;logo' : 'Legado'}"/>
										<display:column property="cdFamilia" title="Código" sortable="true" headerClass="sortable" />
										<display:column property="nmFamilia" title="Nome" sortable="true" headerClass="sortable" />
										<display:column title="Descrição das Categorias" sortable="true" headerClass="sortable">
											<div class='clean'>
												<a href="#" onClick="showDetails('${familitaTO.idFamilia}')" class="clean">Exibir categorias (${familitaTO.categoriaTOListSize})</a>
											</div>
										</display:column>
										<display:column title="Status" sortable="true" headerClass="sortable">
											<div class='clean'><div class='${familitaTO.inDisponivel ? "iconativo" : "iconinativo" }'></div></div>
										</display:column>
										<cata:temPermissao acao="alterarCategoriaFamilia">
											<display:column title="Alterar" style="text-align:center; width:60px;">
												<img src="/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" width="15" onclick="editFamilia('${familitaTO.idFamilia}', '${familitaTO.nmFamilia}');" alt="Alterar" style="cursor: pointer;"/>
											</display:column>
										</cata:temPermissao>
										<cata:temPermissao acao="excluirCategoriaFamilia">
											<display:column title="Excluir" style="text-align:center; width:60px;">
													<c:if test="${familitaTO.inCriacaoCatalogo}">
														<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="removeFamilia('${familitaTO.idFamilia}')" style="cursor: pointer;"/>
													</c:if>
											</display:column>
										</cata:temPermissao>
										<cata:temPermissao acao="ativarDesativarCategoriaFamilia">
											<display:column title="Ação" style="text-align:center; width:60px;">
													<a href="javascript:changeStatus('${familitaTO.idFamilia}','${familitaTO.inDisponivel}','changeStatusFamilia.do#acaoForm')">${familitaTO.inDisponivel ? "Desativar" : "Ativar"}</a>
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
								</catalogo:contentBox>
							</div>
						</c:if>
<!--						Final Resultado da Pesquisa Familia -->
					<!--Descrição Categorias #verCategoriasFamilia -->	
					<c:if test="${familiaTO != null}">
						<div id="verCategoriasFamilia">
							<catalogo:contentBox title="Descrição da Categoria" doubt="false" requiredFields="false">
								<fieldset>
									<legend>Informações da Família</legend>
									<ul class="breadcrumb">
										<li style="padding-right:10px"><span>Código: </span>${familiaTO.cdFamilia}</li>
										<li style="padding-right:10px"><span>Nome: </span>${familiaTO.nmFamilia}</li>
										<li style="padding-right:10px"><span>Descrição Família: </span>${familiaTO.dsFamilia}</li>
									</ul>							
								</fieldset>
								<display:table name="familiaTO.categoriaTOList" id="categoriaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "search.do" class="tabela-padrao-new tablesorter table_body" >
									<display:column title="Origem" sortable="true" headerClass="sortable" value="${categoriaTO.indCatalogo ? 'Cat&aacute;logo' : 'Legado'}"/>
									<display:column property="dsCategoria" title="Descrição da Categoria" sortable="true" headerClass="sortable" />
									<display:column title="Status" sortable="true" headerClass="sortable">
										<div class='clean'><div class='${categoriaTO.inDisponivel ? "iconativo" : "iconinativo" }'></div></div>
									</display:column>
									<cata:temPermissao acao="alterarCategoriaFamilia">
										<display:column title="Alterar" style="text-align:center; width:60px;">
											<img src="/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" width="15" onclick="editCategoria('${categoriaTO.idCategoria}', '${familiaTO.idFamilia}', '${categoriaTO.dsCategoria}', '${familiaTO.nmFamilia}');" alt="Alterar" style="cursor: pointer;"/>
										</display:column>
									</cata:temPermissao>
									<cata:temPermissao acao="excluirCategoriaFamilia">
										<display:column title="Excluir" style="text-align:center; width:60px;">
												<c:if test="${categoriaTO.indCatalogo}">
													<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="removeCategoria('${categoriaTO.idCategoria}')" style="cursor: pointer;"/>
												</c:if>
										</display:column>
									</cata:temPermissao>
									<cata:temPermissao acao="ativarDesativarCategoriaFamilia">
										<display:column title="Ação" style="text-align:center; width:60px;">
												<a href="javascript:changeStatus('${categoriaTO.idCategoria}', '${categoriaTO.inDisponivel}', 'changeStatusCategoria.do#acaoForm')">${categoriaTO.inDisponivel ? "Desativar" : "Ativar"}</a>
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
								<!-- botões pesquisar e limpar campos -->
								<div class="barra"></div>
								<div class="botao">
									<cata:temPermissao acao="novoCategoriaFamilia">
										<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="editCategoria('','${familiaTO.idFamilia}','','${familiaTO.nmFamilia}')" styleClass=" btNavegacao74" value="Novo" alt="Salvar" title=""/><span>&nbsp;</span>
									</cata:temPermissao>
								</div>
							</catalogo:contentBox>	
						</div>
					</c:if>
					<!--Final Descrição Categorias  -->						
						
<!--						 Resultado da Pesquisa Categoria #resultadoCategoria -->
					<c:if test="${categoriaTOList != null}" >
						<div id="resultadoCategoria">	
							<catalogo:contentBox  title="Resultado da Pesquisa" doubt="false" requiredFields="false">
								<display:table name="categoriaTOList" id="categoriaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "search.do" class="tabela-padrao-new tablesorter table_body" >
									<display:column title="Origem" sortable="true" headerClass="sortable" value="${categoriaTO.indCatalogo ? 'Cat&aacute;logo' : 'Legado'}"/>
									<display:column property="idCategoria" title="Código" sortable="true" headerClass="sortable" />
									<display:column property="dsCategoria" title="Descrição da Categoria" sortable="true" headerClass="sortable" />
									<display:column property="familiaTO.nmFamilia" title="Nome da Família" sortable="true" headerClass="sortable" />
									<display:column title="Status" sortable="true" headerClass="sortable">
										<div class='clean'><div class='${categoriaTO.inDisponivel ? "iconativo" : "iconinativo" }'></div></div>
									</display:column>
									<cata:temPermissao acao="alterarCategoriaFamilia">
										<display:column title="Alterar" style="text-align:center; width:60px;">
											<img src="/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" width="15" onclick="editCategoria('${categoriaTO.idCategoria}', '', '${categoriaTO.dsCategoria}', '${categoriaTO.familiaTO.nmFamilia}');" alt="Alterar" style="cursor: pointer;"/>
										</display:column>
									</cata:temPermissao>
									<cata:temPermissao acao="excluirCategoriaFamilia">
										<display:column title="Excluir" style="text-align:center; width:60px;">
												<c:if test="${categoriaTO.indCatalogo}">
													<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="removeCategoria('${categoriaTO.idCategoria}')" style="cursor: pointer;"/>
												</c:if>
										</display:column>
									</cata:temPermissao>
									<cata:temPermissao acao="ativarDesativarCategoriaFamilia">
										<display:column title="Ação" style="text-align:center; width:60px;">
												<a href="javascript:changeStatus('${categoriaTO.idCategoria}', '${categoriaTO.inDisponivel}', 'changeStatusCategoria.do#acaoForm')">${categoriaTO.inDisponivel ? "Desativar" : "Ativar"}</a>
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
							</catalogo:contentBox>
						</div>
					</c:if>
					<!--Final Resultado da Pesquisa -->
							
					<div id="edit" style="display:${editVisibility} !important">
						<div id="editFamilia" style="display:${tipoPesquisa == 'familia' && familiaTO == null ? 'block' : 'none' } !important;">
							<catalogo:contentBox title="Cadastro Fam&iacute;lia" doubt="true" requiredFields="true" > 
								<div id="flashFamilia" style="display:none;color:red !important"></div>
								<div class="line">
									<div class="linerow bold "><label for="nomeFamiliaNew">Nome:</label><span class="required">*</span></div>
								</div>
								<div class="line">
									<div class="linerow"><html:text property="nomeFamiliaNew" styleId="nomeFamiliaNew"/></div>
								</div>
								<div class="barra"></div>
								<div class="botao">
								    <cata:temPermissao acao="gravarCategoriaFamilia">
										<html:button property="botao_gravar_familia" styleId="botao_gravar_familia" onClick="reccordFamilia()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
									</cata:temPermissao>
								</div>
							</catalogo:contentBox>
						</div>
						<div id="editCategoria" style="display:${tipoPesquisa == 'categoria' || (tipoPesquisa == 'familia' && familiaTO != null) ? 'block' : 'none' } !important;">
							<catalogo:contentBox title="Cadastro Categoria" doubt="true" requiredFields="true" >
								<div id="flashCategoria" style="display:none;color:red !important"></div>
								<div class="line">
									<div class="linerow bold "><label for="descricaoCategoriaNew">Descrição da Categoria:</label><span class="required">*</span></div>
									<div class="linerow bold "><label for="textDescFamilia">Descrição Família:</label><span class="required">*</span></div>
								</div>
								<div class="line">
									<div class="linerow"><html:text property="descricaoCategoriaNew" styleId="descricaoCategoriaNew"/></div>
									<div class="linerow"><html:text property="textDescFamilia" styleId="nmFamiliaCategoriaEdit" disabled="true"/></div>
								</div>
								<div class="barra"></div>
								<div class="botao">
									<cata:temPermissao acao="gravarCategoriaFamilia">
										<html:button property="botao_gravar_categoria" styleId="botao_gravar_categoria" onClick="reccordCategoria()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>								
									</cata:temPermissao>
								</div>
							</catalogo:contentBox>
						</div>
					</div>			
				</div>
				<html:hidden property="idFamilia" styleId="idFamilia"/>
				<html:hidden property="idEditado" styleId="idEditado"/>
				<html:hidden property="statusAtual" styleId="statusAtual"/>			
				<c:if test="${erro != null}">
					<script>mostrarErro('${erro}')</script>
				</c:if>
        </html:form>     
   </jsp:body>        
</catalogo:defaultTemplate>