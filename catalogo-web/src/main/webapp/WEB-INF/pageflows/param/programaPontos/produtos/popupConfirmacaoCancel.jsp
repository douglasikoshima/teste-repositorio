<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>

<catalogo:popupConfirmacao>	
		<br/>
		<br/>
		<input type="hidden" id="alturaPopup" value="150"/>
		<input type="hidden" id="larguraPopup" value="400"/>
		<br clear="all" />
		<strong>Você deseja cancelar o Filtro de Disponibilidade por Ação e Canal?</strong><br/><br/>
		<br clear="all" />
		<input type="button" id="botao_sim" onclick="clearAndShow('disp_acao_canal');fecharPopup('popup1');" class="btNavegacao74" value="Sim"/>
		&nbsp;
		<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Não"/>
		<br clear="all" /><br clear="all" />
</catalogo:popupConfirmacao>
