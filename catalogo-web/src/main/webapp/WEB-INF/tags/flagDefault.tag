<%@ tag pageEncoding='UTF-8' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="test" required="true" type="java.lang.String" %>

<c:choose>
	<c:when test="${test}">
		<img alt="Sim" src="/catalogo/static_server/img/bullets/icon-available.png"/>
	</c:when>
	<c:otherwise>
		<img alt="Não" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
	</c:otherwise>
</c:choose>