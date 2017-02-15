<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<catalogo:contentBox title="Descrição do Produto do Catálogo">
	<netui:form action="alterarNomeProduto" tagId="cadastroProdutoForm">
		<netui:hidden dataSource="actionForm.idProduto" dataInput="${id_produto}"/>
		<netui:hidden dataSource="actionForm.indModelo" dataInput="${indicador_modelo}"/>
		<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;">
			<div class="label-form-bold label_required" style="text-align: right;"><label for="codigoSAPCadastro">Código do Produto SAP:</label></div>
			<netui:textBox dataSource="actionForm.codigoSAPCadastro" defaultValue="${codigo_sap}" tagId="codigoSAPCadastro" maxlength="255" size="70" disabled="true" />
		</div>
		<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;">
			<div class="label-form-bold label_required" style="text-align: right;"><label for="nomeComercialCadastro">Nome Comercial:</label></div>
			<netui:textBox dataSource="actionForm.nomeComercialCadastro" defaultValue="${nome_comercial}" tagId="nomeComercialCadastro" maxlength="255" size="70" />
		</div>
		<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;">
			<div class="label-form-bold label_required" style="text-align: right;"><label for="descricaoSAPCadastro">Descrição do Produto:</label></div>
			<netui:textBox dataSource="actionForm.descricaoSAPCadastro" defaultValue="${descricao_sap}" tagId="descricaoSAPCadastro" maxlength="255" size="70" disabled="true" />
		</div>
		<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;">
			<div class="label-form-bold label_required" style="text-align: right;"><label for="composicaoCadastro">Composição do Produto:</label></div>
			<netui:textBox dataSource="actionForm.composicaoCadastro" defaultValue="${composicao}" tagId="composicaoCadastro" maxlength="255" size="70" disabled="true" />
		</div>
		<c:if test="${indicador_modelo eq 'S'}">
			<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;"> 
				<div class="label-form-bold label_required" style="text-align: right;"><label for="select_tipo_produto">Tipo de Produto: </label></div>
				<netui:select tabindex="1" tagId="select_tipo_produto" style="width: 200px;" repeater="true" dataSource="actionForm.idTipoProdutoCadastro" defaultValue="${id_tipo_produto}" optionsDataSource="${tipoProdutoLista}" repeatingOrder="default, option" disabled="true" >
					<c:if test="${container.metadata.defaultStage}">
						<netui:selectOption repeatingType="default" value="">-- Selecione --</netui:selectOption>
					</c:if>
					<c:if test="${container.metadata.optionStage}">
						<netui:selectOption repeatingType="option" value="${container.item.idTipoProduto}">
		                      ${container.item.nmTipoProduto}
						</netui:selectOption>
	                </c:if>
				</netui:select>
			</div>
		</c:if>
		<c:if test="${indicador_modelo eq 'N'}">
			<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;"> 
				<div class="label-form-bold label_required" style="text-align: right;"><label for="select_tipo_produto">Tipo de Produto: </label></div>
				<netui:select tabindex="1" tagId="select_tipo_produto" style="width: 200px;" repeater="true" dataSource="actionForm.idTipoProdutoCadastro" defaultValue="${id_tipo_produto}" optionsDataSource="${tipoProdutoLista}" repeatingOrder="default, option">
					<c:if test="${container.metadata.defaultStage}">
						<netui:selectOption repeatingType="default" value="">-- Selecione --</netui:selectOption>
					</c:if>
					<c:if test="${container.metadata.optionStage}">
						<netui:selectOption repeatingType="option" value="${container.item.idTipoProduto}">
		                      ${container.item.nmTipoProduto}
						</netui:selectOption>
	                </c:if>
				</netui:select>
			</div>
		</c:if>
		<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;">
			<div class="label-form-bold label_required" style="text-align: right;"><label for="destaqueProdutoCadastro">Destaque do Produto:</label></div>
			<netui:textArea dataSource="actionForm.destaqueProdutoCadastro" defaultValue="${destaque_produto}" tagId="destaqueProdutoCadastro" cols="50" rows="5" />
		</div>
		<c:if test="${indicador_modelo eq 'S'}">
			<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;"> 
				<div class="label-form-bold label_required" style="text-align: right;"><label for="idCorCadastro">Cor do Produto: </label></div>
				<netui:select tabindex="1" tagId="idCorCadastro" style="width: 200px;" repeater="true" dataSource="actionForm.idCorCadastro" defaultValue="${id_cor}" optionsDataSource="${listaCores}" repeatingOrder="default, option">
					<c:if test="${container.metadata.defaultStage}">
						<netui:selectOption repeatingType="default" value="">-- Selecione --</netui:selectOption>
					</c:if>
					<c:if test="${container.metadata.optionStage}">
						<netui:selectOption repeatingType="option" value="${container.item.idCor}">
		                      ${container.item.nmCor}
						</netui:selectOption>
	                </c:if>
				</netui:select>
			</div>
		</c:if>
		<c:if test="${indicador_modelo eq 'N'}">
			<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;"> 
				<div class="label-form-bold label_required" style="text-align: right;"><label for="idCorCadastro">Cor do Produto: </label></div>
				<netui:select tabindex="1" tagId="idCorCadastro" style="width: 200px;" repeater="true" dataSource="actionForm.idCorCadastro" defaultValue="${id_cor}" optionsDataSource="${listaCores}" repeatingOrder="default" disabled="true">
					<c:if test="${container.metadata.defaultStage}">
						<netui:selectOption repeatingType="default" value="">-- Selecione --</netui:selectOption>
					</c:if>
				</netui:select>
			</div>
		</c:if>	
		<br clear="all"/><br clear="all"/>
		<div class="barra"></div>
		<div class="botao">
			<netui:button tagId="botao_pesquisar_form" type="button" onClick="clearAndShow('contentForm')" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title="Cancelar"/><span>&nbsp;</span>
			<cata:temPermissao acao="alterarProduto">
				<netui:button tagId="botao_pesquisar_form" type="button" onClick="if(send(this, null, null, 'right_section')){clearAndShow('contentForm')} return false;" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title="Gravar"/>
			</cata:temPermissao>
		</div>
	</netui:form>
</catalogo:contentBox>