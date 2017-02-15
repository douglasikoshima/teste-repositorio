<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${id_forma_pagamento != 3}">
	<c:set scope="page" var="display" value="none"/>
        <div id="cartao_credito">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/descontos/gravarDadosCanalFormaPagamento.do">
	        	<html:hidden property="idFormaPagamento" styleId="idFormaPagamento" value="${id_forma_pagamento}"/>
	        	<html:hidden property="idCanal" styleId="idCanal" value="${id_canal}"/>
				<div class="fleft">
					<div class="label-form-bold label_required">N° Máximo de Parcelas:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:select property="numMaxParcelas" styleId="numMaxParcelas" tabindex="3" style="width:140px;" onkeypress="return mascaraNumerica(event, 0, 0, 0, 0)">
						<c:forEach var="condicaoPagamentoTO" items="${descontosForm.condicaoPagamentoList}">
							<html:option value="${condicaoPagamentoTO.qtparcelas}" >${condicaoPagamentoTO.qtparcelas}</html:option>
						</c:forEach>
					</html:select>
				</div>
				
				<div class="fleft" style="display:${display};">
					<div class="label-form-bold label_required">Taxa de Juros/Mensal:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<c:choose>
						<c:when test="${dadosCanalFormaPagamento[0].taxaJuros == null || dadosCanalFormaPagamento[0].taxaJuros == ''}">
							<c:set var="taxaJuros" scope="page" value="69"/>
						</c:when>
						<c:otherwise>
							<c:set var="taxaJuros" scope="page" value="${dadosCanalFormaPagamento[0].taxaJuros}"/>
						</c:otherwise>
					</c:choose>
					<html:text tabindex="4" styleClass="${display == 'none'?'':'required'} editavel"  size="5" property="taxaJuros" value="${taxaJuros}" onkeypress="return mascaraNumerica(event, 1, 0, 0, 0)">
						<fmt:formatNumber pattern="###.##" type="currency" value="${taxaJuros}"/>
					</html:text>
				</div>
				
				<div class="fleft" style="display:${display};">
					<div class="label-form-bold label_required" style="width: 200px">N° de Parcelas Sem Juros:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<c:choose>
						<c:when test="${dadosCanalFormaPagamento[0].nrMaxParcSemJuros == null}">
							<c:set var="nrMaxParcSemJuros" scope="page" value="51"/>
						</c:when>
						<c:otherwise>
							<c:set var="nrMaxParcSemJuros" scope="page" value="${dadosCanalFormaPagamento[0].nrMaxParcSemJuros}"/>
						</c:otherwise>
					</c:choose>
					<input tabindex="5" type="text" class="${display == 'none'?'':'required'} editavel" size="5" name="numParcelasSemJuros" value="${nrMaxParcSemJuros}" onkeypress="return mascaraNumerica(event, 0, 0, 0, 0)"/>
				</div>
				<br clear="all"/>
				<logic:iterate id="condicaoPagamentoTO" property="condicaoPagamentoList" name="descontosForm">
					<html:hidden property="idsCondicaoPagamento" styleId="idsCondicaoPagamento" value="${condicaoPagamentoTO.idCondicaoPagamento}" />
				</logic:iterate>
				<div class="barra">
				</div>
				
				<div class="botao">
				    <html:button property="bt_limpar" onClick="limparForm(this);limparForm('select_canal');clearAndShow('resultado_calculo_desconto');clearAndShow('dados_canal_forma_pagamento');return false;" styleClass="btNavegacao74" tabindex="7"  value="Limpar" bundle="messages" titleKey="catalogo.global.Limpar"/>
				    <span>&nbsp;</span>
					<html:button property="bt_gravar" tabindex="6" onclick="clearEditando();send(this, 'null', null, 'right_section');setEditando();" styleClass="btNavegacao74" value="Gravar" bundle="messages" titleKey="catalogo.dadosCanalFormaPagamento.Gravar"/>
				</div>
	        </html:form>
        </div>
</c:if>

