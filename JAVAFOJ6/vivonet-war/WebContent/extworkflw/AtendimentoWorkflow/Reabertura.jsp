<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFGrupoVOArray"/>
<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript">
        function checaTextarea(obj, tamanho){
            obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
        }
        
        function submitAplicar() {
            if(document.forms[0].elements["motivoSel"].value <= 0) {
                alert("Selecione um Motivo!");
                return;
            }
            //Liga animação
            if(top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
            if(parent.dvRetornoOperacao) {
                parent.dvRetornoOperacao.style.visibility="";
                targetFrame = "iframeRetornoOperacao";
            }else{ 
                targetFrame = "";
            }
            document.forms[0].method = "POST";
            document.forms[0].action = "reaberturaGravar.do";
            document.forms[0].target = targetFrame;
            document.forms[0].submit();
        }
        
        function retornarAnterior()  {
            parent.submitPesquisar();
        }

        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    </script>
</head>
<body onload="document.body.style.backgroundColor='#ededed';">
<form action="reaberturaGravar.do" method="post" name="atendimentoForm">
    <html:hidden name="form" property="idAtendimento" title="idAtendimento"/>
    <html:hidden name="form" property="idAtividade"/>
    <html:hidden name="form" property="inCRI"/>
    <html:hidden name="form" property="fila"/>
    <table width=100%>
        <tr valign="top">
            <td width="60">Motivo:</td>
            <td align="left" style="padding-left:5px;">
                <html:select name="form" property="motivoSel" title="grupoSel" style="width:250px;">
                    <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                    <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
                </html:select>
            </td>
            <td>Comentário:</td>
            <td rowspan="4"><html:textarea name="form" property="comentario" rows="3" style="width:280px;height:90px;" onkeyup="checaTextarea(this, 500);"/></td>
        </tr>
        <tr valign="top">
            <logic:notEqual value="0" name="form" property="numGruposAbertura">
            <td width="60">Grupo de Abertura:</td>
            <td align="left" style="padding-left:5px;">
                <html:select name="form" property="grupoSel" title="grupoSel" style="width=250px">
                <html:option value="-1" key="grupoSel">&nbsp;</html:option>
                <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/> </html:select>
            </td>
            </logic:notEqual>
        </tr>
        <tr>
            <td height="20" colspan="4">
        </tr>
        <tr>
            <td height="20" colspan="4">
        </tr>
        <tr>
            <td colspan="4" align="right">
                <img onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/> 
            </td>
        </tr>
    </table>
</form>
</body>
</html>