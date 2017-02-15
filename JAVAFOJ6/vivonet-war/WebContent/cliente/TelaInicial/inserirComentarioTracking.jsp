<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
<head>
    <script language="javascript" type="text/javascript">
    
        function salvarComentario(){
            if(trim($('dsComentarioTracking').value) == ""){
                alert("Por favor, digite o comentário a ser salvo.")
            }else{
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                var f = document.forms[0];
                f.submit();
            }
        }
    
        function closeWindow(){
            parent.document.getElementById("divComentario").style.display = "none";
        }
    
    </script>
    <script for="window" event="onload" language="jscript">
        <!--
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        -->
    </script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
</head>
<body style="margin:0;border:0;">

<form name="formComentarioTracking" method="post" action="gravarComentarioTracking.do">
    <table cellpadding="0" cellspacing="0" width="700" style="margin:5px 0 0 10px;">
        <tr>
            <td style="font-weight:bold;">
                Comentário
            </td>
        </tr>
        <tr>
            <td>
                <textarea onkeyup="checaTextarea(this, 1000)" name="dsComentarioTracking" style="width:710px;height:50px;font-family:Tahoma;font-size:11px;"></textarea>
            </td>
        </tr>
        <tr>
            <td align="right">
                <img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif"  
                     onmouseover="this.src='<%=request.getContextPath()%>/resources/images/bt_gravar_over.gif';" 
                     onmouseout="this.src='<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif';" 
                     style="cursor:pointer;" 
                     onmouseup="salvarComentario()" />
            </td>
        </tr>
    </table>
</form>

<vivo:alert atributo="msg" scope="request" afterFunction="closeWindow()" />

</body>
</html>