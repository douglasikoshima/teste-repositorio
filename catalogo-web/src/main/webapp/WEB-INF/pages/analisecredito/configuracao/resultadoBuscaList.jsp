<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<li class="ui-state-default ui-state-disabled" value="1" ><div class="ui-state-default" style="width: 80%;">Descrição</div><div class="ui-state-default" style="width: 20%;">Valor</div></li>
<c:forEach items="${registroList}" var="registroTO">
<fmt:setLocale value="pt-BR" />
<li class="ui-state-default" value="${registroTO.idCategoriaScore}"><div class="ui-state-default" style="width: 80%;">${registroTO.nome}</div><div class="ui-state-default" style="width: 20%;"><fmt:formatNumber value="${registroTO.valor}" minFractionDigits="2" type="currency"/></div></li>
</c:forEach>
<c:if test="${registroList == null}">
	<li class="ui-state-default ui-state-disabled" value="1" ><div class="ui-state-default" style="width: 95%;">Não foram encontrado(s) iten(s) para esse(s) filtro(s) de pesquisa</div></li>
</c:if>
