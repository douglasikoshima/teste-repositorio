<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<html:form styleId="lista_planos" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/pesquisar.do" target="target_return_tela">
	<div id="resultado_pesquisa_planos" style="width:100% ;position: relative">
			<div class="both-scroll" style="height: 300px; width: 100%;">
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
							<thead>
								<tr>
									<th class="center"></th>
									<th class="sortable">Nome Comercial</th>
									<th class="sortable">Nome T&eacute;cnico</th>
									<%--<th class="sortable">Tipo de Cliente</th>--%>
									<th class="sortable">Tecnologia</th>
								</tr>
							</thead>
							<tbody>			
						<logic:iterate id="planos" property="arrayListaPlanos" name="consultaAcessoPlanosServicosForm">
								<tr>
									<td class="center">
									    <html:checkbox property="nmComercialSelecionados" value="${planos.idPlano}" onClick="selecionaCheckBoxPopup('hidden_nmComercial_planoCheck', this); verificarSelecaoAll(this, 'radio_nmComercial_${planos.nmComercial}');" styleId="radio_nmComercial_${planos.nmComercial}" styleClass="semBorda belongsToForm checkbox"/>
									</td>
									<td>${planos.nmComercial}</td>
									<td>${planos.cdCodigo}</td>
									<%--<td> ${planos.nmTipoCliente} </td>--%>
									<td>${planos.nmTecnologia}</td>
								</tr>
						</logic:iterate>	
						</tbody>
					</table>
			</div>
			<br clear="all"><br clear="all">
			
			<div class="barra"></div>	
			<div class="botao">
				<html:button property="Selecionar" styleClass="btNavegacao74" value="Selecionar" onclick="fecharPopup('popup1');"/>
			</div>
	</div>
</html:form>
<iframe id='target_return_tela' name='target_return_tela' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_pesquisa_popup');"></iframe>							

