<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<html>
<vivo:alert atributo="msgError" scope="request"/>
<script>
    //top.frameApp.CarregaAba("bt02");
    top.frameApp.CarregaAba("bt02");
    top.frameApp.controlCombos1();
    top.frameApp.DoResizeHeaderTableVivo();
</script>
</html>