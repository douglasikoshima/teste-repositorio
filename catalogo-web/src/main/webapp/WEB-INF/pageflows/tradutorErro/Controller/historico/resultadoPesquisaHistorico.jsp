<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="resultado_busca_historico">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
		alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/historico/exportar.do" styleId="lista_script" target="target_download">
		<!-- <netui:checkBoxGroup dataSource="actionForm.historicoVO.ids"> -->
				<div>
					<div class="conteudo_box_middle">
						<div class="conteudo_box_middle_mg" style="position:relative;">
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" >
								<thead>
									<tr>
										<th class="center"><input type="checkbox" name="script" class="semBorda"  onclick="selectTodosCheckbox('lista_script', '.checkbox_historico', this.checked);"/></th>
										<th class="sortable">Login</th>
										<th class="sortable">Data</th>
										<th class="sortable">Tabela</th>
										<th class="sortable">Script</th>
									</tr>
								</thead>
							</table>
							<div style="height:250px;" class="both-scroll">
<!-- 								<netui-data:repeater dataSource="actionForm.voListPage" defaultText="<br><span align='center'>Nenhum Serviço de Negocio encontrado.</span>">
										<netui-data:repeaterHeader> -->
											<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
												<thead>
													<tr>
														<th class="center"><input type="checkbox" name="script" class="semBorda"  onclick="selectTodosCheckbox('lista_script', '.checkbox_historico', this.checked);"/></th>
														<th class="sortable">Login</th>
														<th class="sortable">Data</th>
														<th class="sortable">Tabela</th>
														<th class="sortable">Script</th>
													</tr>
												</thead>
												<tbody>
<!-- 										</netui-data:repeaterHeader>
												<netui-data:repeaterItem> -->
												<c:if test="${historicoForm.listaHistoricoVO == null}">	
												   <br><span align='center'>Nenhum Histórico encontrado.</span>
												</c:if>	
												<c:if test="${historicoForm.listaHistoricoVO ne null}">										
										        <logic:iterate id="listPageTO" property="listaHistoricoVO" name="historicoForm">												
													<tr>
														<td class="center" style="padding-left: 2px">
															<html:checkbox property="ids" value="${listPageTO.cdHistorico}" styleId="checkbox_historico_${listPageTO.cdHistorico}" styleClass="semBorda belongsToForm checkbox_historico"/>
														</td>
														<td style="center">${listPageTO.login}&nbsp;</td>
														<td style="center">${listPageTO.dataScript}&nbsp;</td>
														<td style="center">${listPageTO.tabela}&nbsp;</td>
														<td class="center">
															<html:link action="popupScript.do?ds_script=${listPageTO.dsScript}" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'));return false;">
																<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>
															</html:link>
														</td>
													</tr>
										        </logic:iterate>
										        </c:if>													
<!-- 												</netui-data:repeaterItem>
												<netui-data:repeaterFooter> -->
												</tbody>
											</table>
<!-- 												</netui-data:repeaterFooter>
								</netui-data:repeater> -->
							</div>
							<br>
							<div class="paginacao" style="width:99%;" align="right">
								<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
									<c:choose>
										<c:when test="${no_pagina == paginaAtual}">
											<html:link styleClass="selected" onClick="return false;" href="">
												${no_pagina}
											</html:link>
										</c:when>
										<c:otherwise>
											<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('bot_pesquisa').onclick();return false;" href="#">
												${no_pagina}
											</html:link>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</div>
							<div class="barra"></div>
							<div class="botao">
								<label class="lblForm" >Quantidade : ${totalRegistros}</label>
								<netui:button type="submit" styleClass="btNavegacao74" value="Exportar" targetScope="target_download"/>
							</div>
						</div>
					</div>
					<div class="conteudo_box_bottom"></div>
				</div>
<!-- 			</netui:checkBoxGroup> -->
		</netui:form>
	</div>
</div>
<br clear="all" />
<iframe id='target_download' name='target_download' src='' style='display:none;'  onload="retornoDownloadFile(this, 'resultado_busca_historico');"></iframe>