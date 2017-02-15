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
        <link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/promocaofixa.css"/>
        <script type="text/javascript" src="/catalogo/static_server/js/promocaofixa.js"></script>
</jsp:attribute>
<jsp:body>
        <div id="conteudo_success" style="color: #299B00;display: none" ></div>
        <div class="breadcrumb"> Parametriza&ccedil;&atilde;o > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/PromocaoFixaAction.do';" style="cursor: pointer;">Oferta Banda Larga</a><span></div>
        <script>carregaMenu('mn_parametrizacao_promocaofixa');</script>
        <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/search.do" styleId="acaoForm" onsubmit="false">
            <div class="secao_conteudo">
                <div id="boxOfertas">
                    <!-- Pesquisa Ofertas !-->
                    <catalogo:contentBox title="Pesquisar Oferta Banda Larga" doubt="false" requiredFields="true">
                        <div class="boxline">
                            <div class="linerow bold"><label for="codigo">C&oacute;digo:</label></div>
                            <div class="linerow clear"><html:text property="codigo" styleId="codigo"/></div>
                        </div>
                        <div class="boxline">
                            <div class="linerow bold"><label for="nome">Nome:</label></div>
                            <div class="linerow clear"><html:text property="nome" styleId="nome"/></div>
                        </div>
                        <div class="boxline">
                            <div class="linerow bold"><label for="nomeServico">Nome do Servi&ccedil;o:</label></div>
                            <div class="linerow clear"><html:text property="nomeServico" styleId="nomeServico"/></div>
                        </div>
                        <div class="boxline">
                            <div class="linerow bold"><label for="status">Status:</label></div>
                            <div class="linerow clear">
                                <html:select styleId="status" property="status">
                                    <html:option value="">--Selecione--</html:option>
                                    <html:option value="vigente">Vigente</html:option>
                                    <html:option value="finalizado">Finalizados</html:option>
                                    <html:option value="naoiniciado">N&atilde;o Iniciados</html:option>
                                </html:select>
                            </div>
                        </div>
                        <div class="boxline">
                            <div class="linerow bold"><label for="dataVigenteInicio">Vigente entre:</label></div>
                            <div class="linerow clear">
                                <html:text property="dataVigenteInicio" styleId="dataVigenteInicio" value="" style="width:75px"/>
                                <span>|</span>
                                <html:text property="dataVigenteFim" styleId="dataVigenteFim" value="" style="width:75px"/>
                            </div>
                        </div>
               
                        <div class="boxline">
                            <div class="linerow bold">Pendente de Configura&ccedil;&atilde;o:</div>
                            <div class="linerow clear">
                                <html:select property="pendenteConfiguracao" styleId="pendenteConfiguracao">
                                    <html:option value="">--Selecione--</html:option>
                                    <html:option value="sim">Sim</html:option>
                                    <html:option value="nao">N&atilde;o</html:option>
                                </html:select>
                            </div>
                        </div>
                        <!-- botões pesquisar Ofertas  -->
                        <div class="barra"></div>
                        <div class="botao">
                            <cata:temPermissao acao="acessarOfertaBandaLarga">
                                <html:button property="botao_pesquisar" styleId="botao_pesquisar" onClick="pesquisar()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
                            </cata:temPermissao>
                            <cata:temPermissao acao="novoOfertaBandaLarga">
                                <html:button property="botao_novo" styleId="botao_novo" onClick="carregarFormularioAlteracao('','')" styleClass="btNavegacao74" value="Novo" alt="Novas Ofertas" title=""/><span>&nbsp;</span>
                            </cata:temPermissao>
                        </div>
                    </catalogo:contentBox>
                    <div id="pesquisaOfertas"></div>
                    <div class="boxline">
                        <div class="linerow bold" id="exibirCodigoPromocao" style="width:500px"></div>
                        <div class="linerow clear" id="iconPendenteConfiguracao" style="width:500px;display:none !important;">
                            <div class="left"><img src= "/catalogo/static_server/img/icons_alerta_naoanimado.gif" alt="Pendente de Configura&ccedil;&atilde;o"></div>
                            <div id="msgPendenteConfiguracao">Pendente de Configura&ccedil;&atilde;o</div>
                        </div>
                    </div>     
                    <div id="caixa" class="detalharOfertas">
                        <span id="abas">
                            <a href="#aba1" id="aba1Link" onclick="abrirAba(this)" class="selected">Detalhes</a>
                            <a href="#aba2" id="aba2Link" onclick="abrirAba(this)">&Iacute;tens</a>
                            <a href="#aba3" id="aba3Link" onclick="abrirAba(this)">Disponibilidade</a>
                        </span>
                        <ul id="conteudos">
                            <li id="aba1" class="contentRel">
                                <div id="detalhesOfertas"></div>
                            </li>
                            <li id="aba2" class="contentRel" style="display:none !important">
                                <div class="boxServPromo" id="itemComposicao"></div>
                            </li>
                            <li id="aba3" class="contentRel" style="display:none !important">
                                <div id="disponibilidade"></div>
                            </li>
                        </ul>
                    </div>
                </div>    
            </div>
            <html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>
            <html:hidden property="disabled" styleId="disabled"/>    
            <html:hidden property="canalVendaCheckedAba" styleId="canalVendaCheckedAba"/>  
            <html:hidden property="operacao" styleId="operacao"/>
        </html:form>
</jsp:body>        
</catalogo:defaultTemplate>