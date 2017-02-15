<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<catalogo:popupPadrao>	
		<c:set var="nomeBox" scope="application" value="Organização de Vendas / Preço"/>
		<input type="hidden" id="larguraPopup" value="400"/>
		<input type="hidden" id="alturaPopup" value="200"/>
		<jsp:include page="/templates/box_pre.jsp" />
		<div id="disp_preco_orgvendas">
			
			<table class="tabela-padrao" >
				<c:forEach var="produto" items="${produtoListaOrgVenda}">
					<c:if test="${produto.idProduto eq id_produto}">
						<tr>
							<td style="width: 10px;">
								<c:choose>
									<c:when test="${produto.inDisponivel=='S'}">
										<img alt="Dispon&iacute;vel" src="/catalogo/static_server/img/bullets/icon-available.png"/>
									</c:when>
									<c:otherwise>
										<img alt="Indispon&iacute;vel" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="text-align: left;">
								<c:out value="${produto.sgOrgVendas}"/>
							</td>
							<td style="text-align: right;" style="width: 90px;">
								<c:set var="valor" value="${produto.valor}" />   
								<fmt:setLocale value="pt-BR" />
								<fmt:formatNumber value="${valor}" minFractionDigits="2" type="currency"/>
							</td>
						</tr>							
					</c:if>
				</c:forEach>
			</table>
		</div>
		<jsp:include page="/templates/box_pos.jsp" />
		<div class="botao">
			<input type="button"  />
			<html:button property="bt_ok" onclick="fecharPopup('popup1');" value="Ok" styleClass="btOk" bundle="messages" titleKey="catalogo.global.OK"/>
		</div>
</catalogo:popupPadrao>
