<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<catalogo:popUpDefault title="Bandeiras" close="true" >
	<div id="contentAllPopUp">
		<div id="valorCaracteristicaList">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/abrirBandeiras.do" styleId="formaPagamentoForm">
				<div style="overflow: auto; height: 300px; "><br /><br />
					<display:table name="bandeiraList" id="formaPagamentoBandeiraTO" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI="" class="tabela-padrao-new tablesorter table_body">
						<display:column title="Bandeira" headerClass="sortable">
							${formaPagamentoBandeiraTO.bandeiraTO.nmBandeira}&nbsp;&nbsp;&nbsp;
							<c:if test="${formaPagamentoBandeiraTO.bandeiraTO.urlImagem != null}">
								<img src="/catprs/imgs/${formaPagamentoBandeiraTO.bandeiraTO.urlImagem}" alt="${formaPagamentoBandeiraTO.bandeiraTO.nmBandeira}" style="height: 20px; width: inherit;" />
							</c:if>
						</display:column>
					</display:table>
				</div>
				<div class="botao" style="position: absolute; bottom: 10px;">
					<input class="btOk" type="button" onclick="fecharPopup('popup1');" value="OK" title="OK"/>
				</div>
			</html:form>
		</div>
	</div>
</catalogo:popUpDefault>