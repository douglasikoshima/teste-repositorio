<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="detalhesProcessoForm"
             type="workflow.AtendimentoFila.Portabilidade.formBeans.DetalhesProcessoForm" />

<div id="clienteWrapper">
    <vivo:abaGrupo id="abasDetalheProcessoCliente" width="250" height="10" styleClass="abatexto">
        <vivo:abaItem id="abaSolicitante" onclick="CarregaAba('detalheProcessoSolicitante')" value="Solicitante" select="S"/>
        <vivo:abaItem id="abaGestorGerente" onclick="CarregaAba('detalheProcessoGestorGerente')" value="Consultar Gestor/Gerente"/>
    </vivo:abaGrupo>
    <div id="contentAbasCliente">
        <jsp:include page="detalheProcessoSolicitante.jsp" />
    </div>
</div>

<style type="text/css">
    #contentAbas {
        width:778px;
        height:390px;
        background:#ededed;
        border:1px solid #adadad;
    }
    #contentAbasCliente {
        width:760px;
        height:100px;
        background:#ededed;
        border:1px solid #adadad;
        padding:5px;
    }
    #contentAbasCliente dl {
		width:41.1em;
		padding: 0;
    }
	#contentAbasCliente dl dt {
		width:120px;
		float:left;
		margin:0;
		padding:.5em;
		font-weight: bold;
    }
	#contentAbasCliente dl dd {
		color:#1865c5;
        float:left;
		width:24em;
		margin:0;
		padding:.5em;
    }
    #clienteWrapper {
        margin:10px;
    }
</style>