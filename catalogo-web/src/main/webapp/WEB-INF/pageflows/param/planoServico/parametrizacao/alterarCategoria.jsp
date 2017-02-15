<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/alterarValorCategoria.do">
	<div class="label-form-bold2 fleft label_required" style="width: 120px;">Grupo de Serviços<font size="1px" color="#EEB422" valign="center">*</font></div>
		<html:text property="nmCategoria" value="${nmCategoria}" size="45" styleClass="required editavel" onkeypress="return semPontoVirgula(event);" maxlength="50"></html:text>
		<html:hidden property="idCategoria" value="${idCategoria}"/>
	
	<br clear="all" />
	<div class="fleft" style="width: 100%;">
		<div class="label-form-bold2 fleft label_required" style="width: 120px">Status:<font size="1px" color="#EEB422" valign="center">*</font></div>
			<html:select property="indisponivel" tabindex="2" style="width: 47.2%;"  styleClass="required editavel" styleId="select_categoria">
				<option value="S">Disponivel</option>
				<option value="N">Indisponivel</option>				    				   			   				   
			</html:select> 		
		<html:button property="btn_alterar" value="Alterar" styleClass="btNavegacao74" onclick="clearEditando();if(checkRequired($(this).form, 'div_erros_popup')){return false;}abrirPopup2($(this).next('a'), null, 'popup_valores_categoria');setEditando();" bundle="messages" titleKey="catalogo.alterarCategoria.alterar"/>
		<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/popupAlterarValorCategoria.do?id_categoria=${idCategoria}" onclick=""></html:link> 		
		<html:button property="botao_alterar_valor_categoria" value="Alterar" styleClass="btNavegacao74 hide" styleId ="btAlterar" onclick="clearEditando();send(this, null, null, 'div_erros_popup');setEditando();" bundle="messages" titleKey="catalogo.alterarCategoria.alterar"/>
	</div>
</html:form> 