<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/search.do" >
<c:if test="${promocaoTO != null}">
	<html:hidden property="cdPromocao" styleId="cdPromocao" value="${promocaoTO.cdPromocao}"/>
	<html:hidden property="inStatusConfiguracao" styleId="inStatusConfiguracao" value="${promocaoTO.inStatusConfiguracao}"/>
	<html:hidden property="idPromocao" styleId="idPromocao" value="${promocaoTO.idPromocao}"/>
    <div class="boxline" id="codOferta">
        <div class="linerow bold"><label for="codigoNew">C&oacute;digo:</label><span class="required">*</span></div>
        <div class="linerow clear"><input type="text" class="changeable" id="cdPromocaoNew" value="${promocaoTO.cdPromocao}" ${promocaoTO.cdPromocaoDisabled} /></div>
    </div>
    <div class="boxline" >
        <div class="linerow bold"><label for="nomeNew">Nome:</label><span class="required">*</span></div>
        <div class="linerow clear"><input type="text" class="changeable" id="nmPromocaoNew" value="${promocaoTO.nmPromocao}" ${promocaoTO.nmPromocaoDisabled} /></div>
    </div>
    <div class="boxline">
        <div class="linerow bold">Data In&iacute;cio Vig&ecirc;ncia:<span class="required">*</span></div>
        <div class="linerow clear"><input class="changeable" type="text" id="dtInicioNew" value='${promocaoTO.dtInicioFormatado != null ? promocaoTO.dtInicioFormatado : ""}' ${promocaoTO.dtInicioDisabled} /></div>
    </div>
    <div class="boxline">
        <div class="linerow bold">Data Fim Vigência:</div>
        <div class="linerow clear"><input class="changeable" type="text" id="dtFimNew" value='${promocaoTO.dtFimFormatado != null ? promocaoTO.dtFimFormatado : ""}' ${promocaoTO.dtFimDisabled} /></div>
    </div>
    
    <!-- botões pesquisar Serviços  !-->
    <div class="barra clear"></div>
    <div class="botao">
        <cata:temPermissao acao="gravarOfertaBandaLarga">
            <c:if test="${promocaoTO.alteravel}">
                <html:button property="botao_gravar" styleId="botao_gravar" onClick="gravar()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
            </c:if>
        </cata:temPermissao>
    </div>
</c:if>
</html:form>
<html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>