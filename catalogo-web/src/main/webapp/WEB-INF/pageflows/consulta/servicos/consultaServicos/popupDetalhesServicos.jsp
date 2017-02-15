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
			<c:set var="nomeBox" scope="application"  value="Detalhes do Serviço:: ${nmComercial}"/>
			<jsp:include page="/templates/box_pre.jsp"/>			
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/abrirPopupDetalhesServico.do" onsubmit="false">
			<div class="both-scroll">
			<c:set var="firstPass" value="true"/>
			<div align="left">
	 			<table width="100%"  cellspacing="0" cellpadding="0" class="tabela-padrao">
					<thead>
						<br clear="all"/>
						<tr>							
							<th>Descrição do Serviço:</th>
							<th>Planos do Serviço:</th>																				
						</tr>
					</thead>
				<tbody height="100%">								
					 <logic:iterate id="detalhesServico" property="detalhesServicoList" name="servicoForm">				
						<c:set value="true" var="firstPass"/>											
						<td>${detalhesServico.nmComercial}</td>
						<td>
						
	 			<html:select property="idPlataforma">
				   <option value="">-- Selecione --</option>
				   <c:if test="${detalhesServico.listaPlano != null}">
				    <c:forEach items="${detalhesServico.listaPlano}" var="detalhesServicoTO">				       
				       <option>${detalhesServicoTO.descricao}</option>				       
				   </c:forEach>	
				   </c:if>					   			   				  
			 	</html:select> 
						</td>							
						<c:set value="false" var="firstPass"/>
					</logic:iterate> 
					</tbody>
				 </table>				 
			</div>
				 <br clear="all"><br>				
 			</div>
 			</html:form>
			<jsp:include page="/templates/box_pos.jsp"/>
						
		</div>
	</div>
</catalogo:popupPadrao>