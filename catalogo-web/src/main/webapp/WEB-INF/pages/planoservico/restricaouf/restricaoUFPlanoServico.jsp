<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="CatalogoPRSTags" prefix="cata"%>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:defaultTemplate titulo="Home Catalogo">

	<jsp:attribute name="headScripts">
		<script type="text/javascript" src="/catalogo/static_server/js/restricaoUFPlanoServico.js"></script>
	</jsp:attribute>	
	
	<jsp:body>
		<div class="breadcrumb">Parametrização > Planos Serviços > <span><a onclick="$jq('#mn_parametrizacao_plnsrv_restricaouf > a')[0].click();" style="cursor: pointer;">Restrição por UF</a></span></div>
		<script>carregaMenu('mn_parametrizacao_plnsrv_restricaouf');</script>
		<c:if test="${labelError != null}">
			<script>generateContentError("${labelError}");</script>
		</c:if>		
		<div class="secao_conteudo">
			<div class="contentGroup">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/restricaouf/search.do" styleId="restricaoPlanoServicoForm" >
					<catalogo:contentBox title="Pesquisa" doubt="true" requiredFields="true">					
						<fieldset id="tipoPesquisa">
							<legend>Escolha o tipo de pesquisa:<font size="1px" color="#EEB422">*</font></legend>
							<div class="line">
							
								<html:hidden styleId="tipoPesquisaHidden" property="tipoPesquisa" />
								<html:radio property="trash" value="P" styleClass="bold" onclick="$jq('#tipoPesquisaHidden').val('P');">Plano</html:radio>
								<html:radio property="trash" value="S" styleClass="bold" onclick="$jq('#tipoPesquisaHidden').val('S');">Serviço</html:radio>
							</div>
						</fieldset>

						<div class="boxline">
							<div class="linerow bold"><label for="txtCodigo">Nome Técnico:</label></div>
							<div class="linerow clear"><html:text property="codigo" styleId="txtCodigo"/></div>
						</div>
						
						<div class="boxline">
							<div class="linerow bold"><label for="txtNome">Nome Comercial:</label></div>
							<div class="linerow clear"><html:text property="nome" styleId="txtNome"/></div>
						</div>
						
						<div class="boxline">
							<div class="linerow bold"><label for="checkBox4G">Indicador 4G:</label></div>
							<div class="linerow clear"><html:checkbox property="ind4G" styleId="checkBox4G" styleClass="checkbox" disabled="true" /></div>
						</div>

						<br clear="all" />
						<div class="barra"></div>
						<div class="botao">
							<html:button property="btnPesquisar" styleId="btnPesquisar" onclick="search();" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title="Pesquisar"/>
						</div>
					</catalogo:contentBox>
					
					<c:if test="${planoServicoList != null}">
						
						<html:select property="restricoesUf" styleId="restricoesUF" multiple="true" style="display: none;"/>
						<html:select property="semRestricoesUf" styleId="semRestricoesUF" multiple="true" style="display: none;"/>
						
						<div id="divPlanoTable">
							<catalogo:contentBox title="Resultado da Pesquisa">
								<table class="tabela-padrao-new" id="planoTable" style="width: 99%">
									<thead>
										<tr>
											<th>Nome Técnico</th>
											<th>Nome Comercial</th>
											<th>UF</th>
										</tr>
									</thead>
									<tbody>
									
										<logic:iterate id="planoList" property="planoServicoList" name="restricaoPlanoServicoForm">
											<tr>
												<td>${planoList.codigo}</td>
												<td>${planoList.nome}</td>
												<td>
													<select id="${planoList.id}" class="selectJqPlugin" multiple="multiple">
														<logic:iterate id="ufList" property="ufList" name="restricaoPlanoServicoForm"> <%-- Todas as UFs --%>
															<c:set var="igual" value="false"/>
																<logic:iterate id="ufTOList" property="ufTOList" name="planoList"> <%-- UFs restritas --%>
																	<c:if test="${ufList.idUf eq ufTOList.idUf}">
																		<option selected="selected" value="${ufList.idUf}">${ufList.nmUf}</option>
																		<c:set var="igual" value="true"/>
																	</c:if>
																</logic:iterate>
															<c:if test="${not igual}">
																<option value="${ufList.idUf}">${ufList.nmUf}</option>
															</c:if>
														</logic:iterate>
													</select>
												</td>
											</tr>
										</logic:iterate>
										
									</tbody>
								</table>
								
								<br clear="all" />
								<div class="barra"></div>
								<div class="botao">
									<html:button property="btnGravar" styleId="btnGravar" onclick="save();search();" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title="Gravar"/>
									<span>&nbsp;</span>
									<html:button property="btnCancelar" styleId="btnCancelar" onclick="begin();" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title="Cancelar"></html:button>
								</div>
							</catalogo:contentBox>
						</div>
					</c:if>
				</html:form>	
			</div>
		</div>
	</jsp:body>
</catalogo:defaultTemplate>
