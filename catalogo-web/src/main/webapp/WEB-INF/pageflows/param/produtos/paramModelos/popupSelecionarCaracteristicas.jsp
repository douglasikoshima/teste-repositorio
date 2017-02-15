<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
<input type="hidden" id="alturaPopup" value="350"/>
<div id="popup_selecionar_caracteristicas">
	<netui-temp:section name="conteudo">
		<c:set var="nomeBox" scope="application"  value="Caracteristicas"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div align="left" class="largePopup">
			<table width="100%">
				<tr>
					<td valign="top" width="50%">
						<form class="fleft">
							<div class="vertical-scroll" style="height:280px;">
								<table class="tabConteudoGeral">
									<netui:checkBoxGroup dataSource="pageFlow.caracteristicasSelecionadas" optionsDataSource="${caracteristicas}" repeater="true">
										<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
											<td width="10px" >
												<netui:checkBoxOption onClick="selecionarCaracteristica('hiddenCaracteristicas', this, 'hiddenCaracteristicasNomes', '${container.item.nmCaracteristica}' );mostrarValoresCaracteristica('div_valores','${container.item.idCaracteristica}');" tagId="caracteristicas_cb_${container.item.idCaracteristica}" value="${container.item.idCaracteristica}" styleClass="semBorda"/>
											</td>
											<td class="word-wrap">
												<label for="caracteristica_cb_${container.item.idCaracteristica}" onclick="mostrarValoresCaracteristica('div_valores','${container.item.idCaracteristica}');">${container.item.nmCaracteristica}</label>
											</td>
										</tr>
									</netui:checkBoxGroup>
								</table>
							</div>
						</form>
					</td>
					<td valign="top" width="50%">
						<div id="div_valores" class="fleft vertical-scroll" style="height:280px;width:100%;">
							<c:forEach items="${caracteristicas}" var="caracteristica" >
									<table id="valores_caracteristica_${caracteristica.idCaracteristica}" style="display:none;" class="tabConteudoGeral" height="250px">
										<tr><td class="titulo word-wrap" colspan="2" >VALORES PARA: ${caracteristica.nmCaracteristica}</td></tr>
										<c:if test="${caracteristica.listaValorCaracteristica.valorCaracteristicaList !=null }">
										<netui:checkBoxGroup dataSource="pageFlow.valoresCaracteristicasSelecionadas" optionsDataSource="${caracteristica.listaValorCaracteristica.valorCaracteristicaList}" repeater="true">
											<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
												<td width="10%" align="center">
													<netui:checkBoxOption onClick="selecionarValorCaracteristica('hiddenCaracteristicas', ${container.item.idCaracteristica}, this, 'hiddenCaracteristicasNomes', '${caracteristica.nmCaracteristica}', '${container.item.valor}')" tagId="valores_caracteristicas_cb_${container.item.idValorCaracteristica}" value="${container.item.idValorCaracteristica}" styleClass="semBorda checkbox"/>
												</td>
												<td width="90%" class="word-wrap">
													<label for="valores_caracteristicas_cb_${container.item.idValorCaracteristica}">${container.item.valor}</label>
												</td>
											</tr>
										</netui:checkBoxGroup>
										</c:if>
									</table>
							</c:forEach>
						</div>
					</td>
				</tr>
			</table>
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
				<input class="btOk" type="button" onclick="fecharPopup('popup1');" value="OK" title="${bundle.default['catalogo.popupSelecionarCaracteristicas.OK']}"/>
			</div>

			</form>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>

	</netui-temp:section>
</div>
</netui-temp:template>