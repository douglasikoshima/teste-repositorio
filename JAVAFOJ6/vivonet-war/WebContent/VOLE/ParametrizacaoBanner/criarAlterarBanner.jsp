<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="parametrizacaoBannerForm"
             type="VOLE.ParametrizacaoBanner.formBeans.ParametrizacaoBannerForm" />

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery.fileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/messages.js"></script>
    <script type="text/javascript">
    <!--

    jQuery.noConflict();

    var imageUploaded = false;
    var tdArquivoInnerHTML;

    var imageMaxWidth = 0;
    var imageMaxHeight = 0;

    var imageWidth, imageHeight, imageSize, imageExtension;

    var ESQ_mxW = 145;
    var ESQ_mxH = 310;
    var CEN_mxW = 630;
    var CEN_mxH = 180;
    var ALE_mxW = 778;
    var ALE_mxH = 400;

    ajaxFileUpload = function(obj) {

        imageUploaded = false;
        var extensao = getExtensao();

        $('nome-arquivo').value = obj.value;
        if ($('tipoBannerImagem').checked && extensao != 'JPG'
                && extensao != 'JPEG'
                && extensao != 'JPE'
                && extensao != 'GIF'
                && extensao != 'SWF') {
            alert('Por favor, selecione um arquivo válido.');
            return;
        }

        if (extensao == 'SWF') {
            $('tipoBannerFlash').checked = true;
            changeTipoBanner($('tipoBannerFlash'));
        } else if (extensao == 'JPG'
                || extensao == 'JPEG'
                || extensao == 'JPE'
                || extensao == 'GIF') {
            $('tipoBannerImagem').checked = true;
            changeTipoBanner($('tipoBannerImagem'));
        }

        jQuery('#loading').ajaxStart(function(){
            jQuery(this).show();
        }).ajaxComplete(function(){
            jQuery(this).hide();
        });

        jQuery.ajaxFileUpload ({
            url : 'enviarArquivo.do?idAreaBanner=' + $('idAreaBanner').value,
            secureuri: false,
            fileElementId: 'arquivo',
            dataType: 'json',
            success: function (data, status) {
                if (data.image) {
                    imageExtension = data.image.extension;
                    imageWidth  = data.image.width;
                    imageHeight = data.image.height;
                    imageSize   = data.image.size;
                    $('nmArquivoBanner').value = data.image.filename;
                    $('extensaoBanner').value = imageExtension;
                    imageUploaded = true;
                } else if (data.error) {
                    alert(data.error.msg);
                    imageUploaded = false;
                }
            }, error: function (data, status, e) {
                // alert(e);
            }
        })
        return false;
    }

    getExtensao = function() {
        var extensao = '';
        try {
            extensao = $F('arquivo').substring($F('arquivo').lastIndexOf('.') + 1, $F('arquivo').length).toUpperCase();
        } catch(e) {}
        return extensao;
    }

    validaForm = function() {
        if ($F('dsBanner').blank()) {
            alert('Por favor, digite uma descrição para o banner.');
            return false;
        } else if ($('idAreaBanner').selectedIndex == 0) {
            alert('Por favor, selecione uma área para o banner.');
            return false;
        } else if (!imageUploaded) {
            alert('Por favor, selecione um arquivo.');
            return false;
        } else if ($('tipoBannerImagem').checked && imageExtension != 'JPG'
                && imageExtension != 'JPEG'
                && imageExtension != 'JPE'
                && imageExtension != 'GIF') {
            alert('Por favor, selecione um arquivo válido.');
            return false;
        }

        var cdArea = '';
        switch (parseInt($('idAreaBanner').value)) {
            // Área Central
            case 1:
                cdArea = 'CEN';
                break;
            // Área lateral esquerda
            case 2:
                cdArea = 'ESQ';
                break;
            // Aleatória
            case 3:
                cdArea = 'ALE';
                break;
        }

        imageMaxWidth = eval(cdArea + '_mxW');
        imageMaxHeight = eval(cdArea + '_mxH');

        if ($('tipoBannerFlash').checked && imageExtension != 'SWF') {
            alert('Por favor, selecione um arquivo válido.');
            return false;
        } else if (imageWidth > imageMaxWidth || imageHeight > imageMaxHeight) {
            var msgAlert  = 'Esta imagem possui dimensões maiores que o máximo permitido.\n';
                msgAlert += 'Imagem: ' + imageWidth + 'x' + imageHeight + ' pixels.\n';
                msgAlert += 'Máximo permitido: ' + imageMaxWidth + 'x' + imageMaxHeight + ' pixels.';
            alert(msgAlert);
            return false;
        } else if ($('tipoBannerImagem').checked && !isValidURL($F('urlBanner'))) {
            alert('Por favor, digite um valor de URL válido.');
            return false;
        } else if ($('autenticacao').value == 'true' && !isValidIPAddress($F('nrIPBanner'))) {
            alert('Por favor, digite um número de IP válido.');
            return false;
        } else if (!validaData($F('dtVigenciaDe'))) {
            alert('Por favor, selecione uma data inicial de vigência.');
            return false;
        } else if (!$F('dtVigenciaAte').blank() && !validaData($F('dtVigenciaDe'))) {
            alert('Por favor, selecione uma data final de vigência válida.');
            return false;
        } else if (!$F('dtVigenciaAte').blank() &&
                validaData($F('dtVigenciaAte')) && !validaDataFinal($F('dtVigenciaDe'), $F('dtVigenciaAte'))) {
            alert('Por favor, selecione uma data final de vigência maior ou igual à data inicial.');
            return false;
        }
        return true;
    }
    salvarBanner = function() {
        if (validaForm()) {
            tdArquivoInnerHTML = $('td-arquivo').innerHTML;
            $('td-arquivo').innerHTML = '';
            $('nrIPBanner').value = '10.0.0.1';
            new Ajax.Request('salvarCadastroAlteracao.do', {
                method: 'post',
                parameters: $('formCadastroAlteracaoBanner').serialize(),
                onSuccess: function(e) {
                    var participio = ($F('idBanner') == 0) ? 'cadastrado' : 'alterado';
                    alert('Banner ' + participio + ' com sucesso.');
                    parent.$('cadastroAlteracaoBanner').hide();
                    parent.pesquisar();
                }, onCreate: function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                }, onFailure: function(e) {
                    $('td-arquivo').innerHTML = tdArquivoInnerHTML;
                    window.top.frameApp.createErrorPopUp('erroParametrizacaoBanner', Messages.ERRO_PADRAO, e.responseText, false);
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }
            });
        }
    }
    changeAutenticacao = function(obj) {
        if (obj.value == 'true') {
            $('tr-url-ip').show();
        } else if (obj.value == 'false') {
            $('tr-url-ip').hide();
        }
    }
    changeTipoBanner = function(obj) {
        if (obj.value == 1) { // Flash
            $('urlBanner').value = '';
            $('autenticacao').selectedIndex = 1;
            $('tr-url-ip').hide();
            $('tr-link-url-ip').hide();
        } else {
            $('tr-link-url-ip').show();
        }
    }
    onload = function() {
        <logic:equal name="Form" property="banner.idBanner" value="0">
        $('tipoBannerImagem').checked = true;
        </logic:equal>
        <logic:notEqual name="Form" property="banner.idBanner" value="0">
            imageUploaded = true;
            imageExtension = $F('extensaoBanner');
            <logic:notEqual name="Form" property="banner.nrIPBanner" value="">
            $('autenticacao').options[0].selected = true;
            changeAutenticacao($('autenticacao'));
            </logic:notEqual>
            <logic:equal name="Form" property="banner.idTipoBanner" value="1">
                $('tipoBannerFlash').checked = true;
                changeTipoBanner($('tipoBannerFlash'));
            </logic:equal>
            <logic:notEqual name="Form" property="banner.idTipoBanner" value="1">
                $('tipoBannerImagem').checked = true;
                changeTipoBanner($('tipoBannerImagem'));
            </logic:notEqual>
        </logic:notEqual>
        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.stopAnimation();
    }
    -->
    </script>
    <style type="text/css">
        body {
            background:#ededed;
        }
        input {
            width: 400px;
        }
        select {
            width:400px;
            margin-left:3px;
        }
        #div-nome-arquivo {
            position: absolute;
            margin:0;
            margin-left:-2px;
        }
        #nome-arquivo {
            border:1px solid #000;
            border-right: none;
            color:#006699;
            white-space: nowrap;
            background:#fff;
            width:305px;
            height:17px;
            overflow: hidden;
            margin-top: 0px;
        }
    </style>
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<form id="formCadastroAlteracaoBanner" name="formCadastroAlteracaoBanner" enctype="multipart/form-data" method="post" action="enviarArquivo.do">
    <html:hidden name="Form" property="banner.idBanner" styleId="idBanner" />
    <html:hidden name="Form" property="banner.nmArquivoBanner" styleId="nmArquivoBanner" />
    <html:hidden name="Form" property="banner.extensaoBanner" styleId="extensaoBanner" />
    <table>
        <tr>
            <td class="bold">Descrição:</td>
            <td valign="top">
                <html:text name="Form" property="banner.dsBanner" styleId="dsBanner" maxlength="60" />
            </td>
        </tr>
        <tr>
            <td class="bold">Área:</td>
            <td valign="top">
                <html:select name="Form" property="banner.idAreaBanner" styleId="idAreaBanner">
                    <html:option value="">-- Selecione --</html:option>
                    <html:optionsCollection name="Form" property="listaAreas" label="value" value="key" />
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="bold">Arquivo:</td>
            <td valign="top" id="td-arquivo">
                <div id="div-nome-arquivo">
                    <input type="text" id="nome-arquivo" readonly />
                </div>
                <html:file name="Form" property="banner.arquivo" styleId="arquivo" onchange="ajaxFileUpload(this)" style="width:380px;" />
                <img align="absmiddle" id="loading" src="<%=request.getContextPath()%>/resources/images/loader.gif" style="display:none" />
            </td>
        </tr>
        <tr>
            <td valign="top" colspan="2">
                <div id="tr-link-url-ip">
                    <table cellpadding="0" cellspacing="1">
                        <tr>
                            <td width="110" class="bold">Link:</td>
            <td valign="top">
                                <html:select name="Form" property="banner.autenticacao" styleId="autenticacao" onchange="changeAutenticacao(this)" style="margin-bottom:2px;">
                    <html:option value="true">Com Autenticação</html:option>
                    <html:option value="false">Sem Autenticação</html:option>
                </html:select>
            </td>
        </tr>
        <tr>
            <td valign="top" class="bold">URL:</td>
            <td valign="top">
                                <html:text name="Form" property="banner.urlBanner" styleId="urlBanner" maxlength="250" />
            </td>
        </tr>
        <tr>
            <td valign="top" colspan="2">
                <div id="tr-url-ip" style="display:none">
                                    <table cellpadding="0" cellspacing="1">
                        <tr>
                                            <td width="110" class="bold">IP:</td>
                            <td valign="top">
                                                <html:text name="Form" property="banner.nrIPBanner" styleId="nrIPBanner" maxlength="250" />
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
        <tr>
            <td class="bold">Vigência:</td>
            <td valign="top">
                <html:text name="Form"
                           property="banner.dtVigenciaDe"
                           styleId="dtVigenciaDe"
                           styleClass="data"
                           maxlength="10"
                           onkeyup="checaData(this)"
                           onblur="checaData(this)"
                           onfocus="checaData(this)" />
                <img src="<%=request.getContextPath()%>\resources\images\calendario.gif"
                     class="button"
                     title="Calendário"
                     onclick="return showCalendar('dtVigenciaDe', '%d/%m/%Y');" />
                até
                <html:text name="Form"
                           property="banner.dtVigenciaAte"
                           styleId="dtVigenciaAte"
                           styleClass="data"
                           maxlength="10"
                           onkeyup="checaData(this)"
                           onblur="checaData(this)"
                           onfocus="checaData(this)" />
                <img src="<%=request.getContextPath()%>\resources\images\calendario.gif"
                     class="button"
                     title="Calendário"
                     onclick="return showCalendar('dtVigenciaAte', '%d/%m/%Y');" />
            </td>
        </tr>
        <tr>
            <td class="bold">Tipo Imagem:</td>
            <td valign="top">

                <html:radio name="Form" value="1" property="banner.idTipoBanner" styleId="tipoBannerFlash" styleClass="radio" onclick="changeTipoBanner(this)" />
                <label for="tipoBannerFlash">.SWF</label>

                <html:radio name="Form" value="2" property="banner.idTipoBanner" styleId="tipoBannerImagem" styleClass="radio" onclick="changeTipoBanner(this)" />
                <label for="tipoBannerImagem">Imagem (.GIF/.JPG)</label>

            </td>
        </tr>
        <tr>
            <td class="bold">Contador de Acessos:</td>
            <td valign="top">
                <html:checkbox name="Form" property="banner.contadorAcessos" styleId="contadorAcessos" styleClass="checkbox" />
            </td>
        </tr>
        <tr>
            <td valign="top" colspan="2" align="right">
                <img src="<%=request.getContextPath()%>\resources\images\bt_<%=Form.getBanner().getIdBanner()==0?"incluir":"alterar"%>_nrml.gif"
                     class="button"
                     onmouseup="salvarBanner()" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>