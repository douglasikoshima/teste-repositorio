<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO"%>
<%@ page import="workflow.AtendimentoInBox.AtendimentoInBoxController.RWFAtendimentoForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
String ctx=request.getContextPath();
String styleEnTrat = "tbProcessosTratamento";
String styleEnPau = "tbProcessosPausa";
String styleEnPesq = "tbProcessosPesquisa";
String style = null;
String tabPausa = (String)request.getParameter("tabPausa");
if(tabPausa==null){
    tabPausa=ConstantesCRM.SZERO;
}
if(tabPausa.equals(ConstantesCRM.SZERO)){
    style = styleEnTrat;
}else if(tabPausa.equals(ConstantesCRM.SONE)) {
    style = styleEnPau;
}else if(tabPausa.equals(ConstantesCRM.STWO)) {
    style = styleEnPesq;
}
String idClassRow = null;
%>
<script type="text/javascript" src="../../resources/scripts/frameweb.js"></script>
<script>function getH (a){return eval(a+'h');}function getS (a){return eval(a+'s');} var solcan="";</script>
<%--
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_riatd_verpagina">
--%>
<bean:define id="form" name="rwfAtendimentoForm" />

<table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
    <tr>
        <td>Atualização:</td>
        <td width="5%"><bean:write name="form" property="atualizacao" format="###"/> min</td>
        <td width="10%" align="right">Vermelho: </td>
        <td width="5%"><div id="lblVermelhoTrat">0</div></td>
        <td width="10%" align="right">Amarelo: </td>
        <td width="5%"><div id="lblAmareloTrat">0</div></td>
        <td width="10%" align="right">Normal: </td>
        <td width="5%"><div id="lblNormalTrat">0</div></td>
        <td width="20%" align="right">Registros Retornados: </td>
        <td width="5%"><div id="lblTotalTrat">0</div></td>
        <td width="20%" align="right">Registros Encontrados: </td>
        <td width="5%"><div id="lblTotalTrat2">0</div></td>
    </tr>
