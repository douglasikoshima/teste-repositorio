<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="resultado_busca_modelo">
<div class="conteudo_box_top">
	<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
	<div class="conteudo_box_top_esq">
	</div>
	<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/exportar.do" target="target_download">
					<c:if test="${ (nomes_valores_caracteristicas != null && fn:length(nomes_valores_caracteristicas) > 1 ) || (nomes_tecnologias != null && fn:length(nomes_tecnologias) > 1) }">
						<div style="padding-top:10px;padding-bottom: 10px;padding-left:10px;">
							<font style="font-style: italic;">
								<c:if test="${nomes_tecnologias != null && fn:length(nomes_tecnologias) > 1}">
									<strong>Tecnologia</strong>:
									${nomes_tecnologias}
								</c:if>
								&nbsp;
								<c:if test="${nomes_valores_caracteristicas != null && fn:length(nomes_valores_caracteristicas) > 1}">
									<strong>Caracteristica</strong>:
									${nomes_valores_caracteristicas}
								</c:if>
							</font>
						</div>
					</c:if>
												
						<c:if test="${modelos == null }">
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
								<thead>
									<tr>
										<th class="center"><input  type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
										<th class="sortable">Modelo</th>
										<th class="sortable">Fabricante</th>
										<th class="sortable">Tipo de Produto</th>
										<th width="1px">Fim de Vida</th>
										<th width="1px">Disp</th>
										<th></th>
									</tr>
								</thead>
							</table>
							<br><span align='center'>Modelo não encontrado.</span>
							
						</c:if>
						
						<div class="both-scroll" style="height:300px"> 
						
						<c:if test="${modelos !=null }">
							
								
								<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
									<thead>
										<tr>
											<th class="center"><input  type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
											<th>Modelo</th>
											<th>Fabricante</th>
											<th>Tipo de Produto</th>
											<th width="1px">Fim de Vida</th>
									        <th width="1px">Disp</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
								
								<logic:iterate id="modelos" property="modelos" name="ConsultaModeloForm">
									<tr>
										<td class="center">
											<html:checkbox property="idGrupoProdutoSelecionados" value="${modelos.idGrupoProduto}" styleClass="semBorda belongsToForm checkbox_modelo" />
										</td>
									
										<td class="center">${modelos.nmGrupoProduto}</td>
										<td class="center">${modelos.nmFabricante}</td>
										<td class="center">${modelos.nmTipoProduto}</td>
										<td>
											<c:choose>
												<c:when test="${modelos.inFimVida=='S'}">
													<input id="fim_vida_${modelos.idGrupoProduto}" type="checkbox" class="SemBorda" checked="checked" value="fimVida" disabled/>
												</c:when>
												<c:otherwise>
													<input  id="fim_vida_${modelos.idGrupoProduto}" type="checkbox" class="SemBorda" value="fimVida" disabled/>
												</c:otherwise>
											</c:choose>
											
										</td>
										<td>			
											<c:choose>
												<c:when test="${modelos.inDisponivel=='S'}">
													<input id="disp_${modelos.idGrupoProduto}" type="checkbox" class="SemBorda" checked="checked" value="disp" disabled/>
												</c:when>
												<c:otherwise>
													<input  id="disp_${modelos.idGrupoProduto}" type="checkbox" class="SemBorda" value="disp" disabled/>
												</c:otherwise>
											</c:choose>	
										</td>
										<td  class="center">
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/popupDetalheModelo.do?id_modelo=${modelos.idGrupoProduto}" onclick="abrirPopup1(this.href, null, 'resultado_busca_modelo');return false;" >
												<html:img alt="DetalheModelo" src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif" />
											</html:link>
										</td>
									</tr>
								</logic:iterate>					
									
									
									</tbody>
								</table>
								
							
						</c:if>
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
										<html:link onclick="$('pagina_solicitada').value='${no_pagina}';$('botao_listar_modelos').onclick();return false;" href="#">
											${no_pagina}
										</html:link>
									</c:otherwise>
								</c:choose>
							</c:forEach>	
						</div>
					<div class="barra"></div>
					<div class="botao"><label class="lblForm">Quantidade : ${totalRegistros}</label> 
						<html:submit bundle="messages" styleClass="btNavegacao74" value="Exportar" titleKey="catalogo.resultadoBuscaModelos.Exportar"/>
					</div>
				</html:form>		
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
</div>
<iframe id='target_download' name='target_download' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_pesquisa');"></iframe>