<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
   

<bean:define id="Form"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />
<bean:define id="aLinhaVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.contaSelecionadaVO.linhaVOArray" />

<html>
    <head>
        <title></title>
    </head>
    <body>
    
        <form name="registrarContatoFormIframe">
            <html:select name="Form" property="linhaSelecionada">
                <html:option value="">-- Selecione --</html:option>
                <c:catch var="errOptionsaLinhaVO">
               	<html:options collection="aLinhaVO" property="idPessoaLinhaHistorico" labelProperty="dsAuxiliar" />
                </c:catch>
            </html:select><br/><br/>
        </form>
        <script>
            var aOptComponent = document.forms["registrarContatoFormIframe"].elements["linhaSelecionada"];                                                                   
            var aOptComponentsParent = parent.document.forms["registrarContatoForm2"].elements["linhaSelecionada"];
            
            while(aOptComponentsParent.options.length != 0)                                                         
                aOptComponentsParent.options.remove(0);
            
            for( x = 0; x < aOptComponent.options.length; x++){
                oOption = parent.document.createElement("OPTION");               
                aOptComponentsParent.options.add(oOption);
                oOption.innerText = aOptComponent.options(x).text;
                oOption.value     = aOptComponent.options(x).value;                                                                       
            }
            <logic:equal name="Form" property="inResponsavelAbertura" value="2">
                parent.pintaLinhaSelecionada();
            </logic:equal>  
            
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
       
    </body>
</html>