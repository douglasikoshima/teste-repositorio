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
<c:if test='${ofertafixaDisponibilidadeTO != null && acao eq "carregarAba"}'>
    <fieldset class="content accordion">
        <h3 id="uf">Selecione UF</h3>
        <div id="montaraccordion">
        	<div id="conteudoAreaRegistro" >
				<div class="info">[Caso n&atilde;o preencha nenhuma UF a oferta ser&aacute; v&aacute;lida para todas]</div>  
	            <div id="displayAreaRegistroTOList">
                    <display:table name="ofertafixaDisponibilidadeTO.areaRegistroTOList" id="areaRegistroTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displayAreaRegistroTOList" class="tabela-padrao-new tablesorter table_body" >
                        <display:column property="ufTO.nmUf" title="UF" sortable="true" headerClass="sortable" />
			            <display:column property="codigoArea" title="&Aacute;rea Registro" sortable="true" headerClass="sortable" />
			        </display:table> 
		        </div>
			    <c:if test='${ofertafixaTO.disponibilidadeDisabled == ""}'>
                    <div class="barra"></div>
                    <div class="botao">
                        <html:button property="botao_novo_arearegistro" styleId="botao_novo_arearegistro" onClick="carregarFormularioAreaRegistro();" styleClass="btNavegacao74" value="Configurar" alt="Configurar" title=""/><span>&nbsp;</span>
                    </div> 
                </c:if>
                <div id="pesquisarAreaRegistro"></div>
            </div>
        </div>
    </fieldset>
    <fieldset class="content accordion" id="fildsetCNL">
        <h3>CNL</h3>
        <div id="montaraccordion">
        	<div id="conteudoAgrupadorCNL"> 
		        <div id="displayAgrupadoCNLTOList">
		            <display:table name="ofertafixaDisponibilidadeTO.agrupadorCNLTOList" id="agrupadorCNLTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displayAgrupadoCNLTOList" class="tabela-padrao-new tablesorter table_body" >
		                <display:column property="nmUf" title="UF" sortable="true" headerClass="sortable" />
		                <display:column property="cdAreaRegistro" title="&Aacute;rea" sortable="true" headerClass="sortable" />
		                <display:column property="nmCidade" title="Munic&iacute;pio" sortable="true" headerClass="sortable" />
		                <display:column property="cdLocalidade" title="CNL" sortable="true" headerClass="sortable" />
		                <cata:temPermissao acao="excluirOfertafixa">
		                    <c:if test='${ofertafixaTO.disponibilidadeDisabled == ""}'>
		                        <display:column title="Excluir" headerClass="sortable">
		                                <div class="clean"><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas" alt="Excluir" onclick="excluirCNLConfigurada('${agrupadorCNLTO.idCidade}')"></div>
		                        </display:column>
		                    </c:if>
		                </cata:temPermissao>
		            </display:table>        
		        </div>
		        <c:if test='${ofertafixaTO.disponibilidadeDisabled == ""}'>
		            <div class="barra"></div>
		            <div class="botao">
		                <html:button property="botao_novo_cnl" styleId="botao_novo_cnl" onClick="carregarFormularioCNL()" styleClass="btNavegacao74" value="Configurar" alt="Configurar" title=""/><span>&nbsp;</span>
		            </div> 
		        </c:if>
		        <div id="pesquisarCNL"></div>        	
        	</div>
        </div>
    </fieldset>
</c:if>

<c:if test='${ufTOList != null && acao eq "carregarFormularioAreaRegistro"}'>
    <div class="boxline">
        <div class="linerow bold">&nbsp;&nbsp;&nbsp;UF:</div>
        <div class="linerow clear">&nbsp;
            <select id="idUf">
                <option value="">--Selecione--</option>
                <c:forEach var="ufTO" items="${ufTOList}">
                    <option value="${ufTO.idUf}">${ufTO.nmUf}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="boxline">
		<div class="linerow bold">C&oacute;digo:</div>
		<div class="linerow clear">
            <input type="text" id="cdAreaRegistro"/>
        </div>
    </div>
    <div class="barra"></div>
    <div class="botao">
        <html:button property="botao_pesquisar_arearegistro" styleId="botao_pesquisar_arearegistro" onClick="pesquisarAreaRegistro()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
    </div>
    <br/>
    <div id="adicionarAreaRegistro"></div>
