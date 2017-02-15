<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="consultorRelacionamentoForm"
             type="cliente.ConsultorRelacionamento.ConsultorRelacionamentoController.ConsultorRelacionamentoForm" />

<vivo:alert atributo="msgRetorno" scope="request" /> 

<script>
<logic:equal name="Form" property="mostrarMensagemErro" value="0">
    parent.recarregarTela();    
</logic:equal>
<logic:equal name="Form" property="mostrarMensagemErro" value="">
    parent.recarregarTela();    
</logic:equal>
<logic:equal name="Form" property="mostrarMensagemErro" value="1">
    parent.pararTela(); 
</logic:equal>
</script>
    
    

