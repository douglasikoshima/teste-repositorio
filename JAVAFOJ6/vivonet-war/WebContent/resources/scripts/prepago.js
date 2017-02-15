var clientePesquisado = null;
var inAlteracaoCliente = false;
var idLinhaTelefonica;
var cdTipoLinha = '';
var idPessoa = '';
var ttPesquisaInicial = false;  // Vari�vel booleana para identificar realiza��o de pesquisa (clique no bot�o pesquisar) em Troca de Titularidade.
var cdUFEndPrincipal = '';
var ID_TIPO_INSCRICAO_IE = '';
var IN_PESQUISA_ENDERECO_BASE_FO = true;
var maxCaracteres = 60;

function selecionaFormulario(tpPessoa) {
    try {
        hideAllDivs();
        $('dvRodape').show();
        inTipoPessoa = tpPessoa;
        if (tpOperacao!="A") {
            limparForms();
            limparDadosEndereco();
            if (inTipoPessoa=="PJ") {
                abaOcultar(btAba, bt02, true);
                displayClientePJ(true);
                $('btImgAvancar').hide();
            } else if (inTipoPessoa=="PF") {
                abaOcultar(btAba, bt02, false);
                displayClientePF(true);
                $('btImgAvancar').show();
            } else {
                return false;
            }
            $('btImgGravar').hide();
            enableAllTabs();
            disableTabUsuario();
            abaSelected(btAba, bt01);
        }
        if (inTipoPessoa == 'PJ' && $('idTipoInscricaoA').options[$('idTipoInscricaoA').selectedIndex].text.toUpperCase() == 'ISENTO') {
            $('nrInscricaoA').readOnly = true;
            $('nrInscricaoA').style.visibility = 'hidden';
        }
    } catch(e) {
        alert(e.description);
    }
}

function limparForms() {
    clientePesquisado = null;
    $('fPrePago').reset();
    limparFormCliente();
    limparFormUsuario();
}

function limparFormCliente() {
    $('formClientePF').reset();
    $('formDadosCliente').reset();
    $('formEndCliente').reset();
    $('formPJ').reset();
    $('formEndPJ').reset();
}

function limparFormUsuario() {
    $('formUsuarioPF').reset();
    $('formDadosUsuario').reset();
    $('formEndUsuario').reset();
}

function displayClientePF(inShow) {
    if (inShow) {
        $('dvFormCliente').show();
        $('dvFormEndCliente').show();
        loadEnderecos(inTipoPessoa, "C");
    } else {
        $('dvFormCliente').hide();
        $('dvFormEndCliente').hide();
    }
}

function displayUsuarioPF(inShow) {
    if (inShow) {
        $('dvFormUsuario').show();
        $('dvFormEndUsuario').show();
        loadEnderecos(inTipoPessoa, "U");
    } else {
        $('dvFormUsuario').hide();
        $('dvFormEndUsuario').hide();
    }
}

function displayClientePJ(inShow) {
    if (inShow) {
        $('dvFormPJ').show();
        $('dvFormEndPJ').show();
        loadEnderecos(inTipoPessoa, "C");
    } else {
        $('dvFormPJ').hide();
        $('dvFormEndPJ').hide();
    }
}

function hideAllDivs() {
    $('dvFormCliente').hide();
    $('dvFormUsuario').hide();
    $('dvFormEndCliente').hide();
    $('dvFormEndUsuario').hide();
    $('dvFormEndPJ').hide();
    $('dvDadosCliente').hide();
    $('dvDadosUsuario').hide();
    $('dvFormPJ').hide();
    $('btImgGravar').hide();
    $('btImgAvancar').hide();
}

function carregaAba(nmAba) {
    try{
        hideAllDivs();
        if (inTipoPessoa=="") return false;
        if (nmAba=="C") {
            if (inTipoPessoa=="PF") {
                displayClientePF(true);
                $('btImgAvancar').show();
                if (inIncluiEndPC>0) {
                    $('btImgGravar').show();
                } else {
                    $('btImgGravar').hide();
                }
            } else if (inTipoPessoa=="PJ") {
                displayClientePJ(true);
                    if ($F('inUsuarioPJ')=="0") {
                    $('btImgAvancar').show();
                    } else if ($F('inUsuarioPJ')=="1") {
                    $('btImgAvancar').hide();
                }
                    if (inIncluiEndPJ>0) {
                    $('btImgGravar').show();
                } else {
                    $('btImgGravar').hide();
            }
            }
        } else if (nmAba=="CD") {
            $('dvDadosCliente').show();
            if (!document.getElementById("bt03").disabled) {
                $('btImgAvancar').show();
            } else {
                $('btImgAvancar').hide();
            }
                if (inIncluiEndPC>0) {
                $('btImgGravar').show();
            } else {
                $('btImgGravar').hide();
            }
        } else if (nmAba=="U") {
            displayUsuarioPF(true);
            if (!document.getElementById("bt04").disabled) {
                $('btImgAvancar').show();
            } else {
                $('btImgAvancar').hide();
            }
            if (inTipoPessoa=="PF") {
                if (inIncluiEndPC>0) {
                    $('btImgGravar').show();
            } else {
                    $('btImgGravar').hide();
                }
            } else if (inTipoPessoa=="PJ") {
                if (inIncluiEndPJ>0) {
                    $('btImgGravar').show();
                } else {
                    $('btImgGravar').hide();
                }
            }
        } else if (nmAba=="UD") {
            $('dvDadosUsuario').show();
            $('btImgAvancar').hide();
            if (inTipoPessoa=="PF") {
                if (inIncluiEndPC>0) {
                    $('btImgGravar').show();
                } else {
                    $('btImgGravar').hide();
                }
            } else if (inTipoPessoa=="PJ") {
                if (inIncluiEndPJ>0) {
                    $('btImgGravar').show();
                } else {
                    $('btImgGravar').hide();
        }
            }
        }
    }catch(e) {
        alert(e.description);
    }
}

function loadAbaInicio() {
    carregaAba("C");
    if (inTipoPessoa=="PJ")
        abaOcultar(btAba, bt02, true);
    abaSelected(btAba, bt01);
}

function trataRG(obj) {
    if (bt01.className=="abaSelected") { //Tratamento do documento RG para Cliente
        var combo01 = $('idTipoDocumentoACli');
        var combo02 = $('idTipoDocumentoBCli');
        if (obj.name==combo01.name) {
            verificaDocumento(obj, combo02, "C", 1);
            removeStrEspecial($('nrDocumentoACli'));
        } else if (obj.name==combo02.name) {
            verificaDocumento(obj, combo01, "C", 2);
            removeStrEspecial($('nrDocumentoBCli'));
        }
    } else if (bt03.className=="abaSelected") { //Tratamento do documento RG para Usuario
        var combo01 = $('idTipoDocumentoAUsu');
        var combo02 = $('idTipoDocumentoBUsu');
        if (obj.name==combo01.name) {
            verificaDocumento(obj, combo02, "U", 1);
        } else if (obj.name==combo02.name) {
            verificaDocumento(obj, combo01, "U", 2);
        }
    }
}

function verificaDocumento(objA, objB, tipo, nDiv) {
    if (getTextBySelect(objA)=="RG") {
        if (getTextBySelect(objB)=="RG") {
            alert("Um �nico Documento de Identidade (RG) pode ser selecionado.");
            objA.value = '0';
            objA.selectedIndex = 0;
        } else {
            if (tipo=="C") {
                if (nDiv==1) {
                    $('docExtras1Cli').show();
                    $('docExtras2Cli').hide();
                } else if (nDiv==2) {
                    $('docExtras1Cli').hide();
                    $('docExtras2Cli').show();
                }
            } else if (tipo=="U") {
                if (nDiv==1) {
                    $('docExtras1Usu').show();
                    $('docExtras2Usu').hide();
                } else if (nDiv==2) {
                    $('docExtras1Usu').hide();
                    $('docExtras2Usu').show();
                }
            }
        }
    } else {
        if (objA.value!='0' || objB.value!='0') {
            if (getTextBySelect(objA)==getTextBySelect(objB)) {
                alert("N�o � possivel selecionar duas vezes o mesmo tipo de documento!");
                objA.value = '0';
                objA.selectedIndex = 0;
            } else {
                if (tipo=="C") {
                    if (nDiv==1) {
                        $('docExtras1Cli').hide();
                    } else if (nDiv==2) {
                        $('docExtras2Cli').hide();
                    }
                } else if (tipo=="U") {
                    if (nDiv==1) {
                        $('docExtras1Cli').hide();
                    } else if (nDiv==2) {
                        $('docExtras2Cli').hide();
                    }
                }
            }
        } else {
            if (tipo=="C") {
                if (nDiv==1) {
                    $('docExtras1Cli').hide();
                } else if (nDiv==2) {
                    $('docExtras2Cli').hide();
                }
            } else if (tipo=="U") {
                if (nDiv==1) {
                    $('docExtras1Usu').hide();
                } else if (nDiv==2) {
                    $('docExtras2Usu').hide();
                }
            }
        }
    }
}

function verificaDocumentoCPF(obj,objSel) {
    if (getTextBySelect(objSel)=="CPF") {
        if (obj.value!="") {
            if (!validaCPF(obj.value)) {
                alert("N�mero de CPF inv�lido!");
                obj.value = "";
                obj.focus();
                return false;
            }
            return true;
        }
    }
}

function verificaDocumentoCNPJ(obj) {
    if (obj.value!="") {
        if (!validaCNPJ(obj.value)) {
            alert("N�mero de CNPJ inv�lido!");
            obj.value = "";
            obj.focus();
            return false;
        }
        return true;
    }
}

function verificaNumCEP(obj) {
    if (obj!=null && (obj.value!="" && obj.value!=null)) {
        if (obj.value.length<8) {
            alert("N�mero de CEP inv�lido!");
            obj.value = "";
            obj.focus();
            return false;
        } else if (obj.value.length==9 && obj.value.indexOf('-')!=5) {
            alert("N�mero de CEP inv�lido!");
            obj.value = "";
            obj.focus();
            return false;
        }
        return true;
    }
    return false;
}

function verificaEndEmail(obj) {
    if (obj.value!="") {
        var reEmail = /^[\w-]+(\.[\w-]+)*@(([\w-]{2,63}\.)+[A-Za-z]{2,6}|\[\d{1,3}(\.\d{1,3}) {3}\])$/;
        if (!reEmail.test(obj.value)) {
            alert("Endere�o de e-Mail inv�lido!");
            obj.value = "";
            obj.focus();
            return false;
        }
        return true;
    }
}

function loadEnderecos(tpCliente, tpVinculo) {

    new Ajax.Request('tabListaEnderecos.do', {
        method: 'get',
        parameters: {
            inTipoPessoa: tpCliente, limit: 2,
            inTipoRelaciona: tpVinculo, limit: 1
        },
        contentType: 'text/xml',
        onComplete: function(r) {
            if (tpCliente=="PF" && tpVinculo=="C") {
                $('tabListaEndCliente').update(r.responseText);
            } else if (tpCliente=="PJ" && tpVinculo=="C") {
                $('tabListaEndPJ').update(r.responseText);
            } else if (tpVinculo=="U") {
                $('tabListaEndUsuario').update(r.responseText);
            }
            if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
        },
        onFailure: function(e) {alert("[Failure] "+e+"\n");},
        onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport);}
    });
    if (inTipoPessoa=="PF") {
        if (inIncluiEndPC>0) {
            $('btImgGravar').show();
        } else {
            $('btImgGravar').hide();
        }
    } else if (inTipoPessoa=="PJ") {
        if (inIncluiEndPJ>0) {
            $('btImgGravar').show();
        } else {
            $('btImgGravar').hide();
        }
    }
}

function onChangeUser(obj) {
    if (obj.value=="0") {
        enableTabUsuario();
        $('btImgAvancar').show();
        if (inTipoPessoa=="PJ") {
            if (inIncluiEndPJ>0) {
                $('btImgGravar').show();
            } else {
                $('btImgGravar').hide();
            }
        } else if (inTipoPessoa=="PF") {
            if (inIncluiEndPC>0) {
                $('btImgGravar').show();
            } else {
                $('btImgGravar').hide();
            }
            }
    } else if (obj.value=="1") {
        disableTabUsuario();
        if (inTipoPessoa=="PJ") {
            if (inIncluiEndPJ>0) {
                $('btImgAvancar').hide();
                $('btImgGravar').show();
            } else {
                $('btImgAvancar').hide();
                $('btImgGravar').hide();
            }
        } else {
            $('btImgAvancar').show();
            if (inIncluiEndPC>0) {
                $('btImgGravar').show();
            } else {
                $('btImgGravar').hide();
            }
        }
    }
}

function validarLinha() {
    //var numLinha = $F("nrLinha").replace("(","").replace(")","").replace("-","");
    //checaInteiro($("nrLinha"));
    var numLinha = $F("nrLinha");
    if (window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();
    new Ajax.Request('validarLinha.do', {
        method: 'get',
        parameters: {
            nrLinha: numLinha
        },
        contentType: 'text/xml',
        onComplete: function(originalRequest) {
            var xmlString = originalRequest.responseText;
            oXml       = new ActiveXObject("Microsoft.XMLDOM");
            oXml.async = false;
            var regExp = new RegExp('&', 'gi') ;
            xmlString  = xmlString.replace(regExp,"&amp;");
            oXml.loadXML(xmlString);
            if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage") != null) {
                friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                alert(friendlyMessage);
            } else {
                var nrProtocolo = oXml.selectSingleNode("/PrePagoVO/nrProtocolo").text;
                $('tbPessNrLinha').innerHTML = oXml.selectSingleNode("/PrePagoVO/nrLinha").text;
                $('tbPessCdSeguranca').innerHTML = oXml.selectSingleNode("/PrePagoVO/cdSeguranca").text;
                $('tbPesquisa').hide();
                $('tbPessoa').show();
                alert('Protocolo: '+ nrProtocolo);
            }
            if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
        },
        onFailure: function(e) {alert("[Failure] "+e.description);},
        onException: function(transport, e) {alert("[Exception] "+e.description+"\n");}
    });
}

