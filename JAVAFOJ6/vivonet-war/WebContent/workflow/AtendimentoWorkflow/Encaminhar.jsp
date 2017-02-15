<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFGrupoVOArray"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="usuarioVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.usuarioVIVO" />
<head></head>
<body>
<acesso:controlHiddenItem nomeIdentificador="wor_enc_verpagina">
    <form action="listaEncaminharGravar.do" name="atendimentoForm" method="post" tagId="formEncaminhar" id="formEncaminhar">
    <html:hidden name="form" property="idAtendimento"/>
    <html:hidden name="form" property="fila"/>
    <html:hidden name="form" property="idAtividade"/>
        <html:hidden name="form" property="inCRI"/>
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
    <table width="100%" border="0">
                <tr>
                    <td height="7" colspan="4"></td>
                </tr>
                <tr valign="top">
                    <td valign="top" width="60">Grupo:</td>
                    <td valign="top" align="left" style="padding-left:5px;">
                    <html:select name="form" property="grupoSel" title="grupoSel" style="width=250px" onchange="submitGrupo();focaTipCampos(this.options[this.selectedIndex].text,this, 0, 70, 35);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 0, 70, 35);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 0, 70, 35);" onblur="HideTip();" onmouseout="HideTip();">
                    <html:option value="-1" key="grupoSel">&nbsp;</html:option>
                    <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/> </html:select>
                    </td>
                    <td valign="top">Comentário:</td>
                    <td rowspan="5"><html:textarea name="form" property="atendimentoWorkflowVO.atendimentoWorkflowComumVO.dsObservacao" rows="3" style="width:280px;height:90px;" onkeyup="checaTextarea(this,500);"/></td>
                </tr>
                <tr valign="top">
                    <td valign="top" width="60">Motivo:</td>
                    <td valign="top" align="left" style="padding-left:5px;">
                    <html:select name="form" property="motivoSel" title="grupoSel" style="width=250px">
                    <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                    <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/> </html:select>
                    </td>
                </tr>
                <tr valign="top">
                    <td valign="top" width="60">Usuário:</td>
                    <td valign="top" align="left" style="padding-left:5px;">
                    <html:select name="form" property="usuarioSel" title="usuarioSel" style="width=250px">
                    <html:option value="-1" key="usuarioSel">&nbsp;</html:option>
                    <html:options collection="usuarioVIVO" property="idPessoaUsuario" labelProperty="nmLoginUsuario" /> </html:select>
                    </td>
                </tr>
        <tr>
            <td height="15" colspan="4">
        </tr>
        <tr>
            <td height="15" colspan="4">
        </tr>
        <tr>
            <td colspan="4" align="right">
            <acesso:controlHiddenItem nomeIdentificador="wor_enc_gravar">
                <img hspace="10" onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
            </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
</form>

</acesso:controlHiddenItem>
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

            if (document.forms[0].elements["grupoSel"].value <= 0) {
                alert("Selecione um Grupo!");
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

        document.forms["formEncaminhar"].method = "POST";
        document.forms["formEncaminhar"].action = "listaEncaminharGravar.do";
        document.forms["formEncaminhar"].target = targetFrame;
        document.forms["formEncaminhar"].submit();

    }

    function retornarAnterior()  {
        parent.submitPesquisar();
    }

    function submitGrupo() {

        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        var meuCliente = '';
        if (parent.inMeuCliente) {
            meuCliente = '?meuCliente=true';
        }

        document.forms["formEncaminhar"].method = "POST";
        document.forms["formEncaminhar"].action = "obterUsuario.do" + meuCliente;
        document.forms["formEncaminhar"].target = "iframeUsuario";
        document.forms["formEncaminhar"].submit();
    }


    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

    document.body.style.backgroundColor = '#ededed';

</script>
</body>
