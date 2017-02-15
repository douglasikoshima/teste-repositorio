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

<bean:define id="salvaAgendaAniversarioForm"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="salvaAgendaAniversarioForm"/>

<head>
    <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css"> 
    <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">    
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" charset="ISO-8859-1"></script>                                         
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script language="javascript" src="/FrontOfficeWeb/resources/scripts/questionario.js" charset="ISO-8859-1"></script>    

</head>


<body>  
<acesso:controlHiddenItem nomeIdentificador="que_daa_verpagina">

    <script language="javascript" for="window" event="onload">
    <!--   
        document.body.style.backgroundColor = '#ededed';
        <logic:equal value="1" parameter="isSalvaAgendaAniversario">
            parent.questionarioCmpAtendimento_fecharDataAniversario();
        </logic:equal>
    -->
    </script> 
        
    <form action="salvaAgendaAniversario.do" onSubmit="return false;" method="post" name="salvaAgendaAniversarioForm">   
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="8" cellpadding="0" height="60" width="100%" align="center">
                <tr> 
                    <td style="text-indent:6px" width="60%">
                        <b>Agenda de Aniversário</b>
                    </td>
                    <td width="40%" align="right">                           
                        <html:text property="dataAniversario" tabindex="1" name="salvaAgendaAniversarioForm" styleClass="textfield" onkeypress="this.value = Format(this.value,'##/##/####');" maxlength="10" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataAniversario', '%d/%m/%Y');">
                    </td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
                <tr><td height="10" colspan="2" align="right">                                    
                    <acesso:controlHiddenItem nomeIdentificador="que_daa_btGravar">
                        <img id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" onClick="dataAniversario_salvar();" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                </td></tr>
            </table>
   </form>
    </acesso:controlHiddenItem>
</body>
