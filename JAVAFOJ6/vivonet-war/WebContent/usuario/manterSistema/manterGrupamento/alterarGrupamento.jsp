<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormManterGrupam" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm"/>
<bean:define id="grupamento"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm.grupamento"/>
<bean:define id="aGrupamentos"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm.listaGrupamento"/>

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
    
        function alteraGrupamento() {
            return false;
        }
        
        function cancelar(){
            return false;
        }
    </script>
    
    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormManterGrupam" property="msgError" />' != "")
        {
            alert('<bean:write name="FormManterGrupam" property="msgError" />');
        }
        parent.oculta_div();
    -->
    </script> 
            

</head>

<body>
    <acesso:controlHiddenItem nomeIdentificador="usu_agr_verpagina">
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
        <form action="salvaGrupam.do" target="_parent" method="post"> 
        <html:hidden name="grupamento" property="idGrupamento"/>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                <table cellspacing="0" cellpadding="0" height="60">
                    <tr> 
                        <td style="text-indent:6px;" width="78">
                            <b>Grupamento:</b>
                        </td>
                        <td width="402">
                            <input style="width:400px;" type="text" name="dsgrupamento" maxlength="60">
                        </td>
                    </tr>             
                    <tr><td height="10"></td></tr>
                    <tr>
                        <td colspan="2" align="right" width="780" style="padding-right:20px;">
                            <acesso:controlHiddenItem nomeIdentificador="usu_agr_cancelar"><img hspace="5" style="border:none;cursor:hand;" onClick="cancelar(); return false"  src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_cancelar_over.gif'"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_agr_alterar"><img hspace="5" style="border:none;cursor:hand;" onClick="alteraGrupamento(); return false"  src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_alterar_over.gif'"/></acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                
       </form>
     </acesso:controlHiddenItem>
</body>