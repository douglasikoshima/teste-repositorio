<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao>
	<input id="larguraPopup" value="700" class="hide"/>
	<input id="alturaPopup" value="320" class="hide"/>
	<div id="popup_altera_planos" style="width: 100%">
		<c:set var="nomeBox" scope="application"  value="Visualização de Planos"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div id="lista_planos_alterado" style="width: 99%; height: 290px">
			<html:form action="">
					<div style="width:100%; height: 320px" id="lista_planos">
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
							<thead>
								<tr>
									<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
									<th class="sortable" nowrap="nowrap">Nome<br>T&eacute;cnico</th>
									<th class="sortable" nowrap="nowrap">Nome<br>Comercial do Plano</th>
									<th nowrap="nowrap">Quant.<br>Dependentes</th>
									<th>Disp</th>
								</tr>
							</thead>
						</table>
					<div class="both-scroll" style="height: 280px">
							<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="TabelaRelacionamentosRecentes">
								<thead>
								<tr>
									<th class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
									<th class="sortable" nowrap="nowrap">Nome<br>T&eacute;cnico</th>
									<th class="sortable" nowrap="nowrap">Nome<br>Comercial do Plano</th>
									<th nowrap="nowrap">Quant.<br>Dependentes</th>
									<th>Disp</th>
								</tr>
								</thead>
								<tbody>
									<c:forEach>
										<c:set value="true" var="firstPass"/>
										<tr>
											<td class="center">
												<html:multibox property="idPlano" value="${container.item.idPlano}" styleClass="semBorda belongsToForm checkbox_modelo" labelStyleClass="hide" />
											</td>
											<td>
												<c:if test="${firstPass == true}">
													${container.item.nmTecnico}
												</c:if>
											</td>
											<td>${container.item.nmComercial}</td>
											<td>${container.item.qtMaximaDependentes}</td>
											<td>
												<c:choose>
													<c:when test="${container.item.inDisponibilidadeCatalogo == 'S'}">
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
													</c:when>
													<c:otherwise>
														<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/redcross.gif"/>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										<c:set value="false" var="firstPass"/>
 									<c:forEach>
								</tbody>
							</table>
					</div>
				</div>
					<div class="botao" style="margin: 0px" >
						<html:button property="bt_submit" onClick="if(checkSelecaoPlanosPopup($('lista_planos_alterado').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){return false;};send(this, 'right_section', null, 'popup_altera_planos', null, null);fecharPopup('popup1');return false" styleClass="btOk" value="OK" bundle="messages" titleKey="catalogo.popupValidacaoPlanos.confirmar"/>
					</div>
			</html:form>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
	</div>
</catalogo:popupPadrao>
