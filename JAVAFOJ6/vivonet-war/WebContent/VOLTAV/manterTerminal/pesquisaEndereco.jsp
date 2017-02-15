<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>

<html>

<script language="javascript">

    function pesquisarEnderecos() {
        if(validaCampos()) {
            //controlCombos();
            if (top.frameApp.dvAnimarAguarde != null)
                top.frameApp.startAnimation();
            document.forms[0].target = 'frmResultadoPesquisa';
            document.forms[0].submit();
        } else {
            return false;
        } 
    }

    function validaCampos() {
        if (document.forms[0].cep.value == "") {
            if (document.forms[0].localidade.value == "") {
                alert("Favor preencher o campo Município.");
                document.forms[0].localidade.focus();
                return false;
            } else if(document.forms[0].idEstadoSelecionado.value == "") {
                alert("Favor selecionar um Estado.");
                document.forms[0].idEstadoSelecionado.focus();
                return false;
            }
            return true;
        } else {
            checaCEP(document.forms[0].cep);
            cep = document.forms[0].cep.value;
            cep = cep.replace("-","");
            document.forms[0].cep.value = cep;
            return true;
        } 
    }

    function controlCombos() {
        for(i=0; i < document.frames.length; i++) {
            var  array = document.frames[i].document.getElementsByTagName("SELECT");
            internalCombos(array);
        }
        internalCombos(document.getElementsByTagName("SELECT"));   
    }

    function internalCombos(array) {
        for(j=0; j < array.length; j++) {
            if(array[j].style.display=='none')
                array[j].style.display='';
            else
                array[j].style.display='none';
        }
    }

    function limpaFormEndereco(){
        document.forms[0].dsLogradouro.value = "";
        document.forms[0].dsBairro.value = "";
        document.forms[0].dsLocalidade.value = "";
        document.forms[0].nrCEP.value = "";
        document.forms[0].idUFSelecionado.value = "";

        document.getElementById('ifrResultadoPesquisa').src = '';
        return;
    }

</script>


<SCRIPT FOR="window" EVENT="onload" LANGUAGE="JScript">
<!--
    if(top.frameApp.dvAnimarAguarde != null)
        top.frameApp.stopAnimation();
-->
</SCRIPT>

<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
</head>

<body>

<acesso:controlHiddenItem nomeIdentificador="term_pesq_end">

<html:form action="/VOLTAV/manterTerminal/pesquisarEndereco.do">
    <table width="98%" align="center" height="220" cellpadding="0" cellspacing="10">
        <tr>
            <td valign="top">
                <table width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="60">Logradouro:</td>
                        <td width="310"><html:text property="dsLogradouro" size="40" maxlength="255" styleId="logradouro" /></td>
                        <td width="40">Bairro:</td>
                        <td width="250"><html:text property="dsBairro" size="30" maxlength="255" styleId="bairro"  /></td>
                    </tr>
                    <tr><td height="5"></td></tr>
                    <tr>
                        <td>Município:</td>
                        <td>
                            <html:text property="dsLocalidade" size="30" maxlength="255" styleId="localidade" />&nbsp;&nbsp;&nbsp;&nbsp;
                            CEP:&nbsp;
                            <html:text property="nrCEP" style="width:80px;" styleId="cep" onkeyup="checaCEP(this)" size="10"/>
                        </td>
                        <td>UF:</td>
                        <td style="padding-left:3px;">
                            <bean:define id="comboUF" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm.comboUF"/>
                            <html:select property="idUFSelecionado" styleId="idEstadoSelecionado">
                                <option value="">Escolha...</option>
                                <html:options collection="comboUF" property="idUF" labelProperty="sgUF"/>
                            </html:select>
                          <acesso:controlHiddenItem nomeIdentificador="term_pesq_end_pesquisar">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" style="border:none;" onClick="pesquisarEnderecos();return false;"/>
                          </acesso:controlHiddenItem>
                          <img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" hspace="5" onClick="limpaFormEndereco(); return false"/>
                        </td>

                    </tr>
                    <tr>
                      <td height="10"></td>
                     </tr>
                    <tr>
                        <td colspan="4">
                            <iframe name="frmResultadoPesquisa" id="ifrResultadoPesquisa" frameborder="0" marginwidth="0" marginheight="0" scrolling="auto" width="690" height="150"></iframe>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
  </html:form>

  </acesso:controlHiddenItem>

<script>
    document.body.style.backgroundColor = '#ededed';
</script>

</body>
  
</html>
