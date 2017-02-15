<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="abaContato"
             type="cliente.TelaInicial.DetalheCliente.DetalheClienteController.AbaContato" />
<html>
<head>
    <title>Vivo Net</title>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <style type="text/css">
        #clienteWrapper {
            margin:0px;
            display:block;
            width:770px;
            height:235px;
            overflow-x:hidden;
            overflow-y:auto;
        }
        #clienteWrapper img {
            margin-right:2px;
        }
        .gestorGerente strong {
            color:#000;
        }
        .gestorGerente td {
            color:#1865c5;
        }
    </style>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript">

        cancelarInclusaoAlteracaoGestorGerente = function(){
            $('dvIncluirAlterarGestorGerente').remove();
        }

        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }

    </script>
</head>
<body>
<div id="clienteWrapper">
     <!--acesso:controlHiddenItem nomeIdentificador="cli_esp_verpagina"-->
     <logic:equal name="Form" property="clienteEspecial" value="true">
     <logic:iterate id="clienteEspecial"
                   name="Form"
                   property="listaClienteEspecial"
                   indexId="c"
                   type="br.com.vivo.fo.ctrls.cliente.associarGestor.db.ClienteEspecial">

                    <table width="100%">
                         <tr>
                            <td width="30%"><strong>Cargo</strong></td>
                            <td width="30%" style="color:#1865c5;">
                                <bean:write name="clienteEspecial" property="nmTipoRelacionamento" />
                            </td>
                            <td width="20%">&nbsp;</td>
                            <td width="20%" colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="30%"><strong>Nome</strong></td>
                            <td width="30%" style="color:#1865c5;">
                                <bean:write name="clienteEspecial" property="nmNomeCompleto" />
                            </td>
                            <td width="20%"><strong>CPF</strong></td>
                            <td width="20%" colspan="2" style="color:#1865c5;"><bean:write name="clienteEspecial" property="nrCpfFormatado" /></td>
                        </tr>
                        <tr>
                            <td width="30%"><strong>Tel. Contato com a Vivo 1</strong></td>
                            <td width="20%" style="color:#1865c5;"><bean:write name="clienteEspecial" property="nrLinhaContato1Formatado" /></td>
                            <td width="20%"><strong>Tel. Contato com a Vivo 2</strong></td>
                            <td width="20%" colspan="2" style="color:#1865c5;"><bean:write name="clienteEspecial" property="nrLinhaContato2Formatado" /></td>
                        </tr>
                        <tr>
                            <td width="30%"><strong>E-mail</strong></td>
                            <td width="20%" style="color:#1865c5;"><bean:write name="clienteEspecial" property="email" /></td>
                            <td width="20%"><strong>Data de nascimento</strong></td>
                            <td width="20%" style="color:#1865c5;"><bean:write name="clienteEspecial" property="dtAniversarioFormatado" /></td>
                        </tr>
                        <tr>
                            <td width="30%"><strong>Última alteração</strong></td>
                            <td width="30%" style="color:#1865c5;"><bean:write name="clienteEspecial" property="dtUltimaAlteracaoFormatado" /></td>
                            <td width="20%"><strong>Login responsável</strong></td>
                            <td width="20%" style="color:#1865c5;"><bean:write name="clienteEspecial" property="nmLoginUsuario" /></td>
                        </tr>
                    </table>
                    <br>
   </logic:iterate>
   </logic:equal>
   <logic:equal name="Form" property="clienteEspecial" value="false">
                <table width="100%">
                         <tr>
                            <td width="30%"><strong>Cargo</strong></td>
                            <td width="30%">
                                &nbsp;
                            </td>
                            <td width="20%">&nbsp;</td>
                            <td width="20%" colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="30%"><strong>Nome</strong></td>
                            <td width="30%">
                                &nbsp;
                            </td>
                            <td width="20%"><strong>CPF</strong></td>
                            <td width="20%" colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="30%"><strong>Tel. Contato com a Vivo 1</strong></td>
                            <td width="20%">&nbsp;</td>
                            <td width="20%"><strong>Tel. Contato com a Vivo 2</strong></td>
                            <td width="20%" colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="30%"><strong>E-mail</strong></td>
                            <td width="20%">&nbsp;</td>
                            <td width="20%"><strong>Data de nascimento</strong></td>
                            <td width="20%">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="30%"><strong>Última alteração</strong></td>
                            <td width="30%">&nbsp;</td>
                            <td width="20%"><strong>Login responsável</strong></td>
                            <td width="20%">&nbsp;</td>
                        </tr>
                    </table>
      </logic:equal>
   <!--/acesso:controlHiddenItem-->
</div>
</body>
</html>