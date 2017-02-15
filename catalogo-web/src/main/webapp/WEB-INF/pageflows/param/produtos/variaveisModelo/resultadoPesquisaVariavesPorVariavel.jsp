<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="resultado_pesquisa_variaveis_modelo" style="position:relative;">
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
				<div style="width: 100%; position:relative;">		
					<netui:form action="associarVariaveisModelo" tagId="lista_variaveis" target="target_return_tela">
						<netui:checkBoxGroup dataSource="actionForm.idsVariaveis" >
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
								<thead>
									<tr>
										<th class="center" style="width: 30px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_variaveis', '.checkbox', this.checked);"/></th>
										<th class="sortable">Modelo</th>
										<th class="sortable">Fabricante</th>
									</tr>
								</thead>
							</table>
							<div class="both-scroll" style="height:300px;">
								<netui-data:repeater dataSource="variaveis" defaultText="<br><span align='center'>Nenhum Variavel encontrada.</span>">
									<netui-data:repeaterHeader>
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
										<thead>
											<tr>
												<th class="center" style="width: 30px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_variaveis', '.checkbox', this.checked);"/></th>
												<th class="sortable">Modelo</th>
												<th class="sortable">Fabricante</th>
											</tr>
										</thead>
										<tbody>
										</netui-data:repeaterHeader>
											<netui-data:repeaterItem>
												<c:set value="true" var="firstPass"/>
													<tr>
														<td class="center" style="width: 30px; padding-left: 10px">
															<netui:checkBoxOption value="${container.item.idVariavel}" tagId="checkbox_${container.container.item.idVariavel}" styleClass="semBorda belongsToForm checkbox" labelStyleClass="hide" />
														</td>
														<td class="center">${container.item.modelo}</td>
														<td class="center">${container.item.fabricante}</td>
													</tr>
													<c:set value="false" var="firstPass"/>
											</netui-data:repeaterItem>
										<netui-data:repeaterFooter>
										</tbody>
									</table>
										</netui-data:repeaterFooter>
								</netui-data:repeater>
							</div>
						</netui:checkBoxGroup>
					</netui:form>
				</div>
			</div>
			<br>
			<div class="paginacao" style="width:99%;" align="right">
				<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
					<c:choose>
						<c:when test="${no_pagina == paginaAtual}">
							<netui:anchor styleClass="selected" onClick="return false;" href="">
								${no_pagina}
							</netui:anchor>
						</c:when>
						<c:otherwise>
							<netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_cabecalho_matriz_oferta').onclick();return false;" href="#">
								${no_pagina}
							</netui:anchor>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div class="barra"></div>
			<div class="botao">
				<label>&nbsp;&nbsp;</label>
				<label class="lblForm" >Quantidade : ${totalRegistros}</label>
				<span>&nbsp;</span>
				<netui:button tagId="botao_remover_associacao_variaveis" type="button" onClick="if(send(this, 'resultado_pesquisa', null, 'resultado_pesquisa')){};return false" styleClass="btNavegacao74" value="Desassociar" alt="${bundle.default['catalogo.variavel.modelo.botao.RemoverAssociar']}" title="${bundle.default['catalogo.variavel.modelo.botao.RemoverAssociar']}"/>
				<span>&nbsp;</span>
				<netui:button tagId="botao_associar_variaveis_modelo" type="button" onClick="if(send(this, 'resultado_pesquisa', null, 'resultado_pesquisa')){};return false" styleClass="btNavegacao74" value="Associar" alt="${bundle.default['catalogo.variavel.modelo.botao.Associar']}" title="${bundle.default['catalogo.variavel.modelo.botao.Associar']}"/>
			</div>
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
</div>
<iframe id='target_return_tela' name='target_return_tela' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_pesquisa_popup');"></iframe>							
