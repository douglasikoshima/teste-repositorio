<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="cadastroTerminalForm"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm"/>

<head>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">        
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<script language="javaScript">

    function onlyDigit(obj) {
        charCode = event.keyCode;
        if (charCode >= 48 && charCode <= 57) {
            return true;
        }
        return false;
    }

    function valida() {
//        if (trim(document.forms[0].idPessoaDePara.value) == "") {
//            alert("Grupo é um campo obrigatório, favor preencher.");
//            return(false);
//        }
        if (trim(document.forms[0].cdLojaOperadoraCartao.value) == "") {
            alert("Código SITEF é um campo obrigatório, favor preencher.");
            return(false);
        }
        if (trim(document.forms[0].nmPessoa.value) == "") {
            alert("Nome da loja é um campo obrigatório, favor preencher.");
            return(false);
        }
//        if (trim(document.forms[0].cdSitefSenha.value) == "") {
//            alert("Senha é um campo obrigatório, favor preencher.");
//            return(false);
//        }
//        if (trim(document.forms[0].cdSitefSenha.value).length != 4) {
//            alert("A Senha deve ter 4 digitos, favor corrigir.");
//            return(false);
//        }
        if (trim(document.forms[0].nrCep.value) == "") {
            alert("CEP é um campo obrigatório, favor preencher.");
            return(false);
        }
        if (trim(document.forms[0].nrEndereco.value) == "") {
            alert("Número é um campo obrigatório, favor preencher.");
            return(false);
        }
        if (trim(document.forms[0].nrTerminal.value) == "") {
            alert("Núm. Terminal é um campo obrigatório, favor preencher.");
            return(false);
        }
        if (trim(document.forms[0].nrIp1.value) == "") {
            alert("IP é um campo obrigatório, favor preencher.");
            return(false);
        }
        if (trim(document.forms[0].nrIp2.value) == "") {
            alert("IP é um campo obrigatório, favor preencher.");
            return(false);
        }
        if (trim(document.forms[0].nrIp3.value) == "") {
            alert("IP é um campo obrigatório, favor preencher.");
            return(false);
        }
        if (trim(document.forms[0].nrIp4.value) == "") {
            alert("IP é um campo obrigatório, favor preencher.");
            return(false);
        }
        if (trim(document.forms[0].idCor.value) == "" || document.forms[0].idCor.value == '0') {
            alert("Cor é um campo obrigatório, favor preencher.");
            return(false);
        }
        return(true);
    }

    function pesquisarNomeLoja() {
        if (document.forms[0].nomeLoja.value.length < 3) {
            alert('Digite pelo menos 3 caracteres para a pesquisa.');
            return;
        }
        document.forms[0].target = '';
        document.forms[0].action = 'pesquisarNomeLoja.do';
        document.forms[0].submit();
    }

    function selecionarEnderecoLoja() {
        if (document.forms[0].idNomeLoja.value != 0) {
            document.forms[0].target = '';
            document.forms[0].idPessoaDePara.value = document.forms[0].idNomeLoja.value;
            document.forms[0].action = 'selecionarEnderecoLoja.do';
            document.forms[0].submit();
        }
    }

    function pesquisarEndereco() {
        divPesquisaEndereco.style.display = '';
        document.forms[0].action = 'loadPesquisaEndereco.do';
        document.forms[0].target = 'ifrmPesquisaEndereco';
        document.forms[0].submit();
    }

