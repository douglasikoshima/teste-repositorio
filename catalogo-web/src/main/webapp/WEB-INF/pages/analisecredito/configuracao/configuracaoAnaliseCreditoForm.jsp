<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:defaultTemplate titulo="Home Catalogo">
   <jsp:attribute name="headScripts">
		<style>
			ul.connectedSortable { list-style-type: none; margin: 0; padding: 0; float: left; margin-bottom: 10px;  margin-right: 10px; border: 1px solid #5875A1; height: 100px; width: 235px; float: left; overflow-y: scroll; overflow-x: hidden;}
			ul.connectedSortable li { margin: 0 2px 2px 2px; padding: 5px; font-size: 9px; width: 100%; }
			.contentScore{float: left; margin-top: 5px; text-align: left;}
			li.ui-state-default div.ui-state-default {width: 50%; float: left; font-size: 9px; border: 0px;}
			h3.labelAccordion {font-size: 11px;	font-weight: bold; width:50%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase}
		</style>
		<script type="text/javascript" src="/catalogo/static_server/js/configuracaoanalisecredito.js"></script>
   </jsp:attribute>
   <jsp:body>
		<div class="breadcrumb">Parametriza&ccedil;&atilde;o > An&aacute;lise de Cr&eacute;dito > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/ConfiguracaoAnaliseCreditoAction.do';" style="cursor: pointer;">Configura&ccedil;&atilde;o de An&aacute;lise de Cr&eacute;dito</a><span></div>
		<script>carregaMenu('mn_parametrizacao_configuracao_analise');</script>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/save.do" styleId="acaoForm">
			<div class="secao_conteudo">
				<catalogo:contentBox title="" requiredFields="true" doubt="true">
					<html:hidden property="inDisponivel" styleId="inDisponivel"/>
					<html:hidden property="tpClassificacao" styleId="tpClassificacao"/>
					<html:hidden property="idCabecalhoAnaliseCredito" styleId="idCabecalhoAnaliseCredito"/>
					<html:hidden property="idCabecalhoAnaliseCreditoCopia" styleId="idCabecalhoAnaliseCreditoCopia"/>
					<html:hidden property="salvarConfigScore" styleId="salvarConfigScore"/>
					<html:hidden property="canaisConfiguraveisPorRegionalJSON" styleId="canaisConfiguraveisPorRegionalJSON"/>
					<html:hidden property="canalConfiguravelPorRegional" styleId="canalConfiguravelPorRegional"/>
					
					<table border="0" cellpadding="0" cellspacing="2" width="95%">
					<tr>
						<td align="left"><div class="label-form-bold label_required" style="text-align: left;"><label for="nome">Nome:<font size="1px" color="#EEB422">*</font></label></div></td>
						<td align="left"><div class="label-form-bold label_required" style="text-align: left;"><label for=idCanalAtendimento>Canal Atendimento:<font size="1px" color="#EEB422">*</font></label></div></td>
						<td align="left"><div class="label-form-bold label_required" style="text-align: left;"><label for="status">Status:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div></td>
					</tr>
					<tr>
						<td>
							<html:text property="nome" styleId="nome" style="text-align: left; width: 200px;"/>
						</td>
						<td>
                            <html:select property="idCanalAtendimento" styleId="idCanalAtendimento" style="text-align: left; width: 250px;" onChange="controlarRegional();">
                                <html:option value="">--Selecione--</html:option>
                                <c:forEach var="canalAtendimentoTO" items="${canalAtendimentoList}">
                                    <html:option value="${canalAtendimentoTO.idCanalAtendimento}" styleId="${canalAtendimentoTO.idCanalAtendimento}">${canalAtendimentoTO.nmCanal}</html:option>
                                </c:forEach>
                            </html:select>	                    	
						</td>
						<td>
							<c:if test="${configuracaoAnaliseCreditoForm.status != null}">
								<html:text property="status" styleId="status" disabled="true" style="text-align: left; width: 190px;"/>
							</c:if>
							<c:if test="${configuracaoAnaliseCreditoForm.status == null}">
								<html:text property="status" styleId="status" disabled="true" style="text-align: left; width: 190px;" value="Inativa"/>
							</c:if>
						</td>
					</tr>
					</table>	
					<br clear="all"/><br clear="all"/>
			</catalogo:contentBox>
			
			<!-- Lista Categorias -->
			<div class="fleft" style="width:36%; margin-bottom: 20px;" >
						<div class="label-form-bold label_required" style="text-align: left; width:200px;"><label for=idCanalAtendimento>Selecione a Categoria:<font size="1px" color="#EEB422">*</font></label></div><br>
                        <html:select property="idCategoriaScore" styleId="idCategoriaScore" style="width: 300px;" onChange="controlarListaCategoria();">
                            <html:option value="">--Selecione--</html:option>
                            <c:forEach var="categoriaScoreTO" items="${categoriaScoreList}">
                                <html:option value="${categoriaScoreTO.idCategoriaScore}" styleId="${categoriaScoreTO.classificacaoCategoriaScoreTO.idClassificacaoCategoriaScore}">${categoriaScoreTO.nmCategoriaScore}</html:option>
                            </c:forEach>
                        </html:select>		                    
					</div>
			</div>
			<br clear="all"/>
			
            <c:if test="${regionalList != null}">	
            <br clear="all"/>	
              <div class="fleft" style="width:100%; margin-bottom: 20px;" id="regionalCheck" style="display:none"> 
				<div class="label-form-bold label_required" style="text-align: right; width: 30px;"><label>Regional:<font size="1px" color="#EEB422">*</font></label></div><br><br>
				<div style="float: left;">
					<div id="divRegionaisSearch">
	                    <c:forEach var="regionalTO" items="${regionalList}">
	                        <div class="idRegional" style="width:100px;float:left">
	                              <input type="checkbox" id="idRegionaisSearch" value="${regionalTO.idRegional}" name="${regionalTO.nmRegional}" class="semBorda checkbox" <c:if test="${true}">checked</c:if>>${regionalTO.nmRegional}&nbsp;
	                        </div>
	                    </c:forEach>
	                </div>
                </div>
              </div>	
            </c:if>
			<br clear="all"/>
			<div id="pesquisa" style="display:none">      
			   <br clear="all"/>
				<div id="dscCategoriaName" style="width:45%;text-align:left"></div>
				<br clear="all"/>
				<catalogo:contentBox title="Pesquisar" requiredFields="false" doubt="false">
				
					<table border="0" cellpadding="0" cellspacing="2" width="95%">
					<tr>
						<td align="left"><div class="label-form-bold label_required" style="text-align: left;"><label for="nome">Nome:</label></div></td>
						<td align="left"><div class="label-form-bold label_required" style="text-align: left;"><label for=idCanalAtendimento>Preço de R$:</label></div></td>
						<td align="left"><div class="label-form-bold label_required" style="text-align: left;"><label for="status">Até R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div></td>
						<td align="left"><div class="label-form-bold label_required" style="text-align: left;"><label for="status">Score:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div></td>
					</tr>
					<tr>
						<td>
							<html:text property="nomeSearch" styleId="nomeSearch" style="text-align: left; width: 200px;"/>
						</td>
						<td>	
							<html:text property="precoDeSearch" styleId="precoDeSearch"  style="width: 100px; float: left; margin-left: 3px; text-align:right;" onKeyDown="mascara(this,valor);" onKeyPress="mascara(this,valor);" onKeyUp="mascara(this,valor);"/>
						</td>
						<td>
							<html:text property="precoAteSearch" styleId="precoAteSearch" style="width: 100px; float: left; text-align:right;" onKeyDown="mascara(this,valor);" onKeyPress="mascara(this,valor);" onKeyUp="mascara(this,valor);"/>
						</td>
						 <td>
	                        <html:select property="idAnaliseCreditoSearch" styleId="idAnaliseCreditoSearch" style="text-align: left; width: 100px;">
	                           <html:option value="">--Selecione--</html:option>
	                           <c:forEach var="analiseCreditoTO" items="${analiseCreditoList}">
	                               <html:option value="${analiseCreditoTO.idAnaliseCredito}" >${analiseCreditoTO.cdCor}</html:option>
	                           </c:forEach>
	                        </html:select>		                    
						</td>
					</tr>
					</table>	
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="bot_pesquisa" styleId="bot_pesquisa" tabindex="10" onClick="pesquisar();" styleClass="btNavegacao74" value="Pesquisar"/><span>&nbsp;</span>
					</div>
				</catalogo:contentBox>
			</div>	
			<br/>
			
			<div id="resultado_pesquisa" style="display:none">
			</div>
			<html:select property="configuracoes" styleId="configuracoes" multiple="true"  size="10"  style="height:100px;width:200px;display:none"/>
			<html:select property="configuracoesRemove" styleId="configuracoesRemove" multiple="true"  size="10"  style="height:100px;width:200px;display:none"/>
			<html:select property="itensPesquisados" styleId="itensPesquisados" multiple="true"  size="10"  style="height:100px;width:200px;display:none"/>
            <html:hidden property="configuracoesJson" styleId="configuracoesJson"/>
			</html:form>
			<div class="spacer"></div>
			<br clear="all"/><br clear="all"/>
			<div class="barra"></div>
			<div class="botao" id="divBotoes" style="display:none">
				<html:button property="botao_gravar" styleId="botao_gravar" onClick="save()" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/><span>&nbsp;</span>
			</div>
			<c:if test="${labelError != null}">
				<script>
					generateContentError("${labelError}")
				</script>
			</c:if>   
    </jsp:body>        
</catalogo:defaultTemplate>
