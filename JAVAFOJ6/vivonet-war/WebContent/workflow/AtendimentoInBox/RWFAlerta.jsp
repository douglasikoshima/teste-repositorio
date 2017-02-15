<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<link href="../../resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="../../resources/scripts/frameweb.js"></script>
<bean:define id="form" name="rWFAlertaForm" scope="request"/>

<%--<acesso:controlInitEnv/>--%>
<%--<acesso:controlHiddenItem nomeIdentificador="wor_rialaibx_verpagina">--%>

<vivo:tbTable selectable="true" layoutWidth="417" layoutHeight="180" tableWidth="417" styleId="tableTitle3" sortable="false" onRowClick="return false;">
<vivo:tbHeader>
 <vivo:tbHeaderColumn align="left" width="30%" type="string">Descrição</vivo:tbHeaderColumn>
</vivo:tbHeader>
<vivo:tbRows>
 <logic:iterate id="alertaVO" name="form" property="alertasVO" indexId="idx">
 <bean:define name="alertaVO" property="nmIcone" id="nmIcone"/><% 
    StringBuffer sbPathIcone = new StringBuffer("/FrontOfficeWeb/resources/images/");
    sbPathIcone.append(nmIcone.toString());
    //String pathIcone = "/FrontOfficeWeb/resources/images/" + nmIcone.toString();
    String idClassRow = ConstantesCRM.SVAZIO;%>
 <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
 <logic:notEmpty name="alertaVO" property="nmCor"><%idClassRow = nmCor.toString();%></logic:notEmpty>
 <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
  <vivo:tbRowColumn><bean:write name="alertaVO" property="dsAlerta"/></vivo:tbRowColumn>
 </vivo:tbRow>
 </logic:iterate>
</vivo:tbRows>
</vivo:tbTable>

<%--</acesso:controlHiddenItem>--%>

<script language="javascript">if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();</script>