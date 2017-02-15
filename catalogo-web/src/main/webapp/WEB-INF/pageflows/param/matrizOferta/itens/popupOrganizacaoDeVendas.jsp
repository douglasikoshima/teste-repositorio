<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>


<catalogo:popupPadrao >	
	<div id="popup_selecionar_regional">
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/buscarListaOrganizacaoDeVendas.do">		
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
							<logic:iterate id="listaOrgVendasValida" property="listOrganizacaoVendasValida" name="itensMatrizOfertaForm">
								<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
									<td style="width: 10%">
										<html:radio property="organizacoesSelecionadas" value="${listaOrgVendasValida.idOrganizacaoVendas}" styleId="ddds_cb_${listaOrgVendasValida.idOrganizacaoVendas}" onClick="selecionarChavePopupForRadio('hidden_org_vendas', this, 'ddd_orgaizacao_vendas_');mostrarValoresDaChaveNoPopup('div_ddd_organizacao', 'ddd_orgaizacao_vendas_', '${listaOrgVendasValida.idOrganizacaoVendas}')" styleClass="semBorda checkbox"/>																	
									</td>	
									<td nowrap="nowrap" style="width: 89%">
										<label onclick="selecionarChavePopupForRadio('hidden_org_vendas', $('ddds_cb_${listaOrgVendasValida.idOrganizacaoVendas}'), 'ddd_orgaizacao_vendas_');mostrarValoresDaChaveNoPopup('div_ddd_organizacao', 'ddd_orgaizacao_vendas_', '${listaOrgVendasValida.idOrganizacaoVendas}')">${listaOrgVendasValida.sgOrganizacaoVendas}</label>
									</td>							
								</tr>							
							</logic:iterate>
						</table>
					</form>
				</div>
				<div id="div_ddd_organizacao" style="padding: 10px 0px 0px 10px; width: 220px; height: 250px; overflow-y: auto;">
					<div id="id_form_ddd">
						<logic:iterate id="ddds" property="listOrganizacaoVendasValida" name="itensMatrizOfertaForm">												
							<table border="0" id="ddd_orgaizacao_vendas_${ddds.idOrganizacaoVendas}" style="display: none" class="tabConteudoGeral" width="200px" >
								<tr>
									<td class="word-wrap" style="padding: 0px 0px 10px 25px" colspan="2"><b>DDD PARA: ${ddds.sgOrganizacaoVendas}</b></td>
								</tr>
								<tr>
									<td>
										<input id="id_ddd_${ddds.idOrganizacaoVendas}" type="checkbox" value="" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkDddAll'), this.checked);selecionarValorDaChavePopup('hidden_org_vendas', ${ddds.idOrganizacaoVendas}, this);" class="semBorda checkbox ddd_${ddds.idOrganizacaoVendas}"/>
									</td>
									<td><b>Todos</b></td>
								</tr>
								<c:if test="${ddds != null }">
									<c:forEach items="${ddds.areaRegistroList}" var="sublista" >
										<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
											<td width="10%" align="center">
												<html:multibox property="dddsSelecionados" onClick="verificarSelecaoAll(this, 'id_ddd_${ddds.idOrganizacaoVendas}');selecionarValorDaChavePopup('hidden_org_vendas', ${ddds.idOrganizacaoVendas}, this);" styleId="ddd_orgaizacao_vendas_cb_${sublista.codigoArea}" value="${sublista.codigoArea}" styleClass="semBorda checkbox ddd_${ddds.idOrganizacaoVendas} checkDddAll">
													<c:out value="${sublista.codigoArea}"/>
												</html:multibox>											
											</td>
											<td width="90%" class="word-wrap">
												<label for="ddds_uf_cb_${sublista.codigoArea}">${sublista.codigoArea}</label>
											</td>
										</tr>									
									</c:forEach>
								</c:if>
							</table>
						</logic:iterate>		
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<jsp:include page="/templates/box_pos.jsp"/>
		<div style="margin: 5px;" class="botao">
			<html:button bundle="messages" styleClass="btOk" property="btn_ok" onclick="fecharPopup('popup1');" value="OK" altKey="catalogo.global.OK" titleKey="catalogo.global.OK"/>
		</div>
		</html:form>
	</div>
</catalogo:popupPadrao>