<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupConfirmacao>
	<br/>
	<br/>
	<input id="larguraPopup" value="500px" class="hide"/>
	<input id="alturaPopup" value="150px" class="hide"/>
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			O serviço '${servicoIncompativel1 }' não pode ser associado à Oferta de Serviço porque existe uma incompatibilidade com o serviço '${servicoIncompativel2}'
			<br clear="all" /><br clear="all" /><b>
			<br clear="all" /><br clear="all" /><br clear="all" />
			<input type="button" onclick="fecharPopup('popup2');" class="btNavegacao74" value="Ok" />
		</div>	
	<br/><br/>
</catalogo:popupConfirmacao>