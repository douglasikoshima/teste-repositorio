<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/listarVariaveisPlano.do" target="target_download2" styleId="consultaPlanosForm">
<div id="resultado_busca_plano" style="position:relative;">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
			<div class="conteudo_box_top_esq">
			</div>
			<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
						
						<div style="width:99%;position:relative;" id="lista_planos">
							<html:hidden property="idPlataforma" value="${idPlataforma}"/>
						
							<div class="both-scroll" style="height:300px;">
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
										<thead>
											<tr>
												<c:choose>
													<c:when test="${idPlataforma == 1 || idSistema == 3}">
														<!--  //PRE PAGO  -->
														<th ><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_plano', this.checked);"/></th>
														<th class="sortable">Nome do Plano</th>
														<th class="sortable">Cód. Master</th>
														<th class="sortable">Cód.</th>
														<th class="sortable">Cód. Anatel</th>
														<th class="sortable">Período</th>
														<th>UF</th>
														<th>Serviços</th>
														<th>Disp.</th>
													</c:when>
													<c:otherwise>
														<!--  //POS PAGO e CONTROLE -->
														<th rowspan="2"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_plano', this.checked);"/></th>
														<th class="sortable" rowspan="2">Nome do Plano</th>
														<th class="sortable" rowspan="2">Cód. sistêmico</th>
														<th class="sortable" rowspan="2">Cód. Anatel</th>
														<th class="sortable" rowspan="2">Período</th>
														<th colspan="2"><b>Tarifa</b></th>
														<th rowspan="2">UF</th>
														<th rowspan="2">Serviços</th>
														<th rowspan="2">Disp.</th>
													</tr>
													<tr>
														<th class="sortable">Valor Assinatura</th>
														<th class="sortable">Franquia</th>
													</c:otherwise>
												</c:choose>
											</tr>
										</thead> 
										
									<tbody>
									
								<logic:iterate id="listaPlano" property="retornoPlanoList" name="consultaPlanosForm">
									<tr>
										<c:choose>
											<c:when test="${idPlataforma == 1 || idSistema == 3}">
												<!--  //PRE PAGO  -->
												<td><html:checkbox property="idsPlanos" value="${listaPlano.idPlano}" styleClass="semBorda checkbox_plano" style="hide" name="consultaPlanosForm"/></td>
												<td>
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupAlterarDescricaoPlano.do?id_plano=${listaPlano.idPlano}&desc_plano=${listaPlano.idPlano}" onclick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;">
														${listaPlano.nmComercial}
													</html:link>
												</td>
												<td>${listaPlano.cdCodigoMaster}</td>
												<td>${listaPlano.cdCodigo}</td>
												<td>${listaPlano.cdAnatel}</td>
												<td>
													<c:set var="dtInicial" value="${consultaPlanosForm.mapaDataIncial[listaPlano.nmComercial]}"/>
													<bean:define id="dtInicial" value="${dtInicial}" />
                                                    <bean:write bundle="messages" name="dtInicial"/>
													a
													<c:set var="dtFinal" value="${consultaPlanosForm.mapaDataFinal[listaPlano.nmComercial]}"/>
													<bean:define id="dtFinal" value="${dtFinal}" />
                                                    <bean:write bundle="messages" name="dtFinal"/>
												</td>
												<td class="center">
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupUFs.do?id_plano=${listaPlano.idPlano}&id_plataforma=${idPlataforma}" onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;" title="UFs">
														<img alt="UFs" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif">
													</html:link>
												</td>
												<td class="center">
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupServicos.do?id_plano=${listaPlano.idPlano}" onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;" title="Serviços">
														<img alt="Serviços" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>
													</html:link>
												</td>
												<td class="center">
													<c:choose>
														<%-- <c:when test="${container.item.inDisponivel == 'S'}"> --%>
														<c:when test="${listaPlano.inDisponivel == 'S'}">
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
														</c:when>
														<c:otherwise>
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
														</c:otherwise>
													</c:choose>
												</td>
											</c:when>
											<c:otherwise>
												<!--  //POS PAGO e CONTROLE  -->
												<td><html:checkbox property="idsPlanos" styleClass="semBorda checkbox_plano" value="${listaPlano.idPlano}" style="hide" name="consultaPlanosForm"/></td>
												<td>
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupAlterarDescricaoPlano.do?id_plano=${listaPlano.idPlano}&desc_plano=${listaPlano.idPlano}" onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;">
														${listaPlano.nmComercial}
													</html:link>
												</td>
												<td>${listaPlano.cdCodigo}</td>
												<td>${listaPlano.cdAnatel}</td>											
												<td>
													<c:set var="dtInicial" value="${consultaPlanosForm.mapaDataIncial[listaPlano.nmComercial]}"/>
													<bean:define id="dtInicial" value="${dtInicial}" />
                                                    <bean:write bundle="messages" name="dtInicial"/>
													a
													<c:set var="dtFinal" value="${consultaPlanosForm.mapaDataFinal[listaPlano.nmComercial]}"/>
													<bean:define id="dtFinal" value="${dtFinal}" />
                                                    <bean:write bundle="messages" name="dtFinal"/>
												</td>
												<td class="center">
													<fmt:formatNumber value="${listaPlano.valorAssinaturaMensal}" type="currency"/>
												</td>
												<td class="center">
													<fmt:formatNumber value="${listaPlano.qtdeMinFranquia}" type="number"/>
												</td>
												<td class="center">
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupUFs.do?id_plano=${listaPlano.idPlano}&id_plataforma=${idPlataforma}" onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;" title="UFs">
														<img alt="UFs" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>
													</html:link>
												</td>
												<td class="center">
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupServicos.do?id_plano=${listaPlano.idPlano}" onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;" title="Serviços">
														<img alt="Serviços" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>
													</html:link>
												</td>
												<td class="center">
													<c:choose>
														<%-- <c:when test="${container.item.inDisponivel == 'S'}"> --%>
														<c:when test="${listaPlano.inDisponivel == 'S'}">
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
														</c:when>
														<c:otherwise>
															<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
														</c:otherwise>
													</c:choose>
												</td>
											</c:otherwise>
										</c:choose>
									</tr>
								
									</logic:iterate>
								</tbody>
							</table>
							</div>
						</div>
						<br>
						<div class="paginacao" style="width:99%;" align="right">
						<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">
									<html:link styleClass="selected" onclick="return false;" href="">
										${no_pagina}
									</html:link>
								</c:when>
								<c:otherwise>
									<html:link onclick="$('pagina_solicitada').value='${no_pagina}';$('bota_listar_planos').onclick();return false;" href="#">
										${no_pagina}
									</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="barra"></div>	
						
						<div class="botao"">
							<label class="lblForm">Quantidade : ${totalRegistros}</label>
							<html:submit bundle="messages" value="Restrições" onclick="send(this, 'lista_variaveis', null, 'resultado_busca_plano', null, null);return false;" styleClass="btNavegacao120" titleKey="catalogo.resultadoPesquisaPlanos.Variaveis" />
							<span>&nbsp;</span>
							<c:if test="${(idPlataforma != 1 || idPlataforma == 3) && (idSistema != '' && idSistema != 3 || idSistema == 2 && idPlataforma == 3)}">
								<div id="btnExportar" class="botao" style="margin: 0; padding: 0">
									<html:button property="bt_exportar" value="Exportar" styleClass="btNavegacao120" onClick="$(this).form.action=$(this).next('a').href;send(this, 'lista_variaveis', null, 'resultado_busca_plano', null, null);" />
									<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/exportarPlanos.do" styleClass="hide"/>
								</div>
							</c:if>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
</html:form>

	<iframe id='target_download2' name='target_download2' src='' style='display:none;'  onload="retornoDownloadFile(this, 'resultado_busca_plano');"></iframe>
	
	<div id="lista_variaveis" style="position:relative;"></div>
