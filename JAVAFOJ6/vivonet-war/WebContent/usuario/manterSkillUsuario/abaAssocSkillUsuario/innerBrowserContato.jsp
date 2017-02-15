<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<html>
    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body>
        <div id="div_contato_disp_inner" style="width:340px;height:150px;overflow:auto;" nowrap class="tbl_bggray">
            <script>
                <%=(String)request.getAttribute("arvore")%>
            </script>
        </div>
        <script>
            parent.document.getElementById('div_contato_disp').innerHTML = document.getElementById('div_contato_disp_inner').innerHTML;    
            parent.document.forms[0].contatoSelecionado.length = 0;
            parent.parent.oculta_div();
        </script>
    </body>
</html>