<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormManterServ" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm"/>
<!-- array de grupos - contém a lista de grupos existentes -->
<bean:define id="aServidores"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm.listaServidorVO"/>
<bean:define id="servidorVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm.servidorVO"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="javaScript">
        function valida() {
            if (document.forms[0].dsservidor.value == ""){
                alert("Favor preencher o campo!");
                return(false);
            } else {
                return(true);
            }
        }
    
        function alteraServidor() {
            return false;
        }
        
        function cancelar(){
            return false;
        }
    </script>

</head>

<body>
    <acesso:controlHiddenItem nomeIdentificador="usu_aser_verpagina">
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>     
        <script language="javascript" for="window" event="onload">                    
            <!--   
            if('<bean:write name="FormManterServ" property="msgError" />' != "")
            {
                alert('<bean:write name="FormManterServ" property="msgError" />');
            }      
            -->        
            parent.oculta_div();
        </script>
        <form action="salvaServidor.do" target="_parent" method="post"> 
        <html:hidden name="servidorVO" property="idServidor"/>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                <table cellspacing="0" cellpadding="0" height="60">
                    <tr> 
                        <td style="text-indent:6px;" width="78">
                            <b>Servidor:</b>
                        </td>
                        <td width="402">
                            <input style="width:400px;" type="text" name="dsservidor" maxlength="254">
                        </td>
                    </tr>             
                    <tr><td height="10"></td></tr>
                    <tr>
                        <td colspan="2" align="right" width="780" style="padding-right:20px;">
                            <acesso:controlHiddenItem nomeIdentificador="usu_aser_gravar">
                               <img hspace="5" style="border:none;" onClick="alteraServidor(); return false"  src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_alterar_over.gif'" style="border:none;cursor:hand;"/>
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                
       </form>
      </acesso:controlHiddenItem>
</body>