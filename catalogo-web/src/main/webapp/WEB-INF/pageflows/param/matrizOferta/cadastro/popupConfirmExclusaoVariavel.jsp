<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>


<catalogo:popupConfirmacao>
	<html:text property="larguraPopup" styleId="larguraPopup" value="500px" styleClass="hide"/>
	<html:text property="alturaPopup" styleId="alturaPopup" value="150px" styleClass="hide" />
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			Voc&ecirc; tem certeza de que deseja excluir essa Informa&ccedil;&atilde;o?
			<br clear="all" /><br clear="all" />
			<br clear="all" /><br clear="all" /><br clear="all" />
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/excluirVariavelMatrizOferta.do">						
				<html:hidden property="idMatrizOfertaVariaveis" value="${idMatrizOfertaVariaveis}"/>
				<html:submit bundle="messages" styleId="botao_sim" onclick="send(this, null, null, 'div_erro_variaveis_elegibilidade');fecharPopup('popup1');return false;" styleClass="btNavegacao74" value="Sim" titleKey="catalogo.popupAlterarModelo.Sim" />
			</html:form>
			&nbsp;
			<html:button property="btn_nao" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Não" titleKey="catalogo.popupAlterarModelo.Não"/>
		</div>
	
</catalogo:popupConfirmacao>