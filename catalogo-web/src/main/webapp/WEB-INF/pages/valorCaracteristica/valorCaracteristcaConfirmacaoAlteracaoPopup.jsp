<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<catalogo:popupConfirmacao>	
			A Alteração afetará a associação com os Modelos:
			<br clear="all"/>
			<br/>
			<div class="box" style="width:70%;">
				<div class="box_top">
					<div class="box_top_center">
						
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
								<div class="box_middle_center_conteudo vertical-scroll" style="height:200px;">
									<display:table name="grupoProdutoList" id="grupoProdutoTO" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
										<display:column title="Modelo" headerClass="sortable" >
											${grupoProdutoTO.nmGrupoProduto} (${grupoProdutoTO.qtProdutoGrupoProduto})
										</display:column>
									</display:table>
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
			<br clear="all"/>
		<br clear="all"/>
		Confirme a Alteração:
		<br clear="all"/>
		<br clear="all"/>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/remove.do" styleId="caracteristicaForm" onsubmit="false">
			<html:hidden property="idValorCaracteristica" styleId="idValorCaracteristica"/>
			<input type="button" id="botao_sim" class="btNavegacao74" value="Sim" title="Sim" onclick="$('btInclusaoHidden').onclick();fecharPopup('popup2');"/>
			&nbsp;
			<input type="button" onclick="document.caracteristicaForm('idValorCaracteristica').value = ''; document.caracteristicaForm('valor').value = '';fecharPopup('popup2');" class="btNavegacao74" value="Não" title="Não"/>
		</html:form>
		<br/><br/>
</catalogo:popupConfirmacao>
