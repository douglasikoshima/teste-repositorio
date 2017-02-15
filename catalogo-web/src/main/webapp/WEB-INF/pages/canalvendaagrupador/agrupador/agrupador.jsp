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
		<script type="text/javascript" src="/catalogo/static_server/js/agrupador.js"></script>
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/agrupador.css"/>
  </jsp:attribute>
  <jsp:body>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/agrupador/pesquisar.do" styleId="agrupadorForm" onsubmit="false" >
			<html:hidden styleId="idEps" property="idEps" />
			<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
			<c:if test="${labelError != null}">
				<script>
					generateContentErrorForm("${labelError}")
				</script>
			</c:if>

				<catalogo:contentBox title="Agrupador" >
					<div id="tabelaAgrupadores" >
						<display:table name="epsTOList" id="epsTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "trocarPagina.do" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="Nome" property="nmEps" sortable="true" headerClass="sortable"/>
							<cata:temPermissao acao="alterarAgrupadorGrupoComercial">
								<display:column title="Alterar" style="text-align:center; width:40px;">
									<img src="/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" onclick="editar(${epsTO.idEps},'${epsTO.nmEps}')" width="15" style="cursor: pointer;"/>
								</display:column>
							</cata:temPermissao>
							<cata:temPermissao acao="excluirAgrupadorGrupoComercial">
								<display:column title="Excluir" style="text-align:center; width:40px;">
									<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" onclick="remover(${epsTO.idEps})" width="15" style="cursor: pointer;"/>
								</display:column>
							</cata:temPermissao>
						</display:table>
						<div class="legendatable">
							<div><span class="bold">Legenda:</span></div>
							<div><img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar"> <span> Alterar</span></div>
							<div><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir"> <span> Excluir</span></div>
						</div>
						<br clear="all"/>
						<div class="barra"></div>
					    <div class="botao"> 
							<cata:temPermissao acao="criarAgrupadorGrupoComercial">
								<html:button property="botao_novo_form" styleId="botao_novo_form" onClick="criarNovo()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/>
								<span>&nbsp;</span>
							</cata:temPermissao>
					    </div>
					</div>
				</catalogo:contentBox>
			    <div id="cadastroAgrupador" style="display: none">
					<catalogo:contentBox title="Novo">
						<div class="line">
							<div class="linerow bold"> <label for="nmEps">Nome : </label><span class="required">*</span> </div>
						</div>
						<div class="line">
							<div class="linerow bold"> <html:text property="nmEps" styleId="nmEps"/></div>
						</div>
						<br clear="all"/>
						<div class="barra"></div>
					    <div class="botao">
							<html:button property="botao_gravar_form" styleId="botao_gravar_form" onClick="salvar()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
							<span>&nbsp;</span>
					    </div>
					</catalogo:contentBox>
				</div>
        </html:form>     
   </jsp:body>        
</catalogo:blankTemplate>