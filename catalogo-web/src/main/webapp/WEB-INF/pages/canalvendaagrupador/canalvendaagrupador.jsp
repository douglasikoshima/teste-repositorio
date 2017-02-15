<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:defaultTemplate titulo="Home Catalogo">
  <jsp:attribute name="headScripts">
		<script type="text/javascript" src="/catalogo/static_server/js/canalvendaagrupador.js"></script>
  </jsp:attribute>
  <jsp:body>
		<script>carregaMenu('mn_parametrizacao_canal_venda_agrupador');</script>
		<div class="breadcrumb"> Parametrização > Fixa > <span>Grupo Comercial<span></div>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/pesquisar.do" styleId="canalVendaAgrupadorForm" onsubmit="flase">
			<catalogo:contentBox title="Grupo Comercial" >
				<div class="contentGroup">
					<fieldset id="pesquisaTipo">
						<legend>Selecione o tipo</legend>
						<div class="line">
						    <html:radio property="canalVendaAgrupadorFormContentGroup" styleId="canalVendaAgrupadorFormContentGroup" value="${canalvenda}" onClick="openFrame(this)">Grupo Comercial</html:radio>
						    <html:radio property="canalVendaAgrupadorFormContentGroup" styleId="canalVendaAgrupadorFormContentGroup" value="${agrupador}" onClick="openFrame(this)">Agrupador</html:radio>
<%-- 							<netui:radioButtonGroup dataSource="canalVendaAgrupadorFormContentGroup" orientation="horizontal">
								<netui:radioButtonOption value="${canalvenda}" onClick="openFrame(this)">
									Grupo Comercial
								</netui:radioButtonOption>
								<netui:radioButtonOption value="${agrupador}" onClick="openFrame(this)">
									Agrupador
								</netui:radioButtonOption>
							</netui:radioButtonGroup> --%>
						</div>
					</fieldset>
				</div>
			</catalogo:contentBox>
			<div id="mensagemLoadSearch" class="linerow bold">  Aguarde, carregando dados...</div>
			<div id="boxOperacoes">
				<div id="caixa">
					<iframe width="100%" id="iframe" style="display: none;" frameborder="0" scrolling="no" onload="sizeFrame(this)" ></iframe>
				</div>
			</div>
        </html:form>     
   </jsp:body>        
</catalogo:defaultTemplate>