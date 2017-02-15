<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<bean:define id="form" name="atendimentoFormDocumento" scope="session"/>
<bean:define id="tiposVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.WFDocumentoTecnicoTipoVOArray"/>
<bean:define id="estadosVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.WFDocumentoTecnicoEstadoVOArray"/>
<bean:define id="procedenciasVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.procedenciaVOArray"/>

<acesso:controlHiddenItem nomeIdentificador="wor_dafec_verpagina">
<form name="fechamento">
    <table width="100%" border="0">
        <tr valign="top">
            <td width="20%">
                Número:
            </td>
            <td width="30%">
                <html:text name="form" property="atendimentoWorkflowTecnicoDocVO.nrDocumento" style="width=100"/>
            </td>
            <td width="20%" rowspan="4">
                Comentário:
            </td>
            <td width="30%" rowspan="4">
                <html:textarea rows="6" name="form" property="atendimentoWorkflowTecnicoDocVO.dsDocumento" style="width=300" onkeyup="checaTextarea(this, 500);"/>
            </td>
        </tr>
        <tr valign="top">
            <td>
                Data Encerramento:
            </td>
            <td>
                <html:text name="form" property="atendimentoWorkflowTecnicoDocVO.dtFechamento" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" styleId="dtFechamento" style="width=66"/><img id="imgCalendDtAbIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFechamento', '%d/%m/%Y');">
            </td>
        </tr>
        <tr valign="top">
            <td>
                Horário Encerramento:
            </td>
            <td>
                <html:text name="form" property="hora" styleId="hora" size="5" maxlength="5" onkeyup ="Formatar(this.value, this.form.name, this.name, 'hora');" onchange="Formatar(this.value, this.form.name, this.name, 'hora');"/>
            </td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td align="right" valign="top">
            <acesso:controlHiddenItem nomeIdentificador="wor_dafec_salvar">
                <img hspace="10" onClick="submitFechamento();" style="border:none;" id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"/>
            </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
</form>
   
</acesso:controlHiddenItem>
<script language="JavaScript">

    var targetFrame = "ifrmAbas";

<logic:equal name="form" property="docTec" value="1">
    targetFrame = "ifrmDocTec";
</logic:equal>

    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }
    
    function verificaDataDigitos(vEntrada) {
        if(vEntrada<10){
            vEntrada = 0+''+vEntrada;
        }
        return vEntrada;
    }
    
    function submitFechamento() {
        erros = "";
        if (document.forms["fechamento"].elements["atendimentoWorkflowTecnicoDocVO.nrDocumento"].value == "") {
            erros += "Digite o Número do Documento!\n";
        }
                
        if (document.forms["fechamento"].elements["atendimentoWorkflowTecnicoDocVO.dtFechamento"].value == "") {
            erros += "Digite a Data do Fechamento!\n";
        }   
                
        if (document.forms["fechamento"].hora.value == "") {
            erros += "Digite o Horário do Fechamento!";
        }
    
        if (erros == "") {
            //Inicio animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
            
            document.forms["fechamento"].method = "POST";
            document.forms["fechamento"].action = "documentoAssociadoFechamento.do";
            document.forms["fechamento"].target = "frameRetornoDocTec";//targetFrame;
            document.forms["fechamento"].submit();
            document.forms["fechamento"].reset();
            
        } else {
            alert(erros);
            return false;
        }
    }

    function validaData(data){
        if(data.value == '')
            return false;
        if(!validate_date(data.value)){
            data.value = '';
            data.focus();
            alert("Data inválida");
        }
    }

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        
    document.body.style.backgroundColor = '#ededed';
    
</script>
