<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="resultado_pesquisa_importacao_matriz" style="position:relative;">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Resultado da Pesquisa</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg">
				<div style="width:100%;position:relative;" id="lista_importacao_matriz">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/processarMatrizOfertaArquivo.do">
						<display:table name="consultaImportacaoMatrizOfertaForm.resultadoImportacaoList" id="resultadoImportacao" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/processarMatrizOfertaArquivo.do" class="tabela-padrao-menor tablesorter table_body">
							<display:column property="nmArquivo" title="Nome do Arquivo" sortable="true" headerClass="sortable"/>
							<display:column title="Data da Importa&ccedil;&atilde;o" sortable="true" headerClass="sortable">
								<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${resultadoImportacao.dtImportacao.time}" />
							</display:column>
							<display:column title="Data do Processamento" sortable="true" headerClass="sortable">
								<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${resultadoImportacao.dtProcessamento.time}" />
							</display:column>
							<display:column property="usuarioResponsavel" title="Usu&aacute;rio" sortable="true" headerClass="sortable"/>
							<display:column property="descStatus" title="Status" sortable="true" headerClass="sortable"/>
							<display:column property="dsmensagemadvertencia" title="Advert&ecirc;ncia" sortable="true" headerClass="sortable"/>
							<display:column title="Detalhe" sortable="false">
								<c:choose>
									<c:when test="${descStatusMap[resultadoImportacao.descStatus] == 4}">
										<div class="ico icoGrid" style="text-align: center !important;">
											<html:link styleId="icone_abrir_popup_criticas" action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/openPopupDetalhesArquivoItem.do?idMatrizOfertaArquivoIcon=${resultadoImportacao.idMatrizOfertaArquivo}" onClick="abrirPopup1(this.href, null, null);return false;" title="Detalhe">
											<div></div>
											</html:link>											
										</div>
									</c:when>
									<c:when test="${descStatusMap[resultadoImportacao.descStatus] == 6 or descStatusMap[resultadoImportacao.descStatus] == 5}">
										<div style="text-align: center !important;">
											<cata:temPermissao acao="exportarMatrizOfertasItens">
												<html:link styleId="icone_abrir_popup_detalhes_erro_processamento" action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/openPopupDetalhesErroProcessamento.do?descErroProcessamentoArquivo=${resultadoImportacao.dscErro}" onClick="abrirPopup1(this.href, null, null);return false;" title="Detalhe">
												<img alt="Detalhes" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>
												</html:link>
											</cata:temPermissao>
										</div>											
									</c:when>
									<c:otherwise>
										<div style="text-align: center !important;">&nbsp;</div>
									</c:otherwise>
								</c:choose>
							</display:column>
							<cata:temPermissao acao="processarMatrizOfertaImportacao">
								<display:column title="Processar" sortable="false">
									<c:choose>
										<c:when test="${descStatusMap[resultadoImportacao.descStatus] == 1}">
											<div style="text-align: center !important;">
												<a href="#">
													<img alt="Processar" src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif" onclick="$('btnProcessarArquivo').onclick();"/>
												</a>
												<html:hidden property="idMatrizOfertaAquivo" styleId="idMatrizOfertaAquivo" value="${resultadoImportacao.idMatrizOfertaArquivo}"/>
												<input id="btnProcessarArquivo" type="hidden" onclick="send(this);"/>
											</div>
										</c:when>
										<c:otherwise>
											<div style="text-align: center !important;">&nbsp;</td>
										</c:otherwise>
									</c:choose>							
								</display:column>
								</cata:temPermissao>
						</display:table>
					</html:form>
					<div class="paginacao" style="width:99%;" align="right">
						<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">
									<html:link styleClass="selected" onClick="return false;" href="">${no_pagina}</html:link>
								</c:when>
								<c:otherwise>
									<html:link onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_listar_arquivos_importados').onclick();return false;" href="#">${no_pagina}</html:link>
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
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
</div>
<iframe id='target_download2' name='target_download2' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_pesquisa_importacao_matriz');"></iframe>
<div id="lista_variaveis" style="position:relative;"></div>	
