<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm" />

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida(){
            if (trim(document.forms[0].dsSkill.value) == ""){
                alert("Por favor, preencha o campo Skill.");
                return(false);
            } else
                return(true);
        }

        function salvaSkill(){
            var idSkill = document.forms[0].idSkill.value;
            if(valida()){
                // Se ID do grupo nao foi preenchido, salva NOVO.
                if(idSkill == ''){
                    document.forms[0].action = 'insereSkill.do';
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    document.forms[0].submit();
                }else{
                    document.forms[0].action = 'alteraSkill.do';
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    document.forms[0].submit();
                }
            }
        }
    </script>
</head>
<body>
     <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
             if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        -->
    </SCRIPT>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>

    <form action="insereSkill" target="_parent" onSubmit="return false;" method="post">
        <html:hidden name="FormGrupo" property="idSkill"/>
        <html:hidden name="FormGrupo" property="idGrupo"/>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr>
                    <td style="text-indent:6px;">
                        <netui:label value="Skill:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td>
                        <html:text name="FormGrupo" property="dsSkill" size="60" maxlength="254"/>
                    </td>
                      <td colspan="2" align="right" width="780" style="padding-right:20px;">

                        <img hspace="5" style="border:none;cursor:hand;"  onclick="salvaSkill();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                    </td>
                </tr>
            </table>
   </form>
</body>