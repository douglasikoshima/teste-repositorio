<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prePagoForm" type="cliente.PrePago.PrePagoController.PrePagoForm" />
<bean:define id="list" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listasVO" type="br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value=""/>
    <netui-template:setAttribute name="modulo" value="PrePago"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/xtree.css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prepago.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/ie.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script type="text/javascript" language="javascript">
            <!--
                semCaracterEspeciais = new RegExp("[\"!@#$%&*{}_-¨^~´`?/><()=+;:?,.\\|\\\[\\\]]");
                function removeStrEspecial(obj){
                    valor = obj.value;
                    for(i=0;i<valor.length;i++){
                        if(semCaracterEspeciais.test(valor.charAt(i))){
                            valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                            i = -1;
                        }
                    }
                    obj.value = valor;
                }

                var inTipoPessoa  = "";
                var tpOperacao    = "<bean:write name="form" property="tpOperacao"/>";
                var inValidaLinha = "<bean:write name="form" property="isNrLinhaValid"/>";
                var inIncluiEndPC = 0;
                var inIncluiEndPU = 0;
                var inIncluiEndPJ = 0;

                if(top.frameApp!=null)
                    if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();

                function voltar(){
                    if(top.frameApp!=null){
                        mostrar_div();
                        <logic:equal value="C" name="form" property="tpOperacao">
                        top.frameApp.location.href = "/FrontOfficeWeb/cliente/TelaInicial/begin.do?inicioTela=TRUE";
                        </logic:equal>
                        <logic:notEqual value="C" name="form" property="tpOperacao">
                        top.frameApp.location.href = "/FrontOfficeWeb/cliente/TelaInicial/begin.do?nrLinha=" + <bean:write name="form" property="nrLinha"/>;
                        </logic:notEqual>
                    }
                }

                checkInsEst = function(obj) {
                    if (obj.options[obj.selectedIndex].text.toUpperCase() == 'ISENTO') {
                        $('nrInscricaoA').value = '';
                        $('nrInscricaoA').readOnly = true;
                        $('nrInscricaoA').style.visibility = 'hidden';
                    } else {
                        $('nrInscricaoA').readOnly = false;
                        $('nrInscricaoA').style.visibility = 'visible';
                        $('nrInscricaoA').focus();
                    }
                }

                onload = function() {

                    <% if (!"T".equals(form.getInSucesso()) && !"F".equals(form.getInSucesso())) { %>

                        if(top.frameApp!=null)
                            if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();

                        disableAllTabs();
                        $('btIncluirEndCliente').hide();
                        $('btIncluirEndUsuario').hide();
                        $('btIncluirEndPJ').hide();

                        <logic:notEqual value="" name="form" property="tpOperacao">
                            $('tbPessNrLinha').innerHTML     = "<bean:write name="form" property="nrLinha"/>";
                            $('tbPessCdSeguranca').innerHTML = "<bean:write name="form" property="cdSeguranca"/>";
                        </logic:notEqual>

                        <%-- // Caso seja Novo Cadastro --%>
                        <logic:equal value="C" name="form" property="tpOperacao">
                            $('idTipoDocumentoACli').disabled = true;
                            $('btImgGravar').hide();
                            $('btImgAvancar').hide();
                            $('dvRodape').hide();
                            $('PrePago').select('[class="sessaoOperacoes"]')[0].innerHTML = "Inclusão";
                            if(inValidaLinha=="F"){
                                $('tbPesquisa').show();
                                $('tbPessoa').hide();
                                $('nrLinha').focus();
                            }else if(inValidaLinha=="T"){
                                $('tbPessoa').show();
                                $('tbPesquisa').hide();
                                alert('Protocolo: <%= session.getAttribute(ConstantesCRM.NRPROTOCOLO)%>');
                            }
                            $('fPrePago').action = "execCadastro.do";
                            $('dvGravar').hide();
                        </logic:equal>

                        <%-- // Caso seja Alteracao --%>
                        <logic:equal value="A" name="form" property="tpOperacao">
                            $('PrePago').select('[class="sessaoOperacoes"]')[0].innerHTML = "Alteração";
                            loadDadosAlteracao();
                            $('tbPessoa').show();
                            $('tpPessoaF').disabled = true;
                            $('tpPessoaJ').disabled = true;
                            $('tbPessoa').show();
                            selecionaFormulario(inTipoPessoa);
                            $('fPrePago').action = "execAlteracao.do";
                            $('isUtilEndCliente').disabled=true;
                        </logic:equal>

                        <%-- // Caso seja Troca de Titularidade --%>
                        <logic:equal value="T" name="form" property="tpOperacao">
                            $('PrePago').select('[class="sessaoOperacoes"]')[0].innerHTML = "Troca de Titularidade";
                            $('tbTitNrLinha').innerHTML = formatPhoneNumber("<bean:write name="form" property="nrLinha"/>");
                            $('tbTitNmCliente').innerHTML = "<bean:write name="form" property="nmCliente"/>";
                            $('tbTitNmDoc').innerHTML = "<bean:write name="form" property="tpDocumento"/>";
                            $('tbTitNrDoc').innerHTML = "<bean:write name="form" property="nrDocumento"/>";
                            loadDadosTitularidade();
                            $('tbTitularidade').show();
                            $('tbPessoa').show();
                            $('fPrePago').action = "execTitularidade.do";
                        </logic:equal>

                    <% } %>

                    <logic:equal value="T" name="form" property="inSucesso">
                        alert('Dados gravados com sucesso.');
                        var nrProtocolo = '<%= session.getAttribute(ConstantesCRM.NRPROTOCOLO)!=null?session.getAttribute(ConstantesCRM.NRPROTOCOLO):""%>';
                        if(window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();
                        top.frameApp.location.href = "/FrontOfficeWeb/cliente/TelaInicial/begin.do?nrLinha=<bean:write name="form" property="nrLinha"/>&nrProtocolo="+nrProtocolo;
                    </logic:equal>
                    <logic:equal value="F" name="form" property="inSucesso">
                        if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                        alert("<bean:write name="msg"/>");
                        top.frameApp.location.href = "/FrontOfficeWeb/cliente/PrePago/begin.do?incluir=true";
                        if(window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();
                    </logic:equal>

                }

            -->
        </script>

        <script language="VBScript">
            function messageBox(pergunta)
                messageBox = msgbox(pergunta, 4, "Vivo Net")
            end function
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <!--acesso:controlHiddenItem nomeIdentificador="cli_ipp_verpagina"-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="3"></div>
        <vivo:sessao id="PrePago" width="790" height="470" label="Cliente Pré-Pago" operacoes="Aguarde...">
            <form name="fPrePago" id="fPrePago" action="" method="POST" style="margin:0px;">
                <!-- Campos para os dados de Cliente da Linha -->
                <input type="hidden" name="idPessoaCli"     id="idPessoaCli" value="">
                <input type="hidden" name="nmPessoaCli"     id="nmPessoaCli" value="">
                <input type="hidden" name="idSexoCli"       id="idSexoCli" value="">
                <input type="hidden" name="idDocumentoCliA" id="idDocumentoCliA" value="">
                <input type="hidden" name="idDocumentoCliB" id="idDocumentoCliB" value="">
                <input type="hidden" name="idTipoDocumentoCliA" id="idTipoDocumentoCliA" value="">
                <input type="hidden" name="idTipoDocumentoCliB" id="idTipoDocumentoCliB" value="">
                <input type="hidden" name="nrDocumentoCliA"   id="nrDocumentoCliA" value="">
                <input type="hidden" name="nrDocumentoCliB"   id="nrDocumentoCliB" value="">
                <input type="hidden" name="inDocumentoRGCli"  id="inDocumentoRGCli" value="">
                <input type="hidden" name="idUFDocumentoCli"  id="idUFDocumentoCli" value="">
                <input type="hidden" name="dsOrgaoEmissorCli" id="dsOrgaoEmissorCli" value="">
                <input type="hidden" name="dtExpedicaoCli"    id="dtExpedicaoCli" value="">
                <input type="hidden" name="dtNascimentoCli"   id="dtNascimentoCli" value="">

                <!-- Campos para os Outros dados de Cliente -->
                <input type="hidden" name="idContatoCliA" id="idContatoCliA" value="">
                <input type="hidden" name="idContatoCliB" id="idContatoCliB" value="">
                <input type="hidden" name="idContatoCliC" id="idContatoCliC" value="">

                <input type="hidden" name="idTipoTelefoneCliA" id="idTipoTelefoneCliA" value="">
                <input type="hidden" name="idTipoTelefoneCliB" id="idTipoTelefoneCliB" value="">
                <input type="hidden" name="idTipoTelefoneCliC" id="idTipoTelefoneCliC" value="">
                <input type="hidden" name="nrTelefoneCliA" id="nrTelefoneCliA" value="">
                <input type="hidden" name="nrTelefoneCliB" id="nrTelefoneCliB" value="">
                <input type="hidden" name="nrTelefoneCliC" id="nrTelefoneCliC" value="">
                <input type="hidden" name="nmContatoCliA" id="nmContatoCliA" value="">
                <input type="hidden" name="nmContatoCliB" id="nmContatoCliB" value="">
                <input type="hidden" name="nmContatoCliC" id="nmContatoCliC" value="">
                <input type="hidden" name="nmEmailParticularCli" id="nmEmailParticularCli" value="">
                <input type="hidden" name="nmEmailComercialCli" id="nmEmailComercialCli" value="">
                <input type="hidden" name="idEstadoCivilCli" id="idEstadoCivilCli" value="">
                <input type="hidden" name="idEscolaridadeCli" id="idEscolaridadeCli" value="">
                <input type="hidden" name="dsProfissaoCli" id="dsProfissaoCli" value="">
                <input type="hidden" name="idNatOcupacaoCli" id="idNatOcupacaoCli" value="">
                <input type="hidden" name="nrCPRCli" id="nrCPRCli" value="">

                <!-- Campos para os dados de Usuario da Linha -->
                <input type="hidden" name="idPessoaUsu" id="idPessoaUsu" value="">
                <input type="hidden" name="nmPessoaUsu" id="nmPessoaUsu" value="">
                <input type="hidden" name="idSexoUsu" id="idSexoUsu" value="">
                <input type="hidden" name="idDocumentoUsuA" id="idDocumentoUsuA" value="">
                <input type="hidden" name="idDocumentoUsuB" id="idDocumentoUsuB" value="">
                <input type="hidden" name="idTipoDocumentoUsuA" id="idTipoDocumentoUsuA" value="">
                <input type="hidden" name="idTipoDocumentoUsuB" id="idTipoDocumentoUsuB" value="">
                <input type="hidden" name="nrDocumentoUsuA" id="nrDocumentoUsuA" value="">
                <input type="hidden" name="nrDocumentoUsuB" id="nrDocumentoUsuB" value="">
                <input type="hidden" name="inDocumentoRGUsu" id="inDocumentoRGUsu" value="">
                <input type="hidden" name="idUFDocumentoUsu" id="idUFDocumentoUsu" value="">
                <input type="hidden" name="dsOrgaoEmissorUsu" id="dsOrgaoEmissorUsu" value="">
                <input type="hidden" name="dtExpedicaoUsu" id="dtExpedicaoUsu" value="">
                <input type="hidden" name="dtNascimentoUsu" id="dtNascimentoUsu" value="">
                <input type="hidden" name="inUtilEndCliente" id="inUtilEndCliente" value="">

                <!-- Campos para os Outros dados de Usuario -->
                <input type="hidden" name="idContatoUsuA" id="idContatoUsuA" value="">
                <input type="hidden" name="idContatoUsuB" id="idContatoUsuB" value="">
                <input type="hidden" name="idContatoUsuC" id="idContatoUsuC" value="">

                <input type="hidden" name="idTipoTelefoneUsuA" id="idTipoTelefoneUsuA" value="">
                <input type="hidden" name="idTipoTelefoneUsuB" id="idTipoTelefoneUsuB" value="">
                <input type="hidden" name="idTipoTelefoneUsuC" id="idTipoTelefoneUsuC" value="">
                <input type="hidden" name="nrTelefoneUsuA" id="nrTelefoneUsuA" value="">
                <input type="hidden" name="nrTelefoneUsuB" id="nrTelefoneUsuB" value="">
                <input type="hidden" name="nrTelefoneUsuC" id="nrTelefoneUsuC" value="">
                <input type="hidden" name="nmContatoUsuA" id="nmContatoUsuA" value="">
                <input type="hidden" name="nmContatoUsuB" id="nmContatoUsuB" value="">
                <input type="hidden" name="nmContatoUsuC" id="nmContatoUsuC" value="">
                <input type="hidden" name="nmEmailParticularUsu" id="nmEmailParticularUsu" value="">
                <input type="hidden" name="nmEmailComercialUsu" id="nmEmailComercialUsu" value="">
                <input type="hidden" name="idEstadoCivilUsu" id="idEstadoCivilUsu" value="">
                <input type="hidden" name="idEscolaridadeUsu" id="idEscolaridadeUsu" value="">
                <input type="hidden" name="dsProfissaoUsu" id="dsProfissaoUsu" value="">
                <input type="hidden" name="idNatOcupacaoUsu" id="idNatOcupacaoUsu" value="">
                <input type="hidden" name="nrCPRUsu" id="nrCPRUsu" value="">

                <!-- Campos para os dados de Pessoa Juridica -->
                <input type="hidden" name="idPessoaPJ" id="idPessoaPJ" value="">
                <input type="hidden" name="nrCNPJ" id="nrCNPJ" value="">
                <input type="hidden" name="nmFantasia" id="nmFantasia" value="">
                <input type="hidden" name="nmRazaoSocial" id="nmRazaoSocial" value="">
                <input type="hidden" name="nrCNAE" id="nrCNAE" value="">
                <input type="hidden" name="idTipoInscricao" id="idTipoInscricao" value="">
                <input type="hidden" name="nrInscricao" id="nrInscricao" value="">
                <input type="hidden" name="idClassTributaria" id="idClassTributaria" value="">
                <input type="hidden" name="idClassEmpresa" id="idClassEmpresa" value="">
                <input type="hidden" name="dtFundacao" id="dtFundacao" value="">
                <input type="hidden" name="nrCCM" id="nrCCM" value="">

                <input type="hidden" name="idContatoPJA" id="idContatoPJA" value="">
                <input type="hidden" name="idTipoTelefonePJ" id="idTipoTelefonePJ" value="">
                <input type="hidden" name="nrTelefonePJ" id="nrTelefonePJ" value="">
                <input type="hidden" name="nmContatoPJ" id="nmContatoPJ" value="">

                <input type="hidden" name="inTipoPessoa" id="inTipoPessoa" value="">
                <input type="hidden" name="inUsuario" id="inUsuario" value="">
                <input type="hidden" name="inSMSCli" id="inSMSCli" value="">
            </form>
            <%--//Dados apresentados quando a operacao a ser realizada seja Troca de Titularidade apresentando os dados do Cliente de Origem da Linha //--%>
            <logic:equal value="T" name="form" property="tpOperacao">
            <table id="tbTitularidade" width="760" class="tbl_bgGray" style="display:none;">
                <tr>
                    <td colspan="10" style="background-color:#1865C5;color:#ffffff;">&nbsp;<b>Cliente Origem</b></td>
                </tr>
                <tr>
                    <td align="right" width="40">&nbsp;<b>Linha:</b></td>
                    <td align="left"  width="80"><span id="tbTitNrLinha"></span></td>
                    <td align="right" width="95"><b>Nome do Cliente:</b></td>
                    <td align="left" colspan="4"><span id="tbTitNmCliente"></span></td>
                    <td align="right" width="35"><b><span id="tbTitNmDoc">CPF</span>:</b></td>
                    <td align="left" width="110"><span id="tbTitNrDoc"></span></td>
                    <td width="55"></td>
                </tr>
            </table>
            </logic:equal>
            <%--// //--%>
            <table border="0" width="760">
                <tr>
                    <td height="1"></td>
                </tr>
            </table>
            <%--//Dados Apresentados   //--%>
            <logic:equal value="C" name="form" property="tpOperacao">
            <form name="fValidaLinha" id="fValidaLinha" method="post" onsubmit="return false;" style="margin:0px;">
                <table id="tbPesquisa" width="760" height="29" cellpadding="0" cellspacing="0" class="tbl_bgGray" style="display:none;">
                    <tr>
                        <td colspan="3"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="7"></td>
                    </tr>
                    <tr>
                        <td align="left" colspan="2">
                            <input type="text" name="nrLinha" id="nrLinha" value="" maxlength="14" onkeypress="if(event.keyCode=='13'){return false;}" onkeyup="maskPhoneNumberObj(this)" />  <!--onkeyup="checaTelefone(this);" /> -->
                            &nbsp;
                            <img id="btValidarLinha" align="middle" onClick="validarLinha();" src="<%=request.getContextPath()%>/resources/images/bt_validarlinha_nrml.gif" style="border:none;cursor:hand;" hspace="5"/>
                        </td>
                        <td align="right">
                            <img id="btVoltar" align="middle" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" style="border:none;cursor:pointer;" hspace="5" onClick="voltar();">
                        </td>
                    </tr>
                </table>
            </form>
            </logic:equal>
            <table id="tbPessoa" width="760" height="29" cellpadding="0" cellspacing="0" class="tbl_bgGray" style="display:none;">
                <tr>
                    <td colspan="4"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="7"></td>
                </tr>
                <tr>
                    <td width="170" class="bfixa_texto_black" style="text-indent:6px;"><b>Tipo de Cliente:</b></td>
                    <td valign="middle" width="200">
                        <input type="radio" class="radio" name="tpPessoa" id="tpPessoaF" onclick="selecionaFormulario('PF');"><label for="tpPessoaF">Pessoa Física</label>
                        <input type="radio" class="radio" name="tpPessoa" id="tpPessoaJ" onclick="selecionaFormulario('PJ');"><label for="tpPessoaJ">Pessoa Jurídica</label>
                    </td>
                    <td style="height:25px;font-size:12px;color:#ff0000;"><span id="tbPessNrLinha"></span>&nbsp;(<span id="tbPessCdSeguranca"></span>)</td>
                    <td align="right">
                        <img id="btVoltar" align="middle" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" style="border:none;cursor:pointer;" hspace="5" onClick="voltar();">
                    </td>
                </tr>
            </table>
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="3"></div>
            <vivo:abaGrupo id="btAba" styleClass="abatexto" width="400" height="16">
                <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);carregaAba('C');"  value="Cliente"></vivo:abaItem>
                <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);carregaAba('CD');" value="Outros Dados"></vivo:abaItem>
                <vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);carregaAba('U');"  value="Usuário"></vivo:abaItem>
                <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);carregaAba('UD');" value="Outros Dados Usuário"></vivo:abaItem>
            </vivo:abaGrupo>
            <table class="tbl_bgGray" cellpadding="0" cellspacing="0" border="0" width="760" height="405"><tr><td valign="top">
                <!--//
                /****************************************************************************************************************/
                ABA CLIENTE PF
                /****************************************************************************************************************/
                //-->
                <div id="dvFormCliente" style="display:none;">
                <form name="formClientePF" id="formClientePF" style="margin:0px;" onsubmit="return false;">
                <input type="hidden" name="idPessoaCli" id="idPessoaCli" value="">
                <table width="760" border="0">
                    <tr>
                        <td height="5" colspan="6"></td>
                    </tr>
                    <tr>
                        <td align="right">Nome:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="3">
                            <input type="text"
                                   value=""
                                   id="nmNomeCli"
                                   name="nmNomeCli"
                                   maxlength="60"
                                   style="width:285px;"
                                   onkeyup="inAlteracaoCliente=true">
                        </td>
                        <td align="right" style="padding-right:5px;">Sexo:<span style="color:#ff0000;">*</span></td>
                        <td align="left">
                            <html:select name="form" property="idSexo" styleId="idSexoACli" style="width:150px;" onchange="inAlteracaoCliente=true">
                                <option value="0">--Selecione--</option>
                                <logic:iterate name="list" property="listaTipoSexoArray" id="lstSexo">
                                    <logic:notEqual value="0" name="lstSexo" property="idSexo">
                                        <option value="<bean:write name="lstSexo" property="idSexo"/>"><bean:write name="lstSexo" property="dsSexo"/></option>
                                    </logic:notEqual>
                                </logic:iterate>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td height="5" colspan="6"></td>
                    </tr>
                    <!-- Recuperar todos os documentos -->
                    <tr id="documentos">
                        <td align="right" valign="top">Tipo de Documento: <span style="color:#ff0000">*</span></td>
                        <td colspan="5" style="padding-left:4px;">
                            <input type="hidden" name="idDocumentoA" id="idDocumentoACli" value="">
                            <html:select name="form" property="idTipoDocumentoA" styleId="idTipoDocumentoACli" style="width:150px;" onchange="trataRG(this);clientePesquisado=null;">
                                <option value="0">--Selecione--</option>
                                <logic:iterate name="list" property="listaTipoDocumentoArray" id="optDocs">
                                    <logic:equal name="optDocs" property="dsTipoDocumento" value="CPF">
                                    <option value="<bean:write name="optDocs" property="idTipoDocumento"/>" selected>
                                        <bean:write name="optDocs" property="dsTipoDocumento"/>
                                    </option>
                                    </logic:equal>
                                    <logic:equal name="optDocs" property="dsTipoDocumento" value="RG">
                                    <option value="<bean:write name="optDocs" property="idTipoDocumento"/>"><bean:write name="optDocs" property="dsTipoDocumento"/></option>
                                    </logic:equal>
                                    <logic:equal name="optDocs" property="dsTipoDocumento" value="PASSAPORTE">
                                    <option value="<bean:write name="optDocs" property="idTipoDocumento"/>"><bean:write name="optDocs" property="dsTipoDocumento"/></option>
                                    </logic:equal>
                                </logic:iterate>
                            </html:select>
                            <input type="text"
                                   name="nrDocumentoACli"
                                   id="nrDocumentoACli"
                                   size="15"
                                   maxlength="20"
                                   value=""
                                   onkeydown="removeStrEspecial(this);escolheMascara(this,'1');"
                                   onblur="removeStrEspecial(this);escolheMascara(this,'1');verificaDocumentoCPF(this,$('idTipoDocumentoACli'));"
                                   onkeyup="clientePesquisado=null;" />
                            <logic:notEqual value="A" name="form" property="tpOperacao">
                                <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif"
                                                   style="border:none;cursor:hand;"
                                                   onClick="pesquisa($('idTipoDocumentoACli'),$('nrDocumentoACli'));" />
                            </logic:notEqual>
                            <br/>
                            <div id="docExtras1Cli" style="display:none;">
                                <table><tr><td>
                                &nbsp;UF:<span style="color:#ff0000">*</span>
                                <html:select name="form"
                                             property="idUFDocumento"
                                             styleId="idUFDocumentoCliEx1"
                                             style="width:50px;"
                                             onchange="inAlteracaoCliente=true">
                                    <option value="0">--</option>
                                    <html:optionsCollection property="listaUFArray" name="list" label="sgUF" value="idUF"/>
                                </html:select >
                                &nbsp;Órgão Emissor:<span style="color:#ff0000">*</span>
                                <input type="text" name="dsOrgEmisDocSelCliEx1" id="dsOrgEmisDocSelCliEx1" maxlength="5" size="5" value="" onkeyup="inAlteracaoCliente=true" />
                                &nbsp;Data Expedição:<span style="color:#ff0000">*</span>
                                <input type="text" name="dtExpDocSelCliEx1" id="dtExpDocSelCliEx1" size="11" maxlength="10" onkeyup="this.value = Format(this.value,'##/##/####');inAlteracaoCliente=true" onkeydown="this.value = Format(this.value,'##/##/####');" onblur="if(this.value!=''){if(!validaData(this.value)){alert('Data inválida!');this.value='';return false;}}"/>
                                <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtExpDocSelCliEx1', '%d/%m/%Y');">
                                </td></tr></table>
                            </div>
                            <input type="hidden" name="idDocumentoBCli" id="idDocumentoBCli" value="">
                            <html:select name="form" property="idTipoDocumentoB" styleId="idTipoDocumentoBCli" style="width:150px;" onchange='trataRG(this);'>
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoDocumentoArray" name="list" label="dsTipoDocumento" value="idTipoDocumento"/>
                            </html:select >
                            <input type="text" name="nrDocumentoBCli" id="nrDocumentoBCli" size="15" maxlength="15" value="" onkeydown="removeStrEspecial(this);escolheMascara(this,'2');" onblur="removeStrEspecial(this);escolheMascara(this,'2');verificaDocumentoCPF(this,$('idTipoDocumentoBCli'));"/>
                            <div id="docExtras2Cli" style="display:none;">
                                <table><tr><td>
                                &nbsp;UF:<span style="color:#ff0000">*</span>
                                <html:select name="form" property="idUFDocumento" styleId="idUFDocumentoCliEx2" style="width:50px;">
                                    <option value="0">--</option>
                                    <html:optionsCollection property="listaUFArray" name="list" label="sgUF" value="idUF"/>
                                </html:select >
                                &nbsp;Órgão Emissor:<span style="color:#ff0000">*</span>
                                <input type="text" name="dsOrgEmisDocSelCliEx2" id="dsOrgEmisDocSelCliEx2" maxlength="5" size="5" value=""/>
                                &nbsp;Data Expedição:<span style="color:#ff0000">*</span>
                                <input type="text" name="dtExpDocSelCliEx2" id="dtExpDocSelCliEx2" size="11" maxlength="10" onkeyup="this.value = Format(this.value,'##/##/####');" onkeydown="this.value = Format(this.value,'##/##/####');" onblur="if(this.value!=''){if(!validaData(this.value)){alert('Data inválida!');this.value='';return false;}}"/>
                                <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtExpDocSelCliEx2', '%d/%m/%Y');">
                                </td></tr></table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Data Nascimento:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <input type="text" name="dtNascimentoACli" id="dtNascimentoACli" size="11" maxlength="10" onkeyup="this.value = Format(this.value,'##/##/####');" onkeydown="this.value = Format(this.value,'##/##/####');" onblur="if(this.value!=''){if(!validaData(this.value)){alert('Data inválida!');this.value='';return false;}}"/>
                            <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtNascimentoACli', '%d/%m/%Y');">
                        </td>
                        <td align="right" colspan="2">Cliente é usuário da linha? <span style="color:#ff0000">*</span></td>
                        <td align="left">
                            <select name="inUsuarioPFCli" id="inUsuarioPFCli" onchange="onChangeUser(this);inAlteracaoCliente=true">
                                <option value="">--</option>
                                <option value="0">Não</option>
                                <option value="1" selected>Sim</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <logic:notEqual value="A" name="form" property="tpOperacao">
                                Cliente aceita receber em seu celular mensagens de Promoções, Benefícios e Serviços da Vivo e seus Parceiros?<span style="color:#ff0000">*</span>
                                <select name="inSMSPF" id="inSMSPF">
                                    <option value="">--</option>
                                    <option value="1" selected>Aceita Receber</option>
                                    <option value="0">Não Aceita Receber</option>
                                </select>
                            </logic:notEqual>
                        </td>
                    </tr>
                </table>
                </form>
                </div>
                <!--//
                /****************************************************************************************************************/
                ABA USUARIO PF
                /****************************************************************************************************************/
                //-->
                <div id="dvFormUsuario" style="display:none;">
                <form name="formUsuarioPF" id="formUsuarioPF" style="margin:0px;" onsubmit="return false;">
                <input type="hidden" name="idPessoaUsu" id="idPessoaUsu" value="">
                <table width="760" border="0">
                    <tr>
                        <td height="5" colspan="6"></td>
                    </tr>
                    <tr>
                        <td align="right">Nome:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="3">
                            <input type="text"
                                   id="nmNomeUsu"
                                   name="nmNomeUsu"
                                   maxlength="60"
                                   style="width:285px;">
                        </td>
                        <td align="right" style="padding-right:5px;">Sexo:<span style="color:#ff0000;">*</span></td>
                        <td align="left">
                            <html:select name="form" property="idSexo" styleId="idSexoAUsu" style="width:150px;">
                                <option value="0">--Selecione--</option>
                                <logic:iterate name="list" property="listaTipoSexoArray" id="lstSexo">
                                    <logic:notEqual value="0" name="lstSexo" property="idSexo">
                                        <option value="<bean:write name="lstSexo" property="idSexo"/>"><bean:write name="lstSexo" property="dsSexo"/></option>
                                    </logic:notEqual>
                                </logic:iterate>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td height="5" colspan="6"></td>
                    </tr>
                    <!-- Recuperar todos os documentos -->
                    <tr id="documentos">
                        <td align="right" valign="top">Tipo de Documento: <span style="color:#ff0000">*</span></td>
                        <td colspan="5" style="padding-left:4px;">

                            <input type="hidden" name="idDocumentoAUsu" id="idDocumentoAUsu" value="">

                            <html:select name="form"
                                         property="idTipoDocumentoA"
                                         styleId="idTipoDocumentoAUsu"
                                         style="width:150px;"
                                         onchange='trataRG(this);'>
                                <option value="0">--Selecione--</option>
                                <logic:iterate name="list" property="listaTipoDocumentoArray" id="optDocs">
                                    <logic:equal name="optDocs" property="dsTipoDocumento" value="CPF">
                                    <option value="<bean:write name="optDocs" property="idTipoDocumento"/>"><bean:write name="optDocs" property="dsTipoDocumento"/></option>
                                    </logic:equal>
                                    <logic:equal name="optDocs" property="dsTipoDocumento" value="RG">
                                    <option value="<bean:write name="optDocs" property="idTipoDocumento"/>"><bean:write name="optDocs" property="dsTipoDocumento"/></option>
                                    </logic:equal>
                                    <logic:equal name="optDocs" property="dsTipoDocumento" value="PASSAPORTE">
                                    <option value="<bean:write name="optDocs" property="idTipoDocumento"/>"><bean:write name="optDocs" property="dsTipoDocumento"/></option>
                                    </logic:equal>
                                </logic:iterate>
                            </html:select>
                            <input type="text" name="nrDocumentoAUsu" id="nrDocumentoAUsu" size="15" maxlength="15" value="" onkeydown="removeStrEspecial(this);escolheMascara(this,'1');" onblur="removeStrEspecial(this);escolheMascara(this,'1');verificaDocumentoCPF(this,$('idTipoDocumentoAUsu'));"/>

                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif"
                                               style="border:none;cursor:hand;"
                                               onClick="pesquisa($('idTipoDocumentoAUsu'),$('nrDocumentoAUsu'));"/>
                            <br/>
                            <div id="docExtras1Usu" style="display:none;">
                                <table><tr><td>
                                &nbsp;UF:<span style="color:#ff0000">*</span>
                                <html:select name="form" property="idUFDocumento" styleId="idUFDocumentoUsuEx1" style="width:50px;">
                                    <option value="0">--</option>
                                    <html:optionsCollection property="listaUFArray" name="list" label="sgUF" value="idUF"/>
                                </html:select >
                                &nbsp;Órgão Emissor:<span style="color:#ff0000">*</span>
                                <input type="text" name="dsOrgEmisDocSelUsuEx1" id="dsOrgEmisDocSelUsuEx1" maxlength="5" size="5" value=""/>
                                &nbsp;Data Expedição:<span style="color:#ff0000">*</span>
                                <input type="text" name="dtExpDocSelUsuEx1" id="dtExpDocSelUsuEx1" size="11" maxlength="10" onkeyup="this.value = Format(this.value,'##/##/####');" onkeydown="this.value = Format(this.value,'##/##/####');" onblur="if(this.value!=''){if(!validaData(this.value)){alert('Data inválida!');this.value='';return false;}}"/>
                                <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtExpDocSelUsuEx1', '%d/%m/%Y');">
                                </td></tr></table>
                            </div>
                            <input type="hidden" name="idDocumentoBUsu" id="idDocumentoBUsu" value="">
                            <html:select name="form" property="idTipoDocumentoB" styleId="idTipoDocumentoBUsu" style="width:150px;" onchange='trataRG(this);'>
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoDocumentoArray" name="list" label="dsTipoDocumento" value="idTipoDocumento"/>
                            </html:select >
                            <input type="text" name="nrDocumentoBUsu" id="nrDocumentoBUsu" style="width:137px;" size="15" onkeydown="removeStrEspecial(this);escolheMascara(this,'2');" onblur="removeStrEspecial(this);escolheMascara(this,'2');verificaDocumentoCPF(this,$('idTipoDocumentoBUsu'));"/>
                            <div id="docExtras2Usu" style="display:none;">
                                <table><tr><td>
                                &nbsp;UF:<span style="color:#ff0000">*</span>
                                <html:select name="form" property="idUFDocumento" styleId="idUFDocumentoUsuEx2" style="width:50px;">
                                    <option value="0">--</option>
                                    <html:optionsCollection property="listaUFArray" name="list" label="sgUF" value="idUF"/>
                                </html:select >
                                &nbsp;Órgão Emissor:<span style="color:#ff0000">*</span>
                                <input type="text" name="dsOrgEmisDocSelUsuEx2" id="dsOrgEmisDocSelUsuEx2" maxlength="5" size="5" value=""/>
                                &nbsp;Data Expedição:<span style="color:#ff0000">*</span>
                                <input type="text" name="dtExpDocSelUsuEx2" id="dtExpDocSelUsuEx2" size="11" maxlength="10" onkeyup="this.value = Format(this.value,'##/##/####');" onkeydown="this.value = Format(this.value,'##/##/####');" onblur="if(this.value!=''){if(!validaData(this.value)){alert('Data inválida!');this.value='';return false;}}"/>
                                <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtExpDocSelUsuEx2', '%d/%m/%Y');">
                                </td></tr></table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Data Nascimento:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <input type="text" name="dtNascimentoAUsu" id="dtNascimentoAUsu" size="11" maxlength="10" onkeyup="this.value = Format(this.value,'##/##/####');" onkeydown="this.value = Format(this.value,'##/##/####');" onblur="if(this.value!=''){if(!validaData(this.value)){alert('Data inválida!');this.value='';return false;}}"/>
                            <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtNascimentoAUsu', '%d/%m/%Y');">
                        </td>
                        <td align="right" colspan="2"></td>
                        <td align="left"></td>
                    </tr>
                </table>
                </form>
                </div>
                <!--//
                /****************************************************************************************************************/
                ABA CLIENTE PJ
                /****************************************************************************************************************/
                //-->
                <div id="dvFormPJ" style="display:none;">
                <form name="formPJ" id="formPJ" style="margin:0px;" onsubmit="return false;">
                <input type="hidden" name="idPessoaPJA" id="idPessoaPJA" value="">
                <input type="hidden" name="idContatoPJA1" id="idContatoPJA1" value="">
                <table width="760" border="0">
                    <tr>
                        <td height="1" colspan="6"></td>
                    </tr>
                    <tr>
                        <td align="right">CNPJ:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <input type="text" id="nrCNPJA" name="nrCNPJA" maxlength="18" style="width:200px;" onkeypress="removeStrEspecial(this);Formatar(this.value, this.form.name, this.name, 'cnpj');" onblur="removeStrEspecial(this);Formatar(this.value, this.form.name, this.name, 'cnpj');verificaDocumentoCNPJ(this);">
                            <logic:notEqual value="A" name="form" property="tpOperacao">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand;" onClick="pesquisa($('nrCNPJA'));"/>
                            </logic:notEqual>
                        </td>
                        <td align="right">Nome Fantasia:<span style="color:#ff0000;">*</span></td>
                        <td align="left" colspan="2">
                            <input type="text"
                                   id="nmFantasiaA"
                                   name="nmFantasiaA"
                                   maxlength="60"
                                   style="width:200px;" />
                        </td>
                    </tr>
                    <!-- Recuperar todos os documentos -->
                    <tr id="documentos">
                        <td align="right">Razão Social: <span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="5">
                            <input type="text" name="nmRazaoSocialA" id="nmRazaoSocialA" style="width:200px;" maxlength="60">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            CNAE: <span style="color:#ff0000">*</span>
                            <input type="text" name="nrCNAEA" id="nrCNAEA" size="8" maxlength="7" onblur="validarCNAE();">
                            <span id="dsCNAEA" style="text-overflow:ellipsis;width:205px;white-space:nowrap;display:inline;overflow:hidden;"></span>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Inscrição:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <html:select name="form" property="idTipoInscricao" styleId="idTipoInscricaoA" style="width:160px;" onchange="checkInsEst(this)">
                                <logic:iterate id="listaInscricoes"
                                               name="list"
                                               property="listaTipoInscricaoArray"
                                               type="br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO.ListaTipoInscricao">

                                    <% if ("INSCRIÇÃO ESTADUAL".equalsIgnoreCase(listaInscricoes.getDsInscricao())
                                               || "ISENTO".equalsIgnoreCase(listaInscricoes.getDsInscricao())) {
                                           String selected = (form.getIdTipoInscricao().equals(listaInscricoes.getIdInscricao())) ? "selected" : ""; %>
                                        <option value="<%=listaInscricoes.getIdInscricao()%>" <%=selected%>><%=listaInscricoes.getDsInscricao()%></option>
                                        <% if ("INSCRIÇÃO ESTADUAL".equalsIgnoreCase(listaInscricoes.getDsInscricao())) { %>
                                            <script type="text/javascript">ID_TIPO_INSCRICAO_IE = '<%=listaInscricoes.getIdInscricao()%>';</script>
                                        <% } %>
                                    <% } %>
                                </logic:iterate>
                            </html:select>
                            <input type="text" name="nrInscricaoA" id="nrInscricaoA" size="15" maxlength="18">
                        </td>
                        <td align="right">Classificação Tributária:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <html:select name="form" property="idClassTributaria" styleId="idClassTributariaA" style="width:200px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaClassTributariaArray" name="list" label="dsTributaria" value="idTributaria"/>
                            </html:select>
                        </td>
                    </tr>
                    <!-- Recuperar todos os telefones cadastrados-->
                    <tr valign="middle">
                        <td align="right">Classificação da Empresa:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="3">
                            <html:select name="form" property="idClassEmpresa" styleId="idClassEmpresaA" style="width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaClassEmpresaArray"
                                                        name="list"
                                                        label="dsClassificacaoEmpresa"
                                                        value="idClassificacaoEmpresa" />
                            </html:select>
                            &nbsp;&nbsp;&nbsp;Fundação:<span style="color:#ff0000">*</span>
                            <input type="text" name="dtFundacaoA" id="dtFundacaoA" size="11" maxlength="10" onkeyup="this.value = Format(this.value,'##/##/####');" onkeydown="this.value = Format(this.value,'##/##/####');" onblur="if(this.value!=''){if(!validaData(this.value)){alert('Data inválida!');this.value='';return false;}}"/>
                            <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFundacaoA', '%d/%m/%Y');">
                        </td>
                        <td align="right">CCM:<span style="color:#ff0000">*</span></td>
                        <td align="left">
                            <input type="text"
                                   id="nrCCMA"
                                   name="nrCCMA"
                                   maxlength="60"
                                   style="width:160px;" />
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Telefone:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <html:select name="form" property="idTipoTelefonePJ" styleId="idTipoTelefonePJA" style="width:130px;" onchange="inAlteracaoCliente=true">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoTelefoneArray" name="list" label="dsTipoTelefone" value="idTipoTelefone"/>
                            </html:select>
                            <input type="text" name="nrTelefonePJA" id="nrTelefonePJA" value="" maxlength="11" onblur="formatPhoneNumberObj(this)" />
                        </td>
                        <td align="right">Nome para Contato:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2"><input type="text" name="nmContatoPJA" id="nmContatoPJA" maxlength="60" style="width:200px" onkeyup="inAlteracaoCliente=true"/></td>
                    </tr>
                    <tr>
                        <td align="right" colspan="2">Cliente é usuário da linha? <span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="5">
                            <select name="inUsuarioPJ" id="inUsuarioPJ" onchange="onChangeUser(this);inAlteracaoCliente=true">
                                <option value="">--</option>
                                <option value="0">Não</option>
                                <option value="1" selected>Sim</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <logic:notEqual value="A" name="form" property="tpOperacao">
                                Cliente aceita receber em seu celular mensagens de Promoções, Benefícios e Serviços da Vivo e seus Parceiros?<span style="color:#ff0000">*</span>
                                <select name="inSMSPJ" id="inSMSPJ" onchange="inAlteracaoCliente=true">
                                    <option value="">--</option>
                                    <option value="1" selected>Aceita Receber</option>
                                    <option value="0">Não Aceita Receber</option>
                                </select>
                            </logic:notEqual>
                        </td>
                    </tr>
                </table>
                </form>
            </div>
            <!--//
            /****************************************************************************************************************/
            Dados Endereço Cliente
            /****************************************************************************************************************/
            //-->
            <div id="dvFormEndCliente" style="display:none;margin-top:5px;">
                <form name="formEndCliente" id="formEndCliente" style="margin:0px;" onsubmit="return false;">
                <input type="hidden" name="idEnderecoCli"   id="idEnderecoCli"   value="">
                <input type="hidden" name="nrPosAltEndCli"  id="nrPosAltEndCli"  value="">
                <input type="hidden" name="inEndDefaultCli" id="inEndDefaultCli" value="">

                <input type="hidden" name="codLogradouroCli" id="codLogradouroCli" value="">
                <input type="hidden" name="inCnlCli" id="inCnlCli" value="">
                <input type="hidden" name="inCodigoIBGECli" id="inCodigoIBGECli" value="">

                <table width="760" border="0">
                    <!-- Recuperar todos os Endereços cadastrados-->
                    <tr valign="middle">
                        <td align="right">Tipo Endereço:<span style="color:#ff0000">*</span></td>
                        <td align="left">
                            <html:select name="form" property="idTipoEndereco" styleId="idTipoEnderecoCli" style="margin-left:5px;width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoEnderecoArray" name="list" label="dsTipoEndereco" value="idTipoEndereco"/>
                            </html:select>
                        </td>
                        <td align="right">CEP:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <input type="text"
                                   name="nrCEPCli"
                                   id="nrCEPCli"
                                   value=""
                                   maxlength="9"
                                   size="10"
                                   onkeyup="Formatar(this.value, this.form.name, this.name, 'cep');"
                                   onblur="Formatar(this.value, this.form.name, this.name, 'cep');verificaNumCEP(this);" />
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif"
                                 style="cursor:pointer;border:none"
                                 onmouseup="pesquisarEndereco($('nrCEPCli'));" />
                        </td>
                        <td align="left">
                            <input type="checkbox" name="isEndPrinCli" id="isEndPrinCli" value="1" style="border:none;background:none">
                            <b>Gravar como endereço principal</b>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Tipo Logradouro:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2"><input type="text" name="tpLogradouroCli" id="tpLogradouroCli" value="" style="width:160px;"/></td>
                        <td align="right" colspan="2">Título do Logradouro:</td>
                        <td align="left"><input type="text" name="dsTitLogradouroCli" id="dsTitLogradouroCli" maxlength="60" style="width:160px"/></td>
                    </tr>
                    <tr>
                        <td align="right">Logradouro:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmLogradouroCli" id="nmLogradouroCli" maxlength="60" style="width:160px;"/></td>
                        <td align="right">Número:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nrLogradouroCli" id="nrLogradouroCli" size="6" maxlength="60"/></td>
                        <td align="right">Complemento:</td>
                        <td align="left"><input type="text" name="dsComplementoCli" id="dsComplementoCli" maxlength="60" style="width:160px;" value=""/></td>
                    </tr>
                    <tr>
                        <td align="right">Bairro:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmBairroCli" id="nmBairroCli" maxlength="60" style="width:160px;"/></td>
                        <td align="right">Município:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmMunicipioCli" id="nmMunicipioCli" maxlength="60" style="width:130px;"/></td>
                        <td align="left">UF:<span style="color:#ff0000">*</span>
                            <html:select name="form" property="idUFEndereco" styleId="idUFEnderecoCli" style="width:50px;">
                                <option value="0">--</option>
                                <html:optionsCollection property="listaUFArray" name="list" label="sgUF" value="idUF"/>
                            </html:select >
                        </td>
                        <td align="center">
                            <div id="btIncluirEndCliente">
                                <img src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif"  border="0" style="cursor:pointer;" onclick="$('formEndCliente').reset();habilitaCamposEndereco();$('imgAltEndCli').hide();$('imgIncEndCli').show();$('btIncluirEndCliente').hide();"/>&nbsp;
                                <img id="imgIncEndCli" src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif" border="0" style="cursor:pointer;" onclick="processaIncEnd('C');"/>
                                <img id="imgAltEndCli" src="<%=request.getContextPath()%>/resources/images/bt_alterar_nrml.gif" border="0" style="display:none;cursor:pointer;" onclick="processaAltEnd('C');"/>
                            </div>
                        </td>
                    </tr>
                </table>
                </form>
                <div id="tabListaEndCliente" style="width:745px;height:100px;"></div>
            </div>
            <!--//
            /****************************************************************************************************************/
            Dados Endereço Usuario
            /****************************************************************************************************************/
            //-->
            <div id="dvFormEndUsuario" style="display:none;margin-top:5px;">
                <form name="formEndUsuario" id="formEndUsuario" style="margin:0px;" onsubmit="return false;">
                <input type="hidden" name="idEnderecoUsu"   id="idEnderecoUsu"   value="">
                <input type="hidden" name="nrPosAltEndUsu"  id="nrPosAltEndUsu"  value="">
                <input type="hidden" name="inEndDefaultUsu" id="inEndDefaultUsu" value="">

                <input type="hidden" name="codLogradouroUsu" id="codLogradouroUsu" value="">
                <input type="hidden" name="inCnlUsu" id="inCnlUsu" value="">
                <input type="hidden" name="inCodigoIBGEUsu" id="inCodigoIBGEUsu" value="">

                <table width="760" border="0">
                    <!-- Recuperar todos os Endereços cadastrados-->
                    <tr valign="middle">
                        <td align="right">Tipo Endereço:<span style="color:#ff0000">*</span></td>
                        <td align="left">
                            <html:select name="form" property="idTipoEndereco" styleId="idTipoEnderecoUsu" style="margin-left:5px;width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoEnderecoArray" name="list" label="dsTipoEndereco" value="idTipoEndereco"/>
                            </html:select>
                        </td>
                        <td align="right">CEP:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <input type="text" name="nrCEPUsu" id="nrCEPUsu" value="" maxlength="9" size="10" onkeyup="Formatar(this.value, this.form.name, this.name, 'cep');" onblur="Formatar(this.value, this.form.name, this.name, 'cep');verificaNumCEP(this);"/>
                            <img id="btPesqEndUsu" src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" style="cursor:pointer" border="0" onclick="pesquisarEndereco($('nrCEPUsu'));"/>
                        </td>
                        <td align="left">
                            <input type="checkbox"
                                   name="isUtilEndCliente"
                                   id="isUtilEndCliente"
                                   value="1"
                                   style="border:none;background:none"
                                   onclick="reutilizaEndCliente(this.checked);">
                            <b>Reutilizar endereço Cliente</b>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Tipo Logradouro:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2"><input type="text" name="tpLogradouroUsu" id="tpLogradouroUsu" value="" style="width:160px;"/></td>
                        <td align="right" colspan="2">Título do Logradouro:</td>
                        <td align="left"><input type="text" name="dsTitLogradouroUsu" id="dsTitLogradouroUsu" maxlength="60" style="width:160px"/></td>
                    </tr>
                    <tr>
                        <td align="right">Logradouro:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmLogradouroUsu" id="nmLogradouroUsu" maxlength="60" style="width:160px;"/></td>
                        <td align="right">Número:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nrLogradouroUsu" id="nrLogradouroUsu" size="6" maxlength="60"/></td>
                        <td align="right">Complemento:</td>
                        <td align="left"><input type="text" name="dsComplementoUsu" id="dsComplementoUsu" maxlength="60" style="width:160px;"/></td>
                    </tr>
                    <tr>
                        <td align="right">Bairro:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmBairroUsu" id="nmBairroUsu" maxlength="60" style="width:160px;"/></td>
                        <td align="right">Município:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmMunicipioUsu" id="nmMunicipioUsu" maxlength="60" style="width:130px;"/></td>
                        <td align="left">UF:<span style="color:#ff0000">*</span>
                            <html:select name="form" property="idUFEndereco" styleId="idUFEnderecoUsu" style="width:50px;">
                                <option value="0">--</option>
                                <html:optionsCollection property="listaUFArray" name="list" label="sgUF" value="idUF"/>
                            </html:select >
                        </td>
                        <td align="center">
                            <div id="btIncluirEndUsuario">
                                <img src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif"  border="0" style="cursor:pointer;" onclick="$('formEndUsuario').reset();habilitaCamposEndereco();$('imgAltEndUsu').hide();$('imgIncEndUsu').show();$('btIncluirEndUsuario').hide();"/>&nbsp;
                                <img id="imgIncEndUsu" src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif" border="0" style="cursor:pointer;" onclick="processaIncEnd('U');"/>
                                <img id="imgAltEndUsu" src="<%=request.getContextPath()%>/resources/images/bt_alterar_nrml.gif" border="0" style="display:none;cursor:pointer;" onclick="processaAltEnd('C');"/>
                            </div>
                        </td>
                    </tr>
                </table>
                </form>
                <div id="tabListaEndUsuario" style="width:745px;height:100px;"></div>
            </div>
            <!--//
            /****************************************************************************************************************/
            Dados Endereço Pessoa Juridica
            /****************************************************************************************************************/
            //-->
            <div id="dvFormEndPJ" style="display:none;margin-top:5px;">
                <form name="formEndPJ" id="formEndPJ" style="margin:0px;" onsubmit="return false;">
                <input type="hidden" name="idEnderecoPJ"   id="idEnderecoPJ"   value="">
                <input type="hidden" name="nrPosAltEndPJ"  id="nrPosAltEndPJ"  value="">
                <input type="hidden" name="inEndDefaultPJ" id="inEndDefaultPJ" value="">

                <input type="hidden" name="codLogradouroPJ" id="codLogradouroPJ" value="">
                <input type="hidden" name="inCnlPJ" id="inCnlPJ" value="">
                <input type="hidden" name="inCodigoIBGEPJ" id="inCodigoIBGEPJ" value="">
                <table width="760" border="0">
                    <!-- Recuperar todos os Endereços cadastrados-->
                    <tr valign="middle">
                        <td align="right">Tipo Endereço:<span style="color:#ff0000">*</span></td>
                        <td align="left">
                            <html:select name="form" property="idTipoEndereco" styleId="idTipoEnderecoPJ" style="margin-left:5px;width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoEnderecoArray" name="list" label="dsTipoEndereco" value="idTipoEndereco"/>
                            </html:select>
                        </td>
                        <td align="right">CEP:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2">
                            <input type="text" name="nrCEPPJ" id="nrCEPPJ" value="" maxlength="9" size="10" onkeyup="Formatar(this.value, this.form.name, this.name, 'cep');" onblur="Formatar(this.value, this.form.name, this.name, 'cep');verificaNumCEP(this);"/>
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" style="cursor:pointer" border="0" onclick="pesquisarEndereco($('nrCEPPJ'));"/>
                        </td>
                        <td align="left">
                            <input type="checkbox" name="isEndPrinPJ" id="isEndPrinPJ" value="1" style="border:none;background:none">
                            <b>Gravar como endereço principal</b>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Tipo Logradouro:<span style="color:#ff0000">*</span></td>
                        <td align="left" colspan="2"><input type="text" name="tpLogradouroPJ" id="tpLogradouroPJ" value="" style="width:160px;"/></td>
                        <td align="right" colspan="2">Título do Logradouro:</td>
                        <td align="left"><input type="text" name="dsTitLogradouroPJ" id="dsTitLogradouroPJ" maxlength="60" style="width:160px"/></td>
                    </tr>
                    <tr>
                        <td align="right">Logradouro:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmLogradouroPJ" id="nmLogradouroPJ" maxlength="60" style="width:160px;"/></td>
                        <td align="right">Número:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nrLogradouroPJ" id="nrLogradouroPJ" size="6" maxlength="60"/></td>
                        <td align="right">Complemento:</td>
                        <td align="left"><input type="text" name="dsComplementoPJ" id="dsComplementoPJ" maxlength="60" style="width:160px;"/></td>
                    </tr>
                    <tr>
                        <td align="right">Bairro:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmBairroPJ" id="nmBairroPJ" maxlength="60" style="width:160px;"/></td>
                        <td align="right">Município:<span style="color:#ff0000">*</span></td>
                        <td align="left"><input type="text" name="nmMunicipioPJ" id="nmMunicipioPJ" maxlength="60" style="width:130px;"/></td>
                        <td align="left">UF:<span style="color:#ff0000">*</span>
                            <html:select name="form" property="idUFEndereco" styleId="idUFEnderecoPJ" style="width:50px;">
                                <option value="0">--</option>
                                <html:optionsCollection property="listaUFArray" name="list" label="sgUF" value="idUF"/>
                            </html:select >
                        </td>
                        <td align="center">
                            <div id="btIncluirEndPJ">
                                <img src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif"  border="0" style="cursor:pointer;" onclick="$('formEndPJ').reset();habilitaCamposEndereco();$('imgAltEndPJ').hide();$('imgIncEndPJ').show();$('btIncluirEndPJ').hide();"/>&nbsp;
                                <img id="imgIncEndPJ" src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif" border="0" style="cursor:pointer;" onclick="processaIncEnd('J');"/>
                                <img id="imgAltEndPJ" src="<%=request.getContextPath()%>/resources/images/bt_alterar_nrml.gif" border="0" style="display:none;cursor:pointer;" onclick="processaAltEnd('C');"/>
                            </div>
                        </td>
                    </tr>
                </table>
                </form>
                <div id="tabListaEndPJ" style="width:745px;height:100px;"></div>
            </div>
            <!--//
            /****************************************************************************************************************/
            Aba Outros Dados
            /****************************************************************************************************************/
            //-->
            <div id="dvDadosCliente" style="display:none;">
                <form name="formDadosCliente" id="formDadosCliente" style="margin:0px;" onsubmit="return false;">
                <input type="hidden" name="idContatoCliA1" id="idContatoCliA1" value=""/>
                <input type="hidden" name="idContatoCliB2" id="idContatoCliA2" value=""/>
                <input type="hidden" name="idContatoCliC3" id="idContatoCliA3" value=""/>
                <table width="760" border="0">
                    <tr>
                        <td colspan="6" height="5"></td>
                    </tr>
                    <tr>
                        <td align="right">Telefone:</td>
                        <td>
                            <html:select name="form" property="idTipoTelefoneA" styleId="idTipoTelefoneACli" style="width:160px;" onchange="inAlteracaoCliente=true">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoTelefoneArray" name="list" label="dsTipoTelefone" value="idTipoTelefone"/>
                            </html:select>
                        </td>
                        <td><input type="text" name="nrTelefone1Cli" id="nrTelefone1Cli" style="width:137px;" size="15" value="" maxlength="11" onblur="formatPhoneNumberObj(this)" /></td>
                        <td align="right">Nome para Contato:</td>
                        <td><input type="text" name="nmContato1Cli" id="nmContato1Cli" style="width:137px;" size="15" value="" onkeyup="inAlteracaoCliente=true" /></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">Telefone:</td>
                        <td>
                            <html:select name="form" property="idTipoTelefoneB" styleId="idTipoTelefoneBCli" style="width:160px;" onchange="inAlteracaoCliente=true">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoTelefoneArray" name="list" label="dsTipoTelefone" value="idTipoTelefone"/>
                            </html:select>
                        </td>
                        <td><input type="text" name="nrTelefone2Cli" id="nrTelefone2Cli" style="width:137px;" size="15" value="" maxlength="11" onkeypress="inAlteracaoCliente=true" onblur="formatPhoneNumberObj(this)" /></td>
                        <td align="right">Nome para Contato:</td>
                        <td><input type="text" name="nmContato2Cli" id="nmContato2Cli" style="width:137px;" size="15" value="" onkeyup="inAlteracaoCliente=true"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">Telefone:</td>
                        <td>
                            <html:select name="form" property="idTipoTelefoneC" styleId="idTipoTelefoneCCli" style="width:160px;" onchange="inAlteracaoCliente=true">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoTelefoneArray" name="list" label="dsTipoTelefone" value="idTipoTelefone"/>
                            </html:select>
                        </td>
                        <td><input type="text" name="nrTelefone3Cli" id="nrTelefone3Cli" style="width:137px;" size="15" value="" maxlength="11" onkeypress="inAlteracaoCliente=true" onblur="formatPhoneNumberObj(this)" /></td>
                        <td align="right">Nome para Contato:</td>
                        <td><input type="text" name="nmContato3Cli" id="nmContato3Cli" style="width:137px;" size="15" value="" onkeyup="inAlteracaoCliente=true" /></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">E-mail particular:</td>
                        <td><input type="text" name="nmEmailPartCli" id="nmEmailPartCli" style="width:137px;" size="15" value="" onblur="verificaEndEmail(this);"/></td>
                        <td align="right">E-mail Comercial:</td>
                        <td colspan="3"><input type="text" name="nmEmailComCli" id="nmEmailComCli" style="width:137px;" size="15" value="" onblur="verificaEndEmail(this);"/></td>
                    </tr>
                    <tr>
                        <td align="right">Estado Civil:</td>
                        <td>
                            <html:select name="form" property="idEstadoCivil" styleId="idEstadoCivilCliA" style="width:160px;" onchange="inAlteracaoCliente=true">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaEstadoCivilArray" name="list" label="dsEstadoCivil" value="idEstadoCivil"/>
                            </html:select>
                        </td>
                        <td align="right">Escolaridade:</td>
                        <td colspan="3">
                            <html:select name="form" property="idEscolaridade" styleId="idEscolaridadeCliA" style="width:160px;" onchange="inAlteracaoCliente=true">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaEscolaridadeArray" name="list" label="dsEscolaridade" value="idEscolaridade"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Profissão:</td>
                        <td><input type="text" name="nmProfissaoCli" id="nmProfissaoCli" style="width:137px;" size="15" value="" onkeyup="inAlteracaoCliente=true"/></td>
                        <td align="right">Natureza da Ocupação:</td>
                        <td colspan="3">
                            <html:select name="form" property="idNatOcupacao" styleId="idNatOcupacaoCliA" style="width:160px;" onchange="inAlteracaoCliente=true">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaNatOcupacaoArray" name="list" label="dsOcupacao" value="idOcupacao"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">CPR:</td>
                        <td colspan="5"><input type="text" name="cdCPRCli" id="cdCPRCli" style="width:137px;" size="15" value="" onkeyup="inAlteracaoCliente=true" />&nbsp;(Código de Propriedade Rural)</td>
                    </tr>
                </table>
                </form>
            </div>
            <!--//
            /****************************************************************************************************************/
            Aba Outros Dados Usuario
            /****************************************************************************************************************/
            //-->
            <div id="dvDadosUsuario" style="display:none;">
                <form name="formDadosUsuario" id="formDadosUsuario" style="margin:0px;" onsubmit="return false;">
                <input type="hidden" name="idContatoUsuA1" id="idContatoUsuA1" value=""/>
                <input type="hidden" name="idContatoUsuB2" id="idContatoUsuB2" value=""/>
                <input type="hidden" name="idContatoUsuC3" id="idContatoUsuC3" value=""/>
                <table width="760" border="0">
                    <tr>
                        <td colspan="6" height="5"></td>
                    </tr>
                    <tr>
                        <td align="right">Telefone:</td>
                        <td>
                            <html:select name="form" property="idTipoTelefoneA" styleId="idTipoTelefoneAUsu" style="width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoTelefoneArray" name="list" label="dsTipoTelefone" value="idTipoTelefone"/>
                            </html:select>
                        </td>
                        <td><input type="text" name="nrTelefone1Usu" id="nrTelefone1Usu" style="width:137px;" size="15" value="" maxlength="11" onblur="formatPhoneNumberObj(this)" /></td>
                        <td align="right">Nome para Contato:</td>
                        <td><input type="text" name="nmContato1Usu" id="nmContato1Usu" style="width:137px;" size="15" value=""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">Telefone:</td>
                        <td>
                            <html:select name="form" property="idTipoTelefoneB" styleId="idTipoTelefoneBUsu" style="width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoTelefoneArray" name="list" label="dsTipoTelefone" value="idTipoTelefone"/>
                            </html:select>
                        </td>
                        <td><input type="text" name="nrTelefone2Usu" id="nrTelefone2Usu" style="width:137px;" size="15" value="" maxlength="11" onblur="formatPhoneNumberObj(this)" /></td>
                        <td align="right">Nome para Contato:</td>
                        <td><input type="text" name="nmContato2Usu" id="nmContato2Usu" style="width:137px;" size="15" value=""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">Telefone:</td>
                        <td>
                            <html:select name="form" property="idTipoTelefoneC" styleId="idTipoTelefoneCUsu" style="width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaTipoTelefoneArray" name="list" label="dsTipoTelefone" value="idTipoTelefone"/>
                            </html:select>
                        </td>
                        <td><input type="text" name="nrTelefone3Usu" id="nrTelefone3Usu" style="width:137px;" size="15" value="" maxlength="11" onblur="formatPhoneNumberObj(this)" /></td>
                        <td align="right">Nome para Contato:</td>
                        <td><input type="text" name="nmContato3Usu" id="nmContato3Usu" style="width:137px;" size="15" value=""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">E-mail particular:</td>
                        <td><input type="text" name="nmEmailPartUsu" id="nmEmailPartUsu" style="width:137px;" size="15" value="" onblur="verificaEndEmail(this);"/></td>
                        <td align="right">E-mail Comercial:</td>
                        <td colspan="3"><input type="text" name="nmEmailComUsu" id="nmEmailComUsu" style="width:137px;" size="15" value="" onblur="verificaEndEmail(this);"/></td>
                    </tr>
                    <tr>
                        <td align="right">Estado Civil:</td>
                        <td>
                            <html:select name="form" property="idEstadoCivil" styleId="idEstadoCivilUsuA" style="width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaEstadoCivilArray" name="list" label="dsEstadoCivil" value="idEstadoCivil"/>
                            </html:select>
                        </td>
                        <td align="right">Escolaridade:</td>
                        <td colspan="3">
                            <html:select name="form" property="idEscolaridade" styleId="idEscolaridadeUsuA" style="width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaEscolaridadeArray" name="list" label="dsEscolaridade" value="idEscolaridade"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Profissão:</td>
                        <td><input type="text" name="nmProfissaoUsu" id="nmProfissaoUsu" style="width:137px;" size="15" value=""/></td>
                        <td align="right">Natureza da Ocupação:</td>
                        <td colspan="3">
                            <html:select name="form" property="idNatOcupacao" styleId="idNatOcupacaoUsuA" style="width:160px;">
                                <option value="0">--Selecione--</option>
                                <html:optionsCollection property="listaNatOcupacaoArray" name="list" label="dsOcupacao" value="idOcupacao"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">CPR:</td>
                        <td colspan="5"><input type="text" name="cdCPRUsu" id="cdCPRUsu" style="width:137px;" size="15" value=""/>&nbsp;(Código de Propriedade Rural)</td>
                    </tr>
                </table>
                </form>
            </div>
            <!--//
            /****************************************************************************************************************/
            Area Avançar
            /****************************************************************************************************************/
            //-->
            <div id="dvRodape">
                <table border="0" width="760">
                    <tr>
                        <td>
                            <span style="color:#ff0000">*</span><i>Campos obrigat&oacute;rios.</i>
                        </td>
                        <td align="right">
                            <img id="btImgGravar"  src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" style="border:none;cursor:hand;" onClick="validaForm();"/>
                            <img id="btImgAvancar" src="<%=request.getContextPath()%>/resources/images/bt_avancar_nrml.gif" style="border:none;cursor:hand;" onClick="avancar();"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="dvGravar" style="display:none;">
            </div>
            </td></tr></table>
            <div id="divPopupTI" style="z-index:998;position:absolute;top:0;left:0;width:100%;height:100%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif);display:none;">
                <div id="wrapper_divPopupTI" style="width:600px;height:300px;margin:10px 0 0 10px;">
                    <div id="header_divPopupTI" style="width:500px;height:21px;background-image:url(/FrontOfficeWeb/resources/images/window_topbg.gif);background-color:#0066cb;">
                        <span id="title_divPopupTI" style="float:left;padding:3px 0 0 3px;;color:#fff;font-weight:bold;">Pesquisa</span>
                        <span id="upperRightButton_divPopupTI" style="float:right;">
                            <img id="imgClose_divPopupTI" hspace="3" src="<%=request.getContextPath()%>/resources/images/window_fechar.gif" onclick="divPopupTI.style.display='none';iframePopupTI.src='about:blank';cleanContent();" style="cursor:pointer;">
                        </span>
                    </div>
                    <div id="wrapper_iframePopupTI" style="width:600px;background-color:#0066cb;">
                        <iframe width="100" height="195" id="iframePopupTI" name="iframePopupTI" frameborder="0" scrolling="no"></iframe>
                    </div>
                </div>
                <iframe id="ifrm_divPopupTI" style="display:none;display/**/:block;position:absolute;top:0;left:0;z-index:-1;filter:mask();width:800px;height:600px;"></iframe>
            </div>
        </vivo:sessao>
    <!--/acesso:controlHiddenItem-->
    </netui-template:section>
</netui-template:template>
