<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${id_forma_pagamento != id_forma_pagamento_cartao_credito}">
	<c:set scope="page" var="display" value="none"/>
</c:if>
<br/>
<div id="resultado_calculo_desconto" style="display:${display};">
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
			<html:hidden property="numMaxParcelas" styleId="numMaxParcelas" value="${num_max_parcelas}"/>
			<html:hidden property="numParcelasSemJuros" styleId="numParcelasSemJuros" value="${num_parcelas_sem_juros}"/>
			<html:hidden property="taxaJuros" styleId="taxaJuros" value="${taxa_juros}"/>
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
									<logic:iterate id="descontoTO" property="descontosList" name="descontosForm" indexId="index">
											<tr>
												<td class="center">
													${index+1}X
												</td>
												<td class="center">
													<html:hidden property="idsCondicaoPagamento" styleId="idsCondicaoPagamento" value="${ids_condicao_pagamento[index]}" />
													<html:hidden property="descontos" styleId="descontos" value="${descontos[index]}" />
													<fmt:formatNumber type="percent" maxFractionDigits="2" minFractionDigits="2">${descontos[index]}</fmt:formatNumber>
												</td>
											</tr>
									</logic:iterate>
								</tbody>
							</table>
							<div class="barra">
							</div>
							<div class="botao">
								<c:if test="${id_forma_pagamento != id_forma_pagamento_cartao_credito}">
									<img src="/catalogo/static_server/img/background/bg_conteudo.jpg" onload="$('gravar_calculo').onclick();" style="display:none;"/>
								</c:if>
								<html:button property="gravar_calculo" onclick="send(this, 'null', null, 'right_section')" value="Gravar" styleClass="btNavegacao74" bundle="messages" titleKey="catalogo.resultadoCalculoDesconto.Gravar"/>
							</div>
						</div>
					</div>
				<div class="conteudo_box_bottom"></div>
			</div>
			</html:form>
		</div>
	</div>
</div>

