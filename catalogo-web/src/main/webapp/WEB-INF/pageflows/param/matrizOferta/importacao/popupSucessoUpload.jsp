<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupConfirmacao>
		<input id="larguraPopup" value="500px" class="hide"/>
		<input id="alturaPopup" value="150px" class="hide"/>
		<div style="width: 99%; height: 150px; padding: 30px 0px 0px 0px" class="box">
			<b><c:out value="${return}"/></b>
		<br clear="all" />
		<div style="width: 99%; margin: 40px 0px 5px 0px; text-align: center;">
			<input type="button" onclick="fecharPopup('popup1');$('link_parametrizacao_matrizOferta_importar_arquivo').onclick();" class="btNavegacao74" value="OK" title="Ok"/>
		</div>		
		</div>
</catalogo:popupConfirmacao>