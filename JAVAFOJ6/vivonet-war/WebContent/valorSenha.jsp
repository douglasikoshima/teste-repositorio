<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
    </head>
    <body>

    <form action="valorSenha.do" method="post">
        DDD: <input type="text" name="ddd" size="2" maxlength="2">
        <br>
        Telefone: <input type="text" name="fone" size="9" maxlength="9">
        <br>
        <input type="radio" name="tipoRel" value="2" checked> Cliente
        <input type="radio" name="tipoRel" value="1"> Usuario
        <br>
        <input type="submit" value="Obter">
        <%
        String valorSenha = (String) request.getAttribute("valorSenha");
        if (valorSenha != null) { %>
        <br>
        Senha gerada: <%= valorSenha %>
        <% } %>
    </form>

    </body>
</html>


