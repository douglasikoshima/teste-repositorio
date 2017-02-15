<!--
Módulo.....: Workflow
Caso de Uso: Registrar Contato (Atendimento)
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="form" scope="request" name="registrarContatoForm"/>
<bean:define id="FormularioValor" name="form" property="formularioCampoVO.formularioCampoValorVOArray"/>
<html>
    <head>
        <title>Vivo Net</title>
    </head>
    <body>
        <form name="pesquisaFormIframe">
            <html:select name="form" property="selecaoFormulario">
                <html:option value="">-- Selecione --</html:option>
                <html:options collection="FormularioValor" property="idFormularioCampoValor" labelProperty="valor" />
            </html:select><br/><br/>
        </form>
        <script>
            var aOptComponent = document.forms["pesquisaFormIframe"].elements["selecaoFormulario"];
            var aOptComponentsParent = parent.document.all["selecaoFormulario"];

            while(aOptComponentsParent.options.length != 0)
                aOptComponentsParent.options.remove(0);
            for( x = 0; x < aOptComponent.options.length; x++ ){
                oOption = parent.document.createElement("OPTION");
                aOptComponentsParent.options.add(oOption);
                oOption.innerText = aOptComponent.options(x).text;
                oOption.value     = aOptComponent.options(x).value;
            }
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
    </body>
</html>