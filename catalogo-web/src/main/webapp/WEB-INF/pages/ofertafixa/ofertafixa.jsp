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
        <link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/ofertafixa.css"/>
        <script type="text/javascript" src="/catalogo/static_server/js/ofertafixa.js"></script>
</jsp:attribute>
<jsp:body>
        <div id="conteudo_success" style="color: #299B00;display: none" ></div>
        <div class="breadcrumb"> Parametriza&ccedil;&atilde;o > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/OfertaFixaAction.do';" style="cursor: pointer;">Oferta Venda Linha</a><span><span></div>
        <script>carregaMenu('mn_parametrizacao_oferta');</script>
        <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/search.do" styleId="acaoForm" onsubmit="false">
            <div class="secao_conteudo">
                <div id="boxOfertas">
                    <!-- Pesquisa Ofertas !-->
                    <catalogo:contentBox title="Pesquisar Ofertas" doubt="false" requiredFields="false">
                        <div class="boxline">
                            <div class="linerow bold">C&oacute;digo da Oferta:</div>
                            <div class="linerow clear">
                                <html:text property="cdOferta" styleId="cdOferta"/>
                            </div>
                        </div>
                        <div class="boxline">
                            <div class="linerow bold">Nome da Oferta:</div>
                            <div class="linerow clear">
                                <html:text property="nmOferta" styleId="nmOferta"/>
                            </div>
                        </div>
                        <div class="boxline">                        
                            <div class="linerow bold">PS de Linha:</div>
                            <div class="linerow clear">
                                <html:text property="psServivoLinha" styleId="psServivoLinha" onkeypress="return validarNumeros(event);" />
                            </div>   
                        </div>                                                
                        <div class="boxline">
                            <div class="linerow bold">Servi&ccedil;o Linha:</div>
                            <div class="linerow clear">
                                <html:select property="idServicoLinha" styleId="idServicoLinha" onchange="carregarDependentes(this)">
                                    <html:option value="">--Selecione--</html:option>
                                    <c:forEach var="servicoFixaTO" items="${servicoLinhaList}">
                                        <html:option value="${servicoFixaTO.idServico}">${servicoFixaTO.nmComercial}</html:option>
                                    </c:forEach>
                                </html:select>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="boxline">                        
                            <div class="linerow bold">Op. Comercial Linha:</div>
                            <div class="linerow clear" id="selectOpSolicitacaoComercialNormal">
                                <html:text property="opSolicitacaoComercialNormal" styleId="opSolicitacaoComercialNormal" onkeypress="return validarNumeros(event);" />
                            </div> 	           
                        </div>                                                                  
                        <div class="boxline">
                            <div class="linerow bold">Sol. Comercial Normal:</div>
                            <div class="linerow clear" id="selectSolicitacaoComercialNormal">
                                <html:select property="idSolicitacaoComercialNormal" styleId="idSolicitacaoComercialNormal" disabled="disabled">
                                    <html:option value="">--Selecione--</html:option>
                                </html:select>
                            </div>
                        </div>
                        <div class="boxline">                        
                           <div class="linerow bold">Op. Comercial Pré-Cadastro:</div>
                            <div class="linerow clear" id="selectOpSolicitacaoComercialPreCadastro">
                                <html:text property="opSolicitacaoComercialPreCadastro" styleId="opSolicitacaoComercialPreCadastro" onkeypress="return validarNumeros(event);" />
                            </div>   
                        </div>                                                  
                        <div class="boxline">
                            <div class="linerow bold">Sol. Comercial Pr&eacute;-Cadastro:</div>
                            <div class="linerow clear" id="selectSolicitacaoComercialPreCadastro">
                                <html:select property="idSolicitacaoComercialPreCadastro" styleId="idSolicitacaoComercialPreCadastro" disabled="disabled">
                                    <html:option value="">-- Selecione --</html:option>
                                </html:select>
                            </div>
                        </div>
                        <div class="clear"></div>                         
                        <div class="boxline">                        
  							<div class="linerow bold">PS de Plano:</div>
                            <div class="linerow clear" id="selectPsServicoPlano">
                                <html:text property="psServicoPlano" styleId="psServicoPlano" onkeypress="return validarNumeros(event);" />
                            </div>   
                        </div>                                                                          
                        <div class="boxline">
                            <div class="linerow bold">Serviço Plano:</div>
                            <div class="linerow clear" id="selectServicoPlano">
                                <html:select property="idServicoPlano" styleId="idServicoPlano" disabled="disabled" onchange="carregarDependentes(this)">
                                    <html:option value="">--Selecione--</html:option>
                                </html:select>
                            </div>
                        </div>
                        <div class="boxline">                        
                            <div class="linerow bold">Op. Comercial Plano:</div>
                            <div class="linerow clear" id="selectOpSolicitacaoComercialPlano">
                                <html:text property="opSolicitacaoComercialPlano" styleId="opSolicitacaoComercialPlano" onkeypress="return validarNumeros(event);" />
                            </div> 
                        </div>                                                    
                        <div class="boxline">
                            <div class="linerow bold">Sol. Comercial Plano:</div>
                            <div class="linerow clear" id="selectSolicitacaoComercialPlano">
                                <html:select property="idSolicitacaoComercialPlano" styleId="idSolicitacaoComercialPlano" disabled="disabled">
                                    <html:option value="">--Selecione--</html:option>
                                </html:select>
                            </div>
                        </div>
                        <div class="clear"></div>                         
                        <div class="boxline">
                            <div class="linerow bold">Status:</div>
                            <div class="linerow clear">
                                <html:select property="status" styleId="status">
                                    <html:option value="">--Selecione--</html:option>
                                    <html:option value="vigente">Vigente</html:option>
                                    <html:option value="naoiniciado">N&atilde;o Iniciados</html:option>
                                    <html:option value="finalizado">Finalizados</html:option>
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
                            <div class="linerow bold">Problema de Configura&ccedil;&atilde;o:</div>
                            <div class="linerow clear">
                                <html:select property="pendenteConfiguracao" styleId="pendenteConfiguracao">
                                    <html:option value="">--Selecione--</html:option>
                                    <html:option value="sim">Sim</html:option>
                                    <html:option value="nao">N&atilde;o</html:option>
                                </html:select>
                            </div>
                        </div>                        
                        <div class="clear valuecheck" id="checkboxgroup_pesquisa">
                            <div class="bold">Tipo de Oferta:</div>
                            <div class="check"><html:checkbox property="tipoOfertaLinhacobre" value="linhacobre" styleClass="checkbox" />Linha Speedy Cobre</div>
                            <div class="check"><html:checkbox property="tipoOfertaLinhafibra" value="linhafibra" styleClass="checkbox" />Linha Speedy Fibra</div>
                            <div class="check"><html:checkbox property="tipoOfertaSolocobre" value="solocobre" styleClass="checkbox" />Speedy Solo Cobre</div>
                            <div class="check"><html:checkbox property="tipoOfertaSolofibra" value="solofibra" styleClass="checkbox" />Speedy Solo Fibra</div>
                            <div class="check"><html:checkbox property="tipoOfertaPortabilidade" value="portabilidade" styleClass="checkbox" />Portabilidade</div>
                            <div class="check"><html:checkbox property="tipoOfertaFwt" value="fwt" styleClass="checkbox" />FWT</div>
                            <div class="check"><html:checkbox property="tipoOfertaBasePontual" value="basepontual" styleClass="checkbox" />Base Pontual</div>
                            <div class="check"><html:checkbox property="tipoOfertaInadimplente" value="inadimplente" styleClass="checkbox" />Inadimplente</div>
                            <div class="check"><html:checkbox property="tipoOfertaFatb" value="fatb" styleClass="checkbox" />FATB</div>
                        </div>
                        
                        <!-- botões pesquisar Ofertas  -->
                        <div class="barra"></div>
                        <div class="botao">
                            <html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="pesquisar()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
                            <cata:temPermissao acao="novoOfertafixa">
                            <html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="novaOferta()" styleClass="btNavegacao74" value="Novo" alt="Novas Ofertas" title=""/><span>&nbsp;</span>
                            </cata:temPermissao>
                        </div>
                    </catalogo:contentBox>
                    <!-- Final Pesquisa Ofertas !-->
                    
                    <!-- Resultado Pesquisa Ofertas !-->                
                    <div id="pesquisaOfertas"></div>
                    <!-- Final Resultado Pesquisa Ofertas !-->
                    <div class="bold bottom clear" id="ofertaSelecionada"></div>
                    <div class="linerow clear" id="iconPendenteConfiguracao" style="width:500px;display:none !important;">
                        <div class="left"><img src= "/catalogo/static_server/img/icons_alerta_naoanimado.gif" alt="Problema de Configura&ccedil;&atilde;o"></div>
                        <div id="msgPendenteConfiguracao">Problema de Configura&ccedil;&atilde;o</div>
                    </div>
                    <!-- Monta caixa das Abas !-->
                    <div id="caixa" class="detalharOfertas">
                        <!-- Menu das Abas !-->
                        <span id="abas">
                            <a href="#aba1" id="aba1Link" onclick="alternarAbas(this,'')" class="selected">Oferta</a>
                            <cata:temPermissao acao="acessarOfertafixaComplementar">
                                <a href="#aba2" id="aba2Link" onclick="alternarAbas(this,'')">Oferta Complementar</a>
                            </cata:temPermissao>
                            <cata:temPermissao acao="acessarOfertafixaDisponibilidade">
                                <a href="#aba3" id="aba3Link" onclick="alternarAbas(this,'')">Disponibilidade</a>
                            </cata:temPermissao>
                        </span>
                        <ul id="conteudos">
                            <li id="aba1" class="contentRel">
                                <div id="detalhesOfertas"></div>
                            </li>
                            <cata:temPermissao acao="acessarOfertafixaComplementar">
                                <li id="aba2" class="contentRel" style="display: none">
                                    <div id="ofertaComplementar"></div>
                                </li>
                            </cata:temPermissao>
                            <cata:temPermissao acao="acessarOfertafixaDisponibilidade">
                                <li id="aba3" class="contentRel" style="display: none">
                                    <div id="disponibilidade"></div>                        
                                </li>
                            </cata:temPermissao>
                        </ul>
	                    <br/>
				        <div class="botao"  id="gravarOfertas" style="display:none" >
				            <cata:temPermissao acao="gravarOfertafixa">
				                <html:button property="botao_gravar_oferta" styleId="botao_gravar_oferta" onClick="gravar('')" styleClass="btNavegacao74" value="Gravar" alt="Gravar Ofertas" title=""/><span>&nbsp;</span>
				            </cata:temPermissao>
				        </div>                        
                    </div>
                </div>
            </div>
            <html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>
            <html:hidden property="disabled" styleId="disabled"/>
            <html:hidden property="idOfertafixa" styleId="idOfertafixa"/>
            <html:hidden property="operacao" styleId="operacao"/>
            <html:hidden property="tipoOfertaCheckedAba" styleId="tipoOfertaCheckedAba"/>
            <html:hidden property="canalVendaCheckedAba" styleId="canalVendaCheckedAba"/>
            <html:hidden property="areaRegistroCheckedAba" styleId="areaRegistroCheckedAba"/>
            <html:hidden property="cdOfertaNewAba" styleId="cdOfertaNewAba"/>
            <html:hidden property="nmOfertaNewAba" styleId="nmOfertaNewAba"/>
            <html:hidden property="idServicoLinhaNewAba" styleId="idServicoLinhaNewAba"/>
            <html:hidden property="idSolicitacaoComercialNormalNewAba" styleId="idSolicitacaoComercialNormalNewAba"/>
            <html:hidden property="idSolicitacaoComercialPreCadastroNewAba" styleId="idSolicitacaoComercialPreCadastroNewAba"/>
            <html:hidden property="idServicoPlanoNewAba" styleId="idServicoPlanoNewAba"/>
            <html:hidden property="idSolicitacaoComercialPlanoNewAba" styleId="idSolicitacaoComercialPlanoNewAba"/>
            <html:hidden property="dataVigenteInicioNewAba" styleId="dataVigenteInicioNewAba"/>
            <html:hidden property="dataVigenteFimNewAba" styleId="dataVigenteFimNewAba"/>
            <html:hidden property="disponibilidadeAlterada" styleId="disponibilidadeAlterada"/>
            <html:hidden property="complementarAlterada" styleId="complementarAlterada"/>
        </html:form>     
</jsp:body>        
</catalogo:defaultTemplate>