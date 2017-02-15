<%@ tag pageEncoding='UTF-8' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="title" required="true" type="java.lang.String" %>
<%@attribute name="close" required="true" type="java.lang.Boolean" %>
<%@attribute name="width" required="false" type="java.lang.String" %>
<%@attribute name="left" required="false" type="java.lang.String" %>
<%@attribute name="height" required="false" type="java.lang.String" %>

<div id="templatePopUpPadrao" class="popupPadrao" style="width: ${width};">
	<c:if test="${!close}">
		<div style="height:22px;width:100%;"></div>
	</c:if>
	<c:if test="${close}">
		<div class="popup_padrao_box_top_right_close" onclick="fecharPopup($(this).up(1).id);"></div>
	</c:if>
	<br clear="all"/>
	<div class="popup_box_middle_center">
		<div class="popup_box_middle_center_conteudo" align="center">
			<div style="width:90%;padding-top:5px;margin-bottom:5px; background-color: white;" >
				<div id="div_erros_popup"></div>
				<div id="div_erros_popup2"></div>
				<div class="box" style="width: 100%">
					<div class="box_top" style="100%">
						<div class="box_top_center">${title}</div>
						<div class="box_top_left"></div>
						<div class="box_top_right"></div>
					</div>
					<div class="box_middle">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td class="box_middle_left"></td>
								<td>
									<div class="box_middle_center" id="boxMiddleCenter">
										<div class="box_middle_center_conteudo">
											<jsp:doBody />
										</div>
									</div>
								</td>
								<td class="box_middle_right"></td>
							</tr>
						</table>
					</div>
					<div class="box_bottom">
						<div class="box_bottom_center"></div>
						<div class="box_bottom_left"></div>
						<div class="box_bottom_right"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="hide">
	<c:if test="${not (empty width)}">
		<img src="/catalogo/static_server/img/transparent.gif" class="hide"
			onload="$('templatePopUpPadrao').up('div').style.width = '${width}';$('templatePopUpPadrao').up('div').previous('iframe').style.width = '${width}';"/>
	</c:if>
	<c:if test="${not (empty left)}">
		<img src="/catalogo/static_server/img/transparent.gif" class="hide"
			onload="$('templatePopUpPadrao').up('div').style.left = '${left}';$('templatePopUpPadrao').up('div').previous('iframe').style.left = '${left}';"/>
	</c:if>
	<c:if test="${not (empty height)}">
		<img src="/catalogo/static_server/img/transparent.gif" class="hide"
			onload="$('boxMiddleCenter').style.height = '${height}';$('templatePopUpPadrao').up('div').previous('iframe').style.height = '${height}';"/>
	</c:if>
</div>