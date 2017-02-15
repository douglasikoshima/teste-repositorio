<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="resultado_busca_servico_negocio">
<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/pesquisarSN.do">
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
					<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" >
						<thead>
							<tr>
								<th class="sortable">C&oacute;digo de SN</th>
								<th class="sortable">Descri&ccedil;ão de SN</th>
								<th >Alterar</th>
								<th >Excluir</th>
							</tr>
						</thead>
					</table>
					<div style="height:250px;" class="both-scroll">
<!-- 						<netui-data:repeater dataSource="actionForm.voListPage" defaultText="<br><span align='center'>Nenhum Serviço de Negocio encontrado.</span>">
								<netui-data:repeaterHeader> -->
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
										<thead>
											<tr>
												<th class="sortable">C&oacute;digo de SN</th>
												<th class="sortable">Descri&ccedil;ão de SN</th>
												<th >Alterar</th>
												<th >Excluir</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${servicoNegocioForm.listaServicoNegocio == null}">	
											   <br><span align='center'>Nenhum Serviço de Negocio encontrado.</span>
											</c:if>	
											<c:if test="${servicoNegocioForm.listaServicoNegocio ne null}">										
									        <logic:iterate id="listPageTO" property="listaServicoNegocio" name="servicoNegocioForm">
											<tr>
												<td>${listPageTO.cdServicoNegocio}</td>
												<td>${listPageTO.dsServicoNegocio}</td>
												<td class="center">
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/abrirAlterarSN.do?cd_sn=${listPageTO.cdServicoNegocio}&ds_sn=${listPageTO.dsServicoNegocio}" onClick="abrirPopup1(this.href, null , 'resultado_pesquisa');return false;">
										 				<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" title="Alterar" >
													</html:link>
												</td>
												<td class="center">
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/abrirExcluirSN.do?cd_sn=${listPageTO.cdServicoNegocio}" onClick="abrirPopup1(this.href, null , 'resultado_pesquisa');return false;">
														<img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif" title="Excluir" alt="Excluir" />
													</html:link>
												</td>
											</tr>
									        </logic:iterate>
									        </c:if>
										</tbody>
 									</table>
<!-- 										</netui-data:repeaterFooter>
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
					</div>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
	</html:form>
</div>
<br clear="all" />