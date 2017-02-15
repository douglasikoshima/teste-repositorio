<%--
*** REFACTORING ***
 Date: 25/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%-- TODO: Poner control de acceso
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_rdoper_verpagina">
--%>
<bean:define id="rdacao" scope="request" name="rWFAcaoForm"/>
<bean:define id="WFAcaoVO" name="rdacao" property="WFAcaoVO"/>
<html:select name="rdacao" property="WFAcaoVO" onchange="changeOpcao(this)" style="width=250">
<html:option value="0">Selecionar Operação</html:option>
<html:options collection="WFAcaoVO" property="idAtividade" labelProperty="dsAtividade"/>
</html:select>
<%-- TODO: Poner control de acceso
</acesso:controlHiddenItem>
--%>
<script>
function abaDinamica() {
    if(parent.abaValue==0){
        parent.bt05.innerText='Selecione Operaçao';
    }
    <logic:iterate id="acoeVO" name="rdacao" property="WFAcaoVO" indexId="idxAcoe">
    if(parent.abaValue==<bean:write name="acoeVO" property="idAtividade"/>){
        parent.bt05.innerText='<bean:write name="acoeVO" property="dsAtividade"/>';
        parent.<bean:write name="acoeVO" property="jsAtividade"/>;
    }
    </logic:iterate>
}
parent.showIfr("Oper");
</script>
