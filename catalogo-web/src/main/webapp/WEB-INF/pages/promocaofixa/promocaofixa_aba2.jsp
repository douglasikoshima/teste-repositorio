<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/search.do" >

<c:if test='${promocaoTO != null && order != null}'>
    <display:table name="composicaoPromocaoTOList" id="composicaoPromocaoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderItem.do" class="tabela-padrao-new tablesorter table_body" >
        <display:column property="nomeServico" title="Nome do Serviço" sortable="true" headerClass="sortable" />
        <display:column property="nomeDesconto" title="Nome do Desconto" sortable="true" headerClass="sortable" />
        <display:column property="nomeSolicitacao" title="Nome da Solicitacao" sortable="true" headerClass="sortable" />
        <display:column property="validadeDesconto" title="Validade do Desconto (Meses)" sortable="true" headerClass="sortable" />
        <cata:temPermissao acao="excluirItemOfertaBandaLarga">
            <c:if test="${promocaoTO.alteravel}">
                <display:column title="Excluir" headerClass="sortable">
                    <img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="remove('${composicaoPromocaoTO.idComposicao}','${composicaoPromocaoTO.idServico}','${composicaoPromocaoTO.idSolicitacao}')" style="cursor: pointer;"/>
                </display:column>
            </c:if>
        </cata:temPermissao>
    </display:table>
</c:if>
<c:if test="${promocaoTO != null && order == null}">
    <c:if test="${error != null}">
        <div id="flashComposicaoPromocao" style="display:block;color:red !important">${error}</div><br/>
    </c:if>
    <c:if test="${success != null}">
        <div id="flashComposicaoPromocao" style="display:block;color: #299B00 !important">${success}</div><br/>
    </c:if>
    <catalogo:contentBox title="Itens da Oferta Banda Larga" doubt="false" requiredFields="false">
        <div id="displayComposicaoPromocaoTOList">
            <display:table name="composicaoPromocaoTOList" id="composicaoPromocaoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderItem.do,displayComposicaoPromocaoTOList" class="tabela-padrao-new tablesorter table_body" >
                <display:column property="nomeServico" title="Nome do Serviço" sortable="true" headerClass="sortable" />
                <display:column property="nomeDesconto" title="Nome do Desconto" sortable="true" headerClass="sortable" />
                <display:column property="nomeSolicitacao" title="Nome da Solicitacao" sortable="true" headerClass="sortable" />
                <display:column property="validadeDesconto" title="Validade do Desconto (Meses)" sortable="true" headerClass="sortable" />
                <cata:temPermissao acao="excluirItemOfertaBandaLarga">
                    <c:if test="${promocaoTO.alteravel}">
                        <display:column title="Excluir" headerClass="sortable">
                            <img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="remove('${composicaoPromocaoTO.idComposicao}','${composicaoPromocaoTO.idServico}','${composicaoPromocaoTO.idSolicitacao}')" style="cursor: pointer;"/>
                        </display:column>
                    </c:if>                
                </cata:temPermissao>
            </display:table>
        </div>
    </catalogo:contentBox>
    <div id="box_erro_aba2" style="display: none">
        <div id="erro_aba2" class="box_middle_center_conteudo" style="color:red !important;"></div>
        <div class="barra"></div>
    </div>
    <c:if test='${promocaoTO.composicaoPromocaoTODisable == ""}'>
        <div id="clone" style="float: left;">
            <div class="boxline">
                <div class="linerow bold">C&oacute;digo do Servi&ccedil;o:</div>
                <div class="linerow clear">
                    <input id="idServico" type="hidden" class="data"/>
                    <input id="nmServico" type="hidden" class="data"/>
                    <input id="cdServico" disabled="true" type="text" class="data"/>
                </div>
            </div>  
            <div class="boxline">
                <div class="linerow bold">Tipo do Servi&ccedil;o:<span class="required">*</span></div>
                <div class="linerow clear">
                    <select id="idTipoServico" onchange="carregarServicoList()" class="data changeable">
                        <option value="">--Selecione--</option>
                        <c:forEach var="tipoServicoTO" items="${tipoServicoTOList}">
                            <option value="${tipoServicoTO.idTipoServico}">${tipoServicoTO.nmTipoServico}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="boxline" style="width: 380px !important;">
                <div class="linerow bold">Nome do Servi&ccedil;o:<span class="required">*</span></div>
                <div class="linerow clear"  id="servicoList" style="width: 370px !important;">
                    <select id="idCdServico" disabled="true" onchange="carregarServicoDescontoList()" style="width: 340px !important;" class="data changeable">
                        <option value="" >-- Selecione --</option>
                    </select>
                </div>
            </div>
            <span class="clear"></span>
            <div class="boxline">
                <div class="linerow bold">C&oacute;digo do Desconto:</div>    
                <div class="linerow clear">
                    <input id="nmServicoDesconto" type="hidden" class="data changeable" />
                    <input id="idServicoDesconto" type="hidden" class="data changeable" />
                    <input type="text" id="cdServicoDesconto" disabled="true" class="data changeable" />
                </div>
            </div>
            <div class="boxline">
                <div class="linerow bold">Nome do Desconto:<span class="required">*</span></div>    
                <div class="linerow clear" id="servicoDescontoList">
                    <select id="idCdServicoDesconto" disabled="true" onchange="carregarSolicitacaoList()" class="data changeable">
                        <option value="" >-- Selecione --</option>
                    </select>
                </div>
            </div>
            <div class="boxline">
                <div class="linerow bold">C&oacute;digo da Solicita&ccedil;&atilde;o Comercial:</div>    
                <div class="linerow clear">
                    <input id="idSolicitacaoComercial" type="hidden" class="data changeable" />
                    <input id="pzMaximoVigenciaATIS" type="hidden" class="data changeable" />
                    <input id="nmSolicitacaoComercial" type="hidden" class="data changeable" />                                            
                    <input type="text" id="cdSolicitacaoComercial" disabled="true" class="data changeable" />
                </div>
            </div>
            <div class="boxline">
                <div class="linerow bold">Nome da Solicita&ccedil;&atilde;o Comercial:<span class="required">*</span></div>
                <div class="linerow clear" id="solicitacaoComercialList">
                    <select id="idCdSolicitacaoComercial" disabled="true" onchange="carregarCdSolicitacao()" class="data changeable">
                        <option value="" >-- Selecione --</option>
                    </select>
                </div>
            </div>
            <div class="boxline">
                <div class="linerow bold">Validade do Desconto:<span class="required">*</span></div>
                <div class="linerow clear">
                    <input type="text" id="validadeDesconto" class="data changeable" />
                </div>
            </div>
            <div class="boxline">
                <div class="linerow bold"></div>
                <div class="linerow clear">
                    <cata:temPermissao acao="adicionarItemOfertaBandaLarga">
                        <span><a href="#botao"  id="adicionarCampo"  class="adicionarCampo">Adicionar</a></span>
                    </cata:temPermissao>                                    
                </div>    
            </div>
        </div>
        <div class="barra"></div>
        <div class="botao">
            <cata:temPermissao acao="gravarOfertaBandaLarga">
                <html:button property="botao_gravar_itens" styleId="botao_gravar_itens" onClick="gravarItens()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
            </cata:temPermissao>                                
        </div>
    </c:if>
</c:if>
</html:form>
<html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>