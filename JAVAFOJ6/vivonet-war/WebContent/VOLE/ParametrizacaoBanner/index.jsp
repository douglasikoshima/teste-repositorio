<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="parametrizacaoBannerForm"
             type="VOLE.ParametrizacaoBanner.formBeans.ParametrizacaoBannerForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="VOL-E"/>
<netui-template:setAttribute name="modulo" value="Manutenção de Banners VOL-E"/>
<netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript">
        var nenhumaBusca = 'Nenhuma busca foi realizada até o momento.';
        onload = function() {
            if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
        }
        incluirBanner = function() {
            $('cadastroAlteracaoBanner').show();
            $('dv_cadastroAlteracaoBanner').innerText = 'Incluir Banner';
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            $('iframeCadastroAlteracaoBanner').src = 'about:blank';
            $('iframeCadastroAlteracaoBanner').src = 'beginCadastroBanner.do';
        }
        alterarBanner = function(idBanner) {
            $('cadastroAlteracaoBanner').show();
            $('dv_cadastroAlteracaoBanner').innerText = 'Alterar Banner';
            $('iframeCadastroAlteracaoBanner').src = 'about:blank';
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            $('iframeCadastroAlteracaoBanner').src = 'buscarDadosBanner.do?idBanner=' + idBanner;
        }
        pesquisar = function() {
            if ($('rd-upload').checked) { // Upload
                if (getAmountSelectedItems($('filtroAreas')) == 0) {
                    selectAllOptions($('filtroAreas'));
                }
                new Ajax.Updater('lista-banners', 'pesquisar.do?tipoPesquisa=upload', {
                    method: 'post',
                    parameters: $('form-filtros').serialize(),
                    onSuccess: function(e) {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.stopAnimation();
                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }, onFailure: function(e) {
                        var sessaoExpirada = e.getHeader("sessaoExpirada") ? true : false;
                        window.top.frameApp.createErrorPopUp('erroParametrizacaoBanner', e.statusText, e.responseText, sessaoExpirada);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            } else { // Associação
                if (getAmountSelectedItems($('filtroAreas')) == 0) {
                    alert('Por favor, selecione ao menos uma Área do Banner.');
                } else {
                    if (getAmountSelectedItems($('filtroUFs')) == 0) {
                        selectAllOptions($('filtroUFs'));
                    }
                    new Ajax.Updater('lista-banners', 'pesquisar.do?tipoPesquisa=associacao', {
                        method: 'post',
                        parameters: $('form-filtros').serialize(),
                        onSuccess: function(e) {
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.stopAnimation();
                        }, onCreate: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                        }, onFailure: function(e) {
                            var sessaoExpirada = e.getHeader("sessaoExpirada") ? true : false;
                            window.top.frameApp.createErrorPopUp('erroParametrizacaoBanner', e.statusText, e.responseText, sessaoExpirada);
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }
                    });
                }
            }
        }
        paginacao = function(pagina) {
            var nrPagina = parseInt($F('nrPagina'));
            if (pagina == 'proxima') {
                nrPagina = nrPagina + 1;
            } else if (pagina == 'anterior') {
                nrPagina = nrPagina - 1;
            } else {
                nrPagina = pagina;
            }
            $('nrPagina').value = nrPagina;
            pesquisar();
        }
        manageUploadAssociacao = function(operacao) {
            $('lista-banners').update(nenhumaBusca);
            $('filtroAreas').selectedIndex = -1;
            if (operacao == 'upload') {
                $('filtroAreas').multiple = true;
                $('filtroUFs').selectedIndex = -1;
                $('divUF').hide();
                $('botoes-associacao').hide();
                $('bt-incluir').show();
            } else {
                $('filtroAreas').multiple = false;
                $('divUF').show();
                $('botoes-associacao').show();
                $('bt-incluir').hide();
            }
        }
        manageCheckboxes = function(obj) {
            var arrayCB = $('lista-banners').select('.checkbox-relacionamento');
            for (var i = 0; i < arrayCB.length; i++) {
                arrayCB[i].checked = obj.checked;
            }
        }
        limpar = function() {
            unselectAllOptions($('filtroAreas'));
            $('nrPagina').value = 1;
            $('lista-banners').update(nenhumaBusca);
            if (!$('rd-upload').checked) {
                unselectAllOptions($('filtroUFs'));
            }
        }
        associarDesassociar = function(operacao) {
            var dsOperacao = (operacao == 'associar') ? 'associação' : 'desassociação';
            if ($('lista-banners').innerHTML == nenhumaBusca) {
                alert('Por favor, realize uma pesquisa antes de realizar uma ' + dsOperacao + '.');
            } else {
                var qtdeSelecionados = 0;
                clearSelectList($('relacionamentosSelecionados'));
                var arrayCB = $('lista-banners').select('.checkbox-relacionamento');
                for (var i = 0; i < arrayCB.length; i++) {
                    if (arrayCB[i].checked) {
                        qtdeSelecionados++;
                        addValueToSelectList('', arrayCB[i].value, $('relacionamentosSelecionados'));
                    }
                }
                if (qtdeSelecionados == 0) {
                    alert('Por favor, selecione ao menos um banner para ' + dsOperacao + '.');
                } else {
                    selectAllOptions($('relacionamentosSelecionados'));
                    $('form-filtros').action = 'associarDesassociarBanner.do?operacao=' + operacao;
                    if (operacao == 'associar') {
                        $('associacaoBanner').show();
                        $('iframeAssociacaoBanner').src = 'about:blank';
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                        $('form-filtros').target = 'iframeAssociacaoBanner';
                    } else if (operacao == 'desassociar') {
                        $('form-filtros').target = 'hidden-frame';
                    }
                    $('form-filtros').method = 'post';
                    $('form-filtros').submit();
                }
            }
        }
        mostrarImagemBanner = function(nmArquivo) {
            var params = $H();
            params.set('nmArquivo', nmArquivo);
            createNewPopUp('popBanner', 'Imagem Banner', 800, 500, null, 'mostrarImagemBanner.do', true, params);
        }
        manageListaAssociacao = function() {
            if ($('rd-associacao').checked) {
                $('lista-banners').update(nenhumaBusca);
            }
        }
    </script>
    <style type="text/css">
        #table-parametros {
            border: 1px solid #c7daea;
            width: 100%;
            margin-top: 3px;
        }
        #div-pesquisa {
            border: 1px solid #c7daea;
            margin-top: 5px;
            height: 427px;
            padding: 5px;
        }
        #div-form {
            margin: 5px 0 5px 0;
        }
        #div-form form {
            margin: 0;
            padding: 0;
        }
    </style>
