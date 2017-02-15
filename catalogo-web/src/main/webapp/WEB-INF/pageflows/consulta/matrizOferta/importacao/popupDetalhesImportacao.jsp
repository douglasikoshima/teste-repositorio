<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>


<img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="ajustesSizePopup1(null, '920px', '50px');$('popup1IF').style.height='425px';"/>

<catalogo:popupPadrao>
		<input id="larguraPopup" value="810px" class="hide"/>
		<input id="alturaPopup" value="350px" class="hide"/>
		<c:set var="nomeBox" scope="application" value="Detalhes de Críticas Importação" />
		<div id="popup_criticas_importacao" style="width: 110%">
		<div id="div_erro_popup_detalhes_importacao"></div>
			<jsp:include page="/templates/box_pre.jsp"/>
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/exportarDetalhesImportacao.do" styleId="lista_detalhes_importacao" target="target_download">
					<html:hidden property="idMatrizOfertaAquivo" styleId="idMatrizOfertaAquivo" value="${idMatrizOfertaArquivoIcon}"/>
					<div id="lista_criticas" style=" width: 100%">
						<div style="width:100%;position:relative" id="lista_detalhes">
						<div style="height: 280px">
							<div class="both-scroll" style="height: 300px;">
										<table border="0" style="width: 100%;" cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="lista_criticas_repeater">
											<thead>
												<tr>
													<th class="sortable">Org. Vendas</th>
													<th class="sortable">Canal Distribui&ccedil;&atilde;o</th>
													<th class="sortable">Material</th>
													<th class="sortable">Utiliza&ccedil;&atilde;o</th>
													<th class="sortable">Oferta<br>SAP</th>
													<th class="sortable">Pre&ccedil;o</th>
													<th class="sortable">Dt In&iacute;cio</th>
													<th class="sortable">Dt Final</th>
													<th class="sortable">DDD</th>
													<th class="sortable">Canal de<br>Vendas</th>
													<th class="sortable">A&ccedil;&atilde;o</th>
													<th class="sortable">Escrit&oacute;rio de Venda</th>
													<th class="sortable">Status<br>Registro</th>
												</tr>
											</thead>
											<tbody>
											<c:if test="${consultaImportacaoMatrizOfertaForm.detalhesImportacao == null}">	
											   <br><span align='center'>Nenhuma detalhe de Cr&iacute;tica encontrado.</span>
											</c:if>	
											<c:if test="${consultaImportacaoMatrizOfertaForm.detalhesImportacao ne null}">												
												<logic:iterate id="itemImportacao" property="detalhesImportacao" name="consultaImportacaoMatrizOfertaForm">
													<c:set value="true" var="firstPass"/>
													<tr>
														<td>
															<c:if test="${firstPass == true}">
																${itemImportacao.sgOrgVendas} 
															</c:if>
														</td>
														<td>${itemImportacao.canalDistribuicao}</td>
														<td>${itemImportacao.nmMaterial}</td>
														<td>${itemImportacao.sgUtilizacao}</td>
														<td>${itemImportacao.cdOfertaSap}</td>
														<td>${itemImportacao.descPreco}</td>
														<td>${itemImportacao.dtInicial}</td>
														<td>${itemImportacao.dtFinal}</td>
														<td>${itemImportacao.ddd}</td>
														<td>${itemImportacao.canalVendas}</td>
														<td>${itemImportacao.acao}</td>
														<td>${itemImportacao.sgEscritorioVenda}</td>
														<td><img src="/catalogo/static_server/img/botoes/valorCarac.gif" alt="${itemImportacao.dsStatus}" style="width: 10px; height: 10px;" /></td>
													</tr>
													<c:set value="false" var="firstPass"/>
												</logic:iterate>
												</c:if>
											</tbody>
										</table>
							</div>
						</div>
						<div class="paginacao" style="width:99%; margin-top: 8px" align="right">
							<c:set var="begin" value="${(paginaAtual>5)?(paginaAtual-5):(1)}"/>
							<c:set var="end" value="${(paginaAtual<(totalPagina-5))?(begin+9):(totalPagina)}"/>
							<c:if test="${begin > 1}">
                                <html:link styleId="popupDetalhesArquivoItem" 
                                action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/openPopupDetalhesArquivoItem.do?pagina_solicitada=${no_pagina}&idMatrizOfertaArquivoPag=${idMatrizOfertaArquivoIcon}" 
                                onClick="abrirPopup1(this.href, null, null);return false;" > <<
								</html:link>								
								&nbsp;
                                <html:link styleId="popupDetalhesArquivoItem" 
                                action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/openPopupDetalhesArquivoItem.do?pagina_solicitada=${paginaAtual-1}&idMatrizOfertaArquivoPag=${idMatrizOfertaArquivoIcon}" 
                                onClick="abrirPopup1(this.href, null, null);return false;" >< &nbsp;
								</html:link>								
							</c:if>
							<c:forEach begin="${begin}" end="${end}" var="no_pagina">
								<c:choose>
									<c:when test="${no_pagina == paginaAtual}">
										<html:link styleClass="selected" onClick="return false;" href="">${no_pagina}</html:link>
									</c:when>
									<c:otherwise>
		                                <html:link styleId="popupDetalhesArquivoItem" 
		                                action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/openPopupDetalhesArquivoItem.do?pagina_solicitada=${no_pagina}&idMatrizOfertaArquivoPag=${idMatrizOfertaArquivoIcon}" 
		                                onClick="abrirPopup1(this.href, null, null);return false;" >${no_pagina}
										</html:link>										
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${end < totalPagina}">
                                <html:link styleId="popupDetalhesArquivoItem" 
                                action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/openPopupDetalhesArquivoItem.do?pagina_solicitada=${paginaAtual+1}&idMatrizOfertaArquivoPag=${idMatrizOfertaArquivoIcon}" 
                                onClick="abrirPopup1(this.href, null, null);return false;" >&nbsp; >
								</html:link>								
								&nbsp;
                                <html:link styleId="popupDetalhesArquivoItem" 
                                action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/openPopupDetalhesArquivoItem.do?pagina_solicitada=${totalPagina}&idMatrizOfertaArquivoPag=${idMatrizOfertaArquivoIcon}" 
                                onClick="abrirPopup1(this.href, null, null);return false;" > >>
								</html:link>									
							</c:if>							
						</div>
						<div style="position: absolute; top: 308px; padding-top: 10px">
							<div class="barra"></div>
							<div class="paginacao" style="float:right; margin-right:5px;" align="right">
								<div class="botao">
									<label class="lblForm" >Quantidade : ${totalRegistros}</label>
									<cata:temPermissao acao="exportarMatrizOfertasItens">
										<html:submit property="bt_exportar" value="Exportar" styleClass="btNavegacao74"/>
									</cata:temPermissao>
								</div>
							</div>
						</div>
						</div>
					</div>
				</html:form>
				<iframe id='target_download' name='target_download' src='' style='display:none;'  onload="retornoDownloadFile(this, 'div_erro_popup_detalhes_importacao');"></iframe>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupPadrao>