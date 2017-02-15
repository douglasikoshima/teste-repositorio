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

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="tiposVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.WFDocumentoTecnicoTipoVOArray"/>
<bean:define id="estadosVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.WFDocumentoTecnicoEstadoVOArray"/>
<bean:define id="procedenciasVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.procedenciaVOArray"/>

<acesso:controlHiddenItem nomeIdentificador="wor_dato_verpagina">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<form name="filtrar" action="documentoAssociadoPesquisarTodos.do">
    <tr valign="top">
        <td width="5%">Tipo:</td>
        <td width="20%">
            <html:select name="form" property="WFDocumentoTecnicoPesquisaVO.idDocTecTipo" style="width=100px">
                <html:option value="" key="WFDocumentoTecnicoPesquisaVO.idDocTecTipo">&nbsp;</html:option>
                <html:options collection="tiposVO" property="idDocTecTipo" labelProperty="dsDocTecTipo"/>
            </html:select>
        </td>
        <td width="5%">Número:</td>
        <td width="20%">
            <html:text name="form" property="WFDocumentoTecnicoPesquisaVO.nrDocumentoTecnico" style="width=100"/>
        </td>
        <td width="10%">Abertura:</td>
        <td width="10%">
            <html:text name="form" property="WFDocumentoTecnicoPesquisaVO.dtPeriodo1" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" styleId="dtPeriodo1" style="width=66"/><img id="imgCalendDtAbIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtPeriodo1', '%d/%m/%Y');">
        </td>
        <td width="5%" align="center">Até</td>
        <td width="10%">
            <html:text name="form" property="WFDocumentoTecnicoPesquisaVO.dtPeriodo2" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" styleId="dtPeriodo2" style="width=66"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtPeriodo2', '%d/%m/%Y');">
        </td>
        <td width="15%">
            <acesso:controlHiddenItem nomeIdentificador="wor_dato_pesqui">
            <img style="border:none;" onClick="submitAplicar(); return false" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>            
            </acesso:controlHiddenItem>
        </td>
    </tr>
    <tr valign="top">
        <td>
            Estado:
        </td>
        <td>
            <html:select name="form" property="WFDocumentoTecnicoPesquisaVO.idDocTecEstado" style="width=100px">
                <html:option value="">&nbsp;</html:option>
                <html:options collection="estadosVO" property="idDocTecEstado" labelProperty="dsDocTecEstado"/>
            </html:select>
        </td>
        <td>
            Comentários:
        </td>
        <td colspan="5">
            <html:text name="form" property="WFDocumentoTecnicoPesquisaVO.comentario" style="width=300"/>
        </td>
        <td valign="middle">
            <img style="border:none;" onClick="limparFiltro(); return false" id="btLimparFiltro" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif"/> 
        </td>
    </tr>
</form>
</table>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<form action="documentoAssociadoAssociar.do" name="formAssociar">
    <tr>
        <td width="100%">

            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="725" layoutHeight="52" tableWidth="741" styleId="docTecTodos" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>					
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">No.</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Estado</vivo:tbHeaderColumn>					
                    <vivo:tbHeaderColumn align="center" width="10%" type="data"  >Abertura</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left"   width="15%" type="string">Comentários</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="data"  >Fechamento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left"   width="20%" type="string">Resposta</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="right"  width="10%"  type="string">QPV</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows scroll="false">
                    <logic:iterate id="documentosVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.atendimentoWorkflowTecnicoDocVOArray" indexId="idx">
                    <vivo:tbRow key="documento">
                        <vivo:tbRowColumn>
                            <input type="checkbox" name=documentosId value='<bean:write name="documentosVO" property="idDocumentoTecnico"/>'>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="documentosVO" property="dsTipoDocumentoProcesso"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="documentosVO" property="nrDocumento"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="documentosVO" property="dsEstadoDocumentoProcesso"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="documentosVO" property="dtAbertura"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <vivo:hint maxLength="20" spaces="S"><bean:write name="documentosVO" property="dsDocumento"/></vivo:hint>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="documentosVO" property="dtFechamento"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <vivo:hint maxLength="20" spaces="S"><bean:write name="documentosVO" property="dsResposta"/></vivo:hint>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <center><bean:write name="documentosVO" property="qtdProcessosVinculados"/></center>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>
        </td>
    </tr>
    <tr>
        <td align="right" valign="top">
            <acesso:controlHiddenItem nomeIdentificador="wor_dato_associ">
            <img hspace="10" onClick="submitAssociar(); return false" style="border:none;" id="btAssociar" src="/FrontOfficeWeb/resources/images/bt_associar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_associar_over.gif"/> 
            </acesso:controlHiddenItem>
        </td>
    </tr>
<html:hidden name="form" property="docTec"/>
</form>
</table>
</acesso:controlHiddenItem>
<script language="JavaScript">
var targetFrame = "ifrmAbas";
<logic:equal name="form" property="docTec" value="1">
targetFrame = "ifrmDocTec";
</logic:equal>
function submitAssociar() {
    documentosId = document.forms["formAssociar"].documentosId;
    docSel = 0;
    if (documentosId != null) {
        if (documentosId.length != null) {
            for (i=0; i<documentosId.length; i++){
                if (documentosId[i].checked) {
                    docSel=1;
                    break;
                }
            }
        } else {
            if (documentosId.checked) {
                docSel=1;
            }
        }
    }
    if (!docSel) {
        alert("Selecione um Documento Técnico antes de Associar ao Atendimento!");
        return false;
    }
    //Liga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    document.forms["formAssociar"].method = "POST";
    document.forms["formAssociar"].target = targetFrame;
    document.forms["formAssociar"].action = "documentoAssociadoAssociar.do";
    document.forms["formAssociar"].submit();   
    return true;     
}
function submitAplicar() {
    dt1 = document.forms[0].elements["WFDocumentoTecnicoPesquisaVO.dtPeriodo1"].value;
    dt2 = document.forms[0].elements["WFDocumentoTecnicoPesquisaVO.dtPeriodo2"].value;
    if (dt1 == "" || dt2 == "") {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms[0].method = "POST";
        document.forms[0].action = "documentoAssociadoPesquisarTodos.do?pesq=1";
        document.forms[0].submit();
    } else {
        if (validaDataFinal(dt1,dt2)) {
            //Liga animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    
            document.forms[0].method = "POST";
            document.forms[0].action = "documentoAssociadoPesquisarTodos.do?pesq=1";
            document.forms[0].submit();
        } else {
            alert("Data início deve ser anterior que a Data final!");
        }
    }
}
function limparFiltro() {
    document.forms[0].elements["WFDocumentoTecnicoPesquisaVO.idDocTecTipo"].value="";
    document.forms[0].elements["WFDocumentoTecnicoPesquisaVO.nrDocumentoTecnico"].value="";
    document.forms[0].elements["WFDocumentoTecnicoPesquisaVO.dtPeriodo1"].value="";
    document.forms[0].elements["WFDocumentoTecnicoPesquisaVO.dtPeriodo2"].value="";
    document.forms[0].elements["WFDocumentoTecnicoPesquisaVO.comentario"].value="";
    document.forms[0].elements["WFDocumentoTecnicoPesquisaVO.idDocTecEstado"].value="";
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
if (parent.up)
{
    parent.alteraSizeMin();
}
else
{
    parent.alteraSize();
}

DoResizeHeaderTableVivo();
</script>
