<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
				
<div class="conteudo_box_top">
	<div class="conteudo_box_top_center">Restri&ccedil;&otilde;es:</div>
	<div class="conteudo_box_top_esq">
	</div>
	<div class="conteudo_box_top_dir openclose">
		<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
	</div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaRestricaoModelo/exportar.do" target="target_download">
					<c:set var="novoModelo" value="true" />
					<div class="both-scroll" style="height:300px;">					
						<table width="100%"  cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaResultadoBuscaProdutos">
							<thead>
								<tr>
								     <th class="center" width="10px">
								     	<input  type="checkbox" name="restricao" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th> 
								     <th align="center" width="40%">Modelo</th>
								     <th>UF</th>    
								     <th nowrap="nowrap">Tipo de Cliente</th>
								     <th>Segmento</th>
								     <th>Carteira</th>
								     <th>Canal</th>
								     <th></th>
								</tr>
							</thead>
							<tbody>							
								<c:if test="${not empty consultaRestricaoModeloForm.resultadoModeloList}">
									<logic:iterate id="modeloList" property="resultadoModeloList" name="consultaRestricaoModeloForm">
								  		<tr>
										    <td class="center">
										        <c:if test="${novoModelo != modeloList.nmModelo}">
											        <%-- <netui:checkBoxOption value="${container.item.idModelo}" styleClass="semBorda belongsToForm checkbox_modelo" labelStyleClass="hide"/> --%>
											        <html:checkbox property="idsModelos" value="${modeloList.idModelo}" styleClass="semBorda belongsToForm checkbox_modelo" />
												</c:if>
											</td>
										    <td class="center">
										    	<c:if test="${novoModelo != modeloList.nmModelo}">
											    	${modeloList.nmModelo}
											    	<c:set var="novoModelo" value="${modeloList.nmModelo}" />
										    	</c:if>
										    </td>
										    <td class="center">${modeloList.modeloRestricoes.nmUf}</td>       
										    <td class="center">${modeloList.modeloRestricoes.nmTipoCliente}</td>
										    <td class="center">${modeloList.modeloRestricoes.sgSegmento}</td>
										    <td class="center">${modeloList.modeloRestricoes.sgCarteira}</td>
										    <td class="center">${modeloList.modeloRestricoes.nmCanal}</td>
										    <td  class="center">
										    	<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaRestricaoModelo/popupDetalheModelo.do?id_modelo=${modeloList.idModelo}" 
										    		onClick="abrirPopup1(this.href);return false;" >
										    		<html:img alt="Detalhe Modelo" src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif"  /> 
										    	</html:link>
											</td>
								   		</tr>
									</logic:iterate>
								</c:if>
								
							</tbody>
						 </table>
					</div>
					<div class="barra"></div>
					<div class="paginacao" style="width:99%;" align="right">
						<c:set var="begin" value="${(paginaAtual>5)?(paginaAtual-5):(1)}"/>
						<c:set var="end" value="${(paginaAtual<(totalPagina-5))?(begin+9):(totalPagina)}"/>
						<c:if test="${begin > 1}">
							<html:link onClick="$('pagina_solicitada').value='1';$('botao_pesquisar_modelos').onclick();return false;" href="#">
								<<
							</html:link>
							&nbsp;
							<html:link onClick="$('pagina_solicitada').value='${paginaAtual-1}';$('botao_pesquisar_modelos').onclick();return false;" href="#">
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
									<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_modelos').onclick();return false;" href="#">
										${no_pagina}
									</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${end < totalPagina}">
							<html:link onClick="$('pagina_solicitada').value='${paginaAtual+1}';$('botao_pesquisar_modelos').onclick();return false;" href="#">
								>
							</html:link>
							&nbsp;
							<html:link onClick="$('pagina_solicitada').value='${totalPagina}';$('botao_pesquisar_modelos').onclick();return false;" href="#">
								>>
							</html:link>
						</c:if>
					    <div class="barra"></div>
					    <div class="botao">
					   		<label class="lblForm">Quantidade : ${totalRegistros}</label> 
							<html:submit bundle="messages" styleClass="btNavegacao74" value="Exportar" titleKey="catalogo.resultadoBuscaModelosRestricoes.Exportar" />
					    </div>
				  	</div>
				</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom">
			</div>
		</div>
	</div>

<iframe id='target_download' name='target_download' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_pesquisa');"></iframe>