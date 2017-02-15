<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filaPortabilidadeFiltrosForm"/>

<bean:define id="total" name="Form" property="resultadoPesquisa.nrRegistros"/>
<bean:define id="total2" name="Form" property="resultadoPesquisa.totalRegistros"/>

<%
int qtdVermelho = 0;
int qtdLaranja = 0;
int qtdAmarelo = 0;
int qtdNormal = 0;
String textoContato = null;
%>

<vivo:tbTable selectable="true" layoutWidth="764" layoutHeight="238" tableWidth="764" styleId="tbResultadoPesquisa" sortable="true" onRowClick="getDetalheProcesso(this);">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="5%" type="string">Alerta</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Protocolo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="13%" type="string">Número Linha</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="25%" type="string">Estado / Subestado</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="15%" type="string">Operador</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="11%" type="string">Data Janela Migração</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="date">Abertura</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="11%" type="date">Fechamento</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="false">
        <logic:notEmpty name="Form" property="resultadoPesquisa.atendimentoFilaVOArray">
        <logic:iterate id="atdFilaVO" name="Form" property="resultadoPesquisa.atendimentoFilaVOArray" indexId="idx" type="br.com.vivo.fo.workflow.vo.AtendimentoFilaVODocument.AtendimentoFilaVO">
        <%String idClassRow = ConstantesCRM.SVAZIO;%>
        <logic:iterate id="alertaVO" name="atdFilaVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
            <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
            <logic:notEmpty name="alertaVO" property="nmCor"><%idClassRow = nmCor.toString();%></logic:notEmpty>
        </logic:iterate>
        <%
        qtdVermelho += idClassRow.equals("rowTabelaAlertaAlto") ? 1 : 0;
        qtdLaranja += idClassRow.equals("rowTabelaAlertaMedio") ? 1 : 0;
        qtdAmarelo  += idClassRow.equals("rowTabelaAlertaBaixo") ? 1 : 0;
        qtdNormal   += idClassRow.equals(ConstantesCRM.SVAZIO) ? 1 : 0;
        textoContato = atdFilaVO.getAtendimentoVO().getArvoreAtendimentoVO().getDescricaoCompleta().replaceAll("/"," / ");%>
        <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
            <vivo:tbRowColumn>
            <logic:iterate id="alertaVO" name="atdFilaVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                <logic:notEmpty name="alertaVO" property="nmCor">
                    <a href="javascript:getAlerta('<bean:write name="atdFilaVO" property="atendimentoVO.idAtendimento" format="####"/>')">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif" style="border:none;background:transparent;"/>
                    </a>
                </logic:notEmpty>
            </logic:iterate>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="atdFilaVO" property="atendimentoVO.nrProtocolo" format="####"/>
                <input type="hidden" class="classIdAtendimento" value="<%=idx%>" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.nrTelefone"/></vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="atdFilaVO" property="atendimentoVO.WFEstadoVO.dsEstado"/> / 
                <bean:write name="atdFilaVO" property="atendimentoVO.WFSubEstadoVO.dsSubEstado"/>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <%=atdFilaVO.getAtendimentoVO().getUsuarioVIVO().getNmNome() %>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.dtJanelaPortout" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.dtAbertura"/></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.dtFechamento"/></vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
        </logic:notEmpty>
    </vivo:tbRows>
</vivo:tbTable>

<script type="text/javascript" language="javascript">
    $('lblVermelho').update('<%=qtdVermelho%>');
    $('lblLaranja').update('<%=qtdLaranja%>');
    $('lblAmarelo').update('<%=qtdAmarelo%>');
    $('lblNormal').update('<%=qtdNormal%>');
    $('total').update('<%=total%>');
    if ($('total').innerHTML == '100') {
        $('btGetQuantidadeTotal').show();
        $('qtdeTotalRegistros').update('');
    } else {
        $('btGetQuantidadeTotal').hide();
        $('qtdeTotalRegistros').update($('total').innerHTML);
    }
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
</script>

<vivo:businessLog requestAttribute="debugMessage" inResetWindow="false" inAjaxCall="true" />