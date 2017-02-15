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
        <script type="text/javascript" src="/catalogo/static_server/js/importacaoservicofixa.js"></script>
  </jsp:attribute>
  <jsp:body>
		<div class="breadcrumb"> Parametrização > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/ImportacaoServicoFixaAction.do';" style="cursor: pointer;">Importa&ccedil;&atilde;o</a><span></div>
		<script>carregaMenu('mn_parametrizacao_fixa_importacao');</script>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/importar.do" styleId="acaoForm" onsubmit="false" enctype="multipart/form-data">
			<div class="secao_conteudo">
				<!-- Importação de Arquivo !-->
				<catalogo:contentBox title="Importação de Arquivo" doubt="true" requiredFields="true">
					<div class="line" id="msg_obrigatorio" style="display:none;">
			        	<div class="box_middle_center_conteudo" style="color:red !important;">Favor preencher os campos obrigat&oacute;rios!</div>
			        	<div class="barra"></div>
					</div>
					<div class="line" id="selecionar_tipo" style="display:none;">
			        	<div class="box_middle_center_conteudo" style="color:red !important;">Favor selecionar tipo de importa&ccedil;&atilde;o</div>
			        	<div class="barra"></div>
					</div>
					<div class="line">
						<div class="linerow bold" style="width:260px"><label for="idTipoImportacao">Tipo de Importa&ccedil;&atilde;o:</label><span class="required">*</span></div>
						<div class="linerow bold" ><label for="fileImport">Arquivo:</label><span class="required">*</span></div>
					</div>
	                <div class="line">
	                	<div class="linerow bold" style="width:260px">
							<html:select styleClass="required" style="width:250px" styleId="idTipoImportacao" property="idTipoImportacao" onchange="esconderMsgs()">
								<html:option value="0">-- Selecione --</html:option>
								<cata:temPermissao acao="acessarImportacaoRelacionamento">
									<html:option value="1">Relacionamento</html:option>
								</cata:temPermissao>
								<cata:temPermissao acao="acessarImportacaoSCxGCxPMxAC">
									<html:option value="2">SCxGCxPMxAC - Desconto</html:option>
								</cata:temPermissao>
								<cata:temPermissao acao="acessarImportacaoSCxENCxPFxGC">
									<html:option value="3">SCxENCxPFxGC – Financiamento</html:option>
								</cata:temPermissao>
								<cata:temPermissao acao="acessarImportacaoSCxENCxPFxGCxPMxAC">
									<html:option value="4">SCxENCxPFxGCxPMxAC - Financiamento</html:option>
								</cata:temPermissao>
                                <cata:temPermissao acao="acessarImportacaoServico">
                                    <html:option value="5">Servi&ccedil;o</html:option>
                                </cata:temPermissao>
								<cata:temPermissao acao="acessarImportacaoServico"> <!-- trocar o atributo acao  -->
									<html:option value="6">Oferta Venda Linha</html:option>
								</cata:temPermissao>
								<cata:temPermissao acao="acessarImportacaoServico"> <!-- trocar o atributo acao  -->
									<html:option value="7">Oferta Complementar </html:option>
								</cata:temPermissao>
				</html:select>
						</div>
						<div class="linerow bold"><html:file property="fileImport" styleId="fileImport" style="width:450px;" styleClass="required"/></div>
					</div>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_importar" styleId="botao_importar" onClick="importar()" styleClass="btNavegacao74" value="Importar" alt="Importar" title=""/><span>&nbsp;</span>			    				    						
						<cata:temPermissao acao="modeloImportacaoSCxGCxPMxAC,modeloImportacaoRelacionamento,modeloImportacaoSCxENCxPFxGCxPMxAC,modeloImportacaoSCxENCxPFxGC,modeloImportacaoServico,modeloImportacaoOfertaVendaLinha">
							<html:button property="botao_obter_modelo" styleId="botao_obter_modelo" onClick="obterModelo()" styleClass="btNavegacao74" value="Modelo" alt="Modelo" title=""/>
						</cata:temPermissao>
					</div>
				</catalogo:contentBox>
			</div>
			<input type="hidden" name="isCriticas" id="isCriticas" />					
			<input type="hidden" name="erro" id="erro" value="${labelError}">
         </html:form>     
   </jsp:body>        
</catalogo:defaultTemplate>