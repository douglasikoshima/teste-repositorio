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
         <script type="text/javascript" src="/catalogo/static_server/js/servicoFixa.js">
         function checkError() {
             <c:if test="${labelError != null}">
                 generateContentErrorForm("${labelError}");
             </c:if>
         }         
         </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumb"> Parametrização > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/ServicoFixaAction.do';" style="cursor: pointer;">Servi&ccedil;o</a><span></div>
        <script>carregaMenu('mn_parametrizacao_servico');</script>
        <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/search.do" styleId="acaoForm">
            <div class="secao_conteudo">
                <!-- Pesquisa de Serviços !-->
                <catalogo:contentBox title="Pesquisa" doubt="true" requiredFields="true">
                    <div class="line">
                        <div class="linerow bold"><label for="idSistema">Sistema Origem:</label><span class="required">*</span></div>
                        <div class="linerow bold"><label for="cdPS">Código do Serviço Origem:</label></div>
                        <div class="linerow bold"><label for="cdServico">Código do Serviço:</label></div>
                        <div class="linerow bold"><label for="nomeDoServicoOrigem">Nome do Serviço Origem:</label></div>
                    </div>
                    <div class="line">
                        <div class="linerow">
                        	<html:select styleClass="required" property="idSistema" styleId="idSistema" >
			                   	<html:option value="0">-- Selecione --</html:option>
								<html:option value="8">ATIS</html:option>
			                   	<html:option value="9">CSO</html:option>
		                    </html:select>
                        </div>
                        <div class="linerow"><html:text property="cdPS" styleId="cdPS"/></div>
                        <div class="linerow"><html:text property="cdServico" styleId="cdServico"/></div>
						<div class="linerow"><html:text property="nomeDoServicoOrigem" styleId="nomeDoServicoOrigem"/></div> 
                    </div>
                    <div class="line">
                        <div class="linerow bold"><label for="nmComercial">Nome do Serviço Catálogo:</label></div>
                        <div class="linerow bold"><label for="inDisponivel">Disponibilidade:</label></div>
                    </div>
                    <div class="line">
                        <div class="linerow"><html:text property="nmComercial" styleId="nmComercial"/></div>
                        <div class="linerow">
                        	<html:select styleClass="required" property="inDisponivel" styleId="inDisponivel" >
								<html:option  value="sim">Sim</html:option>
			                   	<html:option  value="nao">Não</html:option>
		                    </html:select>                        
                        </div>
                    </div>
                    <!-- botões pesquisar e limpar campos -->
                    <div class="barra"></div>
                    <div class="botao">
                        <html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
                    </div>
                    <!--Final botões pesquisar e limpar campos -->
                </catalogo:contentBox>

                <!-- Resultado Pesquisa de Serviços !-->
                <c:if test="${servicoFixaTOList != null}">
	                <div id="resultadoPesquisa">
	                    <catalogo:contentBox title="Resultado Pesquisa" doubt="false" requiredFields="false">
							<display:table name="servicoFixaTOList" id="servicoFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "search.do" class="tabela-padrao-new tablesorter table_body" >						
								<display:column title="Código do Serviço" sortable="true" style="text-align:center; width:60px;">
									<a href="javascript:view('${servicoFixaTO.idServico}', '${servicoFixaTO.cdServico}','${servicoFixaTO.nmTipoServico}')">${servicoFixaTO.cdServico}</a>
								</display:column>
								<display:column property="cdPS" title="Código do Serviço Origem" sortable="true" headerClass="sortable" />
								<display:column property="nmSistema" title="Sistema Origem" sortable="true" headerClass="sortable" />
								<display:column property="nomeDoServicoOrigem" title="Nome do Serviço Origem" sortable="true" headerClass="sortable" />
								<display:column property="nmComercial" title="Nome do Serviço Catálogo" sortable="true" headerClass="sortable" />
								<display:column title="Status" sortable="true" headerClass="sortable">
									<div class='clean'><div class='${servicoFixaTO.inDisponivel ? "iconativo" : "iconinativo" }'></div></div>
								</display:column>
								<cata:temPermissao acao="alterarServico">
									<display:column title="Alterar" style="text-align:center; width:60px;">
										<img src="/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" width="15" onclick="change('${servicoFixaTO.idServico}', '${servicoFixaTO.cdServico}','${servicoFixaTO.nmTipoServico}' );" alt="Alterar" style="cursor: pointer;"/>
									</display:column>
								</cata:temPermissao>
								<cata:temPermissao acao="ativarDesativarServico">
									<display:column title="Ação" style="text-align:center; width:60px;">
										<a href="javascript:changeStatus('${servicoFixaTO.idServico}', ${servicoFixaTO.inDisponivel})">${servicoFixaTO.inDisponivel ? "Desativar" : "Ativar"}</a>
									</display:column>
								</cata:temPermissao>
							</display:table>
							<div class="legendatable">
							<div><span class="bold">Legenda:</span></div>
							      <div><img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar"> <span> Alterar</span></div>
							      <div><div class="iconativo"></div><span>Ativo</span></div>
							      <div><div class="iconinativo"></div><span>Inativo</span></div>
							</div>							
							<html:hidden property="alterar" styleId="alterar"/>
	                        <!-- botões pesquisar e limpar campos -->
	                        <div class="barra"></div>
	                        <div class="botao">
	                        	<c:if test="${servicoSelecionado != null}">
	                        		<div class="linerow bold">${servicoSelecionado}</div>
		                        </c:if>
	                        </div>
								
	                    </catalogo:contentBox>
	                </div>
	                <c:if test="${idServico != null}">
						<div id="boxOperacoes">
							<!-- Monta caixa das Abas !-->
							<div id="caixa">
								<!-- Menu das Abas !-->
								<span id="abas">
									<a onclick="openFrame('detail')" href="#acaoForm" class="selected">Detalhe</a>
									<cata:temPermissao acao="acessarRelacionamento">
										<a onclick="openFrame('relationship')" href="#acaoForm">Relacionamentos</a>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarSolicitacaoComercial">
										<a onclick="openFrame('commSolic')" href="#acaoForm">Solicitação Comercial</a>
									</cata:temPermissao>
								</span>
								<ul id="conteudos">
									<iframe width="100%" id="detail" style="display: none;" frameborder="0" scrolling="no" onload="sizeFrame('detail')" ></iframe>
									<iframe width="100%" id="relationship" style="display: none;" frameborder="0" scrolling="no" onload="sizeFrame('relationship')" ></iframe>
									<iframe width="100%" id="commSolic" style="display: none;" frameborder="0" scrolling="no" onload="sizeFrame('commSolic')" ></iframe>
								</ul>
							</div>
						</div>
					</c:if>
				</c:if>
            </div>
			<html:hidden property="cdServicoSelecionado" styleId="cdServicoSelecionado"/>
			<html:hidden property="idServico" styleId="idServico"/>
			<html:hidden property="nmTipoServico" styleId="nmTipoServico"/>
        </html:form>
     </jsp:body>        
</catalogo:defaultTemplate>