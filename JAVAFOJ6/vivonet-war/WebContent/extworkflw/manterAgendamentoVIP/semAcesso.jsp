<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript">
    
    </script>    
</head>
<body>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    
    <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"  property="formManterAgendamentoVIP"/>    
    
        <vivo:sessao id="consultaHexa" width="700" height="260" label="Agendamento VIP"  scroll="false" operacoes="Linha sem acesso">            
            <br><br>
            <table width="100%" border="0">
                <tr>
                    <td align="center"> <br> <br>
                        <b>Esta funcionalidade não esta disponível para este perfil de cliente.</b>
                    </td>
                </tr>
            </table>
        </vivo:sessao>
         

</body>
</html>