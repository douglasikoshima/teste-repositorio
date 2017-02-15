<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui:form action="exportarRestricoes" target="target_download">
	<div id="resultado_busca_plano">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Variaveis do Servi&ccedil;o:</div>
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
							<netui:checkBoxGroup dataSource="actionForm.idsServicos" >
								<netui:hidden dataSource="actionForm.idPlataforma" dataInput="${idPlataforma}"/>
								<c:set var="servico" value="" />
								<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" id="TabelaRelacionamentosRecentes">
									<thead>
										<tr>
											<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_servico', this.checked);"/></th>
											<th class="sortable">Nome do Servi&ccedil;o</th>
											<th class="sortable">Plataforma</th>
											<th class="sortable">Tecnologia</th>
											<th class="sortable">Tipo de Cliente</th>
											<th class="sortable">Tipo de Assinatura</th>
											<th class="sortable">Canal</th>
											<th class="sortable">Segmento</th>
											<th class="sortable">UF</th>
											<th class="sortable">DDD</th>
										</tr>
									</thead>
								</table>
								<div class="both-scroll" style="height:300px">
									<netui-data:repeater dataSource="variaveis" defaultText="<br><span align='center'>Nenhum Plano encontrado.</span>">
										<netui-data:repeaterHeader>
											<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
												<thead>
													<tr>
														<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_servico', this.checked);"/></th>
														<th class="sortable">Nome do Servi&ccedil;o</th>
														<th class="sortable">Plataforma</th>
														<th class="sortable">Tecnologia</th>
														<th class="sortable">Tipo de Cliente</th>
														<th class="sortable">Tipo de Assinatura</th>
														<th class="sortable">Canal</th>
														<th class="sortable">Segmento</th>
														<th class="sortable">UF</th>
														<th class="sortable">DDD</th>
													</tr>
												</thead>
											<tbody>
										</netui-data:repeaterHeader>
										<netui-data:repeaterItem>
											<c:if test="${container.item.codigoArea != null}">
												<netui-data:repeater dataSource="container.item.codigoArea">
													<tr align="left">
														<td style="text-align: center;">
															<c:if test="${plano != container.container.item.nmComercial}">
																<netui:checkBoxOption value="${container.container.item.idServico}" tagId="checkbox_servico_${container.container.item.nmUf}" styleClass="semBorda belongsToForm checkbox_servico" labelStyleClass="hide"/>
															</c:if>
														</td>
														<td style="text-align: left;">
															<c:if test="${plano != container.container.item.nmComercial}">
																${container.container.item.nmComercial}
																<c:set var="plano" value="${container.container.item.nmComercial}"/>
															</c:if>
														</td>
														<td style="text-align: left;">${container.container.item.nmPlataforma}</td>
														<td style="text-align: left;">${container.container.item.nmTecnologia}</td>
														<td style="text-align: left;">${container.container.item.nmTipoCliente}</td>
														<c:if test="${idPlataforma != 1}">
															<td style="text-align: left;">${container.container.item.descTpAssinatura}</td>
														</c:if>
														<c:if test="${idPlataforma == 1}">
															<td style="text-align: left;">${container.container.item.sgCarteira}</td>
														</c:if>
														<td style="text-align: left;">${container.container.item.nmCanal}</td>
														<td style="text-align: left;">${container.container.item.sgSegmento}</td>
														<td style="text-align: left;">${container.container.item.nmUf}</td>
														<td style="text-align: left;">${container.item}</td>
													</tr>
												</netui-data:repeater>
											</c:if>
										</netui-data:repeaterItem>
										<netui-data:repeaterFooter>
													</tbody>
											</table>
										</netui-data:repeaterFooter>
									</netui-data:repeater>
								</div>
							
							</netui:checkBoxGroup>
						</div>
						<div class="paginacao" style="width:99%;" align="right">
							<c:set var="begin" value="${(paginaAtual>5)?(paginaAtual-5):(1)}" />
							<c:set var="end" value="${(paginaAtual<(totalPagina-5))?(begin+9):(totalPagina)}" />
							<c:if test="${begin > 1}">
								<netui:anchor onClick="$('pagina_solicitada').value='1';$('botao_listar_variaveis').onclick();;return false;" href="#">
									<<
								</netui:anchor>
								&nbsp;
								<netui:anchor onClick="$('pagina_solicitada').value='${paginaAtual-1}';$('botao_listar_variaveis').onclick();return false;" href="#">
									<
								</netui:anchor>
							</c:if>
							<c:forEach begin="${begin}" end="${end}" var="no_pagina">
								<c:choose>
									<c:when test="${no_pagina == paginaAtual}">
										<netui:anchor styleClass="selected" onClick="return false;" href="">
											${no_pagina}
										</netui:anchor>
									</c:when>
									<c:otherwise>
										<netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_listar_variaveis').onclick();return false;" href="#">
											${no_pagina}
										</netui:anchor>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${end < totalPagina}">
								<netui:anchor onClick="$('pagina_solicitada').value='${paginaAtual+1}';$('botao_listar_variaveis').onclick();return false;" href="#">
									>
								</netui:anchor>
								&nbsp;
								<netui:anchor onClick="$('pagina_solicitada').value='${totalPagina}';$('botao_listar_variaveis').onclick();return false;" href="#">
									>>
								</netui:anchor>
							</c:if>
						</div>
						
						<div class="barra"></div>	
						<div class="botao">
							<label class="lblForm">Quantidade : ${totalRegistros}</label> 
							<netui:button type="submit" value="Exportar" styleClass="btNavegacao120" targetScope="target_download"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>	
</netui:form>
<iframe id='target_download' name='target_download' src='' style='display:none;' onload="retornoDownloadFile(this, 'lista_variaveis');"></iframe>
