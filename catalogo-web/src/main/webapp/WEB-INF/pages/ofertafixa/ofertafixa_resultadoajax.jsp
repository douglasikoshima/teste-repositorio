<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregar.do" >

<c:if test='${cidadeTOList != null && acao eq "carregar"}'>
    <html:select property="idCidade" styleId="idCidade">
        <html:option value="">-- Selecione --</html:option>
        <c:forEach items="${cidadeTOList}" var="cidadeTO" }>
            <html:option value="${cidadeTO.idCidade}">${cidadeTO.nmCidade}</html:option>
        </c:forEach>
    </html:select>
</c:if>
<c:if test='${areaRegistroTOList != null && acao eq "carregar"}'>
    <html:select property="idAreaRegistro" styleId="idAreaRegistro" onchange="carregarDependentes(this)">
        <html:option value="">-- Selecione --</html:option>
        <c:forEach items="${areaRegistroTOList}" var="areaRegistroTO" }>
            <html:option value="${areaRegistroTO.idAreaRegistro}">${areaRegistroTO.codigoArea}</html:option>
        </c:forEach>
    </html:select>
</c:if>
<c:if test='${solicitacaoComercalFixaTONormalList != null && acao eq "carregar"}'>
    <html:select property="idSolicitacaoComercialNormal" styleId="idSolicitacaoComercialNormal${neo}" onchange="carregarDependentes(this)" >
        <html:option value="">-- Selecione --</html:option>
        <c:forEach items="${solicitacaoComercalFixaTONormalList}" var="solicitacaoComercialFixaTO" }>
            <html:option value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}&${solicitacaoComercialFixaTO.cdoperacaocomercial}">${solicitacaoComercialFixaTO.nmSolicitacaoComercial}</html:option>
        </c:forEach>
    </html:select>
</c:if>
<c:if test='${servicoFixaTOPlanoList != null && acao eq "carregar"}'>
    <html:select property="idServicoPlano" styleId="idServicoPlano${neo}" onchange="carregarDependentes(this)">
        <html:option value="">-- Selecione --</html:option>
        <c:forEach items="${servicoFixaTOPlanoList}" var="servicoFixaTO">
            <html:option value="${servicoFixaTO.idServico}&${servicoFixaTO.cdPS}">${servicoFixaTO.nmComercial}</html:option>
        </c:forEach>
    </html:select>
</c:if>
<c:if test='${solicitacaoComercalFixaTOPreCadastroList != null && acao eq "carregar"}'>
    <html:select property="idSolicitacaoComercialPreCadastro" styleId="idSolicitacaoComercialPreCadastro${neo}" onchange="carregarDependentes(this)">
        <html:option value="">-- Selecione --</html:option>
        <c:forEach items="${solicitacaoComercalFixaTOPreCadastroList}" var="solicitacaoComercialFixaTO">
            <html:option value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}&${solicitacaoComercialFixaTO.cdoperacaocomercial}">${solicitacaoComercialFixaTO.nmSolicitacaoComercial}</html:option>
        </c:forEach>
    </html:select>
</c:if>
<c:if test='${solicitacaoComercalFixaTOPlanoList != null && acao eq "carregar"}'>
    <html:select property="idSolicitacaoComercialPlano" styleId="idSolicitacaoComercialPlano${neo}" onchange="carregarDependentes(this)">
        <html:option value="">-- Selecione --</html:option>
        <c:forEach items="${solicitacaoComercalFixaTOPlanoList}" var="solicitacaoComercialFixaTO">
            <html:option value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}&${solicitacaoComercialFixaTO.cdoperacaocomercial}">${solicitacaoComercialFixaTO.nmSolicitacaoComercial}</html:option>
        </c:forEach>
    </html:select>
</c:if>
<c:if test='${servicoFixaTOList != null && acao eq "carregar"}'>
    <html:select property="idServicoOfertaComplementar" styleId="idServicoOfertaComplementar" onchange="carregarDependentes(this)" style="width: 340px !important;">
        <html:option value="">--Selecione--</html:option>
        <c:forEach items="${servicoFixaTOList}" var="servicoFixaTO">
            <html:option value="${servicoFixaTO.idServico}&${servicoFixaTO.cdPS}">${servicoFixaTO.nmComercial}</html:option>
        </c:forEach>        
    </html:select>
