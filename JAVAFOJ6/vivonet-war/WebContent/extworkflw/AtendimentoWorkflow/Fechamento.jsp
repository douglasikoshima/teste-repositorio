<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
   
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="scriptValidacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.scriptValidacao" />

<form action="fechamentoGravar.do" method="post" name="atendimentoForm">
    <html:hidden name="form" property="idAtendimento"/>
    <html:hidden name="form" property="idAtividade"/>
    <html:hidden name="form" property="inCRI"/>
    <html:hidden name="form" property="fila"/>
    <html:hidden name="form" property="numeroCampos"/>   
    <table width="100%" border="0">
                    <tr valign="top">
                        <td valign="top" width="60">Motivo:</td>
                        <td valign="top" align="left" style="padding-left:5px;">
                            <html:select name="form" property="motivoSel" title="motivoSel" style="width=250px">
                                <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                                <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
                            </html:select>
                        </td>
                        <td>Comentário:</td>
                        <td><html:textarea name="form" property="comentario" rows="3" style="width:280px;height:90px;" onkeyup="checaTextarea(this, 500);"/></td>
                    </tr>
                    <tr valign="top">
                        <td>
                            <table>
                                <tr>
                                    <logic:notEmpty name="form" property="arvoreEncerramentoVO.formularioVO">
                                        <jsp:include page="FormularioDinamico.jsp" />
                                    </logic:notEmpty>
                                </tr>
                            </table>
                        </td>
                    </tr>
        <tr>
            <td colspan="4" align="right">            
                <img onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>             
            </td>
        </tr>
    </table>
    <!--Quadro Flutuante que exibe a pesquisa do formulário-->
    <vivo:quadroFlutuante id="dvPesqForm" idIframe="ifrmPesqForm" width="270" height="200" spacesTop="25" spacesLeft="250" display="none" url="<%=request.getContextPath()%>" src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/pesquisaFormulario.do" onclick="ativar_combos();"/>
    <iframe id="divPesquisaForm" src="javascript:false;" scrolling="no" frameborder="0" style="position:absolute; top:0px; left:0px; display:none;"></iframe>
</form>
   
<script language="javascript">

    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }
    
    function mostraPesquisaFormulario(idCampo, nomeCampo, campo){
        var IfrRef = parent.document.getElementById('ifrmPesqForm');
        desativar_combos();
        parent.ifrmPesqForm.document.all['tdCampo'].innerText = nomeCampo;
        parent.ifrmPesqForm.document.all['idCampo'].value = idCampo;
        parent.ifrmPesqForm.document.all['nomeCampo'].value = campo.parentElement.parentElement.all['tdValorCampo'].childNodes(0).name;
        parent.ifrmPesqForm.vaciarLista();
        parent.dvPesqForm.style.display = '';
    }

     // Array p/ controle de ativa/desativa elementos dos forms
     frmsState = null;
 
     //desativa todos os combos
    function desativar_combos() {
        parent.desativar_combos();
        if (frmsState == null) {
            forms = document.forms;
            frmsState = new Array(forms.length);
            
            for (i=0;i<forms.length;i++) {
                el = forms[i].elements;
                elState = new Array(el.length);
                frmsState[i] = elState;
                for(j=0;j<el.length;j++){
                    elState[j]=el[j].disabled;
                    el[j].disabled=true;
                }
            }
        }
        return;
    }
        
    //ativa todos o s combos
    function ativar_combos() {
        parent.ativar_combos();
        if (frmsState != null) {
    
            forms = document.forms;
            
            for (i=0;i<forms.length;i++) {
                el = forms[i].elements;
                elState = frmsState[i];
                frmsState[i] = elState;
                for(j=0;j<el.length;j++){
                    el[j].disabled=elState[j];
                }
            }
        }
        frmsState = null;
        return;
    }

    function submitAplicar() {
    
        if (document.forms[0].elements["motivoSel"].value <= 0) {
            alert("Selecione um Motivo!");
            return;
        }
        else if (!validaFormulario()) {
            return false;
        }
    
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        if (parent.dvRetornoOperacao) {
            parent.dvRetornoOperacao.style.visibility="";
            targetFrame = "iframeRetornoOperacao";
        } else { 
            targetFrame = "";
        }


        // parent.dvConPesq.style.display = '';

        document.forms[0].method = "POST";
        document.forms[0].action = "fechamentoGravar.do";
        document.forms[0].target = targetFrame;
        document.forms[0].submit();
    }
    
    <%=scriptValidacao%>

    function validaCampo(campo, tipoCampo){
        switch(tipoCampo){
            case 'texto':
                return;
                break;
            case 'telefone':
                if(campo.value == '')
                    return;
                maskPhoneNumberObj(campo);
                if(campo.value.length < 13){
                    campo.value = '';
                    campo.focus();
                    alert("Linha inválida");
                }
                break;
            case 'data':
                if(campo.value == '')
                    return false;
                if(!validate_date(campo.value)){
                    campo.value = '';
                    campo.focus();
                    alert("Data inválida");
                }
                break;
                
            case 'hora':
                if(campo.value == '')
                    return false;
                if(!validaHora(campo.value)){
                    campo.value = '';
                    campo.focus();
                }
                break;
                
            case 'cpf':
                if(campo.value == '')
                    return false;
                if(!VerificaCPF(campo.value)){
                    campo.value = '';
                    campo.focus();
                    alert("CPF inválido");
                }
                break;
                
            case 'cnpj':
                if(campo.value == '')
                    return false;
                if(!VerificaCNPJ(campo.value)){
                    campo.value = '';
                    campo.focus();
                    alert("CNPJ inválido");
                }
                break;                                            
        }
    }                                                       

    function retornarAnterior()  {
        parent.submitPesquisar();
    }
</script>

<script language="javascript">
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
    document.body.style.backgroundColor = '#ededed';
    
</script>
