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
		<script type="text/javascript" src="/catalogo/static_server/js/servicosAdicionais.js"></script>
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/servicosAdicionais.css">
  </jsp:attribute>
    <jsp:body>
		<div id="conteudo_success" style="color: #299B00;display: none"></div>
		<!-- BREADCRUMB !-->
		<div class="breadcrumb">Parametriza&ccedil;&atilde;o > Fixa > <span><a
			onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/ServicosAdicionaisAction.do';" style="cursor: pointer;">Serviços Adicionais</a><span></div>
		<script>carregaMenu('mn_parametrizacao_servico_adicionais');</script>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/search.do" styleId="acaoForm" >
			<div class="secao_conteudo"><!-- COMEÇO DO BOX DOS SERVICOS ADICIONAIS !-->
			<div id="tableServicosAdicionais"><catalogo:contentBox title="Resultado da Pesquisa" doubt="false" requiredFields="false">

				<display:table name="servicosAdicionaisTOList" id="displayServicosAdicionais" pagesize="15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="ordenar.do"
					class="tabela-padrao-new tablesorter table_body">
					<display:column title="Nome do Servi&ccedil;o" sortable="true" headerClass="sortable">
						<a href="javascript:visualizar('${displayServicosAdicionais.idServicosAdicionais}')">${displayServicosAdicionais.nomeServico}</a>
					</display:column>
					<display:column property="solicitacaoComercial.nmSolicitacaoComercial" title="Nome da Sol. Comercial" sortable="true" headerClass="sortable" />
					<display:column property="tempoVigencia" title="Tempo de Vigência (Meses)" sortable="true" headerClass="sortable" />
					<display:column property="periodoVigencia" title="Período Vigência" sortable="true" headerClass="sortable" />
					<display:column title="Dependente" sortable="false" headerClass="sortable"> 
				    	<div class="clean icon">
							<div class=${displayServicosAdicionais.dependente ? "icondep" : ""}></div>
						</div>
					</display:column> 
					<cata:temPermissao acao="excluirServicoAdicional">
						<c:if test="${!displayServicosAdicionais.dependente}">
							<display:column title="Excluir" headerClass="sortable">
								<div class="clean" id="botaoExcluir"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas" alt="Excluir" onClick="excluirServicosAdicionais('${displayServicosAdicionais.idServicosAdicionais}')"></div>
							</display:column>						
						</c:if>
					</cata:temPermissao>
				</display:table>
			</catalogo:contentBox></div>
			<!-- ADICIONA NOVOS SERVICOS ADICIONAIS !--> <div id="servicoAdicional"><catalogo:contentBox title="Adicionar Serviço Adicional" doubt="true" requiredFields="true">
				<div class="boxServPromo clear">
				<div class="boxline">
				<div class="linerow bold">Código do Serviço:</div>
				<div class="linerow clear"><input id="idServico" disabled="true" type="text" /></div>
				</div>
				<div class="boxline">
				<div class="linerow bold">Tipo do Serviço:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboTipoServico">
				<html:select property="comboTipoServico" styleId="comboTipoServico" onchange="carregarNomeServicoList()" styleClass="data changeable">
					<html:option value="0">--Selecione--</html:option>
					<c:forEach var="tipoServicoTO" items="${tipoServicoTOList}">
						<html:option value="${tipoServicoTO.idTipoServico}">${tipoServicoTO.nmTipoServico}</html:option>
					</c:forEach>
				</html:select></div>
				</div>
				<div class="boxline" style="width:380px !important">
				<div class="linerow bold">Nome do Serviço:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboNomeServico" style="width:370px !important">
				<html:select styleClass="data" property="comboNomeServico" styleId="comboNomeServico" disabled="disabled" style="width: 340px !important;">
					<html:option value="0">--Selecione--</html:option>
				</html:select></div>
				</div>
				<div class="boxline">
				<div class="linerow bold">Código da Solicitação:</div>
				<div class="linerow clear"><html:text property="codSolicitacao" styleId="codSolicitacao" disabled="true"/></div>
				</div>
				<div class="boxline">
				<div class="linerow bold">Nome da Solicitação:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboNomeSolicitacao">
				<html:select styleClass="data" property="comboNomeSolicitacao" styleId="comboNomeSolicitacao" disabled="disabled" style="width: 150px !important;">
					<html:option value="0">--Selecione--</html:option>
				</html:select></div>
				</div>
				<div class="boxline">
				<div class="linerow bold">Tempo de Vigência (Meses):<span class="required">*</span></div>
				<div class="linerow clear"><html:text property="idTempoVigencia" styleId="idTempoVigencia" onkeypress='return SomenteNumero(event)' disabled="true"/></div>
				</div>

				<div class="clear"></div>

				<div class="boxline">
				<div class="linerow bold">Data Inicio de Vig&#234;ncia<span class="required">*</span></div>
				<div class="linerow clear"><html:text property="dataVigenteInicio" styleId="dataVigenteInicio" value=""/></div>
				</div>

				<div class="boxline">
				<div class="linerow bold">Data Fim de Vig&#234;ncia:</div>
				<div class="linerow clear"><html:text property="dataVigenteFim" styleId="dataVigenteFim" value=""/></div>
				</div>

				<!-- BOTÃO ADICIONAR NOVOS ITENS !-->
				<div class="barra"></div>
				<cata:temPermissao acao="adicionarServicoAdicional">
					<div class="botao"><html:button property="botao_gravar" styleId="botao_gravar" onClick="gravarServicosAdicionais()" styleClass="btNavegacao74" value="Adicionar" alt="Adicionar Serviços" title=""/></div>
				</cata:temPermissao></div></div>
			</catalogo:contentBox></div>
		</html:form>
	</jsp:body>        
</catalogo:defaultTemplate>
