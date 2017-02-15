<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<catalogo:popupConfirmacao>
	<input id="larguraPopup" value="500px" class="hide"/>
	<input id="alturaPopup" value="100px" class="hide"/>
		<div>
			Voc&ecirc; tem certeza de que deseja excluir esta informa&ccedil;&atilde;o?
			<br clear="all" /><br clear="all" /><br clear="all" />
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/excluirServicoOfertaServico.do">
				<html:hidden styleId="hidden_id_servico_oferta_servico" property="idServicoOfertaServico" />
				<html:button property="bt_excluir" styleId="bt_excluir" onMouseDown="$('hidden_id_servico_oferta_servico').value=${idServicoOfertaServico};" onclick="send(this, null, null, 'right_section');fecharPopup('popup1');return false" styleClass="btNavegacao74" value="Sim" />
			</html:form>
			&nbsp;
			<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Não"/>
		</div>
</catalogo:popupConfirmacao>