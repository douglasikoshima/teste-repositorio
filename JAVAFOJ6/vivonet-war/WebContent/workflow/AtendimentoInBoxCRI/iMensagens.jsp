<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO"%>
<%@ page import="workflow.AtendimentoInBox.AtendimentoInBoxController.RWFAtendimentoForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%><%
String ctx=request.getContextPath();
String style = "tbResultado";
String idClassRow = null; 
%>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script type="text/javascript" src="../../resources/scripts/frameweb.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
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
<vivo:tbTable selectable="true" resize="false" layoutWidth="765" layoutHeight="266" tableWidth="765" styleId="<%=style%>" sortable="true" onRowClick="">
 <vivo:tbHeader>
  <vivo:tbHeaderColumn align="center" width="13%" type="date">Data</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="number">N. Processo</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="12%" type="string">N. Linha</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="25%" type="string">Contato</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="25%" type="date">Quem enviou</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="15%" type="date">Ação</vivo:tbHeaderColumn>
 </vivo:tbHeader>
 <vivo:tbRows scroll="false">        
 <% 
    int  qtdTotalTrat = 0; 
 %>
 <logic:notEqual name="form" property="nrRegistros" value="0">

 <logic:iterate id="atdInBoxVO" name="form" property="rwfAtendimentosVO.RWFAtendimentoVOArray" indexId="idx">
    <%
        String textoContato = ((RWFAtendimentoVO)atdInBoxVO).getDescricaoCompleta().replaceAll("/"," / ");
    %>
             
 <vivo:tbRow key="linha"> 
  <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="dtAbertura"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="nrProtocolo" format="###"/><html:hidden name="atdInBoxVO" property="idAtendimento"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="nrTelefone"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><vivo:hint maxLength="20" spaces="S"><%=textoContato%></vivo:hint></vivo:tbRowColumn>
  <vivo:tbRowColumn><vivo:hint maxLength="15" spaces="S"><bean:write name="atdInBoxVO" property="nmNome"/></vivo:hint></vivo:tbRowColumn>  
  <vivo:tbRowColumn><vivo:hint maxLength="15" spaces="S"><bean:write name="atdInBoxVO" property="operacaoWorkflow"/></vivo:hint></vivo:tbRowColumn>  
 </vivo:tbRow>
 </logic:iterate>
 </logic:notEqual>
 <%
    qtdTotalTrat = (form).getNrRegistros();
    int qtdTotalTrat2 = (form).getTotalRegistros();
    StringBuffer strBuffer = new StringBuffer();
    strBuffer.append("<script language=\"javascript\">");                  
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
    
    //Calcula o numero de milesegundos
    var SecondsRefresh   = 60 * <bean:write name="form" property="atualizacao" format="###"/>;
    var mlSecondsRefresh = SecondsRefresh * 1000;
    
    parent.startRefresh(parent.abaSelecionada,mlSecondsRefresh);
    
    parent.ajustaTela();
    
    DoResizeHeaderTableVivo();
    if(top.frameApp.dvAnimarAguarde!=null){
        top.frameApp.stopAnimation();
    }
</script>