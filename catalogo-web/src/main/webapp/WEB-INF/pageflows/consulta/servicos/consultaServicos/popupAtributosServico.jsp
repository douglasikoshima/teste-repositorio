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
		<div id="popup_atributos_servico">
			<c:set var="nomeBox" scope="application"  value="Detalhes do Serviço: ${nome_servico}"/>
			<jsp:include page="/templates/box_pre.jsp"/>
														
			<div class="both-scroll" style="height:350px;">
			<c:set var="firstPass" value="true"/>
	 			<table  cellspacing="0" cellpadding="0" class="tabela-padrao" id="TabelaRelacionamentosRecentes" width="90%">
					<thead>
						<tr>
							<th>Nome do Atributo</th>
							<th>Valor do Atributo</th>
						</tr>
					</thead>
				<tbody height="100%">								
					<logic:iterate id="listaAtributo" property="atributoList" name="servicoForm">				
						<c:set value="true" var="firstPass"/>
						<tr>							
							<td style="text-align: left;"><label class="lblForm">
								<c:if test="${firstPass == true}">
									${listaAtributo.svcAttrNm}
								</c:if>
							</td>
							<td>${listaAtributo.valor}</td>																					
						</tr>
						<c:set value="false" var="firstPass"/>
					</logic:iterate>
					</tbody>
				 </table>
				 <br clear="all"><br>				
 			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
						
		</div>
	</div>
</catalogo:popupPadrao>
 