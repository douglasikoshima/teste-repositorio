<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
	<netui-temp:section name="conteudo">
		<div id="popup_selecionar_tecnologias" style="width: 100%">
			<c:set var="nomeBox" scope="application"  value="Tecnologias"/>
			<jsp:include page="/templates/box_pre.jsp"/>
				<div align="left vertical-scroll">
					<div style="padding: 10px 0px 0xp 10px; float: left; width: 100%; height: 280px; overflow-y: auto;">
						<form>
							<table class="tabConteudoGeral" width="100%">
								<tr>
									<td><input type="checkbox" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkbox'), this.checked);" class="semBorda"/></td>
								    <td><b>Todas</b></td>
								</tr>
								<netui:checkBoxGroup dataSource="pageFlow.tecnologiasSelecionadas" optionsDataSource="${tecnologias}" repeater="true">
								<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
									<td width=10px">
										<netui:checkBoxOption onClick="selecionaCheckBoxPopup('hidden_tecnologias', this)" tagId="tecnologias_cb_${container.item.idTecnologia}" value="${container.item.idTecnologia}" styleClass="semBorda checkbox"/>
									</td>
									<td nowrap="nowrap">
										<label for="tecnologias_cb_${container.item.idTecnologia}">${container.item.nmTecnologia}</label>
									</td>
								</tr>
								</netui:checkBoxGroup>
							</table>
						</form>
					</div>	
				</div>
			<jsp:include page="/templates/box_pos.jsp"/>
			<div style="margin: 5px;" class="botao">
				<input class="btOk" type="button" onclick="fecharPopup('popup1');" value="OK" alt="${bundle.default['catalogo.variavel.modelo.Ok']}" title="${bundle.default['catalogo.variavel.modelo.Ok']}" />
			</div>
		</div>
	</netui-temp:section>
</netui-temp:template>