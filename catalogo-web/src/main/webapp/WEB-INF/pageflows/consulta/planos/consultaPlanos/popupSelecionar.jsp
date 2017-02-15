<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div align="left">
			<table class="tabConteudoGeral" width="100%">
				<tr>
					<td><input class="semBorda" type="checkbox" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkbox'), this.checked);" /></td>
					<td></td>
				</tr>
			
				<logic:iterate id="listaTO" property="lista" name="consultaPlanosForm">
					<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
						<td width=10px">
							<html:multibox property="selecionados" onClick="selecionaCheckBoxPopup('${hiddenInput}', this)" styleClass="semBorda checkbox" value="${listaTO.id}" styleId="entidade_cb_${listaTO.id}"/>
						</td>
						<td width="100px" nowrap="nowrap">
							<label for="entidade_cb_${listaTO.id}">${listaTO.nome}</label>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
		<div class="botao">
			<html:button bundle="messages" property="btOk" styleClass="btOk" onclick="fecharPopup('popup1');" value="OK" titleKey="catalogo.popupSelecionar.OK"/>
		</div>
</catalogo:popupPadrao>