</table>
<div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
<vivo:tbTable selectable="true" layoutWidth="765" layoutHeight="155" tableWidth="765" styleId="<%=style%>" sortable="true" onRowClick="linhaSel(this);">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn width="5%"/>
        <vivo:tbHeaderColumn align="center" width="6%" type="string">Alerta</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="number">Protocolo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="number">Processo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Contato</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="11%" type="string">N. Linha</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="18%" type="string">Estado / Sub-Estado</vivo:tbHeaderColumn>
        <%if ( (tabPausa != null) && (!tabPausa.equals(ConstantesCRM.SONE)) ){%>
            <vivo:tbHeaderColumn align="center" width="9%" type="date">Abertura</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="9%" type="date">Fechamento</vivo:tbHeaderColumn>
        <%} else {%>
            <vivo:tbHeaderColumn align="center" width="9%" type="date">Fim Pausa</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="9%" type="date">Abertura</vivo:tbHeaderColumn>
        <%}%>
        <vivo:tbHeaderColumn align="center" width="12%" type="date">VOL-E</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="false">
        <%int qtdVermelhoTrat = 0, qtdAmareloTrat = 0, qtdNormalTrat = 0, qtdTotalTrat = 0;%>
        <logic:notEqual name="form" property="nrRegistros" value="0">
            <logic:iterate id="atdInBoxVO" name="form" property="rwfAtendimentoVO" indexId="idx">
                <bean:define name="atdInBoxVO" property="nmCor" id="nmCor"/>
                <%
                idClassRow = nmCor.toString();
                qtdVermelhoTrat += idClassRow.equals("rowTabelaAlertaAlto") ? 1 : 0;
                qtdAmareloTrat += idClassRow.equals("rowTabelaAlertaMedio") ? 1 : 0;
                qtdNormalTrat += idClassRow.equals(ConstantesCRM.SVAZIO) ? 1 : 0;
                String textoContato = ((RWFAtendimentoVO)atdInBoxVO).getDescricaoCompleta().replaceAll("/"," / ");
                %>
                <logic:notEmpty name="atdInBoxVO" property="dtSolicitacaoCancelamento">
                    <script>
                        solcan += "<bean:write name="atdInBoxVO" property="nrProtocolo" format="###"/> - <bean:write name="atdInBoxVO" property="dtSolicitacaoCancelamento"/>\n";
                    </script>
                </logic:notEmpty>
                <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
                    <vivo:tbRowColumn>
                        <script>
                            var id<bean:write name="atdInBoxVO" property="idAtendimento" format="###"/>h='<bean:write name="atdInBoxVO" property="idAtendimentoBaixaHistorico"/>';
                            var id<bean:write name="atdInBoxVO" property="idAtendimento" format="###"/>s='<bean:write name="atdInBoxVO" property="dtSuspeito"/>';
                        </script>
                        <input type="checkbox" name="id<bean:write name="atdInBoxVO" property="idAtendimento" format="###"/>" value="true" onclick="controleDetalhe = false;" style="border=0; background-color=transparent;">
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <%if((nmCor!=null) && !(nmCor.equals(ConstantesCRM.SVAZIO))){%>
                            <a href="/FrontOfficeWeb/workflow/AtendimentoInBox/RWFAlerta.do" style="border=0; background-color=transparent;" onclick="submitAlerta('<bean:write name="atdInBoxVO" property="idAtendimento" format="###"/>'); return false;" title="Ver alertas">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" style="border=0; background-color=transparent;"/>
                            </a>
                        <%}%>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="atdInBoxVO" property="nrProtocolo" format="###"/>
                        <span class="classIdAtendimento"><bean:write name="atdInBoxVO" property="idAtendimento" format="###"/></span>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="idAtendimento" format="###"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><vivo:hint maxLength="30" spaces="S"><%=textoContato%></vivo:hint></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="nrTelefone"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dsEstado"/> / <bean:write name="atdInBoxVO" property="dsSubEstado"/></vivo:tbRowColumn>
                    <%if((tabPausa != null) && (!tabPausa.equals(ConstantesCRM.SONE))){%>
                        <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtAbertura"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtFechamento"/></vivo:tbRowColumn>
                    <%}else{%>
                        <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtFimPausaAtendimento"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtAbertura"/></vivo:tbRowColumn>
                    <%}%>
                    <vivo:tbRowColumn>
                        <%= ConstantesCRM.SONE.equals(((RWFAtendimentoVO)atdInBoxVO).getIsVOLE()) ? ConstantesCRM.SYES : ConstantesCRM.SVAZIO %>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </logic:notEqual>
        <%
        qtdTotalTrat = ((RWFAtendimentoForm)form).getNrRegistros();
        int qtdTotalTrat2 = ((RWFAtendimentoForm)form).getTotalRegistros();
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("<script language=\"javascript\">");
        strBuffer.append("lblVermelhoTrat.innerHTML=" + qtdVermelhoTrat + ";");
        strBuffer.append("lblAmareloTrat.innerHTML=" + qtdAmareloTrat + ";");
        strBuffer.append("lblNormalTrat.innerHTML=" + qtdNormalTrat + ";");
        strBuffer.append("lblTotalTrat.innerHTML=" + qtdTotalTrat + ";");
        strBuffer.append("lblTotalTrat2.innerHTML=" + qtdTotalTrat2 + ";");
        strBuffer.append("</script>");
        String scriptHtml = strBuffer.toString();%>
        <%=scriptHtml%>
    </vivo:tbRows>
</vivo:tbTable>
<%--</acesso:controlHiddenItem>--%>
<script>
    <%if (tabPausa.equals(ConstantesCRM.SONE)) {%>
        parent.showIfr("EPau");
    <%}else if (tabPausa.equals(ConstantesCRM.SZERO)){%>
        parent.showIfr("ETrat");
    <%}else if (tabPausa.equals(ConstantesCRM.STWO)){%>
        parent.showIfr("Pesquisa");
    <%}%>
    parent.up=!parent.up;
    parent.resizeMaxMin();

    function pesquisar() {
        parent.submitPesquisar(<%=tabPausa%>);
    }

    if(solcan != "") {
        alert("Foi solicitado o cancelamento\npara os seguintes processos :\n"+solcan);
    }

    //Calcula o numero de milesegundos
    var SecondsRefresh   = 60 * <bean:write name="form" property="atualizacao" format="###"/>;
    var mlSecondsRefresh = SecondsRefresh * 1000;

    parent.startRefresh('<%=tabPausa%>',mlSecondsRefresh);

    if( top.frameApp.dvAnimarAguarde != null )
         top.frameApp.stopAnimation();

    <logic:equal name="form" property="flLimpar" value="1">
    parent.limparPesquisa();
    </logic:equal>
</script>