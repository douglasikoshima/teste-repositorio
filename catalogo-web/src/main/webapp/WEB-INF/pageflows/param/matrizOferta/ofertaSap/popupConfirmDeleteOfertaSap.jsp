<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<catalogo:popupConfirmacao>
	<input id="larguraPopup" value="500px" class="hide"/>
	<input id="alturaPopup" value="150px" class="hide"/>
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			Ao excluir uma Oferta SAP, sua composição será excluída automaticamente. Deseja prosseguir?
			<br clear="all" /><br clear="all" /><b>
			Oferta SAP a ser exclu&iacute;da: ${dscOfertaSap}
			<br clear="all" /><br clear="all" /><br clear="all" />
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/deleteOfertaSap.do">
				<html:hidden property="idOfertaSap" value="${idOfertaSap}"/>
				<html:button bundle="messages" property="botao_sim" styleClass="btNavegacao74" onclick="send(this, null, null, 'right_section');fecharPopup('popup1');return false;" value="Sim" titleKey="catalogo.popupAlterarModelo.Sim"/>
			</html:form>
			&nbsp;
			<html:button property="btn_nao" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Não"/>
		</div>
</catalogo:popupConfirmacao>