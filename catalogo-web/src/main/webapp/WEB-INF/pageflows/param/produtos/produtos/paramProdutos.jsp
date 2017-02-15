<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<div class="secao_conteudo">
	<catalogo:contentBox title="Produtos:" doubt="true" requiredFields="true">
		<netui:form action="pesquisarProdutos">
			<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
			<netui:error key="tipoProduto"/>
			<div class="fleft">
				<div class="label-form-bold label_required" style="width:110px;">Tipo de Produto:<font size="1px" color="#EEB422" valign="center">*</font></div>
				<netui:select tabindex="1" styleClass="required" tagId="select_tipo_produto" style="width:140px;" repeater="true" dataSource="actionForm.tipoProduto" defaultValue="-- Selecione --" optionsDataSource="${tipos_produto}"
					repeatingOrder="default, option" onChange="jsonListarFabricantes($(this).next('a').href, $F(this));return false;">
					<c:if test="${container.metadata.defaultStage}">
					<netui:selectOption repeatingType="default" value="">
						${container.item}
					</netui:selectOption>
					</c:if>
					<c:if test="${container.metadata.optionStage}">
					<netui:selectOption repeatingType="option" value="${container.item.idTipoProduto}">
                        ${container.item.nmTipoProduto}
                    </netui:selectOption>
                    </c:if>
				</netui:select>
				<netui:anchor action="buscarFabricantes" styleClass="display:none;"/>
			</div>
			<div class="fleft">
				<div class="label-form-bold label_required" style="width:110px;">Fabricante:<font size="1px" color="#EEB422" valign="center">*</font></div>
				<netui:select tabindex="2" styleClass="required" tagId="select_fabricante" style="width:140px;"
						dataSource="actionForm.fabricante" onChange="jsonListarModelos($(this).next('a').href, $F('select_tipo_produto'), $F(this));return false;">
					<netui:selectOption  value="">-- Selecione --</netui:selectOption>
				</netui:select>
				<netui:anchor action="buscarModelos" styleClass="display:none;"/>
			</div>
			<div class="fleft">
				<div class="label-form-bold label_required" style="width:110px;">Tecnologia:<font size="1px" color="#EEB422" valign="center">*</font></div>
				<netui:select tabindex="3" styleClass="required" tagId="select_tecnologia" style="width:140px;" repeater="true" dataSource="actionForm.tecnologia" defaultValue="-- Selecione --" optionsDataSource="${tecnologias}"
					repeatingOrder="default, option" >
					<c:if test="${container.metadata.defaultStage}">
					<netui:selectOption repeatingType="default" value="">
						${container.item}
					</netui:selectOption>
					</c:if>
					<c:if test="${container.metadata.optionStage}">
					<netui:selectOption repeatingType="option" value="${container.item.idTecnologia}">
                        ${container.item.nmTecnologia}
                    </netui:selectOption>
                    </c:if>
				</netui:select>
			</div>
			<br clear="all"/><br clear="all"/>
			<div class="fleft">
				<div class="label-form-bold" style="width:110px;">Modelo:</div>
				<netui:select tabindex="4" tagId="select_modelo" style="width:140px;"
						dataSource="actionForm.idModelo" disabled="true">
					<netui:selectOption tagId="select_modelo"  value="">-- Selecione --</netui:selectOption>
				</netui:select>
			</div>
			<div class="fleft">
				<div class="label-form-bold" style="width:110px;">Código do Produto:</div>
				<netui:textBox tabindex="5" size="25" dataSource="actionForm.codigoProduto"/>
				<netui:error key="codigoProduto"/>
				<netui:error key="CodigoProduto"/>
				<netui:error key="actionForm.codigoProduto"/>
				<netui:error key="actionForm.CodigoProduto"/>
			</div>
			<div class="fleft">
				<div class="label-form-bold" style="width:110px;">Código associado:</div>
				<netui:radioButtonGroup  dataSource="actionForm.codigoAssociado" defaultValue="nao">
					<netui:radioButtonOption tabindex="6" styleClass="semBorda" tagId="codigo_associado_sim" value="sim" labelStyle="display:none;"
					onClick="$('select_modelo').enable();" /><label class="label-form-bold2" for="codigo_associado_sim">Sim</label>
					<netui:radioButtonOption tabindex="7" styleClass="semBorda" tagId="codigo_associado_nao" value="nao" labelStyle="display:none;"
					onClick="$('select_modelo').selectedIndex=0;$('select_modelo').disable();"/><label class="label-form-bold2" for="codigo_associado_nao">Não</label>
				</netui:radioButtonGroup>
			</div>
			<br clear="all"/>
			<br clear="all"/>
			<div class="barra"></div>
			<div class="botao">
				<input type="button" tabindex="8" onClick="$('resultado_pesquisa').hide();$('contentForm').hide();limparForm(this);return false;" value="Limpar" class="btNavegacao74" title="${bundle.default['catalogo.global.Limpar']}"/>
				<span>&nbsp;</span>
				<netui:button tabindex="7" tagId="botao_pesquisar_produtos" onMouseDown="$('pagina_solicitada').value='';return true;" type="button" onClick="$('contentForm').hide();clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null , 'right_section');" styleClass="btNavegacao74" value="Pesquisar" title="${bundle.default['catalogo.paramProdutos.Pesquisar']}"/>
			</div>
		</netui:form>
	</catalogo:contentBox>			
</div>
<br/>
<div id="resultado_pesquisa" style="position:relative;"></div>
<div id="contentForm" style="position:relative; float: left;"></div>