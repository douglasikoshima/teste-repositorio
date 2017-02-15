<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<%-- <netui:form action="exportarVariaveis" target="target_download"> --%>

<head>
	<script type="text/javascript" src="/catalogo/static_server/js/consultaPlanos.js"></script>
</head>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/exportarVariaveis.do" target="target_download" styleId="consultaPlanosForm" >
	<div id="resultado_busca_plano">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Variaveis de Planos:</div>
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
						<div style="width:99%;position:relative;" id="lista_variaveis">
						
							<%-- <netui:checkBoxGroup dataSource="actionForm.idsPlanos" > --%>
							<%-- <logic:iterate id="idsPlanos" property="idsPlanos"> --%>
							<%-- <html:multibox property="idsPlanos"> --%>
							
							<%-- <netui:hidden dataSource="actionForm.idPlataforma" dataInput="${idPlataforma}"/> --%>
							<html:hidden property="idPlataforma" value="${idPlataforma}"/>
							<c:set var="plano" value=""/>
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" id="TabelaRelacionamentosRecentes">
								<thead>
									<tr>
										<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_plano', this.checked);"/></th>
										<th class="sortable">Nome do Plano</th>
										<th class="sortable">Plataforma</th>
										<th class="sortable">Tecnologia</th>
										<th class="sortable">Tipo de Cliente</th>
										<th class="sortable">Canal</th>
										<th class="sortable">Segmento</th>
										<c:if test="${idPlataforma == 1}">
											<th class="sortable">Carteira</th>
										</c:if>
										<c:if test="${idPlataforma != 1}">
											<th class="sortable">Tipo Assinatura</th>
										</c:if>
										<th class="sortable">UF</th>
										<th class="sortable">DDD</th>
									</tr>
								</thead>
							</table>
							<div class="both-scroll" style="height:300px">
							
							<%-- <netui-data:repeater dataSource="variaveis" defaultText="<br><span align='center'>Nenhum Plano encontrado.</span>"> --%>
							<%-- <display:table name="variaveis"> --%>
								<%-- <netui-data:repeaterHeader> --%>
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
										<thead>
											<tr>
												<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_plano', this.checked);"/></th>
												<th class="sortable">Nome do Plano</th>
												<th class="sortable">Plataforma</th>
												<th class="sortable">Tecnologia</th>
												<th class="sortable">Tipo de Cliente</th>
												<th class="sortable">Canal</th>
												<th class="sortable">Segmento</th>
												<c:if test="${idPlataforma == 1}">
													<th class="sortable">Carteira</th>
												</c:if>
												<c:if test="${idPlataforma != 1}">
													<th class="sortable">Tipo Assinatura</th>
												</c:if>
												<th class="sortable">UF</th>
												<th class="sortable">DDD</th>
											</tr>
										</thead>
										
									<tbody>
								<%-- </netui-data:repeaterHeader> --%>
								
								
								<%-- <netui-data:repeaterItem> --%>
									<%-- <c:if test="${container.item.codigoArea != null}"> --%>
									<logic:iterate id="listaPlano" property="retornoPlanoList" name="consultaPlanosForm">
									<c:if test="${codigoArea != null}">
										<%-- <netui-data:repeater dataSource="container.item.codigoArea"> --%>
										<%-- <display:table name="codigoArea"> --%>
											<tr align="left">
												<td style="text-align: center;">
													<%-- <c:if test="${plano != container.container.item.nmComercial}">
														<netui:checkBoxOption value="${container.container.item.idPlano}" tagId="checkbox_plano_${container.container.item.nmUf}" styleClass="semBorda belongsToForm checkbox_plano" labelStyleClass="hide"/>
													</c:if> --%>
													<c:if test="${plano != nmComercial}">
														<html:checkbox  value="${listaPlano.idPlano}" styleId="checkbox_plano_${nmUf}" styleClass="semBorda belongsToForm checkbox_plano" style="hide"/>
													</c:if>
												</td>
												<td style="text-align: left;">
													<%-- <c:if test="${plano != container.container.item.nmComercial}">
														${container.container.item.nmComercial}
														<c:set var="plano" value="${container.container.item.nmComercial}"/>
													</c:if> --%>
													<c:if test="${plano != nmComercial}">
														${listaPlano.nmComercial}
														<c:set var="plano" value="${listaPlano.nmComercial}"/>
													</c:if>
												</td>
												<%-- <td style="text-align: left;">${container.container.item.nmPlataforma}</td>
												<td style="text-align: left;">${container.container.item.nmTecnologia}</td>
												<td style="text-align: left;">${container.container.item.nmTipoCliente}</td>
												<td style="text-align: left;">${container.container.item.nmCanal}</td>
												<td style="text-align: left;">${container.container.item.sgSegmento}</td>
												<c:if test="${idPlataforma == 1}">
													<td style="text-align: left;">${container.container.item.sgCarteira}</td>
												</c:if>
												<c:if test="${idPlataforma != 1}">
													<td style="text-align: left;">${container.container.item.descTpAssinatura}</td>
												</c:if>
												<td style="text-align: left;">${container.container.item.nmUf}</td>
												<td style="text-align: left;">${container.item}</td> --%>
												<td style="text-align: left;">${listaPlano.nmPlataforma}</td>
												<td style="text-align: left;">${listaPlano.nmTecnologia}</td>
												<td style="text-align: left;">${listaPlano.nmTipoCliente}</td>
												<td style="text-align: left;">${listaPlano.nmCanal}</td>
												<td style="text-align: left;">${listaPlano.sgSegmento}</td>
												<c:if test="${idPlataforma == 1}">
													<td style="text-align: left;">${listaPlano.sgCarteira}</td>
												</c:if>
												<c:if test="${idPlataforma != 1}">
													<td style="text-align: left;">${listaPlano.descTpAssinatura}</td>
												</c:if>
												<td style="text-align: left;">${listaPlano.nmUf}</td>
												<td style="text-align: left;">${listaPlano.item}</td>
											</tr>
										<%-- </netui-data:repeater> --%>
										<%-- </display:table> --%>
									</c:if>
								<%-- </netui-data:repeaterItem> --%>
								
								<!-- <netui-data:repeaterFooter>
										</tbody>
									</table>
								</netui-data:repeaterFooter> -->
								<%-- <display:footer> --%>
										</logic:iterate>			
									</tbody>
								</table>
								<%-- </display:footer> --%>
								
								
							<%-- </netui-data:repeater> --%>
							<%-- </display:table> --%>
							
							</div>
							<%-- </netui:checkBoxGroup> --%>
							<%-- </html:multibox> --%>
							
							
						</div>
						<br>
						<div class="paginacao" style="width:99%;" align="right">
						<c:set var="begin" value="${(paginaAtual>5)?(paginaAtual-5):(1)}"/>
						<c:set var="end" value="${(paginaAtual<(totalPagina-5))?(begin+9):(totalPagina)}"/>
						<c:if test="${begin > 1}">
							<%-- <netui:anchor onClick="$('pagina_solicitada').value='1';$('botao_listar_variaveis').onclick();;return false;" href="#">
								<<
							</netui:anchor> --%>
							<html:link onClick="$('pagina_solicitada').value='1';$('botao_listar_variaveis').onclick();;return false;" href="#">
								<<
							</html:link>
							&nbsp;
							<%-- <netui:anchor onClick="$('pagina_solicitada').value='${paginaAtual-1}';$('botao_listar_variaveis').onclick();return false;" href="#">
								<
							</netui:anchor> --%>
							<html:link onClick="$('pagina_solicitada').value='${paginaAtual-1}';$('botao_listar_variaveis').onclick();return false;" href="#">
								<
							</html:link>
						</c:if>
						<c:forEach begin="${begin}" end="${end}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">
									<%-- <netui:anchor styleClass="selected" onClick="return false;" href="">
										${no_pagina}
									</netui:anchor> --%>
									<html:link styleClass="selected" onClick="return false;" href="">
										${no_pagina}
									</html:link>
								</c:when>
								<c:otherwise>
									<%-- <netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_listar_variaveis').onclick();return false;" href="#">
										${no_pagina}
									</netui:anchor> --%>
									<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_listar_variaveis').onclick();return false;" href="#">
										${no_pagina}
									</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${end < totalPagina}">
							<%-- <netui:anchor onClick="$('pagina_solicitada').value='${paginaAtual+1}';$('botao_listar_variaveis').onclick();return false;" href="#">
								>
							</netui:anchor> --%>
							<html:link onClick="$('pagina_solicitada').value='${paginaAtual+1}';$('botao_listar_variaveis').onclick();return false;" href="#">
								>
							</html:link>
							&nbsp;
							<%-- <netui:anchor onClick="$('pagina_solicitada').value='${totalPagina}';$('botao_listar_variaveis').onclick();return false;" href="#">
								>>
							</netui:anchor> --%>
							<html:link onClick="$('pagina_solicitada').value='${totalPagina}';$('botao_listar_variaveis').onclick();return false;" href="#">
								>>
							</html:link>
						</c:if>
					</div>
					<br>
					<div class="barra"></div>	
						
						<div class="botao">
							<label class="lblForm">Quantidade : ${totalRegistros}</label> 
							<%-- <netui:button type="submit" action="exportarVariaveis" value="Exportar" styleClass="btNavegacao120" targetScope="target_download"/> --%>
							<html:submit property="Exportar" value="Exportar" styleClass="btNavegacao120" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
	<%-- </netui:form> --%>
</html:form>
	
	<iframe id='target_download' name='target_download' src='' style='display:none;' onload="retornoDownloadFile(this, 'lista_variaveis');"></iframe>
