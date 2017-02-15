<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>


<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>    

    <script language="javaScript">
        
        <logic:present name="exibir" scope="request">
            window.showModalDialog('<%=(String)request.getAttribute("exibir")%>','Denatran','dialogWidth:750px;dialogHeight:500px');
        </logic:present>
        
        function buscar() {
            document.forms[0].action="buscar.do";
            document.forms[0].submit();
        }        
        
    </script>

</head>
<body>
<vivo:sessao id="listaAgendamendo"  width="700" height="260" label="Denatran"  scroll="false" operacoes="Buscar Protocolo">            

    <html:form action="/extworkflw/denatran/buscar.do"  method="post">         
<script language="javaScript">
</script>	
        <img id="btRightSimp" align="right"  onmouseup="buscar();" src="/FrontOfficeWeb/resources/images/bt_buscar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_buscar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_buscar_nrml.gif'" style="border:none;cursor:hand;"/>
    </html:form>
</vivo:sessao>    

<vivo:alert atributo="msgStatus" scope="request"/>
    
</body>
</html>