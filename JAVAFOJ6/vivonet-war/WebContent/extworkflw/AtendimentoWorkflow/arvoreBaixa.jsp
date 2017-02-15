<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm" />
<bean:define id="scriptArvoreBaixa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.scriptArvoreBaixa" />
<bean:define id="scriptValidacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.scriptValidacao" />
   

<%
    String altura = "226";
    String largura = "755";
%>
<logic:equal name="form" property="idAtendimento" value="0">
<%
   altura = "440";
   largura = "770";
%>

</logic:equal>
<vivo:body idTable="tbMain" idDiv="dvMain" height="<%=altura%>" width="<%=largura%>">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css">
    <script src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>

    <script>
        var flUltimoNivel = false;
        var nrUltimoNivel = 1;
        var qtdeNiveis = 1;
    </script>

    <form action="listaEncerrarGravar.do" method="post" name="AtendimentoForm">
        <html:hidden name="form" property="idBaixa"/>
        <html:hidden name="form" property="idBaixaMensagem"/>
        <html:hidden name="form" property="documentoAssociado"/>
        <html:hidden name="form" property="idAtendimento"/>
        <html:hidden name="form" property="idAtividade"/>
        <html:hidden name="form" property="inCRI"/>
        <html:hidden name="form" property="inTratamentoUsuario"/>
        <html:hidden name="form" property="idContato"/>
        <html:hidden name="form" property="idTipoComunicacao"/>
        <html:hidden name="form" property="fila"/>
        <html:hidden name="form" property="fechamentoMassa"/>
        <html:hidden name="form" property="numeroCampos"/>
        <div id="divQuest" style="width:745px;height:220px;overflow-y:auto;overflow-x:auto">
            <table width="720" border="0" bgcolor="#E3ECF4" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="top" width="205" nowrap>
                        <div style="width:200px;height:120px;overflow:auto;">
                            <script>
                                 <%=scriptArvoreBaixa%>    
                            </script>
                        </div>
                    </td>
                    <td valign="top" width="515" nowrap>
                        <table width="100%" border="0" bgcolor="#E3ECF4">
                            <logic:notEmpty name="form" property="dsTipoComunicacao">
                            <tr>
                                <td width="125" align="left">Tipo Comunicação:</td>
                                <td width="200" align="left"><b><html:text name="form" property="dsTipoComunicacao" readonly="true" style="width=200"/></td>
                            </tr>
                            </logic:notEmpty>
                            <tr>
                                <td width="125" align="left">Código de Baixa:</td>
                                <td width="200" align="left" style="padding-left:4px;"><b><div id="dvCodigoBaixa"></div></b></td>
                            </tr>
                            <tr>
                                <td>Resposta Padrão:</td>
                                <td style="padding-left:4px;"><div id="dvRespostaPadrao"></div></td>
                            </tr>
                            <logic:equal name="form" property="docTec" value="1">
                            <tr>
                                <td>Documento Associado:</td>
                                <td><img id="imgLupa" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="lupaDocumentosTecnicos()" style='cursor:pointer' border="0"></td>
                            </tr>
                            </logic:equal>
                            <tr>
                                <td colspan="2">
                                    <table width="100%">
                                    <logic:notEmpty name="form" property="arvoreEncerramentoVO.formularioVO">
                                        <jsp:include page="FormularioDinamico.jsp" />
                                    </logic:notEmpty>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="left">
                        Comentário:<br/>
                        <html:textarea name="form" property="comentario" style="width:720px;" rows="4"  onblur="return checaTextarea(this,1000);return false;"  onkeyup="return checaTextarea(this,1000);return false;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right" style="padding-right:10px;">                    
                    <img onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/> 
                    <logic:equal name="form" property="idAtendimento" value="0">
                        <img onClick="submitFechar(); return false" style="border:none;" id="btFechar" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif"/> 
                    </logic:equal>                    
                    </td>
                </tr>
            </table>
        </div>
    <iframe scrolling="yes" style="display:none;" name="iframeArvoreBaixa" height="0" width="0"></iframe>    
    </form>
    
