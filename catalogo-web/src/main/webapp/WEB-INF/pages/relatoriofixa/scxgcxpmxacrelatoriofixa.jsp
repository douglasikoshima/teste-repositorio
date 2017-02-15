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
    	<script>
			var $jq = jQuery.noConflict();
			function pesquisar() {
				document.getElementById('acaoForm').submit();
			}
			
			function exportar(obj) {
				var clazz = $jq(obj).attr("class");
				if (clazz == "btNavegacao74") {
					document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/scxgcxpmxac/exportar.do';
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
        <html:form styleId="acaoForm" action="/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/scxgcxpmxac/search.do" >
			<div id="pesquisaDesconto">
			
	        	<div class="line">
			        <catalogo:contentBox title="Pesquisa" doubt="false" requiredFields="false">
						
						<div class="line">
							<div class="linerow bold"><label for="cdServico">C&oacute;digo do Servi&ccedil;o:</label></div>
							<div class="linerow bold"><label for="nmServico">Nome do Servi&ccedil;o:</label></div>
							<div class="linerow bold"><label for="cdTipoSolicitacaoComercial">Tipo de Solicita&ccedil;&atilde;o:</label></div>
							<div class="linerow bold"><label for="cdSolicitacaoComercial">C&oacute;digo da Solicita&ccedil;&atilde;o Comercial:</label></div>
						</div>
						<div class="line">
							<div class="linerow bold"><html:text property="cdServico" styleId="cdServico" style="width: 150px"/></div>
							<div class="linerow bold"><html:text property="nmServico" styleId="nmServico" style="width: 150px"/></div>
							<div class="linerow bold">
							<html:select property="cdTipoSolicitacaoComercial" styleClass="select200" >
								<html:option value="">&nbsp;</html:option>
								<c:forEach var="tipoSolicitacaoComercialTO" items="${tipoSolicitacaoComercialTOList}">
									<html:option value="${tipoSolicitacaoComercialTO.cdTipoSolicitacaoComercial}">${tipoSolicitacaoComercialTO.nmTipoSolicitacaoComercial}</html:option>
								</c:forEach>
							</html:select>									
							</div>
							<div class="linerow bold"><html:text property="cdSolicitacaoComercial" styleId="cdSolicitacaoComercial" style="width: 150px"/></div>
						</div>
						<div class="line">
							<div class="linerow bold"><label for="nmSolicitacaoComercial">Nome da Solicita&ccedil;&atilde;o Comercial:</label></div>
							<div class="linerow bold"><label for="cdGrupoComercial">C&oacute;digo do Grupo:</label></div>
							<div class="linerow bold"><label for="nmGrupoComercial">Nome do Grupo:</label></div>
							<div class="linerow bold"><label for="cdPlanoMinutos">C&oacute;digo do Plano de Minutos:</label></div>
						</div>
						<div class="line">
							<div class="linerow bold"><html:text property="nmSolicitacaoComercial" styleId="nmSolicitacaoComercial" style="width: 150px"/></div>
							<div class="linerow bold"><html:text property="cdGrupoComercial" styleId="cdGrupoComercial" style="width: 150px"/></div>
							<div class="linerow bold"><html:text property="nmGrupoComercial" styleId="nmGrupoComercial" style="width: 150px"/></div>
							<div class="linerow bold"><html:text property="cdPlanoMinutos" styleId="cdPlanoMinutos" style="width: 150px"/></div>
						</div>
						<div class="line">
							<div class="linerow bold"><label for="cdAreaConcorrencia">C&oacute;digo da &Aacute;rea de Concorr&ecirc;ncia:</label></div>
						</div>
						<div class="line">
							<div class="linerow bold"><html:text property="cdAreaConcorrencia" styleId="cdAreaConcorrencia"  style="width: 150px"/></div>
						</div>
						<div class="barra"></div>
						<div class="botao" id="divBotoes" style="display:block">
							<html:button property="Pesquisar" value="Pesquisar" alt="Pesquisar" styleClass="btNavegacao74" onclick="pesquisar();" title=""/><span>&nbsp;</span>
						</div>
					</catalogo:contentBox>
					<c:if test="${sCxGCxPMxACRelatorioFixaTOList != null}">
						<div = id="resultado_pesquisa">
							<catalogo:contentBox  title="Resultado da Pesquisa" doubt="false" requiredFields="false">
								<display:table name="sCxGCxPMxACRelatorioFixaTOList" id="sCxGCxPMxACRelatorioFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "ordenar.do" class="tabela-padrao-new tablesorter table_body" >
									<display:column property="cdServico" title="C&oacute;digo do Servi&ccedil;o" sortable="true" headerClass="sortable" ></display:column>
									<display:column property="nmServico" title="Nome do Servi&ccedil;o" sortable="true" headerClass="sortable" ></display:column>
									<display:column property="cdSolicitacaoComercial" title="C&oacute;digo da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" />
									<display:column property="nmSolicitacaoComercial" title="Nome da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" />
									<display:column property="cdGrupoComercial" title="C&oacute;digo do Grupo" sortable="true" headerClass="sortable" />
									<display:column property="nmGrupoComercial" title="Nome do Grupo" sortable="true" headerClass="sortable" />
									<display:column property="cdPlanoMinutos" title="C&oacute;digo do Plano de Minutos" sortable="true" headerClass="sortable" />
									<display:column property="cdAreaConcorrencia" title="C&oacute;digo da &Aacute;rea de Concorr&ecirc;ncia" sortable="true" headerClass="sortable" />
								</display:table>
								<div class="barra"></div>
								<div class="botao">
								    <cata:temPermissao acao="exportarRelatorioSCxGCxPMxAC">
										<html:button property="Pesquisar" value="Exportar" alt="${exportar}" styleClass="${styleBotaoExportar}" onclick="exportar(this);" title="Exportar"/><span>&nbsp;</span>
									</cata:temPermissao>
								</div>
							</catalogo:contentBox>
						</div>
					</c:if>
				</div>
			</div>
			<html:hidden property="erro" styleId="erro" value="${erro}"/>
        </html:form>
  </jsp:body>        
</catalogo:blankTemplate>  