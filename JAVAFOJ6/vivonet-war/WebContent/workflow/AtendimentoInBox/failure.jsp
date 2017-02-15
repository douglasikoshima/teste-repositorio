<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento InBox
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<head>
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript">
    function fechar(){
        parent.dvEncaminhar.style.display='none';
        parent.dvSuspeito.style.display='none';
        parent.ativar_combos();
    }
    //Fim animação
    if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
</script>
</head>
<vivo:body idTable="tbMain" idDiv="dvMain" height="240" width="770">
    <acesso:controlHiddenItem nomeIdentificador="wor_failre_verpagina">
    <center><br><p>Nenhum atendimento selecionado!</p></center>
    <table align="center" border="0" width="100%">
        <tr>
            <td align="center">
                <img onClick="fechar(); return false" style="border:none;" id="btFechar" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif"/>
            </td>
        </tr>
    </table>
    </acesso:controlHiddenItem>
</vivo:body>