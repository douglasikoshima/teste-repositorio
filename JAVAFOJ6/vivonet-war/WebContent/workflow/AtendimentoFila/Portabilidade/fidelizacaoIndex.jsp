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
             property="fidelizacaoForm"
             type="workflow.AtendimentoFila.Portabilidade.PortabilidadeController.FidelizacaoForm" />

<div id="fidelizacaoWrapper">
    <a href="javascript:realizarOferta()"><img style="margin:5px 5px 0 0;border:none;" src="<%=request.getContextPath()%>/resources/images/bt_realizaroferta_nrml.gif" width="102" height="13" /></a>
    <a href="javascript:clienteNaoAtendeu();"><img style="margin:5px 0 0 0;border:none;" src="<%=request.getContextPath()%>/resources/images/bt_clientenaoatendeu_nrml.gif" width="126" height="13" /></a>
</div>

<style type="text/css">
    #fidelizacaoWrapper, #fidelizacaoNavigation {
        margin:5px;
    }
    #fidelizacaoWrapper {
        overflow:auto;
    }
</style>

<script type="text/javascript" language="javascript">
    proximaOperacao = 'fidelizacaoIntencaoCancelamento';
    operacaoAtual = '';
    realizarOferta = function() {
        FidelizacaoFlowController(true);
    }
    clienteNaoAtendeu = function () {
        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
        $('formPesquisaProcessos').action = "begin.do?voltar=true&inClienteNaoAtendeu=true";
        $('formPesquisaProcessos').submit();
    }
</script>

<vivo:alert atributo="msgRetorno" scope="request" />