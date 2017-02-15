<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="idCategoria">
	<option value="">-- Selecione --</option>
	<c:forEach items="${categoriaTOList}" var="categoriaTO">
		<option value="${categoriaTO.idCategoria}">${categoriaTO.nmCategoria}</option>
	</c:forEach>
</select>