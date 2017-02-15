<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/search.do" >

<c:if test='${agrupadorDisponibilidadeByEps != null && acaoCarregarAba eq "carregarAba"}'>
    <fieldset class="content accordion">
        <h3 id="gc">Grupo Comercial<span class="required">*</span></h3>
        <div id="montaraccordion"> 
        	<div id="conteudoDisponibilidadePromocaoFixaTOList">
	            <div id="displayDisponibilidadePromocaoFixaTOList">
					<display:table name="agrupadorDisponibilidadeByEps.disponibilidadePromocaoFixaTOList" id="disponibilidadePromocaoFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderDisponibilidade.do,displayDisponibilidadePromocaoFixaTOList" class="tabela-padrao-new tablesorter table_body" >
		                <display:column property="nmEps" title="Nome do Agrupador" sortable="true" headerClass="sortable" />
		                <display:column property="cdCanalVenda" title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" />
		                <display:column property="nmCanalVenda" title="Nome Grupo Comercial" sortable="true" headerClass="sortable" />
		            </display:table> 
	            </div>            
	             <c:if test='${promocaoTO.disponibilidadeAlteracao == ""}'>	            
	    	        <div class="barra" id="barra_novo_canalvenda"></div>
	        	    <div class="botao" id="botaoCarregarFormularioCanalVenda">
	            	    <html:button property="botao_novo_canalvenda" styleId="botao_novo_canalvenda" onClick="carregarFormularioCanalVenda()" styleClass="btNavegacao74" value="Configurar" alt="Configurar" title=""/><span>&nbsp;</span>
		            </div> 
		         </c:if>           
		        <div id="pesquisarCanalVenda"></div>
	        </div>
        </div>
    </fieldset>           
</c:if>

<c:if test='${agrupadorDisponibilidadeByEps != null && acaoAdicionarCanal eq "adicionarCanal"}'>
	<div id="displayDisponibilidadePromocaoFixaTOList">
		<display:table name="agrupadorDisponibilidadeByEps.disponibilidadePromocaoFixaTOList" id="disponibilidadePromocaoFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderDisponibilidade.do,displayDisponibilidadePromocaoFixaTOList" class="tabela-padrao-new tablesorter table_body" >
		    <display:column property="nmEps" title="Nome do Agrupador" sortable="true" headerClass="sortable" />
		    <display:column property="cdCanalVenda" title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" />
		    <display:column property="nmCanalVenda" title="Nome Grupo Comercial" sortable="true" headerClass="sortable" />
		</display:table> 
	</div>            
	    <div class="barra" id="barra_novo_canalvenda"></div>
	    <div class="botao" id="botaoCarregarFormularioCanalVenda">
	        <html:button property="botao_novo_canalvenda" styleId="botao_novo_canalvenda" onClick="carregarFormularioCanalVenda()" styleClass="btNavegacao74" value="Configurar" alt="Configurar" title=""/><span>&nbsp;</span>
		</div>     	            
	<div id="pesquisarCanalVenda"></div>
</c:if>
           
<c:if test='${epsTOList != null && acaoCarregarFormularioCanalVenda eq "carregarFormularioCanalVenda"}'>
<div id="carregarFormularioCanalVenda">
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
</div>
</c:if>

<c:if test='${pesquisaCanalVendaTOList != null && acaoPesquisarCanalVenda eq "pesquisarCanalVenda"}'>
	<input type="hidden" id="idEps2" value="${idEps2}">
	<div id="displaySearchCanalVendaTOList">
		<display:table name="pesquisaCanalVendaTOList" id="canalVendaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderDisponibilidade.do,displaySearchCanalVendaTOList" class="tabela-padrao-new tablesorter table_body" >
			<display:column title="&nbsp;<input class='checkbox ckbCanalVendaTodos' type='checkbox' id='ckbCanalVendaTodos' />" headerClass="" style="text-align:center;">
				<input type="checkbox" class="checkbox ckbCanalVenda" value="${canalVendaTO.idCanalVenda}" id="idCanalVenda" ${canalVendaTO.checked} ${canalVendaTO.disabled} />
			</display:column>
			<display:column property="epsTO.nmEps" title="Nome do Agrupador" sortable="true" headerClass="sortable" />
			<display:column property="cdCanalVenda" title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" />
			<display:column property="nmCanalVenda" title="Nome Grupo Comercial" sortable="true" headerClass="sortable" />
		</display:table> 
	</div>
	<div id="botaoAtualizarCanalVenda">
		<div class="barra"></div>
		<div class="botao">
		    <html:button property="botao_atualizar_canalvenda" styleId="botao_atualizar_canalvenda" onClick="adicionarCanalVenda()" styleClass="btNavegacao74" value="Atualizar" alt="Atualizar" title=""/><span>&nbsp;</span>
		</div>
	</div>		
</c:if>

<c:if test='${pesquisaCanalVendaTOList != null && acaoPesquisarCanal eq "pesquisarCanal"}'>
	<div id="displaySearchCanalVendaTOList">
		<display:table name="pesquisaCanalVendaTOList" id="canalVendaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderDisponibilidade.do,displaySearchCanalVendaTOList" class="tabela-padrao-new tablesorter table_body" >
			<display:column title="&nbsp;<input class='checkbox ckbCanalVendaTodos' type='checkbox' id='ckbCanalVendaTodos' />" headerClass="" style="text-align:center;">
				<input type="checkbox" class="checkbox ckbCanalVenda" value="${canalVendaTO.idCanalVenda}" id="idCanalVenda" ${canalVendaTO.checked} ${canalVendaTO.disabled} />
			</display:column>
			<display:column property="epsTO.nmEps" title="Nome do Agrupador" sortable="true" headerClass="sortable" />
			<display:column property="cdCanalVenda" title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" />
			<display:column property="nmCanalVenda" title="Nome Grupo Comercial" sortable="true" headerClass="sortable" />
		</display:table> 
	</div>
</c:if>

<c:if test='${pesquisaCanalVendaTOList != null && acaoGravar eq "gravarCanalVenda"}'>
    <div class="barra" id="gravarCanalVendaBarra"></div>
    <div class="botao" id="gravarCanalVenda">
         <html:button property="botao_gravar_disponibilidade" styleId="botao_gravar_disponibilidade" onClick="gravarDisponibilidade()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title="" style="display:block"/><span>&nbsp;</span>
     </div>    
</c:if>	
</html:form>
<html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>