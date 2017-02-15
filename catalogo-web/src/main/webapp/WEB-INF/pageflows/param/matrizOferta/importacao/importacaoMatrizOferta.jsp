<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<div id="div_erros_popup"></div>
<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Importa&ccedil;&atilde;o Matriz Oferta:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
	<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<html:link property="reload_popup_importar_arquivo" styleId="reload_popup_importar_arquivo" action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/importacao/popupSucessoUpload.do" onClick="abrirPopup1(this.href, null, 'div_erros_popup'); return false;" style="display:none;"/>
			<iframe id='target_upload' name='target_upload' src='' style='display: none;' onload="retornoUploadArquivo(this, 'div_erros_popup')"></iframe>
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/importacao/importarArquivoCsvMatrizOferta.do" enctype="multipart/form-data" target="target_upload">
				<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
				<html:hidden property="isValorSimCard" styleId="hidden_confirm_valor"/>
				<html:errors name="tipoProduto"/>
			           <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			            <div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 120px">Arquivo:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:file property="nmArquivo" styleId="file_nmArquivo" tabindex="1" size="80" styleClass="required extArquivo"/>
						</div>
						
						<br clear="all"/><br clear="all"/>
						<div class="fleft"> 
							<div class="label-form-bold label_required" style="width: 120px">Canal Atendimento:<font size="1px" color="#EEB422" valign="center">*</font></div>
                            <html:select property="idCanalAtendimento" styleId="idCanalAtendimento" style="width: 150px;" disabled="false" styleClass="required">
                                <html:option value="">-- Selecione --</html:option>
                                <c:forEach var="canalAtendimentoTO" items="${canalAtendimentoList}">
                                    <html:option value="${canalAtendimentoTO.idCanalAtendimento}">${canalAtendimentoTO.nmCanal}</html:option>
                                </c:forEach>
                            </html:select>		                    
						</div>
						<br clear="all"/><br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
						    <span>&nbsp;</span>
						    <html:submit property="botao_importar_arquivos_matriz_oferta" styleId="botao_importar_arquivos_matriz_oferta" tabindex="5" onClick="return validarFormImportarAquivoCsv(this, 'div_erros_popup', 'right_section', 'file_nmArquivo')" value="Importar" styleClass="btNavegacao74" bundle="messages" altKey="catalogo.parametrizacao.matrizOferta.Importar" titleKey="catalogo.parametrizacao.matrizOferta.Importar"/>
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
