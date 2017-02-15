<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="ManterServidorForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm"/>
<bean:define id="servidor" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm.servidorVO"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].dsServidor.value) == ""){
                alert("Servidor é um campo obrigatório.");
                return false;
            }else{
                return true;
            }
        }

        function salvaServidor(){
            if(valida()){
                document.forms[0].action = 'salvaServidor.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }

        function cancelar(){
        <%if(request.getParameter("operacao").equals("incluir")) {%>
            top.divIncluiServidor.style.display='none';
        <%}else{%>
            top.divAlteraServidor.style.display='none';
        <%}%>
        }
        </script>
</head>
<body>
    <acesso:controlHiddenItem nomeIdentificador="usu_ais_verpagina">
    <form action="salvaServidor.do" target="_parent" onSubmit="return false;" method="post">    
    <html:hidden name="ManterServidorForm" property="idServidor" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px;" width="78">
                        <b>Servidor:</b>
                    </td>
                    <td width="402">
                        <html:text  name="ManterServidorForm" property="dsServidor" style="width:400px;" maxlength="254" onkeypress="return ValidarTeclaURL()"/>
                    </td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td colspan="2" align="right" width="780" style="padding-right:20px;">
                        <acesso:controlHiddenItem nomeIdentificador="usu_ais_incluir">
                        <img hspace="5"   onclick="salvaServidor();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;"/>
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>

    <script language="javascript" for="window" event="onload">        
        document.body.style.backgroundColor = '#ededed';
        parent.oculta_div();
        <%if(request.getParameter("operacao").equals("incluir")) {%>
            document.forms[0].dsServidor.value = '';
            document.forms[0].idServidor.value = '';
        <%}%>
    </script>
   </form>
   </acesso:controlHiddenItem>
</body>
