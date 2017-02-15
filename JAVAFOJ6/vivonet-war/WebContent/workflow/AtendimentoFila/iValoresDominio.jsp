<%--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Fila - pesquisa avancada
Versão.....: $Author: a5112272 $

*** REFACTORING ***
 Date: 29/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm"/>
<bean:define id="camposValorVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.camposValorVO"/>
<html>
<body>
<form name="tmpForm">
<html:select name="form" property="valorSel">
<html:option value="-1" key="valorSel">-- Selecione --</html:option>            
<html:options collection="camposValorVO" property="idFormularioCampoValor" labelProperty="valor"/> 
</html:select>
</form>
<script>
parent.divValorDominio.style.display="";
var oOption = document.forms["tmpForm"].elements["valorSel"];
var oOptionParent = parent.document.forms["formFila"].elements["valorSel"];
while(oOptionParent.options.length > 0) { oOptionParent.options.remove(0);}
for(i=0;i<oOption.options.length;i++) {
 var oOptionNew=parent.document.createElement("OPTION");               
 oOptionParent.options.add(oOptionNew);
 oOptionNew.innerText=oOption.options(i).text;
 oOptionNew.value=oOption.options(i).value;            
}
if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
</script>                                      
</body>
</html>
