<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm" type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm"/>

<html:select name="Form" property="idSubForm" style="width:215px;" styleClass="SELECT">
<option value="">-- Selecione --</option>
<html:optionsCollection name="Form" property="lstSubFrm.itArray" value="id" label="ds"/>
</html:select>