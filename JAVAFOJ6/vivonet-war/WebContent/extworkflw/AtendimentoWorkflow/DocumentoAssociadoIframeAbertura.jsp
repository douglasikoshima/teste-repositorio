<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
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


<form method="post" name="abertura">
    <table width="100%" border="0">
        <tr valign="top">
            <td width="20%">
        Tipo de Documento:                        
            </td>
            <td width="30%">
                <html:select name="form" property="atendimentoWorkflowTecnicoDocVO.idTipoDocumentoProcesso" style="width=100">
                    <html:option value="" key="-1">&nbsp;</html:option>
                    <html:options collection="tiposVO" property="idDocTecTipo" labelProperty="dsDocTecTipo"/>
                </html:select>
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
                Número:
            </td>
            <td>
                <html:text name="form" property="atendimentoWorkflowTecnicoDocVO.nrDocumento" style="width=100"/>
            </td>
        </tr>
        <tr valign="top">
            <td>
                Data Abertura:
            </td>
            <td>
                <html:text name="form" property="atendimentoWorkflowTecnicoDocVO.dtAbertura" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" styleId="dtAbertura" style="width=66"/><img id="imgCalendDtAbIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtAbertura', '%d/%m/%Y');">
            </td>
        </tr>
        <tr valign="top">
            <td>
                Previsão de Fechamento:
            </td>
            <td>
                <html:text name="form" property="atendimentoWorkflowTecnicoDocVO.dtEstimadaFechamento" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" styleId="dtEstimadaFechamento" style="width=66"/><img id="imgCalendDtAbIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtEstimadaFechamento', '%d/%m/%Y');">
            </td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td align="right" valign="top">            
                <img hspace="10" onClick="submitAbertura(); return false" style="border:none;" id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"/>             
            </td>
        </tr>
    </table>
</form>
   
<script language="JavaScript">

    var targetFrame = "ifrmAbas";

<logic:equal name="form" property="docTec" value="1">
    targetFrame = "ifrmDocTec";
</logic:equal>

function checaTextarea(obj, tamanho){
  obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
}
    
function submitAbertura() {
    
        erros = "";
        if (document.forms["abertura"].elements["atendimentoWorkflowTecnicoDocVO.idTipoDocumentoProcesso"].value <= 0) {
            erros+="Selecione um Tipo de Documento!\n";
        }
        
        if (document.forms["abertura"].elements["atendimentoWorkflowTecnicoDocVO.nrDocumento"].value == "") {
            erros+="Digite o Número do Documento!\n";
        }
        
        if (document.forms["abertura"].elements["atendimentoWorkflowTecnicoDocVO.dtAbertura"].value == "") {
            erros+="Digite a Data de Abertura!\n";
        }
        
        if (document.forms["abertura"].elements["atendimentoWorkflowTecnicoDocVO.dtEstimadaFechamento"].value == "") {
            erros+="Digite a Data Estimada para o Fechamento!\n";
        }
        
        if (erros == "") {
    
            dtAbertura = document.forms["abertura"].elements["atendimentoWorkflowTecnicoDocVO.dtAbertura"].value;
            dtEstimadaFechamento = document.forms["abertura"].elements["atendimentoWorkflowTecnicoDocVO.dtEstimadaFechamento"].value;
    
            if (validaDataFinal(dtAbertura, dtEstimadaFechamento)) {
                //Inicio animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
                document.forms["abertura"].method = "POST";
                document.forms["abertura"].action = "documentoAssociadoAbertura.do";
                document.forms["abertura"].target = "frameRetornoDocTec";//targetFrame;
                document.forms["abertura"].submit();
                document.forms["abertura"].reset();
            } else {
                alert("A Data de Abertura deve ser anterior\na Data Estimada de Fechamento!");
            }
            

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
