<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao >
		<div id="popup_lista_perfil" style="width: 100%">
			<c:set var="nomeBox" scope="application"  value="Perfil"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			
			<div align="left" class="vertical-scroll" style="height:300px">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaPerfilSCAPerfilPlanoPerfilServico.do">
					<table class="tabConteudoGeral" width="100%">
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						
						<logic:iterate id="perfilSCA" property="arrayPerfil" name="parametrizacaoAcessoForm"> 						
							<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
								<td width=10px">
									<html:radio property="nmPerfilSCA" styleId="perfil_cb${perfilSCA.idPerfilSCA}" value="${perfilSCA.idPerfilSCA}" onClick="selecionaRadioGroupPopup('hidden_lista_perfil_servico_perfil', this)"  styleClass="semBorda checkbox"/>									
								</td>
								<td width="100px" nowrap="nowrap">
									<bean:write bundle="messages" name="perfilSCA" property="nmPerfilSCA"/>						
								</td>
							</tr>
						</logic:iterate> 
					</table>
				</html:form>
			</div>	
				
			<jsp:include page="/templates/box_pos.jsp"/>
			<div style="margin: 5px;" class="botao">
				<html:button property="btn_ok" styleId="btn_ok" styleClass="btOk" onclick="fecharPopup('popup1');" value="OK" title="Ok"/>
			</div>
		</div>
	</catalogo:popupPadrao>