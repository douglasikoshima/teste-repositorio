<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
<input type="hidden" id="alturaPopup" value="350"/>
<div id="popup_selecionar_tecnologias_frequencias">
	<netui-temp:section name="conteudo">
		<c:set var="nomeBox" scope="application"  value="Tecnologia / Frequência"/>
		<c:if test="${gravar_select_tipo_produto == 10 || gravar_select_tipo_produto == 7}">
			<c:set var="display_tpf_fq" value="none"/>
			<c:set var="nomeBox" scope="application"  value="Tecnologia"/>
		</c:if>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div align="left" class="largePopup">
			<div id="toto">
			<table width="100%">
				<tr>
					<c:choose>
						<c:when test="${gravar_select_tipo_produto == 10 || gravar_select_tipo_produto == 7}">
							<c:set var="width_tecnologia" value="99"/>
						</c:when>
						<c:otherwise>
							<c:set var="width_tecnologia" value="33"/>
						</c:otherwise>
					</c:choose>
					<td valign="top" width="${width_tecnologia}%">
						<div class="fleft vertical-scroll" style="height:230px;width:100%;">
							<form class="fleft">
								<table id="lista_selecionar_tecnologias" class="tabConteudoGeral">
									    <tr>
											<td class="titulo" colspan="2">												
													TECNOLOGIA												
											</td>
										</tr>								
									<netui:checkBoxGroup dataSource="pageFlow.tecnologiasFrequencias_tecnologiasSelecionadas" optionsDataSource="${tecnologias}" repeater="true">
										<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
											<td width="10px">
												<netui:checkBoxOption onClick="selecionarTecnologiasFrequencias_Tecnologia('hiddenTecnologiasFrequencias', this, ${container.item.idTecnologiaPai});mostrarTiposFrequenciaTecnologia('div_tipos_frequencia','${container.item.idTecnologia}');" tagId="tecnologias_cb_${container.item.idTecnologia}" value="${container.item.idTecnologia}" styleClass="semBorda checkbox"/>
												<input type="hidden" value="${container.item.idTecnologiaPai}"/>
											</td>
											<td>
												<label style="width:100%;" id="label_tecnologia_${container.item.idTecnologia}" onclick="mostrarTiposFrequenciaTecnologia('div_tipos_frequencia','${container.item.idTecnologia}');">${container.item.nmTecnologia}</label>
											</td>
										</tr>
									</netui:checkBoxGroup>
								</table>
							</form>
						</div>
					</td>
					<td valign="top" width="33%">
						<div id="div_tipos_frequencia" class="fleft vertical-scroll" style="height:230px;width:100%;display:${display_tpf_fq};">
							<c:forEach items="${tecnologias}" var="tecnologia"  >
								<c:set  var="idTecnologiaLong" value="${tecnologia.idTecnologia}"/>
								<% pageContext.setAttribute("idTecnologiaString", String.valueOf(pageContext.getAttribute("idTecnologiaLong"))); %>
								<c:set var="idsTipoFrequencia" value="${pageFlow.tecnologiasFrequencias_tiposFrequenciaSelecionadas[idTecnologiaString]}"/>
									<table id="tiposFrequencia_tecnologia_${tecnologia.idTecnologia}" style="display:none;" class="tabConteudoGeral" height="200px">
										<tr>
											<td class="titulo" colspan="2">
												<c:if test="${gravar_select_tipo_produto != 10 && gravar_select_tipo_produto != 7 }">
													TIPOS FREQUÊNCIA PARA: ${tecnologia.nmTecnologia}
												</c:if>
											</td>
										</tr>
										<c:if test="${tecnologia.listaTipoFrequencia.tipoFrequenciaList !=null && gravar_select_tipo_produto != 10 && gravar_select_tipo_produto != 7 }">
										<netui:checkBoxGroup dataSource="idsTipoFrequencia" optionsDataSource="${tecnologia.listaTipoFrequencia.tipoFrequenciaList}" repeater="true">
											<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
												<td width="10%" align="center">
													<netui:checkBoxOption onClick="selecionarTecnologiasFrequencias_TipoFrequencia('hiddenTecnologiasFrequencias', ${tecnologia.idTecnologia}, this);mostrarFrequenciasTipoFrequenciaTecnologia('div_frequencias', ${tecnologia.idTecnologia} , '${container.item.idTipoFrequencia}');" tagId="tiposFrequencia_tecnologia_cb_${tecnologia.idTecnologia}_${container.item.idTipoFrequencia}" value="${container.item.idTipoFrequencia}" styleClass="semBorda checkbox"/>
												</td>
												<td width="90%">
													<label id="label_tipo_frequencia_${tecnologia.idTecnologia}_${container.item.idTipoFrequencia}" onclick="mostrarFrequenciasTipoFrequenciaTecnologia('div_frequencias', ${tecnologia.idTecnologia} , '${container.item.idTipoFrequencia}');">${container.item.nmTipoFrequencia}</label>
													<div class="hide" id="qt_frequencia_${tecnologia.idTecnologia}_${container.item.idTipoFrequencia}">${container.item.qtFrequencia}</div>
												</td>
											</tr>
										</netui:checkBoxGroup>
										</c:if>
									</table>
							</c:forEach>
						</div>
					</td>
					<td valign="top" width="33%">
						<div id="div_frequencias" class="fleft vertical-scroll" style="height:230px;width:100%;display:${display_tpf_fq};">
							<c:forEach items="${tecnologias}" var="tecnologia" >
								<c:forEach items="${tecnologia.listaTipoFrequencia.tipoFrequenciaList}" var="tipoFrequencia">
									<c:set  var="idTecnologiaLong" value="${tecnologia.idTecnologia}"/>
									<% pageContext.setAttribute("idTecnologiaString", String.valueOf(pageContext.getAttribute("idTecnologiaLong"))); %>
									<c:set  var="idTipoFrequenciaLong" value="${tipoFrequencia.idTipoFrequencia}"/>
									<% pageContext.setAttribute("idTipoFrequenciaString", String.valueOf(pageContext.getAttribute("idTipoFrequenciaLong"))); %>
									<c:set var="idsFrequencia" value="${pageFlow.tecnologiasFrequencias_frequenciaSelecionadas[idTecnologiaString][idTipoFrequenciaString]}"/>	
									<table id="frequencias_tipoFrequencia_${tecnologia.idTecnologia}_${tipoFrequencia.idTipoFrequencia}" style="display:none;" class="tabConteudoGeral" height="200px">
										<tr>
											<td class="titulo" colspan="2">
												<c:if test="${gravar_select_tipo_produto != 10 && gravar_select_tipo_produto != 7 }">
													FREQUÊNCIAS PARA: ${tipoFrequencia.nmTipoFrequencia}
												</c:if>
											</td>
										</tr>
										<c:if test="${tipoFrequencia.listaValorFrequencia.valorFrequenciaList !=null && gravar_select_tipo_produto != 10 && gravar_select_tipo_produto != 7 }">
										<netui:checkBoxGroup dataSource="idsFrequencia" optionsDataSource="${tipoFrequencia.listaValorFrequencia.valorFrequenciaList}" repeater="true">
											<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
												<td width="10%" align="center">
													<netui:checkBoxOption onClick="selecionarTecnologiasFrequencias_Frequencia('hiddenTecnologiasFrequencias', ${tecnologia.idTecnologia}, ${tipoFrequencia.idTipoFrequencia}, this)" tagId="frequencia_tipoFrequencia_cb_${tecnologia.idTecnologia}_${tipoFrequencia.idTipoFrequencia}_${container.item.idVlFrequencia}" value="${container.item.idTecnologiaTpFrequenciaVl}" styleClass="semBorda checkbox"/>
												</td>
												<td width="90%">
													<label for="frequencia_tipoFrequencia_cb_${tecnologia.idTecnologia}_${tipoFrequencia.idTipoFrequencia}_${container.item.idVlFrequencia}">${container.item.vlFrequencia}</label>
												</td>
											</tr>
										</netui:checkBoxGroup>
										</c:if>
									</table>
								</c:forEach>
							</c:forEach>
						</div>
					</td>
				</tr>
			</table>
			</div>
			<br clear="all"/>
			<div style="position: absolute; bottom: 50px;">
				<div class="paginacao" style="float:right;margin-right:5px;" align="right">
					<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
						<c:choose>
							<c:when test="${no_pagina == paginaAtual}">
								<netui:anchor styleClass="selected" onClick="return false;" href="">
									${no_pagina}
								</netui:anchor>
							</c:when>
							<c:otherwise>
								<netui:anchor action="listarCaracteristicas" onClick="abrirPopup1(this.href, [$('hiddenCaracteristicas'), $('hiddenValoresCaracteristicas')], 'right_section');return false;">
									<netui:parameter  name="pagina_solicitada" value="${no_pagina}"/>
									${no_pagina}
								</netui:anchor>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
 			<div class="botao" style="position: absolute; bottom: 10px;">
				<input class="btOk" type="button" onclick="if(checkSelecionarTecTpFreqFreq('hiddenTecnologiasFrequencias')){fecharPopup('popup1');};" value="OK" title="${bundle.default['catalogo.popupSelecionarTecnolgiasFrequencias.OK']}"/>
			</div>

			</form>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>

	</netui-temp:section>
</div>
</netui-temp:template>