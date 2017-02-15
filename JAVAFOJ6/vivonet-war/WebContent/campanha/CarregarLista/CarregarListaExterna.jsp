<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>
<head>
    <title> Web Application Page </title>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script>
        function carregar() {

            var valida = true;

            var objForm = document.forms.carregarListaActionForm;

            if(trim(objForm.elements["nmLista"].value) == "")
            {
                alert("Favor preencher o campo Lista");
                valida = false;
            }
            else if(trim(objForm.elements["theFile"].value) == "")
            {
                alert("Favor preencher o campo Arquivo");
                valida = false;
            }

            if(valida)
            {
               objForm.submit();
               top.frames.frameApp.mostrar_div();
            }
        }
        </script>
        <SCRIPT FOR=window EVENT=onload>
            top.frames.frameApp.oculta_div();
        </script>
</head>
<body>
    <acesso:controlHiddenItem nomeIdentificador="cam_cle_verpagina">
    <form action="CarregarListaAction.do" name="carregarListaActionForm" enctype="multipart/form-data" method="post">

                <table height="100%" width="100%" class="tbl_bggray" border="0" cellspacing="1" cellpadding="2">
                    <tr>
                        <td align="center" colspan="2">
                        <logic:messagesPresent message="true">
                        <html:messages id="sucesso" message="true">
                        <font color="red"><bean:write name="sucesso"/>&nbsp;</font> </html:messages> </logic:messagesPresent>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                        <font color="red">
                        <netui:error value="nmLista"/>
                        &nbsp; </font>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome" align="right"><netui:label value="Lista:"/></td>
                        <td>
                            <input type="text" name="nmLista" maxlength="60" size="40" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"  align="center">
                        <font color="red">
                        <netui:error value="theFile"/>
                        &nbsp; </font>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome" align="right"><netui:label value="Arquivo:"/></td>
                        <td>
                            <input type="file" name="theFile" size="25" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="top">
                        <table border="0" cellspacing="1" cellpadding="0" align="right">
                            <tr>
                                <td >
                                <acesso:controlHiddenItem nomeIdentificador="cam_cle_grav">
                                <img hspace="10"
                                     src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
                                     style="border:none;cursor:pointer"
                                     onClick="carregar();" />
                                </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                </table>

                <netui:hidden dataSource="{actionForm.idCanalCampanha}" />
	    </form>
    </acesso:controlHiddenItem>
</body>