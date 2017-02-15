<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<div id="resultado_busca_modelo_vendas">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
			<div class="conteudo_box_top_esq">
			</div>
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
								<th class="sortable">C&oacute;digo do Modelo de Vendas</th>
								<th class="sortable">Nome do Modelo de Vendas</th>
								<th class="sortable">Produtos<br>Associados</th>
								<th >Alterar</th>
								<th >Excluir</th>
							</tr>
						</thead>
					</table>
					<div style="height:300px;" class="both-scroll">
						<netui-data:repeater dataSource="modelosVenda" defaultText="<br><span align='center'>Nunhum Modelo de Vendas encontrado.</span>">
								<netui-data:repeaterHeader>
									<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
										<thead>
										<tr>
											<th class="sortable">C&oacute;digo do Modelo de Vendas</th>
											<th class="sortable">Nome do Modelo de Vendas</th>
											<th class="sortable">Produtos<br>Associados</th>
											<th >Alterar</th>
											<th >Excluir</th>
										</tr>
									</thead>
									<tbody>
										</netui-data:repeaterHeader>
										<netui-data:repeaterItem>
										<tr>
											<td style="text-align: left;">${container.item.cdModeloVenda}</td>
											<td style="center">${container.item.nmModeloVenda}</td>
											<td class="center">
												<netui:anchor action="listarProdutosAssociado" onClick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;" title="Produtos Associados">
													<netui:parameter name="id_modelo_venda" value="${container.item.idModeloVenda}"/>
													<netui:parameter name="nm_modelo_venda" value="${container.item.nmModeloVenda}"/>
													<netui:parameter  name="pagina_solicitada" value="1"/>
													<img alt="Produtos Associados" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>
												</netui:anchor>
											</td>
											<td class="center">
												<netui:anchor action="alterarModeloVendaMatrizOferta" tagId="alterar_modelo_menda" onClick="if(abrirLink('div_alterar_modelo_venda', this.href, 'right_section')){$('novo_modelo_vendas').hide();clearAndShow('div_alterar_modelo_venda');}return false">
									 				<netui:parameter name="id_modelo_venda" value="${container.item.idModeloVenda}"/>
									 				<netui:parameter name="cd_modelo_venda" value="${container.item.cdModeloVenda}"/>
									 				<netui:parameter name="nm_modelo_venda" value="${container.item.nmModeloVenda}"/>
													<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
												</netui:anchor>
											</td>
											<td class="center">
												<netui:anchor action="excluirModeloVendaMatrizOferta" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa')){$('novo_modelo_vendas').innerHTML=''}return false;">
													<netui:parameter  name="id_modelo_venda" value="${container.item.idModeloVenda}"/>
									 				<netui:parameter name="cd_modelo_venda" value="${container.item.cdModeloVenda}"/>
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
						</div>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br clear="all" />
<div id="div_alterar_modelo_venda" style="position: relative; display: none">
</div>