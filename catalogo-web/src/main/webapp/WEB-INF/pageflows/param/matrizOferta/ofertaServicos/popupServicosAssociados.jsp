<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupPadrao>
		<div id="popup_servicos_associados">
			<input id="larguraPopup" value="500px" class="hide"/>
			<input id="alturaPopup" value="200px" class="hide"/>
			<c:set var="nomeBox" scope="application"  value="Serviços Associados"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div>
				<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
					<thead>
						<tr>
							<th class="sortable">Nome do Servi&ccedil;o:</th>
						</tr>
					</thead>
				</table>			
				<div style="height:160px;" class="both-scroll">
				            <c:if test="${ofertaServicosMatrizOfertaForm.servicos ne null}">
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body">
								<thead>
									<tr>
										<th class="sortable">Nome do Servi&ccedil;o:</th>
									</tr>
								</thead>
								<tbody>
																		
									<logic:iterate id="servico" property="servicos" name="ofertaServicosMatrizOfertaForm">
										<tr>
											<td style="text-align: left;">${servico.nmComercial}</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
							</c:if>							
							<c:if test="${ofertaServicosMatrizOfertaForm.servicos == null}">	
							   <br><span align='center'>Nenhum servi&ccedil;o associado.</span>
							</c:if>								
				</div>
			</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupPadrao>