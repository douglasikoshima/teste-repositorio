<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="notasForm" name="notasForm" scope="request" />
<bean:define id="tipoNotasVO" name="notasForm" property="tipoNotasVO" />
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<form action="editarComentarioNotaURA.do" method="post" name="notasForm">
<acesso:controlHiddenItem nomeIdentificador="ntura_edt_verpagina">
<html:hidden name="notasForm" property="notaVO.idAtendimentoNota"/>
<html:hidden name="notasForm" property="notaVO.tipoNotaAtendimento"/>
<table cellpadding="5" width="" height="100%" border="0">
    <tr>
        <td align="right">
            <b>Processo:</b>            
        </td>
        <td>
            <html:text readonly="true" name="notasForm" property="notaVO.idAtendimento"  size="13" />
        </td>
        <td align="right">
            <b>Tipo Nota:</b>            
        </td>
        <td colspan="3">
        <bean:write name="notasForm" property="notaVO.tipoNotaAtendimento"/>
        </td>        
    </tr>           
    <tr>
        <td align="right">
            <b>Coment�rio:</b>
        </td>
        <td colspan="5">
            <html:textarea name="notasForm" property="notaVO.comentario" size="50" rows="5" onkeyup="checaTextarea(this,1024);" onblur="checaTextarea(this,1024);"/>
        </td>
    </tr>
    <tr>
        <td>
        </td>
        <td align="right" colspan="5">
            <img hspace="8" vspace="6" style="border:none;" onClick="parent.dvEditarNota.style.display='none'; return false;" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif"/>
            <acesso:controlHiddenItem nomeIdentificador="ntura_edt_gravar">
            <img hspace="8" vspace="6" style="border:none;" onClick="gravar(); return false;" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
            </acesso:controlHiddenItem>
        </td>
    </tr>
</table>
</acesso:controlHiddenItem>
</form>
<iframe id="ifrmResultadoOperacao" name="ifrmResultadoOperacao" style="width:0px; height:0px; display:none;"></iframe>
<script>
screen_unblock();
function gravar() {
    f=document.forms[0];
    //parent.screen_block();
    //f.target="ifrmResultadoOperacao";
    f.submit();
}
function fechar() {
    document.forms[0].action='voltarInbox.do';
    document.forms[0].submit(); 
    parent.dvEditarNota.style.display='none';    
}
function fecharInbox(){
    fechar();
}
function fecharNotaUra(){
   parent.dvEditarNota.style.display='none';  
}
function checaTextarea(obj, tamanho){
    if (obj.value.length>tamanho) {
        obj.value = obj.value.substr(0,tamanho);
        alert("Tamanho m�ximo: 1024 carcteres!");
    }
}
function screen_block() {
    //Liga anima��o
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
}

function screen_unblock() {
    //Desliga anima��o
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
}

</script>
