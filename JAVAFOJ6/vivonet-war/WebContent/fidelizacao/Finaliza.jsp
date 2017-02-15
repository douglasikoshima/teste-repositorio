<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<html>
<script>
        top.frameApp.location.href = "/FrontOfficeWeb/index.jsp";
</script>
    <head>
        <title>
            Web Application Page
        </title>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="fid_final_verpagina">
        <p></p>
    </acesso:controlHiddenItem>
    </body>
</html>
