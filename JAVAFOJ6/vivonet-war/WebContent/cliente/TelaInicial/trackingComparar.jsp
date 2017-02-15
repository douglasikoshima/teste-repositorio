<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="Form"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="trackingForm" type="cliente.TelaInicial.TelaInicialController.TrackingForm"/>
<logic:notEqual  value="E" name="Form" property="tpRetorno">
<bean:define id="dOrigem" name="Form" property="comparaTrackingAparelhoVO.origem"/>
<bean:define id="dSAP"    name="Form" property="comparaTrackingAparelhoVO.sap"/>
</logic:notEqual>
<html>
<head>
    <title>Compara&ccedil;&atilde;o - Pedido na origem com o SAP</title>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <style type="text/css">
        <!--
        body {width:790px;margin:5px;}
        h1, dl { font-family:Tahoma;font-size:11px;}
        h1 {font-weight:bold;font-style:italic;}
        dl.trackingDefList {width: 100%;}
        dl.trackingDefList dt {
        	width: 30%;
        	clear:both;
        	float: left;
        	font-weight: 500;
        }

        dl.trackingDefList dd {
            font-weight:bold;
	        float: none;
	        width: 90%;
	        margin: 0 0 .25em 9%;
	    }
        -->
    </style>
    <script for="window" event="onload" language="jscript">
        <!--
            if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            <logic:equal name="Form" property="tpRetorno" value="E">
                alert('<bean:write name="Form" property="msgError"/>');
                history.back();
            </logic:equal>
        -->
    </script>
</head>

<body>
<logic:equal  value="E" name="Form" property="tpRetorno">
<h1><bean:write name="Form" property="msgError"/></h1>
</logic:equal>
<logic:notEqual  value="E" name="Form" property="tpRetorno">
<div style="float:left;width:50%">
    <vivo:quadro label="Origem - Pedido" width="370" height="520" id="quadroPedido">
        <h1>Dados do pedido</h1>
        <dl class="trackingDefList">
            <dt>Número do Pedido no Sistema Origem:</dt><dd><bean:write name="dOrigem" property="nrPedido"/></dd>
            <dt>Origem do Pedido:</dt><dd><bean:write name="dOrigem" property="dsOrigem"/></dd>
            <dt>UF:</dt><dd><bean:write name="dOrigem" property="sgUF"/></dd>
            <dt>Abertura:</dt><dd><bean:write name="dOrigem" property="dtAbertura"/></dd>
            <dt></dt><dd></dd>
            <dt>Total do Pedido:</dt><dd><logic:notEmpty name="dOrigem" property="vlTotalPedido">R$ </logic:notEmpty><bean:write name="dOrigem" property="vlTotalPedido"/></dd>
            <dt>Pagamento:</dt><dd><bean:write name="dOrigem" property="dsPagamento"/></dd>
            <dt>Valor das Parcelas:</dt><dd><logic:notEmpty name="dOrigem" property="vlParcelas">R$ </logic:notEmpty><bean:write name="dOrigem" property="vlParcelas"/></dd>
            <dt>Endere&ccedil;o de Entrega:</dt><dd><bean:write name="dOrigem" property="dsEnderecoEntrega"/></dd>
            <dt>Observa&ccedil;&otilde;es:</dt><dd><bean:write name="dOrigem" property="dsObservacao"/></dd>
        </dl>
        <h1>Dados do Item</h1>
        <logic:iterate id="lsoItem" name="dOrigem" property="listaItens.itensArray" indexId="idxoItem">
        <dl class="trackingDefList">
            <dt>N&uacute;mero do Item:</dt><dd><bean:write name="lsoItem" property="nrItem"/></dd>
            <dt>C&oacute;digo:</dt><dd><bean:write name="lsoItem" property="cdItem"/></dd>
            <dt>Descri&ccedil;&atilde;o:</dt><dd><bean:write name="lsoItem" property="dsItem"/></dd>
            <dt>Valor do Item:</dt><dd><logic:notEmpty name="lsoItem" property="vlItem">R$ </logic:notEmpty><bean:write name="lsoItem" property="vlItem"/></dd>
            <dt>Quantidade:</dt><dd><bean:write name="lsoItem" property="qtItem"/></dd>
            <dt>Observa&ccedil;&otilde;es:</dt><dd><bean:write name="lsoItem" property="dsObservacao"/></dd>
        </dl>
        </logic:iterate>
    </vivo:quadro>
</div>
<div style="float:right;width:50%;">
    <vivo:quadro label="SAP - Ordem de venda" width="370" height="520" id="quadroOrdem">
        <h1>Dados do pedido</h1>
        <dl class="trackingDefList">
            <dt>Número do Pedido no Sistema Origem:</dt><dd><bean:write name="dSAP" property="nrPedido"/></dd>
            <dt>Origem do Pedido:</dt><dd><bean:write name="dSAP" property="dsOrigem"/></dd>
            <dt>UF:</dt><dd><bean:write name="dSAP" property="sgUF"/></dd>
            <dt>Abertura:</dt><dd><bean:write name="dSAP" property="dtAbertura"/></dd>
            <dt></dt><dd></dd>
            <dt>Total do Pedido:</dt><dd><bean:write name="dSAP" property="vlTotalPedido"/></dd>
            <dt>Pagamento:</dt><dd><bean:write name="dSAP" property="dsPagamento"/></dd>
            <dt>Valor das Parcelas:</dt><dd><logic:notEmpty name="dSAP" property="vlParcelas">R$ </logic:notEmpty><bean:write name="dSAP" property="vlParcelas"/></dd>
            <dt>Endere&ccedil;o de Entrega:</dt><dd><bean:write name="dSAP" property="dsEnderecoEntrega"/></dd>
            <dt>Observa&ccedil;&otilde;es:</dt><dd><bean:write name="dSAP" property="dsObservacao"/></dd>
        </dl>
        <h1>Dados do Item</h1>
        <logic:iterate id="lssItem" name="dSAP" property="listaItens.itensArray" indexId="lsoItem">
        <dl class="trackingDefList">
            <dt>N&uacute;mero do Item:</dt><dd><bean:write name="lssItem" property="nrItem"/></dd>
            <dt>C&oacute;digo:</dt><dd><bean:write name="lssItem" property="cdItem"/></dd>
            <dt>Descri&ccedil;&atilde;o:</dt><dd><bean:write name="lssItem" property="dsItem"/></dd>
            <dt>Valor do Item:</dt><dd><logic:notEmpty name="lssItem" property="vlItem">R$ </logic:notEmpty><bean:write name="lssItem" property="vlItem"/></dd>
            <dt>Quantidade:</dt><dd><bean:write name="lssItem" property="qtItem"/></dd>
            <dt>Observa&ccedil;&otilde;es:</dt><dd><bean:write name="lssItem" property="dsObservacao"/></dd>
        </dl>
        </logic:iterate>
    </vivo:quadro>
</div>
</logic:notEqual>
</body>
</html>