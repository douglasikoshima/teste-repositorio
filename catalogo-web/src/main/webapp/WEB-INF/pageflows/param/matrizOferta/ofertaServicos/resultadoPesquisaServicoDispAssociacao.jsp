<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div style="float: left">
<div id="response_error" style="position: relative"></div>
</div>
<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/validarServicoOfertaServico.do" styleId="lista_servicos" target="target_return_tela">
	<div id="resultado_pesquisa_servicos_disp" style="width:100% ;position: relative">
		<html:hidden property="idOfertaServico" styleId="idOfertaServico" value="${idOfertaServico}" />
		<html:hidden property="cdOfertaServico" styleId="cdOfertaServico" value="${cdOfertaServico}" />
			<div class="both-scroll" style="height: 280px; width: 100%;">
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
							<thead>
								<tr>
									<th class="center"><input type="checkbox" name="servico" class="semBorda"  onclick="selectTodosCheckbox('lista_servicos', '.checkbox_servico', this.checked);"/></th>
									<th class="sortable">Nome Comercial</th>
									<th class="sortable">Nome T&eacute;cnico</th>
									<th class="sortable">Grupo de Serviços</th>
									<th class="sortable">Tipo de Servi&ccedil;o</th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${ofertaServicosMatrizOfertaForm.servicosParaAssociar ne null}">												
								<logic:iterate id="servicos" property="servicosParaAssociar" name="ofertaServicosMatrizOfertaForm">					
								<tr>
									<td class="center" style="padding-left: 2px">
										<html:checkbox property="idsServico" value="${servicos.idServico}" styleId="checkbox_servico_${servicos.idServico}" styleClass="semBorda belongsToForm checkbox_servico" />
									</td>
									<td>${servicos.nmComercial}</td>
									<td>${servicos.cdServico}</td>
									<td>${servicos.nmCategoriaCatalogo}</td>
									<td>${servicos.dscTipoServico}</td>
								</tr>
								</logic:iterate>
							</c:if>							
						</tbody>
					</table>
					<c:if test="${ofertaServicosMatrizOfertaForm.servicosParaAssociar == null}">	
					   <br><span align='center'>Nenhum Servi&ccedil;o encontrado.</span>
					</c:if>						
			</div>
			
			<br clear="all">
			<div class="paginacao" style="width:99%; height: auto; bottom: 30px;" align="right">	
				<c:set var="begin" value="${(paginaAtual>5)?(paginaAtual-5):(1)}"/>
				<c:set var="end" value="${(paginaAtual<(totalPagina-5))?(begin+9):(totalPagina)}"/>
				<c:if test="${begin > 1}">
                    <html:link styleId="servicoDispAssociacao" onClick="$('pagina_solicitada').value='1';$('botao_pesquisar_servicos_sem_associasao').onclick();return false;" href="#">
                    	<<
                    </html:link>
                    &nbsp;
                    <html:link styleId="servicoDispAssociacao" onClick="$('pagina_solicitada').value='${paginaAtual-1}';$('botao_pesquisar_servicos_sem_associasao').onclick();return false;" href="#">
                    	<
                    </html:link>                    				
				</c:if>
				<c:forEach begin="${begin}" end="${end}" var="no_pagina">
					<c:choose>
						<c:when test="${no_pagina == paginaAtual}">
							<html:link styleClass="selected" onClick="return false;" href="">${no_pagina}</html:link>
						</c:when>
						<c:otherwise>
							<html:link styleClass="selected" onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_servicos_sem_associasao').onclick();return false;" href="#">
							${no_pagina}
							</html:link>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${end < totalPagina}">
                    <html:link styleId="servicoDispAssociacao" onClick="$('pagina_solicitada').value='${paginaAtual+1}';$('botao_pesquisar_servicos_sem_associasao').onclick();return false;" href="#">
                    	>
                    </html:link>
                    &nbsp;
                    <html:link styleId="servicoDispAssociacao" onClick="$('pagina_solicitada').value='${totalPagina}';$('botao_pesquisar_servicos_sem_associasao').onclick();return false;" href="#">
                    	>>
                    </html:link>  					
				</c:if>
			</div>
			<div class="barra"></div>
			<div class="botao">
				<label class="lblForm">Quantidade : ${totalRegistros}</label> 
				<html:button property="bt_associar" styleClass="btNavegacao74" value="Associar" onClick="send(this, 'abertura_popup', null, 'response_error');return false" title="Associar Serviço" />	
			</div>
				
			<div id="abertura_popup" style="display:none">
				<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/confirmAssociacaoServicoOfertaServico.do" styleId="idLinkPopUpConfirm" styleClass="hide" onClick="abrirPopup2(this.href, null, 'right_section');return false"/>
			    <html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/incompatibilidadeAssociacaoServicoOfertaServico.so" styleId="idLinkPopUpError" styleClass="hide" onClick="abrirPopup2(this.href, null, 'right_section');return false"/>		 		
			</div>
	</div>
</html:form>
<iframe id='target_return_tela' name='target_return_tela' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_pesquisa_popup');"></iframe>							

