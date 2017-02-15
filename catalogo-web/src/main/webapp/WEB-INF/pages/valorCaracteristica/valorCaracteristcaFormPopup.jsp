<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<catalogo:popUpDefault title="Valores Característica" close="true">
	<div id="contentAllPopUp">
		<div class="legendaObrigatorio help">&nbsp; (*) Campo Obrigat&oacute;rio</div>
		<c:if test="${labelError != null}">
			<div id="divErrosMidlePopUp" style="background-color: white;position:relative;"><catalogo:contentError id="contentErrorPopUp" >${labelError}</catalogo:contentError></div>
		</c:if>
		<table>
			<tr>
				<td style="text-align: left;">
					<div id="adicionarValor">
						<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/saveValores.do" styleId="caracteristicaForm">
							<cata:temPermissao acao="adicionarValor">
								<html:hidden property="idValorCaracteristica" styleId="idValorCaracteristica"/>
								<html:hidden property="idCaracteristica" styleId="idCaracteristica"/>
								<html:hidden property="existAssociacao" styleId="existAssociacao"/>
								<div class="label-form-bold2 fleft label_required">Valor:<font size="1px" color="#EEB422">*</font></div>
								<html:text property="valor" styleId="valor" styleClass="required editavel" onkeypress="return semPontoVirgula(event);" maxlength="200"/>
								<c:choose>
									<c:when test="${alterar}">
										<input type="button" value="Alterar" class="btNavegacao74" id="btIncluir" name="btIncluir" onclick="if (document.getElementById('idValorCaracteristica').value != '' && document.getElementById('existAssociacao').value == 'S') {clearEditando();$('link' + document.getElementById('idValorCaracteristica').value).onclick();} else {clearEditando();send(this, 'popup1', null, 'div_erros_popup');}"/>
									</c:when>
									<c:otherwise>
										<input type="button" value="Incluir" class="btNavegacao74" id="btIncluir" name="btIncluir" onclick="if (document.getElementById('idValorCaracteristica').value != '' && document.getElementById('existAssociacao').value == 'S') {clearEditando();$('link' + document.getElementById('idValorCaracteristica').value).onclick();} else {clearEditando();send(this, 'popup1', null, 'div_erros_popup');}"/>
									</c:otherwise>
								</c:choose>
								<input type="button" id="btInclusaoHidden" style="display: none;" onclick="send(this, 'popup1', null, 'div_erros_popup');"/>
							</cata:temPermissao>
						</html:form>
					</div>
					<div id="alterarValorCaracteristica"></div>
				</td>
			</tr>
		</table>
		<div id="valorCaracteristicaList">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/removeValores.do" styleId="caracteristicaForm" onsubmit="false">
				<html:hidden property="idValorCaracteristica" styleId="idValorCaracteristica"/>
				<html:hidden property="idCaracteristica" styleId="idCaracteristica" value="${caracteristicaForm.idCaracteristica}"/>
				<div style="overflow: auto; height: 300px; ">
					<table cellspacing="0" cellpadding="0" class="tabela-padrao-new tablesorter table_body" id="TabelaRelacionamentosRecentes" style="width: 99%;">
						<thead>
							<tr>
								<th class="sortable">Valor</th>
								<th width="50px">Alterar</th>
								<th width="50px">Excluir</th>
							</tr>
						</thead>
						<tbody>
						<logic:iterate id="valorCaracteristicaList" property="valorCaracteristicaList" name="caracteristicaForm">
							<tr>
								<td class="left">${valorCaracteristicaList.valor}</td>
								<td class="left" style="text-align: center;">
									<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/verificarAssociacaoAlteracaoValorCaracteristica.do?idValorCaracteristica=${valorCaracteristicaList.idValorCaracteristica}" styleId="link${valorCaracteristicaList.idValorCaracteristica}" onclick="abrirPopup2(this.href);return false;" style="display: none;"/>
									
									<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/loadValor.do?idValorCaracteristica=${valorCaracteristicaList.idValorCaracteristica}&idCaracteristica=${caracteristicaForm.idCaracteristica}"
										onclick="abrirPopup1(this.href);return false;">
										<input type="button" class="icoAlterar" id="icoAlterar" onclick=""/>
									</html:link>
								</td>
								<td class="left" style="text-align: center;">
									<c:choose>
										<c:when test="${valorCaracteristicaList.existGrupoProdutoCaracteristica}">
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/verificarAssociacaoExclusaoValorCaracteristica.do?idValorCaracteristica=${valorCaracteristicaList.idValorCaracteristica}" title=""
												onclick="document.getElementById('idValorCaracteristica').value='${valorCaracteristicaList.idValorCaracteristica}'; abrirPopup2(this.href);return false;">
												<input type="button" class="btExcluir" id="btExcluir"/>
											</html:link>
											<input type="button" style="display: none;" id="btExcluir${valorCaracteristicaList.idValorCaracteristica}" onclick="document.getElementById('idValorCaracteristica').value='${valorCaracteristicaList.idValorCaracteristica}';$(this).form.action=$(this).next('a').href;send(this, 'popup1', null, null);"/>
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/removeValores.do?idValorCaracteristica=${valorCaracteristicaList.idValorCaracteristica}" styleClass="hide">
											</html:link>
										</c:when>
										<c:otherwise>
											<input type="button" class="btExcluir" id="btExcluir" onclick="document.getElementById('idValorCaracteristica').value='${valorCaracteristicaList.idValorCaracteristica}';$(this).form.action=$(this).next('a').href;send(this, 'popup1', null, null);"/>
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/removeValores.do?idValorCaracteristica=${valorCaracteristicaList.idValorCaracteristica}" styleClass="hide">
											</html:link>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</logic:iterate>
						
						<c:if test="${!not empty caracteristicaForm.valorCaracteristicaList}">
							<tr>
								<td class="center"><span>Não existem valores para essa Característica.</span></td>
							</tr>
						</c:if>	
						
						</tbody>
					</table>
				</div>
			</html:form>	
		</div>
	</div>
</catalogo:popUpDefault>
