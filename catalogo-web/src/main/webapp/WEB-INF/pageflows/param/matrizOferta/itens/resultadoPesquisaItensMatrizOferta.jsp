<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>


<div id="resultado_pesquisa_itens_matriz" style="position:relative;">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg">
				<div style="width:100%;position:relative;">		
					<div class="both-scroll" style="height: 300px;">
						<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="lista_itens_matriz_oferta">
							<thead>
								<tr>
									<th class="sortable">C&oacute;d <br> Produto</th>
									<th class="sortable">Descri&ccedil;&atilde;o</th>
									<th class="sortable">Oferta <br>SAP</th>
									<th class="sortable">DDD</th>
									<th class="sortable">Pt. Vendas</th>
									<th class="sortable">A&ccedil;&atilde;o</th>
									<th class="sortable">Esc. Venda</th>
									<th class="sortable">Valor</th>
									<th>Alterar</th>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${itensMatrizOfertaForm.itensLista == null}">
									<!-- <span align='center'>Nenhum Item encontrado para esse critério de pesquisa.</span> -->
								</c:when>
								<c:otherwise>
									<c:if test="${not empty itensMatrizOfertaForm.itensLista}">
										<logic:iterate id="listaItens" property="itensLista" name="itensMatrizOfertaForm">
											<c:set value="true" var="firstPass"/>
											<tr>
												<td style="text-align: left; padding-left: 5px">
													<c:if test="${firstPass == true}">
														${listaItens.cdCodigo}
													</c:if>
												</td>
												<td>${listaItens.dsProduto}</td>
												<td>${listaItens.dscOfertaSAP}</td>
												<c:choose>
													<c:when test="${listaItens.codigoArea eq '0'}">
														<td>&nbsp;</td>
													</c:when>
													<c:otherwise>
														<td>${listaItens.codigoArea}</td>
													</c:otherwise>
												</c:choose>
												<td>${listaItens.cdAdabas}</td>
												<td>${listaItens.nmAcao}</td>
												<td>${listaItens.nmEscritorioVenda}</td>
												<td>
													<fmt:formatNumber value="${listaItens.valorItem}" type="currency" />
												</td>
												<td class="center">
													<c:set var="dt_inicial" value="${itensMatrizOfertaForm.mapaDataIncial[listaItens.cdCodigo]}" />
													<c:set var="dt_final" value="${itensMatrizOfertaForm.mapaDataFinal[listaItens.cdCodigo]}"/>
													<cata:temPermissao acao="alterarMatrizOfertasItens">
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/abrirParaAlterarItensMatrizOferta.do?cd_codigo=${listaItens.cdCodigo}&ds_produto=${listaItens.dsProduto}&cd_oferta_sap=${listaItens.cdOfertaSAP}&dsc_oferta_sap=${listaItens.dscOfertaSAP}&sg_utilizacao=${listaItens.sgUtilizacao}&sg_organizacao_vendas=${listaItens.sgOrganizacaoVendas}&codigo_area=${listaItens.codigoArea}&canal_vendas=${listaItens.cdAdabas}&nm_acao=${listaItens.nmAcao}&valor_base_a_vista=${listaItens.valorBaseAVista}&valor_item_a_vista=${listaItens.valorItemAVista}&valor_chip_a_vista=${listaItens.valorChipAVista}&valor_base=${listaItens.valorBase}&valor_item=${listaItens.valorItem}&valor_chip=${listaItens.valorChip}&dt_inicial=${dt_inicial}&dt_final=${dt_final}&id_Matriz_oferta_item_preco=${listaItens.idMatrizOfertaItemPreco}&idCanalDistribuicao=${listaItens.idCanalDistribuicao}&idAcao=${listaItens.idAcao}&idEscritorioVenda=${listaItens.idEscritorioVenda}&nmEscritorioVenda=${listaItens.nmEscritorioVenda}&" styleId="link_alterar_itens_matriz_oferta" onClick="if(abrirLink('div_alterar_itens_matriz', this.href, 'div_returno_erro_alteracao_itens')){clearAndShow('div_alterar_itens_matriz');}return false">												
														<html:img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" />
													</html:link>
													</cata:temPermissao>
												</td>
												<td class="center">&nbsp;</td>
											</tr>
											<c:set value="false" var="firstPass"/>
										</logic:iterate>
									</c:if>
								</c:otherwise>								
							</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<br>
			
			
			
			<div class="paginacao" style="width:99%; height: auto; bottom: 30px;" align="right">
				<c:set var="begin" value="${(paginaAtual>5)?(paginaAtual-5):(1)}"/>
				<c:set var="end" value="${(paginaAtual<(totalPagina-5))?(begin+9):(totalPagina)}"/>
				<c:if test="${begin > 1}">
					<html:link onClick="$('pagina_solicitada').value='1';$('botao_pesquisar_itens_matriz_oferta').onclick();return false;" href="#">
						<<
					</html:link>

					&nbsp;
					<html:link onClick="$('pagina_solicitada').value='${paginaAtual-1}';$('botao_pesquisar_itens_matriz_oferta').onclick();return false;" href="#">
						<
					</html:link>
				</c:if>
				<c:forEach begin="${begin}" end="${end}" var="no_pagina">
					<c:choose>
						<c:when test="${no_pagina == paginaAtual}">
							<html:link styleClass="selected" onClick="return false;" href="">
								${no_pagina}
							</html:link>
						</c:when>
						<c:otherwise>
							<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_itens_matriz_oferta').onclick();return false;" href="#">
								${no_pagina}
							</html:link>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${end < totalPagina}">
					<html:link onClick="$('pagina_solicitada').value='${paginaAtual+1}';$('botao_pesquisar_itens_matriz_oferta').onclick();return false;" href="#">
						>
					</html:link>
					&nbsp;
					<html:link onClick="$('pagina_solicitada').value='${totalPagina}';$('botao_pesquisar_itens_matriz_oferta').onclick();return false;" href="#">
						>>
					</html:link>
				</c:if>
			</div>
			
		<%-- 
			<div class="paginacao" style="width:99%;" align="right">
				<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
					<c:choose>
						<c:when test="${no_pagina == paginaAtual}">
							<netui:anchor styleClass="selected" onClick="return false;" href="">
								${no_pagina}
							</netui:anchor>
						</c:when>
						<c:otherwise>
							<netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_itens_matriz_oferta').onclick();return false;" href="#">
								${no_pagina}
							</netui:anchor>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		--%>
			
			
			<div class="barra"></div>
			<div class="botao">
				<label>&nbsp;</label>
				<label class="lblForm" >Quantidade : ${totalRegistros}</label>
			</div>
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
</div>
<div id="div_alterar_itens_matriz" style="display: none; position: relative;"></div>
