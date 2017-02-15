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

<div id="fidelizacaoNavigation">

    <div style="width:70px;height:30px;float:left;">
        <% if (!"fidelizacaoIntencaoCancelamento".equals(Form.getDestino())
               && !"fidelizacaoMensagemEncerramento".equals(Form.getDestino())) {  %>
            <a href="javascript:flVoltar=true;FidelizacaoFlowController()"><img id="retencaoBtVoltar" vspace="7" hspace="7" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" border="0" /></a>
        <% }  %>
    </div>

    <div  align="center" style="width:608px;height:30px;float:left;">
        <% if ("fidelizacaoMatrizOfertas".equals(Form.getDestino())) { %>
        <img id="retencaoBtNaoRetido" style="cursor:pointer;margin-right:10px" onmouseup="setVaiPensarNaoRetido('fidelizacaoNaoRetido')" vspace="7" hspace="7" src="<%=request.getContextPath()%>/resources/images/bt_naoretido_nrml.gif" border="0" />
        <img id="retencaoBtVaiPensar" style="cursor:pointer;margin-left:10px;" onmouseup="setVaiPensarNaoRetido('fidelizacaoVaiPensar')" vspace="7" hspace="7" src="<%=request.getContextPath()%>/resources/images/bt_vaipensar_nrml.gif" border="0" />
        <img id="retencaoBtReter" style="margin-right:10px;cursor:pointer" onmouseup="FidelizacaoReter()" vspace="7" hspace="7" src="<%=request.getContextPath()%>/resources/images/bt_reter_nrml.gif" border="0" />
        <% } else if ("fidelizacaoBonus".equals(Form.getDestino())
                      || "fidelizacaoAdequacaoPlanos".equals(Form.getDestino())
                      || "fidelizacaoAparelhos".equals(Form.getDestino())) { %>
        <img id="retencaoBtVaiPensar" style="cursor:pointer;margin-left:10px;" onmouseup="setVaiPensarNaoRetido('fidelizacaoVaiPensar')" vspace="7" hspace="7" src="<%=request.getContextPath()%>/resources/images/bt_vaipensar_nrml.gif" border="0" />
        <% } %>
    </div>

    <div style="width:70px;float:right;">
        <% if ("fidelizacaoAdequacaoPlanos".equals(Form.getDestino())
               || "fidelizacaoAparelhosFinalizacao".equals(Form.getDestino())
               || "fidelizacaoBonus".equals(Form.getDestino())
               || "fidelizacaoPontos".equals(Form.getDestino())) { %>
        <a href="javascript:FidelizacaoReter()"><img id="retencaoBtReter" vspace="7" hspace="7" src="<%=request.getContextPath()%>/resources/images/bt_reter_nrml.gif" border="0" /></a>
        <% } else if ("fidelizacaoMensagemEncerramento".equals(Form.getDestino())) { %>
        <a href="javascript:FidelizacaoAcaoFinal()"><img id="retencaoBtOK" vspace="7" hspace="7" src="<%=request.getContextPath()%>/resources/images/bt_ok_nrml.gif" border="0" /></a>
        <% } else { %>
        <a href="javascript:FidelizacaoFlowController()"><img id="retencaoBtProxima" vspace="7" hspace="7" src="<%=request.getContextPath()%>/resources/images/bt_proxima_nrml.gif" border="0" /></a>
        <% } %>
    </div>

</div>