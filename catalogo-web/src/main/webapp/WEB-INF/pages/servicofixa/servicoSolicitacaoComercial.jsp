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
		<link rel="stylesheet" type="text/css" href="/catalogo/pages/servicofixa/servicofixa.css"/>
        <script type="text/javascript" src="/catalogo/static_server/js/servicoSolicitacaoComercial.js" >
         function checkError() {
             <c:if test="${labelError != null}">
                 generateContentErrorForm("${labelError}");
             </c:if>
         }
        </script>
		
    </jsp:attribute>
    <jsp:body>
        <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/searchSolicitacaoComercial.do" styleId="solicitacaoComercialForm" >
			
			<html:hidden property="idServico" styleId="idServico"/>
			<html:hidden property="nmTipoServico" styleId="nmTipoServico"/>
			<html:hidden property="idSistema" styleId="idSistema"/>
			<html:hidden property="idSolicitacaoComercial" styleId="idSolicitacaoComercial"/>
			<html:hidden property="idPoliticaDispSlctComercial" styleId="idPoliticaDispSlctComercial"/>
			<html:hidden property="inDisponivelNew" styleId="inDisponivelNew"/>
			<html:hidden property="idCondicaoPagamentoEdit" styleId="idCondicaoPagamentoEdit"/>
			<html:hidden property="idCanalVendaEdit" styleId="idCanalVendaEdit"/>
			<html:hidden property="idCanalVendaSolicitacaoComercial" styleId="idCanalVendaSolicitacaoComercial"/>
			<html:hidden property="idEncargo" styleId="idEncargo"/>
			<html:hidden property="idCondicaoPagamentoEncargo" styleId="idCondicaoPagamentoEncargo"/>
			<html:hidden property="idCondicaoPagamentoEncargoNew" styleId="idCondicaoPagamentoEncargoNew"/>
			
			<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
			<div id="pesqSolicitacaoComDiv">
				<script>
					posicionaDivErrosMidle("pesqSolicitacaoComDiv");
					posicionaDivSucess("pesqSolicitacaoComDiv");
				</script>
				<catalogo:contentBox title="Pesquisar Solicitação Comercial" doubt="true">
					<div class="boxline">
						<div class="linerow bold" ><label for="idTipoSolicitacaoComercial">Tipo:</label></div>
	                    <div class="linerow clear">
							<html:select property="idTipoSolicitacaoComercial" styleId="idTipoSolicitacaoComercial" styleClass="required" style="width:140px;" onChange="toggleInOfertaInadimplente(this)">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach var="tipoSolicitacaoComercialTO" items="${servicoSolicitacaoComercialTOCt.tipoSolicitacaoComercialTOList}">
									<html:option value="${tipoSolicitacaoComercialTO.idTipoSolicitacaoComercial}">${tipoSolicitacaoComercialTO.nmTipoSolicitacaoComercial}</html:option>
								</c:forEach>
							</html:select>
			            </div>
					</div>
					<div class="boxline">
						<div class="linerow bold"><label for=cdSolicitacaoComercial>C&oacute;digo:</label></div>
						<div class="linerow clear"><html:text property="cdSolicitacaoComercial" styleId="cdSolicitacaoComercial" styleClass="required" maxlength="254"/></div>
					</div>
					
					<div class="boxline"> 
						<div class="linerow bold" ><label for="Nome">Nome:</label></div>
						<div class="linerow clear"><html:text property="nmSolicitacaoComercial" styleId="nmSolicitacaoComercial" style="width:155px;" styleClass="required" maxlength="254"/></div>
					</div>
					
					<div class="boxline"> 
						<div class="linerow bold" ><label for="pzMaximoVigencia">Prazo m&aacute;ximo de vig&ecirc;ncia:</label></div>
						<div class="linerow clear"><html:text property="pzMaximoVigencia" styleId="pzMaximoVigencia" onKeyPress="return isIntNumber(event);" onKeyUp="$jq(this).val(integer($jq(this).val()));" style="width:155px;" styleClass="required" maxlength="254"/></div>
					</div>
	
					<div class="boxline"> 
						<div class="linerow bold" ><label for="pzMaximoAtendimento">Prazo m&aacute;ximo de atendimento:</label></div>
						<div class="linerow clear"><html:text property="pzMaximoAtendimento" styleId="pzMaximoAtendimento" onKeyPress="return isIntNumber(event);" onKeyUp="$jq(this).val(integer($jq(this).val()));" style="width:155px;" styleClass="required" maxlength="254"/></div>
					</div>
					<div class="boxline"> 
						<div class="linerow bold" ><label for="inDisponivel">Disponibilidade:</label></div>
	                    <div class="linerow clear">
							<html:select styleId="inDisponivel" property="inDisponivel" styleClass="required" style="width:120px; height: 60px;">
								<html:option  value="" >&nbsp;</html:option>
								<html:option  value="S" > Sim </html:option>
			                    <html:option  value="N" > Não </html:option >
		                   	</html:select>
	                   	</div>
					</div>
					<div class="boxline divInOfertaClienteInadimplente" style="display: none"> 
						<div class="linerow bold" ><label for="inOfertaClienteInadimplente">Inadimplente:</label></div>
	                    <div class="linerow clear">
							<html:select styleId="inOfertaClienteInadimplente" property="inOfertaClienteInadimplente" styleClass="required" style="width:120px; height: 60px;">
								<html:option  value="" >-- Selecione --</html:option>
								<html:option  value="S" > Sim </html:option>
			                	<html:option  value="N" > Não </html:option>
		                   	</html:select>
	                   	</div>
					</div>

					<br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_pesquisar_solicitacao_comercial_form" styleId="botao_pesquisar_solicitacao_comercial_form" onClick="searchSolicitacaoComercial()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
					</div>
				</catalogo:contentBox>
			</div>
			<c:if test="${slct}">
				<catalogo:contentBox title="Resultado da Pesquisa" >
					<!-- <netui:checkBoxGroup dataSource="actionForm.idsOfertaClienteInadimplenteSelecionados"> -->
						<display:table name="servicoSolicitacaoComercialTOListCt" id="servicoSolicitacaoComercialTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="" sortable="false">
								<c:if test="${servicoSolicitacaoComercialTO.cdTipoSolicitacaoComercial == '1' && nmTipoServico == 'BANDA LARGA'}">
									<%-- <netui:checkBoxOption tagId="idsOfertaClienteInadimplente" value="${servicoSolicitacaoComercialTO.idSolicitacaoComercial}" labelStyleClass="hide" /> --%>
									<html:checkbox property="idsOfertaClienteInadimplenteSelecionados" styleId="idsOfertaClienteInadimplente" value="${servicoSolicitacaoComercialTO.idSolicitacaoComercial}" StyleClass="hide"/>
								</c:if>
							</display:column>
							<display:column title="Código" property="cdSolicitacaoComercial" sortable="true" headerClass="sortable"/>
							<display:column title="Nome" property="nmSolicitacaoComercial" sortable="true" headerClass="sortable"/>
							<display:column title="Tipo" property="nmTipoSolicitacaoComercial" sortable="true" headerClass="sortable"/>
							<display:column title="Prazo Máximo de Vigência" sortable="true" headerClass="sortable">
								${servicoSolicitacaoComercialTO.pzMaximoVigencia} ${servicoSolicitacaoComercialTO.pzMaximoVigencia=='1'? "dia" : "dias"}
							</display:column>
							<display:column title="Prazo Máximo de Atendimento" sortable="true" headerClass="sortable">
								${servicoSolicitacaoComercialTO.pzMaximoAtendimento} ${servicoSolicitacaoComercialTO.pzMaximoAtendimento=='1'? "dia" : "dias"}
							</display:column>
							
							<display:column title="Inadimplente" sortable="true" headerClass="sortable" style="text-align: center">
								<c:if test="${servicoSolicitacaoComercialTO.cdTipoSolicitacaoComercial == '1'}">
									<catalogo:flagDefault test="${servicoSolicitacaoComercialTO.inOfertaClienteInadimplente=='S'}" />
								</c:if>		
							</display:column>

							<display:column title="Status" sortable="true" headerClass="sortable" style="text-align: center">
								<catalogo:flagDefault test="${servicoSolicitacaoComercialTO.inDisponivel=='S'}" />
							</display:column>
							<display:column title="Grupo Comercial" style="text-align:center; width:40px;">
								<img src="/catalogo/static_server/img/more_detail.gif" alt="Grupo Comercial" onclick="findCanalVendaBySolicitacaoComercial(${servicoSolicitacaoComercialTO.idSolicitacaoComercial},'${servicoSolicitacaoComercialTO.politicaDispSlctComercialTO.idPoliticaDispSlctComercial}')" width="15" style="cursor: pointer;"/>
							</display:column>
							<display:column title="Encargo" style="text-align:center; width:40px;">
								<img src="/catalogo/static_server/img/iconEncargos.gif" alt="Encargo" onclick="openEncargoFormSearch(${servicoSolicitacaoComercialTO.idSolicitacaoComercial})" width="15" style="cursor: pointer;"/>
							</display:column>
							<cata:temPermissao acao="ativarDesativarSolicitacaoComercial">
								<display:column title="Ação" sortable="true" headerClass="sortable" style="text-align: center">
									<a href="javascript:switchDisponibilidadeSolicitacaoComercial(${servicoSolicitacaoComercialTO.idSolicitacaoComercial},'${servicoSolicitacaoComercialTO.inDisponivel}')" >${servicoSolicitacaoComercialTO.inDisponivel=='S' ? "Desativar" : "Ativar"}</a>
								</display:column>
							</cata:temPermissao>
						</display:table>
						<div class="legendatable">
						<div><span class="bold">Legenda:</span></div>
						      <div><img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar"> <span> Alterar</span></div>
						      <div><div class="iconativo"></div><span>Ativo</span></div>
						      <div><div class="iconinativo"></div><span>Inativo</span></div>
						      <div><img src="/catalogo/static_server/img/more_detail.gif" alt="Grupo Comercial"/><span> Grupo Comercial </span></div>
						      <div><img src="/catalogo/static_server/img/iconEncargos.gif" alt="Encargo"/><span> Encargo </span></div>
						</div>							

					<!-- </netui:checkBoxGroup> -->
				</catalogo:contentBox>
				
				<div class="boxline divInOfertaClienteInadimplente" style="display: none "> 
					<div class="linerow bold" ><label for="inOfertaClienteInadimplenteNew" style="margin-left: 3px;">Inadimplente:</label><span class="required">*</span></div>
                    <div class="linerow clear">
						<html:select property="inOfertaClienteInadimplenteNew" styleId="inOfertaClienteInadimplenteNew" styleClass="required" style="width:100px; height: 60px; margin-left: 3px;">
							<html:option  value="S" > Sim </html:option>
		                	<html:option  value="N" > N&aacute;o </html:option>
	                   	</html:select>
                   	</div>
					</br>
					<html:button property="btCcreateOfertaClienteInadimplente" styleId="btCcreateOfertaClienteInadimplente" onClick="saveSolicitacaoInadimplente();" styleClass="btNavegacao74 " value="Gravar" alt="Gravar" title=""/>
					<span>&nbsp;</span>
				</div>

			</c:if>
			<c:if test="${slctc_1}">
				<div id="grupoComercialDiv">
					<script>
						posicionaDivErrosMidle("grupoComercialDiv");
						posicionaDivSucess("grupoComercialDiv");
					</script>
					<catalogo:contentBox title="Grupo Comercial" >
						<display:table name="servicoSolicitacaoComercialTOCt.canalVendaSolicitacaoComercialTOList" id="canalVendaSolicitacaoComercialTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="Grupo Comercial" property="canalVendaTO.nmCanalVenda" sortable="true" headerClass="sortable"/>
							<!-- erro col diplaytable -->
							<display:column title="Plano de Minutos" sortable="true" headerClass="sortable" style="text-align: center">
								<c:if test="${servicoSolicitacaoComercialTOCt.politicaDispSlctComercialTO.inPlanoMinuto=='S'}">
									<catalogo:flagDefault test="${canalVendaSolicitacaoComercialTO.inPossuiPlanoMinuto}" />
								</c:if>
							</display:column>
							<!-- erro col diplaytable -->
							<display:column title="Área de Concorrência" sortable="true" headerClass="sortable" style="text-align: center">
								<c:if test="${servicoSolicitacaoComercialTOCt.politicaDispSlctComercialTO.inAreaConcorrencia=='S'}">
									<catalogo:flagDefault test="${canalVendaSolicitacaoComercialTO.inPossuiAreaConcorrencia}" />
								</c:if>
							</display:column>
							<display:column title="Status" sortable="true" headerClass="sortable" style="text-align: center">
								<catalogo:flagDefault test="${canalVendaSolicitacaoComercialTO.inDisponivel=='S'}" />
							</display:column>
							<cata:temPermissao acao="configurarSolicitacaoComercial">
							<!-- erro col diplaytable -->
							<display:column title="Configurar" style="text-align:center; width:40px;">
								<c:if test="${servicoSolicitacaoComercialTOCt.politicaDispSlctComercialTO.inPlanoMinuto == 'S' || servicoSolicitacaoComercialTOCt.politicaDispSlctComercialTO.inAreaConcorrencia=='S'}">
									<img src="/catalogo/static_server/img/config.gif" alt="Configurar" onclick="findDispArConcPlMinByIdCnVendaSlct(${canalVendaSolicitacaoComercialTO.idCanalVendaSolicitacaoComercial})" width="15" style="cursor: pointer;"/>
								</c:if>
							</display:column>
							</cata:temPermissao>
							<cata:temPermissao acao="ativarDesativarSolicitacaoComercial">
								<display:column title="Ação" sortable="true" headerClass="sortable" style="text-align: center">
									<a href="javascript:switchDisponibilidadeCanalVendaSlctCmrl(${canalVendaSolicitacaoComercialTO.idCanalVendaSolicitacaoComercial},'${canalVendaSolicitacaoComercialTO.inDisponivel}')" >${canalVendaSolicitacaoComercialTO.inDisponivel=='S' ? "Desativar" : "Ativar"}</a>
								</display:column>
							</cata:temPermissao>
						</display:table>
	
						<br clear="all"/>
						<cata:temPermissao acao="grupoComercialSolicitacaoComercial">
							<c:if test="${servicoSolicitacaoComercialTOCt.idSistema != 8}">
								<div class="barra"></div>
								<div class="botao">
									<html:button property="openSlct" styleId="openSlct" onClick="openSlctc1Dialog1()" style="width: 105px" styleClass="btNavegacao120" value="Grupo Comercial" alt="Grupo Comercial" title=""/>
									<span>&nbsp;</span>
								</div>
							</c:if>
						</cata:temPermissao>
					</catalogo:contentBox>
				</div>
			</c:if>
			<c:if test="${slctc_1_dialog1}">
				<html:select property="idCanalVendaSelecionadoList" styleId="canalVendaSelecionadoForm" multiple="true" style="display: none;"/>
				<div id="slcte_4_1_dialog1jq" title="Configuração de Grupos Comerciais" class="solicitacaoComercialDialogs">
					<fieldset class="content" style="height: 150px">
						<div style="text-align: center; float: left; width: 100%;" id="div-acao-selecao">
							<br/>
							<div class="contentStepLeft">
								<label value="Grupos" for="canais-selecionaveis" /><br/>
								<html:select property="idCanalVendaSelecionavelList" styleId="idCanalVendaSelecionavelList"  style="width: 290px; height: 150px;" styleClass="canaisSelecionaveis" multiple="true">
									<html:option value="" >--Selecione--</html:option>
									<c:forEach var="canalVendaSelecionavelTO" items="${servicoSolicitacaoComercialTOCt.canalVendaSelecionavelTOList}">
										<html:option value="${canalVendaSelecionavelTO.idCanalVenda}">${canalVendaSelecionavelTO.nmCanalVenda}</html:option>
									</c:forEach>
								</html:select>
							</div>
							<div class="contentStepCenter">
								<br/>
								<button type="button" id="add-canais" class="acao" onclick="addOneValueSelect($('canais-selecionaveis'), $('canais-selecionados'));">&gt;</button><br/>
								<button type="button" id="remove-canais" class="acao" onclick="addOneValueSelect($('canais-selecionados'), $('canais-selecionaveis'));">&lt;</button><br/>
							</div>
							<div class="contentStepRight">
								<label value="Grupos Atuais" for="canais-selecionados"/><br/>
								<html:select property="idCanalVendaSelecionadoList" styleId="idCanalVendaSelecionadoList"  style="width: 290px; height: 150px;" styleClass="canaisSelecionados" multiple="true">
									<html:option value="" >--Selecione--</html:option>
									<c:forEach var="canalVendaSolicitacaoComercialTO" items="${servicoSolicitacaoComercialTOCt.canalVendaSolicitacaoComercialTOList}">
										<html:option value="${canalVendaSolicitacaoComercialTO.canalVendaTO.idCanalVenda}">${canalVendaSolicitacaoComercialTO.canalVendaTO.nmCanalVenda}</html:option>
									</c:forEach>
								</html:select>								
							</div>
						</div>
						<br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="createUpdate" styleId="createUpdate" onClick="createUpdateCanalVendaSlctCmrlList();" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
							<span>&nbsp;</span>
							</br></br>
						</div>
					</fieldset>
				</div>
				<script>
					$jq(".canaisSelecionaveis").attr("id", "canais-selecionaveis");
					$jq(".canaisSelecionados").attr("id", "canais-selecionados");
				</script>
			</c:if>
			<c:if test="${slctc_1_dialog2}">
				<html:select property="idPlanoDeMinutosSlctSelecionadoList" styleId="plMinutoSelecionadoForm" multiple="true" style="display: none;"/>
				<html:select property="idAreaConcorrenciaSlctSelecionadoList" styleId="areaConcSelecionadoForm" multiple="true" style="display: none;"/>
				<div id="slctc_1_dialog2" title="Configuração de Plano de Minutos" class="solicitacaoComercialDialogs">
					<c:if test="${servicoSolicitacaoComercialTOCt.politicaDispSlctComercialTO.inPlanoMinuto=='S'}">
						<fieldset class="content" style="height: 150px">
							<div style="text-align: center; float: left; width: 100%;" id="div-acao-selecao">
								<br/>
								<div class="contentStepLeft">
									<label value="Planos de Minuto" for="plMinSlctSelecionaveis"/><br/>
									<html:select property="idPlanoDeMinutosSlctSelecionavelList" styleId="idPlanoDeMinutosSlctSelecionavelList"  style="width: 290px; height: 150px;" styleClass="plMinSlctSelecionaveisClass" multiple="true">
										<html:option value="" >--Selecione--</html:option>
										<c:forEach var="espServicoPlanoMinutoSlctSelecionavelTO" items="${servicoSolicitacaoComercialTOCt.espServicoPlanoMinutoSlctSelecionavelTOList}">
											<html:option value="${espServicoPlanoMinutoSlctSelecionavelTO.idServico}">${espServicoPlanoMinutoSlctSelecionavelTO.nmServico}</html:option>
										</c:forEach>
									</html:select>										
								</div>
								<div class="contentStepCenter">
									<br/>
									<html:button property="add-canais" styleId="add-canais" styleClass="acao" onclick="addOneValueSelect($('plMinSlctSelecionaveis'), $('plMinSlctSelecionados'));">&gt;</html:button><br/>
									<html:button property="remove-canais" styleId="remove-canais"  styleClass="acao" onclick="addOneValueSelect($('plMinSlctSelecionados'), $('plMinSlctSelecionaveis'));">&lt;</html:button><br/>
								</div>
								<div class="contentStepRight">
									<label value="Planos de Minuto Atuais" for="plMinSlctSelecionados"/><br/>
									<html:select property="idPlanoDeMinutosSlctSelecionadoList" styleId="idPlanoDeMinutosSlctSelecionadoList"  style="width: 290px; height: 150px;" styleClass="plMinSlctSelecionadosClass" multiple="true">
										<html:option value="" >--Selecione--</html:option>
										<c:forEach var="espServicoPlanoMinutoSlctSelecionadoTO" items="${servicoSolicitacaoComercialTOCt.espServicoPlanoMinutoSlctSelecionadoTOList}">
											<html:option value="${espServicoPlanoMinutoSlctSelecionadoTO.idServico}">${espServicoPlanoMinutoSlctSelecionadoTO.nmServico}</html:option>
										</c:forEach>
									</html:select>										
								</div>
							</div>
						</fieldset>
					</c:if>
					<br/>
					<div id="errorPoliticaDispSlctComercialTO" style="color:red !important;display:none !important">Favor adicionar ao menos um registro de Plano de Minutos e &Aacute;rea de Concorr&ecirc;ncia!</div>
					<c:if test="${servicoSolicitacaoComercialTOCt.politicaDispSlctComercialTO.inAreaConcorrencia=='S'}">
						<fieldset class="content" style="height: 150px">
							<div style="text-align: center; float: left; width: 100%;" id="div-acao-selecao">
								<br/>
								<div class="contentStepLeft">
									<label value="Área de Concorrência" for="arConcSlctSelecionaveis"/><br/>
									<html:select property="idAreaConcorrenciaSlctSelecionavelList" styleId="idAreaConcorrenciaSlctSelecionavelList"  style="width: 290px; height: 150px;" styleClass="arConcSlctSelecionaveisClass" multiple="true">
										<html:option value="" >--Selecione--</html:option>
										<c:forEach var="areaConcorrenciaSlctSelecionavelTO" items="${servicoSolicitacaoComercialTOCt.areaConcorrenciaSlctSelecionavelTOList}">
											<html:option value="${areaConcorrenciaSlctSelecionavelTO.idAreaConcorrencia}">${areaConcorrenciaSlctSelecionavelTO.nmAreaConcorrencia}</html:option>
										</c:forEach>
									</html:select>									
								</div>
								<div class="contentStepCenter">
									<br/>
									<html:button property="add-canais" styleId="add-canais" styleClass="acao" onclick="addOneValueSelect($('arConcSlctSelecionaveis'), $('arConcSlctSelecionadas'));">&gt;</html:button><br/>
									<html:button property="remove-canais" styleId="remove-canais"  styleClass="acao" onclick="addOneValueSelect($('arConcSlctSelecionadas'), $('arConcSlctSelecionaveis'));">&lt;</html:button><br/>									
								</div>
								<div class="contentStepRight">
									<label value="Áreas de Concorrência Atuais" for="arConcSlctSelecionadas"/><br/>
									<html:select property="idAreaConcorrenciaSlctSelecionadoList" styleId="idAreaConcorrenciaSlctSelecionadoList"  style="width: 290px; height: 150px;" styleClass="arConcSlctSelecionadasClass" multiple="true">
									    <html:option value="" >--Selecione--</html:option>
										<c:forEach var="areaConcorrenciaSlctSelecionadaTO" items="${servicoSolicitacaoComercialTOCt.areaConcorrenciaSlctSelecionadaTOList}">
											<html:option value="${areaConcorrenciaSlctSelecionadaTO.idAreaConcorrencia}">${areaConcorrenciaSlctSelecionadaTO.nmAreaConcorrencia}</html:option>
										</c:forEach>
									</html:select>										
								</div>
							</div>
						</fieldset>
					</c:if>
					<br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="createUpdate" styleId="createUpdate" onClick="createUpdateDispArConcPlMinByIdCnVendaSlct()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
						<span>&nbsp;</span>
						</br></br>
					</div>
				</div>
				<script>
					$jq(".plMinSlctSelecionaveisClass").attr("id", "plMinSlctSelecionaveis");
					$jq(".plMinSlctSelecionadosClass").attr("id", "plMinSlctSelecionados");
					$jq(".arConcSlctSelecionaveisClass").attr("id", "arConcSlctSelecionaveis");
					$jq(".arConcSlctSelecionadasClass").attr("id", "arConcSlctSelecionadas");
				</script>
			</c:if>
			<c:if test="${slcte_1}" >
				<div id="pesqEncargoDiv">
					<script>
						posicionaDivErrosMidle("pesqEncargoDiv");
						posicionaDivSucess("pesqEncargoDiv");
					</script>
					<catalogo:contentBox title="Filtrar Encargo" >
						<div class="boxline">
							<div class="linerow bold"><label for=dsEncargo>Nome do Encargo:</label></div>
							<div class="linerow clear"><html:text property="dsEncargo" styleId="dsEncargo" styleClass="required" maxlength="254"/></div>
						</div>
						
						<div class="boxline"> 
							<div class="linerow bold" ><label for="pzGratuidade" >Prazo de Gratuidade:</label></div>
							<div class="linerow clear"><html:text property="pzGratuidade" styleId="pzGratuidade" styleClass="required" maxlength="254" onKeyPress="return isIntNumber(event);" onKeyUp="$jq(this).val(integer($jq(this).val()));"/></div>
						</div>
						
						<div class="boxline"> 
							<div class="linerow bold" ><label for="nmPacote">Pacote:</label></div>
							<div class="linerow clear"><html:text property="nmPacote" styleId="nmPacote" styleClass="required" maxlength="254"/></div>
						</div>
		
						<div class="boxline"> 
							<div class="linerow bold" ><label for="idTipoEncargo">Tipo:</label></div>
							<div class="linerow clear">
								<html:select property="idTipoEncargo" styleId="idTipoEncargo"  styleClass="required" style="width:150px;" >
								    <html:option value="" >--Selecione--</html:option>
									<c:forEach var="tipoEncargoTO" items="${servicoSolicitacaoComercialTOCt.tipoEncargoTOList}">
										<html:option value="${tipoEncargoTO.idTipoEncargo}">${tipoEncargoTO.nmTipoEncargo}</html:option>
									</c:forEach>
								</html:select>								
							</div>
						</div>
						<br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="botao_pesquisar_encargos_form" styleId="botao_pesquisar_encargos_form" onClick="searchEncargoBySolicitacaoComercial()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
							<span>&nbsp;</span>
						</div>
					</catalogo:contentBox>
				</div>
			</c:if>
			<c:if test="${slcte_2}" >
				<catalogo:contentBox title="Encargos" >
					<display:table name="servicoSolicitacaoComercialTOCt.encargoPoliticaPrecificacaoTOList" id="encargoPoliticaPrecificacaoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
						<display:column title="Tipo" property="tipoEncargoTO.dsTipoEncargo" sortable="true" headerClass="sortable"/>
						<display:column title="Nome do Encargo" property="dsEncargo" sortable="true" headerClass="sortable"/>
						<display:column title="Atributo" property="politicaValorAtributoTOListConcat" sortable="true" headerClass="sortable"/>
						<display:column title="Preço" property="vlEncargo" sortable="true" headerClass="sortable"/>
						<display:column title="Classe de Linha" property="nmClasseServicoPrincipal" sortable="true" headerClass="sortable"/>
						<display:column title="Pacote" property="nmPacote" sortable="true" headerClass="sortable"/>
						<display:column title="Empresa Instaladora" property="nmEmpresaInstaladora" sortable="true" headerClass="sortable"/>
						<display:column title="Plano de Financiamento" style="text-align:center; width:40px;">
							<c:if test="${encargoPoliticaPrecificacaoTO.tipoEncargoTO.nmTipoEncargo=='CA'}">
								<img src="/catalogo/static_server/img/iconFinanciamento.gif" alt="Plano de Financiamento" onclick="openFinanciamentoFormSearch(${encargoPoliticaPrecificacaoTO.idEncargo})" width="15" style="cursor: pointer;"/>
							</c:if>
						</display:column>
					</display:table>
				</catalogo:contentBox>
			</c:if>
			<c:if test="${slcte_3}" >
				<div id="pesqFinaciamentoDiv">
					<script>
						posicionaDivErrosMidle("pesqFinaciamentoDiv");
						posicionaDivSucess("pesqFinanciamentoDiv");
					</script>
					<catalogo:contentBox title="Pesquisar Financiamento" >
						<div class="boxline"> 
							<div class="linerow bold" ><label for="idCanalVendaEncargo">Grupo Comercial:</label><span class="required">*</span></div>
							<div class="linerow clear">
								<html:select property="idCanalVendaEncargo" styleId="idCanalVendaEncargo"  styleClass="required" style="width:150px;" >
								    <html:option value="" >--Selecione--</html:option>
									<c:forEach var="canalVendaCndcPgtoEncargoTO" items="${servicoSolicitacaoComercialTOCt.canalVendaCndcPgtoEncargoTOList}">
										<html:option value="${canalVendaCndcPgtoEncargoTO.idCanalVenda}">${canalVendaCndcPgtoEncargoTO.nmCanalVenda}</html:option>
									</c:forEach>
								</html:select>									
							</div>
						</div>
		
						<br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="botao_pesquisar_cndc_pgto_encargo" styleId="botao_pesquisar_cndc_pgto_encargo" onClick="searchCndcPgtoEncargo();" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
							<span>&nbsp;</span>
							<cata:temPermissao acao="novoSolicitacaoComercial">
								<html:button property="botao_novo_cndc_pgto_encargo" styleId="botao_novo_cndc_pgto_encargo" onClick="openAssociacaoFinanciamento();" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/>
								<span>&nbsp;</span>
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</div>
			</c:if>

			<c:if test="${slcte_4_2}" >
				<catalogo:contentBox title="Resultado da Pesquisa" >
					<display:table name="servicoSolicitacaoComercialTOCt.condicaoPagamentoEncargoTOList" id="condicaoPagamentoEncargoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
						<display:column title="Grupo Comercial" property="canalVendaTO.nmCanalVenda" sortable="true" headerClass="sortable"/>
						<display:column title="Nome do Plano" property="condicaoPagamentoTO.nmCondicaoPagamento" sortable="true" headerClass="sortable"/>
						<display:column title="Quantidade de Parcelas" property="condicaoPagamentoTO.qtParcelas" sortable="true" headerClass="sortable"/>
						<display:column title="Status" sortable="true" headerClass="sortable" style="text-align: center">
							<catalogo:flagDefault test="${condicaoPagamentoEncargoTO.inDisponivel=='S'}" />
						</display:column>
						<!-- erro col diplaytable -->
						<display:column title="Plano de Minutos" sortable="true" headerClass="sortable" style="text-align: center">
							<c:if test="${condicaoPagamentoEncargoTO.politicaDispCndcPagamentoTO.inPlanoMinuto=='S'}">
								<catalogo:flagDefault test="${condicaoPagamentoEncargoTO.inPossuiPlanoMinuto}" />
							</c:if>
						</display:column>
						<!-- erro col diplaytable -->
						<display:column title="Área de Concorrência" sortable="true" headerClass="sortable" style="text-align: center">
							<c:if test="${condicaoPagamentoEncargoTO.politicaDispCndcPagamentoTO.inAreaConcorrencia=='S'}">
								<catalogo:flagDefault test="${condicaoPagamentoEncargoTO.inPossuiAreaConcorrencia}" />
							</c:if>
						</display:column>
						<cata:temPermissao acao="alterarSolicitacaoComercial">
							<display:column title="Alterar" style="text-align:center; width:40px;">
								<img src="/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" onclick="openEditAssociacaoFinanciamento('${condicaoPagamentoEncargoTO.idCondicaoPagamentoEncargo}','${condicaoPagamentoEncargoTO.condicaoPagamentoTO.idCondicaoPagamento}','${condicaoPagamentoEncargoTO.canalVendaTO.idCanalVenda}')" width="15" style="cursor: pointer;"/>
							</display:column>
						</cata:temPermissao>
						<cata:temPermissao acao="configurarSolicitacaoComercial">
						<!-- erro col diplaytable -->
						<display:column title="Configurar" style="text-align:center; width:40px;">
							<c:if test="${condicaoPagamentoEncargoTO.politicaDispCndcPagamentoTO.inPlanoMinuto=='S' || condicaoPagamentoEncargoTO.politicaDispCndcPagamentoTO.inAreaConcorrencia=='S'}">
								<img id="show_slcte_4_2_dialog1" src="/catalogo/static_server/img/config.gif" alt="Configurar" onclick="findDispArConcPlMinByIdCndcPgtoEnc(${condicaoPagamentoEncargoTO.idCondicaoPagamentoEncargo})" width="15" style="cursor: pointer;"/>
							</c:if>
						</display:column>
						</cata:temPermissao>
						<cata:temPermissao acao="ativarDesativarSolicitacaoComercial">
							<display:column title="Ação" sortable="true" headerClass="sortable" style="text-align: center">
								<a href="javascript:switchDisponibilidadeCndcPgtoEncargo(${condicaoPagamentoEncargoTO.idCondicaoPagamentoEncargo},'${condicaoPagamentoEncargoTO.inDisponivel}')" >${condicaoPagamentoEncargoTO.inDisponivel=='S' ? "Desativar" : "Ativar"}</a>
							</display:column>
						</cata:temPermissao>
					</display:table>
				</catalogo:contentBox>
			</c:if>

			<c:if test="${slcte_4_1}" >
				<catalogo:contentBox title="Associação de Financiamento" >
					<div class="boxline"> 
						<div class="linerow bold" ><label for="idCondicaoPagamentoNew">Nome do Plano de Financiamento:</label></div>
						<div class="linerow clear">
							<html:select property="idCondicaoPagamentoNew" styleId="idCondicaoPagamentoNew"  styleClass="required" style="width:150px;" >
							    <html:option value="" >--Selecione--</html:option>
								<c:forEach var="condicaoPagamentoFullTO" items="${servicoSolicitacaoComercialTOCt.condicaoPagamentoFullTOList}">
									<html:option value="${condicaoPagamentoFullTO.idCondicaoPagamento}">${condicaoPagamentoFullTO.nmCondicaoPagamento}</html:option>
								</c:forEach>
							</html:select>								
						</div>
					</div>
					<div class="boxline"> 
						<div class="linerow bold" ><label for="idCanalVendaNew">Grupo Comercial:<br />&nbsp;</label></div>
						<div class="linerow clear">
							<html:select property="idCanalVendaNew" styleId="idCanalVendaNew"  styleClass="required" style="width:150px;" >
							    <html:option value="" >--Selecione--</html:option>
								<c:forEach var="canalVendaFullTO" items="${servicoSolicitacaoComercialTOCt.canalVendaFullTOList}">
									<html:option value="${canalVendaFullTO.idCanalVenda}">${canalVendaFullTO.nmCanalVenda}</html:option>
								</c:forEach>
							</html:select>								
						</div>
					</div>
					<br clear="all"/>
					<div class="barra"></div>
					<cata:temPermissao acao="gravarSolicitacaoComercial">
						<div class="botao">
							<html:button property="botao_gravar_cndc_pgto_encargo" styleId="botao_gravar_cndc_pgto_encargo" onClick="createUpdateCndcPgtoEncargo()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
							<span>&nbsp;</span>
						</div>
					</cata:temPermissao>
				</catalogo:contentBox>
			</c:if>

			<c:if test="${slcte_4_2_dialog1}" >
				<html:select property="idPlanoDeMinutosCndcSelecionadoList" styleId="plMinCndcSelecionadosForm" multiple="true" style="display: none;"/>
				<html:select property="idAreaConcorrenciaCndcSelecionadoList" styleId="arConcCndcSelecionadasForm" multiple="true" style="display: none;"/>
				<div id="slcte_4_2_dialog1" title="Configuração de Plano de Minutos" class="solicitacaoComercialDialogs">
					<c:if test="${condicaoPagamentoEncargoTO.politicaDispCndcPagamentoTO.inPlanoMinuto=='S'}">
						<fieldset class="content" style="height: 150px">
							<div style="text-align: center; float: left; width: 100%;" id="div-acao-selecao">
								<br/>
								<div class="contentStepLeft">
									<label value="Planos de Minuto" for="plMinCndcSelecionaveis"/><br/>
									<html:select property="idPlanoDeMinutosCndcSelecionavelList" styleId="idPlanoDeMinutosCndcSelecionavelList"  styleClass="plMinCndcSelecionaveisClass" style="width: 290px; height: 120px;" multiple="true" >
									    <html:option value="" >--Selecione--</html:option>
										<c:forEach var="espServicoPlanoMinutoCndcSelecionavelTO" items="${servicoSolicitacaoComercialTOCt.espServicoPlanoMinutoCndcSelecionavelTOList}">
											<html:option value="${espServicoPlanoMinutoCndcSelecionavelTO.idServico}">${espServicoPlanoMinutoCndcSelecionavelTO.nmServico}</html:option>
										</c:forEach>
									</html:select>									
								</div>
								<div class="contentStepCenter">
									<br/>
									<html:button property="add-canais" styleId="add-canais" styleClass="acao" onclick="addOneValueSelect($('plMinCndcSelecionaveis'), $('plMinCndcSelecionados'));">&gt;</html:button><br/>
									<html:button property="remove-canais" styleId="remove-canais"  styleClass="acao" onclick="addOneValueSelect($('plMinCndcSelecionados'), $('plMinCndcSelecionaveis'));">&lt;</html:button><br/>									
								</div>
								<div class="contentStepRight">
									<label value="Planos de Minuto Atuais" for="plMinCndcSelecionados"/><br/>
									<html:select property="idPlanoDeMinutosCndcSelecionadoList" styleId="idPlanoDeMinutosCndcSelecionadoList"  styleClass="plMinCndcSelecionadosClass" style="width: 290px; height: 120px;" multiple="true" >
									    <html:option value="" >--Selecione--</html:option>
										<c:forEach var="espServicoPlanoMinutoCndcSelecionadoTO" items="${servicoSolicitacaoComercialTOCt.espServicoPlanoMinutoCndcSelecionadoTOList}">
											<html:option value="${espServicoPlanoMinutoCndcSelecionadoTO.idServico}">${espServicoPlanoMinutoCndcSelecionadoTO.nmServico}</html:option>
										</c:forEach>
									</html:select>										
								</div>
							</div>
						</fieldset>
					</c:if>
					<br/>
					<div id="errorPoliticaDispCndcPagamentoTO" style="color:red !important;display:none !importante">Favor adicionar ao menos um registro de Plano de Minutos e &Aacute;rea de Concorr&ecirc;ncia!</div>
					<c:if test="${condicaoPagamentoEncargoTO.politicaDispCndcPagamentoTO.inAreaConcorrencia=='S'}">
						<fieldset class="content" style="height: 150px">
							<div style="text-align: center; float: left; width: 100%;" id="div-acao-selecao">
								<br/>
								<div class="contentStepLeft">
									<label value="Área de Concorrência" for="arConcCndcSelecionaveis"/><br/>
									<html:select property="idAreaConcorrenciaCndcSelecionavelList" styleId="idAreaConcorrenciaCndcSelecionavelList"  styleClass="arConcCndcSelecionaveisClass" style="width: 290px; height: 120px;" multiple="true" >
									    <html:option value="" >--Selecione--</html:option>
										<c:forEach var="areaConcorrenciaCndcSelecionavelTO" items="${servicoSolicitacaoComercialTOCt.areaConcorrenciaCndcSelecionavelTOList}">
											<html:option value="${areaConcorrenciaCndcSelecionavelTO.idAreaConcorrencia}">${areaConcorrenciaCndcSelecionavelTO.nmAreaConcorrencia}</html:option>
										</c:forEach>
									</html:select>										
								</div>
								<div class="contentStepCenter">
									<br/>
									<html:button property="add-canais" styleId="add-canais" styleClass="acao" onclick="addOneValueSelect($('arConcCndcSelecionaveis'), $('arConcCndcSelecionadas'));">&gt;</html:button><br/>
									<html:button property="remove-canais" styleId="remove-canais"  styleClass="acao" onclick="addOneValueSelect($('arConcCndcSelecionadas'), $('arConcCndcSelecionaveis'));">&lt;</html:button><br/>									
								</div>
								<div class="contentStepRight">
									<label value="Áreas de Concorrência Atuais" for="arConcCndcSelecionadas"/><br/>
									<html:select property="idAreaConcorrenciaCndcSelecionadoList" styleId="idAreaConcorrenciaCndcSelecionadoList"  styleClass="arConcCndcSelecionadasClass" style="width: 290px; height: 120px;" multiple="true" >
									    <html:option value="" >--Selecione--</html:option>
										<c:forEach var="areaConcorrenciaCndcSelecionadaTO" items="${servicoSolicitacaoComercialTOCt.areaConcorrenciaCndcSelecionadaTOList}">
											<html:option value="${areaConcorrenciaCndcSelecionadaTO.idAreaConcorrencia}">${areaConcorrenciaCndcSelecionadaTO.nmAreaConcorrencia}</html:option>
										</c:forEach>
									</html:select>										
								</div>
							</div>
						</fieldset>
					</c:if>
					<br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="createUpdate" styleId="createUpdate" onClick="createUpdateDispArConcPlMinByIdCndcPgtoEnc()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
						<span>&nbsp;</span>
						</br></br>
					</div>
				</div>
				<script>
					$jq(".plMinCndcSelecionaveisClass").attr("id", "plMinCndcSelecionaveis");
					$jq(".plMinCndcSelecionadosClass").attr("id", "plMinCndcSelecionados");
					$jq(".arConcCndcSelecionaveisClass").attr("id", "arConcCndcSelecionaveis");
					$jq(".arConcCndcSelecionadasClass").attr("id", "arConcCndcSelecionadas");
				</script>
			</c:if>
			<html:hidden property="politicaDispCndcPagamentoTOinPlanoMinuto" styleId="politicaDispCndcPagamentoTOinPlanoMinuto" value="${condicaoPagamentoEncargoTO.politicaDispCndcPagamentoTO.inPlanoMinuto}"/>
			<html:hidden property="politicaDispCndcPagamentoTOinAreaConcorrencia" styleId="politicaDispCndcPagamentoTOinAreaConcorrencia" value="${condicaoPagamentoEncargoTO.politicaDispCndcPagamentoTO.inAreaConcorrencia}"/>
			<html:hidden property="politicaDispSlctComercialTOinAreaConcorrencia" styleId="politicaDispSlctComercialTOinAreaConcorrencia" value="${servicoSolicitacaoComercialTOCt.politicaDispSlctComercialTO.inAreaConcorrencia}"/>
			<html:hidden property="politicaDispSlctComercialTOinPlanoMinuto" styleId="politicaDispSlctComercialTOinPlanoMinuto" value="${servicoSolicitacaoComercialTOCt.politicaDispSlctComercialTO.inPlanoMinuto}"/>
		</html:form>
	</jsp:body>	
</catalogo:blankTemplate>