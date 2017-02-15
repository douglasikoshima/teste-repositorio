<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<div id="resultado_busca_servico">
<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/pesquisarEN.do">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg" style="position:relative;">
					<div style="height:250px;" class="both-scroll">											
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
							<thead>
								<tr>
									<th class="sortable">C&oacute;digo de SL</th>
									<th class="sortable">C&oacute;digo de SE</th>
									<th >Alterar</th>
									<th >Excluir</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="resultPesquisaEN" property="collectionPesquisarEN" name="servicoFormBean">	
									<tr>
										<td>${resultPesquisaEN.extraFields.map['CD_SISTEMA_LEGADO']}- ${resultPesquisaEN.extraFields.map['DS_SISTEMA_LEGADO']}&nbsp;</td>
										<td>${resultPesquisaEN.cdServico} - ${resultPesquisaEN.dsServico}&nbsp;</td>
										<td class="center">
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/abrirAlterarEN.do?cd_en=${resultPesquisaEN.cdServico}&ds_en=${resultPesquisaEN.dsServico}" onClick="abrirPopup1(this.href, null , 'resultado_pesquisa');return false;">
												<html:img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" title="Alterar" alt="Alterar" />
											</html:link>
										</td>
										<td class="center">
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/abrirExcluirEN.do?cd_en=${resultPesquisaEN.cdServico}" onClick="abrirPopup1(this.href, null , 'resultado_pesquisa');return false;">
												<html:img onclick="" src="/catalogo/static_server/img/botoes/bt-excluir.gif" title="Excluir" alt="Excluir"  />
											</html:link>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>						
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