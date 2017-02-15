<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento InBox
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:41 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/weblogic-tags.tld" prefix="wl"%>
   
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxCRIForm" />

<script for="window" event="onload">
   parent.document.getElementById('divListaGrupos').innerHTML = document.getElementById('divListaGruposNova').innerHTML;
</script>

<bean:define id="gruposVO" name="form" property="gruposVO"/>

<div id="divListaGruposNova">
<html:select name="form" property="grupoSel" style="width:250px">
    <html:option value="" key="grupoSel">&nbsp;</html:option>
    <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
</html:select>
</div>
