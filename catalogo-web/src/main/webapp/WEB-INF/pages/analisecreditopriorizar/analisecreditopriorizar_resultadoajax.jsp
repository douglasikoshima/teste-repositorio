<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form styleId="acaoForm" action="/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/pesquisarOferta.do">
<div id="caixa" class="detalharAgrupadores">
<!--  Aba !-->
<span id="abas"><a href="#aba1" id="aba1Link" class="selected">Agrupador Comercial</a></span>
<ul id="conteudos">
	<li id="aba1" class="contentRel">
    	<div id="agrupadorComer"></div>

<c:if test="${analisePriorizarTOList != null}">
        <div >  
           <table>
           	<tr>
          		<td><div class="linerow bold" style='width: 25px;'>Agrupador: </div><div>${epsTO.nmEps}&nbsp;&nbsp;</div></td>
          		<td><div class="linerow bold" style='width: 20px;'>Score: </div><div>${analiseCreditoTO.cdCor}</div></td>
			</tr>
		   </table>             
        </div> 
        <div class="clear"></div>    
        <div class="clear"></div>        
        <input type="hidden" id="idAnaliseCredito" value="${analiseCreditoTO.idAnaliseCredito}" />
        <!-- Monta caixa dos Selects Não priorizados  !-->  
        <div id="sidea">        
            <span> Não Configurado no Agrupador </span>  
            <div class="clear">
                <html:select property="select1" styleId="select1" multiple="true">  
                    <c:forEach var="analisePriorizarTO" items="${analisePriorizarTOList}">
                        <c:if test="${analisePriorizarTO.cdPrioridade == null}">
                            <html:option value="${analisePriorizarTO.idOfertafixaCreditoScore}">${analisePriorizarTO.cdOfertafixa} - ${analisePriorizarTO.dsOfertafixa}</html:option>
                        </c:if>
                    </c:forEach>  
                </html:select>
            </div>  
        </div>
        <!-- Controle dos botões de enviar e retornar as ofertas  !-->      
        <div id="middle">
            <html:button property="addAll" styleId="addAll" onclick="seleciona_todos('select1', true)" styleClass="btNavegacao44" value="&#9658;&#9658;" /><span>&nbsp;</span>
            <html:button property="add" styleId="add" onClick="adicionar()"  styleClass="btNavegacao44" value="&#9658;" /><span>&nbsp;</span>
            <html:button property="remove" styleId="remove" onClick="remover()" styleClass="btNavegacao44" value="&#9668;" /><span>&nbsp;</span>
            <html:button property="removeAll" styleId="removeAll" onclick="deseleciona_todos('select2', true)" styleClass="btNavegacao44" value="&#9668;&#9668;"/><span>&nbsp;</span>
        </div>
        <!-- Monta caixa dos Selects Priorizados  !-->  
        <div id="sideB">
            <span> Priorizadas </span>
            <div class="clear">
                <html:select property="select2" styleId="select2" multiple="true">
                    <c:forEach var="analisePriorizarTO" items="${analisePriorizarTOList}">
                        <c:if test="${analisePriorizarTO.cdPrioridade != null}">
                            <html:option styleClass="priorizada"  value="${analisePriorizarTO.idOfertafixaCreditoScore}">${analisePriorizarTO.cdOfertafixa} - ${analisePriorizarTO.dsOfertafixa}</html:option>
                        </c:if>
                    </c:forEach>                
                </html:select> 
            </div>
        </div>
        <!-- Controle dos botões de UP e Down das Ofertas  Priorizadas !--> 
        <div id="middle">
            <html:button property="up" styleId="up" onclick="ReorderFields('up', 'select2');return false;"  styleClass="btNavegacao44" value="&#9650;"/><span>&nbsp;</span> 
            <html:button property="down" styleId="down" onclick="ReorderFields('down', 'select2');return false;" styleClass="btNavegacao44" value="&#9660;"/><span>&nbsp;</span>
        </div>
    
        <!-- botão Gravar  !-->
        <div class="barra"></div>
        <cata:temPermissao acao="gravarAnaliseCreditoPrioridade">
            <div class="botao">
                <html:button property="btngravarAgrupador" styleId="btngravarAgrupador" onClick="gravarPriorizacao()" styleClass="btNavegacao74" value="Gravar" alt="Gravar Prioridade" title=""/><span>&nbsp;</span>
            </div>
        </cata:temPermissao>
</c:if>
	</li>                            
    	</ul>   

 </div>
<input type="hidden" name="${type_msg}" id="${type_msg}" value="${msg}"> 
</html:form>