<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"   prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"  prefix="logic"%>
<html>
    <head>
        <title></title>
    </head>
    <body>
    <logic:notEmpty  name="linCtrl">
        <logic:equal name="linCtrl" value="true">
            <script>
                alert("Não é possivel fazer a transferencia de titularidade de cliente de Linha Controle.");
                top.frameApp.location.href='/FrontOfficeWeb/cliente/TelaInicial/begin.do';
            </script>
        </logic:equal>
    </logic:notEmpty>
    <logic:notEmpty  name="tranf">
        <logic:equal name="tranf" value="true">
          <script>
            alert("Não é possivel fazer a transferencia de titularidade de cliente Pós pago.");
                top.frameApp.location.href='/FrontOfficeWeb/cliente/TelaInicial/begin.do';
          </script>
        </logic:equal>
    </logic:notEmpty>
    <logic:notEmpty  name="posPago">
        <logic:equal name="posPago" value="false">
          <script>
            alert("Não há linha validada para ser alterada.");
                top.frameApp.location.href='/FrontOfficeWeb/cliente/TelaInicial/begin.do';
          </script>
        </logic:equal>
        <logic:equal name="posPago" value="true">
          <script>
            alert("Não é possivel associar um cliente pós pago a uma linha Pré Paga.");
                top.frameApp.location.href='/FrontOfficeWeb/cliente/TelaInicial/begin.do';
          </script>
        </logic:equal>
    </logic:notEmpty>
    <logic:equal name="semCliente" value="true">
        <script>
            alert("Não há linha validada para realizar a operação");
            top.frameApp.location.href='/FrontOfficeWeb/cliente/TelaInicial/begin.do';
        </script>
    </logic:equal>
    <logic:equal name="naoIdentificado" value="true">
        <script>
            alert("Não é permitido acessar '<bean:write name="msg"/>' sem identificar um Número de linha");
            top.frameApp.location.href='/FrontOfficeWeb/cliente/TelaInicial/begin.do';
        </script>
    </logic:equal>
    <logic:equal name="geral" value="true">
        <script>
            alert("Não é possivel realizar a operação para a Linha/Cliente Selecionado!\nFavor, verifique os dados e tente novamente!!!");
            top.frameApp.location.href='/FrontOfficeWeb/cliente/TelaInicial/begin.do';
        </script>
    </logic:equal>
    <logic:equal name="bloq" value="true">
        <script>
            alert("<bean:write name="msg"/>");
            top.frameApp.location.href='/FrontOfficeWeb/cliente/TelaInicial/begin.do';
        </script>
    </logic:equal>
</body>
</html>
