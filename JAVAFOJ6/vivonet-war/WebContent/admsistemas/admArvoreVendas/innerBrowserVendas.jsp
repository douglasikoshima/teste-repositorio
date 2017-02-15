<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="FormArvoreContato"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato"/>
<bean:define id="AdmNomeContatoVO"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admNomeContatoVO"/>

<html>
    <head>

    <script FOR=window EVENT=onload LANGUAGE="JScript">
        parent.document.getElementById('divTipo1').innerHTML = document.getElementById('dvComboNomeContato').innerHTML;    
        parent.parent.oculta_div();
        
    </script>

    </head>
    <body>

        <div id="dvComboNomeContato">
            <html:select name="FormArvoreContato" property="idNomeContatoEscolhido" style="text-indent:3px;width:370px;left: -150px;" styleClass="SELECT" onchange="limpaNome();">
                <html:option value="-1">Escolha uma opção...</html:option>
                <html:options collection="AdmNomeContatoVO" property="idNomeContato" labelProperty="nmContato" /> 
            </html:select>
        </div>

    </body>
    
</html>