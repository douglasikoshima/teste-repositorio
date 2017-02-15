<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>


<catalogo:popupConfirmacao>

		<br/>
		<br/>
		
		<html:hidden property="alturaPopup" styleId="alturaPopup" value="150" />
		<html:hidden property="larguraPopup" styleId="larguraPopup" value="400" />
		<%-- <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/alterarItensMatrizOferta.do"> --%>						
			<c:set var="dataAtual" value="<%= new java.util.Date() %>"/>
			<br clear="all" />
			<fmt:formatDate value="${dataAtual}" pattern="dd/MM/yyyy" var="dataAtualFmt"/>
			<strong>O Pre&ccedil;o do Produto valer&aacute; a partir de ${dataAtualFmt}, Confirma?</strong><br/><br/>
			<br clear="all" />
			<html:button property="botao_sim" styleId="botao_sim" onclick="$('tbDtInicial').value='${dataAtualFmt}';$('btnGravar').onclick();fecharPopup('popup1');" styleClass="btNavegacao74" value="Sim" />
		<%-- </html:form> --%>
		&nbsp;
		<html:button property="btn_nao" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Não" />
		<br clear="all" /><br clear="all" />
</catalogo:popupConfirmacao>