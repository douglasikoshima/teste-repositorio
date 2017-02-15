<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">
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
				<div class="conteudo_box_middle_mg">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/formasCondicoesPagamento/salvar.do" styleId="formasCondicoesPagamentoForm" onsubmit="false">
					<html:hidden property="formasCondicoesAntigas" styleId="formasCondicoesAntigas" value="${formas_condicoes_pagamento_persistidas}"/>
					<html:hidden property="todasFormasCondicoes" styleId="todasFormasCondicoes" value="${todas_formas_condicoes_pagamento}"/>
					<html:hidden property="idPlataforma" styleId="idPlataforma" value="${id_plataforma}"/>
					<html:hidden property="idCanal" styleId="idCanal" value="${id_canal}"/>
					<html:hidden property="idsTiposProduto" styleId="idsTiposProduto" value="${ids_tipos_produto}"/>
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter" >
							<thead>
								<tr>
									<th width="50%" class="sortable">Forma de Pagamento</th>
									<th width="50%" class="sortable" title="A condição selecionada significa que o canal/plataforma/tipo de produto terá ATÉ esta opção para parcelar">Condição de Pagamento</th>
									<th width="50%" title="Será aplicado ao valor da compra e servirá para determinar se a mesma poderá ser parcelada">Valor Minimo Parcela</th>
								</tr>
							</thead>
							<tbody>
						<c:if test="${formasCondicoesPagamentoForm.formaPagamentoList != null}">
						<logic:iterate id="formaPagamentoListTO" property="formaPagamentoList" name="formasCondicoesPagamentoForm">
								<c:if test="${ (fn:length(ids_tipos_produto) > 1 && formaPagamentoListTO.idFormaPagamento != 6) || ( ids_tipos_produto == 9 && formaPagamentoListTO.idFormaPagamento != 7 && formaPagamentoListTO.idFormaPagamento != 6  ) || ( ids_tipos_produto == 10)}">	
									<tr>
										<td style="text-align: left; border-bottom:1px solid #9DC4ED;height:50px;">
										
											<c:if test="${formaPagamentoListTO.listaCondicaoPagamentoSelecionada[0] == null}">
												<input type="checkbox" name="formasSelecionadas" id="checkbox_${formaPagamentoListTO.idFormaPagamento}" value="${formaPagamentoListTO.idFormaPagamento}" class="semBorda editavel" onclick="selectFormaPgto(this);"/>
											</c:if>
											<c:if test="${formaPagamentoListTO.listaCondicaoPagamentoSelecionada[0] != null}">
												<input type="checkbox" name="formasSelecionadas" id="checkbox_${formaPagamentoListTO.idFormaPagamento}" checked="checked" value="${formaPagamentoListTO.idFormaPagamento}" class="semBorda editavel" onclick="selectFormaPgto(this);"/>
											</c:if>
											
											<label for="checkbox_${formaPagamentoListTO.idFormaPagamento}">${formaPagamentoListTO.nmFormaPagamento}</label>
										</td>
										<td style="text-align: left; border-bottom:1px solid #9DC4ED;">
											<c:choose>
												<c:when test="${formaPagamentoListTO.listaCondicaoPagamento[0].qtparcelas == 0}">
													<html:hidden property="condicoesSelecionadas" styleId="condicoesSelecionadas" value="${formaPagamentoListTO.listaCondicaoPagamento[0].idCondicaoPagamento}"/>
													A Vista
												</c:when>
												<c:otherwise>
													<c:set var="foundSelected" value="false"/>
													<c:set var="list_index" value="0"/>
													<c:set var="id_condicao" value="-1"/>
													
													<c:if test="${formaPagamentoListTO.listaCondicaoPagamentoSelecionada[0].idCondicaoPagamento != null}">
										 				<c:set var="list_index" value="-1"/>
														<c:forEach var="selecionada" items="${formaPagamentoListTO.listaCondicaoPagamentoSelecionada}">
															<c:if test="${id_condicao != selecionada.idCondicaoPagamento}">
																<c:set var="id_condicao" value="${selecionada.idCondicaoPagamento}"/>
																<c:set var="list_index" value="${list_index+1}"/>
															</c:if>
														</c:forEach>
													</c:if>
													
													<html:select property="condicoesSelecionadas" style="width:300px;" styleClass="required editavel">
														<c:forEach var="condicaoPagamentoTO" items="${formaPagamentoListTO.listaCondicaoPagamento}">
															<html:option value="${condicaoPagamentoTO.idCondicaoPagamento}">${condicaoPagamentoTO.nmCondicaoPagamento}</html:option>
														</c:forEach>
													</html:select>
								                    <img style="display:none;" onload="$(this).previous('select').selectedIndex=${list_index}" src="/catalogo/static_server/img/botoes/bg-dots.gif" />
												</c:otherwise>
											</c:choose>
										</td>
										<td style="text-align: center; border-bottom:1px solid #9DC4ED;">
											<c:set var="displayCombo" value="true" />
											
											<c:if test="${formaPagamentoListTO.idFormaPagamento == 1 || formaPagamentoListTO.idFormaPagamento == 5}">
												<c:set var="displayCombo" value="false"/>
											</c:if>
											
											<c:if test="${formaPagamentoListTO.listaCondicaoPagamentoSelecionada[0] == null}">
												<c:choose>
												<c:when test="${formaPagamentoListTO.vlMinimoParcela == null}">
													<fmt:formatNumber pattern="###,###.##" value="0" var="valor_1"/>
													<html:text property="parcelasMinimas" styleClass="valor_minimo" disabled="true" size="8" style='text-align:right;display:${displayCombo?"":"none" }' value="${valor_1}" onkeypress="return formatar_moeda(this,'.',',',event);">
												</html:text>
												</c:when>
												<c:otherwise>
													<fmt:formatNumber pattern="###,###.##" value="${formaPagamentoListTO.vlMinimoParcela}" var="valor_2"/>
													<html:text property="parcelasMinimas" styleClass="valor_minimo" disabled="true" size="8" style='text-align:right;display:${displayCombo?"":"none" }' value="${valor_2}" onkeypress="return formatar_moeda(this,'.',',',event);"/>
												</c:otherwise>
												</c:choose>
											</c:if>
											
											<c:if test="${formaPagamentoListTO.listaCondicaoPagamentoSelecionada[0] != null}">
												<fmt:formatNumber pattern="###,###.##" value="${formaPagamentoListTO.vlMinimoParcela}" var="valor_3"/>
												<html:text property="parcelasMinimas" styleClass="valor_minimo" size="8" style='text-align:right;display:${displayCombo?"":"none" }' value="${valor_3}" onkeypress="return formatar_moeda(this,'.',',',event);"/>
											</c:if>
											
											<span style='display:${displayCombo?"none":"" }'>N/A</span>
											
										</td>
									</tr>
							</c:if>
							</logic:iterate>
							</c:if>
							
							<c:if test="${!not empty formasCondicoesPagamentoForm.formaPagamentoList}">
								<tr>
									<td class="center"><span>Característica não encontrada.</span></td>
								</tr>
							</c:if>	
								
							</tbody>
						</table>
					<br clear="all"/>
					<div class="botao">
						<html:button property="bt_gravar" onclick="clearEditando();send(this, null, null, 'right_section');setEditando();return false;" styleClass="btNavegacao74" value="Gravar" bundle="messages" titleKey="catalogo.resultadoBusca.Gravar"/>
					</div>
					
					</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom">
			</div>
		</div>
	</div>
</div>