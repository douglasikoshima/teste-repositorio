<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
   

<head>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>      
    <script>
    function save(){
      this.location = "setServico.do?idUsuario=1&nrLinha=999944444&servico=1&operacao=0";
    }
    </script>
</head>
<html>
<body style="background-color:#EDEDED;">
  <table width="80%" align="center">
        <tr>
            <td width="20%" valign="middle">&nbsp;Numero Bloqueado com sucesso</td>                        
        </tr>
   </table>


</body>
   
</html>

            
        
