<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<catalogo:popupPadrao>
	<html:text property="larguraPopup" value="500px" styleId="larguraPopup" styleClass="hide" ></html:text>
	<html:text property="alturaPopup" value="150px" styleId="alturaPopup" styleClass="hide" ></html:text>
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			Existem itens de matriz de oferta associados a Oferta SAP: <b>${dscOfertaSap}</b>. Clique em Alterar caso deseja inativá-la.
			<br clear="all" /><br clear="all" /><b>
			<br clear="all" /><br clear="all" /><br clear="all" />
			<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Ok" />
		</div>
</catalogo:popupPadrao>
