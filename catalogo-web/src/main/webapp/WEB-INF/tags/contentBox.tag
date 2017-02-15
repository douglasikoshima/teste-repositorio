<%@ tag pageEncoding='UTF-8' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="title" required="true" type="java.lang.String" %>
<%@attribute name="doubt" required="false" type="java.lang.Boolean" %>
<%@attribute name="requiredFields" required="false" type="java.lang.Boolean" %>

<div class="conteudo_box_top">
	<div class="conteudo_box_top_center">${title}</div>
	<div class="conteudo_box_top_esq"></div>
	<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
</div>
<div>
	<div class="conteudo_box_middle fleft">
		<div class="conteudo_box_middle_mg relative"> 
			<c:if test="${requiredFields}"><div class="legendaObrigatorio help">&nbsp; (*) Campo Obrigat&oacute;rio</div></c:if>
			<c:if test="${doubt}">
				<c:if test="${!requiredFields}"><div class="legendaObrigatorio help">&nbsp;</div></c:if>
				<div class="link_manual" title="Dúvida" >
					<a href="/catalogo/static_server/manual/manual_catalogo.html" target="_blank">
						<img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
					</a>
				</div>
			</c:if>
			<br />
			<jsp:doBody />
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>
</div>