function validarCNAE() {
    var nrCNAE = $F("nrCNAEA");
    if (nrCNAE!="") {
        new Ajax.Request('validarCNAE.do', {
            method: 'get',
            parameters: {
                nrCNAE: nrCNAE, limit: 10
            },
            contentType: 'text/xml',
            onComplete: function(originalRequest) {
                var xmlString = originalRequest.responseText;
                oXml       = new ActiveXObject("Microsoft.XMLDOM");
                oXml.async = false;
                var regExp = new RegExp('&', 'gi') ;
                xmlString  = xmlString.replace(regExp,"&amp;");
                oXml.loadXML(xmlString);
                if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage") != null) {
                    friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                    exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                    alert(friendlyMessage);
                    $("nrCNAEA").value = "";
                    $("nrCNAEA").focus();
                } else {
                    $("dsCNAEA").innerHTML = oXml.selectSingleNode("/CNAEVO/dsCNAE").text;
                }
            },
            onFailure: function(e) {alert("[Failure] "+e.description);},
            onException: function(transport, e) {alert("[Exception] "+e.description+"\n");}
        });
    }
}

function enableTabUsuario() {
    if (document.getElementById("bt03")) {
        document.getElementById("bt03").disabled = false;
    }
    if (document.getElementById("bt04")) {
        document.getElementById("bt04").disabled = false;
    }
}

function disableTabUsuario() {
    if (document.getElementById("bt03")) {
        document.getElementById("bt03").disabled = true;
    }
    if (document.getElementById("bt04")) {
        document.getElementById("bt04").disabled = true;
    }
}

function enableAllTabs() {
    if (document.getElementById("bt01")) {
        document.getElementById("bt01").disabled = false;
    }
    if (document.getElementById("bt02")) {
        document.getElementById("bt02").disabled = false;
    }
    if (document.getElementById("bt03")) {
        document.getElementById("bt03").disabled = false;
    }
    if (document.getElementById("bt04")) {
        document.getElementById("bt04").disabled = false;
    }
}

function disableAllTabs() {
    if (document.getElementById("bt01")) {
        document.getElementById("bt01").disabled = true;
    }
    if (document.getElementById("bt02")) {
        document.getElementById("bt02").disabled = true;
    }
    if (document.getElementById("bt03")) {
        document.getElementById("bt03").disabled = true;
    }
    if (document.getElementById("bt04")) {
        document.getElementById("bt04").disabled = true;
    }
}

