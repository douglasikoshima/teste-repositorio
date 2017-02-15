<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="FormSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm"/>
<bean:define id="aUsuarioExistente"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.usuarioExistenteVOArray"/>

    <head>
            <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body>
        <div id="usuario_existente_div_inner">
            <html:select name="FormSkill" property="usuarioExistente" multiple="true" ondblclick="transfereSelecaoLista(document.forms[0].usuarioExistente, document.getElementById('usuarioSelecionado'));" onmouseover="ativarToolTip(this,null)" size="9" style="width=335px;height=305px;">
                <html:options collection="aUsuarioExistente" property="idUsuario" labelProperty="nmUsuario"/>
            </html:select>
        </div>
</body>

    <script>
        parent.document.getElementById('usuario_existente_div').innerHTML = document.getElementById('usuario_existente_div_inner').innerHTML;
        parent.parent.oculta_div();
    </script>

    <vivo:alert atributo="msgStatus" scope="request"/>
