<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form styleId="analiseScore" action="/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/pesquisar.do">
<div id="resTabela">
	<script type="text/javascript" src="/catalogo/static_server/js/analiseCreditoLinha.js"></script>
	<c:if test="${analiseCreditoLinhaTOList != null}">
		<display:table name="analiseCreditoLinhaTOList" id="analiseCreditoLinhaTO" pagesize="15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="ordenar.do" class="tabela-padrao-new tablesorter table_body">
			<display:column title="Nome da Oferta" sortable="true" headerClass="sortable" >
				<a href="#" style="float:left" id="tool" title="${analiseCreditoLinhaTO.info}">${analiseCreditoLinhaTO.cdOfertafixa} - ${analiseCreditoLinhaTO.dsOfertafixa}</a>
			</display:column>
			<c:forEach var="analiseCreditoTO" items="${analiseCreditoLinhaTO.analiseCreditoTOList}">
				<display:column title="${analiseCreditoTO.cdCor}">
					<input type="checkbox" vinculado="${analiseCreditoTO.flagScore}" name="checkboxScore" id="checkboxScore" value="${analiseCreditoLinhaTO.idOferta}|${analiseCreditoTO.idAnaliseCredito}|${analiseCreditoTO.priorizado};" ${analiseCreditoTO.flagScore ? "checked" : ""} class="checkbox checkboxScore ${analiseCreditoLinhaTO.idOferta}"/>
				</display:column>
			</c:forEach>
			<display:column title="Todos">
				<input type="checkbox" class="checkbox ${analiseCreditoLinhaTO.idOferta}" value="${analiseCreditoLinhaTO.priorizado}" id="todos" ${analiseCreditoLinhaTO.allChecked ? "checked" : ""}/> 
			</display:column>
		</display:table>
	</c:if>
	<!-- Fim if not null analiseCreditoLinhaTOList -->
    <c:if test="${not empty analiseCreditoLinhaTOList}">
		<div class="barra"></div>
		<cata:temPermissao acao="gravarAnaliseCreditoLinha">
			<div class="botao"><html:button property="btngravarAgrupador" styleId="btngravarAgrupador" onClick="gravarScore()" styleClass="btNavegacao74" value="Gravar" alt="Adicionar Agrupador" title=""/><span>&nbsp;</span>
			</div>
		</cata:temPermissao>    
    </c:if>
	<!-- Mensagens de resposta do servidos -->
	<input type="hidden" id="${msg_type}" value="${msg_info}" />
</div>
</html:form>