function pesquisarEndereco(obj,tpPes) {
    var oCP = null;
    var cep = "";
    var log = "";
    var cid = "";
    var est = "";
    if (inTipoPessoa=="PF" && bt01.className=="abaSelected") {
        oCP = $('nrCEPCli');
        cep = $F('nrCEPCli');
        log = $F('nmLogradouroCli');
        cid = $F('nmMunicipioCli');
        est = $F('idUFEnderecoCli');
    } else if (inTipoPessoa=="PJ" && bt01.className=="abaSelected") {
        oCP = $('nrCEPPJ');
        cep = $F('nrCEPPJ');
        log = $F('nmLogradouroPJ');
        cid = $F('nmMunicipioPJ');
        est = $F('idUFEnderecoPJ');
    } else if (bt03.className=="abaSelected") {
        oCP = $('nrCEPUsu');
        cep = $F('nrCEPUsu');
        log = $F('nmLogradouroUsu');
        cid = $F('nmMunicipioUsu');
        est = $F('idUFEnderecoUsu');
    }
    if (verificaNumCEP(oCP)) {
        if (cep!="") {
            oCP.value = cep.replace("-","");
            if (window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();
            showPopup("Busca CEP", 725, 290, 120, "loadPesquisaEndereco.do");
        }
    } else {
        if (log!="" && cid!="" && est!="0" && est!="") {
            if (window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();
            showPopup("Busca CEP", 725, 290, 120, "loadPesquisaEndereco.do");
        } else {
            alert("A pesquisa deve ser realizada atrav�s do CEP ou do Logradouro, Munic�pio e UF");
        }
    }
}

function pesquisa(objA, objB) {

    var idTipoDocumento = '';
    if (objA.disabled) {
        objA.disabled = false;
    }
    inAlteracaoCliente = false;

    if (objA.tagName.toUpperCase() == 'SELECT') {
        var cdTipoDocumento = objA.options[objA.selectedIndex].text;
    } else {
        var cdTipoDocumento = 'CNPJ';
    }

    var tpRelaciona     = '';
    if (inTipoPessoa=="PF" && bt01.className=="abaSelected") {
        if (objA.value=="0") {
            alert("Por favor, selecione um tipo de documento para realizar a pesquisa.");
            if (getTextBySelect(objA) == 'CPF') {
                objA.disabled = true;
            }
            return false;
        }
        if (objB.value=="") {
            alert("Por favor, digite o n�mero do documento para realizar a pesquisa.");
            if (getTextBySelect(objA) == 'CPF') {
                objA.disabled = true;
            }
            return false;
        }
        idTipoDocumento = objA.value;
        nrDocumento     = objB.value.replace(".","").replace(".","").replace("-","");
        tpRelaciona     = (bt03.className=="abaSelected")?"U":(bt01.className=="abaSelected")?"C":"";

    } else if (inTipoPessoa=="PJ" && bt01.className=="abaSelected") {

        if (objA.value=="") {
            alert("Por favor, digite o n�mero do CNPJ para realizar a pesquisa.");
            return false;
        }
        idTipoDocumento = "";
        nrDocumento     = objA.value.replace(".","").replace(".","").replace("/","").replace("-","");
        tpRelaciona     = (bt03.className=="abaSelected")?"U":(bt01.className=="abaSelected")?"C":"";

    } else if (bt03.className=="abaSelected") {

        if (objA.value=="0") {
            alert("Por favor, selecione um tipo de documento para realizar a pesquisa.");
            return false;
        }

        if (objB.value=="") {
            alert("Por favor, digite o n�mero do documento para realizar a pesquisa.");
            return false;
        }

        idTipoDocumento = objA.value;
        nrDocumento     = objB.value.replace(".","").replace(".","").replace("-","");
        tpRelaciona     = (bt03.className=="abaSelected")?"U":(bt01.className=="abaSelected")?"C":"";

    }

    if (window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();

    new Ajax.Request('pesquisaCliente.do', {
        method: 'get',
        parameters: {
            tpPes: inTipoPessoa, limit: 2,
            tpRel: tpRelaciona, limit: 1,
            tpDoc: idTipoDocumento, limit: 10,
            nrDoc: nrDocumento, limit: 18
        },
        contentType: 'text/xml',
        onComplete: function(r) {

            var xmlString = r.responseText;
            var oXml      = new ActiveXObject("microsoft.xmldom");
            oXml.async    = false;
            var regExp    = new RegExp ('&', 'gi') ;
            xmlString     = xmlString.replace(regExp,"&amp;");
            oXml.loadXML(xmlString);

            var dom = parseXml(xmlString);
            var jsonString = xml2json(dom, '');
            var jsonObj = jsonString.evalJSON();
            clientePesquisado = jsonObj;

            if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage") != null) {

                exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                clientePesquisado = null;
                alert(friendlyMessage);

                if (friendlyMessage.indexOf('expirada') >= 0) {
                    top.location.href = "/FrontOfficeWeb/begin.do";
                }

                // Cliente
                if (bt01.className=="abaSelected") {

                    ttPesquisaInicial = true;
                    limparDadosEndereco();
                    limparFormCliente();

                } else {

                    // Usu�rio
                    if (bt03.className == "abaSelected") {
                        limparFormUsuario();
                    }
                }

                if (inTipoPessoa == 'PJ' && bt01.className == 'abaSelected') {
                    objA.value = nrDocumento;
                } else {
                    objA.value = idTipoDocumento;
                    objB.value = nrDocumento;
                    if (objA.options[objA.selectedIndex].text == 'CPF') {
                        checaCPF(objB);
                        objB.focus();
                        objB.select();
                    }
                }
                if (bt01.className == 'abaSelected') {
                    loadEnderecos(inTipoPessoa, "C");
                } else if (bt03.className == 'abaSelected') {
                    loadEnderecos(inTipoPessoa, "U");
                }

            } else {

                if ((jsonObj.PrePagoVO.PF
                        && jsonObj.PrePagoVO.PF.length != undefined
                        && jsonObj.PrePagoVO.PF.length > 1)
                    || (jsonObj.PrePagoVO.PJ
                        && jsonObj.PrePagoVO.PJ.length != undefined
                        && jsonObj.PrePagoVO.PJ.length > 1)) {

                    createNewPopUp('pesquisaCliente', 'Pesquisa de clientes', 790, 530, null, 'getListaClientes.do', true, null);

                } else

                // N�o foi encontrado nenhum cliente PF ou PJ
                if (!jsonObj.PrePagoVO.PF && !jsonObj.PrePagoVO.PJ) {

                    alert('Cadastro n�o encontrado.');
                    ttPesquisaInicial = true;
                    limparFormCliente();
                    objB.value = nrDocumento;
                    $('nmNomeCli').focus();
                    objB.focus();
                    clientePesquisado = null;
                    objA.disabled = false;

                } else {

                    if (inTipoPessoa == 'PF' && bt01.className == 'abaSelected') {
                        ttPesquisaInicial = true;
                        if (jsonObj.PrePagoVO.PF.Telefone) {
                            cdTipoLinha = jsonObj.PrePagoVO.PF.Telefone.idClassificacaoTipoLinha;
                        }
                        idPessoa =  jsonObj.PrePagoVO.PF.idPessoa;
                        setFormClientePFByVO(oXml);
                    } else if (inTipoPessoa == 'PJ' && bt01.className == 'abaSelected') {
                        ttPesquisaInicial = true;
                        if (jsonObj.PrePagoVO.PJ.Telefone) {
                            cdTipoLinha = jsonObj.PrePagoVO.PJ.Telefone.idClassificacaoTipoLinha;
                        }
                        idPessoa =  jsonObj.PrePagoVO.PJ.idPessoa;
                        setFormClientePJByVO(oXml);
                    } else if (bt03.className == 'abaSelected') {
                        setFormUsuarioByVO(oXml);
                        loadEnderecos(inTipoPessoa, 'U');
                    }

                }
            }
            if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
        },
        onFailure: function(e) {
            alert("[Failure] "+e.description);
            if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
        },
        onException: function(transport, e) {
            alert("[Exception] "+e.description+"\n"+transport.xml);
            if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
        }
    });
    checkInsEst($('idTipoInscricaoA'));
    return false;
}

function avancar() {
    if (bt01.className=="abaSelected") {
        if (inTipoPessoa=="PF") {
        abaSelected(btAba, bt02);
        carregaAba("CD");
        } else if (inTipoPessoa=="PJ") {
            if (!document.getElementById("bt03").disabled) {
                abaSelected(btAba, bt03);
                carregaAba("U");
            }
        }
    } else if (bt02.className=="abaSelected") {
        if (!document.getElementById("bt03").disabled) {
            abaSelected(btAba, bt03);
            carregaAba("U");
        }
    } else if (bt03.className=="abaSelected") {
        if (!document.getElementById("bt04").disabled) {
            abaSelected(btAba, bt04);
            carregaAba("UD");
        }
    } else if (bt04.className=="abaSelected") {
        abaSelected(btAba, bt01);
        carregaAba("C");
    }
    return false;
}

function validaForm() {

    var msgIniErro = "Por favor, preencha corretamente os sequintes campos: \n";
    var msgErro = "";
    var tipoDocADisabled = false;
    
    // Em troca de titularidade � obrigat�ria a realiza��o de pesquisa por documento.
    if ($('PrePago').select('[class="sessaoOperacoes"]')[0].innerText == "Troca de Titularidade"
            && !ttPesquisaInicial) {
    	
    	//TODO - Lucas Moraes 04/01/2016: Alteração de retirada do alerta aprovada pelo cliente. INC000001156829
    	//alert('Realiza��o de pesquisa por documento � obrigat�ria.');
        //return false;
    }

    if (inTipoPessoa=="PF") {
        if ($F('nmNomeCli').blank()) {
            msgErro += "Aba Cliente - Nome \n";
        }
        if ($('idSexoACli').selectedIndex == 0) {
            msgErro += "Aba Cliente - Sexo \n";
        }
        if ($('idTipoDocumentoACli').disabled) {
            tipoDocADisabled = true;
            $('idTipoDocumentoACli').disabled = false;
        }
        if ($('idTipoDocumentoACli').options[$('idTipoDocumentoACli').selectedIndex].text != "CPF"
                && $('idTipoDocumentoBCli').options[$('idTipoDocumentoBCli').selectedIndex].text != "CPF") {
            msgErro += "Aba Cliente - Preenchimento de CPF � obrigat�rio. \n";
        }
        if ($('idTipoDocumentoACli').options[$('idTipoDocumentoACli').selectedIndex].text == "CPF"
                && !validaCPF($F('nrDocumentoACli'))) {
            msgErro += "Aba Cliente - CPF inv�lido. \n";
        }
        if ($('idTipoDocumentoBCli').options[$('idTipoDocumentoBCli').selectedIndex].text == "CPF"
                && !validaCPF($F('nrDocumentoBCli'))) {
            msgErro += "Aba Cliente - CPF inv�lido. \n";
        }
        if (tipoDocADisabled) {
            $('idTipoDocumentoACli').disabled = true;
        }
        if ($('idTipoDocumentoACli').selectedIndex == 0) {
            msgErro += "Aba Cliente - Tipo de Documento \n";
        }
        if ($F('nrDocumentoACli').blank()) {
            msgErro += "Aba Cliente - N�mero do Documento \n";
        }
        if ($('idTipoDocumentoACli').options[$('idTipoDocumentoACli').selectedIndex].text == 'RG') {
            if ($('idUFDocumentoCliEx1').selectedIndex == 0) {
                msgErro += "Aba Cliente - UF de expedi��o do Documento de Identifica��o RG \n";
            }
            if ($F('dsOrgEmisDocSelCliEx1').blank()) {
                msgErro += "Aba Cliente - Org�o expedidor do Documento de Identifica��o RG \n";
            }
            if ($F('dtExpDocSelCliEx1').blank()) {
                msgErro += "Aba Cliente - Data de expedi��o do Documento de Identifica��o RG \n";
            }
        }
        if ($('idTipoDocumentoBCli').selectedIndex == 0 && !$F('nrDocumentoBCli').blank()) {
            msgErro += "Aba Cliente - Tipo do Segundo Documento \n";
        }
        if ($('idTipoDocumentoBCli').selectedIndex != 0 && $F('nrDocumentoBCli').blank()) {
            msgErro += "Aba Cliente - N�mero do Segundo Documento \n";
        }
        if ($('idTipoDocumentoBCli').options[$('idTipoDocumentoBCli').selectedIndex].text == 'RG') {
            if ($F('nrDocumentoBCli').blank()) {
                msgErro += "Aba Cliente - N�mero do Documento de Identifica��o RG \n";
            }
            if ($('idUFDocumentoCliEx2').selectedIndex == 0) {
                msgErro += "Aba Cliente - UF de expedi��o do Documento de Identifica��o RG \n";
            }
            if ($F('dsOrgEmisDocSelCliEx2').blank()) {
                msgErro += "Aba Cliente - Org�o expedidor do Documento de Identifica��o RG \n";
            }
            if ($F('dtExpDocSelCliEx2').blank()) {
                msgErro += "Aba Cliente - Data de expedi��o do Documento de Identifica��o RG \n";
            }
        }
        if ($F('dtNascimentoACli').blank()) {
            msgErro += "Aba Cliente - Data de Nascimento \n";
        }
        if ($('inSMSPF')) {
            if ($F('inSMSPF')=="") {
                msgErro += "Aba Cliente - Campo Sele��o Receber Mensagem da Vivo\n";
            }
        }
        if ($('idTipoTelefoneACli').selectedIndex != 0 && $F('nrTelefone1Cli').blank()) {
            msgErro += "Aba Outros Dados - N�mero de Telefone para o 1� Tipo Telefone Selecionado \n";
        }
        if ($('idTipoTelefoneBCli').selectedIndex != 0 && $F('nrTelefone2Cli').blank()) {
            msgErro += "Aba Outros Dados - N�mero de Telefone para o 2� Tipo Telefone Selecionado \n";
        }
        if ($('idTipoTelefoneCCli').selectedIndex != 0 && $F('nrTelefone3Cli').blank()) {
            msgErro += "Aba Outros Dados - N�mero de Telefone para o 3� Tipo Telefone Selecionado \n";
        }
        if ($('idTipoTelefoneACli').selectedIndex == 0 && !$F('nrTelefone1Cli').blank()) {
            msgErro += "Aba Outros Dados - 1� Tipo Telefone para o N�mero de Telefone preenchido. \n";
        }
        if ($('idTipoTelefoneBCli').selectedIndex == 0 && !$F('nrTelefone2Cli').blank()) {
            msgErro += "Aba Outros Dados - 2� Tipo Telefone para o N�mero de Telefone preenchido. \n";
        }
        if ($('idTipoTelefoneCCli').selectedIndex == 0 && !$F('nrTelefone3Cli').blank()) {
            msgErro += "Aba Outros Dados - 3� Tipo Telefone para o N�mero de Telefone preenchido. \n";
        }
        if ($('idTipoTelefoneACli').selectedIndex != 0 && !$F('nrTelefone1Cli').blank() && $F('nmContato1Cli').blank()) {
            msgErro += "Aba Outros Dados - 1� Nome do Contato para o N�mero de Telefone preenchido. \n";
        }
        if ($('idTipoTelefoneBCli').selectedIndex != 0 && !$F('nrTelefone2Cli').blank() && $F('nmContato2Cli').blank()) {
            msgErro += "Aba Outros Dados - 2� Nome do Contato para o N�mero de Telefone preenchido. \n";
        }
        if ($('idTipoTelefoneCCli').selectedIndex != 0 && !$F('nrTelefone3Cli').blank() && $F('nmContato3Cli').blank()) {
            msgErro += "Aba Outros Dados - 3� Nome do Contato para o N�mero de Telefone preenchido. \n";
        }

        if ($('inUsuarioPFCli').selectedIndex == 0) {
            msgErro += "Aba Cliente - Cliente � Usuario da Linha \n";
        }
        if (inIncluiEndPC == 0) {
            msgErro += "Aba Cliente - Deve Incluir pelo menos 1 (um) endere�o de Cliente \n";
        }
        if ($('inUsuarioPFCli').value == '0') {
            msgErro = validaUsuario(msgErro);
        }

    } else if (inTipoPessoa == 'PJ') {

        if ($F('nrCNPJA').blank()) {
            msgErro += "Aba Cliente - N�mero de CNPJ \n";
        }
        if ($F('nmFantasiaA').blank()) {
            msgErro += "Aba Cliente - Nome Fantasia \n";
        }
        if ($F('nmRazaoSocialA').blank()) {
            msgErro += "Aba Cliente - Nome da Raz�o Social \n";
        }
        if ($F('nrCNAEA').blank()) {
            msgErro += "Aba Cliente - N�mero CNAE \n";
        }

        // Caso o usu�rio n�o tenha selecionado a op��o "ISENTO" em Inscri��o.
        if ($('idTipoInscricaoA').selectedIndex == 0) {
            if ($F('nrInscricaoA').blank() || $F('nrInscricaoA').toUpperCase() == 'ISENTO') {
                msgErro += "Aba Cliente - N�mero de Inscri��o Estadual \n";
            } else if (cdUFEndPrincipal == '') {
                msgErro += "Aba Cliente - Um dos endere�os deve ser identificado como principal. \n";
            } else if ($('idTipoInscricaoA').selectedIndex == 0) {
                if (!CheckIE($F('nrInscricaoA').gsub('[^0-9]',''), cdUFEndPrincipal)) {
                    msgErro += "Aba Cliente - N�mero de Inscri��o Estadual inv�lido \n";
                }
            }
        }

        if ($('idClassTributariaA').selectedIndex == 0) {
            msgErro += "Aba Cliente - Tipo de Classifica��o Tribut�ria \n";
        }
        if ($('idClassEmpresaA').selectedIndex == 0) {
            msgErro += "Aba Cliente - Tipo de Classifica��o de Empresa \n";
        }
        if ($F('dtFundacaoA').blank()) {
            msgErro += "Aba Cliente - Data de Funda��o \n";
        }
        if ($F('nrCCMA').blank()) {
            msgErro += "Aba Cliente - N�mero do CCM \n";
        }
        if ($('idTipoTelefonePJA').selectedIndex == 0) {
            msgErro += "Aba Cliente - Tipo de Telefone de Contato \n";
        }
        if ($F('nrTelefonePJA').blank()) {
            msgErro += "Aba Cliente - N�mero de Telefone de Contato \n";
        }
        if ($F('nmContatoPJA').blank()) {
            msgErro += "Aba Cliente - Nome do Contato Telef�nico \n";
        }

        if ($('inSMSPJ')) {
            if ($('inSMSPJ').selectedIndex == 0) {
                msgErro += "Aba Cliente - Campo de sele��o: Receber mensagens da Vivo\n";
            }
        }

        if ($('inUsuarioPJ').selectedIndex == 0) {
            msgErro += "Aba Cliente - Cliente � Usuario da Linha \n";
        }
        if (inIncluiEndPJ == 0) {
            msgErro +="Aba Cliente - Inclua ao menos um endere�o de Pessoa Jur�dica \n";
        }
        if ($('inUsuarioPJ').value == "0") {
            msgErro = validaUsuario(msgErro);
        }

    }

    if (msgErro != '') {
        alert(msgIniErro+msgErro);
        msgErro = "";

    } else if (msgErro == '' && inAlteracaoClientePesquisado()) {
        if (cdTipoLinha == 'PRE') {
            if (confirm('Os dados cadastrais atuais tamb�m ser�o atualizados com os dados desta nova linha ' + $('tbPessNrLinha').innerText + '. Deseja continuar?')) {
                $('idTipoDocumentoACli').disabled = false;
                gravar();
            }
        } else {
            $('idTipoDocumentoACli').disabled = false;
            gravar();
        }
    } else if (msgErro == '') {
        $('idTipoDocumentoACli').disabled = false;
        gravar();
    }

    return false;

}

function validaUsuario(msgErro) {
    if ($('nmNomeUsu').value=="") {
        msgErro += "Aba Usu�rio - Nome do Usuario \n";
    }
    if ($('idSexoAUsu').value=="" || $('idSexoAUsu').value=='0') {
        msgErro += "Aba Usu�rio - Sexo do Usuario \n";
    }
    if ($('idTipoDocumentoAUsu').value=="" || $('idTipoDocumentoAUsu').value=="0") {
        msgErro += "Aba Usu�rio - Tipo de Documento \n";
    }
    if ($('nrDocumentoAUsu').value=="") {
        msgErro += "Aba Usu�rio - N�mero de Documento \n";
    }
    if ($('idTipoDocumentoAUsu').options[$('idTipoDocumentoAUsu').selectedIndex].text=="RG") {
        if ($('idUFDocumentoUsuEx1').value=="" || $('idUFDocumentoUsuEx1').value=="0") {
            msgErro += "Aba Usu�rio - UF de expedi��o do Documento de Identifica��o RG \n";
        }
        if ($('dsOrgEmisDocSelUsuEx1').value=="") {
            msgErro += "Aba Usu�rio - Org�o expedidor do Documento de Identifica��o RG \n";
        }
        if ($('dtExpDocSelUsuEx1').value=="") {
            msgErro += "Aba Usu�rio - Data de expedi��o do Documento de Identifica��o RG \n";
        }
    }
    if (($('idTipoDocumentoBUsu').value=="0" || $('idTipoDocumentoBUsu').value=="") && $('nrDocumentoBUsu').value!="") {
        msgErro += "Aba Usu�rio - Tipo do Segundo Documento \n";
    }
    if ($('idTipoDocumentoBUsu').value!="0" && $('nrDocumentoBUsu').value=="") {
        msgErro += "Aba Usu�rio - N�mero do Segundo Documento \n";
    }
    if ($('idTipoDocumentoBUsu').options[$('idTipoDocumentoBUsu').selectedIndex].text=="RG") {
        if ($('nrDocumentoBUsu').value=="") {
            msgErro += "Aba Usu�rio - N�mero do Documento de Identifica��o RG \n";
        }
        if ($('idUFDocumentoUsuEx2').value=="" || $('idUFDocumentoUsuEx2').value=="0") {
            msgErro += "Aba Usu�rio - UF de expedi��o do Documento de Identifica��o RG \n";
        }
        if ($('dsOrgEmisDocSelUsuEx2').value=="") {
            msgErro += "Aba Usu�rio - Org�o expedidor do Documento de Identifica��o RG \n";
        }
        if ($('dtExpDocSelUsuEx2').value=="") {
            msgErro += "Aba Usu�rio - Data de expedi��o do Documento de Identifica��o RG \n";
        }
    }
    if ($('dtNascimentoAUsu').value=="") {
        msgErro += "Aba Usu�rio - Data de Nascimento \n";
    }
    if ($F('idTipoTelefoneAUsu')!="" && $F('idTipoTelefoneAUsu')!="0" && $F('nrTelefone1Usu')=="") {
        msgErro += "Aba Outros Dados Usu�rio - N�mero de Telefone para o 1� Tipo Telefone Selecionado \n";
    }
    if ($F('idTipoTelefoneBUsu')!="" && $F('idTipoTelefoneBUsu')!="0" && $F('nrTelefone2Usu')=="") {
        msgErro += "Aba Outros Dados Usu�rio - N�mero de Telefone para o 2� Tipo Telefone Selecionado \n";
    }
    if ($F('idTipoTelefoneCUsu')!="" && $F('idTipoTelefoneCUsu')!="0" && $F('nrTelefone3Usu')=="") {
        msgErro += "Aba Outros Dados Usu�rio - N�mero de Telefone para o 3� Tipo Telefone Selecionado \n";
    }
    if (($F('idTipoTelefoneAUsu')=="" || $F('idTipoTelefoneAUsu')=="0") && $F('nrTelefone1Usu')!="") {
        msgErro += "Aba Outros Dados Usu�rio - 1� Tipo Telefone para o N�mero de Telefone preenchido. \n";
    }
    if (($F('idTipoTelefoneBUsu')=="" || $F('idTipoTelefoneBUsu')=="0") && $F('nrTelefone2Usu')!="") {
        msgErro += "Aba Outros Dados Usu�rio - 2� Tipo Telefone para o N�mero de Telefone preenchido. \n";
    }
    if (($F('idTipoTelefoneCUsu')=="" || $F('idTipoTelefoneCUsu')=="0") && $F('nrTelefone3Usu')!="") {
        msgErro += "Aba Outros Dados Usu�rio - 3� Tipo Telefone para o N�mero de Telefone preenchido. \n";
    }
    if (($F('idTipoTelefoneAUsu')!="" || $F('idTipoTelefoneAUsu')!="0") && $F('nrTelefone1Usu')!="" && $F('nmContato1Usu')=="") {
        msgErro += "Aba Outros Dados Usu�rio - 1� Nome do Contato para o N�mero de Telefone preenchido. \n";
    }
    if (($F('idTipoTelefoneBUsu')!="" || $F('idTipoTelefoneBUsu')!="0") && $F('nrTelefone2Usu')!="" && $F('nmContato2Usu')=="") {
        msgErro += "Aba Outros Dados Usu�rio - 2� Nome do Contato para o N�mero de Telefone preenchido. \n";
    }
    if (($F('idTipoTelefoneCUsu')!="" || $F('idTipoTelefoneCUsu')!="0") && $F('nrTelefone3Usu')!="" && $F('nmContato3Usu')=="") {
        msgErro += "Aba Outros Dados Usu�rio - 3� Nome do Contato para o N�mero de Telefone preenchido. \n";
    }
    if (inIncluiEndPU==0 && !$('isUtilEndCliente').checked) {
        msgErro +="Aba Usu�rio - Deve Incluir pelo menos 1 (um) endere�o de Usu�rio";
    }
    return msgErro;
}

function gravar() {
    var f = $('fPrePago');
    var inUsuario = "";
    try {
        if (tpOperacao=="A") desbloqueiaCamposAlteracao(inTipoPessoa);
        if (inTipoPessoa=="PF") {
            inUsuario                   = $F('inUsuarioPFCli');
            f.inTipoPessoa.value        = "PF";
            f.idPessoaCli.value         = $F('idPessoaCli');
            f.nmPessoaCli.value         = $F('nmNomeCli');
            f.idSexoCli.value           = $F('idSexoACli');
            f.idDocumentoCliA.value     = $F('idDocumentoACli');
            f.idDocumentoCliB.value     = $F('idDocumentoBCli');
            f.idTipoDocumentoCliA.value = $F('idTipoDocumentoACli');
            f.idTipoDocumentoCliB.value = $F('idTipoDocumentoBCli');
            f.nrDocumentoCliA.value     = $F('nrDocumentoACli');
            f.nrDocumentoCliB.value     = $F('nrDocumentoBCli');
                if (tpOperacao!="A") f.inSMSCli.value            = $F('inSMSPF');

            if (getTextBySelect($('idTipoDocumentoACli'))=="RG") {
                f.inDocumentoRGCli.value    = '0';//Indica a posi��o dos dados de RG, se � para o primeiro documento ou se � para o segundo documento.
                f.idUFDocumentoCli.value    = $F('idUFDocumentoCliEx1');
                f.dsOrgaoEmissorCli.value   = $F('dsOrgEmisDocSelCliEx1');
                f.dtExpedicaoCli.value      = $F('dtExpDocSelCliEx1');

            } else if (getTextBySelect($('idTipoDocumentoBCli'))=="RG") {
                f.inDocumentoRGCli.value    = '1';//Indica a posi��o dos dados de RG, se � para o primeiro documento ou se � para o segundo documento.
                f.idUFDocumentoCli.value    = $F('idUFDocumentoCliEx2');
                f.dsOrgaoEmissorCli.value   = $F('dsOrgEmisDocSelCliEx2');
                f.dtExpedicaoCli.value      = $F('dtExpDocSelCliEx2');
            }
            f.dtNascimentoCli.value      = $F('dtNascimentoACli');

            f.idContatoCliA.value        = $F('idContatoCliA1');
            f.idContatoCliB.value        = $F('idContatoCliB2');
            f.idContatoCliC.value        = $F('idContatoCliC3');
            f.idTipoTelefoneCliA.value   = $F('idTipoTelefoneACli');
            f.idTipoTelefoneCliB.value   = $F('idTipoTelefoneBCli');
            f.idTipoTelefoneCliC.value   = $F('idTipoTelefoneCCli');
            f.nrTelefoneCliA.value       = $F('nrTelefone1Cli');
            f.nrTelefoneCliB.value       = $F('nrTelefone2Cli');
            f.nrTelefoneCliC.value       = $F('nrTelefone3Cli');
            f.nmContatoCliA.value        = $F('nmContato1Cli');
            f.nmContatoCliB.value        = $F('nmContato2Cli');
            f.nmContatoCliC.value        = $F('nmContato3Cli');
            f.nmEmailParticularCli.value = $F('nmEmailPartCli');
            f.nmEmailComercialCli.value  = $F('nmEmailComCli');
            f.idEstadoCivilCli.value     = $F('idEstadoCivilCliA');
            f.idEscolaridadeCli.value    = $F('idEscolaridadeCliA');
            f.dsProfissaoCli.value       = $F('nmProfissaoCli');
            f.idNatOcupacaoCli.value     = $F('idNatOcupacaoCliA');
            f.nrCPRCli.value             = $F('cdCPRCli');
            f.inUsuario.value            = inUsuario;

        } else if (inTipoPessoa=="PJ") {
            inUsuario                 = $F('inUsuarioPJ');
            f.inTipoPessoa.value      = "PJ";
            f.idPessoaPJ.value        = $F('idPessoaPJA');
            f.nrCNPJ.value            = $F('nrCNPJA');
            f.nmFantasia.value        = $F('nmFantasiaA');
            f.nmRazaoSocial.value     = $F('nmRazaoSocialA');
            f.nrCNAE.value            = $F('nrCNAEA');
            f.idTipoInscricao.value   = $F('idTipoInscricaoA');
            f.nrInscricao.value       = $F('nrInscricaoA');
            f.idClassTributaria.value = $F('idClassTributariaA');
            f.idClassEmpresa.value    = $F('idClassEmpresaA');
            f.dtFundacao.value        = $F('dtFundacaoA');
            f.nrCCM.value             = $F('nrCCMA');
            f.idContatoPJA.value      = $F('idContatoPJA1');
            f.idTipoTelefonePJ.value  = $F('idTipoTelefonePJA');
            f.nrTelefonePJ.value      = $F('nrTelefonePJA');
            f.nmContatoPJ.value       = $F('nmContatoPJA');
                if (tpOperacao!="A") f.inSMSCli.value          = $F('inSMSPJ');
            f.inUsuario.value         = inUsuario;
        }
        if (inUsuario=="0") {
            f.nmPessoaUsu.value         = $F('nmNomeUsu');
            f.idSexoUsu.value           = $F('idSexoAUsu');
            f.idDocumentoUsuA.value     = $F('idDocumentoAUsu');
            f.idDocumentoUsuB.value     = $F('idDocumentoBUsu');
            f.idTipoDocumentoUsuA.value = $F('idTipoDocumentoAUsu');
            f.idTipoDocumentoUsuB.value = $F('idTipoDocumentoBUsu');
            f.nrDocumentoUsuA.value     = $F('nrDocumentoAUsu');
            f.nrDocumentoUsuB.value     = $F('nrDocumentoBUsu');

            if (getTextBySelect($('idTipoDocumentoAUsu'))=="RG") {
                f.inDocumentoRGUsu.value    = '0';
                f.idUFDocumentoUsu.value    = $F('idUFDocumentoUsuEx1');
                f.dsOrgaoEmissorUsu.value   = $F('dsOrgEmisDocSelUsuEx1');
                f.dtExpedicaoUsu.value      = $F('dtExpDocSelUsuEx1');

            } else if (getTextBySelect($('idTipoDocumentoBUsu'))=="RG") {
                f.inDocumentoRGUsu.value    = '1';
                f.idUFDocumentoUsu.value    = $F('idUFDocumentoUsuEx2');
                f.dsOrgaoEmissorUsu.value   = $F('dsOrgEmisDocSelUsuEx2');
                f.dtExpedicaoUsu.value      = $F('dtExpDocSelUsuEx2');
            }
            f.dtNascimentoUsu.value      = $F('dtNascimentoAUsu');
            f.inUtilEndCliente.value     = $F('isUtilEndCliente');

            f.idContatoUsuA.value        = $F('idContatoUsuA1');
            f.idContatoUsuB.value        = $F('idContatoUsuB2');
            f.idContatoUsuC.value        = $F('idContatoUsuC3');
            f.idTipoTelefoneUsuA.value   = $F('idTipoTelefoneAUsu');
            f.idTipoTelefoneUsuB.value   = $F('idTipoTelefoneBUsu');
            f.idTipoTelefoneUsuC.value   = $F('idTipoTelefoneCUsu');
            f.nrTelefoneUsuA.value       = $F('nrTelefone1Usu');
            f.nrTelefoneUsuB.value       = $F('nrTelefone2Usu');
            f.nrTelefoneUsuC.value       = $F('nrTelefone3Usu');
            f.nmContatoUsuA.value        = $F('nmContato1Usu');
            f.nmContatoUsuB.value        = $F('nmContato2Usu');
            f.nmContatoUsuC.value        = $F('nmContato3Usu');
            f.nmEmailParticularUsu.value = $F('nmEmailPartUsu');
            f.nmEmailComercialUsu.value  = $F('nmEmailComUsu');
            f.idEstadoCivilUsu.value     = $F('idEstadoCivilUsuA');
            f.idEscolaridadeUsu.value    = $F('idEscolaridadeUsuA');
            f.dsProfissaoUsu.value       = $F('nmProfissaoUsu');
            f.idNatOcupacaoUsu.value     = $F('idNatOcupacaoUsuA');
            f.nrCPRUsu.value             = $F('cdCPRUsu');
        }
        if ( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
        if (clientePesquisado) {
            f.action = f.action + '?clientePesquisado=true&cdTipoLinha=' + cdTipoLinha + '&idPessoa=' + idPessoa;
        }
        f.submit();
    }catch(e) {
        alert("gravar()::"+e.description);
    }
    return false;
}

function desabilitaCamposEndereco() {
    if (inTipoPessoa=="PF") {
        if (bt01.className=="abaSelected") {
            $('nrCEPCli').disabled = true;
            $('tpLogradouroCli').disabled = true;
            $('dsTitLogradouroCli').disabled = true;
            $('nmLogradouroCli').disabled = true;
            $('nmBairroCli').disabled = true;
            $('nmMunicipioCli').disabled = true;
            $('idUFEnderecoCli').disabled = true;
        }
    } else if (inTipoPessoa=="PJ") {
        if (bt01.className=="abaSelected") {
            $('nrCEPPJ').disabled = true;
            $('tpLogradouroPJ').disabled = true;
            $('dsTitLogradouroPJ').disabled = true;
            $('nmLogradouroPJ').disabled = true;
            $('nmBairroPJ').disabled = true;
            $('nmMunicipioPJ').disabled = true;
            $('idUFEnderecoPJ').disabled = true;
        }
    }
    if (bt03.className=="abaSelected") {
        $('nrCEPUsu').disabled = true;
        $('tpLogradouroUsu').disabled = true;
        $('dsTitLogradouroUsu').disabled = true;
        $('nmLogradouroUsu').disabled = true;
        $('nmBairroUsu').disabled = true;
        $('nmMunicipioUsu').disabled = true;
        $('idUFEnderecoUsu').disabled = true;
    }
}

function habilitaCamposEndereco() {
    if (inTipoPessoa=="PF") {
        if (bt01.className=="abaSelected") {
            $('nrCEPCli').disabled = false;
            $('tpLogradouroCli').disabled = false;
            $('dsTitLogradouroCli').disabled = false;
            $('nmLogradouroCli').disabled = false;
            $('nmBairroCli').disabled = false;
            $('nmMunicipioCli').disabled = false;
            $('idUFEnderecoCli').disabled = false;
        }
    } else if (inTipoPessoa=="PJ") {
        if (bt01.className=="abaSelected") {
            $('nrCEPPJ').disabled = false;
            $('tpLogradouroPJ').disabled = false;
            $('dsTitLogradouroPJ').disabled = false;
            $('nmLogradouroPJ').disabled = false;
            $('nmBairroPJ').disabled = false;
            $('nmMunicipioPJ').disabled = false;
            $('idUFEnderecoPJ').disabled = false;
        }
    }
    if (bt03.className=="abaSelected") {
        $('nrCEPUsu').disabled = false;
        $('tpLogradouroUsu').disabled = false;
        $('dsTitLogradouroUsu').disabled = false;
        $('nmLogradouroUsu').disabled = false;
        $('nmBairroUsu').disabled = false;
        $('nmMunicipioUsu').disabled = false;
        $('idUFEnderecoUsu').disabled = false;
    }
}

function incluirEndereco(inTipo) {
    var exec = false;
    var inTipoRelaciona  = "";
    var idEndereco       = "";
    var inEndDefault     = "";
    var idTipoEndereco   = "";
    var nrCEP            = "";
    var inEndPrincipal   = "";
    var inUtilEndCliente = "";
    var nmTipoLogradouro = "";
    var nmTitLogradouro  = "";
    var nmLogradouro     = "";
    var nrLogradouro     = "";
    var nmComplemento    = "";
    var nmBairro         = "";
    var nmMunicipio      = "";
    var idUF             = "";
    var codLogradouro    = "";
    var inCnl            = "";
    var inCodigoIBGE     = "";
    if (inTipoPessoa=="PF") {
        if (bt01.className=="abaSelected") {
            inTipoRelaciona  = "C";
            idEndereco       = $F('idEnderecoCli');
            idTipoEndereco   = $F('idTipoEnderecoCli');
            inEndDefault     = $F('inEndDefaultCli');
            nrCEP            = $F('nrCEPCli');
            inEndPrincipal   = $F('isEndPrinCli');
            nmTipoLogradouro = $F('tpLogradouroCli');
            nmTitLogradouro  = $F('dsTitLogradouroCli');
            nmLogradouro     = $F('nmLogradouroCli');
            nrLogradouro     = $F('nrLogradouroCli');
            nmComplemento    = $F('dsComplementoCli');
            nmBairro         = $F('nmBairroCli');
            nmMunicipio      = $F('nmMunicipioCli');
            idUF             = $F('idUFEnderecoCli');
            codLogradouro    = $F('codLogradouroCli');
            inCnl            = $F('inCnlCli');
            inCodigoIBGE     = $F('inCodigoIBGECli');

            exec = true;
        }
    } else if (inTipoPessoa=="PJ") {
        if (bt01.className=="abaSelected") {
            inTipoRelaciona  = "C";
            idEndereco       = $F('idEnderecoPJ');
            inEndDefault     = $F('inEndDefaultPJ');
            idTipoEndereco   = $F('idTipoEnderecoPJ');
            nrCEP            = $F('nrCEPPJ');
            inEndPrincipal   = $F('isEndPrinPJ');
            nmTipoLogradouro = $F('tpLogradouroPJ');
            nmTitLogradouro  = $F('dsTitLogradouroPJ');
            nmLogradouro     = $F('nmLogradouroPJ');
            nrLogradouro     = $F('nrLogradouroPJ');
            nmComplemento    = $F('dsComplementoPJ');
            nmBairro         = $F('nmBairroPJ');
            nmMunicipio      = $F('nmMunicipioPJ');
            idUF             = $F('idUFEnderecoPJ');
            codLogradouro    = $F('codLogradouroPJ');
            inCnl            = $F('inCnlPJ');
            inCodigoIBGE     = $F('inCodigoIBGEPJ');
            exec = true;
        }
    }
    if (bt03.className=="abaSelected") {
        inTipoRelaciona  = "U";
        idEndereco       = $F('idEnderecoUsu');
        inEndDefault     = $F('inEndDefaultUsu');
        idTipoEndereco   = $F('idTipoEnderecoUsu');
        nrCEP            = $F('nrCEPUsu');
        inUtilEndCliente = $('isUtilEndCliente').checked?$F('isUtilEndCliente'):"0";
        nmTipoLogradouro = $F('tpLogradouroUsu');
        nmTitLogradouro  = $F('dsTitLogradouroUsu');
        nmLogradouro     = $F('nmLogradouroUsu');
        nrLogradouro     = $F('nrLogradouroUsu');
        nmComplemento    = $F('dsComplementoUsu');
        nmBairro         = $F('nmBairroUsu');
        nmMunicipio      = $F('nmMunicipioUsu');
        idUF             = $F('idUFEnderecoUsu');
        codLogradouro    = $F('codLogradouroUsu');
        inCnl            = $F('inCnlUsu');
        inCodigoIBGE     = $F('inCodigoIBGEUsu');
        exec = true;
    }
    if (exec) {
        if (window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();
        new Ajax.Request('incluirEndereco.do', {
            method: 'post',
            parameters: {
                inTipoPessoa: inTipoPessoa, limit:2,
                inTipoRelaciona: inTipoRelaciona, limit:1,
                idEndereco: idEndereco, limit: 18,
                inEndDefault: inEndDefault, limit: 1,
                idTipoEndereco: idTipoEndereco, limit: 2,
                nrCEP: nrCEP, limit: 2, limit: 9,
                inEndPrincipal: inEndPrincipal, limit: 1,
                inUtilEndCliente: inUtilEndCliente, limit:1,
                tpLogradouro: nmTipoLogradouro, limit: 50,
                nmTitLogradouro: nmTitLogradouro, limit: 50,
                nmLogradouro: nmLogradouro, limit: 255,
                nrLogradouro: nrLogradouro, limit: 18,
                nmComplemento: nmComplemento, limit: 255,
                nmBairro: nmBairro, limit: 255,
                nmMunicipio: nmMunicipio, limit: 255,
                idEndUF: idUF, limit: 2,
                codLogradouro: codLogradouro,  limit: 10,
                inCnl: inCnl,  limit: 10,
                inCodigoIBGE: inCodigoIBGE,  limit: 7
            },
            contentType: 'application/x-www-form-urlencoded',
            onComplete: function(r) {
                var xmlString = r.responseText;
                var oXml      = new ActiveXObject("microsoft.xmldom");
                oXml.async    = false;
                var regExp    = new RegExp ('&', 'gi') ;
                xmlString     = xmlString.replace(regExp,"&amp;");
                oXml.loadXML(xmlString);
                if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage")!=null) {
                    exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                    friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                    alert(friendlyMessage);
                    if (inTipoPessoa=="PF" && inTipoRelaciona=="C") {
                        $('imgIncEndCli').show();
                        $('imgAltEndCli').hide();
                        $('btIncluirEndCliente').hide();

                    } else if (inTipoPessoa=="PF" && inTipoRelaciona=="U") {
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndUsuario').hide();

                    } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="C") {
                        $('imgIncEndPJ').show();
                        $('imgAltEndPJ').hide();
                        $('btIncluirEndPJ').hide();

                    } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="C") {
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndUsuario').hide();
                    }

                } else {
                if (inTipoPessoa=="PF" && inTipoRelaciona=="C") {
                    inIncluiEndPC++;
                        $('imgIncEndCli').show();
                        $('imgAltEndCli').hide();
                        $('btIncluirEndCliente').hide();
                        loadEnderecos(inTipoPessoa, "C");

                } else if (inTipoPessoa=="PF" && inTipoRelaciona=="U") {
                    inIncluiEndPU++;
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndUsuario').hide();
                        loadEnderecos(inTipoPessoa, "U");

                } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="C") {
                    inIncluiEndPJ++;
                        $('imgIncEndPJ').show();
                        $('imgAltEndPJ').hide();
                        $('btIncluirEndPJ').hide();
                    onChangeUser($('inUsuarioPJ'));
                        loadEnderecos(inTipoPessoa, "C");

                } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="U") {
                    inIncluiEndPU++;
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndUsuario').hide();
                        loadEnderecos(inTipoPessoa, "U");
                    }
                }
                if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
            },
            onFailure: function(e) {alert("[Failure] "+e+"\n"+e.description);},
            onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport);}
        });
    }
}

