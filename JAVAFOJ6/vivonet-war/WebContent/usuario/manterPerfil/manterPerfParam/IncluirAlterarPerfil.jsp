<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm"/>

<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida(){
            if (trim(document.forms[0].dsPerfil.value) == ""){
                alert("Perfil é um campo obrigatório, favor preencher.");
                return(false);
            } else 
                return(true);
        }

        function salvaPerfil(){
            if(valida()){
                    document.forms[0].action = 'salvaPerfil.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
            }
        }

        function carregaPerfil(){            
            parent.oculta_div();
        }

    </script>
    <script language="javascript" for="window" event="onload">
    <!--   
        document.body.style.backgroundColor = '#ededed';
        if('<bean:write name="Form" property="msgError" />' != ""){
            alert('<bean:write name="Form" property="msgError" />');
        }
        parent.oculta_div();
    -->
    </script> 
</head>

<body onload="carregaPerfil();">
    <acesso:controlHiddenItem nomeIdentificador="usu_iap_verpagina">    
    <form action="salvaPerfil.do" target="_parent" onSubmit="return false;" method="post">  
    <input type="hidden" name="acao" value='<%=request.getParameter("acao")%>'>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px;">
                        <b>Perfil:</b>
                    </td>
                    <td>                        
                        <html:hidden name="Form" property="idPerfil" />
                        <html:text name="Form" tabindex="1" property="dsPerfil" style="width:420px" maxlength="254" onkeyup="checaStrEspecial(this);" onblur="document.getElementById('btSalvar').focus();"/>                        
                    </td>
                <tr>
                    <td colspan="2" align="right" width="780" style="padding-right:20px;">
                    <acesso:controlHiddenItem nomeIdentificador="usu_iap_salvar">
                        <img hspace="5" id="btSalvar" style="border:none;cursor:hand"  onclick="salvaPerfil();return false;" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" onBlur="//document.forms[0].dsPerfil.focus();"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
   </form>
   <script>
        <%if(request.getParameter(ConstantesCRM.SACTION) != null && request.getParameter(ConstantesCRM.SACTION).trim().equals("incluir")){%>
            document.forms[0].dsPerfil.value = '';
        <%}%>  
        document.forms[0].dsPerfil.focus();                          
   </script>
   
   
   </acesso:controlHiddenItem>
</body>