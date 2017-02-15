<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/pesquisarNmComercialNoPopupPerfilServico.do">
		<div style="width:99%;" id="lista_nomes_planos">
	<!--		<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
 				<thead>
				<tr>
					<th class="center" width="10px">&nbsp;</th>
					<th class="sortable">Nome Comercial</th>
					<th class="sortable">Nome T&eacute;cnico</th>
					<th class="sortable">Grupo de Serviços</th>
					<th class="sortable">Tipo de Servi&ccedil;o</th>
				</tr>
				</thead> 
			</table> -->
			<div class="both-scroll" style="height: 300px;">
				<table cellspacing="0" cellpadding="0" class="tabela-padrao table_body">
					<thead>
						<tr>
							<th class="center" width="10px">&nbsp;</th>
							<th class="sortable">Nome Comercial</th>
							<th class="sortable">Nome T&eacute;cnico</th>
							<th class="sortable">Grupo de Serviços</th>
							<th class="sortable">Tipo de Servi&ccedil;o</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty parametrizacaoAcessoForm.retornoServicoLista}">						
							<logic:iterate id="servicos" property="retornoServicoLista" name="parametrizacaoAcessoForm" >												
								<c:set value="true" var="firstPass"/>
								<tr>
									<td class="center">
										<html:checkbox property="nmComercialSelecionados" value="${servicos.idServico}" onClick="selecionaCheckBoxPopup('hidden_nmComercial_perfil_servico', this)" styleId="plano_cb${servicos.idServico}" styleClass="semBorda checkbox" />
							        </td>
									<td>
										<c:if test="${firstPass == true}">
											<label for="perfil_cb${servicos.idServico}">${servicos.nmComercial}</label>
										</c:if>
									</td>
									<td>${servicos.cdCodigo}</td>
									<td>
										${servicos.nmCategoria}
									</td>
									<td>${servicos.dscTipoServico}</td>
								</tr>
								<c:set value="false" var="firstPass"/>
							</logic:iterate>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<br clear="all" />

		<div class="barra"></div>
		<div style="margin: 5px;" class="botao">
			<html:button property="btOk" styleClass="btOk" onclick="fecharPopup('popup1');" value="OK" title="Ok"/>
		</div>
</html:form>