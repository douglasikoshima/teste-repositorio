<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="lista_modelos_compativeis">
<div class="conteudo_box_top">
	<div class="conteudo_box_top_center">Associar Modelo:</div>
	<div class="conteudo_box_top_esq">
	</div>
	<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg">
						<div style="width:99%;position:relative;" id="lista_modelos">
							<div style="display: none;">*Modelo:</div>
							<c:if test="${container.item.idTipoProduto == 7 || container.item.idTipoProduto == 9 || container.item.idTipoProduto == 10}">
								<input type="hidden" value="true" name="wlw-checkbox_group_key:{actionForm.idsGruposProdutos}OldValue"/>
							</c:if>
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
								<thead>
									<tr>
										<th class="center" width="10px">
											<c:if test="${container.item.idTipoProduto == 7 || container.item.idTipoProduto == 9 || container.item.idTipoProduto == 10}">
												<input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_modelos', '.checkbox_grupo_produto', this.checked);"/>
											</c:if>
										</th>
										<th class="sortable">Modelo</th>
										<th class="sortable">Fabricante</th>
										<th class="sortable">Cor</th>
										<th></th>
									</tr>
								</thead>
							</table>
							<div class="both-scroll" style="height:430px;">
							<netui-data:repeater dataSource="modelos" defaultText="<br><span align='center'>Nenhum Produto encontrado.</span>">
									<netui-data:repeaterHeader>
										<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
											<thead>
												<tr>
													<th class="center" width="10px">
														<c:if test="${container.item.idTipoProduto == 7 || container.item.idTipoProduto == 9 || container.item.idTipoProduto == 10}">
															<input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_modelos', '.checkbox_grupo_produto', this.checked);"/>
														</c:if>
													</th>
													<th class="sortable">Modelo</th>
													<th class="sortable">Fabricante</th>
													<th class="sortable">Cor</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
									</netui-data:repeaterHeader>
									<netui-data:repeaterItem>
										<tr>
											<td class="center">
												<c:choose>
													<c:when test="${container.item.idTipoProduto == 7 || container.item.idTipoProduto == 9 || container.item.idTipoProduto == 10}">
														<input type="checkbox" value="${container.item.idModelo}" class="semBorda belongsToForm checkbox_grupo_produto" id="checkbox_grupo_produto_${container.item.idModelo}" name="wlw-checkbox_group_key:{actionForm.idsGruposProdutos}"/>
													</c:when>
													<c:otherwise>
														<input type="radio"  value="${container.item.idModelo}" class="semBorda belongsToForm checkbox_grupo_produto" id="checkbox_grupo_produto_${container.item.idModelo}" name="wlw-radio_button_group_key:{actionForm.idsGruposProdutos}" onclick="desabilitaCores($('select_cor_${container.item.idModelo}'))"/>
													</c:otherwise>
												</c:choose>
											</td>
											<td>${container.item.nmModelo}</td>
											<td>${container.item.fabricante.nmFabricante}</td>
											<td>
											    <input type="hidden" name="id_cor_request" id="id_cor_field"/>
												<c:choose>
													<c:when test="${container.item.idTipoProduto == 7 || container.item.idTipoProduto == 9 || container.item.idTipoProduto == 10}">
														N/A
													</c:when>
													<c:otherwise>
														<netui:select tabindex="1" tagId="select_cor_${container.item.idModelo}" style="width: 200px;" repeater="true" defaultValue="" dataSource="pageFlow.idCor" optionsDataSource="${container.item.listaCores.corList}" repeatingOrder="default, option" onChange="$('id_cor_field').value = this.value" disabled="true">
															<c:if test="${container.metadata.defaultStage}">
																	<netui:selectOption repeatingType="default" value="">-- Selecione --</netui:selectOption>
															</c:if>
															<c:if test="${container.metadata.optionStage}">
																<netui:selectOption repeatingType="option" value="${container.item.idCor}">
												                     ${container.item.nmCor}
																</netui:selectOption>
															</c:if>
														</netui:select>
													</c:otherwise>
												</c:choose>
											</td>
											<td class="center">
												<netui:anchor action="popupDetalheModelo" onClick="abrirPopup1(this.href, null, 'resultado_busca_produto');return false;">
													<netui:parameter name="id_modelo" value="${container.item.idModelo}"/>
													<img alt="DetalheModelo" src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif"/>
												</netui:anchor>
											</td>
										</tr>
									</netui-data:repeaterItem>
									<netui-data:repeaterFooter>
										</tbody>
									</table>
								</netui-data:repeaterFooter>
							</netui-data:repeater>
							</div>
						</div>		
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
									<netui:anchor action="listarModelosCompativeis" onClick="abrirLink('listaModelosCompativeis', this.href, 'listaModelosCompativeis');return false;">
										<netui:parameter name="id_tipo_produto" value="${id_tipo_produto}"/>
										<netui:parameter name="id_fabricante" value="${id_fabricante}"/>
										<netui:parameter name="id_tecnologia" value="${id_tecnologia}"/>
										<netui:parameter name="pagina_solicitada" value="${no_pagina}"/>
										${no_pagina}
									</netui:anchor>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="barra"></div>	
					
					<div class="botao"><label class="lblForm">Quantidade : ${totalRegistros}</label> 
						<netui:button type="button" onClick="if(!verificaModeloSelecionado(this)){return false};desabilitaCoresSubmit(this); send(this, null, null, 'listaModelosCompativeis')" styleClass="btOk" value="Ok" title="${bundle.default['catalogo.listaModelosCompativeis.OK']}"/>
					</div>
					</div>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
</div>