function alterarEndereco() {
    var exec             = false;
    var nrPosAltEnd      = "";
    var inTipoRelaciona  = "";
    var idEndereco       = "";
    var inEndDefault     = "";
    var inUtilEndCliente = "";
    var idTipoEndereco   = "";
    var nrCEP            = "";
    var inEndPrincipal   = "";
    var nmTipoLogradouro = "";
    var nmTitLogradouro  = "";
    var nmLogradouro     = "";
    var nrLogradouro     = "";
    var nmComplemento    = "";
    var nmBairro         = "";
    var nmMunicipio      = "";
    var idUF             = "";
    if (inTipoPessoa=="PF") {
        if (bt01.className=="abaSelected") {
            inTipoRelaciona  = "C";
            nrPosAltEnd      = $F('nrPosAltEndCli');
            idEndereco       = $F('idEnderecoCli');
            inEndDefault     = $F('inEndDefaultCli');
            idTipoEndereco   = $F('idTipoEnderecoCli');
            nrCEP            = $F('nrCEPCli');
            inEndPrincipal   = $F('isEndPrinCli');
            nmTipoLogradouro = $F('tpLogradouroCli');
            nmTitLogradouro  = $F('dsTitLogradouroCli');
            nmLogradouro     = $F('nmLogradouroCli');
            nrLogradouro     = $F('nrLogradouroCli');
            nmComplemento    = $F('dsComplementoCli');
            nmBairro         = $F('nmBairroCli');
            nmMunicipio      = $F('nmMunicipioCli');
            idUF             = $F('idUFEnderecoCli');
            exec = true;
        }
    } else if (inTipoPessoa=="PJ") {
        if (bt01.className=="abaSelected") {
            inTipoRelaciona  = "C";
            nrPosAltEnd      = $F('nrPosAltEndPJ');
            idEndereco       = $F('idEnderecoPJ');
            inEndDefault     = $F('inEndDefaultPJ');
            idTipoEndereco   = $F('idTipoEnderecoPJ');
            nrCEP            = $F('nrCEPPJ');
            inEndPrincipal   = $F('isEndPrinPJ');
            nmTipoLogradouro = $F('tpLogradouroPJ');
            nmTitLogradouro  = $F('dsTitLogradouroPJ');
            nmLogradouro     = $F('nmLogradouroPJ');
            nrLogradouro     = $F('nrLogradouroPJ');
            nmComplemento    = $F('dsComplementoPJ');
            nmBairro         = $F('nmBairroPJ');
            nmMunicipio      = $F('nmMunicipioPJ');
            idUF             = $F('idUFEnderecoPJ');
            exec = true;
        }
    }
    if (bt03.className=="abaSelected") {
        inTipoRelaciona  = "U";
        nrPosAltEnd      = $F('nrPosAltEndUsu');
        idEndereco       = $F('idEnderecoUsu');
        inEndDefault     = $F('inEndDefaultUsu');
        idTipoEndereco   = $F('idTipoEnderecoUsu');
        nrCEP            = $F('nrCEPUsu');
        inUtilEndCliente = $('isUtilEndCliente').checked?$F('isUtilEndCliente'):"0";
        nmTipoLogradouro = $F('tpLogradouroUsu');
        nmTitLogradouro  = $F('dsTitLogradouroUsu');
        nmLogradouro     = $F('nmLogradouroUsu');
        nrLogradouro     = $F('nrLogradouroUsu');
        nmComplemento    = $F('dsComplementoUsu');
        nmBairro         = $F('nmBairroUsu');
        nmMunicipio      = $F('nmMunicipioUsu');
        idUF             = $F('idUFEnderecoUsu');
        exec = true;
    }
    if (exec) {
        if (window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();
        new Ajax.Request('alterarEndereco.do', {
            method: 'post',
            parameters: {
                nrPosAltEnd: nrPosAltEnd, limit:3,
                inTipoPessoa: inTipoPessoa, limit:2,
                inTipoRelaciona: inTipoRelaciona, limit:1,
                idEndereco: idEndereco, limit: 18,
                inEndDefault: inEndDefault, limit: 1,
                inUtilEndCliente: inUtilEndCliente, limit: 1,
                idTipoEndereco: idTipoEndereco, limit: 2,
                nrCEP: nrCEP, limit: 2, limit: 9,
                inEndPrincipal: inEndPrincipal, limit: 1,
                tpLogradouro: nmTipoLogradouro, limit: 50,
                nmTitLogradouro: nmTitLogradouro, limit: 50,
                nmLogradouro: nmLogradouro, limit: 255,
                nrLogradouro: nrLogradouro, limit: 18,
                nmComplemento: nmComplemento, limit: 255,
                nmBairro: nmBairro, limit: 255,
                nmMunicipio: nmMunicipio, limit: 255,
                idEndUF: idUF, limit: 2
            },
            contentType: 'application/x-www-form-urlencoded',
            onComplete: function(r) {
                var xmlString = r.responseText;
                var oXml      = new ActiveXObject("microsoft.xmldom");
                oXml.async    = false;
                var regExp    = new RegExp ('&', 'gi') ;
                xmlString     = xmlString.replace(regExp,"&amp;");
                oXml.loadXML(xmlString);
                if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage")!=null) {
                    exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                    friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                    alert(friendlyMessage);
                    if (inTipoPessoa=="PF" && inTipoRelaciona=="C") {
                        $('imgIncEndCli').show();
                        $('imgAltEndCli').hide();
                        $('btIncluirEndCliente').hide();

                    } else if (inTipoPessoa=="PF" && inTipoRelaciona=="U") {
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndUsuario').hide();

                    } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="C") {
                        $('imgIncEndPJ').show();
                        $('imgAltEndPJ').hide();
                        $('btIncluirEndPJ').hide();

                    } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="U") {
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndUsuario').hide();
                    }

                } else {
                if (inTipoPessoa=="PF" && inTipoRelaciona=="C") {
                    $('imgIncEndCli').show();
                    $('imgAltEndCli').hide();
                    $('btIncluirEndCliente').hide();
                        loadEnderecos(inTipoPessoa, "C");

                } else if (inTipoPessoa=="PF" && inTipoRelaciona=="U") {
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndUsuario').hide();
                        loadEnderecos(inTipoPessoa, "U");

                    } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="C") {
                    $('imgIncEndPJ').show();
                    $('imgAltEndPJ').hide();
                    $('btIncluirEndPJ').hide();
                        loadEnderecos(inTipoPessoa, "C");

                    } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="U") {
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndPJ').hide();
                        loadEnderecos(inTipoPessoa, "U");
                }
                }
                if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                habilitaCamposEndereco();
            },
            onFailure: function(e) {alert("[Failure] "+e+"\n"+e.description);},
            onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport);}
        });
    }
}

