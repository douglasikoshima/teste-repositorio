<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui:form action="efetivarAssociasaoProdutoModelo" tagId="lista_produtos" target="target_return_tela">
	<div id="resultado_pesquisa_produtos_disp" style="width:100% ;position: relative">
		<netui:checkBoxGroup dataSource="actionForm.idsProdutos" >
		<netui:hidden dataSource="actionForm.idModeloVenda" dataInput="${idModeloVenda}" />
			<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" id="TabelaRelacionamentosRecentes">
				<thead>
					<tr>
						<th class="center"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_produtos', '.checkbox_produto', this.checked);"/></th>
						<th class="sortable">Código</th>
						<th class="sortable">Desc. Produto</th>
						<th class="sortable">Desc. Produto Catalogo</th>
					</tr>
				</thead>
			</table>
			<div class="both-scroll" style="height: 300px; width: 100%;">
				<netui-data:repeater dataSource="produtosSemAssociacao" defaultText="<br><span align='center'>Nenhum Produto encontrado.</span>">
					<netui-data:repeaterHeader>
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
							<thead>
								<tr>
									<th class="center"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox('lista_produtos', '.checkbox_produto', this.checked);"/></th>
									<th class="sortable">Código</th>
									<th class="sortable">Desc. Produto</th>
									<th class="sortable">Desc. Produto Catalogo</th>
								</tr>
							</thead>
							<tbody>
					</netui-data:repeaterHeader>
					<netui-data:repeaterItem>
								<tr>
									<td class="center" style="padding-left: 2px">
										<netui:checkBoxOption value="${container.item.idProduto}" tagId="checkbox_produto_${container.container.item.idProduto}" styleClass="semBorda belongsToForm checkbox_produto" labelStyleClass="hide" />
									</td>
									<td>${container.item.cdCodigo}</td>
									<td>${container.item.dsSap}</td>
									<td>${container.item.dsProduto}</td>
								</tr>
					</netui-data:repeaterItem>
					<netui-data:repeaterFooter>
						</tbody>
					</table>
					</netui-data:repeaterFooter>
				</netui-data:repeater>
			</div>
			
			<br clear="all"><br clear="all">
			
			<div class="paginacao" style="width:99%; height: auto; bottom: 30px;" align="right">
				<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
					<c:choose>
						<c:when test="${no_pagina == paginaAtual}">
							<netui:anchor styleClass="selected" onClick="return false;" href="">
								${no_pagina}
							</netui:anchor>
						</c:when>
						<c:otherwise>
							<netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_produtos_sem_associasao').onclick();return false;" href="#">
								${no_pagina}
							</netui:anchor>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div class="barra"></div>	
			<div class="botao">
				<label class="lblForm">Quantidade : ${totalRegistros}</label> 
				<netui:button type="button" styleClass="btNavegacao74" value="Associar" onClick="if(checkSelecaoProdutosSemAssociacao($('lista_produtos').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){}else{abrirPopup2($(this).next('a'), null, 'resultado_pesquisa_produtos_disp');}return false" alt="${bundle.default['catalogo.alterarModelo.associar.Produto']}" title="${bundle.default['catalogo.alterarModelo.associar.Gravar']}" />
				<netui:anchor action="confirmAssociacaoProdutoModelo" styleClass="hide">
					<netui:parameter name="cd_modelo_venda" value="${cdModeloVenda}" />
					<netui:button type="button" tagId="botao_hide_associar_novos_produtos_modelo" styleClass="hide" value="Associar" onClick="send(this, null, null, 'lista_produtos_associados');fecharPopup('popup1');return false" />
				</netui:anchor>
			</div>
		</netui:checkBoxGroup>
	</div>
</netui:form>
<iframe id='target_return_tela' name='target_return_tela' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_pesquisa_popup');"></iframe>							

