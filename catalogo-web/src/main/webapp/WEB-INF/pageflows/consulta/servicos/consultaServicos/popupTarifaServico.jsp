<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupPadrao>
<div name="conteudo">
 	<c:set var="nomeBox" scope="application"  value="Detalhes da Tarifa"/>
 	<jsp:include page="/templates/box_pre.jsp"/> 	
 	<div align="left">
 	<div class="both-scroll" style="height:350px;">
 	<table width="100%"  cellspacing="0" cellpadding="0" class="tabela-padrao">
				<thead>
					<tr>
						<th>Descrição</th>
						<th>Tipo de Débito</th>
						<th>Valor</th>
						<th>Unidade</th>
					</tr>
				</thead>
			<tbody>								
				<logic:iterate id="tarifaServicos" property="listaServicoTarifa" name="servicoForm">				
					<c:set value="true" var="firstPass"/>
					<tr>
						<td>${tarifaServicos.dscTarifa}</td>
						<td>${tarifaServicos.tpDebitoTarifa}</td>
						<td>${tarifaServicos.precoBase}</td>
						<td>${tarifaServicos.tpUnidadeTarifa}</td>															
					</tr>
					<c:set value="false" var="firstPass"/>
				</logic:iterate>
				</tbody>
			 </table>
 	</div>
 	 	 	 	 	 	 			
			<br clear="all"/>
		</div>
 	<jsp:include page="/templates/box_pos.jsp"/>
 	
</div>
</catalogo:popupPadrao>