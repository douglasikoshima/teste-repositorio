<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Consulta Importa&ccedil;&atilde;o Matriz Oferta:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
	<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/pesquisarImportacaoMatrizOferta.do">
				<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
				<html:errors name="tipoProduto"/>
			           <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			            <div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						
						<div class="fleft">
						<div class="label-form-bold" style="width: 120px">Status do Arquivo:</div>
                            <html:select property="idStatusArquivoImportacao" styleId="select_status_arquivo" style="width: 234px;" tabindex="1">
                                <html:option value="">Todos</html:option>
                                <c:forEach var="listaStatusImportacaoTO" items="${listaStatusImportacao}">
                                    <html:option value="${listaStatusImportacaoTO.idStatusImportacao}">${listaStatusImportacaoTO.dscStatusImportacao}</html:option>
                                </c:forEach>
                            </html:select>							
														
						</div>	
						
						<div class="fleft">
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo Nome do arquivo.</div>
							<div class="hide min_size_required_value">3</div>
							<div class="label-form-bold" style="width: 140px">Nome do arquivo:</div>
							<html:text property="nmArquivo" styleId="nmArquivo" styleClass="min_size_required" maxlength="50" tabindex="2" size="50"/>
						</div>							
						
						<br clear="all"/><br clear="all"/>
						<div class="fleft">
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo Importado por</div>
							<div class="hide min_size_required_value">3</div>
							<div class="label-form-bold" style="width: 120px">Importado por:</div>
							<html:text property="userImportacao" styleId="userImportacao" styleClass="min_size_required" maxlength="50" tabindex="3" size="44"/>
						</div>	
						
						<div class="fleft">
							<div class="label-form-bold" style="width: 140px">Per&iacute;odo de:</div>
							<div class="data_nome_campo hide">Per&iacute;odo de</div>
							<html:text property="periodoInicio" styleId="periodoInicio" size="20" maxlength="10" tabindex="4" styleClass="data" onKeyPress="return isFormatDate(event, this, '/')"/>
						</div>
						<div class="fleft">
							<div class="label-form-bold" style="width: 33px">at&eacute;:</div>
							<div class="data_nome_campo hide">Per&iacute;odo at&eacute;</div>
							<html:text property="periodoFim" styleId="periodoFim" size="20" maxlength="10" tabindex="5" styleClass="data" onKeyPress="return isFormatDate(event, this, '/')"/>
						</div>				
						
						<br clear="all"/><br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
							<html:button property="botao_limpar" styleId="botao_limpar" onClick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" tabindex="7" styleClass="btNavegacao74" value="Limpar" title="Limpar Filtros e Resultados"/>
						    <span>&nbsp;</span>
						    <html:button property="botao_listar_arquivos_importados" styleId="botao_listar_arquivos_importados" onMouseDown="$('pagina_solicitada').value='1';" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section');" tabindex="6" styleClass="btNavegacao74" value="Pesquisar" title="Listar importações de matriz oferta"/>
						</div>
					</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br/>
<div id="resultado_pesquisa" style="position: relative;"></div>