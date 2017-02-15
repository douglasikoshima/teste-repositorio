<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div id="resultado_busca_produtos_associados">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Produtos Associados</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg" style="position:relative;">
					
					<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" >
						<thead>
							<tr>
								<th class="sortable">C&oacute;digo</th>
								<th class="sortable">Descri&ccedil;&atilde;o</th>
								<th class="sortable">Modelo do Produto</th>
								<th class="center">Excluir Associa&ccedil;&atilde;o</th>
							</tr>
						</thead>
					</table>
					
					<div style="height: 300px;" class="both-scroll">
						<netui-data:repeater dataSource="produtosAssociados" defaultText="<br><span align='center'>Nunhum Produto Associado.</span>">
								<netui-data:repeaterHeader>
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
										<thead>
										<tr>
											<th class="sortable">C&oacute;digo</th>
											<th class="sortable">Descri&ccedil;&atilde;o</th>
											<th class="sortable">Modelo do Produto</th>
											<th class="center">Excluir Associa&ccedil;&atilde;o</th>
										</tr>
									</thead>
									<tbody>
										</netui-data:repeaterHeader>
										<netui-data:repeaterItem>
										<tr>
											<td style="text-align: left;">${container.item.cdCodigo}</td>
											<td style="text-align: left;">${container.item.dsProduto}</td>
											<td style="text-align: left;">${container.item.nmGrupoProduto}</td>
											<td class="center">
												<netui:anchor action="abrirPopupConfimRemocaoAssociacao" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa')){$('novo_modelo_vendas').innerHTML=''}return false;">
													<netui:parameter  name="id_produto_associado" value="${container.item.idGrupoProduto}" />
									 				<netui:parameter name="ds_produto" value="${container.item.dsProduto}" />
									 				<netui:parameter name="cd_modelo_venda" value="${nmModeloVenda}" />
													<img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif"/>
												</netui:anchor>
											</td>
										</tr>
										</netui-data:repeaterItem>
									<netui-data:repeaterFooter>
									</tbody>
								</table>
									</netui-data:repeaterFooter>
						</netui-data:repeater>
						</div>
						<br>
						<div class="paginacao" style="width:99%;" align="right">
							<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
								<c:choose>
									<c:when test="${no_pagina == paginaAtual}">
										<netui:anchor styleClass="selected" onClick="return false;" href="">
											${no_pagina}
										</netui:anchor>
									</c:when>
									<c:otherwise>
										<netui:anchor onClick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_modelo_vendas').onclick();return false;" href="#">
											${no_pagina}
										</netui:anchor>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<div class="barra"></div>
						<div class="botao">
							<label class="lblForm" >Quantidade : ${totalRegistros}</label>
							<netui:anchor action="novaAssociacaoProduto" onClick="abrirPopup1(this.href, null, 'lista_produtos_associados');return false;">
								<netui:parameter  name="id_modelo_venda" value="${idModeloVenda}"/>
								<netui:parameter  name="cd_modelo_venda" value="${cdModeloVenda}"/>
								<netui:parameter name="totalDeAssociados" value="${totalDeAssociados}"/>
								<netui:parameter name="id_grupo_produto" value="${idGrupoProduto}"/>
								<netui:parameter name="nm_grupo_produto" value="${nmGrupoProduto}"/>
								<netui:parameter name="nm_modelo_venda" value="${nmModeloVenda}"/>
								<netui:button tabindex="8" tagId="botao_nova_associacao_produto" type="button" styleClass="btNavegacao120" value="Nova Associa&ccedil;&atilde;o" alt="${bundle.default['catalogo.alterarModelo.associar.Produto']}" title="${bundle.default['catalogo.alterarModelo.associar.Produto']}"/>
							</netui:anchor>
						</div>
					</div>
				</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br clear="all" />
<div id="div_alterar_modelo_venda" style="position: relative;">
</div>