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

<bean:define id="UFs"  name="abaEndereco" property="pesquisaEndereco.UFVOArray"/>
<bean:define id="Form" name="abaEndereco" property="pesquisaEndereco"/>

<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
    <script type="text/javascript">
        onload = function() {
            top.frameApp.oculta_div();
        }
        validaCampos = function() {
            if ($F('cep').blank()) {

                if ($F('logradouro').blank()
                        || $F('cidade').blank()
                        || $('uf').selectedIndex == 0) {
                    alert('A pesquisa deve ser realizada através do CEP ou do Logradouro, Município e UF.');
                    return false;
                }

                if ($F('cidade')=="") {
                    alert("Por favor, digite um valor para o campo Município.");
                    $('cidade').focus();
                    return false;
                } else if ($('uf').selectedIndex == 0) {
                    alert("Por favor, selecione um Estado.");
                    $('uf').focus();
                    return false;
                } else if ($F('logradouro').blank()) {
                    alert("Por favor, digite um valor para Logradouro.");
                    $('logradouro').focus();
                    return false;
                }

            } else {
                if (verificaNumCEP($F('cep').replace("-",""))) {
                    $('cep').value = $F('cep').replace("-","");
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
        verificaNumCEP = function(numCep) {
            var ldone = false;
            if (numCep!=null && numCep!="") {
                if (numCep.length<8) {
                    alert("Número de CEP inválido!");
                    $('cep').value = "";
                    $('cep').focus();
                } else if (numCep.length==9 && numCep.indexOf("-")!=5) {
                    alert("Número de CEP inválido!");
                    $('cep').value = "";
                    $('cep').focus();
                } else {
                    ldone = true;
                }
            }
            return ldone;
        }
        pesquisarEnderecos = function() {
            if (validaCampos()) {
                $('cep').value = limpaInteiro(document.getElementById("cep").value);
                var sgUF = $('uf').selectedIndex == 0 ? '' : $('uf').options[$('uf').selectedIndex].text;
                document.forms[0].action = 'buscaEndereco.do?sgUF=' + sgUF;
                document.forms[0].target="frmResultadoPesquisa"
                top.frameApp.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }
        }
        limpar = function() {
            document.forms[0].action="pesquisaEndereco.do?limpar=TRUE&pagina=inicioPesquisa";
            document.forms[0].target="_self";
            top.frameApp.mostrar_div();
            document.forms[0].method = "POST";
            document.forms[0].submit();
        }
    </script>
</head>
<body>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
    <acesso:controlHiddenItem nomeIdentificador="cli_pend_verpagina">
    <html:form action="/cliente/TelaInicial/DetalheUsuario/buscaEndereco.do" method="post">
    <table width="760" align="center" height="520" cellpadding="0" cellspacing="10">
        <tr>
            <td valign="top">
                <table width="750" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="80">Logradouro:</td>
                        <td width="430"><html:text name="Form" property="filtroPesquisa.dsLogradouro" style="width:400px;" styleId="logradouro"/></td>
                        <td width="60">Bairro:</td>
                        <td width="180"><html:text name="Form" property="filtroPesquisa.dsBairro" style="width:180px;" styleId="bairro"/></td>
                    </tr>
                    <tr><td height="5"></td></tr>
                    <tr>
                        <td>Município:</td>
                        <td>
                            <html:text name="Form" property="filtroPesquisa.dsLocalidade" style="width:273px;" styleId="cidade"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            CEP:&nbsp;&nbsp;
                            <html:text name="Form" property="filtroPesquisa.nrCEP" style="width:80px;" onkeyup="checaCEP(this)" styleId="cep"/>
                        </td>
                        <td>UF:</td>
                        <td style="padding-left:3px;">
                            <html:select name="Form" property="filtroPesquisa.idUFSelecionado" styleId="uf">
                                <html:option value="">--</html:option>
                                <html:options collection="UFs" property="idUF" labelProperty="sgUF"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="right">
                            <acesso:controlHiddenItem nomeIdentificador="cli_pend_pesquisar">
                                <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand;" onClick="pesquisarEnderecos();"/>
                            </acesso:controlHiddenItem>
                            <img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none;cursor:hand;" onClick="limpar();"/>
                        </td>
                    </tr>
                    <tr><td height="10"></td></tr>
                    <tr>
                        <td colspan="4">

                            <iframe name="frmResultadoPesquisa" id="ifrResultadoPesquisa" frameborder="0" src="buscaEndereco.do?iniciarTela=TRUE&retorno=<bean:write name="Form" property="filtroPesquisa.idUFSelecionado" />" marginwidth="0" marginheight="0" scrolling="no" width="750" height="300"></iframe>

                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</html:form>
<script>
    document.body.style.backgroundColor = '#ededed';
</script>
</acesso:controlHiddenItem>
</body>
</html>