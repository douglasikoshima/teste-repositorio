<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupPadrao>
	<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/popupServicos.do">	
		<c:set var="nomeBox" scope="application"  value="Serviços:"/>
		<jsp:include page="/templates/box_pre.jsp"/>
			<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" >
				<thead>
					<tr>
						<th width="50%" class="dynamic_width">Grupo/Grupo de Serviços</th>
						<th width="50%" class="dynamic_width">Nome do Serviço</th>
					</tr>
				</thead>
			</table>
			<div class="both-scroll" style="height:300px;">
			<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
				<tbody>
					<thead style="visibility:hidden;">
						<tr>
							<th width="50%" class="dynamic_width">Grupo/Grupo de Serviços</th>
							<th width="50%" class="dynamic_width">Nome do Serviço</th>
						</tr>
					</thead>
					
				<%--<netui-data:repeater dataSource="servicos">
							<netui-data:repeaterItem>
								
								<c:if test="${container.item.listaServico != null}">
									<netui-data:repeater dataSource="container.item.listaServico.servicoList">
										<netui-data:repeaterItem>
											<tr>
												<td style="text-align: left;">${container.container.item.nmCategoria}</td>
												<td style="text-align: left;">${container.item.nmComercial}</td>
											</tr>
										</netui-data:repeaterItem>
									</netui-data:repeater>
								</c:if>
							</netui-data:repeaterItem>
					</netui-data:repeater> --%>
			
				<display:table name="servicos" id="servicosTO">
					<c:if test="${servicosTO.listaServico != null}">
]						<display:table name="listaServico.servicoList" id="servicoListTO">
							<tr>
								<td style="text-align: left;">${servicoListTO.nmCategoria}</td>
								<td style="text-align: left;">${servicoListTO.nmComercial}</td>
							</tr>
						</display:table>
					</c:if>
				</display:table>
			
				</tbody>
			</table>
			</div>
		<jsp:include page="/templates/box_pos.jsp"/>
		<br/>
	</html:form>
</catalogo:popupPadrao>			
