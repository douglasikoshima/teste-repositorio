<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>

<netui:form action="alterarValorCaracteristica">
	<div class="label-form-bold2 fleft label_required">Valor:<font size="1px" color="#EEB422" valign="center">*</font></div>
	<netui:textBox dataSource="actionForm.valorCaracteristica" defaultValue="${valorCaracteristica.valor}" styleClass="required editavel" onKeyPress="return semPontoVirgula(event);"/>
	<netui:hidden dataSource="actionForm.idCaracteristica" dataInput="${valorCaracteristica.idCaracteristica}"/>
	<netui:hidden dataSource="actionForm.idValorCaracteristica" dataInput="${valorCaracteristica.idValorCaracteristica}"/>
	<netui:textArea tagId="justificativa_alterar_valor_caracteristica" dataSource="actionForm.justificativa" styleClass="hide"/>
	<input type="button" value="Alterar" class="btNavegacao74" onClick="clearEditando();if(checkRequired($(this).form, 'div_erros_popup')){return false;}abrirPopup2($(this).next('a'), null, 'popup_valores_caracteristica');setEditando();" title="${bundle.default['catalogo.AlterarValorCaracteristica.Alterar']}"/>
	<netui:anchor styleClass="hide" action="popupAlterarValorCaracteristica">Mathieu
		<netui:parameter name="id_caracteristica" value="${valorCaracteristica.idCaracteristica}"/>
		<netui:parameter name="id_valor_caracteristica" value="${valorCaracteristica.idValorCaracteristica}"/>
	</netui:anchor>
	<input id="botao_alterar_valor_caracteristica" type="button" value="Alterar" class="btNavegacao74 hide" id ="btAlterar" onclick="clearEditando();send(this, null, null, 'div_erros_popup');setEditando();" title="${bundle.default['catalogo.AlterarValorCaracteristica.Alterar']}"/>
</netui:form>