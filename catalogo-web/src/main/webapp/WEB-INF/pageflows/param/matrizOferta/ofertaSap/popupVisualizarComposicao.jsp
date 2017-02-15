<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>

<catalogo:popupPadrao>
		<html:text property="larguraPopup" value="810px" styleId="larguraPopup" styleClass="hide" ></html:text>
		<html:text property="alturaPopup" value="250px" styleId="alturaPopup" styleClass="hide" ></html:text>
		<c:set var="nomeBox" scope="application"  value="Composição Oferta SAP: ${dscOfertaSap}"/>
		<jsp:include page="/templates/box_pre.jsp"/>
			<div>
				<div style="width:100%;position:relative;">		
					<div class="both-scroll" style="height: 270px; width: 100%">
							<table style="width: 99%" cellspacing="0" cellpadding="0" class="tabela-padrao table_body" id="lista_composicao">
								<thead>
									<tr>
										<th colspan="2" class="throwspan">Oferta SAP</th>
										<th colspan="2" class="throwspan">Plano</th>
										<th rowspan="2">Matriz Oferta</th>
										<th rowspan="2">Oferta Servi&ccedil;o</th>
										<th rowspan="2">Excluir</th>
									</tr>
									<tr>
										<th rowspan="2">Codigo</th>
										<th rowspan="2">Descri&ccedil;&atilde;o</th>
										<th rowspan="2">Nome T&eacute;cnico</th>
										<th rowspan="2">Nome Comercial</th>
									</tr>
								</thead>
							<tbody>
							<logic:iterate id="composicaoId" property="composicaoLista" name="ofertaSapForm">
								<c:set value="true" var="firstPass"/>
									<tr>
										<td>
											<c:if test="${firstPass == true}">
												${composicaoId.cdOfertaSap}
											</c:if>
										</td>
										<td>${composicaoId.dscOfertaSap}</td>
										<td>${composicaoId.nmTecnico}</td>
										<td>${composicaoId.nmComercial}</td>
										<td>${composicaoId.nmMatrizOferta}</td>
										<td>${composicaoId.nmOfertasServico}</td>
										<td class="center">

											<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/excluirComposicao.do?id_oferta_sap=${composicaoId.idOfertaSap}&dsc_oferta_sap=${composicaoId.dscOfertaSap}&id_ofertaServico=${composicaoId.idOfertaServico}&id_sistemaPlano=${composicaoId.idSistemaPlano}&id_matrizOferta=${composicaoId.idMatrizOferta}" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'))return false;">
												<img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif"/>
     										</html:link>
										</td>
									</tr>
									<c:set value="false" var="firstPass"/>
								</logic:iterate>
								</tbody>
							</table>
					</div>
					<div class="barra"></div>
					<div class="botao">
						<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/selecionarMatrizOfertaComposicao.do?id_oferta_sap=${idOfertaSap}" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'));$('div_alterar_oferta_sap').hide();$('resultado_pesquisa').hide();return false">
							<html:button property="comporoferta" styleClass="btNavegacao120" value="Compor Oferta"/>
						</html:link>
					</div>
				</div>
			</div>
			
		<jsp:include page="/templates/box_pos.jsp"/>
</catalogo:popupPadrao>
