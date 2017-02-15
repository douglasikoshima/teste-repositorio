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
        <link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/analisecreditopriorizar.css"/>
        <script type="text/javascript" src="/catalogo/static_server/js/analisecreditopriorizar.js"></script>
  </jsp:attribute>
  <jsp:body>
        <div id="conteudo_success" style="color: #299B00;display: none" ></div>
        <div class="breadcrumb"> Parametriza&ccedil;&atilde;o > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/OfertafixaAction.do';" style="cursor: pointer;">An&aacute;lise de Cr&eacute;dito - Prioridade</a><span><span></div>
        <script>carregaMenu('mn_parametrizacao_fixa_analise_credito_prioridade');</script>
        <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/pesquisarOferta.do" styleId="acaoForm">
            <div class="secao_conteudo">
                <div id="boxOfertas">
                    <!-- Pesquisa Ofertas !-->
                    <catalogo:contentBox title="Pesquisar Ofertas" doubt="false" requiredFields="true">
                        <div class="boxline">
                            <div class="linerow bold">Agrupador:<span class="required">*</span></div>
                                <div class="linerow clear">
                                    <html:select property="idEps" styleId="idEps">
                                        <html:option value="">--Selecione--</html:option>
                                        <c:forEach var="epsTO" items="${epsTOList}">
                                            <html:option value="${epsTO.idEps}">${epsTO.nmEps}</html:option>
                                        </c:forEach>
                                    </html:select>
                                </div>
                        </div>
                        <div class="boxline">
                        	<div class="linerow bold">Score:<span class="required">*</span></div>
                        	<div class="linerow clear">
                                    <html:select property="idAnaliseCredito" styleId="idAnaliseCredito">
                                        <html:option value="">--Selecione--</html:option>
                                        <c:forEach var="analiseCreditoTO" items="${analiseCreditoTOList}">
                                            <html:option value="${analiseCreditoTO.idAnaliseCredito}">${analiseCreditoTO.cdCor}</html:option>
                                        </c:forEach>
                                    </html:select>
                        	</div>                        	
                        </div>                        
                            <div class="barra"></div>
                            <div class="botao">
                                <html:button property="btnPesquisar" styleId="btnPesquisar" onClick="pesquisarOferta()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar Ofertas" title=""/><span>&nbsp;</span>
                            </div>
                        </catalogo:contentBox>
                    <div id="pesquisaOfertas"></div>                  
                </div>
            </div>
        </html:form>     
    </jsp:body>        
</catalogo:defaultTemplate>