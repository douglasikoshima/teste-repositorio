<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO"%>
<%@ page import="workflow.AtendimentoInBox.AtendimentoInBoxController.RWFAtendimentoForm"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<%
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
<td width="50">Atualização: </td>
<td width="50" align="left"><bean:write name="form" property="atualizacao" format="###"/> min</td>
<td width="120">Registros Retornados: </td>
<td width="50"><div id="lblTotalTrat">0</div></td>
<td width="120">Registros Encontrados: </td>
<td align="left"><div id="lblTotalTrat2">0</div></td>
</tr>
</table>
<div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
<vivo:tbTable selectable="true" resize="false" layoutWidth="765" layoutHeight="183" tableWidth="765" styleId="<%=style%>" sortable="true">
 <vivo:tbHeader>
  <vivo:tbHeaderColumn align="center" width="12%" type="number">N. Processo</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="12%" type="string">N. Linha</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="17%" type="date">Data</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="12%" type="string">RE Consultor</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="15%" type="string">Nome Consultor</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="16%" type="string">Tipo Nota</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="8%" type="string">Editar</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="8%" type="string">Param.</vivo:tbHeaderColumn>
 </vivo:tbHeader>
 <vivo:tbRows scroll="false">        
 <% 
    int qtdNormalTrat = 0, qtdTotalTrat = 0; 
 %>
 <logic:notEqual name="form" property="nrRegistros" value="0">
 <logic:iterate id="atdUraVO" name="form" property="notasVO.WFAtdNotaVOArray" indexId="idx"> 
 <%
    String idAtdNota = ""+((WFAtdNotaVO)atdUraVO).getIdAtendimentoNota();
 %>            
 <vivo:tbRow key="linha"  onRowClick=''> 
  <vivo:tbRowColumn><bean:write name="atdUraVO" property="nrProtocolo" format="###"/><html:hidden name="atdUraVO" property="idAtendimento"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdUraVO" property="nrLinha"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdUraVO" property="dtAberturaIni"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdUraVO" property="reConsultor"/></vivo:tbRowColumn>
  <vivo:tbRowColumn><vivo:hint maxLength="35"><bean:write name="atdUraVO" property="nmUsuario"/></vivo:hint></vivo:tbRowColumn>
  <vivo:tbRowColumn><bean:write name="atdUraVO" property="tipoNotaAtendimento"/></vivo:tbRowColumn>  
  <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="icri_intura_editar"><a href="Javascript:parent.alteraNota('<%=idAtdNota%>');"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Nota"></a></acesso:controlHiddenItem></vivo:tbRowColumn>
  <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="icri_intura_editpar"><a href="Javascript:parent.detNota('<%=idAtdNota%>');"><img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" alt="Editar Parametros" border="0"></a></acesso:controlHiddenItem></vivo:tbRowColumn> 
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
    
    
    DoResizeHeaderTableVivo();
    if(top.frameApp.dvAnimarAguarde!=null){
        top.frameApp.stopAnimation();
    }
    
    parent.ajustaTela();
    
</script>