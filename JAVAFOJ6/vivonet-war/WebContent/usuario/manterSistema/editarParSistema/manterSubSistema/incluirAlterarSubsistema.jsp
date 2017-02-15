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
<bean:define id="FormSubSistema"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm"/>
<bean:define id="listaSubSistemas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm.listaObjSubSistemas"/>
<bean:define id="subSistema"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm.subSistema"/>
<bean:define id="idSistema"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm.idSistema"/>
<bean:define id="dsSubSistema"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm.dsSubSistema"/>

<head>

    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].dsSubSistema.value) == ""){
                alert("SubSistema é um campo obrigatório, favor preencher.");
                return(false);
            } else {
                return(true);
            }
        }
    
        function incluiSubsistema(id) 
        {
            if(valida())
            {
                document.forms[0].target = "ifrmAbas";
                if(document.forms[0].idSubSistema.value == "" || document.forms[0].idSubSistema.value == "0" || document.forms[0].idSubSistema.value == 'null')
                    document.forms[0].action = "salvaSubSistema.do?tipo=novo&idSubSistema="+id;
                else
                    document.forms[0].action = "salvaSubSistema.do?tipo=edicao&idSubSistema="+id;
                    
                parent.parent.mostrar_div();
                                
                document.forms[0].submit();

            }
            return false;
        }
        
        function cancelar(){
            return false;
        }
    </script>

</head>

<body>
    <acesso:controlHiddenItem nomeIdentificador="usu_ias_verpagina">
    <script>
        document.body.style.backgroundColor = '#ededed';
        parent.parent.mostrar_div();
    </script>     
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        parent.parent.oculta_div();
    </script>
    
        <form action="salvaSubSistema.do" onsubmit="return false;" method="post">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                <table cellspacing="0" cellpadding="0" height="60">
                    <tr> 
                        <td style="text-indent:6px;" width="78">
                            <b>Subsistema:</b>
                        </td>
                        <td width="402">
                            <html:text name="FormSubSistema" property="dsSubSistema" style="width:300px" styleClass="input" maxlength="200"/>
                            <input type="hidden" name="tipo" value="<%= request.getParameter("tipo") %>">
                            <input type="hidden" name="idSubSistema" value="<%= request.getParameter("idSubSistema") %>">
                        </td>
                    </tr>             
                    <tr><td height="10"></td></tr>
                    <tr>
                        <td colspan="2" align="right" width="780" style="padding-right:20px;">
                        <acesso:controlHiddenItem nomeIdentificador="usu_ias_salvar">
                            <img hspace="5" style="border:none;cursor:hand" onClick="incluiSubsistema(document.forms[0].idSubSistema.value); return false"  src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                
            <script>
                if(document.forms[0].tipo.value == 'novo')
                    document.forms[0].dsSubSistema.value = '';
            </script>
                
       </form>
     </acesso:controlHiddenItem>
</body>