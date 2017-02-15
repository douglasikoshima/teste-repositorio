<%--
*** REFACTORING ***
 Date: 18/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm"/>
<bean:define id="formCamposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.formularioVO.formularioCampoVOArray"/>
<html:select name="form" property="formularioCampoSel" title="formularioCampoSel" style="width=200px" onchange="submitCampo();focaTipCampos(this.options[this.selectedIndex].text,this, 20, 50, 25);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 20, 50, 25);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 20, 50, 25);" onblur="HideTip();" onmouseout="HideTip();">
<html:option value="-1" key="formularioCampoSel">--Selecione o Campo--</html:option>
<html:options collection="formCamposVO" property="idCampo" labelProperty="nmCampo"/> </html:select>
<script>
parent.carregaComboCampos();
top.frameCTI.filtro.psqAv=null;
</script>