</c:if>
<c:if test='${ofertafixaTOList != null && ((acao eq "order" && element eq "pesquisaOfertas" ) || acao eq "search" || acao eq "gravar" || acao eq "excluir" )}'>
    <catalogo:contentBox title="Resultado da Pesquisa" doubt="false" requiredFields="false">
        <display:table name="ofertafixaTOList" id="ofertafixa" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false"  requestURI="pesquisaOfertas" class="tabela-padrao-new tablesorter table_body" >
            <display:column title="Nome Oferta" sortable="true" headerClass="sortable"> 
                <a href="javascript:visualizarOferta('${ofertafixa.idOfertafixa}')">${ofertafixa.cdOfertafixa} - ${ofertafixa.dsOfertafixa}</a>
            </display:column>
            <display:column property="servicoOfertaFixaTOLinha.nome" title="Servi&ccedil;o Linha" sortable="true" headerClass="sortable" />
            <display:column property="servicoOfertaFixaTOPlano.nome" title="Servi&ccedil;o Plano" sortable="true" headerClass="sortable" />
            <display:column property="periodoVigencia" title="Período de Vigência" sortable="false" headerClass="sortable" />
            <display:column title="Status" headerClass="sortable">
                <div class='clean icon'>
                    <c:if test="${ofertafixa.pendenteConfiguracao}">
                        <div class='iconalert left'></div>
                    </c:if>
                    <div class='${ofertafixa.status == "vigente" ? "iconativo" : ofertafixa.status == "finalizado" ? "iconinativo" : "iconwait"}'></div>
                </div>
            </display:column>
            <cata:temPermissao acao="alterarOfertafixa">
                <display:column title="Alterar" headerClass="sortable">
                    <c:if test='${ofertafixa.alteravel}'>
                        <div class="clean"><img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" class="btnAlterarOfertas" alt="Alterar" onclick="alterarOferta('${ofertafixa.idOfertafixa}')"></div>
                    </c:if>
                </display:column>
            </cata:temPermissao>
            <display:column title="Copiar" headerClass="sortable" style="block">
           		<div class="clean">
                   <img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif" class="btnCopiarOfertas" alt="Copiar" onclick="copiarOferta('${ofertafixa.idOfertafixa}')" >
           		</div>
           	</display:column>                        
            <cata:temPermissao acao="excluirOfertafixa">
                <display:column title="Excluir" headerClass="sortable">
                    <c:if test='${ofertafixa.excluivel}'>
                        <div class="clean"><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas" alt="Excluir" onclick="excluir('${ofertafixa.idOfertafixa}')"></div>
                    </c:if>
                </display:column>
            </cata:temPermissao>
                     
        </display:table>
    </catalogo:contentBox>
</c:if>
<c:if test='${tipoServicoTOList != null && acao eq "carregarFormularioNovaOfertaComplementar"}'>
    <catalogo:contentBox title="Nova Oferta Complementar" doubt="true" requiredFields="true">
        <div class="boxline">
            <div class="linerow bold">Tipo Servi&ccedil;o:<span class="required">*</span></div>
            <div class="linerow clear">
                <html:select property="idTipoServico" styleId="idTipoServico" onchange="carregarDependentes(this)">
                    <html:option value="">--Selecione--</html:option>
                    <c:forEach var="tipoServicoTO" items="${tipoServicoTOList}">
                        <html:option value="${tipoServicoTO.idTipoServico}">${tipoServicoTO.dscTipoServico}</html:option>
                    </c:forEach>
                </html:select>
            </div>
        </div>
        
        
        <div class="boxline">
        	<div class="linerow bold">PS de Servi&ccedil;o:<span class="required">*</span></div>
            <div class="linerow clear" id="selectPsServivo">
            <table>
            	<tr>
            		<td><html:text property="psServicoNew" styleId="psServicoNew" style="width: 80px" onkeypress="return validarNumeros(event);" disabled="disabled"/></td>
                    <td><html:button property="botao_ps_servico" styleId="botao_ps_servico" onClick="carregarDependentes(psServicoNew)" style='width: 20px; display:${(ofertafixaTO.status eq "" || ofertafixaTO.status eq "naoiniciado") ? "block" : "none"} !important' styleClass="btnMais" value=">" alt=">" title=""/></td>            		
            	</tr>
            </table>
		    </div>
        </div>
       	
        <div class="boxline" style="width: 380px !important;">
            <div class="linerow bold">Nome Servi&ccedil;o:<span class="required">*</span></div>
            <div class="linerow clear" id="selectServicoOfertaComplementar" style="width: 370px !important;">
                <html:select property="idServicoOfertaComplementar" styleId="idServicoOfertaComplementar" disabled="disabled" style="width: 340px !important;">
                    <html:option value="">--Selecione--</html:option>
                </html:select>
            </div>
        </div>
        <div class="barra"></div>
        <div class="botao" >
        <div class="botao"  id="gravarOfertas" >
            <html:button property="botao_pesquisar_complementar" styleId="botao_pesquisar_complementar" onClick="carregarFormularioNovaOfertaComplementar('resultadoPesquisaComplementar')" styleClass="btNavegacao74" style='display:${(ofertafixaTO.status eq "" || ofertafixaTO.status eq "naoiniciado") ? "block" : "none"} !important' value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
        </div>        
    </catalogo:contentBox>
    <div id="resultadoPesquisaComplementar"></div>
