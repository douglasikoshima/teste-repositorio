<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
	<jsp:attribute name="headScripts">
		<script type="text/javascript" src="/catalogo/static_server/js/cadastrocor.js"></script>
	</jsp:attribute>
		<script>carregaMenu('menu_CadastroCor');</script>
		<div class="secao_conteudo">
			<catalogo:contentBox title="Cadastro de Cor" doubt="true" requiredFields="true">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/cor/save.do" styleId="cadastroCorForm">
					<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 5px;  text-align: left">
						<html:hidden property="idCor" styleId="formRgb"/>
						<html:hidden property="rgb" styleId="corRGB"/>
						<div class="label-form-bold label_required" style="text-align: right; width: 85px;"><label for="actionForm.nmCor">Nome da Cor:<font size="1px" color="#EEB422">*</font></label></div>
							<html:select property="idCor" tabindex="1" onchange="load(this.value)" styleClass="required" styleId="idCor" style="width:100px;">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach var="corListTO" items="${corList}">
									<html:option value="${corListTO.idCor}">${corListTO.nmCor}</html:option>
								</c:forEach>
							</html:select>
					</div>
					<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 5px;">
						<div class="label-form-bold label_required" style="text-align: right; width: 85px;">Código Hexa<font size="1px" color="#EEB422">*</font></div>
<!-- 	 				<input id="rgbDisplay" style="margin-left: 0px; width:99px" disabled="disabled" name="rgbDisplay" />  -->
						<html:text property="rgbDisplay" styleId="rgbDisplay" style="margin-left: 0px; width:99px; background-color: #f0f0f0; color:#7d7d7d;" readonly="true"/> 							
						<input class="color" id="rgb" type="color" style="margin-left: 0px; width:0px" name="rgb" data-hex="true" maxlength="7"/>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="btn_salvar" styleId="botao_salvar_form" onClick="save()" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/><span>&nbsp;</span>
						<html:button property="btn_cancelar" styleId="botao_cancelar_form" onClick="formClear()" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title=""/><span>&nbsp;</span>
					</div>
					<script>
						$jq("#rgbDisplay").val('');
						if (document.getElementById("corRGB").value != '') {
							document.getElementById("rgb").value = document.getElementById("corRGB").value;
							document.getElementById("rgbDisplay").style.backgroundColor = document.getElementById("rgb").value;
							document.getElementById("rgbDisplay").value = document.getElementById("rgb").value;
						}
					</script>
				</html:form>
			</catalogo:contentBox>
		</div>
</catalogo:defaultTemplate>