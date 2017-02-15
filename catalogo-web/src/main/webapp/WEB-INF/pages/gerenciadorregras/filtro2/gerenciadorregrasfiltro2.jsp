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
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/filtro2/pesquisar.do" styleId="actionForm" onsubmit="false">
			<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
			<div id="caixa">
				<span id="abas">
					<a id="abaFiltro1" href="javascript:abrirAba('abaFiltro2','abaFiltro1')" >Filtro I</a>
					<a id="abaFiltro2" class="selected">Filtro II</a>
					<a id="abaDocumentos" href="javascript:abrirAba('abaFiltro2','abaDocumentos')" >Documentos</a>
				</span>
				<ul id="conteudos">
					<!-- Aba FILTRO I #aba1 !-->
					<li id="aba2" class="contentRel">
						<div id="regraFiltroII">
							<div class="box">
								<html:hidden property="gerenciadorRegrasConfiguracaoTOJSONNew" styleId="gerenciadorRegrasConfiguracaoTOJSONNew"/>
								<div class="linerow bold">Canal Atendimento:<span class="required">*</span></div>
								<div class="linerow clear">
	                                <html:select property="idCanalAtendimento" styleId="idCanalAtendimento" onChange="pesquisarIndicadores('abaFiltro2')" styleClass="required" style="width:140px;">
	                                    <html:option value="">--Selecione--</html:option>
	                                    <c:forEach var="canalAtendimentoDispScaTO" items="${canalAtendimentoDispScaTOList}">
	                                        <html:option value="${canalAtendimentoDispScaTO.idCanalAtendimento}">${canalAtendimentoDispScaTO.nmCanal}</html:option>
	                                    </c:forEach>
	                                </html:select>					               	
					            </div>
							</div>
							<c:if test="${gerenciadorRegrasConfiguracaoTOList != null}">
								<display:table  name="gerenciadorRegrasConfiguracaoTOList" id="gerenciadorRegrasConfiguracaoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body">
									<display:column title="Servico Linha" >
										<a href='javascript:carregarSelects(${gerenciadorRegrasConfiguracaoTO})'>${gerenciadorRegrasConfiguracaoTO.nmServico}</a>
									</display:column>
									<c:forEach var="col" items="${nmIndicadorComercialList}">
										<display:column property="indicadorComercialRegraPrioridadeTOMap.${col}.regraPrioridadeAltaTO.idRegraPrioridadeAlta" title="idIndicador" headerClass="hidden" class="hidden" />
										<display:column property="indicadorComercialRegraPrioridadeTOMap.${col}.regraPrioridadeAltaTO.dsRegraAlta" title="${col}" />
									</c:forEach>
								</display:table>
							</c:if>
							<div id="edicaoIndicadores"  style="display: none">
							<div id="nomeFiltroII"></div>
							<catalogo:contentBox title="Configuração Filtro II" doubt="true" requiredFields="true">
							<div class="scroll-pane">
								<table id="tabelaIndicadores" cellpadding="5" class="tabela-padrao-new tablesorter table_body tableList" style="width: 99%;" cellspacing="0">
									<thead>
										<tr>
											<c:forEach var="nmIndicadorComercial" items="${nmIndicadorComercialList}">
												<th>${nmIndicadorComercial} <span class="required">*</span> </th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:forEach var="nmIndicadorComercial" items="${nmIndicadorComercialList}">
												<td>
													<select id="${nmIndicadorComercial}" size="1">
														<option value="0">--Selecione--</option>
														<c:forEach var="regraPrioridadeAltaTO" items="${regraPrioridadeAltaTOList}">
															<option value="${regraPrioridadeAltaTO.idRegraPrioridadeAlta}"> ${regraPrioridadeAltaTO.dsRegraAlta} </option>
														</c:forEach>
													</select>	
												</td>
											</c:forEach>
										</tr>
									</tbody>
								</table>
								</catalogo:contentBox>
								<cata:temPermissao acao="gravarGerenciadorRegras">
									<div class="barra"></div>
										<div class="botao">
											<html:button property="botao_gravar_form" styleId="botao_gravar_form" onClick="salvarIndicadores('abaFiltro2')" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
										</div>
									</div>
								</cata:temPermissao>
							</div>
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