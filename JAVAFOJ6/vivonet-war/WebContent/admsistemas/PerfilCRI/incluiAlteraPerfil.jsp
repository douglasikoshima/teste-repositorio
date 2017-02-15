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

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilForm" />

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() 
        {
            if (trim(document.forms[0].nmPerfil.value) == "")
            {
                alert("Perfil é um campo obrigatório, favor preencher.");
                return false;
            }
                return true;
        }

        function salvaPerfil() 
        {
            if(valida())
            {
                document.forms[0].action = 'incluirPerfil.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }

    </script>

</head>

<body>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
        
    <form target="_parent" onSubmit="return false;">
        <html:hidden property="idPerfil" name="Form" />  
        <html:hidden property="inAcao" name="Form" value="1"/>
 
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px;">
                        <netui:label value="Perfil:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td>
                        <html:text name="Form" property="nmPerfil" size="60" maxlength="254"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right" width="780" style="padding-right:20px;">
                        <img hspace="5" style="border:none;cursor:hand;"  onclick="salvaPerfil();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                    </td>
                </tr>
            </table>
        </form>
        <script>
            parent.oculta_div();
        </script>

</body>