</netui-template:section>
<netui-template:section name="bodySection">
<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
<vivo:sessao id="qdMain" height="470" width="790" label="VOL-E" operacoes="Upload / Associação de Banner" scroll="no">
    <form id="form-filtros" style="padding:0;margin:0 0 5px 0">
        <html:select name="Form" property="relacionamentosSelecionados" styleId="relacionamentosSelecionados" multiple="true" style="display:none" />
        <html:hidden name="Form" property="nrPagina" styleId="nrPagina" value="1" />
        <input name="tipo-filtro" type="radio" id="rd-upload" class="radio" onclick="manageUploadAssociacao('upload')" checked>
        <label for="rd-upload">Upload</label>
        <input name="tipo-filtro" type="radio" id="rd-associacao" class="radio" onclick="manageUploadAssociacao('associacao')">
        <label for="rd-associacao">Associação</label>
        <vivo:quadro width="775" height="145" id="qdrFiltros" scroll="no" label="Filtros">
            <table border="0" width="100%">
                <tr>
                    <td>
                        <table align="left" width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="150" valign="top" height="120" nowrap>
                                    <strong>&Aacute;rea do Banner</strong><br/>
                                    <html:select name="Form" property="filtroAreas" styleId="filtroAreas" multiple="true" size="3" onchange="manageListaAssociacao()">
                                        <html:optionsCollection name="Form" property="listaAreas" label="value" value="key" />
                                    </html:select>
                                </td>
                                <td valign="top" height="120" width="100%">
                                    <div id="divUF" style="display:none">
                                    <strong>UF</strong><br/>
                                    <html:select name="Form" property="filtroUFs" styleId="filtroUFs" multiple="true" size="6" style="width:200px;" onchange="manageListaAssociacao()">
                                        <html:optionsCollection name="Form" property="listaUF" label="sgUF" value="idUF" />
                                    </html:select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="right">
                                    <img id="bt-incluir" src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif" class="button" onmouseup="incluirBanner()" />
                                    <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" class="button" onmouseup="$('nrPagina').value=1;pesquisar()" />
                                    <img src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif" class="button" onmouseup="limpar()" />
                                </td>
                            </tr>
                        </table>
                   </td>
                </tr>
            </table>
        </vivo:quadro>
    </form>
    <vivo:quadro width="775" height="243" id="qdrResultado" scroll="no" label="Lista de Banners">
        <table width="100%">
            <tr>
                <td align="center">
                 <div id="lista-banners" >
                    Nenhuma busca foi realizada até o momento.
                 </div>
                </td>
            </tr>
        </table>
    </vivo:quadro>

    <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td><img src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif"
                     class="button"
                     style="margin-top:5px;"
                     onmouseup="window.location.href='/FrontOfficeWeb/index.jsp';" /></td>
            <td align="right">
                <div id="botoes-associacao" style="display:none">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_associar_nrml.gif" class="button" style="margin-top:5px;" onmouseup="associarDesassociar('associar')" />
                    <img src="<%=request.getContextPath()%>/resources/images/bt_desassociar_nrml.gif" class="button" style="margin-top:5px;" onmouseup="associarDesassociar('desassociar')"  />
                </div>
            </td>
        </tr>
    </table>
</vivo:sessao>
<vivo:quadroFlutuante id="cadastroAlteracaoBanner"
                      idIframe="iframeCadastroAlteracaoBanner"
                      label="Alteração de Banner"
                      scroll="no"
                      spacesLeft="125"
                      spacesTop="150"
                      url="<%=request.getContextPath()%>"
                      display="none"
                      width="530"
                      height="250" />
<vivo:quadroFlutuante id="associacaoBanner"
                      idIframe="iframeAssociacaoBanner"
                      label="Associar Banner"
                      scroll="no"
                      spacesLeft="25"
                      spacesTop="100"
                      url="<%=request.getContextPath()%>"
                      display="none"
                      width="750"
                      height="400" />
<iframe id="hidden-frame" name="hidden-frame" frameborder="0" width="0" height="0" src="about:blank"></iframe>
<vivo:alert atributo="msgRetorno" scope="request" />
</netui-template:section>
</netui-template:template>