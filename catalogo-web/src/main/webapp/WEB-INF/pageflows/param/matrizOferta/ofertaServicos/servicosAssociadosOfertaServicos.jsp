<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="servicos_oferta_servicos">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Serviços Associados:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg" style="position:relative;">
					<div style="height: 150px" class="both-scroll">
								<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
									<thead>
										<tr>
											<th class="sortable">Nome Comercial do Servi&ccedil;o</th>
											<th class="sortable">Grupo de Serviços</th>
											<th class="sortable">Tipo de Servi&ccedil;o</th>
											<th class="sortable">Excluir Associa&ccedil;&atilde;o</th>
										</tr>
									</thead>
									<tbody>
									<c:if test="${ofertaServicosMatrizOfertaForm.listaServicosAssociadosOfertaServicos ne null}">	
								    <logic:iterate id="listaServicosAssociados" property="listaServicosAssociadosOfertaServicos" name="ofertaServicosMatrizOfertaForm">
									<tr>
										<td style="text-align: center;">${listaServicosAssociados.nmComercial}</td>
										<td style="text-align: center;">${listaServicosAssociados.nmCategoria}</td>
										<td style="text-align: center;">${listaServicosAssociados.dscTipoServico}</td>
										<td class="center">
											<cata:temPermissao acao="desassociarOfertaServicosMatrizOferta">
												<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/abrirPopupExcluirAssociacaoServico.do?id_servicoOfertaServico=${listaServicosAssociados.idServicoOfertaServico}" onClick="abrirPopup1(this.href, null , 'servicos_oferta_servicos');return false">
													<img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif" title="Excluir Associação Serviço com Oferta de Serviços" alt="Excluir Associação Serviço com Oferta de Serviços"/>
												</html:link>
											</cata:temPermissao>
										</td>
									</tr>
									</logic:iterate>
									</c:if>
									</tbody>
								</table>
							    <c:if test="${ofertaServicosMatrizOfertaForm.listaServicosAssociadosOfertaServicos == null}">	
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
								    <html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_oferta_servicos').onclick();return false;" href="#">${no_pagina}</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>						
					</div>
					<div class="barra"></div>
					<div class="botao">
						<label class="lblForm" >Quantidade : ${totalRegistros}</label>
						<cata:temPermissao acao="associarOfertaServicosMatrizOferta">
							<html:button tabindex="16" property="botao_nova_associacao" styleId="botao_nova_associacao" onMouseDown="$('pagina_solicitada').value='1';" onClick="abrirPopup1($(this).next('a').href, null, 'resultado_pesquisa');" styleClass="btNavegacao120" value="Nova Associação" title="Abrir Popup Associação" alt="Abrir Popup Associação" />
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/abrirPopupNovaAssociacao.do?id_ofertaServico=${idOfertaServico}" styleClass="hide"/>
						</cata:temPermissao>
					</div>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>