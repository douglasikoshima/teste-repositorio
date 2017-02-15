<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<catalogo:popUpDefault title="Valores Característica" close="true">
	<div style="overflow: auto; height: 300px; ">
			<table class="tabela-padrao-new tablesorter table_body" id="TabelaRelacionamentosRecentes" style="width: 99%;">
				<thead>
					<tr>
						<th class="sortable">Valor</th>
					</tr>
				</thead>
				<logic:iterate id="valorCaracteristicaTO" property="valorCaracteristicaList" name="caracteristicaForm">
					<tbody>
					<c:choose>
						<c:when test="${valorCaracteristicaTO.valor != null}">
							<tr>
								<td class="left">${valorCaracteristicaTO.valor}</td>
							</tr>
						</c:when>
					</c:choose>
					</tbody>
				</logic:iterate>
				<c:if test="${!not empty caracteristicaForm.valorCaracteristicaList}">
					<tr>
						<td class="center"><span>Não existem valores para essa Característica.</span></td>
					</tr>
				</c:if>			
			</table>
	</div>
</catalogo:popUpDefault>