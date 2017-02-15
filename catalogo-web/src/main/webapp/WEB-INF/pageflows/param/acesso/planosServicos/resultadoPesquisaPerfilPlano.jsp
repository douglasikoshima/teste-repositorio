<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
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
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/salvarAlteracoesRestricoesAcessoPlano.do" styleId="lista_perfil_plano">								
					<div style="width:100%;position:relative;">		
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
							<thead>
								<tr>
									<th rowspan="2" class="sortable" style="width: 20%">Perfil de Acesso</th>
									<th rowspan="2" class="sortable" style="width: 30%">Nome Comercial</th>
									<th colspan="4" class="throwspan">Restri&ccedil;&otilde;es</th>
								</tr>
								<tr>
									<th style="text-align: left; padding-left: 22px">
										<html:checkbox property="modelo" style="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_consulta'), this.checked);" >
										 Consulta
										</html:checkbox>
									</th>
									
									<th style="text-align: left; padding-left: 13px">
										<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_ativacao'), this.checked);" >
										Ativa&ccedil;&atilde;o
										</html:checkbox>											
									</th>
									
									<th style="text-align: left; padding-left: 12px">
										<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_desativacao'), this.checked);" >
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
										<th rowspan="2" class="sortable" style="width: 20%">Perfil de Acesso</th>
										<th rowspan="2" class="sortable" style="width: 30%">Nome Comercial</th>
										<th colspan="4" class="throwspan">Restri&ccedil;&otilde;es</th>
									</tr>
									<tr>
										<th style="text-align: left; padding-left: 22px">
											<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_consulta'), this.checked);" >
											Consulta
											</html:checkbox>
										</th>
										
										<th style="text-align: left; padding-left: 13px">
											<html:checkbox property="modelo" styleId="modelo" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('form').select('.checkbox_ativacao'), this.checked);">
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
							<tbody>
							<c:if test="${not empty parametrizacaoAcessoForm.acessoPlanoLista}">							
								<logic:iterate id="planos" property="acessoPlanoLista" name="parametrizacaoAcessoForm" indexId="index">																								
									<c:set value="true" var="firstPass"/>
										<tr>
											<td>
												<c:if test="${firstPass == true}">
													${perfilSCA[planos.idPerfilSCA]}
													<html:checkbox property="idAcessoPlano" value="${planos.idAcessoPlano}" styleClass="semBorda belongsToForm checkbox hide"/>
												</c:if>
											</td>
											<td>${planos.nmComercial}</td>
											<td style="text-align: left; padding-left: 12px">
												<c:choose>
													<c:when test="${planos.inRestricaoConsulta eq 'S'}">
														<input type="checkbox" checked="checked" onClick="setValueArrayRestricoes(this.checked, 'txtConsulta_${index}');checkAtivacaoAndDesativacao(this.checked, $(ativa_${index}).checked, 'ativa_${index}', $(desativa_${index}).checked, 'desativa_${index}')" class="semBorda belongsToForm checkbox_consulta" />
														<html:hidden property="inRestricaoConsulta" styleId="txtConsulta_${index}" value="${planos.inRestricaoConsulta}" />
													</c:when>
													<c:otherwise>
														<input type="checkbox" onClick="setValueArrayRestricoes(this.checked, 'txtConsulta_${index}');checkAtivacaoAndDesativacao(this.checked, $(ativa_${index}).checked, 'ativa_${index}', $(desativa_${index}).checked, 'desativa_${index}')" class="semBorda belongsToForm checkbox_consulta" />
														<html:hidden property="inRestricaoConsulta" styleId="txtConsulta_${index}" value="${planos.inRestricaoConsulta}" />
													</c:otherwise>
												</c:choose>
											</td>
											<td style="text-align: left; padding-left: 12px">
												<c:choose>
													<c:when test="${planos.inRestricaoAtivacao eq 'S'}">
														<input type="checkbox" checked="checked" onClick="setValueArrayRestricoes(this.checked, 'txtAtivacao_${index}')" class="semBorda belongsToForm checkbox_ativacao" id="ativa_${index}" />
														<html:hidden property="inRestricaoAtivacao" styleId="txtAtivacao_${index}" value="${planos.inRestricaoAtivacao}" />
													</c:when>
													<c:otherwise>
														<input type="checkbox" onClick="setValueArrayRestricoes(this.checked, 'txtAtivacao_${index}')" class="semBorda belongsToForm checkbox_ativacao" id="ativa_${index}" />
														<html:hidden property="inRestricaoAtivacao" styleId="txtAtivacao_${index}" value="${planos.inRestricaoAtivacao}" />
													</c:otherwise>
												</c:choose>
											</td>
											<td style="text-align: left; padding-left: 12px">
												<c:choose>
													<c:when test="${planos.inRestricaoDesativacao eq 'S'}">
														<input type="checkbox" checked="checked" onClick="setValueArrayRestricoes(this.checked, 'txtDesativacao_${index}')" class="semBorda belongsToForm checkbox_desativacao" id="desativa_${index}" />
														<html:hidden property="inRestricaoDesativacao" styleId="txtDesativacao_${index}" value="${planos.inRestricaoDesativacao}" />
													</c:when>
													<c:otherwise>
														<input type="checkbox" onClick="setValueArrayRestricoes(this.checked, 'txtDesativacao_${index}')" class="semBorda belongsToForm checkbox_desativacao" id="desativa_${index}" />
														<html:hidden property="inRestricaoDesativacao" styleId="txtDesativacao_${index}" value="${planos.inRestricaoDesativacao}"  />
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<html:link
													action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/abrirPopupConfirmExclusaoRestricoesAcessoPlano.do?id_acesso_plano=${planos.idAcessoPlano}&in_restricao_consulta=${planos.inRestricaoConsulta}&in_restricao_ativacao=${planos.inRestricaoAtivacao}&in_restricao_desativacao=${planos.inRestricaoDesativacao} "
													onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'))return false;">
													<html:img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif" />												
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
									<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_acesso_plano_servico').onclick();return false;" href="#">
										${no_pagina}
									</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="barra"></div>
					<div class="botao">
						<cata:temPermissao acao="configurarPerfilAcesso">
							<html:button bundle="messages" property="btn_gravar" tabindex="13" styleId="botao_gravar_restricoes_alteradas" onClick="if(send(this, 'resultado_pesquisa', null, 'resultado_pesquisa')){};return false" styleClass="btNavegacao74" value="Salvar" altKey="catalogo.matrizOferta.cabecalho.Alterar" titleKey="catalogo.matrizOferta.cabecalho.Alterar" />
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