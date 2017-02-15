<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/atualizarStatusProdutosPP.do?paramIdOrgVendas=${paramIdOrgVendas}" styleId="parametrizacaoProdutosPPForm">

    <div id="resultado_busca_produtopp">
    	<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
						<div style="width: 99%; position: relative;" id="lista_produtospp">
							<div class="both-scroll" style="height:250px;width:804px;">
							
								<table cellspacing="0" cellpadding="0" class="tabela-padrao-new tablesorter table_body" id="TabelaRelacionamentosRecentes">
										<thead>
											<tr>
												<th class="center" width="10px" ><input type="checkbox" name="produtosCheck" class="semBorda"  onclick="selectTodosCheckbox('lista_produtospp', '.checkbox_produtopp', this.checked);"/></th>
												<th class="sortable" align="left">Código SAP</th>
												<th class="sortable" align="left">Descrição SAP</th>
												<th class="sortable" align="left">Descrição Catálogo</th>
												<th class="sortable" align="left">Tecnologia</th>
												<th>&nbsp;</th>
												<th>&nbsp;</th>
												<cata:temPermissao acao="disponibilidadeAcaoCanal">
													<th>&nbsp;</th>
												</cata:temPermissao>
											</tr>
										</thead>
									<tbody>
								
								<logic:iterate id="produtoLista" property="produtoLista" name="parametrizacaoProdutosPPForm">
									<tr>
										<td class="center" style="padding-left: 5px" width="10px">
											<html:checkbox property="idsProdutosPP" value="${produtoLista.idProduto}" styleId="checkbox_produtopp_${produtoLista.idProduto}" styleClass="semBorda belongsToForm checkbox_produtopp"/>
										</td>
										<td>${produtoLista.cdCodigo}</td>
										<td>${produtoLista.dsSap}</td>
										<td>${produtoLista.nmProduto}</td>
										<td>${produtoLista.nmTecnologia}</td>
										<td class="center">
										
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/popupDispPrecoOrgVendas.do?id_produto=${produtoLista.idProduto}" 
													onclick="abrirPopup1(this.href, null, 'right_section');return false;">
												<img alt="Org. de Vendas" src="/catalogo/static_server/img/botoes/bt-brasil-ico.png"/>
											</html:link>
										</td>
										<td class="center">
											<c:choose>
												<c:when test="${produtoLista.inDisponivel=='S'}">
													<img alt="Disponível" src="/catalogo/static_server/img/bullets/icon-available.png"/>
												</c:when>
												<c:otherwise>
													<img alt="Indisponível" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
												</c:otherwise>
											</c:choose>
										</td>
										<cata:temPermissao acao="disponibilidadeAcaoCanal">
											<td class="center">
											
												<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/disponibilidadeAcaoCanal.do?id_produto=${produtoLista.idProduto}&nm_produto=${produtoLista.nmProduto}"
														onclick="if(abrirLink('disp_acao_canal', this.href)){clearAndShow('disp_acao_canal');}return false;">
													<img alt="Configurar Produto" src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif"/>
												</html:link>
											</td>
										</cata:temPermissao>
									</tr>
								</logic:iterate>	
									</tbody>
								</table>
						</div>
					</div>
					<br clear="all"/>
					<div class="barra"></div>
					<table class="tabelaIcones">
						<tr>
							<td width="50px;"><label>Ícones:</label></td>
							<td width="15"><img src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif" alt="Detalhar disponibilidade do produto por ação e canal"/></td>
							<td>Detalhar disponibilidade do produto por ação e canal</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><img src="/catalogo/static_server/img/botoes/bt-brasil-ico.png" alt="Visualizar organizações de vendas que possuem o produto" /></td>
							<td>Visualizar organizações de vendas que possuem o produto</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><img src="/catalogo/static_server/img/bullets/icon-available.png" alt="Produto disponível em alguma organização de venda" /></td>
							<td>Produto disponível em alguma organização de venda</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><img src="/catalogo/static_server/img/bullets/icon-unavailable.png" alt="Produto indisponível" /></td>
							<td>Produto indisponível</td>
						</tr>
					</table>
					<div class="barra"></div>
					<div class="paginacao" style="width:99%;" align="right">
						<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
							<c:choose>
								<c:when test="${no_pagina == paginaAtual}">
									<html:link styleClass="selected" onclick="return false;" href="">
										${no_pagina}
									</html:link>
								</c:when>
								<c:otherwise>
									<html:link onclick="$('pagina_solicitada').value='${no_pagina}';$('botao_pesquisar_produtos_pp').onclick();return false;" href="#">
										${no_pagina}
									</html:link>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="barra"></div>
					<label class="lblForm">Quantidade: ${totalRegistros}</label><br /><br />
					<fieldset class="content" style="height: 80px;">
						<h3 style="margin-bottom: 5px;">Gravação de Disponibilidade</h3>
						<label style="font-weight: bold; color: #666; margin-left: 10px;" >Status:</label>
						
						<html:select property="opcaoSelect">
							<html:option value="S">Disponível</html:option>
							<html:option value="N">Indisponível</html:option>
						</html:select>
						<label style="font-weight: bold; color: #666;">Organização de Venda:</label>
						
						<label style="color: #5875A1; font-weight: bold;" >
							<span>${orgVendas}</span>
						</label>
												
						<cata:temPermissao acao="gravarDisponibilidadeProduto">
							<div class="botao">
								<html:button property="botao_gravar_disp" tabindex="7" styleId="botao_gravar_disp" onclick="if(checkSelecaoTabela($('lista_produtospp').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){return false;}send(this, 'resultado_pesquisa', null, 'right_section');pauseReCall($('botao_pesquisar_produtos_pp'), 1000);" styleClass="btNavegacao74" value="Gravar" bundle="messages" altKey="catalogo.pp.produtos.GravarDisp" titleKey="catalogo.pp.produtos.GravarDisp"/>
							</div>
						</cata:temPermissao>
					</fieldset>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
    </div>

</html:form>
<div id="disp_acao_canal" style="position:relative;"></div>