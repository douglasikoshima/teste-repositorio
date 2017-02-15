<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupConfirmacao>
	<html:text property="larguraPopup" styleId="larguraPopup" value="500px" styleClass="hide"/>
	<html:text property="alturaPopup" value="150px" styleClass="hide" />
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			Voc&ecirc; tem certeza de que deseja excluir essa Informa&ccedil;&atilde;o?
			<br clear="all" /><br clear="all" /><b>${nmMatrizOferta}</b>
			<br clear="all" /><br clear="all" /><br clear="all" />
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/excluirCabecalhoMatrizOferta.do">											
				<html:hidden property="idMatrizOferta" value="${idMatrizOferta}" />
				<html:submit bundle="messages" styleId="botao_sim" onclick="send(this, null, null, 'right_section');fecharPopup('popup1');return false;" styleClass="btNavegacao74" value="Sim" titleKey="catalogo.popupAlterarModelo.Sim"/>
			</html:form>
			&nbsp;
			<html:button bundle="messages" property="btn_nao" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Não" titleKey="catalogo.popupAlterarModelo.Não"/>
		</div>
</catalogo:popupConfirmacao>
