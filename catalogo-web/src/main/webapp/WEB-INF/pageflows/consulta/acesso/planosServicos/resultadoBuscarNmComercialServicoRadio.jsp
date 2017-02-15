<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<html:form styleId="lista_servicos" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/pesquisar.do" target="target_return_tela">
	<div id="resultado_pesquisa_servicos" style="width:100% ;position: relative">
			<div class="both-scroll" style="height: 300px; width: 100%;">
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes">
							<thead>
								<tr>
									<th class="center"></th>
									<th class="sortable">Nome Comercial</th>
									<th class="sortable">Nome T&eacute;cnico</th>
									<th class="sortable">Categoria</th>
									<th class="sortable">Tipo de Servi&ccedil;o</th>
								</tr>
							</thead>
							<tbody>			
						<logic:iterate id="servicos" property="arrayListaServicos" name="consultaAcessoPlanosServicosForm">
								<tr>
									<td class="center">
									    <html:radio property="nmComercialSelecionados" value="${servicos.idServico}" onClick="selecionaCheckBoxPopup('hidden_nmComercial_servicoRadio', this); verificarSelecaoAll(this, 'radio_nmComercial_${servicos.nmComercial}');" styleId="radio_nmComercial_${servicos.nmComercial}" styleClass="semBorda belongsToForm checkbox" />
									</td>
									<td>${servicos.nmComercial}</td>
									<td>${servicos.cdServico}</td>
									<td>${servicos.idCategoriaLegado}</td>
									<td>${servicos.dscTipoServico}</td>
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