function loadDadosAlteraEndereco(nPos) {
    if (inTipoPessoa=="PF") {
        if (bt01.className=="abaSelected") {
            inTipoRelaciona  = "C";
        }
    } else if (inTipoPessoa=="PJ") {
        if (bt01.className=="abaSelected") {
            inTipoRelaciona  = "C";
        }
    }
    if (bt03.className=="abaSelected") {
        inTipoRelaciona  = "U";
    }
    new Ajax.Request('loadDadosAlteraEndereco.do', {
        method: 'post',
        parameters: {
            inTipoPessoa: inTipoPessoa, limit:2,
            inTipoRelaciona: inTipoRelaciona, limit:1,
            nrPosicao: nPos, limit: 3
        },
        contentType: 'application/x-www-form-urlencoded',
        onComplete: function(originalRequest) {
            var xmlString = originalRequest.responseText;
            var oXml      = new ActiveXObject("microsoft.xmldom");
            oXml.async    = false;
            var regExp    = new RegExp ('&', 'gi') ;
            xmlString     = xmlString.replace(regExp,"&amp;");
            oXml.loadXML(xmlString);
            if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage")!=null) {
                exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                alert(friendlyMessage);
            } else {
            var inEndDefault     = "";
            var idEndereco       = getNodeValue(oXml, "/DadosEndereco/idEndereco");
            var idTipoEndereco   = getNodeValue(oXml, "/DadosEndereco/idTipoEndereco");
            var inEndPrincipal   = getNodeValue(oXml, "/DadosEndereco/inEndPrincipal");
            var nrCEP            = getNodeValue(oXml, "/DadosEndereco/nrCEP");
            var nmTipoLogradouro = getNodeValue(oXml, "/DadosEndereco/nmTipoLogradouro");
            var nmTitLogradouro  = getNodeValue(oXml, "/DadosEndereco/nmTitLogradouro");
            var nmLogradouro     = getNodeValue(oXml, "/DadosEndereco/nmLogradouro");
            var nrLogradouro     = getNodeValue(oXml, "/DadosEndereco/nrLogradouro");
            var nmComplemento    = getNodeValue(oXml, "/DadosEndereco/nmComplemento");
            var nmBairro         = getNodeValue(oXml, "/DadosEndereco/nmBairro");
            var nmMunicipio      = getNodeValue(oXml, "/DadosEndereco/nmMunicipio");
            var idUF             = getNodeValue(oXml, "/DadosEndereco/idUF");
            desabilitaCamposEndereco();
            if (inTipoPessoa=="PF") {
                if (bt01.className=="abaSelected") {
                        $('formEndCliente').reset();
                    if (inEndPrincipal=="1") $('isEndPrinCli').checked = true;
                    $('nrPosAltEndCli').value     = nPos;
                    $('idEnderecoCli').value      = idEndereco;
                    $('idTipoEnderecoCli').value  = idTipoEndereco;
                    $('nrCEPCli').value           = nrCEP;
                    $('tpLogradouroCli').value    = nmTipoLogradouro;
                    $('dsTitLogradouroCli').value = nmTitLogradouro;
                    $('nmLogradouroCli').value    = nmLogradouro;
                    $('nrLogradouroCli').value    = nrLogradouro;
                    $('dsComplementoCli').value   = nmComplemento;
                    $('nmBairroCli').value        = nmBairro;
                    $('nmMunicipioCli').value     = nmMunicipio;
                    $('idUFEnderecoCli').value    = idUF;
                    $('imgIncEndCli').hide();
                    $('imgAltEndCli').show();
                    $('btIncluirEndCliente').show();
                }
            } else if (inTipoPessoa=="PJ") {
                if (bt01.className=="abaSelected") {
                        $('formEndPJ').reset();
                    if (inEndPrincipal=="1") $('isEndPrinPJ').checked=true;
                    $('nrPosAltEndPJ').value     = nPos;
                    $('idEnderecoPJ').value      = idEndereco;
                    $('idTipoEnderecoPJ').value  = idTipoEndereco;
                    $('nrCEPPJ').value           = nrCEP;
                    $('tpLogradouroPJ').value    = nmTipoLogradouro;
                    $('dsTitLogradouroPJ').value = nmTitLogradouro;
                    $('nmLogradouroPJ').value    = nmLogradouro;
                    $('nrLogradouroPJ').value    = nrLogradouro;
                    $('dsComplementoPJ').value   = nmComplemento;
                    $('nmBairroPJ').value        = nmBairro;
                    $('nmMunicipioPJ').value     = nmMunicipio;
                    $('idUFEnderecoPJ').value    = idUF;
                    $('imgIncEndPJ').hide();
                    $('imgAltEndPJ').show();
                    $('btIncluirEndPJ').show();
                }
            }
            if (bt03.className=="abaSelected") {
                    $('formEndUsuario').reset();
                //if (inEndPrincipal=="1") $('isEndPrinUsu').checked = true;
                $('nrPosAltEndUsu').value     = nPos;
                $('idEnderecoUsu').value      = idEndereco;
                $('idTipoEnderecoUsu').value  = idTipoEndereco;
                $('nrCEPUsu').value           = nrCEP;
                $('tpLogradouroUsu').value    = nmTipoLogradouro;
                $('dsTitLogradouroUsu').value = nmTitLogradouro;
                $('nmLogradouroUsu').value    = nmLogradouro;
                $('nrLogradouroUsu').value    = nrLogradouro;
                $('dsComplementoUsu').value   = nmComplemento;
                $('nmBairroUsu').value        = nmBairro;
                $('nmMunicipioUsu').value     = nmMunicipio;
                $('idUFEnderecoUsu').value    = idUF;
                $('imgIncEndUsu').hide();
                $('imgAltEndUsu').show();
                $('btIncluirEndUsuario').show();
            }
            }
        },
        onFailure: function(e) {alert("[Failure] "+e+"\n");},
        onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport.text);}
    });
}