</c:if>
<c:if test='${solicitacaoComercialFixaTOComplementarList != null && acao eq "carregarFormularioNovaOfertaComplementar"}'>
    <catalogo:contentBox title="Resultado da Pesquisa" doubt="false" requiredFields="false">
    	<div id="displaySolicitacaoComercialFixaTOComplementarList">
	        <display:table name="solicitacaoComercialFixaTOComplementarList" id="solicitacaoComercialFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displaySolicitacaoComercialFixaTOComplementarList" class="tabela-padrao-new tablesorter table_body" >
	            <display:column title="">
	                <input type="checkbox" class="checkbox ckbInput changeable" value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}" />
	            </display:column>
	            <display:column property="cdServicoCatalogo" title="C&oacute;digo do Servi&ccedil;o" sortable="true" headerClass="sortable" />        
	            <display:column property="nmServicoCatalogo" title="Nome do Servi&ccedil;o" sortable="true" headerClass="sortable" />
	            <display:column property="nmSolicitacaoComercial" title="Nome da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" />
	            <display:column property="prazoMaximoVigencia" title="Tempo de Vig&ecirc;ncia" sortable="true" headerClass="sortable" />
	        </display:table>
        </div>
        <div class="barra"></div>
        <div class="botao" >
        <div class="botao"  id="gravarOfertas" >
            <cata:temPermissao acao="adicionarOfertafixaComplementar">
                <html:button property="botao_adicionar_complementar" styleId="botao_adicionar_complementar" onClick="adicionarComplementar()" styleClass="btNavegacao74" style='display:${(ofertafixaTO.status eq "" || ofertafixaTO.status eq "naoiniciado") ? "block" : "none"} !important' value="Adicionar" alt="Adicionar" title=""/><span>&nbsp;</span>
            </cata:temPermissao>
        </div>           
    </catalogo:contentBox>
</c:if>
<c:if test='${solicitacaoComercialFixaTOComplementarList != null && (acao eq "order" && element eq "displaySolicitacaoComercialFixaTOComplementarList")}'>
    <display:table name="solicitacaoComercialFixaTOComplementarList" id="solicitacaoComercialFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displaySolicitacaoComercialFixaTOComplementarList" class="tabela-padrao-new tablesorter table_body" >
        <display:column title="">
            <html:checkbox property="idSolicitacaoComercial" styleId="checkboxIdSolicitacaoComercial" styleClass="checkbox ckbInput changeable" value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}"/>
        </display:column>
        <display:column property="cdServicoCatalogo" title="C&oacute;digo do Servi&ccedil;o" sortable="true" headerClass="sortable" />        
        <display:column property="nmServicoCatalogo" title="Nome do Servi&ccedil;o" sortable="true" headerClass="sortable" />
        <display:column property="nmSolicitacaoComercial" title="Nome da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" />
        <display:column property="prazoMaximoVigencia" title="Tempo de Vig&ecirc;ncia" sortable="true" headerClass="sortable" />
    </display:table>
