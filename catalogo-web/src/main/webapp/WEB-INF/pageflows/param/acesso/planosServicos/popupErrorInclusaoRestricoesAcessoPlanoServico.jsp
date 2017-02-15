<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>


<catalogo:popupConfirmacao>
	<html:text property="larguraPopup" styleId="larguraPopup" value="500px" styleClass="hide" />
	<html:text property="alturaPopup" styleId="alturaPopup" value="150px" styleClass="hide"/>
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			Ocorreu um erro ao incluir a restri&ccedil;&atilde;o.
			<br clear="all" /><br clear="all" /><b>
			<br clear="all" /><br clear="all" /><br clear="all" />
			<html:button property="btn_fechar_popup" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Ok"/>
		</div>	
</catalogo:popupConfirmacao>
