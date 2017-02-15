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
					document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/solicitacaocomercial/exportar.do';
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
        <html:form styleId="acaoForm" action="/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/solicitacaocomercial/search.do">
        	<div class="line">
		        <catalogo:contentBox title="Pesquisa" doubt="false" requiredFields="false">
					<div class="line">
						<div class="linerow bold"><label for="cdTipoServico">Tipo de Servi&ccedil;o:</label></div>
						<div class="linerow bold"><label for="cdServicoOrigem">C&oacute;digo do Servi&ccedil;o Origem:</label></div>
						<div class="linerow bold"><label for="cdServicoCatalogo">C&oacute;digo do Servi&ccedil;o Cat&aacute;logo:</label></div>
						<div class="linerow bold"><label for="nmServicoOrigem">Nome do Servi&ccedil;o Origem:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold">
							<html:select property="cdTipoServico" styleClass="select200" >
								<html:option value="">&nbsp;</html:option>
								<c:forEach var="tipoServicoTO" items="${tipoServicoTOList}">
									<html:option value="${tipoServicoTO.cdTipoServico}">${tipoServicoTO.dscTipoServico}</html:option>
								</c:forEach>
							</html:select>								
						</div>
						<div class="linerow bold"><html:text property="cdServicoOrigem" styleId="cdServicoOrigem" style="width: 150px"/></div>
						<div class="linerow bold"><html:text property="cdServicoCatalogo" styleId="cdServicoCatalogo" style="width: 150px"/></div>
						<div class="linerow bold"><html:text property="nmServicoOrigem" styleId="nmServicoOrigem" style="width: 150px"/></div>
					</div>
					<div class="line">
						<div class="linerow bold"><label for="nmServicoCatalogo">Nome do Servi&ccedil;o Cat&aacute;logo:</label></div>
						<div class="linerow bold"><label for="cdTipoSolicitacaoComercial">Tipo de Solicita&ccedil;&atilde;o:</label></span></div>
						<div class="linerow bold"><label for="cdSolicitacaoComercial">C&oacute;digo da Solicita&ccedil;&atilde;o Comercial:</label></div>
						<div class="linerow bold"><label for="nmSolicitacaoComercial">Nome da Solicita&ccedil;&atilde;o Comercial:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold"><html:text property="nmServicoCatalogo" styleId="nmServicoCatalogo" style="width: 150px"/></div>
						<div class="linerow bold">
							<html:select property="cdTipoSolicitacaoComercial" styleClass="select200" >
								<html:option value="">&nbsp;</html:option>
								<c:forEach var="tipoSolicitacaoComercialTO" items="${tipoSolicitacaoComercialTOList}">
									<html:option value="${tipoSolicitacaoComercialTO.cdTipoSolicitacaoComercial}">${tipoSolicitacaoComercialTO.nmTipoSolicitacaoComercial}</html:option>
								</c:forEach>
							</html:select>								
						</div>
						<div class="linerow bold"><html:text property="cdSolicitacaoComercial" styleId="cdSolicitacaoComercial" style="width: 150px"/></div>
						<div class="linerow bold"><html:text property="nmSolicitacaoComercial" styleId="nmSolicitacaoComercial" style="width: 150px"/></div>
					</div>
					<div class="line">
						<div class="linerow bold"><label for="prazoMaximoVigencia">Prazo M&aacute;ximo de Vig&ecirc;ncia:</label></div>
						<div class="linerow bold"><label for="prazoMaximoAtendimento">Prazo M&aacute;ximo de Atendimento:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold"><html:text property="prazoMaximoVigencia" styleId="prazoMaximoVigencia" style="width: 150px"/></div>
						<div class="linerow bold"><html:text property="prazoMaximoAtendimento" styleId="prazoMaximoAtendimento" style="width: 150px"/></div>
					</div>
					<div class="barra"></div>
					<div class="botao" id="divBotoes" style="display:block">
 					    <html:button property="Pesquisar" value="Pesquisar" alt="Pesquisar" styleClass="btNavegacao74" onclick="pesquisar();" title=""/><span>&nbsp;</span>
					</div>
				</catalogo:contentBox>
				<c:if test="${solicitacaoComercialRelatorioFixaTOList != null}">
					<div id="resultado_pesquisa">
						<catalogo:contentBox  title="Resultado da Pesquisa" doubt="false" requiredFields="false">
							<display:table name="solicitacaoComercialRelatorioFixaTOList" id="solicitacaoComercialRelatorioFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "ordenar.do" class="tabela-padrao-new tablesorter table_body" >
								<display:column property="nmServicoCatalogo" title="Nome do Servi&ccedil;o Cat&aacute;logo" sortable="true" headerClass="sortable" />
								<display:column property="nmTipoSolicitacaoComercial" title="Tipo de Solicita&ccedil;&atilde;o" sortable="true" headerClass="sortable" />
								<display:column property="nmSolicitacaoComercial" title="Nome da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" />
								<display:column property="prazoMaximoVigencia" title="Prazo M&aacute;ximo de Vig&ecirc;ncia" sortable="true" headerClass="sortable" />
								<display:column property="prazoMaximoAtendimento" title="Prazo M&aacute;ximo de Atendimento" sortable="true" headerClass="sortable" />
							</display:table>
							<div class="barra"></div>
							<div class="botao">
							    <cata:temPermissao acao="exportarRelatorioSolicitacaoComercial">
							        <html:button property="Pesquisar" value="Exportar" alt="${exportar}" styleClass="${styleBotaoExportar}" onclick="exportar(this);" title="Exportar"/><span>&nbsp;</span>
								</cata:temPermissao>
							</div>
						</catalogo:contentBox>
					</div>
				</c:if>
			</div>
			<html:hidden property="erro" styleId="erro" value="${erro}"/>
        </html:form>
  </jsp:body>        
</catalogo:blankTemplate>        