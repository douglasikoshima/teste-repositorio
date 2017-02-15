<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!-- Form Bean -->
<bean:define id="Formdominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dominioForm"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() 
        {
            if (trim(document.getElementById("dominioVO.nmTabelaDominio").value) == "")
            {
                alert("Dominio é um campo obrigatório, favor preencher.");
                return false;
            }
             
           return true;
        }

        function persisteDominio() 
        {
            if(valida())
            {
                document.forms[0].action = 'incluiAlteraDominio.do';
                parent.parent.mostrar_div();
                document.forms[0].submit();
            }
        }

    </script>
    
    <script language="javascript" for="window" event="onload">
    <!--          
        parent.parent.oculta_div();
    -->
    </script> 

</head>

<body>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
        
    <html:form action="incluiAlteraDominio" target="_parent" onsubmit="return false;">  
        <html:hidden name="Formdominio" property="dominioVO.idTabelaDominio"  />   
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px;">
                        <b><font color="red">*</font> Domínio:</b>
                    </td>
                    <td>
                        <html:text name="Formdominio" property="dominioVO.nmTabelaDominio" size="60" maxlength="254"/>
                    </td>
                <tr>
                    <td colspan="2" align="right" width="780" style="padding-right:20px;">
                        <img hspace="5" style="border:none;cursor:hand;"  onclick="persisteDominio();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                    </td>
                </tr>
            </table>
   </html:form>    
</body>