function excluirEndereco(nPos) {
    if (inTipoPessoa=="PF") {
        if (bt01.className=="abaSelected") {
            inTipoRelaciona  = "C";
        }
    } else if (inTipoPessoa=="PJ") {
        if (bt01.className=="abaSelected") {
            inTipoRelaciona  = "C";
        }
    }
    if (bt03.className=="abaSelected") {
        inTipoRelaciona  = "U";
    }
    if (messageBox("Deseja realmente excluir este endere�o?")=="6") {
        new Ajax.Request('excluirEndereco.do', {
            method: 'post',
            parameters: {
                inTipoPessoa: inTipoPessoa, limit:2,
                inTipoRelaciona: inTipoRelaciona, limit:1,
                nrPosicao: nPos, limit: 3
            },
            contentType: 'application/x-www-form-urlencoded',
            onComplete: function(r) {
                var xmlString = r.responseText;
                var oXml      = new ActiveXObject("microsoft.xmldom");
                oXml.async    = false;
                var regExp    = new RegExp ('&', 'gi') ;
                xmlString     = xmlString.replace(regExp,"&amp;");
                oXml.loadXML(xmlString);
                if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage")!=null) {
                    exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                    friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                    alert(friendlyMessage);
                    if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                } else {
                if (inTipoPessoa=="PF" && inTipoRelaciona=="C") {
                    inIncluiEndPC--;
                    if ($F('nrPosAltEndCli')==nPos) {
                        $('formEndCliente').reset();
                        $('imgIncEndCli').show();
                        $('imgAltEndCli').hide();
                        $('btIncluirEndCliente').hide();
                            habilitaCamposEndereco();
                    }
                        //$('tabListaEndCliente').update(r.responseText);
                        loadEnderecos(inTipoPessoa, "C");
                } else if (inTipoPessoa=="PF" && inTipoRelaciona=="U") {
                    inIncluiEndPU--;
                        if ($F('nrPosAltEndUsu')==nPos) {
                            $('formEndUsuario').reset();
                        $('imgIncEndUsu').show();
                        $('imgAltEndUsu').hide();
                        $('btIncluirEndUsuario').hide();
                            habilitaCamposEndereco();
                    }
                        //$('tabListaEndUsuario').update(r.responseText);
                        loadEnderecos(inTipoPessoa, "U");
                } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="C") {
                    inIncluiEndPJ--;
                        if ($F('nrPosAltEndPJ')==nPos) {
                            $('formEndPJ').reset();
                            $('imgIncEndPJ').show();
                            $('imgAltEndPJ').hide();
                            $('btIncluirEndPJ').hide();
                            habilitaCamposEndereco();
                        }
                    onChangeUser($('inUsuarioPJ'));
                        //$('tabListaEndPJ').update(r.responseText);
                        loadEnderecos(inTipoPessoa, "C");
                    } else if (inTipoPessoa=="PJ" && inTipoRelaciona=="U") {
                        inIncluiEndPU--;
                        if ($F('nrPosAltEndUsu')==nPos) {
                            $('formEndUsuario').reset();
                            $('imgIncEndUsu').show();
                            $('imgAltEndUsu').hide();
                            $('btIncluirEndUsuario').hide();
                            habilitaCamposEndereco();
                        }
                        //$('tabListaEndUsuario').update(r.responseText);
                        loadEnderecos(inTipoPessoa, "U");
                }
                }
            },
            onFailure: function(e) {alert("[Failure] "+e+"\n");},
            onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport);}
        });
    }
}

function validaDadosEndereco() {
    var msgError = "";
    if (inTipoPessoa=="PF") {
        if (bt01.className=="abaSelected") {
            if ($F('idTipoEnderecoCli')=="" || $F('idTipoEnderecoCli')=="0") {
                msgError += "- Tipo de Endereco\n";
            }
            if ($F('nrCEPCli')=="") {
                msgError += "- C�digo CEP\n";
            }
            if ($F('tpLogradouroCli')=="" || $F('tpLogradouroCli')=="0") {
                msgError += "- Tipo do Logradouro\n";
            }
            if ($F('nmLogradouroCli')=="") {
                msgError += "- Nome do Logradouro\n";
            }
            if ($F('nrLogradouroCli')=="") {
                msgError += "- N�mero do Logradouro\n";
            }
            if ($F('nmBairroCli')=="") {
                msgError += "- Nome do Bairro\n";
            }
            if ($F('nmMunicipioCli')=="") {
                msgError += "- Nome do Municipio\n";
            }
            if ($F('idUFEnderecoCli')=="") {
                msgError += "- Unidade Federativa\n";
            }
        }
    } else if (inTipoPessoa=="PJ") {
        if (bt01.className=="abaSelected") {
            if ($F('idTipoEnderecoPJ')=="" || $F('idTipoEnderecoPJ')=="0") {
                msgError += "- Tipo de Endereco\n";
            }
            if ($F('nrCEPPJ')=="") {
                msgError += "- C�digo CEP\n";
            }
            if ($F('tpLogradouroPJ')=="") {
                msgError += "- Tipo do Logradouro\n";
            }
            if ($F('nmLogradouroPJ')=="") {
                msgError += "- Nome do Logradouro\n";
            }
            if ($F('nrLogradouroPJ')=="") {
                msgError += "- N�mero do Logradouro\n";
            }
            if ($F('nmBairroPJ')=="") {
                msgError += "- Nome do Bairro\n";
            }
            if ($F('nmMunicipioPJ')=="") {
                msgError += "- Nome do Municipio\n";
            }
            if ($F('idUFEnderecoPJ')=="") {
                msgError += "- Unidade Federativa\n";
            }
        }
    }
    if (bt03.className=="abaSelected") {
        if ($F('idTipoEnderecoUsu')=="" || $F('idTipoEnderecoUsu')=="0") {
            msgError += "- Tipo de Endereco\n";
        }
        if ($F('nrCEPUsu')=="") {
            msgError += "- C�digo CEP\n";
        }
        if ($F('tpLogradouroUsu')=="") {
            msgError += "- Tipo do Logradouro\n";
        }
        if ($F('nmLogradouroUsu')=="") {
            msgError += "- Nome do Logradouro\n";
        }
        if ($F('nrLogradouroUsu')=="") {
            msgError += "- N�mero do Logradouro\n";
        }
        if ($F('nmBairroUsu')=="") {
            msgError += "- Nome do Bairro\n";
        }
        if ($F('nmMunicipioUsu')=="") {
            msgError += "- Nome do Municipio\n";
        }
        if ($F('idUFEnderecoUsu')=="") {
            msgError += "- Unidade Federativa\n";
        }
    }
    if (msgError=="") {
        return true;
    } else {
        alert("Por favor, preencha os seguintes campos:\n"+msgError);
        return false;
    }
}

function processaIncEnd() {
    if (validaDadosEndereco()) {
        incluirEndereco();
        $('formEndCliente').reset();
        $('formEndUsuario').reset();
        $('formEndPJ').reset();
        habilitaCamposEndereco();
    }
}

function processaAltEnd() {
    if (validaDadosEndereco()) {
        alterarEndereco();
        $('formEndCliente').reset();
        $('formEndUsuario').reset();
        $('formEndPJ').reset();
        habilitaCamposEndereco();
    }
}

function reutilizaEndCliente(lcheck) {
    if (lcheck) {
        $('idTipoEnderecoUsu').disabled = true;
        $('nrCEPUsu').disabled = true;
        $('btPesqEndUsu').disabled = true;
        $('btPesqEndUsu').src = "/FrontOfficeWeb/resources/images/bt_pesquisar_off.gif";
        $('tpLogradouroUsu').disabled = true;
        $('dsTitLogradouroUsu').disabled = true;
        $('nmLogradouroUsu').disabled = true;
        $('nrLogradouroUsu').disabled = true;
        $('dsComplementoUsu').disabled = true;
        $('nmBairroUsu').disabled = true;
        $('nmMunicipioUsu').disabled = true;
        $('idUFEnderecoUsu').disabled = true;
        $('btIncluirEndUsuario').hide();
    } else {
        $('idTipoEnderecoUsu').disabled = false;
        $('nrCEPUsu').disabled = false;
        $('btPesqEndUsu').disabled = false;
        $('btPesqEndUsu').src = "/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif";
        $('tpLogradouroUsu').disabled = false;
        $('dsTitLogradouroUsu').disabled = false;
        $('nmLogradouroUsu').disabled = false;
        $('nrLogradouroUsu').disabled = false;
        $('dsComplementoUsu').disabled = false;
        $('nmBairroUsu').disabled = false;
        $('nmMunicipioUsu').disabled = false;
        $('idUFEnderecoUsu').disabled = false;
        $('btIncluirEndUsuario').hide();
    }
    $('formEndUsuario').reset();
    $('isUtilEndCliente').checked = lcheck;
}

function cleanContent() {$('iframePopupTI').src = "about:blank";}

