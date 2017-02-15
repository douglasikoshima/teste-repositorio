<%--
M�dulo.....: Gest�o de Processos
Caso de Uso: Atendimento Fila
Vers�o.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/17 17:18:00 $

*** REFACTORING ***
 Date: 29/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm"/>
<bean:define id="subEstadosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.atendimentoInformacaoVO.WFSubEstadoVOArray"/>
<html>
<body>
<acesso:controlHiddenItem nomeIdentificador="wor_subes_verpagina">
<form name="formSubEstado">
<html:select name="form" property="subEstadoSel" title="subEstadoSel" style="width=120px">
<html:option value="-1" key="subEstadoSel">&nbsp;</html:option>
<html:options collection="subEstadosVO" property="idSubEstado" labelProperty="dsSubEstado" /> 
</html:select>
</form>
<script>
var oOption=document.forms["formSubEstado"].elements["subEstadoSel"];
var oOptionParent=parent.document.forms["formFila"].elements["subEstadoSel"];
while(oOptionParent.options.length!=0) {oOptionParent.options.remove(0);}
for(i=0;i<oOption.options.length;i++ ) {
 var oOptionNew=parent.document.createElement("OPTION");               
 oOptionParent.options.add(oOptionNew);
 oOptionNew.innerText=oOption.options(i).text;
 oOptionNew.value=oOption.options(i).value;            
}
if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
</script>  
</acesso:controlHiddenItem>                                                   
</body>
</html>
