<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Parametriza&ccedil;&atilde;o de Desconto:</div>
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
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161335" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/descontos/pesquisar.do">
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 40px; text-align: left;">Canal:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="idCanal" styleId="select_canal" tabindex="1" style="width:250px;" onChange="$('buscar_dados_canal_forma_pagamento').onclick();">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach var="canalTO" items="${canais}">
									<html:option value="${canalTO.idCanal}">${canalTO.nmCanal}</html:option>
								</c:forEach>
							</html:select>								
						</div>
						<div  class="fleft">
							<div  class="label-form-bold label_required">Forma de Pagamento:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="idFormaPagamento" styleId="select_fpagamento" tabindex="2" style="width:250px;" onChange="$('buscar_dados_canal_forma_pagamento').onclick();">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach var="formaPagamentoTO" items="${formasPagamento}">
								    <c:if test="${formaPagamentoTO.idFormaPagamento == 3 || formaPagamentoTO.idFormaPagamento == 7}">
									    <html:option value="${formaPagamentoTO.idFormaPagamento}">${formaPagamentoTO.nmFormaPagamento}</html:option>
								    </c:if>	
								</c:forEach>
							</html:select>								
						</div>
						<br clear="all"/>
						<div>
							<div style="display:none;">
								<html:button property="buscar_dados_canal_forma_pagamento" onclick="$('resultado_calculo_desconto').innerHTML='';if(checkFiltrosDesconto()){send(this, 'dados_canal_forma_pagamento', null, 'right_section')};" value="buscar" bundle="messages" titleKey="catalogo.Descontos.Buscar"/>
							</div>
						</div>
						<br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
							<html:button property="bt_exportar" tabindex="8" value="Exportar" styleClass="btNavegacao74" onclick="window.location.href=$(this).next('a').href;return false;" bundle="messages" titleKey="catalogo.Descontos.Exportar"/>
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/descontos/exportar.do" styleClass="hide">Exportar</html:link>
						</div>
						<div class="barra">
						</div>
					</html:form>
					<div id="dados_canal_forma_pagamento">
					</div>
				</div>
			</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
	<div id="resultado_calculo_desconto">
	</div>


