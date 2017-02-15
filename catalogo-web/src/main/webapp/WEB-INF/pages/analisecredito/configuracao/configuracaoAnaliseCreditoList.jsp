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
		<script type="text/javascript" src="/catalogo/static_server/js/configuracaoanalisecredito.js"></script>
   </jsp:attribute>
   <jsp:body>
		<script>carregaMenu('mn_parametrizacao_configuracao_analise');</script>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/search.do" styleId="configuracaoAnaliseCreditoForm" onsubmit="false">
			<div class="secao_conteudo">
				<html:hidden property="idCabecalhoAnaliseCredito" styleId="idCabecalhoAnaliseCredito"/>
				<catalogo:contentBox title="Pesquisa Análise de Crédito" doubt="true">
					<display:table name="cabecalhoAnaliseCreditoList" id="cabecalhoAnaliseCreditoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
						<display:column property="canalAtendimento.nmCanal" style="text-align: center;"  title="Canal" sortable="true" headerClass="sortable" />
						<display:column style="text-align: center; width:250px;" title="Nome" sortable="true" headerClass="sortable" >
							<cata:temPermissao acao="alterarConfiguracaoAnaliseCredito">
							<a href="javascript:edit('${cabecalhoAnaliseCreditoTO.idCabecalhoAnaliseCredito}')">
							</cata:temPermissao>
								${cabecalhoAnaliseCreditoTO.nmCabecalhoAnaliseCredito}
							<cata:temPermissao acao="alterarConfiguracaoAnaliseCredito">
							</a>
							</cata:temPermissao>
						</display:column>
						<display:column style="text-align: center; width:80px;" title="Status" sortable="true" headerClass="sortable" >
							<c:if test="${cabecalhoAnaliseCreditoTO.inDisponivel == 'S'}">
								Ativa
							</c:if>
							<c:if test="${cabecalhoAnaliseCreditoTO.inDisponivel != 'S'}">
								Inativa
							</c:if>	
						</display:column>
						<display:column property="dtCriacao" style="text-align: center;" format="{0,date,dd/MM/yyyy}" title="Data" sortable="true" headerClass="sortable" />
						<display:column property="nmUsuarioCriacao" style="text-align: center;" title="Usuário" sortable="true" headerClass="sortable" />
						<cata:temPermissao acao="ativarDesativarConfiguracaoAnaliseCredito">
						<display:column style="text-align: center;" title="&nbsp;" headerClass="sortable" >
							<a href="javascript:ativarDesativar(${cabecalhoAnaliseCreditoTO.idCabecalhoAnaliseCredito}, '${cabecalhoAnaliseCreditoTO.canalAtendimento.idCanalAtendimento}', '${cabecalhoAnaliseCreditoTO.inDisponivel}', '${cabecalhoAnaliseCreditoTO.nmCabecalhoAnaliseCredito}')">
								<c:if test="${cabecalhoAnaliseCreditoTO.inDisponivel == 'S'}">
									Desativar
								</c:if>
								<c:if test="${cabecalhoAnaliseCreditoTO.inDisponivel != 'S'}">
									Ativar
								</c:if>	
							</a>
						</display:column>
						</cata:temPermissao>
						<cata:temPermissao acao="copiarConfiguracaoAnaliseCredito">
							<display:column style="text-align: center;" title="&nbsp;" headerClass="sortable" >
								<a href="javascript:criarCopia('${cabecalhoAnaliseCreditoTO.idCabecalhoAnaliseCredito}')"><img src= "/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif" alt="Copiar"></a>
							</display:column>
						</cata:temPermissao>
						<cata:temPermissao acao="excluirConfiguracaoAnaliseCredito">
						<display:column style="text-align: center;" title="&nbsp;" headerClass="sortable" >
							<a href="javascript:remover(${cabecalhoAnaliseCreditoTO.idCabecalhoAnaliseCredito}, '${cabecalhoAnaliseCreditoTO.canalAtendimento.idCanalAtendimento}', '${cabecalhoAnaliseCreditoTO.inDisponivel}', '${cabecalhoAnaliseCreditoTO.nmCabecalhoAnaliseCredito}')">
								<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" style="cursor: pointer;"/>
							</a>
						</display:column>
						</cata:temPermissao>
						<display:footer >
							<tr>
								<td colspan="8">
								 	<table class="tabelaIcones">
										<tr>
											<td width="50px;"><label>Ícones:</label></td>
											<td width="15px"><img src= "/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif" alt="Copiar"></td>
											<td width="30px">Copiar</td>
											<td width="15px"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" style="cursor: pointer;"/></td>
											<td>Excluir</td>
										</tr>
									</table>
								</td>
							</tr>
						</display:footer>
					</display:table>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<cata:temPermissao acao="novaConfiguracaoAnaliseCredito">
					    	<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="create()" styleClass="btNavegacao74" value="Nova" alt="Nova" title=""/>
					    </cata:temPermissao>
					</div>
				</catalogo:contentBox>
			</div>
			<c:if test="${labelError != null}">
				<script>
					generateContentError("${labelError}")
				</script>
			</c:if>
         </html:form>     
    </jsp:body>        
</catalogo:defaultTemplate>