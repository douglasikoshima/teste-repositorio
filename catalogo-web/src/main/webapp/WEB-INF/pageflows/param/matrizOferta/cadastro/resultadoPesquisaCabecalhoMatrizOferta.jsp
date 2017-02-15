<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>


<div id="resultado_pesquisa_cabecalho_matriz" style="position:relative;">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg">
				<div style="width:100%;position:relative;">		
<!-- 					<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
						<thead>
							<tr>
								<th class="sortable">Nome da Matriz</th>
								<th class="sortable">Vigência Inicial</th>
								<th class="sortable">Vigência Final</th>
								<th>Alterar</th>
								<th>Excluir</th>
							</tr>
						</thead>
					</table> -->
					<div class="both-scroll" style="height: 300px;">
						<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="lista_cabecalho_matriz_oferta">
							<thead>
								<tr>
									<th class="sortable">Nome da Matriz</th>
									<th class="sortable">Vig&ecirc;ncia Inicial</th>
									<th class="sortable">Vig&ecirc;ncia Final</th>
									<th>Alterar</th>
									<th>Excluir</th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${not empty cadastroCabecalhoMatrizOfertaForm.mapaDataIncial && not empty cadastroCabecalhoMatrizOfertaForm.mapaDataFinal}">
								<logic:iterate id="cabecalhoMatrixOferta" property="cabecalhoMatrizOfertaLista" name="cadastroCabecalhoMatrizOfertaForm" indexId="index">	
								<c:set value="true" var="firstPass"/>
									<tr>
										<td style="text-align: left; padding-left: 5px">
											<c:if test="${firstPass == true}">
												${cabecalhoMatrixOferta.nmMatrizOferta}
											</c:if>
										</td>
										<td>
											<c:set var="dtInicial" value="${cadastroCabecalhoMatrizOfertaForm.mapaDataIncial[cabecalhoMatrixOferta.nmMatrizOferta]}" />
											<bean:define id="dtInicial" value="${dtInicial}" />
											<bean:write bundle="messages" name="dtInicial" />
										</td>
										<td>
											<c:set var="dtFinal" value="${cadastroCabecalhoMatrizOfertaForm.mapaDataFinal[cabecalhoMatrixOferta.nmMatrizOferta]}"/>
											<bean:define id="dtFinal" value="${dtFinal}" />
											<bean:write bundle="messages" name="dtFinal" />
										</td>
										<td class="center">
											<c:set var="dtInicialVigencia" value="${cadastroCabecalhoMatrizOfertaForm.mapaDataIncial[cabecalhoMatrixOferta.nmMatrizOferta]}" />
											<c:set var="dtFinalVigencia" value="${cadastroCabecalhoMatrizOfertaForm.mapaDataFinal[cabecalhoMatrixOferta.nmMatrizOferta]}"/>
											
											<cata:temPermissao acao="matrizOfertaAlterar">
												<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/abrirParaAlterarCabecalhoMatrizOferta.do?id_matriz_oferta=${cabecalhoMatrixOferta.idMatrizOferta}&nm_matriz_oferta=${cabecalhoMatrixOferta.nmMatrizOferta}&id_tipo_matriz_oferta=${cabecalhoMatrixOferta.idMatrizOfertaTipo}&vigencia_inicial=${dtInicialVigencia}&vigencia_final=${dtFinalVigencia}&indice=${index}" styleId="alterar_modelo_menda" onClick="if(abrirLink('div_alterar_cabecalho_matriz', this.href, 'right_section')){clearAndShow('div_alterar_cabecalho_matriz');}return false" >
													<html:img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" />
												</html:link>
											</cata:temPermissao>
										</td>
										<td class="center">
											<cata:temPermissao acao="matrizOfertaExcluir">
												<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/abrirPopupConfirmExclusaoCabecalho.do?id_matriz_oferta=${cabecalhoMatrixOferta.idMatrizOferta}&nm_matriz_oferta=${cabecalhoMatrixOferta.nmMatrizOferta}" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa')){$('div_cadastro_novo_cabecalho').innerHTML=''}return false;">
													<html:img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif"/>
												</html:link>
											</cata:temPermissao>
										</td>								
									</tr>
									<c:set value="false" var="firstPass"/>
								</logic:iterate>
								</c:if>
								</tbody>
							</table>
					</div>
				</div>
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
							<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_cabecalho_matriz_oferta').onclick();return false;" href="#">
								${no_pagina}
							</html:link>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div class="barra"></div>
			<div class="botao">
				<label>&nbsp;</label>
				<label class="lblForm" >Quantidade : ${totalRegistros}</label>
			</div>
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
</div>
<br/>
<div id="div_returno_erro_alteracao" style="display: none; position: relative;"></div>
<div id="div_alterar_cabecalho_matriz" style="display: none; position: relative;"></div>
