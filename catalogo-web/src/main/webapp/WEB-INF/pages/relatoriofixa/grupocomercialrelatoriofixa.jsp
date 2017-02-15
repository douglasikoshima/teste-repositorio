<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:blankTemplate titulo="Home Catalogo">

    <jsp:attribute name="headScripts">
    	<script type="text/javascript">
			var $jq = jQuery.noConflict();
			function pesquisar() {				
				document.getElementById('acaoForm').action = 'search.do';
				document.getElementById('acaoForm').submit();
			}
			
			function exportar(obj) {
				var clazz = $jq(obj).attr("class");
				if (clazz == "btNavegacao74") {
					document.getElementById('acaoForm').action = obj.alt;
					document.getElementById('acaoForm').submit();
				}
			}			
			
			function init() {
				if (document.getElementById('erro').value != "") {
					document.getElementById('divErros').style.display = "block";
			       	document.getElementById('conteudo_divErros').innerHTML = document.getElementById('erro').value;
				}
			}
    	</script>
    </jsp:attribute>
    
    <jsp:body>
        <html:form styleId="acaoForm" action="/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/grupocomercial/search.do" >
        	<div class="line">
		        <catalogo:contentBox title="Pesquisa" doubt="false" requiredFields="false">
					<div class="line">
						<div class="linerow bold"><label for="cdGrupoComercial">C&oacute;digo do Grupo:</label></div>
						<div class="linerow bold"><label for="dsGrupoComercial">Nome do Grupo:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold">
							<html:text property="cdGrupoComercial" styleId="cdGrupoComercial" style="width: 150px" />
						</div>
						<div class="linerow bold">
							<html:text property="dsGrupoComercial" styleId="dsGrupoComercial" style="width: 150px" />
						</div>
					</div>
					<div class="barra"></div>
					<div class="botao" id="divBotoes" style="display:block">
						<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="pesquisar();" value="Pesquisar" alt="Pesquisar" styleClass="btNavegacao74" /><span>&nbsp;</span>
					</div>
				</catalogo:contentBox>
				<c:if test="${grupoComercialRelatorioFixaTOList != null}">
					<catalogo:contentBox  title="Resultado da Pesquisa" doubt="false" requiredFields="false">
						<display:table name="grupoComercialRelatorioFixaTOList" id="grupoComercialRelatorioFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "ordenar.do" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="cdGrupoComercial" title="C&oacute;digo do Grupo" sortable="true" headerClass="sortable" />
							<display:column property="dsGrupoComercial" title="Nome do Grupo" sortable="true" headerClass="sortable" />
						</display:table>
						<div class="barra"></div>
						<div class="botao">
						    <cata:temPermissao acao="exportarRelatorioGrupoComercial">												    
						        <html:button property="botao_exportar" styleId="botao_exportar" onClick="exportar(this)" styleClass="${styleBotaoExportar}" value="Exportar" alt="${exportar}" title="Exportar"  /><span>&nbsp;</span>
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</c:if>
			</div>
			<html:hidden property="erro" styleId="erro" value="${erro}"/>
        </html:form>
  </jsp:body>        
</catalogo:blankTemplate>  