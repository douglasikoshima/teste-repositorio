<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>

   

<head>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>      
    <script>
    function save(){
      this.location = "setServico.do?codServico=<%=request.getParameter("codServico")%>&operacao=0";
    }
    </script>    
</head>
<html>
<body >
  <table width="80%" align="center">
        <tr>
            <td  colspan="2" valign="middle">O serviço selecionado está ativo. <br> Clique no botão abaixo para desativar.</td>            
         </tr>
        <tr>
            <td  valign="middle">
                <img src="/FrontOfficeWeb/resources/images/bt_desativar_nrml.gif" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_desativar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_desativar_nrml.gif';" style="border:none;cursor:hand;" onClick="save();"/>
            </td>
            <td  valign="middle">&nbsp;</td>                        
         </tr>         
   </table>
   

</body>
</html>