function showPopup(title, width, height, top, action) {
    if ( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
    if ($('divPopupTI').style.display != "none") {
        $('divPopupTI').style.display = "none;";
    }
    $('divPopupTI').style.display = "block";
    $('title_divPopupTI').innerHTML = title;
    $('iframePopupTI').width = width;
    $('ifrm_divPopupTI').style.width = $('iframePopupTI').width;
    $('wrapper_iframePopupTI').style.width = width + "px";
    $('header_divPopupTI').style.width = width + "px";
    $('iframePopupTI').height = height-5;
    $('ifrm_divPopupTI').style.height = $('iframePopupTI').height;
    $('iframePopupTI').src = action;
    $('wrapper_divPopupTI').style.marginLeft = "5px"; //(800-width)/2 + "px";
    if (top != null) {
        $('wrapper_divPopupTI').style.marginTop = (top-21) + "px";
    } else {
        $('wrapper_divPopupTI').style.marginTop = (600-height)/2 + "px";
    }
}

function abaOcultar(abas, abaDestion, estado) {
    var tdElement;
    var imgElementLeft;
    var imgElementRight;
    for( x = 1; x < abas.cells.length; x+=3 ) {
        tdElement       = abas.cells(x);
        imgElementLeft  = $("AbaLeft_"  + tdElement.id);
        imgElementRight = $("AbaRight_" + tdElement.id);
        try{
            if ( tdElement.id == abaDestion.id) {
                if (estado) {
                    abas.cells(x).style.display='none';
                    imgElementLeft.parentElement.style.display='none';
                    imgElementRight.parentElement.style.display='none';
                } else {
                    abas.cells(x).style.display='block';
                    imgElementLeft.parentElement.style.display='block';
                    imgElementRight.parentElement.style.display='block';
                }
                break;
            }
        }catch(e) {
            break;
        }
    }
}

function escolheMascara(obj,elem) {
    try{
    if (inTipoPessoa=="PF") {
        if (bt01.className=="abaSelected") {
            if (elem=="1") {
                if (getTextBySelect($('idTipoDocumentoACli'))=="CPF") {
                    Formatar(obj.value, obj.form.name, obj.name, 'cpf');
                    checaCPF(obj);
                }
            } else if (elem=="2") {
                if (getTextBySelect($('idTipoDocumentoBCli'))=="CPF") {
                    Formatar(obj.value, obj.form.name, obj.name, 'cpf');
                    checaCPF(obj);
                }
            }
        }
    }
    if (bt03.className=="abaSelected") {
        if (elem=="1") {
            if (getTextBySelect($('idTipoDocumentoAUsu'))=="CPF") {
                Formatar(obj.value, obj.form.name, obj.name, 'cpf');
                checaCPF(obj);
            }
        } else if (elem=="2") {
            if (getTextBySelect($('idTipoDocumentoBUsu'))=="CPF") {
                Formatar(obj.value, obj.form.name, obj.name, 'cpf');
                checaCPF(obj);
            }
        }
    }
    }catch(e) {
        alert("[escolheMascara] "+e.description);
    }
}

function getTextBySelect(o) {
    try{
        return o.options[o.selectedIndex].text;
    }catch(e) {
        //alert("getTextBySelect: "+e.description);
        return "";
    }
}

function limparDadosEndereco() {

    var suffixes = ['Cli','PJ','Usu'];
    for (var i = 0; i < suffixes.length; i++) {
        $('nrCEP' + suffixes[i]).disabled = false;
        $('tpLogradouro' + suffixes[i]).disabled = false;
        $('dsTitLogradouro' + suffixes[i]).disabled = false;
        $('nmLogradouro' + suffixes[i]).disabled = false;
        $('nmBairro' + suffixes[i]).disabled = false;
        $('nmMunicipio' + suffixes[i]).disabled = false;
        $('idUFEndereco' + suffixes[i]).disabled = false;
    }

    new Ajax.Request('limparDadosEndereco.do', {
        method: 'get',
        contentType: 'text/xml',
        onComplete: function(r) {
            var xmlString = r.responseText;
            var oXml      = new ActiveXObject("microsoft.xmldom");
            oXml.async    = false;
            var regExp    = new RegExp ('&', 'gi');
            xmlString     = xmlString.replace(regExp,"&amp;");
            oXml.loadXML(xmlString);
            if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage") != null) {
                exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                alert(friendlyMessage);
            } else {
                if (bt03.className=="abaSelected") {
                    loadEnderecos(inTipoPessoa, "U");
                } else {
                    loadEnderecos(inTipoPessoa, "C");
                }
            }
        },
        onFailure: function(e) {alert("[Failure] "+e.description);},
        onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport.xml);}
    });
    return false;
}

function loadDadosAlteracao() {
    new Ajax.Request('loadDadosAlteracao.do', {
        method: 'get',
        contentType: 'text/xml',
        onComplete: function(r) {
            var xmlString = r.responseText;
            var oXml      = new ActiveXObject("microsoft.xmldom");
            oXml.async    = false;
            var regExp    = new RegExp ('&', 'gi') ;
            xmlString     = xmlString.replace(regExp,"&amp;");
            oXml.loadXML(xmlString);
            if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage") != null) {
                exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                alert(friendlyMessage);
            } else {
                inTipoPessoa     = "";
                var tpRelaciona  = "";
                var inUsuario    = "";
                var idTipoPessoa = getNodeValue(oXml,"/PrePagoVO/inTipoPessoa");
                inUsuario        = getNodeValue(oXml,"/PrePagoVO/inUsuario");//1 - Sim -- 0 - N�o (Cliente � Usuario da Linha?
                inTipoPessoa     = idTipoPessoa=="1"?"PF":idTipoPessoa=="2"?"PJ":""; //1 - PF (Pessoa Fisica) 2 - PJ (Pessoa Juridica);
                enableAllTabs();
                if (inTipoPessoa=="PF") {
                    $('tpPessoaF').checked    = true;
                    $('inUsuarioPFCli').value = inUsuario=="1"?"1":"0"; //POG para certificar que apenas ter� os valores 0 e 1
                    setFormClientePFByVO(oXml);
                    onChangeUser($('inUsuarioPFCli'));
                    bloqueiaCamposAlteracao(inTipoPessoa);

                } else if (inTipoPessoa=="PJ") {
                    $('tpPessoaJ').checked = true;
                    $('inUsuarioPJ').value = inUsuario=="1"?"1":"0"; //POG para certificar que apenas ter� os valores 0 e 1
                    carregaAba("C");
                    setFormClientePJByVO(oXml);
                    onChangeUser($('inUsuarioPJ'));
                    bloqueiaCamposAlteracao(inTipoPessoa);
                    checkInsEst($('idTipoInscricaoA'));
                }
                if (inUsuario=="0") {
                    noUser = oXml.selectNodes("/PrePagoVO/PF");
                    if (noUser!=null && noUser.length>0) {
                        setFormUsuarioByVO(oXml);
                    }
                }
                loadAbaInicio();
            }
        },
        onFailure: function(e) {alert("[Failure] "+e.description);},
        onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport.xml);}
    });
    return false;
}

function bloqueiaCamposAlteracao(tipo) {
    if (tipo=="PJ") {
        $('nrCNPJA').disabled = true;
        $('nmFantasiaA').disabled = true;
        $('nmRazaoSocialA').disabled = true;
    } else if (tipo=="PF") {
        $('nmNomeCli').disabled = true;
        $('idTipoDocumentoACli').disabled = true;
        $('nrDocumentoACli').disabled = true;
    }
}

function desbloqueiaCamposAlteracao(tipo) {
    if (tipo=="PJ") {
        $('nrCNPJA').disabled = false;
        $('nmFantasiaA').disabled = false;
        $('nmRazaoSocialA').disabled = false;
    } else if (tipo=="PF") {
        $('nmNomeCli').disabled = false;
        $('idTipoDocumentoACli').disabled = false;
        $('nrDocumentoACli').disabled = false;
    }
}

function loadDadosTitularidade() {
    new Ajax.Request('loadDadosTitularidade.do', {
        method: 'get',
        contentType: 'text/xml',
        onComplete: function(r) {
            var xmlString = r.responseText;
            var oXml      = new ActiveXObject("microsoft.xmldom");
            oXml.async    = false;
            var regExp    = new RegExp ('&', 'gi') ;
            xmlString     = xmlString.replace(regExp,"&amp;");
            oXml.loadXML(xmlString);
            if (oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage") != null) {
                exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                alert(friendlyMessage);
            } else {
                inTipoPessoa     = "";
                var tpRelaciona  = "";
                var inUsuario    = "";
                var idTipoPessoa = getNodeValue(oXml,"/PrePagoVO/inTipoPessoa");
                inUsuario        = getNodeValue(oXml,"/PrePagoVO/inUsuario");//1 - Sim -- 0 - N�o (Cliente � Usuario da Linha?
                inTipoPessoa     = idTipoPessoa=="1"?"PF":idTipoPessoa=="2"?"PJ":""; //1 - PF (Pessoa Fisica) 2 - PJ (Pessoa Juridica);
                enableAllTabs();
                if (inTipoPessoa=="PF") {
                    $('tpPessoaF').checked    = true;
                    $('inUsuarioPFCli').value = inUsuario=="1"?"1":"0"; //POG para certificar que apenas ter� os valores 0 e 1
                    setFormClientePFByVO(oXml);
                    onChangeUser($('inUsuarioPFCli'));

                } else if (inTipoPessoa=="PJ") {
                    $('tpPessoaJ').checked = true;
                    $('inUsuarioPJ').value    = inUsuario=="1"?"1":"0"; //POG para certificar que apenas ter� os valores 0 e 1
                    $('tbTitNmDoc').innerHTML = "CNPJ";
                    setFormClientePJByVO(oXml);
                    onChangeUser($('inUsuarioPJ'));
                    checkInsEst($('idTipoInscricaoA'));
                }
                if (inUsuario=="0") {
                    noUser = oXml.selectNodes("/PrePagoVO/PF");
                    if (noUser!=null && noUser.length>0) {
                        setFormUsuarioByVO(oXml);
                    }
                }
                loadAbaInicio();
            }
        },
        onFailure: function(e) {alert("[Failure] "+e.description);},
        onException: function(transport, e) {alert("[Exception] "+e.description+"\n"+transport.xml);}
    });
    return false;
}

function getNodeValue(obj,key) {
    try{
        return obj.selectSingleNode(key)==null?"":obj.selectSingleNode(key).text;
    }catch(e) {
        return "";
    }
}

function setFormClientePFByVO(obj) {
    if (obj!=null) {
        try{
            if (obj.selectSingleNode("/PrePagoVO/PF")!=null) {
                noCli  = obj.selectNodes("/PrePagoVO/PF");
                if (noCli!=null && noCli.length>1) {
                    pf = noCli[0];
                } else {
                    //pf = obj.selectSingleNode("/PrePagoVO/PF");
                    pf = noCli[0];
                }

                if (tpOperacao=="A")
                $('idPessoaCli').value = getNodeValue(pf,"idPessoa");
                $('nmNomeCli').value   = getNodeValue(pf,"nmPessoa");
                if ($F('nmNomeCli').length > maxCaracteres) {$('nmNomeCli').value = $F('nmNomeCli').substring(0, maxCaracteres)}
                $('idSexoACli').value  = getNodeValue(pf,"idSexo");

                noDOC = pf.selectNodes("Documento");
                if (noDOC!=null && noDOC.length>0) {
                    $('idDocumentoACli').value       = getNodeValue(noDOC[0],"idDocumento");

                    var idTpDoc01                    = getNodeValue(noDOC[0],"idTipoDocumento");
                    $('idTipoDocumentoACli').value   = idTpDoc01==""?"0":idTpDoc01;
                    if (getTextBySelect($('idTipoDocumentoACli'))==null || getTextBySelect($('idTipoDocumentoACli'))=="")
                        $('idTipoDocumentoACli').value   = "0";

                    $('nrDocumentoACli').value       = getNodeValue(noDOC[0],"nrDocumento");

                    if ($('PrePago').select('[class="sessaoOperacoes"]')[0].innerText == "Troca de Titularidade"
                            || $('PrePago').select('[class="sessaoOperacoes"]')[0].innerText == "Altera��o") {
                        if (getTextBySelect($('idTipoDocumentoACli')) == 'CPF') {
                            $('idTipoDocumentoACli').disabled = true;
                        } else {
                            $('idTipoDocumentoACli').disabled = false;
                        }
                    }

                    if (getTextBySelect($('idTipoDocumentoACli'))=="RG") {
                        $('idUFDocumentoCliEx1').value   = getNodeValue(noDOC[0],"idUFDocumento");
                        $('dsOrgEmisDocSelCliEx1').value = getNodeValue(noDOC[0],"dsOrgaoEmissor");
                        $('dtExpDocSelCliEx1').value     = getNodeValue(noDOC[0],"dtExpedicao");
                        $('docExtras1Cli').show();
                        if ($('tbTitNmDoc')!=null) {
                            $('tbTitNmDoc').innerHTML = "RG";
                            $('tbTitNrDoc').innerHTML = $('nrDocumentoACli').value;
                        }
                    } else {
                        if ($('tbTitNmDoc')!=null) {
                            $('tbTitNmDoc').innerHTML = getTextBySelect($('idTipoDocumentoACli'));
                            $('tbTitNrDoc').innerHTML = $('nrDocumentoACli').value;
                        }
                        $('docExtras1Cli').hide();
                    }
                    if (noDOC.length==2) {
                        $('idDocumentoBCli').value       = getNodeValue(noDOC[1],"idDocumento");

                        var idTpDoc02                    = getNodeValue(noDOC[1],"idTipoDocumento");
                        $('idTipoDocumentoBCli').value   = idTpDoc02==""?"0":idTpDoc02;
                        if (getTextBySelect($('idTipoDocumentoBCli'))==null || getTextBySelect($('idTipoDocumentoBCli'))=="")
                            $('idTipoDocumentoBCli').value = "0";

                        $('nrDocumentoBCli').value       = getNodeValue(noDOC[1],"nrDocumento");
                        if (getTextBySelect($('idTipoDocumentoBCli'))=="RG") {
                            $('idUFDocumentoCliEx2').value   = getNodeValue(noDOC[1],"idUFDocumento");
                            $('dsOrgEmisDocSelCliEx2').value = getNodeValue(noDOC[1],"dsOrgaoEmissor");
                            $('dtExpDocSelCliEx2').value     = getNodeValue(noDOC[1],"dtExpedicao");
                            $('docExtras2Cli').show();
                        } else {
                            $('docExtras2Cli').hide();
                        }
                    }
                }

                $('dtNascimentoACli').value = getNodeValue(pf,"dtNascimento");

                noTEL = pf.selectNodes("Telefone");
                if (noTEL!=null && noTEL.length>0) {
                    $('idContatoCliA1').value     = getNodeValue(noTEL[0],"idContato");

                    var idTpTel01 = getNodeValue(noTEL[0],"idTipoTelefone");
                    $('idTipoTelefoneACli').value = idTpTel01==""?"0":idTpTel01;
                    if (getTextBySelect($('idTipoTelefoneACli'))==null || getTextBySelect($('idTipoTelefoneACli'))=="")
                        $('idTipoTelefoneACli').value = "0";

                    $('nrTelefone1Cli').value     = getNodeValue(noTEL[0],"nrTelefone");
                    $('nmContato1Cli').value      = getNodeValue(noTEL[0],"nmContato");
                    if (noTEL.length>1) {
                        $('idContatoCliB2').value     = getNodeValue(noTEL[1],"idContato");

                        var idTpTel02                 = getNodeValue(noTEL[1],"idTipoTelefone");
                        $('idTipoTelefoneBCli').value = idTpTel02==""?"0":idTpTel02;
                        if (getTextBySelect($('idTipoTelefoneBCli'))==null || getTextBySelect($('idTipoTelefoneBCli'))=="")
                            $('idTipoTelefoneBCli').value = "0";

                        $('nrTelefone2Cli').value     = getNodeValue(noTEL[1],"nrTelefone");
                        $('nmContato2Cli').value      = getNodeValue(noTEL[1],"nmContato");
                        if (noTEL.length>2) {
                            $('idContatoCliC3').value     = getNodeValue(noTEL[2],"idContato");

                            var idTpTel03                 = getNodeValue(noTEL[2],"idTipoTelefone");
                            $('idTipoTelefoneCCli').value = idTpTel03==""?"0":idTpTel03;
                            if (getTextBySelect($('idTipoTelefoneCCli'))==null || getTextBySelect($('idTipoTelefoneCCli'))=="")
                                $('idTipoTelefoneCCli').value = "0";

                            $('nrTelefone3Cli').value     = getNodeValue(noTEL[2],"nrTelefone");
                            $('nmContato3Cli').value      = getNodeValue(noTEL[2],"nmContato");
                        }
                    }
                }
                $('nmEmailPartCli').value    = getNodeValue(pf,"nmEmailParticular");
                $('nmEmailComCli').value     = getNodeValue(pf,"nmEmailComercial");

                var idEstCivCli               = getNodeValue(pf,"idEstadoCivil");
                $('idEstadoCivilCliA').value  = idEstCivCli==""?"0":idEstCivCli;
                if (getTextBySelect($('idEstadoCivilCliA'))==null || getTextBySelect($('idEstadoCivilCliA'))=="")
                    $('idEstadoCivilCliA').value = "0";

                var idEscCli                  = getNodeValue(pf,"idEscolaridade");
                $('idEscolaridadeCliA').value = idEscCli==""?"0":idEscCli;
                if (getTextBySelect($('idEscolaridadeCliA'))==null || getTextBySelect($('idEscolaridadeCliA'))=="")
                    $('idEscolaridadeCliA').value = "0";

                $('nmProfissaoCli').value    = getNodeValue(pf,"dsProfissao");

                var idNatOcupCli              = getNodeValue(pf,"idNatOcupacao");
                $('idNatOcupacaoCliA').value  = idNatOcupCli==""?"0":idNatOcupCli;
                if (getTextBySelect($('idNatOcupacaoCliA'))==null || getTextBySelect($('idNatOcupacaoCliA'))=="")
                    $('idNatOcupacaoCliA').value = "0";

                $('cdCPRCli').value           = getNodeValue(pf,"nrCPR");

                nos = pf.selectNodes("DadosEndereco");
                if (nos!=null) {
                    inIncluiEndPC = nos.length;
                    loadEnderecos(inTipoPessoa, "C");
                }

            }
            inAlteracaoCliente = false;
        }catch(e) {
            alert("[setFormClientePFByVO] "+e.description);
        }
    }
}