<logic:equal name="cadastroTerminalForm" property="operacao" value="I">
    function mudarTR() {
        // Seleciona os campos que aparecerao
        selecionarTR();
        // Limpa os campos após troca
        document.forms[0].idPessoaDePara.value = '';
        document.forms[0].idNomeLoja.value = '';
        document.forms[0].nomeLoja.value = '';
        document.forms[0].cdLojaOperadoraCartao.value = '';
        document.forms[0].nmPessoa.value = '';
        document.forms[0].idUfOperadora.value = '';
        document.forms[0].dsUfOperadora.value = '';
        document.forms[0].nmTipoLogradouro.value = '';
        document.forms[0].nmTituloLogradouro.value = '';
        document.forms[0].nmLogradouro.value = '';
        document.forms[0].nrEndereco.value = '';
        document.forms[0].dsEnderecoComplemento.value = '';
        document.forms[0].nmBairro.value = '';
        document.forms[0].nmMunicipio.value = '';
        document.forms[0].nrCep.value = '';
        document.forms[0].nmUF.value = '';
    }

    function selecionarTR() {
        if (document.forms[0].lojaExistente[0].checked) {
            trExistente.style.display = '';
            trNova.style.display = 'none';

            document.forms[0].cdLojaOperadoraCartao.readOnly = 'readonly';
            document.forms[0].nrEndereco.readOnly = 'readonly';
            document.forms[0].dsEnderecoComplemento.readOnly = 'readonly';
            linkPesquisaEnd.href = '#';
        } else {
            trExistente.style.display = 'none';
            trNova.style.display = '';

            document.forms[0].cdLojaOperadoraCartao.readOnly = '';
            document.forms[0].nrEndereco.readOnly = '';
            document.forms[0].dsEnderecoComplemento.readOnly = '';
            linkPesquisaEnd.href = 'javascript:pesquisarEndereco();';
        }
    }