<c:if test="${id_forma_pagamento == 3}">
        <div id="cartao_credito">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/descontos/calcularDesconto.do">
	        	<html:hidden property="idFormaPagamento" styleId="idFormaPagamento" value="${id_forma_pagamento}"/>
	        	<html:hidden property="idCanal" styleId="idCanal" value="${id_canal}"/>
				<div class="fleft">
					<div class="label-form-bold label_required">N° Máximo de Parcelas:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:select property="numMaxParcelas" styleId="numMaxParcelas" tabindex="3" style="width:140px;" onkeypress="return mascaraNumerica(event, 0, 0, 0, 0)">
						<c:forEach var="condicaoPagamentoTO" items="${descontosForm.condicaoPagamentoList}">
							<html:option value="${condicaoPagamentoTO.qtparcelas}" >${condicaoPagamentoTO.qtparcelas}</html:option>
						</c:forEach>
					</html:select>
				</div>
				
				<div class="fleft" style="display:${display};">
					<div class="label-form-bold label_required">Taxa de Juros/Mensal:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<c:choose>
						<c:when test="${dadosCanalFormaPagamento[0].taxaJuros == null || dadosCanalFormaPagamento[0].taxaJuros == ''}">
							<c:set var="taxaJuros" scope="page" value="69"/>
						</c:when>
						<c:otherwise>
							<c:set var="taxaJuros" scope="page" value="${dadosCanalFormaPagamento[0].taxaJuros}"/>
						</c:otherwise>
					</c:choose>
					<html:text tabindex="4" styleClass="${display == 'none'?'':'required'} editavel"  size="5" property="taxaJuros" value="${taxaJuros}" onkeypress="return mascaraNumerica(event, 1, 0, 0, 0)">
						<fmt:formatNumber pattern="###.##" type="currency" value="${taxaJuros}"/>
					</html:text>
				</div>
				
				<div class="fleft" style="display:${display};">
					<div class="label-form-bold label_required" style="width: 200px">N° de Parcelas Sem Juros:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<c:choose>
						<c:when test="${dadosCanalFormaPagamento[0].nrMaxParcSemJuros == null}">
							<c:set var="nrMaxParcSemJuros" scope="page" value="51"/>
						</c:when>
						<c:otherwise>
							<c:set var="nrMaxParcSemJuros" scope="page" value="${dadosCanalFormaPagamento[0].nrMaxParcSemJuros}"/>
						</c:otherwise>
					</c:choose>
					<input tabindex="5" type="text" class="${display == 'none'?'':'required'} editavel" size="5" name="numParcelasSemJuros" value="${nrMaxParcSemJuros}" onkeypress="return mascaraNumerica(event, 0, 0, 0, 0)"/>
				</div>
				<br clear="all"/>
				<logic:iterate id="condicaoPagamentoTO" property="condicaoPagamentoList" name="descontosForm">
					<html:hidden property="idsCondicaoPagamento" styleId="idsCondicaoPagamento" value="${condicaoPagamentoTO.idCondicaoPagamento}" />
				</logic:iterate>
				<div class="barra">
				</div>
				
				<div class="botao">
				    <html:button property="bt_limpar" onClick="limparForm(this);limparForm('select_canal');clearAndShow('resultado_calculo_desconto');clearAndShow('dados_canal_forma_pagamento');return false;" tabindex="7"  value="Limpar" styleClass="btNavegacao74" bundle="messages" titleKey="catalogo.global.Limpar"/>
				    <span>&nbsp;</span>
					<html:button property="bt_calcular" onclick="clearEditando();send(this, 'resultado_calculo_desconto', null, 'right_section');" styleClass="btNavegacao74 clearEditavel" value="Calcular" tabindex="6" bundle="messages" titleKey="catalogo.dadosCanalFormaPagamento.Calcular"/>
				</div>
	        </html:form>
        </div>
</c:if>

<c:if test="${id_forma_pagamento == 3}">
<div>
<img src="/catalogo/static_server/img/transparent.gif" onload="$('resultado_calculo_desconto').innerHTML=$('toto').innerHTML;" class="hide"/>
</div>
<div id="toto" style="display:none;">
	<div class="secao_conteudo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Tabela de Desconto do Cartão de Crédito:</div>
				<div class="conteudo_box_top_esq">
				</div>
				<div class="conteudo_box_top_dir openclose">
					<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"	alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
				</div>
			</div>
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/descontos/gravarDadosCanalCartaoCredito.do">
        	<html:hidden property="idFormaPagamento" styleId="idFormaPagamento" value="${id_forma_pagamento}"/>
        	<html:hidden property="idCanal" styleId="idCanal" value="${id_canal}"/>			
			<div>
				<div>
					<div class="conteudo_box_middle">
						<div class="conteudo_box_middle_mg">
							<table border="0" cellspacing="0" cellpadding="0" class="treeTable tabela-padrao tablesorter " id="TabelaResultadoBuscaProdutos" >
								<thead>
									<tr>
										<th width="50%"  class="sortable">N° de Parcelas:</th>
										<th width="50%"  class="sortable">Percentual de Desconto:</th>
									</tr>
								</thead>
								<tbody>
								    <logic:iterate id="parcelaTO" property="parcelasList" name="descontosForm" indexId="index">
											<tr>
												<td class="center">
													${index+1}X
												</td>
												<td class="center">
													<html:hidden property="idsCondicaoPagamento" styleId="idsCondicaoPagamento" value="${condicoesPagamento[index].idCondicaoPagamento}" />
													<html:hidden property="descontos" styleId="descontos" value="${parcelaTO.fatorPreco}" />
													<fmt:formatNumber type="percent" maxFractionDigits="2" minFractionDigits="2">${1-parcelaTO.fatorPreco}</fmt:formatNumber>
												</td>
											</tr>
									</logic:iterate>
								</tbody>
							</table>
							<div class="barra">
							</div>
							<div class="botao">
								<c:if test="${id_forma_pagamento != id_forma_pagamento_cartao_credito}">
									<img src="/catalogo/static_server/img/background/bg_conteudo.jpg" style="display:none;"/>
								</c:if>
							</div>
						</div>
					</div>
				<div class="conteudo_box_bottom"></div>
			</div>
			</html:form>
		</div>
	</div>
</div>
</c:if>