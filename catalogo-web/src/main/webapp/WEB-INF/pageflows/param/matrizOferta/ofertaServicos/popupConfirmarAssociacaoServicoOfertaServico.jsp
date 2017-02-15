<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupConfirmacao>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/efetivarAssociasaoServicoOfertaServico.do"  styleId="efetivar_associasao_servico_oferta_servico">	
			<div>
				<html:hidden styleId="cd_oferta_servico" property="cdOfertaServico" value="${cdOfertaServico}"/>
 				<html:hidden styleId="id_oferta_servico" property="idOfertaServico" value="${idOfertaServico}"/>
				<html:hidden styleId="lista_id_servico" property="listaIdServico" value="${listaIdServico}"/>
				<br clear="all" /><br clear="all" /> <br clear="all" />
				Os servi&ccedil;os selecionados ser&atilde;o associados a Oferta de Servi&ccedil;o: <b>${cdOfertaServico}</b>
				<br clear="all" /><br clear="all" /><br clear="all" />				
				<input type="button" id="botao_sim" onclick="send(this, null, null, 'alterar_oferta_servicos');fecharPopup('popup2');return false" title="Associar Serviço" alt="Associar Serviço"  class="btNavegacao74" value="Sim"/>
				&nbsp;
				<input type="button" onclick="fecharPopup('popup2');return false" class="btNavegacao74" value="Não"/>
				<br clear="all" /><br clear="all" />
			</div>
		</html:form>	
</catalogo:popupConfirmacao>