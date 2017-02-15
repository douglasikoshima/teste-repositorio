<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>


<catalogo:popUpDefault title="" close="true" width="400px" left="400px" height="100px">
	<netui:form action="adicionarValorCaracteristica" onKeyPress="if ( event.keyCode == 13 ){ $('btnAdicionar').onclick(); return false; }" focus="nmValorTextBox">
		<div class="fleft">
			<div class="legendaObrigatorio help">(*) Campo Obrigatório</div>
			<br clear="all" />
			<div class="label-form-bold label_required" style="width:80px;">Novo Valor:<font size="1px" color="#EEB422" valign="center">*</font></div>
			<netui:textBox tagId="nmValorTextBox" dataSource="actionForm.nmValorCaracteristica" style="width: 220px;" styleClass="required"/>
			<netui:hidden dataSource="actionForm.idCaracteristica" dataInput="${idCaracteristica}"/>
			<netui:hidden dataSource="actionForm.paginaSelecionada" dataInput="${paginaSelecionada}"/>
			<netui:hidden dataSource="actionForm.caracteristicasSelect" dataInput="${caracteristicas_selecionadas}"/>
		</div>
		<br clear="all" /><br clear="all" /><br clear="all" />
		<div class="botao" style="width: 99%">
			<netui:button tagId="btnAdicionar" styleClass="btNavLaranja74" value="Adicionar" onClick="send(this, null, null, 'div_erros_popup2');" type="button"/>
		</div>
	</netui:form>
	<img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="$('nmValorTextBox').focus();" />
</catalogo:popUpDefault>