<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="extratoForm"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>        
        <script>
            function validaPesquisa() {
                if (document.getElementById("dtInicial").value == "")
                    alert("A data inicial é obrigatoria!");
                else if (document.getElementById("dtFinal").value == "")
                    alert("A data final é obrigatoria!");
                else {                    
                    document.forms[0].action="pesquisarExtrato.do";
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
        </script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
	<acesso:controlHiddenItem nomeIdentificador="cli_epp_verpagina">
        <logic:notEmpty name="idSistemaOrigem">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td>Sistema indisponível para essa linha.</td>
                    </tr>
            </table>            
        </logic:notEmpty>
        <logic:empty name="idSistemaOrigem">        
        <form action="pesquisarExtrato.do" id="pesquisarExtrato" target="detalheExtrato" method="post">    
            <div class="tbl_bgGray">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td height="5"></td>
                    </tr>
                    <tr>                                
                        <td style="text-indent:8px;" width="50">Período:</td>
                        <td colspan="3">&nbsp;de 
                            <html:text style="width:65px;" name="form" property="dtInicial" styleId="dtInicial" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicial', '%d/%m/%Y');">
                                at&eacute;&nbsp;
                            <html:text style="width:65px;" name="form" property="dtFinal" styleId="dtFinal" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFinal', '%d/%m/%Y');">
                         </td>
                        <td valign="middle" align="right">
						<acesso:controlHiddenItem nomeIdentificador="cli_epp_pesq">
                            <img style="border:none;" onClick="validaPesquisa(); return false" vspace="5" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>
                        </acesso:controlHiddenItem>
						</td>                                       
                    </tr>
                    <tr>
                        <td colspan="5"><iframe name="detalheExtrato" height="350" width="100%" frameborder="0" scrolling="auto"></iframe>                        </td>
                    </tr>
                </table>
            </div>
        </form>
        </logic:empty>
                    
	</acesso:controlHiddenItem>
    </body>
</html>
