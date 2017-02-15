<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupWidthFull.jsp">
	<netui-temp:section name="conteudo">
	<div id="popup_nova_associacao_produtos" style="width: 100%;">
		<input id="larguraPopup" value="810px" class="hide"/>
		<input id="alturaPopup" value="auto" class="hide"/>
		<div id="div_erro_popup_produtos"></div>
		<c:set var="nomeBox" scope="application"  value="Associar novos Produtos ao Modelo: ${nmModeloVenda}"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		
		<br clear="all"/><br clear="all"/>
		<div id="div_form" style="width: 100%; float: left;">
			<netui:form action="pesquisarTipoProdutoSemAssociasao">
				<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
				<netui:hidden dataSource="actionForm.idModeloVenda" dataInput="${idModeloVenda}" />
				<netui:hidden dataSource="actionForm.cdModeloVenda" dataInput="${cdModeloVenda}" />
				<div id="conteudo_form" style="float: left" >
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">Tipo de Produto:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<c:choose>
							<c:when test="${not empty idTipoProduto}">
								<netui:select tabindex="1" disabled="false" styleClass="required" tagId="select_tipo_produto" style="width:140px;" dataSource="actionForm.idTipoProduto">
									<netui:selectOption value="${idTipoProduto}">
		                       		${nmTipoProduto}
			                   	 </netui:selectOption>
								</netui:select>
							</c:when>
							<c:otherwise>
								<netui:select tabindex="1" styleClass="required" tagId="select_tipo_produto" style="width:140px;" repeater="true" dataSource="actionForm.idTipoProduto" defaultValue="-- Selecione --" optionsDataSource="${tipoProdutos}"
									repeatingOrder="default, option" onChange="listarFabricantesParaAssociacao($(this).next('a').href, $F(this));return false;">
									<c:if test="${container.metadata.defaultStage}">
										<netui:selectOption repeatingType="default" value="">${container.item}</netui:selectOption>
									</c:if>
									<c:if test="${container.metadata.optionStage}">
										<netui:selectOption repeatingType="option" value="${container.item.idTipoProduto}">
			                        		${container.item.nmTipoProduto}
					                    </netui:selectOption>
			                    	</c:if>
								</netui:select>
								<netui:anchor action="buscarFabricantes" styleClass="display:none;"/>
							</c:otherwise>
						</c:choose>
						</div>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:120px;">Fabricante:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<c:choose>
							<c:when test="${not empty idFabricante}">
								<netui:select tabindex="1" disabled="false" styleClass="required" tagId="select_fabricante" style="width:140px;" dataSource="actionForm.idFabricante">
									<netui:selectOption value="${idFabricante}">
			                       		${nmFabricante}
				                    </netui:selectOption>
								</netui:select>
							</c:when>
							<c:otherwise>
								<netui:select tabindex="2" styleClass="required" tagId="select_fabricante" style="width:140px;" dataSource="actionForm.idFabricante" defaultValue="${idFabricante}" onChange="jsonListarModelos($(this).next('a').href, $F('select_tipo_produto'), $F(this));return false;">
									<netui:selectOption  value="">-- Selecione --</netui:selectOption>
								</netui:select>
								<netui:anchor action="buscarModelos" styleClass="display:none;"/>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:100px;">Tecnologia:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<c:choose>
							<c:when test="${not empty tecnologia}">
								<netui:select tabindex="3" styleClass="required" tagId="select_tecnologia" style="width:140px;" repeater="true" dataSource="actionForm.idTecnologia" defaultValue="${container.item.nmTecnologia}" optionsDataSource="${tecnologia}"
									repeatingOrder="default, option" >
									<c:if test="${container.metadata.defaultStage}">
									<netui:selectOption repeatingType="default" value="">
										${container.item}
									</netui:selectOption>
									</c:if>
									<c:if test="${container.metadata.optionStage}">
									<netui:selectOption repeatingType="option" value="${container.item.idTecnologiaPai}">
				                        ${container.item.nmTecnologia}
				                    </netui:selectOption>
				                    </c:if>
								</netui:select>
							</c:when>
							<c:otherwise>
								<netui:select tabindex="3" styleClass="required" tagId="select_tecnologia" style="width:140px;" repeater="true" dataSource="actionForm.idTecnologia" defaultValue="-- Selecione --" optionsDataSource="${tecnologiaLista}"
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
							</c:otherwise>
						</c:choose>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<c:choose>
							<c:when test="${not empty nmGrupoProduto}">
								<div class="label-form-bold label_required" style="width:110px;">Modelo:</div>
								<netui:select tabindex="1" disabled="false" styleClass="required" tagId="select_modelo" style="width:140px;" dataSource="actionForm.idModeloProduto" repeatingOrder="default, option">
									<netui:selectOption value="${idGrupoProduto}">
			                       		${nmGrupoProduto}
				                    </netui:selectOption>
								</netui:select>
							</c:when>
							<c:otherwise>
								<div class="label-form-bold" style="width:110px;">Modelo:</div>
								<netui:select tabindex="4" tagId="select_modelo" style="width:140px;" dataSource="actionForm.idModeloProduto" defaultValue="${idGrupoProduto}">
									<netui:selectOption tagId="select_modelo"  value="">-- Selecione --</netui:selectOption>
								</netui:select>
							</c:otherwise>
						</c:choose>
						
					</div>
					
					<div class="fleft">
						<div class="label-form-bold" style="width:120px;">C&oacute;digo do Produto:</div>
						<netui:textBox tabindex="5" size="25" dataSource="actionForm.codigoProduto"/>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>

					<div class="botao">
						<input type="button" tabindex="8" onClick="$('resultado_pesquisa_popup').hide();limparForm(this);return false;" value="Limpar" class="btNavegacao74" alt="${bundle.default['catalogo.global.Limpar']}" title="${bundle.default['catalogo.global.Limpar']}"/>
						<span>&nbsp;</span>
						<netui:button tabindex="7" tagId="botao_pesquisar_produtos_sem_associasao" onMouseDown="$('pagina_solicitada').value='';return true;" type="button" onClick="clearAndShow('resultado_pesquisa_popup');send(this, 'resultado_pesquisa_popup', null , 'div_erro_popup_produtos', null);" styleClass="btNavegacao74" value="Pesquisar" alt="${bundle.default['catalogo.paramProdutos.Pesquisar']}" title="${bundle.default['catalogo.paramProdutos.Pesquisar']}" />
					</div>
				</div>
			</netui:form>
			<br clear="all">
			<div class="barra"></div>
			<br clear="all">
			<div style="display: block; width: 100%; height: 300px;">
				<div id="resultado_pesquisa_popup" style="display: none;"></div>
			</div>
		<jsp:include page="/templates/box_pos.jsp"/>
	</div>
	</netui-temp:section>
</netui-temp:template>