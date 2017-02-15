<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<catalogo:popUpDefault title="Condições de Pagamento" close="true" >
	<div id="contentAllPopUp">
		<div id="valorCaracteristicaList">
<!-- 		<netui:form action="abrirCondicoes" tagId="formaPagamentoForm" > -->
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/abrirCondicoes.do" styleId="formaPagamentoForm">
				<div style="overflow: auto; height: 300px; "><br /><br />
					<display:table name="condicaoList" id="condicPagamentoTO" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI="" class="tabela-padrao-new tablesorter table_body">
						<display:column property="nmCondicaoPagamento" title="Condição de Pagamento" />
					</display:table>
				</div>
				<div class="botao" style="position: absolute; bottom: 10px;">
					<input class="btOk" type="button" onclick="fecharPopup('popup1');" value="OK" title="OK"/>
				</div>
			</html:form>
<!-- 		</netui:form> -->
		</div>
	</div>
</catalogo:popUpDefault>