<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO"%>
<%@ page import="sun.text.Normalizer"%>
<%@ page import="extworkflw.RegistrarContato.RegistrarContatoController.RegistrarContatoForm"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<bean:define id="Form"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />
<bean:define id="AtendimentoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO" />
<bean:define id="aContaVO"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.contas.contaVOArray" />
<bean:define id="aComunicacao"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.pessoaVO.atendimentoTipoComunicacaoVOArray" />
<bean:define id="aCanalVO"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.canais.canalVOArray" />
<bean:define id="aProcedenciaVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.procedencias.procedenciaVOArray" />
<bean:define id="nmURLContatoAcao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.nmURLContatoAcao" />
<bean:define id="scriptValidacao"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.scriptValidacao" />
<bean:define id="scriptCarrega"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.scriptCarrega" />
<bean:define id="acaoVO"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.WFAcaoVOArray"/>
<bean:define id="aComunicacaoSel"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.WFAtendimentoContatoComunicVOArray" />

<html:html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript" language="javascript" >
        <!--
        var tamanhoTdObservacao = 45;
        var tamanhoAreaObservacao = "310px";
        var tamanhoTabelaFormulario = "100%";
        var flUltimoNivel = false;
        var nrUltimoNivel = 1;
        var qtdeNiveis = 1;
        
        function chamaTela(){
            <logic:notEqual name="nmURLContatoAcao" value="">
            document.getElementById('ifrmServicos').src="<%="/FrontOfficeWeb"+(String)nmURLContatoAcao%>";
            </logic:notEqual>
            if (document.forms[0].tipoComunicacaoSelecionada) {
                for (i=0;i<document.forms[0].tipoComunicacaoSelecionada.options.length;i++) {
                    if (document.forms[0].tipoComunicacaoSelecionada.options[i].text == "CELULAR"){
                        parent.inCelular = true;
                        break;
                    }
                }
            }
        }

        function checaTextarea(obj, tamanho){
            if(obj.value.length>tamanho){
                obj.value = obj.value.substr(0,tamanho);
                alert("O campo comentário não aceita mais de "+tamanho+" caracteres");
            }
        }

        function pesquisaEndereco() {
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
            var src = "/FrontOfficeWeb/cliente/PrePago/loadPesquisaEndereco.do?origem=FORMDINAMICO";
            if (window.top.frameApp.$('divPopupTI')) {
                window.top.frameApp.showPopupTI("Pesquisa de Endereços", 750, 300, null, src);
            } else {
                window.top.frameApp.$('divPesquisaEndereco').style.display = "block";
                window.top.frameApp.iframePesquisaEndereco.location.href = src;
            }
        }

        function getDadosAparelhos() {
            new Ajax.Request('getDadosAparelhos.do', {
                method: 'get',
                contentType: 'text/xml',
                onComplete: function (originalRequest) {
                    var dsModeloAparelho, dsMarcaAparelho, dsTecnologia;
                    var xmlString = originalRequest.responseText;
                    var oXml      = new ActiveXObject("microsoft.xmldom");
                    oXml.async    = false;
                    var regExp    = new RegExp ('&', 'gi') ;
                    xmlString     = xmlString.replace(regExp,"&amp;");
                    oXml.loadXML(xmlString);

                    if (oXml.selectSingleNode("/xml-fragment/friendlyMessage") == null) {
                        if(oXml.selectSingleNode("/xml-fragment/DadosLupaLinha/dsTecnologia"))
                            $('dsTecnologia').value = oXml.selectSingleNode("/xml-fragment/DadosLupaLinha/dsTecnologia").text;
                        else $('dsTecnologia').value = oXml.selectSingleNode("/xml-fragment/DadosLupaLinha/descricao").text;
                        dsModeloAparelho = oXml.selectSingleNode("/xml-fragment/DadosLupaLinha/modelo").text;
                        dsMarcaAparelho  = oXml.selectSingleNode("/xml-fragment/DadosLupaLinha/marca").text;
                        $('dsModeloAparelho').value = dsModeloAparelho;
                        $('dsMarcaAparelho').value  = dsMarcaAparelho;
                        $('dsModeloAparelho').readOnly = false;
                        $('dsMarcaAparelho').readOnly = false;
                    }
                }
            });
        }

        function getValoresProximoNivel(objSelect) {
            /* Valores enviados através do ID do objeto select:
             * grupoCamposDependentes;idGrupoCamposDependentes;idCampo|nrNivel
             */
            var qtdeNiveis;
            var dadosGrupo = objSelect.id;
            var idResposta = objSelect.value;
            var dadosCampoDependente = dadosGrupo.split(";");
            var idGrupoCamposDependentes = dadosCampoDependente[1];
            var idCampo = dadosCampoDependente[2];
            var nrNivel = dadosCampoDependente[3];
            var nmPath  = dadosCampoDependente[4];
            var nmTable = "";

            var nmClasse = "grupos" + idGrupoCamposDependentes;
            qtdeNiveis = $('divQuest').select('[class="'+nmClasse+'"]').length;

            if (parseInt(nrNivel) < parseInt(nrUltimoNivel)) {
                flUltimoNivel = false;
            }

            if (!flUltimoNivel) {
                if (parseInt(nrNivel) < parseInt(qtdeNiveis)) {
                    for (i = parseInt(nrNivel) + 1; i <= parseInt(qtdeNiveis); i++) {
                        nmTable = "tableDependentes|" + idGrupoCamposDependentes + "|" + i;
                        removeElementById(nmTable);
                    }
                    qtdeNiveis = nrNivel;
                    flUltimoNivel = false;
                }

                if ( (idResposta.indexOf("|") > 0 && !flUltimoNivel) ) {
                    if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                    new Ajax.Request('getValoresProximoNivel.do', {
                        method: 'get',
                        contentType: 'text/xml',
                        encoding: 'ISO-8859-1',
                        parameters: {
                            nrNivel: nrNivel, limit: 12,
                            dadosGrupo: dadosGrupo, limit: 100,
                            nmPath: nmPath, limit: 255,
                            idResposta: idResposta.substring(idResposta.indexOf("|")+1,idResposta.length), limit: 30
                        },
                        onComplete: function (originalRequest) {
                            var dsModeloAparelho, dsMarcaAparelho, dsTecnologia;
                            var xmlString = originalRequest.responseText;
                            var oXml      = new ActiveXObject("microsoft.xmldom");
                            oXml.async    = false;
                            var regExp    = new RegExp ('&', 'gi') ;
                            xmlString     = xmlString.replace(regExp,"&amp;");
                            oXml.loadXML(xmlString);
                            $('containerCamposDependentes'+idGrupoCamposDependentes).innerHTML += createNewField(oXml, idGrupoCamposDependentes);
                            if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                        },
                        onException: function(e){
                            //alert("Ocorreu um erro durante carregamento de dados de campos dependentes.");
                            if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                        }
                    });
                }
            }
        }

        function createNewField(oXml, idGrupoCamposDependentes) {
            var nmCampoDependente = oXml.selectSingleNode("/xml-fragment/AdmGruposCamposDependentesVO/AdmGrupoCamposDependentesVO/nmCampoDependente").text;
            var idCampoDependente = oXml.selectSingleNode("/xml-fragment/AdmGruposCamposDependentesVO/AdmGrupoCamposDependentesVO/idCampoDependente").text;
            var nmPath            = oXml.selectSingleNode("/xml-fragment/AdmGruposCamposDependentesVO/AdmGrupoCamposDependentesVO/nmPath").text;
            nmPath = escape(nmPath);
            var nrNivel           = oXml.selectSingleNode("/xml-fragment/AdmGruposCamposDependentesVO/AdmGrupoCamposDependentesVO/nrNivel").text;
            var valoresCampos     = oXml.getElementsByTagName("AdmCampoVO");
            var idCampo, nmCampo;

            if (nrNivel == "") {
                flUltimoNivel = true;
                nrUltimoNivel++;
                qtdeNiveis = nrUltimoNivel;
            } else {
                nrUltimoNivel = nrNivel;
                qtdeNiveis = nrNivel;
            }

            var html  = "<table class='grupos"+idGrupoCamposDependentes+"' id='tableDependentes|"+idGrupoCamposDependentes+"|";

            if (nrNivel == "")
                html += nrUltimoNivel;
            else
                html += nrNivel;

            var nivEl = (nrNivel == "") ? nrUltimoNivel : nrNivel;

            html     += "'>";
            html     += "";
            html     += "<tr>";
            html     += "        <td width='150' nowrap style='padding-right:10px;'>" + nmCampoDependente + "</td>";
            html     += "        <td nowrap>";
            html     += "             <select id='grupoCamposDependentes;" + idGrupoCamposDependentes + ";" + idCampoDependente + ";" + nivEl + ";" + nmPath + "' name='grupoCamposDependentes;" + idGrupoCamposDependentes + ";" + nivEl + "' onchange='getValoresProximoNivel(this)' style='width:150px'>";
            html     += "                 <option value='" + idCampoDependente + "'>-- Selecione --</option>";
                                                for (i = 0; i < valoresCampos.length; i++) {
                                                    idCampo = valoresCampos[i].childNodes[0].text;
                                                    nmCampo = valoresCampos[i].childNodes[1].text;
                                                    html     += "<option value='" + idCampoDependente + "|" + idCampo + "'>" + nmCampo + "</option>\n";
                                                }
            html     += "                 </option>";
            html     += "             </select>";
            html     += "         </td>";
            html     += "         <td width='100%'></td>";
            html     += "     </tr>";
            html     += " </table>";
            return html;
        }

        function formatLinha(value,format) {
            var i=0, j=0;
            value = value.replace(/\D/g,"");
            var result="";
            if(format.length < value.length)
                value = value.substring(0,format.length -1);

            for(i=0,j=0;(i<format.length)&&(j<value.length);i++){
                var ch = format.charAt(i);
                if(ch=='#'){
                    result += value.charAt(j++);
                    continue;
                }
                result += ch;
            }
            return result;
        }

        function buscaLinhasByConta(cdConta){
            try{
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                new Ajax.Request('getLinhasByConta.do', {
                    method: 'get',
                    contentType: 'text/xml',
                    encoding: 'ISO-8859-1',
                    parameters: {
                        cdConta: cdConta, limit: 18
                    },
                    onComplete: function (originalRequest) {
                        var xmlString = originalRequest.responseText;
                        var oXml      = new ActiveXObject("microsoft.xmldom");
                        oXml.async    = false;
                        var regExp    = new RegExp ('&', 'gi') ;
                        xmlString     = xmlString.replace(regExp,"&amp;");
                        oXml.loadXML(xmlString);

                        while($('nrLinhasContaSel').options.length != 0)
                            $('nrLinhasContaSel').options.remove(0);

                        nrLinhas = oXml.selectNodes("/xml-fragment/vlItem");
                        for(r=0;r<nrLinhas.length;r++){
                            var nrLinha = nrLinhas[r].text;
                            var texto = formatPhoneNumber(nrLinha);
                            $('nrLinhasContaSel').options[$('nrLinhasContaSel').options.length] = new Option(texto, nrLinha);
                        }
                        if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                    },
                    onException: function(t, e){
                        if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                        alert("Erro[buscaLinhasByConta(Ajax-E)]: "+e.description+"\n"+t.transport);
                    },
                    onFailure: function(e){
                        if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                        alert("Erro[buscaLinhasByConta(Ajax-F)]: "+e.description);
                    }
                });
            }catch(e){
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                alert("Erro[buscaLinhasByConta]: "+e.description);
            }
        }

        function addLinhaSelect(){
            var x = 0;
            for(x=0; x<$('nrLinhasContaSel').options.length; x++){
                if($('nrLinhasContaSel').options[x].selected){
                    var linha = formatPhoneNumber($('nrLinhasContaSel').options[x].value);
                    var value = $('nrContaConsulta').value+"|"+$('nrLinhasContaSel').options[x].value;
                    if(!isAdded(value, 'nrLinhasSelecionadas')){
                        $('nrLinhasSelecionadas').options[$('nrLinhasSelecionadas').options.length] = new Option(linha, value);
                    }
                }
            }
        }

        function isAddedX(value){
            var x = 0;
            for(x=0;x<$('nrLinhasSelecionadas').options.length;x++){
                if($('nrLinhasSelecionadas').options[x].value == value){
                    return true;
                }
            }
            return false;
        }

        function isAdded(value, sObjSel){
            var x = 0;
            for(x=0;x<document.getElementById(sObjSel).options.length;x++){
                if(document.getElementById(sObjSel).options[x].value == value){
                    return true;
                }
            }
            return false;
        }


        function removeLinhaSelec(){
            var cont=0;
            var x = 0;
            for(x=0;x<$('nrLinhasSelecionadas').options.length;x++)
                if($('nrLinhasSelecionadas').options[x].selected) cont++;

            while(cont>0){
                for(x=0;x<$('nrLinhasSelecionadas').options.length;x++){
                    if($('nrLinhasSelecionadas').options[x].selected){
                        $('nrLinhasSelecionadas').options[x] = null;
                        cont--;
                    }
                }
            }
        }

        function buscaContaByLinha(nrLinha){
            try{
                if(nrLinha=="") return false;
                var nrLinhaFmt = nrLinha;
                nrLinha = nrLinha.replace("(","").replace(")","").replace("-","");
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                new Ajax.Request('getContaByLinha.do', {
                    method: 'get',
                    contentType: 'text/xml',
                    encoding: 'ISO-8859-1',
                    parameters: {
                        nrLinha: nrLinha, limit: 10
                    },
                    onComplete: function (originalRequest) {
                        var xmlString = originalRequest.responseText;
                        var oXml      = new ActiveXObject("microsoft.xmldom");
                        oXml.async    = false;
                        var regExp    = new RegExp ('&', 'gi') ;
                        xmlString     = xmlString.replace(regExp,"&amp;");
                        oXml.loadXML(xmlString);

                        nrContas = oXml.selectNodes("/xml-fragment/vlItem");
                        idLinha  = oXml.selectSingleNode("/xml-fragment/idLinhaTelefonica")!=null?oXml.selectSingleNode("/xml-fragment/idLinhaTelefonica").text:"";

                        if(nrContas==null || nrContas.length==0){
                            alert("Linha não Localizada");

                        }else if(nrContas.length>0){
                            var value = nrContas[0].text;
                            if(value!=null || value!=""){
                                <logic:equal name="Form" property="carregaLinha" value="2">
                                //if(isAdded(value, 'contaSelecionada'))
                                //    $('contaSelecionada').options[$('contaSelecionada').options.length] = new Option(nrContas[0].text, nrContas[0].text);
                                $('contaSelecionada').value = nrContas[0].text;

                                while($('linhaSelecionada').options.length != 0)
                                    $('linhaSelecionada').options.remove(0);

                                $('linhaSelecionada').options[$('linhaSelecionada').options.length] = new Option(nrLinhaFmt, idLinha);
                                $('linhaSelecionada').value = idLinha;
                                </logic:equal>
                                <logic:equal name="Form" property="carregaLinha" value="1">
                                $('contaSelecionada').value = nrContas[0].text;
                                    $('linhaSelecionada').value = nrLinhaFmt;
                                </logic:equal>
                            }
                        }
                        if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                    },
                    onException: function(t, e){
                        if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                        alert("Erro[buscaContaByLinha(Ajax-E)]: "+e.description+"\n"+t.transport);
                    },
                    onFailure: function(e){
                        if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                        alert("Erro[buscaContaByLinha(Ajax-F)]: "+e.description);
                    }
                });
            
            }catch(e){
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                alert("Erro[buscaContaByLinha]: "+e.description);
            }
        }
        
        onload = function() {
            parent.redimensionaProcessosCorrentes();
            chamaTela();
            // reset formulário dinâmico
            $('tabelaFormulario').select('input,select').each(function(s) {
                s.value = '';
                if (s.tagName.toLowerCase() == 'select') {
                    s.selectedIndex = 0;
                }
            });
        }
        -->
    </script>
