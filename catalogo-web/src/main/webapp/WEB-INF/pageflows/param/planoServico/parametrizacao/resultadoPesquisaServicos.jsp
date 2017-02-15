<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="resultado_busca_servicos" style="position:relative;">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa Por Servi&ccedil;o: </div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
			<div style="width:99%;position:relative;" id="lista_servicos">		
					    <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/gravarAssociacaoCategoria.do" target="target_download2">
						
				 		<html:link styleId="url_associacao" action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/gravarAssociacaoCategoria.do" styleClass="display: none"></html:link>
						<html:link styleId="url_ativacao" action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/gravarAtivacaoWiFi.do" styleClass="display: none"></html:link>
						
						<html:hidden property="idPlataforma" value="${idPlataforma}"/>
						<html:hidden property="idSistema" value="${idSistema}"/>
						<html:hidden property="idCategoria" value="" styleId="hidden_idCategoria" /> 
						<div style="width:99%;" id="lista_servicos">										
<!-- 							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
								<thead>
									<tr>
										<th rowspan="2" class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
										<th rowspan="2" class="sortable">Nome T&eacute;cnico</th>
										<th rowspan="2" class="sortable">Nome Comercial</th>
										<th rowspan="2" nowrap="nowrap" class="sortable">Grupo de <br>Serviços Cat&aacute;logo</th>
										<th rowspan="2" class="sortable">Tipo Servi&ccedil;o</th>
										<th colspan="2"><label style="cursor: help" title="Limites para ativações definidas no Catalogo">Limite para ativa&ccedil;&otilde;es</label></th>
										<th rowspan="2">Wi-Fi Ativo</th>
										<th rowspan="2">Disp</th>
										<th rowspan="2" title="Alterar" >Alt.</th>
									</tr>
									<tr>
										<th>Padr&atilde;o</th>
										<th>M&aacute;ximo</th>
									</tr>
								</thead>
							</table> -->
							<div class="both-scroll" style="height:480px;">
								<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="TabelaRelacionamentosRecentes">
										<thead>
											<tr>
												<th rowspan="2" class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
												<th rowspan="2" class="sortable">Nome T&eacute;cnico</th>
												<th rowspan="2" class="sortable">Nome Comercial</th>
												<th rowspan="2" nowrap="nowrap" class="sortable">Grupo de <br>Serviços Cat&aacute;logo</th>
												<th rowspan="2" class="sortable">Tipo Servi&ccedil;o</th>
												<th colspan="2"><label title="Teste Calixto">Limite para ativa&ccedil;&otilde;es</label></th>
												<th rowspan="2">Wi-Fi Ativo</th>
												<th rowspan="2">Disp</th>
												<th rowspan="2" title="Alterar" >Alt.</th>
											</tr>
											<tr>
												<th>Padr&atilde;o</th>
												<th>M&aacute;ximo</th>
											</tr>
										</thead>
								<tbody>
								<logic:iterate id="iterateServicos" property="listaServicoParametrizacao" name="servicoForm">
									<c:set value="true" var="firstPass"/>
										<tr>
											<td class="center">
												<html:checkbox property="idsServicos" styleId="check_ids_sistema"
												value="${iterateServicos.idServico}" styleClass="semBorda belongsToForm checkbox_modelo"/>											
										    </td>
											<td>
												<c:if test="${firstPass == true}">
													${iterateServicos.cdServico}
												</c:if>
											</td>
											<td>${iterateServicos.nmComercial}</td>
											<td>${iterateServicos.nmCategoriaCatalogo}</td>
											<td>${iterateServicos.dscTipoServico}</td>
											<td>${iterateServicos.qtdMinAtivCatalogo}</td>
											<td>${iterateServicos.qtdMaxAtivCatalogo}</td>
											<td class="center">
												<c:choose>
													<c:when test="${iterateServicos.ativaWifi == 'S'}">
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
													</c:when>
													<c:otherwise>
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
													</c:otherwise>
												</c:choose>
											</td>
											<td class="center">
												<c:choose>
													<c:when test="${iterateServicos.inDisponibilidadeCatalogo == 'S'}">
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
													</c:when>
													<c:otherwise>
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
													</c:otherwise>
												</c:choose>
											</td>
											<td class="center">
												<fmt:formatDate value="${iterateServicos.dtAlteracao.time}" pattern="dd/MM/yyyy HH:mm:ss" var="data_alteracao" scope="request"/>									 			
									 			<cata:temPermissao acao="alterarCategoriaPlanosServicos">										 													 				
										 				<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/carregarAlterarServico.do?id_plataforma=${idPlataforma}&id_servico=${iterateServicos.idServico}
										 				&id_tpServico=${iterateServicos.idTpServico}&cd_servico=${iterateServicos.cdServico}&nm_comercial=${iterateServicos.nmComercial}&id_categoriaCatalogo=${iterateServicos.idCategoriaCatalogo}
										 				&nm_categoriaCatalogo=${iterateServicos.nmCategoriaCatalogo}&nm_categoriaLegado=${iterateServicos.nmCategoriaLegado}&dsc_tpServico=${iterateServicos.dscTipoServico}&qtd_minAtivCatalogo=${iterateServicos.qtdMinAtivCatalogo}
														&qtd_maxAtivCatalogo=${iterateServicos.qtdMaxAtivCatalogo}&qtd_mimAtivLegado=${iterateServicos.qtdMinAtivLegado}&qtd_maxAtivLegado=${iterateServicos.qtdMaxAtivLegado}&nm_usuarioAlteracao=${iterateServicos.nmUsuarioAlteracao}
														&indisponibilidade_catalogo=${iterateServicos.inDisponibilidadeCatalogo}&indisponibilidade_legado=${iterateServicos.inDisponibilidadeLegado}&ativa_wifi=${iterateServicos.ativaWifi}&dt_alteracao=${data_alteracao}"										 				 
										 				onClick="abrirLink('alterar_servicos', this.href, 'right_section');return false;" title="Alterar" onmouseup="return false;">
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar"/>
														</html:link>										 														 																
									 			</cata:temPermissao>
											</td>
										</tr>
										<c:set value="false" var="firstPass"/>	
									</logic:iterate>								
								</tbody> 
							</table>
						</div>
						</div>
						<div class="paginacao" style="width:99%;" align="right">
							<c:set var="begin" value="${(paginaAtual>5)?(paginaAtual-5):(1)}"/>
							<c:set var="end" value="${(paginaAtual<(totalPagina-5))?(begin+9):(totalPagina)}"/>
							<c:if test="${begin > 1}">
								<html:link onClick="$('pagina_solicitada').value='1';$('botao_pesquisar_parametrizacao').onclick();;return false;" href="#">
									<<
								</html:link onClick="$('pagina_solicitada').value='1';$('botao_pesquisar_parametrizacao').onclick();;return false;" href="#">
								&nbsp;
								<html:link>
									<
								</html:link>
							</c:if>
							<c:forEach begin="${begin}" end="${end}" var="no_pagina">
								<c:choose>
									<c:when test="${no_pagina == paginaAtual}">
										<html:link styleClass="selected" onClick="return false;" href="">
											${no_pagina}
										</html:link>
									</c:when>
									<c:otherwise>
										<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_parametrizacao').onclick();return false;" href="#">
											${no_pagina}
										</html:link>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${end < totalPagina}">
								<html:link onClick="$('pagina_solicitada').value='${paginaAtual+1}';$('botao_pesquisar_parametrizacao').onclick();return false;" href="#">
									>
								</html:link>
								&nbsp;
								<html:link onClick="$('pagina_solicitada').value='${totalPagina}';$('botao_pesquisar_parametrizacao').onclick();return false;" href="#">
									>>
								</html:link>
							</c:if>
						</div>
					<div class="barra"></div>	
						<div class="botao">
							<label class="lblForm">Quantidade : ${totalRegistros}</label> 
							<cata:temPermissao acao="associarCategoriaPlanosServicos">
								<html:link styleId="abrir_popup_associar_categoria" action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/abrirPoupuAssociarCategoria.do?id_plataforma=${idPlataforma}&ids_servicos=${idsServicos}" onClick="if(checkSelecaoServicos($('lista_servicos').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){return false;};abrirPopup1(this.href, null, null);return false;" bundle="messages" titleKey="catalogo.paramPlanoServico.associarCategoria">
									<html:button property="bt_associar" onclick="return false" value="Associar" styleClass="btNavegacao120"/>
								</html:link>
								<html:button property="bt_associar" styleId="salvar_associacao_categoria" onClick="if(checkSelecaoServicos($('lista_servicos').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){return false;};this.form.action=$('url_associacao').href;send(this, 'div_associar_nova_categoria', null, 'resultado_pesquisa', null, null);return false;" styleClass="btNavegacao120 hide" value="Associar" bundle="messages" titleKey="catalogo.paramPlanoServico.associarCategoria" />
							</cata:temPermissao>
							<span>&nbsp;</span>
						</div>
						<br />
						<fieldset class="content" style="height: 80px;">
							<h3 style="margin-bottom: 5px;">Grava&ccedil;&atilde;o de Wi-Fi</h3>
							<label style="font-weight: bold; color: #666; margin-left: 10px;" >Ativa&ccedil;&atilde;o Wi-Fi:</label>
								<html:select property="opAtivaWiFi">
									<html:option value="S">Sim</html:option>
									<html:option value="N">Não</html:option>
								</html:select>
							<cata:temPermissao acao="alterarCategoriaPlanosServicos">
								<div class="botao">
									<html:button property="bt_gravar" styleId="botao_gravar_ativacao" onClick="if(checkSelecaoServicos($('lista_servicos').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){return false;}else{this.form.action=$('url_ativacao').href;send(this, 'resultado_pesquisa', null, 'right_section');}" styleClass="btNavegacao74" value="Gravar" bundle="messages" altKey="catalogo.paramPlanoServico.gravarAtivacaoWiFi" titleKey="catalogo.paramPlanoServico.gravarAtivacaoWiFi" />
								</div>
							</cata:temPermissao>
						</fieldset>
						
						</html:form>
					</div>  										
				</div>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
	<iframe id='target_download2' name='target_download2' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_busca_servicos');"></iframe>
	<div id="lista_variaveis" style="position:relative;"></div>	




