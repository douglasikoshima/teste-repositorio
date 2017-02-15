<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="resultado_pesquisa_acesso_planos_servicos" style="position:relative;">
	<fmt:bundle basename="catalogoprs_messages" >
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/salvarAlteracoesRestricoesAcessoServico.do" styleId="lista_servico_perfil">
					<div style="width:100%;position:relative;">		
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
							<thead>
								<tr>
									<th rowspan="2" class="sortable" style="width: 30%">Nome Comercial</th>
									<th rowspan="2" class="sortable" style="width: 20%">Perfil de Acesso</th>
									<th colspan="4" class="throwspan">Restri&ccedil;&otilde;es</th>
								</tr>
								<tr>
									<th style="text-align: left; padding-left: 22px">
										<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_consulta'), this.checked);">
										Consulta
										</html:checkbox> 											
									</th>
									
									<th style="text-align: left; padding-left: 13px">
										<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_ativacao'), this.checked);" > 
										Ativa&ccedil;&atilde;o
										</html:checkbox> 											
									</th>
									
									<th style="text-align: left; padding-left: 12px">
										<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_desativacao'), this.checked);">
										Desativa&ccedil;&atilde;o
										</html:checkbox>
									</th>
									
									<th>Excluir</th>
								</tr>
							</thead>
						</table>
						<div class="both-scroll" style="height: 300px;">
							<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="lista_cabecalho_matriz_oferta">
								<thead>
									<tr>
										<th rowspan="2" class="sortable" style="width: 30%">Nome Comercial</th>
										<th rowspan="2" class="sortable" style="width: 20%">Perfil de Acesso</th>
										<th colspan="4" class="throwspan">Restri&ccedil;&otilde;es</th>
									</tr>
									<tr>
										<th style="text-align: left; padding-left: 22px">
											<!-- <input type="checkbox" name="modelo" class="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_consulta'), this.checked);"/> -->
											<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_consulta'), this.checked);"> 
											Consulta
											</html:checkbox>
										</th>
										<th style="text-align: left; padding-left: 13px">
											<!-- <input type="checkbox" name="modelo" class="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_ativacao'), this.checked);"/> -->
											<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_ativacao'), this.checked);"> 
											Ativa&ccedil;&atilde;o
											</html:checkbox>
										</th>
										<th style="text-align: left; padding-left: 12px">
											<!-- <input type="checkbox" name="modelo" class="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_desativacao'), this.checked);"/> -->
											<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_desativacao'), this.checked);"> 
											Desativa&ccedil;&atilde;o
											</html:checkbox>
										</th>
											
										<th>Excluir</th>
									</tr>
								</thead>
							<tbody>
							<c:if test="${not empty parametrizacaoAcessoForm.acessoServicoLista}">														
								<logic:iterate id="servicos" property="acessoServicoLista" name="parametrizacaoAcessoForm" indexId="index" >														
									<c:set value="true" var="firstPass"/>
										<tr>
											<td>${servicos.nmComercial}</td>
											<td>
												<c:if test="${firstPass == true}">
													<c:choose>
														<c:when test="${not empty perfilSCA[servicos.idPerfilSCA]}">
															${perfilSCA[servicos.idPerfilSCA]}
														</c:when>
														<c:otherwise>
															${perfilScaMap[servicos.idPerfilSCA].name}
														</c:otherwise>
													</c:choose>
													<html:checkbox property="idAcessoServico" value="${servicos.idAcessoServico}" styleClass="semBorda belongsToForm checkbox hide"/>
												</c:if>
											</td>
											<td style="text-align: left; padding-left: 11px">
												<c:choose>
													<c:when test="${servicos.inRestricaoConsulta eq 'S'}">
														<input type="checkbox" checked="checked" onClick="setValueArrayRestricoes(this.checked, 'txtConsulta_${index}');checkAtivacaoAndDesativacao(this.checked, $(ativa_${index}).checked, 'ativa_${index}', $(desativa_${index}).checked, 'desativa_${index}')" class="semBorda belongsToForm checkbox_consulta" />
														<html:hidden property="inRestricaoConsulta" styleId="txtConsulta_${index}" value="${servicos.inRestricaoConsulta}" />
													</c:when>
													<c:otherwise>
														<input type="checkbox" onClick="setValueArrayRestricoes(this.checked, 'txtConsulta_${index}');checkAtivacaoAndDesativacao(this.checked, $(ativa_${index}).checked, 'ativa_${index}', $(desativa_${index}).checked, 'desativa_${index}')" class="semBorda belongsToForm checkbox_consulta" />
														<html:hidden property="inRestricaoConsulta" styleId="txtConsulta_${index}" value="${servicos.inRestricaoConsulta}" />
													</c:otherwise>
												</c:choose>
											</td>
											<td style="text-align: left; padding-left: 12px">
												<c:choose>
													<c:when test="${servicos.inRestricaoAtivacao eq 'S'}">
														<input type="checkbox" checked="checked" onClick="setValueArrayRestricoes(this.checked, 'txtAtivacao_${index}')" class="semBorda belongsToForm checkbox_ativacao" id="ativa_${index}" />
														<html:hidden property="inRestricaoAtivacao" styleId="txtAtivacao_${index}" value="${servicos.inRestricaoAtivacao}" />
													</c:when>
													<c:otherwise>
														<input type="checkbox" onClick="setValueArrayRestricoes(this.checked, 'txtAtivacao_${index}')" class="semBorda belongsToForm checkbox_ativacao" id="ativa_${index}" />
														<html:hidden property="inRestricaoAtivacao" styleId="txtAtivacao_${index}" value="${servicos.inRestricaoAtivacao}" />
													</c:otherwise>
												</c:choose>
											</td>
											<td style="text-align: left; padding-left: 12px">
												<c:choose>
													<c:when test="${servicos.inRestricaoDesativacao eq 'S'}">
														<input type="checkbox" checked="checked" onClick="setValueArrayRestricoes(this.checked, 'txtDesativacao_${index}')" class="semBorda belongsToForm checkbox_desativacao" id="desativa_${index}" />
														<html:hidden property="inRestricaoDesativacao" styleId="txtDesativacao_${index}" value="${servicos.inRestricaoDesativacao}"/>
													</c:when>
													<c:otherwise>
														<input type="checkbox" onClick="setValueArrayRestricoes(this.checked, 'txtDesativacao_${index}')" class="semBorda belongsToForm checkbox_desativacao" id="desativa_${index}" />
														<html:hidden property="inRestricaoDesativacao" styleId="txtDesativacao_${index}" value="${servicos.inRestricaoDesativacao}" />
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<html:link
														action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/abrirPopupConfirmExclusaoRestricoesAcessoServico.do?id_acesso_servico=${servicos.idAcessoServico}&in_restricao_consulta=${servicos.inRestricaoConsulta}&in_restricao_ativacao=${servicos.inRestricaoAtivacao}&in_restricao_desativacao=${servicos.inRestricaoDesativacao} "
														onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'))return false;">
													<html:img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif"/>
												</html:link>
											</td>
										</tr>
										<c:set value="false" var="firstPass"/>
									</logic:iterate>
								</c:if>
								</tbody>
							</table>
						</div>
					</div>
					<br>
					<div class="paginacao" style="width:99%;" align="right">
						<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">
									<html:link styleClass="selected" onClick="return false;" href="" >
										${no_pagina}
									</html:link>
								</c:when>
								<c:otherwise>
									<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_acesso_plano_servico').onclick();return false;" href="#" >
										${no_pagina}
									</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="barra"></div>
					<div class="botao">
						<cata:temPermissao acao="configurarPerfilAcesso">
							<html:button bundle="messages" tabindex="13" styleId="botao_gravar_restricoes_alteradas" property="btn_gravar" onClick="if(send(this, 'resultado_pesquisa', null, 'resultado_pesquisa')){};return false" styleClass="btNavegacao74" value="Salvar" altKey="catalogo.matrizOferta.cabecalho.Alterar" titleKey="catalogo.matrizOferta.cabecalho.Alterar" />
							<label>&nbsp;</label>
						</cata:temPermissao>
						<label class="lblForm" >Quantidade : ${totalRegistros}</label>
					</div>
				</html:form>
			</div>
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
	</fmt:bundle>
</div>