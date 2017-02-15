<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO"%>
<%@ page import="workflow.AtendimentoInBox.AtendimentoInBoxController.RWFAtendimentoForm"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
String ctx=request.getContextPath();
String style = "tbResultado";
String idClassRow = null; 
int inAbaPesquisa = Integer.parseInt(request.getParameter("inAbaPesquisa"));
%>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script type="text/javascript" src="../../resources/scripts/frameweb.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script>
    var solcan="";
</script>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"  property="atendimentoInBoxCRIForm" type="workflow.AtendimentoInBoxCRI.AtendimentoInBoxCRIController.AtendimentoInBoxCRIForm"/>
<form name="form" id="form">
<input type="hidden" name="doumento" id="documento"/>
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
<vivo:tbTable selectable="true" resize="false" layoutWidth="765" layoutHeight="266" tableWidth="2500" styleId="<%=style%>" sortable="true" onRowClick="parent.linhaSel(this);">
 <vivo:tbHeader>
  <vivo:tbHeaderColumn align="center" width="1%" type="string">Alerta</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="3%" type="number">N. Processo</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="5%" type="string">N. Linha</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="string">Cliente</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="11%" type="string">Contato</vivo:tbHeaderColumn>
  <% if (inAbaPesquisa==3) { %>
    <vivo:tbHeaderColumn align="center" width="4%" type="string">Analista (BKO)</vivo:tbHeaderColumn>
    <vivo:tbHeaderColumn align="center" width="7%" type="string">Grupo (BKO)</vivo:tbHeaderColumn>
  <% } %>
  <%--<vivo:tbHeaderColumn align="center" width="4%" type="string">Analista (CRI)</vivo:tbHeaderColumn>--%>
  <% if (inAbaPesquisa==6) { %>
    <vivo:tbHeaderColumn align="center" width="7%" type="string">Grupo (CRI)</vivo:tbHeaderColumn>
  <% } %>
  <vivo:tbHeaderColumn align="center" width="8%" type="string">Regional</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="5%" type="date">Criacao</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="5%" type="date">Fechamento VIVO</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="5%" type="date">Fechamento ANATEL</vivo:tbHeaderColumn>
  <%if (inAbaPesquisa<3) {%>
    <vivo:tbHeaderColumn align="center" width="5%" type="date">Devolução</vivo:tbHeaderColumn>
  <%}%>
  <% if (inAbaPesquisa==3) { %>
    <vivo:tbHeaderColumn align="center" width="5%" type="date">Encaminhamento</vivo:tbHeaderColumn>
  <% } %>
  <vivo:tbHeaderColumn align="center" width="10%" type="string">Estado / Sub-Estado</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="5%" type="string">CPF / CNPJ</vivo:tbHeaderColumn>
 </vivo:tbHeader>
 <vivo:tbRows scroll="false">        
        <%int qtdVermelhoTrat = 0, qtdAmareloTrat = 0, qtdNormalTrat = 0, qtdTotalTrat = 0;%>
 <logic:notEqual name="form" property="nrRegistros" value="0">
 <logic:iterate id="atdInBoxVO" name="form" property="rwfAtendimentosVO.RWFAtendimentoVOArray" indexId="idx">
    <bean:define name="atdInBoxVO" property="nmCor" id="nmCor"/>
    <%
        idClassRow = nmCor.toString();
        qtdVermelhoTrat += idClassRow.equals("rowTabelaAlertaAlto") ? 1 : 0;
        qtdAmareloTrat += idClassRow.equals("rowTabelaAlertaMedio") ? 1 : 0;
        if(!idClassRow.equals("rowTabelaAlertaAlto")&&!idClassRow.equals("rowTabelaAlertaMedio")){
            qtdNormalTrat += 1;
        }
        String textoContato = ((RWFAtendimentoVO)atdInBoxVO).getDescricaoCompleta().replaceAll("/"," / ");
    %>
 <logic:notEmpty name="atdInBoxVO" property="dtSolicitacaoCancelamento">
    <script>
                    solcan += "<bean:write name="atdInBoxVO" property="nrProtocolo" format="###"/> - <bean:write name="atdInBoxVO" property="dtSolicitacaoCancelamento"/>\n";
    </script>
 </logic:notEmpty>   
 <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
  <vivo:tbRowColumn>
   <%if ( (nmCor!=null) && !(nmCor.equals(""))){%>
    <a style="border=0; background-color=transparent;" onclick="parent.submitAlerta('<bean:write name="atdInBoxVO" property="idAtendimento" format="###"/>'); return false;" title="Ver alertas">
        <img src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" style="border=0; background-color=transparent;"/>
    </a>
   <%}%>                            
  </vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="nrProtocolo" format="###"/><html:hidden name="atdInBoxVO" property="idAtendimento"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="nrTelefone"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><vivo:hint maxLength="20"><bean:write name="atdInBoxVO" property="nmCliente"/></vivo:hint></vivo:tbRowColumn>
  <vivo:tbRowColumn><vivo:hint maxLength="30" spaces="S"><%=textoContato%></vivo:hint></vivo:tbRowColumn>
  <% if (inAbaPesquisa==3) { %>
    <vivo:tbRowColumn><vivo:hint maxLength="20"><bean:write name="atdInBoxVO" property="analistaBKO"/></vivo:hint></vivo:tbRowColumn>
    <vivo:tbRowColumn><vivo:hint maxLength="30"><bean:write name="atdInBoxVO" property="grupoBKO"/></vivo:hint></vivo:tbRowColumn>
  <% } %>
  <%--<vivo:tbRowColumn><vivo:hint maxLength="20"><bean:write name="atdInBoxVO" property="analistaCRI"/></vivo:hint></vivo:tbRowColumn>--%>
  <% if (inAbaPesquisa==6) { %>
    <vivo:tbRowColumn><vivo:hint maxLength="30"><bean:write name="atdInBoxVO" property="grupoCRI"/></vivo:hint></vivo:tbRowColumn>
  <% } %>
  <vivo:tbRowColumn><vivo:hint maxLength="30"><bean:write name="atdInBoxVO" property="regional"/></vivo:hint></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtAbertura"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtFechamento"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtFechamentoAnatel"/></vivo:tbRowColumn>
  <%if (inAbaPesquisa<3) {%>
    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtDevolucao"/></vivo:tbRowColumn>
  <%}%>
  <% if (inAbaPesquisa==3) { %>
    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtEncaminhamento"/></vivo:tbRowColumn>
  <% } %>
  <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dsEstado"/> / <bean:write name="atdInBoxVO" property="dsSubEstado"/></vivo:tbRowColumn>
    <script>
        document.getElementById('documento').value = '<bean:write name="atdInBoxVO" property="documento"/>';
        tipoDocumento = '<bean:write name="atdInBoxVO" property="tipoDocumento"/>';
        if(tipoDocumento=='1'){
            documento = checaCPF(document.getElementById('documento'));
                        }else
            if(tipoDocumento=='2'){
                documento = checaCNPJ(document.getElementById('documento'));
            }
    </script>
  <vivo:tbRowColumn><script>document.write(document.getElementById('documento').value)</script></vivo:tbRowColumn>
 </vivo:tbRow>
 </logic:iterate>
 </logic:notEqual>
 <%
    qtdTotalTrat = (form).getNrRegistros();
    int qtdTotalTrat2 = (form).getTotalRegistros();
    StringBuffer strBuffer = new StringBuffer();
    strBuffer.append("<script language=\"javascript\">");
    strBuffer.append("lblVermelhoTrat.innerHTML=" + qtdVermelhoTrat + ";");
    strBuffer.append("lblAmareloTrat.innerHTML=" + qtdAmareloTrat + ";");     
    strBuffer.append("lblNormalTrat.innerHTML=" + qtdNormalTrat + ";");                        
    strBuffer.append("lblTotalTrat.innerHTML=" + qtdTotalTrat + ";");                        
    strBuffer.append("lblTotalTrat2.innerHTML=" + qtdTotalTrat2 + ";");                        
    strBuffer.append("</script>");                                        
    String scriptHtml = strBuffer.toString();
%>
<%=scriptHtml%>
</vivo:tbRows>
</vivo:tbTable>        
</form>
<script>
    if (solcan != "") {
        alert("Foi solicitado o cancelamento\npara os seguintes processos :\n"+solcan);
    }
    //Calcula o numero de milesegundos
    var SecondsRefresh   = 60 * <bean:write name="form" property="atualizacao" format="###"/>;
    var mlSecondsRefresh = SecondsRefresh * 1000;
    
    parent.startRefresh(parent.abaSelecionada,mlSecondsRefresh);
    document.all["tbResultado_body"].parentElement.style.height = ((parent.dvMin)+"px");    
    document.getElementById('tbResultado_div').style.overflowX = 'auto'; 
    parent.ajustaTela();
    DoResizeHeaderTableVivo();
    if(top.frameApp.dvAnimarAguarde!=null){
        top.frameApp.stopAnimation();
    }
    //Reset Pesquisa field
    parent.document.forms[0].inPesquisa.value = 0;
</script>