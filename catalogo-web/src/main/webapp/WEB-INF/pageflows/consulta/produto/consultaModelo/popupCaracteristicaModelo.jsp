<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>


<catalogo:popupPadrao>
<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/abrirpopupCaracteristicaModelo.do">
	<input type="hidden" id="alturaPopup" value="350"/>
			<c:set var="nomeBox" scope="application"  value="Caracteristicas"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div align="left" class="largePopup">
				<table width="100%">
					<tr>
						<td valign="top" width="50%">
							<form class="fleft">
								<div class="vertical-scroll" style="height:270px;width:100%;">
									<table class="tabConteudoGeral">
	
										<logic:iterate id="caracteristicas" property="listaCaracteristicas" name="ConsultaModeloForm">
											<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
												<td width="10px">
												<html:multibox property="caracteristicasSelecionadas" onClick="selecionaCheckBoxPopup('hiddenCaracteristicas', this);selecionarNomesCaracteristicas('hiddenCaracteristicas_Nomes', '${caracteristicas.nmCaracteristica}', this);mostrarValoresCaracteristica('div_valores','${caracteristicas.idCaracteristica}');" styleClass="semBorda">
							                        <bean:write bundle="messages" name="caracteristicas"  property="idCaracteristica" />
												</html:multibox>
												</td>
												<td width="100px" nowrap="nowrap">
												    <label for="caracteristica_cb_${caracteristicas.idCaracteristica}" onclick="mostrarValoresCaracteristica('div_valores','${caracteristicas.idCaracteristica}');">${caracteristicas.nmCaracteristica}</label>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</form>
						</td>
						<td valign="top" width="50%">
							<div id="div_valores" class="fleft vertical-scroll" style="height:230px;width:100%;">
								
								<logic:iterate id="caracteristica" property="listaCaracteristicas" name="ConsultaModeloForm">
										<table id="valores_caracteristica_${caracteristica.idCaracteristica}" style="display:none;" class="tabConteudoGeral" width="200px" >
											<tr><td class="titulo" colspan="2">VALORES PARA: ${caracteristica.nmCaracteristica}</td></tr>
											<c:if test="${caracteristica !=null }">
												<c:forEach items="${caracteristica.listaValorCaracteristica}" var="sublista" >
													<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
													<td width="10%" align="center">
														<html:multibox property="valoresCaracteristicasSelecionadas" onclick="selecionaCheckBoxPopup('hiddenValoresCaracteristicas', this);selecionarNomeValorCaracteristica('hiddenCaracteristicas_Nomes', '${caracteristica.nmCaracteristica}','${sublista.valor}', this)">
															<c:out value="${sublista.idValorCaracteristica}"/>
														</html:multibox>
													</td>
													<td width="90%">
														<label for="valores_caracteristicas_cb_${sublista.idValorCaracteristica}">${sublista.valor}</label>
													</td>
												</c:forEach>
											</c:if>
										</table>
								</logic:iterate>
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
									<html:link styleClass="selected" onclick="return false;" href="">
										${no_pagina}
									</html:link>
								</c:when>
								<c:otherwise>
									<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/abrirpopupCaracteristicaModelo.do?pagina_solicitada=${no_pagina}" onclick="abrirPopup1(this.href, [$('hiddenCaracteristicas'), $('hiddenValoresCaracteristicas')], 'right_section');$('pagina_solicitada').value='${no_pagina}';return false;">
										${no_pagina}
									</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>	
					</div>
				</div>
	 			<div class="botao" style="position: absolute; bottom: 10px;">
					<html:button bundle="messages" property="btOk" styleClass="btOk" onclick="fecharPopup('popup1');" value="OK" titleKey="catalogo.popupCaracteristicaModelo.OK" />
				</div>
	
				</form>
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</html:form>
</catalogo:popupPadrao>