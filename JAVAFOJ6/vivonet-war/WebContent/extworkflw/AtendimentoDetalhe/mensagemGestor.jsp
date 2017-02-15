<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>


<html>
<head>
    <title>Vivo Net</title>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <style type="text/css">
        #clienteWrapper {
            margin:5px;
        }
        #clienteWrapper img {
            margin-right:2px;
        }
        .gestorGerente strong {
            color:#000;
        }
        .gestorGerente td {
            color:#1865c5;
        }
    </style>
</head>
<body>
    <div id="clienteWrapper">
        <table width="100%" height="100%" border="0">
            <tr>
                <td align="center" valign="middle">
                    Esta funcionalidade está apenas disponível para clientes Pessoa Jurídica
                </td>
            </tr>
        </table>
    </div>
</body>
</html>