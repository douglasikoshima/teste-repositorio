<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
    <jsp:attribute name="headScripts">
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/jquery.jscrollpane.css"/>
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/gerenciadorregras.css"/>
		<script type="text/javascript" src="/catalogo/static_server/js/json2.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/gerenciadorregras.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/jquery.jscrollpane.min.js"></script>
    </jsp:attribute>
    <jsp:body>
		<script>carregaMenu('mn_parametrizacao_gerenciador_regras');</script>
		<div id="mensagem" style="margin-left: 5px;">
		<div class="breadcrumb">Parametrização > Fixa > <span>Gerenciador de Regras<span></div>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/documentos/pesquisar.do" styleId="actionForm" onsubmit="false">
			<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
			<div id="caixa">
				<span id="abas">
					<a id="abaFiltro1" href="javascript:abrirAba('abaDocumentos','abaFiltro1')" >Filtro I</a>
					<a id="abaFiltro2" href="javascript:abrirAba('abaDocumentos','abaFiltro2')" >Filtro II</a>
					<a href="#abaDocumentos" class="selected">Documentos</a>
				</span>
				<ul id="conteudos">
					<!-- Aba FILTRO I #aba1 !-->
					<li id="aba3" class="contentRel">
						<div id="documentos">
							<input type="hidden" id="abaDocumentoChanged"/>	
							<div class="box">
								<div class="linerow bold">Indicador Comercial:<span class="required">*</span></div>
								<div class="linerow clear">
	                                <html:select property="idIndicadorComercial" styleId="idIndicadorComercial" onChange="pesquisarIndicadoresDocumento()" styleClass="required" style="width:140px;">
	                                    <html:option value="">--Selecione--</html:option>
	                                    <c:forEach var="indicadorComercialDocumentoTO" items="${indicadorComercialDocumentoTOList}">
	                                        <html:option value="${indicadorComercialDocumentoTO.idIndicadorComercial}">${indicadorComercialDocumentoTO.nmIndicadorComercial}</html:option>
	                                    </c:forEach>
	                                </html:select>					               	
					            </div>
							</div>
							<c:if test="${gerenciadorRegrasAbaFiltroForm.idTipoDocumentoCheckList != null}">
								<div id="selectDocumentos">
									<div id="tabelaDocumentos" class="clear valuecheck" style="padding:10px">
										<div class="bold">Documentos:</div>
										<c:forEach items="${tipoDocumentoTOList}" var="tipoDocumentoTO">
											<html:multibox property="idTipoDocumentoCheckList" styleId="checkbox_tipodocumento_${tipoDocumentoTO.idTipoDocumento}" value="${tipoDocumentoTO.idTipoDocumento}" style="font-family: Verdana, Arial, Helvetica; font-size: 10px; color: #7D7D7D;" styleClass="semBorda checkbox">
						                        
											</html:multibox><span>&nbsp;</span>${tipoDocumentoTO.nmTipoDocumento}</br>											
										</c:forEach>
									</div>
									<cata:temPermissao acao="gravarGerenciadorRegras">
										<div class="barra"></div>
										<div class="botao">
											<html:button property="botao_gravar_form" styleId="botao_gravar_form" onClick="salvarIndicadoresDocumento()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
										</div>
									</cata:temPermissao>
								</div>
							</c:if>
						</div>
					</li>
					<li>
						<div id="mensagemLoadSearch" class="linerow bold">  Aguarde, carregando dados...</div>
					</li>
				</ul>
			</div>
        </html:form>     
   </jsp:body>        
</catalogo:defaultTemplate>