<script>

    function checaTextarea(obj, tamanho){
      if(obj.value.length>tamanho){
        obj.value = obj.value.substr(0,tamanho);
        alert("O campo comentário não aceita mais de "+tamanho+" caracteres");
      }
    }
    
    
    var tmpArvore = null;

    function expandirNo(idBaixa) {
        
        if (!tree.getSelected().isAddEnabled()) {
            return;
        }

        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                
        document.forms[0].idBaixa.value = idBaixa;
        document.forms[0].method = "POST";
        document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/obtemArvoreBaixaParte.do";
        document.forms[0].target = "iframeArvoreBaixa";
        document.forms[0].submit();

    }

    function mostraPesquisaFormulario(idCampo, nomeCampo, campo){
        var IfrRef = parent.document.getElementById('ifrmPesqForm');
        desativar_combos();
        parent.ifrmPesqForm.document.all['tdCampo'].innerText = nomeCampo;
        parent.ifrmPesqForm.document.all['idCampo'].value = idCampo;
        parent.ifrmPesqForm.document.all['nomeCampo'].value = campo.parentElement.parentElement.all['tdValorCampo'].childNodes(0).name;
        parent.ifrmPesqForm.vaciarLista();
        parent.dvPesqForm.style.display = '';
    }

     // Array p/ controle de ativa/desativa elementos dos forms
     frmsState = null;
 
     //desativa todos os combos
    function desativar_combos() {
        parent.desativar_combos();
        if (frmsState == null) {
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
        
    //ativa todos o s combos
    function ativar_combos() {
        parent.ativar_combos();
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

    function mostraDadosArvore(idBaixa, idBaixaMensagem, respostaPadrao, idTipoContato) {
        
        document.all['dvCodigoBaixa'].innerText = idBaixa;
        
        document.forms[0].idBaixa.value = idBaixa;
        document.forms[0].idBaixaMensagem.value = idBaixaMensagem;
        document.all["dvRespostaPadrao"].innerText = respostaPadrao;
        
    }
    
    function lupaDocumentosTecnicos() {

        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        // ativa quadro flutuante para Documentos Tecnicos
        parent.qdDocTec.style.display='';
        
        document.forms[0].method = "POST";
        document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/documentoAssociadoBegin.do?docTec=1";
        document.forms[0].target = "ifrmDocTec";
        document.forms[0].submit();
    }
    
    function initPagina() { }

    function submitAplicar() {

        var erroStr = "Corrija os seguintes problemas:\n\n";
        var erro = false;
        
        if (document.forms[0].idBaixa.value == "") {
            erroStr += "Selecione um item válido na Árvore de Baixa!\n";
            erro = true;
        }
        else if (document.forms[0].idBaixaMensagem.value == "") {
            erroStr += "Selecione um item válido na Árvore de Baixa!\n";
            erro = true;
        }
                
        else if (!validaFormulario()) {
            return false;
        }
        
        if (!erro) {
        
            //Inicio animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

            if (parent.dvRetornoOperacao) {
                parent.dvRetornoOperacao.style.visibility="";
                targetFrame = "iframeRetornoOperacao";
            } else { 
                targetFrame = "";
            }
            
            document.forms[0].method = "POST";
            
            
            <logic:notEmpty name="ENCERRAROV" scope="request">
                document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/confirmaCancelamentoOV.do";            
            </logic:notEmpty>            
            <logic:empty name="ENCERRAROV" scope="request">
                document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/listaEncerrarGravar.do";
            </logic:empty>            

            document.forms[0].target = targetFrame;
            document.forms[0].submit();
        } else {
            alert(erroStr);
            return false;
        }
    }
    
    function iniciaEncerramento(codBaixa) {

        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
       
        document.forms[0].idBaixa.value = codBaixa;
        document.forms[0].method = "POST";
        document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/listaEncerrarPesquisarResposta.do";
        document.forms[0].target = "";
        document.forms[0].submit();

    }

    <%=scriptValidacao%>
    
    function validaCampo(campo, tipoCampo){
        switch(tipoCampo){
            case 'texto':
                return;
                break;
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
        
    function submitFechar() {
        parent.exibicaoAbaPesquisas(1);
        document.forms[0].method = "POST";
        document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/atendimentoWorkflowVoltar.do";
        document.forms[0].target = "iframeUsuario";
        document.forms[0].submit();
    }
        
    if (parent.up != null)
    {
        if (parent.up == false)
        {    
            document.getElementById('tbMain').height="352";
            document.getElementById('dvMain').style.height="345";
        }
    }           
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

    document.getElementById("dvMain").style.overflow = "hidden";

    function pesquisaEndereco() {
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();             
        var src = "/FrontOfficeWeb/cliente/PrePago/manutencaoPrePago/loadPesquisaEndereco.do?origem=FORMDINAMICOWF";
        window.top.frameApp.showPopupTI("Pesquisa de Endereços", 750, 230, null, src);
    }

    function getDadosAparelhos() {
        new Ajax.Request('getDadosAparelhos.do', {
            method: 'get',
            contentType: 'text/xml',
            parameters: {
                idAtendimento: document.forms[0].idAtendimento.value
            },
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
         * grupoCamposDependentes|idGrupoCamposDependentes|idCampo|nrNivel
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
        nmPath                = escape(nmPath);
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

</script>

    
    
</vivo:body>
   
