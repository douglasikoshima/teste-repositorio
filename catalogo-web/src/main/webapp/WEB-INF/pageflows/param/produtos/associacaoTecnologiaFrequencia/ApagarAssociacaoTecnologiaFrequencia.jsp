<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupConfirmacao>
		<c:if test="${associacaoFrequenciaForm.apagarModeloAfetados != null}">
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
							<div class="box_middle_center_conteudo vertical-scroll" style="height:230px;">
							
							<table class="tabela-padrao tablesorter" >
							        <logic:iterate id="modelosAfetadosTO" property="apagarModeloAfetados" name="associacaoFrequenciaForm">
										<tr><td><label class="lblForm">${modelosAfetadosTO.nmGrupoProduto} (${modelosAfetadosTO.qtdProdutosAfetados})</label></td></tr>
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
			<div class="box_bottom">
				<div class="box_bottom_center">
				</div>
				<div class="box_bottom_left">
				</div>
				<div class="box_bottom_right">
				</div>
			</div>
		</div>
		</c:if>
		<br clear="all"/>
		Confirme a exclusão:
		<br clear="all"/>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/apagarAssociacaoTecnologiaFrequencia.do">
		<div style="width:70%;">
			<c:if test="${associacaoFrequenciaForm.apagarModeloAfetados != null}">
				<div align="left" class="label_required">Justificativa:<font size="1px" color="#EEB422" valign="center">*</font></div>
				<html:textarea styleClass="required" property="justificativa" styleId="justificativa" rows="4" cols="45" style="width:100%;"/>
			</c:if>
			<html:hidden property="idEntidade" styleId="idEntidade" value="${id_tecnologia_tipo_frequencia}"/>
		</div>
		<br clear="all"/>
		<input type="button" onclick="send(this, 'right_section', 'popup1', 'right_section', null, 'div_erros_popup_confirmacao');" class="btNavegacao74" value="Sim" title="Confirmar Exclus&atilde;o de Associa&ccedil;&atilde;o"/>
		</html:form>
		&nbsp;
		<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Não" title="Cancelar Exclus&atilde;o de Associa&ccedil;&atilde;o"/>
		<br/><br/>
</catalogo:popupConfirmacao>


