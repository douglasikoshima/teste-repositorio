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
  
<bean:define id="ManterTipoOrganizacaoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterTipoOrganizacaoForm"/>


<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].dsTipoOrganizacao.value) == ""){
                alert("Favor preencher o campo!");
                return(false);
            } else {
                return(true);
            }
        }

        function salvaTipoOrganizacao() {
            if(valida()) {
                document.forms[0].dsTipoOrganizacao.value = trim(document.forms[0].dsTipoOrganizacao.value);
                var action = "salvaTipoOrganizacao.do";
                document.forms[0].action = action;
                parent.mostrar_div();
                document.forms[0].submit();
                document.forms[0].idTipoOrganizacao.value = '';
                document.forms[0].dsTipoOrganizacao.value = '';
            }
        }

        function cancelar() {
            var action = "limpaTipoOrganizacao.do";
            document.forms[0].idTipoOrganizacao.value = '';
            document.forms[0].dsTipoOrganizacao.value = '';
            document.forms[0].action = action;
            parent.mostrar_div();
            document.forms[0].submit();
        }
        </script> 

</head>

<body onload="document.forms[0].dsTipoOrganizacao.select();document.forms[0].dsTipoOrganizacao.focus()">
<acesso:controlHiddenItem nomeIdentificador="usu_aitiporg_verpagina">  
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
    
    <form action="<%=request.getContextPath()%>/usuario/Organograma/manterTipoOrganizacao/salvaTipoOrganizacao.do" id="salvaTipoOrganizacao" name="salvaTipoOrganizacao" target="_parent" onSubmit="return false;" method="POST">    
        <html:hidden name="ManterTipoOrganizacaoForm" property="idTipoOrganizacao"/>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px" width="200">
                        <b>Tipo Organizacao:</b>
                    </td>
                    <td width="300" align="right">
                        <html:text property="dsTipoOrganizacao" style="width:350px;" name="ManterTipoOrganizacaoForm" maxlength="254"/>
                    </td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td colspan="2" align="right" width="500" style="padding-right:20px;">
                    <acesso:controlHiddenItem nomeIdentificador="usu_aitiporg_gravar">
        			<img vspace="5" hspace="10" id="btSalvar1" onclick="salvaTipoOrganizacao();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
   </form>
    <script language="javascript" for="window" event="onload">
    <!--   
        parent.oculta_div();
    -->
    </script> 
   </acesso:controlHiddenItem>
</body>
