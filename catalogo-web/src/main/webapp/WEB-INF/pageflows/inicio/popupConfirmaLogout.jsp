<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupConfirmacao>
		<br/>
		<br/>
		<input type="hidden" id="alturaPopup" value="150"/>
		<input type="hidden" id="larguraPopup" value="400"/>		
		<br clear="all"/>		
		<strong>Deseja realmente sair do sistema?</strong><br/><br/>	
		<br clear="all"/>
		<html:button bundle="messages" property="btn_sim" onclick="location.href='/catalogo/br/com/vivo/catalogoPRS/pageflows/inicio/doLogout.do'" value="Sim" styleClass="btNavegacao74" titleKey="catalogo.popupConfirmaLogout.Sim" />		 	
		&nbsp;
		<html:button bundle="messages" property="btn_nao" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Não" titleKey="catalogo.popupConfirmaLogout.Nao"/>		
		<br/><br/>
</catalogo:popupConfirmacao>