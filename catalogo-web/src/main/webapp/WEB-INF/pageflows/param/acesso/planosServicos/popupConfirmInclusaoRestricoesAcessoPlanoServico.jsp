<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupConfirmacao>	
	<html:text property="larguraPopup" styleId="larguraPopup"  value="500px" styleClass="hide"/>
	<html:text property="alturaPopup" styleId="alturaPopup" value="150px" styleClass="hide" />
	
	<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/pesquisarAcessoPlanosServicos.do?tp_pesquisa=${tpPesquisa}" styleId="form_pesquisa_restricoes_plano_servico">			
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			Restri&ccedil;&atilde;o gravada com sucesso.
			<br clear="all" /><br clear="all" /><b>
			<br clear="all" /><br clear="all" /><br clear="all" />
			&nbsp;
			<html:hidden styleId="id_plataforma" property="idPlataforma" value="${idPlataforma}" />			
			<html:hidden styleId="ids_perfis" property="idsPerfilPpSp" value="${idsPerfil}" />
			<html:hidden styleId="tp_pesquisa" property="tpPesquisa" value="${tpPesquisa}"/>
			<html:hidden styleId="id_plano" property="idPlano" value="${idPlano}" />
			<html:hidden styleId="id_servico" property="idServico" value="${idServico}" />
			<html:hidden styleId="nm_com_plano_perfil_pop" property="nmComPlanoPerfilPop" value="" />
			<html:hidden styleId="nm_com_plano_perfil_text" property="nmComPlanoPerfilText" value="" />
			<html:hidden styleId="nm_com_servico_perfil_pop" property="nmComServicoPerfilPop" value=""/>
			<html:hidden styleId="nm_com_servico_perfil_text" property="nmComServicoPerfilText" value="" />
			
			<html:button property="btnOk" styleId="btnOk" onclick="clearAndShow('resultado_pesquisa');$('div_incluir_novas_restricoes').hide();send(this, 'resultado_pesquisa', null, 'right_section');fecharPopup('popup1');" StyleClass="btNavegacao74" value="Ok"/>
			
			<logic:present name="idPlataforma">
				<c:remove var="idPlataforma"/>
			</logic:present>
			
			<logic:present name="idsPerfil">
				<c:remove var="idsPerfil"/>			
			</logic:present>
			
			<logic:present name="tpPesquisa">
				<c:remove var="tpPesquisa"/>			
			</logic:present>
			
			<logic:present name="idServico">
				<c:remove var="idServico"/>			
			</logic:present>
			
			<logic:present name="idPlano">
				<c:remove var="idPlano"/>			
			</logic:present>			
			
		</div>
	</html:form> 	
</catalogo:popupConfirmacao>