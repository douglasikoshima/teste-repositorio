<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/vivo_360.css" />
</head>
<body id="vivo_360">
<div id="vivo306_header">
    <div id="vivo306_header_logo">
        <img src="<%=request.getContextPath()%>/resources/images/vivo360_logo.gif" alt="Vivo 360" width="119" height="47" />
    </div>
    <div id="vivo306_header_login_info">

    </div>
    <div id="vivo306_header_sair">
        <img src="<%=request.getContextPath()%>/resources/images/vivo360_top_sair.gif" alt="Vivo 360" width="42" height="22" />
    </div>

</div>
<div id="vivo306_body">
    <h1>Autenticação</h1>
    <div class="basic" id="list1a">
        <a id="filtros" class="table_title">Erro</a>
        <div class="item table_body tab_content_body" style="height:450px;">
            <div style="background:#fff;text-align:center;width:780px;margin-top:220px;font-size:13px;">
                <%=request.getAttribute("msgErro")%>
                <!-- <%=request.getAttribute("detailedMessage")%> -->
            </div>
        </div>
    </div>
</div>
</body>
</html>