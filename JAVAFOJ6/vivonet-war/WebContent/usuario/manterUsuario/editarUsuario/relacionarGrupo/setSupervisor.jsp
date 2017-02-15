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

<bean:define id="setSupervisorForm"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="setSupervisorForm"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function salvar()
        {
            document.forms[0].submit();
            parent.parent.oculta_div();
        }
    </script> 

</head>

<body onload="">  

    <acesso:controlHiddenItem nomeIdentificador="usu_umer_verpagina">
    <script language="javascript" for="window" event="onload">
    <!--   
        document.body.style.backgroundColor = '#ededed';
    -->
    </script> 
        
    <form action="setSupervisor.do" target="_parent" onSubmit="return false;" method="post">   
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60" width="100%">
                <tr> 
                    <td style="text-indent:6px" width="35%">
                        <b>Grupos:</b>
                    </td>
                    <td width="65%" align="left">                           
                        <bean:write name="setSupervisorForm" property="dsGrupo" />                 
                    </td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
                <tr> 
                    <td style="text-indent:6px" width="35%">
                        <b>Supervisor:</b>
                    </td>
                    <td width="65%" align="left">     
                    <html:select name="setSupervisorForm" property="inSupervisor">                   
                        <html:option value="1">Sim</html:option>
                        <html:option value="0">Não</html:option>
                    </html:select>
                    </td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
                <tr><td height="10" colspan="2" align="right">                
                    <acesso:controlHiddenItem nomeIdentificador="usu_umer_btGravar">
                        <img id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" onClick="salvar();" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                </td></tr>
            </table>
   </form>
    </acesso:controlHiddenItem>
</body>
