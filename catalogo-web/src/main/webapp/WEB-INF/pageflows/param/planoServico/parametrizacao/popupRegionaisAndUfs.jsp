<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao>
	<div name="conteudo">
		<div id="popup_selecionar_regional">
		<c:set var="nomeBox" scope="application"  value="Regionais E UFs: ${nome_regional}"/>
		<jsp:include page="/templates/box_pre.jsp"/>		
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/listarRegionais.do" onsubmit="false">
			<table width="100%">
				<tr>
					<td valign="top" width="50%">
						<div style="height:250px" class="vertical-scroll">							
								<table id="id_tab_regionais" class="tabConteudoGeral" width="100%">
										<thead>
											<tr>
												<td colspan="2" align="center" style="padding-bottom: 10px"><b>REGIONAIS</b></td>
											</tr>
											<tr>
												<td><input id="id_regionais_todos" type="checkbox" value="Todas" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkbox'), this.checked);selecionarTodosCheckboxUfs($('id_form_ufs').up('table').select('.checkboxUfs'), this.checked);" class="semBorda"/></td>
												<td><b>Todas</b></td>
											</tr>
										</thead>
								<tbody>								
								<logic:iterate id="regionaisItem" property="listaRegionalUf" name="servicoForm">
									<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
										<td width=10px">
										 	<html:checkbox property="idRegional" onclick="selecionarChavePopup('hidden_regionais', this, 'uf_regional_',  null, null);mostrarValoresDaChaveNoPopup('div_valores', 'uf_regional_', '${regionaisItem.idRegional}');" styleId="ufs_cb_${regionaisItem.idRegional}"
											value="${regionaisItem.idRegional}" styleClass="semBorda checkbox"/>
										</td>
										<td width="100px" nowrap="nowrap">
											<label for="ufs_cb_${regionaisItem.idRegional}" onclick="if($F('select_plataforma')!=1){mostrarValoresDaChaveNoPopup('div_valores', 'uf_regional_', '${regionaisItem.idRegional}');}return false;">${regionaisItem.nmRegional}</label>
										</td>
									</tr>
								</logic:iterate>
								</tbody>
								</table>
					</td>
					<td valign="top" width="50%">
						 <div id="div_valores" class="fleft vertical-scroll" style="height:280px;width:100%;">
						 <logic:iterate id="regional" property="listaRegionalUf" name="servicoForm">
							<table id="uf_regional_${regional.idRegional}" style="display:none;" class="tabConteudoGeral" width="200px" >
								<tr><td class="titulo word-wrap" colspan="2">UFs PARA: ${regional.nmRegional}</td></tr>
									<c:if test="${regional != null}">									
										<c:forEach items="${regional.listaUF}" var="sublista" >
											<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
												<td width=10px">												
													<html:multibox property="idRegional" onclick="selecionarValorDaChavePopup('hidden_regionais', '${regional.idRegional}', this);" styleClass="semBorda checkbox checkboxUfs" value="${sublista.idUf}" styleId="uf_regional_cb_${sublista.idUf}">
														<c:out value="${sublista.idUf}"/>
													</html:multibox>
													<%-- <netui:checkBoxOption onClick="selecionarValorDaChavePopup('hidden_regionais', ${regional.idRegional}, this);" tagId="uf_regional_cb_${container.item.idUf}" value="${container.item.idUf}" styleClass="semBorda checkbox checkboxUfs"/> --%>
												</td>
												<td width="90%" class="word-wrap">
													<label for="ddds_uf_cb_${sublista.idUf}">${sublista.nmUf}</label>
												</td>
											</tr>
										</c:forEach>
									</c:if>
							</table>
							</logic:iterate>										
						</div> 
					</td>
				</tr>					
			</table>
		</html:form>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
		<div class="botao">
			<html:button styleClass="btOk" property="bt_ok" onclick="fecharPopup('popup1');" value="OK" bundle="messages" titleKey="catalogo.popupSelecionarRegionais.OK"/>
		</div>
	</div>
    </div>
</catalogo:popupPadrao>
