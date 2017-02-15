<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="FormUser"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm"/>
<!-- array de Unidade -->
<bean:define id="aUnidades"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaUnidadeOrganograma.unidadeOrganogramaVOArray"/>

<html>
    <head>
    <script FOR=window EVENT=onload LANGUAGE="JScript">
        parent.document.getElementById('divOrganizacao').innerHTML = document.getElementById('dvComboOrganizacao').innerHTML;
        parent.bolEnvia = false;
        parent.oculta_div();
        parent.ativar_combos();
        parent.document.forms[0].organizacaoAtual.focus();
    </script>
    </head>
    <body>
        <div id="dvComboOrganizacao">
            <html:select name="FormUser" property="unidadeOrganizacaoAtual" style="width=160px;height=70px;margin-left:3px;" onmouseover="ativarToolTip(this,1)">
                <html:option value="-1">Escolha uma opção...</html:option>
                <html:options collection="aUnidades" property="idUnidade" labelProperty="nmUnidade" /> 
            </html:select>
        </div>
    </body>
</html>