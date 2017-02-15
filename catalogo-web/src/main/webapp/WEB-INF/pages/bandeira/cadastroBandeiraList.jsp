<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<catalogo:defaultTemplate titulo="Home Catalogo">
	<jsp:attribute name="headScripts">
		<script type="text/javascript" src="/catalogo/static_server/js/bandeira.js"></script>
	</jsp:attribute>
		<script>carregaMenu('mn_parametrizacao_pagamento_cadastro_bandeira');</script>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/bandeira/search.do" styleId="bandeiraForm" enctype="multipart/form-data">
			<div class="secao_conteudo">
 			<catalogo:contentBox title="Cadastro de Bandeira" doubt="true"> 
					<html:hidden property="idBandeira" styleId="idBandeira"/>
					<div class="fleft" style="width:43%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="width: 50px;"><label for="nmBandeiraSearch">Nome:</label></div>
						<html:text property="nmBandeiraSearch" styleId="nmBandeiraSearch" style="width:300px;" styleClass="required" maxlength="254" />
					</div>
					<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="width: 100px;"><label for="sgBandeiraSAPSearch">Sigla SAP:</label></div>
						<html:text property="sgBandeiraSAPSearch" styleId="sgBandeiraSAPSearch" style="width:100px;" styleClass="required" maxlength="10"/>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<cata:temPermissao acao="novoBandeira">
							<html:button property="bt_novo" styleId="botao_pesquisar_form" onClick="create()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/>
							<span>&nbsp;</span>
						</cata:temPermissao>
						<html:button property="bt_pesquisar" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
						<span>&nbsp;</span>	
						<html:button property="bt_limpar" styleId="botao_limpar_form" onClick="clearPage()" styleClass="btNavegacao74" value="Limpar" alt="Limpar" title=""/>
					</div>
				</catalogo:contentBox>
			
				<c:if test="${bandeiraList != null}">
					<catalogo:contentBox title="Resultado da Pesquisa">	
						<display:table name="bandeiraList" id="bandeiraTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "search.do" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="nmBandeira" title="Nome" sortable="true" headerClass="sortable" />
							<display:column title="Imagem" sortable="true" headerClass="sortable" style="text-align:center; width:150px;">
								<c:if test="${bandeiraTO.urlImagem != null}">
									<html:img src="/catprs/imgs/${bandeiraTO.urlImagem}" alt="${bandeiraTO.nmBandeira}" style="height: 20px; width: inherit;" />
								</c:if>
							</display:column>
							<display:column property="cdBandeiraSAP" title="Sigla SAP" sortable="true" headerClass="sortable" style="width:80px;" />
							<display:column property="vlParcelaMinima" format="{0,number,0.00}" title="Parcela Mínima" sortable="true" headerClass="sortable" style="text-align:right; width:100px;"/>
							<display:column property="cdInstituicaoFinanceira" title="Inst. Financeira" sortable="true" headerClass="sortable" style="width:100px;" />
							<cata:temPermissao acao="excluirBandeira">
								<display:column title="Excluir" style="text-align:center; width:60px;"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Bandeira" width="15" onclick="remove(${bandeiraTO.idBandeira})" style="cursor: pointer;"/></display:column>
							</cata:temPermissao>
							<display:footer >
								<tr>
									<td colspan="5">
									 	<table class="tabelaIcones" cellpadding="0" cellspacing="0">
											<tr>
												<td width="30px;">&Iacute;cones:</td>
												<td width="15px;"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Bandeira" width="15"/></td>
												<td >Excluir Bandeira</td>
											</tr>
										</table>
									</td>
								</tr>
							</display:footer>
						</display:table>
					</catalogo:contentBox>
				</c:if>
				<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
				<c:if test="${labelError != null}">
					<script>
						generateContentError("${labelError}")
					</script>
				</c:if>
				<c:if test="${cadastrar}">
					<catalogo:contentBox title="Cadastro de Bandeira" requiredFields="true">	
						<div class="fleft" style="width:51%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 100px;"><label for="nmBandeira">Nome:<font size="1px" color="#EEB422">*</font></label></div>
							<html:text property="nmBandeira" styleId="nmBandeira" style="width:300px;" maxlength="254"/>
						</div>
						<div class="fleft" style="width:40%; margin-bottom: 5px; margin-left: 5px;" id="contentForm"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 154px;"><label for="sgBandeiraSAP">Sigla SAP:<font size="1px" color="#EEB422">*</font></label></div>
							<html:text property="sgBandeiraSAP" styleId="sgBandeiraSAP" style="width:100px;" maxlength="10"/>
						</div>
						<div class="fleft" style="width:51%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 100px;"><label for="valorMinimo">Parcela M&iacute;nima:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div>
							<html:text property="valorMinimo" styleId="valorMinimo"  style="width:100px; text-align: right;" maxlength="10" onKeyDown="mascara(this,valor);" onKeyPress="mascara(this,valor);" onKeyUp="mascara(this,valor);"/>
						</div>
						<div class="fleft" style="width:40%; margin-bottom: 5px; margin-left: 5px;" id="contentForm"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 154px;"><label for="cdInstituicaoFinanceira">Cod. Inst. Financeira:<font size="1px" color="#EEB422">*</font></label></div>
							<html:text property="cdInstituicaoFinanceira" styleId="cdInstituicaoFinanceira" style="width:100px;" maxlength="254"/>
						</div>
						<div class="fleft" style="width:51%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 100px;"><label for="imgBandeira">Imagem:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div>
							<html:file property="imgBandeira" styleId="imgBandeira" style="width:300px;" />
						</div>
						
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="bt_cancelar" styleId="botao_pesquisar_form" onClick="cancel()" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title=""/><span>&nbsp;</span>
							<cata:temPermissao acao="gravarBandeira">
								<html:button property="bt_salvar" styleId="botao_salvar_form" onClick="save()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</c:if>
			</div>
			</html:form>
</catalogo:defaultTemplate>
