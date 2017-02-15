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

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="FrontOffice - Portabilidade" />
<netui-template:setAttribute name="modulo" value="Detalhes do Processo de Portabilidade" />
<netui-template:section name="headerSection">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
    <link rel="stylesheet" type="text/css" href="css/filaPortabilidade.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" src="javascript/detalheProcesso.js" ></script>
    <script type="text/javascript" src="javascript/fluxoRetencao.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <style type="text/css">
        .radioCheckbox {border:none;background:none;margin:0;padding:0;}
        select {margin-left:3px}
        .dadosProcesso {
            color:#1865c5;
        }
        #detalheProtocolo {
            background:#fff;
            border:1px solid #adadad;
            padding:7px;
            margin-bottom:6px;
            height:30px;
        }
        #contentAbas {
            overflow:hidden;
            width:778px;
            height:390px;
            background:#ededed;
            border:1px solid #adadad;
        }
    </style>
</netui-template:section>
<netui-template:section name="bodySection">

    <logic:notEqual name="Form" property="dsOrigem" value="TELAATENDIMENTO">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    </logic:notEqual>

    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <% int height = !"TELAATENDIMENTO".equals(Form.getDsOrigem()) ? 470 : 450; %>
    <vivo:sessao id="bodyRelatoriosTracking" height='<%=String.valueOf(height)%>' width="790" label="Workflow" operacoes="Detalhes do Processo de Portabilidade" scroll="N">

        <form name="formPesquisaProcessos" id="formPesquisaProcessos" action="/FrontOfficeWeb/workflow/AtendimentoFila/Portabilidade/begin.do?voltar=true" method="post">

        <div id="detalheProtocolo">
            <span style="float:left;margin-right:20px">Protocolo de Atendimento: <span class="dadosProcesso"><bean:write name="Form" property="detalheProcesso.nrProtocolo" /></span></span>
            <span style="float:left;margin-right:20px">Protocolo de Portabilidade: <span class="dadosProcesso"><bean:write name="Form" property="dadosProcesso.nrProtocoloPortabilidade" /></span></span>
            <span id="spanWorkspaceControl" style="display:none;float:right;margin:0;padding:0;width:9px;height:9px;display:block">
                <a href="javascript:WorkspaceControl();"><img id="imgWorkspaceControl" src="<%=request.getContextPath()%>/resources/images/bt_small_minimize.gif" width="10" height="10" border="0" /></a>
            </span>
            <% if (Form.getListaLinhasAssociadas().getDetalheLinhaVOArray().length > 0) { %>
            <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="70" tableWidth="745" styleId="tbLinhasAssociadas" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="33%" type="string">Linha</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="33%" type="string">Conta</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="33%" type="string">Protocolo de Portabilidade</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:iterate id="iteratorLinhasAssociadas" name="Form" property="listaLinhasAssociadas.detalheLinhaVOArray">
                        <vivo:tbRow key="linha">
                            <vivo:tbRowColumn><bean:write name="iteratorLinhasAssociadas" property="numero" /></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="iteratorLinhasAssociadas" property="nrConta" /></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="iteratorLinhasAssociadas" property="nrProtocoloPortabilidade" /></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>
            <% } %>
        </div>

        <vivo:abaGrupo id="abasDetalheProcesso" width="778" height="10px" styleClass="abatexto">
            <vivo:abaItem id="btx01" onclick="abaSelected($('abasDetalheProcesso'), btx01);CarregaAba('detalheProcessoCliente');" value="Cliente" select="S"/>
            <vivo:abaItem id="btx02" onclick="abaSelected($('abasDetalheProcesso'), btx02);CarregaAba('detalheProcessoHistorico');" value="Histórico do Processo"/>
            <vivo:abaItem id="btx03" onclick="abaSelected($('abasDetalheProcesso'), btx03);CarregaAba('detalheProcessoRetencao');" value="Retenção"/>
            <!--vivo:abaItem id="btx04" onclick="abaSelected($('abasDetalheProcesso'), btx04);CarregaAba('detalheProcessoGestorContas');" value="Gestor de Contas"/-->
        </vivo:abaGrupo>
        <div id="contentAbas"></div>

        <logic:notEqual name="Form" property="dsOrigem" value="TELAATENDIMENTO">
        <div id="divNavigationButtons">
            <div style="float:left">
                <a href="javascript:if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();$('formPesquisaProcessos').submit()">
                    <img id="btVoltarDetalheProcesso" style="margin:5px 0 0 0;border:none;" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" /></a>
            </div>
        </div>
        </logic:notEqual>

        </form>

    </vivo:sessao>

    <script type="text/javascript" language="javascript">
        var qtDiasValidadeBonus;
        var flNovaOfertaProcessosAgrupados = false;
        dsOrigem = '<bean:write name="Form" property="dsOrigem" />';
        <% if (Form.getListaLinhasAssociadas().getDetalheLinhaVOArray().length > 0) { %>
        $('spanWorkspaceControl').style.display = 'block';
        flProcessosAgrupados = true;
        <% } else { %>
        $('spanWorkspaceControl').style.display = 'none';
        <% } %>
    </script>

</netui-template:section>
</netui-template:template>