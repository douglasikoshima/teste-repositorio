<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="breadcrumb">Parametrização > Produtos > <span><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/formasCondicoesPagamento/FormasCondicoesPagamentoAction.do');" style="cursor: pointer;">Formas e Condi&ccedil;&otilde;es de Pagamento</a></span></div>
		<!-- INICIO DIV MODAL VALORES TIPO PRODUTO -->
		<div id="detalhesTipoProduto" style="z-index: 25; display:none; position: absolute; background-color: #FFFFFF; border:1px solid #9DC4ED; width: 110px; height: 150px;" class="transparencia">
			<div style="padding: 0px 0px 0px 3px">
				<p style="padding: 5px 0px 0px 0px"><b>Produtos:</b></p>
				<c:forEach items="${popup_tp_produto}" var="produto">
					${produto}<br/>
				</c:forEach>
			</div>
		</div>
		<!-- FIM DIV MODAL VALORES TIPO PRODUTO -->
		<div class="secao_conteudo">
				<div class="conteudo_box_top">
					<div class="conteudo_box_top_center">
						Formas e Condições de Pagamento:
					</div>
					<div class="conteudo_box_top_esq">
					</div>
					<div class="conteudo_box_top_dir openclose">
						<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
					</div>
				</div>
				<div>
					<div>
						<div class="conteudo_box_middle">
							<div class="conteudo_box_middle_mg relative">
								<div class="legendaObrigatorio help">(*) Campo Obrigatório</div>
							    <div class="link_manual" title="Dúvida">
									<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161332" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
								</div>
								<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/formasCondicoesPagamento/pesquisar.do" styleId="formasCondicoesPagamentoForm" onsubmit="false">
									<html:errors prefix="tipoProduto" name="tipoProduto"/>
									
									<div class="fleft">
										<div class="label-form-bold label_required" style="width:110px;">Tipo de Produto:<font size="1px" color="#EEB422" valign="center">*</font></div>
										
										<html:select tabindex="1" property="idsTiposProduto" onkeyup="changeRequirementFormaCondPagamento(selectedIndex, 'select_plataforma');"
											onclick="changeRequirementFormaCondPagamento(selectedIndex, 'select_plataforma');" style="width:175px;" styleClass="required" onchange="$('resultado_pesquisa').innerHTML='';formaPagtoMostrarBotaoDetalhesTipoProduto(this);">
											<html:option value="">-- Selecione --</html:option>
											<html:option value="${ids_tipo_produto}">${desc_tipo_produto}</html:option>
											<html:option value="9">Recargas</html:option>
											<html:option value="10">Acessórios</html:option>
										</html:select>
									</div>
									<div id="botaoDetalhesTipoProduto" class="fleft" style="visibility:hidden">
										<input type="button" onmouseout="formaPagtoEsconderDetalhesTipoProduto(this)" onmouseover="formaPagtoMostrarDetalhesTipoProduto(this);" value="+" class="btnMais"/>
									</div>
									<div class="fleft">
										<div class="label-form-bold label_required" style="width:110px;">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
										
										<html:select tabindex="2" property="idPlataforma" styleId="select_plataforma" style="width:140px;" styleClass="required" onchange="$('resultado_pesquisa').innerHTML='';">
											<html:option value="">-- Selecione --</html:option>
											<c:forEach var="plataformasTO" items="${plataformas}">
												<html:option value="${plataformasTO.idPlataforma}">${plataformasTO.nmPlataforma}</html:option>
											</c:forEach>
										</html:select>
									</div>
									
									<div class="fleft">
										<div class="label-form-bold label_required" style="width:60px;">Canal:<font size="1px" color="#EEB422" valign="center">*</font></div>
										
										<html:select tabindex="3" property="idCanal" style="width:170px;" styleClass="required" onchange="$('resultado_pesquisa').innerHTML=''">
											<html:option value="">-- Selecione --</html:option>
											<c:forEach var="canaisTO" items="${canais}">
												<html:option value="${canaisTO.idCanal}">${canaisTO.nmCanal}</html:option>
											</c:forEach>
										</html:select>
									</div>
									
									<br clear="all"/>
									<br clear="all"/>
	
									<div class="barra">
									</div>
	
									<div class="botao">
										<html:button property="tb_limpar" tabindex="6" onClick="limparForm(this);$('resultado_pesquisa').hide();return false;" value="Limpar" styleClass="btNavegacao74" bundle="messages" titleKey="catalogo.global.Limpar"/>
										<span>&nbsp;</span>
										<html:button property="bt_exportar" tabindex="5" value="Exportar" styleClass="btNavegacao74" onclick="window.location.href=$(this).next('a').href;return false;" bundle="messages" titleKey="catalogo.FormaCondicoesPagamento.Exportar"/>
										
										<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/formasCondicoesPagamento/exportar.do?${FormasCondicoesPagamento.csv}"/>
										<span>&nbsp;</span>
										
										<html:button tabindex="4" styleId="pesquisar_formas_condicoes_pagamento" property="pesquisar_formas_condicoes_pagamento" onclick="if(send(this, 'resultado_pesquisa', null, 'right_section')){$('resultado_pesquisa').show();};return false;"
														styleClass="btNavegacao74" value="Pesquisar" bundle="messages" titleKey="catalogo.FormaCondicoesPagamento.Pesquisar"/>
									</div>
								
								</html:form>	
							</div>
						</div>
						<div class="conteudo_box_bottom">
						</div>
					</div>
				</div>
			</div>
			

<div id="resultado_pesquisa">
</div>
			
