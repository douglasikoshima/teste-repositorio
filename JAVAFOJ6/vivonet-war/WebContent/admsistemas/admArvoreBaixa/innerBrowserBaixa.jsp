<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="FormEditaBaixa"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa"/>
<bean:define id="AdmNomeBaixaVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa.admNomeBaixaVO"/>

<html>
    <head>

    <script FOR=window EVENT=onload LANGUAGE="JScript">
        parent.document.getElementById('div1').innerHTML = document.getElementById('dvComboNomeBaixa').innerHTML;    
        parent.parent.oculta_div();
        
    </script>

    </head>
    <body>

        <div id="dvComboNomeBaixa">
            <html:select name="FormEditaBaixa" property="idNomeBaixa" style="text-indent:3px;width:150px;left: -150px;" styleClass="SELECT">
                <html:option value="-1">-- Selecione --</html:option>
                <html:options collection="AdmNomeBaixaVO" property="idNomeBaixa" labelProperty="nmBaixa" /> 
            </html:select>
        </div>

    </body>
    
</html>