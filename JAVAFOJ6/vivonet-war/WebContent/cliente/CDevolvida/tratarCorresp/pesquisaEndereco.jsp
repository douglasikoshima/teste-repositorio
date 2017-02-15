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

<bean:define id="UFs"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.pesquisaEndereco.UFVOArray"/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.pesquisaEndereco"/>

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script>
        function pesquisarEnderecos(){               
            document.getElementById("cep").value = limpaInteiro(document.getElementById("cep").value);
            if(document.getElementById("cep").value=="" || document.getElementById("cep").value.length < 8){
                if((trim(document.getElementById('cidade').value)=="") || (document.getElementById('uf').value=="")){
                    alert('Quando não houver nº de CEP válido, favor preencher Município e UF!');
                    return false;
                }else{
                    document.forms[0].action="buscaEnderecoCD.do";
                    document.forms[0].target="frmResultadoPesquisa"
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }else{
                document.forms[0].action="buscaEnderecoCD.do";
                document.forms[0].target="frmResultadoPesquisa"
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function limpar(){
            document.forms[0].action="pesquisaEndereco.do";
            document.forms[0].target="_self";
            parent.mostrar_div();
            document.forms[0].submit();
        }
    </script>
    <script for="window" event="onload">
        parent.oculta_div();
    </script>
</head>
<body>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>    
    <acesso:controlHiddenItem nomeIdentificador="cli_pe_verpagina">
    <html:form action="/cliente/CDevolvida/tratarCorresp/buscaEnderecoCD.do">
    <table width="740" align="center" height="535" cellpadding="0" cellspacing="10">
        <tr>
            <td valign="top">
                <table width="740" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="80">Logradouro:</td>
                        <td width="410"><html:text name="Form" property="filtroPesquisa.dsLogradouro" style="width:400px;" styleId="logradouro"/></td>
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
                            <acesso:controlHiddenItem nomeIdentificador="cli_pe_pesquisar">
                                <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand;" onClick="pesquisarEnderecos();"/>
                            </acesso:controlHiddenItem>
                            <img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none;cursor:hand;" onClick="limpar();"/>
                        </td>
                    </tr>
                    <tr><td height="10"></td></tr>
                    <tr>
                        <td colspan="4">
                            <iframe name="frmResultadoPesquisa" id="ifrResultadoPesquisa" frameborder="0" src="buscaEnderecoCD.do?iniciarTela=TRUE&retorno=<bean:write name="Form" property="filtroPesquisa.idUFSelecionado" />" marginwidth="0" marginheight="0" scrolling="no" width="765" height="450"></iframe>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    </html:form>
    </acesso:controlHiddenItem> 
    </body>
</html>