<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<catalogo:popupPadrao>
		<input id="larguraPopup" value="700" class="hide"/>
		<input id="alturaPopup" value="320" class="hide"/>
		<div id="popup_nova_categoria" style="width: 100%">
			<c:set var="nomeBox" scope="application" value="Visualização de Serviços" />
			<c:set var="popupServ" value="Visualização de Serviços" scope="request" />
			<jsp:include page="/templates/box_pre.jsp"/>
				<div id="lista_servicos_alterado" style="width: 99%">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/validarSicronizacaoServicos.do"  target="target_download" style="padding: 0px; margin: 0px" >
							<div style="width:100%; height: 320px" id="lista_servicos">
								<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
									<thead>
										<tr>
											<th rowspan="2" class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
											<th rowspan="2" class="sortable" nowrap="nowrap"><label title="Alterações efetuadas no Legado que impactaram o Catálogo">Nome<br>T&eacute;cnico</label></th>
											<th rowspan="2" class="sortable" nowrap="nowrap"><label title="Alterações efetuadas no Legado que impactaram o Catálogo">Nome Comercial<br>do Servi&ccedil;o</label></th>
											<th colspan="2" class="throwspan"><label title="Alterações efetuadas no Legado que impactaram o Catálogo">Quant. Ativa&ccedil;&atilde;o</label></th>
											<th rowspan="2"><label title="Alterações efetuadas no Legado que impactaram o Catálogo">Disp</label></th>
										</tr>
										<tr>
											<th><label title="Alterações efetuadas no Legado que impactaram o Catálogo">Min</label></th>
											<th><label title="Alterações efetuadas no Legado que impactaram o Catálogo">Max</label></th>
										</tr>
									</thead>
								</table>
							<div class="both-scroll" style="height: 280px">
									<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="TabelaRelacionamentosRecentes">
										<thead>
											<tr>
												<th rowspan="2" class="center" width="10px"><input type="checkbox" name="modelo" class="semBorda"  onclick="selectTodosCheckbox($(this).form, '.checkbox_modelo', this.checked);"/></th>
												<th rowspan="2" class="sortable" nowrap="nowrap">Nome<br>T&eacute;cnico</th>
												<th rowspan="2" class="sortable" nowrap="nowrap">Nome Comercial<br>do Servi&ccedil;o</th>
												<th colspan="2" class="throwspan">Quant. Ativa&ccedil;&atilde;o</th>
												<th rowspan="2">Disp</th>
											</tr>
											<tr>
												<th>Min</th>
												<th>Max</th>	
											</tr>
										</thead>
										<tbody>
											<c:forEach var="servicosListTO" items="${servicosList}">
												<c:set value="true" var="firstPass"/>
												<tr>
													<td class="center">
														<html:multibox property="idServico" value="${servicosListTO.idServico}" styleClass="semBorda belongsToForm checkbox_modelo" labelStyleClass="hide" />
													</td>
													<td>
														<c:if test="${firstPass == true}">
															${servicosListTO.nmTecnico}
														</c:if>
													</td>
													<td>${servicosListTO.nmComercial}</td>
													<td>${servicosListTO.qtdeMinAtivacaoCatalogo}</td>
													<td>${servicosListTO.qtdeMaxAtivacaoCatalogo}</td>
													<td class="center">
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
											</c:forEach>
										</tbody>
									</table>
							</div>
						</div>
						<div class="botao" style="margin: 0px" >
							<html:button property="bt_submit" onClick="if(checkSelecaoServicosPopup($('lista_servicos_alterado').select('input.belongsToForm').collect(function(n){if(n.checked)return n; else return null;}))){return false;};send(this, 'right_section', null, 'popup_nova_categoria', null, null);fecharPopup('popup1');return false" styleClass="btOk" value="OK" bundle="messages" titleKey="catalogo.popupValidacaoServicos.confirmar"/>
						</div>
					</html:form>
				</div>
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupPadrao>
