<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles-el.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="popupPadrao">
		<c:if test="${close != null && !close}">
			<div style="height:22px;width:100%;">
			</div>
		</c:if>
		<c:if test="${close == null || close}">
			<div class="popup_padrao_box_top_right_close" onclick="fecharPopup($(this).up(1).id);">
			</div>
		</c:if>
		<br clear="all"/>
		<div class="popup_box_middle_center">
			<div class="popup_box_middle_center_conteudo" align="center">
				<div  style="width:90%;padding-top:5px;margin-bottom:5px; background-color: white; ">
					<div id="div_erros_popup">
					</div>
			   		<jsp:doBody/>
			</div>
		</div>
	</div>