function setFormClientePJByVO(obj) {
    if (obj!=null) {
        try{
            noPJ = obj.selectSingleNode("/PrePagoVO/PJ");
            if (noPJ!=null) {
                if (tpOperacao=="A")
                $('idPessoaPJA').value        = getNodeValue(noPJ,"idPessoa");

                $('nrCNPJA').value            = getNodeValue(noPJ,"nrCNPJ");
                if ($('nrCNPJA').value.length>1) $('nrCNPJA').value = Format($('nrCNPJA').value,"##.###.###/####-##");
                if ($('tbTitNmDoc')!=null) {
                    $('tbTitNmDoc').innerHTML = "CNPJ";
                    $('tbTitNrDoc').innerHTML = $('nrCNPJA').value;
                }
                $('nmFantasiaA').value        = getNodeValue(noPJ,"nmFantasia");
                $('nmRazaoSocialA').value     = getNodeValue(noPJ,"nmRazaoSocial");
                $('nrCNAEA').value            = getNodeValue(noPJ,"nrCNAE");

                if ($F('nmFantasiaA').length > maxCaracteres) {$('nmFantasiaA').value = $F('nmFantasiaA').substring(0, maxCaracteres)}
                if ($F('nmRazaoSocialA').length > maxCaracteres) {$('nmRazaoSocialA').value = $F('nmRazaoSocialA').substring(0, maxCaracteres)}

                var idTpInsc                  = getNodeValue(noPJ,"idTipoInscricao");
                idTpInsc = (idTpInsc == '') ? '0' : idTpInsc;

                // Somente setar� valor se for Inscri��o Estadual.
                // Altera��o necess�ria devido � OS de valida��o cadastral. Os outros tipos de inscri��es n�o mais ser�o utilizados.
                if (idTpInsc == ID_TIPO_INSCRICAO_IE) {
                    $('idTipoInscricaoA').value   = idTpInsc;
                    $('nrInscricaoA').value       = getNodeValue(noPJ, "nrInscricao");
                } else {
                    $('idTipoInscricaoA').value   = idTpInsc;
                }
                if ($('idTipoInscricaoA').selectedIndex < 0) {
                    $('idTipoInscricaoA').selectedIndex = 0;
                }

                var idClasTrib                = getNodeValue(noPJ,"idClassTributaria");
                $('idClassTributariaA').value = idClasTrib==""?"0":idClasTrib;
                if (getTextBySelect($('idClassTributariaA'))==null || getTextBySelect($('idClassTributariaA'))=="")
                    $('idClassTributariaA').value = "0";

                var idClasEmp                 = getNodeValue(noPJ,"idClassEmpresa");
                $('idClassEmpresaA').value    = idClasEmp==""?"0":idClasEmp;
                if (getTextBySelect($('idClassEmpresaA'))==null || getTextBySelect($('idClassEmpresaA'))=="")
                    $('idClassEmpresaA').value = "0";

                $('dtFundacaoA').value        = getNodeValue(noPJ,"dtFundacao");
                $('nrCCMA').value             = getNodeValue(noPJ,"nrCCM");
                noTEL = noPJ.selectSingleNode("Telefone");
                if (noTEL!=null) {
                    $('idContatoPJA1').value     = getNodeValue(noTEL,"idContato");

                    var idTpTel                  = getNodeValue(noTEL,"idTipoTelefone");
                    $('idTipoTelefonePJA').value = idTpTel==""?"0":idTpTel;
                    if (getTextBySelect($('idTipoTelefonePJA'))==null || getTextBySelect($('idTipoTelefonePJA'))=="")
                        $('idTipoTelefonePJA').value = "0";

                    $('nrTelefonePJA').value     = getNodeValue(noTEL,"nrTelefone");
                    $('nmContatoPJA').value      = getNodeValue(noTEL,"nmContato");
                }

                nos = noPJ.selectNodes("DadosEndereco");
                if (nos != null) {
                    $('btImgGravar').show();
                    inIncluiEndPJ = nos.length;
                    loadEnderecos(inTipoPessoa, "C");
                }

            }
            inAlteracaoCliente = false;
        } catch(e) {
            alert("[setFormClientePJByVO] "+e.description);
        }
    }
}

function setFormUsuarioByVO(obj) {
    if (obj!=null) {
        try{
            noUser = obj.selectNodes("/PrePagoVO/PF");
            if (noUser!=null && noUser.length==2)
                pf = noUser[1];
            else
                pf = noUser[0];

            if (tpOperacao=="A")
            $('idPessoaUsu').value = getNodeValue(pf,"idPessoa");

            $('nmNomeUsu').value   = getNodeValue(pf,"nmPessoa");
            if ($F('nmNomeUsu').length > maxCaracteres) {$('nmNomeUsu').value = $F('nmNomeUsu').substring(0, maxCaracteres)}
            $('idSexoAUsu').value  = getNodeValue(pf,"idSexo");

            noDOC = pf.selectNodes("Documento");
            if (noDOC!=null && noDOC.length>0) {
                $('idDocumentoAUsu').value     = getNodeValue(noDOC[0],"idDocumento");

                var idTpDoc01                  = getNodeValue(noDOC[0],"idTipoDocumento");
                $('idTipoDocumentoAUsu').value = idTpDoc01==""?"0":idTpDoc01;
                if (getTextBySelect($('idTipoDocumentoAUsu'))==null || getTextBySelect($('idTipoDocumentoAUsu'))=="")
                    $('idTipoDocumentoAUsu').value = "0";

                $('nrDocumentoAUsu').value     = getNodeValue(noDOC[0],"nrDocumento");
                if (getTextBySelect($('idTipoDocumentoAUsu'))=="RG") {
                    $('idUFDocumentoUsuEx1').value   = getNodeValue(noDOC[0],"idUFDocumento");
                    $('dsOrgEmisDocSelUsuEx1').value = getNodeValue(noDOC[0],"dsOrgaoEmissor");
                    $('dtExpDocSelUsuEx1').value     = getNodeValue(noDOC[0],"dtExpedicao");
                    $('docExtras1Usu').show();
                } else {
                    $('docExtras1Usu').hide();
                }
                if (noDOC.length==2) {
                    $('idDocumentoBUsu').value     = getNodeValue(noDOC[1],"idDocumento");

                    var idTpDoc02                  = getNodeValue(noDOC[1],"idTipoDocumento");
                    $('idTipoDocumentoBUsu').value = idTpDoc02==""?"0":idTpDoc02;
                    if (getTextBySelect($('idTipoDocumentoBUsu'))==null || getTextBySelect($('idTipoDocumentoBUsu'))=="")
                        $('idTipoDocumentoBUsu').value = "0";

                    $('nrDocumentoBUsu').value     = getNodeValue(noDOC[1],"nrDocumento");
                    if (getTextBySelect($('idTipoDocumentoBUsu'))=="RG") {
                        $('idUFDocumentoUsuEx2').value   = getNodeValue(noDOC[1],"idUFDocumento");
                        $('dsOrgEmisDocSelUsuEx2').value = getNodeValue(noDOC[1],"dsOrgaoEmissor");
                        $('dtExpDocSelUsuEx2').value     = getNodeValue(noDOC[1],"dtExpedicao");
                        $('docExtras2Usu').show();
                    } else {
                        $('docExtras2Usu').hide();
                    }
                }
            }

            $('dtNascimentoAUsu').value = getNodeValue(pf,"dtNascimento");
            noTEL = pf.selectNodes("Telefone");
            if (noTEL!=null && noTEL.length>0) {
                $('idContatoUsuA1').value     = getNodeValue(noTEL[0],"idContato");

                var idTpTel01                 = getNodeValue(noTEL[0],"idTipoTelefone");
                $('idTipoTelefoneAUsu').value = idTpTel01==""?"0":idTpTel01;
                if (getTextBySelect($('idTipoTelefoneAUsu'))==null || getTextBySelect($('idTipoTelefoneAUsu'))=="")
                    $('idTipoTelefoneAUsu').value = "0";

                $('nrTelefone1Usu').value     = getNodeValue(noTEL[0],"nrTelefone");
                $('nmContato1Usu').value      = getNodeValue(noTEL[0],"nmContato");
                if (noTEL.length>1) {
                    $('idContatoUsuB2').value     = getNodeValue(noTEL[1],"idContato");

                    var idTpTel02                 = getNodeValue(noTEL[1],"idTipoTelefone");
                    $('idTipoTelefoneBUsu').value = idTpTel02==""?"0":idTpTel02;
                    if (getTextBySelect($('idTipoTelefoneBUsu'))==null || getTextBySelect($('idTipoTelefoneBUsu'))=="")
                        $('idTipoTelefoneBUsu').value = "0";

                    $('nrTelefone2Usu').value     = getNodeValue(noTEL[1],"nrTelefone");
                    $('nmContato2Usu').value      = getNodeValue(noTEL[1],"nmContato");
                    if (noTEL.length>2) {
                        $('idContatoUsuC3').value     = getNodeValue(noTEL[2],"idContato");

                        var idTpTel03                 = getNodeValue(noTEL[2],"idTipoTelefone");
                        $('idTipoTelefoneCUsu').value = idTpTel03==""?"0":idTpTel03;
                        if (getTextBySelect($('idTipoTelefoneCUsu'))==null || getTextBySelect($('idTipoTelefoneCUsu'))=="")
                            $('idTipoTelefoneCUsu').value = "0";

                        $('nrTelefone3Usu').value     = getNodeValue(noTEL[2],"nrTelefone");
                        $('nmContato3Usu').value      = getNodeValue(noTEL[2],"nmContato");
                    }
                }
            }
            $('nmEmailPartUsu').value     = getNodeValue(pf,"nmEmailParticular");
            $('nmEmailComUsu').value      = getNodeValue(pf,"nmEmailComercial");

            var idEstCivUsu               = getNodeValue(pf,"idEstadoCivil");
            $('idEstadoCivilUsuA').value  = idEstCivUsu==""?"0":idEstCivUsu;
            if (getTextBySelect($('idEstadoCivilUsuA'))==null || getTextBySelect($('idEstadoCivilUsuA'))=="")
                $('idEstadoCivilUsuA').value = "0";

            var idEscUsu                  = getNodeValue(pf,"idEscolaridade");
            $('idEscolaridadeUsuA').value = idEscUsu==""?"0":idEscUsu;
            if (getTextBySelect($('idEscolaridadeUsuA'))==null || getTextBySelect($('idEscolaridadeUsuA'))=="")
                $('idEscolaridadeUsuA').value = "0";

            $('nmProfissaoUsu').value     = getNodeValue(pf,"dsProfissao");

            var idNatOcupUsu              = getNodeValue(pf,"idNatOcupacao");
            $('idNatOcupacaoUsuA').value  = idNatOcupUsu==""?"0":idNatOcupUsu;
            if (getTextBySelect($('idNatOcupacaoUsuA'))==null || getTextBySelect($('idNatOcupacaoUsuA'))=="")
                $('idNatOcupacaoUsuA').value = "0";

            $('cdCPRUsu').value           = getNodeValue(pf,"nrCPR");

            nos = pf.selectNodes("DadosEndereco");
            if (nos!=null) {
                inIncluiEndPU = nos.length;
                //loadEnderecos(inTipoPessoa, "U");
            }
        }catch(e) {
            alert("[setFormUsuarioByVO] "+e.description);
        }
    }
}

inAlteracaoClientePesquisado = function() {
    var rt = false;
    if (clientePesquisado && inAlteracaoCliente) {
        rt = true;
    }
    return rt;
}