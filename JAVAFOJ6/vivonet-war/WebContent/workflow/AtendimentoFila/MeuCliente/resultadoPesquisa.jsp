<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filaMeuClienteFiltrosForm"/>

<bean:define id="total" name="Form" property="resultadoPesquisa.nrRegistros"/>
<bean:define id="total2" name="Form" property="resultadoPesquisa.totalRegistros"/>

<%
int qtdVermelho = 0;
int qtdAmarelo = 0;
int qtdNormal = 0;
String textoContato = null;

int layoutWidth = 764;
int layoutHeight = 238;

if (ConstantesCRM.STRUE.equals(request.getSession().getAttribute("ACESSO_EXTERNO"))) {
    layoutWidth = 764;
    layoutHeight = 180;
}

%>

<vivo:tbTable selectable="true"
              layoutWidth="<%=String.valueOf(layoutWidth)%>"
              layoutHeight="<%=String.valueOf(layoutHeight)%>"
              tableWidth="<%=String.valueOf(layoutWidth)%>"
              styleId="tbResultadoPesquisa"
              sortable="true"
              onRowClick="getDetalheProcesso(this);">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Alerta</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="9%" type="string">Protocolo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Processo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="9%" type="string">Contato</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="13%" type="string">Número Linha</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="13%" type="string">Estado / Subestado</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Operador</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="9%" type="date">Abertura</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="11%" type="date">Fechamento</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="false">
        <logic:notEmpty name="Form" property="resultadoPesquisa.atendimentoFilaVOArray">
        <logic:iterate id="atdFilaVO"
                       name="Form"
                       property="resultadoPesquisa.atendimentoFilaVOArray"
                       indexId="idx"
                       type="br.com.vivo.fo.workflow.vo.AtendimentoFilaVODocument.AtendimentoFilaVO">
        <%String idClassRow = "";%>
        <logic:iterate id="alertaVO" name="atdFilaVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
            <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
            <logic:notEmpty name="alertaVO" property="nmCor"><%idClassRow = nmCor.toString();%></logic:notEmpty>
        </logic:iterate>
        <%
        qtdVermelho += idClassRow.equals("rowTabelaAlertaAlto") ? 1 : 0;
        qtdAmarelo  += idClassRow.equals("rowTabelaAlertaBaixo") ? 1 : 0;
        qtdNormal   += idClassRow.equals("") ? 1 : 0;
        textoContato = atdFilaVO.getAtendimentoVO().getArvoreAtendimentoVO().getDescricaoCompleta().replaceAll("/"," / ");%>
        <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
            <vivo:tbRowColumn>
                <input type="checkbox"
                       name="encaminhar_<%=atdFilaVO.getAtendimentoVO().getIdAtendimento()%>"
                       id="encaminhar_<%=atdFilaVO.getAtendimentoVO().getIdAtendimento()%>"
                       class="selecao_processos"
                       value="<%=idx%>"
                       style="border:none;background:none;padding:0;margin:0;left:0;top:0;width:15px;height:15px;"
                       onclick="controleDetalhe=false" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
            <logic:iterate id="alertaVO" name="atdFilaVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                <logic:notEmpty name="alertaVO" property="nmCor">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif"
                         style="border:none;background:transparent;cursor:pointer"
                         onclick="getAlerta('<bean:write name="atdFilaVO" property="atendimentoVO.idAtendimento" format="####"/>');return false" />
                </logic:notEmpty>
            </logic:iterate>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="atdFilaVO" property="atendimentoVO.nrProtocolo" format="####"/>
                <input type="hidden" class="classIdAtendimento" value="<%=atdFilaVO.getAtendimentoVO().getIdAtendimento()%>" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="atdFilaVO" property="atendimentoVO.idAtendimento" format="####"/>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <%=textoContato%>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.nrTelefone"/></vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="atdFilaVO" property="atendimentoVO.WFEstadoVO.dsEstado"/> /
                <bean:write name="atdFilaVO" property="atendimentoVO.WFSubEstadoVO.dsSubEstado"/>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <%=atdFilaVO.getAtendimentoVO().getUsuarioVIVO().getNmNome() %>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.dtAbertura"/></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdFilaVO" property="atendimentoVO.dtFechamento"/></vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
        </logic:notEmpty>
    </vivo:tbRows>
</vivo:tbTable>

<script type="text/javascript" language="javascript">
    $('lblVermelho').update('<%=qtdVermelho%>');
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
    if (window.top.frameApp.$('idAnime')) window.top.frameApp.$('idAnime').hide();
</script>

<vivo:businessLog requestAttribute="debugMessage" inResetWindow="false" inAjaxCall="true" />