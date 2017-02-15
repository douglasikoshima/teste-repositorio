<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupConfirmacao>
	<input id="larguraPopup" value="500px" class="hide"/>
	<input id="alturaPopup" value="100px" class="hide"/>
		<div>
			<br clear="all" /><br clear="all" />
			<c:choose>
				<c:when test="${totalServicosAssociados != 0}">
					N&atilde;o &eacute; poss&iacute;vel excluir a Oferta de Servi&ccedil;os, <br>pois h&aacute; servi&ccedil;os associados a essa Oferta de Servi&ccedil;os.			
					<br clear="all" /><br clear="all" /><br clear="all" />
					<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="OK" />
				</c:when>
				<c:otherwise>
					Voc&ecirc; tem certeza de que deseja excluir esta informa&ccedil;&atilde;o?	
					<br clear="all" /><br clear="all" /><br clear="all" />
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/excluirOfertaServico.do">
						<html:hidden styleId="hidden_id_oferta_servico" property="idOfertaServico" />
						<html:button property="bt_excluir" styleId="bt_excluir" onMouseDown="$('hidden_id_oferta_servico').value=${idOfertaServico};" onclick="send(this, null, null, 'right_section');fecharPopup('popup1');return false" styleClass="btNavegacao74" value="Sim" />
					</html:form>
					&nbsp;
					<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Não" />
				</c:otherwise>
			</c:choose>
</catalogo:popupConfirmacao>