<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<html:form styleId="categoriaForm" action="/br/com/vivo/catalogoPRS/pageflows/param/categoria/search.do">
<c:if test="${categoriaScoreForm.cadastrar == true}"> 
	<catalogo:contentBox title="Cadastro de Categoria" >

			<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;" >
				<label for="nmCategoriaScore" style="text-align: right; width:82px;">Categoria:<font size="1px" color="#EEB422">*</font></label>
				<html:text styleId="nmCategoriaScore" property="nmCategoriaScore" style="width:200px;"/>
			</div>

			<div class="fleft" >
				<label for="nmClassificacaoCategoriaScore" style="text-align: right; width: 82px;">Classificação:<font size="1px" color="#EEB422">*</font></label>
				<c:if test="${categoriaScoreForm.idCategoriaScore != null && categoriaScoreForm.idCategoriaScore != 0}">
					<html:select property="idClassificacaoCategoriaScore" styleId="idClassificacaoCategoriaScore" style="width:200px;" styleClass="required">
                              <html:option value="">--Selecione--</html:option>
                               <c:forEach var="classificacaoCategoriaScoreTO" items="${classificacaoCategoriaScoreList}">
                                   <html:option value="${classificacaoCategoriaScoreTO.idClassificacaoCategoriaScore}">${classificacaoCategoriaScoreTO.nmClassificacaoCategoriaScore}</html:option>
                               </c:forEach>
                         	</html:select>
                        	
				</c:if>
				<c:if test="${categoriaScoreForm.idCategoriaScore == null || categoriaScoreForm.idCategoriaScore == 0}">
					<html:select property="idClassificacaoCategoriaScore" styleId="idClassificacaoCategoriaScore" style="width:200px;" styleClass="required">
                              <html:option value="">--Selecione--</html:option>
                               <c:forEach var="classificacaoCategoriaScoreTO" items="${classificacaoCategoriaScoreList}">
                                   <html:option value="${classificacaoCategoriaScoreTO.idClassificacaoCategoriaScore}">${classificacaoCategoriaScoreTO.nmClassificacaoCategoriaScore}</html:option>
                               </c:forEach>
                         	</html:select>
				</c:if>
			</div>
			<br clear="all"/><br clear="all"/>
			<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;" >
				<label for="cdCategoriaScore" style="text-align: right; width: 82px;">Código:<font size="1px" color="#EEB422">*</font></label>
				<html:text styleId="cdCategoriaScore" property="cdCategoriaScore" style="width: 200px;"/>
			</div>
			<br/><br/>
			<div class="barra"></div>
			<div class="botao">
				<html:button property="botao_cancelar_form" styleId="botao_cancelar_form" onClick="clearPage()" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title=""/>
				<span>&nbsp;</span>
				<cata:temPermissao acao="gravarCategoria">
					<html:button property="botao_gravar_form" styleId="botao_gravar_form" onClick="save()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
				</cata:temPermissao>
			</div>
	</catalogo:contentBox>
</c:if>
</html:form>