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
        <link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/analiseCreditoLinha.css"/>
        <script type="text/javascript" src="/catalogo/static_server/js/analiseCreditoLinha.js"></script>
</jsp:attribute>
   <jsp:body>
		<div id="conteudo_success" style="color: #299B00;display: none"></div>
		<!-- BREADCRUMB !-->
		<div class="breadcrumb">Parametriza&ccedil;&atilde;o > Fixa > <span><a
			onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/AnaliseCreditoLinhaAction.do';" style="cursor: pointer;">An&#225;lise de
		Cr&#233;dito de Linha</a><span></div>

		<html:form styleId="analiseScore" action="/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/pesquisar.do">
		<!-- Lista de Agrupadores !-->
		<div id="tableAnaliseCreditoLinha">
		<catalogo:contentBox title="Pesquisar Ofertas" doubt="false" requiredFields="false">
			<div class="boxline">
			<div class="linerow bold">Nome da Oferta:</div>
			<div class="linerow clear"><input type="text" id="txtOferta" /></div>
			</div>
			<div class="boxline">
			<div class="linerow bold">Servi&#231;o Linha:</div>
			<div class="linerow clear">
			<html:select property="comboServicoLinha" styleId="comboServicoLinha" styleClass="data changeable">
				<html:option value="0">--Selecione--</html:option>
				<c:forEach var="servicoLinhaTO" items="${servicoLinhaTOList}">
					<html:option value="${servicoLinhaTO.idServico}">${servicoLinhaTO.nomeComercialServico}</html:option>
				</c:forEach>
			</html:select>
			</div>
			</div>

			<div class="boxline">
			<div class="linerow bold">Score:</div>
			<div class="linerow clear">
			<html:select property="comboScore" styleId="comboScore" styleClass="data changeable" style="width: 100px !important;">
				<html:option value="0">--Selecione--</html:option>
				<html:option value="-1">(nenhum)</html:option>
				<c:forEach var="scoreTO" items="${scoreTOList}">
					<html:option value="${scoreTO.idAnaliseCredito}">${scoreTO.cdCor}</html:option>
				</c:forEach>
			</html:select></div>
			</div>

			<!-- botão Novo Agrupador  !-->
			<div class="barra"></div>
			<cata:temPermissao acao="acessarAnaliseCreditoLinha">
				<div class="botao"><html:button property="btnPesquisar" styleId="btnPesquisar" onClick="pesquisar()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar Ofertas" title=""/><span>&nbsp;</span></div>
			</cata:temPermissao>
		</catalogo:contentBox> 
		</div>
		<!--Final Lista de Agrupadores !-->

		<div id="carregarDados"></div>

		<div id="tabelaAnaliseCreditoLinha">
		    <catalogo:contentBox title="Score" doubt="false" requiredFields="false">
			    <div id="resultAnaliseCreditoLinha"></div>
            </catalogo:contentBox>
        </div>
        </html:form>     
  </jsp:body>        
</catalogo:defaultTemplate>