</c:if>

<c:if test='${epsTOList != null && acao eq "carregarFormularioCanalVenda"}'>
    <div class="boxline">
        <div class="linerow bold">&nbsp;&nbsp;&nbsp;Agrupador:</div>
        <div class="linerow clear">&nbsp;
            <select id="idEps">
                <option value="">--Selecione--</option>
                <option value="0">--Sem agrupador--</option>
                <c:forEach var="epsTO" items="${epsTOList}">
                    <option value="${epsTO.idEps}">${epsTO.nmEps}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="boxline">
		<div class="linerow bold">C&oacute;digo:</div>
		<div class="linerow clear">
           	<input type="text" id="cdCanalVenda"/>
        </div>
    </div>
    <div class="barra"></div>
    <div class="botao">
        <html:button property="botao_pesquisar_cnl" styleId="botao_pesquisar_cnl" onClick="pesquisarCanalVenda()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
    </div>
    <br/><div id="adicionarCanalVenda"></div>
</c:if>

<c:if test='${ufTOList != null && acao eq "carregarFormularioCNL"}'>
    <div class="boxline">
        <div class="linerow bold">&nbsp;UF:<span class="required">*</span></div>
        <div class="linerow clear">&nbsp;
            <select id="idUf" onchange="carregarDependentes(this)">
                <option value="">--Selecione--</option>
                <c:forEach var="ufTO" items="${ufTOList}">
                    <option value="${ufTO.idUf}">${ufTO.nmUf}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="boxline">
        <div class="linerow bold">&nbsp;&Aacute;rea:<span class="required">*</span></div>
        <div class="linerow clear">
            <div id="selectIdAreaRegistro">&nbsp;
                <select id="idAreaRegistro" disabled="disabled" onchange="carregarDependentes(this)">
                    <option value="">--Selecione--</option>
                </select>
            </div>
        </div>
    </div>
    <div class="boxline">
        <div class="linerow bold">&nbsp;Municipio:<span class="required">*</span></div>
        <div class="linerow clear">
            <div id="selectIdCidade">&nbsp;
                <select id="idCidade" disabled="disabled">
                    <option value="">--Selecione--</option>
                </select>
            </div>
        </div>
    </div>
    <div class="barra"></div>
    <div class="botao">
        <html:button property="botao_pesquisar_cnl" styleId="botao_pesquisar_cnl" onClick="pesquisarCNL()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
    </div>
    <div id="adicionarCNL"></div>
</c:if>
<c:if test='${pesquisaAreaRegistroTOList != null && acao eq "pesquisarAreaRegistro"}'>
	<input type="hidden" id="idUf" value="${idUf}">
	<div id="displaySearchAreaRegistroTOList">
		<display:table name="pesquisaAreaRegistroTOList" id="areaRegistroTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displaySearchAreaRegistroTOList" class="tabela-padrao-new tablesorter table_body" >
			<display:column title="&nbsp;<input class='checkbox ckbAreaRegistroTodos' type='checkbox' id='ckbAreaRegistroTodos' />" headerClass="" style="text-align:center;">
				<input type="checkbox" class="checkbox ckbAreaRegistro" value="${areaRegistroTO.idAreaRegistro}" id="idAreaRegistro" ${areaRegistroTO.checked} />
			</display:column>
            <display:column property="ufTO.nmUf" title="UF" sortable="true" headerClass="sortable" />
            <display:column property="codigoArea" title="&Aacute;rea Registro" sortable="true" headerClass="sortable" />
		</display:table> 
	</div>
	<c:if test='${ofertafixaTO.disponibilidadeDisabled == ""}'>
		<div class="barra"></div>
		<div class="botao">
		    <html:button property="botao_novo_arearegistro" styleId="botao_novo_arearegistro" onClick="adicionarAreaRegistro();" styleClass="btNavegacao74" value="Atualizar" alt="Atualizar" title=""/><span>&nbsp;</span>
		</div> 
	</c:if>
