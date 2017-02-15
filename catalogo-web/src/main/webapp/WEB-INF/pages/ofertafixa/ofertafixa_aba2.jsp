<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregar.do" >

<input type="hidden" id="dsOfertafixa" value='${ofertafixaTO.dsOfertafixa == null ? "" : ofertafixaTO.dsOfertafixa}'/>
<c:if test='${ofertafixaTO != null && (acao eq "carregarAba" || acao eq "adicionarComplementar")}'>
    <catalogo:contentBox title="Resultado da Pesquisa" doubt="false" requiredFields="false">
        <div id="displayOfertaComplementarList">
            <display:table name="ofertafixaTO.ofertafixaComplementarTOList" id="ofertafixaComplementarTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displayOfertaComplementarList" class="tabela-padrao-new tablesorter table_body" >
                <display:column property="servicoOfertaFixaTO.cdServico" title="C&oacute;digo do Servi&ccedil;o" sortable="true" headerClass="sortable" /> 
                <display:column property="servicoOfertaFixaTO.nome" title="Nome do Servi&ccedil;o" sortable="true" headerClass="sortable" /> 
                <display:column property="solicitacaoComercialFixaTO.nmSolicitacaoComercial" title="Nome da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" /> 
                <display:column property="pzMaximoVigencia" title="Tempo de Vig&ecirc;ncia" sortable="true" headerClass="sortable" /> 
                <display:column title="Dependente" sortable="false" headerClass="sortable"> 
	                <div class="clean icon">
						<div class=${ofertafixaComplementarTO.dependente ? "icondep" : ""}></div>
	                </div>
			    </display:column> 
                <cata:temPermissao acao="excluirOfertafixa">
                    <c:if test='${ofertafixaTO.excluivel && !ofertafixaComplementarTO.dependente}'>
                        <display:column title="Excluir" headerClass="sortable">
                                <div class="clean"><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas" alt="Excluir" onclick="excluirOfertaComplementar('${ofertafixaComplementarTO.solicitacaoComercialFixaTO.idSolicitacaoComercial}')"></div>
                        </display:column>
                    </c:if>
                </cata:temPermissao>
            </display:table>        
        </div>
        <c:if test='${ofertafixaTO.ofertaComplementarAlteravel}'>
            <div class="barra"></div>        
            <div class="botao" id="botaoNovoAba2">
<!--                <cata:temPermissao acao="excluirOfertafixa">-->
                    <html:button property="botao_nova_oferta_complementar_form" styleId="botao_nova_oferta_complementar_form" onClick="carregarFormularioNovaOfertaComplementar('novaOfertaComplementar')" styleClass="btNavegacao74" value="Novo" alt="Novas Ofertas Complementares" title=""/><span>&nbsp;</span>
<!--                </cata:temPermissao>-->
            </div>    
        </c:if>        
    </catalogo:contentBox>

    <div id="novaOfertaComplementar"></div>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</c:if>
</html:form>
<html:hidden property="statusOfertafixa" styleId="statusOfertafixa" value="${ofertafixaTO.status}"/>
<html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>