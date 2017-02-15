<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm" />
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<script src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css">

<vivo:body idTable="tbMain" idDiv="dvMain" height="440" width="770">
    <form action="listaVoltarGravar.do" name="atendimentoForm" method="post" tagId="formVoltar" id="formVoltar">
    <html:hidden name="form" property="idAtendimento" title="idAtendimento"/>
    <html:hidden name="form" property="idAtividade" title="idAtendimento"/>
        <html:hidden name="form" property="inCRI"/>
    <html:hidden name="form" property="inTratamentoUsuario"/>
    <html:hidden name="form" property="fila"/>
        <table align="left" border="0" bgcolor="#E3ECF4">
            <tr>
                <td>Motivo:</td>
                <td align="left" style="padding-left:4px;">
                <html:select name="form" property="motivoSel" title="motivoSel" style="width=200px">
                    <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                    <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
                </html:select>
                </td>
            </tr>
            <tr>
                <td>Comentário:</td>
                <td><html:textarea name="form" property="comentario" rows="3" style="width=200px" onkeyup="checaTextarea(this, 500);"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <img onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/> 
                    <logic:equal name="form" property="idAtendimento" value="0">
                        <img onClick="submitFechar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif"/> 
                    </logic:equal>
                </td>
            </tr>
        </table>
    </form>
</vivo:body>

<script language="javascript">

    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }
    
    function initPagina() { }
    
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

        document.forms["formVoltar"].method = "POST";
        document.forms["formVoltar"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/listaVoltarGravar.do";
        document.forms["formVoltar"].target = targetFrame;
        document.forms["formVoltar"].submit();
    }
    
    function submitFechar() {
        //parent.submitPesquisar();
        parent.exibicaoAbaPesquisas(1);
        document.forms[0].method = "POST";
        document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/atendimentoWorkflowVoltar.do";
        document.forms[0].target = "iframeUsuario";
        document.forms[0].submit();        
    }
    
    
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>

