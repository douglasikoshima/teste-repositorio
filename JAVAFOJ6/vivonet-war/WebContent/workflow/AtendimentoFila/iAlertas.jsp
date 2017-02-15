<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm"/>
<link href="../../resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="../../resources/scripts/frameweb.js"></script>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<acesso:controlHiddenItem nomeIdentificador="wor_ialetas_verpagina">
<form id="formAlerta" method="post">
<vivo:tbTable layoutWidth="417" layoutHeight="180" tableWidth="417" styleId="tableTitle3" sortable="false" onRowClick="return false;">
 <vivo:tbHeader>
  <vivo:tbHeaderColumn align="left" width="100%" type="string">Descrição</vivo:tbHeaderColumn>
 </vivo:tbHeader>
 <vivo:tbRows>
  <logic:iterate id="alertaVO" name="form" property="alertasVO" indexId="idx">
   <bean:define name="alertaVO" property="nmIcone" id="nmIcone"/>
    <% 
    StringBuffer sbPathIcone = new StringBuffer();
    sbPathIcone.append("/FrontOfficeWeb/resources/images/");
    sbPathIcone.append(nmIcone.toString());
    //String pathIcone = "/FrontOfficeWeb/resources/images/" + nmIcone.toString();
    String idClassRow = ConstantesCRM.SVAZIO;
    %>
   <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
   <logic:notEmpty name="alertaVO" property="nmCor"><%idClassRow=nmCor.toString();%></logic:notEmpty>
   <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
    <vivo:tbRowColumn><bean:write name="alertaVO" property="dsAlerta"/></vivo:tbRowColumn>
   </vivo:tbRow>
  </logic:iterate>
 </vivo:tbRows>
</vivo:tbTable>
</form>
<script>if( top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();</script>
</acesso:controlHiddenItem>
</body>