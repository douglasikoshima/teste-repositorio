<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="ajustesSizePopup1(null, '900px', '50px');"/>
<img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="if( ${flagNewModelo} ){ pauseReCall2('carregarAlterarModelo_${id_modelo}', 2000); }"/>
<netui-temp:template templatePage="/templates/popupPadrao.jsp">
	<netui-temp:section name="conteudo">
		<netui:anchor style="display:none;" tagId="reload_popup_imagens_modelo" action="popupImagemModelo" onClick="abrirPopup1(this.href, null, 'div_erros_popup');$('inMultimidia').value='S';return false;">
			<netui:parameter name="id_modelo" value="${id_modelo}"/>
			hidden
		</netui:anchor>
		<c:set var="nomeBox" scope="application"  value="CADASTRO DE IMAGEM E VÍDEO:"/>
		<iframe id='target_upload' name='target_upload' src='' style='display:none;' onload="retornoUploadImagem(this, 'div_erros_popup')"></iframe>
        <jsp:include page="/templates/box_pre.jsp"/>
        	<netui:form action="uploadImagem" enctype="multipart/form-data" target="target_upload">
        		<netui:hidden dataSource="actionForm.idModelo" dataInput="${id_modelo}"/>
        		<netui:fileUpload dataSource="actionForm.imagem" size="50" />
        		<netui:button onClick="return checkMaxImagemModelo(${totalMultimidias});" value="Adicionar" styleClass="btNavegacao120" title="${bundle.default['catalogo.popupImagemModelo.Adicionar']}"/>
        	</netui:form>
        	<br>
        	<br>
        	<div id="listaImagens">
	        	<jsp:include page="_popupImagem_Lista.jsp"/>
	        </div>
	        
	        <netui:form action="classificarCorPadrao">
	        	<netui:hidden dataSource="actionForm.idModeloCor" dataInput="${id_modelo}"/>
	        	<netui:hidden dataSource="actionForm.dsNotaModeloCor" dataInput="${pageInput.modeloCorPadrao.dsNota}"/>
				<div class="fleft">
					<div class="label-form-bold" style="width:165px;">Identificador de Cor Padr&atilde;o:&nbsp;</div>
					<netui:select tabindex="20" tagId="select_cor_padrao" style="width:140px;" dataSource="actionForm.idCorPadrao"
						defaultValue="${pageInput.modeloCorPadrao.idCorPadrao == null ? '' : pageInput.modeloCorPadrao.idCorPadrao}" optionsDataSource="${corPadraoLista}" repeater="true" repeatingOrder="default, option"
						onChange="if ( $('select_cor_padrao').value != '' ) {$('btn_corPadrao').onclick();} else { posicionarDivErros('div_erros_popup'); clearErrors(); addError('Não é possível realizar a desassociação da Cor Padrão do Modelo', true, true); }">
						<c:if test="${container.metadata.optionStage}">
							<netui:selectOption repeatingType="option" value="${container.item.idCor}">
								${container.item.nmCor}
							</netui:selectOption>
						</c:if>
						<c:if test="${container.metadata.defaultStage}">
							<netui:selectOption repeatingType="default" value="">
								-- Selecione --
							</netui:selectOption>
						</c:if>
					</netui:select>
				</div>
				<netui:button tabindex="21" tagId="btn_corPadrao" type="button" onClick="send(this, null, null, 'div_erros_popup');" styleClass="hide"/>
			</netui:form>

        <jsp:include page="/templates/box_pos.jsp"/>
	</netui-temp:section>
</netui-temp:template>