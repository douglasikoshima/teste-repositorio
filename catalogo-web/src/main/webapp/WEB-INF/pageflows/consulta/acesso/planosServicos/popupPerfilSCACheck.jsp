<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao>
	<div id="popup_lista_perfil" style="width: 100%">
		<c:set var="nomeBox" scope="application" value="Perfil" />
		<jsp:include page="/templates/box_pre.jsp" />

		<div align="left" class="vertical-scroll" style="height: 300px">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/listarPerfilSCA.do">
				<table class="tabConteudoGeral" width="100%">
					<tr>
						<td>
							<html:checkbox property="chkTodos" styleId="chkTodos" styleClass="semBorda" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkbox'), this.checked);" />							
						</td>
					    <td><b>Todas</b></td>
					</tr>
					
					
					
					<logic:iterate id="perfilSCA" property="arrayPerfil" name="consultaAcessoPlanosServicosForm">
						<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
							<td width="10px">
							<html:multibox bundle="messages" property="perfilSelecionados" styleId="checkbox_perfil_${perfilSCA.idPerfilSCA}" value="${perfilSCA.idPerfilSCA}" onClick="selecionaCheckBoxPopup('hidden_perfilSCACheck', this);" styleClass="semBorda checkbox">													
		                        <bean:write bundle="messages"  name="perfilSCA"  property="idPerfilSCA" />
							</html:multibox>
							</td>
							<td width="100px" nowrap="nowrap">
							    <bean:write bundle="messages" name="perfilSCA" property="nmPerfilSCA"/>
							</td>
						</tr>
					</logic:iterate>
					
					
					
					
				</table>
			</html:form>
		</div>
		<jsp:include page="/templates/box_pos.jsp" />
		<div style="margin: 5px;" class="botao">
			<html:button bundle="messages" property="btOk" styleId="btOk" styleClass="btOk" onclick="fecharPopup('popup1');" value="OK" title="Ok" />
		</div>
	</div>
</catalogo:popupPadrao>