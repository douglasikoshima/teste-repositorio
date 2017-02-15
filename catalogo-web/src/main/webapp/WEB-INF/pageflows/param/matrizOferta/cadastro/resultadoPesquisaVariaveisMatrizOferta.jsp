<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>

<div id="resultado_pesquisa_variaveis_matriz" style="position:relative;">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Vari&aacute;veis de Elegibilidade</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg">
				<div style="width:100%;position:relative;">
					<div class="both-scroll" style="height: 300px;">
						<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="lista_variaveis_matriz">
							<thead>
								<tr>
									<th class="sortable">Tipo de Pessoa</th>
									<th class="sortable">Carteira</th>
									<th class="sortable">Segmento</th>
									<th>Excluir</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty cadastroCabecalhoMatrizOfertaForm.listaVariaveis}">
									<logic:iterate id="listaVariaveisTO"  property="listaVariaveis" name="cadastroCabecalhoMatrizOfertaForm" indexId="index">
									<c:set value="true" var="firstPass"/>
										<tr>
											<td>
												<c:if test="${firstPass == true}">
												<c:choose>
													<c:when test="${fn:toUpperCase(listaVariaveisTO.nmTipoCliente) eq 'PF' or fn:toUpperCase(listaVariaveisTO.nmTipoCliente) eq 'PESSOA FÍSICA' or fn:toUpperCase(listaVariaveisTO.nmTipoCliente) eq 'PESSOA FISICA'}">
														${'Pessoa F&iacute;sica'}
													</c:when>
													<c:otherwise>
														${'Pessoa Jur&iacute;dica'}
													</c:otherwise>									
												</c:choose>
												</c:if>
											</td>											
											<c:set var="nmCarteira" value="${listaVariaveisTO.nmCarteira}" />
											<c:set var="nmSegmento" value="${listaVariaveisTO.nmSegmento}" />
											<td><c:out value="${nmCarteira}"/></td>
											<td><c:out value="${nmSegmento}"/></td>
											<td class="center">
												<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/abrirPopupConfirmExclusaoVariavel.do?id_matriz_oferta_variaveis=${listaVariaveis.idMatrizOfertaVariaveis}" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa')){$('div_cadastro_novo_cabecalho').innerHTML=''}return false;">
													<html:img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif" />
												</html:link>
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
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/pesquisarListaVariaveisMatrizOferta.do?id_matriz_oferta=${idMatrizOferta}&pagina_solicitada=${no_pagina}" >
								${no_pagina}
							</html:link>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div class="barra"></div>
			<div class="botao">
				<label>&nbsp;</label>
				<cata:temPermissao acao="matrizOfertaVariaveisIncluir">
				<c:set value="listaVariaveis" var="cadastroCabecalhoMatrizOfertaForm.listaVariaveis"/>
					<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/cadastrarNovaVariavelCabecalhoMatriz.do?id_matriz_oferta=${idMatrizOferta}" styleId="botao_novo_cabecalho_matriz" onClick="if(abrirLink('div_cadastro_nova_variavel_cabecalho', this.href, 'right_section')){clearAndShow('div_cadastro_nova_variavel_cabecalho');}return false">
						
						<html:button bundle="messages" tabindex="3" property="btn_novo" styleClass="btNavegacao74" value="Novo" altKey="catalogo.matrizOferta.variaveis.Novo" titleKey="catalogo.matrizOferta.variaveis.Novo" />
					
					</html:link>

				</cata:temPermissao>
				<label class="lblForm" >Quantidade : ${totalRegistros}</label>
			</div>
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
</div>
<br />
<div id="div_returno_erro_variavel" style="display: none; position: relative;"></div>
<div id="div_cadastro_nova_variavel_cabecalho" style="display: none; position: relative;"></div>