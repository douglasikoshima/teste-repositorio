<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/gravarAtivacaoWiFiPlanos.do">
	<div id="resultado_busca_planos" style="position:relative;">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa Por Plano: </div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
						<div style="width:99%;" id="lista_planos">
<!-- 							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
								<thead>
									<tr>
										<th class="center" width="10px" ><input type="checkbox" name="planosCheck" class="semBorda" onclick="selectTodosCheckbox('lista_planos', '.checkbox_plano', this.checked);"/></th>
										<th class="sortable">Nome T&eacute;cnico</th>
										<th class="sortable">Nome Comercial</th>
										<th class="sortable">UF</th>
										<th class="sortable" nowrap="nowrap">Limite m&aacute;ximo<br>de dependentes</th>
										<th>Wi-Fi<br />Ativo</th>
										<th>Disp</th>
										<th>Alterar</th>
									</tr>
								</thead>
							</table> -->
						<div class="both-scroll" style="height:350px;">
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body">
										<thead>
											<tr>																							
												<th class="center" width="10px" ><input type="checkbox" name="planosCheck" class="semBorda" onclick="selectTodosCheckbox('lista_planos', '.checkbox_plano', this.checked);"/></th>
												<th class="sortable">Nome T&eacute;cnico</th>
												<th class="sortable">Nome Comercial</th>
												<th class="sortable">UF</th>
												<th class="sortable" nowrap="nowrap">Limite m&aacute;ximo<br>de dependentes</th>
												<th>Wi-Fi<br />Ativo</th>
												<th>Disp</th>
												<th>Alterar</th>												
											</tr>
										</thead>
								<tbody>								
								<logic:iterate id="listPlanos" property="listaPlanoParametrizacao" name="servicoForm">
									<c:set value="true" var="firstPass"/>
											<tr>
												<td class="center" >													
													<html:checkbox property="idsPlanos" value="${listPlanos.idPlano}" styleId="checkbox_plano_${listPlanos.idPlano}" styleClass="semBorda belongsToForm checkbox_modelo" />
												</td>
												<td>
													<c:if test="${firstPass == true}">
														${listPlanos.cdCodigo}
													</c:if>
												</td>
												<td>${listPlanos.nmComercial}&nbsp;</td>
												<td class="center">
													<html:link styleId="link_listarCategorias"						          										          	
											          	action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/popupUFs.do?id_plano=${listPlanos.idPlano}"										          	       
											          	onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;" title="UFs">	
											         		<img alt="UFs" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>			          
											        </html:link>																																																															
												</td>
												<td>${listPlanos.qtdMaxDependenteCatalogo}</td>
												<td class="center">
													<c:choose>
														<c:when test="${listPlanos.ativaWiFi == 'S'}">
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
														</c:when>
														<c:otherwise>
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
														</c:otherwise>
													</c:choose>
												</td>
												<td class="center">
													<c:choose>
														<c:when test="${listPlanos.inDisponibilidadeCatalogo == 'S'}">
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
														</c:when>
														<c:otherwise>
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
														</c:otherwise>
													</c:choose>
												</td>
												<td class="center">
										 			<fmt:formatDate value="${listPlanos.dtUltimaAlteracao.time}" pattern="dd/MM/yyyy HH:mm:ss" var="data_alteracao" scope="request"/>
										 			<cata:temPermissao acao="alterarLimitePlanoPlanosServicos">
										 			<html:link styleId="link_listarCategorias" action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/carregarAlterarPlano.do?id_plataforma=${idPlataforma}&indisponibilidade_legado=${listPlanos.inDisponibilidadeLegado}&id_plano=${listPlanos.idPlano}&id_tipo_plano=${listPlanos.idTipoPlano}&cd_codigo=${listPlanos.cdCodigo}&nm_comercial=${listPlanos.nmComercial}&ind_titDep=${listPlanos.indTitDep}&qtd_maxDependenteCatalogo=${listPlanos.qtdMaxDependenteCatalogo}&qtd_maxDependenteLegado=${listPlanos.qtdMaxDependenteLegado}&nm_usuarioAlteracao=${listPlanos.nmUsuarioAlteracao}&indisponibilidade_catalogo=${listPlanos.inDisponibilidadeCatalogo}&ativa_wifi=${listPlanos.ativaWiFi}&dt_ultimaAlteracao=${data_alteracao}" onClick="abrirLink('alterar_servicos', this.href, 'right_section');return false;" title="Alterar" onMouseUp="return false;">												         		
											         		<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">		          
											        </html:link>	
											        
											        
											        <%-- <c:out value="${listPlanos.inDisponibilidadeLegado}"></c:out> --%>
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
								</html:link>
								&nbsp;
								<html:link onClick="$('pagina_solicitada').value='${paginaAtual-1}';$('botao_pesquisar_parametrizacao').onclick();return false;" href="#">
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
							<span>&nbsp;</span>
						</div>
						
						<br />
						<fieldset class="content" style="height: 80px;">
							<h3 style="margin-bottom: 5px;">Grava&ccedil;&atilde;o de Wi-Fi</h3>
							<label style="font-weight: bold; color: #666; margin-left: 10px;" >Ativa&ccedil;&atilde;o Wi-Fi:</label>
							<html:select property="opAtivaWiFi">						   
							   <option value="S">Sim</option>
							   <option value="N">Nâo</option>				    				   			   				   
						 	</html:select> 					 						 																			
							<cata:temPermissao acao="alterarLimitePlanoPlanosServicos">
								<div class="botao">
									<html:button property="botaoGravarAtivacao" styleId="botao_gravar_ativacao" onclick="if(checkSelecaoTabela($('lista_planos').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){return false;}else{send(this, 'resultado_pesquisa', null, 'right_section');}" styleClass="btNavegacao74" value="Gravar" bundle="messages" altKey="catalogo.paramPlanoServico.gravarAtivacaoWiFi" titleKey="catalogo.paramPlanoServico.gravarAtivacaoWiFi"/>									
								</div>
							</cata:temPermissao>
						</fieldset>
						
					</div>
				</div>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
</html:form>
	
<iframe id='target_download2' name='target_download2' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_busca_planos');"></iframe>
<div id="lista_variaveis" style="position:relative;"></div>
