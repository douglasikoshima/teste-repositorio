<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<div id="resultado_busca_oferta_servicos">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
		alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg" style="position:relative;">
					<div style="height:350px;" class="both-scroll">
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
										<thead>
											<tr>
												<th class="sortable">C&oacute;digo<br>da Oferta</th>
												<th class="sortable">Nome da Oferta</th>
												<th class="sortable">Servi&ccedil;os<br>Associados</th>
												<th >Alterar</th>
												<th >Excluir</th>
											</tr>
										</thead>
										<tbody>
									<c:if test="${ofertaServicosMatrizOfertaForm.listaOfertaServicos ne null}">												
										<logic:iterate id="ofertaServicos" property="listaOfertaServicos" name="ofertaServicosMatrizOfertaForm">
											<tr>
												<td style="text-align: left;">${ofertaServicos.cdOfertaServico}&nbsp;</td>
												<td style="center">${ofertaServicos.nmOfertaServico}&nbsp;</td>
												<td class="center">
													<html:link onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;" title="Serviços Associados" 
													    action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/listarServicosAssociados.do?id_ofertaServico=${ofertaServicos.idOfertaServico}">
													    <img alt="Produtos Associados" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif" title="Serviços Associados" alt="Serviços Associados" />
													</html:link>													
												</td>
												<td class="center">
													<cata:temPermissao acao="alterarOfertaServicosMatrizOferta">
														<html:link onClick="$('alterarOfertaServicos').show();habilitar('botao_nova_ofertas_servico');abrirLink('alterarOfertaServicos', this.href, 'right_section');$('novo_oferta_servico').hide();$('servicosOfertaServicos').hide();return false;" 
														    action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/abrirAlterarOfertaServicosMatriz.do?id_oferta_servico=${ofertaServicos.idOfertaServico}&cd_oferta_servico=${ofertaServicos.cdOfertaServico}&nm_oferta_servico=${ofertaServicos.nmOfertaServico}&ds_nota_servico=${ofertaServicos.dsNota}">
														    <img src="/catalogo/static_server/img/botoes/bt-alterar.gif" title="Alterar" alt="Alterar" />
														</html:link>														
													</cata:temPermissao>
												</td>
												<td class="center">
													<cata:temPermissao acao="excluirOfertaServicosMatrizOferta">
														<html:link onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'));return false;" 
														    action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/abrirPopupExcluirOfertaServico.do?id_ofertaServico=${ofertaServicos.idOfertaServico}">
														    <img onclick="" src="/catalogo/static_server/img/botoes/bt-excluir.gif" title="Excluir" alt="Excluir" />
														</html:link>
													</cata:temPermissao>
												</td>
											</tr>
										</logic:iterate>	
									</c:if>
										</tbody>
									</table>
									<c:if test="${ofertaServicosMatrizOfertaForm.listaOfertaServicos == null}">	
									   <br><span align='center'>Nenhuma Oferta de Servi&ccedil;o encontrada.</span>
									</c:if>	
					</div>
					<br>
					<div class="paginacao" style="width:99%;" align="right">
						<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">
								    <html:link styleClass="selected" onClick="return false;" href="">${no_pagina}</html:link>
								</c:when>
								<c:otherwise>
								    <html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_listar_ofertas_servico').onclick();return false;" href="#">${no_pagina}</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>						
					</div>
					<div class="barra"></div>
					<div class="botao">
						<label class="lblForm" >Quantidade : ${totalRegistros}</label>
					</div>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br clear="all" />