</logic:equal>

    function gravar() {
        if (valida()) {
<logic:equal name="cadastroTerminalForm" property="operacao" value="I">
            msg = 'Confirma a inclusão dos dados deste terminal?';
</logic:equal>
<logic:equal name="cadastroTerminalForm" property="operacao" value="A">
            msg = 'Confirma a alteração dos dados deste terminal?';
</logic:equal>
            if (!window.confirm(msg)) {
                return false;
            }

            document.forms[0].target = '';

            // Junta o nrIp
            document.forms[0].nrIpTerminal.value = document.forms[0].nrIp1.value + '.' +
                                                   document.forms[0].nrIp2.value + '.' +
                                                   document.forms[0].nrIp3.value + '.' +
                                                   document.forms[0].nrIp4.value;

            // Se ID do terminal nao foi preenchido, salva NOVO.
            if(document.forms[0].idTerminal.value == '') {
                document.forms[0].action = 'incluirTerminalAction.do';
                parent.mostrar_div();
                document.forms[0].submit();
            } else {
                document.forms[0].action = 'alterarTerminalAction.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
    }

</script>

</head>

<body onload="parent.oculta_div();<logic:equal name="cadastroTerminalForm" property="operacao" value="I">selecionarTR();</logic:equal>">
    <acesso:controlHiddenItem nomeIdentificador="term_cad">
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     

    <html:form action="/VOLTAV/manterTerminal/incluirTerminalAction.do" method="post" onSubmit="return false;">  

        <html:hidden property="idTerminal" />
        <html:hidden property="idPessoaDePara" />
        <html:hidden property="idPessoaEndereco" />
        <html:hidden property="indexEndereco" />
        <html:hidden property="nrIpTerminal" />

        <html:hidden property="idUF" />

        <div><img src="<%= request.getContextPath() %>/resources/images/transp.gif" width="1" height="5"></div>

        <table cellspacing="0" cellpadding="0" border="0" width="100%">

            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>

<logic:equal name="cadastroTerminalForm" property="operacao" value="I">
            <tr>
                <td style="text-indent:6px;width:70pt;">&nbsp;</td>

                <td style="text-indent:6px;" colspan="3">&nbsp;
                    <html:radio value="1" property="lojaExistente" onclick="mudarTR();" styleClass="radio" /> <netui:label value="Existente" styleClass="tblEstatica_campoNome"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <html:radio value="2" property="lojaExistente" onclick="mudarTR();" styleClass="radio" /> <netui:label value="Nova" styleClass="tblEstatica_campoNome"/>
                </td>
            </tr>

            <tr id="trExistente">
                <td style="text-indent:6px;">
                    <netui:label value="Nome da Loja:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <bean:define id="comboNomeLoja" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm.comboNomeLoja"/>
                    <html:select property="idNomeLoja" style="width:165pt;" onchange="javascript:selecionarEnderecoLoja();">
                        <html:option value="0">Escolha...</html:option>
                        <logic:present name="comboNomeLoja">
                            <html:options collection="comboNomeLoja" property="idPessoaDePara" labelProperty="nmLoja" />
                        </logic:present>
                    </html:select>
                </td>

                <td style="text-indent:6px;" colspan="2">
                    <html:text property="nomeLoja" size="20" />
                    <img src="<%= request.getContextPath() %>/resources/images/bt_pesquisar_nrml.gif" style="cursor: hand" onclick="pesquisarNomeLoja();">
                </td>
            </tr>

            <tr id="trNova" style="display: none;">
                <td style="text-indent:6px;">
                    <netui:label value="Nome da Loja:" styleClass="tblEstatica_campoNome"/>
                </td>
                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nmPessoa" size="40" maxlength="255" />
                </td>
                <td colspan="2" style="width:200pt;">&nbsp;
                </td>
            </tr>
</logic:equal>

<logic:equal name="cadastroTerminalForm" property="operacao" value="A">
    <tr id="trNova">
        <td style="text-indent:6px;width:70pt;">
            <netui:label value="Nome da Loja:" styleClass="tblEstatica_campoNome"/>
        </td>
        <td style="text-indent:6px;" colspan="3">&nbsp;
            <html:text property="nmPessoa" size="40" maxlength="255" />
        </td>
    </tr>
</logic:equal>

            <tr>
                <td>&nbsp;
                    <netui:label value="Cód Sitef:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="cdLojaOperadoraCartao" size="15" maxlength="12" />
                </td>
                <td style="text-indent:6px;">
                    <netui:label value="Operadora:" styleClass="tblEstatica_campoNome"/>
                </td>

                <html:hidden property="idUfOperadora"/>
                <td style="text-indent:6px;">&nbsp;
                    <html:text property="dsUfOperadora" size="20" readonly="true" />
                </td>
            </tr>

            <tr>
                <td style="text-indent:6px;">
                    <netui:label value="Tipo Logradouro:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nmTipoLogradouro" maxlength="255" size="15" readonly="true" />
                </td>

                <td style="text-indent:6px;">
                    <netui:label value="Título do Logradouro:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nmTituloLogradouro" maxlength="255" size="15" readonly="true" />
                </td>
            </tr>

            <tr>
                <td style="text-indent:6px;">
                    <netui:label value="Logradouro:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nmLogradouro" maxlength="255" size="35" readonly="true" />
                </td>

                <td style="text-indent:6px;">
                    <netui:label value="Número:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nrEndereco" size="6" maxlength="6" />
                </td>
            </tr>

            <tr>
                <td style="text-indent:6px;">
                    <netui:label value="Complemento:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="dsEnderecoComplemento" size="30" maxlength="255" />
                </td>

                <td style="text-indent:6px;">
                    <netui:label value="Bairro:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nmBairro" maxlength="255" size="27" readonly="true" />
                </td>
            </tr>

            <tr>
                <td style="text-indent:6px;">
                    <netui:label value="Município:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nmMunicipio" maxlength="255" size="35" readonly="true" />
                </td>

                <td style="text-indent:6px;">
                    <netui:label value="CEP:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nrCep" maxlength="9" size="8" onkeyup="checaCEP(this);" readonly="true" />
                    <a id="linkPesquisaEnd" href="javascript:pesquisarEndereco();">
                        <img src="<%= request.getContextPath() %>/resources/images/bt_icon_pesquisar_lista.gif" border="0">
                    </a>
				</td>

                <script language="javascript">
                    checaCEP(document.getElementById("nrCEP"))
                </script>
            </tr>

            <tr>
				<td style="text-indent:6px;" id="estado">
                    <netui:label value="UF:" styleClass="tblEstatica_campoNome"/>
				</td>
				<td id="nmUF" style="text-indent:6px;">&nbsp;
					<html:text property="nmUF" size="2" readonly="true" />
				</td>
<!--
				<td style="text-indent:6px;" id="pais">
                    <netui:label value="País:" styleClass="tblEstatica_campoNome"/>
				</td>
				<td id="nmPais" style="text-indent:6px;">
					<b></b>
				</td> -->
            </tr>

            <tr>
                <td colspan="4">&nbsp;<hr width="88%" align="center"></td>
            </tr>

            <tr>
                <td style="text-indent:6px;">
                    <netui:label value="Núm. Terminal:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:text property="nrTerminal" size="10" maxlength="8" />
                </td>

                <td style="text-indent:6px;">
                    <netui:label value="IP:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">
                    <html:text property="nrIp1" size="2" maxlength="3" style="text-align: center" onkeypress="return onlyDigit(this);" /> . 
                    <html:text property="nrIp2" size="2" maxlength="3" style="text-align: center" onkeypress="return onlyDigit(this);" /> . 
                    <html:text property="nrIp3" size="2" maxlength="3" style="text-align: center" onkeypress="return onlyDigit(this);" /> . 
                    <html:text property="nrIp4" size="2" maxlength="3" style="text-align: center" onkeypress="return onlyDigit(this);" />
                </td>
            </tr>

            <tr>
                <td style="text-indent:6px;">
                    <netui:label value="Cor:" styleClass="tblEstatica_campoNome"/>
                </td>

                <td style="text-indent:6px;">&nbsp;
                    <html:select property="idCor">
                        <html:option value="0">Escolha...</html:option>
                        <html:option value="1">Azul</html:option>
                        <html:option value="11">Laranja</html:option>
                        <html:option value="10">Multicor</html:option>
                        <html:option value="3">Verde</html:option>
                        <html:option value="2">Vermelho</html:option>
                    </html:select>
                </td>

                <td style="text-indent:6px;" colspan="2">
                    <html:checkbox property="inLiberadoRecarga" value="1" styleClass="radio" /> <netui:label value="Permitir recarga" styleClass="tblEstatica_campoNome"/>&nbsp;
					<html:checkbox property="inLiberadoPagamento" value="1" styleClass="radio" /> <netui:label value="Permitir pagamento" styleClass="tblEstatica_campoNome"/>
                </td>
            </tr>

            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>

            <tr>
                <td colspan="4" align="right" width="780" style="padding-right:20px;">
<logic:equal name="cadastroTerminalForm" property="operacao" value="I">
                    <img hspace="5" style="border:none;cursor:hand;" onClick="parent.divIncluiTerminal.style.display='none';" src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_cancelar_over.gif'"/>
</logic:equal>
<logic:equal name="cadastroTerminalForm" property="operacao" value="A">
                    <img hspace="5" style="border:none;cursor:hand;" onClick="parent.divAlteraTerminal.style.display='none';" src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_cancelar_over.gif'"/>
</logic:equal>
                    <acesso:controlHiddenItem nomeIdentificador="term_cad_gravar">
                        <img hspace="5" style="border:none;cursor:hand;"  onclick="gravar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
   </html:form>

   <vivo:quadroFlutuante label="Pesquisar Endereço" scroll="false" src="" idIframe="ifrmPesquisaEndereco" id="divPesquisaEndereco" spacesLeft="10" height="240" spacesTop="10" url="<%=request.getContextPath()%>" display="none" width="720" />

   </acesso:controlHiddenItem>

    <vivo:alert atributo="msgError" scope="request"/>

</body>