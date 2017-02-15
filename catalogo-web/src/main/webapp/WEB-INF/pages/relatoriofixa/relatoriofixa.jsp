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
        <script>
            var $jq = jQuery.noConflict();

            function init() {}

			function sizeFrame(frame) {
				$jq("#aguarde").css('display','none');
				if (frame.src == "") {
					return;
				}
				$jq("#iframe").css('display','block');
				if(frame.contentDocument) {
					frame.height = frame.contentDocument.documentElement.scrollHeight+60; //FF 3.0.11, Opera 9.63, and Chrome
				} else {
					frame.height = frame.contentWindow.document.body.scrollHeight+60; //IE6, IE7 and Chrome
				}
			}
			
			function openFrame(obj) {
				if (obj.value != "") {
					$jq("#aguarde").css('display','block');
				}
				$jq("#iframe").css('display','none');
				document.getElementById("iframe").src = obj.value;
			}
        </script>
</jsp:attribute>
<jsp:body>
        <div class="breadcrumb"> Consultas > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/RelatorioFixaAction.do';" style="cursor: pointer;">Relat&oacute;rio</a><span></div>
        <script>carregaMenu('mn_consulta_fixa_relatorio');</script>
        <form action="begin" id="acaoForm" genJavaScriptFormSubmit="false">
            <div class="secao_conteudo">
            	<div class="contentGroup">
	                <!-- Pesquisa de Serviços !-->
	                <catalogo:contentBox title="Relat&oacute;rios" doubt="true" requiredFields="true">
	                    <div class="line">
	                        <div class="linerow bold"><label for="tipoRelatorio">Tipo de Relat&oacute;rio:</label><span class="required">*</span></div>
	                    </div>
	                    <div class="line" style="margin-bottom:20px !important;">
	                        <div class="linerow">
								<select onchange="openFrame(this)" style="width:250px ">
									<option value="">-- Selecione --</option>
									<cata:temPermissao acao="acessarRelatorioServico">
										<option value="${servico}">Servi&ccedil;o</option>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarRelatorioSolicitacaoComercial">
										<option value="${solicitacaoComercial}">Solicita&ccedil;&atilde;o Comercial</option>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarRelatorioGrupoComercial">
										<option value="${grupoComercial}">Grupo Comercial</option>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarRelatorioRelacionamento">
										<option value="${relacionamento}">Relacionamento</option>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarRelatorioSCxGCxPMxAC">
										<option value="${SCxGCxPMxAC}">SCxGCxPMxAC - Desconto</option>
									</cata:temPermissao> 
									<cata:temPermissao acao="acessarRelatorioSCxENCxPFxGCxPMxAC">
										<option value="${SCxENCxPFxGCxPMxAC}">SCxENCxPFxGCxPMxAC - Financiamento</option>
									</cata:temPermissao>
								</select>                        
	                        </div>
	                    </div>
	                </catalogo:contentBox>
				</div>
                <div class="line" id="aguarde" style="display:none">
                    <div class="linerow bold"><img src="/catalogo/static_server/img/carregando.gif" /></div>
                </div>
				<div id="boxOperacoes">
					<div id="caixa">
						<iframe class="iframe" width="100%" id="iframe" style="display: none;" frameborder="0" scrolling="no" onload="sizeFrame(this)" ></iframe>
					</div>
				</div>
            </div>
        </form>
</jsp:body>        
</catalogo:defaultTemplate>