</c:if>
<c:if test='${localidadeTOList != null && acao eq "pesquisarCNL"}'>
    <div>
        <ul>
            <li>
                <div style="display:none;">nao retirar essa div</div>
                <input type="checkbox" class="checkbox ckbLocalidadeTodos changeable" name="todos" id="todos" value="todos" />
                <label>Todos</label>
                <ul>
                    <c:forEach items="${localidadeTOList}" var="localidadeTO" }>
                        <li><input type="checkbox" class="checkbox marcar ckbLocalidade ckbLocalidadeTodos changeable" value="${localidadeTO.idLocalidade}" ${localidadeTO.checked} />${localidadeTO.cdLocalidade} - ${localidadeTO.nmLocalidade}</li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
    </div>
    <div class="barra"></div>
    <div class="botao">
        <cata:temPermissao acao="alterarOfertafixa">
            <html:button property="botao_novo_cnl" styleId="botao_novo_cnl" onClick="adicionarLocalidade(${idCidade})" styleClass="btNavegacao74" value="Atualizar" alt="Atualizar" title=""/><span>&nbsp;</span>
        </cata:temPermissao>
    </div> 

</c:if>
<c:if test='${ofertafixaDisponibilidadeTO.areaRegistroTOList != null && acao eq "adicionarAreaRegistro"}'>
	<div class="info">[Caso n&atilde;o preencha nenhuma UF a oferta ser&aacute; v&aacute;lida para todas]</div>  
    <div id="displayAreaRegistroTOList">
		<display:table name="ofertafixaDisponibilidadeTO.areaRegistroTOList" id="areaRegistroTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displayAreaRegistroTOList" class="tabela-padrao-new tablesorter table_body" >
        	<display:column property="ufTO.nmUf" title="UF" sortable="true" headerClass="sortable" />
            <display:column property="codigoArea" title="&Aacute;rea Registro" sortable="true" headerClass="sortable" />
		</display:table> 
	</div>
    <c:if test='${ofertafixaTO.disponibilidadeDisabled == ""}'>
 		<div class="barra"></div>
     	<div class="botao">
        	<html:button property="botao_novo_arearegistro" styleId="botao_novo_arearegistro" onClick="carregarFormularioAreaRegistro();" styleClass="btNavegacao74" value="Configurar" alt="Configurar" title=""/><span>&nbsp;</span>
		</div> 
	</c:if>
    <div id="pesquisarAreaRegistro"></div>
</c:if>
<c:if test='${ofertafixaDisponibilidadeTO.canalVendaTOList != null && acao eq "adicionarOfertafixaFatorLocalidade"}'>
	<div id="displayAgrupadoCNLTOList">
	    <display:table name="ofertafixaDisponibilidadeTO.agrupadorCNLTOList" id="agrupadorCNLTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="displayAgrupadoCNLTOList" class="tabela-padrao-new tablesorter table_body" >
	        <display:column property="nmUf" title="UF" sortable="true" headerClass="sortable" />
	        <display:column property="cdAreaRegistro" title="&Aacute;rea" sortable="true" headerClass="sortable" />
	        <display:column property="nmCidade" title="Munic&iacute;pio" sortable="true" headerClass="sortable" />
	        <display:column property="cdLocalidade" title="CNL" sortable="true" headerClass="sortable" />
	        <cata:temPermissao acao="excluirOfertafixa">
	            <c:if test='${ofertafixaTO.disponibilidadeDisabled == ""}'>
	                <display:column title="Excluir" headerClass="sortable">
	                        <div class="clean"><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas" alt="Excluir" onclick="excluirCNLConfigurada('${agrupadorCNLTO.idCidade}')"></div>
	                </display:column>
	            </c:if>
	        </cata:temPermissao>
	    </display:table>        
	</div>
	<c:if test='${ofertafixaTO.disponibilidadeDisabled == ""}'>
	    <div class="barra"></div>
	    <div class="botao">
	        <html:button property="botao_novo_cnl" styleId="botao_novo_cnl" onClick="carregarFormularioCNL()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/><span>&nbsp;</span>
	    </div> 
	</c:if>
	<div id="pesquisarCNL"></div>
</c:if>
</html:form>
<html:hidden property="statusOfertafixa" styleId="statusOfertafixa" value="${ofertafixaTO.status}"/>
<html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>