<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="div_erros_popup"></div>
<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Importa&ccedil;&atilde;o Tipos de Matriz e Contratos:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
	<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<netui:anchor style="display:none;" tagId="reload_popup_importar_tpmatriz_contrato" action="popupSucessoUpload" onClick="abrirPopup1(this.href, null, 'div_erros_popup'); return false;" ></netui:anchor>
			<iframe id='target_upload' name='target_upload' src='' style='display: none;' onload="retornoUploadArquivo(this, 'div_erros_popup')"></iframe>
			<netui:form action="importarTiposMatrizAndContratos" enctype="multipart/form-data" target="target_upload">
				<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
				<netui:error key="tipoProduto"/>
			           <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			            <div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 120px">Arquivo:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<netui:fileUpload styleClass="required extArquivo" tagId="file_nmArquivo" dataSource="actionForm.nmArquivo" tabindex="1" size="80" />
						</div>

						<br clear="all"/><br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
						    <input type="reset" id="botao_limpar_form_importar_tpmatriz_contrato" tabindex="3" onClick="limparForm(this);$('link_parametrizacao_matrizOferta_importar_tipo_matriz_contrato').onclick();return false;" class="btNavegacao74" value="Limpar" alt="${bundle.default['catalogo.global.Limpar']}" title="${bundle.default['catalogo.global.Limpar']}" />
						    <span>&nbsp;</span>
						    <netui:button tabindex="2" tagId="botao_importar_tipos_matriz_contratos" onClick="return validarFormImportarAquivoCsv(this, 'div_erros_popup', 'right_section', 'file_nmArquivo')" value="Importar" styleClass="btNavegacao74" alt="${bundle.default['catalogo.parametrizacao.matrizOferta.Importar']}" title="${bundle.default['catalogo.parametrizacao.matrizOferta.Importar']}" />
						</div>
					</netui:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br/>
<div id="resultado_pesquisa" style="position: relative;"></div>
