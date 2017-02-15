<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${novo_modelo != null}">
	<!-- c:set var="close" value="false" / -->
	<img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="$('carregarAlterarModelo_${id_modelo}').onclick();" />
</c:if>
<netui-temp:template templatePage="/templates/popupPadrao.jsp">
	<input type="hidden" id="alturaPopup" value="410" />
	<div id="popup_alterar_caracteristicas">
		<netui-temp:section name="conteudo">
			<input type="hidden" id="caracteristicas_selecionadas" value='${caracteristicas_selecionadas}' />
			<c:set var="nomeBox" scope="application" value="Caracteristicas" />
			<jsp:include page="/templates/box_pre.jsp" />
			<br clear="all" />
			<div id="pesquisaDiv">
				<div class="label-form-bold" style="width:100px;">Caracter&iacute;stica:</div>
				<input id="pesquisaInput" type="text" maxlength="70" style="width: 300px;" value="${pesquisaInput}" onkeypress="if( event.keyCode == 32 && $F('pesquisaInput').empty() ){ return false; } else { if( event.keyCode == 13 ) { $('pesquisaButton').onclick(); } }" />
				<netui:button tagId="pesquisaButton" type="button" styleClass="btNavegacao74" value="Pesquisar" title="${bundle.default['catalogo.global.form.Pesquisar']}"
					onClick="if( $F('pesquisaInput').strip().length < 3 && $F('pesquisaInput').strip().length > 0 ){ posicionarDivErros('div_erros_popup'); clearErrors(); addError('Por favor, informar pelo menos 3 caracteres para pesquisa.', true, true); } else { $('pesquisarAnchor').onclick(); }"/>
				<netui:anchor tagId="pesquisarAnchor" action="popupAlterarCaracteristica" onClick="if(checkSelecionarCaracteristicasModelo($('caracteristicas_selecionadas'),null)){abrirPopup1(this.href, [$('caracteristicas_selecionadas'), $('valores_caracteristicas_selecionadas'), $('pesquisaInput')], 'right_section');}return false;" styleClass="hide">
					<netui:parameter name="pagina_solicitada" value="1" />
					<netui:parameter name="id_modelo" value="${id_modelo}" />
				</netui:anchor>
			</div>
			<div align="left largePopup">
				<c:choose>
					<c:when test="${empty caracteristicas}">
						<img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="posicionarDivErros('div_erros_popup'); clearErrors(); addError('Não foram encontrados valores para a pesquisa informada.', true, true);" />
					</c:when>
					<c:otherwise>
						<table width="100%">
							<tr>
								<td valign="top" width="50%">
									<div class="vertical-scroll" style="height:280px;width:100%;">
										<form class="fleft">
											<table class="tabConteudoGeral">
												<netui:checkBoxGroup dataSource="pageFlow.caracteristicasSelecionadasAlterar" optionsDataSource="${caracteristicas}" repeater="true">
													<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
														<td width="10px">
															<netui:checkBoxOption onClick="if(checkSelecionarCaracteristicasModelo($('caracteristicas_selecionadas'),this)){selecionarCaracteristica('caracteristicas_selecionadas', this);mostrarValoresCaracteristica('div_valores','${container.item.idCaracteristica}'); if ( $('caracteristicas_cb_${container.item.idCaracteristica}').checked ) { $('novoValorDiv').removeClassName('hide');$('idCaracteristica').value=${container.item.idCaracteristica}; } else { $('novoValorDiv').addClassName('hide'); } }else{return false;}"
																tagId="caracteristicas_cb_${container.item.idCaracteristica}" value="${container.item.idCaracteristica}"
																styleClass="semBorda" />
														</td>
														<td class="word-wrap">
															<label id="caracteristicaLabel_${container.item.idCaracteristica}" for="caracteristica_cb_${container.item.idCaracteristica}" onclick="mostrarValoresCaracteristica('div_valores','${container.item.idCaracteristica}'); if ( $('caracteristicas_cb_${container.item.idCaracteristica}').checked ) { $('novoValorDiv').removeClassName('hide');$('idCaracteristica').value=${container.item.idCaracteristica}; } else { $('novoValorDiv').addClassName('hide'); }">${container.item.nmCaracteristica}</label>
														</td>
													</tr>
												</netui:checkBoxGroup>
											</table>
										</form>
									</div>
								</td>
								<td valign="top" width="50%">
									<div id="div_valores" class="fleft vertical-scroll" style="height:280px;width:100%;">
										<c:forEach items="${caracteristicas}" var="caracteristica">
											<table id="valores_caracteristica_${caracteristica.idCaracteristica}" style="display:none;" class="tabConteudoGeral" width="200px">
												<tr>
													<td class="titulo word-wrap" colspan="2">VALORES PARA: ${caracteristica.nmCaracteristica}</td>
												</tr>
												<c:if test="${caracteristica.listaValorCaracteristica.valorCaracteristicaList !=null }">
													<netui:checkBoxGroup dataSource="pageFlow.valoresCaracteristicasSelecionadasAlterar" optionsDataSource="${caracteristica.listaValorCaracteristica.valorCaracteristicaList}" repeater="true">
														<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
															<td width="10%" align="center">
																<netui:checkBoxOption onClick="selecionarValorCaracteristica('caracteristicas_selecionadas', ${container.item.idCaracteristica}, this)" tagId="valores_caracteristicas_cb_${container.item.idValorCaracteristica}"
																	value="${container.item.idValorCaracteristica}" styleClass="semBorda checkbox" />
															</td>
															<td width="90%" class="word-wrap">
																<label for="valores_caracteristicas_cb_${container.item.idValorCaracteristica}">${container.item.valor}</label>
															</td>
														</tr>
													</netui:checkBoxGroup>
												</c:if>
											</table>
										</c:forEach>
									</div>
								</td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>
				<br clear="all" />
				<div style="position: absolute; bottom: 2px;">
					<div id="novoValorDiv" class="botao hide">
						<input id="idCaracteristica" type="hidden" />
						<input id="paginaSelecionada" type="hidden" value="${paginaAtual}"/>
						<netui:button type="button" onClick="abrirPopup2($(this).next('a').href, [$('idCaracteristica'), $('paginaSelecionada'), $('caracteristicas_selecionadas')], 'div_erros_popup');" styleClass="btNavegacao74" value="Novo Valor" title="Novo Valor"/>
						<netui:anchor action="popupNovoValorCaracteristica" />
					</div>
					<div class="paginacao" style="float:right;margin-right:5px;" align="right">
						<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">
									<netui:anchor tagId="pagina${no_pagina}" action="popupAlterarCaracteristica" styleClass="selected" onClick="if(checkSelecionarCaracteristicasModelo($('caracteristicas_selecionadas'),null)){abrirPopup1(this.href, [$('caracteristicas_selecionadas'), $('valores_caracteristicas_selecionadas'), $('pesquisaInput')], 'right_section');}return false;">
										<netui:parameter name="pagina_solicitada" value="${no_pagina}" />
										<netui:parameter name="id_modelo" value="${id_modelo}" />
										${no_pagina}
									</netui:anchor>
								</c:when>
								<c:otherwise>
									<netui:anchor tagId="pagina${no_pagina}" action="popupAlterarCaracteristica" onClick="if(checkSelecionarCaracteristicasModelo($('caracteristicas_selecionadas'),null)){abrirPopup1(this.href, [$('caracteristicas_selecionadas'), $('valores_caracteristicas_selecionadas'), $('pesquisaInput')], 'right_section');}return false;">
										<netui:parameter name="pagina_solicitada" value="${no_pagina}" />
										<netui:parameter name="id_modelo" value="${id_modelo}" />
										${no_pagina}
									</netui:anchor>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<br clear="all" /><br />
					<div>
						<table width="100%">
							<tr>
								<td valign="bottom" width="60%">
									<div style="float:left;margin-left:2px;" align="left">OBS: Para alterar o Valor da Característica que já está
										associada ao Modelo, é necessário clicar no NOME da característica e não no checkbox relativo a mesma.
									</div>
								</td>
								<td valign="bottom" width="40%">
									<div class="botao" style="position: absolute; bottom: 5px;">
										<input class="btOk" type="button" onclick="if(checkSelecionarCaracteristicasModelo($('caracteristicas_selecionadas'),null)){abrirLink(null, $(this).next('a').href, 'div_erros_popup', [$('caracteristicas_selecionadas'), $('valores_caracteristicas_selecionadas')]); }"
											value="OK" title="${bundle.default['catalogo.popupAlterarCaracteristicas.OK']}" />
										<netui:anchor action="alterarCaracteristicasModelo">
											<netui:parameter name="id_modelo" value="${id_modelo}" />
											<netui:parameter name="novo_modelo" value="${novo_modelo}" />
										</netui:anchor>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<jsp:include page="/templates/box_pos.jsp" />
		</netui-temp:section>
	</div>
	<!-- <img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="clearAndShow('novo_modelo');"/> -->
</netui-temp:template>
