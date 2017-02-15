<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaLinhaForm"
             type="cliente.TelaInicial.TelaInicialController.LupaLinhaForm"/>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript">
        onload = function() {
            parent.parent.oculta_div();
            <% if (request.getAttribute(ConstantesCRM.NRPROTOCOLO) != null) { %>
            alert('Protocolo <%=request.getAttribute(ConstantesCRM.NRPROTOCOLO)%> gerado.');
            top.frameApp.updateProtocolo('<%=request.getAttribute(ConstantesCRM.NRPROTOCOLO)%>');
            top.frameApp.nrProtocoloScreenPop = '';
            <% } %>
        }
    </script>
    <style type="text/css">
        body {
            background-color:#ededed;
            padding:10px;
            font-size:11px;
        }
        dl, dt, dd {
            display:inline;
            margin:0;
        }
        dl {
            border:1px solid black;
            display:block;
            padding:20px;
            background:#fff;
            margin-bottom:20px;
        }
        dd {
            font-weight:bold;
            margin-right:50px;
        }
        div#divInfoTrafego {
            color:#666;
        }
        #divErro {
            height:150px;
            padding-top:75px;
            text-align:center;
        }
    </style>
</head>

<body rightmargin="0" leftmargin="0" topmargin="0">

    <logic:equal name="Form" property="erro" value="">

     <dl>  
            <logic:notEmpty name="Form" property="reducaoVelocidadeItem1005">
                <dt>Parcial de dados trafegados no Vivo Internet Ilimitado:</dt>
                <dd><bean:write name="Form" property="reducaoVelocidadeItem1005.bytes" ignore="true" /></dd>
            </logic:notEmpty>
            <logic:notEmpty name="Form" property="reducaoVelocidadeItem1006">
                <dt>Parcial de dados trafegados no Vivo Internet:</dt>
                <dd><bean:write name="Form" property="reducaoVelocidadeItem1006.bytes" ignore="true" /></dd>
            </logic:notEmpty>
    </dl>

    <div id="divInfoTrafego" style="margin-top:3px">
        Caso o cliente tenha pacote de Dados Vivo Internet Ilimitado  informe que ao atingir 2GB de Dados Trafegados,
        haverá redução para 128Kpbs na velocidade  do tráfego de dados.<br/>
        O  parcial de tráfego se refere ao consumo utilizado desde o início do seu ciclo.<br/><br/>
        <i>Verifique qual o ciclo do cliente na Lupa Faturamento</i>.
    </div>

    </logic:equal>

    <logic:notEqual name="Form" property="erro" value="">

    <div id="divErro"><bean:write name="Form" property="erro" /></div>

    </logic:notEqual>

</body>

</html>