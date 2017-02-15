<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<catalogo:popupPadrao>
		<div id="div_erro_selecionar_matriz" style="position: relative"></div>
		<input id="larguraPopup" value="600px" class="hide"/>
		<input id="alturaPopup" value="200px" class="hide"/>
		<div id="popup_selecionar_matriz_oferta" style="width: 100%">
			<c:set var="nomeBox" scope="application"  value="Selecionar Matriz Oferta"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<div align="left">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/addMatrizOfertaSession.do">
					<html:hidden property="idOfertaSap" value="${idOfertaSap}"/>
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
		            <div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">Matriz Oferta:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select property="idMatrizOferta" styleClass="required" styleId="select_matriz_oferta" style="width:250px;">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="item" items="${matrizOferta}">
 								<html:option value="${item.idMatrizOferta}"> ${item.nmMatrizOferta}</html:option>
							</c:forEach>
						</html:select>
					</div>
					<br clear="all" /><br clear="all" /><br clear="all" />
					<br clear="all" /><br clear="all" />
					<div style="height: 100px">&nbsp;</div>
					<div class="barra"></div>
					<div class="botao" style="margin-right: 2px;">
						<html:button property="cancelar" styleId="botao_cancelar_matriz_oferta" onClick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Cancelar" />
						<span>&nbsp;</span>
						<html:button property="gravar" styleId="botao_gravar_matriz_oferta" onClick="clearAndShow('div_pesquisa_planos');if(send(this, 'div_pesquisa_planos', null, 'div_erro_selecionar_matriz')){fecharPopup('popup1');return false};" styleClass="btNavegacao74" value="Gravar"  />
					</div>
				</html:form>
			</div>	
			<jsp:include page="/templates/box_pos.jsp"/>
		</div>
</catalogo:popupPadrao>
