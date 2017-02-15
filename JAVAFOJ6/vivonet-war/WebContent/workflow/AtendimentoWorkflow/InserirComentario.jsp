<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFGrupoVOArray"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="usuarioVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.usuarioVIVO" />
<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script>
        var moveToolTip = true;;
                    
        xBump=yBump=10;
        MSIE=document.all;
        NS6=document.getElementById&&!document.all;
        if(MSIE||NS6){
            ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
        }
        
        function carregaToolTip(content) {
         ttipObj.innerHTML=content;
         ttipObj.style.display='';
        }
    </script>
    <script language="javascript">
        function checaTextarea(obj, tamanho){
            obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
        }
        
        function submitAplicar() {
            //Liga animação
            if(top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
            if(parent.dvRetornoOperacao){
                parent.dvRetornoOperacao.style.visibility="";
                targetFrame = "iframeRetornoOperacao";
            }else{
                targetFrame = "";
            }
            document.forms["formEncaminhar"].method = "POST";
            document.forms["formEncaminhar"].action = "inserirComentarioGravar.do";
            document.forms["formEncaminhar"].target = targetFrame;
            document.forms["formEncaminhar"].submit();
        }
        
        function retornarAnterior()  {
            parent.submitPesquisar();
        }
    
        function submitGrupo() {
            //Liga animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
            document.forms["formEncaminhar"].method = "POST";
            document.forms["formEncaminhar"].action = "obterUsuario.do";
            document.forms["formEncaminhar"].target = "iframeUsuario";
            document.forms["formEncaminhar"].submit();
        }

        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        document.body.style.backgroundColor = '#ededed';
    </script>
</head>
<body>  
    <form action="listaEncaminharGravar.do" name="atendimentoForm" method="post" tagId="formEncaminhar" id="formEncaminhar">
        <html:hidden name="form" property="idAtendimento"/>
        <html:hidden name="form" property="fila"/>
        <html:hidden name="form" property="idAtividade"/>
        <html:hidden name="form" property="inCRI"/>
        <html:hidden name="form" property="dsMotivo"/>
        <html:hidden name="form" property="grupoSel" value=""/>
        <html:hidden name="form" property="usuarioSel" value=""/>
        <div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
        <table width="100%">
            <tr>
                <td valign="top">Comentário:</td>
                <td valign="top">
                    <html:textarea name="form" property="atendimentoWorkflowVO.atendimentoWorkflowComumVO.dsObservacao" rows="3" style="width:360px;height:90px;" onkeyup="checaTextarea(this,500);"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <img hspace="10" onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
                </td>
            </tr>
        </table>
    </form>
    <iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
</body>
</html>