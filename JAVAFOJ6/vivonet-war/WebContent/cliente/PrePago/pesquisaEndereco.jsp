<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prePagoEnderecoForm"/>
<bean:define id="list" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listasVO" type="br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO"/>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/FrontOfficeWeb/resources/css/frontoffice.css" />
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/validacao.js"></script>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
    <script type="text/javascript">
        <!--
            parent.IN_PESQUISA_ENDERECO_BASE_FO = <bean:write name="Form" property="pesquisaEnderecoBaseFO" />;
            validaCampos = function() {
                var f = document.forms[0];
                var ldone = false;
                if ($F('nrCEP').blank()) {

                    if ($F('dsLocalidade')=="") {
                        alert("Favor preencher o campo Município.");
                        f.dsLocalidade.focus();
                        return false;

                    } else if ($('idUFSelecionado').selectedIndex == 0) {
                        alert("Favor selecionar um Estado.");
                        f.idUFSelecionado.focus();
                        return false;

                    } else if ($F('dsLogradouro')=="") {
                        alert("Favor selecionar um Logradouro.");
                        f.dsLogradouro.focus();
                        return false;
                    }

                } else {
                    if (verificaNumCEP($F('nrCEP'))) {
                        $('nrCEP').value = $F('nrCEP').replace("-","");
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }

            function loadEnderecos() {
                try {
                if (validaCampos()) {
                    if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                    var nrCEP = $F('nrCEP');
                    var nmLog = $F('dsLogradouro');
                    var nmMun = $F('dsLocalidade');
                    var idUF  = $('idUFSelecionado').value;
                    var sgUF  = ($('idUFSelecionado').selectedIndex <= 0) ? '' : $('idUFSelecionado').options[$('idUFSelecionado').selectedIndex].text;
                    new Ajax.Request('/FrontOfficeWeb/cliente/PrePago/pesquisarEndereco.do?sgUF=' + sgUF, {
                        method: 'post',
                        parameters: {
                            nrCEP: nrCEP, limit: 9,
                            nmLogradouro: nmLog, limit: 255,
                            nmMunicipio: nmMun, limit: 255,
                            idEndUF: idUF, limit: 2
                        },
                        contentType: 'application/x-www-form-urlencoded',
                        onComplete: function(r) {

                            $('tabListaEndereco').update(r.responseText);
                            var janela = window.top.frameApp;

                            if (($('errEnd')!=null && $('errEnd').innerHTML!="")) {
                                alert($('errEnd').innerText.replace('\\n', '\n').replace('[', '\n['));

                            } else

                            if ($('msgEnd')!=null && $('msgEnd').innerHTML != '') {

                                if (messageBox("Endereço não localizado. Deseja continuar a pesquisa?") == "6") {
                                    $('formPesqEnd').reset();
                                } else {

                                    var parametrosPesquisa;
                                    if (!$F('nrCEP').blank()) {
                                        parametrosPesquisa = '\nCEP: ' + $F('nrCEP');
                                    } else {
                                        parametrosPesquisa  = '\nLogradouro: ' + $F('dsLogradouro').strip();
                                        parametrosPesquisa += '\nMunicípio: ' + $F('dsLocalidade').strip();
                                        parametrosPesquisa += '\nUF: ' + $('idUFSelecionado').options[$('idUFSelecionado').selectedIndex].text;
                                    }

                                    if (!parent.IN_PESQUISA_ENDERECO_BASE_FO) {

                                        alert('Endereço não localizado. Por favor, refine a pesquisa ou cadastre o endereço no novo ADM.' + parametrosPesquisa);

                                    } else {

                                        if (messageBox('Tem certeza que deseja abrir o processo?') == '6') {

                                            if (janela.inTipoPessoa == 'PF' && janela.bt01.className == 'abaSelected') {

                                                janela.$('inEndDefaultCli').value = "1";
                                                if (janela.$('imgAltEndCli').style.display=="none") {
                                                    janela.$('imgIncEndCli').show();
                                                    janela.$('imgAltEndCli').hide();
                                                } else {
                                                    janela.$('imgIncEndCli').hide();
                                                    janela.$('imgAltEndCli').show();
                                                }
                                                janela.$('btIncluirEndCliente').show();

                                            } else if (janela.inTipoPessoa == 'PJ' && janela.bt01.className == 'abaSelected') {

                                                janela.$('inEndDefaultPJ').value = "1";
                                                if (janela.$('imgAltEndPJ').style.display=="none") {
                                                    janela.$('imgIncEndPJ').show();
                                                    janela.$('imgAltEndPJ').hide();
                                                } else {
                                                    janela.$('imgIncEndPJ').hide();
                                                    janela.$('imgAltEndPJ').show();
                                                }
                                                janela.$('btIncluirEndPJ').show();

                                            } else if (janela.bt03.className == 'abaSelected') {

                                                janela.$('inEndDefaultUsu').value = "1";
                                                if (janela.$('imgAltEndUsu').style.display=="none") {
                                                    janela.$('imgIncEndUsu').show();
                                                    janela.$('imgAltEndUsu').hide();
                                                } else {
                                                    janela.$('imgIncEndUsu').hide();
                                                    janela.$('imgAltEndUsu').show();
                                                }
                                                janela.$('btIncluirEndUsuario').show();
                                            }
                                            janela.$('imgClose_divPopupTI').click();
                                            janela.$('divPopupTI').hide();
                                        }
                                    }
                                }
                            }
                            if ( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
                        },
                        onFailure: function(r) {alert("[Failure] "+r.responseText);},
                            onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport);}
                    });
                }
                }catch(e) {
                    //alert("[loadEnderecos] "+e.description);
                }
                return false;
            }

            function get_radio_value() {
                for (var i=0; i < document.formPesqEndereco.enderecoSelecionado.length; i++) {
                    if (document.formPesqEndereco.enderecoSelecionado[i].checked) {
                        var rad_val = document.formPesqEndereco.enderecoSelecionado[i].value;
                        return rad_val;
                    }
                }
            }

            function recuperarEndereco() {
                endereco = document.forms[0].enderecoSelecionado;
                var indexEndereco, janela;
                if (!endereco.length)
                    indexEndereco = 0;
                else
                    for(i = 0; i < endereco.length; i++)
                        if (endereco[i].checked)
                            indexEndereco = i;

                if (indexEndereco != undefined) {
                    if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();

                    new Ajax.Request('getAjaxEndereco.do', {
                        method: 'get',
                        parameters: {
                            indexEndereco: indexEndereco, limit: 12
                        },
                        contentType: 'text/xml',
                        onComplete: function (originalRequest) {

                            var xmlString = originalRequest.responseText;
                            var oXml      = new ActiveXObject("microsoft.xmldom");
                            oXml.async    = false;
                            var regExp    = new RegExp ('&', 'gi') ;
                            xmlString     = xmlString.replace(regExp,"&amp;");
                            oXml.loadXML(xmlString);

                            if (oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {
                                exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                                friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                                alert(friendlyMessage);

                            } else {

                                var nmTipoLogradouro      = oXml.selectSingleNode("/xml-fragment/nmTipoLogradouro").text;
                                nmTipoLogradouro          = (nmTipoLogradouro!=null && nmTipoLogradouro!="")?trim(nmTipoLogradouro):"null";
                                var nmTituloLogradouro    = oXml.selectSingleNode("/xml-fragment/nmTituloLogradouro").text;
                                var nmLogradouro          = oXml.selectSingleNode("/xml-fragment/nmLogradouro").text;
                                nmLogradouro              = (nmLogradouro!=null && nmLogradouro!="")?trim(nmLogradouro):"null";
                                var dsEnderecoComplemento = oXml.selectSingleNode("/xml-fragment/dsEnderecoComplemento").text;
                                var nmBairro              = oXml.selectSingleNode("/xml-fragment/nmBairro").text;
                                nmBairro                  = (nmBairro!=null && nmBairro!="")?trim(nmBairro):"null";
                                var nmMunicipio           = oXml.selectSingleNode("/xml-fragment/nmMunicipio").text;
                                nmMunicipio               = (nmMunicipio!=null && nmMunicipio!="")?trim(nmMunicipio):"null";
                                var nrCEP                 = oXml.selectSingleNode("/xml-fragment/nrCEP").text;
                                var idUF                  = oXml.selectSingleNode("/xml-fragment/UFVO/idUF").text;
                                idUF                      = (idUF!=null && idUF!="")?idUF:"0";
                                var codLogradouro         = oXml.selectSingleNode("/xml-fragment/codLogradouro").text;
                                codLogradouro             = (codLogradouro!=null && codLogradouro!="")?trim(codLogradouro):"";
                                var inCnl                 = oXml.selectSingleNode("/xml-fragment/inCnl").text;
                                inCnl                     = (inCnl!=null && inCnl!="")?trim(inCnl):"";
                                var inCodigoIBGE          = oXml.selectSingleNode("/xml-fragment/inCodigoIBGE").text;
                                inCodigoIBGE              = (inCodigoIBGE!=null && inCodigoIBGE!="")?trim(inCodigoIBGE):"";
                                var sgUF                  = oXml.selectSingleNode("/xml-fragment/UFVO/sgUF").text;
                                var nmPais                = oXml.selectSingleNode("/xml-fragment/PaisVO/nmPais").text;

                                janela = window.top.frameApp;

                                var origem = '<%=request.getAttribute("origem")%>';
                                if (origem == "FORMDINAMICO") {
                                    if (window.top.frameApp.ti_frameAbas) {
                                        janela = window.top.frameApp.ti_frameAbas.ifrmAbas;
                                    } else if (window.top.frameApp.iframeRetornoOperacao.ifrmAbas) {
                                        janela = window.top.frameApp.iframeRetornoOperacao.ifrmAbas;
                                    } else {
                                        janela = window.top.frameApp.fraAbas.ifrmAbas;
                                    }
                                }

                                if (origem == "FORMDINAMICO") {

                                    janela.$('nmTipoLogradouro').value      = nmTipoLogradouro;
                                    janela.$('nmTituloLogradouro').value    = nmTituloLogradouro;
                                    janela.$('nmLogradouro').value          = nmLogradouro;
                                    janela.$('dsEnderecoComplemento').value = dsEnderecoComplemento;
                                    janela.$('nmBairro').value              = nmBairro;
                                    janela.$('nmMunicipio').value           = nmMunicipio;
                                    janela.$('nrCEP').value                 = nrCEP;
                                    janela.$('sgUF').value                  = sgUF;
                                    janela.$('nmPais').value                = nmPais;
                                    janela.$('spanEstado').innerHTML        = sgUF;
                                    janela.$('spanPais').innerHTML          = nmPais;

                                } else {

                                    if (janela.inTipoPessoa=="PF") {
                                        if (janela.bt01.className=="abaSelected") {
                                            janela.$('tpLogradouroCli').value    = nmTipoLogradouro;
                                            janela.$('dsTitLogradouroCli').value = nmTituloLogradouro;
                                            janela.$('nmLogradouroCli').value    = nmLogradouro;
                                            janela.$('dsComplementoCli').value   = dsEnderecoComplemento;
                                            janela.$('nmBairroCli').value        = nmBairro;
                                            janela.$('nmMunicipioCli').value     = nmMunicipio;
                                            janela.$('nrCEPCli').value           = nrCEP;
                                            janela.$('idUFEnderecoCli').value    = idUF;
                                            janela.$('codLogradouroCli').value   = codLogradouro;
                                            janela.$('inCnlCli').value           = inCnl;
                                            janela.$('inCodigoIBGECli').value    = inCodigoIBGE;

                                            if (janela.$('imgAltEndCli').style.display=="none") {
                                            janela.$('imgIncEndCli').show();
                                            janela.$('imgAltEndCli').hide();
                                            } else {
                                                janela.$('imgIncEndCli').hide();
                                                janela.$('imgAltEndCli').show();
                                            }
                                            janela.$('btIncluirEndCliente').show();
                                        }
                                    } else if (janela.inTipoPessoa=="PJ") {
                                        if (janela.bt01.className=="abaSelected") {
                                            janela.$('tpLogradouroPJ').value    = nmTipoLogradouro;
                                            janela.$('dsTitLogradouroPJ').value = nmTituloLogradouro;
                                            janela.$('nmLogradouroPJ').value    = nmLogradouro;
                                            janela.$('dsComplementoPJ').value   = dsEnderecoComplemento;
                                            janela.$('nmBairroPJ').value        = nmBairro;
                                            janela.$('nmMunicipioPJ').value     = nmMunicipio;
                                            janela.$('nrCEPPJ').value           = nrCEP;
                                            janela.$('idUFEnderecoPJ').value    = idUF;
                                            janela.$('codLogradouroPJ').value   = codLogradouro;
                                            janela.$('inCnlPJ').value           = inCnl;
                                            janela.$('inCodigoIBGEPJ').value    = inCodigoIBGE;
                                            if (janela.$('imgAltEndPJ').style.display=="none") {
                                            janela.$('imgIncEndPJ').show();
                                            janela.$('imgAltEndPJ').hide();
                                            } else {
                                                janela.$('imgIncEndPJ').hide();
                                                janela.$('imgAltEndPJ').show();
                                            }
                                            janela.$('btIncluirEndPJ').show();
                                        }
                                    }
                                    if (janela.bt03.className=="abaSelected") {
                                        janela.$('tpLogradouroUsu').value    = nmTipoLogradouro;
                                        janela.$('dsTitLogradouroUsu').value = nmTituloLogradouro;
                                        janela.$('nmLogradouroUsu').value    = nmLogradouro;
                                        janela.$('dsComplementoUsu').value   = dsEnderecoComplemento;
                                        janela.$('nmBairroUsu').value        = nmBairro;
                                        janela.$('nmMunicipioUsu').value     = nmMunicipio;
                                        janela.$('nrCEPUsu').value           = nrCEP;
                                        janela.$('idUFEnderecoUsu').value    = idUF;
                                        janela.$('codLogradouroUsu').value   = codLogradouro;
                                        janela.$('inCnlUsu').value           = inCnl;
                                        janela.$('inCodigoIBGEUsu').value    = inCodigoIBGE;
                                        if (janela.$('imgAltEndUsu').style.display=="none") {
                                        janela.$('imgIncEndUsu').show();
                                        janela.$('imgAltEndUsu').hide();
                                        } else {
                                            janela.$('imgIncEndUsu').hide();
                                            janela.$('imgAltEndUsu').show();
                                        }
                                        janela.$('btIncluirEndUsuario').show();
                                    }
                                    janela.desabilitaCamposEndereco();

                                }
                            }
                            top.frameApp.$('imgClose_divPopupTI').click();
                            top.frameApp.$('divPopupTI').hide();
                            if ( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
                        },
                        onFailure: function(e) {
                            alert("[Failure] "+e.description);
                            if ( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
                        }, onException: function(transport, e) {
                            alert("[Exception] "+e.description+"\n"+transport);
                            if ( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();

                        }
                    });
                }
            }

            function verificaNumCEP(numCep) {
                var ldone = false;
                if (numCep!=null && numCep!="") {
                    if (numCep.length<8) {
                        alert("Número de CEP inválido!");
                        $('nrCEP').value = "";
                        $('nrCEP').focus();
                    } else if (numCep.length==9 && numCep.indexOf("-")!=5) {
                        alert("Número de CEP inválido!");
                        $('nrCEP').value = "";
                        $('nrCEP').focus();
                    } else {
                        ldone = true;
                    }
                }
                return ldone;
            }

            onload = function() {

                if (top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();

                var janela = window.top.frameApp;

                if ((janela.inTipoPessoa && janela.inTipoPessoa == "PF")
                        && janela.bt01.className=="abaSelected") {
                    $('nrCEP').value           = janela.$('nrCEPCli').value;
                    $('dsLogradouro').value    = janela.$('nmLogradouroCli').value;
                    $('dsLocalidade').value    = janela.$('nmMunicipioCli').value;
                    $('idUFSelecionado').value = janela.$('idUFEnderecoCli').value;

                } else if ((janela.inTipoPessoa && janela.inTipoPessoa == "PJ")
                            && (janela.bt01 && janela.bt01.className == "abaSelected")) {
                    $('nrCEP').value           = janela.$('nrCEPPJ').value;
                    $('dsLogradouro').value    = janela.$('nmLogradouroPJ').value;
                    $('dsLocalidade').value    = janela.$('nmMunicipioPJ').value;
                    $('idUFSelecionado').value = janela.$('idUFEnderecoPJ').value;

                } else if (janela.bt03 && janela.bt03.className == "abaSelected") {
                    $('nrCEP').value           = janela.$('nrCEPUsu').value;
                    $('dsLogradouro').value    = janela.$('nmLogradouroUsu').value;
                    $('dsLocalidade').value    = janela.$('nmMunicipioUsu').value;
                    $('idUFSelecionado').value = janela.$('idUFEnderecoUsu').value;
                }

                if (verificaNumCEP($F('nrCEP'))) {

                    if (!$F('nrCEP').blank()) {
                        $('nrCEP').value = $F('nrCEP').replace("-","");
                        loadEnderecos();
                    }
                } else {

                    if ($F('dsLogradouro')!="" && $F('dsLocalidade')!="" && ($F('idUFSelecionado')!="0" || $F('idUFSelecionado')!="")) {
                        loadEnderecos();
                    } else {
                        <% if (!"FORMDINAMICO".equals(request.getAttribute("origem"))) { %>
                        alert("A pesquisa deve ser realizada através do CEP ou do Logradouro, Município e UF");
                        <% } %>
                    }
                }

            }

        -->
    </script>
    <script language="VBScript">
        function messageBox(pergunta)
            messageBox = msgbox(pergunta, 4, "Vivo Net")
        end function
    </script>
</head>
<body bgcolor="#ededed">
<form id="formPesqEnd" action="pesquisarEndereco.do" onsubmit="return false;" style="margin:0px;">
    <table width="700" align="center" height="298" cellpadding="0" cellspacing="10">
        <tr>
            <td valign="top">
                <table width="700" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="73">Logradouro:</td>
                        <td width="391"><input type="text" maxlength="255" name="dsLogradouro" id="dsLogradouro" style="width:350px;"/></td>
                        <td width="53">Bairro:</td>
                        <td width="183"><input type="text" maxlength="255" name="dsBairro" id="dsBairro" style="width:180px;"/></td>
                    </tr>
                    <tr><td height="5"></td></tr>
                    <tr>
                        <td>Município:</td>
                        <td>
                            <input type="text" maxlength="255" name="dsLocalidade" id="dsLocalidade" style="width:223px;" />
                            &nbsp;&nbsp;&nbsp;&nbsp;CEP:&nbsp;&nbsp;
                            <input type="text" style="width:80px;" name="nrCEP" id="nrCEP" onkeypress="checaCEP(this);" maxlength="9"/>
                        </td>
                        <td>UF:</td>
                        <td style="padding-left:3px;">
                            <html:select name="Form" property="pesquisaEnderecoVO.filtroPesquisa.idUFSelecionado" styleId="idUFSelecionado">
                                <option value="">--</option>
                                <html:optionsCollection property="listaUFArray" name="list" label="sgUF" value="idUF" />
                            </html:select>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <img id="btPesqEnd" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;" onClick="loadEnderecos();"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="10"></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <div id="tabListaEndereco" style="width:695px;height:210px;overflow:hidden;"></div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
</html>