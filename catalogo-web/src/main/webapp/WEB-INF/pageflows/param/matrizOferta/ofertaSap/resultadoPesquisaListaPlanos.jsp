<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<div id="resultado_pesquisa_plano_oferta" style="position:relative;">
<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/gravarComposicaoOfertaSap.do">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa de Planos</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg">
				<div style="width:100%;position:relative;" id="lista_planos">		
					<html:hidden property="idMatrizOferta" value="${idMatrizOferta}"/>
					<html:hidden property="idOfertaSap" value="${idOfertaSap}"/>
						<div class="both-scroll" style="height: 250px;">
								<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="lista_plano_oferta">
									<thead>
										<tr>
											<th><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_planos', '.checkbox_plano', this.checked);"/></th>
											<th class="sortable">Nome T&eacute;cnico</th>
											<th class="sortable">Nome Comercial</th>
											<th>UF</th>
											<th>Disp</th>
										</tr>
									</thead>
								<tbody>
								<logic:iterate id="plano" property="planoLista" name="ofertaSapForm">
									<c:set value="true" var="firstPass"/>
										<tr>
	  										<td><html:checkbox property="idsPlanos" styleClass="semBorda checkbox_plano" value="${plano.idSistemaPlano}"></html:checkbox></td>
 											<td style="text-align: left; padding-left: 6px">
												<c:if test="${firstPass == true}">
													${plano.nmTecnico}
												</c:if>
											</td>
											<td style="text-align: left; padding-left: 6px">${plano.nmComercial}</td>
										
											<td class="center">
												<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/showListaUFs.do?id_plano=${plano.idPlano}&id_plataforma=${idPlataforma}" onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;" title="UFs">
													<img alt="UFs" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>
	 											</html:link>
											</td>
											<td class="center">
												<c:choose>
													<c:when test="${plano.inDisponivel == 'S'}">
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
													</c:when>
													<c:otherwise>
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
													</c:otherwise>
												</c:choose>
											</td>								
										</tr>
										<c:set value="false" var="firstPass"/>
										</logic:iterate>
									</tbody>
								</table>
						</div>
					</div>
			</div>
			<br>
			<div class="paginacao" style="width:99%; height: auto; bottom: 30px;" align="right">
				<c:set var="begin" value="${(paginaAtual>5)?(paginaAtual-5):(1)}"/>
				<c:set var="end" value="${(paginaAtual<(totalPagina-5))?(begin+9):(totalPagina)}"/>
				<c:if test="${begin > 1}">
					<html:link onClick="$('paginaSolicitada').value='1';$('botao_pesquisar_planos_oferta').onclick();return false;" href="#">
						<<
					</html:link>
					&nbsp;
					<html:link onClick="$('paginaSolicitada').value='${paginaAtual-1}';$('botao_pesquisar_planos_oferta').onclick();return false;" href="#">
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
							<html:link onClick="$('paginaSolicitada').value='${no_pagina}';$('botao_pesquisar_planos_oferta').onclick();return false;" href="#">
								${no_pagina}
							</html:link>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${end < totalPagina}">
					<html:link onClick="$('paginaSolicitada').value='${paginaAtual+1}';$('botao_pesquisar_planos_oferta').onclick();return false;" href="#">
						>
					</html:link>
					&nbsp;
					<html:link onClick="$('paginaSolicitada').value='${totalPagina}';$('botao_pesquisar_planos_oferta').onclick();return false;" href="#">
						>>
					</html:link>
				</c:if>
			</div>
			<div class="barra"></div>
			<div class="botao" style="padding-left: 6px">
				<label class="lblForm" >Quantidade : ${totalRegistros}</label>
			</div>
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
	<br>
	<div style="position: relative">
		<div class="secao_conteudo" style="position: relative">
			<div class="conteudo_box_top">
				<div class="conteudo_box_top_center">Selecionar Oferta de Servi&ccedil;o</div>
				<div class="conteudo_box_top_esq"></div>
				<div class="conteudo_box_top_dir openclose">
					<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
				</div>
			</div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg relative">
						
						<div class="fleft">
						
							<br clear="all"/>
							<div class="fleft">
								<div class="label-form-bold" style="width: 170px;">Oferta de Servi&ccedil;os:</div>
								<html:select property="idOfertaServico" style="width:300px;" styleId="select_oferta_servico">
									<html:option value="">-- Selecione --</html:option>
									<c:forEach var="ofertaServicoTO" items="${ofertaServicoLista}">
										<html:option value="${ofertaServicoTO.idOfertaServico}">${ofertaServicoTO.nmOfertaServico}</html:option>
									</c:forEach>
								</html:select>
							</div>
						</div>	
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
					    <span>&nbsp;</span>
							<html:button bundle="messages" property="gravar" styleId="botao_gravar_composicao_oferta" onClick="send(this, 'div_retorno_erro', null, 'resultado_pesquisa_sap');return false" styleClass="btNavegacao74" value="Gravar" altKey="catalogo.global.form.Gravar" titleKey="catalogo.global.form.Gravar"/>
					</div>
					<div class="conteudo_box_bottom"></div>
				</div>
			</div>
		</div>
	</div>
</html:form>
</div>
