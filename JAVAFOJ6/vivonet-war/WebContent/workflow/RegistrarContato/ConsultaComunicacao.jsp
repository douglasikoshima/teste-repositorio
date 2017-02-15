<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
   
<acesso:controlInitEnv/>
<bean:define id="Form"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />
<bean:define id="aComunicacaoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.tipoComunicacaoSelecionadaVO.atendimentoComunicacaoVOArray" />

<html>
    <head>
        <title></title>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_ccom_verpagina">
        <form name="registrarContatoFormIframe">
            <html:select name="Form" property="comunicacaoDisponivel" multiple="true" size="3">
            <logic:present  name="aComunicacaoVO">
            	<c:catch var="errItaComunicacaoVO">
                <html:options collection="aComunicacaoVO" property="dsAuxiliar" labelProperty="descricao" />
            	</c:catch>
            </logic:present>  
            </html:select>            
        </form>
        <script>
            var aOptComponent = document.forms["registrarContatoFormIframe"].elements["comunicacaoDisponivel"];
            var aOptComponentsParent = parent.document.forms["registrarContatoForm"].elements["comunicacaoDisponivel"];
            var aOptComponentsParentSelec = parent.document.forms["registrarContatoForm"].elements["comunicacaoSelecionada"];
            var tipoComunicacaoSelecionada = parent.document.forms[0].tipoComunicacaoSelecionada;

            while(aOptComponentsParent.options.length != 0)                                                         
                aOptComponentsParent.options.remove(0);
            
            for( x = 0; x < aOptComponent.options.length; x++ ){
                var tratar = true;
                for( i = 0; i < aOptComponentsParentSelec.options.length; i++ ){
                    if (aOptComponent.options(x).value == aOptComponentsParentSelec.options(i).value){
                        tratar = false;   
                        break;
                    }
                }
                
                if (tratar){
                    oOption = parent.document.createElement("OPTION");               
                    aOptComponentsParent.options.add(oOption);
                    oOption.innerText = aOptComponent.options(x).text;
                    oOption.value     = aOptComponent.options(x).value;
                }
            }

            if (tipoComunicacaoSelecionada.options[tipoComunicacaoSelecionada.selectedIndex].text == "CELULAR") {
                if (parent.parent.nrCelular == null){
                    parent.parent.nrCelular = "";
                }
                
                var inCreateCell = true;
                for (i=0; i<aOptComponentsParentSelec.length; i++) {
                    if (aOptComponentsParentSelec.options[i].value == parent.parent.nrCelular) {
                        inCreateCell = false;
                    }
                }
                
                aOptComponentsParent.selectedIndex = 0;
                if (inCreateCell) {
                    parent.transfereSelecaoLista(aOptComponentsParent, aOptComponentsParentSelec);    
                
                } else {
                    while(aOptComponentsParent.options.length != 0)                                                         
                        aOptComponentsParent.options.remove(0);
                }
            }

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
   
    </acesso:controlHiddenItem>
    </body>
</html>
