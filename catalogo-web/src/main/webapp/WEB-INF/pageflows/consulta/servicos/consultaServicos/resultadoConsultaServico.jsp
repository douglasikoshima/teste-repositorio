<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

	<div id="resultado_busca_produto" style="position:relative;">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa: </div>
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
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/exportar.do" target="target_download2" styleId="resultadoServicoForm">
					    <html:hidden property="idPlataforma" styleId="idPlataforma" value="${idPlataforma}"/>
					    <html:hidden property="idSistema" styleId="idSistema" value="${idSistema}"/>
						
						<div style="width:99%;" id="lista_servicos">
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
								<thead>
									<tr>
										<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
										<th class="sortable">Grupo de Serviços</th>
										<th class="sortable">Nome Comercial</th>
										<th class="sortable">Código</th>
										<th class="sortable">Código Anatel</th>
										<c:if test="${idPlataforma == 1}">
											<th class="sortable">Tarifa</th>
											<th class="sortable">Tipo de Tarifa</th>
										</c:if>
										<c:if test="${idPlataforma != 1}">
											<th>Tarifa</th>
										</c:if>
										<th class="sortable">Período</th>
										<th>Atributos</th>
										<th>Disp.</th>
									</tr>
								</thead>
							</table>						
						<div class="both-scroll" style="height:250px;">
						</br>
								<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="TabelaRelacionamentosRecentes">
										<thead>
											<tr>
												<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
												<th>Grupo de Serviços</th>
												<th>Nome Comercial</th>
												<th>Código</th>
												<th>Código Anatel</th>
												<c:if test="${idPlataforma == 1}">
													<th>Tarifa</th>
													<th>Tipo de Tarifa</th>
												</c:if>
												<c:if test="${idPlataforma != 1}">
													<th>Tarifa</th>
												</c:if>
												<th>Período</th>
												<th>Atributos</th>
												<th>Disp.</th>
											</tr>
										</thead>
								<tbody>								
								<logic:iterate id="servicos" property="arrayListaServicos" name="servicoForm">
									<c:set value="true" var="firstPass"/>
										<tr>
											<td class="center">		
											<html:checkbox property="idServicoSelecionados" value="${servicos.idServico}" styleClass="semBorda belongsToForm checkbox_modelo" /> 											  											 												 											 											 											
									        </td> 									        
											<td>
												<c:if test="${firstPass == true}">
													${servicos.nmCategoria}
												</c:if>
											</td>
											<td>															
												<html:link styleId="link_listarCategorias"						          										          	
										          	action="/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/abrirPopupDetalhesServico.do?id_servico=${servicos.idServico}"										          	       
										          	onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;">	
										         		${servicos.nmComercial}			          
										        </html:link>												
											</td> 
											<td>${servicos.cdCodigo}</td>
											<td>${servicos.cdAnatel}</td>
											<c:if test="${idPlataforma == 1}">
												<td>												
													<%-- <netui:span value="${servicos.valor}">
														<netui:formatNumber country="BR" language="pt" type="currency" />
													</netui:span> --%>													
													<fmt:formatNumber type="currency" value="${servicos.valor}"></fmt:formatNumber>
												</td>
												<td>${servicos.tpTarifa}</td>
											</c:if>
											<c:if test="${idPlataforma != 1}">
												<td>													
													<html:link styleId="link_listarCategorias"						          
											          	action="/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/abrirPopupTarifaServico.do?id_servico=${servicos.idServico}" 
											          	onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;">											         			
											         		<img alt="Tarifa serviço" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>	          
											        </html:link>													
												</td>
											</c:if>
											<td class="center">																																													
												<c:set var="dtInicial" value="${servicoForm.mapaDataIncial[servicos.nmComercial]}" />
												<bean:define id="dtInicial" value="${dtInicial}" />
												<bean:write bundle="messages" name="dtInicial" />																						
													a
												<c:set var="dtFinal" value="${servicoForm.mapaDataFinal[servicos.nmComercial]}" />
												<bean:define id="dtFinal" value="${dtFinal}" />
												<bean:write bundle="messages" name="dtFinal" />																					
											</td>
											<td class="center">																																	
												<html:link styleId="link_listarCategorias"						          
											          	action="/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/abrirPopupAtributosServico.do?nome_servico=${servicos.nmComercial}&id_servico=${servicos.idServico}"											          																				   
											          	onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;">											         			
											         		<img alt="Atributos do Serviço" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>	          
											       </html:link>																								
											</td>
											<td class="center">
												<c:choose>
													<c:when test="${servicos.indisponivel=='S'}">
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
													</c:when>
													<c:otherwise>
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										<c:set value="false" var="firstPass"/>
										</logic:iterate>
									</tbody>
								</table>
							</div>
						</div>
						</div>
						<div class="barra"></div>
						<div class="paginacao" style="width:99%;" align="right">
						<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">								
									<html:link styleClass="selected" onClick="return false;" href="">											         			
										${no_pagina}	         			          
									</html:link>																																					
								</c:when>
								<c:otherwise>							
									<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_servico').onclick();return false;" href="">											         			
										${no_pagina}	         			          
									</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach> 
					</div>
					<div class="barra"></div>
					<div class="botao">
							<label class="lblForm">Quantidade : ${totalRegistros}</label> 
								<html:submit bundle="messages" value="Exportar" styleClass="btNavegacao74" titleKey="catalogo.resultadoConsultaServico.Exportar"/>							
							<span>&nbsp;</span>
							 <c:if test="${(idPlataforma != 1 || idPlataforma == 3) && (idSistema != '' && idSistema != 3 || idSistema == 2 && idPlataforma == 3)}">
								<div id="btnExportar" class="botao" style="margin: 0; padding: 0">								
									<html:button bundle="messages" styleClass="btNavegacao74" value="Restri&ccedil;&otilde;es" property="bt_exportar" 
									onclick="$(this).form.action=$(this).next('a').href;send(this, 'lista_variaveis', null, 'resultado_busca_produto', null, null);"
									title="catalogo.resultadoPesquisaPlanos.Variaveis" />	
																	    
								<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/listarRestricoes.do" styleClass="hide"/>															
								</div>
							</c:if>
						</div>																												
						</html:form>	
					</div>
				</div>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>	
	<iframe id='target_download2' name='target_download2' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_busca_produto');"></iframe>
<div id="lista_variaveis" style="position:relative;">
</div>
