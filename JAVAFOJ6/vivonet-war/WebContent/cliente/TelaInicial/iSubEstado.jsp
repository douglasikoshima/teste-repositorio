<!--
Módulo.....: Workflow
Caso de Uso: Relacionamento
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/17 17:18:00 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
   
<bean:define id="Form"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm" />
<bean:define id="aSubEstados"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.atendimentoPesquisaVO.WFSubEstados.WFSubEstadoVOArray" />

<html>
    <head>
        <title>
            Web Application Page
        </title>
    </head>
    <body>
    
        <form name="relacionamentoFormIframe">
            <html:select name="Form" property="subEstadoSelecionado" style="WIDTH:150px" onchange="obtemLinhasIFrame(this);">
                <html:option value="">-- Selecione --</html:option>
                <html:options collection="aSubEstados" property="idSubEstado" labelProperty="dsSubEstado" /> 
            </html:select>
        </form>
        <script>
            var aOptComponent = document.forms["relacionamentoFormIframe"].elements["subEstadoSelecionado"];                                                                   
            var aOptComponentsParent = parent.document.forms["relacionamentoForm"].elements["subEstadoSelecionado"];
            
            while(aOptComponentsParent.options.length != 0)                                                         
                aOptComponentsParent.options.remove(0);
                
            for( x = 0; x < aOptComponent.options.length; x++ ) {
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
