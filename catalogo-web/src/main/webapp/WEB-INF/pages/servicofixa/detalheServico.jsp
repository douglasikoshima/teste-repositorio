<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:blankTemplate titulo="Home Catalogo">
    <jsp:attribute name="headScripts">
		<style>
		
			ul.connectedSortable { list-style-type: none; margin: 0; padding: 0; float: left; margin-bottom: 10px;  margin-right: 10px; border: 1px solid #5875A1; height: 100px; width: 235px; float: left; overflow-y: scroll; overflow-x: hidden;}
			ul.connectedSortable li { margin: 0 2px 2px 2px; padding: 5px; font-size: 9px; width: 100%; }
			.contentScore{float: left; margin-top: 5px; text-align: left;}
			li.ui-state-default div.ui-state-default {width: 50%; float: left; font-size: 9px; border: 0px;}
			
			/***** APAGAR  **********/


.secao_conteudo #grupos div.contentGroup {padding:10px 10px 10px 10px; background:#fff;border:1px solid #9bc3ef}
.secao_conteudo #grupos h3 {padding:05px 0px 05px 25px; margin-top:10px}



.contentGroup fieldset {padding:05px;border:0px none;}
.contentGroup fieldset legend {background:#fff;font-weight:bold;color:#0364cb}
.contentGroup fieldset input {border: 0px none;}

/* Serviços */
#resultadoPesquisaServicos,#alterarServicos {display:none}
#indicadorDiponibilidade {clear:both;float:left}
#alterarServicos fieldset {clear:both; Width:99%; border:1px solid #ccc}
.contentGroup fieldset.gupoField {background:#fff;width:97% !important; border:1px solid #ccc; float:left !important;clear:none !important;margin-right:10px;margin-bottom:10px}
.contentGroup fieldset.gupoField .linerow {width:165px}
.contentGroup fieldset .linerow {width:145px}
#grupos fieldset {background:#fafafa;}
		/***** APAGAR  **********/	



		</style>
        <script type="text/javascript" src="/catalogo/static_server/js/detalheServico.js"></script>
    </jsp:attribute>
    <jsp:body>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/search.do" styleId="detalheServicoFixaForm">
			<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
			<c:if test="${labelError != null}">
				<script>
					generateContentErrorForm("${labelError}")
				</script>
			</c:if>
			
			<div class="secao_conteudo">
				<br clear="all"/>
				
				<div class="boxline">
					<div class="linerow bold"><label for="nmSistema">Sistema Origem:</label></div>
					<div class="linerow clear"><html:text property="nmSistema" styleId="nmSistema" readonly="true" styleClass="required" maxlength="254"/></div>
				</div>
				
				<div class="boxline"> 
					<div class="linerow bold" ><label for="cdCodigo">C&oacute;digo do Servi&ccedil;o:</label></div>
					<div class="linerow clear"><html:text property="cdCodigo" styleId="cdCodigo" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>
				
				<div class="boxline"> 
					<div class="linerow bold" ><label for="cdServicoOrigem">C&oacute;digo do Servi&ccedil;o Origem:</label></div>
					<div class="linerow clear"><html:text property="cdServicoOrigem" styleId="cdServicoOrigem" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>				
				
				<div class="boxline"> 
					<div class="linerow bold" ><label for="nmComercialOrigem">Nome do Servi&ccedil;o Origem:</label></div>
					<div class="linerow clear"><html:text property="nmComercialOrigem" styleId="nmComercialOrigem" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>
				
				<div class="boxline"> 
					<div class="linerow bold" ><label for="nmComercialCatalogo">Nome do Servi&ccedil;o:</label></div>
					<div class="linerow clear"><html:text property="nmComercialCatalogo" styleId="nmComercialCatalogo" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>

				<div class="boxline"> 
					<div class="linerow bold" ><label for="nmTipoServicoDetalheServico">Tipo de Servi&ccedil;o:</label></div>
					<div class="linerow clear"><html:text property="nmTipoServicoDetalheServico" styleId="nmTipoServicoDetalheServico" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>
				
				<div class="boxline"> 
					<div class="linerow bold" ><label for="idFamilia">Fam&iacute;lia:</label></div>
					<div class="linerow clear">
						<html:select property="idFamilia" styleId="idFamilia" onchange="preencher(this)">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach items="${familiaTOList}" var="familiaTO">
								<html:option value="${familiaTO.idFamilia}">${familiaTO.nmFamilia}</html:option>
							</c:forEach>
						</html:select>
					</div>
				</div>
				
				<div class="boxline"> 
					<div class="linerow bold" ><label for="idCategoria">Categoria:</label></div>
					<div class="linerow clear" id="categoriaList">
						<html:select property="idCategoria" styleId="idCategoria">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach items="${categoriaTOList}" var="categoriaTO">
								<html:option value="${categoriaTO.idCategoria}">${categoriaTO.nmCategoria}</html:option>
							</c:forEach>
						</html:select>
					</div>
				</div>
				
				<c:if test="${servicoFixaForm.cdTipoServico == 20 && servicoFixaForm.cdPacote == null}">
					<div class="boxline"> 
						<div class="linerow bold"><label for="qtMinutoLivreFMLocal">Qt. Minutos Fixo M&oacute;vel:</label></div>
						<div class="linerow clear"><html:text property="qtMinutoLivreFMLocal" styleId="qtMinutoLivreFMLocal" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
					</div>
					
					<div class="boxline"> 
						<div class="linerow bold"><label for="qtMinutoLivreFFLocal">Qt. Minutos Fixo Fixo:</label></div>
						<div class="linerow clear"><html:text property="qtMinutoLivreFFLocal" styleId="qtMinutoLivreFFLocal" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
					</div>
					
					<div class="boxline">
						<div class="linerow bold" ><label for="vlMinutoAdicionalFFLocal">Valor Minutos Excedentes:</label></div>
						<div class="linerow clear"><html:text property="vlMinutoAdicionalFFLocal" styleId="vlMinutoAdicionalFFLocal" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
					</div>
				</c:if>
				<c:if test="${servicoFixaForm.cdPacote != null}">
					<div class="boxline"> 
						<div class="linerow bold"><label for="cdPacote">C&oacute;digo do Pacote:</label></div>
						<div class="linerow clear"><html:text property="cdPacote" styleId="cdPacote" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
					</div>
					<div class="boxline"> 
						<div class="linerow bold" style="text-align: left; width: 155px;"><label for="cdTipoPrecoPacote">Ind. Pre&ccedil;o do Pacote:</label></div>
						<div class="linerow clear"><html:text property="cdTipoPrecoPacote" styleId="cdTipoPrecoPacote" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
					</div>
				</c:if>
				<div class="boxline"> 
					<div class="linerow bold" ><label for="inVendaIsolada">Indicador de Venda Isolada:</label></div>
					<div class="linerow clear"><html:checkbox property="inVendaIsolada" disabled="true" styleId="inVendaIsolada" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;"/></div>
				</div>

				<div class="boxline">
					<div class="linerow bold" ><label for="nmTecnologia">Tecnologia:</label></div>
					<div class="linerow clear"><html:text property="nmTecnologia" styleId="nmTecnologia" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>
				
				<div class="boxline"> 
					<div class="linerow bold"><label for="cdClasseServicoPrincipal">Classe de Servi&ccedil;o Principal:</label></div>
					<div class="linerow clear"><html:text property="cdClasseServicoPrincipal" styleId="cdClasseServicoPrincipal" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>
				<div class="boxline"> 
					<div class="linerow bold" ><label for="cdClasseServicoAdicional">Classe de Servi&ccedil;o Adicional:</label></div>
					<div class="linerow clear"><html:text property="cdClasseServicoAdicional" styleId="cdClasseServicoAdicional" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>
				<div class="boxline"> 
					<div class="linerow bold" ><label for="cdTipoEquipamento">Tipo de Equipamento:</label></div>
					<div class="linerow clear"><html:text property="cdTipoEquipamento" styleId="cdTipoEquipamento" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>

				<div class="boxline"> 
					<div class="linerow bold" ><label for="inPreFactibilidade">Pr&eacute;-Factibilidade:</label></div>
					<div class="linerow clear"><html:checkbox property="inPreFactibilidade" styleId="inPreFactibilidade" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;"/></div>
				</div>
				
				<div class="boxline"> 
					<div class="linerow bold" ><label for="inPosFactibilidade">P&oacute;s-Factibilidade:</label></div>
					<div class="linerow clear"><html:checkbox property="inPosFactibilidade" styleId="inPosFactibilidade" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;"/></div>
				</div>
				
				<div class="boxline"> 
					<div class="linerow bold" ><label for="cdSiscom">C&oacute;digo Syscom:</label></div>
					<div class="linerow clear"><html:text property="cdSiscom" styleId="cdSiscom" styleClass="required" maxlength="254" style="width:155px;"/></div>
				</div>
				<div class="boxline"> 
					<div class="linerow bold" ><label for="inDisponivelDetalheServico">Indicador de Disponibilidade:</label></div>
					<div class="linerow clear"><html:checkbox property="inDisponivelDetalheServico" styleId="inDisponivelDetalheServico" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;" disabled="true"/></div>
				</div>
				
				<c:if test="${servicoFixaForm.cdTipoServico == 4}">
					<div class="boxline"> 
						<div class="linerow bold"><label for="sgTipoAplicacaoDesconto">Tipo de Desconto:</label></div>
						<div class="linerow clear"><html:text property="sgTipoAplicacaoDesconto" styleId="sgTipoAplicacaoDesconto" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
					</div>
					<div class="boxline"> 
						<div class="linerow bold" ><label for="vlDescontoAbsoluto">Valor do Desconto:</label></div>
						<div class="linerow clear"><html:text property="vlDescontoAbsoluto" styleId="vlDescontoAbsoluto" readonly="true" styleClass="required" maxlength="254" style="width:155px;"/></div>
					</div>
				</c:if>
				<br clear="all"/>
				<div class="barra"></div>
			
				<div id="grupos">
					<h3 onclick="parent.sizeFrame('detail')"> Vari&aacute;veis de Disponibilidade</h3>
					<div class="contentGroup" id="caixa02">
						<c:if test="${(servicoFixaForm.cdTipoServico == 4 && servicoFixaForm.inPossuiVarDispDesconto) || (servicoFixaForm.cdTipoServico != 4 && servicoFixaForm.inPossuiVarDispFinanciamento)}">
							<fieldset class="gupoField">
								<c:if test="${servicoFixaForm.cdTipoServico == 4 && servicoFixaForm.inPossuiVarDispDesconto}">
									<legend>Desconto</legend>
									<div class="boxline">
										<div class="linerow bold label_required"><label for="inAreaConcorrenciaDesconto">&Aacute;rea Concorr&ecirc;cia:</label></div>
										<div class="linerow clear"><html:checkbox property="inAreaConcorrenciaDesconto" styleId="inAreaConcorrenciaDesconto" onClick="msgDisponibilidade(this)" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;"/></div>
									</div>
									<div class="boxline">
										<div class="linerow bold label_required"><label for="inPlanoMinutoDesconto">Plano de Minuto:</label></div>
										<div class="linerow clear"><html:checkbox property="inPlanoMinutoDesconto" styleId="inPlanoMinutoDesconto" onClick="msgDisponibilidade(this)" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;"/></div>
									</div>
								</c:if>	
								
								<c:if test="${servicoFixaForm.cdTipoServico != 4 && servicoFixaForm.inPossuiVarDispFinanciamento}">
									<legend>Financiamento</legend>
									<div class="boxline">
										<div class="linerow bold label_required"><label for="inAreaConcorrenciaFinanciamento">&Aacute;rea Concorr&ecirc;cia:</label></div>
										<div class="linerow clear"><html:checkbox property="inAreaConcorrenciaFinanciamento" styleId="inAreaConcorrenciaFinanciamento" onClick="msgDisponibilidade(this)" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;"/></div>
									</div>
									<div class="boxline">
										<div class="linerow bold label_required"><label for="inPlanoMinutoFinanciamento">Plano de Minuto:</label></div>
										<div class="linerow clear"><html:checkbox property="inPlanoMinutoFinanciamento" styleId="inPlanoMinutoFinanciamento" onClick="msgDisponibilidade(this)" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;"/></div>
									</div>
								</c:if>	
							</fieldset>
						</c:if>			
						<fieldset class="gupoField clear" style="clear:both !important">
							<legend>Preço</legend>
							<div class="boxline">
								<div class="linerow bold label_required"><label for="inEspServicoPacote">Pacote:</label></div>
								<div class="linerow clear"><html:checkbox property="inEspServicoPacote" styleId="inEspServicoPacote" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;" disabled="true"/></div>
							</div>
							<div class="boxline">
								<div class="linerow bold label_required"><label for="inClasseServicoPrincipal">Classe de Linha:</label></div>
								<div class="linerow clear"><html:checkbox property="inClasseServicoPrincipal" styleId="inClasseServicoPrincipal" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;" disabled="true"/></div>
							</div>
							<div class="boxline">
								<div class="linerow bold label_required"><label for="inEmpresaInstaladora">Empresa Instaladora:</label></div>
								<div class="linerow clear"><html:checkbox property="inEmpresaInstaladora" styleId="inEmpresaInstaladora" styleClass="semBorda belongsToForm checkbox_modelo" style="width:155px;" disabled="true"/></div>
							</div>

						
							<div class="boxline">
								<div class="linerow bold"><label for="openerAtributosPolitica">Atributo:</label></div>
								<c:if test="${servicoFixaForm.atributoPoliticaPrecificacaoTOList ne null}">
									<div class="linerow clear"><div class="iconrelacionamento" id="openerAtributosPolitica"  alt="Alterar" width="15" style="cursor: pointer;"></div></div>
								</c:if>
							</div>
						
						
						
							<div class="boxline">

								<div class="linerow bold">
									<div id="dialog" title="Atributos da politica de precificação">
										<table cellspacing="0" cellpadding="0" class="tabela-padrao-new tablesorter table_body" id="atributoPoliticaPrecificacao">
											<thead>
												<tr>
													<th class="sortable" align="left">Nome do atributo</th>
												</tr>
											</thead>
											<tbody>		
											<c:if test="${servicoFixaForm.atributoPoliticaPrecificacaoTOList == null}">						
												<tr>
													<td><span align='center'>Nenhum atributo encontrado.</span></td>
												</tr>
											</c:if>													
											<c:if test="${servicoFixaForm.atributoPoliticaPrecificacaoTOList ne null}">						
												<logic:iterate id="atributoPoliticaPrecificacao" property="atributoPoliticaPrecificacaoTOList" name="DetalheServicoFixaForm">
													<tr>
														<td>${atributoPoliticaPrecificacao.svcAttrNm}</td>
													</tr>
												</logic:iterate>
											</c:if>	
											</tbody>
										</table>						
															
<%-- 										<netui-data:repeater dataSource="atributoPoliticaPrecificacaoTOList" defaultText="<br><span align='center'>Nenhum atributo encontrado.</span>">
											<netui-data:repeaterHeader>
												<table cellspacing="0" cellpadding="0" class="tabela-padrao-new tablesorter table_body" id="atributoPoliticaPrecificacao">
													<thead>
														<tr>
															<th class="sortable" align="left">Nome do atributo</th>
														</tr>
													</thead>
													<tbody>
											</netui-data:repeaterHeader>
											<netui-data:repeaterItem>
														<tr>
															<td>${container.item.svcAttrNm}</td>
														</tr>
											</netui-data:repeaterItem>
											<netui-data:repeaterFooter>
													</tbody>
												</table>
											</netui-data:repeaterFooter>
										</netui-data:repeater> --%>
									</div>
								</div>
							</div>							
						</fieldset>
					</div>
					<h3 onclick="parent.sizeFrame('detail')"> Atributos </h3>
					<div>
						<display:table name="atributoTOList" id="atributoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="Código" property="cdAtributo" sortable="true" headerClass="sortable"/>
							<display:column title="Nome" property="svcAttrNm" sortable="true" headerClass="sortable"/>
							<display:column title="Descrição da Mascara" property="svcAttrDesc" sortable="true" headerClass="sortable" style="text-align: center"/>
							<display:column title="Quantidade Máxima" property="qtMaxImaAtivacao" sortable="true" headerClass="sortable" style="text-align: center"/>
							<display:column title="Indicador de Obrigatoriedade" sortable="true" headerClass="sortable" style="text-align: center">
								<catalogo:flagDefault test="${atributoTO.indObrigatorio=='S'}" />
							</display:column>
							<display:footer />
						</display:table>
					</div>
				</div>		
			
				<br clear="all"/>
				<div class="barra"></div>
				<div class="botao">
					<html:button property="botao_gravar_form" styleId="botao_gravar_form" onClick="save()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
					<span>&nbsp;</span>
				</div>
				<html:hidden property="idServico2" styleId="idServico2" value="${idServicoSearch}"/>
				<html:hidden property="alterarServico2" styleId="alterarServico2" value="${alterarServicoSearch}"/>
				<html:hidden property="idServicoSearch" styleId="idServicoSearch"/>
				<html:hidden property="alterarServicoSearch" styleId="alterarServicoSearch"/>
				<html:hidden property="idFamiliaForm" styleId="idFamiliaForm"/>
				<html:hidden property="idCategoriaForm" styleId="idCategoriaForm"/>
			</div>
			<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			<html:hidden property="alterarPoliticas" styleId="alterarPoliticas"/>
		</html:form>
	</jsp:body>	
</catalogo:blankTemplate>
