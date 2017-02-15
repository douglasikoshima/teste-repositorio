<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>


<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/search.do" >

<c:if test="${promocaoTOList != null}">
    <catalogo:contentBox title="Resultado da Pesquisa" doubt="false" requiredFields="false">
        <display:table name="promocaoTOList" id="promocaoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderPromocao.do,pesquisaOfertas" class="tabela-padrao-new tablesorter table_body" >
            <display:column title="Código" sortable="true" headerClass="sortable"> 
                <a href="javascript:carregarFormularioAlteracao('${promocaoTO.idPromocao}','true')">${promocaoTO.cdPromocao}</a>
            </display:column>
            <display:column property="nmPromocao" title="Nome" sortable="true" headerClass="sortable" />
            <display:column property="periodoVigencia" title="Período de Vigência" sortable="false" headerClass="sortable" />
            <display:column title="Status" headerClass="sortable">
                <div class='clean icon'>
                    <c:if test="${promocaoTO.pendenteConfiguracao}">
                        <div class='iconalert left'></div>
                    </c:if>
                    <div class='${promocaoTO.status == "vigente" ? "iconativo" : promocaoTO.status == "finalizado" ? "iconinativo" : "iconwait"}'></div>
                </div>
            </display:column>
            <cata:temPermissao acao="alterarOfertaBandaLarga">
                <display:column title="Alterar" headerClass="sortable">
                    <c:if test='${promocaoTO.alteravel}'>
                        <div class="clean"><img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" class="btnAlterarOfertas" alt="Alterar" onclick="carregarFormularioAlteracao('${promocaoTO.idPromocao}','')"></div>
                    </c:if>
                </display:column>
            </cata:temPermissao>
            <cata:temPermissao acao="excluirOfertaBandaLarga">
                <display:column title="Excluir" headerClass="sortable">
                    <c:if test='${promocaoTO.excluivel}'>
                        <div class="clean"><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas" alt="Excluir" onclick="excluirOferta('${promocaoTO.idPromocao}')"></div>
                    </c:if>
                </display:column>
            </cata:temPermissao>
        </display:table>
    </catalogo:contentBox>
</c:if>
<c:if test="${servicoFixaTOList != null}">
    <select id="idCdServico" onchange="carregarServicoDescontoList()" style="width: 340px !important;" class="data changeable">
        <option value="">-- Selecione --</option>
        <c:forEach items="${servicoFixaTOList}" var="servicoFixaTO">
            <option value="${servicoFixaTO.idServico}|${servicoFixaTO.cdServico}">${servicoFixaTO.nmComercial}</option>
        </c:forEach>
    </select>
</c:if>
<c:if test="${servicoFixaDescontoTOList != null}">
    <select id="idCdServicoDesconto" onchange="carregarSolicitacaoList()" class="data changeable">
        <option value="">-- Selecione --</option>
        <c:forEach items="${servicoFixaDescontoTOList}" var="servicoFixaTO">
            <option value="${servicoFixaTO.idServico}|${servicoFixaTO.cdServico}">${servicoFixaTO.nmComercial}</option>
        </c:forEach>
    </select>
</c:if>
<c:if test="${solicitacaoComercialTOList != null}">
    <select id="idCdSolicitacaoComercial" onchange="carregarCdSolicitacao()" class="data changeable">
        <option value="">-- Selecione --</option>
        <c:forEach items="${solicitacaoComercialTOList}" var="solicitacaoComercialFixaTO">
            <option value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}|${solicitacaoComercialFixaTO.cdSolicitacaoComercial}|${solicitacaoComercialFixaTO.prazoMaximoVigencia}">${solicitacaoComercialFixaTO.nmSolicitacaoComercial}</option>
        </c:forEach>
    </select>
</c:if>
<c:if test='${agrupadorDisponibilidadeByEps.disponibilidadePromocaoFixaTOList != null && (acao eq "orderDisponibilidade" && element eq "displayDisponibilidadePromocaoFixaTOList")}'>
	<display:table name="agrupadorDisponibilidadeByEps.disponibilidadePromocaoFixaTOList" id="disponibilidadePromocaoFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderDisponibilidade.do,displayDisponibilidadePromocaoFixaTOList" class="tabela-padrao-new tablesorter table_body" >
		<display:column property="nmEps" title="Nome do Agrupador" sortable="true" headerClass="sortable" />
		<display:column property="cdCanalVenda" title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" />
		<display:column property="nmCanalVenda" title="Nome Grupo Comercial" sortable="true" headerClass="sortable" />
	</display:table>
</c:if>
<c:if test='${pesquisaCanalVendaTOList != null && (acao eq "orderDisponibilidade" && element eq "displaySearchCanalVendaTOList")}'>
		<display:table name="pesquisaCanalVendaTOList" id="canalVendaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderDisponibilidade.do,displaySearchCanalVendaTOList" class="tabela-padrao-new tablesorter table_body" >
			<display:column title="&nbsp;<input class='checkbox ckbCanalVendaTodos' type='checkbox' id='ckbCanalVendaTodos' />" headerClass="" style="text-align:center;">
				<input type="checkbox" class="checkbox ckbCanalVenda" value="${canalVendaTO.idCanalVenda}" id="idCanalVenda" ${canalVendaTO.checked} ${canalVendaTO.disabled} />
			</display:column>
			<display:column property="epsTO.nmEps" title="Nome do Agrupador" sortable="true" headerClass="sortable" />
			<display:column property="cdCanalVenda" title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" />
			<display:column property="nmCanalVenda" title="Nome Grupo Comercial" sortable="true" headerClass="sortable" />
		</display:table>
</c:if>
<c:if test='${composicaoPromocaoTOList != null && (acao eq "orderItem" && element eq "displayComposicaoPromocaoTOList")}'>
		<display:table name="composicaoPromocaoTOList" id="composicaoPromocaoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderItem.do,displayComposicaoPromocaoTOList" class="tabela-padrao-new tablesorter table_body" >
		    <display:column property="nomeServico" title="Nome do Serviço" sortable="true" headerClass="sortable" />
		    <display:column property="nomeDesconto" title="Nome do Desconto" sortable="true" headerClass="sortable" />
		    <display:column property="nomeSolicitacao" title="Nome da Solicitacao" sortable="true" headerClass="sortable" />
		    <display:column property="validadeDesconto" title="Validade do Desconto (Meses)" sortable="true" headerClass="sortable" />
		    <cata:temPermissao acao="excluirItemOfertaBandaLarga">
		        <c:if test="${promocaoTO.alteravel}">
		            <display:column title="Excluir" headerClass="sortable">
		                <img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="remove('${composicaoPromocaoTO.idComposicao}','${composicaoPromocaoTO.idServico}','${composicaoPromocaoTO.idSolicitacao}')" style="cursor: pointer;"/>
		            </display:column>
		        </c:if>                
		    </cata:temPermissao>
		</display:table>
</c:if>
</html:form>
<html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>