<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<catalogo:popupPadrao>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/abrirpopupTecnologiaModelo.do">
		<input id="larguraPopup" style="display:none;" value="800"/>
		<input id="alturaPopup" style="display:none;" value="100"/>
		<div class="titulo">${nmFabricante}&nbsp;${modelo.nmModelo}
			<c:if test="${not empty consultaRestricaoModeloForm.listaTecnologia}">
				<logic:iterate id="tecnologia" property="listaTecnologia" name="consultaRestricaoModeloForm">
					- ${tecnologia.nmTecnologia}
					<c:if test="${not empty consultaRestricaoModeloForm.listaTipoFrequencia}">
 						<logic:iterate id="tipoFrequencia" property="listaTipoFrequencia" name="consultaRestricaoModeloForm">
							: ${tipoFrequencia.nmTipoFrequencia} 
							<c:if test="${not empty consultaRestricaoModeloForm.listaValorFrequencia}">
								<logic:iterate id="valorFrequencia" property="listaValorFrequencia" name="consultaRestricaoModeloForm" indexId="index">
									<c:if test="${index > 0}">/</c:if>${valorFrequencia.vlFrequencia}
								</logic:iterate>
							</c:if>
						</logic:iterate>
					</c:if>
				</logic:iterate>
			</c:if>
		</div>
		<table width="100%">
			<tr>
				<td width="370">	
					<c:set var="nomeBox" scope="application"  value="Caracteristicas do Modelo"/>
					<jsp:include page="/templates/box_pre.jsp"/>
							<div class="fleft" style="width:120px; height:200px;">
								<br>
								<c:set var="display" value=""/>
								<c:if test="${not empty consultaRestricaoModeloForm.multimidiaList}">																
	 								<logic:iterate id="multimidia" property="multimidiaList" name="consultaRestricaoModeloForm" indexId="index" >
											<c:if test="${index > 0}">
												<c:set var="display" value="none"/>
											</c:if>
											<div id="multimidia_${index}" style="display:${display};" align="center">
												<div class="foto-moldura">
													<c:set var="image" value=""/>
													<c:if test="${consultaRestricaoModeloForm.caminho_link_imagens_modelo != null}">
														<c:set var="image" value="${consultaRestricaoModeloForm.caminho_link_imagens_modelo}"/>
													</c:if>
													<html:img height="165px" width="110px" src="/${image}/${multimidia.nmMultimidia}" title="/${image}/${multimidia.nmMultimidia}" />
												</div>
												<div align="center" style="margin-top:5px;">
													<c:choose>
														<c:when test="${index > 0}">
															<html:link href="#" onclick="$('multimidia_${index}').hide();$('multimidia_${index -1 }').show();return false;" title="Foto Anterior">
																<html:img src="/catalogo/static_server/img/botoes/bt-fotos-anterior.gif" alt="Foto Anterior" title="Foto Anterior" />
															</html:link>
														</c:when>
														<c:otherwise>
															<html:link href="#" style="visibility:hidden;" onclick="" title="Foto Anterior" >
																<html:img src="/catalogo/static_server/img/botoes/bt-fotos-anterior.gif" alt="Foto Anterior" title="Foto Anterior" />
															</html:link>
														</c:otherwise>
													</c:choose>
													<html:img src="/catalogo/static_server/img/botoes/bt-fotos-middle.gif" />
													<c:choose>
														<c:when test="${ fn:length(consultaRestricaoModeloForm.multimidiaList) > (index +1) }">
															<html:link href="#" onclick="$('multimidia_${index}').hide();$('multimidia_${index +1 }').show();return false;" title="Próxima Foto">
																<html:img src="/catalogo/static_server/img/botoes/bt-fotos-proxima.gif" alt="Próxima Foto" title="Próxima Foto" />
															</html:link>
														</c:when>
														<c:otherwise>
															<html:link href="#" style="visibility:hidden;" onclick="" title="Próxima Foto">
																<html:img src="/catalogo/static_server/img/botoes/bt-fotos-proxima.gif" alt="Próxima Foto" title="Próxima Foto" />
															</html:link>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
									</logic:iterate>
								</c:if>
							</div>
							<div class="fleft" style="width:14px;">
							</div>
							<div class="fleft vertical-scroll" style="width:170px;height:200px;overflow:auto;" align="left">
								<div align="left">
									<ul class="dados-list" style="margin-left:0px">
										<c:if test="${not empty consultaRestricaoModeloForm.caracteristicaList}">																				
	 										<logic:iterate id="caracteristic" property="caracteristicaList" name="consultaRestricaoModeloForm">
												<li>${caracteristic.nmCaracteristica}															
													<c:if test="${not empty consultaRestricaoModeloForm.listaValorCaracteristica}">																
														<logic:iterate id="valorCaracteristica" property="listaValorCaracteristica" name="consultaRestricaoModeloForm">	
															<c:if test="${ valorCaracteristica.idCaracteristica  == caracteristic.idCaracteristica  }">																														
																	/ ${valorCaracteristica.valor}
															</c:if>																		
														</logic:iterate>																
													</c:if>
												</li>
											</logic:iterate>
										</c:if>
									</ul>
								</div>
							</div>
						<br clear="all"/>
					<jsp:include page="/templates/box_pos.jsp"/>
				</td>
				
				<td width="370">
				<c:set var="nomeBox" scope="application"  value="Código do Produto"/>
					<jsp:include page="/templates/box_pre.jsp"/>
						<div style="height:200px;">	
							<c:if test="${not empty consultaRestricaoModeloForm.sistemaProdutoList}">
								<logic:iterate id="listSistProd" property="sistemaProdutoList" name="consultaRestricaoModeloForm" >															
								  	<div class="dados-list">
								  		<li>${listSistProd.cdCodigo}&nbsp;(${listSistProd.nmGama})</li>
								  	</div>
								</logic:iterate>
							</c:if>
						</div>
					<jsp:include page="/templates/box_pos.jsp"/>
				</td>
			</tr>
		</table>
	</html:form>
</catalogo:popupPadrao>