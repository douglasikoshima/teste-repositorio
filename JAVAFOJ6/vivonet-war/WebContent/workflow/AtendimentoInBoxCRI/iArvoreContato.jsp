<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>


<link type="text/css" rel="stylesheet" href="../../resources/css/xtree.css">
<link href="../../resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="../../resources/scripts/frameweb.js"></script>
<script src="../../resources/scripts/xtree.js"></script>
<script src="../../resources/scripts/RWFArvore.js"></script>

<%String scriptArvore = (String)request.getAttribute("scriptArvore");%>

<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="icri_arvc_verpagina">

<form name="ExpandeArvoreContatoForm">
<script language="javascript"><%=scriptArvore%></script>
<table  width="100%" cellspacing="0" cellpadding="0" border="0">
<tr>
    <td align="right">
    <%--<acesso:controlHiddenItem nomeIdentificador="wor_riac_selecionar">--%>
    <vivo:botao id="btSelecionar" width="100px" height="15px" value="Selecionar" styleClass="btTemplate" onclick="selecionar();"/>
    <%--</acesso:controlHiddenItem>--%>
    </td>
    <td align="left">
    <%--<acesso:controlHiddenItem nomeIdentificador="wor_riac_cancelar">--%>
    <vivo:botao id="btCancelar" width="100px" height="15px" value="Cancelar" styleClass="btTemplate" onclick="cancelar();"/>
    <%--</acesso:controlHiddenItem>--%>
    </td>
</tr>
</table>
<iframe name="ifrmArvoreContato" style="visibility:hidden;" width="1" height="1"></iframe>
<input type="hidden" name="idContato">
</form>

</acesso:controlHiddenItem>

<script language="javascript">
parent.dvArvore.style.display='';
var idContatoSel='';
var dscContatoSel='';
parent.desativar_combos();
if(top.frameApp.dvAnimarAguarde!=null){
    top.frameApp.stopAnimation();
}
</script>