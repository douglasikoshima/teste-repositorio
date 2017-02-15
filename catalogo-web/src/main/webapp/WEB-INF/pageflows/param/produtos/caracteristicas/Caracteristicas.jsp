<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>

	<div id="pesquisar_caracteristicas" >
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Característica:</div>
			<div class="conteudo_box_top_esq">
			</div>
			<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg" style="position:relative;">
						<netui:form action="pesquisarCaracteristicas">
						<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
						<table>
							<tr>
								<td><div class="label-form-bold" style="text-align: left; width: 50px">Caracter&iacute;stica:</div></td>
								<td>
									<div class="hide min_size_required_message">Obrigatório informar, no mínimo, 3 caracteres para o campo Característica.</div>
									<div class="hide min_size_required_value">3</div>
									<netui:textBox tagId="nome_caracteristica" onKeyPress="if(CapturaTeclaEntra(event,'botao_pesquisar')){return false;}" dataSource="actionForm.nomeCaracteristica" size="60" styleClass="min_size_required"/>
								</td>

								<td><netui:button tagId="botao_pesquisar" onMouseDown="$('pagina_solicitada').value='';return false;" onClick="if(send(this, 'right_section', null, 'right_section')){$('tbody_caracteristicas').immediateDescendants().invoke('remove');habilitar('botao_novo_caracteristica');}return false;" value="Pesquisar" styleClass="btNavegacao74" title="${bundle.default['catalogo.Caracteristica.Pesquisar']}" /></td>
								<td><input title="${bundle.default['catalogo.global.Limpar']}" type="button" onClick="limparForm(this);$('botao_pesquisar').onclick();return false;" value="Limpar" class="btNavegacao74" /></td>

							</tr>
						</table>
						<div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161286" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						<br clear="all"/>
						</netui:form>
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" >
										<thead>
										<tr>
				
											<th class="sortable dynamic_width word-wrap">Caracter&iacute;stica</th>
											<th class="sortable dynamic_width word-wrap">Descri&ccedil;&atilde;o</th>
											<th>Disp</th>
											<th >Filtro</th>
											<cata:temPermissao acao="alterarCaracteristica">
												<th>Alterar</th>
											</cata:temPermissao>
											<cata:temPermissao acao="excluirCaracteristica">
												<th>Excluir</th>
											</cata:temPermissao>
											<cata:temPermissao acao="consultarValoresCaracteristica">
												<th>Valores</th>
											</cata:temPermissao>
				
										</tr>
									</thead>
							</table>
							<div class="both-scroll" style="height:360px;">
							<netui-data:repeater dataSource="caracteristicas" defaultText="<br><span align='center'>Característica não encontrada.</span>">
								<netui-data:repeaterHeader>
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
										<thead style="visibility:;">
											<tr>
					
												<th width="50%">Caracter&iacute;stica</th>
												<th width="50%">Descri&ccedil;&atilde;o</th>
												<th >Disp</th>
												<th >Filtro</th>
												<cata:temPermissao acao="alterarCaracteristica">
													<th>Alterar</th>
												</cata:temPermissao>
												<cata:temPermissao acao="excluirCaracteristica">
													<th>Excluir</th>
												</cata:temPermissao>
												<cata:temPermissao acao="consultarValoresCaracteristica">
													<th>Valores</th>
												</cata:temPermissao>
					
											</tr>
										</thead>
									<tbody id="tbody_caracteristicas">
										</netui-data:repeaterHeader>
										<netui-data:repeaterItem>
										<tr>
											<td style="text-align: left;" class="word-wrap">${container.item.nmCaracteristica}</td>
											<td style="text-align: left;" class="word-wrap">${container.item.descricao}</td>
											<td class="center">
												<c:choose>
													<c:when test="${container.item.inDisponivel=='S'}">
														<input id="disp_${container.item.idCaracteristica}" type="checkbox" class="SemBorda" checked="checked" value="disp" onclick="toggleCheckbox($(this).next().href, this.checked, 'right_section');return false;"/>
														<netui:anchor action="disponibilizarCaracteristica" style="display:none;">
											 				<netui:parameter name="id_caracteristica" value="${container.item.idCaracteristica}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>
													</c:when>
													<c:otherwise>
														<input  id="disp_${container.item.idCaracteristica}" type="checkbox" class="SemBorda" value="disp" onclick="toggleCheckbox($(this).next().href, this.checked, 'right_section');return false;"/>
														<netui:anchor action="disponibilizarCaracteristica" style="display:none;">
											 				<netui:parameter name="id_caracteristica" value="${container.item.idCaracteristica}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>
													</c:otherwise>
												</c:choose>
											</td>
											<td class="center">
												<c:choose>
													<c:when test="${container.item.inSimulador == 'S'}">
														<input id="filtro_${container.item.idCaracteristica}" type="checkbox" class="SemBorda" checked="checked" value="filtro" onclick="toggleCheckbox($(this).next().href, this.checked, 'right_section');return false;"/>
														<netui:anchor action="ativarFiltroCaracteristica" style="display:none;">
											 				<netui:parameter name="id_caracteristica" value="${container.item.idCaracteristica}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${container.item.inDisponivel=='S'}">
																<input id="filtro_${container.item.idCaracteristica}" type="checkbox" class="SemBorda"value="filtro" onclick="toggleCheckbox($(this).next().href, this.checked, 'right_section');return false;"/>
																<netui:anchor action="ativarFiltroCaracteristica" style="display:none;">
													 				<netui:parameter name="id_caracteristica" value="${container.item.idCaracteristica}"/>
																	<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
																</netui:anchor>
															</c:when>
															<c:otherwise>
																<input id="filtro_${container.item.idCaracteristica}" type="checkbox" class="SemBorda" value="filtro" onclick="toggleCheckbox($(this).next().href, this.checked, 'right_section');return false;" disabled="disabled" />
																<netui:anchor action="ativarFiltroCaracteristica" style="display:none;">
													 				<netui:parameter name="id_caracteristica" value="${container.item.idCaracteristica}"/>
																	<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
																</netui:anchor>
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>						
											</td>
											<cata:temPermissao acao="alterarCaracteristica">
												<td class="center ico icoAlterar">
										 			<netui:anchor tagId="AlterarCaracteristica" action="carregarAlterarCaracteristica" onClick="if(abrirLink('altCaracteristica', this.href, 'right_section')){habilitar('botao_novo_caracteristica');$('nova_caracteristica').hide();clearAndShow('altCaracteristica');}return false;"
										 				title="Alterar" onMouseUp="return false;">
										 				<netui:parameter name="id_caracteristica" value="${container.item.idCaracteristica}"/>
										 				<div></div>
													</netui:anchor>
												</td>
											</cata:temPermissao>
											<cata:temPermissao acao="excluirCaracteristica">
												<td class="center ico icoExcluir">
													<netui:anchor action="popupApagarCaracteristica" onClick="abrirPopup1(this.href);return false;"
															title="Excluir" >
														<netui:parameter  name="id_caracteristica" value="${container.item.idCaracteristica}"/>
														<div></div>
													</netui:anchor>
												</td>
											</cata:temPermissao>
											<cata:temPermissao acao="consultarValoresCaracteristica">
												<td class="center ico icoGrid">
													<netui:anchor action="ValoresCaracteristica" onClick="abrirPopup1(this.href);return false;"
															title="Valores da Característica">
														<netui:parameter  name="id_caracteristica" value="${container.item.idCaracteristica}"/>
														<netui:parameter  name="pagina_solicitada" value="1"/>
														<div></div>
													</netui:anchor>
												</td>
											</cata:temPermissao>
										</tr>
										</netui-data:repeaterItem>
									<netui-data:repeaterFooter>
									</tbody>
								</table>
									</netui-data:repeaterFooter>
						</netui-data:repeater>
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
										<netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar').click();return false;" href="">
											${no_pagina}
										</netui:anchor>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<div class="barra"></div>
						<div class="botao">
							<label class="lblForm" >Quantidade de Caracteristicas: ${size_caracteristicas}</label>
							<cata:temPermissao acao="adicionarCaracteristica">
								<input id='botao_novo_caracteristica' type="button" onclick="$('altCaracteristica').hide(); $('nova_caracteristica').show();$('nova_caracteristica').scrollTo();$('nomeCaracteristica').focus();desabilitar(this);" class="btNavegacao74" value="Novo" title="${bundle.default['catalogo.Caracteristica.Novo']}"/>
							</cata:temPermissao>
						</div>
					</div>
				</div>
				<div class="conteudo_box_bottom"></div>
			</div>
		</div>
	</div>
	<br>
	<div id="altCaracteristica">
	</div>
	<div id="nova_caracteristica" class="secao_conteudo"  style="display: none;">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Cadastro de Característica:</div>
				<div class="conteudo_box_top_esq"></div>
				<div class="conteudo_box_top_dir openclose"><img id="img_MaxMin" onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
				alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
						<netui:form action="addCaracteristica">
						<br>
						<div class="legendaObrigatorio">(*) Campo Obrigatório</div>
						<table>
							<tr>
								<td width="20%">
									<div class="label-form-bold label_required ">Nome&nbsp;Caracter&iacute;stica:<font size="1px" color="#EEB422" valign="center">*</font></div> 
									<netui:textBox tagId="nomeCaracteristica" dataSource="actionForm.nomeCaracteristica" maxlength="200" styleClass="required editavel" onKeyPress="return semPontoVirgula(event);" />
								</td>
							</tr>
							<tr>
								<td width="50%">
									<div class="label-form-bold">Descri&ccedil;&atilde;o: &nbsp;</div>
									<netui:textArea styleClass="editavel" dataSource="actionForm.descricaoCaracteristica" cols="47" rows="5" onKeyUp="maxSize(200, this);"/>
								</td>
							</tr>
						</table>
						<div class="barra">
						</div>
						<div style="text-align: right;" colspan="4">
							<netui:button type="button" onClick="clearEditando();send(this, null, null, 'nova_caracteristica');setEditando();" styleClass="btNavegacao74" value="Gravar" title="${bundle.default['catalogo.Caracteristica.Gravar']}"/>
							<input title="" type="button" value="Cancelar" class="btNavegacao74 clearEditavel" onclick="limparForm(this);$('nova_caracteristica').hide();habilitar('botao_novo_caracteristica')" title="${bundle.default['catalogo.Caracteristica.Cancelar']}"/>
						</div>
						</netui:form>
					</div>
				</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<!-- Para resolver o problema estranho do IE de renderização no caso de conexões lentas -->
<img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="doSuccess();"/>
