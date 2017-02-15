<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<c:set var="close" value="false"/>
<catalogo:popupPadrao>
	<div id="popup_valores_frequencia">
	<c:set var="nomeBox" scope="application"  value="frequencias"/>
	<jsp:include page="/templates/box_pre.jsp"/>
		<div >
			<html:form styleId="form_valores_frequencia" action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/confirmarValoresTecnologiaTipoFrequencia.do">
				<html:hidden property="idTecnologiaTipoFrequencia" styleId="idTecnologiaTipoFrequencia"/>
				<div class="vertical-scroll" style="height:250px">
				<table class="tabConteudoGeral" width="100%" >
				<logic:iterate id="todosVlfrequenciaTO" property="todosVlfrequenciaList" name="associacaoFrequenciaForm">
					<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
						<td width=10px">
							<html:multibox property="frequenciasSelecionadas" styleId="freq_cb_${todosVlfrequenciaTO.idVlFrequencia}" value="${todosVlfrequenciaTO.idVlFrequencia}" styleClass="semBorda" onClick="toggleCheckboxFrequenciaTecnologiaTipoFrequencia($(this).next('a').href, this.checked, 'popup_valores_frequencia');return false;"/>
							<html:link  style="display:none;"
							   action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/alterarValorTecnologiaTipoFrequencia.do?id_valor_frequencia=${todosVlfrequenciaTO.idVlFrequencia}&id_tecnologia_tipo_frequencia=${id_tecnologia_tipo_frequencia}&id_tecnologia_tipo_frequencia_vl=${ids_tecnologia_tipo_frequencia_valor[todosVlfrequenciaTO.idVlFrequencia]}" >
							   <img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar">
							</html:link>								
						</td>
						<td width="100px" nowrap="nowrap">
							<label for="freq_cb_${todosVlfrequenciaTO.vlFrequencia}">${todosVlfrequenciaTO.vlFrequencia}</label>
						</td>
					</tr>
				</logic:iterate>
				</table>
				</div>
				<div class="botao">
					<html:button styleClass="btOk" property="bt_ok"" onclick="if(${qtFrequencia} > $('form_valores_frequencia').select('input[type=checkbox]').collect(function(n){if(n.checked)return n; else return null;}).compact().length){posicionarDivErros('div_erros_popup');clearErrors();addError('Quantidade mínima de freqüência não respeitada (${qtFrequencia}).', true);abrirPopupErros();}else{fecharPopup('popup1');}" value="OK" bundle="messages" titleKey="catalogo.global.OK"/>
				</div>
			</html:form>
		</div>
	<jsp:include page="/templates/box_pos.jsp"/>
	</div>
</catalogo:popupPadrao>