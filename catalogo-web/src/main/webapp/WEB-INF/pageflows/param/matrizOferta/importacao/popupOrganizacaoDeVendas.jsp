<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupPadrao>
	<div id="popup_selecionar_regional">
		<c:choose>
			<c:when test="${listaOrganizacaoVenda == null}">
				<div style="width: 100%; float: left;">
					<div style="background-color: white; position: relative; width: 100%" id="divErros">
						<div style="position: relative;" class="box">
							<div class="box_simples_header">
								<div class="box_simples_top_center"></div>
								<div class="box_simples_top_left"></div>
								<div class="box_simples_top_right"></div>
							</div>
							<div style="position: relative;" class="box_middle"> 
								<table cellspacing="0" cellpadding="0" border="0" width="100%">
									<tbody>
										<tr><td class="box_middle_left" rowspan="2"></td>
											<td>
												<div class="box_middle_center">
													<div class="box_middle_center_conteudo" id="conteudo_divErros">
														<ul id="popup_erros_ul">
															<li class="foo"><c:out value="${mensagem}"/></li>
														</ul>
													</div>
												</div>
											</td>
											<td class="box_middle_right" rowspan="2"></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="box_bottom">
								<div class="box_bottom_center">
								</div>
								<div class="box_bottom_left">
								</div>
								<div class="box_bottom_right">
								</div>
							</div>
						</div>
					</div>
				</div>
				<c:set var="nomeBox" scope="application"  value="Organizações de Vendas/DDD:"/>
				<jsp:include page="/templates/box_pre.jsp"/>
				<div id="div_ddd_organizacao" style="width: 220px; height: 150px">&nbsp;</div>
			</c:when>
			<c:otherwise>
				<c:set var="nomeBox" scope="application"  value="Organizações de Vendas/DDD:"/>
				<jsp:include page="/templates/box_pre.jsp"/>
				<div style="padding: 10px 0px 0xp 10px; float: left; width: 290px; height: 250px; overflow-y: auto;">
					<form>
						<table id="divOrganizacaoVendas" border="0" class="tabConteudoGeral" style="width: 99%">
							<tr>
								<td class="word-wrap" style="padding: 0px 0px 10px 25px" colspan="2"><b>Organiza&ccedil;&otilde;es de Vendas</b></td>
							</tr>
							<tr>
								<td><input id="id_organizacaoVendas_todos" type="checkbox" value="Todas" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkbox'), this.checked);" class="semBorda"/></td>
							    <td><b>Todas</b></td>
							</tr>
							<netui:checkBoxGroup dataSource="pageFlow.organizacoesSelecionadas" optionsDataSource="${listaOrganizacaoVenda}" repeater="true">
								<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
									<td style="width: 10%">
										<netui:checkBoxOption onClick="verificarSelecaoAll(this, 'id_organizacaoVendas_todos');selecionarChavePopup('hidden_org_vendas', this, 'ddd_orgaizacao_vendas_');mostrarValoresDaChaveNoPopup('div_ddd_organizacao', 'ddd_orgaizacao_vendas_', '${container.item.idOrganizacaoVendas}');selecionarTodosCheckboxUfs($('id_form_ddd').up('table').select('.ddd_${container.item.idOrganizacaoVendas}'), this.checked)" tagId="ddds_cb_${container.item.idOrganizacaoVendas}" value="${container.item.idOrganizacaoVendas}" styleClass="semBorda checkbox"/>
									</td>
									<td nowrap="nowrap" style="width: 89%">
										<label onclick="mostrarValoresDaChaveNoPopup('div_ddd_organizacao', 'ddd_orgaizacao_vendas_', '${container.item.idOrganizacaoVendas}')">${container.item.sgOrganizacaoVendas}</label>
									</td>
								</tr>
							</netui:checkBoxGroup>
						</table>
					</form>
				</div>
				<div id="div_ddd_organizacao" style="padding: 10px 0px 0px 10px; width: 220px; height: 250px; overflow-y: auto;">
					<div id="id_form_ddd">
						<c:forEach items="${listaOrganizacaoVenda}" var="organizacao">
							<table border="0" id="ddd_orgaizacao_vendas_${organizacao.idOrganizacaoVendas}" style="display: none" class="tabConteudoGeral" width="200px" >
								<tr>
									<td class="word-wrap" style="padding: 0px 0px 10px 25px" colspan="2"><b>DDD PARA: ${organizacao.sgOrganizacaoVendas}</b></td>
								</tr>
								<tr>
									<td><input id="id_ddd_${organizacao.idOrganizacaoVendas}" type="checkbox" value="N" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkDddAll'), this.checked);selecionarValorDaChavePopup('hidden_org_vendas', ${organizacao.idOrganizacaoVendas}, this);" class="semBorda checkbox ddd_${organizacao.idOrganizacaoVendas}"/></td>
									<td><b>Todos</b></td>
								</tr>
								<c:if test="${organizacao != null }">
									<netui:checkBoxGroup dataSource="pageFlow.dddsSelecionados" optionsDataSource="${organizacao.listaDDD.dddList}" repeater="true">
										<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
											<td width="10%" align="center">
												<c:if test="${container.item.codigoArea != null}" >
													<netui:checkBoxOption onClick="verificarSelecaoAll(this, 'id_ddd_${organizacao.idOrganizacaoVendas}');selecionarValorDaChavePopup('hidden_org_vendas', ${organizacao.idOrganizacaoVendas}, this);" tagId="ddd_orgaizacao_vendas_cb_${container.item.codigoArea}" value="${container.item.codigoArea}" styleClass="semBorda checkbox ddd_${organizacao.idOrganizacaoVendas} checkDddAll"/>
												</c:if>
											</td>
											<td width="90%" class="word-wrap">
												<label for="ddds_uf_cb_${container.item.codigoArea}">${container.item.codigoArea}</label>
											</td>
										</tr>
									</netui:checkBoxGroup>
								</c:if>
							</table>
						</c:forEach>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<jsp:include page="/templates/box_pos.jsp"/>
		<div style="margin: 5px;" class="botao">
			<input class="btOk" type="button" onclick="fecharPopup('popup1');" value="OK" title="Ok"/>
		</div>
	</div>
</catalogo:popupConfirmacao>