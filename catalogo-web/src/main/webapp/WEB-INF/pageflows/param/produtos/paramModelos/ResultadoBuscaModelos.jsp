<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<div id="resultado_busca_modelo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
			<div class="conteudo_box_top_esq">
			</div>
			<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg" style="position:relative;">
					<c:if test="${ (nomes_valores_caracteristicas != null && fn:length(nomes_valores_caracteristicas) > 1 ) || (nomes_tecnologias != null && fn:length(nomes_tecnologias) > 1) }">
						<div style="padding-top:10px;padding-bottom: 10px;padding-left:10px;">
							<font style="font-style: italic;">
								<c:if test="${nomes_tecnologias != null && fn:length(nomes_tecnologias) > 1}">
									<strong>Tecnologia</strong>:
									${nomes_tecnologias}
								</c:if>
								&nbsp;
								<c:if test="${nomes_valores_caracteristicas != null && fn:length(nomes_valores_caracteristicas) > 1}">
									<strong>Caracteristica</strong>:
									${nomes_valores_caracteristicas}
								</c:if>
							</font>
						</div>
					</c:if>
					<div style="height:250px;" class="both-scroll">
						<netui-data:repeater dataSource="modelos" defaultText="<br><span align='center'>Modelo não encontrado.</span>">
								<netui-data:repeaterHeader>
									<table cellspacing="0" cellpadding="0" class="tabela-padrao-new tablesorter table_body" >
										<thead>
										<tr height="40">
				
											<th width="40%" class="sortable" style="text-align: left">Nome do Modelo</th>
											<th width="40%" class="sortable" style="text-align: left">Fabricante</th>
											<th width="20%">Fim de Vida</th>
											<th width="1px">Disp</th>
											<th width="1px">Características</th>
											<th width="1px">Multim&iacute;dia</th>
											<!-- <cata:temPermissao acao="alterarModelo">
												<th width="1px">Alterar</th>
											</cata:temPermissao> -->
											<cata:temPermissao acao="copiarModelo">
												<th width="1px">Copiar</th>
											</cata:temPermissao>
											<cata:temPermissao acao="excluirModelo">
												<th width="1px">Excluir</th>
											</cata:temPermissao>
											<th width="1px">&nbsp;</th>
										</tr>
									</thead>
									<tbody>
										</netui-data:repeaterHeader>
										<netui-data:repeaterItem>
										<tr>
											
											<td style="text-align: left;">
												<netui:anchor tagId="carregarAlterarModelo_${container.item.idGrupoProduto}" action="carregarAlterarModelo" onClick="if(abrirLink('novo_modelo', this.href, 'resultado_pesquisa')){clearAndShow('novo_modelo');}return false;">
									 				<netui:parameter name="id_modelo" value="${container.item.idGrupoProduto}"/>
													${container.item.nmGrupoProduto}
												</netui:anchor>
											</td>
											
											<!-- <td style="text-align: left;">${container.item.nmGrupoProduto}</td> -->
											<td>${container.item.nmFabricante}</td>
											<td class="center">
												<c:choose>
													<c:when test="${container.item.inFimVida=='S'}">
														<!--<input id="fim_vida_${container.item.idGrupoProduto}" type="checkbox" class="SemBorda" checked="checked" value="fimVida" onclick="toggleCheckbox($(this).next().href, this.checked, 'resultado_busca_modelo');return false;"/>
														<netui:anchor action="alterarFimVidaModelo" style="display:none;">
											 				<netui:parameter name="id_grupo_produto" value="${container.item.idGrupoProduto}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>-->
														<img alt="Sim" src="/catalogo/static_server/img/bullets/icon-available.png"/>
													</c:when>
													<c:otherwise>
														<!--<input  id="fim_vida_${container.item.idGrupoProduto}" type="checkbox" class="SemBorda" value="fimVida" onclick="toggleCheckbox($(this).next().href, this.checked, 'resultado_busca_modelo');return false;"/>
														<netui:anchor action="alterarFimVidaModelo" style="display:none;">
											 				<netui:parameter name="id_grupo_produto" value="${container.item.idGrupoProduto}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>-->
														<img alt="N&atilde;o" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
													</c:otherwise>
												</c:choose>
												
											</td>
											<td class="center">			
												<c:choose>
													<c:when test="${container.item.inDisponivel=='S'}">
														<!--<input id="disp_${container.item.idGrupoProduto}" type="checkbox" class="SemBorda" checked="checked" value="disp" onclick="toggleCheckbox($(this).next().href, this.checked, 'resultado_busca_modelo');return false;"/>
														<netui:anchor action="alterarDispModelo" style="display:none;">
											 				<netui:parameter name="id_grupo_produto" value="${container.item.idGrupoProduto}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>-->
														<img alt="Sim" src="/catalogo/static_server/img/bullets/icon-available.png"/>
													</c:when>
													<c:otherwise>
														<!--<input  id="disp_${container.item.idGrupoProduto}" type="checkbox" class="SemBorda" value="disp" onclick="toggleCheckbox($(this).next().href, this.checked, 'resultado_busca_modelo');return false;"/>
														<netui:anchor action="alterarDispModelo" style="display:none;">
											 				<netui:parameter name="id_grupo_produto" value="${container.item.idGrupoProduto}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>-->
														<img alt="N&atilde;o" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
													</c:otherwise>
												</c:choose>	
											</td>
											<td class="center">
												<!-- <netui:anchor tagId="popupAlterarCaracteristica" action="popupAlterarCaracteristica" onClick="abrirPopup1(this.href, null, 'resultado_busca_modelo', 'popup1');return false;">
									 				<netui:parameter name="id_modelo" value="${container.item.idGrupoProduto}"/>
													<img src= "/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif" alt="Características">
												</netui:anchor> -->
												<c:choose>
													<c:when test="${container.item.inCaracteristica=='S'}">
														<!--<input id="disp_${container.item.idGrupoProduto}" type="checkbox" class="SemBorda" checked="checked" value="disp" onclick="toggleCheckbox($(this).next().href, this.checked, 'resultado_busca_modelo');return false;"/>
														<netui:anchor action="alterarDispModelo" style="display:none;">
											 				<netui:parameter name="id_grupo_produto" value="${container.item.idGrupoProduto}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>-->
														<img alt="Sim" src="/catalogo/static_server/img/bullets/icon-available.png"/>
													</c:when>
													<c:otherwise>
														<!--<input  id="disp_${container.item.idGrupoProduto}" type="checkbox" class="SemBorda" value="disp" onclick="toggleCheckbox($(this).next().href, this.checked, 'resultado_busca_modelo');return false;"/>
														<netui:anchor action="alterarDispModelo" style="display:none;">
											 				<netui:parameter name="id_grupo_produto" value="${container.item.idGrupoProduto}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>-->
														<img alt="N&atilde;o" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
													</c:otherwise>
												</c:choose>	
											</td>
											<!-- <cata:temPermissao acao="copiarModelo"> -->
												<td class="center">
													<!-- <netui:anchor tagId="popupImagemModelo" action="popupImagemModelo" onClick="abrirPopup1(this.href, null, 'resultado_busca_modelo');return false;">
										 				<netui:parameter name="id_modelo" value="${container.item.idGrupoProduto}"/>
														<img src= "/catalogo/static_server/img/botoes/toolbarButtonGraphics/media/Movie16.gif" alt="Imagem">
													</netui:anchor> -->
													<c:choose>
													<c:when test="${container.item.inMultimidia=='S'}">
														<!--<input id="disp_${container.item.idGrupoProduto}" type="checkbox" class="SemBorda" checked="checked" value="disp" onclick="toggleCheckbox($(this).next().href, this.checked, 'resultado_busca_modelo');return false;"/>
														<netui:anchor action="alterarDispModelo" style="display:none;">
											 				<netui:parameter name="id_grupo_produto" value="${container.item.idGrupoProduto}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>-->
														<img alt="Sim" src="/catalogo/static_server/img/bullets/icon-available.png"/>
													</c:when>
													<c:otherwise>
														<!--<input  id="disp_${container.item.idGrupoProduto}" type="checkbox" class="SemBorda" value="disp" onclick="toggleCheckbox($(this).next().href, this.checked, 'resultado_busca_modelo');return false;"/>
														<netui:anchor action="alterarDispModelo" style="display:none;">
											 				<netui:parameter name="id_grupo_produto" value="${container.item.idGrupoProduto}"/>
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
														</netui:anchor>-->
														<img alt="N&atilde;o" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
													</c:otherwise>
												</c:choose>	
												</td>
											<!-- </cata:temPermissao> -->
											<!-- <cata:temPermissao acao="alterarModelo">
												<td class="center">
													<netui:anchor tagId="carregarAlterarModelo" action="carregarAlterarModelo" onClick="if(abrirLink('novo_modelo', this.href, 'resultado_pesquisa')){habilitar('botao_novo_modelo');clearAndShow('novo_modelo');}return false;">
										 				<netui:parameter name="id_modelo" value="${container.item.idGrupoProduto}"/>
														<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
													</netui:anchor>
												</td>
											</cata:temPermissao> -->
											<cata:temPermissao acao="copiarModelo">
												<td class="center">
													<netui:anchor tagId="carregarCopiarModelo" action="carregarCopiarModelo" onClick="if(abrirLink('novo_modelo', this.href, 'resultado_pesquisa' )){habilitar('botao_novo_modelo');clearAndShow('novo_modelo');}return false;">
										 				<netui:parameter name="id_modelo" value="${container.item.idGrupoProduto}"/>
														<img src= "/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif" alt="Copiar">
													</netui:anchor>
												</td>
											</cata:temPermissao>
											<cata:temPermissao acao="excluirModelo">
												<td class="center">
													<netui:anchor action="popupApagarModelo" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa')){$('novo_modelo').innerHTML='';habilitar('botao_novo_modelo');}return false;">
														<netui:parameter  name="id_modelo" value="${container.item.idGrupoProduto}"/>
														<img alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif"/>
													</netui:anchor>
												</td>
											</cata:temPermissao>
											<td class="center">
												<netui:anchor action="popupDetalheModelo" onClick="abrirPopup1(this.href, null, 'resultado_busca_modelo');return false;">
													<netui:parameter name="id_modelo" value="${container.item.idGrupoProduto}"/>
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
						<br clear="all"/>
					<div class="barra"></div>
					<table class="tabelaIcones">
						<tr style="float: left;">
							<td><label>Ícones:</label></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><img src="/catalogo/static_server/img/bullets/icon-available.png" alt="Sim" /></td>
							<td>Sim</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><img src="/catalogo/static_server/img/bullets/icon-unavailable.png" alt="N&atilde;o" /></td>
							<td>N&atilde;o</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>	
							<td><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Modelo" /></td>
							<td>Excluir Modelo</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><img src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif" alt="Detalhes do Modelo"/></td>
							<td>Detalhes do Modelo</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>							
							<td><img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif" alt="Copiar Modelo" /></td>
							<td>Copiar Modelo</td>
						</tr>
					</table>
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
										<netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar').onclick();return false;" href="#">
											${no_pagina}
										</netui:anchor>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<div class="barra"></div>
						<div class="botao">
							<label class="lblForm" >Quantidade : ${totalRegistros}</label>
							<cata:temPermissao acao="criarModelo">
								<input id="botao_novo_modelo" type="button" onclick="if(abrirLink('novo_modelo', $(this).next('a').href, 'right_section', [$('id_tipo_produto'), $('select_fabricante')])){$('novo_modelo').show();}return false;" class="btNavegacao74" value="Novo" title="${bundle.default['catalogo.ResultadoBuscaModelos.Novo']}" />
								<netui:anchor action="abrirNovoModelo" styleClass="hide" />
							</cata:temPermissao>
						</div>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>