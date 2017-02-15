<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormSistema"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm"/>
<bean:define id="sistema"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.sistema"/>
<bean:define id="itemSistema"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.itemSistema"/>
<!-- utilizado para percorrer cada item do array de resultados-->
<bean:define id="listaSistemas"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.listaSistemas"/>

<head>

    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>

    <script language="javaScript">
        function valida() 
        {
            if(trim(document.forms[0].sgSistema.value) == "")
            {
                alert('Sigla do Sistema é um campo obrigatório, favor preencher.');
                document.forms[0].sgSistema.focus();
                return false;
                
            }else if(trim(document.forms[0].dsSistema.value) == "")
            {
                alert('Nome do Sistema é um campo obrigatório, favor preencher.');
                document.forms[0].dsSistema.focus();
                return false;

            }else if(trim(document.forms[0].nmUrlBase.value) == "")
            {
                alert('URL da Base é um campo obrigatório, favor preencher.');
                document.forms[0].nmUrlBase.focus();
                return false;

            }else if (document.forms[0].inAcessoControlado.selectedIndex == 0)
            {
                alert('Acesso Controlado é um campo obrigatório, favor preencher.');
                document.forms[0].inAcessoControlado.focus();
                return false;

            } else 
            {
                return true;
            }
        }
    
        function alteraServidor() 
        {
            if(valida())
            {
                document.forms[0].target = '_top';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        
        }
                
    </script>

</head>

<body onload="carregaCombo();">
    <acesso:controlHiddenItem nomeIdentificador="usu_as_verpagina">
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        parent.oculta_div();
    </SCRIPT>

        <form action="salvaSistema.do" method="post">
        <html:hidden name="FormSistema" property="idSistema"/>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                <table border="0" cellspacing="0" cellpadding="">
                    <tr><td height="10"></td><td></td></tr>
                    <tr>
                        <td width="125" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Sigla do Sistema:" styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" style="padding-left:3px;">
                            <html:text name="FormSistema" property="sgSistema" style="width:100px;" maxlength="10"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Nome do Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" style="padding-left:3px;">
                            <html:text name="FormSistema" property="dsSistema" style="width:250px" maxlength="100"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="URL Base: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" style="padding-left:3px;">
                            <html:text name="FormSistema" property="nmUrlBase" style="width:250px" maxlength="60" onkeypress="return ValidarTeclaURL()"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Acesso Controlado: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" align="left">&nbsp;
                            <html:select name="FormSistema" property="inAcessoControlado" value="inAcessoControlado">
                                <html:option value="">Escolha...</html:option>
                                <html:option value="1">Sim</html:option>
                                <html:option value="0">Não</html:option>
                            </html:select>
                        </td>
                    </tr>
                    <tr><td height="4"></td><td colspan="3"></td></tr>
                    <tr>
                        <td></td>
                        <td>
                            <table width="80%" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="right">
                                    <acesso:controlHiddenItem nomeIdentificador="usu_as_salvar">
                                       <img hspace="5" style="border:none;cursor:hand;" onClick="alteraServidor(); return false"  src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'"/>
                                    </acesso:controlHiddenItem> 
                                    </td>
                                    <td align="center">
                                    </td>
                                    <td align="center">
                                </td>
                            </table>
                        </td>
                    </tr>
                    <tr><td height="4"></td><td></td></tr>
                </table>
            <script>
                document.body.style.backgroundColor = '#ededed';
                document.forms[0].inAcessoControlado.value = '<bean:write name="FormSistema" property="inAcessoControlado"/>';                

            </script>
                
       </form>
       </acesso:controlHiddenItem>
</body>