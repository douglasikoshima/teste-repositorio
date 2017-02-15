<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

   

<acesso:controlInitEnv/>
<script>
function cancelarAtd(){
    parent.cancelarAtd(document.getElementById("cancelarAtd").options[document.getElementById("cancelarAtd").selectedIndex].value);
}
</script>
<table border="0" cellpadding="0" cellspacing="0" align="center" background="#545454">
    <tr>
        <td valign="middle" align="center">
            <select name="cancelarAtd" id="cancelarAtd">
                <option value="4">Ligação Indevida</option>
                <option value="7">Ligação Interrompida</option>
            </select>
        </td>                            
    </tr>
    <tr>
        <td valign="middle" align="center">&nbsp;</td>
    </tr>
    <tr>
        <td valign="middle" align="center">
            <img src="/FrontOfficeWeb/resources/images/bt_ok_nrml.gif" onClick="cancelarAtd();" border="0"/>            
        </td>
    </tr>                
</table>
