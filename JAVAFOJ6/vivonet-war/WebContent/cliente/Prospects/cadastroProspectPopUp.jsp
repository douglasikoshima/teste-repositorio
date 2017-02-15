<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm" type="cliente.Prospects.ProspectsController.CadastroProspectForm" />
<bean:define id="TpDocto"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.tipoDocumentoVOArray" />
<bean:define id="TpCommc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.tipoComunicacaoVOArray" />
<bean:define id="Paises"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.paisVOArray" />
<bean:define id="UFs"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.UFVOArray" />
<bean:define id="Contatos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.prospectVO.contatoProspectVOArray" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="Javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="Javascript" src="<%=request.getContextPath()%>/resources/scripts/messages.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript" language="Javascript">

        var dia = new Date();
        var mes = dia.getMonth() + 1;
        var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
        var qtContatos = 0;
        var inPesquisaEndereco = false;

        bemVindo = function() {
            document.forms[0].action = '<%=request.getContextPath()%>/index.jsp';
            document.forms[0].submit();
        }

        get_radio_value = function() {
            for (var i=0; i < document.forms[0].tipoPessoaSelecionado.length; i++) {
                if (document.forms[0].tipoPessoaSelecionado[i].checked) {
                    var rad_val = document.forms[0].tipoPessoaSelecionado[i].value;
                    return rad_val;
                }
            }
        }

        reload_docs = function() {
            var idTpPessoa = get_radio_value();
            if (idTpPessoa == 1) {
              $('pessJuridica').style.display = 'none';
              $('pessFisica').style.display = 'block';
            } else {
              $('pessJuridica').style.display = 'block';
              $('pessFisica').style.display = 'none';
            }
            document.forms[0].action = '/FrontOfficeWeb/cliente/Prospects/loadProspect.do?destino=POPUP&idTipoPessoa=' + idTpPessoa;
            //document.forms[0].action = '/FrontOfficeWeb/cliente/Prospects/loadProspect.do?destino=POPUP&idTipoPessoa=' + idTpPessoa;
            parent.mostrar_div();
            document.forms[0].submit();
        }

        submitProspect = function() {
            var idTpPessoa = get_radio_value();
            if (validaProspect()) {
                if (qtContatos == 0) {
                    alert(Messages.IN_CONTATO_PROSPECT);
                    return false;
                } else {
                    $("primeiroNomePF").value = $F('primeiroNomePF').strip();
                    $("primeiroNomePJ").value = $F('primeiroNomePJ').strip();
                    $("sobreNome").value = $F('sobreNome').strip();
                    enableUf();
                    document.forms[0].action = '/FrontOfficeWeb/cliente/Prospects/salvarProspect.do?destino=POPUP&idTipoPessoa=' + idTpPessoa;
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
        }

        validaProspect = function() {

            var idTpPessoa = get_radio_value();
            var f = document.forms[0];

            // PF
            if (idTpPessoa == 1) {
                if ($F('primeiroNomePF').blank() || $F("sobreNome").blank()) {
                    alert(Messages.DG_PROSPECT_NOMESBNOME);
                    return false;
                }
            } else {
                if ($F("primeiroNomePJ").blank()) {
                    alert(Messages.DG_RAZAO_SOCIAL);
                    return false;
                }
            }

            if ($F("idTipoDoc").blank()) {
               alert(Messages.SL_DOCUMENTO_TIPO);
               return false;
            }

            var tipo = f.elements["cadastroProspectVO.prospectVO.tipoDocumento"].options[f.elements["cadastroProspectVO.prospectVO.tipoDocumento"].selectedIndex].text;

            if (tipo.toUpperCase() == "CPF") {		// CPF
                if ($F('nrDocto').blank()) {
                    alert(Messages.DG_CPF_NUMERO);
                    return false;
                } else if (!validaCPF($F('nrDocto'))) {
                    alert(Messages.DG_CPF_NUMERO_VALIDO);
                    return false;
                } else {
                    $('nrDocto').value = limpaInteiro($F('nrDocto'));
                }
            } else if (tipo.toUpperCase() == "RG") { 		// RG
                if ($F('nrDocto').blank()) {
                    alert(Messages.DG_RG_NUMERO);
                    return false;
                } else if ($('uf').selectedIndex == 0) {
                    alert(Messages.SL_UF);
                    return false;
                } else if ($F('orgaoExpedidor').blank()) {
                    alert(Messages.DG_ORGAO_EXPEDIDOR);
                    return false;
                } else if ($F('dataExpedicao').blank()) {
                    alert(Messages.DG_DATA_EXPEDICAO);
                    return false;
                } else if (!validaDataFinal($F('dataExpedicao'), hoje)) {
                    alert(Messages.DT_EXPEDICAO_MAIOR_HOJE);
                    return false;
                }
            } else if (tipo.toUpperCase() == "PASSAPORTE") { 		// Passaporte
                if ($F('nrDocto').blank()) {
                    alert(Messages.DG_PASSAPORTE_NUMERO);
                    return false;
                }
            } else if (tipo.toUpperCase() == "CNPJ") { 		// CNPJ
                if ($F('nrDocto').blank()) {
                    alert(Messages.DG_CNPJ_NUMERO);
                    return false;
                } else if (!validaCNPJ($F('nrDocto'))) {
                    alert(Messages.DG_CNPJ_NUMERO_VALIDO);
                    return false;
                } else {
                    $('nrDocto').value = limpaInteiro($F('nrDocto'));
                }
            } else if (tipo.toUpperCase() == "INSCRIÇÃO ESTADUAL") { 		// Inscrição Estadual
                if ($F('nrDocto').blank()) {
                    alert("Favor preencher uma Inscrição Estadual!");
                    return false;
                }
            } else if (tipo.toUpperCase() == "INSCRIÇÃO MUNICIPAL") { 		// Inscrição Municipal
                if ($F('nrDocto').blank()) {
                    alert("Favor preencher uma Inscrição Municipal!");
                    return false;
                }
            } else {
                if ($F('nrDocto').blank()) {
                    alert("Favor preencher o número de documento!");
                    return false;
                } else if (f.elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != '' && !validaDataFinal(f.elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)) {
                    alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
                    return false;
                }
            }
            if ($('idPaisDocumento').selectedIndex == 0) {
                alert(Messages.SL_PAIS);
                return false;
            } else if ($('idPaisDocumento').options[$('idPaisDocumento').selectedIndex].innerHTML.toUpperCase() == 'BRASIL'
                    && $('uf').selectedIndex == 0) {
                alert(Messages.SL_UF);
                return false;
            }
            if (inPesquisaEndereco) {
                if ($('idTipoEndereco').selectedIndex == 0) {
                    alert(Messages.SL_ENDERECO_TIPO);
                    return false;
                } else if ($F('nrEndereco').blank()) {
                    alert(Messages.DG_ENDERECO_NUMERO);
                    return false;
                }
            }
            return true;
        }

        validaContato = function() {
            tipo = document.forms[0].elements["idComunicacao"].options[document.forms[0].elements["idComunicacao"].selectedIndex].text;
            obj = document.forms[0].dsComunicacao;
            idTpPessoa = get_radio_value();

            if (idTpPessoa == 1) {
                $('primeiroNomePF').value = $F('primeiroNomePF').strip();
            } else {
                $('primeiroNomePJ').value = $F('primeiroNomePJ').strip();
            }

            $("prNome").value = (idTpPessoa == 1) ? $F('primeiroNomePF') : $F('primeiroNomePJ');
            $("mnNome").value = $("nomeMeio").value;
            $("ulNome").value = $("sobreNome").value;

            if (document.forms[0].idComunicacao.value == '') {
                alert(Messages.SL_CONTATO_TIPO);
            } else if (tipo.toUpperCase() == "TELEFONE RESIDENCIAL"          ||
                       tipo.toUpperCase() == "TELEFONE COMERCIAL"            ||
                       tipo.toUpperCase() == "TELEFONE RECADO"               ||
                       tipo.toUpperCase() == "RECADO"                        ||
                       tipo.toUpperCase() == "TELEFONE DE CONTATO"           ||
                       tipo.toUpperCase() == "TELEFONE DE COMERCIAL"         ||
                       tipo.toUpperCase() == "TELEFONE CONTATO"              ||
                       tipo.toUpperCase() == "COMERCIAL"                     ||
                       tipo.toUpperCase() == "RESIDENCIAL"                   ||
                       tipo.toUpperCase() == "CONTATO"                       ||
                       tipo.toUpperCase() == "TELEFONE CONTATO COMERCIAL"    ||
                       tipo.toUpperCase() == "TELEFONE DE CONTATO COMERCIAL" ||
                       tipo.toUpperCase() == "NÚMERO DO HOTEL PARA"          ||
                       tipo.toUpperCase() == "NÚMERO DO HOTEL PARA RECADO"   ||
                       tipo.toUpperCase() == "NUMERO DO HOTEL PARA"          ||
                       tipo.toUpperCase() == "NUMERO DO HOTEL PARA RECADO"   ||
                       tipo.toUpperCase() == "NUMERO HOTEL PARA"             ||
                       tipo.toUpperCase() == "NÚMERO HOTEL PARA"             ||
                       tipo.toUpperCase() == "NUMERO HOTEL"                  ||
                       tipo.toUpperCase() == "NÚMERO HOTEL"                  ||
                       tipo.toUpperCase() == "TELEFONE DE RECADO"            ||
                       tipo.toUpperCase() == "SMS"                           ||
                       tipo.toUpperCase() == "TELEFONE PARA RECADO"          ||
                       tipo.toUpperCase() == "FAX"                           ||
                       tipo.toUpperCase() == "CELULAR") {		           // TELEFONES
                if ($F('dsComunicacao').blank()) {
                    alert(Messages.DG_CONTATO_NUMERO);
                } else if ($F('dsComunicacao').length < 12) {
                    alert(Messages.DG_TELEFONE_NUMERO_VALIDO);
                } else {
                    document.forms[0].action = '/FrontOfficeWeb/cliente/Prospects/adicionarContatoProspect.do?destino=POPUP';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            } else if (tipo.toUpperCase() == "E-MAIL"             ||
                       tipo.toUpperCase() == "ENDEREÇO DE E-MAIL" ||
                       tipo.toUpperCase() == "ENDEREÇO DE EMAIL"  ||
                       tipo.toUpperCase() == "EMAIL"              ||
                       tipo.toUpperCase() == "E-MAIL PESSOAL"     ||
                       tipo.toUpperCase() == "E-MAIL PARTICULAR"  ||
                       tipo.toUpperCase() == "E-MAIL COMERCIAL"   ||
                       tipo.toUpperCase() == "EMAIL PESSOAL"      ||
                       tipo.toUpperCase() == "EMAIL PARTICULAR"   ||
                       tipo.toUpperCase() == "EMAIL COMERCIAL") {  		// E-MAIL
                if ($F('dsComunicacao').blank()) {
                    alert(Messages.DG_EMAIL);
                } else if (!validaEmail($F('dsComunicacao'))) {
                    alert(Messages.DG_EMAIL_VALIDO);
                } else {
                    document.forms[0].action="/FrontOfficeWeb/cliente/Prospects/adicionarContatoProspect.do?destino=POPUP";
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            } else {
                if ($F('dsComunicacao').blank()) {
                    alert(Messages.DG_CONTATO_DESCRICAO);
                } else {
                    document.forms[0].action = '/FrontOfficeWeb/cliente/Prospects/adicionarContatoProspect.do?destino=POPUP';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
        }

        removerContato = function(idCont) {

            if (confirm('Deseja realmente excluir este registro?')) {

                document.forms[0].ndxContatoARemover.value = idCont;
                idTpPessoa = get_radio_value();

                if (idTpPessoa == 1) {
                    $('primeiroNomePF').value = $F('primeiroNomePF').strip();
                } else {
                    $('primeiroNomePJ').value = $F('primeiroNomePJ').strip();
                }

                $("prNome").value = (idTpPessoa == 1) ? $F('primeiroNomePF') : $F('primeiroNomePJ');
                $("mnNome").value = $("nomeMeio").value;
                $("ulNome").value = $("sobreNome").value;

                document.forms[0].action = "/FrontOfficeWeb/cliente/Prospects/removeContatoProspect.do?destino=POPUP";
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }

        limpa = function() {
            document.forms[0].action = '/FrontOfficeWeb/cliente/Prospects/loadProspect.do?destino=POPUP&limpar=TRUE';
            parent.mostrar_div();
            document.forms[0].submit();
        }

        escolheMascara = function(obj) {

            tipo = document.forms[0].elements["cadastroProspectVO.prospectVO.tipoDocumento"].options[document.forms[0].elements["cadastroProspectVO.prospectVO.tipoDocumento"].selectedIndex].text;
            var qtdeCaracteres = 25;

            if (tipo.toUpperCase() == "CPF") {              // CPF
                checaCPF(obj);
                qtdeCaracteres = 14;
            } else if (tipo.toUpperCase() == "CNPJ") { 		// CNPJ
                checaCNPJ(obj);
                qtdeCaracteres = 18;
            }
            obj.maxLength = qtdeCaracteres;
        }

        escolheMascara2 = function(obj) {
            tipo = document.forms[0].elements["idComunicacao"].options[document.forms[0].elements["idComunicacao"].selectedIndex].text;
            if (tipo.toUpperCase() == "TELEFONE RESIDENCIAL"          ||
                    tipo.toUpperCase() == "TELEFONE COMERCIAL"            ||
                    tipo.toUpperCase() == "TELEFONE RECADO"               ||
                    tipo.toUpperCase() == "RECADO"                        ||
                    tipo.toUpperCase() == "COMERCIAL"                     ||
                    tipo.toUpperCase() == "RESIDENCIAL"                   ||
                    tipo.toUpperCase() == "CONTATO"                       ||
                    tipo.toUpperCase() == "TELEFONE DE CONTATO"           ||
                    tipo.toUpperCase() == "TELEFONE DE COMERCIAL"         ||
                    tipo.toUpperCase() == "TELEFONE CONTATO"              ||
                    tipo.toUpperCase() == "TELEFONE CONTATO COMERCIAL"    ||
                    tipo.toUpperCase() == "TELEFONE DE CONTATO COMERCIAL" ||
                    tipo.toUpperCase() == "NÚMERO DO HOTEL PARA"          ||
                    tipo.toUpperCase() == "NÚMERO DO HOTEL PARA RECADO"   ||
                    tipo.toUpperCase() == "NUMERO DO HOTEL PARA"          ||
                    tipo.toUpperCase() == "NUMERO DO HOTEL PARA RECADO"   ||
                    tipo.toUpperCase() == "NUMERO HOTEL PARA"             ||
                    tipo.toUpperCase() == "NÚMERO HOTEL PARA"             ||
                    tipo.toUpperCase() == "NUMERO HOTEL"                  ||
                    tipo.toUpperCase() == "NÚMERO HOTEL"                  ||
                    tipo.toUpperCase() == "TELEFONE DE RECADO"            ||
                    tipo.toUpperCase() == "SMS"                           ||
                    tipo.toUpperCase() == "TELEFONE PARA RECADO"          ||
                    tipo.toUpperCase() == "FAX"                           ||
                    tipo.toUpperCase() == "CELULAR") {				       // TELEFONES

                maskPhoneNumberObj(obj);
                qtdeCaracteres = 14;

            } else if (tipo.toUpperCase() == "E-MAIL"             ||
                       tipo.toUpperCase() == "ENDEREÇO DE E-MAIL" ||
                       tipo.toUpperCase() == "ENDEREÇO DE EMAIL"  ||
                       tipo.toUpperCase() == "EMAIL"              ||
                       tipo.toUpperCase() == "E-MAIL PESSOAL"     ||
                       tipo.toUpperCase() == "E-MAIL PARTICULAR"  ||
                       tipo.toUpperCase() == "E-MAIL COMERCIAL"   ||
                       tipo.toUpperCase() == "EMAIL PESSOAL"      ||
                       tipo.toUpperCase() == "EMAIL PARTICULAR"   ||
                       tipo.toUpperCase() == "EMAIL COMERCIAL") { 		// ENDEREÇO DE E-MAIL
                qtdeCaracteres = 255;
            } else if (tipo.toUpperCase() == "PAGER" ||
                       tipo.toUpperCase() == "BIP") {               // PAGER
                qtdeCaracteres = 25;
            } else {                                                // OUTROS
                qtdeCaracteres = 30;
            }
            obj.maxLength = qtdeCaracteres;
        }

        voltar = function() {
            var primeiro= document.forms[0].prNome.value;
            var meio= document.forms[0].nmNome.value;
            var ultimo= document.forms[0].ulNome.value;
            var pes= document.forms[0].pesquisa.value;
            document.forms[0].action = '/FrontOfficeWeb/cliente/TelaInicial/pesquisaCliente.do?tipoLista=clientes&pesquisa='+ pes +'+&prospect=true&prNome=' + primeiro + '&nmNome=' + meio + '&ulNome=' + ultimo;
            parent.mostrar_div();
            document.forms[0].submit();

        }

        camposExtrasRG = function() {
            var i;
            if (document.forms[0].idTipoDoc.options[document.forms[0].idTipoDoc.selectedIndex].text.toUpperCase() == "CPF") {
                for (i = 0; i < $('idPaisDocumento').length; i++) {
                    if ($('idPaisDocumento').options[i].text == 'BRASIL') {
                        $('idPaisDocumento').options[i].selected = true;
                        break;
                    }
                }
            } else if (document.forms[0].idTipoDoc.options[document.forms[0].idTipoDoc.selectedIndex].text.toUpperCase() == "RG") {
                for (i = 0; i < $('idPaisDocumento').length; i++) {
                    if ($('idPaisDocumento').options[i].text == 'BRASIL') {
                        $('idPaisDocumento').options[i].selected = true;
                        break;
                    }
                }
                $('docExtrasRG').style.display = 'block';
                $('tableTitle_div').style.height = "100px";
                document.forms[0].elements['cadastroProspectVO.prospectVO.orgaoExpedidor'].value = '';
                document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value = '';
            } else {
                $('idPaisDocumento').options[0].selected = true;
                $('tableTitle_div').style.height = "130px";
                document.forms[0].elements['cadastroProspectVO.prospectVO.orgaoExpedidor'].value = '';
                document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value = '';
                $('docExtrasRG').style.display = 'none';
           }
        }

        onlyAlpha = function(obj) {
            if (/[^a-z]/i.test(obj.value)) {
                obj.value=obj.value.replace(/[^a-z]/gi,'')
            }
            obj.value += '';
            obj.focus();
        }

        verificaPais = function() {
            var paisTmp      =  document.cadastroProspectForm.elements['cadastroProspectVO.prospectVO.idPaisDocumento'];
            var ufNameTmp    =  paisTmp.options[paisTmp.selectedIndex].innerHTML;
            var optionUfTmp  =  document.cadastroProspectForm.elements['cadastroProspectVO.prospectVO.idUFDocumento'];
            if (trim(ufNameTmp.toUpperCase()) != "BRASIL") {
                optionUfTmp.index    = 0;
                optionUfTmp.options[0].selected = true;
                optionUfTmp.disabled = true;
            } else {
                optionUfTmp.disabled = false;

            }

        }

        enableUf = function() {
            var optionUfTmp  =  document.cadastroProspectForm.elements['cadastroProspectVO.prospectVO.idUFDocumento'];
            if (optionUfTmp.disabled == true) {
                optionUfTmp.disabled = false;
                optionUfTmp.selectedIndex = 0;
            }
        }

        pesquisaEndereco = function() {
            if ($F('nrCEP').blank() || $F('nrCEP').length != 9) {
                alert('Por favor, digite um CEP para a realização de pesquisa.');
            } else {
                var params = $H();
                params.set('enderecoVO.nrCEP', $F('nrCEP'));
                new Ajax.Request('/FrontOfficeWeb/cliente/Prospects/pesquisarEndereco.do', {
                    method: 'post',
                    parameters: params,
                    onSuccess: function(e) {

                        var dom = parseXml(e.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();

                        $('nmTipoLogradouro').value = '';
                        $('nmTituloLogradouro').value = '';
                        $('nmLogradouro').value = '';
                        $('nmBairro').value = '';
                        $('nmMunicipio').value = '';
                        $('idUF').value = '';
                        $('idPais').value = '';
                        $('infoEndereco').update('');

                        if (jsonObj.erro) {
                            $('infoEndereco').update(jsonObj.erro);
                            inPesquisaEndereco = false;
                        } else if (jsonObj.PesquisaEnderecoVO.erro || jsonObj.PesquisaEnderecoVO.noCep
                                || !jsonObj.PesquisaEnderecoVO.ListaEnderecos) {
                            $('infoEndereco').update('Endereço não encontrado.');
                            inPesquisaEndereco = false;
                        } else {
                            var endVO = jsonObj.PesquisaEnderecoVO.ListaEnderecos.EnderecoVO ?
                                jsonObj.PesquisaEnderecoVO.ListaEnderecos.EnderecoVO : null;
                            if (endVO) {
                                if (endVO.length && endVO.length > 1) {
                                    $('nmTipoLogradouro').value = endVO[0].nmTipoLogradouro;
                                    $('nmTituloLogradouro').value = endVO[0].nmTituloLogradouro ? endVO[0].nmTituloLogradouro : '';
                                    $('nmLogradouro').value = endVO[0].nmLogradouro;
                                    $('nmBairro').value = endVO[0].nmBairro;
                                    $('nmMunicipio').value = endVO[0].nmMunicipio;
                                    $('idUF').value = endVO[0].UFVO.idUF;
                                    $('idPais').value = endVO[0].PaisVO.idPais;
                                    $('nmUF').value = endVO[0].UFVO.nmUF;
                                    $('nmPais').value = endVO[0].PaisVO.nmPais;
                                } else {
                                    $('nmTipoLogradouro').value = endVO.nmTipoLogradouro;
                                    $('nmTituloLogradouro').value = endVO.nmTituloLogradouro ? endVO.nmTituloLogradouro : '';
                                    $('nmLogradouro').value = endVO.nmLogradouro;
                                    $('nmBairro').value = endVO.nmBairro;
                                    $('nmMunicipio').value = endVO.nmMunicipio;
                                    $('idUF').value = endVO.UFVO.idUF;
                                    $('idPais').value = endVO.PaisVO.idPais;
                                    $('nmUF').value = endVO.UFVO.nmUF;
                                    $('nmPais').value = endVO.PaisVO.nmPais;
                                }
                                inPesquisaEndereco = true;
                            } else {
                                $('infoEndereco').update('Endereço não encontrado.');
                            }
                        }
                        $('nrEndereco').value = '';
                        $('dsEnderecoComplemento').value = '';

                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }, onComplete: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }, onException: function(e) {
                        alert(e.description);
                    }, on406: function(e) {
                        window.top.frameApp.createErrorPopUp('erroAlterarNumeroSMS', e.statusText, e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }

        onload = function() {
		    
			parent.oculta_div();
			
            var idTpPessoa = get_radio_value();
            if (idTpPessoa == 1) {
                $('pessJuridica').style.display = 'none';
                $('pessFisica').style.display = 'block';
                $('primeiroNomePF').focus();
            } else {
                $('pessJuridica').style.display = 'block';
                $('pessFisica').style.display = 'none';
                $('primeiroNomePJ').focus();
            }

            for (i = 0; i < document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options.length;i++) {
                if (document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options[i].text == "CPF") {
                    if (document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options[i].selected) {
                        checaCPF(document.forms[0].elements['cadastroProspectVO.prospectVO.numeroDocumento'])
                    }
                }
            }
            for (i = 0; i < document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options.length;i++) {
                if (document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options[i].text == "CNPJ") {
                    if (document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options[i].selected) {
                        checaCNPJ(document.forms[0].elements['cadastroProspectVO.prospectVO.numeroDocumento'])
                    }
                }
            }

            if (document.forms[0].idTipoDoc.options[document.forms[0].idTipoDoc.selectedIndex].text.toUpperCase() != 'RG') {
                document.forms[0].elements['cadastroProspectVO.prospectVO.orgaoExpedidor'].value = '';
                document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value = '';
                $('docExtrasRG').style.display = 'none';
            }

            <%-- parent.oculta_div(); --%>
        }

    </script>

</head>

<body style="background-color:#ededed;margin:10px;">

<acesso:controlHiddenItem nomeIdentificador="cli_cppop_verpagina">

<logic:equal name="Form" property="inMsgRetorno" value="true">
    <script type="text/javascript" language="javascript">
        alert('Erro ao gravar Prospect!');
        <% Form.setInMsgRetorno("false"); %>
    </script>
</logic:equal>

<html:form action="/cliente/Prospects/salvarProspect.do" styleId="salvarProspect" onsubmit="return false;" method="post">

<html:hidden name="Form" property="prNome" styleId="prNome" />
<html:hidden name="Form" property="mnNome" styleId="nmNome" />
<html:hidden name="Form" property="ulNome" styleId="ulNome" />
<html:hidden name="Form" property="tipoPesquisa" styleId="pesquisa" />

<% if (request.getSession().getAttribute("idNovoProspect") != null) {
    request.getSession().setAttribute("idProspect", request.getSession().getAttribute("idNovoProspect").toString());
    request.getSession().removeAttribute("idNovoProspect"); %>
    <script type="text/javascript" language="javascript">
        window.top.frameApp.fechaLupa();
        window.top.frameApp.identificado = true;
        window.top.frameApp.inProspect = true;
        <logic:equal name="Form" property="nrLinhaProspect" value="">
        window.top.frameApp.$('ti_inputValorPesquisa').value = '';
        window.top.frameApp.getProspect();
        </logic:equal>
        <logic:notEqual name="Form" property="nrLinhaProspect" value="">
        window.top.frameApp.$('ti_comboPesquisa').selectedIndex = 0;
        window.top.frameApp.$('ti_inputValorPesquisa').value = '<bean:write name="Form" property="nrLinhaProspect" />';
        window.top.frameApp.isScreenPop = false;
        window.top.frameApp.inIdPosAutomatico = true;
        window.top.frameApp.mostraIdPos();
        window.top.frameApp.nrProtocoloScreenPop = '';
        window.top.frameApp.validaPesquisa();
        </logic:notEqual>
    </script>

<% } else { %>

<html:hidden name="Form" property="ndxContatoARemover" />

<table border="0" cellpadding="0" cellspacing="0" height="30">
    <tr>
        <td>&nbsp;&nbsp;Pessoa Física</td>
        <logic:equal value="1" name="Form" property="idTipoPessoa">
            <td><input type="radio" name="tipoPessoaSelecionado" value="1" onclick="reload_docs()" checked style="border=none" class="radio" /></td>
            <td>&nbsp;&nbsp;Pessoa Jurídica</td>
            <td><input type="radio" name="tipoPessoaSelecionado" value="2" onclick="reload_docs()" style="border=none" class="radio" /></td>
        </logic:equal>
        <logic:equal value="2" name="Form" property="idTipoPessoa">
            <td><input type="radio" name="tipoPessoaSelecionado" value="1" onclick="reload_docs()" style="border=none" class="radio" /></td>
            <td>&nbsp;&nbsp;Pessoa Jurídica</td>
            <td><input type="radio" name="tipoPessoaSelecionado" value="2" onclick="reload_docs()" checked style="border=none" class="radio" /></td>
        </logic:equal>
    </tr>
</table>

<div id="pessFisica">
    <table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
      <tr>
        <td width="100" >&nbsp;&nbsp;Primeiro Nome: </td>
        <td width="183">
        <html:text name="Form" property="cadastroProspectVO.prospectVO.nmPrimeiro" styleClass="textfield" maxlength="50" styleId="primeiroNomePF" style="width:170px;" size="10" /></td>
        <td width="78">Nome do Meio: </td>
        <td width="110"><html:text name="Form" property="cadastroProspectVO.prospectVO.nmMeio" styleClass="textfield" maxlength="50" style="width:100px;" styleId="nomeMeio" size="10" /></td>
        <td width="53">Sobrenome:</td>
        <td><html:text name="Form" property="cadastroProspectVO.prospectVO.nmSobrenome" styleClass="textfield" maxlength="50" style="width:162px;" styleId="sobreNome" size="10" /></td>
      </tr>
    </table>
</div>

<div id="pessJuridica">
    <table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
      <tr>
        <td width="100" >&nbsp;&nbsp;Razão Social: </td>
        <td width="183">
        <html:text name="Form" property="cadastroProspectVO.prospectVO.nmPrimeiro" styleClass="textfield" maxlength="255" styleId="primeiroNomePJ" style="width:170px;" size="30" /></td>
        <td width="78">&nbsp;</td>
        <td width="110">&nbsp;</td>
        <td width="53">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
</div>

<table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
    <tr>
        <td width="102">&nbsp;&nbsp;Documento:</td>
        <td width="200">
            <html:select style="width:200px;" styleId="idTipoDoc" name="Form" property="cadastroProspectVO.prospectVO.tipoDocumento"  onchange="camposExtrasRG();document.forms[0].elements['cadastroProspectVO.prospectVO.numeroDocumento'].value = '';$('uf').value='0'">
                <html:option value=''>--Selecione--</html:option>
                <html:options collection="TpDocto" property="idTipoDocumento" labelProperty="dsTipoDocumento" />
             </html:select>
        </td>
        <td width="133">
            <html:text name="Form"
                    property="cadastroProspectVO.prospectVO.numeroDocumento"
                    maxlength="25"
                    styleClass="textfield"
                    onkeyup="escolheMascara(this)"
                    styleId="nrDocto"
                    size="20" />
        </td>
        <td width="30">Pa&iacute;s:</td>
        <td width="167" >
            <html:select style="width:160px" name="Form" property="cadastroProspectVO.prospectVO.idPaisDocumento" styleId="idPaisDocumento" onchange="verificaPais()">
                <html:option value="0">-- Selecione --</html:option>
                <html:options collection="Paises" property="idPais" labelProperty="nmPais" />
             </html:select>
        </td>
        <td width="23">UF:</td>
        <td>
            <html:select name="Form" styleId="uf" property="cadastroProspectVO.prospectVO.idUFDocumento" style="width:40px;">
                <html:option value="0">--</html:option>
                <html:options collection="UFs" property="idUF" labelProperty="sgUF" />
             </html:select>
        </td>
    </tr>
</table>

<div id='docExtrasRG'>
    <table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
        <tr>
            <td width="100">&nbsp;&nbsp;Org&atilde;o Expedidor: </td>
            <td width="55"><html:text name="Form" property="cadastroProspectVO.prospectVO.orgaoExpedidor" styleId="orgaoExpedidor" styleClass="textfield" size="40" maxlength="255" style="width:50px;" onkeyup="onlyAlpha(this)" /></td>
            <td width="105">&nbsp;&nbsp;Data de Expedi&ccedil;&atilde;o: </td>
            <td><html:text maxlength="10" name="Form" property="cadastroProspectVO.prospectVO.dataExpedicao" styleId="dataExpedicao" styleClass="textfield" size="12" onkeyup="checaData(this)" onblur="checaData(this)" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="CalendÃ¡rio" onclick="return showCalendar('cadastroProspectVO.prospectVO.dataExpedicao', '%d/%m/%Y');"></td>
        </tr>
    </table>
</div>

<table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
    <tr>
        <td width="102">&nbsp;&nbsp;Contato:</td>
        <td width="50">
            <html:select style="width:180px;padding-top:3px;"  name="Form" property="idComunicacao" onchange="document.forms[0].elements['dsComunicacao'].value = ''">
                <html:option value=''>--Selecione--</html:option>
                <html:options collection="TpCommc" property="idTipoComunicacao" labelProperty="dsTipoComunicacao" />
            </html:select></td>
        <td width="357">
            <html:text name="Form" property="dsComunicacao" styleId="dsComunicacao" style="vertical-align:top;" maxlength="255" onkeyup="escolheMascara2(this)" size="40" />
        </td>
        <td>
            <acesso:controlHiddenItem nomeIdentificador="cli_cppop_incluir">
            <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif" onClick="validaContato(); return false" align="absmiddle" style="border:none;" />
            </acesso:controlHiddenItem>
        </td>
    </tr>
</table>

<table width="750" border="0" cellspacing="0" cellpadding="0" style="text-indent:5px;line-height:22px;">
    <tr>
        <td>
            <vivo:tbTable selectable="true" onRowClick='' layoutWidth="750" layoutHeight="100" tableWidth="750" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="left" width="270" type="string">Tipo</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="450" type="string">Valor</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:iterate id="info" name="Contatos" indexId="ndx" >
                        <script type="text/javascript" language="javascript">
                            qtContatos++;
                        </script>
                        <vivo:tbRow key="Linha">
                            <vivo:tbRowColumn><bean:write name="info" property="dsTipoComunicacao" /></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="info" property="descricao" /></vivo:tbRowColumn>
                            <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="cli_cppop_excluir"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onClick="removerContato('<%=ndx%>');return false"></acesso:controlHiddenItem></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>
        </td>
    </tr>
</table>
<table width="768" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;margin-top:3px;">
    <tr>
        <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
        <td width="519"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Excluir Contato</td>
    </tr>
</table>

<table>
    <tr>
        <td>
            <fieldset>
                <legend style="font-weight:bold;">Dados de Endere&ccedil;o</legend>
                <table width="760" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                        <td colspan="3">
                            CEP:
                            <html:text name="Form" property="enderecoVO.nrCEP" maxlength="9" onkeyup="checaCEP(this)" size="8" styleId="nrCEP" />
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif"
                                 align="absmiddle"
                                 style="cursor:pointer;margin-left:15px;"
                                 onclick="pesquisaEndereco()" />
                            <script type="text/javascript" language="javascript">
                                checaCEP($('nrCEP'));
                            </script>
                        </td>
                        <td align="right">
                            <span id="infoEndereco" style="color:#ff0000;margin-right:10px;"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Tipo de Endereço:</td>
                        <td colspan="3">
                            <html:select name="Form" property="enderecoVO.tipoEnderecoVO.idTipoEndereco" styleId="idTipoEndereco" style="margin-left:3px">
                                <html:option value="">-- Selecione --</html:option>
                                <html:optionsCollection name="Form" property="listaTiposEndereco" label="dsTipoEndereco" value="idTipoEndereco" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                    <tr>
                        <td>Tipo Logradouro:</td>
                        <td>
                            <html:text name="Form" property="enderecoVO.nmTipoLogradouro" maxlength="255" size="15" styleId="nmTipoLogradouro" readonly="true"/>
                        </td>
                        <td colspan="2">
                            T&iacute;tulo do Logradouro:
                            <html:text name="Form" property="enderecoVO.nmTituloLogradouro" maxlength="255" size="15" styleId="nmTituloLogradouro" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Logradouro:</td>
                        <td>
                            <html:text name="Form" property="enderecoVO.nmLogradouro" maxlength="255" style="width:200px;" styleId="nmLogradouro" readonly="true"/>
                        </td>
                        <td colspan="2">
                            N&uacute;mero: <span style="color:#ff0000">*</span>
                            <b></b><html:text name="Form" property="enderecoVO.nrEndereco" maxlength="10" style="width:35px;" styleId="nrEndereco"/>
                            <span style="padding-left:50px;">Complemento:</span>
                            <html:text name="Form" property="enderecoVO.dsEnderecoComplemento" maxlength="255" style="width:70px;" styleId="dsEnderecoComplemento"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Bairro:</td>
                        <td>
                            <html:text name="Form" property="enderecoVO.nmBairro" maxlength="255" size="35" styleId="nmBairro" />
                        </td>
                        <td colspan="2">
                            Munic&iacute;pio:
                            <html:text name="Form" style="margin-left:3px;width:129px;" property="enderecoVO.nmMunicipio" maxlength="255" size="35" styleId="nmMunicipio" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td id="estado">
                            <html:hidden name="Form" property="enderecoVO.UFVO.idUF" styleId="idUF" />
                            Estado:
                        </td>
                        <td>
                            <html:text name="Form" property="enderecoVO.UFVO.nmUF" styleId="nmUF" readonly="true" />
                        </td>
                        <td id="pais" colspan="2">
                            <html:hidden name="Form" property="enderecoVO.paisVO.idPais" styleId="idPais" />
                            País: <html:text name="Form" property="enderecoVO.paisVO.nmPais" styleId="nmPais" style="margin-left:27px" readonly="true" />
                        </td>
                    </tr>
                </table>
            </fieldset>
        </td>
    </tr>
</table>

<table width="760" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td>
            <img onClick="voltar(); return false"  vspace="5" hspace="8" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" style="border:none;" />
        </td>
        <td align="right">
            <acesso:controlHiddenItem nomeIdentificador="cli_cppop_limpar">
                <img vspace="5" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" onClick="limpa(); return false" />
            </acesso:controlHiddenItem>
            <acesso:controlHiddenItem nomeIdentificador="cli_cppop_gravar">
                <img onClick="submitProspect(); return false" vspace="5" hspace="8" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" />
            </acesso:controlHiddenItem>
        </td>
    </tr>
</table>

<% } %>

</html:form>

</acesso:controlHiddenItem>

</body>