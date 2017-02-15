<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="motivoForm" name="motivoForm" scope="request" />
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<form action="gravarMotivo.do" method="post">
<table cellpadding="5" width="100%" height="100%" border="0">
    <tr>
        <td align="right">
            <b>Descrição do Motivo:</b>
        </td>
        <td>
            <html:hidden name="motivoForm" property="idMotivo"/>
            <html:text name="motivoForm" property="dsMotivo" size="60" maxlength="250"/>
        </td>
        <td>
            <img hspace="8" vspace="6" style="border:none;" onClick="gravar(); return false;" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
        </td>
        <td>
            <img hspace="8" vspace="6" style="border:none;" onClick="parent.divIncluirAlterar.style.display='none'; return false;" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif"/>
        </td>
    </tr>
</table>
</form>
<iframe id="ifrmResultadoOperacao" name="ifrmResultadoOperacao" style="width:0px; height:0px; display:none;"></iframe>
<script>
document.forms[0].dsMotivo.focus();
function gravar() {
    f=document.forms[0];
    if (f.dsMotivo.value=="") {
        alert("Descrição do Motivo não pode ser Vazia!");
        return false;
    }
    parent.screen_block();
    f.target="ifrmResultadoOperacao";
    f.submit();
}
function fechar() {
    parent.divIncluirAlterar.style.display='none';
    parent.pesquisar();
}
parent.document.getElementById("dv_divIncluirAlterar").innerHTML="Editar Motivo";
<logic:equal name="motivoForm" property="idMotivo" value="0">
    parent.document.getElementById("dv_divIncluirAlterar").innerHTML="Incluir novo Motivo";
</logic:equal>
</script>
