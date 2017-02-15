<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<display:table name="categorizacaoScoreList" id="categorizacaoAnaliseCreditoPorRegionalTO" pagesize= "15" cellspacing="0" cellpadding="0" requestURIcontext="false" requestURI="searchProduto.do" style="width: 99%;" class="tabela-padrao-new tablesorter table_body">
	<display:column title="Descrição" property="nome" style="width:250px" />
	<display:column title="Valor" property="valor" />
	<c:if test="${canalConfiguravelPorRegional}"> 
		<display:column title="Regional" property="regionalTO.nmRegional"/>
	</c:if>
	<c:forEach items="${analiseCreditoList}" var="score">
		<c:set var="key" value="id_${categorizacaoAnaliseCreditoPorRegionalTO.idCabecalhoAnaliseCredito}_${categorizacaoAnaliseCreditoPorRegionalTO.idCategoriaScore}_${score.idAnaliseCredito}_${canalConfiguravelPorRegional?categorizacaoAnaliseCreditoPorRegionalTO.regionalTO.idRegional:0}"/>
		<c:set var="keySelected" value="${categorizacaoAnaliseCreditoPorRegionalTO.scoresConfigurados[key]}"/>
		<c:if test="${tpClassificacao != '2'}">
			<display:column title="${score.cdCor}" > <input id="${key}" class="clConfigScore semBorda checkbox" type="checkbox" <c:if test="${keySelected}">checked</c:if> /> </display:column>
		</c:if>
		<c:if test="${tpClassificacao == '2'}">
			<display:column title="${score.cdCor}" > <input id="${key}" class="clConfigScore semBorda checkbox" type="checkbox" <c:if test="${!keySelected}">checked</c:if> /> </display:column>
		</c:if>
	</c:forEach>
	<display:column title="Selecionar Todos" style="width:40px;text-align:center"> <input class="clConfigScoreCheckAll semBorda checkbox" type="checkbox" onclick="checkAll(this)" </display:column>
</display:table>
<script type="text/javascript"> atualizarSelecionarTodos(); </script>