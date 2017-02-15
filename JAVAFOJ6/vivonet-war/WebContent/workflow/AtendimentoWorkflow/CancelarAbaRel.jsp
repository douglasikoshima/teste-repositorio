<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
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
            if(parent.dvRetornoOperacao){
                parent.dvRetornoOperacao.style.visibility="";
                targetFrame = "iframeRetornoOperacao";
            }else{
                targetFrame = "";
            }
            getIdAgrupamentoEstadoTpfuturo();
            document.forms[0].method = "POST";
            document.forms[0].action = "cancelarGravarAbaRel.do";
            document.forms[0].target = targetFrame;
            document.forms[0].submit();
        }
        
        function retornarAnterior()  {
            parent.submitPesquisar();
        }
        
        /*
         * arrayIdsAgrupamentos[x] = "y,z" -> y=idMotivo z=idAgrupamentoEstadoTpfuturo
         */
        function getIdAgrupamentoEstadoTpfuturo(){
            var arrayIdsAgrupamentos = new Array();
            idMotivoSelecionado = document.forms[0].motivoSel.value;
            <logic:iterate name="motivosVO" id="motivosVOArray" indexId="indice">
			<%-- arrayIdsAgrupamentos[<%=indice%>] = "<bean:write name="motivosVOArray" property="idMotivo"/>,<bean:write name="motivosVOArray" property="idAgrupamentoEstadoTpfuturo"/>"; --%>
            arrayIdsAgrupamentos[<bean:write name="indice" />] = "<bean:write name="motivosVOArray" property="idMotivo"/>,<bean:write name="motivosVOArray" property="idAgrupamentoEstadoTpfuturo"/>";
            </logic:iterate>
            for (i=0; i<arrayIdsAgrupamentos.length; i++){
                if (arrayIdsAgrupamentos[i].split(",",1) == idMotivoSelecionado){
                    document.forms[0].idAgrupamentoEstadoTpfuturo.value = arrayIdsAgrupamentos[i].substring(arrayIdsAgrupamentos[i].indexOf(",")+1);
                }
            }
        }
    
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        //document.body.style.backgroundColor = '#ededed';
    </script>
</head>
<body style="background:'#ededed';">
<acesso:controlHiddenItem nomeIdentificador="wor_can_verpagina_abarel">
<form action="cancelarGravarAbaRel.do" name="atendimentoForm" method="post">
    <html:hidden name="form" property="idAgrupamentoEstadoTpfuturo"/>
    <html:hidden name="form" property="idAtendimento" title="idAtendimento"/>
    <html:hidden name="form" property="idAtividade"/>
    <html:hidden name="form" property="inCRI"/>
    <html:hidden name="form" property="fila"/>
    <table width="100%">
        <tr>
            <td valign="top" width="60">Motivo:</td>
            <td valign="top" align="left" style="padding-left:6px;">
                <html:select name="form" property="motivoSel" title="grupoSel" style="width=250px">
                    <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                    <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
                </html:select>
            </td>
            <td valign="top">Comentário:</td>
            <td valign="top"><html:textarea name="form" property="comentario" rows="3" style="width:280px;height:90px;" onkeyup="checaTextarea(this, 500);"/></td>
        </tr>
        <tr>
            <td align="right" colspan="4">
                <acesso:controlHiddenItem nomeIdentificador="wor_can_gravar_abarel">
                    <img onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
                </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
</form>
</acesso:controlHiddenItem>
</body>
</html>