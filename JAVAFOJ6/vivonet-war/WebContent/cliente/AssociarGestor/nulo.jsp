<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<html>
    <head>
        <title>
        </title>
    </head>
    <body bgcolor="#e3ecf4">
            <script>
                parent.oculta_div();
                setTimeout("parent.document.getElementById('valorPesquisa').focus()",300);
            </script>
    </body>
</html>