</c:if>
<c:if test='${ofertafixaDisponibilidadeTO != null && ((acao eq "order" && element eq "displayAgrupadoCNLTOList") || acao eq "excluirAgrupadorCNL")}'>
    <display:table name="ofertafixaDisponibilidadeTO.agrupadorCNLTOList" id="agrupadorCNLTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displayAgrupadoCNLTOList" class="tabela-padrao-new tablesorter table_body" >
        <display:column property="nmUf" title="UF" sortable="true" headerClass="sortable" />
        <display:column property="cdAreaRegistro" title="&Aacute;rea" sortable="true" headerClass="sortable" />
        <display:column property="nmCidade" title="Munic&iacute;pio" sortable="true" headerClass="sortable" />
        <display:column property="cdLocalidade" title="CNL" sortable="true" headerClass="sortable" />
        <cata:temPermissao acao="excluirOfertafixa">
            <c:if test='${disabled != "true"}'>
                <display:column title="Excluir" headerClass="sortable">
                        <div class="clean"><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas" alt="Excluir" onclick="excluirCNLConfigurada('${agrupadorCNLTO.idCidade}')"></div>
                </display:column>
            </c:if>
        </cata:temPermissao>
    </display:table>
</c:if>
<c:if test='${ofertafixaDisponibilidadeTO.canalVendaTOList != null && (acao eq "order" && element eq "displayCanalVendaTOList")}'>
	<display:table name="ofertafixaDisponibilidadeTO.canalVendaTOList" id="canalVendaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displayCanalVendaTOList" class="tabela-padrao-new tablesorter table_body" >
    	<display:column property="epsTO.nmEps" title="Nome do Agrupador" sortable="true" headerClass="sortable" />
        <display:column property="cdCanalVenda" title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" />
		<display:column property="nmCanalVenda" title="Nome Grupo Comercial" sortable="true" headerClass="sortable" />
	</display:table>
</c:if>
<c:if test='${ofertafixaTO != null && ((acao eq "order" && element eq "displayOfertaComplementarList")|| acao eq "excluirOfertaComplementar")}'>
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
</c:if>
<c:if test='${pesquisaCanalVendaTOList != null && (acao eq "order" && element eq "displaySearchCanalVendaTOList")}'>
	<display:table name="pesquisaCanalVendaTOList" id="canalVendaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displaySearchCanalVendaTOList" class="tabela-padrao-new tablesorter table_body" >
		<display:column title="&nbsp;<input class='checkbox ckbCanalVendaTodos' type='checkbox' id='ckbCanalVendaTodos' />" headerClass="" style="text-align:center;">
			<html:checkbox property="idCanalVenda" styleClass="checkbox ckbCanalVenda" value="${canalVendaTO.idCanalVenda}" styleId="idCanalVenda" />
		</display:column>
		<display:column property="epsTO.nmEps" title="Nome do Agrupador" sortable="true" headerClass="sortable" />
		<display:column property="cdCanalVenda" title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" />
		<display:column property="nmCanalVenda" title="Nome Grupo Comercial" sortable="true" headerClass="sortable" />
	</display:table> 
</c:if>
<c:if test='${ofertafixaDisponibilidadeTO.areaRegistroTOList != null && (acao eq "order" && element eq "displayAreaRegistroTOList")}'>
	<display:table name="ofertafixaDisponibilidadeTO.areaRegistroTOList" id="areaRegistroTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displayAreaRegistroTOList" class="tabela-padrao-new tablesorter table_body" >
		<display:column property="ufTO.nmUf" title="UF" sortable="true" headerClass="sortable" />
		<display:column property="codigoArea" title="&Aacute;rea Registro" sortable="true" headerClass="sortable" />
	</display:table> 
</c:if>
<c:if test='${acao eq "pregravar" || acao eq "validarCompatibilidadeComplementar"}'>
    ${msg}<br><c:forEach var="txt" items="${listaCanalVendaIncompativel}">${txt}<br></c:forEach>
    <div class="barra"></div>
    <div class="botao">
        <html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="cleanMessages()" styleClass="btNavegacao74" value="N&atilde;o" alt="Pesquisar" title=""/><span>&nbsp;</span>
		<c:if test='${acao eq "pregravar"}'>
	        <html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick='gravar("force")' styleClass="btNavegacao74" value="Sim" alt="Novas Ofertas" title=""/><span>&nbsp;</span>
		</c:if>
		<c:if test='${acao eq "validarCompatibilidadeComplementar"}'>
	        <html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick='removerIncompativeis()' styleClass="btNavegacao74" value="Sim" alt="Novas Ofertas" title=""/><span>&nbsp;</span>		
		</c:if>
    </div>
    
</c:if>
</html:form>
<html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>
