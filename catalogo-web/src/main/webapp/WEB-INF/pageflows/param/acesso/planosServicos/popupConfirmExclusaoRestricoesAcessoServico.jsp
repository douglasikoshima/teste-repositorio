<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<catalogo:popupConfirmacao>
	<html:text property="larguraPopup" styleId="larguraPopup" value="500px" styleClass="hide" />
	<html:text property="alturaPopup" styleId="alturaPopup" value="150px" styleClass="hide"/>
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			Deseja efetuar a exclus&atilde;o da restri&ccedil;&atilde;o?
			<br clear="all" /><br clear="all" /><b>
			<br clear="all" /><br clear="all" /><br clear="all" />
			
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/excluirRestricoesAcessoServico.do">
				<html:hidden property="idAcessoServicoExcluirServico" value="${idAcessoServico}" />				
				<html:hidden property="inRestricaoConsultaExcluir" value="${inRestricaoConsulta}"/>				
				<html:hidden property="inRestricaoAtivacaoExcluir" value="${inRestricaoAtivacao}"/>				
				<html:hidden property="inRestricaoDesativacaoExcluir" value="${inRestricaoDesativacao}"/>

				<html:submit bundle="messages" styleId="botao_sim" onclick="send(this, null, null, 'right_section');fecharPopup('popup1');return false;" styleClass="btNavegacao74" value="Sim" titleKey="catalogo.popupAlterarModelo.Sim" />
			</html:form>
			&nbsp;
			<html:button bundle="messages" property="btn_nao" styleId="btn_nao" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Não" titleKey="catalogo.popupAlterarModelo.Não" />
		</div>	
</catalogo:popupConfirmacao>
