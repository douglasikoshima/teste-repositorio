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
        <script type="text/javascript" src="/catalogo/static_server/js/importacaotipomatrizcadastro.js"></script>
   </jsp:attribute>
   <jsp:body>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/importar.do" styleId="importacaoTipoMatrizContratoForm" onsubmit="false" enctype="multipart/form-data">
			
			<script>carregaMenu('mn_parametrizacao_importacao_matriz_contrato');</script>
			<div class="secao_conteudo">
				<catalogo:contentBox title="Importação Matriz Contratos" doubt="true" requiredFields="true">
					<input type="hidden" name="isCriticas" id="isCriticas" />
					<div class="fleft" style="width:63%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="text-align: left; width: 40px;"><label for="fileImport">Arquivo:<font size="1px" color="#EEB422">*</font></label></div>
						<html:file property="fileImport" styleId="fileImport" style="width:450px;" styleClass="required"/>
					</div>
					<div class="fleft" style="width:25%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="text-align: left; width: 40px;"><label for="trabalho">Trabalho:</label></div>
						<label><html:radio property="trabalho" styleId="trabalho" value="S" styleClass="semBorda checkbox"/>&nbsp;Sim</label>
						<label><html:radio property="trabalho" styleId="trabalho" value="N" styleClass="semBorda checkbox"/>&nbsp;Não</label>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
				    	<c:if test="${validado}">
					    	<html:button property="botao_producao_form" styleId="botao_producao_form" onClick="producao()" styleClass="btNavegacao74" value="Produção" alt="Produção" title=""/>					    				    
					    	<span>&nbsp;</span>
				    	</c:if>
						<c:if test="${importado}">
					    	<html:button property="botao_validar_form" styleId="botao_validar_form" onClick="validar()" styleClass="btNavegacao74" value="Validar" alt="Validar" title=""/>					    				    
					    	<span>&nbsp;</span>
					    </c:if>
						<c:if test="${liberar}">
					    	<html:button property="botao_liberar_form" styleId="botao_liberar_form" onClick="liberar()" styleClass="btNavegacao74" value="Liberar" alt="Liberar" title=""/>					    				    
					    	<span>&nbsp;</span>
					    </c:if>
						<html:button property="botao_importar_form" styleId="botao_importar_form" onClick="importar()" styleClass="btNavegacao74" value="Importar" alt="Importar" title=""/>					    				    						
						<span>&nbsp;</span>	
					    <html:button property="botao_exportar_form" styleId="botao_exportar_form" onClick="liberarExport()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title=""/>					    				    
					</div>
				</catalogo:contentBox>
			</div>
			<c:if test="${labelError != null}">
				<script>
					generateContentError("${labelError}")
				</script>
			</c:if>
			<div id="contentCriticas">
			<c:if test="${contratoMatrizOfertaCriticaList != null}">
				<c:if test="${importado}">
					<catalogo:contentBox title="Criticas" >
						<display:table name="contratoMatrizOfertaCriticaList" id="contratoMatrizOfertaCriticaTO" export="false" pagesize= "50" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "ImportacaoTipoMatrizContratoController.jpf" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="contratoMatrizOfertaTO.codigoPlano" title="CDPLANO" style="text-align: center;" />
							<display:column property="contratoMatrizOfertaTO.codigoServico"  title="CDPROMOCAO" style="text-align: center;" />
							<display:column property="contratoMatrizOfertaTO.siglaCsa"  title="CSA" style="text-align: center;" />
							<display:column property="contratoMatrizOfertaTO.valorContrato"  title="VALOR" style="text-align: center;" />
							<display:column property="matrizOfertaContratoCriticaTO.dscCriticaimportacao"  title="CRITICA" style="text-align: center;" />
						</display:table>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="botao_exportarcriticas_form" styleId="botao_exportarcriticas_form" onClick="exportarCriticas()" styleClass="btNavegacao120" value="Exportar Criticas" alt="Exportar Criticas" title=""/>					    				    						
						</div>
					</catalogo:contentBox>
					<script>
						document.getElementById('isCriticas').value = "S";
					</script>
				</c:if>
			</c:if>
			</div>
			<c:if test="${exportar}">
				<script>
					exportar();
				</script>
			</c:if>
        </html:form>     
     </jsp:body>        
</catalogo:defaultTemplate>