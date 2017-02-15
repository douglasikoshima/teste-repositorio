<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
			
	<netui-temp:section name="conteudo">
		<c:set var="nomeBox" scope="application"  value="Tecnologias"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div align="left" class="vertical-scroll" style="height:300px">
			<form>
			<table class="tabConteudoGeral" width="100%">
			<netui:checkBoxGroup dataSource="pageFlow.tecnologiasSelecionadas" optionsDataSource="${tecnologias}" repeater="true">
				<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
					<td width=10px">
						<netui:checkBoxOption onClick="selecionaCheckBoxPopup('hiddenTecnologias', this)" tagId="tecnologias_cb_${container.item.idTecnologia}" value="${container.item.idTecnologia}" styleClass="semBorda"/>
					</td>
					<td width="100px" nowrap="nowrap">
						<label for="tecnologias_cb_${container.item.idTecnologia}">${container.item.nmTecnologia}</label>
					</td>
				</tr>
			</netui:checkBoxGroup>
			</table>
			<div class="botao">
				<input class="btOk" type="button" onclick="fecharPopup('popup1');" value="OK" title="${bundle.default['catalogo.popupSelecionarTecnologias.OK']}"/>
			</div>
			</form>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
	</netui-temp:section>

</netui-temp:template>