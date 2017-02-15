<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="resultado_pesquisa_planos">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
		alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg" style="position:relative;">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/exportar.do" styleId="lista_acesso_planos" target="target_download">
					<html:hidden styleId="id_tipo_pesquisa" property="idTpPesquisa" value="${idTipoPesquisa}"/>
					<html:text property="idExporte" styleId="idExporte" styleClass="hide required" style="display: none;" />
						<div style="height:300px;" class="both-scroll">
										<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
											<thead>
												<tr>
													<th rowspan="2" class="center" style="width: 25px"><input type="checkbox" name="perfis_acesso" class="semBorda"  onclick="selectTodosCheckboxPlanoServico('lista_acesso_planos', '.checkbox_perfis_acesso', this.checked);"/></th>
													<th rowspan="2" class="sortable">Perfil</th>
													<th rowspan="2" class="sortable">Nome Comercial</th>
													<th colspan="3" class="sortable">Restri&ccedil;&atilde;o</th>
												</tr>
												<tr>	
													<th class="sortable">Consulta</th>
													<th class="sortable">Ativa&ccedil;&atilde;o</th>
													<th class="sortable">Desativa&ccedil;&atilde;o</th>
												</tr>
											</thead>
											<tbody>		
											<c:if test="${consultaAcessoPlanosServicosForm.arrayListaAcessoPlano ne null}">				
												<logic:iterate id="planos" property="arrayListaAcessoPlano" name="consultaAcessoPlanosServicosForm">
												<tr>
													<td class="center" style="padding-left: 2px">
														<html:checkbox property="checkAcessoPlano" value="${planos.idAcessoPlano}" onClick="selecionaCheckBoxPopup('idExporte', this);" styleId="checkbox_perfis_acesso_${planos.idAcessoPlano}" styleClass="semBorda belongsToForm checkbox_perfis_acesso"/>
													</td>
													<td style="text-align: left;">${listaPerfilSCA[planos.idPerfilSCA]}&nbsp;</td>
													<td style="center">${planos.nmComercial}&nbsp;</td>
													<td class="center">
														<c:choose>
															<c:when test="${planos.inRestricaoConsulta=='S'}">
																<input id="consulta_check_${planos.inRestricaoConsulta}" type="checkbox" class="SemBorda" checked="checked" disabled="disabled"/>
															</c:when>
															<c:otherwise>
																<input id="consulta_check_${planos.inRestricaoConsulta}" type="checkbox" class="SemBorda" disabled="disabled"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td class="center">
														<c:choose>
															<c:when test="${planos.inRestricaoAtivacao=='S'}">
																<input id="ativacao_check_${planos.inRestricaoAtivacao}" type="checkbox" class="SemBorda" checked="checked" disabled="disabled"/>
															</c:when>
															<c:otherwise>
																<input id="ativacao_check_${planos.inRestricaoAtivacao}" type="checkbox" class="SemBorda" disabled="disabled"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td class="center">
														<c:choose>
															<c:when test="${planos.inRestricaoDesativacao=='S'}">
																<input id="desativacao_check_${planos.inRestricaoDesativacao}" type="checkbox" class="SemBorda" checked="checked" disabled="disabled"/>
															</c:when>
															<c:otherwise>
																<input id="desativacao_check_${planos.inRestricaoDesativacao}" type="checkbox" class="SemBorda" disabled="disabled"/>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
												</logic:iterate>	
											</c:if>
											</tbody>
										</table>
										<c:if test="${consultaAcessoPlanosServicosForm.arrayListaAcessoPlano == null}">	
										   <br><span align='center'>Nenhuma Oferta de Servi&ccedil;o encontrada.</span>
										</c:if>											
						</div>
						<br>
						<div class="paginacao" style="width:99%;" align="right">
							<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
								<c:choose>
									<c:when test="${no_pagina == paginaAtual}">
									    <html:link styleClass="selected" onClick="return false;" href="">${no_pagina}</html:link>
									</c:when>
									<c:otherwise>
									    <html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('bot_pesquisa').onclick();return false;" href="#">${no_pagina}</html:link>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<div class="barra"></div>
						<div class="botao">
							<label class="lblForm" >Quantidade : ${totalRegistros}</label>
							<html:submit property="Exportar" value="Exportar" styleClass="btNavegacao74"/>
						</div>
				</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<iframe id='target_download' name='target_download' src='' style='display:none;'  onload="retornoDownloadFile(this, 'resultado_pesquisa_planos');"></iframe>