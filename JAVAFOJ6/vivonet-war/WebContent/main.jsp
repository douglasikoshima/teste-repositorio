<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<html>
    <head>
        <title>VivonetWeb</title>
    </head>
    <body>
        <p>
            <script type="text/javascript">
            var anotherwindow=window.open("<%=request.getContextPath()%>/begin.do","VivonetWeb","height=600,width=800,status=no,toolbar=no,menubar=no,location=no,fullscreen=no,resizable=yes");
            window.opener=null;
            window.close();
            </script>
        </p>
    </body>
</html>