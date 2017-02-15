<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>

<netui:form action="associarProdutoModelo">
	<netui:hidden dataSource="actionForm.codigoAssociado" dataInput="${codigo_associado}"/>
	<div id="resultado_busca_produto" >
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
			<div class="conteudo_box_top_esq">
			</div>
			<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
					<div style="width:99%;position:relative;" id="lista_produtos">
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" id="TabelaRelacionamentosRecentes">
							<thead>
								<tr>
									<cata:temPermissao acao="alterarProduto">
										<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_produtos', '.checkbox_produto', this.checked);"/></th>
									</cata:temPermissao>
									<th class="sortable">Código</th>
									<th class="sortable">Desc. Produto</th>
									<th class="sortable">Desc. Produto Catalogo</th>
									<th class="sortable">Tipo Produto</th>
									<th class="sortable">Tecnologia</th>
									<th class="sortable">Fabricante</th>
									<th class="sortable">Modelo</th>
									<th class="sortable">Cor</th>
								</tr>
							</thead>
						</table>
							<div class="both-scroll" style="height:450px;width:804px;">
							<netui:checkBoxGroup dataSource="actionForm.idsProdutos" >
							<netui-data:repeater dataSource="produtos" defaultText="<br><span align='center'>Nenhum Produto encontrado.</span>">
								<netui-data:repeaterHeader>
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
										<thead>
											<tr>
												<cata:temPermissao acao="alterarProduto">
													<th class="center" width="10px" ><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_produtos', '.checkbox_produto', this.checked);"/></th>
												</cata:temPermissao>
												<th class="sortable">Código</th>
												<th class="sortable">Desc. Produto</th>
												<th class="sortable">Desc. Produto Catalogo</th>
												<th class="sortable">Tipo Produto</th>
												<th class="sortable">Tecnologia</th>
												<th class="sortable">Fabricante</th>
												<th class="sortable">Modelo</th>
												<th class="sortable">Cor</th>
											</tr>
										</thead>
									<tbody>
								</netui-data:repeaterHeader>
								<netui-data:repeaterItem>
									<netui-data:repeater dataSource="container.item.listaSistemaProduto.sistemaProdutoList">
										<netui-data:repeaterItem>
											<tr>
												<cata:temPermissao acao="alterarProduto">
													<td class="center" style="padding-left: 5px">
														<c:if test="${codigo_associado == 'sim'}">
															<netui:checkBoxOption value="produto_${container.item.idProduto}" tagId="checkbox_produto_${container.container.item.idProduto}" styleClass="semBorda belongsToForm checkbox_produto" labelStyleClass="hide" onChange="$(this).next('input').checked=this.checked;$(this).next('input').next('input').checked=this.checked;"/>
															<netui:checkBoxOption value="produtogrupoproduto_${container.container.item.idProdutoGrupoProduto}" tagId="checkbox_produtogrupoproduto_${container.container.item.idProdutoGrupoProduto}" styleClass="semBorda belongsToForm checkbox_produto hide" labelStyleClass="hide"/>
															<netui:checkBoxOption value="grupoproduto_${container.container.item.idGrupoProduto}" tagId="checkbox_grupoproduto_${container.container.item.idProduto}_${container.container.item.idGrupoProduto}" styleClass="semBorda belongsToForm checkbox_produto hide" labelStyleClass="hide"/>
														</c:if>
														<c:if test="${codigo_associado == 'nao'}">
															<netui:checkBoxOption value="${container.item.idProduto}" tagId="checkbox_produto_${container.container.item.idProduto}" styleClass="semBorda belongsToForm checkbox_produto" labelStyleClass="hide"/>
														</c:if>
													</td>
												</cata:temPermissao>
												<td>${container.item.cdCodigo}</td>
												<td>${container.container.item.dsSap}</td>
												<td>
													<netui:anchor action="popupAlterarNomeProduto" onClick="if(abrirLink('contentForm', this.href)){clearAndShow('contentForm');}return false;">
														<netui:parameter name="id_produto" value="${container.container.item.idProduto}"/>
														<netui:parameter name="codigo_sap" value="${container.item.cdCodigo}"/>
														<netui:parameter name="nome_comercial" value="${container.container.item.nmProduto}"/>
														<netui:parameter name="id_tipo_produto" value="${container.container.item.idTipoProduto}"/>
														<netui:parameter name="id_grupo_produto" value="${container.container.item.idGrupoProduto}"/>
														<netui:parameter name="tipo_produto" value="${container.container.item.nmTipoProduto}"/>
														<netui:parameter name="descricao_sap" value="${container.container.item.dsSap}"/>
														<netui:parameter name="composicao" value="${container.container.item.dsProduto}"/>
														<netui:parameter name="destaque_produto" value="${container.container.item.dsNota}"/>
														<netui:parameter name="id_cor" value="${container.container.item.idCor}"/>
														<netui:parameter name="cor_produto" value="${container.container.item.nmCor}"/>
														<netui:parameter name="rgb_produto" value="${container.container.item.rgb}"/>
														<netui:parameter name="indicador_modelo" value="${container.container.item.inModelo}"/>
														<netui:parameter name="id_fabricante" value="${id_fabricante}"/>
														${container.container.item.nmProduto}
													</netui:anchor>
													
												</td>
												<td>
													<netui:anchor action="popupAlterarNomeProduto" onClick="if(abrirLink('contentForm', this.href)){clearAndShow('contentForm');}return false;">
														<netui:parameter name="id_produto" value="${container.container.item.idProduto}"/>
														<netui:parameter name="codigo_sap" value="${container.item.cdCodigo}"/>
														<netui:parameter name="nome_comercial" value="${container.container.item.nmProduto}"/>
														<netui:parameter name="id_tipo_produto" value="${container.container.item.idTipoProduto}"/>
														<netui:parameter name="id_grupo_produto" value="${container.container.item.idGrupoProduto}"/>
														<netui:parameter name="tipo_produto" value="${container.container.item.nmTipoProduto}"/>
														<netui:parameter name="descricao_sap" value="${container.container.item.dsSap}"/>
														<netui:parameter name="composicao" value="${container.container.item.dsProduto}"/>
														<netui:parameter name="destaque_produto" value="${container.container.item.dsNota}"/>
														<netui:parameter name="id_cor" value="${container.container.item.idCor}"/>
														<netui:parameter name="cor_produto" value="${container.container.item.nmCor}"/>
														<netui:parameter name="rgb_produto" value="${container.container.item.rgb}"/>
														<netui:parameter name="indicador_modelo" value="${container.container.item.inModelo}"/>
														<netui:parameter name="id_fabricante" value="${id_fabricante}"/>
														${container.container.item.nmTipoProduto}
													</netui:anchor>
												</td>
												<td>${container.container.item.nmTecnologia}</td>
												<td>${container.container.item.nmFabricante}</td>
												<td>
													<table cellpadding="0" cellspacing="0" border="0">
														<tr>
															<td>${container.container.item.nmGrupoProduto}</td>
															<td>
																<c:if test="${container.container.item.inFimVida == 'S'}">
																	<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
																</c:if>
															</td>
														</tr>
													</table>
												</td>
												<td>${container.container.item.nmCor}</td>
											</tr>
										</netui-data:repeaterItem>
									</netui-data:repeater>
								</netui-data:repeaterItem>
								<netui-data:repeaterFooter>
										</tbody>
									</table>
								</netui-data:repeaterFooter>
							</netui-data:repeater>
							</netui:checkBoxGroup>
							</div>
						</div>
						<br clear="all">
						<div class="barra"></div>
						<div class="paginacao" style="width:99%;" align="right">
						<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">
									<netui:anchor styleClass="selected" onClick="return false;" href="">
										${no_pagina}
									</netui:anchor>
								</c:when>
								<c:otherwise>
									<netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_produtos').onclick();return false;" href="#">
										${no_pagina}
									</netui:anchor>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="barra"></div>	
						
						<div class="botao">
							<label class="lblForm">Quantidade : ${totalRegistros}</label> 
							<c:set var="valor_botoa_listar_modelos" value="Associar Modelo"/>
							<cata:temPermissao acao="alterarProduto">				
								<c:if test="${codigo_associado == 'sim'}">
									<netui:anchor action="popupDesassociarProdutosModelos" onClick="$('contentForm').hide();abrirPopup1(this.href, $('lista_produtos').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}), 'resultado_pesquisa');return false;">
										<input type="button" onclick="return false" class="btNavegacao120" value="Remover Associação" title="${bundle.default['catalogo.resultadoPesquisarProdutos.RemoverAssociacao']}"/>
									</netui:anchor>
									<span>&nbsp;</span>
									<c:set var="valor_botoa_listar_modelos" value="Alterar Associação"/>
								</c:if>
							</cata:temPermissao>
							<cata:temPermissao acao="alterarProduto">
								<netui:anchor action="listarModelosCompativeis" onClick="if(checkSelecaoProdutos($('lista_produtos').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){return false;}abrirLink('listaModelosCompativeis', this.href, 'resultado_pesquisa');return false;">
									<netui:parameter name="id_tipo_produto" value="${id_tipo_produto}"/>
									<netui:parameter name="id_fabricante" value="${id_fabricante}"/>
									<netui:parameter name="id_tecnologia" value="${id_tecnologia}"/>
									<input type="button" class="btNavegacao120" onclick="$('contentForm').hide();return false" class="btNavegacao74" value="${valor_botoa_listar_modelos}" title="${bundle.default['catalogo.resultadoPesquisarProdutos.BtListarModelo']}"/>
								</netui:anchor>
							</cata:temPermissao>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
	<div id="listaModelosCompativeis" style="position:relative;"></div>
</netui:form>