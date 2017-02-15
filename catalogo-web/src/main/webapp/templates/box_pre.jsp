<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="box" style="width: 100%">
			<div class="box_top" style="100%">
				<c:if test="${popupServ == 'Visualização de Serviços'}">
					<div class="box_top_center">
						<label title="Altera&ccedil;&otilde;es efetuadas no Sistema Origem que impactaram o Cat&aacute;logo"><c:out value="${popupServ}" /></label>
					</div>
				</c:if>
				<c:if test="${popupServ == null}">
					<div class="box_top_center">
						<c:out value="${nomeBox}" />
					</div>
				</c:if>
				<div class="box_top_left">
				</div>
				<div class="box_top_right">
				</div>
			</div>
			<div class="box_middle">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="box_middle_left">
					</td>
					<td>
						<div class="box_middle_center">
							<div class="box_middle_center_conteudo">
							