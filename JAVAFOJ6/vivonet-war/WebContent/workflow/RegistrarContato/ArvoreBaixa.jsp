<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>

<bean:define id="Form"                name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />
<bean:define id="scriptArvoreBaixa"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.scriptArvoreBaixa" />

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<script>
    function initPagina() { }
    
    function mostraDadosArvore(idBaixa, idBaixaMensagem, respostaPadrao){
        document.all['dvCodigoBaixa'].innerText = idBaixa;
        document.all['tdRespostaPadrao'].innerText = respostaPadrao;
        document.all['idBaixaMensagem'].value = idBaixaMensagem;    
    }
    
    function fechamentoImediato(){        
        if(document.all['dvCodigoBaixa'].innerText == ''){
            alert("Selecione uma folha da árvore!");
            return false;
        }
        //fecha a apresentação
        parent.dvNumProc.style.display = 'none';
        if(parent.dvAtendimento)
            parent.dvAtendimento.style.display = 'none';
        else
            parent.$('divPopupTI').style.display = 'none';

        parent.ifrmAbas.ativar_combos();
        parent.ifrmNumProc.document.location.replace("about:blank");
        parent.fecharProcesso(document.all['dvCodigoBaixa'].innerText, document.all['idBaixaMensagem'].value, document.all['observacao'].innerText);
    }
    
    function expandirNo(idBaixa) {
        
        if (!tree.getSelected().isAddEnabled()) {
            return;
        }
                            
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        document.forms[0].method = "POST";
        document.forms[0].action = "obtemArvoreBaixaParte.do?IDBAIXA="+idBaixa;
        document.forms[0].target = "iframeArvoreBaixa";
        document.forms[0].submit();

    }
    
    function fechar() {
        //fecha a apresentação
        parent.dvNumProc.style.display = 'none';
        if(parent.dvAtendimento)
            parent.dvAtendimento.style.display = 'none';
        else
            parent.$('divPopupTI').style.display = 'none';
        
        parent.ifrmAbas.ativar_combos();
        parent.ifrmNumProc.document.location.replace("about:blank");
    }
    
    onload = function(){
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    }
</script>

<script src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css">
<form>
    <html:hidden name="Form" property="idContato"/>                         
    <%--<html:hidden name="Form" property="user"/>--%>
    <html:hidden name="Form" property="descricaoCompleta"/> 
    <input type="hidden" value="" id="idBaixaMensagem">
    <acesso:controlHiddenItem nomeIdentificador="wor_ab_verpagina">
        <table width="100%" border="0">
            <tr valign="top">
                <td colspan=2>
                    <script>
                        <%=scriptArvoreBaixa%>    
                        //Fim animação
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                    </script>
                    <p>
                </td>
            </tr>
            <tr>
                <td width="100px">
                    <b>Código de Baixa:</b>
                </td>
                <td>
                    <div id="dvCodigoBaixa"></div>
                </td>
            </tr>
            <tr>
                <td>Resposta Padrão:</td>
                <td id="tdRespostaPadrao"></td>
            </tr>
            <tr valign="top">
                <td>Comentário:</td>
                <td><textarea  id="observacao" rows="5" style="width:450px" onblur="return checaTextarea(this,1000);return false;" onkeyup="return checaTextarea(this,1000);return false;"></textarea></td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                <acesso:controlHiddenItem nomeIdentificador="wor_ab_aplicar">
                    <vivo:botao id="btAplicar" width="60px" height="10px" value="Gravar" styleClass="btTemplate" onclick="fechamentoImediato();"/>
                </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        <iframe scrolling="yes" style="visibility:hidden;" name="iframeArvoreBaixa" height="1px" width="1px"></iframe> 
   
    </acesso:controlHiddenItem>
</form>
<script>
    parent.ifrmAbas.desativar_combos();
    
    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }
</script>
