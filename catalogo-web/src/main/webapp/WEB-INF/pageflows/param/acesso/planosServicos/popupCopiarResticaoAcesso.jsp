<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao >
		<div id="div_erro_copiar_restricao" style="position: relative"></div>
		<div id="popup_copiar_restricoes" style="width: 100%">
			<c:set var="nomeBox" scope="application"  value="Copiar Restrições de Acesso"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div align="left" style="height:150px">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/copiarRestricoesAcesso.do" styleId="form_copiar_resticoes_acesso">
					<br clear="all" /><br clear="all" />
					<br clear="all" /><br clear="all" />

					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">Perfil Origem:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select tabindex="1" styleClass="required" styleId="select_perfil_origem" property="idPerfilOrigemCopiarRestrAcesso" style="width:360px;">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="perfilTO" items="${listaPerfil}" >
								<html:option value="${perfilTO.idPerfilSCA}">${perfilTO.nmPerfilSCA}</html:option>							
							</c:forEach>
						</html:select>
					</div>
					
					<br clear="all" /><br clear="all" />
					<div class="fleft" style="padding-top: 5px">
						<div class="label-form-bold label_required" style="width:110px;">Perfil Destino:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select tabindex="2" styleClass="required" styleId="select_perfil_destino" style="width:360px;" property="idPerfilDestinoCopiarRestrAcesso">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="perfilTO" items="${listaPerfil}">
								<html:option value="${perfilTO.idPerfilSCA}">${perfilTO.nmPerfilSCA}</html:option>
							</c:forEach>
						</html:select>
					</div>
					<div style="height: 130px"></div>
					<br clear="all" /><br clear="all" /><br clear="all" />
					<div style="margin: 5px" class="botao">
						<html:button styleClass="btOk" property="btOk" onclick="if(send(this, 'resultado_pesquisa', null, 'div_erro_copiar_restricao')){return false}" value="OK" title="Ok"/>
					</div>
				</html:form>
			</div>	
			<jsp:include page="/templates/box_pos.jsp"/>
			<div style="margin: 5px; display: none;" class="botao">				
				<html:button styleClass="btOk" property="btOk" styleId="botao_fechar_popup_copiar_restricao" onclick="fecharPopup('popup1');$('resultado_pesquisa').hide();" value="OK" title="Ok"/>
			</div>
		</div>
</catalogo:popupPadrao>