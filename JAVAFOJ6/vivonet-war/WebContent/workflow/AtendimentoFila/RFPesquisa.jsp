<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoFilaVODocument.AtendimentoFilaVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="workflow.AtendimentoFila.AtendimentoFilaController.AtendimentoFilaForm"%>
<%--
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_rfpesquisa_verpagina">
--%>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm"/>
<bean:define id="total" name="form" property="atendimentoInformacaoVO.nrRegistros"/>
<bean:define id="total2" name="form" property="atendimentoInformacaoVO.totalRegistros"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<html:hidden name="form" property="inResp"/>
<%int qtdVermelho = 0, qtdAmarelo = 0, qtdNormal = 0; String textoContato = null;%>
<vivo:tbTable selectable="true" layoutWidth="764" layoutHeight="183" tableWidth="764" styleId="tbResultadoPesquisa" sortable="true" onRowClick="linhaSel(this);">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="4%" type="string">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="4%" type="string">Alerta</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Protocolo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Processo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="20%" type="string">Contato</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">N. Linha</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="14%" type="string">Estado / Sub-Estado</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Operador</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="9%" type="date">Abertura</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="9%" type="date">Fechamento</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="false">
        <logic:notEmpty name="form" property="atendimentoInformacaoVO.atendimentoFilaVOArray">
        <logic:iterate id="atdFilaVO" name="form" property="atendimentoInformacaoVO.atendimentoFilaVOArray" indexId="idx">
        <%String idClassRow="";%>
        <logic:iterate id="alertaVO" name="atdFilaVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
            <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
            <logic:notEmpty name="alertaVO" property="nmCor"><%idClassRow = nmCor.toString();%></logic:notEmpty>
        </logic:iterate>
        <%
        qtdVermelho += idClassRow.equals("rowTabelaAlertaAlto") ? 1 : 0;
        qtdAmarelo  += idClassRow.equals("rowTabelaAlertaMedio") ? 1 : 0;
        qtdNormal   += idClassRow.equals(ConstantesCRM.SVAZIO) ? 1 : 0;
        textoContato = ((AtendimentoFilaVO)atdFilaVO).getAtendimentoVO().getArvoreAtendimentoVO().getDescricaoCompleta().replaceAll("/"," / ");%>
        <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
            <vivo:tbRowColumn>
                <html:hidden name="atdFilaVO" property="atendimentoVO.inCRI"/>
                <html:hidden name="atdFilaVO" property="atendimentoVO.dtSuspeito"/>
                <html:hidden name="atdFilaVO" property="atendimentoVO.idAtendimentoBaixaHistorico"/>
                <html:hidden name="atdFilaVO" property="atendimentoVO.nmURLDados"/>
                <html:checkbox name="atdFilaVO" property="operacaoWorkflow" indexed="true" value="true" onclick="controleDetalhe=false;" style="border=0; background-color=transparent;"></html:checkbox>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
            <logic:iterate id="alertaVO" name="atdFilaVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                <logic:notEmpty name="alertaVO" property="nmCor">
                    <a href="<%=request.getContextPath()%>/workflow/AtendimentoFila/obterAlerta.do" style="border=0; background-color=transparent;" onclick="submitAlerta('<bean:write name="atdFilaVO" property="atendimentoVO.idAtendimento" format="####"/>'); return false;">
                        <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif" style="border=0; background-color=transparent;"/>
                    </a>
                </logic:notEmpty>
            </logic:iterate>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="atdFilaVO" property="atendimentoVO.nrProtocolo" format="####"/>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="atdFilaVO" property="atendimentoVO.idAtendimento" format="####"/>
                <html:hidden styleClass="classIdAtendimento" name="atdFilaVO" property="atendimentoVO.idAtendimento"/>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn><vivo:hint maxLength="25" spaces="S"><%=textoContato%></vivo:hint></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.nrTelefone"/></vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="atdFilaVO" property="atendimentoVO.WFEstadoVO.dsEstado"/> /
                <bean:write name="atdFilaVO" property="atendimentoVO.WFSubEstadoVO.dsSubEstado"/>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn><vivo:hint maxLength="15" spaces="S"><bean:write name="atdFilaVO" property="atendimentoVO.usuarioVIVO.nmNome"/></vivo:hint></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.dtAbertura"/></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.dtFechamento"/></vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
        </logic:notEmpty>
    </vivo:tbRows>
</vivo:tbTable>
<%
String textoTotal = ConstantesCRM.SVAZIO;
String textoTotal2 = String.valueOf(total2);
for (int i=0; i< 5 - textoTotal2.length(); i++)
    textoTotal += ConstantesCRM.SZERO;
textoTotal += textoTotal2;

StringBuffer sbScriptHtml = new StringBuffer("<script language=\"javascript\">");
sbScriptHtml.append("parent.lblVermelho.innerHTML=").append(qtdVermelho).append(";");
sbScriptHtml.append("parent.lblAmarelo.innerHTML=").append(qtdAmarelo).append(";");
sbScriptHtml.append("parent.lblNormal.innerHTML=").append(qtdNormal).append(";");
sbScriptHtml.append("parent.total.innerHTML=").append(total).append(";");
//RICARDOBREDES era textoTotal
sbScriptHtml.append("parent.total2.innerHTML='").append("").append("';");
sbScriptHtml.append("</script>");%>
<%=sbScriptHtml.toString()%>
<%-- TODO: Poner control de acceso
</acesso:controlHiddenItem>--%>
<script>
    //DoResizeHeaderTableVivo();
    parent.carregaFilas();
    //Controle da tread de refresh automatico
    var treadRefresh;

    function stopRefresh(){
        //Limpa a tread se necessario
        window.clearInterval(treadRefresh);
    }

    //Controle da tread do refresh automático parametrizável
    function startRefresh(){
        //Limpa a tread se necessario
        window.clearInterval(treadRefresh);

        //Calcula o numero de milesegundos
        var SecondsRefresh   = 60 * <bean:write name="form" property="atualizacao" format="###"/>;
        var mlSecondsRefresh = SecondsRefresh * 1000;

        //Inicializa o refresh automático
        treadRefresh = window.setInterval("pesquisar()", mlSecondsRefresh );
    }

    function pesquisar(){
        switch(parent.tipoPesquisa){
            case 1:
                parent.submitPesquisaBasica();
                break;
            case 2:
                parent.submitPesquisaAvancada();
                break;
        }
    }

    startRefresh();

    if (parent.inRC!="1"){
        if (parent.tbConfiguracoes.style.display=="none"){
            parent.reziseObject(true);
        } else {
            parent.reziseObject(false);
        }
    }

    <logic:present parameter="erro" scope="request">
    alert('<%=request.getParameter("erro")%>');
    stopRefresh();
    </logic:present>
</script>
<script for=document event=onbeforeunload>
    stopRefresh();
</script>