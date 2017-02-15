<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioAcessosForm"/>

<html>
    <body>
        <html:form styleId="formUsuario" action="/VOLTAV/relatorios/obterTerminais.do" method="post">
            <html:select property="terminalSelecionado" style="width:200px">
                <html:option value="-1" key="terminalSelecionado">Todos</html:option>
                <html:optionsCollection name="form" property="terminaisVO" value="nrTerminal" label="nrIpTerminal"/>
            </html:select>
        </html:form>
        <script>
            var oOption = document.forms["formUsuario"].elements["terminalSelecionado"];
            var oOptionParent = parent.document["formFiltro"]["terminalSelecionado"];
            while(oOptionParent.options.length != 0) {
                oOptionParent.options.remove(0);
            }
            for(i = 0; i < oOption.options.length; i++ ) {
                var oOptionNew = parent.document.createElement("OPTION");               
                oOptionParent.options.add(oOptionNew);
                oOptionNew.innerText = oOption.options(i).text;
                oOptionNew.value = oOption.options(i).value;            
            }
            if( top.dvAnimarAguarde != null ) top.stopAnimation();
        </script>   
    </body>
</html>