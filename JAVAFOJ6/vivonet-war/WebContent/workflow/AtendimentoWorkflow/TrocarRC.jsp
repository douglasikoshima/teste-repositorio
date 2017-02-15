<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFGrupoVOArray"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="usuarioVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.usuarioVIVO" />
<head></head>
<body>  
    <form action="listaEncaminharGravar.do" method="post" name="atendimentoForm" tagId="formEncaminhar" id="formEncaminhar">
    <html:hidden name="form" property="idAtendimento"/>
    <html:hidden name="form" property="fila"/>
    <html:hidden name="form" property="idAtividade"/>
    <html:hidden name="form" property="inCRI"/>
    <html:hidden name="form" property="dsMotivo"/>
    
    <html:hidden name="form" property="grupoSel" value=""/>
    <html:hidden name="form" property="usuarioSel" value=""/>

    
<div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
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
    
    <table width="100%">
        <tr>
            <td valign="top">Motivo:</td>
            <td valign="top">
                <html:select name="form" property="motivoSel" title="grupoSel" style="width=250px">
                <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/> </html:select>
            </td>
            <td valign="top">Comentário:</td>
            <td valign="top">
                <html:textarea name="form" property="atendimentoWorkflowVO.atendimentoWorkflowComumVO.dsObservacao" rows="3" style="width:360px;height:90px;" onkeyup="checaTextarea(this,500);"/>
            </td>
        </tr>
        <tr>
            <td colspan="4" align="right">
                <img hspace="10" onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
            </td>
        </tr>
    </table>
</form>
   
<iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
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
        
        descricaoMotivo = document.forms["formEncaminhar"].motivoSel.options[document.forms["formEncaminhar"].motivoSel.selectedIndex].text;
        document.forms["formEncaminhar"].dsMotivo.value = descricaoMotivo;
        document.forms["formEncaminhar"].method = "POST";
        document.forms["formEncaminhar"].action = "respostaClienteInRC.do";
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
</body>
