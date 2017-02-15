<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>

<acesso:controlHiddenItem nomeIdentificador="wor_dbko_verpagina">
<form action="listaVoltarGravar.do" method="post" name="atendimentoForm">
    <html:hidden name="form" property="idAtendimento" title="idAtendimento"/>
    <html:hidden name="form" property="idAtividade"/>
        <html:hidden name="form" property="inCRI"/>
    <html:hidden name="form" property="fila"/>
    <html:hidden name="form" property="inTratamentoUsuario"/>
    <table width="100%">
        <tr valign="top">
            <td width="60">Motivo:</td>
            <td align="left" style="padding-left:6px;">
                <html:select name="form" property="motivoSel" title="motivoSel" style="width=250px">
                    <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                    <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
                </html:select>
            </td>
            <td>Comentário:</td>
            <td><html:textarea name="form" property="comentario" rows="3" style="width:280px;height:90px;" onkeyup="checaTextarea(this, 500);"/></td>
        </tr>
        <tr>
            <td align="right" colspan="4">
            <acesso:controlHiddenItem nomeIdentificador="wor_dbko_gravar">
                <img onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/> 
            </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
</form>
   
</acesso:controlHiddenItem>
<script language="javascript">

    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }

    function submitAplicar() {
    
            if (document.forms[0].elements["motivoSel"].value <= 0) {
                alert("Selecione um Motivo!");
                return;
            }
    
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
        if (parent.dvRetornoOperacao) {
            parent.dvRetornoOperacao.style.visibility="";
            targetFrame = "iframeRetornoOperacao";
        } else { 
            targetFrame = "";
        }

        document.forms[0].method = "POST";
        document.forms[0].action = "listaVoltarGravar.do";
        document.forms[0].target = targetFrame;
        document.forms[0].submit();
    }
    
    function retornarAnterior()  {
        parent.submitPesquisar();
    }

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
    document.body.style.backgroundColor = '#ededed';
    
</script>

