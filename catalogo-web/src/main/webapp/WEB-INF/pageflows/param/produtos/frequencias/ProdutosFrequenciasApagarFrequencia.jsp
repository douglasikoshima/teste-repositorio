<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupConfirmacao>
		<c:if test="${frequenciaForm.modeloPorValorFrequenciaList != null}">
		<div class="legendaObrigatorio">
			(*) Campo Obrigatório
		</div>
		A Exclusão afetará a associação com os Modelos:
		<br clear="all"/>
		<br/>
		<div class="box" style="width:70%;">
			<div class="box_top">
				<div class="box_top_center">
					MODELO (NÚMERO DE PRODUTOS ASSOCIADOS)
				</div>
				<div class="box_top_left">
				</div>
				<div class="box_top_right">
				</div>
			</div>
			<div class="box_middle">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="box_middle_left">
					</td>
					<td>
						<div class="box_middle_center">
							<div class="box_middle_center_conteudo">
								<table class="tabela-padrao tablesorter" >
									<logic:iterate id="modeloPorValorFrequenciaTO" property="modeloPorValorFrequenciaList" name="frequenciaForm">
										<tr><td><label class="lblForm">${modeloPorValorFrequenciaTO.nmGrupoProduto} (${modeloPorValorFrequenciaTO.qtdProdutosAfetados})</label></td></tr>
									</logic:iterate>
								</table>
		
								<br/>
							</div>
						</div>
					</td>
					<td class="box_middle_right">
					</td>
				</tr>
				</table>
			</div>
			<div class="box_bottom" >
				<div class="box_bottom_center">
				</div>
				<div class="box_bottom_left">
				</div>
				<div class="box_bottom_right">
				</div>
			</div>
		</div>
		</c:if>
		Confirme a exclusão:
		<br clear="all"/>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/frequencias/apagarFrequencia.do">
		<div style="width:70%;">
			<c:if test="${frequenciaForm.modeloPorValorFrequenciaList != null}">
				<div align="left" class="label_required">Justificativa:<font size="1px" color="#EEB422" valign="center">*</font></div>
				<html:textarea styleClass="required" property="justificativa" styleId="justificativa" rows="4" cols="45" style="width:100%;"/>
			</c:if>
			<html:hidden property="idEntidade" styleId="idEntidade" value="${id_frequencia}"/>
		</div>
		<br clear="all"/>
		<html:button property="bt_sim" onclick="send(this, 'right_section', 'popup1', null, null, 'div_erros_popup_confirmacao');" styleClass="btNavegacao74" value="Sim" bundle="messages" titleKey="catalogo.ProdutosFrequenciasApagarFrequencia.Sim"/>
		</html:form>
		&nbsp;
		<html:button property="bt_nao" onclick="fecharPopup('popup1');" styleClass="btNavegacao74" value="Não" bundle="messages" titleKey="catalogo.ProdutosFrequenciasApagarFrequencia.Nao"/>
		<br/><br/>
</catalogo:popupConfirmacao>