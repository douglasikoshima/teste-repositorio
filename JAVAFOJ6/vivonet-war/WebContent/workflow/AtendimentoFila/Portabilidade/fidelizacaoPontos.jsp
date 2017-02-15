<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="fidelizacaoForm"
             type="workflow.AtendimentoFila.Portabilidade.PortabilidadeController.FidelizacaoForm" />

<jsp:include page="fidelizacaoDadosLinha.jsp" />

<div id="fidelizacaoWrapper">

    <vivo:quadro id="divPontos" height="111" width="743" label="Pontos" scroll="no">
        <table width="100%">
            <tr>
                <td>Quantidade:</td>
                <td width="90%">
                    <html:text name="Form" property="dadosPontos.qtPontos" styleId="qtPontosInput" style="width:30px;margin:0 30px 0 4px;" onkeyup="checaInteiro(this)" onblur="checaInteiro(this)" />
                    <a href="http://sac.celular.telerj.net.br/pontos/login" target="_blank"><img border="0" src="<%=request.getContextPath()%>/resources/images/bt_progpts_nrml.gif" /> </a>
                </td>
            </tr>
            <tr>
                <td nowrap><strong>Oferta de Exceção</strong>:</td>
                <td>
                    <html:checkbox name="Form" property="inExcecao" styleClass="checkbox" />
                </td>
            </tr>
        </table>
    </vivo:quadro>

</div>

<jsp:include page="fidelizacaoNavigation.jsp"/>

<script type="text/javascript" language="javascript">
    operacaoAtual = 'fidelizacaoPontos';
    proximaOperacao = 'finalizacao';
</script>