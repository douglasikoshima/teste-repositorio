<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="UFs"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.regionais"/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.pesquisaEndereco"/>
<acesso:controlInitEnv/>
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script>
            <!--
            var idMotivoAlteracaoSelecionado = '<%=request.getAttribute("idMotivoAlteracaoEndereco")%>';
        
            function pesquisarEnderecos(){               
                document.getElementById("cep").value = limpaInteiro(document.getElementById("cep").value);        
                if(document.getElementById("cep").value==""){        
                    if((trim(document.getElementById('cidade').value)=="") || (document.getElementById('uf').value=="")){                
                        alert('Quando não houver nº de CEP, favor preencher Município e UF!');
                        return false;
                    }else{
                        document.forms[0].action="buscaEnderecoCD.do";
                        document.forms[0].target="frmResultadoPesquisa"
                        document.forms[0].submit();
                    }
                }else{
                    document.forms[0].action="buscaEnderecoCD.do";
                    document.forms[0].target="frmResultadoPesquisa"
                    top.frameApp.mostrar_div();
                    document.forms[0].submit();
                }
            }
            -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            top.frameApp.oculta_div();
            document.body.style.backgroundColor = '#ededed';
        -->
        </SCRIPT>
    </head>
    <body>
        <acesso:controlHiddenItem nomeIdentificador="fid_pe_verpagina">
            <html:form action="/fidelizacao/buscaEnderecoCD.do">
                <table width="700" border="0" align="center" height="200" cellpadding="0" cellspacing="5">
                    <tr>
                        <td valign="top">
                            <table width="700" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td width="80">Logradouro:</td>
                                    <td width="410"><html:text name="Form" property="filtroPesquisa.dsLogradouro" style="width:400px;" styleId="logradouro"/></td>
                                    <td width="60">Bairro:</td>
                                    <td width="140"><html:text name="Form" property="filtroPesquisa.dsBairro" style="width:180px;" styleId="bairro"/></td>
                                </tr>
                                <tr><td height="5"></td></tr>
                                <tr>
                                    <td>Município:</td>
                                    <td>
                                        <html:text name="Form" property="filtroPesquisa.dsLocalidade" style="width:273px;" styleId="cidade"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                        CEP:&nbsp;&nbsp;
                                        <html:text name="Form" property="filtroPesquisa.nrCEP" style="width:80px;" onkeypress="checaCEP(this);" styleId="cep" maxlength="9"/>
                                    </td>
                                    <td>UF:</td>
                                    <td style="padding-left:3px;">
                                        <html:select name="Form" property="filtroPesquisa.idUFSelecionado" styleId="uf">
                                            <html:option value="">--</html:option>
                                            <html:options collection="UFs" property="idUF" labelProperty="sgUF"/>
                                        </html:select>
                                        <acesso:controlHiddenItem nomeIdentificador="fid_pe_pesquisar">
                                            <img hspace="22" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;text-align:right;" onClick="pesquisarEnderecos(); return false;"/>
                                        </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                                <tr><td height="5"></td></tr>
                                <tr>
                                    <td colspan="4">
                                        <iframe name="frmResultadoPesquisa" id="ifrResultadoPesquisa" frameborder="0" src="buscaEnderecoCD.do?iniciarTela=true" marginwidth="0" marginheight="0" scrolling="no" width="700" height="170"></iframe>
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