</head>

    <body>    
        <html:form styleId="registrarContatoForm2" action="/extworkflw/RegistrarContato/listarProcessos.do" method="post" onsubmit="return false;">
        <div id="divProcessoCorrentes" style="display:none;position:absolute;">
            <iframe name="ifrmListaProcessos" src="listarProcessos.do?idChamadaTelefonica=<%=((RegistrarContatoForm)Form).getIdChamadaTelefonica()%>" width="400" height="320" scrolling="no" style="visibility:;"></iframe>
        </div>
        <div id="divQuest" style="display:;position:absolute;">
            <html:hidden name="Form" property="rowIndex" />
            <html:hidden name="Form" property="idAtendimentoExclusao" />
            <html:hidden name="Form" property="idLinhaAtendimento" />
            <html:hidden name="Form" property="tipoOperacao"/>
            <html:hidden name="Form" property="idContato"/>
            <html:hidden name="Form" property="telContatoFrm"/>
            <html:hidden name="Form" property="inResponsavelAbertura"/>
            <html:hidden name="Form" property="idGrupoAbertura"/>
            <html:hidden name="Form" property="idSegmentacao"/>
            <html:hidden name="Form" property="idTipoCarteira"/>
            <html:hidden name="Form" property="idTipoLinha"/>
            <html:hidden name="Form" property="idUfOperadora"/>
            <html:hidden name="Form" property="idChamadaTelefonica"/>
            <html:hidden name="Form" property="nomeContato"/>
            <html:hidden name="Form" property="nrLinha"/>
            <html:hidden name="Form" property="nrConta"/>
            <html:hidden name="Form" property="idConta"/>
            <html:hidden name="Form" property="idLinha"/>
            <html:hidden name="Form" property="inTipoPessoa"/>
            <html:hidden name="Form" property="idClienteDePara"/>
            <html:hidden name="Form" property="idUsuarioDePara"/>
            <html:hidden name="Form" property="idPessoa"/>
            <html:hidden name="Form" property="idDiscador"/>
            <html:hidden name="Form" property="idAtendimento"/>
            <html:hidden name="Form" property="idAtendimentoSituacao"/>
            <html:hidden name="Form" property="nrProtocoloSituacao"/>
            <html:hidden name="Form" property="idTipoReaberturaProcesso"/>
            <html:hidden name="Form" property="nmTipo"/>
            <html:hidden name="Form" property="qtDias"/>
            <html:hidden name="Form" property="fila"/>
            <html:hidden name="Form" property="abreProcessoReaRei"/>
            <html:hidden name="Form" property="descricaoCompleta"/>
            <html:hidden name="Form" property="idBaixa"/>
            <html:hidden name="Form" property="idBaixaMensagem"/>
            <html:hidden name="Form" property="observacaoFechamento"/>
            <html:hidden name="Form" property="carregaLinha"/>
            <html:hidden name="Form" property="numeroCampos" />
            <table border="0">
                <logic:equal name="nmURLContatoAcao" value="">
                <tr>
                    <td>
                        <table id="tabelaFormulario" border="0" cellpadding="1" cellspacing="0" width="100%">
                            <jsp:include page='FormularioDinamico.jsp' />
                            <tr>
                                <td colspan=2 align="center">
                                     <table border="0" width="100%">
                                         <tr>
                                             <td id="tdObservacao" align="left" width="45" style="font-size: 9px;">Observação:</td>
                                             <td id="tdAreaObservacao" align="left" width="480">
                                                <html:textarea name="Form" property="observacaoAtendimento" onblur="return checaTextarea(this,1000);return false;" onkeyup="return checaTextarea(this,1000);return false;" style="width:450;color:#006699;" rows="4"/>
                                             </td>
                                         </tr>
                                     </table>
                                    <fieldset style="width:100%;">
                                        <legend style="font-weight:bold;">Linhas Associadas</legend>
                                        <table width="100%" style="font-size:9px" cellpadding="3" cellspacing="3">
                                            <tr>
                                                <td>Conta:</td>
                                                <td>Linhas da Conta</td>
                                                <td></td>
                                                <td>Linhas Associadas</td>
                                            </tr>
                                            <tr>
                                                <td width="250" valign="top">
                                                    <select name="nrContaConsulta" style="width:150px;" onchange="buscaLinhasByConta(this.value);">
                                                        <option value="0">--Selecione--</option>
                                                        <c:catch var="logicItnrContaConsulta">
                                                        <logic:iterate id="itValue" name="Form" property="listaDadosVO.vlItemArray">
                                                        <option value="<bean:write name="itValue"/>"><bean:write name="itValue"/></option>
                                                        </logic:iterate>
                                                        </c:catch>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="nrLinhasContaSel" name="nrLinhasContaSel" size="7" multiple style="width:150px;">
                                                    </select>
                                                </td>
                                                <td align="center" width="55">
                                                    <img src="<%=request.getContextPath()%>/resources/images/bt_rightaln_nrml.gif" onclick="addLinhaSelect();"><br><br>
                                                    <img src="<%=request.getContextPath()%>/resources/images/bt_x_nrml.gif" onclick="removeLinhaSelec();">
                                                </td>
                                                <td>
                                                    <select id="nrLinhasSelecionadas" name="nrLinhasSelecionadas" size="7" multiple style="width:150px;">
                                                    </select>
                                                </td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                 </td>
                             </tr>
                        </table>
                    </td>
                </tr>
                <script>
                    //se possui formulário dinâmco aumenta o tamanho da tabela
                    tabelaFormulario.style.width = tamanhoTabelaFormulario;
                    tdObservacao.style.width = tamanhoTdObservacao;
                </script>
                </logic:equal>
                
                <logic:notEqual name="nmURLContatoAcao" value="">
                <tr>
                    <td>
                        &nbsp;&nbsp;&nbsp;<iframe name="ifrmServicos"  src="" width="700" height="650" scrolling="auto"></iframe>
                    </td>
                </tr>
                <tr align="left">
                <td>
                    <table>
                        <tr>
                            <td align="right" width="80px">Observação:</td>
                            <td align="left" colspan="2">
                                <html:textarea name="Form" property="observacaoAtendimento" cols="40" rows="4" style="width:630"/>
                            </td>
                            </tr>
                        </table>
                        <fieldset style="width:100%;">
                            <legend style="font-weight:bold;">Linhas Associadas</legend>
                            <table width="100%" style="font-size:9px" cellpadding="3" cellspacing="3">
                                <tr>
                                    <td>Conta:</td>
                                    <td>Linhas da Conta</td>
                                    <td></td>
                                    <td>Linhas Associadas</td>
                                </tr>
                                <tr>
                                    <td width="250" valign="top">
                                        <select name="nrContaConsulta" style="width:150px;" onchange="buscaLinhasByConta(this.value);">
                                            <option value="0">--Selecione--</option>
                                            <c:catch var="logicItnrContaConsulta">
                                            <logic:iterate id="itValue" name="Form" property="listaDadosVO.vlItemArray">
                                            <option value="<bean:write name="itValue"/>"><bean:write name="itValue"/></option>
                                            </logic:iterate>
                                            </c:catch>
                                        </select>
                                    </td>
                                    <td>
                                        <select id="nrLinhasContaSel" name="nrLinhasContaSel" size="7" multiple style="width:150px;">
                                        </select>
                                    </td>
                                    <td align="center" width="55">
                                        <img src="<%=request.getContextPath()%>/resources/images/bt_rightaln_nrml.gif" onclick="addLinhaSelect();"><br><br>
                                        <img src="<%=request.getContextPath()%>/resources/images/bt_x_nrml.gif" onclick="removeLinhaSelec();">
                                    </td>
                                    <td>
                                        <select id="nrLinhasSelecionadas" name="nrLinhasSelecionadas" size="7" multiple style="width:150px;">
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                </logic:notEqual>
            </table>
            </div>

            <div id="divContato" style="display:none;position:absolute;">
                <table border="0">
                    <tr>
                        <td valign="top">Canal:</td>
                        <td valign="top">
                            <html:select name="Form" property="idCanal" style="width:150px">
                                <%--html:option value="">-- Selecione --< / html:option--%>
                                <html:options collection="aCanalVO" property="idCanal" labelProperty="nmCanal" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top">Procedencia:</td>
                        <td valign="top">
                            <html:select name="Form" property="idProcedencia" style="width:150px">
                                <%--html:option value="">-- Selecione --</html:option--%>
                                <html:options collection="aProcedenciaVO" property="idProcedencia" labelProperty="dsProcedencia" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top">Falando com:</td>
                        <td valign=top >
                            <input name="nomeContato" type="text" style="width:100%" value=''>
                        </td>
                    </tr>
                    <tr>
                            <td valign="top">No. Telefone Contato:</td>
                        <td align=left valign="top"><html:text name="Form" property="telContato" style="width:150px" onkeyup="Formatar(this.value, this.form.name, this.name, 'telefone');" onchange="Formatar(this.value, this.form.name, this.name, 'telefone');"/></td>
                    </tr>
                    <tr>
                        <td>Nome do Contato:</td>
                        <td>
                             <html:text name="Form" style="width:100%" property="nomeContatoAlterado" />
                        </td>
                    </tr>
                    <!--Caso seja usuário-->
                    <logic:equal name="Form" property="carregaLinha" value="1">
                        <tr>
                            <td>Conta:</td>
                            <td>
                                <html:hidden name="Form" property="contaSelecionada" styleId="contaSelecionada"/>
                                <input type="text" style="width:150px" readonly value="<bean:write name="Form" property="nrConta" />">
                            </td>
                        </tr>
                        <tr>
                            <td>Linha:</td>
                            <td>
                                <html:hidden  name="Form" property="linhaSelecionada" />
                                <input type="text" style="width:150px" readonly value="<bean:write name="Form" property="telContato" />">&nbsp;
                                <input type="text" name="linhaBusca" id="linhaBusca" value="" size="15" onkeyup="maskPhoneNumberObj(this);" maxlength="14">&nbsp;
                                <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" onclick="buscaContaByLinha($F('linhaBusca'));" border="0">
                            </td>
                        </tr>
                    </logic:equal>
                    <!--Caso seja cliente-->
                    <logic:equal name="Form" property="carregaLinha" value="2">
                        <tr>
                            <td valign="top">Conta:</td>
                            <td valign="top">
                                <html:select name="Form" property="contaSelecionada" styleId="contaSelecionada" style="width:150px" onchange="obtemLinhasIFrame(this);">
                                    <html:option value="">-- Selecione --</html:option>
                                    <logic:present name="aContaVO">
                                    	<html:options collection="aContaVO" property="idConta" labelProperty="dsAuxiliar" />
                                    </logic:present>	
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top">Linha:</td>
                            <td valign="top">
                                <html:select name="Form" property="linhaSelecionada" styleId="linhaSelecionada" style="width:150px"/>&nbsp;
                                <input type="text" name="linhaBusca" id="linhaBusca" value="" size="15" onkeyup="maskPhoneNumberObj(this);" maxlength="14">&nbsp;
                                <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" onclick="buscaContaByLinha($F('linhaBusca'));" border="0">
                            </td>
                        </tr>
                    </logic:equal>
                    <tr>
                        <td valign="top">Tipo de Retorno:</td>
                        <td>
                            <table>
                                <tr>
                                    <td valign=top colspan="2">
                                        <html:select name="Form" property="tipoComunicacaoSelecionada" styleId="tipoComunicacaoSelecionada" onchange="obtemComunicacaoIFrame(this,document.forms['registrarContatoForm2'].comunicacaoDisponivel);">
                                            <html:option value="-1">-- Selecione --</html:option>
                                            <logic:present name="aComunicacao">
                                            	<html:options collection="aComunicacao" property="idTipoComunicacao" labelProperty="descricao"/>
                                            </logic:present>	
                                        </html:select>
                                    </td>
                                    <td valign=top colspan="3">
                                        <img name="customerProfile" src="<%=request.getContextPath()%>/resources/images/lupa_bg_ffffff.gif" onclick="abreCustomerProfile();" style="cursor:pointer">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>Retorno:</td>
                        <td align="left" colspan="6">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="left">
                                        <html:select name="Form" property="comunicacaoDisponivel" styleId="comunicacaoDisponivel" multiple="true" size="6" style="width=150px"/>
                                    </td>
                                    <td align="center" width="70">
                                        <vivo:botao id="botaoTransfere" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista($('comunicacaoDisponivel'), $('comunicacaoSelecionada'));"/>
                                        <vivo:botao id="botaoApaga" width="25px" height="20px" value="&lt;" styleClass="btTemplate" onclick="transfereSelecaoLista($('comunicacaoSelecionada'), $('comunicacaoDisponivel'));"/>
                                    </td>
                                    <td align="right">
                                        <html:select name="Form" property="comunicacaoSelecionada" styleId="comunicacaoSelecionada" multiple="true" size="6" style="width=150px">
                                        <logic:present name="comunicacaoSelecionada">
                                        	<html:options collection="aComunicacaoSel" property="dsAuxiliar" labelProperty="dsComunicacao" />
                                        </logic:present>
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            <br><br>
        </html:form>

        <iframe id="ifrmObtemLinhas" name="ifrmObtemLinhas" style="visibility:hidden;" width="1" height="1"></iframe>
        <iframe id="ifrmObtemTipoComunicacao" name="ifrmObtemTipoComunicacao" style="visibility:hidden;" width="1" height="1"></iframe>
        <iframe id="ifrmRegistrarContato" name="ifrmRegistrarContato" style="visibility:hidden;" width="1" height="1"></iframe>
        <script language="javascript">
            conteudo = "<table cellspacing='0' cellpadding='0' border='0'><tr>";
        </script>
        <span id="spanAcoes" style="display:none">
        	<c:catch var="spanAcoes">
            <logic:iterate id="acoeVO" name="AtendimentoVO" property="WFAcaoVOArray" indexId="idxAcoe">
                <%WFAcaoVO wFAcaoVO = (WFAcaoVO)acoeVO;%>
                <%String acao = wFAcaoVO.getDsAtividade();
                if( acao != null && acao.equalsIgnoreCase("REGISTRAR") ){
                    acao = "TABULAR";
                }
                if( acao != null && acao.equalsIgnoreCase("ENCAMINHAR") ){
                    acao = "ENCAMINHAR SOLIC. PARA BKO";
                }
                String size = "100px";
                if (acao.length()>12)
                    size = "180px";%>
                <%String js = wFAcaoVO.getJsAtividade();%>
                <td align="left" id="<%=acao%>">
                	<vivo:botao id="btFec" width="<%=size%>" height="10px" value="<%=acao%>" styleClass="btTemplate" onclick="<%=js%>"/>
               	</td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;
            </logic:iterate>
            </c:catch>
        </span>
        <script language="javascript">
            document.write("<div id=\"ttip\" style=\"display:none;position:absolute;max-width:200px;\"><\/div>");

            var moveToolTip = true;;

            xBump=yBump=10;
            MSIE=document.all;
            NS6=document.getElementById&&!document.all;
            if(MSIE||NS6){
            	ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
            }

            function carregaToolTip(content) {
	            ttipObj.innerHTML=content;
	            ttipObj.style.display='';
            }

            conteudo = conteudo+ document.getElementById('spanAcoes').innerHTML +"</tr></table>"
            parent.tdBotoesAcao.innerHTML = conteudo;

            var botoesAcoes = new Array(
            <logic:iterate id="acoeVO" name="AtendimentoVO" property="WFAcaoVOArray" indexId="idxAcoe" type="br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO">
                document.all['<bean:write name="acoeVO" property="dsAtividade" />'],
            </logic:iterate>
            document.all['vazio']);

            parent.botoesAcoes = botoesAcoes;

            //seta o valor do campo que pode ser alterado(nomeContatoAlterado) com o valor do FormEntrada(imutável)
            if(typeof(top.frameApp.frameURA)!= "undefined"){
                if(top.frameApp.frameURA.document.forms[0].all['nomeContato'].value != ''){
                    document.all["nomeContato"][0].value = top.frameApp.frameURA.document.forms[0].all['nomeContato'].value;
                    document.all["nomeContato"][1].value = top.frameApp.frameURA.document.forms[0].all['nomeContato'].value;
                    document.all["nomeContatoAlterado"].value = top.frameApp.frameURA.document.forms[0].all['nomeContato'].value;
                }else{
                    document.all["nomeContato"][0].value='<bean:write name="Form" property="nomeContato"/>';
                    document.all["nomeContato"][1].value = '<bean:write name="Form" property="nomeContato"/>';
                    document.all["nomeContatoAlterado"].value = '<bean:write name="Form" property="nomeContatoAlterado"/>';
                }
            }else{
                document.all["nomeContato"][0].value='<bean:write name="Form" property="nomeContato"/>';
                document.all["nomeContato"][1].value = '<bean:write name="Form" property="nomeContato"/>';
                document.all["nomeContatoAlterado"].value = '<bean:write name="Form" property="nomeContatoAlterado"/>';
            }
            //seta o valor do hidden da descrição da árvore da página pai
            parent.document.all['descricaoCompleta'].value = document.all['descricaoCompleta'].value;

            function obterArvoreBaixa(){
                if(!validaFormulario()) return false;
                <logic:equal name="Form" property="carregaLinha" value="2">
                    if(document.all['contaSelecionada'].selectedIndex == 0 ){
                        alert("Selecione uma conta!");
                        return false;
                    }
                </logic:equal>
                <logic:equal name="Form" property="telContato" value="">
                    if(document.all['telContato'].value == '' ){
                        alert("Insira um telefone de contato!");
                        return false;
                    }
                </logic:equal>
                if(document.all['tipoComunicacaoSelecionada'].options.selectedIndex==0 || document.all['tipoComunicacaoSelecionada'].options.value==-1){
                    if(document.all['comunicacaoSelecionada'].options.length==0){
                        alert("Escolha pelo menos um Retorno no Tipo de Retorno!");
                        return false;
                    }
                }
                for( y = 0; y < registrarContatoForm2.comunicacaoSelecionada.options.length; y++ )
                    registrarContatoForm2.comunicacaoSelecionada.options[y].selected = true;

                for(x=0;x<$('nrLinhasSelecionadas').options.length;x++){
                    $('nrLinhasSelecionadas').options[x].selected = true;
                }

                idxTipoComunic = registrarContatoForm2.tipoComunicacaoSelecionada.options.selectedIndex;
                registrarContatoForm2.tipoComunicacaoSelecionada.options[idxTipoComunic].selected = true;
                if(registrarContatoForm2.comunicacaoSelecionada.options.length == 0 && idxTipoComunic > 0 && registrarContatoForm2.tipoComunicacaoSelecionada.options[idxTipoComunic].value != "0"){
                    //Ricardo
                    alert("Escolha pelo menos um Retorno no Tipo de Retorno!");
                    //alert("Escolha pelo menos um Retorno ou escolha a opção Sem Retorno no Tipo de Retorno!");
                    return false;
                }

                for(x=0;x<$('nrLinhasSelecionadas').options.length;x++){
                    $('nrLinhasSelecionadas').options[x].selected = true;
                }

                idxCanal = registrarContatoForm2.idCanal.options.value;
                if(idxCanal<1){
                    alert("Escolha um canal!");
                    return false;
                }
                idxProcedencia = registrarContatoForm2.idProcedencia.options.value;
                if(idxProcedencia<1){
                    alert("Escolha uma procedência!");
                    return false;
                }
                <logic:equal name="Form" property="carregaLinha" value="1">
                   document.all['contaSelecionada'].value = <bean:write name="Form" property="idConta" />;
                   document.all['linhaSelecionada'].value = <bean:write name="Form" property="idLinha" />;
                </logic:equal>

                document.all["nomeContato"][0].value = document.all["nomeContato"][1].value;
                <logic:equal name="Form" property="carregaLinha" value="2">
                    idxConta = registrarContatoForm2.contaSelecionada.options.selectedIndex;
                    if(idxConta>0) registrarContatoForm2.contaSelecionada.options[idxConta].selected = true;

                    idxLinha = registrarContatoForm2.linhaSelecionada.options.selectedIndex;
                    if(idxLinha>0) registrarContatoForm2.linhaSelecionada.options[idxLinha].selected = true;
                </logic:equal>
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                /*document.forms["registrarContatoForm2"].target = "ifrmAtendimento";
                document.forms["registrarContatoForm2"].action = "obterArvoreBaixa.do";
                document.forms["registrarContatoForm2"].submit();*/
                //$('ifrmAtendimento').src = "obterArvoreBaixa.do?"+$('registrarContatoForm2').serialize();
                parent.document.all['ifrmAtendimento'].src = "obterArvoreBaixa.do?"+$('registrarContatoForm2').serialize(); 
                return true;
            }

            function obtemLinhasIFrame(conta) {
                if( top.dvAnimarAguarde != null ) top.startAnimation();
                //Verifica se a pessoa selecionou a opção -- Selecione--
                if(conta.selectedIndex == 0) return false;
                //Monta o path seleção
                /*document.forms["registrarContatoForm2"].target = "ifrmRegistrarContato";
                document.forms["registrarContatoForm2"].action = "obterLinhas.do?INDEX=" + (conta.selectedIndex - 1);
                document.forms["registrarContatoForm2"].submit();*/
                $('ifrmObtemLinhas').src = "obterLinhas.do?contaSelecionada="+$F('contaSelecionada')+"&idLinha="+$F('idLinha');
            }

            function obtemTipoComunicacaoIFrame() {
                //Monta o path seleção
                /*document.forms["registrarContatoForm2"].target = "ifrmRegistrarContato";
                document.forms["registrarContatoForm2"].action = "obterTipoComunicacao.do";
                document.forms["registrarContatoForm2"].submit();*/
                $('ifrmObtemTipoComunicacao').src = "obterTipoComunicacao.do?idPessoa="+$F('idPessoa');
            }

            function obtemComunicacaoIFrame(tipoComunicacao, comunicacaoDisponivel) {
                /*
                while(document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"].options.length != 0)
                        document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"].options.remove(0);
                */
                //Verifica se a pessoa selecionou a opção -- Sem Retorno--
                //if (tipoComunicacao.options[tipoComunicacao.selectedIndex].text == "Sem Retorno"
                //    || tipoComunicacao.options[tipoComunicacao.selectedIndex].text == "-- Selecione --") {
                if(tipoComunicacao.value < 1){
                    //limpa o combo de comunicação disponivel
                    while(document.forms["registrarContatoForm2"].elements["comunicacaoDisponivel"].options.length != 0)
                        document.forms["registrarContatoForm2"].elements["comunicacaoDisponivel"].options.remove(0);
                    //limpa o combo de comunicação disponivel
                    while(document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"].options.length != 0)
                        document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"].options.remove(0);
                    return false;
                }
                /* Na aba Contato não havia opção "Celular" vinda do serviço.
                 * Logo, se o usuário trocar de opções nos combos em "Tipo de Retorno",
                 * para a opçao "Celular" o seu número será recuperado da tela de atendimento,
                 * não chamando novamente o serviço.
                 */

                if (top.frameApp.document.getElementById("pesquisa")
                    && top.frameApp.document.getElementById("pesquisa").value != "naoCliente"
                    && parent.inCelular == false
                    && parent.inCelAlready == false
                    && tipoComunicacao.options[tipoComunicacao.selectedIndex].text.toUpperCase() == "CELULAR"
                    && top.frameApp.idRelacionamento != undefined
                    && top.frameApp.idRelacionamento != 6) {

                    while(document.forms["registrarContatoForm2"].elements["comunicacaoDisponivel"].options.length != 0)
                        document.forms["registrarContatoForm2"].elements["comunicacaoDisponivel"].options.remove(0);

                    var aOptComponentsParentSelec = document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"];
                    /* aOptComponentsParentSelec.options[0] = new Option(parent.nrCelular, parent.nrCelular); */

                    var inCreateCell = true;
                    for(i=0; i<aOptComponentsParentSelec.length; i++){
                        if(aOptComponentsParentSelec.options[i].value == parent.nrCelular){
                            inCreateCell = false;
                        }
                    }
                    if(inCreateCell){
                        oOption = document.createElement("OPTION");
                        aOptComponentsParentSelec.options.add(oOption);
                        oOption.innerText = parent.nrCelular;
                        oOption.value     = parent.nrCelular;
                    }
                }else{
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    /*document.forms["registrarContatoForm2"].target = "ifrmRegistrarContato";
                    document.forms["registrarContatoForm2"].action = "obterComunicacao.do";
                    document.forms["registrarContatoForm2"].submit();*/
                    $('ifrmRegistrarContato').src = "obterComunicacao.do?idPessoa="+$F('idPessoa')+"&tipoComunicacaoSelecionada="+$F('tipoComunicacaoSelecionada');
                }
            }

            //copia os elementos selecionados para o combo destino, mas não os apaga do combo origem
            function transfereSelecaoLista(listaOrigem, listaDestino) {
                var i;
                for(i = 0; i<listaOrigem.options.length; i++){
                    if(listaOrigem.options[i].selected && testaDestino(listaOrigem.options[i].value)){
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    if(listaOrigem.options[i].selected){
                        listaOrigem.options[i] = null;
                    }
                }
            }

            //testa se o elemento selecionado do combo origem já está no combo destino
            function testaDestino(valor){
                var i;
                var idTipoComunicacao = '';
                var indice = $('tipoComunicacaoSelecionada').options.selectedIndex;
                for(i=0;i<valor.length-1;i++){
                    if (valor.charAt(i) == ','){
                        break;
                    }
                    idTipoComunicacao += valor.charAt(i)
                }
                if (idTipoComunicacao == $('tipoComunicacaoSelecionada').options[indice].value){
                    return true;
                }else{
                    return false;
                }
            }

            function removeSelecaoLista(listaSelecionada){
                var i;
                for(i = listaSelecionada.options.length-1; i>=0; i--)
                    if(listaSelecionada.options[i].selected) listaSelecionada.options[i] = null;
                return false;
            }

            encaminharProcesso = function() {
                if (!validaFormulario()) return false;
                <logic:equal name="Form" property="carregaLinha" value="2">
                    //alert(document.all['contaSelecionada'].selectedIndex);
                    if(document.all['contaSelecionada'].selectedIndex == 0 ){
                        alert("Selecione uma conta!");
                        return false;
                    }
                </logic:equal>

                <logic:equal name="Form" property="telContato" value="">
                    if(document.all['telContato'].value == '' ){
                        alert("Insira um telefone de contato!");
                        return false;
                    }
                </logic:equal>

                if(document.all['tipoComunicacaoSelecionada'].options.selectedIndex==0 || document.all['tipoComunicacaoSelecionada'].options.value==-1){
                    if(document.all['comunicacaoSelecionada'].options.length==0){
                        alert("Escolha pelo menos um Retorno no Tipo de Retorno!");
                        return false;
                    }
                }

                document.all["nomeContato"][0].value = document.all["nomeContato"][1].value;
                for( y = 0; y < document.all['comunicacaoSelecionada'].options.length; y++ )
                    document.all['comunicacaoSelecionada'].options[y].selected = true;

                idxTipoComunic = document.all['tipoComunicacaoSelecionada'].options.selectedIndex;
                document.all['tipoComunicacaoSelecionada'].options[idxTipoComunic].selected = true;

                if(document.all['comunicacaoSelecionada'].options.length == 0 && idxTipoComunic > 0 && document.all['tipoComunicacaoSelecionada'].options[idxTipoComunic].value != "0"){
                    //Ricardo
                    //alert("Escolha pelo menos um Retorno ou escolha a opção Sem Retorno no Tipo de Retorno!");
                    alert("Escolha pelo menos um Retorno no Tipo de Retorno!");
                    return false;
                }

                for(x=0;x<$('nrLinhasSelecionadas').options.length;x++){
                    $('nrLinhasSelecionadas').options[x].selected = true;
                }

                idxCanal = document.all['idCanal'].options.value;
                if(idxCanal<1){
                    alert("Escolha um canal!");
                    return false;
                }
                idxProcedencia = document.all['idProcedencia'].options.value;
                if(idxProcedencia<1){
                    alert("Escolha uma procedência!");
                    return false;
                }

                <logic:equal name="Form" property="carregaLinha" value="1">
                    document.all['contaSelecionada'].value = <bean:write name="Form" property="idConta" />;
                    document.all['linhaSelecionada'].value = <bean:write name="Form" property="idLinha" />;
                </logic:equal>
                <logic:equal name="Form" property="carregaLinha" value="2">
                    idxConta = document.all['contaSelecionada'].options.selectedIndex;
                    if(idxConta>0) document.all['contaSelecionada'].options[idxConta].selected = true;
                    idxLinha = document.all['linhaSelecionada'].options.selectedIndex;
                    if(idxLinha>0) document.all['linhaSelecionada'].options[idxLinha].selected = true;
                </logic:equal>
                document.all['tipoOperacao'].value = "2";

                var queryString = '?inCelular=' + parent.inCelular;
                <logic:notEmpty name="Form" property="abreProcessoReaRei">
                    queryString += '&OPER=RA';
                </logic:notEmpty>

                if (top.frameApp.$('divPopupTI') && !top.frameApp.$('divPopupTI').visible()) {
                    submitProcessoAjax(queryString + '&inAjax=true');
                    parent.escondeBotoes();
                    return true;
                } else {
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    /*document.forms["registrarContatoForm2"].target = "ifrmNumProc";
                    document.forms["registrarContatoForm2"].action = "encaminharProcesso.do" + queryString + "&inAjax=false";
                    document.forms["registrarContatoForm2"].method = 'POST';
                    document.forms["registrarContatoForm2"].submit();*/
                    parent.document.all['ifrmNumProc'].src = "encaminharProcesso.do" + queryString + "&inAjax=false&" + $('registrarContatoForm2').serialize();
                    return true;
                }
            };

            function fecInmediato() {
                if(!validaFormulario()) return false;
                document.all["nomeContato"][0].value = document.all["nomeContato"][1].value;
                <logic:equal name="Form" property="carregaLinha" value="2">
                    if(document.all['contaSelecionada'].selectedIndex == 0 ){
                        alert("Selecione uma conta!");
                        return false;
                    }
                </logic:equal>
                <logic:equal name="Form" property="telContato" value="">
                    if(document.all['telContato'].value == '' ){
                        alert("Insira um telefone de contato!");
                        return false;
                    }
                </logic:equal>

                for( y = 0; y < document.all['comunicacaoSelecionada'].options.length; y++ )
                    document.all['comunicacaoSelecionada'].options[y].selected = true;

                idxTipoComunic = document.all['tipoComunicacaoSelecionada'].options.selectedIndex;
                document.all['tipoComunicacaoSelecionada'].options[idxTipoComunic].selected = true;
                if (document.all['comunicacaoSelecionada'].options.length == 0 && idxTipoComunic > 0 && document.all['tipoComunicacaoSelecionada'].options[idxTipoComunic].value != "0"){
                    alert("Escolha pelo menos um Retorno no Tipo de Retorno!");
                    return false;
                }

                for(x=0;x<$('nrLinhasSelecionadas').options.length;x++){
                    $('nrLinhasSelecionadas').options[x].selected = true;
                }

                idxCanal = document.all['idCanal'].options.value;
                if(idxCanal<1){
                    alert("Escolha um canal!");
                    return false;
                }
                idxProcedencia = document.all['idProcedencia'].options.value;
                if(idxProcedencia<1){
                    alert("Escolha uma procedência!");
                    return false;
                }

                <logic:equal name="Form" property="carregaLinha" value="1">
                    document.all['contaSelecionada'].value = <bean:write name="Form" property="idConta" />;
                    document.all['linhaSelecionada'].value = <bean:write name="Form" property="idLinha" />;
                </logic:equal>

                <logic:equal name="Form" property="carregaLinha" value="2">
                    idxConta = document.all['contaSelecionada'].options.selectedIndex;
                    if(idxConta>0) document.all['contaSelecionada'].options[idxConta].selected = true;
                    idxLinha = document.all['linhaSelecionada'].options.selectedIndex;
                    if(idxLinha>0) document.all['linhaSelecionada'].options[idxLinha].selected = true;
                </logic:equal>

                document.all['tipoOperacao'].value = "1";

                var queryString = '';
                <logic:notEmpty name="Form" property="abreProcessoReaRei">
                    queryString += '&OPER=RA';
                </logic:notEmpty>

                if (top.frameApp.$('divPopupTI') && !top.frameApp.$('divPopupTI').visible()) {
                    submitProcessoAjax('?inAjax=true' + queryString);
                    parent.escondeBotoes();
                    return true;
                } else {
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    /*document.forms["registrarContatoForm2"].target = "ifrmNumProc";
                    document.forms["registrarContatoForm2"].action = "encaminharProcesso.do?inAjax=false" + queryString;
                    document.forms["registrarContatoForm2"].method = "POST";
                    document.forms["registrarContatoForm2"].submit();*/
                    parent.document.all['ifrmNumProc'].src = "encaminharProcesso.do?inAjax=false" + queryString + "&" + $('registrarContatoForm2').serialize();
                    return true;
                }

            }

            regContato = function () {
                if(!validaFormulario()) return false;

                document.all["nomeContato"][0].value = document.all["nomeContato"][1].value;
                <logic:equal name="Form" property="carregaLinha" value="2">
                    if(document.all['contaSelecionada'].selectedIndex == 0 ){
                        alert("Selecione uma conta!");
                        return false;
                    }
                </logic:equal>
                <logic:equal name="Form" property="telContato" value="">
                    if(document.all['telContato'].value == '' ){
                        alert("Insira um telefone de contato!");
                        return false;
                    }
                </logic:equal>
                for( y = 0; y < document.all['comunicacaoSelecionada'].options.length; y++ )
                    document.all['comunicacaoSelecionada'].options[y].selected = true;

                idxTipoComunic = document.all['tipoComunicacaoSelecionada'].options.selectedIndex;
                document.all['tipoComunicacaoSelecionada'].options[idxTipoComunic].selected = true;
                <logic:equal name="Form" property="carregaLinha" value="1">
                    document.all['contaSelecionada'].value = <bean:write name="Form" property="idConta" />;
                    document.all['linhaSelecionada'].value = <bean:write name="Form" property="idLinha" />;
                </logic:equal>
                <logic:equal name="Form" property="carregaLinha" value="2">
                    idxConta = document.all['contaSelecionada'].options.selectedIndex;
                    if(idxConta>0) document.all['contaSelecionada'].options[idxConta].selected = true;
                    idxLinha = document.all['linhaSelecionada'].options.selectedIndex;
                    if(idxLinha>0) document.all['linhaSelecionada'].options[idxLinha].selected = true;
                </logic:equal>

                for(x=0;x<$('nrLinhasSelecionadas').options.length;x++){
                    $('nrLinhasSelecionadas').options[x].selected = true;
                }

                document.all['tipoOperacao'].value = '1';

                var queryString = '';
                <logic:notEmpty name="Form" property="abreProcessoReaRei">
                    queryString += '&OPER=RA';
                </logic:notEmpty>

                if (top.frameApp.$('divPopupTI') && !top.frameApp.$('divPopupTI').visible()) {
                    submitProcessoAjax('?inAjax=true' + queryString + "&"+$('registrarContatoForm2').serialize());
                    parent.escondeBotoes();
                    return true;
                
                } else {
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    /*document.forms["registrarContatoForm2"].target = "ifrmNumProc";
                    document.forms["registrarContatoForm2"].action = "encaminharProcesso.do?inAjax=false" + queryString;
                    document.forms["registrarContatoForm2"].method = "POST";
                    document.forms["registrarContatoForm2"].submit();*/
                    parent.document.all['ifrmNumProc'].src = "encaminharProcesso.do?inAjax=false" + queryString + "&" + $('registrarContatoForm2').serialize();
                    return true;
                }

            };

            // Array p/ controle de ativa/desativa elementos dos forms
            frmsState = null;
            //desativa todos os combos
            function desativar_combos() {
                if(frmsState == null){
                    forms = document.forms;
                    frmsState = new Array(forms.length);
                    for (i=0;i<forms.length;i++) {
                        el = forms[i].elements;
                        elState = new Array(el.length);
                        frmsState[i] = elState;
                        for(j=0;j<el.length;j++){
                            elState[j]=el[j].disabled;
                            el[j].disabled=true;
                        }
                    }
                }
                return;
            }

			submitProcessoAjax = function(requestParameters) {

				var requestParametersString = requestParameters ? requestParameters : '';
				if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();

                new Ajax.Request('encaminharProcesso.do' + requestParametersString, {
                    method: 'post',
                    parameters: $('registrarContatoForm2').serialize(),
					onCreate: function() {
						if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
					
					}, onSuccess: function(transport) {
						var dom = parseXml(transport.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();

						if (jsonObj.AtendimentoVO) {
							if (jsonObj.AtendimentoVO.msgProtocolo) {
                                var filtro = top.frameCTI.filtro;
                                if (filtro && filtro.telaOrigem == 'FILA_MEU_CLIENTE') {
                                    extra_vars = '&' + filtro.queryString;
                                } else {
                                    extra_vars = '';
                                }

								var innerMessage = jsonObj.AtendimentoVO.msgProtocolo + "<img src=\"/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif\" style=\"cursor:pointer;display:block;margin:15px 0 0 120px\" ";
                                innerMessage += " onmouseup=\"top.frameApp.$('msgAtendimento').remove();";
                                innerMessage += " if(top.frameApp.ti_frameAbas){top.frameApp.ti_frameAbas.redimensionaFrames('botaoMeioArvore');}";
                                innerMessage += " else{if(top.frameCTI.filtro != undefined && top.frameCTI.filtro.telaOrigem == 'FILA_MEU_CLIENTE'){";
                                innerMessage += " top.frameApp.location.href='<bean:write name="Form" property="fila"/>?voltar=1"+extra_vars+"'; }};\" />";

                                top.frameApp.createNewPopUpText('msgAtendimento', '', 300, 150, null, true, innerMessage, '', 'font-size:13px');
							}

							// Atualiza tela de atendimento com número do protocolo gerado
							if (top.frameApp.nrProtocolo == '' && jsonObj.AtendimentoVO.nrProtocolo && jsonObj.AtendimentoVO.nrProtocolo != '') {
								top.frameApp.nrProtocolo = jsonObj.AtendimentoVO.nrProtocolo;
								top.frameApp.$('nrProtocolo').update(jsonObj.AtendimentoVO.nrProtocolo);
								top.frameApp.setAlteracaoSMSSolicitacaoProtocolo('alteracaoSMS');
							}

			                if (parent.dvAtendimento) {
								parent.dvAtendimento.style.display = 'none';
			                } else {
			                    parent.$('divPopupTI').style.display = 'none';
							}

							// Identifica que chamada é proveniente de alguma fila
							if (jsonObj.AtendimentoVO.observacao) {
								parent.parent.voltar();
							
							} else {
				                //Atualiza a lista de processos correntes
				                if (jsonObj.AtendimentoVO.idAtendimento != 0) {
									parent.ifrmAbas.listarProcessos(jsonObj.AtendimentoVO.idAtendimento, jsonObj.AtendimentoVO.ArvoreAtendimentoVO.descricaoCompleta, jsonObj.AtendimentoVO.nrProtocolo);
									parent.document.all['abaPCorrentes'].click();
									parent.ifrmNumProc.document.location.replace("about:blank");
									parent.ifrmAtendimento.document.location.replace("about:blank");

				                } else {
									parent.ifrmArvore.iniciaAtendimento(top.frameApp.idContato, '', jsonObj.AtendimentoVO.inResponsavelAbertura);
								}
							}
						}

                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                    }, onComplete: function(transport) {
						if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
					}
                });
			};

            //ativa todos o s combos
            function ativar_combos() {
                var IfrRef = parent.document.getElementById('divPesquisaForm');
                IfrRef.style.display = "none";
                if (frmsState != null) {
                    forms = document.forms;
                    for (i=0;i<forms.length;i++) {
                        el = forms[i].elements;
                        elState = frmsState[i];
                        frmsState[i] = elState;
                        for(j=0;j<el.length;j++){
                            el[j].disabled=elState[j];
                        }
                    }
                }
                frmsState = null;
                return;
            }

            function listarProcessos(idAtendimento, descricaoArvore, nrProtocolo) {
                var parentTable = ifrmListaProcessos.document.all['dvListaProcessos_body'];
                //var imagem = document.createElement("<IMG SRC='/FrontOfficeWeb/resources/images/bt_icon_excluir.gif' border='0' onclick='excluir(this)' align='center' valign='center'>");
                var myTR = parentTable.insertRow(0);
                myTR.className = "rowTabela";
                var tdImagem = myTR.insertCell(0);
                tdImagem.width="20%";
                //tdImagem.insertAdjacentElement("afterBegin", imagem);
                tdImagem.innerHTML = "<center><center><IMG SRC='/FrontOfficeWeb/resources/images/bt_icon_excluir.gif' border='0' onclick='excluir("+idAtendimento+",this)' align='center'></center></center>";
                var tdId=myTR.insertCell(1);
                tdId.width="20%";
                tdId.innerHTML = "<center>"+nrProtocolo+"</center>";
                var tdDescricao = myTR.insertCell(2);
                tdDescricao.width="60%";
                tdDescricao.title=descricaoArvore;
                var texto = '';
                var textoCortado = '';
                var longitud = 0;
                var inTextoCortado = false;
                for (i=0; i<descricaoArvore.length;i++){
                    if(descricaoArvore.charAt(i) == '/'){
                        texto += ' ';
                        longitud = 0;
                    }
                    if(descricaoArvore.charAt(i) == ' ') longitud = 0;
                    texto += descricaoArvore.charAt(i);
                    if (descricaoArvore.charAt(i) == '/'){
                        texto += ' ';
                    }
                    if(i == 25){
                        for (j=0; j<25;j++){
                            textoCortado += texto.charAt(j);
                        }
                        textoCortado += "...";
                    }
                    if(longitud >= 25) inTextoCortado = true;
                    longitud ++;
                }
                if (inTextoCortado) texto = textoCortado;
                tdDescricao.innerHTML = "<center>"+texto+"</center>";
                //document.forms["registrarContatoForm2"].target = "ifrmListaProcessos";
                //document.forms["registrarContatoForm2"].action = "listarProcessos.do";
                //document.forms["registrarContatoForm2"].submit();
            }

            function abreCustomerProfile(){
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                parent.dvLupa.style.display = '';
                document.forms["registrarContatoForm2"].target = "ifrmLupa";
                document.forms["registrarContatoForm2"].action = "/FrontOfficeWeb/extworkflw/RegistrarContato/loadContato.do?idPessoaCliente=<bean:write name="Form" property="idPessoa" />";
                document.forms["registrarContatoForm2"].submit();
            }

            function validaCampo(campo, tipoCampo){
                switch(tipoCampo){
                    case 'telefone':
                        if(campo.value == '')
                            return;
                        maskPhoneNumberObj(campo);
                        if(campo.value.length < 13){
                            campo.value = '';
                            campo.focus();
                            alert("Linha inválida");
                        }
                        break;

                    case 'telefone_rural':
                        if(campo.value == '')
                            return;
                        maskPhoneNumberObj(campo);
                        if(campo.value.length < 13){
                            campo.value = '';
                            campo.focus();
                            alert("Linha inválida");
                        }
                        break;

                    case 'texto':
                        return;
                        break;
                    case 'data':
                        if(campo.value == '')
                            return false;
                        if(!validate_date(campo.value)){
                            campo.value = '';
                            campo.focus();
                            alert("Data inválida");
                        }
                        break;

                    case 'hora':
                        if(campo.value == '')
                            return false;
                        if(!validaHora(campo.value)){
                            campo.value = '';
                            campo.focus();
                        }
                        break;

                    case 'cpf':
                        if(campo.value == '')
                            return false;
                        if(!VerificaCPF(campo.value)){
                            campo.value = '';
                            campo.focus();
                            alert("CPF inválido");
                        }
                        break;

                    case 'cnpj':
                        if(campo.value == '')
                            return false;
                        if(!VerificaCNPJ(campo.value)){
                            campo.value = '';
                            campo.focus();
                            alert("CNPJ inválido");
                        }
                        break;
                }
            }

            //caso seja cliente
            <logic:equal name="Form" property="carregaLinha" value="2">
                //força o selected da conta de acordo com o idConta que veio da tela inicial
                var contaGrupo = 1;
                for(i=0;i<document.all['contaSelecionada'].options.length;i++){
                    if(document.all['contaSelecionada'].options[i].value == '<bean:write name="Form" property="idConta"/>'){
                        document.all['contaSelecionada'].options[i].selected = true;
                        contaGrupo = 0;
                    }
                }
                if (contaGrupo==1) {
                    document.all['contaSelecionada'].options[1].selected = true;
                }
                obtemLinhasIFrame(document.all['contaSelecionada']);
                //força o selected da linha de acordo com o idLinha que veio da tela inicial
                
                function pintaLinhaSelecionada(){
                    for( i=0; i < document.all['linhaSelecionada'].options.length; i++ ){
                        if(document.all['linhaSelecionada'].options[i].value == '<bean:write name="Form" property="idLinha"/>'){
                            document.all['linhaSelecionada'].options[i].selected = true;
                        }
                    }
                }
            </logic:equal>

            function mostraPesquisaFormulario(idCampo, nomeCampo, campo){
                desativar_combos();
                var IfrRef = parent.document.getElementById('divPesquisaForm');
                parent.ifrmPesqForm.vaciarLista();
                parent.ifrmPesqForm.document.all['tdCampo'].innerText = nomeCampo;
                parent.ifrmPesqForm.document.all['idCampo'].value = idCampo;
                parent.ifrmPesqForm.document.all['nomeCampo'].value = campo.parentElement.parentElement.all['tdValorCampo'].childNodes(0).name;
                parent.dvPesqForm.style.display = '';
                IfrRef.style.width = parent.dvPesqForm.offsetWidth;
                IfrRef.style.height = parent.dvPesqForm.offsetHeight;
                IfrRef.style.top = parent.dvPesqForm.style.top;
                IfrRef.style.left = parent.dvPesqForm.style.left;
                IfrRef.style.zIndex = parent.dvPesqForm.style.zIndex - 1;
                IfrRef.style.filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';
                IfrRef.style.display = '';
            }

            //Habilita para edição
            parent.clickAba = true;
            //Monta a aba inicial para preenchimento do formulário;
            parent.document.all['abaFormulario'].click();
            <logic:notEmpty name="Form" property="abreProcessoReaRei">
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            </logic:notEmpty>
            
            //scriptCarrega
            <%=scriptCarrega%>
            
            //scriptValidacao
            <%=scriptValidacao%>
            
            if (document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"].options[0]!=null && document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"].options[0].value==""){
                while(document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"].options.length != 0)
                    document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"].options.remove(0);
            }
        </script>

    </body>
</html:html>
