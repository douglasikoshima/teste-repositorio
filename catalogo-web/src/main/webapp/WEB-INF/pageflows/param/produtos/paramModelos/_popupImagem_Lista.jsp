<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="fleft vertical-scroll" style="height:250px;width:550px">
		<netui-data:repeater dataSource="multimidias">
	
			<netui-data:repeaterHeader>
				<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
					<thead>
						<tr>
							<th>Nome do Arquivo</th>
							<th>Excluir</th>
							<th>Classifica&ccedil;&atilde;o</th>
							<th>Cor</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
			</netui-data:repeaterHeader>
	
			<netui-data:repeaterItem>
				<netui:form action="classificarMultimidia">
					<netui:hidden dataSource="actionForm.dsNotaModelo" dataInput="${pageInput.modeloCorPadrao.dsNota}"/>
					<netui:hidden dataSource="actionForm.idCorPadrao" dataInput="${pageInput.modeloCorPadrao.idCorPadrao}"/>
					<tr>
						<td>
							<netui:hidden dataSource="actionForm.idMultimidia" dataInput="${container.item.idMultimidia}"/>
							<input type="hidden" id="img_modelo_${container.item.idMultimidia}" value="${container.item.nmMultimidia}"/>
							<input type="text" size="30" value="${container.item.nmMultimidia}" readonly="readonly"
								onclick="if($('img_modelo_${container.item.idMultimidia}').value.slice($('img_modelo_${container.item.idMultimidia}').value.lastIndexOf('.')).toUpperCase() == '.SWF') { $('imagem_modelo').hide();$('paramMovie').value='/${caminho_link_imagens_modelo}/'+$F('img_modelo_${container.item.idMultimidia}');$('movieSwf').show(); } else { $('movieSwf').hide();$('imagem_modelo').src='/${caminho_link_imagens_modelo}/'+$F('img_modelo_${container.item.idMultimidia}');$('imagem_modelo').show(); }"/>
						</td>
						<td class="center">
							<netui:hidden dataSource="actionForm.idModelo" dataInput="${id_modelo}"/>
							<netui:anchor action="popupApagarImagem" onClick="abrirPopup2(this.href, null, 'div_erros_popup');return false;">
								<netui:parameter name="id_modelo" value="${id_modelo}"/>
								<netui:parameter name="id_multimidia" value="${container.item.idMultimidia}"/>
								<netui:parameter name="idCorPadrao" value="${pageInput.modeloCorPadrao.idCorPadrao}"/>
								<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Delete24.gif"/>
							</netui:anchor>
						</td>
						<td>
							<div class="center">
								<netui:select tabindex="4" tagId="select_classificacao_${container.item.idMultimidia}" style="width:140px;" dataSource="actionForm.idClassificacao"
									defaultValue="${container.item.idClassificacao}" optionsDataSource="${pageInput.classificacaoLista}" repeater="true" repeatingOrder="default, option"
									onChange="if ( $('select_classificacao_${container.item.idMultimidia}').value != '' ) {$('btn_multimidia_${container.item.idMultimidia}').onclick();} else { posicionarDivErros('div_erros_popup'); clearErrors(); addError('Não é possível realizar a desassociação da Classificação da Multimídia', true, true); }"
									disabled="${container.item.sgTipoMultimidia eq 'VDO' ? 'true' : 'false'}" >
									<c:if test="${container.metadata.optionStage}">
										<netui:selectOption repeatingType="option" value="${container.item.idClassificacao}">
											${container.item.nmClassificacao}
										</netui:selectOption>
									</c:if>
									<c:if test="${container.metadata.defaultStage}">
										<netui:selectOption repeatingType="default" value="">
											-- Selecione --
										</netui:selectOption>
									</c:if>
								</netui:select>
							</div>
						</td>
						<td>
							<div class="center">
								<netui:select tabindex="4" tagId="select_cor_${container.item.idMultimidia}" style="width:140px;" dataSource="actionForm.idCor"
									defaultValue="${container.item.idCor}" optionsDataSource="${pageInput.corLista}" repeater="true" repeatingOrder="default, option"
									onChange="if ( $('select_cor_${container.item.idMultimidia}').value != '' ) {$('btn_multimidia_${container.item.idMultimidia}').onclick();} else { posicionarDivErros('div_erros_popup'); clearErrors(); addError('Não é possível realizar a desassociação da Cor da Multimídia', true, true); }"
									disabled="${container.item.sgTipoMultimidia eq 'VDO' ? 'true' : 'false'}" >
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
						</td>
						<td>
							<netui:button tabindex="13" tagId="btn_multimidia_${container.item.idMultimidia}" type="button" onClick="send(this, null, null, 'div_erros_popup');" styleClass="hide"/>
						</td>
					</tr>
				</netui:form>
			</netui-data:repeaterItem>
			<netui-data:repeaterFooter>
					</tbody>
				</table>
			</netui-data:repeaterFooter>
		</netui-data:repeater>
	</div>
<div class="fleft" style="width: 230px;" align="center">
	<img id="imagem_modelo" height="200px" width="210px" style="display:none;" src="">
	
	<object id="movieSwf" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="210px" height="200px" 
		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,16,0" style="display: none;">
		
		<param id="paramMovie" name="movie" value="">
	
	</object>
	
</div>
<div class="fleft" style="width:520px;" align="center">
</div>
<br clear="all"/>
