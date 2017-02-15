<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao>


	<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/abrirpopupTecnologiaModelo.do">
		<c:set var="nomeBox" scope="application"  value="Tecnologias"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div align="left">
			
			<table class="tabConteudoGeral" width="100%">
				
			<logic:iterate id="tecnologias" property="tecnologiasEncontradas" name="ConsultaModeloForm">
				<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
					<td width=10px">
						<html:multibox property="tecnologiasSelecionadas" onClick="selecionaCheckBoxPopup('hiddenTecnologias', this);" styleClass="semBorda checkbox" value="${tecnologias.idTecnologia}" styleId="tecnologias_cb_${tecnologias.idTecnologia}">
	                        <bean:write bundle="messages" name="tecnologias"  property="idTecnologia" />
						</html:multibox>
					</td>
					<td width="100px" nowrap="nowrap">
						<label for="tecnologias_cb_${tecnologias.idTecnologia}">${tecnologias.nmTecnologia}</label>
					</td>
				</tr>
			</logic:iterate>
			
			</table>
			<div class="botao">
				<html:button bundle="messages" property="btOk" styleClass="btOk" onclick="fecharPopup('popup1');" value="OK" titleKey="catalogo.popupTecnologiaModelo.OK"/>
			</div>
			
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
		
	</html:form>


</catalogo:popupPadrao>