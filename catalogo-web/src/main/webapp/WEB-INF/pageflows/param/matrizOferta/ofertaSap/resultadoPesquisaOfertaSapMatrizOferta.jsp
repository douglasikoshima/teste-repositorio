<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>

<div style="position:relative;">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg">
				<div style="width:100%;position:relative;">		
					<div class="both-scroll" style="height: 300px;">
						
							<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="lista_oferta_sap">
								<thead>
									<tr>
										<th class="sortable">C&oacute;digo Oferta</th>
										<th class="sortable">Descri&ccedil;&atilde;o</th>
										<th class="sortable">Utiliza&ccedil;&atilde;o</th>
										<th class="sortable">Ativo</th>
										<th>Alterar</th>
										<th>Excluir</th>
									</tr>
								</thead>
							<tbody>
							<logic:iterate id="ofertaSap" property="ofertaSapLista" name="ofertaSapForm">		
								<c:set value="true" var="firstPass"/>
									<tr>
										<td>
											<c:if test="${firstPass == true}">
												${ofertaSap.cdOfertaSap}
											</c:if>
										</td>
										<td>${ofertaSap.dscOfertaSap}</td>
										<td>${ofertaSap.sgUtilizacao}</td>
										<td class="center">
													<c:choose>
														<c:when test="${ofertaSap.inDisponivel == 'S'}">
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
														</c:when>
														<c:otherwise>
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
														</c:otherwise>
													</c:choose>
												</td>
										<td class="center">
											<cata:temPermissao acao="alterarMatrizOfertaOfertaSap">
												<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/openForEditOfertaSap.do?id_oferta_sap=${ofertaSap.idOfertaSap}&cd_ofertaSap=${ofertaSap.cdOfertaSap}&dsc_ofertaSap=${ofertaSap.dscOfertaSap}&sg_utilizacao=${ofertaSap.sgUtilizacao}&in_disponivel=${ofertaSap.inDisponivel}" styleId="alterar_oferta_sap" onClick="if(abrirLink('div_alterar_oferta_sap', this.href, 'right_section')){clearAndShow('div_alterar_oferta_sap');}return false">
													<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
												</html:link>
											</cata:temPermissao>
										</td>
										<td class="center">
											<cata:temPermissao acao="excluirMatrizOfertaOfertaSap">
												<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/openConfirmDeleteOfertaSap.do?id_oferta_sap=${ofertaSap.idOfertaSap}&dsc_ofertaSap=${ofertaSap.dscOfertaSap}" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'))return false;">
													<img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif"/>
												</html:link>	
											</cata:temPermissao>
										</td>								
									</tr>
									<c:set value="false" var="firstPass"/>
								</logic:iterate>
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
					<html:link onClick="$('pagina_solicitada').value='1';$('botao_pesquisar_oferta_sap').onclick();return false;" href="#">
						<<
					</html:link>
					&nbsp;
					<html:link onClick="$('pagina_solicitada').value='${paginaAtual-1}';$('botao_pesquisar_oferta_sap').onclick();return false;" href="#">
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
							<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_oferta_sap').onclick();return false;" href="#">
								${no_pagina}
							</html:link>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${end < totalPagina}">
					<html:link onClick="$('pagina_solicitada').value='${paginaAtual+1}';$('botao_pesquisar_oferta_sap').onclick();return false;" href="#">
						>
					</html:link>
					&nbsp;
					<html:link onClick="$('pagina_solicitada').value='${totalPagina}';$('botao_pesquisar_oferta_sap').onclick();return false;" href="#">
						>>
					</html:link>
				</c:if>
			</div>
			<div class="barra"></div>
			<div class="botao" style="padding-left: 6px">
				<label class="lblForm" >Quantidade : ${totalRegistros}</label>
			</div>
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
</div>
